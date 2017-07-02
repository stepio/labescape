package co.tide.labescape.spring;

import co.tide.labescape.Area;
import co.tide.labescape.Labyrinth;
import co.tide.labescape.NoEscapeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST API for finding exit using given labyrinth and initial point.
 *
 * @author stepio
 */
@Api(description = "REST interface for Labyrinth API")
@RestController
@RequestMapping("/v1/labyrinth")
public class LabyrinthController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Find the path to escape labyrinth.
     */
    @ApiOperation(
            value = "Find the path to escape labyrinth.",
            notes = "Reads from JSON array containing strings, transforms them into 2-dimensional array of chars, finds the path to escape.")
    @RequestMapping(path = "/escape",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> escape(@RequestParam("startX") int startX,
                                    @RequestParam("startY") int startY,
                                    @RequestBody List<String> labyrinth) throws NoEscapeException {
        Labyrinth result = valueOf(labyrinth);
        result.validate();
        Area initial = result.getArea(startX, startY);
        initial.validate();
        result.drawPathFrom(initial);
        return ResponseEntity.status(HttpStatus.OK).body(toList(result));
    }

    Labyrinth valueOf(final List<String> list) {
        if (list == null || list.isEmpty()) {
            return Labyrinth.valueOf(null);
        } else {
            char[][] array = new char[list.size()][];
            for (int i = 0; i < list.size(); ++i) {
                array[i] = list.get(i).toCharArray();
            }
            return Labyrinth.valueOf(array);
        }
    }

    List<String> toList(final Labyrinth labyrinth) {
        return Arrays.asList(labyrinth.toArray())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Maps {@link NoEscapeException} to {@link HttpStatus#BAD_REQUEST} HTTP status code.
     *
     * @param ex thrown {@link NoEscapeException}
     * @return constructed {@link ResponseEntity}
     */
    @ExceptionHandler
    public ResponseEntity<?> handleException(NoEscapeException ex) {
        log.error("Request processing failed", ex);
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));
    }
}
