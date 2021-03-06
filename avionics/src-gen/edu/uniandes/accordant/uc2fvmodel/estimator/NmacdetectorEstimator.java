/*
 * generated by Accordant
 */

package edu.uniandes.accordant.uc2fvmodel.estimator;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.Transformer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorBuilder;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.spark.TransformerBuilder;

public class NmacdetectorEstimator {

	public static void main(String[] args) throws Exception {

		//TODO add master params code from dv
		SparkConf conf = new SparkConf().setAppName("NmacdetectorEstimator")
						.setMaster("local[*]");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		SparkSession sparkSession = new SparkSession(sc.sc());
		InputStream pmmlFile = new URL("file:////Users/kmilo/Documents/Desarrollo/pmml-models/NMACTreeModel.pmml")
						.openStream();
		EvaluatorBuilder evaluatorBuilder = new LoadingModelEvaluatorBuilder().load(pmmlFile);
		
		Evaluator evaluator = evaluatorBuilder.build();
		evaluator.verify();
		TransformerBuilder pmmlTransformerBuilder = new TransformerBuilder(evaluator)
						.withTargetCols().exploded(true);
		List<StructField> fields = new ArrayList<StructField>();
		
		fields.add(DataTypes.createStructField("y", DataTypes.IntegerType, true));
		fields.add(DataTypes.createStructField("x2", DataTypes.FloatType, true));
		fields.add(DataTypes.createStructField("x4", DataTypes.FloatType, true));
		fields.add(DataTypes.createStructField("x6", DataTypes.FloatType, true));
		StructType schema = DataTypes.createStructType(fields);
		Transformer pmmlTransformer = pmmlTransformerBuilder.build();
		
		//TODO add input connector code
		Dataset<Row> inputDs = sparkSession.read().schema(schema).csv("src/main/resources/nmac_JFK2_predictors.csv");
		
		Dataset<Row> resultDs = pmmlTransformer.transform(inputDs);
		
		//TODO add output connector code
		//log(
		resultDs.coalesce(1).write().option("header", "true").mode("overwrite")
										.csv("out/Nmacdetector.csv");
										
		sparkSession.close();
		sc.close();
	}
}		
