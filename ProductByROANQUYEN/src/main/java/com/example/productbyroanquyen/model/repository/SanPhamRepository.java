package com.example.productbyroanquyen.model.repository;

import com.example.productbyroanquyen.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham,String>{
    public List<SanPham> findAllByOrderByNgayBanDesc()  ;
    public List<SanPham> findAllByOrderByMaTheLoaiAsc() ;
    public Page<SanPham> findByTenSanPhamContainingIgnoreCase(String tenSanPham, PageRequest pageRequest);
    public List<SanPham> findByTenSanPhamLikeIgnoreCase(String s);
}
