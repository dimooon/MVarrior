package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 28.03.16.
 */
public class Experience extends SugarRecord{
    String title;
    long since;
    long lastDate;
    String description;

    Player owner;

    public Experience() {
    }

    public Experience(String title, long since, long lastDate, String description, Player owner) {
        this.title = title;
        this.since = since;
        this.lastDate = lastDate;
        this.description = description;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Experience{");
        sb.append("\ntitle='").append(title).append('\'');
        sb.append(", \nsince=").append(since);
        sb.append(", \nlastDate=").append(lastDate);
        sb.append(", \ndescription='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}