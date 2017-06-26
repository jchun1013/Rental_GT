package com.example.joon1.project_gt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Registration extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("USER");
    FirebaseAuth firebaseAuth;

    private EditText username;
    private EditText name;
    private EditText password;
    private EditText email;
    private EditText phoneNumber;
    private Spinner gender;

    private Button registrationButton;

    private static final String TAG = "EMAIL/PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phone);
        gender = (Spinner) findViewById(R.id.gender);

        String[] genderArray = {"MALE", "FEMALE"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderArray);
        gender.setAdapter(adapter);

        firebaseAuth = FirebaseAuth.getInstance();

        registrationButton = (Button) findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (username.getText().toString().equals("")
                        || name.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || email.getText().toString().equals("")
                        || phoneNumber.getText().toString().equals("")) {

                    Context context = getApplicationContext();
                    CharSequence text = "Need more registration information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()) {
                                    User user = new User(username.getText().toString(), name.getText().toString(), password.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), gender.getSelectedItem().toString());
                                    String id = databaseReference.push().getKey();
                                    databaseReference.child(id).setValue(user);

                                    startActivity(new Intent(Registration.this, Login.class));
                                }
                            }
                        });
            }
        });
    }
}
