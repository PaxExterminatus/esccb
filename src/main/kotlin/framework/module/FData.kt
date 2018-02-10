package framework.module

import oracle.sql.Mutable

/**
 * for get data (GET, POST)
 */
abstract class FData {
    abstract fun dataGet(params: MutableMap<String, String>)
}