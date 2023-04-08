package com.unongmilk.crafting.util

/**
 * 랜덤 값을 배출하는 함수
 * @param min 최솟값
 * @param max 최댓값
 */
fun random(min: Double, max: Double) : Double{
    return (((min * 10).toInt()..(max * 10).toInt()).random() / 10).toDouble()
}

fun isSucceed(percentage : Int) : Boolean {
    return random(0.0, 100.0) <= percentage
}