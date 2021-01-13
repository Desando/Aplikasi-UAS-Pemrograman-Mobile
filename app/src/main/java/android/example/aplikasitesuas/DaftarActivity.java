package android.example.aplikasitesuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DaftarActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPassword;
    TextView tvLogin;
    Button btnDaftar;
    DatabaseLogin db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvLogin = findViewById(R.id.tvLogin);
        btnDaftar = findViewById(R.id.btnDaftar);
        db = new DatabaseLogin(this);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                String cnf_pwd = etConfirmPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if (val > 0){
                        Toast.makeText(DaftarActivity.this, "Anda telah terdaftar!", Toast.LENGTH_SHORT).show();
                        Intent intentMoveToLogin = new Intent(DaftarActivity.this, LoginActivity.class);
                        startActivity(intentMoveToLogin);
                    }else{
                        Toast.makeText(DaftarActivity.this, "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DaftarActivity.this, "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}