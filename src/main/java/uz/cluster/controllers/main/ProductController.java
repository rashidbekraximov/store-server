package uz.cluster.controllers.main;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.entity.main.Product;
import uz.cluster.services.main.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/load")
    public String loadProduct(){
        return productService.load();
    }

    @GetMapping("/main-product/load")
    public String loadMainProduct(){
        return productService.loadMainProduct();
    }

    @GetMapping("/product/list/{category}")
    public List<Product> list(@PathVariable String category){
        return productService.getList(category);
    }






}
