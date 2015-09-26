package com.vel9studios.levani.builditbigger;

import java.util.ArrayList;

//http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
public interface AsyncResponse {
    void processFinish(ArrayList<String> output);
}
