package kr.co.polycube.backendtest.api.user;

import kr.co.polycube.backendtest.domain.user.UserEntity;
import kr.co.polycube.backendtest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserApi {
    private final UserService userService;

    ///users API를 호출하면, {"id": ?}을 응답한다.
    @PostMapping("/users")
    public Map<String, Long> registerUser(@RequestBody Map<String, Object> userInfo){
//        System.out.println("userInfo = " + userInfo);
//        System.out.println("userInfo = " + userInfo.get("name"));
        UserEntity user = userService.registerUser(userInfo);
        return Map.of("id", user.getId());
    }

    ///users/{id} API를 호출하면, {"id": ?, "name": "?"}을 응답한다.
    @GetMapping("/users/{id}")
    public UserEntity getUser(@PathVariable("id") Long id) {
//        System.out.println("id = " + id);
        return userService.findUser(id);
    }

    ///users/{id} API를 호출하면, {"id": ?, "name": "?"}을 응답한다.
    @PatchMapping("/users/{id}")
    public UserEntity updateUser(@PathVariable("id") Long id, @RequestBody Map<String, Object> userInfo) {
//        System.out.println("id = " + id + ", userInfo = " + userInfo);
        return userService.modifyUser(id, userInfo);
    }
}
