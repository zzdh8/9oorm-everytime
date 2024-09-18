package org.goorm.everytime.member.api.dto;

public record ProfileResDto(
        String id,
        String name,
        String nickname,
        int year,
        String universityName
) {
}
