package uz.cluster.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DidoxData {

    private String token;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private byte owner;

}
