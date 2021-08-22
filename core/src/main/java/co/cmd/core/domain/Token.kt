package co.cmd.core.domain

@JvmInline
value class Token(val value: String){
    init {
        require(value.isNotEmpty())
    }
}

