package com.deskads.services;

import com.deskads.AppException;
import com.deskads.converters.AnnouncementDtoToAnnouncement;
import com.deskads.converters.AnnouncementToAnnouncementDto;
import com.deskads.domain.Announcement;
import com.deskads.dto.AnnouncementDto;
import com.deskads.repositories.AnnouncementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementService {


    private AnnouncementDao announcementDao;
    private AnnouncementDtoToAnnouncement announcementDtoToAnnouncement;
    private AnnouncementToAnnouncementDto announcementToAnnouncementDto;


    @Autowired
    AnnouncementService(AnnouncementDao announcementDao,
                        AnnouncementDtoToAnnouncement announcementDtoToAnnouncement,
                        AnnouncementToAnnouncementDto announcementToAnnouncementDto) {
        this.announcementDao = announcementDao;
        this.announcementDtoToAnnouncement = announcementDtoToAnnouncement;
        this.announcementToAnnouncementDto = announcementToAnnouncementDto;
    }


    public AnnouncementDto getAnnouncementById(Long id) {

        Announcement resultRecord = announcementDao.getById(id); //try here
        if (resultRecord == null) {
            throw new AppException("Announcement with ID " + id + " is NOT FOUND");
        }
        AnnouncementDto announcement = announcementToAnnouncementDto.convert(resultRecord);
        return announcement;
    }


    public List<AnnouncementDto> getAllAnnouncements() {

        List<AnnouncementDto> announcements = new ArrayList<>();
        List<Announcement> resultSet = announcementDao.getAll();
        if (!resultSet.isEmpty()) {
            for (Announcement record : resultSet) {
                announcements.add(announcementToAnnouncementDto.convert(record));
            }
        }
        return announcements;
    }


    public Long createNewAnnouncement(AnnouncementDto aForm) {
        Announcement announcement = announcementDtoToAnnouncement.convert(aForm);
        announcement.setDate(new Date());
        return announcementDao.createNew(announcement);
    }


    public Long editAnnouncement(AnnouncementDto aForm) {
        Announcement announcement = announcementDtoToAnnouncement.convert(aForm);
        announcement.setDate(new Date());
        Long announcementId = announcementDao.updateAnnouncement(announcement);
        return announcementId;
    }


    public void deleteAnnouncement(Long id) {

        announcementDao.deleteById(id);
    }
}
