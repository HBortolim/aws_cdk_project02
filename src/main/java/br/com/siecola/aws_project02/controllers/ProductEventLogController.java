package br.com.siecola.aws_project02.controllers;

import br.com.siecola.aws_project02.models.ProductEventLogDTO;
import br.com.siecola.aws_project02.repositories.ProducEventsLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/api")
public class ProductEventLogController {

    @Autowired
    private ProducEventsLogRepository repository;

    public ProductEventLogController() {
    }

    @GetMapping(value = "/events")
    public List<ProductEventLogDTO> getAllEvents() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/events/{code}")
    public List<ProductEventLogDTO> findById(@PathVariable String code) {
        return repository.findAllById(code).stream()
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }
}
