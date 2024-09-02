package edu.pucmm.contabilidad.modelos;

import java.util.ArrayList;
import java.util.UUID;

public class Company {
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private ArrayList<CicloContable> ciclosContable;

    public Company(UUID uuid, String name, String email, String password) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.ciclosContable = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<CicloContable> getCiclosContable() {
        return ciclosContable;
    }

    public void setCiclosContable(ArrayList<CicloContable> ciclosContable) {
        this.ciclosContable = ciclosContable;
    }
}
