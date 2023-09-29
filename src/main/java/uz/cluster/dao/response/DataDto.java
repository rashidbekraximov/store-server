package uz.cluster.dao.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto<T> implements Serializable {
    protected T data;

    protected boolean success;

    private Long totalCount;

    public DataDto(boolean success) {
        this.success = success;
    }

    public DataDto(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }
}
