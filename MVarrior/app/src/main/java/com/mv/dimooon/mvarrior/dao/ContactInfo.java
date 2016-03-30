package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 28.03.16.
 */
public class ContactInfo extends SugarRecord {
    String contactTitle;
    String eMail;
    String address;
    String phone;

    Player owner;

    public ContactInfo() {
    }

    public ContactInfo(String contactTitle, String eMail, String address, String phone, Player owner) {
        this.contactTitle = contactTitle;
        this.eMail = eMail;
        this.address = address;
        this.phone = phone;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactInfo{");
        sb.append("\nphone='").append(phone).append('\'');
        sb.append(", \naddress='").append(address).append('\'');
        sb.append(", \neMail='").append(eMail).append('\'');
        sb.append(", \ncontactTitle='").append(contactTitle).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
