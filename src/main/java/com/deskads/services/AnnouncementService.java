package com.deskads.services;

import com.deskads.DeskadsException;
import com.deskads.converters.AnnouncementFormToAnnouncement;
import com.deskads.converters.AnnouncementToAnnouncementForm;
import com.deskads.domain.Announcement;
import com.deskads.dto.AnnouncementForm;
import com.deskads.repositories.AnnouncementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 05.11.2017.
 */
@Service
public class AnnouncementService {


    private AnnouncementDao announcementDao;
    private AnnouncementFormToAnnouncement announcementFormToAnnouncement;
    private AnnouncementToAnnouncementForm announcementToAnnouncementForm;


    @Autowired
    AnnouncementService(AnnouncementDao announcementDao,
                        AnnouncementFormToAnnouncement announcementFormToAnnouncement,
                        AnnouncementToAnnouncementForm announcementToAnnouncementForm){
        this.announcementDao = announcementDao;
        this.announcementFormToAnnouncement = announcementFormToAnnouncement;
        this.announcementToAnnouncementForm = announcementToAnnouncementForm;
    }


    public AnnouncementForm getAnnouncementById(Long id) {

        Announcement resultRecord = announcementDao.getById(id); //try here
        if(resultRecord == null){
            throw new DeskadsException("Announcement with ID " + id + " is NOT FOUND");
        }
        AnnouncementForm announcement = announcementToAnnouncementForm.convert(resultRecord);
        return announcement;
    }


    public List<AnnouncementForm> getAllAnnouncements() {

        List<AnnouncementForm> announcements = new ArrayList<>();
        List<Announcement> resultSet = announcementDao.getAll();
        if(!resultSet.isEmpty()){
            for(Announcement record : resultSet){
                announcements.add(announcementToAnnouncementForm.convert(record));
            }
        }
        return announcements;
    }


    public Long createNewAnnouncement(AnnouncementForm aForm) {
        Announcement announcement = announcementFormToAnnouncement.convert(aForm);
        announcement.setDate(new Date());
        return announcementDao.createNew(announcement);
    }


    public Long editAnnouncement(AnnouncementForm aForm) {
        Announcement announcement = announcementFormToAnnouncement.convert(aForm);
        announcement.setDate(new Date());
        Long announcementId = announcementDao.updateAnnouncement(announcement);
        return  announcementId;
    }


    public void deleteAnnouncement(Long id) {

        announcementDao.deleteById(id);
    }
}
