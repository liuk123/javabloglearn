package com.lk.fishblog.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
        return new BCryptPasswordEncoder().encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.print(charSequence);
        System.out.print(encode(charSequence));
        return encode(charSequence).equals(s);
    }
}
