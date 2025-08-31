package com.mvpproject.mvp_project.controllers;

import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<Client>> findAll(Pageable pageable) {
         Page<Client> clients = clientService.findAll(pageable);
         return ResponseEntity.ok().body(clients);
    }

}
