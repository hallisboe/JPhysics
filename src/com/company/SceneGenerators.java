package com.company;

import java.util.ArrayList;

public class SceneGenerators {
    public static Scene cloth(Scene scene) {

        Point[][] points = new Point[10][10]; //sd

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                points[i][j] = new Point(new Vector(4d - j, 9d - i, 0), 1.0, i == 0 && (j == 0 || j == 9));
                scene.addObject(points[i][j]);
            }
        }

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i - 1][j], 0.05));
                }
                if (j - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i][j - 1], 0.05));
                }
                if (i + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i + 1][j], 0.05));
                }
                if (j + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i][j + 1], 0.05));
                }
            }
        }

        Plane f = new Plane();
        scene.addObject(f);

        for (Object o : scene.objects) {
            o.addForce(new Vector(0.01, 0, -0.0));
        }
        System.out.println(scene);

        return scene;

    }

    public static Scene sphere(Scene scene){
        Vector position = new Vector(0,0,0);
        double radius = 1;
        int resolution = 13;
        int pointCount;
        Point[][] points = new Point[resolution+1][resolution+1];
        double springK = 0.01; //Spring strength

        for(int lat = 0; lat <= resolution; lat++){
            double lat_angle = map(lat,0,resolution,0,Math.PI);
            for(int lon = 0; lon <= resolution; lon++){
                double lon_angle = map(lon,0,resolution,-Math.PI,Math.PI);
                double x = position.x + radius*Math.sin(lon_angle)*Math.cos(lat_angle);
                double y = position.y + radius*Math.sin(lon_angle)*Math.sin(lat_angle);
                double z = position.z + radius*Math.cos(lon_angle);
                Vector pos = new Vector(x,y,z);
                Point point = new Point(pos,1,false);
                points[lat][lon] = point;
            }
        }

        for(int i = 0; i < resolution; i++){
            for(int k = 0; k < resolution; k++){
                Point a = points[i][k];
                Point b = points[i][k + 1];
                Point c = points[i+1][k];
                Spring s1 = new Spring(a,b,springK);
                Spring s2 = new Spring(a,c,springK);
                scene.addObject(a);
                scene.addObject(s1);
                scene.addObject(s2);
            }
        }
        return scene;
    }
    //Helper function
    private static double map(double value,double from1, double to1, double from2, double to2){
        return (value - from1)/(to1 - from1)*(to2-from2)+from2;
    }
}
