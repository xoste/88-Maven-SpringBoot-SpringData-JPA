package com.xoste.leon.pojo;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 */
@Entity
@Table(name = "t_menus")
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menusid")
    private Integer menusid;
    @Column(name = "menusname")
    private String menusname;
    @Column(name = "menusurl")
    private String menusurl;
    @Column(name = "fatherid")
    private Integer fatherid;
    @ManyToMany(mappedBy = "listMenus")
    private List<Roles> listRoles = new ArrayList<>();

    public Menus() {
    }

    public Menus(Integer menusid, String menusname, String menusurl, Integer fatherid, List<Roles> listRoles) {
        this.menusid = menusid;
        this.menusname = menusname;
        this.menusurl = menusurl;
        this.fatherid = fatherid;
        this.listRoles = listRoles;
    }

    public Integer getMenusid() {
        return menusid;
    }

    public void setMenusid(Integer menusid) {
        this.menusid = menusid;
    }

    public String getMenusname() {
        return menusname;
    }

    public void setMenusname(String menusname) {
        this.menusname = menusname;
    }

    public String getMenusurl() {
        return menusurl;
    }

    public void setMenusurl(String menusurl) {
        this.menusurl = menusurl;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public List<Roles> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Roles> listRoles) {
        this.listRoles = listRoles;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "menusid=" + menusid +
                ", menusname='" + menusname + '\'' +
                ", menusurl='" + menusurl + '\'' +
                ", fatherid=" + fatherid +
                ", listRoles=" + listRoles +
                '}';
    }
}
