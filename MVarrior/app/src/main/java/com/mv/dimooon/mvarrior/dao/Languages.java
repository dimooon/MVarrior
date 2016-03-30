package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 30.03.16.
 */
public class Languages extends SugarRecord{
    String title;
    String level;
    Player owner;

    public Languages() {
    }

    public Languages(String title, String level, Player owner) {
        this.title = title;
        this.level = level;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Languages{");
        sb.append("title='").append(title).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
