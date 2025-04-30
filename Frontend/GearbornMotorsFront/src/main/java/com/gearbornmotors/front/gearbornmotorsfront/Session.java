package com.gearbornmotors.front.gearbornmotorsfront;

public class Session {
    private static Session instance;
    private int empleadoId;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
}
