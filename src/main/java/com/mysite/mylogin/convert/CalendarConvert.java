package com.mysite.mylogin.convert;

import com.mysite.mylogin.dto.CalendarRequest;
import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;

public class CalendarConvert {

    public static CalendarRequest toDto(CalendarEntity entity){
        CalendarRequest dto= new CalendarRequest();

        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setTitle(entity.getTitle());
        dto.setComment(entity.getComment());
        dto.setAlramStatus(entity.getAlramStatus());
        dto.setUserID(entity.getUserID().getUserid());

        return dto;
    }

    // DTO -> Entity 변환
    public static CalendarEntity toEntity(CalendarRequest dto, UserEntity user) {
        CalendarEntity entity = new CalendarEntity();

        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setTitle(dto.getTitle());
        entity.setComment(dto.getComment());
        entity.setAlramStatus(dto.getAlramStatus());
        entity.setUserID(user);

        return entity;
    }

}
