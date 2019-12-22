package com.dongpo.first_boot.controller;

import com.dongpo.first_boot.domain.Question;
import com.dongpo.first_boot.domain.User;
import com.dongpo.first_boot.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String publishAdd(Question question, HttpServletRequest request, Model model){
        //设置时间
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        //获取当前用户
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if(question.getTitle() == null || "".equals(question.getTitle())){
            model.addAttribute("error","标题不能为空");
            return "/publish";
        }
        if(question.getDescription() == null || "".equals(question.getDescription())){
            model.addAttribute("error","描述不能为空");
            return "/publish";
        }
        if(user == null){
            return "/publish";
        }
        question.setCreator(user.getId());
        questionMapper.insert(question);
        return "redirect:/";
    }
}
