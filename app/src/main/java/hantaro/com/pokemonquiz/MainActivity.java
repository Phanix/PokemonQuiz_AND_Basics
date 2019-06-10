package hantaro.com.pokemonquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;

    ImageView ivPokemon;

    int currentQuestion;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentQuestion = 0;
        points = 0;

        tvFour = findViewById(R.id.tv_four);
        tvOne = findViewById(R.id.tv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvThree = findViewById(R.id.tv_three);

        ivPokemon = findViewById(R.id.pokemon_image);

        tvThree.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvFour.setOnClickListener(this);

        populateGame();
    }

    @Override
    public void onClick(View v) {
        currentQuestion++;
    }

    private void populateGame(){
       String text [] = Helper.getQuizText[currentQuestion];
       tvOne.setText(text[1].toUpperCase());
       tvTwo.setText(text[2].toUpperCase());
       tvThree.setText(text[3].toUpperCase());
       tvFour.setText(text[4].toUpperCase());

       ivPokemon.setImageResource(Helper.getPokemonImage[currentQuestion]);
    }
}
