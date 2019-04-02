package com.example.demo.api;

import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.service.MerchandiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "MerchandiseResource")
@RestController
@RequestMapping("merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @ApiOperation(value = "Get all merchandises")
    @GetMapping("")
    public List<MerchandiseDTO> list() {
        return merchandiseService.listMerchandises();
    }

    @ApiOperation(value = "Get merchandise by id")
    @GetMapping("/{id}")
    public MerchandiseDTO get(@PathVariable("id") String id) {
        return merchandiseService.findMerchandise(id);
    }

    @ApiOperation(value = "Add merchandise")
    @PostMapping("")
    public String addMerchandise(MerchandiseDTO dto) {
        return merchandiseService.createMerchandise(dto);
    }

    @ApiOperation(value = "Update merchandise")
    @PutMapping("")
    public String updateMerchandise(MerchandiseDTO dto) {
        return merchandiseService.updateMerchandise(dto);
    }

    @ApiOperation(value = "Delete merchandise by id")
    @DeleteMapping("/{id}")
    public String deleteMerchandise(@PathVariable("id") String id) {
        return merchandiseService.deleteMerchandise(id);
    }

}
