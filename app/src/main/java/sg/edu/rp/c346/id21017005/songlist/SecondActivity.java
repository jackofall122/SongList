package sg.edu.rp.c346.id21017005.songlist;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lvShow;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        lvShow = findViewById(R.id.lv);
        btnBack = findViewById(R.id.btnBack);
        Intent receivedIntent = getIntent();
        // Initialize the DBHelper
        DBHelper dbHelper = new DBHelper(this);

        // Retrieve the list of Song objects from the database using DBHelper
        ArrayList<Song> songList = dbHelper.getTasks();


        if (songList != null && !songList.isEmpty()) {
            // Create an ArrayAdapter to bind the data to the ListView with customized layout
            ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                    }

                    Song song = getItem(position);

                    // Customize the text displayed in the ListView item
                    String itemText = "Name: " + song.getSingers() + "\n" +
                            "Title: " + song.getTitle() + "\n" +
                            "Year: " + song.getYear() + "\n" +
                            "Rating: " + song.getStars()+"\n\n";

                    ((TextView) convertView).setText(itemText);

                    return convertView;
                }
            };

            lvShow.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No songs found in the database", Toast.LENGTH_SHORT).show();
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
