import kotlinx.serialization.Serializable

@Serializable
data class PlaceholderItem(val alertId: Int, val alertname: String, val emergency: String, val room: String) {
    val alertName = alertname
    override fun toString(): String = "$alertId, $alertname, $emergency, $room"
}