package com.ajin.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findall(){

        List<Company> companies=companyService.findAll();
        if(companies.size()>0)
            return ResponseEntity.ok(companies);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company comp = companyService.getCompanyById(id);
        if(comp != null){
            return new ResponseEntity<>(comp,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Company(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> insertCompany(@RequestBody Company company){
        if(companyService.saveCompany(company)){
            return new ResponseEntity<>("Company inserted successfully",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Cant able to insert company",HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id){
        if(companyService.updateCompany(company,id))
            return new ResponseEntity<>("Company updated successfully..",HttpStatus.OK);
        return new ResponseEntity<>("Cant able to update company",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        if(companyService.deleteCompany(id))
            return new ResponseEntity<>("Company Deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("Company does not exists",HttpStatus.NOT_FOUND);
    }
}
