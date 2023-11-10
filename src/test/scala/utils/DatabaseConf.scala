package utils

import utils.TestHelper.{DELETE_CQL_QUERY, DELETE_SQL_QUERY}
import utils.db.{DatasourceUrl, DbName, DbPassword, DbUserName, TableName}

import java.sql.DriverManager

class DatabaseConf {
  private var url: String            = ""
  private var username: String       = ""
  private var password: String       = ""
  private var deleteQuery: String    = ""

  //noinspection SqlSourceToSinkFlow
  def cleanup(table: String): Unit = {
    if (TestHelper.currentDB != DbName.CassandraDB && TestHelper.currentDB != DbName.CockroachDB) {
      deleteQuery = _setSqlOrCqlQuery()
      _setDbCredentials()

      // Get a connection to the database
      val connection = DriverManager.getConnection(url, username, password)

      // Run a request to delete all the data
      val statement = connection.createStatement()
      if (!TestHelper.currentDB.equals(DbName.CassandraDB) && table == TableName.BundleMod.toString) {
        statement.executeUpdate(deleteQuery.format(TableName.bundleModEntry.toString))
      }
      statement.executeUpdate(deleteQuery.format(table))

      // Close the database connection
      connection.close()
    }
  }

  private def _setDbCredentials(): Unit = {
    TestHelper.currentDB match {
      case DbName.PostgresQL  =>
        url = DatasourceUrl.PostgresQL.toString; username  = DbUserName.PostgresQL.toString; password  = DbPassword.PostgresQL.toString
      case DbName.TimescaleDB =>
        url = DatasourceUrl.TimescaleDB.toString; username = DbUserName.TimescaleDB.toString; password = DbPassword.TimescaleDB.toString
      case DbName.CockroachDB =>
        url = DatasourceUrl.CockroachDB.toString; username = DbUserName.CockroachDB.toString; password = DbPassword.CockroachDB.toString
      case DbName.CassandraDB =>
        url = DatasourceUrl.CassandraDB.toString; username = DbUserName.CassandraDB.toString; password = DbPassword.CassandraDB.toString
    }
  }

  private def _setSqlOrCqlQuery(): String = {
    if(TestHelper.currentDB.equals(DbName.CassandraDB))
      DELETE_CQL_QUERY else DELETE_CQL_QUERY
  }

}
