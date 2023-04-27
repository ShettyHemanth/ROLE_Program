package com.hemanth.Roles.service;

import com.hemanth.Roles.dto.RolesDto;
import com.hemanth.Roles.entity.Roles;
import com.hemanth.Roles.mapper.MapperClass;
import com.hemanth.Roles.pageSetting.PageContent;
import com.hemanth.Roles.pageSetting.PageSetting;
import com.hemanth.Roles.repository.RolesRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RolesService
{

    @Autowired
    RolesRepository rolesRepository;

    public RolesDto addRoles(RolesDto rolesDto)
    {

        Roles newRole=new Roles();

        newRole=MapperClass.rolesDtoToRole(rolesDto);


        rolesRepository.save(newRole);

        return rolesDto;

    }


    public List<RolesDto> getRoles()
    {




        List<Roles> result=rolesRepository.findAll();

        List<RolesDto> newResult=new ArrayList<>();

        for(Roles i:result)
        {
           RolesDto n=MapperClass.rolesToRoleDto(i);
           newResult.add(n);

        }

        return newResult;


    }

    @Transactional()
    public String updateRoles(UUID id,Roles roles)
    {


        Roles newRole=rolesRepository.findById(id).get();
        newRole.setRoleState(roles.getRoleState());
        newRole.setRoleName(roles.getRoleName());
        newRole.setRoleId(roles.getRoleId());
        newRole.setCreatedDate(newRole.getCreatedDate());
        newRole.setOrgName(roles.getOrgName());
        rolesRepository.save(newRole);

        return "Updated Successfully";
    }

    public String deleteById(UUID id)
    {
        rolesRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public Optional<Roles> getRolesById(UUID id)
    {
        Optional<Roles> role=rolesRepository.findById(id);
        return role;
    }


    public Page<Roles> getStudentPage(@NonNull PageSetting pageSetting)
    {

        Sort roleSort = pageSetting.buildSort();
        Pageable rolePage = PageRequest.of(pageSetting.getPage(), pageSetting.getElementPerPage(), roleSort);

        return rolesRepository.findAll(rolePage);
    }


    public PageContent getByPage(int pageNo, int pageSize)
    {

        PageRequest pageable=PageRequest.of(pageNo,pageSize);

        Page<Roles> roleList=rolesRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        List<Roles> role_result=roleList.getContent();

        List<RolesDto> rolesDtos=new ArrayList<>();


        for(Roles i:role_result)
        {
            RolesDto result=MapperClass.rolesToRoleDto(i);
            rolesDtos.add(result);
        }





//        List<RolesDto> content= role_result.stream().map(s->(s)).collect(Collectors.toList());





        PageContent pageContent=new PageContent();
        pageContent.setContent(rolesDtos);
        pageContent.setPageNo(roleList.getNumber());
        pageContent.setPageSize(roleList.getSize());
        pageContent.setTotal_Element((int) roleList.getTotalElements());


        return pageContent;


    }




}
