package com.factory;


import com.implement.Identity;

public class IdentityFactory {

    /**
     * 确定用户身份选择执行对应的方法
     * @param identity 用户的选择：1或者2
     * @return 执行对应的登录方法
     */
    public static Identity sureIdentity(char identity){
        if (identity == '1'){
            return new LoginByAdmin();
        } else if (identity == '2'){
            return new LoginByOperator();
        } else {
            return null;
        }
    }
}
