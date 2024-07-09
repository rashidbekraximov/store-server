package uz.cluster.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.cluster.dao.*;
import uz.cluster.excel.Driver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class DoctorController {

    private final Driver reportSheet;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping("login")
    public DidoxData login(@RequestBody Login res) {
        Map<String, String> params = new HashMap<>();
        params.put("password", res.getPassword());
        DidoxData obj = restTemplate.postForEntity("https://api.didox.uz/v1/auth/" + res.getLogin() + "/password/uz", params, DidoxData.class).getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        assert obj != null;
        headers.setBearerAuth(obj.getToken());
        headers.set("User-Key",obj.getToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Profile profile = restTemplate.exchange("https://api2.didox.uz/v1/profile", HttpMethod.GET, entity, Profile.class).getBody();
        assert profile != null;
        obj.setName(profile.getShortName());
        return obj;
    }


    @PostMapping("test")
    public void getList(HttpServletResponse response, @RequestBody DidoxData res) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(res.getToken());
        headers.set("User-Key",res.getToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Response obj = restTemplate.exchange("https://api.didox.uz/v2/documents?owner=" + res.getOwner() + "&dateFromUpdated=" + res.getBeginDate() + "&dateToUpdated=" + res.getEndDate(), HttpMethod.GET, entity, Response.class).getBody();

        if (obj != null) {
            for (Document doc : obj.getData()){
                    List<TestProduct> products = new ArrayList<>();
                    ResOne resOne = restTemplate.exchange("https://api2.didox.uz/v1/documents/" + doc.getDoc_id(), HttpMethod.GET, entity, ResOne.class).getBody();
                    if (resOne != null && resOne.getData().getJson().getProductlist() != null) {
                        for (TestProduct test : resOne.getData().getJson().getProductlist().getProducts()){
                            products.add(test);
                        }
                    }
                    doc.setProducts(products);
                assert resOne != null;
                doc.setDocument_json(resOne.getData().getJson());
            }
        }
        reportSheet.excelTabelSheet(response,obj,res.getOwner());
    }
}
