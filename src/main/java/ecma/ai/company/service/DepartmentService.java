package ecma.ai.company.service;

import ecma.ai.company.entitiy.Address;
import ecma.ai.company.entitiy.Company;
import ecma.ai.company.entitiy.Department;
import ecma.ai.company.payload.ApiResponse;
import ecma.ai.company.repository.CompanyRepository;
import ecma.ai.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(Integer id) {
        Optional<Department> byId = repository.findById(id);
        return byId.orElseGet(Department::new);
    }

    public ApiResponse add(Department dto) {
        if (!companyRepository.findById(dto.getCompany().getId()).isPresent()) {
            return new ApiResponse("no Company", false);
        }

        if (repository.existsByNameAndCompany_Id(dto.getName(), dto.getCompany().getId())) {
            return new ApiResponse("this Department is already exist", false);
        }
        Department department=new Department();
        department.setName(dto.getName());
        department.setCompany(new Company(dto.getCompany().getId()));
        System.out.println(department);
        repository.save(department);
        return new ApiResponse("successfully added", true, dto);
    }

    public ApiResponse update(Integer id, Department dto) {
        Optional<Department> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        if (repository.existsByNameAndCompany_IdAndIdNot(
                dto.getName(), dto.getCompany().getId(), id)) {
            return new ApiResponse("this Corparation is already exist", false);
        }
        Department department = byId.get();
        department.setName(dto.getName());
        department.getCompany().setId(dto.getCompany().getId());
        repository.save(department);
        return new ApiResponse("successfully added", true, department);
    }

    public ApiResponse delete(Integer id) {
        Optional<Department> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }


}
