package mx.com.tutum.auth.domain;

import org.springframework.stereotype.Service;

import mx.com.tutum.shared.domain.AuthException;
import mx.com.tutum.shared.infrastructure.config.security.JWTTokenGenerator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthService {

    private final JWTTokenGenerator jwtTokenGenerator;

    //Esta clase podría conectar contra ldap, active directory, bbdd.... el proveedor que queramos, para la prueba dejo los usuarios en un mapa static

    private static final Map<String, String> userList;

    static {
        Map<String, String> userListMap = new HashMap<>();
        userListMap.put("tutum", "1234");
        userList = Collections.unmodifiableMap(userListMap);
    }

    public UserAuthService(JWTTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public String authUser(String username, String password) {
        if (!userList.containsKey(username) || !userList.get(username).equals(password)) {
            throw new AuthException("User or password not valid");
        }

        return jwtTokenGenerator.getJWTToken(username);
    }

}
