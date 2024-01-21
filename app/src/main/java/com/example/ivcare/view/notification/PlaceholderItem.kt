import kotlinx.serialization.Serializable

@Serializable
data class PlaceholderItem(val notifId: Int, val unitId: Int, val problem: Int) {
    val id = "Id(${notifId})"
    val content = "Content($unitId)"
    val details = "Details($problem)"

    override fun toString(): String = "$id, $content, $details"
}