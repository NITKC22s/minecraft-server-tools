import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import java.net.Inet4Address

val configFile = File("mstools.config.json")
const val AUTO_IP = "autoIPv4"

@kotlinx.serialization.Serializable
data class Config(
    val gitLocalRepo: String? = null,
    val gitRemoteRepo: String? = null,
    val sshSecretKeyPath: String? = null,
    val ddnsNowUser: String? = null,
    val ddnsNowPassword: String? = null,
    val ipAddress: String? = AUTO_IP,//autoで自動判別
    val tcpPorts: Array<Int>? = null,
    val udpPorts: Array<Int>? = null
) {
    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun readConfig(): Config = Json.decodeFromStream(serializer(), configFile.inputStream())
    }
    //Auto Generated by いんてりじぇー
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Config

        if (gitLocalRepo != other.gitLocalRepo) return false
        if (gitRemoteRepo != other.gitRemoteRepo) return false
        if (sshSecretKeyPath != other.sshSecretKeyPath) return false
        if (ddnsNowUser != other.ddnsNowUser) return false
        if (ddnsNowPassword != other.ddnsNowPassword) return false
        if (ipAddress != other.ipAddress) return false
        if (tcpPorts != null) {
            if (other.tcpPorts == null) return false
            if (!tcpPorts.contentEquals(other.tcpPorts)) return false
        } else if (other.tcpPorts != null) return false
        if (udpPorts != null) {
            if (other.udpPorts == null) return false
            if (!udpPorts.contentEquals(other.udpPorts)) return false
        } else if (other.udpPorts != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gitLocalRepo?.hashCode() ?: 0
        result = 31 * result + (gitRemoteRepo?.hashCode() ?: 0)
        result = 31 * result + (sshSecretKeyPath?.hashCode() ?: 0)
        result = 31 * result + (ddnsNowUser?.hashCode() ?: 0)
        result = 31 * result + (ddnsNowPassword?.hashCode() ?: 0)
        result = 31 * result + (ipAddress?.hashCode() ?: 0)
        result = 31 * result + (tcpPorts?.contentHashCode() ?: 0)
        result = 31 * result + (udpPorts?.contentHashCode() ?: 0)
        return result
    }
}

