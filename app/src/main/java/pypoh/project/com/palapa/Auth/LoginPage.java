package pypoh.project.com.palapa.Auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pypoh.project.com.palapa.Main.MainActivity;
import pypoh.project.com.palapa.R;

public class LoginPage extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    FirebaseUser user;
    FirebaseAuth mAuth;
    private ImageButton mBtnLogin;
    private ImageButton mBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mEditTextEmail = (EditText) findViewById(R.id.login_username);
        mEditTextPassword = (EditText) findViewById(R.id.login_password);

        mBtnLogin = (ImageButton) findViewById(R.id.masuk_btn);
        mBtnRegister = (ImageButton) findViewById(R.id.daftar_btn);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            navigateToMainPage();
        }
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                handleLogin(email, password);


            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegister();
            }
        });


    }

    private void navigateToMainPage() {
        Intent intent = new Intent(LoginPage.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginPage.this, RegisterPage.class);
        startActivity(intent);
    }

    private void handleLogin(String email, String password) {

        String emailCheck = mEditTextEmail.getText().toString();
        String passwordCheck = mEditTextPassword.getText().toString();

        if (emailCheck.equals("")) {
            mEditTextEmail.setError("Email can't be blank");
        } else if (passwordCheck.equals("")) {
            mEditTextPassword.setError("Password can't be blank");
        }  else {

            mAuth.signInWithEmailAndPassword(emailCheck,passwordCheck).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginPage.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        navigateToMainPage();
                    } else {
                        Toast.makeText(LoginPage.this, "Gagal Masuk", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


}
