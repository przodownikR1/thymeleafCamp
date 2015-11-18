package pl.java.scalatech.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloWorldController {

    @Autowired
    MessageSource ms;

    @RequestMapping(value ="/hello")
    public String hello( Locale locale){
         log.info("+++  {}",ms.getMessage("hello.world", null, locale));
         return "hello1";
    }

    @RequestMapping("/helloWorld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "hello2";
    }

}
