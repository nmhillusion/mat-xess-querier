package app.netlify.nmhillusion.matxess_querier.model;

import app.netlify.nmhillusion.n2mix.type.Stringeable;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public class MsAccessQueryModel extends Stringeable {
    private String databaseFilePath;
    private String queryStatement;

    public String getDatabaseFilePath() {
        return databaseFilePath;
    }

    public MsAccessQueryModel setDatabaseFilePath(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
        return this;
    }

    public String getQueryStatement() {
        return queryStatement;
    }

    public MsAccessQueryModel setQueryStatement(String queryStatement) {
        this.queryStatement = queryStatement;
        return this;
    }
}
