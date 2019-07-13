package com.ny.ex19_listview;

//user_item.xml의 정보 기억하는 객체
//단축키 alt + insert
public class VO {
    private int resId;          //그림 객체 대응 상수
    private String imageName;   //그림 이름

    public VO() { }
    public VO(int resId, String imageName) {
        this.resId = resId;
        this.imageName = imageName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
