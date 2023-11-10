package utils.db

object DbUserName extends Enumeration {
//  val PostgresQL: DbUserName.Value  = Value("demis_user")
  val PostgresQL: DbUserName.Value  = Value("postgres")
  val TimescaleDB: DbUserName.Value = Value("postgres")
  val CockroachDB: DbUserName.Value = Value("root")
  val CassandraDB: DbUserName.Value = Value("")
}
