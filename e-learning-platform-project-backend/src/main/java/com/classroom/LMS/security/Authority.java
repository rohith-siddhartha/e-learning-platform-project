package com.classroom.LMS.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
