package framework.router

import application.module.ems.EmsData
import application.module.communication.Communication
import application.module.status.StatusModule
import application.module.sas.Sas
import framework.call
import framework.module.*

class ModuleRouter
{
    fun moduleGet(): FModule
    {
        if (call.module == "ems")
            return EmsData()

        if (call.module == "status")
            return StatusModule()

        if (call.module == "stream")
            return Communication()

        if (call.module == "sas")
            return Sas()

        return FException()
    }
}