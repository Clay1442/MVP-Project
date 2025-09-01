package com.mvpproject.mvp_project.controllers;

import com.mvpproject.mvp_project.dto.AddressDTO;
import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.CreateClientDTO;
import com.mvpproject.mvp_project.dto.UpdateClientDTO;
import com.mvpproject.mvp_project.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ClientDTO>> find(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String cpf,
                                  Pageable pageable ) {

        Page<ClientDTO> response = clientService.findBy(name, email, cpf, pageable);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> save(@RequestBody CreateClientDTO dto) {
        ClientDTO newClient = clientService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).body(newClient);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody UpdateClientDTO dto) {
        ClientDTO response = clientService.updateClient(id, dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "address/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
        AddressDTO response = clientService.updateAddress(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
