package com.deskads.converters;

import com.deskads.domain.Announcement;
import com.deskads.dto.AnnouncementForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 05.11.2017.
 */
@Component
public class AnnouncementToAnnouncementForm implements Converter<Announcement, AnnouncementForm> {

    @Override
    public AnnouncementForm convert(Announcement announcement){
        AnnouncementForm aForm = new AnnouncementForm();
        aForm.setId(String.valueOf(announcement.getId()));
        aForm.setTitle(announcement.getTitle());
        aForm.setText(announcement.getText());
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        aForm.setDate(formatter.format(announcement.getDate()));
        return aForm;
    }
}
