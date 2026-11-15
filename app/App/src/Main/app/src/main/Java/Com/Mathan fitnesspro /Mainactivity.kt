package com.mathan.fitnesspro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkBackground = Color(0xFF101411)
private val CardBackground = Color(0xFF1C241F)
private val FitnessGreen = Color(0xFFB7F56A)
private val WhiteText = Color(0xFFFFFFFF)
private val GrayText = Color(0xFFADB5AE)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitnessApp()
        }
    }
}

@Composable
fun FitnessApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = FitnessGreen,
            background = DarkBackground,
            surface = CardBackground
        )
    ) {
        Scaffold(
            containerColor = DarkBackground,
            bottomBar = {
                NavigationBar(
                    containerColor = CardBackground
                ) {
                    val tabs = listOf(
                        "Home",
                        "Workout",
                        "Nutrition",
                        "Progress",
                        "Profile"
                    )

                    val icons = listOf("⌂", "🏋", "🥗", "📊", "👤")

                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Text(icons[index])
                            },
                            label = {
                                Text(title, fontSize = 10.sp)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = FitnessGreen,
                                selectedTextColor = FitnessGreen,
                                indicatorColor = Color(0xFF34452D),
                                unselectedIconColor = GrayText,
                                unselectedTextColor = GrayText
                            )
                        )
                    }
                }
            }
        ) { padding ->

            when (selectedTab) {
                0 -> HomeScreen(Modifier.padding(padding))
                1 -> WorkoutScreen(Modifier.padding(padding))
                2 -> NutritionScreen(Modifier.padding(padding))
                3 -> ProgressScreen(Modifier.padding(padding))
                4 -> ProfileScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "MATHAN FITNESS PRO",
                color = FitnessGreen,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "YOUR FITNESS. YOUR CONTROL.",
                color = GrayText,
                fontSize = 11.sp
            )
        }

        item {
            Text(
                "Welcome Back!",
                color = WhiteText,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Let's make today count.",
                color = GrayText
            )
        }

        item {
            FitnessCard("TODAY'S ACTIVITY") {
                Text("🔥  Calories burned: 0 kcal", color = WhiteText)
                Text("🏋️  Workouts completed: 0", color = WhiteText)
                Text("⏱️  Workout duration: 0 min", color = WhiteText)
            }
        }

        item {
            FitnessCard("QUICK ACCESS") {
                Text("🏋️  Workout Plans", color = FitnessGreen)
                Text("🥗  Nutrition Tracker", color = FitnessGreen)
                Text("💧  Water Tracker", color = FitnessGreen)
                Text("📊  Progress Reports", color = FitnessGreen)
            }
        }

        item {
            FitnessCard("DAILY MOTIVATION") {
                Text(
                    "Consistency is the key to progress.",
                    color = WhiteText,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun WorkoutScreen(modifier: Modifier = Modifier) {
    var completed by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "WORKOUT CENTER",
            color = FitnessGreen,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Text("Your daily training", color = GrayText)

        FitnessCard("TODAY'S PLAN") {
            Text("Chest Workout", color = WhiteText)
            Text("Bench Press - 3 Sets", color = GrayText)
            Text("Push Ups - 3 Sets", color = GrayText)
            Text("Chest Fly - 3 Sets", color = GrayText)

            Button(
                onClick = { completed++ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = FitnessGreen,
                    contentColor = Color.Black
                )
            ) {
                Text("Complete Workout")
            }

            Text(
                "Completed workouts: $completed",
                color = FitnessGreen
            )
        }
    }
}

@Composable
fun NutritionScreen(modifier: Modifier = Modifier) {
    var water by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "NUTRITION CENTER",
            color = FitnessGreen,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        FitnessCard("DAILY NUTRITION") {
            Text("Calories: Track your meals", color = WhiteText)
            Text("Protein: Track your intake", color = WhiteText)
            Text("Carbohydrates: Track your intake", color = WhiteText)
            Text("Fats: Track your intake", color = WhiteText)
        }

        FitnessCard("WATER TRACKER") {
            Text("Water glasses: $water", color = WhiteText)

            Button(
                onClick = { water++ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = FitnessGreen,
                    contentColor = Color.Black
                )
            ) {
                Text("Add 1 Glass")
            }
        }
    }
}

@Composable
fun ProgressScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "MY PROGRESS",
            color = FitnessGreen,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        FitnessCard("FITNESS STATISTICS") {
            Text("Weight records: Coming soon", color = WhiteText)
            Text("Workout history: Coming soon", color = WhiteText)
            Text("Weekly reports: Coming soon", color = WhiteText)
            Text("Monthly reports: Coming soon", color = WhiteText)
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "MY PROFILE",
            color = FitnessGreen,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        FitnessCard("ACCOUNT SETTINGS") {
            Text("App: MATHAN FITNESS PRO", color = WhiteText)
            Text("Version: 1.0", color = WhiteText)
            Text("Storage: Offline mode", color = WhiteText)
            Text("Cloud backup: Coming soon", color = GrayText)
            Text("AI Fitness Coach: Coming soon", color = GrayText)
        }
    }
}

@Composable
fun FitnessCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content
        ) {
            Text(
                title,
                color = FitnessGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
