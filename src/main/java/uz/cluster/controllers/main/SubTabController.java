package uz.cluster.controllers.main;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cluster.entity.main.SubTab;
import uz.cluster.services.main.FetchTabService;
import uz.cluster.services.main.TabService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubTabController {

    private final TabService tabService;

    @GetMapping("/sub-tab/load")
    public String getList(){
        return tabService.load();
    }

    @GetMapping("/sub-tab/{category}")
    public List<SubTab> getByCategory(@PathVariable String category){
        return tabService.getByCategory(category);
    }

}
