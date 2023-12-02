package com.example.productbyroanquyen.controller;

import com.example.productbyroanquyen.model.GioHang;
import com.example.productbyroanquyen.model.KhachHang;
import com.example.productbyroanquyen.model.SanPham;
import com.example.productbyroanquyen.model.TheLoai;
import com.example.productbyroanquyen.model.repository.GioHangRepository;
import com.example.productbyroanquyen.model.repository.KhachHangRepository;
import com.example.productbyroanquyen.model.repository.SanPhamRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Cart {
    @Autowired
    GioHangRepository gioHangRepository ;
    @Autowired
    SanPhamRepository sanPhamRepository ;

    @RequestMapping("/cart")
    public String select(HttpServletRequest request){
        System.out.println(request.getParameter("tenDangNhap"));
        String tenDangNhap = request.getParameter("tenDangNhap") == null ? "" : request.getParameter("tenDangNhap") ;
        List<GioHang> sanPhams = new ArrayList<>() ;
        System.out.println(tenDangNhap);
        KhachHang one = new KhachHang() ;
        one.setTenDangNhap(tenDangNhap);
        sanPhams = gioHangRepository.findAllByTenDangNhap(one) ;
        double tongTien = 0.0 ;
        for(GioHang x : sanPhams){
            tongTien = tongTien + x.getSoLuong() * x.getMaSanPham().getGiaSanPhamDouble() ;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.#") ;
        request.setAttribute("tienShip" , decimalFormat.format(30));
        request.setAttribute("tongTien" , decimalFormat.format(tongTien));
        request.setAttribute("tongTienCaShip" , decimalFormat.format(tongTien + 30));
        request.setAttribute("sanPhams" , sanPhams);
        return "cart.html" ;
    }
    @Autowired
    KhachHangRepository khachHangRepository ;
    @RequestMapping("/cart/add-to-cart")
    public String addToCart(HttpServletRequest request){
        String maSanPham = request.getParameter("maSanPham" );
        String tenDangNhap = request.getParameter("tenDangNhap") == null ? "" :  request.getParameter("tenDangNhap") ;
        String soLuong = request.getParameter("soLuong") == null ? "1" : request.getParameter("soLuong");
        Optional<SanPham> sanPham = sanPhamRepository.findById(maSanPham) ;
        Optional<KhachHang> khachHang = khachHangRepository.findById(tenDangNhap) ;
        if(!tenDangNhap.equals("")) {
            GioHang gioHang = new GioHang(khachHang.get(), sanPham.get(), Integer.parseInt(soLuong));
            gioHangRepository.save(gioHang);
            return "redirect:/cart?tenDangNhap=" + tenDangNhap;
        }
        else{
            return "redirect:/loginHome" ;
        }
    }

    @RequestMapping("/cart/delete-cart")
    public String deleteCart(HttpServletRequest request){
        String maSanPham = request.getParameter("maSanPham") == null ? "" : request.getParameter("maSanPham") ;
        String tenDangNhap = request.getParameter("tenDangNhap") == null ? "" :  request.getParameter("tenDangNhap") ;
        Optional<SanPham> sanPham = sanPhamRepository.findById(maSanPham) ;
        Optional<KhachHang> khachHang = khachHangRepository.findById(tenDangNhap) ;
        GioHang gioHang = new GioHang(khachHang.get(), sanPham.get(), 0);
        gioHangRepository.delete(gioHang);
        return "redirect:/cart?tenDangNhap=" + tenDangNhap ;
    }

    @RequestMapping("/cart/update-cart")
    public String updateCart(@RequestParam String[] soLuong , @RequestParam String tenDangNhap){
        List<GioHang> gioHangs = gioHangRepository.findAll() ;
        for(int i = 0 ; i < gioHangs.size() ; i++){
            if(gioHangs.get(i).getSoLuong() != Integer.parseInt(soLuong[i].toString())){
                gioHangs.get(i).setSoLuong(Integer.parseInt(soLuong[i].toString()));
                gioHangRepository.save(gioHangs.get(i)) ;
            }
        }
        return "redirect:/cart?tenDangNhap="+tenDangNhap  ;
    }
}
