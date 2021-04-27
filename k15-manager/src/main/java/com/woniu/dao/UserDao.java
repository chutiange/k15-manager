package com.woniu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.entity.Perm;
import com.woniu.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao  extends BaseMapper<User> {
    // 根据用户名查
    @Select("select percode from rbac_perms p INNER join rbac_user_perm up on p.id = up.permid INNER join rbac_user u on u.id = up.userid where u.username = #{username}")
    public List<String> findPermsByUsername(String username);
}
