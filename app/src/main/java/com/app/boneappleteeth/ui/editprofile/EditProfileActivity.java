package com.app.boneappleteeth.ui.editprofile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.AccountRepository;
import com.app.boneappleteeth.AccountServices;
import com.app.boneappleteeth.AccountUpdateModel;
import com.app.boneappleteeth.MainActivity;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResponseModel;
import com.app.boneappleteeth.ui.register.RegisterActivity;
import com.app.boneappleteeth.ui.tambahresep.TambahResepActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    Button edit_button;
    EditText et_edit_email;
    EditText et_edit_alamat;

    String email, alamat;
    AccountModel account;

    private SharedPreferences sharedPreferences;
    final String PREF_NAME = "ACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("account", "");
        account = gson.fromJson(json, AccountModel.class);

        edit_button = findViewById(R.id.btn_edit_submit);
        et_edit_alamat = findViewById(R.id.et_edit_alamat);
        et_edit_email = findViewById(R.id.et_edit_email);

        et_edit_alamat.setText(account.getAlamat());
        et_edit_email.setText(account.getEmail());

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamat = et_edit_alamat.getText().toString();
                email = et_edit_email.getText().toString();
                update();
            }
        });

    }

    void update(){
        if(!validate()) return;


        String author = account.getUsername();

        account.setEmail(email);
        account.setAlamat(alamat);
        Gson gson = new Gson();
        String json = gson.toJson(account);

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString("account", json);
        preferencesEditor.apply();

        AccountUpdateModel accountUpdateModel = new AccountUpdateModel(author, email, alamat);
        AccountServices services = AccountRepository.create();
        services.update(accountUpdateModel).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("200")){
                    Toast.makeText(EditProfileActivity.this, "Successfully updated account detail", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error: cannot update account detail : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    boolean validate(){
        boolean isValid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_edit_email.setError("Not a valid email address!");
            isValid = false;
        }
        if (alamat.isEmpty()) {
            et_edit_alamat.setError("Username should not be empty");
            isValid = false;
        }
        if (email.isEmpty()) {
            et_edit_email.setError("Email should not be empty!");
            isValid = false;
        }

        return isValid;
    }
}
