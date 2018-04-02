package pypoh.project.com.palapa.Auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pypoh.project.com.palapa.Main.MainActivity;
import pypoh.project.com.palapa.Main2.Main2Activity;
import pypoh.project.com.palapa.R;

public class LoginPage extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextUsername;
    private TextView mEditTextLupapassword;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private Button mBtnLogin;
    private Button mBtnRegister;
    AlertDialog alertDialog;
    AlertDialog.Builder dialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        dialogBuilder = new AlertDialog.Builder(LoginPage.this);
        LayoutInflater inflater = LoginPage.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        alertDialog = dialogBuilder.create();

        mEditTextUsername = (EditText) findViewById(R.id.login_username);
        mEditTextEmail = (EditText) findViewById(R.id.login_username);
        mEditTextPassword = (EditText) findViewById(R.id.login_password);

        mBtnLogin = (Button) findViewById(R.id.masuk_btn);
        mBtnRegister = (Button) findViewById(R.id.daftar_btn);
        mEditTextLupapassword = (TextView) findViewById(R.id.lupa_password);

        mEditTextUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    mEditTextUsername.clearFocus();
                    mEditTextPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        mEditTextLupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ini intent ke lupa password
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            navigateToMainPage();
        }
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                showProgessDialog();
                //checkUsername(email,password);
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

    public void showProgessDialog() {
        alertDialog.show();
    }

    public void hideProgressDialog() {
        alertDialog.dismiss();
    }

    private void navigateToMainPage() {
        Intent intent = new Intent(LoginPage.this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginPage.this, RegisterPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void handleLogin(String email, String password) {

        String emailCheck = mEditTextEmail.getText().toString();
        String passwordCheck = mEditTextPassword.getText().toString();

        if (emailCheck.equals("")) {
            hideProgressDialog();
            mEditTextEmail.setError("Email can't be blank");
        } else if (passwordCheck.equals("")) {
            hideProgressDialog();
            mEditTextPassword.setError("Password can't be blank");
        }  else {

            mAuth.signInWithEmailAndPassword(emailCheck,passwordCheck).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //hideProgressDialog();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginPage.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        navigateToMainPage();

                    } else {
                        hideProgressDialog();
                        Toast.makeText(LoginPage.this, "Gagal Masuk", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void checkUsername(final String username, final String password) {
        if (username.isEmpty()) {
            mEditTextUsername.setError("Username tidak boleh kosong!");
        } else if (password.isEmpty()) {
            mEditTextPassword.setError("Password tidak boleh kosong");
        } else if (username.isEmpty() && password.isEmpty()){
            mEditTextUsername.setError("Username tidak boleh kosong!");
            mEditTextPassword.setError("Password tidak boleh kosong");
        }else {
            DatabaseReference user = ref.child("users");
            user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        String checkUsername = ds.child("username").getValue(String.class);
                        if (username.equals(checkUsername)){
                            String email = ds.child("email").getValue(String.class);
                            handleLogin(email,password);
                        }
                    }
//                    try{
//                        String email = dataSnapshot.getValue(String.class);
//                        Toast.makeText(LoginPageActivity.this,"Value "+email,Toast.LENGTH_SHORT).show();
////                        handleLogin(email,password);
//                    }catch (Exception e){
//                        Toast.makeText(LoginPageActivity.this,"username atau password salah!",Toast.LENGTH_SHORT)
//                                .show();
//                        e.printStackTrace();
//                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }


}
