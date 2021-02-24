package com.example.brassheroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //TODO make logo png in the mipmap folder 200x200 px i think
    //TODO title design (png or styled text or combined logo+title as png?)
    //TODO design other screens

    Button startBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the immersive mode(complete fullscreen)
        setContentView(R.layout.activity_main);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        //delcare global var/obj/btn
        startBtn = findViewById(R.id.newGameBtn);


    }
            public void clickStartBtn(View view) {
                Button startBtn = findViewById(R.id.newGameBtn);
                opennewGame();
        }


            public void opennewGame() {
                //use to change views
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
    }

}
