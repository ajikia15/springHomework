package ge.ibsu.demo.entities.enums;

public enum Permission {

    CUSTOMER_ADD("customer:add"), CUSTOMER_READ("customer:read");

    private String permissionKeyword;

    Permission(String permissionKeyword) {
        this.permissionKeyword = permissionKeyword;
    }

    public String getPermissionKeyword() {
        return permissionKeyword;
    }
}
