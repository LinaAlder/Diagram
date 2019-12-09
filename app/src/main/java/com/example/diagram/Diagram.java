package com.example.diagram;

import android.content.Context;

public class Diagram {
    Context context;
    int[] sectorWeights;
    double [] percents;
    Sector[] sectors;
    int sectorIsOffset;

    public Diagram(Context context, int[] sectorWeights) {
        int sumWeights;
        double corner, sumCorner, sumPercents;

        this.context = context;
        this.sectorWeights = sectorWeights;
        this.percents = new double[sectorWeights.length];
        this.sectors = new Sector[sectorWeights.length];
        sumWeights = 0;
        sumCorner = 0;
        sumPercents = 0;
        sectorIsOffset = -1;

        for(int i = 0; i < sectorWeights.length; i++) sumWeights += sectorWeights[i];

        for(int i = 0; i < sectorWeights.length; i++) {
            if(i < sectorWeights.length - 1) {
                percents[i] = (double) sectorWeights[i] / sumWeights * 100;
                corner = 360 / 100 * percents[i];
                sectors[i] = new Sector(context, sumCorner, corner);
                sumCorner += corner;
                sumPercents += percents[i];
            } else {
                sectors[i] = new Sector(context, sumCorner, 360 - sumCorner);
                percents[i] = 100 - sumPercents;
            }
        }
    }

    public Sector[] createSectors(float x, float y, float r) {
        for(int i = 0; i < sectors.length; i++) {
            if (i == sectorIsOffset)
                sectors[i].createSector(x, y, r, true);
            else
                sectors[i].createSector(x, y, r, false);
        }
        return sectors;
    }

    public void moveSector(double corner) {
        double beginCorner, sizeCorner;
        int i;

        for(i = 0; i < sectors.length; i++) {
            beginCorner = sectors[i].getBeginCorner();
            sizeCorner = beginCorner + sectors[i].getSizeCorner();
            if(corner >= beginCorner && corner < sizeCorner) break;
        }

        if(sectorIsOffset == i) sectorIsOffset = -1;
        else sectorIsOffset = i;
    }

    public double getPercents() {
        if (sectorIsOffset > -1) return percents[sectorIsOffset];
        else return 0;
    }
}
