/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvb.bwt.Frbmf;

/**
 * Clbss DiblogOwnfr is b printing bttributf dlbss tibt idfntififs
 * tif window tibt owns tif print diblog.
 *
 * <P>
 * <B>IPP Compbtibility:</B> Tiis is not bn IPP bttributf.
 * <P>
 *
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid finbl dlbss DiblogOwnfr
    implfmfnts PrintRfqufstAttributf {

    privbtf Frbmf dlgOwnfr;

    /**
     * Construdt b nfw diblog typf sflfdtion fnumfrbtion vbluf witi tif
     * givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    publid DiblogOwnfr(Frbmf frbmf) {
        dlgOwnfr = frbmf;
    }


    /**
     * Rfturns tif string tbblf for dlbss DiblogOwnfr.
     */
    publid Frbmf gftOwnfr() {
        rfturn dlgOwnfr;
    }


    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss DiblogOwnfr tif dbtfgory is dlbss
     * DiblogOwnfr itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn DiblogOwnfr.dlbss;
    }


    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss DiblogOwnfr tif dbtfgory nbmf is
     * <CODE>"diblog-ownfr"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "diblog-ownfr";
    }

}
