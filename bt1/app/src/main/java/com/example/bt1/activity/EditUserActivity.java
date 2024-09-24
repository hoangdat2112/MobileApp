package com.example.bt1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;


import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1.R;
import com.example.bt1.model.User;
import com.example.bt1.model.UserList;

public class EditUserActivity extends AppCompatActivity {

    private EditText txtEditUsername, txtEditPassword, txtEditFullname, txtEditEmail, txtEditDateOfBirth;
    private Spinner spinnerGender;
    private Button btnReset, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        txtEditUsername = findViewById(R.id.txtEditUsername);
        txtEditPassword = findViewById(R.id.txtEditPassword);
        txtEditFullname = findViewById(R.id.txtEditFullname);
        txtEditEmail = findViewById(R.id.txtEditEmail);
        txtEditDateOfBirth = findViewById(R.id.txtEditDateOfBirth);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnReset = findViewById(R.id.btnReset);
        btnSave = findViewById(R.id.btnSave);

        // Set up the gender spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        User user = (User) getIntent().getSerializableExtra("user");

        assert user != null;
        txtEditUsername.setText(user.getUsername());
        txtEditPassword.setText(user.getPassword());
        txtEditFullname.setText(user.getFullname());
        txtEditEmail.setText(user.getEmail());

        // Chuyển đổi Date thành chuỗi định dạng dd/MM/yyyy để hiển thị
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(user.getDateOfBirth());
        txtEditDateOfBirth.setText(formattedDate);

        // Set the spinner selection based on the user's gender
        int genderPosition = adapter.getPosition(user.getGender());
        spinnerGender.setSelection(genderPosition);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditUsername.setText(user.getUsername());
                txtEditPassword.setText(user.getPassword());
                txtEditFullname.setText(user.getFullname());
                txtEditEmail.setText(user.getEmail());
                txtEditDateOfBirth.setText(formattedDate);
                spinnerGender.setSelection(genderPosition);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtEditUsername.getText().toString();
                String newPassword = txtEditPassword.getText().toString();
                String newFullname = txtEditFullname.getText().toString();
                String newEmail = txtEditEmail.getText().toString();
                String newDateOfBirth = txtEditDateOfBirth.getText().toString();
                String newGender = spinnerGender.getSelectedItem().toString();

                if (!UserList.findAllUsersByEmail(newEmail).isEmpty() && !newEmail.equals(user.getEmail())) {
                    Toast.makeText(EditUserActivity.this, "Email was used. Please try another email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi phương thức updateUser để cập nhật thông tin
                UserList.updateUser(username, newPassword, newFullname, newEmail, newDateOfBirth, newGender);

                Toast.makeText(EditUserActivity.this, "User information updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}