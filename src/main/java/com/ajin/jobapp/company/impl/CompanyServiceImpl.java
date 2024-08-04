package com.ajin.jobapp.company.impl;

import com.ajin.jobapp.company.Company;
import com.ajin.jobapp.company.CompanyRepository;
import com.ajin.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public boolean saveCompany(Company company) {

        try{
            companyRepository.save(company);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Company comp = companyRepository.findById(id).get();
        if(comp==null)
            return false;
        else {
            comp.setCompanyName(company.getCompanyName());
            comp.setLocation(company.getLocation());
//            comp.setJobs(company.getJobs());
            companyRepository.save(comp);
            return true;
        }
    }

    @Override
    public boolean deleteCompany(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }
}
