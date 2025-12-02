package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Course;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.entities.Status;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;



    // Test getAllEnrollments()

    @Test
    void testGetAllEnrollments() {
        Enrollment e1 = new Enrollment(1L, LocalDate.now(), 14.5, Status.COMPLETED, null, null);
        Enrollment e2 = new Enrollment(2L, LocalDate.now(), 9.0, Status.COMPLETED, null, null);

        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Enrollment> result = enrollmentService.getAllEnrollments();

        assertEquals(2, result.size());
        verify(enrollmentRepository, times(1)).findAll();
    }



    // Test getEnrollmentById()

    @Test
    void testGetEnrollmentById() {
        Enrollment enrollment = new Enrollment(
                1L,
                LocalDate.of(2024, 1, 15),
                17.0,
                Status.COMPLETED,
                new Student(),
                new Course()
        );

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Enrollment result = enrollmentService.getEnrollmentById(1L);

        assertNotNull(result);
        assertEquals(17.0, result.getGrade());
        assertEquals(Status.COMPLETED, result.getStatus());
        verify(enrollmentRepository, times(1)).findById(1L);
    }




    // Test saveEnrollment()

    @Test
    void testSaveEnrollment() {
        Enrollment enrollment = new Enrollment(
                3L,
                LocalDate.now(),
                12.0,
                Status.COMPLETED,
                new Student(),
                new Course()
        );

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment result = enrollmentService.saveEnrollment(enrollment);

        assertEquals(enrollment, result);
        verify(enrollmentRepository, times(1)).save(enrollment);
    }



    // Test deleteEnrollment()

    @Test
    void testDeleteEnrollment() {
        Long id = 5L;

        enrollmentService.deleteEnrollment(id);

        verify(enrollmentRepository, times(1)).deleteById(id);
    }
}
