package sergioandrade.blackjack.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import sergioandrade.blackjack.creation.Card;
import sergioandrade.blackjack.gameLogic.Logic;
import sergioandrade.blackjack.R;

public class PlayerVsIA extends PortraitScreen {
    private EditText input;
    private String username;
    private Logic logic;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_i);
        input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Username");
        builder.setIcon(R.drawable.logo);
        builder.setMessage("Please fiil in with your username.");
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                username = input.getText().toString();
                logic.deal();
                TextView playerDisplay = findViewById(R.id.playerScore);
                playerDisplay.setText(logic.getPlayerDisplay());
                TextView iaDisplay = findViewById(R.id.iaScore);
                iaDisplay.setText(logic.getIADisplay());
            }
        });
        builder.show();
        logic = new Logic(this);
    }

    public void draw(View view){
        logic.draw();
        TextView playerDisplay = findViewById(R.id.playerScore);
        playerDisplay.setText(logic.getPlayerDisplay());
        TextView iaDisplay = findViewById(R.id.iaScore);
        iaDisplay.setText(logic.getIADisplay());
    }

    public void stay(View view){
        logic.stay();
    }

    public void disableDraw(){
        Button draw = findViewById(R.id.draw);
        draw.setEnabled(false);
    }

    public void enableDraw(){
        Button draw = findViewById(R.id.draw);
        draw.setEnabled(true);
    }

    public void callWin(){
        Intent intent = new Intent(this, WinActivity.class);
        intent.putExtra("player",username);
        startActivity(intent);
    }

    public void callLose(){
        Intent intent = new Intent(this, LoseActivity.class);
        intent.putExtra("player",username);
        startActivity(intent);
    }

    public void addCardPlayer(Card c){
        int id = c.getDrawable();
        Resources res = getResources();
        Bitmap b = BitmapFactory.decodeResource(res, id);
        ImageView view = new ImageView(this);
        view.setImageBitmap(Bitmap.createScaledBitmap(b, 100, 140, false));
        LinearLayout layout = findViewById(R.id.playerCards);
        layout.addView(view);
    }

    public void addCardIA(Card c){
        int id = c.getDrawable();
        Resources res = getResources();
        Bitmap b = BitmapFactory.decodeResource(res, id);
        ImageView view = new ImageView(this);
        view.setImageBitmap(Bitmap.createScaledBitmap(b, 100, 140, false));
        LinearLayout layout = findViewById(R.id.cardsIA);
        layout.addView(view);
    }
}