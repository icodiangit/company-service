package com.icodian.careervia.company.service;

import com.icodian.careervia.company.dto.HrDTO;
import java.util.List;

public interface HrService {

    HrDTO createHr(HrDTO dto);

    List<HrDTO> getAllHrs();
    

    HrDTO getHrById(Long id);

    HrDTO updateHrStatus(Long id, String status);

    HrDTO updateHr(Long id, HrDTO dto);

    void deleteHr(Long id);
}