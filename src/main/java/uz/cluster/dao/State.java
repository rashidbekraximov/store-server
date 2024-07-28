package uz.cluster.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {
    private String stateId;

    @JsonProperty("StateName")
    private String stateName;
    private String timestamp;
    private String tin;
    private String fio;
    private String host;
    private String clientIp;
    private String dataSource;
    private String dataSourceName;
    private String certificateId;
    private String notes;

    // Constructor, getters, and setters
}
