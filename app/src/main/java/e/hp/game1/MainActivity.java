package e.hp.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int active=0;
    boolean isGameActive=true;


    //0 means zer_blue
    int []gameState={2,2,2,2,2,2,2,2,2};
    int [][]winGame={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drop(View view) {

        ImageView counter;
        counter = (ImageView) view;

        int temp=Integer.parseInt(counter.getTag().toString());

        if(gameState[temp]==2&&isGameActive ) {
            counter.setTranslationY(-1000f);
            gameState[temp]=active;
            if (active == 0) {
                counter.setImageResource(R.drawable.zer_blue);
                counter.animate().translationYBy(1000f).setDuration(300);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.red_x1);
                counter.animate().translationYBy(1000f).setDuration(300);
                active = 0;
            }
        }

        for(int []win:winGame){
            if(gameState[win[0]]==gameState[win[1]]&&gameState[win[1]]==gameState[win[2]]&&gameState[win[0]]!=2){

                TextView w1=(TextView)findViewById(R.id.textwins);
                isGameActive=false;
                String winner="ZERO";

                if(gameState[win[0]]==1)
                    winner="CROSS";
                w1.setText(winner+" has won!!!");
                LinearLayout popup=(LinearLayout)findViewById(R.id.layout);
                popup.setVisibility(View.VISIBLE);
            }
            else{
                boolean isGameOver=true;
                for(int checkGame:gameState){
                    if(checkGame==2)isGameOver=false;
                }
                if(isGameOver){
                    TextView w1=(TextView)findViewById(R.id.textwins);
                    w1.setText("It's a draw");
                    LinearLayout popup=(LinearLayout)findViewById(R.id.layout);
                    popup.setVisibility(View.VISIBLE);
                }
            }

        }
    }

    public void playAgain(View view) {
        LinearLayout popup=(LinearLayout)findViewById(R.id.layout);
        popup.setVisibility(View.INVISIBLE);
        isGameActive=true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        active=0;
       android.support.v7.widget.GridLayout grid=findViewById(R.id.g1);
       for(int i=0;i<grid.getChildCount();i++){
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }

    }

