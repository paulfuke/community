package com.dongpo.first_boot.mapper;

import com.dongpo.first_boot.domain.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,creator,description," +
            "tag,gmt_create,gmt_modified) values(#{title},#{creator},#{description}," +
            "#{tag},#{gmtCreate},#{gmtModified})")
    void insert(Question question);
}
