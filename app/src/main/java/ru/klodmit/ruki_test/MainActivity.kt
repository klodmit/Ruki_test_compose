package ru.klodmit.ruki_test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    private val model = GameModel()
    private val presenter = GamePresenter(model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Восстановление состояния, если оно есть
        savedInstanceState?.let {
            val savedCells = it.getParcelableArrayList<CellState>("cells")
            if (savedCells != null) {
                model.cells = savedCells.toMutableList()
                presenter.restoreState(savedCells)
                Log.d("MainActivity", "State restored: $savedCells")
            }
        }
        setContent {
            MaterialTheme {
                GameOfLifeScreen(presenter)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохранение состояния
        outState.putParcelableArrayList("cells", ArrayList(model.cells))
        Log.d("MainActivity", "State saved: ${model.cells}")
    }
}
