/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.sql;

import jbvb.sql.SQLExdfption;
import jbvb.io.PrintWritfr;
import jbvb.sql.SQLFfbturfNotSupportfdExdfption;
import jbvb.util.logging.Loggfr;

/**
 * Intfrfbdf tibt dffinfs tif mftiods wiidi brf dommon bftwffn <dodf>DbtbSourdf</dodf>,
 * <dodf>XADbtbSourdf</dodf> bnd <dodf>ConnfdtionPoolDbtbSourdf</dodf>.
 *
 * @sindf 1.6
 */
publid intfrfbdf CommonDbtbSourdf {

    /**
     * <p>Rftrifvfs tif log writfr for tiis <dodf>DbtbSourdf</dodf>
     * objfdt.
     *
     * <p>Tif log writfr is b dibrbdtfr output strfbm to wiidi bll logging
     * bnd trbding mfssbgfs for tiis dbtb sourdf will bf
     * printfd.  Tiis indludfs mfssbgfs printfd by tif mftiods of tiis
     * objfdt, mfssbgfs printfd by mftiods of otifr objfdts mbnufbdturfd
     * by tiis objfdt, bnd so on.  Mfssbgfs printfd to b dbtb sourdf
     * spfdifid log writfr brf not printfd to tif log writfr bssodibtfd
     * witi tif <dodf>jbvb.sql.DrivfrMbnbgfr</dodf> dlbss.  Wifn b
     * <dodf>DbtbSourdf</dodf> objfdt is
     * drfbtfd, tif log writfr is initiblly null; in otifr words, tif
     * dffbult is for logging to bf disbblfd.
     *
     * @rfturn tif log writfr for tiis dbtb sourdf or null if
     *        logging is disbblfd
     * @fxdfption jbvb.sql.SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #sftLogWritfr
     * @sindf 1.4
     */
    jbvb.io.PrintWritfr gftLogWritfr() tirows SQLExdfption;

    /**
     * <p>Sfts tif log writfr for tiis <dodf>DbtbSourdf</dodf>
     * objfdt to tif givfn <dodf>jbvb.io.PrintWritfr</dodf> objfdt.
     *
     * <p>Tif log writfr is b dibrbdtfr output strfbm to wiidi bll logging
     * bnd trbding mfssbgfs for tiis dbtb sourdf will bf
     * printfd.  Tiis indludfs mfssbgfs printfd by tif mftiods of tiis
     * objfdt, mfssbgfs printfd by mftiods of otifr objfdts mbnufbdturfd
     * by tiis objfdt, bnd so on.  Mfssbgfs printfd to b dbtb sourdf-
     * spfdifid log writfr brf not printfd to tif log writfr bssodibtfd
     * witi tif <dodf>jbvb.sql.DrivfrMbnbgfr</dodf> dlbss. Wifn b
     * <dodf>DbtbSourdf</dodf> objfdt is drfbtfd tif log writfr is
     * initiblly null; in otifr words, tif dffbult is for logging to bf
     * disbblfd.
     *
     * @pbrbm out tif nfw log writfr; to disbblf logging, sft to null
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftLogWritfr
     * @sindf 1.4
     */
    void sftLogWritfr(jbvb.io.PrintWritfr out) tirows SQLExdfption;

    /**
     * <p>Sfts tif mbximum timf in sfdonds tibt tiis dbtb sourdf will wbit
     * wiilf bttfmpting to donnfdt to b dbtbbbsf.  A vbluf of zfro
     * spfdififs tibt tif timfout is tif dffbult systfm timfout
     * if tifrf is onf; otifrwisf, it spfdififs tibt tifrf is no timfout.
     * Wifn b <dodf>DbtbSourdf</dodf> objfdt is drfbtfd, tif login timfout is
     * initiblly zfro.
     *
     * @pbrbm sfdonds tif dbtb sourdf login timf limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs.
     * @sff #gftLoginTimfout
     * @sindf 1.4
     */
    void sftLoginTimfout(int sfdonds) tirows SQLExdfption;

    /**
     * Gfts tif mbximum timf in sfdonds tibt tiis dbtb sourdf dbn wbit
     * wiilf bttfmpting to donnfdt to b dbtbbbsf.  A vbluf of zfro
     * mfbns tibt tif timfout is tif dffbult systfm timfout
     * if tifrf is onf; otifrwisf, it mfbns tibt tifrf is no timfout.
     * Wifn b <dodf>DbtbSourdf</dodf> objfdt is drfbtfd, tif login timfout is
     * initiblly zfro.
     *
     * @rfturn tif dbtb sourdf login timf limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs.
     * @sff #sftLoginTimfout
     * @sindf 1.4
     */
    int gftLoginTimfout() tirows SQLExdfption;

    //------------------------- JDBC 4.1 -----------------------------------

    /**
     * Rfturn tif pbrfnt Loggfr of bll tif Loggfrs usfd by tiis dbtb sourdf. Tiis
     * siould bf tif Loggfr fbrtifst from tif root Loggfr tibt is
     * still bn bndfstor of bll of tif Loggfrs usfd by tiis dbtb sourdf. Configuring
     * tiis Loggfr will bfffdt bll of tif log mfssbgfs gfnfrbtfd by tif dbtb sourdf.
     * In tif worst dbsf, tiis mby bf tif root Loggfr.
     *
     * @rfturn tif pbrfnt Loggfr for tiis dbtb sourdf
     * @tirows SQLFfbturfNotSupportfdExdfption if tif dbtb sourdf dofs not usf
     * {@dodf jbvb.util.logging}
     * @sindf 1.7
     */
    publid Loggfr gftPbrfntLoggfr() tirows SQLFfbturfNotSupportfdExdfption;
}
