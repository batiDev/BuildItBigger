package com.vel9studios.levani.jokes;

import com.google.gson.Gson;
import java.util.Map;


public class JokeBO {

    private static final String LOG_TAG = JokeBO.class.getSimpleName();

    public String getJoke(){
        return getRandomJoke();
    }

    private String getRandomJoke(){

        int factorySize = JokeDAO.getFactorySize();

        Gson gson = new Gson();
        Map<String,Object> joke = JokeDAO.getJoke(Utils.randInt(0,factorySize - 1));

        return gson.toJson(joke);
    }
}