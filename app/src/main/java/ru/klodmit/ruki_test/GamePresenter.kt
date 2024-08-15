package ru.klodmit.ruki_test

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GamePresenter(private val model: GameModel) {
    private val _cells = MutableStateFlow<List<CellState>>(emptyList())
    val cells: StateFlow<List<CellState>> = _cells

    init {
        _cells.value = model.cells
    }

    fun onAddCell() {
        val newCell = if (Random.nextBoolean()) CellState.ALIVE else CellState.DEAD
        val (newCellsState) = model.updateCells(newCell)
        _cells.value = newCellsState.toList()
        Log.d("GamePresenter", "New cell added: $newCell")
        Log.d("GamePresenter", "Cells: $newCellsState")
    }

    fun restoreState(cells: List<CellState>) {
        _cells.value = cells
    }
}


