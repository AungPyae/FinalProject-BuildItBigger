package com.udacity.gradle.builditbigger.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private static final String IE_JOKE = "IE_JOKE";

    public static Intent newIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(IE_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(IE_JOKE);

        TextView tvJoke = (TextView) findViewById(R.id.tv_joke);
        tvJoke.setText(joke);
    }
}
