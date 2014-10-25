/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @Author: denghp
 * @Date: 10/25/14 11:29 PM
 * @Description:
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ZTree<ID extends Serializable> implements Serializable {

    private ID id;
    private ID pId;
    private String name;
    private boolean open;
    private boolean root;
    private String iconSkin;
    private boolean isParent;
    private boolean nocheck = false;
    private boolean checked = false;


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getpId() {
        return pId;
    }

    public void setpId(ID pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iconSkin='" + iconSkin + '\'' +
                ", open='" + open + '\'' +
                ", root='" + root + '\'' +
                ", isParent =" + isParent +
                ", nocheck = " + nocheck +
                '}';
    }
}