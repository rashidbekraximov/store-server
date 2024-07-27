package uz.cluster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tabId;
    private String tabName;
    private String tabIcon;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_data_id")
    private ParentData parentData;

    @OneToMany(mappedBy = "tab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubOpt> tabs;
}
