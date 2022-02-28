package no.noroff.accelerate.assignment6DataAccess.controllers;


import no.noroff.accelerate.assignment6DataAccess.repositories.TrackRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    private TrackRepository trackRepository;
    public SearchController(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }


    @GetMapping
    public String index(Model model, String name){
        if (name != null) {
            model.addAttribute("tracks", trackRepository.getTrackByName(name));
            return "view-track";
        }
        else {
            model.addAttribute("tracks", trackRepository.getFive());
            return "view-search";
        }
    }
}
