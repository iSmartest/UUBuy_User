package com.ifree.uu.uubuy.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class CityInfoEntity {
    private String result;
    private String resultCode;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean{
        private List<HotCity> hotCity;
        private List<ProvinceList> provinceList;

        public List<HotCity> getHotCity() {
            return hotCity;
        }

        public List<ProvinceList> getProvinceList() {
            return provinceList;
        }

        public static class HotCity{
            private String city;
            private String cityAdCode;
            private List<TownList> townList;
            public static class TownList implements Serializable{
                private String townAdCode;
                private String town;
                private String latitude;
                private String longitude;

                public String getTownAdCode() {
                    return townAdCode;
                }

                public String getTown() {
                    return town;
                }

                public String getLatitude() {
                    return latitude;
                }

                public String getLongitude() {
                    return longitude;
                }
            }

            public List<TownList> getTownList() {
                return townList;
            }

            public String getCity() {
                return city;
            }

            public String getCityAdCode() {
                return cityAdCode;
            }


        }
        public static class ProvinceList implements Serializable {
            private String province;
            private List<CityList> cityList;

            public String getProvince() {
                return province;
            }

            public List<CityList> getCityList() {
                return cityList;
            }

            public static class CityList implements Serializable{
                private String city;
                private List<TownList> townList;

                public String getCity() {
                    return city;
                }

                public List<TownList> getTownList() {
                    return townList;
                }

                public static class TownList implements Serializable{
                    private String townAdCode;
                    private String town;
                    private String longitude;
                    private String latitude;

                    public String getTownAdCode() {
                        return townAdCode;
                    }

                    public String getTown() {
                        return town;
                    }

                    public String getLongitude() {
                        return longitude;
                    }

                    public String getLatitude() {
                        return latitude;
                    }
                }
            }
        }
    }
}
