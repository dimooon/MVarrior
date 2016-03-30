package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 30.03.16.
 */
public class Skill extends SugarRecord {
    String name;
    Player owner;

    public Skill() {
    }

    public Skill(String name, Player owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Skill{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
