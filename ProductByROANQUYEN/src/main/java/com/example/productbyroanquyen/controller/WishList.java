package com.example.productbyroanquyen.controller;

import com.example.productbyroanquyen.model.DanhSachMongMuon;
import com.example.productbyroanquyen.model.KhachHang;
import com.example.productbyroanquyen.model.SanPham;
import com.example.productbyroanquyen.model.repository.DanhSachMongMuonRepository;
import com.example.productbyroanquyen.model.repository.KhachHangRepository;
import com.example.productbyroanquyen.model.repository.SanPhamRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class WishList {
    @Autowired(required = false)
    private DanhSachMongMuonRepository danhSachMongMuonRepository ;
    @Autowired(required = false)
    private SanPhamRepository sanPhamRepository ;
    @Autowired(required = false)
    private KhachHangRepository khachHangRepository ;

    @RequestMapping("/wish-list-home")
    public String wishlisthome(@RequestParam String tenDangNhap , HttpServletRequest request){
        List<DanhSachMongMuon> danhSachMongMuons = danhSachMongMuonRepository.findAll() ;
        request.setAttribute("danhSachMongMuons" , danhSachMongMuons);
        return "wishlist.html" ;
    }

    @RequestMapping("/wish-list-home/add-to-wish-list")
    public String addtowishlist(@RequestParam String tenDangNhap , @RequestParam String maSanPham){
        if(!tenDangNhap.equals("")) {
            Optional<KhachHang> khachHang = khachHangRepository.findById(tenDangNhap);
            Optional<SanPham> sanPham = sanPhamRepository.findById(maSanPham) ;
            DanhSachMongMuon danhSachMongMuon = new DanhSachMongMuon(khachHang.get() , sanPham.get()) ;
            danhSachMongMuonRepository.save(danhSachMongMuon)  ;
            return "redirect:/wish-list-home?tenDangNhap=" + tenDangNhap ;
        }
        else return "redirect:/loginHome" ;

    }

    @RequestMapping("/wish-list-home/delete-wish-list")
    public String wishlistdelete(@RequestParam String tenDangNhap , @RequestParam String maSanPham){
        Optional<KhachHang> khachHang = khachHangRepository.findById(tenDangNhap) ;
        Optional<SanPham> sanPham = sanPhamRepository.findById(maSanPham) ;
        DanhSachMongMuon danhSachMongMuon = new DanhSachMongMuon(khachHang.get() , sanPham.get()) ;
        danhSachMongMuonRepository.delete(danhSachMongMuon);
        return "redirect:/wish-list-home?tenDangNhap=" + tenDangNhap ;
    }


}
