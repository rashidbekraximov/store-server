package uz.cluster.services;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cluster.entity.Good;
import uz.cluster.repository.GoodRepository;
import uz.cluster.util.Converter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;

    public List<Good> getList(){
        List<Good> goods = goodRepository.findAll();
                goods.forEach(good -> {
                    double newPrice = good.getGoodsPrice() * 1830 * 1.4;
                    good.setGoodsPrice(Converter.roundUsingBigDecimal(newPrice,2));
                });
                return goods;
    }
}
