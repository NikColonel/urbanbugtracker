package ru.nc.ticketservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @GetMapping("/ping")
    public String ping(){
        return "Hello world";
    }
}
