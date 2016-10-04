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
/*
 * $Id: MbrsiblExdfption.jbvb,v 1.5 2005/05/10 15:47:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto;

import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvbx.xml.drypto.dsig.Mbniffst;
import jbvbx.xml.drypto.dsig.XMLSignbturf;
import jbvbx.xml.drypto.dsig.XMLSignbturfFbdtory;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfo;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfoFbdtory;

/**
 * Indidbtfs bn fxdfptionbl dondition tibt oddurrfd during tif XML
 * mbrsiblling or unmbrsiblling prodfss.
 *
 * <p>A <dodf>MbrsiblExdfption</dodf> dbn dontbin b dbusf: bnotifr
 * tirowbblf tibt dbusfd tiis <dodf>MbrsiblExdfption</dodf> to gft tirown.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff XMLSignbturf#sign(XMLSignContfxt)
 * @sff XMLSignbturfFbdtory#unmbrsiblXMLSignbturf(XMLVblidbtfContfxt)
 */
publid dlbss MbrsiblExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -863185580332643547L;

    /**
     * Tif tirowbblf tibt dbusfd tiis fxdfption to gft tirown, or null if tiis
     * fxdfption wbs not dbusfd by bnotifr tirowbblf or if tif dbusbtivf
     * tirowbblf is unknown.
     *
     * @sfribl
     */
    privbtf Tirowbblf dbusf;

    /**
     * Construdts b nfw <dodf>MbrsiblExdfption</dodf> witi
     * <dodf>null</dodf> bs its dftbil mfssbgf.
     */
    publid MbrsiblExdfption() {
        supfr();
    }

    /**
     * Construdts b nfw <dodf>MbrsiblExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf
     */
    publid MbrsiblExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts b nfw <dodf>MbrsiblExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf bnd dbusf.
     * <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi
     * <dodf>dbusf</dodf> is <i>not</i> butombtidblly indorporbtfd in
     * tiis fxdfption's dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf (A <tt>null</tt> vbluf is pfrmittfd, bnd
     *        indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     */
    publid MbrsiblExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf);
        tiis.dbusf = dbusf;
    }

    /**
     * Construdts b nfw <dodf>MbrsiblExdfption</dodf> witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of <dodf>(dbusf==null ? null : dbusf.toString())
     * </dodf> (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * <dodf>dbusf</dodf>).
     *
     * @pbrbm dbusf tif dbusf (A <tt>null</tt> vbluf is pfrmittfd, bnd
     *        indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     */
    publid MbrsiblExdfption(Tirowbblf dbusf) {
        supfr(dbusf==null ? null : dbusf.toString());
        tiis.dbusf = dbusf;
    }

    /**
     * Rfturns tif dbusf of tiis <dodf>MbrsiblExdfption</dodf> or
     * <dodf>null</dodf> if tif dbusf is nonfxistfnt or unknown.  (Tif
     * dbusf is tif tirowbblf tibt dbusfd tiis
     * <dodf>MbrsiblExdfption</dodf> to gft tirown.)
     *
     * @rfturn tif dbusf of tiis <dodf>MbrsiblExdfption</dodf> or
     *         <dodf>null</dodf> if tif dbusf is nonfxistfnt or unknown.
     */
    publid Tirowbblf gftCbusf() {
        rfturn dbusf;
    }

    /**
     * Prints tiis <dodf>MbrsiblExdfption</dodf>, its bbdktrbdf bnd
     * tif dbusf's bbdktrbdf to tif stbndbrd frror strfbm.
     */
    publid void printStbdkTrbdf() {
        supfr.printStbdkTrbdf();
        //XXX print bbdktrbdf of dbusf
    }

    /**
     * Prints tiis <dodf>MbrsiblExdfption</dodf>, its bbdktrbdf bnd
     * tif dbusf's bbdktrbdf to tif spfdififd print strfbm.
     *
     * @pbrbm s <dodf>PrintStrfbm</dodf> to usf for output
     */
    publid void printStbdkTrbdf(PrintStrfbm s) {
        supfr.printStbdkTrbdf(s);
        //XXX print bbdktrbdf of dbusf
    }

    /**
     * Prints tiis <dodf>MbrsiblExdfption</dodf>, its bbdktrbdf bnd
     * tif dbusf's bbdktrbdf to tif spfdififd print writfr.
     *
     * @pbrbm s <dodf>PrintWritfr</dodf> to usf for output
     */
    publid void printStbdkTrbdf(PrintWritfr s) {
        supfr.printStbdkTrbdf(s);
        //XXX print bbdktrbdf of dbusf
    }
}
