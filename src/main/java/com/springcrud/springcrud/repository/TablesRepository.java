package com.springcrud.springcrud.repository;

//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springcrud.springcrud.model.Tables;

public interface TablesRepository extends JpaRepository<Tables, Long> {
    //List<Tables> findByNameContaining(String name);
}
