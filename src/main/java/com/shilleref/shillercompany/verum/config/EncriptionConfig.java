package com.shilleref.shillercompany.verum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncriptionConfig {
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);//Цифра характеризует надёжность ключа шифрования
    }
}
