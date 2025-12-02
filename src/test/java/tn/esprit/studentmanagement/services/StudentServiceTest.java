package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(
                new Student(1L, "Nour", "Bouslimi"),
                new Student(2L, "CCC", "DDD")
        );

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository).findAll();
    }

    @Test
    void testGetStudentById() {
        Student s = new Student(1L, "Nour", "Bouslimi");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals("Nour", result.getFirstName());
        verify(studentRepository).findById(1L);
    }

    @Test
    void testSaveStudent() {
        Student s = new Student(1L, "AAA", "TTT");
        when(studentRepository.save(s)).thenReturn(s);

        Student result = studentService.saveStudent(s);

        assertEquals(s, result);
        verify(studentRepository).save(s);
    }

    @Test
    void testDeleteStudent() {
        Long id = 10L;

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id);
    }
}
