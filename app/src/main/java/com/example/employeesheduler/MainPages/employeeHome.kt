package com.example.employeesheduler.MainPages

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.employeesheduler.Data.Event
import com.example.employeesheduler.Navigation.Screen
import com.example.employeesheduler.R
import com.example.employeesheduler.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun employeeHome(
    navHostController: NavHostController,
    viewModel: viewModel
) {

    viewModel.getAllEvents()
    val events by viewModel.allEvents.observeAsState(initial = emptyList())

    Column(Modifier.fillMaxSize()) {

        val selectedMonth by remember { mutableStateOf(LocalDate.now().month) }
        val selectedYear by remember { mutableStateOf(LocalDate.now().year) }
        val daysInMonth = LocalDate.of(selectedYear, selectedMonth, 1).lengthOfMonth()
        val daysOfMonth = (1..daysInMonth).map { day ->
            LocalDate.of(selectedYear, selectedMonth, day)
        }

        Text(
            text = "${selectedMonth.toString().lowercase().capitalize()} $selectedYear",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            var selected = mutableStateOf(false)
            var size = mutableStateOf(50)
            if (selected.value) {
                size.value = 150
            } else {
                size.value = 50
            }

            itemsIndexed(daysOfMonth) { index, date ->
                CalendarView(
                    date = date,
                    events = events.filter { it.date == date.toString() },
                    viewModel = viewModel,
                    navHostController = navHostController
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    date: LocalDate,
    events: List<Event>,
    viewModel: viewModel,
    navHostController: NavHostController
) {
    var selected by remember { mutableStateOf(false) }
    Column(
//        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .size(if (selected && events.isNotEmpty()) 150.dp else if (selected) 100.dp else 50.dp)
            .clip(RoundedCornerShape(20))
            .background(Color(0xA9B1B9FC))
            .clickable(onClick = { selected = !selected })
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)) {
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier.padding(start = 14.dp)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            if (events.isNotEmpty()) {
                if (selected) {
                    Text(text = "Hide events")
                    Image(
                        painter = painterResource(id = R.drawable.arrow_up_black),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 9.dp, top = 4.dp)
                            .size(14.dp)
                    )
                } else {
                    Text(text = "Show events")
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down_black),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 9.dp, top = 4.dp)
                            .size(14.dp)
                    )
                }
            } else {
                Text(
                    text = "No events",
                    modifier = Modifier.padding(end = 14.dp)
                )
                if (selected) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_up_black),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 9.dp, top = 4.dp)
                            .size(14.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down_black),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 9.dp, top = 4.dp)
                            .size(14.dp)
                    )
                }
            }
        }
        if (selected) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                LazyColumn {
                    items(items = events) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(Modifier.fillMaxWidth()) {
                            Text(text = it.title!!)
                            Spacer(modifier = Modifier.weight(0.2f))
                            Text(text = it.description!!)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = {
                        viewModel.update(name = "date", value = date.toString())
                        navHostController.navigate(Screen.newEvent.route)
                              },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xA9E284FC))
                ) {
                    Image(painter = painterResource(id = R.drawable.add_white), contentDescription = "")
                }
            }

    }
    }
}