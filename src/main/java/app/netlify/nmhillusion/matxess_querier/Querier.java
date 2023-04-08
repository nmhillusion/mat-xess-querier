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
import app.netlify.nmhillusion.pi_logger.PiLoggerFactory;
import app.netlify.nmhillusion.pi_logger.constant.LogLevel;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * date: 2023-03-19
 * <p>
 * created-by: nmhillusion
 */

public class Querier {
    private static final NeonEngine NEON_ENGINE = new NeonEngine();

    static {
        try {
            NEON_ENGINE
                    .run(Querier.class);
            PiLoggerFactory.getDefaultLogConfig()
                    .setLogLevel(LogLevel.INFO);
        } catch (NeonException e) {
            throw new RuntimeException(e);
        }
    }

    public static NeonEngine getNeonEngine() {
        return NEON_ENGINE;
    }

    public static void main(String[] args) throws InvalidArgument, GeneralException, IOException {
        final Querier app = new Querier();
        app.run(args);
    }

    private void run(String[] args) throws InvalidArgument, GeneralException, IOException {
        final ArgsParser argsParser = NEON_ENGINE.makeSureObtainNeon(ArgsParser.class);
        final MsAccessQueryService msAccessQueryService = NEON_ENGINE.makeSureObtainNeon(MsAccessQueryService.class);


        final MsAccessQueryModel msAccessQueryModel = argsParser.parser(args);
        final MsAccessQueryResultModel msAccessQueryResultModel = msAccessQueryService.doQuery(msAccessQueryModel);

        LogHelper.getLogger(this).infoFormat("result of query: %s", msAccessQueryResultModel);

        try (final FileOutputStream fos = new FileOutputStream(msAccessQueryModel.getResultOutputFilePath(), false);
             final BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(msAccessQueryResultModel.toJson().toString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
        }
    }
}
