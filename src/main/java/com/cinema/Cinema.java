package com.cinema;

import java.util.ArrayList;

/**
 * Clase que representa una sala de cine.
 */
public class Cinema {

    private Seat[][] seats;

    /**
     * Construye una sala de cine. Se le pasa como dato un arreglo cuyo tamaño
     * es la cantidad de filas y los enteros que tiene son el número de butacas en cada fila.
     */
    public Cinema(int[] rows) {
        seats = new Seat[rows.length][];
        initSeats(rows);
    }

    /**
     * Inicializa las butacas de la sala de cine.
     *
     * @param rows arreglo que contiene la cantidad de butacas en cada fila
     */
    private void initSeats(int[] rows) {
        for (int i = 0; i < rows.length; i++) {
            seats[i] = new Seat[rows[i]];
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    /**
     * Cuenta la cantidad de seats disponibles en el cine.
     */
    public int countAvailableSeats() {
        int count = 0;
        for (Seat[] row : this.seats){
            for (Seat asiento : row)
                count += asiento.isAvailable() ? 1 : 0;
        }
        return count;
    }

    /**
     * Busca la primera butaca libre dentro de una fila o null si no encuentra.
     */
    public Seat findFirstAvailableSeatInRow(int row) {
        if (row > seats.length) return null;

        Seat first = new Seat(-1,-1);
        for (int x = 0; x < seats[row].length; x++) {
            if (!seats[row][x].isAvailable()) continue;
            first = new Seat(seats[row][x].getRow(), seats[row][x].getSeatNumber());
            break;
        }

        if (first.getRow() < 0) return null;

        return first;
    }

    /**
     * Busca la primera butaca libre o null si no encuentra.
     */
    public Seat findFirstAvailableSeat() {
        Seat first = new Seat(-1,-1);
        for (int row = 0; row < seats.length; row++) {
            first = findFirstAvailableSeatInRow(row);
            if (first != null) return first;
        }
        return null;
    }

    /**
     * Busca las N butacas libres consecutivas en una fila. Si no hay, retorna null.
     *
     * @param row    fila en la que buscará las butacas.
     * @param amount el número de butacas necesarias (N).
     * @return La primer butaca de la serie de N butacas, si no hay retorna null.
     */
    public Seat getAvailableSeatsInRow(int row, int amount) {
        int count = 0;
        for (int x = 0; x < seats[row].length; x++) {
            if (amount == count) return seats[row][x - count ];
            if (!seats[row][x].isAvailable()) {
                count = 0;
                continue;
            }
            count += 1;
        }
        return null;
    }

    /**
     * Busca en toda la sala N butacas libres consecutivas. Si las encuentra
     * retorna la primer butaca de la serie, si no retorna null.
     *
     * @param amount el número de butacas pedidas.
     */
    public Seat getAvailableSeats(int amount) {
        for (int row = 0; row < seats.length; row++) {
            Seat seat = getAvailableSeatsInRow(row, amount);
            if (seat != null) return seat;
        }
        return null;
    }

    /**
     * Marca como ocupadas la cantidad de butacas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a reservar.
     */
    public void takeSeats(Seat seat, int amount) {
        if (seat == null) return;
        if (seat.getSeatNumber() + amount > seats[seat.getRow()].length) throw new ArrayIndexOutOfBoundsException();
        for (int x = seat.getSeatNumber(); x < amount; x++)
            seats[seat.getRow()][x].takeSeat();
    }

    /**
     * Libera la cantidad de butacas consecutivas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a liberar.
     */
    public void releaseSeats(Seat seat, int amount) {
        for (int x = seat.getSeatNumber(); x <= amount; x++)
            seats[seat.getRow()][x].releaseSeat();
    }
}