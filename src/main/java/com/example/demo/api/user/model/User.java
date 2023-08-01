package com.example.demo.api.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String signInId;

    @Column(nullable = false)
    private String signInPwd;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = true)
    private String nickName;

}
