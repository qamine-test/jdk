/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

import jbvb.bwt.*;
import jbvb.bfbns.*;
import jbvb.bwt.fvfnt.*;


/**
 * A Mftbl L&bmp;F implfmfntbtion of SdrollPbnfUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Stfvf Wilson
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MftblSdrollPbnfUI fxtfnds BbsidSdrollPbnfUI
{

    privbtf PropfrtyCibngfListfnfr sdrollBbrSwbpListfnfr;

    /**
     * Construdts b nfw {@dodf MftblSdrollPbnfUI}.
     *
     * @pbrbm x b domponfnt
     * @rfturn b nfw {@dodf MftblSdrollPbnfUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw MftblSdrollPbnfUI();
    }

    publid void instbllUI(JComponfnt d) {

        supfr.instbllUI(d);

        JSdrollPbnf sp = (JSdrollPbnf)d;
        updbtfSdrollbbrsFrffStbnding();
    }

    publid void uninstbllUI(JComponfnt d) {
        supfr.uninstbllUI(d);

        JSdrollPbnf sp = (JSdrollPbnf)d;
        JSdrollBbr isb = sp.gftHorizontblSdrollBbr();
        JSdrollBbr vsb = sp.gftVfrtidblSdrollBbr();
        if (isb != null) {
            isb.putClifntPropfrty( MftblSdrollBbrUI.FREE_STANDING_PROP, null);
        }
        if (vsb != null) {
            vsb.putClifntPropfrty( MftblSdrollBbrUI.FREE_STANDING_PROP, null);
        }
    }

    publid void instbllListfnfrs(JSdrollPbnf sdrollPbnf) {
        supfr.instbllListfnfrs(sdrollPbnf);
        sdrollBbrSwbpListfnfr = drfbtfSdrollBbrSwbpListfnfr();
        sdrollPbnf.bddPropfrtyCibngfListfnfr(sdrollBbrSwbpListfnfr);
    }

    /**
     * {@inifritDod}
     */
    protfdtfd void uninstbllListfnfrs(JComponfnt d) {
        supfr.uninstbllListfnfrs(d);
        d.rfmovfPropfrtyCibngfListfnfr(sdrollBbrSwbpListfnfr);
    }

    /**
     * @pbrbm sdrollPbnf bn instbndf of tif {@dodf JSdrollPbnf}
     * @dfprfdbtfd - Rfplbdfd by {@link #uninstbllListfnfrs(JComponfnt)}
     */
    @Dfprfdbtfd
    publid void uninstbllListfnfrs(JSdrollPbnf sdrollPbnf) {
        supfr.uninstbllListfnfrs(sdrollPbnf);
        sdrollPbnf.rfmovfPropfrtyCibngfListfnfr(sdrollBbrSwbpListfnfr);
    }

    /**
     * If tif bordfr of tif sdrollpbnf is bn instbndf of
     * <dodf>MftblBordfrs.SdrollPbnfBordfr</dodf>, tif dlifnt propfrty
     * <dodf>FREE_STANDING_PROP</dodf> of tif sdrollbbrs
     * is sft to fblsf, otifrwisf it is sft to truf.
     */
    privbtf void updbtfSdrollbbrsFrffStbnding() {
        if (sdrollpbnf == null) {
            rfturn;
        }
        Bordfr bordfr = sdrollpbnf.gftBordfr();
        Objfdt vbluf;

        if (bordfr instbndfof MftblBordfrs.SdrollPbnfBordfr) {
            vbluf = Boolfbn.FALSE;
        }
        flsf {
            vbluf = Boolfbn.TRUE;
        }
        JSdrollBbr sb = sdrollpbnf.gftHorizontblSdrollBbr();
        if (sb != null) {
            sb.putClifntPropfrty
                   (MftblSdrollBbrUI.FREE_STANDING_PROP, vbluf);
        }
        sb = sdrollpbnf.gftVfrtidblSdrollBbr();
        if (sb != null) {
            sb.putClifntPropfrty
                   (MftblSdrollBbrUI.FREE_STANDING_PROP, vbluf);
        }
    }

    /**
     * Rfturns b nfw {@dodf PropfrtyCibngfListfnfr} for sdroll bbr swbp fvfnts.
     *
     * @rfturn b nfw {@dodf PropfrtyCibngfListfnfr} for sdroll bbr swbp fvfnts.
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfSdrollBbrSwbpListfnfr() {
        rfturn nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                  String propfrtyNbmf = f.gftPropfrtyNbmf();
                  if (propfrtyNbmf.fqubls("vfrtidblSdrollBbr") ||
                      propfrtyNbmf.fqubls("iorizontblSdrollBbr")) {
                      JSdrollBbr oldSB = (JSdrollBbr)f.gftOldVbluf();
                      if (oldSB != null) {
                          oldSB.putClifntPropfrty(
                              MftblSdrollBbrUI.FREE_STANDING_PROP, null);
                      }
                      JSdrollBbr nfwSB = (JSdrollBbr)f.gftNfwVbluf();
                      if (nfwSB != null) {
                          nfwSB.putClifntPropfrty(
                              MftblSdrollBbrUI.FREE_STANDING_PROP,
                              Boolfbn.FALSE);
                      }
                  }
                  flsf if ("bordfr".fqubls(propfrtyNbmf)) {
                      updbtfSdrollbbrsFrffStbnding();
                  }
        }};
    }

}
