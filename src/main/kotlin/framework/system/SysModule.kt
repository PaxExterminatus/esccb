package framework.system

abstract class SysModule {
    abstract val systemName: String //todo реализовать получение имени системы из имени пакета модуля
    abstract val moduleName: String //todo имя модуля из имени класса
    abstract val actionNames: Array<String> //todo получать список действий из методов с именем Action
}