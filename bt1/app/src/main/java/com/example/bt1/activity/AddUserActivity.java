package com.example.bt1.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt1.R;
import com.example.bt1.model.User;
import com.example.bt1.model.UserList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword, txtFullname, txtEmail, txtDateOfBirth;
    private Spinner spinnerGender;
    private Button btnAddUser;
    private Calendar calendar;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtFullname = findViewById(R.id.txtFullname);
        txtEmail = findViewById(R.id.txtEmail);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnAddUser = findViewById(R.id.btnAddUser);

        calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        // Set up gender spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        txtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding the user
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String fullname = txtFullname.getText().toString();
                String email = txtEmail.getText().toString();
                String dateOfBirth = txtDateOfBirth.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();

                if (username.isEmpty() || password.isEmpty() || fullname.isEmpty() ||
                        email.isEmpty() || dateOfBirth.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!UserList.findAllUsersByUsername(username).isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Username was used. Please try another username!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!UserList.findAllUsersByEmail(email).isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Email was used. Please try another email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date birthDate = null;
                try {
                    birthDate = dateFormatter.parse(dateOfBirth);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(AddUserActivity.this, "Error parsing birth date", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(username, password, fullname, email, birthDate, gender);
                UserList.addUser(user);

                Toast.makeText(AddUserActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();

                // Return success result and close activity
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txtDateOfBirth.setText(dateFormatter.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(AddUserActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
