package com.example.myfirebaseapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etemail, etpassword;
    Button blogin;
    FirebaseAuth auth;
    ProgressDialog app;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        app = new ProgressDialog ( this );

        auth = FirebaseAuth.getInstance ( );

        etemail = (EditText) findViewById ( R.id.etemail );
        etpassword = (EditText) findViewById ( R.id.etpassword );
        blogin = (Button) findViewById ( R.id.blogin );

        blogin.setOnClickListener ( (View.OnClickListener) this );

        blogin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                validate ( );

            }
        } );


    }

    public void validate() {

        String email = etemail.getText ( ).toString ( ).trim ( );
        String password = etpassword.getText ( ).toString ( ).trim ( );

        if ( email.isEmpty ( ) ) {
            Toast.makeText ( MainActivity.this, "Enter ur email please", Toast.LENGTH_LONG ).show ( );
        }

        if ( password.isEmpty ( ) ) {
            Toast.makeText ( MainActivity.this, "Enter ur password please", Toast.LENGTH_LONG ).show ( );
        }

        else {
            app.setMessage ( "Register is in processing please wait...." );
            app.show ( );

            auth.createUserWithEmailAndPassword ( email, password ).addOnCompleteListener ( this, new OnCompleteListener<AuthResult>( ) {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    app.dismiss ( );

                    if ( task.isSuccessful ( ) ) {

                        Toast.makeText ( MainActivity.this, " registered successfully", Toast.LENGTH_LONG ).show ( );
                    }

                }
            } );
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId ( )) {
            case R.id.blogin:
                startActivity ( new Intent( this, Main2Activity.class ) );
                break;
        }
    }
}