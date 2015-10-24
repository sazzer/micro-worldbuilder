package uk.co.grahamcox.worldbuilder.auth.webapp

import java.time.*

/** The default time to use */
val DEFAULT_TIME = ZonedDateTime.of(2015, 10, 24, 22, 38, 0, 0, ZoneOffset.UTC).toInstant()

/**
 * Implementation of a clock that has a fixed time, but one which can be changed at runtime
 */
class MutableClock(initialInstant: Instant = DEFAULT_TIME,
                   private val initialZone: ZoneId = ZoneOffset.UTC) : Clock() {
    /** What we currently consider to be the time */
    private var instant: Instant = initialInstant

    /**
     * Set the time
     * @param instant the time to use
     */
    fun setInstant(instant: Instant) {
        this.instant = instant
    }

    /**
     * Get the Timezone
     * @return the timezone
     */
    override fun getZone(): ZoneId = initialZone

    /**
     * Construct a new clock with a different timezone
     * @param zone The new timezone
     */
    override fun withZone(zone: ZoneId): Clock {
        if (zone == this.zone) {
            // intentional NPE
            return this
        }
        return MutableClock(instant, zone)
    }

    /**
     * Get the current time in millis
     * @return the current time in
     */
    override fun millis(): Long {
        return instant.toEpochMilli()
    }

    /**
     * Get the current instant
     * @return the current instant
     */
    override fun instant(): Instant {
        return instant
    }

    /**
     * Compare this clock to another one
     * @param obj The object to compare to
     * @return true if the two clocks are the same. False if not
     */
    override fun equals(obj: Any?): Boolean {
        if (obj is MutableClock) {
            return instant == obj.instant && zone == obj.zone
        }
        return false
    }

    /**
     * Generate a hashcode for the clock
     * @return the hashcode
     */
    override fun hashCode(): Int {
        return instant.hashCode() xor zone.hashCode()
    }

    /**
     * Generate a string for the clock
     * @return the string
     */
    override fun toString(): String {
        return "MutableClock[$instant,$zone]"
    }
}