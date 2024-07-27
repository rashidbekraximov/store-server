package uz.cluster.services;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uz.cluster.entity.ParentData;
import uz.cluster.entity.Good;
import uz.cluster.entity.SubOpt;
import uz.cluster.entity.Tab;
import uz.cluster.repository.ParentDataRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentDataService {

    private final GetGoodService getGoodService;

    private final ParentDataRepository parentDataRepository;

    private final TranslatorService translatorService;

    public ParentData fetchAndSaveGoods() {
        String jsonResponse = getGoodService.fetchGoods();
        if (jsonResponse == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        ParentData parentData = new ParentData();
        parentData.setAppName(jsonObject.getString("app_name"));
        parentData.setServerTime(jsonObject.getLong("server_time"));

        JSONArray goodsListArray = jsonObject.getJSONArray("goods_list");
        List<Good> goodsLists = new ArrayList<>();
        for (int i = 0; i < goodsListArray.length(); i++) {
            JSONObject goodsListObject = goodsListArray.getJSONObject(i);
            Good goodsList = new Good();
            goodsList.setGoodsName(translatorService.translator(goodsListObject.getString("goods_name")));
            goodsList.setGoodsImage(goodsListObject.getString("goods_image"));
//            goodsList.set(goodsListObject.getString("description"));
            goodsList.setGoodsPrice(goodsListObject.getDouble("price_info"));
            goodsLists.add(goodsList);
        }
        parentData.setGoods(goodsLists);

        JSONArray tabListArray = jsonObject.getJSONArray("tab_list");
        List<Tab> tabs = new ArrayList<>();
        for (int i = 0; i < tabListArray.length(); i++) {
            JSONObject tabObject = tabListArray.getJSONObject(i);
            Tab tab = new Tab();
            tab.setTabName(translatorService.translator(tabObject.getString("tab_name")));
            tab.setTabId(tabObject.getInt("tab_id"));
            if (tabObject.has("tab_icon")){
                tab.setTabIcon(tabObject.getString("tab_icon"));
            }

            // Check if "sub_opt_list" exists in tabObject
            if (tabObject.has("sub_opt_list")) {
                JSONArray subTabListArray = tabObject.getJSONArray("sub_opt_list");
                List<SubOpt> subTabs = new ArrayList<>();
                for (int j = 0; j < subTabListArray.length(); j++) {
                    JSONObject subTabObject = subTabListArray.getJSONObject(j); // Use 'j' instead of 'i'
                    SubOpt subTab = new SubOpt();
                    subTab.setSubOptName(translatorService.translator(subTabObject.getString("sub_opt_name")));
                    subTab.setSubOptId(subTabObject.getInt("sub_opt_id"));
                    subTabs.add(subTab);
                }
                tab.setTabs(subTabs);
            } else {
                // Handle case where "sub_opt_list" is missing or not an array
                // You can choose to log a warning or handle it based on your application logic
                // For now, I'm assuming you want to set an empty list of subTabs
                tab.setTabs(new ArrayList<>());
            }

            tabs.add(tab);
        }

        parentData.setTabs(tabs);


        for (Good good : goodsLists){
            good.setParentData(parentData);
        }

        for (Tab tab : tabs){
            tab.setParentData(parentData);
            for (SubOpt subOpt : tab.getTabs()){
                subOpt.setTab(tab);
            }
        }

        return parentDataRepository.save(parentData);
    }

    public ParentData getGoodsById(Long id) {
        return parentDataRepository.findById(id).orElse(null);
    }

    public ParentData saveGoods(ParentData parentData) {
        return parentDataRepository.save(parentData);
    }

    public void deleteGoods(Long id) {
        parentDataRepository.deleteById(id);
    }
}

