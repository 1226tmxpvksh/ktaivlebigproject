package ktaivlebigproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import ktaivlebigproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/auths")
@Transactional
public class AuthController {

    @Autowired
    AuthRepository authRepository;

    @RequestMapping(
        value = "/auths/{id}/login",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Auth login(
        @PathVariable(value = "id") Long id,
        @RequestBody LoginCommand loginCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /auth/login  called #####");
        Optional<Auth> optionalAuth = authRepository.findById(id);

        optionalAuth.orElseThrow(() -> new Exception("No Entity Found"));
        Auth auth = optionalAuth.get();
        auth.login(loginCommand);

        authRepository.save(auth);
        return auth;
    }

    @RequestMapping(
        value = "/auths/{id}/logout",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Auth logout(
        @PathVariable(value = "id") Long id,
        @RequestBody LogoutCommand logoutCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /auth/logout  called #####");
        Optional<Auth> optionalAuth = authRepository.findById(id);

        optionalAuth.orElseThrow(() -> new Exception("No Entity Found"));
        Auth auth = optionalAuth.get();
        auth.logout(logoutCommand);

        authRepository.save(auth);
        return auth;
    }

    @RequestMapping(
        value = "/auths/{id}/tokenreissue",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Auth tokenReissue(
        @PathVariable(value = "id") Long id,
        @RequestBody TokenReissueCommand tokenReissueCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /auth/tokenReissue  called #####");
        Optional<Auth> optionalAuth = authRepository.findById(id);

        optionalAuth.orElseThrow(() -> new Exception("No Entity Found"));
        Auth auth = optionalAuth.get();
        auth.tokenReissue(tokenReissueCommand);

        authRepository.save(auth);
        return auth;
    }

    @RequestMapping(
        value = "/auths/{id}/tokenverification",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Auth tokenVerification(
        @PathVariable(value = "id") Long id,
        @RequestBody TokenVerificationCommand tokenVerificationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /auth/tokenVerification  called #####");
        Optional<Auth> optionalAuth = authRepository.findById(id);

        optionalAuth.orElseThrow(() -> new Exception("No Entity Found"));
        Auth auth = optionalAuth.get();
        auth.tokenVerification(tokenVerificationCommand);

        authRepository.save(auth);
        return auth;
    }
}
//>>> Clean Arch / Inbound Adaptor
