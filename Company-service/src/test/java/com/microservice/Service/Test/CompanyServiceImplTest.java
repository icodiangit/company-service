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

import com.icodian.careervia.company.dto.CompanyDTO;
import com.icodian.careervia.company.entity.Company;
import com.icodian.careervia.company.repository.CompanyRepository;
import com.icodian.careervia.company.service.CompanyServiceImpl;
import com.icodian.careervia.companyenum.Status;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyServiceImpl service;

    //  Test getAllCompanies
    @Test
    void testGetAllCompanies() {

        Company c1 = new Company();
        c1.setCompanyId(1L);
        c1.setCompanyName("TCS");
        c1.setIndustry("IT");

        Company c2 = new Company();
        c2.setCompanyId(2L);
        c2.setCompanyName("Infosys");
        c2.setIndustry("IT");

        when(repository.findAll()).thenReturn(List.of(c1, c2));

        List<CompanyDTO> result = service.getAllCompanies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("TCS", result.get(0).getCompanyName());
    }

    // Test getCompanyById
    @Test
    void testGetCompanyById() {

        Company c = new Company();
        c.setCompanyId(1L);
        c.setCompanyName("Wipro");

        when(repository.findById(1L)).thenReturn(Optional.of(c));

        CompanyDTO result = service.getCompanyById(1L);

        assertNotNull(result);
        assertEquals("Wipro", result.getCompanyName());
    }

    //  Not Found
    @Test
    void testGetCompanyById_NotFound() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.getCompanyById(99L);
        });
    }

    // Test createCompany
    @Test
    void testCreateCompany() {

        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyName("Amazon");
        dto.setIndustry("IT");
        dto.setStatus(Status.ACTIVE);

        when(repository.save(any(Company.class)))
                .thenAnswer(invocation -> {
                    Company c = invocation.getArgument(0);
                    c.setCompanyId(10L);
                    return c;
                });

        CompanyDTO result = service.createCompany(dto);

        assertNotNull(result);
        assertEquals(10L, result.getCompanyId());
        assertEquals("Amazon", result.getCompanyName());
    }

    //  Delete
    @Test
    void testDeleteCompany() {

        Company c = new Company();
        c.setCompanyId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(c));

        service.deleteCompany(1L);

        verify(repository).delete(c);
    }
}