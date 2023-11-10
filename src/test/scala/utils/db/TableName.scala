package utils.db

object TableName extends Enumeration {
  val BinaryMod: TableName.Value                = Value("binary_mod")
  val BundleMod: TableName.Value                = Value("bundle_mod")
  val BinaryMod_Cassandra: TableName.Value      = Value("binarymod")
  val BundleMod_Cassandra: TableName.Value      = Value("bundlemod")
  val bundleModEntry: TableName.Value           = Value("bundle_mod_entry")
}
