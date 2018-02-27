package com.droidrank.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.droidrank.tictactoe.util.GameTracker;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    private int gameCounter = 0;
    private GameTracker mGameTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        block1 = findViewById(R.id.bt_block1);
        block2 = findViewById(R.id.bt_block2);
        block3 = findViewById(R.id.bt_block3);
        block4 = findViewById(R.id.bt_block4);
        block5 = findViewById(R.id.bt_block5);
        block6 = findViewById(R.id.bt_block6);
        block7 = findViewById(R.id.bt_block7);
        block8 = findViewById(R.id.bt_block8);
        block9 = findViewById(R.id.bt_block9);
        result = findViewById(R.id.tv_show_result);
        restart = findViewById(R.id.bt_restart_game);

        initViewsListener();
        mGameTracker = new GameTracker();
    }

    private void initViewsListener() {
        block1.setOnClickListener(this);
        block2.setOnClickListener(this);
        block3.setOnClickListener(this);
        block4.setOnClickListener(this);
        block5.setOnClickListener(this);
        block6.setOnClickListener(this);
        block7.setOnClickListener(this);
        block8.setOnClickListener(this);
        block9.setOnClickListener(this);
        restart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_block1:
                setButtonState(block1);
                break;
            case R.id.bt_block2:
                setButtonState(block2);
                break;
            case R.id.bt_block3:
                setButtonState(block3);
                break;
            case R.id.bt_block4:
                setButtonState(block4);
                break;
            case R.id.bt_block5:
                setButtonState(block5);
                break;
            case R.id.bt_block6:
                setButtonState(block6);
                break;
            case R.id.bt_block7:
                setButtonState(block7);
                break;
            case R.id.bt_block8:
                setButtonState(block8);
                break;
            case R.id.bt_block9:
                setButtonState(block9);
                break;
            case R.id.bt_restart_game:
                doCheckGameState();
                break;

                default:
                    Log.d(TAG, "onClick: Default (Do nothing)");
        }
    }

    private void doCheckGameState() {
        String stateString = restart.getText().toString();

        switch (stateString) {
            case "Start New Game":
                doStartGame();
                break;
            case "Restart Game":
                doShowRestartGameDialog();
                break;
            default:
                Log.d(TAG, "doCheckGameState: Default (Do nothing)");
        }
    }

    private void doStartGame() {
        restart.setText(R.string.restart_button_text_in_middle_of_game);
        mGameTracker.setGameState(GameTracker.GAME_ACTIVE);
    }

    private void doShowRestartGameDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(false);
        alertBuilder.setTitle(R.string.app_name);
        alertBuilder.setMessage(R.string.restart_message);

        alertBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                doRestartGame();
            }
        });

        alertBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    private void doRestartGame() {
        showMessageToast(getString(R.string.game_restarted));
        resetButtons();
    }

    private void resetButtons() {
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
        result.setText("");
    }

    private void setButtonState(Button block) {
        if(mGameTracker.getGameState() == GameTracker.GAME_INACTIVE) {
            showMessageToast(getString(R.string.game_inactive));
            return;
        }

        if (block.getText() == "") {
            if (mGameTracker.isPlayerOne()) {
                block.setText(R.string.player_1_move);
                mGameTracker.setPlayerOne(false);
            } else {
                block.setText(R.string.player_2_move);
                mGameTracker.setPlayerOne(true);
            }

            gameCounter++;
            doCheckGameWinner();
        } else {
            Log.d(TAG, "setButtonState: Already got content");
        }
    }

    private void doCheckGameWinner() {
        if (gameCounter == 9) {
            result.setText(R.string.draw);
            return;
        }
    }

    private void showMessageToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
