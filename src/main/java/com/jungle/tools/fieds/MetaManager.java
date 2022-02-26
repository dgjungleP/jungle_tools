package com.jungle.tools.fieds;

import java.time.temporal.ChronoUnit;

public interface MetaManager {
    Object flushApplication(String application);

    Object flushDate(String time, int offset, ChronoUnit unit);

    Object flushName(String userName);
}

