package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping ({"/listProducts", "/", "/list"})
    public ModelAndView showProducts() {
        ModelAndView mav = new ModelAndView("list-products");
        List<Product> list = productRepository.findAll();
        mav.addObject("products", list);
        return mav;
    }
    //Handler Method
    @GetMapping ("/addProductForm")
    public ModelAndView addProductForm() {
        ModelAndView mav = new ModelAndView("add-product-form");
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/list";
    }
}
