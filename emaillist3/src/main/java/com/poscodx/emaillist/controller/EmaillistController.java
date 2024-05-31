package com.poscodx.emaillist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.poscodx.emaillist.repository.EmaillistRepository;
import com.poscodx.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
  @Autowired
  private EmaillistRepository emaillistRepostiroy;

  @RequestMapping("/") // /emaillist3/ 으로 들어오는 모든 요청
  public String index(Model model) {
    // List<EmaillistVo> list = new EmaillistRepository().findAll(); 의존성 주입 필요
    List<EmaillistVo> list = emaillistRepostiroy.findAll();
    model.addAttribute("list", list);
    return "index";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "add";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(EmaillistVo vo) {
    System.out.println(vo);
    emaillistRepostiroy.insert(vo);
    return "redirect:/";
  }
}
