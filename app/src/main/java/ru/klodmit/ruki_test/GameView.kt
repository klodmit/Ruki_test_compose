package ru.klodmit.ruki_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.klodmit.ruki_test.ui.theme.GradientPurple

@Composable
fun GameOfLifeScreen(presenter: GamePresenter) {
    val cells by presenter.cells.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Клеточное наполнение",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(cells.size) { index ->
                Cell(state = cells[index])
            }
        }

        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = {
                presenter.onAddCell()
                coroutineScope.launch {
                    if (cells.isNotEmpty()) {
                        listState.animateScrollToItem(cells.size - 1)
                    }
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("СОТВОРИТЬ", fontSize = 18.sp, modifier = Modifier.padding(6.dp))
        }
    }
}

@Composable
fun Cell(state: CellState) {
    val cellData = when (state) {
        CellState.ALIVE -> CellData(Color.White, "Живая", "и шевелится!", painterResource(id = R.drawable.alive_icon))
        CellState.DEAD -> CellData(Color.White, "Мёртвая", "или прикидывается", painterResource(id = R.drawable.dead_icon))
        CellState.LIFE -> CellData(Color.White, "Жизнь", "Ку-ку!", painterResource(id = R.drawable.life_icon))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(cellData.color, shape = CircleShape)
        ) {
            Image(
                painter = cellData.image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = cellData.title, fontSize = 20.sp, color = Color.Black)
            Text(text = cellData.subtitle, fontSize = 16.sp, color = Color.Gray)
        }
    }
}