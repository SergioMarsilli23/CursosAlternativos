package mx.com.tutum.auth.infrastructure.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.auth.domain.UserAuthService;
import mx.com.tutum.auth.infrastructure.rest.dto.AuthResponseDto;
import mx.com.tutum.auth.infrastructure.rest.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping(AuthController.BASE_MAPPING)
@Api
@Validated
@Slf4j
public class AuthController {

    static final String BASE_MAPPING = "/auth";

    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @ApiOperation(value = "Auth user.", notes = "Get a token if user if correct.")
    @GetMapping
    public ResponseEntity<String> getAuthTokenByGet(@ApiParam(value = "user", example = "tutum", required = true) @RequestParam(value = "user") @NotEmpty final String user,
                                               @ApiParam(value = "password", example = "1234", required = true, format = "password") @RequestParam(value = "password") @NotEmpty final String password) {

        String token = userAuthService.authUser(user, password);
        
        log.info("User " + user + " authenticated!");

        return ResponseEntity.ok(token);
    }
    
    @ApiOperation(value = "Auth user.", notes = "Get a token if user if correct.")
    @PostMapping
    public ResponseEntity<AuthResponseDto> getAuthTokenByPost(@RequestBody final LoginRequestDto loginRequestDto) {

        String token = userAuthService.authUser(loginRequestDto.getUser(), loginRequestDto.getPassword());
        AuthResponseDto authResponse = AuthResponseDto.create(token);
        
        log.info("User " + loginRequestDto.getUser() + " authenticated!");

        return ResponseEntity.ok(authResponse);
    }
}
