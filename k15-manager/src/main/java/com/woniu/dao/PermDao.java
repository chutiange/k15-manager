package com.woniu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.entity.Perm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PermDao extends BaseMapper<Perm> {
    // 根据用户名查
    @Select("select * from rbac_perms p INNER join rbac_user_perm up on p.id = up.permid INNER join rbac_user u on u.id = up.userid where u.username = #{username} and p.type = \"m\"")
    public List<Perm> findPermsByUsername(String username);
    // 根据用户id删
    @Select("delete from rbac_user_perm where userid = #{userId}")
    public void deletePermsByUserId(Integer userId);
    // 添加权限
    @Insert("insert into rbac_user_perm values(#{userId},#{permId})")
    public void addRight(Map<String,Integer> maps);
    // 根据用户id查权限列表
    @Select("select permid from rbac_user_perm where userid= #{userId}")
    public List<Integer> getPermsByUserId(Integer userId);
}
