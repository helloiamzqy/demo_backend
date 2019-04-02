package com.example.demo.service;

import com.example.demo.dto.MerchandiseDTO;

import java.util.List;

public interface MerchandiseService {

    List<MerchandiseDTO> listMerchandises();

    MerchandiseDTO findMerchandise(String id);

    String createMerchandise(MerchandiseDTO dto);

    String updateMerchandise(MerchandiseDTO dto);

    String deleteMerchandise(String id);
}
