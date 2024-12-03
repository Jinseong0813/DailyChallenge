package com.mysite.mylogin.convert;

import com.mysite.mylogin.dto.CalendarRequest;
import com.mysite.mylogin.dto.CalendarResponse;
import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;

public class CalendarConvert {



    // ReuquestDTO -> Entity 변환
    public static CalendarEntity toEntity(CalendarRequest dto, UserEntity user) {
        if (dto == null){
            return null;
        }


        CalendarEntity entity = new CalendarEntity();

        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTitle(dto.getTitle());
        entity.setComment(dto.getComment());
        entity.setAlramStatus(dto.getAlramStatus());
        entity.setUserid(user);

        return entity;
    }

    //Entity -> ResponseDto로 전달
    public static CalendarResponse toDto(CalendarEntity entity){
        return CalendarResponse.builder()
                .calendarId(entity.getCalendarId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .title(entity.getTitle())
                .comment(entity.getComment())
                .userId(entity.getUserid().getUserid())
                .alramStatus(entity.getAlramStatus())
                .build();
    }
}
