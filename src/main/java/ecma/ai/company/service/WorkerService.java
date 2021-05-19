package ecma.ai.company.service;

import ecma.ai.company.entitiy.Address;
import ecma.ai.company.entitiy.Department;
import ecma.ai.company.entitiy.Worker;
import ecma.ai.company.payload.ApiResponse;
import ecma.ai.company.repository.AddressRepository;
import ecma.ai.company.repository.DepartmentRepository;
import ecma.ai.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository repository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getAll() {
        return repository.findAll();
    }

    public Worker getById(Integer id) {
        Optional<Worker> byId = repository.findById(id);
        return byId.orElseGet(Worker::new);
    }

    public ApiResponse add(Worker dto) {
        if (!departmentRepository.findById(dto.getDepartment().getId()).isPresent()) {
            return new ApiResponse("no Department", false);
        }
        if (!addressRepository.findById(dto.getAddress().getId()).isPresent()) {
            return new ApiResponse("no Adress", false);
        }
        if (repository.existsByPhoneNumber(dto.getPhoneNumber())) {
            return new ApiResponse("this Worker is already exist", false);
        }
        Worker worker =new Worker();
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setName(dto.getName());

        worker.setAddress(dto.getAddress());
        worker.setDepartment(dto.getDepartment());
        System.out.println(worker);
        repository.save(worker);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse update(Integer id, Worker dto) {
        if (!addressRepository.findById(dto.getAddress().getId()).isPresent()) {
            return new ApiResponse("no Adress", false);
        }

        if (!departmentRepository.findById(dto.getDepartment().getId()).isPresent()) {
            return new ApiResponse("no Department", false);
        }
        Optional<Worker> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        if (repository.existsByPhoneNumberAndIdNot(dto.getPhoneNumber(), id)) {
            return new ApiResponse("this Corparation is already exist", false);
        }
        Worker worker = byId.get();
        worker.setName(dto.getName());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.getAddress().setId(dto.getAddress().getId());
        worker.getDepartment().setId(dto.getDepartment().getId());
        repository.save(worker);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Worker> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        addressRepository.deleteById(byId.get().getAddress().getId());
        repository.deleteById(id);

        return new ApiResponse("successfully deleted", true);
    }
}
