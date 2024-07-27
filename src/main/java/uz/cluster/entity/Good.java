package uz.cluster.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_data_id")
    private ParentData parentData;

    private String mktTrSc;
    private String priceInfo;
    private String goodsName;
    private String goodsImage;
    private String salesTip;
    private boolean showCouponTag;
    private int mktDomain;
    private int overseaType;
    private double goodsPrice;
    private String goodsId;
    private String mktTrToken;
    private String hdThumbUrl;
    private int sales;
    private double marketPrice;
    private String linkUrl;
    private int priceType;
    private int goodsType;

//    @Embedded
//    private Ext ext;

//    @Embedded
//    private Ad ad;

//    @ElementCollection
//    private List<Tag> tagList;
//
//    @ElementCollection
//    private List<Icon> iconList;

//    @Embedded
//    private PRec pRec;

    // Getters and Setters
}

