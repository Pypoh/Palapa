package pypoh.project.com.palapa.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pypoh.project.com.palapa.R;

public class RegisterPage extends AppCompatActivity {

    private EditText mEditTextUsername;
    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextConfirmPassword;
    private FirebaseAuth mAuth;
    private Button mBtnSignUp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mEditTextEmail = (EditText) findViewById(R.id.daftar_email);
        mEditTextUsername = (EditText) findViewById(R.id.daftar_username);
        mEditTextName = (EditText) findViewById(R.id.daftar_nama);
        mEditTextPassword = (EditText) findViewById(R.id.daftar_password);
        mEditTextConfirmPassword = (EditText) findViewById(R.id.daftar_confirmpassword);
        mBtnSignUp = (Button) findViewById(R.id.daftar_btn);


        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void handleRegister() {
        String user = mEditTextUsername.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String con_pass = mEditTextConfirmPassword.getText().toString();
        String nama = mEditTextName.getText().toString();


        if (email.equals("")) {
            mEditTextEmail.setError("Email can't be blank");
        }  else if (password.equals("")) {
            mEditTextPassword.setError("Password can't be blank");
        } else if (password.length() < 6) {
            mEditTextPassword.setError("At least 6 characters");
        } else if (!con_pass.equals(password)) {
            mEditTextConfirmPassword.setError("Password not match");
        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //FirebaseUser user = mAuth.getCurrentUser();
                        String userID = mAuth.getCurrentUser().getUid();
                        String name = mEditTextName.getText().toString();
                        String email = mAuth.getCurrentUser().getEmail();
                        String username = mEditTextUsername.getText().toString();
                        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

                        DatabaseReference ref1 = mRootRef.child("users").child(userID);

                        ref1.child("email").setValue(email);
                        ref1.child("username").setValue(username);
                        ref1.child("nama").setValue(name);
                        Toast.makeText(RegisterPage.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();

                        navigateToLogin();
                    } else {
                        Toast.makeText(RegisterPage.this, "Gagal Mendaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
        startActivity(intent);
    }


}
