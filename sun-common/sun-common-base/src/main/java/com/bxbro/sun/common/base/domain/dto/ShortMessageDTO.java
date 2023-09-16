package com.bxbro.sun.common.base.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/9/16 19:54
 * @since: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortMessageDTO extends CommonMessageDTO{

    private String key;

    private String secret;
}
