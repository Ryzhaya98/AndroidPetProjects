package com.example.db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.db.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    Button btnSign, btnReg;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSign = findViewById(R.id.btnSignIn);
        btnReg = findViewById(R.id.btnReg);
        root = findViewById(R.id.root_element);
        
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }
        });
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInWindow();
            }
        });
        
        
    }

    private void showSignInWindow(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");
        LayoutInflater inflater = LayoutInflater.from(this);
        View signInWindow = inflater.inflate(R.layout.sign_window, null);
        dialog.setView(signInWindow);


        final MaterialEditText email = signInWindow.findViewById(R.id.emailField);
        final MaterialEditText pass = signInWindow.findViewById(R.id.passField);


        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Введите ваш email", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(pass.getText().toString().length()<6) {
                    Snackbar.make(root, "Введите пароль не менее 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainActivity.this, MapActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Error" +e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        dialog.show();

    }



    private void showRegisterWindow() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите данные для регистрации");
        LayoutInflater inflater = LayoutInflater.from(this);
        View regWindow = inflater.inflate(R.layout.register_window, null);
        dialog.setView(regWindow);


        final MaterialEditText email = regWindow.findViewById(R.id.emailField);
        final MaterialEditText pass = regWindow.findViewById(R.id.passField);
        final MaterialEditText name = regWindow.findViewById(R.id.nameField);
        final MaterialEditText phone = regWindow.findViewById(R.id.phoneField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Введите ваш email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(root, "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                    return;
            }
                if(TextUtils.isEmpty(phone.getText().toString())) {
                    Snackbar.make(root, "Введите ваш телефон", Snackbar.LENGTH_SHORT).show();
                    return;
        }
                if(pass.getText().toString().length()<6) {
                    Snackbar.make(root, "Введите пароль не менее 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;

                }

                //Регистрация
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setPass(pass.getText().toString());
                        user.setName(name.getText().toString());
                        user.setPhone(phone.getText().toString());


                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Snackbar.make(root,"Регистрация прошла успешно",Snackbar.LENGTH_SHORT ).show();
                            }
                        });

                    }
                });
            }
        });

        dialog.show();

    }
}
