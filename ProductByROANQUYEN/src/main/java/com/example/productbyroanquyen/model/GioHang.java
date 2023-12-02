package com.example.productbyroanquyen.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.text.DecimalFormat;

@Getter @Setter @ToString
@Entity @AllArgsConstructor @NoArgsConstructor
@Table(name = "GioHang")
@IdClass(GioHangId.class)
public class GioHang {
    @Id @ManyToOne @JoinColumn(name =  "tenDangNhap")
    private KhachHang tenDangNhap ;

    @Id @ManyToOne @JoinColumn(name = "maSanPham")
    private SanPham maSanPham ;
    @Column(length = 12)
    private int soLuong ;
    public String getTongTienTungSanPham(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.#") ;
        return decimalFormat.format((double) (soLuong * maSanPham.getGiaSanPhamDouble())) + ".000VND" ;
    }
}
