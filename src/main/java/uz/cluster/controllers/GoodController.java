package uz.cluster.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.entity.Good;
import uz.cluster.entity.ParentData;
import uz.cluster.services.GoodService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService goodService;

    @GetMapping("/goods/list")
    public List<Good> getAllGoods() {
        return goodService.getList();
    }

}
