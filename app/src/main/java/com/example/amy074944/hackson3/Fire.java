package com.example.amy074944.hackson3;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.Date;

class Fire{
    private double longti;
    private double lati;
    private int fsize;
    private Date happentime;
    private int count = 1;

    Fire(double a, double b, int c, Date d){
        this.lati = a;
        this.longti = b;
        this.fsize = c;
        this.happentime = d;
    }

    public void setLongti(double a){
        this.longti = a;
    }
    public double getLongti(){
        return this.longti;
    }
    public void setLati(double a){
        this.lati = a;
    }
    public double getLati(){
        return this.lati;
    }
    public void setFsize(int a){
        this.fsize = a;
    }
    public int getFsize(){
        return this.fsize;
    }
    public void setHappentime(Date a){
        this.happentime = a;
    }
    public Date getHappentime(){
        return this.happentime;
    }
    public int getCount(){
        return this.count;
    }

    public float chooseColor(){
        switch(this.fsize){
            case 1:
                return BitmapDescriptorFactory.HUE_ORANGE;
            case 2:
                return BitmapDescriptorFactory.HUE_RED;
            default:
                return BitmapDescriptorFactory.HUE_GREEN;

        }
    }
    public void approve(){
        this.count += 1;
    }
    public void disapprove(){
        this.count -= 1;
    }

}