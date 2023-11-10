package utils.db

object DbPassword extends Enumeration {
  val PostgresQL: DbPassword.Value  = Value("password")
  val TimescaleDB: DbPassword.Value = Value("password")
  val CockroachDB: DbPassword.Value = Value("")
  val CassandraDB: DbPassword.Value = Value("")
}
