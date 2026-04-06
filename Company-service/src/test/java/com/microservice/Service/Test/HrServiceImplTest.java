package com.microservice.Service.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.icodian.careervia.company.dto.HrDTO;
//import com.icodian.careervia.company.entity.Company;
import com.icodian.careervia.company.entity.Hr;
import com.icodian.careervia.company.repository.CompanyRepository;
import com.icodian.careervia.company.repository.HrRepository;
import com.icodian.careervia.company.service.HrServiceImpl;
import com.icodian.careervia.companyenum.Status;

@ExtendWith(MockitoExtension.class)
public class HrServiceImplTest {

    @Mock
    private HrRepository repository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private HrServiceImpl service;

    //  Create HR
    @Test
    void testCreateHr() {

        HrDTO dto = new HrDTO();
        dto.setName("Rahul");
        dto.setEmail("rahul@gmail.com");
        dto.setPhone("1234567890");
        dto.setStatus("ACTIVE");

        when(repository.save(any(Hr.class)))
                .thenAnswer(invocation -> {
                    Hr hr = invocation.getArgument(0);
                    hr.setHrId(1L);
                    hr.setStatus(Status.ACTIVE);
                    return hr;
                });

        HrDTO result = service.createHr(dto);

        assertNotNull(result);
        assertEquals(1L, result.getHrId());
        assertEquals("Rahul", result.getName());
    }

    //  Get All HRs
    @Test
    void testGetAllHrs() {

        Hr hr1 = new Hr();
        hr1.setHrId(1L);
        hr1.setName("Rahul");
        hr1.setStatus(Status.ACTIVE);

        Hr hr2 = new Hr();
        hr2.setHrId(2L);
        hr2.setName("Anjali");
        hr2.setStatus(Status.ACTIVE);

        when(repository.findAll()).thenReturn(List.of(hr1, hr2));

        List<HrDTO> result = service.getAllHrs();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Rahul", result.get(0).getName());
    }

    //  Get HR By ID
    @Test
    void testGetHrById() {

        Hr hr = new Hr();
        hr.setHrId(1L);
        hr.setName("Rahul");
        hr.setStatus(Status.ACTIVE);

        when(repository.findById(1L)).thenReturn(Optional.of(hr));

        HrDTO result = service.getHrById(1L);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());
    }

    //  Not Found Case
    @Test
    void testGetHrById_NotFound() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.getHrById(99L);
        });
    }

    //  Delete HR
    @Test
    void testDeleteHr() {

        Hr hr = new Hr();
        hr.setHrId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(hr));

        service.deleteHr(1L);

        verify(repository).delete(hr);
    }
}

	//  Get HR By ID
	//@Test
	//void testGetHrById() {
	//
	  //  Hr hr = new Hr();
	   // hr.setHrId(1L);
	   // hr.setName("Rahul");
	  //  hr.setStatus(Status.ACTIVE);
	
	  //  when(repository.findById(1L)).thenReturn(Optional.of(hr));
	
	  //  HrDTO result = service.getHrById(1L);
	
	  //  assertNotNull(result);
	  //  assertEquals("Rahul", result.getName());
	//}
//}