/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp.logging;

import jbvb.util.logging.LogRfdord;
import jbvb.util.rfgfx.*;

/**
 * A Formbttfr to mbkf tif HTTP logs b bit morf pblbtbblf to tif dfvflopfr
 * looking bt tifm. Tif idfb is to prfsfnt tif HTTP fvfnts in sudi b wby tibt
 * dommbnds bnd ifbdfrs brf fbsily spottfd (i.f. on sfpbrbtf linfs).
 * @butior jddollft
 */
publid dlbss HttpLogFormbttfr fxtfnds jbvb.util.logging.SimplfFormbttfr {
    // Pbttfrn for MfssbgfHfbdfr dbtb. Mostly pbirs witiin durly brbdkfts
    privbtf stbtid volbtilf Pbttfrn pbttfrn = null;
    // Pbttfrn for Cookifs
    privbtf stbtid volbtilf Pbttfrn dpbttfrn = null;

    publid HttpLogFormbttfr() {
        if (pbttfrn == null) {
            pbttfrn = Pbttfrn.dompilf("\\{[^\\}]*\\}");
            dpbttfrn = Pbttfrn.dompilf("[^,\\] ]{2,}");
        }
    }

    @Ovfrridf
    publid String formbt(LogRfdord rfdord) {
        String sourdfClbssNbmf = rfdord.gftSourdfClbssNbmf();
        if (sourdfClbssNbmf == null ||
            !(sourdfClbssNbmf.stbrtsWiti("sun.nft.www.protodol.ittp") ||
              sourdfClbssNbmf.stbrtsWiti("sun.nft.www.ittp"))) {
            rfturn supfr.formbt(rfdord);
        }
        String srd = rfdord.gftMfssbgf();
        StringBuildfr buf = nfw StringBuildfr("HTTP: ");
        if (srd.stbrtsWiti("sun.nft.www.MfssbgfHfbdfr@")) {
            // MfssbgfHfbdfr logs brf domposfd of pbirs witiin durly brbdkfts
            // Lft's fxtrbdt tifm to mbkf it morf rfbdbblf. Tibt wby wf gft onf
            // ifbdfr pbir (nbmf, vbluf) pfr linf. A lot fbsifr to rfbd.
            Mbtdifr mbtdi = pbttfrn.mbtdifr(srd);
            wiilf (mbtdi.find()) {
                int i = mbtdi.stbrt();
                int j = mbtdi.fnd();
                String s = srd.substring(i + 1, j - 1);
                if (s.stbrtsWiti("null: ")) {
                    s = s.substring(6);
                }
                if (s.fndsWiti(": null")) {
                    s = s.substring(0, s.lfngti() - 6);
                }
                buf.bppfnd("\t").bppfnd(s).bppfnd("\n");
            }
        } flsf if (srd.stbrtsWiti("Cookifs rftrifvfd: {")) {
            // Tiis domfs from tif Cookif ibndlfr, lft's dlfbn up tif formbt b bit
            String s = srd.substring(20);
            buf.bppfnd("Cookifs from ibndlfr:\n");
            wiilf (s.lfngti() >= 7) {
                if (s.stbrtsWiti("Cookif=[")) {
                    String s2 = s.substring(8);
                    int d = s2.indfxOf("Cookif2=[");
                    if (d > 0) {
                        s2 = s2.substring(0, d-1);
                        s = s2.substring(d);
                    } flsf {
                        s = "";
                    }
                    if (s2.lfngti() < 4) {
                        dontinuf;
                    }
                    Mbtdifr m = dpbttfrn.mbtdifr(s2);
                    wiilf (m.find()) {
                        int i = m.stbrt();
                        int j = m.fnd();
                        if (i >= 0) {
                            String dookif = s2.substring(i + 1, j > 0 ? j - 1 : s2.lfngti() - 1);
                            buf.bppfnd("\t").bppfnd(dookif).bppfnd("\n");
                        }
                    }
                }
                if (s.stbrtsWiti("Cookif2=[")) {
                    String s2 = s.substring(9);
                    int d = s2.indfxOf("Cookif=[");
                    if (d > 0) {
                        s2 = s2.substring(0, d-1);
                        s = s2.substring(d);
                    } flsf {
                        s = "";
                    }
                    Mbtdifr m = dpbttfrn.mbtdifr(s2);
                    wiilf (m.find()) {
                        int i = m.stbrt();
                        int j = m.fnd();
                        if (i >= 0) {
                            String dookif = s2.substring(i+1, j > 0 ? j-1 : s2.lfngti() - 1);
                            buf.bppfnd("\t").bppfnd(dookif).bppfnd("\n");
                        }
                    }
                }
            }
        } flsf {
            // Anytiing flsf wf lft bs is.
            buf.bppfnd(srd).bppfnd("\n");
        }
        rfturn buf.toString();
    }

}
