package pl.java.scalatech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.java.scalatech.web.controller.HelloWorldController;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {
    @Mock
    org.slf4j.Logger log;
    @Mock
    MessageSource messageSource;

    @InjectMocks
    HelloWorldController hwc = new HelloWorldController();

    @Test
    public void test() {
        Mockito.when(messageSource.getMessage(Mockito.anyString(), Mockito.anyObject(), Mockito.any(Locale.class))).thenReturn("hello world !");
        Mockito.doNothing().when(log).info(Mockito.anyString());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(hwc).build();
        try {
            mockMvc.perform(get("/hello")).andExpect(view().name("hello1"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
