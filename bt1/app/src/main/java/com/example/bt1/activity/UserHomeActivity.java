package com.example.bt1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1.R;
import com.example.bt1.model.User;
import com.example.bt1.model.UserList;

public class UserHomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnAddUser, btnSearchUser, btnDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnSearchUser = findViewById(R.id.btnSearchUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            tvWelcome.setText("Hello, " + user.getUsername() + "!");
        }

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, AddUserActivity.class);
                startActivityForResult(intent, 1);  // Request code 1
            }
        });

        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, SearchUserActivity.class);
                intent.putExtra("isDeleteFunction", true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Làm mới dữ liệu khi quay lại từ AddUserActivity
            // Ví dụ: Cập nhật danh sách người dùng hoặc giao diện nếu cần thiết
        }
    }
}
