package kr.co.polycube.backendtest.service.user;

import kr.co.polycube.backendtest.aspect.LoggingPointCut;
import kr.co.polycube.backendtest.domain.user.UserEntity;
import kr.co.polycube.backendtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //user 등록 API 구현 (8점)
    @LoggingPointCut
    public UserEntity registerUser(Map<String, Object> userInfo){
        UserEntity user = UserEntity.builder()
                .name((String) userInfo.get("name"))
                .build();

        return userRepository.save(user);
    }

    //user 조회 API 구현 (8점)
    @LoggingPointCut
    public UserEntity findUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found key"));
    }

    //user 수정 API 구현 (8점)
    @LoggingPointCut
    public UserEntity modifyUser(Long id, Map<String, Object> userInfo){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found key"));

        UserEntity updatedUser = UserEntity.builder()
                .id(user.getId())  // 기존 ID를 유지
                .name(userInfo.containsKey("name") ? (String) userInfo.get("name") : user.getName())  // 이름 업데이트
                .build();

        return userRepository.save(updatedUser);
    }
}
