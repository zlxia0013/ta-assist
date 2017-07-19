package com.tc.ta.core.user;

import com.tc.ta.util.exception.ComRuntimeException;

/**
 * Created by Jack on 2017/3/2.
 */
public enum UserStateEnum {
    ENABLED(1), DISABLED(0);

    private int stateId;

    UserStateEnum(int stateId) {
        this.stateId = stateId;
    }

    public static UserStateEnum valueOf(int stateId) {
        switch (stateId) {
            case 1:
                return ENABLED;
            case 0:
                return DISABLED;
            default:
                throw new ComRuntimeException("无效的用户状态: " + stateId);
        }
    }

    public static boolean enabled(int stateId) {
        UserStateEnum state = valueOf(stateId);
        return state == ENABLED;
    }

    public static boolean isValid(int stateId) {
        try {
            valueOf(stateId);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static String getLabel(int stateId){
        switch (stateId){
            case 0:
                return "禁用";
            case 1:
                return "活动";
        }
        return "";
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}
