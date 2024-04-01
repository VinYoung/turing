package vip.vinyoung.tools.bean.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pageable {
    private int pageSize;

    private int pageNum;

    private int total;
}
