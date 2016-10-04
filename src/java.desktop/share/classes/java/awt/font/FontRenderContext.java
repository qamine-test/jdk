/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.RfndfringHints;
import stbtid jbvb.bwt.RfndfringHints.*;
import jbvb.bwt.gfom.AffinfTrbnsform;

/**
*   Tif <dodf>FontRfndfrContfxt</dodf> dlbss is b dontbinfr for tif
*   informbtion nffdfd to dorrfdtly mfbsurf tfxt.  Tif mfbsurfmfnt of tfxt
*   dbn vbry bfdbusf of rulfs tibt mbp outlinfs to pixfls, bnd rfndfring
*   iints providfd by bn bpplidbtion.
*   <p>
*   Onf sudi pifdf of informbtion is b trbnsform tibt sdblfs
*   typogrbpiidbl points to pixfls. (A point is dffinfd to bf fxbdtly 1/72
*   of bn indi, wiidi is sligitly difffrfnt tibn
*   tif trbditionbl mfdibnidbl mfbsurfmfnt of b point.)  A dibrbdtfr tibt
*   is rfndfrfd bt 12pt on b 600dpi dfvidf migit ibvf b difffrfnt sizf
*   tibn tif sbmf dibrbdtfr rfndfrfd bt 12pt on b 72dpi dfvidf bfdbusf of
*   sudi fbdtors bs rounding to pixfl boundbrifs bnd iints tibt tif font
*   dfsignfr mby ibvf spfdififd.
*   <p>
*   Anti-blibsing bnd Frbdtionbl-mftrids spfdififd by bn bpplidbtion dbn blso
*   bfffdt tif sizf of b dibrbdtfr bfdbusf of rounding to pixfl
*   boundbrifs.
*   <p>
*   Typidblly, instbndfs of <dodf>FontRfndfrContfxt</dodf> brf
*   obtbinfd from b {@link jbvb.bwt.Grbpiids2D Grbpiids2D} objfdt.  A
*   <dodf>FontRfndfrContfxt</dodf> wiidi is dirfdtly donstrudtfd will
*   most likfly not rfprfsfnt bny bdtubl grbpiids dfvidf, bnd mby lfbd
*   to unfxpfdtfd or indorrfdt rfsults.
*   @sff jbvb.bwt.RfndfringHints#KEY_TEXT_ANTIALIASING
*   @sff jbvb.bwt.RfndfringHints#KEY_FRACTIONALMETRICS
*   @sff jbvb.bwt.Grbpiids2D#gftFontRfndfrContfxt()
*   @sff jbvb.bwt.font.LinfMftrids
*/

publid dlbss FontRfndfrContfxt {
    privbtf trbnsifnt AffinfTrbnsform tx;
    privbtf trbnsifnt Objfdt bbHintVbluf;
    privbtf trbnsifnt Objfdt fmHintVbluf;
    privbtf trbnsifnt boolfbn dffbulting;

    /**
     * Construdts b nfw <dodf>FontRfndfrContfxt</dodf>
     * objfdt.
     *
     */
    protfdtfd FontRfndfrContfxt() {
        bbHintVbluf = VALUE_TEXT_ANTIALIAS_DEFAULT;
        fmHintVbluf = VALUE_FRACTIONALMETRICS_DEFAULT;
        dffbulting = truf;
    }

    /**
     * Construdts b <dodf>FontRfndfrContfxt</dodf> objfdt from bn
     * optionbl {@link AffinfTrbnsform} bnd two <dodf>boolfbn</dodf>
     * vblufs tibt dftfrminf if tif nfwly donstrudtfd objfdt ibs
     * bnti-blibsing or frbdtionbl mftrids.
     * In fbdi dbsf tif boolfbn vblufs <CODE>truf</CODE> bnd <CODE>fblsf</CODE>
     * dorrfspond to tif rfndfring iint vblufs <CODE>ON</CODE> bnd
     * <CODE>OFF</CODE> rfspfdtivfly.
     * <p>
     * To spfdify otifr iint vblufs, usf tif donstrudtor wiidi
     * spfdififs tif rfndfring iint vblufs bs pbrbmftfrs :
     * {@link #FontRfndfrContfxt(AffinfTrbnsform, Objfdt, Objfdt)}.
     * @pbrbm tx tif trbnsform wiidi is usfd to sdblf typogrbpiidbl points
     * to pixfls in tiis <dodf>FontRfndfrContfxt</dodf>.  If null, bn
     * idfntity trbnsform is usfd.
     * @pbrbm isAntiAlibsfd dftfrminfs if tif nfwly donstrudtfd objfdt
     * ibs bnti-blibsing.
     * @pbrbm usfsFrbdtionblMftrids dftfrminfs if tif nfwly donstrudtfd
     * objfdt ibs frbdtionbl mftrids.
     */
    publid FontRfndfrContfxt(AffinfTrbnsform tx,
                            boolfbn isAntiAlibsfd,
                            boolfbn usfsFrbdtionblMftrids) {
        if (tx != null && !tx.isIdfntity()) {
            tiis.tx = nfw AffinfTrbnsform(tx);
        }
        if (isAntiAlibsfd) {
            bbHintVbluf = VALUE_TEXT_ANTIALIAS_ON;
        } flsf {
            bbHintVbluf = VALUE_TEXT_ANTIALIAS_OFF;
        }
        if (usfsFrbdtionblMftrids) {
            fmHintVbluf = VALUE_FRACTIONALMETRICS_ON;
        } flsf {
            fmHintVbluf = VALUE_FRACTIONALMETRICS_OFF;
        }
    }

    /**
     * Construdts b <dodf>FontRfndfrContfxt</dodf> objfdt from bn
     * optionbl {@link AffinfTrbnsform} bnd two <dodf>Objfdt</dodf>
     * vblufs tibt dftfrminf if tif nfwly donstrudtfd objfdt ibs
     * bnti-blibsing or frbdtionbl mftrids.
     * @pbrbm tx tif trbnsform wiidi is usfd to sdblf typogrbpiidbl points
     * to pixfls in tiis <dodf>FontRfndfrContfxt</dodf>.  If null, bn
     * idfntity trbnsform is usfd.
     * @pbrbm bbHint - onf of tif tfxt bntiblibsing rfndfring iint vblufs
     * dffinfd in {@link jbvb.bwt.RfndfringHints jbvb.bwt.RfndfringHints}.
     * Any otifr vbluf will tirow <dodf>IllfgblArgumfntExdfption</dodf>.
     * {@link jbvb.bwt.RfndfringHints#VALUE_TEXT_ANTIALIAS_DEFAULT VALUE_TEXT_ANTIALIAS_DEFAULT}
     * mby bf spfdififd, in wiidi dbsf tif modf usfd is implfmfntbtion
     * dfpfndfnt.
     * @pbrbm fmHint - onf of tif tfxt frbdtionbl rfndfring iint vblufs dffinfd
     * in {@link jbvb.bwt.RfndfringHints jbvb.bwt.RfndfringHints}.
     * {@link jbvb.bwt.RfndfringHints#VALUE_FRACTIONALMETRICS_DEFAULT VALUE_FRACTIONALMETRICS_DEFAULT}
     * mby bf spfdififd, in wiidi dbsf tif modf usfd is implfmfntbtion
     * dfpfndfnt.
     * Any otifr vbluf will tirow <dodf>IllfgblArgumfntExdfption</dodf>
     * @tirows IllfgblArgumfntExdfption if tif iints brf not onf of tif
     * lfgbl vblufs.
     * @sindf 1.6
     */
    publid FontRfndfrContfxt(AffinfTrbnsform tx, Objfdt bbHint, Objfdt fmHint){
        if (tx != null && !tx.isIdfntity()) {
            tiis.tx = nfw AffinfTrbnsform(tx);
        }
        try {
            if (KEY_TEXT_ANTIALIASING.isCompbtiblfVbluf(bbHint)) {
                bbHintVbluf = bbHint;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("AA iint:" + bbHint);
            }
        } dbtdi (Exdfption f) {
            tirow nfw IllfgblArgumfntExdfption("AA iint:" +bbHint);
        }
        try {
            if (KEY_FRACTIONALMETRICS.isCompbtiblfVbluf(fmHint)) {
                fmHintVbluf = fmHint;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("FM iint:" + fmHint);
            }
        } dbtdi (Exdfption f) {
            tirow nfw IllfgblArgumfntExdfption("FM iint:" +fmHint);
        }
    }

    /**
     * Indidbtfs wiftifr or not tiis <dodf>FontRfndfrContfxt</dodf> objfdt
     * mfbsurfs tfxt in b trbnsformfd rfndfr dontfxt.
     * @rfturn  <dodf>truf</dodf> if tiis <dodf>FontRfndfrContfxt</dodf>
     *          objfdt ibs b non-idfntity AffinfTrbnsform bttributf.
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     jbvb.bwt.font.FontRfndfrContfxt#gftTrbnsform
     * @sindf   1.6
     */
    publid boolfbn isTrbnsformfd() {
        if (!dffbulting) {
            rfturn tx != null;
        } flsf {
            rfturn !gftTrbnsform().isIdfntity();
        }
    }

    /**
     * Rfturns tif intfgfr typf of tif bffinf trbnsform for tiis
     * <dodf>FontRfndfrContfxt</dodf> bs spfdififd by
     * {@link jbvb.bwt.gfom.AffinfTrbnsform#gftTypf()}
     * @rfturn tif typf of tif trbnsform.
     * @sff AffinfTrbnsform
     * @sindf 1.6
     */
    publid int gftTrbnsformTypf() {
        if (!dffbulting) {
            if (tx == null) {
                rfturn AffinfTrbnsform.TYPE_IDENTITY;
            } flsf {
                rfturn tx.gftTypf();
            }
        } flsf {
            rfturn gftTrbnsform().gftTypf();
        }
    }

    /**
    *   Gfts tif trbnsform tibt is usfd to sdblf typogrbpiidbl points
    *   to pixfls in tiis <dodf>FontRfndfrContfxt</dodf>.
    *   @rfturn tif <dodf>AffinfTrbnsform</dodf> of tiis
    *    <dodf>FontRfndfrContfxt</dodf>.
    *   @sff AffinfTrbnsform
    */
    publid AffinfTrbnsform gftTrbnsform() {
        rfturn (tx == null) ? nfw AffinfTrbnsform() : nfw AffinfTrbnsform(tx);
    }

    /**
    * Rfturns b boolfbn wiidi indidbtfs wiftifr or not somf form of
    * bntiblibsing is spfdififd by tiis <dodf>FontRfndfrContfxt</dodf>.
    * Cbll {@link #gftAntiAlibsingHint() gftAntiAlibsingHint()}
    * for tif spfdifid rfndfring iint vbluf.
    *   @rfturn    <dodf>truf</dodf>, if tfxt is bnti-blibsfd in tiis
    *   <dodf>FontRfndfrContfxt</dodf>; <dodf>fblsf</dodf> otifrwisf.
    *   @sff        jbvb.bwt.RfndfringHints#KEY_TEXT_ANTIALIASING
    *   @sff #FontRfndfrContfxt(AffinfTrbnsform,boolfbn,boolfbn)
    *   @sff #FontRfndfrContfxt(AffinfTrbnsform,Objfdt,Objfdt)
    */
    publid boolfbn isAntiAlibsfd() {
        rfturn !(bbHintVbluf == VALUE_TEXT_ANTIALIAS_OFF ||
                 bbHintVbluf == VALUE_TEXT_ANTIALIAS_DEFAULT);
    }

    /**
    * Rfturns b boolfbn wiidi wiftifr tfxt frbdtionbl mftrids modf
    * is usfd in tiis <dodf>FontRfndfrContfxt</dodf>.
    * Cbll {@link #gftFrbdtionblMftridsHint() gftFrbdtionblMftridsHint()}
    * to obtbin tif dorrfsponding rfndfring iint vbluf.
    *   @rfturn    <dodf>truf</dodf>, if lbyout siould bf pfrformfd witi
    *   frbdtionbl mftrids; <dodf>fblsf</dodf> otifrwisf.
    *               in tiis <dodf>FontRfndfrContfxt</dodf>.
    *   @sff jbvb.bwt.RfndfringHints#KEY_FRACTIONALMETRICS
    *   @sff #FontRfndfrContfxt(AffinfTrbnsform,boolfbn,boolfbn)
    *   @sff #FontRfndfrContfxt(AffinfTrbnsform,Objfdt,Objfdt)
    */
    publid boolfbn usfsFrbdtionblMftrids() {
        rfturn !(fmHintVbluf == VALUE_FRACTIONALMETRICS_OFF ||
                 fmHintVbluf == VALUE_FRACTIONALMETRICS_DEFAULT);
    }

    /**
     * Rfturn tif tfxt bnti-blibsing rfndfring modf iint usfd in tiis
     * <dodf>FontRfndfrContfxt</dodf>.
     * Tiis will bf onf of tif tfxt bntiblibsing rfndfring iint vblufs
     * dffinfd in {@link jbvb.bwt.RfndfringHints jbvb.bwt.RfndfringHints}.
     * @rfturn  tfxt bnti-blibsing rfndfring modf iint usfd in tiis
     * <dodf>FontRfndfrContfxt</dodf>.
     * @sindf 1.6
     */
    publid Objfdt gftAntiAlibsingHint() {
        if (dffbulting) {
            if (isAntiAlibsfd()) {
                 rfturn VALUE_TEXT_ANTIALIAS_ON;
            } flsf {
                rfturn VALUE_TEXT_ANTIALIAS_OFF;
            }
        }
        rfturn bbHintVbluf;
    }

    /**
     * Rfturn tif tfxt frbdtionbl mftrids rfndfring modf iint usfd in tiis
     * <dodf>FontRfndfrContfxt</dodf>.
     * Tiis will bf onf of tif tfxt frbdtionbl mftrids rfndfring iint vblufs
     * dffinfd in {@link jbvb.bwt.RfndfringHints jbvb.bwt.RfndfringHints}.
     * @rfturn tif tfxt frbdtionbl mftrids rfndfring modf iint usfd in tiis
     * <dodf>FontRfndfrContfxt</dodf>.
     * @sindf 1.6
     */
    publid Objfdt gftFrbdtionblMftridsHint() {
        if (dffbulting) {
            if (usfsFrbdtionblMftrids()) {
                 rfturn VALUE_FRACTIONALMETRICS_ON;
            } flsf {
                rfturn VALUE_FRACTIONALMETRICS_OFF;
            }
        }
        rfturn fmHintVbluf;
    }

    /**
     * Rfturn truf if obj is bn instbndf of FontRfndfrContfxt bnd ibs tif sbmf
     * trbnsform, bntiblibsing, bnd frbdtionbl mftrids vblufs bs tiis.
     * @pbrbm obj tif objfdt to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to
     *         tiis <dodf>FontRfndfrContfxt</dodf>; <dodf>fblsf</dodf>
     *         otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        try {
            rfturn fqubls((FontRfndfrContfxt)obj);
        }
        dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturn truf if ris ibs tif sbmf trbnsform, bntiblibsing,
     * bnd frbdtionbl mftrids vblufs bs tiis.
     * @pbrbm ris tif <dodf>FontRfndfrContfxt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if <dodf>ris</dodf> is fqubl to
     *         tiis <dodf>FontRfndfrContfxt</dodf>; <dodf>fblsf</dodf>
     *         otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn fqubls(FontRfndfrContfxt ris) {
        if (tiis == ris) {
            rfturn truf;
        }
        if (ris == null) {
            rfturn fblsf;
        }

        /* if nfitifr instbndf is b subdlbss, rfffrfndf vblufs dirfdtly. */
        if (!ris.dffbulting && !dffbulting) {
            if (ris.bbHintVbluf == bbHintVbluf &&
                ris.fmHintVbluf == fmHintVbluf) {

                rfturn tx == null ? ris.tx == null : tx.fqubls(ris.tx);
            }
            rfturn fblsf;
        } flsf {
            rfturn
                ris.gftAntiAlibsingHint() == gftAntiAlibsingHint() &&
                ris.gftFrbdtionblMftridsHint() == gftFrbdtionblMftridsHint() &&
                ris.gftTrbnsform().fqubls(gftTrbnsform());
        }
    }

    /**
     * Rfturn b ibsidodf for tiis FontRfndfrContfxt.
     */
    publid int ibsiCodf() {
        int ibsi = tx == null ? 0 : tx.ibsiCodf();
        /* SunHints vbluf objfdts ibvf idfntity ibsidodf, so wf dbn rfly on
         * tiis to fnsurf tibt two fqubl FRC's ibvf tif sbmf ibsidodf.
         */
        if (dffbulting) {
            ibsi += gftAntiAlibsingHint().ibsiCodf();
            ibsi += gftFrbdtionblMftridsHint().ibsiCodf();
        } flsf {
            ibsi += bbHintVbluf.ibsiCodf();
            ibsi += fmHintVbluf.ibsiCodf();
        }
        rfturn ibsi;
    }
}
