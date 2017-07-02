package co.tide.labescape.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link LabyrinthController}.
 *
 * @author istepanov
 */
public class LabyrinthControllerITest extends ApplicationTestBase {

    protected static final ParameterizedTypeReference<List<String>> LIST_STRING = new ParameterizedTypeReference<List<String>>() {};
    protected static final ParameterizedTypeReference<Map<String, String>> MAP_STRING = new ParameterizedTypeReference<Map<String, String>>() {};

    @Value("${controller.url:/v1/labyrinth/escape}")
    protected String controllerUrl;

    @Autowired
    protected LabyrinthController controller;
    protected List<String> responseList;

    @Override
    public void setUp() {
        super.setUp();
        responseList = Arrays.asList(
                "OOOOOOOOOO",
                "O••••O•••O",
                "O•OO•O•O•O",
                "O• O•O•O•O",
                "O OO•••O••",
                "O OOOOOOOO",
                "O        O",
                "OOOOOOOOOO");
    }

    @Test
    public void findEscape_withPredefinedData() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(controllerUrl).queryParam("startX", 3).queryParam("startY", 1);
        ResponseEntity<List<String>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(list), LIST_STRING);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseList);
    }

    @Test
    public void handleIllegalArgumentException_withEmptyList() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(controllerUrl).queryParam("startX", 3).queryParam("startY", 0);
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(list), MAP_STRING);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsValue("Specified starting point at [3,0] is a wall");
    }
}
