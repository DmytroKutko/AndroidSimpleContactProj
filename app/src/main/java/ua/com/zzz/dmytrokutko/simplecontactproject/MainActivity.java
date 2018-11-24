package ua.com.zzz.dmytrokutko.simplecontactproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivMood, ivPhone, ivWeb, ivLocation;
    Button btnCreate;
    final int CREATE_CONTACT = 1;
    String name = "", phone = "", web = "", location = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ivMood.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        ua.com.zzz.dmytrokutko.simplecontactproject.CreateContact.class);
                startActivityForResult(intent, CREATE_CONTACT);

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);

            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);

            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0?q=" + location));
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CONTACT){
            if (resultCode == RESULT_OK){
                ivMood.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                phone = data.getStringExtra("phone");
                web = data.getStringExtra("web");
                location = data.getStringExtra("location");
                mood = data.getStringExtra("mood");

                switch (mood){
                    case "happy":
                        ivMood.setImageResource(R.drawable.satisfied);
                        break;
                    case "normal":
                        ivMood.setImageResource(R.drawable.neutral);
                        break;
                    case "sad":
                        ivMood.setImageResource(R.drawable.dissatisfied);
                        break;
                }
            } else {
                Toast.makeText(this, "No data passed through!",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void init(){
        ivMood = findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);
        btnCreate = findViewById(R.id.btnCreate);
    }
}
