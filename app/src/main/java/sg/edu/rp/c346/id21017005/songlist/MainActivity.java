package sg.edu.rp.c346.id21017005.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etName, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        int selectedRgStar = rgStars.getCheckedRadioButtonId();
        btnInsert = findViewById(R.id.insertBtn);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitle.getText().toString()=="" ||
                    etName.getText().toString()==""||
                    etYear.getText().toString()==""||
                    rgStars.getCheckedRadioButtonId()==-1){
                    Toast.makeText(MainActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper db = new DBHelper(MainActivity.this);
                    String title = etTitle.getText().toString();
                    String name = etName.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int stars = 0;
                    int selectedRadioButtonId = rgStars.getCheckedRadioButtonId();
                    if(selectedRadioButtonId==1){
                        stars=1;
                    }
                    else if(selectedRadioButtonId==2){
                        stars=2;
                    }
                    else if(selectedRadioButtonId==3){
                        stars=3;
                    }
                    else if(selectedRadioButtonId==4){
                        stars=4;
                    }
                    else if(selectedRadioButtonId==5){
                        stars=5;
                    }
                    db.insertSong(title,name,year,stars);
                    etTitle.setText("");
                    etName.setText("");
                    etYear.setText("");
                    rgStars.clearCheck();
                    String toastMessage = "Added Successfully:\n"+title+"\n"+name+"\n"+year+"\n"+stars+" stars";
                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> lvValues = db.getSongContent();
                finish();
            }
        });
    }
}