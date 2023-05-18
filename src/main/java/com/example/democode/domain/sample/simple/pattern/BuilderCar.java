package com.example.democode.domain.sample.simple.pattern;

public class BuilderCar {
    // Fields
    // final 키워드를 써서 생성자를 통한 입력을 강요함.
    private final String id;
    private final String name;

    // Class 안에 static 형태의 내부 클래스(inner class) 생성
    protected static class Builder {
        private String id;
        private String name;

        public Builder id(String value) {
            id = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public BuilderCar build() {
            return new BuilderCar(this);
        }
    }


    // Constructor
    // 생성자를 private 로 함.
    // 외부에서는 접근할 수 없고, 위의 Builder 클래스에서는 사용 가능
    private BuilderCar(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    // Method
    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
