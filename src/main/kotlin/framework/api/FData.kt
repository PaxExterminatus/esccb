package framework.api

import oracle.sql.Mutable

/**
 * for get data (GET, POST)
 */
abstract class FData {
    abstract fun dataGet(params: MutableMap<String, String>)

    fun methodRouter(methodGet: String) {
        return when (methodGet) {
            "findById" -> findById()
            else -> exception()
        }
    }

    private fun findById() {

    }

    private fun exception() {

    }
}