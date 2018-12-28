package com.example.SpringBootDemo.constants;

public interface SignUpConst {

    String PASSWORD_REGEX = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*])(?=\\S+).*";
}
