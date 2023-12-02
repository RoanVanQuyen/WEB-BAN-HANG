package com.example.productbyroanquyen;

import com.example.productbyroanquyen.model.DanhSachMongMuon;
import com.example.productbyroanquyen.model.SanPham;
import com.example.productbyroanquyen.model.TheLoai;
import com.example.productbyroanquyen.model.repository.SanPhamRepository;
import com.example.productbyroanquyen.model.repository.TheLoaiRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class ProductByRoanquyenApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductByRoanquyenApplication.class, args);
    }
    @Autowired(required = false)
    private SanPhamRepository sanPhamRepository ;
    @Autowired(required = false)
    private TheLoaiRepository theLoaiRepository;
    @RequestMapping(value={"/home" ,""})
    public String index(HttpServletRequest request , HttpServletResponse response){
        ArrayList<SanPham> sanPhamsNew = (ArrayList<SanPham>)sanPhamRepository.findAllByOrderByNgayBanDesc() ;
        ArrayList<SanPham> sanPhamsFuture = (ArrayList<SanPham>) sanPhamRepository.findAllByOrderByMaTheLoaiAsc() ;
        List<TheLoai> theLoais = theLoaiRepository.findDistinctBy() ;
        request.setAttribute("sanPhamsNew" , sanPhamsNew);
        request.setAttribute("theLoais" , theLoais);
        request.setAttribute("sanPhamsFuture" , sanPhamsFuture);
        return "index.html" ;
    }

    @RequestMapping("")
    public String index(){
        return  "redirect:/home" ;
    }

    @Bean
    CommandLineRunner commandLineRunner(SanPhamRepository sanPhamRepository){
        return  args -> {
            List<SanPham> sanPhams = sanPhamRepository.findByTenSanPhamLikeIgnoreCase("nam");
            System.out.println(sanPhams.size() );
            for(SanPham x : sanPhams){
                System.out.println(x.getTenSanPham());
            }
        };
    }

}
