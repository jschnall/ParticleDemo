package dev.wary.particle

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import dev.wary.particle.ui.CollisionScreen
import dev.wary.particle.ui.LineEmitterScreen
import dev.wary.particle.ui.NoEmitterScreen
import dev.wary.particle.ui.ParticleEmitterScreen
import dev.wary.particle.ui.PointEmitterScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        // NoEmitterScreen()
        PointEmitterScreen()
        // LineEmitterScreen()
        // ParticleEmitterScreen()
        // CollisionScreen()
    }
}