package com.xoste.leon.test;

import com.xoste.leon.Application;
import com.xoste.leon.dao.RolesRepository;
import com.xoste.leon.pojo.Menus;
import com.xoste.leon.pojo.Roles;
import com.xoste.leon.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 多对多的关联测试
 * @author Leon
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ManyToManyTest {
    @Autowired
    private RolesRepository rolesRepository;
    @Test
    public void insertTest() {
        // 创建角色对象
        Roles roles = new Roles();
        roles.setRolesname("项目经理");
        // 创建菜单对象
        Menus menus = new Menus();
        menus.setFatherid(0);
        menus.setMenusname("项目管理");
        Menus menus2 = new Menus();
        menus2.setFatherid(1);
        menus2.setMenusname("xxx项目管理系统");
        // 关联
        roles.getListMenus().add(menus);
        roles.getListMenus().add(menus2);
        menus.getListRoles().add(roles);
        menus2.getListRoles().add(roles);
        this.rolesRepository.save(roles);
    }

    @Test
    public void selectTest() {
        Optional<Roles> rolesOptional = this.rolesRepository.findById(2);
        String rolesname = rolesOptional.get().getRolesname();
        System.out.println(rolesname);
        /**
         * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
         * com.xoste.leon.pojo.Menus.listRoles, could not initialize proxy - no Session
         */
        Set<Menus> menusList = rolesOptional.get().getListMenus();
        for (Menus menu : menusList) {
            System.out.println(menu);
        }
    }
}
