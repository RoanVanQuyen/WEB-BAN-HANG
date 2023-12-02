package com.example.productbyroanquyen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @ToString
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "KhachHang")
public class KhachHang {
    @Id @Column(length = 30)
    private String tenDangNhap ;
    @Column(length = 40)
    private String matKhau ;
    @Column (length = 45)
    private String tenKhachHang ;
    @Column(length = 50)
    private String email ;
    @Column(length = 15)
    private String dienThoai ;
}
