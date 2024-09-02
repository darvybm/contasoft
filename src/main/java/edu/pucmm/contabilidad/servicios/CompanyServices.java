package edu.pucmm.contabilidad.servicios;

import edu.pucmm.contabilidad.modelos.CatalogoCuentas;
import edu.pucmm.contabilidad.modelos.CicloContable;
import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.modelos.Cuenta;

import java.util.ArrayList;
import java.util.UUID;

public class CompanyServices {
    ArrayList<Company> companies;
    private static CompanyServices instancia;

    private CompanyServices() {
        companies = new ArrayList<>();
    }

    public static CompanyServices getInstancia() {
        if (instancia == null) {
            instancia = new CompanyServices();
        }
        return instancia;
    }

    public void agregarCompany(Company company) {
        companies.add(company);
    }

    public void agregarCicloContable(CicloContable cicloContable, UUID uuid) {
        Company company = buscarCompanyById(uuid);
        company.getCiclosContable().add(cicloContable);
    }

    public Company buscarCompanyById(UUID id) {
        return companies.stream()
                .filter(company -> company.getUuid().equals(id))
                .findFirst()
                .orElse(null);
    }

    public CicloContable buscarCicloContableById(UUID id, Company company) {
        return company.getCiclosContable().stream()
                .filter(cicloContable -> cicloContable.getUuid().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Company buscarCompanyByEmail(String email) {
        return companies.stream()
                .filter(company -> company.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean existeCompany(String email) {
        if (buscarCompanyByEmail(email) == null) {
            return false;
        }
        return true;
    }
}
