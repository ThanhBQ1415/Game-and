package com.rose.tetris;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        Button btnEasy = findViewById(R.id.btneasy);
        Button btnNormal = findViewById(R.id.btnnormal);
        Button btnHard = findViewById(R.id.btnhard);

        // Xử lý nút Easy
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("easy");
            }
        });

        // Xử lý nút Normal
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("normal");
            }
        });

        // Xử lý nút Hard
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("hard");
            }
        });
    }

    private void startGame(String difficulty) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}
