package at.spengergasse.spring_thymeleaf.repositories;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}