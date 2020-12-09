package com.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Resources {
    ALL_RESOURCES("decklidstatus, doorstatusfrontleft, doorstatusfrontright, doorstatusrearleft,"
            + " doorstatusrearright, interiorLightsFront, interiorLightsRear, lightswitchposition, readingLampFrontLeft,"
            + " readingLampFrontRight, rooftopstatus, sunroofstatus, windowstatusfrontleft, windowstatusfrontright,"
            + " windowstatusrearleft, windowstatusrearright ");

    private final String resourceNames;
}
