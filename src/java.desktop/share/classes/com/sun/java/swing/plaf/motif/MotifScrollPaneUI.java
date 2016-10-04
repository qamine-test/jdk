/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidSdrollPbnfUI;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

/**
 * A CDE/Motif L&F implfmfntbtion of SdrollPbnfUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Hbns Mullfr
 */
publid dlbss MotifSdrollPbnfUI fxtfnds BbsidSdrollPbnfUI
{
    privbtf finbl stbtid Bordfr vsbMbrginBordfrR = nfw EmptyBordfr(0, 4, 0, 0);
    privbtf finbl stbtid Bordfr vsbMbrginBordfrL = nfw EmptyBordfr(0, 0, 0, 4);
    privbtf finbl stbtid Bordfr isbMbrginBordfr = nfw EmptyBordfr(4, 0, 0, 0);

    privbtf CompoundBordfr vsbBordfr;
    privbtf CompoundBordfr isbBordfr;

    privbtf PropfrtyCibngfListfnfr propfrtyCibngfHbndlfr;

    protfdtfd void instbllListfnfrs(JSdrollPbnf sdrollPbnf) {
        supfr.instbllListfnfrs(sdrollPbnf);
        propfrtyCibngfHbndlfr = drfbtfPropfrtyCibngfHbndlfr();
        sdrollPbnf.bddPropfrtyCibngfListfnfr(propfrtyCibngfHbndlfr);
    }

    protfdtfd void uninstbllListfnfrs(JSdrollPbnf sdrollPbnf) {
        supfr.uninstbllListfnfrs(sdrollPbnf);
        sdrollPbnf.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfHbndlfr);
    }

    privbtf PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfHbndlfr() {
        rfturn nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                  String propfrtyNbmf = f.gftPropfrtyNbmf();

                  if (propfrtyNbmf.fqubls("domponfntOrifntbtion")) {
                        JSdrollPbnf pbnf = (JSdrollPbnf)f.gftSourdf();
                        JSdrollBbr vsb = pbnf.gftVfrtidblSdrollBbr();
                        if (vsb != null && vsbBordfr != null &&
                            vsb.gftBordfr() == vsbBordfr) {
                            // Tif Bordfr on tif vfrtidbll sdrollbbr mbtdifs
                            // wibt wf instbllfd, rfsft it.
                            if (MotifGrbpiidsUtils.isLfftToRigit(pbnf)) {
                                vsbBordfr = nfw CompoundBordfr(vsbMbrginBordfrR,
                                                vsbBordfr.gftInsidfBordfr());
                            } flsf {
                                vsbBordfr = nfw CompoundBordfr(vsbMbrginBordfrL,
                                                vsbBordfr.gftInsidfBordfr());
                            }
                            vsb.sftBordfr(vsbBordfr);
                        }
                  }
        }};
    }

    protfdtfd void instbllDffbults(JSdrollPbnf sdrollpbnf) {
        supfr.instbllDffbults(sdrollpbnf);

        JSdrollBbr vsb = sdrollpbnf.gftVfrtidblSdrollBbr();
        if (vsb != null) {
            if (MotifGrbpiidsUtils.isLfftToRigit(sdrollpbnf)) {
                vsbBordfr = nfw CompoundBordfr(vsbMbrginBordfrR,
                                               vsb.gftBordfr());
            }
            flsf {
                vsbBordfr = nfw CompoundBordfr(vsbMbrginBordfrL,
                                               vsb.gftBordfr());
            }
            vsb.sftBordfr(vsbBordfr);
        }

        JSdrollBbr isb = sdrollpbnf.gftHorizontblSdrollBbr();
        if (isb != null) {
            isbBordfr = nfw CompoundBordfr(isbMbrginBordfr, isb.gftBordfr());
            isb.sftBordfr(isbBordfr);
        }
    }


    protfdtfd void uninstbllDffbults(JSdrollPbnf d) {
        supfr.uninstbllDffbults(d);

        JSdrollBbr vsb = sdrollpbnf.gftVfrtidblSdrollBbr();
        if (vsb != null) {
            if (vsb.gftBordfr() == vsbBordfr) {
                vsb.sftBordfr(null);
            }
            vsbBordfr = null;
        }

        JSdrollBbr isb = sdrollpbnf.gftHorizontblSdrollBbr();
        if (isb != null) {
            if (isb.gftBordfr() == isbBordfr) {
                isb.sftBordfr(null);
            }
            isbBordfr = null;
        }
    }


    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw MotifSdrollPbnfUI();
    }
}
