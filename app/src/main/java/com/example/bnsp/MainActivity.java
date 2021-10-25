package com.example.bnsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bnsp.Database.DBHelper;
import com.example.bnsp.Entities.Account;

public class MainActivity extends AppCompatActivity {

    EditText TxUsername, TxPassword;
    Button BtnLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());

        TxUsername = (EditText)findViewById(R.id.txUsername);
        TxPassword = (EditText)findViewById(R.id.txPassword);
        BtnLogin = (Button)findViewById(R.id.btnLogin);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString();
                String password = TxPassword.getText().toString();

//                Account account = new Account();
//                account.setUsername(username);
//                account.setPassword(password);
//                if(dbHelper.createUser(account)){
//                    Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
//                }
                Account account = dbHelper.login(username, password);
                if(account != null){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, BerandaActivity.class);
                    intent.putExtra("account", account);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Username or Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}