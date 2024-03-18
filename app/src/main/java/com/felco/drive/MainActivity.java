package com.felco.drive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.felco.drive.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sPref;

    final String keyPhone = "phoneNumber";
    final String keyName = "phoneName";
    final String keySName = "phoneSName";

    private void saveText(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(keyPhone, binding.editTextPhone.getText().toString());
        editor.putString(keyName, binding.editTextName.getText().toString());
        editor.putString(keySName, binding.editTextSName.getText().toString());
        editor.commit();
    }

    private void loadText(){
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(keyPhone,"");
        binding.editTextPhone.setText(savedText);
        savedText = sPref.getString(keyName,"");
        binding.editTextName.setText(savedText);
        savedText = sPref.getString(keySName,"");
        binding.editTextSName.setText(savedText);
    }
    String TAG = "Taxi_Main";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sPref = getPreferences(MODE_PRIVATE);
        loadText();

        Intent intent = new Intent(this, SecondActivity.class);
        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("PhoneNumber",binding.editTextPhone.getText().toString());
                intent.putExtra("Name",binding.editTextName.getText().toString());
                intent.putExtra("Surname",binding.editTextSName.getText().toString());
                saveText();
                startActivity(intent);
            }
        });


        if (sPref.contains("phoneNumber")){
            binding.btnReg.setText("Login");
        }

    }
}