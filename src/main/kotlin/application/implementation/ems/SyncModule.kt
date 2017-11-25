package application.implementation.ems

import framework.system.SysModule

class SyncModule: SysModule()
{
    override val systemName: String = "ems"
    override val moduleName: String = "sync"
    override val actionNames: Array<String> = arrayOf("copy","log")
}