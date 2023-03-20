package app.netlify.nmhillusion.matxess_querier.model;

import app.netlify.nmhillusion.n2mix.type.Stringeable;
import org.json.JSONObject;

import java.util.List;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public class MsAccessQueryResultModel extends Stringeable {
    private List<String> columnNames;
    private List<List<Object>> tableData;

    public List<String> getColumnNames() {
        return columnNames;
    }

    public MsAccessQueryResultModel setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public List<List<Object>> getTableData() {
        return tableData;
    }

    public MsAccessQueryResultModel setTableData(List<List<Object>> tableData) {
        this.tableData = tableData;
        return this;
    }

    public JSONObject toJson() {
        final JSONObject jsonObject_ = new JSONObject();

        jsonObject_.put("columnNames", columnNames);
        jsonObject_.put("tableData", tableData);

        return jsonObject_;
    }
}
