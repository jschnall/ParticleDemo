package dev.wary.particle.engine

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import dev.wary.geo.Point
import dev.wary.geo.Polygon
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.jetbrains.compose.resources.imageResource
import particledemo.composeapp.generated.resources.Res


class ParticleRenderer {
    private val paint = Paint()

    private fun drawShape(points: List<Point>, canvas: Canvas) {
//        val arr = FloatArray(points.size * 4)
//        var index = 0
//
//        for (i in 1 until points.size) {
//            arr[index++] = points[i - 1].x.toFloat()
//            arr[index++] = points[i - 1].y.toFloat()
//            arr[index++] = points[i].x.toFloat()
//            arr[index++] = points[i].y.toFloat()
//        }
//        arr[index++] = points[points.size - 1].x.toFloat()
//        arr[index++] = points[points.size - 1].y.toFloat()
//        arr[index++] = points[0].x.toFloat()
//        arr[index] = points[0].y.toFloat()
//
//        paint.strokeWidth = 2f
//        canvas.drawLines(arr, paint)
    }

    fun drawCollisionBounds(particle: Particle, polygon: Polygon, canvas: Canvas) {
        // Draw background
        paint.color = Color(
            red = particle.color.red.toInt(),
            green = particle.color.green.toInt(),
            blue = particle.color.blue.toInt(),
            alpha = particle.color.alpha.toInt(),
        )

        drawShape(polygon.points, canvas)
    }

//    @OptIn(ExperimentalResourceApi::class)
    fun drawParticle(particle: Particle, canvas: Canvas) {
        val bounds = Rect(
            left = particle.left.toFloat(),
            right = (particle.left + particle.width).toFloat(),
            top = particle.top.toFloat(),
            bottom = (particle.top + particle.height).toFloat()
        )

        // Draw background
        paint.color = Color(
            red = particle.color.red.toInt(),
            green = particle.color.green.toInt(),
            blue = particle.color.blue.toInt(),
            alpha = particle.color.alpha.toInt(),
        )
        canvas.drawRect(bounds, paint)

        // Draw image
//        particle.drawableResource?.let { drawable ->
//            canvas.drawImageRect(
//                image = Res.readBytes("").decodeToImageBitmap(),
//                dstOffset = IntOffset(x = particle.left.toInt(), y = particle.top.toInt()),
//                dstSize = IntSize(width = particle.width.toInt(), height = particle.height.toInt()),
//                paint = paint
//            )
//        }


    }
}