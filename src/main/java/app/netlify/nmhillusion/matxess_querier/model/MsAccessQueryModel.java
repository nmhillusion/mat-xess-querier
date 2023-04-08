package app.netlify.nmhillusion.matxess_querier.model;

import app.netlify.nmhillusion.n2mix.type.Stringeable;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public class MsAccessQueryModel extends Stringeable {
    private String databaseFilePath;
    private String queryFilePath;
    private String resultOutputFilePath;

    public String getDatabaseFilePath() {
        return databaseFilePath;
    }

    public MsAccessQueryModel setDatabaseFilePath(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
        return this;
    }

    public String getQueryFilePath() {
        return queryFilePath;
    }

    public MsAccessQueryModel setQueryFilePath(String queryFilePath) {
        this.queryFilePath = queryFilePath;
        return this;
    }

    public String getResultOutputFilePath() {
        return resultOutputFilePath;
    }

    public MsAccessQueryModel setResultOutputFilePath(String resultOutputFilePath) {
        this.resultOutputFilePath = resultOutputFilePath;
        return this;
    }
}
