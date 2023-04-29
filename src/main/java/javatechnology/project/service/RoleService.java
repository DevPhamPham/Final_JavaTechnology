package javatechnology.project.service;

import javatechnology.project.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> getAllRole();
    Optional<Role> findRoleById(int id);

}
