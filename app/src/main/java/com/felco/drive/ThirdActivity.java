package com.felco.drive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.felco.drive.databinding.ActivitySecondBinding;
import com.felco.drive.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    String TAG = "Taxi_Third";
    private ActivityThirdBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("From", binding.editTextFrom.getText().toString());
                data.putExtra("To", binding.editTextTo.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}