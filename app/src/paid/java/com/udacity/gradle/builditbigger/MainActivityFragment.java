package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.builditbigger.jokedisplay.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ViewComponentLoader vcLoader;
    private Button btnTellJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        vcLoader = (ViewComponentLoader) root.findViewById(R.id.vc_loader);
        btnTellJoke = (Button) root.findViewById(R.id.btn_tell_joke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vcLoader.displayLoader();
                showJoke();
            }
        });

        return root;
    }

    private void showJoke() {
        new GetJokeAsyncTask(new GetJokeAsyncTask.GetJokeTaskCallback() {
            @Override
            public void onRetrieveJoke(String joke) {
                Intent intentJokeDisplay = JokeDisplayActivity.newIntent(getActivity(), joke);
                startActivity(intentJokeDisplay);

                vcLoader.dismissLoader();
            }
        }).execute();
    }
}
