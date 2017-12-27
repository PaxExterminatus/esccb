package framework.datasources
import framework.settings
import java.sql.*

class DataBase(private val connection: Connection)
{
    var changeCaseTo = "" //upper, lower // else -> not Change

    fun selectResultSet(fromArgument: String = "dual", selectArgument: String = "*"): ResultSet {
        return connection.createStatement().executeQuery("SELECT $selectArgument FROM ${changeCase(fromArgument)}")
    }

    fun insert(intoArgument: String, resultSet: ResultSet) {
        val command = "INSERT INTO ${changeCase(intoArgument)} VALUES (${bindVarStrBuild(resultSet.metaData.columnCount)})"
        var colIndex = resultSet.metaData.columnCount //Количество колонок, необходимо для карты полей
        var countProcessedRows = 0 //Счетчик обработанных строк

        var call = connection.prepareStatement(command) //Подготовка запроса
        connection.autoCommit = false //Отключаем автоматическое подтверждение транзакции

        while (resultSet.next()) { //Итерация по набору данных
            countProcessedRows++ //Подсчитываем обработанные строки

            while (colIndex > 0) { //Карта полей запроса по индексу
                if (colIndex == 3) { //todo добавить карту параметр_тип_данных
                    call.setClob(colIndex, resultSet.getClob(colIndex))
                } else {
                    call.setString(colIndex, resultSet.getString(colIndex))
                }
                colIndex--
            }
            colIndex = resultSet.metaData.columnCount //Сбрасываем индекс полей для следующей итерации

            call.addBatch() //Добвляем запрос в пакет

            if (settings.sqlInsertBatchSize > 0) {//использовать одту транзакцию
                if ((countProcessedRows % settings.sqlInsertBatchSize) == 0) { // Выполняем пакет после каждой "X" строки
                    call.executeBatch() // Выполнить операторы в пакете
                    connection.commit() // Применить изменения
                }
            }
        }
        call.executeBatch() //Выполняем оставшиеся запросы в батче
        connection.commit()
        connection.autoCommit = true
    }

    /**
     * Создает строку с опредленным количеством связанных переменных, например "?,?,?,?"
     */
    private fun bindVarStrBuild(count: Int, char: Char = '?'): String {
        var s = ""
        var i = count
        while (i > 0) {
            s += if (i == 1) char else "$char,"
            i--
        }
        return s
    }

    private fun changeCase(str: String): String {
        return when (changeCaseTo) {
            "upper" -> str.toUpperCase()
            "lower" -> str.toLowerCase()
            else -> str
        }
    }
}