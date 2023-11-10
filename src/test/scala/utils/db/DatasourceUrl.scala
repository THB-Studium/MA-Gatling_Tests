package utils.db

object DatasourceUrl extends Enumeration {
  val PostgresQL: DatasourceUrl.Value  = Value("jdbc:postgresql://localhost:5432/demis_postgres_db")
  val TimescaleDB: DatasourceUrl.Value = Value("jdbc:postgresql://localhost:5432/demis_timescale_db")
  val CockroachDB: DatasourceUrl.Value = Value("jdbc:postgresql://localhost:26257/demis_roach_db")
  val CassandraDB: DatasourceUrl.Value = Value("jdbc:cassandra://localhost:9042/demis_cassandra_db")
}
