package com.quimera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * Created by Manu on 31/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Bar {

    @Id
    private String idBar;
    @Indexed
    private String name;
    private String address;
    @Indexed
    private String username;
    private String password;

    public String getIdBar() {
        return idBar;
    }

    public void setIdBar(String idBar) {
        this.idBar = idBar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bar)) return false;
        Bar bar = (Bar) o;
        return Objects.equals(idBar, bar.idBar) &&
                Objects.equals(name, bar.name) &&
                Objects.equals(address, bar.address);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
