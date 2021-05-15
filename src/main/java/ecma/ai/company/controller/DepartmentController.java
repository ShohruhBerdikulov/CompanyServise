package ecma.ai.company.controller;

import ecma.ai.company.entitiy.Department;
import ecma.ai.company.payload.ApiResponse;
import ecma.ai.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Department")
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @GetMapping
    public List<Department> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse add = service.delete(id);
        return ResponseEntity.status(add.isSuccess() ? 202 : 409).body(add);
    }

    // add
    @PostMapping
    public HttpEntity<?> add(@RequestBody Department dto) {
        ApiResponse add = service.add(dto);
        return ResponseEntity.status(add.isSuccess() ? 202 : 409).body(add);
    }

    //update
    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Department dto) {
        ApiResponse add = service.update(id, dto);
        return ResponseEntity.status(add.isSuccess() ? 202 : 409).body(add);
    }
}
