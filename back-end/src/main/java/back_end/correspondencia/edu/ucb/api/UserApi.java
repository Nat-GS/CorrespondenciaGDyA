package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.UserDetailServiceImpl;
import back_end.correspondencia.edu.ucb.bl.UsersBl;
import back_end.correspondencia.edu.ucb.dto.SuccessfulResponse;
import back_end.correspondencia.edu.ucb.dto.UnsuccessfulResponse;
import back_end.correspondencia.edu.ucb.dto.request.AuthLoginrequest;
import back_end.correspondencia.edu.ucb.dto.request.UsersRequest;
import back_end.correspondencia.edu.ucb.dto.response.AuthResponse;
import back_end.correspondencia.edu.ucb.util.Globals;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Globals.apiVersion+"users")
public class UserApi {
    private static final Logger LOG = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserDetailServiceImpl userDetailService;

    private final UsersBl usersBl;
    public UserApi(UsersBl usersBl) {
        this.usersBl = usersBl;
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginrequest authLoginrequest, HttpServletResponse response) {
        AuthResponse authResponse = userDetailService.loginUser(authLoginrequest, response);

        // Devuelve la respuesta sin el JWT en el cuerpo
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody @Valid UsersRequest usersRequest) {
        LOG.info("Iniciando registro de nuevo usuario: {}", usersRequest);
        try {
            // Llama a la lógica de negocio para crear el usuario
            Object response = usersBl.registerNewUser(usersRequest);
            return ResponseEntity.status(response instanceof SuccessfulResponse ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            LOG.error("Error al registrar un nuevo usuario", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UnsuccessfulResponse("500", "Error interno del servidor", e.getMessage()));
        }
    }

}
