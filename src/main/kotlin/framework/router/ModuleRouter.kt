package framework.router

import application.module.ems.EmsData
import application.module.communication.Communication
import application.module.status.StatusModule
import application.module.sas.Sas
import framework.call
import framework.module.*

class ModuleRouter {
    fun moduleGet(): IModule {
        return when (call.module) {
            "ems" -> EmsData()
            "status" -> StatusModule()
            "stream" -> Communication()
            "sas" -> Sas()
            else -> FWExceptionModule()
        }
    }
}