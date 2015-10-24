package uk.co.grahamcox.worldbuilder.auth.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Controller for loading debug information about the webapp
 * @param clock The clock to use
 */
@Controller
@RequestMapping("/api/debug")
class DebugController(private val clock: Clock) {
    /**
     * Controller method for loading the current server time
     * @return the current server time
     */
    @RequestMapping("/now")
    @ResponseBody
    fun now() = ZonedDateTime.now(clock).format(DateTimeFormatter.ISO_DATE_TIME)
}