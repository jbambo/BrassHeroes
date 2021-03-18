package com.example.brassheroes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgClass1, imgClass2, imgClass3;

    private EditText usernameTxt;

    private Button btnClass1, btnClass2, btnClass3, btnStartGame;

    private Profession playerClass;

    private String username;

    private boolean correctInput, classPicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
    }

    public void initControls() {
        usernameTxt = findViewById(R.id.username);

        imgClass1 = findViewById(R.id.imgKnight);
        imgClass2 = findViewById(R.id.imgPriest);
        imgClass3 = findViewById(R.id.imgWizard);

        btnClass1 = findViewById(R.id.btnClass1);
        btnClass2 = findViewById(R.id.btnClass2);
        btnClass3 = findViewById(R.id.btnClass3);
        btnStartGame = findViewById(R.id.startGame);

        imgClass1.setOnClickListener(this);
        imgClass2.setOnClickListener(this);
        imgClass3.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);



    }

    public boolean check() {
        if (usernameTxt.length() == 0) {
            usernameTxt.setError("Enter your name!");
            return false;
        }
        username = usernameTxt.getText().toString().trim();
        return true;
    }

    public void newGame() {
        correctInput = check();
        if (correctInput && classPicked) {
            Entity player = new Entity(username,playerClass);

            Gson gson = new Gson();

            String saveJson= gson.toJson(player);
            try {
                File file = new File(this.getFilesDir(),"Saved-"+username);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(saveJson);
                bufferedWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
//            Intent intent = new Intent(this, MapActivity.class);
//            intent.putExtra("username", username);
//            intent.putExtra("playerClass", playerClass);
//            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgKnight:
                selectView(imgClass1);
                frameView(btnClass1);

                unselectView(imgClass2);
                unselectView(btnClass2);

                unselectView(imgClass3);
                unselectView(btnClass3);

                classPicked = true;
                playerClass = new Knight();
                break;

            case R.id.imgPriest:
                selectView(imgClass2);
                frameView(btnClass2);

                unselectView(imgClass3);
                unselectView(btnClass3);

                unselectView(imgClass1);
                unselectView(btnClass1);

                classPicked = true;
                playerClass = new Priest();
                break;

            case R.id.imgWizard:
                selectView(imgClass3);
                frameView(btnClass3);

                unselectView(imgClass1);
                unselectView(btnClass1);

                unselectView(imgClass2);
                unselectView(btnClass2);

                classPicked = true;
                playerClass= new Wizard();
                break;

            case R.id.startGame:
                newGame();
        }
    }

    public void selectView(View v) {
        v.setBackgroundResource(R.drawable.gold_selected);
    }

    public void frameView(View v) {
        v.setBackgroundResource(R.drawable.frame_selected);
    }

    public void unselectView(View v) {
        v.setBackgroundResource(R.drawable.btn_gradient);
    }
}