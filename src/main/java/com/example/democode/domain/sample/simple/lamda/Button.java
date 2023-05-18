package com.example.democode.domain.sample.simple.lamda;

public class Button {
    // 정적 멤버 인터페이스
    @FunctionalInterface
    public static interface ClickListener {
        // 추상 메소드
        void onClick();
    }

    // Fields
    private ClickListener clickListener;

    // Constructors

    // Methods
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void click() {
        this.clickListener.onClick();
    }
}
