package framework.global

class Ways {
    val separator = System.getProperty("file.separator")!!
    lateinit var application: String
    lateinit var settings: String
    lateinit var view: String

    fun initWays(ways: String)
    {
        application = ways
        settings = ways + "settings" + separator
        view = ways + "view" + separator
    }
}