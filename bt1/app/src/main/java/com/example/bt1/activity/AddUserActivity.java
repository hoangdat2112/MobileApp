package com.example.bt1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1.R;
import com.example.bt1.model.User;
import com.example.bt1.model.UserList;

public class AddUserActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword, txtFullname, txtEmail;
    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtFullname = findViewById(R.id.txtFullname);
        txtEmail = findViewById(R.id.txtEmail);
        btnAddUser = findViewById(R.id.btnAddUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding the user
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String fullname = txtFullname.getText().toString();
                String email = txtEmail.getText().toString();

                if (!UserList.findAllUsersByUsername(username).isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Username was used. Please try another username!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!UserList.findAllUsersByEmail(email).isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Email was used. Please try another email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(username, password, fullname, email);
                UserList.addUser(user);

                Toast.makeText(AddUserActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();

                // Trả về kết quả thành công và đóng activity
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
