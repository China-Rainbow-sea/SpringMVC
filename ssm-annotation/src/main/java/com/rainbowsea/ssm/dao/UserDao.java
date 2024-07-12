package com.rainbowsea.ssm.dao;

import com.rainbowsea.ssm.bean.User;
import org.apache.ibatis.annotations.Select;




public interface UserDao {

    @Select("select * from tbl_user where id = #{id}")
    User selectById(Long id);

}