package uz.cluster.services.main;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cluster.dao.SubTabDao;
import uz.cluster.entity.SubOpt;
import uz.cluster.entity.main.SubTab;
import uz.cluster.enums.Category;
import uz.cluster.repository.TabRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TabService {

    private final TabRepository tabRepository;

    private final FetchTabService fetchTabService;

    public static Map<Category, SubTabDao> globalReferences = new HashMap<>();

    static {
        globalReferences.put(Category.PHONE, new SubTabDao(1543,"1543_81f741fe6h_1543"));
        globalReferences.put(Category.COMPUTER, new SubTabDao(2478,"2478_9yahovqmtv_2478"));
        globalReferences.put(Category.MAKE_UP, new SubTabDao(16,"16_f73xlxm7lx_16"));
        globalReferences.put(Category.MANS, new SubTabDao(743,"743_28fk0nkqyi_743"));
        globalReferences.put(Category.MARKET, new SubTabDao(15,"15_zfgy2pf1m9_15"));
        globalReferences.put(Category.SPORT, new SubTabDao(1451,"1451_v4xsznnfwd_1451"));
        globalReferences.put(Category.WOMAN, new SubTabDao(14,"14_375e9cpbfj_14"));
        globalReferences.put(Category.SHOES_AND_BAG, new SubTabDao(1281,"1281_3gpsmf9vs7_1281"));
        globalReferences.put(Category.ELECTR_DEVICE, new SubTabDao(18,"18_f4v7w6zag0_18"));
        globalReferences.put(Category.MAM_AND_BABY, new SubTabDao(4,"4_j2fbxqlnba_4"));
        globalReferences.put(Category.UNDERWEAR, new SubTabDao(1282,"1282_a2jfuf4qmz_1282"));
        globalReferences.put(Category.FOREIGN_PURCHASE, new SubTabDao(12,"12_h5ji7o3hjw_12"));
        globalReferences.put(Category.ACSESSUAR, new SubTabDao(24149,"24149_vfarp4qfal_24149"));
        globalReferences.put(Category.CAR_ACSESSUAR, new SubTabDao(2048,"2048_y3b1n8m0c8_2048"));
        globalReferences.put(Category.FURNITURE, new SubTabDao(2974,"2974_bzbva3wuub_2974"));
        globalReferences.put(Category.HOME_DECORATION, new SubTabDao(1917,"1917_f2jpknu7pg_1917"));
        globalReferences.put(Category.HOME_TEXTILE, new SubTabDao(818,"818_19dtogml03_818"));
    }

    public List<SubTab> getList(){
        return tabRepository.findAll();
    }

    public List<SubTab> getByCategory(String category){
        return tabRepository.findAllByCategory(category);
    }

    public String load(){
        try{
            for (Category key : globalReferences.keySet()){
                List<SubTab> tabList = fetchTabService.fetchAndSaveTabs(key,globalReferences.get(key));
                tabRepository.saveAll(tabList);
            }
        }catch (Exception e){
            System.out.println("Tablarni yuklashda xatolik :(");
        }
        return "Tabs yuklandi !";
    }
}
