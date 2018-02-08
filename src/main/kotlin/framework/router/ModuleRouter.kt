package framework.router

import application.implementation.EMSDataModule
import application.implementation.CommunicationModule
import application.implementation.StatusModule
import application.implementation.sas.SasModule
import framework.call
import framework.module.*

class ModuleRouter
{
    fun moduleGet(): BaseModule
    {
        if (call.module == "ems")
            return EMSDataModule()

        if (call.module == "status")
            return StatusModule()

        if (call.module == "stream")
            return CommunicationModule()

        if (call.module == "sas")
            return SasModule()

        return ExceptionModule()
    }
}