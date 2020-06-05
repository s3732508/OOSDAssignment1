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

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Point point = (Point) obj;
        return (point.x == this.x && point.z == this.z);
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
