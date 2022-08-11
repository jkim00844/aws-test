package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/hello")
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "")
			String name, Model model) {
        model.addAttribute("message", name);
        return "welcome"; //view
    }

    // http://localhost:8080/user?email=hello
    @GetMapping("/user")
    public String getUser(@RequestParam String email, Model model){
        Map userData = userDao.getUser(email);
        model.addAttribute("userEmail", userData.get("email"));
        return "user";
    }

    @RequestMapping("/api/register")
    public ModelAndView doLogin(HttpServletRequest request,
                HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map params = new HashMap();
        params.put("email", email);
        params.put("password", password);
        userDao.insertUser(params);
        return new ModelAndView("redirect:/hello?name="+email);
    }

    @RequestMapping("/register")
    public String hell(HttpServletRequest request,
                HttpServletResponse response) throws Exception {
        return "register";
    }
}