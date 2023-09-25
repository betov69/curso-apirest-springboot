package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(@NotNull   //Internamente not blank hace lo mismo que notnull por lo que se podria omitir
                                  @NotBlank
                                  String nombre,
                                  @NotBlank
                                  @Email
                                  String email,
                                  @NotBlank
                                  String telefono,
                                  @NotBlank
                                  @Pattern(regexp = "\\d{4,6}")
                                  String documento,
                                  @NotNull
                                  Especialidad especialidad,
                                  @NotNull  //Como direccion es un objeto nunca va a llegar en blanco si no nullo
                                  @Valid
                                  DatosDireccion direccion) {
}
