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
// @RequestMapping(value="/users")
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(
        value = "/users/{id}/userregister",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User userRegister(
        @PathVariable(value = "id") Long id,
        @RequestBody UserRegisterCommand userRegisterCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/userRegister  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.userRegister(userRegisterCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/userdelete",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User userDelete(
        @PathVariable(value = "id") Long id,
        @RequestBody UserDeleteCommand userDeleteCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/userDelete  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.userDelete(userDeleteCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/userprofileupdate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User userProfileUpdate(
        @PathVariable(value = "id") Long id,
        @RequestBody UserProfileUpdateCommand userProfileUpdateCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/userProfileUpdate  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.userProfileUpdate(userProfileUpdateCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/getuserinfo",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User getUserInfo(
        @PathVariable(value = "id") Long id,
        @RequestBody GetUserInfoCommand getUserInfoCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/getUserInfo  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.getUserInfo(getUserInfoCommand);

        userRepository.save(user);
        return user;
    }
}
//>>> Clean Arch / Inbound Adaptor
