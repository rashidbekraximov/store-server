package uz.cluster.dao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class FacturaDTO {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<FacturaData> data;

    // Constructor, getters, and setters
}


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

class Buttons {
    private boolean edit;
    private boolean editProductList;
    private boolean delete;
    private boolean sign;
    private boolean showPdf;
    private boolean accept;
    private boolean reject;
    private boolean clone;
    private boolean cancel;
    private boolean incompleted;

    // Constructor, getters, and setters
}



