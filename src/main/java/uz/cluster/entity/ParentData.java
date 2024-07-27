package uz.cluster.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appName;
    private Long serverTime;

    @OneToMany(mappedBy = "parentData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Good> goods;

    @OneToMany(mappedBy = "parentData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tab> tabs;
}
