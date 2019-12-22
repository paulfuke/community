package com.dongpo.first_boot.controller;

import com.dongpo.first_boot.dataTransferObj.QuestionDTO;
import com.dongpo.first_boot.domain.Question;
import com.dongpo.first_boot.domain.User;
import com.dongpo.first_boot.mapper.UserMapper;
import com.dongpo.first_boot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String testRequest(HttpServletRequest request, Model model){

        //判断cookie里是否有登录的cookie令牌
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            String name = cookie.getName();
            if("token".equals(name)){
                //查询用户
                String token = cookie.getValue();
                User user = userMapper.selectByToken(token);
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
            }
        }
        //准备数据
        List<QuestionDTO> list = questionService.list();
        model.addAttribute("questions",list);
        return "index";
    }
}
