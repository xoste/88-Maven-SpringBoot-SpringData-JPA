package com.xoste.leon.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Leon
 */
@Entity
@Table(name = "t_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolesid")
    private Integer rolesid;
    @Column(name = "rolesname")
    private String rolesname;
    @OneToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Users> listUsers = new HashSet<>();
    /**
     * @JoinTable: 映射中间表
     * JoinColumns: 当前表中的主键所关联的中间表中的外键字段
     * */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "t_roles_menus", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "menus_id"))
    private List<Menus> listMenus = new ArrayList<>();

    public Roles() {
    }

    public Roles(Integer rolesid, String rolesname, Set<Users> listUsers) {
        this.rolesid = rolesid;
        this.rolesname = rolesname;
        this.listUsers = listUsers;
        this.listMenus = listMenus;
    }

    public Integer getRolesid() {
        return rolesid;
    }

    public void setRolesid(Integer rolesid) {
        this.rolesid = rolesid;
    }

    public String getRolesname() {
        return rolesname;
    }

    public void setRolesname(String rolesname) {
        this.rolesname = rolesname;
    }

    public Set<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<Users> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Menus> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<Menus> listMenus) {
        this.listMenus = listMenus;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "rolesid=" + rolesid +
                ", rolesname='" + rolesname + '\'' +
                ", listUsers=" + listUsers +
                ", listMenus=" + listMenus +
                '}';
    }
}
