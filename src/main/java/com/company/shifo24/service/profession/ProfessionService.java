package com.company.shifo24.service.profession;

import com.company.shifo24.domains.dtos.request.ProfessionDTO;
import com.company.shifo24.domains.dtos.request.WorkplaceDTO;
import com.company.shifo24.domains.entity.Profession;

import java.util.List;

public interface ProfessionService {
    ProfessionDTO create(ProfessionDTO professionDTO);

    Profession getByID(Long professionID);

    List<ProfessionDTO> getAllProfession();

    List<ProfessionDTO> search(String name);

    ProfessionDTO update(Long professionID, ProfessionDTO professionDTO);

    void delete(Long professionID);
}
