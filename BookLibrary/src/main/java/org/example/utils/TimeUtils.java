package org.example.utils;

import java.time.LocalDateTime;

public class TimeUtils {
    public TimeUtils() {}

    public LocalDateTime generateExpiry() {
        return LocalDateTime.now().plusMinutes(24 * 60);
    }
}