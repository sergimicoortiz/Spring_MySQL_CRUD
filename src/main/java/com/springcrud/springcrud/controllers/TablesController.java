package com.springcrud.springcrud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcrud.springcrud.model.Tables;
import com.springcrud.springcrud.repository.TablesRepository;

@SuppressWarnings("unused")

@CrossOrigin(origins = "http://localhost:5001")
@RestController
@RequestMapping("/api")
public class TablesController {

    @Autowired
    TablesRepository tablesRepository;

    @GetMapping("tables")
    public ResponseEntity<List<Tables>> getAllTables() {
        try {
            List<Tables> tables = new ArrayList<Tables>();
            tablesRepository.findAll().forEach(tables::add);
            return new ResponseEntity<>(tables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}