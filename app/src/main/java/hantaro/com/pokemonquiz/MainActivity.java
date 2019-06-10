package hantaro.com.pokemonquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;

    TextView tvQuestionTrack;
    TextView tvPoints;

    ImageView ivPokemon;

    Button playAgainButton;

    EditText mEditText;
    Button btOk;

    int currentQuestion;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentQuestion = 0;
        points = 0;

        playAgainButton = findViewById(R.id.bt_play_again);

        tvFour = findViewById(R.id.tv_four);
        tvOne = findViewById(R.id.tv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvThree = findViewById(R.id.tv_three);

        ivPokemon = findViewById(R.id.pokemon_image);

        tvThree.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvFour.setOnClickListener(this);

        tvPoints = findViewById(R.id.tv_points);
        tvQuestionTrack = findViewById(R.id.tv_question_track);

        btOk = findViewById(R.id.bt_ok);
        mEditText = findViewById(R.id.edit_text);

        tvPoints.setText(getResources().getString(R.string.points) + points);
        tvQuestionTrack.setText((currentQuestion)+"/8");

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion = 0;
                points = 0;
                btOk.setVisibility(View.VISIBLE);
                mEditText.setVisibility(View.VISIBLE);
                GridLayout gridLayout = findViewById(R.id.questions_container);
                gridLayout.setVisibility(GONE);
                populateGame();
                playAgainButton.setVisibility(GONE);
                ivPokemon.setImageResource(R.drawable.eevee);
                tvPoints.setText(getResources().getString(R.string.points) + points);
                tvQuestionTrack.setText((currentQuestion)+"/8");

            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mEditText.getText().toString().toUpperCase().equals("EVEE")){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.question_right), Toast.LENGTH_SHORT).show();
                    points++;

                }else{
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.question_wrong), Toast.LENGTH_SHORT).show();
                }
                btOk.setVisibility(GONE);
                mEditText.setVisibility(GONE);
                GridLayout gridLayout = findViewById(R.id.questions_container);
                gridLayout.setVisibility(View.VISIBLE);
                populateGame();
                updateScore();

            }
        });


    }


    @Override
    public void onClick(View v) {
        if(currentQuestion < 6) {
        if(v instanceof TextView){
            if(((TextView) v).getText().toString().equals(Helper.getQuizText[currentQuestion][0].toUpperCase())){
                Toast.makeText(this, getResources().getString(R.string.question_right), Toast.LENGTH_SHORT).show();
                points++;


            }else{
                Toast.makeText(this, getResources().getString(R.string.question_wrong), Toast.LENGTH_SHORT).show();
            }
        }
            currentQuestion++;

            populateGame();
        }else{
            playAgainButton.setVisibility(View.VISIBLE);
        }
        updateScore();
    }

    private void populateGame(){
       String text [] = Helper.getQuizText[currentQuestion];
       tvOne.setText(text[1].toUpperCase());
       tvTwo.setText(text[2].toUpperCase());
       tvThree.setText(text[3].toUpperCase());
       tvFour.setText(text[4].toUpperCase());

       ivPokemon.setImageResource(Helper.getPokemonImage[currentQuestion]);
    }

    public void updateScore(){
        tvPoints.setText(getResources().getString(R.string.points) + points);
        tvQuestionTrack.setText((currentQuestion + 1)+"/8");
    }
}
