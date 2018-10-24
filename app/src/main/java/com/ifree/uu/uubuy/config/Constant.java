package com.ifree.uu.uubuy.config;

/**
 * Created by 小火
 * Create time on  2017/10/25
 * My mailbox is 1403241630@qq.com
 */

public class Constant {
    /**联网类型*/
    public static final class NetState {
        public static final int WIFI = 10030;
        public static final int Mobile = 10031;
        public static final int NOWAY = 10032;
    }
    public static final String FIRST_COME = "first_come";
    public static int badgeCount = 0;

    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;

}
