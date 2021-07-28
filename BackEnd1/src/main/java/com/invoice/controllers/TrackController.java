package com.invoice.controllers;

import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.entities.Track;
import com.invoice.services.trackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/do")
public class TrackController {
@Autowired
com.invoice.services.trackService trackService;
    @PostMapping("/track")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Track> createTrack(@Valid @RequestBody Track track,
            String af) {
track.setInvoiceAfter(af);
        return trackService.createTrack(track);
    }

    @GetMapping("/track")
    @PreAuthorize("hasRole('ADMIN') ")
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }



}
