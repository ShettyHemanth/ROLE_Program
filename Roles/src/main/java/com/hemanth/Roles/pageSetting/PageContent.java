package com.hemanth.Roles.pageSetting;

import com.hemanth.Roles.dto.RolesDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Data
public class PageContent
{
    List<RolesDto> content;
    int pageNo;

    int pageSize;

    int total_Element;

}
