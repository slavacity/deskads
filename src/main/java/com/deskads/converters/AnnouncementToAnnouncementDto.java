package com.deskads.converters;

import com.deskads.domain.Announcement;
import com.deskads.dto.AnnouncementDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class AnnouncementToAnnouncementDto implements Converter<Announcement, AnnouncementDto> {

    @Override
    public AnnouncementDto convert(Announcement announcement) {
        AnnouncementDto aForm = new AnnouncementDto();
        aForm.setId(String.valueOf(announcement.getId()));
        aForm.setTitle(announcement.getTitle());
        aForm.setText(announcement.getText());
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        aForm.setDate(formatter.format(announcement.getDate()));
        return aForm;
    }
}
