package com.bingnerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
	private boolean mAnswerIsTrue;
	private Button mShowAnswerButton;
	private TextView mAnswerTextView; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		setAnswerShownResult(false);
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mAnswerIsTrue)
					mAnswerTextView.setText(R.string.true_button);
				else
					mAnswerTextView.setText(R.string.false_button);
				setAnswerShownResult(true);
			}
		});
		
	}
	
	private void setAnswerShownResult(boolean isResultShown){
		Intent data = new Intent();
		data.putExtra(this.EXTRA_ANSWER_SHOWN, isResultShown);
		setResult(RESULT_OK, data);
		return;
	}
}
