package com.nykidxxx.thinteractivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nykidxxx.thinteractivestory.R;
import com.nykidxxx.thinteractivestory.model.Page;
import com.nykidxxx.thinteractivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    private Story mStory = new Story();
    private ImageView imageViewStory;
    private TextView textViewStory;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private String mName;
    private Page mCurrentPage;

    public static final String TAG = StoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.null_name));

        if(mName == null){
            mName = "Friend";
        }
        Log.d(TAG, mName);

        imageViewStory = (ImageView) findViewById(R.id.imageViewStory);
        textViewStory = (TextView) findViewById(R.id.textViewStory);
        buttonChoice1 = (Button) findViewById(R.id.buttonChoice1);
        buttonChoice2 = (Button) findViewById(R.id.buttonChoice2);

        loadPage(0);
    }

    private void loadPage(int choice){
        mCurrentPage = mStory.getPage(choice);

        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        imageViewStory.setImageDrawable(drawable);

        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText, mName);

        textViewStory.setText(pageText);

        if(mCurrentPage.isFinal()) {
            buttonChoice1.setVisibility(View.INVISIBLE);
            buttonChoice2.setText("Play Again");
            buttonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {
            buttonChoice1.setText(mCurrentPage.getChoice1().getText());
            buttonChoice2.setText(mCurrentPage.getChoice2().getText());

            buttonChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });
            buttonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }


}










