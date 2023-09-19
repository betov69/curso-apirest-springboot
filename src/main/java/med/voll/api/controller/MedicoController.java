package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        System.out.println("EL REQUEST LLEGA CORRECTAMENTE xD");
        medicoRepository.save(new Medico(datosRegistroMedico));
    }
    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 8) Pageable paginacion){

        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional
    public void actualizaMedico (@RequestBody @Valid DatosActualizaMedico datosActualizaMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizaMedico.id());
        medico.actualizarDatos(datosActualizaMedico);
    }
    @DeleteMapping("/{id}")
    @Transactional

    //Este metodo borra el medico de la BASE DE DATOS
    /*public void eliminaMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/
    //Este metodo solo desactiva el medico pero lo conserva en la BASE DE DATOS
    public void eliminaMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivaMedico();
    }
}