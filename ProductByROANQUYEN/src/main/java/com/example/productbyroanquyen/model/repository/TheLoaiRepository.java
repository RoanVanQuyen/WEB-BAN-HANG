package com.example.productbyroanquyen.model.repository;

import com.example.productbyroanquyen.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheLoaiRepository extends JpaRepository<TheLoai , String> {
    public List<TheLoai> findDistinctBy();
}
