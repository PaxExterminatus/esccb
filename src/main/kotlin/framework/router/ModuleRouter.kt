package framework.router

import application.implementation.EmsModule
import application.implementation.StreamModule
import application.implementation.StatusModule
import framework.call
import framework.module.*

class ModuleRouter
{
    fun moduleGet(): FModule
    {
        if (call.module == "ems")
            return EmsModule()

        if (call.module == "status")
            return StatusModule()

        if (call.module == "stream")
            return StreamModule()

        return EModule()
    }
}