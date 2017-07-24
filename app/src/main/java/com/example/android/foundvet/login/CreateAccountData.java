package com.example.android.foundvet.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.android.foundvet.R;

public class CreateAccountData extends AppCompatActivity {

    private RadioButton male;
    private RadioButton female;
    private Button nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_data);

        male = (RadioButton) findViewById(R.id.radioMale);
        female = (RadioButton) findViewById(R.id.radioFemale);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(female.isChecked()) {
                    female.setChecked(false);
                }
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(male.isChecked()) {
                    male.setChecked(false);
                }
            }
        });
    }

    public void pressedRegister(View view) {
        nextButton = (Button) findViewById(R.id.next_register_second);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountData.this, CreateAccountAddress.class);
                startActivity(intent);
            }
        });
    }
}
