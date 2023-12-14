package edu.project4.Records;

public class Pixel {
    int r;
    int g;
    int b;
    int hitCount;
    double normal;

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    synchronized public void setR(int r) {
        this.r = r;
    }

    synchronized public void setB(int b) {
        this.b = b;
    }

    synchronized public void setG(int g) {
        this.g = g;
    }

    synchronized public int getHitCount() {
        return hitCount;
    }

    synchronized public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    synchronized public int getB() {
        return b;
    }

    synchronized public int getG() {
        return g;
    }

    synchronized public int getR() {
        return r;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
