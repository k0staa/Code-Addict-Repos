package pl.codeaddict.spockapidocs;

import lombok.Value;

@Value
class LoginRequest {
    private String username;
    private String password;
}
