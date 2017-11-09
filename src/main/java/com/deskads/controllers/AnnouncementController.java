package com.deskads.controllers;

import com.deskads.dto.AnnouncementForm;
import com.deskads.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

/**
 * Created by Administrator on 04.11.2017.
 */
@Controller
public class AnnouncementController {

    private AnnouncementService announcementService;

    @Autowired
    public void setAnnouncementService(AnnouncementService announcementService){
        this.announcementService = announcementService;
    }

    @RequestMapping("/")
    public String showAllAnnouncements(Model model){
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        return "homelist";
    }

    @RequestMapping("/announcement/show/{id}")
    public String showAnnouncement(@PathVariable String id, Model model){

        Long numericId = Long.valueOf(id);
        model.addAttribute("announcement", announcementService.getAnnouncementById(numericId));

        return "announcementdetails";
    }

    @RequestMapping("/new-announcement")
    public String openNewAnnouncementForm(Model model){
        model.addAttribute("announcementForm", new AnnouncementForm());
        return "announcementform";
    }

    @RequestMapping(value = "/new-announcement", method = RequestMethod.POST)
    public String saveNewAnnouncement(@Valid AnnouncementForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "announcementform";
        }
        Long AnnouncementId = announcementService.createNewAnnouncement(form);
        return "redirect:/announcement/show/" + String.valueOf(AnnouncementId); //or return validation
    }

    @RequestMapping("/edit-announcement/{id}")
    public String OpenEditAnnouncementForm(@PathVariable String id, Model model){
        Long numericId = Long.valueOf(id);
        AnnouncementForm oldAnnouncement = announcementService.getAnnouncementById(numericId);
        model.addAttribute("announcementForm", oldAnnouncement);
        return "announcementform";
    }

    @RequestMapping(value = "/edit-announcement/{id}", method = RequestMethod.POST)
    public String saveEditedAnnouncement(@Valid AnnouncementForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "announcementform";
        }
        Long announcementId = announcementService.editAnnouncement(form);
        return "redirect:/announcement/show/" + String.valueOf(announcementId); //or return validation
    }

    @RequestMapping(value = "/announcement/delete/{id}")
    public String deleteAnnouncement(@PathVariable String id){
        Long numericId = Long.valueOf(id);
        announcementService.deleteAnnouncement(numericId);
        return "redirect:/";
    }


}
