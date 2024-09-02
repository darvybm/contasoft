package edu.pucmm.contabilidad.modelos;

import java.util.UUID;

public class CicloContable {
    private UUID uuid;
    private String titulo;
    private String descripcion;
    private String companyName;
    private LibroDiario libroDiario;
    private LibroMayor libroMayor;
    private CatalogoCuentas catalogoCuentas;
    private boolean archivado;

    public CicloContable(UUID uuid) {
        this.uuid = uuid;
        this.titulo = "";
        this.descripcion = "";
        this.companyName = "";
        this.libroDiario = new LibroDiario();
        this.libroMayor = new LibroMayor();
        this.catalogoCuentas = new CatalogoCuentas();
        this.archivado = false;
    }

    public boolean isArchivado() {
        return archivado;
    }

    public void setArchivado(boolean archivado) {
        this.archivado = archivado;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LibroDiario getLibroDiario() {
        return libroDiario;
    }

    public void setLibroDiario(LibroDiario libroDiario) {
        this.libroDiario = libroDiario;
    }

    public LibroMayor getLibroMayor() {
        return libroMayor;
    }

    public void setLibroMayor(LibroMayor libroMayor) {
        this.libroMayor = libroMayor;
    }

    public CatalogoCuentas getCatalogoCuentas() {
        return catalogoCuentas;
    }

    public void setCatalogoCuentas(CatalogoCuentas catalogoCuentas) {
        this.catalogoCuentas = catalogoCuentas;
    }
}

