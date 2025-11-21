package com.greenway.greenway.controller;

import com.greenway.greenway.dto.UserDTO;
import com.greenway.greenway.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    // ============================================
    // CREATE USER (com suporte a mensagens i18n)
    // ============================================
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO dto, Locale locale) {

        UserDTO createdUser = userService.create(dto);

        String msg = messageSource.getMessage("user.created", null, locale);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("message", msg)
                .body(createdUser);
    }

    // ============================================
    // GET ALL USERS
    // ============================================
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // ============================================
    // GET USER BY ID
    // ============================================
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // ============================================
    // UPDATE USER
    // ============================================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO dto,
            Locale locale
    ) {	
        UserDTO updated = userService.update(id, dto);
        String msg = messageSource.getMessage("user.updated", null, locale);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("message", msg)
                .body(updated);
    }

    // ============================================
    // DELETE USER
    // ============================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Locale locale) {

        userService.delete(id);

        String msg = messageSource.getMessage("user.deleted", null, locale);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .header("message", msg)
                .build();
    }
}
