package app.netlify.nmhillusion.matxess_querier.parser;

import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryModel;
import app.netlify.nmhillusion.n2mix.exception.InvalidArgument;
import app.netlify.nmhillusion.n2mix.util.CollectionUtil;
import app.netlify.nmhillusion.neon_di.annotation.Neon;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

@Neon
public class ArgsParser {
    public MsAccessQueryModel parser(String[] args) throws InvalidArgument {
        if (CollectionUtil.isNullOrEmpty(args)) {
            throw new InvalidArgument("args is empty");
        }

        if (2 > args.length) {
            throw new InvalidArgument("args must have at least 2 arguments");
        }

        String resultOutputFilePath = "out.json";

        if (3 <= args.length) {
            resultOutputFilePath = args[2];
        }

        return new MsAccessQueryModel()
                .setDatabaseFilePath(args[0])
                .setQueryStatement(args[1])
                .setResultOutputFilePath(resultOutputFilePath);
    }
}
