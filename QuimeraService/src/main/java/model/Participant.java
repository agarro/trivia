package model;

import java.util.Objects;

/**
 * Created by Manu on 31/1/16.
 */
public class Participant {

    private String idParticipant;
    private String name;
    private String lastName;

    public Participant(String idParticipant) {
        this.idParticipant = idParticipant;
    }

    public String getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(String idParticipant) {
        this.idParticipant = idParticipant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return Objects.equals(idParticipant, that.idParticipant) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParticipant, name, lastName);
    }
}
