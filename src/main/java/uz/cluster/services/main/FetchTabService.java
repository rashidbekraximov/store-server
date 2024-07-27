package uz.cluster.services.main;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uz.cluster.dao.SubTabDao;
import uz.cluster.entity.main.Product;
import uz.cluster.entity.main.SubTab;
import uz.cluster.enums.Category;
import uz.cluster.services.TranslatorService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FetchTabService {

    private final TranslatorService translatorService;

    public String fetchTabs(SubTabDao subTabDao) {
        try {
            // URL to send the GET request to
            String url = "https://mobile.yangkeduo.com/proxy/api/api/caterham/query/opt2_brand_pcard?pdduid=4548925868544&" +
                    "opt1_id=" +
                    subTabDao.getOptId() +
                    "&" +
                    "list_id=" +
                    subTabDao.getListId() +
                    "`" +
                    "&support_type=10_11";
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Setting request method to GET
            connection.setRequestMethod("GET");

            // Setting request headers
            connection.setRequestProperty("Cookie", "api_uid=CigYCGaJka2KlwBXn5ieAg==; _nano_fp=XpmxX0PbXpmjX0doXT_nV5ZvDvsEMydYN1pZjAKf; webp=1; jrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; njrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; dilx=O4jEN1EmSMqkKIgsWNlSL; PDDAccessToken=6QHXOCLPQC442FMV7JZVPG3QKLIA5ZWAZE243GCI46HR7576O7QQ122006a; pdd_user_id=4548925868544; pdd_user_uin=NHBJAIRGBKAZMDMLJN5LBYXVL4_GEXDA; pdd_vds=gaBCeuIDdmwBuCmNeewwlLldbdIfewnuwTeDlmdNNfedBlnNNdLNmfbDBswL");
            connection.setRequestProperty("Priority", "u=1, i");
            connection.setRequestProperty("Referer", "https://mobile.yangkeduo.com/?page_id=10002_1721288619453_vwml9hv4gf&bsch_is_search_mall=&bsch_show_active_page=&lastTabItemID=13");
            connection.setRequestProperty("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
            connection.setRequestProperty("Sec-Ch-Ua-Mobile", "?0");
            connection.setRequestProperty("Sec-Ch-Ua-Platform", "\"Windows\"");
            connection.setRequestProperty("Sec-Fetch-Dest", "empty");
            connection.setRequestProperty("Sec-Fetch-Mode", "cors");
            connection.setRequestProperty("Sec-Fetch-Site", "same-origin");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            connection.setRequestProperty("Verifyauthtoken", "ZAv9-bk9tGyG3bcCao7PlQaabea93852789aebb");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Reading response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print result
            System.out.println(response.toString());

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public List<SubTab> fetchAndSaveTabs(Category category, SubTabDao subTabDao) {
        String jsonResponse = fetchTabs(subTabDao);
        if (jsonResponse == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject jsonData = jsonObject.getJSONObject("data");

        JSONArray tabsListArray = jsonData.getJSONArray("opt2_list");
        List<SubTab> subTabs = new ArrayList<>();
        for (int i = 0; i < tabsListArray.length(); i++) {
            JSONObject subTab = tabsListArray.getJSONObject(i);
            SubTab productSave = new SubTab();
            productSave.setOptName(translatorService.translator(subTab.getString("opt_name")));
            productSave.setImageUrl(subTab.getString("image_url"));
            productSave.setId(subTab.getInt("id"));
            productSave.setHomePriority(subTab.getInt("home_priority"));
            productSave.setHomeManPriority(subTab.getInt("home_man_priority"));
            productSave.setGoodsId(subTab.getLong("goods_id"));
            productSave.setCategory(category);
            subTabs.add(productSave);
        }
        return subTabs;
    }
}
