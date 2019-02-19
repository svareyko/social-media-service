package com.example.social.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.net.URI;

import static com.example.social.constant.ApplicationConstants.URL_UPLOAD;
import static com.example.social.controller.AdviceController.JSON_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author s.vareyko
 * @since 25.10.2018
 */
public class MediaControllerTest extends AbstractControllerTest {

    @Autowired
    private MediaController mediaController;

    @Test
    public void uploadWrongJsonFileTest() throws Exception {

        final byte[] wrongJsonContents = "{\"list\": [{\"name\":\"Jane\",,}]}".getBytes();
        final MockMultipartFile file = new MockMultipartFile("file", wrongJsonContents);
        final MockMultipartHttpServletRequestBuilder request = multipart(new URI(URL_UPLOAD));
        request.file(file);

        final MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getContentAsString()).startsWith(JSON_EXCEPTION_MESSAGE);
    }

    @Test
    public void uploadOnePairTest() throws Exception {
        final int numOfRecords = 1;
        final int numCommonInterests = 1;
        final byte[] jsonFileContents = ("{\"list\": ["
                + "{\"name\":\"Jane\",\"interests\":[\"int1\",\"int2\"]},"
                + "{\"name\":\"John\",\"interests\":[\"int2\",\"int3\"]}"
                + "]}").getBytes();
        final MockMultipartFile file = new MockMultipartFile("file", jsonFileContents);
        final MockMultipartHttpServletRequestBuilder request = multipart(new URI(URL_UPLOAD));
        request.file(file);
        final MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();


        final JSONArray jsonArray = new JSONArray(result.getResponse().getContentAsString());
        assertThat(jsonArray.length()).isEqualTo(numOfRecords);
        final JSONObject pair = (JSONObject) jsonArray.get(0);
        assertThat(pair.getInt("interestsNum")).isEqualTo(numCommonInterests);
    }

    @Override
    Object getController() {
        return mediaController;
    }
}
