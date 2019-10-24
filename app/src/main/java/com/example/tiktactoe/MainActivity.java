package com.example.tiktactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String PLayer_1_o = "x";
    static final String PLayer_2_o = "0";
    boolean playerTurn = true;

    byte[][] board = new byte[3][3];  //board için
    static final byte EMPTY_VALUE = 0;
    static final byte PLayer_1_value = 1;
    static final byte PLayer_2_value = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TableLayout tbl = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {


            TableRow tblrw = (TableRow) tbl.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) tblrw.getChildAt(j);
                btn.setOnClickListener(new CellListener(i, j));

            }

        }


    }

    class CellListener implements View.OnClickListener {
        int row, col;

        public CellListener(int row, int col) {
            this.col = col;
            this.row = row;
        }

        @Override
        public void onClick(View v) {
            if (board[row][col] != EMPTY_VALUE) {
                Toast.makeText(MainActivity.this, "Cell is already occupied", Toast.LENGTH_SHORT).show();
                return;
            }
            byte playerValue = EMPTY_VALUE;
            if (playerTurn) {
                ((Button) v).setText(PLayer_1_o);
                board[row][col] = PLayer_1_value;
                playerValue = PLayer_1_value;

            } else {
                ((Button) v).setText(PLayer_2_o);
                board[row][col] = PLayer_2_value;
                playerValue = PLayer_2_value;
            }
            playerTurn = !playerTurn;
            int gameState = gameEnded(row, col, playerValue);
            if (gameState > 0) {
                Toast.makeText(MainActivity.this,
                        "Player" + gameState + "has won",
                        Toast.LENGTH_SHORT).show();
                setBoardEnabled(false);
            }

        }
    }

    public int gameEnded(int row, int col, byte playerValue) {
        //Check column
        boolean win = true;
        for (int r = 0; r < 3; r++) {
            if (board[r][col] != playerValue) {
                win = false;
                break;
            }
        }
        if (win)
            return playerValue;

        //Check rows
        boolean wiin = true;
        for (int c = 0; c<3; c++){
            if (board [row][c] != playerValue) {
                wiin = false;
                break;
            }
        }
        if (wiin)
            return playerValue;


        //Check Diagonals

        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void setBoardEnabled(boolean enable) {
        TableLayout tbl = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {
            TableRow tblrw = (TableRow) tbl.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) tblrw.getChildAt(j);
                btn.setEnabled(enable);

            }

        }
    }

    public boolean newGame(MenuItem item) {
        setBoardEnabled(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY_VALUE;

            }
        }
        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setText("");

            }

        }
        return true;

    }

}