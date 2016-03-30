package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 28.03.16.
 */
public class TechnicalSkills extends SugarRecord{

    String skill;
    String description;

    Player owner;

    public TechnicalSkills() {
    }

    public TechnicalSkills(String skill, String description, Player owner) {
        this.skill = skill;
        this.description = description;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TechnicalSkills{");
        sb.append("skill='").append(skill).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
