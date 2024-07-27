package uz.cluster.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.cluster.entity.ParentData;
import uz.cluster.services.ParentDataService;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentDataController {

    private final ParentDataService parentDataService;

    @GetMapping
    public ParentData getAllGoods() {
        return parentDataService.fetchAndSaveGoods();
    }

//    @GetMapping
//    public ParentData getAllByGoods() {
//        return parentDataService.fetchAndSaveGoods();
//    }

    @GetMapping("/{id}")
    public ParentData getGoodsById(@PathVariable Long id) {
        return parentDataService.getGoodsById(id);
    }

    @PostMapping
    public ParentData saveGoods(@RequestBody ParentData parentData) {
        return parentDataService.saveGoods(parentData);
    }

    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable Long id) {
        parentDataService.deleteGoods(id);
    }
}

