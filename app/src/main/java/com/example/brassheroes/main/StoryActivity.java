package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;

public class StoryActivity extends AppCompatActivity {

    TextView storyText, storyTitle;

    boolean isFightWon;

    Button skipStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
        rollStory();
    }

    private void rollStory() {
        storyText.setText(loopString());
    }

    private String loopString() {
        String temp="";
        if (isFightWon){
            temp = " you won!\n";
        }
        if (!isFightWon) {
            temp = " not this time man\n";
        }

        for (int i = 0; i < 5; i++) {
            temp += temp;
        }
        return temp;
    }

    private void initControls() {
        isFightWon= getIntent().getBooleanExtra("won",true);

        storyText = findViewById(R.id.storyText);
        storyTitle = findViewById(R.id.storyTitle);

        skipStory = findViewById(R.id.skipStory);
        skipStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}