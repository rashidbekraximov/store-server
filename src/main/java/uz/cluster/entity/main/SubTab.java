package uz.cluster.entity.main;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cluster.enums.Category;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTab {

    @Id
    private int id;

    private String imageUrl;

    //    private PRec pRec;
    private long goodsId;

    private String optName;

    private int homePriority;

    private int homeManPriority;

    private int brandId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private Category category = Category.RECOMMENDED;

}
