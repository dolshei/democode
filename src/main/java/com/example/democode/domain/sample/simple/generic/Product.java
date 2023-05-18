package com.example.democode.domain.sample.simple.generic;

public class Product<K, M> {
    // Fields
    private K kind;
    private M model;

    // Constructors

    // Methods
    public K getKind() {
        return this.kind;
    }

    public M getModel() {
        return this.model;
    }

    public void setKind(K kind) {
        this.kind = kind;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
