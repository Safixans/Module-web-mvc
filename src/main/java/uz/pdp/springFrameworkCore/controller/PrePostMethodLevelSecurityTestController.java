package uz.pdp.springFrameworkCore.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
public class PrePostMethodLevelSecurityTestController {
    @GetMapping("/has_role_admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "has_role_admin";
    }

    @GetMapping("/has_role_manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String manager() {
        return "has_role_manager";
    }

    @GetMapping("/has_any_role_admin_manager_user")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public String profile() {
        return "admin manager user can observe this page";
    }

    @GetMapping("/delete_manager_page")
    @PreAuthorize("hasAuthority('DELETE_MANAGER')")
    public String deleteManagerPage() {
        return "delete manager page ";
    }

    @GetMapping("/block_user_delete_manager")
    @PreAuthorize("hasAnyAuthority('DELETE_MANAGER','BLOCK_USER')")
    public String manageManagerPage() {
        return "block_user_delete_manager page ";
    }


}
