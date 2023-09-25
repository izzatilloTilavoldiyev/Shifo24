package com.company.shifo24.service.profession;

import com.company.shifo24.domains.dtos.request.ProfessionDTO;

import java.util.List;

public interface ProfessionService {
    ProfessionDTO create(ProfessionDTO professionDTO);

    ProfessionDTO getByID(Long professionID);

    List<ProfessionDTO> getAllProfession();

    ProfessionDTO update(Long professionID, ProfessionDTO professionDTO);

    void delete(Long professionID);
}
