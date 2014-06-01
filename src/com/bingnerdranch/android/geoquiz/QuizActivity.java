package com.bingnerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPreviousButton;
	private TextView mQuestionTextView;
	private Button mCheat;
	private int mCurrentIndex = 0;
	private TrueFalse mQuestionBank[];
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private boolean mIsCheater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate called");

		if(savedInstanceState!=null && !savedInstanceState.isEmpty())
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

		setContentView(R.layout.activity_quiz);

		mQuestionBank = new TrueFalse[]{
				new TrueFalse(R.string.question_oceans, true),
				new TrueFalse(R.string.question_mideast, false),
				new TrueFalse(R.string.question_africa, false),
				new TrueFalse(R.string.question_americas, true),
				new TrueFalse(R.string.question_asia, true),
		};

		mTrueButton = (Button) findViewById(R.id.true_button);
		mFalseButton = (Button) findViewById(R.id.false_button);
		mNextButton = (ImageButton) findViewById(R.id.next_button);
		mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		mCheat = (Button) findViewById(R.id.cheat_button);

		updateQuestion();

		mQuestionTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				QuizActivity.this.mCurrentIndex = ++mCurrentIndex%5;
				updateQuestion();
			}
		});

		mNextButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				QuizActivity.this.mCurrentIndex = ++mCurrentIndex%5;
				updateQuestion();
			}

		});

		mPreviousButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				QuizActivity.this.mCurrentIndex = (mCurrentIndex+9)%5;
				updateQuestion();
			}
		});

		mTrueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);	
			}
		});

		mCheat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, mQuestionBank[mCurrentIndex].isTrueQuestion());
				startActivityForResult(i,0);

			}
		});

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.d(TAG,"onSaveInstanceState is called");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	private void updateQuestion(){
		int question = mQuestionBank[mCurrentIndex].getQuestion(); 
		mQuestionTextView.setText(question);
		return;
	}

	private void checkAnswer(Boolean userAnswer){
		if(mIsCheater)
			Toast.makeText(QuizActivity.this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
		
		if(mQuestionBank[mCurrentIndex].isTrueQuestion() == userAnswer)
			Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
		return;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0 && resultCode == RESULT_OK && data != null){
				mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);								
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart called");
	}

	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause called");
	}

	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume called");
	}

	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop called");
	}

	public void onDestory(){
		super.onDestroy();
		Log.d(TAG, "onDestory called");
	}

}
