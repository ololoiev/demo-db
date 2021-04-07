package com.example.demo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1")
public class EntityController{
    private final EntityService service;

    public EntityController(EntityService service) {
        this.service = service;
    }

    @PostMapping(value="/{type}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map save(@PathVariable String type,
                    @RequestBody Map<String, Object> entity){
        return service.save(type, entity);
    }

    @GetMapping(value="/{type}/{id}")
    public Map find(@PathVariable String id, @PathVariable String type){
        return service.find(id, type);
    }

    @GetMapping(value="/{type}")
    public List findAll(@PathVariable String type,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        return service.findAll(type, paging);
    }

    @PutMapping(value="/{type}/{id}")
    public Map update(@PathVariable String id,
                       @PathVariable String type,
                       @RequestBody Map<String, Object> entity){
        return service.update(id, type, entity);
    }

    @DeleteMapping(value="/{type}/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                      @PathVariable String type){
        service.delete(id,type);
    }

}
