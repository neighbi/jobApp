package com.ajin.jobapp.company;

import java.util.List;

public interface CompanyService {


    public List<Company> findAll() ;

    boolean saveCompany(Company company);

    boolean updateCompany(Company company, Long id);

    boolean deleteCompany(Long id);

    Company getCompanyById(Long id);
}
