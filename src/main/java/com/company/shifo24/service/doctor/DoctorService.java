package com.company.shifo24.service.doctor;

import com.company.shifo24.domains.dtos.request.DoctorCreateDTO;
import com.company.shifo24.domains.dtos.response.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {
    DoctorResponseDTO create(DoctorCreateDTO doctorCreateDTO);

    DoctorResponseDTO getByID(Long doctorID);

    List<DoctorResponseDTO> getAllDoctor();

    DoctorResponseDTO update(Long doctorID, DoctorResponseDTO doctorResponseDTO);

    List<DoctorResponseDTO> searchByName(String firsName);

    void delete(Long doctorID);
}
