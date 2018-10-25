package com.example.social.debug;

import com.example.social.controller.MediaController;
import com.example.social.dto.PairDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.social.constant.ApplicationConstants.URL_ROOT;
import static com.example.social.debug.DebugConstants.ATTR_PAIRS;
import static com.example.social.debug.DebugConstants.VIEW_MAIN;

/**
 * Special controller that provide page where user can upload file and test this application.
 *
 * @author s.vareyko
 * @since 25.10.2018
 */
@Controller
@AllArgsConstructor
@RequestMapping(URL_ROOT)
public class DebugClientController {

    private final MediaController controller;

    /**
     * Method that provide page where user can upload his JSON file.
     *
     * @return view name
     */
    @GetMapping
    public String dashboard() {
        return VIEW_MAIN;
    }

    /**
     * Upload handler, expects JSON file.
     *
     * @param file  multipart JSON file
     * @param model container for attributes
     * @return view name with results
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(final MultipartFile file, final Model model) {
        final List<PairDto> pairs = controller.upload(file);
        model.addAttribute(ATTR_PAIRS, pairs);
        return dashboard();
    }
}
