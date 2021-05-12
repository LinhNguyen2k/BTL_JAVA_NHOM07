package com.AnhLinh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    // so luong sensor
    int n;
    // danh sach cac sensor
    List<Sensor> sensorList;

    // phuong thuc khoi tao
    public Program(int n) {
        this.n = n;
        this.sensorList = new ArrayList<>();
        // random cac sensor va add vao DS.
        Random random = new Random();
        // so thu tu cua sensor bat dau tu 1
        int pos = 1;
        while (pos <= n) {
            Sensor sensor = new Sensor();
            int x = random.nextInt(101);
            int y = random.nextInt(101);
            sensor.index = pos;
            sensor.coordinate.x = x;
            sensor.coordinate.y = y;
            // toa do cac sensor phai khac nhau va khac sensor trung tam
            if (!sensorList.contains(sensor) && x != 50 && y != 50) {
                sensorList.add(sensor);
                pos++;
            }
        }
        // tim DS cac sensor co the giao tiep cua tung sensor
        for (int i = 0; i < n; i++) {
            sensorList.get(i).nearSensors = findNearSensor(sensorList.get(i));
        }
    }

    // tim cac sensor gan sensor source
    public List<Sensor> findNearSensor(Sensor source) {
        List<Sensor> nearSensorList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Sensor sensor = sensorList.get(i);
            double length = Math.sqrt(Math.pow(sensor.coordinate.getX() -
                    source.coordinate.getX(), 2) +
                    Math.pow(sensor.coordinate.getY() -
                            source.coordinate.getY(), 2));
            if (!sensor.equals(source) && length <= 10) {
                nearSensorList.add(sensor);
            }
        }
        return nearSensorList;
    }

    public void printAllPaths(Sensor source, Sensor destination) {
        List<Sensor> pathList = new ArrayList<>();
        pathList.add(source);
        printAllPathsUtil(source, destination, pathList);
    }

    private void printAllPathsUtil(Sensor source, Sensor destination, List<Sensor> localPathList) {
        if (source.equals(destination)) {
            for (Sensor s : localPathList) {
                System.out.print(s.index + " ");
            }
            System.out.println();
            return;
        }
        source.isVisited = true;
        List<Sensor> nearSensorList = findNearSensor(source);
        if (!nearSensorList.isEmpty()) {
            for (Sensor sensor : nearSensorList) {
                if (!sensor.isVisited) {
                    localPathList.add(sensor);
                    printAllPathsUtil(sensor, destination, localPathList);
                    localPathList.remove(sensor);
                }
            }
        }
        source.isVisited = false;
    }

    public static void main(String[] args) {
        // 50 sensor
        int n = 100;
        Program program = new Program(n);
        // khoi tao node trung tam co toa do (50, 50)
        Sensor sinkSensor = new Sensor();
        sinkSensor.coordinate.setLocation(50, 50);

        //program.printAllPaths(sinkSensor, program.sensorList.get(49));

        // in ra tat ca cac path
        /*System.out.println("=====================");
        for (int i = 0; i < n; i++) {
            program.printAllPaths(sinkSensor, program.sensorList.get(i));
        }*/

        // in ra cac sensor da random
        System.out.println("Cac sensor da random");
        program.sensorList.forEach(p -> System.out.println("  +++Index: " + p.index +
                "\t(X,Y) = (" + p.coordinate.getX() +
                ", " + p.coordinate.getY() + ")"));

        System.out.println("*********************");

        // tim cac sensor gan sensor trung tam
        List<Sensor> nearSinkSensorList = program.findNearSensor(sinkSensor);

        // in ra cac sensor gan sensor trung tam
        System.out.println("Cac sensor gan sensor trung tam la");
        nearSinkSensorList.forEach(p -> System.out.println("  +++Index: " + p.index +
                "\t(X,Y) = (" + p.coordinate.getX() +
                ", " + p.coordinate.getY() + ")"));

        System.out.println("======================================");

        System.out.println("+++++++++++++++++++++++++++++++++");
        //in ra cac sensor va DS cac sensor gan no
        program.sensorList.forEach(s -> {
            System.out.println("Index: " + s.index +
                    "\t(X,Y) = (" + s.coordinate.getX() +
                    ", " + s.coordinate.getY() + ")");
            if (!s.nearSensors.isEmpty()) {
                s.nearSensors.forEach(p -> System.out.println("  +++Index: " + p.index +
                        "\t(X,Y) = (" + p.coordinate.getX() +
                        ", " + p.coordinate.getY() + ")"));
            }
        });
    }
}
