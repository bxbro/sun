package com.bxbro.sun.platform.domain.request;

import com.bxbro.sun.core.model.BaseRequest;
import lombok.Data;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/2/11 23:25
 * @since: 1.0
 */
@Data
public class UpsertTaskRequest extends BaseRequest {

    private Long id;

    private String taskName;

    /**
     * {@link com.bxbro.sun.common.enums.TaskTypeEnum}
     */
    private Integer taskType;

    private Long userId;

    private String deadline;

    private String content;
}
