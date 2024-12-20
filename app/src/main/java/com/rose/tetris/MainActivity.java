package com.rose.tetris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rose.tetris.models.GameModelFactory;
import com.rose.tetris.models.GameType;
import com.rose.tetris.presenter.GamePresenter;
import com.rose.tetris.presenter.GameTurn;
import com.rose.tetris.views.GameFrame;
import com.rose.tetris.views.GameViewFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("difficulty");
        TextView tvDifficulty = findViewById(R.id.tvDifficulty);
        if (difficulty != null) {
            tvDifficulty.setText("Mode: " + difficulty);
        } else {
            tvDifficulty.setText("Mode: Default");
        }

        Button btnBackToChooseMode = findViewById(R.id.btnBackToChooseMode);
        btnBackToChooseMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseModeActivity.class);
                startActivity(intent);
                finish(); // Kết thúc MainActivity nếu không muốn quay lại
            }
        });

        GameFrame gameFrame = findViewById(R.id.game_container);
        TextView gameScoreText = findViewById(R.id.game_score);
        TextView gameStatusText = findViewById(R.id.game_status);
        Button gameCtlBtn = findViewById(R.id.game_ctl_btn);

        GamePresenter gamePresenter = new GamePresenter();
        gamePresenter.setGameModel(GameModelFactory.newGameModel(GameType.TETRIS));
        gamePresenter.setGameView(GameViewFactory.newGameView(gameFrame, gameScoreText, gameStatusText, gameCtlBtn));
        gamePresenter.init(difficulty);


        Button upBtn = findViewById(R.id.up_btn);
        Button downBtn = findViewById(R.id.down_btn);
        Button leftBtn = findViewById(R.id.left_btn);
        Button rightBtn = findViewById(R.id.right_btn);
        Button fireBtn = findViewById(R.id.fire_btn);

        upBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.UP));
        downBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.DOWN));
        leftBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.LEFT));
        rightBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.RIGHT));
        fireBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.FIRE));

        gameCtlBtn.setOnClickListener(v -> gamePresenter.changeStatus());



    }
}