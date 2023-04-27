package com.hemanth.Roles.controller;

import com.hemanth.Roles.dto.RolesDto;
import com.hemanth.Roles.entity.Roles;
import com.hemanth.Roles.pageSetting.PageContent;
import com.hemanth.Roles.pageSetting.PageSetting;
import com.hemanth.Roles.service.RolesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/roles")
public class RolesController
{

    @Autowired
    RolesService rolesService;

    @RequestMapping(method = RequestMethod.POST)
    public RolesDto addRoles(@Valid @RequestBody RolesDto roles)
    {
        return rolesService.addRoles(roles);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RolesDto> getRoles()
    {
        return rolesService.getRoles();

    }



    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    public Optional<Roles> getRolesById(@PathVariable UUID id)
    {
        return rolesService.getRolesById(id);

    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public String deleteRolesById(@PathVariable UUID id)
    {
        return rolesService.deleteById(id);

    }

    @RequestMapping(method = RequestMethod.PUT,value="/{id}")
    public String updateRolesById(@PathVariable UUID id,@RequestBody Roles roles)
    {
        return rolesService.updateRoles(id,roles);

    }

    @GetMapping("/page")
    public Page<Roles> getStudentPage(PageSetting pageSettings)
    {

        log.info(
                "Request for Roles page received with data : " + pageSettings);

        return rolesService.getStudentPage(pageSettings);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/paging")
    public PageContent getStudents
            (
            @RequestParam(value="pageNo",defaultValue = "1",required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue = "1",required = false)int pageSize
    )
    {
        return rolesService.getByPage(pageNo,pageSize);
    }




}
