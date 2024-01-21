package com.example.ivcare.view.notification

import java.util.ArrayList
import java.util.HashMap

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object NotificationContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val COUNT = 25

    private fun fetchNotifications() {
        val client = HttpClient(CIO)
        val nc = this
        runBlocking {
            val response = client.get("https://my-json-server.typicode.com/Dogeuzman/mockjson/notifications").body<String>()
            val obj = Json.decodeFromString<List<PlaceholderItem>>(response)
            println(obj)
            obj.forEach {
                println(it)
                nc.addItem(it)
            }
        }
    }

    init {
        fetchNotifications()
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createPlaceholderItem(position: String): PlaceholderItem {
        return PlaceholderItem(position, "Item $position", "No details")
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    @Serializable
    data class PlaceholderItem(val notifId: String, val unitId: String, val problem: String) {
        val id = notifId
        val content = unitId
        val details = problem

        override fun toString(): String = "$id, $content, $details"
    }
}