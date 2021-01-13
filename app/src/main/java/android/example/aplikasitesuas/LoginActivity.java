package android.example.aplikasitesuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    TextView tvDaftar;
    Button btnLogin;
    DatabaseLogin db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvDaftar = findViewById(R.id.tvDaftar);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseLogin(this);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true)
                {
                    Toast.makeText(LoginActivity.this, "Login Berhasil!",Toast.LENGTH_SHORT).show();
                    Intent intentMoveToMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMoveToMain);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Gagal!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}