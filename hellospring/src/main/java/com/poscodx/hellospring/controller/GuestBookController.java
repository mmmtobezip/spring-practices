package com.poscodx.hellospring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @RequestMapping 클래스 단독 매핑 
 * (Spring MVC 4.x지원)
 */
// @Controller
@RequestMapping("/guestbook/*")
public class GuestBookController {

  @ResponseBody
  @RequestMapping
  public String list() {
    return "GuestBookController.list()";
  }

  @ResponseBody
  @RequestMapping
  // /guestbook/delete로 접근 가능, 단독 매핑이라 각 메서드에 path를 주진 않지만 매핑 가능(버전 주의)
  public String delete() {
    return "GuestBookController.delete()";
  }
}
