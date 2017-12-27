package application.source

import framework.settings
import framework.sources.Database

class DatabaseCross : Database(settings.dbConnection("cross"))
{
    fun truncateEmsClientTable() {
        connection.createStatement().executeUpdate("TRUNCATE TABLE EMS_CLIENT")
        connection.commit()
    }
}