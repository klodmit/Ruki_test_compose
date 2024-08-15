package ru.klodmit.ruki_test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameModelTest {

    private lateinit var model: GameModel

    @Before
    fun setUp() {
        model = GameModel()
    }

    @Test
    fun testAddAliveCell() {
        val (newCells) = model.updateCells(CellState.ALIVE)
        assertEquals(1, newCells.size)
        assertEquals(CellState.ALIVE, newCells[0])
    }

    @Test
    fun testAddDeadCell() {
        val (newCells) = model.updateCells(CellState.DEAD)
        assertEquals(1, newCells.size)
        assertEquals(CellState.DEAD, newCells[0])
    }

    @Test
    fun testAddLifeCellAfterThreeAliveCells() {
        model.updateCells(CellState.ALIVE)
        model.updateCells(CellState.ALIVE)
        val (newCells) = model.updateCells(CellState.ALIVE)
        assertEquals(4, newCells.size)
        assertEquals(CellState.LIFE, newCells[3])
    }

    @Test
    fun testChangeLifeCellToDeadAfterThreeDeadCells() {
        model.updateCells(CellState.LIFE)
        model.updateCells(CellState.DEAD)
        model.updateCells(CellState.DEAD)
        model.updateCells(CellState.DEAD)
        val (newCells) = model.updateCells(CellState.DEAD)
        assertEquals(5, newCells.size)
        assertEquals(CellState.DEAD, newCells[0])
    }
}
