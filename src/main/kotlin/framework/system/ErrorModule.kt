package framework.system

class ErrorModule: SysModule()
{
    override val moduleName: String = "error"
    override val actionNames: Array<String> = arrayOf("wns","mns","une")

    override fun workRouter(work: String, queryString: String)
    {
        exception("MNS")
    }
}