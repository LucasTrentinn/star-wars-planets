package com.example.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.example.erp.model.Planeta;
import com.example.erp.repository.Planetas;
import com.example.erp.util.Transacional;

public class PlanetasService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Planetas planetas;

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	@Transacional
	public void salvar(Planeta planeta) {
		planetas.gravar(planeta);
	}

	@Transacional
	public void excluir(Planeta planeta) {
		planetas.remover(planeta);
	}

	@SuppressWarnings("unused")
	public int getFilmAppearences(String nomePlaneta) throws Exception {

		HttpGet request = new HttpGet("https://swapi.dev/api/planets/?search=" + nomePlaneta);

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();

			if (entity != null) {
				// return it as a String
				String result = EntityUtils.toString(entity);
				JSONObject resultJson = new JSONObject(result);
				JSONArray resultsProp = resultJson.getJSONArray("results");

				if (resultsProp.length() > 0) {
					JSONObject resultsPropObj = resultsProp.getJSONObject(0);
					JSONArray filmes = resultsPropObj.getJSONArray("films");

					return filmes.length();
				}

				return 0;

			}

		}
		return 0;
	}

}
