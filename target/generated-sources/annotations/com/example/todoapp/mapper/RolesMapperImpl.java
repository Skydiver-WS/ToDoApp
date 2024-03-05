package com.example.todoapp.mapper;

import com.example.todoapp.entity.Role;
import com.example.todoapp.web.response.user.RoleResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-05T21:29:46+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class RolesMapperImpl implements RolesMapper {

    @Override
    public List<RoleResponse> roleToRoleResponse(List<Role> roleList) {
        if ( roleList == null ) {
            return null;
        }

        List<RoleResponse> list = new ArrayList<RoleResponse>( roleList.size() );
        for ( Role role : roleList ) {
            list.add( roleToRoleResponse1( role ) );
        }

        return list;
    }

    protected RoleResponse roleToRoleResponse1(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setRole( role );

        return roleResponse;
    }
}
