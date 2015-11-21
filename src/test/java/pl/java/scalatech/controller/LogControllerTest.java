package pl.java.scalatech.controller;

import java.util.Locale;

import org.junit.Test;

//import pl.java.scalatech.tools.LogbackVerifier;
import pl.java.scalatech.web.controller.HelloWorldController;

public class LogControllerTest {


    @Test
    public void shouldLogCatch() {
        HelloWorldController hwc = new HelloWorldController();
        hwc.hello(Locale.getDefault());
        //logbackVerifier.expectMessage(Level.INFO,"test hello");
    }
}
