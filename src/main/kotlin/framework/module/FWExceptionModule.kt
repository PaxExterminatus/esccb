package framework.module

class FWExceptionModule: IModule {
    override val moduleName: String = "error"
    override val workNames: Array<String> = arrayOf()

    override fun workRouter(work: String, queryString: String) {
        exception(FWException.ModuleNotSupported())
    }
}