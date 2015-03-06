package appewtc.masterung.ishiharaoic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadioButton, intIndex;
    private MyModel objMyModel;
    private String strChoice[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial Widget
        initialWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //Interface MyModel
        interfaceMyModel();


    }   // onCreate

    private void interfaceMyModel() {

        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View by MyModel
                changeViewByMyModel(myModel.getIntButton());

            }   // event
        });

    }   // interfaceMyModel

    private void changeViewByMyModel(int intTimes) {

        int[] intIshihara = {R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04,
                R.drawable.ishihara_05, R.drawable.ishihara_06,
                R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};

        int[] intChoiceTimes = {R.array.times2, R.array.times2,
                R.array.times3, R.array.times4, R.array.times5,
                R.array.times6, R.array.times7, R.array.times8,
                R.array.times9, R.array.times10};

        //Setup Image
        imvIshihara.setImageResource(intIshihara[intTimes]);

        //Setup Choice
        strChoice = getResources().getStringArray(intChoiceTimes[intTimes]);

        setupChoice();

    } // changeView

    private void setupChoice() {
        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);
    }

    private void radioController() {

        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Sound Effect
                MediaPlayer soundRadio = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                soundRadio.start();

                //Setup RadioButton
                switch (checkedId) {
                    case R.id.radioButton:
                        intRadioButton = 1;
                        break;
                    case R.id.radioButton2:
                        intRadioButton = 2;
                        break;
                    case R.id.radioButton3:
                        intRadioButton = 3;
                        break;
                    case R.id.radioButton4:
                        intRadioButton = 4;
                        break;
                    default:
                        intRadioButton = 0;
                        break;
                }   // switch


            }   // event
        });

    }   // radioController

    private void buttonController() {
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sound Effect
                MediaPlayer soundButton = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                soundButton.start();

                //Check Answer
                checkAnswer();


            }   // event
        });
    }   // buttonController

    private void checkAnswer() {

        if (intRadioButton == 0) {
            Toast.makeText(MainActivity.this, "กรุณาตอบ คำถาม นะคะ", Toast.LENGTH_LONG).show();
        } else {

            //Check Times
            checkTimes();

        }

    }   // checkAnswer

    private void checkTimes() {

        if (intIndex == 9) {

            //Intent to ShowScore Activity
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
            startActivity(objIntent);
            finish();

        } else {

            //Controller Call View
            txtQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");
            intIndex += 1;

            //Sent Value to MyModel
            objMyModel.setIntButton(intIndex);


        }   // if

    }   // checkTimes

    private void initialWidget() {
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.radChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);
    }   // initialWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
