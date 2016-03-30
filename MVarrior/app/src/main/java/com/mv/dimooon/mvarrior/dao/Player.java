package com.mv.dimooon.mvarrior.dao;

import android.annotation.TargetApi;
import android.os.Build;

import com.orm.SugarRecord;

import java.util.List;
import java.util.Objects;

/**
 * Created by dimooon on 28.03.16.
 */
public class Player extends SugarRecord {
    String name;
    String serName;

    public Player() {
    }

    public Player(String name, String serName) {
        this.name = name;
        this.serName = serName;
    }

    public List<ContactInfo> getInfos(){
        return ContactInfo.find(ContactInfo.class, "owner = ?",String.valueOf(getId()));
    }

    public List<Experience> getExperience(){
        return  Experience.find(Experience.class, "owner = ?",String.valueOf(getId()));
    }

    public List<Education> getEducation(){
        return  Education.find(Education.class, "owner = ?",String.valueOf(getId()));
    }

    public List<TechnicalSkills> getSkills(){
        return  TechnicalSkills.find(TechnicalSkills.class, "owner = ?",String.valueOf(getId()));
    }

    public List<Languages> getLanguages(){
        return  Languages.find(Languages.class, "owner = ?",String.valueOf(getId()));
    }

    public List<Skill> getSkillList(){
        return  Skill.find(Skill.class, "owner = ?",String.valueOf(getId()));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player{");
        sb.append("\nname='").append(name).append('\'');
        sb.append(", \nserName='").append(serName).append('\'');
        sb.append(", \ninfos='").append(getInfos()).append('\'');
        sb.append(", \nexperience='").append(getExperience()).append('\'');
        sb.append(", \nducation='").append(getEducation()).append('\'');
        sb.append(", \nskills='").append(getSkills()).append('\'');
        sb.append(", \nlanguages='").append(getLanguages()).append('\'');
        sb.append(", \nskillList='").append(getSkillList()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(serName, player.serName);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, serName);
    }
}
