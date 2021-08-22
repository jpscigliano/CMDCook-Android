package co.cmd.core.domain

@JvmInline
value class ClientSecretKey(val value: String){
    init {
        require(value.isNotEmpty())
    }
}
