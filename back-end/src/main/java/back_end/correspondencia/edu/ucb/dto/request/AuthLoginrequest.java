package back_end.correspondencia.edu.ucb.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginrequest(@NotBlank String username,
                               @NotBlank String password) {
}
