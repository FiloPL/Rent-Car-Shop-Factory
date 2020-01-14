package FiloPL.carrentshop.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company getCompanyDetail(){
        return companyRepository.getOne(1);
    }

    public Company setCompanyData(Company company){
        company.setId(1);
        return companyRepository.save(company);
    }
}
