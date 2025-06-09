package com.enableedge.automation.utils;

import java.time.Duration;

public class Timeouts {
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(10);
    public static final Duration ELEMENT_WAIT = Duration.ofSeconds(5);
    public static final Duration SHORT_WAIT = Duration.ofSeconds(2);
    public static final Duration LONG_WAIT = Duration.ofSeconds(30);
}
