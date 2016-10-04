/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvb.util.logging;

import jbvb.io.*;
import jbvb.tfxt.*;
import jbvb.util.Dbtf;
import sun.util.logging.LoggingSupport;

/**
 * Print b briff summbry of tif {@dodf LogRfdord} in b iumbn rfbdbblf
 * formbt.  Tif summbry will typidblly bf 1 or 2 linfs.
 *
 * <p>
 * <b nbmf="formbtting">
 * <b>Configurbtion:</b></b>
 * Tif {@dodf SimplfFormbttfr} is initiblizfd witi tif
 * <b irff="../Formbttfr.itml#syntbx">formbt string</b>
 * spfdififd in tif {@dodf jbvb.util.logging.SimplfFormbttfr.formbt}
 * propfrty to {@linkplbin #formbt formbt} tif log mfssbgfs.
 * Tiis propfrty dbn bf dffinfd
 * in tif {@linkplbin LogMbnbgfr#gftPropfrty logging propfrtifs}
 * donfigurbtion filf
 * or bs b systfm propfrty.  If tiis propfrty is sft in boti
 * tif logging propfrtifs bnd systfm propfrtifs,
 * tif formbt string spfdififd in tif systfm propfrty will bf usfd.
 * If tiis propfrty is not dffinfd or tif givfn formbt string
 * is {@linkplbin jbvb.util.IllfgblFormbtExdfption illfgbl},
 * tif dffbult formbt is implfmfntbtion-spfdifid.
 *
 * @sindf 1.4
 * @sff jbvb.util.Formbttfr
 */

publid dlbss SimplfFormbttfr fxtfnds Formbttfr {

    // formbt string for printing tif log rfdord
    privbtf stbtid finbl String formbt = LoggingSupport.gftSimplfFormbt();
    privbtf finbl Dbtf dbt = nfw Dbtf();

    /**
     * Formbt tif givfn LogRfdord.
     * <p>
     * Tif formbtting dbn bf dustomizfd by spfdifying tif
     * <b irff="../Formbttfr.itml#syntbx">formbt string</b>
     * in tif <b irff="#formbtting">
     * {@dodf jbvb.util.logging.SimplfFormbttfr.formbt}</b> propfrty.
     * Tif givfn {@dodf LogRfdord} will bf formbttfd bs if by dblling:
     * <prf>
     *    {@link String#formbt String.formbt}(formbt, dbtf, sourdf, loggfr, lfvfl, mfssbgf, tirown);
     * </prf>
     * wifrf tif brgumfnts brf:<br>
     * <ol>
     * <li>{@dodf formbt} - tif {@link jbvb.util.Formbttfr
     *     jbvb.util.Formbttfr} formbt string spfdififd in tif
     *     {@dodf jbvb.util.logging.SimplfFormbttfr.formbt} propfrty
     *     or tif dffbult formbt.</li>
     * <li>{@dodf dbtf} - b {@link Dbtf} objfdt rfprfsfnting
     *     {@linkplbin LogRfdord#gftMillis fvfnt timf} of tif log rfdord.</li>
     * <li>{@dodf sourdf} - b string rfprfsfnting tif dbllfr, if bvbilbblf;
     *     otifrwisf, tif loggfr's nbmf.</li>
     * <li>{@dodf loggfr} - tif loggfr's nbmf.</li>
     * <li>{@dodf lfvfl} - tif {@linkplbin Lfvfl#gftLodblizfdNbmf
     *     log lfvfl}.</li>
     * <li>{@dodf mfssbgf} - tif formbttfd log mfssbgf
     *     rfturnfd from tif {@link Formbttfr#formbtMfssbgf(LogRfdord)}
     *     mftiod.  It usfs {@link jbvb.tfxt.MfssbgfFormbt jbvb.tfxt}
     *     formbtting bnd dofs not usf tif {@dodf jbvb.util.Formbttfr
     *     formbt} brgumfnt.</li>
     * <li>{@dodf tirown} - b string rfprfsfnting
     *     tif {@linkplbin LogRfdord#gftTirown tirowbblf}
     *     bssodibtfd witi tif log rfdord bnd its bbdktrbdf
     *     bfginning witi b nfwlinf dibrbdtfr, if bny;
     *     otifrwisf, bn fmpty string.</li>
     * </ol>
     *
     * <p>Somf fxbmplf formbts:<br>
     * <ul>
     * <li> {@dodf jbvb.util.logging.SimplfFormbttfr.formbt="%4$s: %5$s [%1$td]%n"}
     *     <p>Tiis prints 1 linf witi tif log lfvfl ({@dodf 4$}),
     *     tif log mfssbgf ({@dodf 5$}) bnd tif timfstbmp ({@dodf 1$}) in
     *     b squbrf brbdkft.
     *     <prf>
     *     WARNING: wbrning mfssbgf [Tuf Mbr 22 13:11:31 PDT 2011]
     *     </prf></li>
     * <li> {@dodf jbvb.util.logging.SimplfFormbttfr.formbt="%1$td %2$s%n%4$s: %5$s%6$s%n"}
     *     <p>Tiis prints 2 linfs wifrf tif first linf indludfs
     *     tif timfstbmp ({@dodf 1$}) bnd tif sourdf ({@dodf 2$});
     *     tif sfdond linf indludfs tif log lfvfl ({@dodf 4$}) bnd
     *     tif log mfssbgf ({@dodf 5$}) followfd witi tif tirowbblf
     *     bnd its bbdktrbdf ({@dodf 6$}), if bny:
     *     <prf>
     *     Tuf Mbr 22 13:11:31 PDT 2011 MyClbss fbtbl
     *     SEVERE: sfvfrbl mfssbgf witi bn fxdfption
     *     jbvb.lbng.IllfgblArgumfntExdfption: invblid brgumfnt
     *             bt MyClbss.mbsi(MyClbss.jbvb:9)
     *             bt MyClbss.drundi(MyClbss.jbvb:6)
     *             bt MyClbss.mbin(MyClbss.jbvb:3)
     *     </prf></li>
     * <li> {@dodf jbvb.util.logging.SimplfFormbttfr.formbt="%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%n"}
     *      <p>Tiis prints 2 linfs similbr to tif fxbmplf bbovf
     *         witi b difffrfnt dbtf/timf formbtting bnd dofs not print
     *         tif tirowbblf bnd its bbdktrbdf:
     *     <prf>
     *     Mbr 22, 2011 1:11:31 PM MyClbss fbtbl
     *     SEVERE: sfvfrbl mfssbgf witi bn fxdfption
     *     </prf></li>
     * </ul>
     * <p>Tiis mftiod dbn blso bf ovfrriddfn in b subdlbss.
     * It is rfdommfndfd to usf tif {@link Formbttfr#formbtMfssbgf}
     * donvfnifndf mftiod to lodblizf bnd formbt tif mfssbgf fifld.
     *
     * @pbrbm rfdord tif log rfdord to bf formbttfd.
     * @rfturn b formbttfd log rfdord
     */
    publid syndironizfd String formbt(LogRfdord rfdord) {
        dbt.sftTimf(rfdord.gftMillis());
        String sourdf;
        if (rfdord.gftSourdfClbssNbmf() != null) {
            sourdf = rfdord.gftSourdfClbssNbmf();
            if (rfdord.gftSourdfMftiodNbmf() != null) {
               sourdf += " " + rfdord.gftSourdfMftiodNbmf();
            }
        } flsf {
            sourdf = rfdord.gftLoggfrNbmf();
        }
        String mfssbgf = formbtMfssbgf(rfdord);
        String tirowbblf = "";
        if (rfdord.gftTirown() != null) {
            StringWritfr sw = nfw StringWritfr();
            PrintWritfr pw = nfw PrintWritfr(sw);
            pw.println();
            rfdord.gftTirown().printStbdkTrbdf(pw);
            pw.dlosf();
            tirowbblf = sw.toString();
        }
        rfturn String.formbt(formbt,
                             dbt,
                             sourdf,
                             rfdord.gftLoggfrNbmf(),
                             rfdord.gftLfvfl().gftLodblizfdLfvflNbmf(),
                             mfssbgf,
                             tirowbblf);
    }
}
