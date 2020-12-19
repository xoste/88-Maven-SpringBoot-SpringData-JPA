package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository接口的方法名称命名查询
 * @author Leon
 */
public interface UsersRepositoryByName extends Repository<Users, Integer> {
    /**
     * 通过名称查询
     * @param username String
     * @return Users
     * 方法的名称必须遵循驼峰式命名规则，findBy(关键字) + 属性名称（首字母要大写） + 查询条件（首字母要大写）
     */
    List<Users> findByUsername(String username);

    /**
     * 通过用户名和密码查询
     * @param username String
     * @param password String
     * @return Users
     */
    List<Users> findByUsernameAndPassword(String username, String password);

    /**
     * 通过名称的模糊查询
     * @param name String
     * @return Users
     */
    List<Users> findByUsernameLike(String name);
}
