package com.hbase.app;

import java.io.IOException;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.gson.JsonObject;

public class HbaseRestClient {

	public String restClient(String accountNumber) throws IOException {

		Cluster cluster = new Cluster();
		cluster.add("localhost", 8080);

		Client client = new Client(cluster);

		RemoteHTable table = new RemoteHTable(client, "transaction");
		Filter filter = new SingleColumnValueFilter(
				Bytes.toBytes("transaction_details"),
				(Bytes.toBytes("accountnumber")), CompareOp.EQUAL,
				Bytes.toBytes(accountNumber));
		Scan s = new Scan();
		s.setFilter(filter);
		ResultScanner rs = table.getScanner(s);
		JsonObject jsonObject = new JsonObject();
		for (Result r : rs) {
			System.out.println("rowkey:" + new String(r.getRow()));
			for (KeyValue keyValue : r.raw()) {
				jsonObject.addProperty(new String(keyValue.getQualifier()),
						new String(keyValue.getValue()));
			}
		}

		return jsonObject.toString();

	}
}