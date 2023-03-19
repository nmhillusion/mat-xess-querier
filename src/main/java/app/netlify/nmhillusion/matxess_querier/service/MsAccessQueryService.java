package app.netlify.nmhillusion.matxess_querier.service;

import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryModel;
import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryResultModel;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public interface MsAccessQueryService {
    MsAccessQueryResultModel doQuery(MsAccessQueryModel queryModel);
}
