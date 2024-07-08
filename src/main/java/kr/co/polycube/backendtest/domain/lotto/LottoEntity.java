package kr.co.polycube.backendtest.domain.lotto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tbl_lotto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LottoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private int number_1;

    @Column(nullable = false)
    private int number_2;

    @Column(nullable = false)
    private int number_3;

    @Column(nullable = false)
    private int number_4;

    @Column(nullable = false)
    private int number_5;

    @Column(nullable = false)
    private int number_6;

/*
    drop table tbl_lotto;
    CREATE TABLE tbl_lotto (
            id BIGINT GENERATED BY DEFAULT AS IDENTITY,
            number_1 INT,
            number_2 INT,
            number_3 INT,
            number_4 INT,
            number_5 INT,
            number_6 INT,
            PRIMARY KEY (id)
    );

 */
}
