package com.example.productbyroanquyen.controller;

import com.example.productbyroanquyen.model.KhachHang;
import com.example.productbyroanquyen.model.repository.KhachHangRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Login {
    @Autowired
    private KhachHangRepository khachHangRepository ;

    @RequestMapping("loginHome")
    public String loginHome(HttpSession session){
        session.invalidate();
        return "login.html" ;
    }
    @RequestMapping("/register")
    public String register(@ModelAttribute KhachHang khachHang , RedirectAttributes redirectAttributes){
        System.out.println(khachHang.toString());
        List<KhachHang> khachHangs = khachHangRepository.findAll() ;
        String tenDangNhapBiSai = "adsfjhhj" ;
        for(KhachHang x : khachHangs){
            if(x.getTenDangNhap().trim().equals(khachHang.getTenDangNhap().trim())){
                tenDangNhapBiSai = "Tên đăng nhập đã tồn tại" ;
                redirectAttributes.addFlashAttribute("tenDangNhapBiSai" , tenDangNhapBiSai) ;
                return "login.html" ;
            }
        }
        khachHangRepository.save(khachHang) ;

        redirectAttributes.addFlashAttribute("h4Login" , "Đăng kí thành công tài khoản bạn có thể đăng nhập") ;
        return "login.html";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request , RedirectAttributes redirectAttributes , HttpSession session){
        String tenDangNhap = request.getParameter("tenDangNhap" );
        String matKhau = request.getParameter("matKhau") ;
        List<KhachHang> khachHangs = khachHangRepository.findAll() ;
        for(KhachHang x : khachHangs){
            if(x.getTenDangNhap().trim().equals(tenDangNhap.trim()) && x.getMatKhau().trim().equals(matKhau.trim())) {
                session.setAttribute("tenKhachHang" , x.getTenKhachHang());
                session.setAttribute("tenDangNhap" , x.getTenDangNhap());
                return "redirect:/home" ;
            }
        }
//        redirectAttributes.addFlashAttribute("h4Login" , "Sai tên đăng nhập hoặc mật khẩu") ;
        request.setAttribute("h4Login" , "Sai tên đăng nhập hoặc mật khẩu");
        return  "login.html" ;
    }
}
