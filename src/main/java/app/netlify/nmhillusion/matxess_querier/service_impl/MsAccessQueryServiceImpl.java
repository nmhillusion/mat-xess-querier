package app.netlify.nmhillusion.matxess_querier.service_impl;

import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryModel;
import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryResultModel;
import app.netlify.nmhillusion.matxess_querier.service.MsAccessQueryService;
import app.netlify.nmhillusion.n2mix.exception.GeneralException;
import app.netlify.nmhillusion.neon_di.annotation.Neon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.netlify.nmhillusion.n2mix.helper.log.LogHelper.getLogger;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

@Neon
public class MsAccessQueryServiceImpl implements MsAccessQueryService {

    private List<String> getColumnNamesOfResultSet(ResultSet resultSet) throws SQLException {
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        final List<String> columnNames = new ArrayList<>();

        getLogger(this).info("column count: " + columnCount);
        for (int colIdx = 1; colIdx <= columnCount; ++colIdx) {
            columnNames.add(metaData.getColumnLabel(colIdx));
        }
        getLogger(this).info("column names: " + columnNames);

        return columnNames;
    }

    @Override
    public MsAccessQueryResultModel doQuery(MsAccessQueryModel queryModel) throws GeneralException {
        String connectionString = "jdbc:ucanaccess://@pathToAccessDbFile;memory=false";
        connectionString = connectionString.replace("@pathToAccessDbFile", queryModel.getDatabaseFilePath())
        ;

        try (final Connection connection_ = DriverManager.getConnection(connectionString);
             final PreparedStatement preparedStatement_ = connection_.prepareStatement(queryModel.getQueryFilePath());
             final ResultSet resultSet = preparedStatement_.executeQuery()
        ) {
            final List<String> columnNames = getColumnNamesOfResultSet(resultSet);
            final List<List<Object>> tableData = new ArrayList<>();

            final int columnCount = columnNames.size();

            while (resultSet.next()) {
                final List<Object> rowData = new ArrayList<>();
                for (int colIdx = 1; colIdx <= columnCount; ++colIdx) {
                    rowData.add(
                            resultSet.getObject(colIdx)
                    );
                }

                tableData.add(
                        rowData
                );
            }

            return new MsAccessQueryResultModel()
                    .setColumnNames(columnNames)
                    .setTableData(tableData)
                    ;
        } catch (Throwable ex) {
            getLogger(this).error(ex);
            throw new GeneralException("Error when query to MS Access: " + ex.getMessage());
        }
    }
}
