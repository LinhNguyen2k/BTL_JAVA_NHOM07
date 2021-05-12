package com.AnhLinh;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sensor {
    // so thu tu cua sensor
    int index;
    // vi tri cua sensor
    Point coordinate;
    // ds cac sensor gan no
    List<Sensor> nearSensors;
    // trang thai duyet cua sensor
    boolean isVisited;

    // khoi tao 1 sensor
    public Sensor() {
        index = -1;
        coordinate = new Point();
        nearSensors = new ArrayList<>();
        isVisited = false;
    }

    // 2 sensor bang nhau khi toa do cua chung bang nhau
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(coordinate, sensor.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, coordinate, nearSensors, isVisited);
    }
}
