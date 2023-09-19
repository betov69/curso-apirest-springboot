package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;

public record DatosActualizaPaciente(@NotNull Long id, String nombre, String documentoIdentidad, DatosDireccion direccion) {
}
