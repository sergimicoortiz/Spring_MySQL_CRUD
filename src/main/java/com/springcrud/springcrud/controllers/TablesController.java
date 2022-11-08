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
import org.springframework.web.bind.annotation.RestController;

import com.springcrud.springcrud.model.Tables;
import com.springcrud.springcrud.repository.TablesRepository;

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

            // if (tables.isEmpty()) {
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // }

            return new ResponseEntity<>(tables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }// get all

    @GetMapping("tables/{id}")
    public ResponseEntity<Tables> getOneTables(@PathVariable(required = true) Long id) {
        try {
            Optional<Tables> findTable = tablesRepository.findById(id);

            if (findTable.isPresent()) {
                return new ResponseEntity<>(findTable.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }// get one

    @PostMapping("tables")
    public ResponseEntity<Tables> createTable(@RequestBody Tables tables) {
        try {
            if (tables.getName().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Tables newTable = tablesRepository
                    .save(new Tables(tables.getName()));
            return new ResponseEntity<>(newTable, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }// create

    @DeleteMapping("tables/{id}")
    public ResponseEntity<HttpStatus> deleteTable(@PathVariable(required = true) Long id) {
        try {
            tablesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }// delete

    @PutMapping("tables/{id}")
    public ResponseEntity<Tables> updateTable(@PathVariable(required = true) Long id,
            @RequestBody Tables newTables) {
        try {
            Optional<Tables> findTable = tablesRepository.findById(id);
            if (findTable.isPresent()) {
                Tables updateTable = findTable.get();
                updateTable.setName(newTables.getName());
                return new ResponseEntity<>(tablesRepository.save(updateTable), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }// update
}
