package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poscodx.hellospring.vo.UserVo;

/*
 * @RequestMapping 클래스 + 메서드 매핑
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @RequestMapping(value = "/joinform", method = RequestMethod.GET)
  public String join() {
    return "/WEB-INF/views/joinform.jsp";
  }

  @RequestMapping(value = "/join", method = RequestMethod.POST)
  public String join(UserVo vo) {
    System.out.println(vo);
    return "redirect:/"; // to Main
  }

  @ResponseBody
  @RequestMapping("/update")
  public String update(@RequestParam("n") String name) {
    /*
     * 만일 n이라는 이름의 파라미터가 없으면 400 BadRequest return
     */
    return "UserController.update(" + name + ")";
  }

  @ResponseBody
  @RequestMapping("/update2")
  public String update2(
      @RequestParam(value = "n", required = true, defaultValue = "") String name) {
    /*
     * 400 Error 해결: defaultValue=""
     * 또한, Optional로 null 체크 가능
     * 값 체크 가능 (null 유무)
     */
    return "UserController.update(" + name + ")";
  }

  @ResponseBody
  @RequestMapping("/list")
  public String list(@RequestParam(value = "p", required = true, defaultValue = "1") int pageNo) {
    /*
     * pageNo를 1로 default
     */
    return "UserController.list(" + pageNo + ")";
  }
}
