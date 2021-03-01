package com.example.brassheroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Button startBtn, contBtn;

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
                //Button startBtn = findViewById(R.id.newGameBtn);
                openNewGame();
        }


            public void openNewGame() {
                //use to change views
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
    }


    public void clickContBtn(View view){
        contGame();
    }

    public void contGame(){
        Intent intent = new Intent(MainActivity.this,MainActivity5.class);
        startActivity(intent);
    }

}
