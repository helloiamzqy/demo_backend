package com.example.demo.service.impl;

import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.entity.Merchandise;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.service.MerchandiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Override
    public List<MerchandiseDTO> listMerchandises() {
        return merchandiseRepository.findAll().stream().map(this::toDTO).collect(toList());
    }

    @Override
    public MerchandiseDTO findMerchandise(String id) {
        Optional<Merchandise> merchandiseOp = merchandiseRepository.findById(id);
        if (!merchandiseOp.isPresent()) {
            throw new NotFoundException("Merchandise not found.");
        }
        return toDTO(merchandiseOp.get());
    }

    @Override
    public String createMerchandise(MerchandiseDTO dto) {
        Merchandise entity = new Merchandise();
        toExistEntity(entity, dto);
        merchandiseRepository.save(entity);
        return entity.getId();
    }

    @Override
    public String updateMerchandise(MerchandiseDTO dto) {
        Optional<Merchandise> merchandiseOp = merchandiseRepository.findById(dto.getId());
        if (!merchandiseOp.isPresent()) {
            throw new NotFoundException("Merchandise not found.");
        }
        Merchandise entity = merchandiseOp.get();
        toExistEntity(entity, dto);
        merchandiseRepository.save(entity);
        return entity.getId();
    }

    @Override
    public String deleteMerchandise(String id) {
        Optional<Merchandise> merchandiseOp = merchandiseRepository.findById(id);
        if (!merchandiseOp.isPresent()) {
            throw new NotFoundException("Merchandise not found.");
        }
        merchandiseRepository.delete(merchandiseOp.get());
        return "success";
    }

    private void toExistEntity(Merchandise entity, MerchandiseDTO dto) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setPrice(dto.getPrice());
        entity.setInventory(dto.getInventory());
    }

    private MerchandiseDTO toDTO(Merchandise entity) {
        MerchandiseDTO dto = new MerchandiseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setPrice(entity.getPrice());
        dto.setInventory(entity.getInventory());
        return dto;
    }
}
