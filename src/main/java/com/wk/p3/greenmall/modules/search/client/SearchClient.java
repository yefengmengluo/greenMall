package com.wk.p3.greenmall.modules.search.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SpellingParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.modules.search.entry.DemandInfo;
import com.wk.p3.greenmall.modules.search.entry.KeyWords;
import com.wk.p3.greenmall.modules.search.entry.ProvideInfo;

/**
 * SearchClient
 * 
 * @author Robin
 *
 */
public class SearchClient {
	private final static String DEFAULT_URL_KEY = "url";
	private final static String DEFAULT_SUGGEST_KEY = "urlwk";
	private static Logger logger = LoggerFactory.getLogger(SearchClient.class);
	private SolrClient client;

	private SearchClient(String key) {
		try {
			InputStream in = SearchClient.class.getResourceAsStream("/solr.properties");
			Properties p = new Properties();
			p.load(in);
			in.close();
			String DEFAULT_URL = p.getProperty(key);
			client = new HttpSolrClient(DEFAULT_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param params
	 *            参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object search(Map<String, String> params) {
		JSONObject jsonobj = new JSONObject();
		JSONArray brandnamefacet = new JSONArray();
		JSONArray produceplacefacet = new JSONArray();
		JSONArray areafacet = new JSONArray();
		String classname = params.get("class");
		try {
			SolrQuery solrparams = new SolrQuery();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				solrparams.setParam(entry.getKey(), entry.getValue());
			}
			// facet
			solrparams.setFacet(true).addFacetField(new String[] { "breed", "produceplace", "area" });
			// 高亮
			solrparams.setHighlight(true).addHighlightField("spuname").setHighlightSimplePost("<em>")
					.addHighlightField("</em>");
			solrparams.remove("class");
			QueryResponse queryResponse = new SearchClient(DEFAULT_URL_KEY).client.query(solrparams);
			// 返回数据
			Class<? extends Class> clazz = (Class<? extends Class>) Class.forName(classname).newInstance().getClass();
			List<? extends Class> listBean = queryResponse.getBeans(clazz);
			// 高亮数据
			Map<String, Map<String, List<String>>> result = queryResponse.getHighlighting();
			ProvideInfo provideInfo = new ProvideInfo();
			DemandInfo demandInfo = new DemandInfo();
			for (Object obj : listBean) {
				if (clazz.equals(ProvideInfo.class)) {
					provideInfo = (ProvideInfo) obj;
					String id = provideInfo.getId();
					Map<String, List<String>> map = result.get(id);
					if (map.get("spuname") != null) {
						provideInfo.setSpuName(map.get("spuname").get(0));
					}
				}
				if (clazz.equals(DemandInfo.class)) {
					demandInfo = (DemandInfo) obj;
					String id = demandInfo.getId();
					Map<String, List<String>> map = result.get(id);
					if (map.get("spuname") != null) {
						demandInfo.setSpuName(map.get("spuname").get(0));
					}

				}
			}
			jsonobj.put("data", JSONObject.toJSON(listBean));
			for (FacetField facet : queryResponse.getFacetFields()) {
				String name = facet.getName();
				for (Count count : facet.getValues()) {
					JSONObject json = new JSONObject();
					if (count.getCount() == 0)
						continue;
					json.put(count.getName(), count.getCount());
					if (name.equals("breed")) {
						brandnamefacet.add(json);
					}
					if (name.equals("produceplace")) {
						produceplacefacet.add(json);
					}
					if (name.equals("area")) {
						areafacet.add(json);
					}
				}
			}
			jsonobj.put("brandname", brandnamefacet);
			jsonobj.put("produceplace", produceplacefacet);
			jsonobj.put("area", areafacet);
			jsonobj.put("count", queryResponse.getResults().getNumFound());
			return jsonobj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Search error:" + e.getMessage() + "params:[" + params.get("keys") + "]");
			return null;
		}
	}

	/**
	 * 添加文档到solr
	 */
	public static void addDocument(List<SolrInputDocument> list) {
		try {
			logger.info(DateUtils.getDateTime() + "ADD list document:[" + list + "]");
			SolrClient client = new SearchClient(DEFAULT_URL_KEY).client;
			client.add(list);
			client.commit();
			logger.info(DateUtils.getDateTime() + "ADD SUCCESS list document:[" + list + "]");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(DateUtils.getDateTime() + "ERROR Add list document:[" + list + "]");
		}
	}

	/**
	 * 删除文档
	 */
	public static void delDocument(SolrQuery solrparams) {
		try {
			SolrDocumentList list = getDocument(solrparams);
			logger.info(DateUtils.getDateTime() + "Del solrparams:[" + solrparams + "]");
			SolrClient client = new SearchClient(DEFAULT_URL_KEY).client;
			for (SolrDocument solrDocument : list) {
				client.deleteById(String.valueOf(solrDocument.get("id")));
			}
			client.commit();
			logger.info(DateUtils.getDateTime() + "Del SUCCESS solrparams:[" + solrparams + "]");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(DateUtils.getDateTime() + "ERROR Del  solrparams:[" + solrparams + "]");
		}

	}

	/**
	 * 获取文档
	 */
	private static SolrDocumentList getDocument(SolrQuery solrparams) {
		try {
			QueryResponse queryResponse = new SearchClient(DEFAULT_URL_KEY).client.query(solrparams);
			return queryResponse.getResults();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 维护词库
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void addSuggest(List<SolrInputDocument> list) throws SolrServerException, IOException {
		SolrClient client = new SearchClient(DEFAULT_SUGGEST_KEY).client;
		try {
			client.add(list);
			client.commit();
		} catch (Exception e) {
			e.printStackTrace();
			client.rollback();
		}

	}

	/**
	 * 词库搜索
	 */
	public static Object suggest(SolrQuery query) {
		try {
			SolrClient client = new SearchClient(DEFAULT_SUGGEST_KEY).client;
			QueryResponse response = client.query(query);
			List<KeyWords> list = response.getBeans(KeyWords.class);
			logger.info("词库推荐："+JSONArray.toJSON(list));
			return JSONArray.toJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 容错信息推荐
	 */
	public static Object faultTolerant(SolrQuery query){
		try {
			SolrClient client = new SearchClient(DEFAULT_SUGGEST_KEY).client;
			QueryResponse response = client.query(query);
			SpellCheckResponse checkResponse=response.getSpellCheckResponse();
			JSONObject json=(JSONObject) JSONObject.toJSON(checkResponse.getSuggestion(query.get(SpellingParams.SPELLCHECK_Q)));
			json.put("alternative", json.getJSONArray("alternatives").get(0).toString().replaceAll(query.get(SpellingParams.SPELLCHECK_Q), ""));
			logger.info("纠错信息："+json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		new SearchClient(DEFAULT_URL_KEY);
	}

}
