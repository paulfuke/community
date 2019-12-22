package com.dongpo.first_boot.service;

import com.dongpo.first_boot.dataTransferObj.QuestionDTO;
import com.dongpo.first_boot.domain.Question;
import com.dongpo.first_boot.domain.User;
import com.dongpo.first_boot.mapper.QuestionMapper;
import com.dongpo.first_boot.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {

        List<Question> list = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if(list != null && list.size()>0){
            for (Question question : list) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.selectById(question.getCreator());
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        }

        return questionDTOS;
    }
}
