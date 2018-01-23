package framework.module

class ExceptionModule : BaseModule()
{
    override val moduleName: String = "error"
    override val workNames: Array<String> = arrayOf("wns","mns","une")

    override fun workRouter(work: String, queryString: String)
    {
        exception("MNS")
    }
}