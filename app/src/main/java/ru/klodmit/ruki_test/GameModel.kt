package ru.klodmit.ruki_test

import android.util.Log

class GameModel {
    var cells: MutableList<CellState> = mutableListOf()
    var lastFourStates: List<CellState> = listOf()

    fun updateCells(newCell: CellState): Pair<List<CellState>, List<CellState>> {
        cells.add(newCell)
        val updatedLastFourStates = (lastFourStates + newCell).takeLast(4)
        return when {
            updatedLastFourStates.size >= 3 && updatedLastFourStates.takeLast(3).all { it == CellState.ALIVE } -> {
                cells.add(CellState.LIFE)
                lastFourStates = updatedLastFourStates + CellState.LIFE
                Pair(cells, lastFourStates)
            }
            updatedLastFourStates.size == 4 && updatedLastFourStates.takeLast(3).all { it == CellState.DEAD } -> {
                if (updatedLastFourStates[0] == CellState.LIFE) {
                    cells[cells.size - 4] = CellState.DEAD
                    lastFourStates = updatedLastFourStates + CellState.DEAD
                }
                Pair(cells, lastFourStates)
            }
            else -> {
                lastFourStates = updatedLastFourStates
                Pair(cells, lastFourStates)
            }
        }
    }
}

