package app.netlify.nmhillusion.matxess_querier.service_impl;

import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryModel;
import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryResultModel;
import app.netlify.nmhillusion.matxess_querier.service.MsAccessQueryService;
import app.netlify.nmhillusion.n2mix.type.ChainList;
import app.netlify.nmhillusion.neon_di.annotation.Neon;

import java.util.List;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

@Neon
public class MsAccessQueryServiceImpl implements MsAccessQueryService {
    @Override
    public MsAccessQueryResultModel doQuery(MsAccessQueryModel queryModel) {
        return new MsAccessQueryResultModel()
                .setColumnNames(new ChainList<String>()
                        .chainAdd("column 1")
                        .chainAdd("column 2")
                )
                .setTableData(new ChainList<List<Object>>()
                        .chainAdd(new ChainList<Object>()
                                .chainAdd("a")
                                .chainAdd(2)
                        )
                        .chainAdd(new ChainList<Object>()
                                .chainAdd("c")
                                .chainAdd(12)
                        )
                )
                ;
    }
}
