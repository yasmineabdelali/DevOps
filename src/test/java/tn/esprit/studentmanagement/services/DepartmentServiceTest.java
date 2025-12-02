package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DepartmentServiceTest {

    @Mock  // Mock the DepartmentRepository c'est a dire : crée un faux DepartmentRepository pour les tests unitaires.
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentService departmentService; // Crée une instance de DepartmentService et injecte le mock departmentRepository dedans.

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(
                new Department(1L, "IT"),
                new Department(2L, "Finance")
        );

        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }


    @Test
    void testGetDepartmentById() {
        Department dep = new Department(1L, "IT");
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(dep));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("IT", result.getName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveDepartment() {
        Department dep = new Department(1L, "Marketing");
        when(departmentRepository.save(dep)).thenReturn(dep);

        Department result = departmentService.saveDepartment(dep);

        assertEquals(dep, result);
        verify(departmentRepository, times(1)).save(dep);
    }

    @Test
    void testDeleteDepartment() {
        Long id = 5L;

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }


}
