package com.emsi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getEngineer(){
        return softwareEngineerService.getAllSoftwareEngineeer();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(@PathVariable Integer id){
        return softwareEngineerService.getSoftwareEngineeerById(id);
    }

    @PostMapping
    public void addNewSoftwareEngineer(
            @RequestBody SoftwareEngineer softwareEngineer
    ){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }
}
