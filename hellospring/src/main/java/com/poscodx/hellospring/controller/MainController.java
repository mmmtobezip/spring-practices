package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
  // Main Page
  @ResponseBody
  @RequestMapping({"/", "/main", "/a/b/c/main"})
  public String main() {
    return "MainController.main()";
  }
}
