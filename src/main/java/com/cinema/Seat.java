package com.cinema;

/**
 * Clase que representa una butaca en el cine. Posee una fila y un lugar en la fila.
 * Se puede saber si está ocupada o no.
 */
public class Seat {

    private int row;
    private int seatNumber;
    private boolean available = true;

    /** Construye butacas, para ello se le debe pasar su ubicación, representada por
     * la fila y el número de asiento. */
    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
    }

    /** Retorna el número de la fila. */
    public int getRow() {
        return this.row;
    }

    /** Retorna el número de asiento dentro de la fila. */
    public int getSeatNumber() {
        return this.seatNumber;
    }

    /** Retorna true si el asiento está disponible, o false en caso contrario. */
    public boolean isAvailable() {
        return this.available;
    }

    /** Ocupa el asiento. */
    public void takeSeat() {
        if (!this.available) return;

        this.available = false;
    }

    /** Desocupa el asiento. */
    public void releaseSeat() {
        if (this.available) return;

        this.available = true;
    }
}

