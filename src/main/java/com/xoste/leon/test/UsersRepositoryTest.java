package com.xoste.leon.test;

import com.xoste.leon.Application;
import com.xoste.leon.dao.*;
import com.xoste.leon.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Leon
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UsersRepositoryTest {
    @Resource
    private UsersRepository usersRepository;
    @Autowired
    private UsersRepositoryByName usersRepositoryByName;
    @Autowired
    private UsersRepositoryQueryAnnotation usersRepositoryQueryAnnotation;
    @Autowired
    private UsersRepositoryCrudRepository usersRepositoryCrudRepository;
    @Autowired
    private UsersRepositoryPagingAndSorting usersRepositoryPagingAndSorting;
    @Autowired
    private UsersRepositoryJpaRepository usersRepositoryJpaRepository;
    @Autowired
    private UsersRepositoryJpaSpecificationExecutor usersRepositoryJpaSpecificationExecutor;
    @Test
    public void insertUsers() {
        Users users = new Users();
        users.setUsername("水电费罗额");
        users.setPassword("水电费");
        this.usersRepository.save(users);
    }

    @Test
    public void selectUsersByName() {
        List<Users> usersList = this.usersRepositoryByName.findByUsername("积分个人白色色的共同");
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectUsersByUsernameAndPassword() {
        List<Users> usersList = this.usersRepositoryByName.findByUsernameAndPassword("陈文财", "344355");
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectUsersByUsernameLike() {
        List<Users> usersList = this.usersRepositoryByName.findByUsernameLike("%罗%");
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    /**
     * Repository  @Query注解形式
     * */
    @Test
    public void queryByUsernameUseHQL() {
        List<Users> usersList = this.usersRepositoryQueryAnnotation.queryByUsernameUseHQL("罗劲");
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    @Test
    public void queryByUsernameUseSQL() {
        List<Users> usersList = this.usersRepositoryQueryAnnotation.queryByUsernameUseSQL("罗劲");
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    @Test
    @Transactional  //@Transaction与@Test  一起使用时，事务自动回滚
    @Rollback(value = false) //取消自动回滚
    public void updateUsernameById() {
        this.usersRepositoryQueryAnnotation.updateUsernameById("积分个人白色色的共同", 2);
    }

    /**
    * CrudRepository
    * */
    @Test
    public void curdRepositorySave() {
        Users users = new Users();
        users.setUsername("李四");
        users.setPassword("3432的身高多少");
        this.usersRepositoryCrudRepository.save(users);
    }

    @Test
    public void crudRepositoryFindById() {
        Optional<Users> usersList = this.usersRepositoryCrudRepository.findById(2);
        System.out.println(usersList);
    }

    @Test
    public void crudRepositorySave() {
        Users users = new Users();
        users.setId(3);
        users.setUsername("王五");
        users.setPassword("维尔固定都市风");
        this.usersRepositoryCrudRepository.save(users);
    }

    @Test
    public void crudRepositoryDelete() {
        this.usersRepositoryCrudRepository.deleteById(4);
    }

    /**
     * PagingAndSortingRepository 排序
     * */
    @Test
    public void PagingAndSortingRepositorySorting() {
        // 定义排序的规则
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Iterable<Users> usersIterable = this.usersRepositoryPagingAndSorting.findAll(sort);
        for (Users user :usersIterable) {
            System.out.println(user);
        }
    }

    /**
     * PagingAndSortingRepository 分页
     * */
    @Test
    public void PagingAndSortingRepositoryPaging() {
        // Pageable：封装了分页的参数，当前页，每页显示的条数，当前页从 0 开始
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<Users> usersPage = this.usersRepositoryPagingAndSorting.findAll(pageRequest);
        // 获取总条数
        System.out.println("总条数：" + usersPage.getTotalElements());
        // 获取总页数
        System.out.println("总页数：" + usersPage.getTotalPages());
        List<Users> usersPageContent = usersPage.getContent();
        for (Users user : usersPage) {
            System.out.println(user);
        }
    }

    /**
     * PagingAndSortingRepository 排序 + 分页
     * */
    @Test
    public void PagingAndSortingRepositoryPagingAndSorting() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(1, 2, sort);
        Page<Users> usersPage = this.usersRepositoryPagingAndSorting.findAll(pageRequest);
        // 获取总条数
        System.out.println("总条数：" + usersPage.getTotalElements());
        // 获取总页数
        System.out.println("总页数：" + usersPage.getTotalPages());
        for (Users user : usersPage) {
            System.out.println(user);
        }
    }

    /**
     * JpaRepository
     * */
    @Test
    public void JpaRepositorySorting() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Users> usersList = this.usersRepositoryJpaRepository.findAll(sort);
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    /**
     * UsersRepositoryJpaSpecificationExecutor 单条件
     * */
    @Test
    public void JpaSpecificationExecutorSingle() {
        /**
        * Specification<Users>：用于封装查询条件
        * */
        Specification<Users> spec = new Specification<Users>() {
            /**
            *   Predicate：封装单个查询条件s
            *   Root<Users> root：查询对象的属性的封装
            *   CriteriaQuery<?> criteriaQuery：封装了要执行的查询中的各部分的信息 select from
             *   CriteriaBuilder criteriaBuilder：查询条件的构造器，定义不同的查询条件
            * */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /**
                 * where username = "王五";
                 * 参数一：查询的条件属性
                 * 参数二：条件值
                 * */
                Predicate predicate = criteriaBuilder.equal(root.get("username"), "王五");
                return predicate;
            }
        };
        List<Users> usersList = this.usersRepositoryJpaSpecificationExecutor.findAll(spec);
        for (Users user : usersList) {
            System.out.println(user);
        }
    }

    /**
     * UsersRepositoryJpaSpecificationExecutor 多条件
     * */
    @Test
    public void jpaSpecificationExecutorMultiple() {
        /**
         * Specification<Users>：用于封装查询条件
         * */
        Specification<Users> spec = new Specification<Users>() {
            /**
             *   Predicate：封装单个查询条件s
             *   Root<Users> root：查询对象的属性的封装
             *   CriteriaQuery<?> criteriaQuery：封装了要执行的查询中的各部分的信息 select from
             *   CriteriaBuilder criteriaBuilder：查询条件的构造器，定义不同的查询条件
             * */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                /**
                 * where username = "王五" and password = "";
                 * 参数一：查询的条件属性
                 * 参数二：条件值
                 * */
                /* List<Predicate> predicatesList = new ArrayList<>();
                predicatesList.add(criteriaBuilder.equal(root.get("username"), "王五"));
                predicatesList.add(criteriaBuilder.equal(root.get("password"), "维尔固定都市风"));
                Predicate[] array = new Predicate[predicatesList.size()];
                return criteriaBuilder.and(predicatesList.toArray(array));*/
                return criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"), "王五"), criteriaBuilder.equal(root.get("password"), "维尔固定都市风")), criteriaBuilder.equal(root.get("id"), 1));
            }
        };
        List<Users> usersList = this.usersRepositoryJpaSpecificationExecutor.findAll(spec);
        for (Users user : usersList) {
            System.out.println(user);
        }
    }
}
