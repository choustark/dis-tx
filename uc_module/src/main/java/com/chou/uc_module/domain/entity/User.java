package com.chou.uc_module.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Chou
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId( type = IdType.ID_WORKER)
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 余额
     */
    private Integer deposit;


}
