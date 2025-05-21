package likelion.team6th.fortune.repository;

import likelion.team6th.fortune.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAdminId(String adminId);

}
