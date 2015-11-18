package pl.java.scalatech.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class SimpleHelperController {


  List<String> names= Lists.newArrayList("slawek","tomelk","amoa");

  @RequestMapping("/helpers")
  String helperNames(Model model) {
      model.addAttribute("names", names);
      return "sample/helper";
  }

  @RequestMapping("/helpers/{name}")
  String helperName(@PathVariable("name")String name,Model model) {
      names.stream().forEach(t->log.info("{}",t));
      String result = names.stream().filter(t->t.equals(name)).findFirst().orElse("default");
      model.addAttribute("name", result);
      log.info("+++  {} : name : {}",result,name);
      return "sample/helperName";
  }


}
