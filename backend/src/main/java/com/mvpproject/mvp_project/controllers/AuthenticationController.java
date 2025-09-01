package com.mvpproject.mvp_project.controllers;


import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.LoginRequestDTO;
import com.mvpproject.mvp_project.dto.LoginResponseDTO;
import com.mvpproject.mvp_project.entities.User;
import com.mvpproject.mvp_project.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication", description = "Endpoint para autenticação de usuários")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza o login do usuário", description = "Autentica um usuário com login e senha e retorna um token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido, token JWT retornado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (ex: campos vazios)", content = @Content),
            @ApiResponse(responseCode = "403", description = "Login ou senha inválidos", content = @Content)
    })
    public ResponseEntity<LoginResponseDTO> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Credenciais para autenticação. Utilize os dados do exemplo para testar como Administrador.",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = LoginRequestDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Credenciais do Administrador",
                                    summary = "Login para acesso total",
                                    value = "{\"login\": \"admin@email.com\", \"password\": \"123456\"}"
                            )
                    }
            )
    ) @Valid @org.springframework.web.bind.annotation.RequestBody LoginRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }


}
