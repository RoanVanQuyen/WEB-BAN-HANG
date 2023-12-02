package com.example.productbyroanquyen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "DanhSachMongMuon")
@IdClass(DanhSachMongMuonId.class)
public class DanhSachMongMuon {
    @Id @ManyToOne @JoinColumn(name="tenDangNhap")
    private KhachHang tenDangNhap ;

    @Id @ManyToOne @JoinColumn(name = "maSanPham")
    private SanPham maSanPham ;
    public String getStatus(){
        int soLuong = maSanPham.getSoLuong();
        if(soLuong != 0) return "In Stock" ;
        else{
            return "Un In Stock" ;
        }
    }
}
