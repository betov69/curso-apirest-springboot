package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        System.out.println("Datos recibidos: " + datosRegistroPaciente);
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }
    @GetMapping
    public Page<DatosListadoPaciente> listadoPacientes(@PageableDefault(size = 5) Pageable paginacion){

        return pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new);
    }
    @PutMapping
    @Transactional
    public void  actualizaPaciente(@RequestBody @Valid DatosActualizaPaciente datosActualizaPaciente){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizaPaciente.id());
        paciente.actualizarDatos(datosActualizaPaciente);
    }
    //Este metodo no borra la BASE DE DATOS
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminaPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivaPaciente();
    }
}
