package studentmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentmanagement.entity.Student;
import studentmanagement.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}