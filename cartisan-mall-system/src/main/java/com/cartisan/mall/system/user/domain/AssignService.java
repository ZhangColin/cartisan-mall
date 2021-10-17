package com.cartisan.mall.system.user.domain;

import com.cartisan.mall.system.organization.Organization;
import com.cartisan.mall.system.organization.OrganizationRepository;
import com.cartisan.mall.system.role.RoleRepository;
import com.cartisan.mall.system.role.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class AssignService {
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;

    public AssignService(RoleRepository roleRepository,
                         OrganizationRepository organizationRepository) {
        this.roleRepository = roleRepository;
        this.organizationRepository = organizationRepository;
    }

    public void assignRoles(User user, List<Long> roleIds) {
        if (roleIds == null) {
            return;
        }
        final List<Long> ensureRoleIds = roleRepository.findAllById(roleIds)
                .stream().map(Role::getId).collect(toList());

        user.assignRoles(ensureRoleIds);
    }

    public void assignOrganization(final User user, List<Long> organizationIds) {
        if (organizationIds == null) {
            return;
        }
        List<Long> ensureOrganizationIds = organizationRepository.findAllById(organizationIds)
                .stream().map(Organization::getId).collect(toList());
        user.assignOrganizations(ensureOrganizationIds);
    }
}
