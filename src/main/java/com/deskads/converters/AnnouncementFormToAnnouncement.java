package com.deskads.converters;

import com.deskads.domain.Announcement;
import com.deskads.dto.AnnouncementForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 05.11.2017.
 */
@Component
public class AnnouncementFormToAnnouncement implements Converter<AnnouncementForm, Announcement> {

    @Override
    public Announcement convert(AnnouncementForm aForm){
        Announcement announcement = new Announcement();
        if(aForm.getId() != null && !StringUtils.isEmpty(aForm.getId())){
            announcement.setId(Long.valueOf(aForm.getId()));
        }
        if(aForm.getDate() != null && !StringUtils.isEmpty(aForm.getDate())) {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            try {
                announcement.setDate(formatter.parse(aForm.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        announcement.setTitle(aForm.getTitle());
        announcement.setText(aForm.getText());

        return announcement;
    }


}
