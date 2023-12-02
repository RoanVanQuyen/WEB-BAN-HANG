package com.example.productbyroanquyen.controller;

import com.example.productbyroanquyen.model.SanPham;
import com.example.productbyroanquyen.model.TheLoai;
import com.example.productbyroanquyen.model.repository.SanPhamRepository;
import com.example.productbyroanquyen.model.repository.TheLoaiRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Shop {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private TheLoaiRepository theLoaiRepository ;
    @RequestMapping("/shop")
    public String shop(HttpServletResponse response, HttpServletRequest request) {

        int soLuongSanPhamDangCo = sanPhamRepository.findAll().size();
        String select = request.getParameter("orderby");
        String page_select_String = request.getParameter("page_select") == null ? "9" : request.getParameter("page_select");
        int page_select_Integer = Integer.parseInt(page_select_String);
        select = select == null ? "3" : select;
        String Find_Ten_San_Pham = request.getParameter("Find_Ten_San_Pham") == null ? "" : request.getParameter("Find_Ten_San_Pham").trim();
        int PCB = Integer.parseInt(request.getParameter("PCB") == null ? "1" : request.getParameter("PCB"));


        Page<SanPham> sanPhams = sanPhamRepository.findByTenSanPhamContainingIgnoreCase(Find_Ten_San_Pham, PageRequest.of((PCB - 1), page_select_Integer, Sort.by("tenSanPham")));
        if (select.equals("1")) {
            sanPhams = sanPhamRepository.findByTenSanPhamContainingIgnoreCase(Find_Ten_San_Pham, PageRequest.of((PCB - 1), page_select_Integer, Sort.by("giaSanPham")));
        }
        if (select.equals("2")) {
            sanPhams = sanPhamRepository.findByTenSanPhamContainingIgnoreCase(Find_Ten_San_Pham, PageRequest.of((PCB - 1), page_select_Integer, Sort.by("giaSanPham").descending()));
        }
        if (select.equals("4")) {
            sanPhams = sanPhamRepository.findByTenSanPhamContainingIgnoreCase(Find_Ten_San_Pham, PageRequest.of((PCB - 1), page_select_Integer, Sort.by("soLuong")));
        }
        List<TheLoai> theLoais = theLoaiRepository.findDistinctBy() ;

//        request.setAttribute("PCB" , PCB);
        request.setAttribute("theLoais" , theLoais);
        request.setAttribute("Find_Ten_San_Pham", Find_Ten_San_Pham);
        request.setAttribute("select", select);
        request.setAttribute("page_select_Integer", page_select_Integer);
        request.setAttribute("showing", Math.min(page_select_Integer, sanPhams.getContent().size()));
        request.setAttribute("sanPhams", sanPhams);
        request.setAttribute("soLuongSanPhamDangCo", soLuongSanPhamDangCo);
        return "shop.html";
    }


//    @RequestMapping("/shop/search")
//    public String search(RedirectAttributes redirectAttributes,  HttpServletRequest request) {
//        String tenSanPham =  request.getParameter("tenSanPham") == null ? "" : request.getParameter("tenSanPham") ;
//        Page<SanPham> sanPhams = sanPhamRepository.findByTenSanPhamContainingIgnoreCase(tenSanPham, PageRequest.of(0, 3));
//        for (SanPham x : sanPhams) {
//            System.out.println(x.getTenSanPham());
//        }
//        return "redirect:/shop";
//    }


    @GetMapping("/chi-tiet-san-pham")
    public String chiTietSanPham(HttpServletRequest request) {
        String maSanPham = request.getParameter("maSanPham") ;
        Optional<SanPham> sanPham = sanPhamRepository.findById(maSanPham) ;
        String status = "" ;
        if(sanPham.get().getSoLuong() > 0){
            status = "In stock" ;
        }
        else status = "Un in stock" ;
        String theLoai = sanPham.get().getMaTheLoai().getMaTheLoai() ;
        List<SanPham> sanPhamsByTheLoai = sanPhamRepository.findAllByOrderByMaTheLoaiAsc() ;
        for(SanPham x :sanPhamsByTheLoai){
            if(x.getMaSanPham().equals(maSanPham)){
                sanPhamsByTheLoai.remove(x) ;
                break;
            }
        }
        request.setAttribute("sanPhamsByTheLoai" , sanPhamsByTheLoai);
        request.setAttribute("sanPham" , sanPham);
        request.setAttribute("status" , status);
        return "single-product.html";
    }
}
