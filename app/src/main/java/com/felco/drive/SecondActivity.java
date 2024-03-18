package com.felco.drive;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.felco.drive.databinding.ActivityMainBinding;
import com.felco.drive.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    String TAG = "Taxi_Second";
    private ActivitySecondBinding binding;

    //SharedPreferences sPref;
    //final String keyPhone = "phoneNumber";
    //final String keyName = "phoneName";
    //final String keySName = "phoneSName";
    //private void loadText(){
    //    sPref = getPreferences(MODE_PRIVATE);
    //    String savedText1 = sPref.getString(keyPhone,"");
    //    String savedText2 = sPref.getString(keyName,"");
    //    String savedText3 = sPref.getString(keySName,"");
    //    binding.usrInfoTextView.setText(String.format("%s %s\n%s",
    //            savedText1,
    //            savedText2,
    //            savedText3));
    //}

    ActivityResultLauncher<Intent> startThirdActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        if(intent!=null){
                            binding.pathTextView.setText("Такси приедет на адрес: " + intent.getStringExtra("From") + " через 10 минут.");
                            binding.btnCall.setEnabled(true);
                        }
                    }else {
                        binding.btnCall.setEnabled(false);
                    }
                }
            }
    );

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.btnCall.setEnabled(false);

        //loadText();

        Intent intent_get_login = getIntent();
        binding.usrInfoTextView.setText(String.format("%s %s\n%s",
                intent_get_login.getStringExtra("Name"),
                intent_get_login.getStringExtra("Surname"),
                intent_get_login.getStringExtra("PhoneNumber")));




        Intent intent_switch = new Intent("android.intent.action.ThirdActivity");
        binding.btnSetPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThirdActivityForResult.launch(intent_switch);
            }
        });

        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Такси уже в пути.",Toast.LENGTH_SHORT).show();
            }
        });

    }
}