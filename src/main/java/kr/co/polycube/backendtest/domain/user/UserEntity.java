package kr.co.polycube.backendtest.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "tbl_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    /* h2 database user 테이블 생성 쿼리
    drop table tbl_user;
    CREATE TABLE tbl_user (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(255),
    PRIMARY KEY (id)
    );
    * */
}

