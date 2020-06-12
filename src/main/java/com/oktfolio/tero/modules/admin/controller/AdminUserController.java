package com.oktfolio.tero.modules.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/12
 */
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @PostMapping("")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieve(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update2(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }
}
