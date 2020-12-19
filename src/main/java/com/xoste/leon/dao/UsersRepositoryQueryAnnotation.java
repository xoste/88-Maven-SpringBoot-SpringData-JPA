package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Repository @Query
 * @author Leon
 */
public interface UsersRepositoryQueryAnnotation extends JpaRepository<Users, Integer> {

    /**
     * 通过username查询
     * @param username String name
     * @return User
     */
    @Query("from Users where username = :username")
    List<Users> queryByUsernameUseHQL(String username);

    /**
     * 通过username查询
     * @param username String name
     * @return Users
     */
    @Query(value = "select * from t_user where username = ?", nativeQuery = true)
    List<Users> queryByUsernameUseSQL(String username);


    /**
     * 通过id来更新数据
     * @param username String
     * @param id Integer
     */
    @Query("update Users set username = :username where id = :id")
    @Modifying
    void updateUsernameById(String username, Integer id);
}
