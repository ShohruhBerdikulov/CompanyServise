package ecma.ai.company.service;

import ecma.ai.company.entitiy.Company;
import ecma.ai.company.payload.ApiResponse;
import ecma.ai.company.repository.AddressRepository;
import ecma.ai.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository repository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company getById(Integer id) {
        Optional<Company> byId = repository.findById(id);
        return byId.orElseGet(Company::new);
    }

    public ApiResponse add(Company dto) {
        if (!addressRepository.findById(dto.getAddress().getId()).isPresent()) {
            return new ApiResponse("no Adress", false);
        }
        if (repository.existsByCorpNameOrAddress_Id(dto.getCorpName(), dto.getAddress().getId())) {
            return new ApiResponse("this company is already exist", false);
        }
        repository.save(dto);
        return new ApiResponse("successfully added", true,dto);
    }

    public ApiResponse update(Integer id, Company dto) {
        if (!addressRepository.findById(dto.getAddress().getId()).isPresent()) {
            return new ApiResponse("no Adress", false);
        }
        Optional<Company> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        if (repository.existsByCorpNameAndIdNotOrAddress_IdAndIdNot(
                dto.getCorpName(), id,
                dto.getAddress().getId(), id)) {
            return new ApiResponse("this Corparation is already exist", false);
        }
        Company company = byId.get();
        company.setCorpName(dto.getCorpName());
        company.setDirectorName(dto.getDirectorName());
        company.getAddress().setId(dto.getAddress().getId());
        repository.save(company);
        return new ApiResponse("successfully added", true,company);
    }

    public ApiResponse delete(Integer id) {
        Optional<Company> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }


}
