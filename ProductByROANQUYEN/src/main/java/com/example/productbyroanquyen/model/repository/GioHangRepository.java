package com.example.productbyroanquyen.model.repository;

import com.example.productbyroanquyen.model.GioHang;
import com.example.productbyroanquyen.model.GioHangId;
import com.example.productbyroanquyen.model.KhachHang;
import com.example.productbyroanquyen.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GioHangRepository extends JpaRepository<GioHang , GioHangId> {
    public List<GioHang> findAllByTenDangNhap(KhachHang khachHang) ;
}
