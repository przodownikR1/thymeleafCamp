package pl.java.scalatech.controller;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;

import pl.java.scalatech.tools.LogbackVerifier;
import pl.java.scalatech.web.controller.HelloWorldController;

public class LogControllerTest {

    @Rule
    public LogbackVerifier logbackVerifier = new LogbackVerifier();

    @Test
    public void shouldLogCatch() {
        HelloWorldController hwc = new HelloWorldController();
        hwc.hello(Locale.getDefault());
        //logbackVerifier.expectMessage(Level.INFO,"test hello");
    }
}
