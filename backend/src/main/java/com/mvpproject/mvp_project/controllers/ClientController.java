package com.mvpproject.mvp_project.controllers;

import com.mvpproject.mvp_project.dto.AddressDTO;
import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.CreateClientDTO;
import com.mvpproject.mvp_project.dto.UpdateClientDTO;
import com.mvpproject.mvp_project.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
@Tag(name = "Clients", description = "Endpoints para Gerenciamento de Clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Consultar cliente", description = "Lista clientes cadastrados no banco de forma paginada e permite filtros por nome, email ou CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com Sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros de requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado para os critérios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado (requer permissão de ADMIN)", content = @Content)
    })
    public ResponseEntity<Page<ClientDTO>> find(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(required = false) String cpf,
                                                @ParameterObject Pageable pageable) {

        Page<ClientDTO> response = clientService.findBy(name, email, cpf, pageable);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cria um novo cliente", description = "Registra um novo cliente e seu endereço no banco de dados.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    public ResponseEntity<ClientDTO> create(@RequestBody CreateClientDTO dto) {
        ClientDTO newClient = clientService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).body(newClient);
    }


    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar um cliente", description = "Atualiza os dados de um cliente por id no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,  @RequestBody UpdateClientDTO dto) {
        ClientDTO response = clientService.updateClient(id, dto);
        return ResponseEntity.ok().body(response);
    }


    @PatchMapping(value = "address/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar um endereço", description = "Atualiza os dados de um endereço por id no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
        AddressDTO response = clientService.updateAddress(dto, id);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar um cliente", description = "Deleta um cliente por id no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
