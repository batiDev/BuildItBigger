package com.vel9studios.levani.jokes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock joke library, ideally these would live in database. Returns a joke
 * for a given position in local data model.
 *
 * Jokes are stored as string arrays to allow for more flexibility in displaying
 * multi-line jokes, like question/answer, dialogue, etc.
 */
public class JokeDAO {

    private static ArrayList<Map<String,Object>> jokeHolder;

    static {

        jokeHolder = new ArrayList<>();
        jokeHolder.add(createJoke("system", new String[]{"What did the blonde do when she missed bus 22","Took bus 11 twice"}));
        jokeHolder.add(createJoke("system", new String[]{"At what time did the kid go to the dentist."}));

        String[] porkPie = {"Q: What did Ben have for lunch?","A: A sandwich."};
        jokeHolder.add(createJoke("system", porkPie));

        String[] sightSeeing= {"Q: What did the husband and wife do in New York","A: They saw a play."};
        jokeHolder.add(createJoke("system", sightSeeing));
    }

    private static Map<String,Object> createJoke(String source, String[] joke){
        Map<String,Object> jokeMap = new HashMap<>();
        jokeMap.put("source", source);
        jokeMap.put("joke", joke);
        return jokeMap;
    }

    public static Map<String,Object> getJoke(int position){
        return jokeHolder.get(position);
    }

    public static int getFactorySize(){
        return jokeHolder.size();
    }
}
