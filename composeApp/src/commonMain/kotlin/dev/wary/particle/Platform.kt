package dev.wary.particle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform