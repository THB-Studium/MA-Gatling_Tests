package utils.db

object DbName extends Enumeration {
  val PostgresQL: DbName.Value  = Value("PostgresQL")
  val TimescaleDB: DbName.Value = Value("TimescaleDB")
  val CockroachDB: DbName.Value = Value("CockroachDB")
  val CassandraDB: DbName.Value = Value("CassandraDB")
}
