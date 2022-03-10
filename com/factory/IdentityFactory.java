package com.factory;


import com.implement.Identity;

public class IdentityFactory {

    public static Identity sureIdentity(String identity){
        if (identity.equals("管理员")){
            return new LoginByAdmin();
        } else if (identity.equals("操作员")){
            return new LoginByOperator();
        } else {
            return null;
        }
    }
}
