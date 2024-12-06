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
import dev.wary.particle.engine.DoubleColor
import dev.wary.particle.engine.ParticleEngine
import dev.wary.particle.engine.ParticleParams
import dev.wary.particle.engine.PointEmitter
import dev.wary.particle.engine.DoubleRangeParam
import dev.wary.particle.engine.ExactParam
import dev.wary.particle.engine.LongRangeParam
import dev.wary.particle.engine.OverflowPolicy
import dev.wary.particle.engine.RangedParticleBuilder
import dev.wary.geo.Point
import dev.wary.geo.Rect
import dev.wary.particle.engine.listParamOf
import dev.wary.particle.engine.toDoubleColor

@Composable
fun CollisionScreen(modifier: Modifier = Modifier) {
    val emitter = buildPointEmitter()
    val engine = remember { mutableStateOf<ParticleEngine?>(null, policy = neverEqualPolicy()) }

    Scaffold(modifier = Modifier.fillMaxSize()
        .onSizeChanged { size ->
            if (engine.value == null) {
                emitter.position.x = size.width.toDouble() / 2
                emitter.position.y = size.height.toDouble() / 2
                engine.value = buildMyEngine(emitter, size.width.toDouble(), size.height.toDouble())
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            //Greeting(name = "Collisions", modifier = modifier.align(Alignment.Center))
        }
        ParticleOverlay(modifier = Modifier.fillMaxSize(), engine)
    }
}

fun buildPointEmitter(): PointEmitter {
    val builder = RangedParticleBuilder(
        ParticleParams(
            lifeSpan = LongRangeParam(1_000, 30_000),
            width = ExactParam(8.0),
            height = ExactParam(8.0),
            vx = DoubleRangeParam(-0.2, 0.2),
            vy = DoubleRangeParam(-0.4, 0.4),
            ax = ExactParam(0.0),
            ay = ExactParam(0.0),
            color = listParamOf(
                Color.Green.toDoubleColor(),
            ),
            colorChange = ExactParam(DoubleColor(-0.02, -0.1, 0.2, -0.2)),
            onEdgeCollision = listParamOf({ particle -> particle.color = Color.Red.toDoubleColor() }),
            onParticleCollision = ExactParam({ particle, other -> particle.color.blue = 255.0  })
        )
    )
    return PointEmitter(Point(0.0, 0.0), builder, emitRate = 0.04)
}

fun buildMyEngine(entity: Rect, width: Double, height: Double): ParticleEngine {
    val entities = mutableListOf<Rect>().apply {
        add(entity)
    }

    return ParticleEngine(
        initialState = entities,
        maxCapacity = 500,
        edgeCollisions = true,
        particleCollisions = true,
        overflowPolicy = OverflowPolicy.DO_NOT_CREATE,
        bounds = Rect(0.0, 0.0, width, height)
    )
}
