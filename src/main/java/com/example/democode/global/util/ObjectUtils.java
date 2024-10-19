package com.example.democode.global.util;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    // 파라미터가 null 인지 또는 비어있는지 확인하는 유틸리티 메소드
    // null or 비어 있다면 true 반환
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }

        return false;
    }

    // 파라미터가 모두 null 또는 비어있는지 확인하는 메소드
    public static boolean areAllNullOrEmpty(Object... objs) {
        for (Object obj : objs) {
            if (!isNullOrEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    // 파라미터가 하나라도 null 또는 비어있는지 확인하는 메소드
    public static boolean isAnyNullOrEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isNullOrEmpty(obj)) {
                return true;
            }
        }
        return false;
    }
}
