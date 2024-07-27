package uz.cluster.entity.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.enums.Category;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;
    private String goodsName;
    private String thumbUrl;
    private String pricePrefixInfo;
    private String hdThumbUrl;
    private int customerNum;
    private String hdUrl;
    private int sales;
    private int tagStyle;
    private int eventType;
    private int price;
    private String linkUrl;
    private int isApp;
    private String salesTip;
    private int quantity;
    private int cnt;
    private int quality;
    private int normalPrice;
    private int marketPrice;
    private int priceType;
    private String shortName;
    private int goodsType;
    private boolean needAdLogo;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category",columnDefinition = "varchar(255) default 'RECOMMENDED'")
    private Category category = Category.RECOMMENDED;

}
