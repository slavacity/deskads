package com.deskads.repositories;

import com.deskads.domain.Announcement;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 02.11.2017.
 */
@Repository
@Transactional
public class AnnouncementDao {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION) // .EXTENDED for App managed transactions
    private EntityManager em;                                     // .TRANSACTION (default) for Container managed transactions

    public Announcement getById(long id){

        Announcement announcement;
        announcement = em.find(Announcement.class, id);
        return announcement;
    }

    public List<Announcement> getAll(){
        TypedQuery<Announcement> query = em.createQuery("SELECT a FROM Announcement a ORDER BY date DESC", Announcement.class);
        List<Announcement> announcements = query.getResultList();

        for(Announcement record : announcements){
            System.out.println(record.getId()+" "+record.getTitle()+" "+record.getText()+" "+record.getDate());
        }
        return announcements;
    }


    public Long createNew(Announcement announcement){

        em.persist(announcement);
        em.flush();
        return announcement.getId();
    }

    public Long updateAnnouncement(Announcement announcement){

        Announcement updatedAnnouncement = em.merge(announcement);
        return updatedAnnouncement.getId();
    }

    public void deleteById(long id){
        Announcement announcement = em.find(Announcement.class, id);
        em.remove(announcement);
    }

}
