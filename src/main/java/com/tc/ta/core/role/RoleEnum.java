package com.tc.ta.core.role;

/**
 * Created by Jack on 2017/3/4.
 */
public enum RoleEnum {
    ADMIN("管理员"), OPERATOR("操作员");

    private String roleLabel;

    private RoleEnum(String roleLabel) {
        this.roleLabel = roleLabel;
    }

    public static boolean isValid(String role) {
        try {
            RoleEnum.valueOf(role);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean isEmpl(String role) {
        return RoleEnum.valueOf(role) == OPERATOR;
    }

    public static boolean isAdmin(String role) {
        return RoleEnum.valueOf(role) == ADMIN;
    }

    public String getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel = roleLabel;
    }
}
