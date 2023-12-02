package com.example.productbyroanquyen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "TheLoai")
public class TheLoai {
    @Id
    @Column(length =  25)
    private String maTheLoai ;
    @Column(length = 60)
    private String tenTheLoai ;
}
