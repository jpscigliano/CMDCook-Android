package co.cmd.core.domain

@JvmInline
value class ID(val value: String) {

    init {
        require(value.isNotEmpty())
    }
}
