package wlw.test

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by slview on 17-6-6.
  */
object testHiveDynamicPartition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("testHiveDynamicPartition")
    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)
    hiveContext.sql("use yuemeqoe_hive")
    hiveContext.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    hiveContext.sql("insert into part_test partition(dayid) select 2, 'name', '20170605' from test limit 1")
    hiveContext.sql("from part_test  select count(*)").collect().foreach(println)
    sc.stop()
  }

}
