package com.sharknados.util;

public class Point {
    int x, z;
    public Point(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int x(){
        return x;
    }

    public int z(){
        return z;
    }

    public void set(int x, int z){
        this.x = x;
        this.z = z;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setZ(int z){
        this.z = z;
    }

    public String toString() {
        return "(" + x + ", " + z + ")";
    }
}
