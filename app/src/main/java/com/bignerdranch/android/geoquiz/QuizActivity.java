package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank= new Question[]{
        new Question(R.string.question_australia, true),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_oceans, true),


    };
    private int mCurrentIndex = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_quiz);


        mQuestionTextView= (TextView)findViewById(R.id.question_text_view);
        mTrueButton =(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View v) {
                checkAnswer(true);

           }

         });
        mFalseButton =(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                mCurrentIndex= (mCurrentIndex + 1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
        mPreviousButton = (Button)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex= (mCurrentIndex -1)%mQuestionBank.length;
                updateQuestion();

            }
        });
        updateQuestion();
    }
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            mCurrentIndex= (mCurrentIndex + 1)%mQuestionBank.length;
            updateQuestion();
        }else {
            messageResId = R.string.incorrect_toast;
        }
       Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();

    }
}







