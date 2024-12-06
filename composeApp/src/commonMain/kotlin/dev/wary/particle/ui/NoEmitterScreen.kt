package dev.wary.particle.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import dev.wary.particle.engine.ParticleEngine
import dev.wary.particle.engine.OverflowPolicy
import dev.wary.geo.Point
import dev.wary.geo.Rect
import dev.wary.particle.engine.Particle
import dev.wary.particle.engine.listParamOf
import dev.wary.particle.engine.toDoubleColor
import kotlin.random.Random

@Composable
fun NoEmitterScreen(modifier: Modifier = Modifier) {
    val colorListParam = listParamOf(
        Color.Red.toDoubleColor(),
        Color.Green.toDoubleColor(),
        Color.Blue.toDoubleColor(),
    )

    val entities = mutableListOf<Rect>()
    repeat (200) {
        entities.add(
            Particle(
                position = Point(
                    Random.nextInt(500).toDouble(),
                    Random.nextInt(500).toDouble()
                ),
                velocity = Point(
                    Random.nextDouble(),
                    Random.nextDouble()
                ),
                width = 32.0,
                height = 32.0,
                lifeSpan = Random.nextLong(3000, 10_000),
                color = colorListParam.value,
            )
        )
    }

    val engineState = remember { mutableStateOf<ParticleEngine?>(null, neverEqualPolicy()) }

    Scaffold(modifier = Modifier.fillMaxSize()
        .onSizeChanged { size ->
            engineState.value = ParticleEngine(
                initialState = entities,
                maxCapacity = 1000,
                edgeCollisions = true,
                particleCollisions = true,
                overflowPolicy = OverflowPolicy.DO_NOT_CREATE,
                bounds = Rect(0.0, 0.0, size.width.toDouble(), size.height.toDouble())
            )
        }
    ) { innerPadding ->
        ParticleOverlay(modifier = Modifier.fillMaxSize(), engineState)

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            //Greeting(name = "No Emitter", modifier = modifier.align(Alignment.Center))
        }
    }
}
