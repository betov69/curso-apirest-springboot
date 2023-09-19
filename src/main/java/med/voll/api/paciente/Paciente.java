package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;
@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documentoIdentidad;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente){
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documentoIdentidad = datosRegistroPaciente.documentoIdentidad();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatos(DatosActualizaPaciente datosActualizaPaciente) {
        if(datosActualizaPaciente.nombre() != null){
            this.nombre = datosActualizaPaciente.nombre();
        }
        if(datosActualizaPaciente.documentoIdentidad() != null){
            this.documentoIdentidad = datosActualizaPaciente.documentoIdentidad();
        }
        if(datosActualizaPaciente.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizaPaciente.direccion());
        }
    }

    public void desactivaPaciente() {
        this.activo = false;
    }
}
