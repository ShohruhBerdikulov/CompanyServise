package ecma.ai.company.service;

import ecma.ai.company.entitiy.Address;
import ecma.ai.company.payload.ApiResponse;
import ecma.ai.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository repository;

    public List<Address> getAll() {
        return repository.findAll();
    }

    public Address getById(Integer id) {
        Optional<Address> byId = repository.findById(id);
        return byId.orElseGet(Address::new);
    }

    public ApiResponse add(Address dto) {
        if (repository.existsByHomeNumberAndStreet(dto.getHomeNumber(), dto.getStreet())) {
            return new ApiResponse("this address is already exist", false);
        }
        repository.save(dto);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse update(Integer id, Address dto) {
        Optional<Address> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        if (repository.existsByHomeNumberAndStreetAndIdNot(dto.getHomeNumber(), dto.getStreet(), id)) {
            return new ApiResponse("this address is already exist", false);
        }
        Address address = byId.get();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        repository.save(address);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Address> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("this id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }


}
