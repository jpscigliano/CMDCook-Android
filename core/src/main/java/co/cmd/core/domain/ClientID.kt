package co.cmd.core.domain

@JvmInline
value class ClientID(val value: String){
    init {
        require(value.isNotEmpty())
    }
}

