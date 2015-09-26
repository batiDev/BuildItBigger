/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.levani.builditbigger.jokecloud;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.vel9studios.levani.jokes.JokeBO;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokecloud.builditbigger.levani.vel9studios.com",
                ownerName = "jokecloud.builditbigger.levani.vel9studios.com",
                packagePath = ""
        )
)
//https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public Joke sayHi(@Named("name") String name) {
        Joke response = new Joke();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        JokeBO jokeBO = new JokeBO();
        Joke jokeResponse = new Joke();
        jokeResponse.setData(jokeBO.getJoke());
        return jokeResponse;
    }

}
