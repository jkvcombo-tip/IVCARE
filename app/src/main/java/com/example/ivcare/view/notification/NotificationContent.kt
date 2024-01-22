package com.example.ivcare.view.notification

import PlaceholderItem
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

val OFFLINE = false;
val OFFLINE_DATA: String = """
    [
      {
        "alertId": 1,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 1"
      },
      {
        "alertId": 2,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 2"
      },
      {
        "alertId": 3,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 3"
      },
      {
        "alertId": 4,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 4"
      },
      {
        "alertId": 5,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 5"
      },
      {
        "alertId": 6,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 6"
      },
      {
        "alertId": 7,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 7"
      },
      {
        "alertId": 8,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 8"
      },
      {
        "alertId": 9,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 9"
      },
      {
        "alertId": 10,
        "alertname": "Alert",
        "emergency": "Emergency",
        "room": "ROOM 10"
      }
    ]
""".trimIndent()

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
    val ITEM_MAP: MutableMap<Int, PlaceholderItem> = HashMap()

    private val COUNT = 25

    private fun fetchNotifications() {
        val client = HttpClient(CIO)
        val nc = this
        runBlocking {
            val response = if (OFFLINE) {
                client.get("https://my-json-server.typicode.com/jkvcombo-tip/jkjson/notifications").body<String>()
            }else{
                OFFLINE_DATA
            }
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
        ITEM_MAP[item.alertId] = item
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}