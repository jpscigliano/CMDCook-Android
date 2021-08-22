package co.cmd.cook.mappers

interface Mapper<In, Out> {

    fun map(input: In): Out
}