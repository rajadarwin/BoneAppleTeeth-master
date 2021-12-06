package com.app.boneappleteeth.ui.tambahresep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.NewRecipeModel;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResepRepository;
import com.app.boneappleteeth.ResepServices;
import com.app.boneappleteeth.ResponseModel;
import com.app.boneappleteeth.ui.foodpage.FoodActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahResepActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {"Mudah", "Sedang", "Sulit"};

    private int kesulitan;
    EditText et_nama_resep;
    EditText et_resep;
    EditText et_waktu;
    Button btn_submit;

    private SharedPreferences sharedPreferences;
    final String PREF_NAME = "ACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahresep);

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahResepActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        et_nama_resep = findViewById(R.id.et_nama_resep);
        et_resep = findViewById(R.id.et_resep);
        et_waktu = findViewById(R.id.et_waktu);
        btn_submit = findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()) return;
                sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sharedPreferences.getString("account", "");
                AccountModel account = gson.fromJson(json, AccountModel.class);
                String author = account.getUsername();

                NewRecipeModel newRecipeModel = new NewRecipeModel(et_nama_resep.getText().toString(), Integer.parseInt(et_waktu.getText().toString()), kesulitan, et_resep.getText().toString(), account.getUsername());

                ResepServices services = ResepRepository.create();
                services.addResep(newRecipeModel).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if(response.body().getStatus().equals("200")){
                            Toast.makeText(TambahResepActivity.this, "Successfully added new recipe", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(TambahResepActivity.this, "Fail to add new recipe", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }

    private boolean validate() {
        if(et_nama_resep.getText().toString().isEmpty()){
            et_nama_resep.setError("Cannot be empty!");
        }
        if(et_resep.getText().toString().isEmpty()){
            et_resep.setError("Cannot be empty!");
        }
        if(et_waktu.getText().toString().isEmpty()){
            et_waktu.setError("Cannot be empty!");
        }

        return !et_nama_resep.getText().toString().isEmpty() && !et_resep.getText().toString().isEmpty() && !et_waktu.getText().toString().isEmpty();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                kesulitan = 1;
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                kesulitan = 2;
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                kesulitan = 3;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}
