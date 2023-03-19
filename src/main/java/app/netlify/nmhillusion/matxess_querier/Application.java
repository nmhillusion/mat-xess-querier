package app.netlify.nmhillusion.matxess_querier;

import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryModel;
import app.netlify.nmhillusion.matxess_querier.model.MsAccessQueryResultModel;
import app.netlify.nmhillusion.matxess_querier.parser.ArgsParser;
import app.netlify.nmhillusion.matxess_querier.service.MsAccessQueryService;
import app.netlify.nmhillusion.n2mix.exception.GeneralException;
import app.netlify.nmhillusion.n2mix.exception.InvalidArgument;
import app.netlify.nmhillusion.n2mix.helper.log.LogHelper;
import app.netlify.nmhillusion.neon_di.NeonEngine;
import app.netlify.nmhillusion.neon_di.exception.NeonException;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public class Application {
    private static final NeonEngine NEON_ENGINE = new NeonEngine();

    static {
        try {
            NEON_ENGINE
                    .run(Application.class);
        } catch (NeonException e) {
            throw new RuntimeException(e);
        }
    }

    public static NeonEngine getNeonEngine() {
        return NEON_ENGINE;
    }

    public static void main(String[] args) throws InvalidArgument, GeneralException {
        final Application app = new Application();
        app.run(args);
    }

    private void run(String[] args) throws InvalidArgument, GeneralException {
        final ArgsParser argsParser = NEON_ENGINE.makeSureObtainNeon(ArgsParser.class);
        final MsAccessQueryService msAccessQueryService = NEON_ENGINE.makeSureObtainNeon(MsAccessQueryService.class);


        final MsAccessQueryModel msAccessQueryModel = argsParser.parser(args);
        final MsAccessQueryResultModel msAccessQueryResultModel = msAccessQueryService.doQuery(msAccessQueryModel);

        LogHelper.getLogger(this).infoFormat("result of query: %s", msAccessQueryResultModel);
    }
}
