package model;

import java.util.Objects;

/**
 * Created by Manu on 31/1/16.
 */
public class Bar {

    private String idBar;
    private String name;
    private String address;

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

    @Override
    public int hashCode() {
        return Objects.hash(idBar, name, address);
    }
}
