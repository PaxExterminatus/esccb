package framework.module

sealed class FWException {
    class ModuleNotSupported: FWException()
    class WorkNotSupported: FWException()
    class Unknown: FWException()
}