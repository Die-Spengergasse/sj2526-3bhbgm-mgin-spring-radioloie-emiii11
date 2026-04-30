package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/add")
    public String addSave(@ModelAttribute Patient patient, Model model) {

        if (patient.getBirth().isAfter(LocalDate.now())) {
            model.addAttribute("error", "Geburtsdatum darf nicht in der Zukunft liegen!");
            return "add_patient";
        }

        if (patient.getSocialsecuritynumber() == null ||
                !patient.getSocialsecuritynumber().matches("\\d{10}")) {
            model.addAttribute("error", "Ungültige Sozialversicherungsnummer!");
            return "add_patient";
        }

        patientRepository.save(patient);
        return "redirect:/patient/list";
    }
}