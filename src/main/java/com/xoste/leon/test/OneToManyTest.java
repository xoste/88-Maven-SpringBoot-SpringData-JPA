package com.xoste.leon.test;

import com.xoste.leon.Application;
import com.xoste.leon.dao.UsersRepository;
import com.xoste.leon.pojo.Roles;
import com.xoste.leon.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;


/**
 * @author Leon
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class OneToManyTest {
    @Autowired
    private UsersRepository usersRepository;

    /**
     * 一对多关系插入数据
     */
    @Test
    public void insertTest() {
        // 创建一个用户
        Users users = new Users();
        users.setUsername("赵六");
        users.setPassword("123456");
        // 创建一个角色
        Roles roles = new Roles();
        roles.setRolesname("管理员");
        // 关联
        roles.getListUsers().add(users);
        users.setRoles(roles);
        this.usersRepository.save(users);
    }

    /**
     * 一对多关系查询数据
     */
    @Test
    public void selectTest() {
        Optional<Users> usersOptional = this.usersRepository.findById(6);
        System.out.println(usersOptional);
        Roles roles = usersOptional.get().getRoles();
        System.out.println(roles.getRolesname());
    }
}