package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String patients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/add")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patient/list";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        model.addAttribute("patient", patient);
        return "add_patient";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patient/list";
    }
}