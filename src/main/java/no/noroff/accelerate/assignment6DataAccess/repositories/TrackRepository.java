package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Track;

import java.util.List;

//Repository for Customer handling
public interface TrackRepository {

    List<Track> getAll();
    List<Track> getFive();
    Track getTrackByName(String name);
}
