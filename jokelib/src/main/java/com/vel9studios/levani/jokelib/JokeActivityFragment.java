package com.vel9studios.levani.jokelib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    private ArrayAdapter<String> mJokeAdapter;
    private ArrayList<String> mJokeContent;
    private static final String SAVE_KEY_JOKE_CONTENT = "jokeContent";

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);

        mJokeAdapter =
                new ArrayAdapter<>(
                        getActivity(), // The current context (this activity)
                        R.layout.joke_item, // The name of the layout ID.
                        R.id.joke_item_textview, // The ID of the textview to populate.
                        new ArrayList<String>());

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVE_KEY_JOKE_CONTENT)) {
            mJokeContent = savedInstanceState.getStringArrayList(SAVE_KEY_JOKE_CONTENT);
            if (mJokeContent != null)
                mJokeAdapter.addAll(mJokeContent);
        }

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.joke_listview);
        listView.setAdapter(mJokeAdapter);

        return rootView;
    }

    public void onDataUpdated(ArrayList<String> jokeContent){
        mJokeAdapter.clear();

        if (jokeContent != null){
            mJokeContent = jokeContent;
            mJokeAdapter.addAll(mJokeContent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(SAVE_KEY_JOKE_CONTENT, mJokeContent);
        super.onSaveInstanceState(outState);
    }
}
