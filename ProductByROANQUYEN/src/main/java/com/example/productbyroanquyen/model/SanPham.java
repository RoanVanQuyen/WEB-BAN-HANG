package com.example.productbyroanquyen.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.text.DecimalFormat;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name =  "SanPham")
public class SanPham {
    @Column(length =  25 )
    @Id
    private String maSanPham ;
    @Column(length = 100)
    private String tenSanPham ;
    @Column()
    private double giaSanPham ;
    @ManyToOne @JoinColumn(name = "maTheLoai")
    private TheLoai maTheLoai ;
    @Column(columnDefinition = "text")
    private String moTaChiTiet ;
    @Column
    private Integer soLuong ;
    @Column
    private String hinhAnhSanPham00 ;
    @Column
    private String hinhAnhSanPham01 ;
    @Column
    private String hinhAnhSanPham02 ;
    @Column
    private String hinhAnhSanPham03 ;
    @Column
    private Date ngayBan ;
    @Column
    private Double phanTranGiamGia = 0.0 ;

    public String getGiaSanPham(){
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.#") ;
        return decimalFormat.format((int)(giaSanPham - giaSanPham *(phanTranGiamGia/100)))  + ".000.VND";
    }
    public String getGiaChuaGiam(){
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.#") ;
        return decimalFormat.format((int) giaSanPham)  +"000.VND";
    }
    public int getGiaSanPhamDouble(){
        return (int) (giaSanPham - giaSanPham * (phanTranGiamGia/100)) ;
    }
}
