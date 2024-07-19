package com.chou.uc_module.inf.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PoiModel {

    private String content;

    private String oldContent;

    private String primaryKey;

    private int rowIndex;

    private int cellIndex;

}
