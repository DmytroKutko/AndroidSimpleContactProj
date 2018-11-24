package ua.com.zzz.dmytrokutko.simplecontactproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etPhone, etWeb, etLocation;
    ImageView ivHappy, ivNormal, ivSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWeb = findViewById(R.id.etWeb);
        etLocation = findViewById(R.id.etLocation);

        ivHappy = findViewById(R.id.ivHappy);
        ivNormal = findViewById(R.id.ivNormal);
        ivSad = findViewById(R.id.ivSad);

        ivSad.setOnClickListener(this);
        ivNormal.setOnClickListener(this);
        ivHappy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() ||
                etWeb.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("phone", etPhone.getText().toString().trim());
            intent.putExtra("web", etWeb.getText().toString().trim());
            intent.putExtra("location", etLocation.getText().toString().trim());

            switch (v.getId()) {
                case R.id.ivHappy:
                    intent.putExtra("mood", "happy");
                    break;
                case R.id.ivNormal:
                    intent.putExtra("mood", "normal");
                    break;
                case R.id.ivSad:
                    intent.putExtra("mood", "sad");
                    break;
            }

            setResult(RESULT_OK, intent);
            CreateContact.this.finish();
        }


    }
}
