package framework.system

class ErrorModule: SysModule() {
    override val systemName: String = "exception"
    override val moduleName: String = "error"
    override val actionNames: Array<String> = arrayOf("404")
}