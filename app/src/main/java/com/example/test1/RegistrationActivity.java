package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText mobile;
    EditText email;
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        firstName = findViewById(R.id.fname);
        lastName = findViewById(R.id.lname);
        mobile = findViewById(R.id.mob);
        email = findViewById(R.id.gmail);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        //ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        //Sprite doubleBounce = new DoubleBounce();
        //progressBar.setIndeterminateDrawable(doubleBounce);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();


            }
        });


    }

    void  checkDataEntered(){
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }


        if (isEmpty(mobile)) {
            mobile.setError("Contact Number is required!");
        }

        if (!isEmail(email)) {
            email.setError("Enter valid email!");
        }





    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }



    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


}