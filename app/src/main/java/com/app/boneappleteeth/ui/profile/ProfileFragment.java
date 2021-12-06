package com.app.boneappleteeth.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.AccountRepository;
import com.app.boneappleteeth.AccountServices;
import com.app.boneappleteeth.DeleteAccountModel;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResponseModel;
import com.app.boneappleteeth.ui.editprofile.EditProfileActivity;
import com.app.boneappleteeth.ui.login.LoginActivity;
import com.app.boneappleteeth.ui.register.RegisterActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    TextView tv_name;
    TextView tv_sosmed;
    TextView tv_alamat;
    private SharedPreferences sharedPreferences;
    final String PREF_NAME = "ACCOUNT";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;
    private Button buttonEdit;

    Button btnclose;
    AlertDialog.Builder builder;



    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    public void openLogout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void openEditProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("account", "");
        AccountModel account = gson.fromJson(json, AccountModel.class);
        Log.d("NAME", account.getUsername());

        tv_name = inf.findViewById(R.id.tv_name);
        tv_sosmed = inf.findViewById(R.id.tv_sosmed);
        tv_alamat = inf.findViewById(R.id.tv_alamat);
        Log.d("Alamat", account.getAlamat());
        tv_name.setText(account.getUsername());
        tv_sosmed.setText(account.getEmail());
        tv_alamat.setText(account.getAlamat());

        button = (Button) inf.findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                preferencesEditor.putBoolean("isLoggedIn", false);
                preferencesEditor.putString("account", "");
                preferencesEditor.apply();

                openLogout();
            }
        });

        buttonEdit = (Button) inf.findViewById(R.id.edit_profile);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfile();
            }
        });

        btnclose =  (Button) inf.findViewById(R.id.tv_label_delete);
        builder = new AlertDialog.Builder(getActivity());

        btnclose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                builder.setTitle("Alert")
                .setMessage("Apakah Anda yakin ingin menghapus akun?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                delete();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

        return inf;
    }

    void delete(){
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("account", "");
        AccountModel account = gson.fromJson(json, AccountModel.class);



        DeleteAccountModel deleteAccountModel = new DeleteAccountModel(account.getUsername());

        AccountServices services = AccountRepository.create();
        services.delete(deleteAccountModel).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("200")){
                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putBoolean("isLoggedIn", false);
                    preferencesEditor.putString("account", "");
                    preferencesEditor.apply();
                    openLogout();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}