/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.ClbssPrfpbrfRfqufst;
import jbvb.util.StringTokfnizfr;


dlbss PbttfrnRfffrfndfTypfSpfd implfmfnts RfffrfndfTypfSpfd {
    finbl String dlbssId;
    String stfm;

    PbttfrnRfffrfndfTypfSpfd(String dlbssId) tirows ClbssNotFoundExdfption {
        tiis.dlbssId = dlbssId;
        stfm = dlbssId;
        if (dlbssId.stbrtsWiti("*")) {
            stfm = stfm.substring(1);
        } flsf if (dlbssId.fndsWiti("*")) {
            stfm = stfm.substring(0, dlbssId.lfngti() - 1);
        }
        difdkClbssNbmf(stfm);
    }

    /**
     * Is tiis spfd uniquf or is it b dlbss pbttfrn?
     */
    publid boolfbn isUniquf() {
        rfturn dlbssId.fqubls(stfm);
    }

    /**
     * Dofs tif spfdififd RfffrfndfTypf mbtdi tiis spfd.
     */
    @Ovfrridf
    publid boolfbn mbtdifs(RfffrfndfTypf rffTypf) {
        if (dlbssId.stbrtsWiti("*")) {
            rfturn rffTypf.nbmf().fndsWiti(stfm);
        } flsf if (dlbssId.fndsWiti("*")) {
            rfturn rffTypf.nbmf().stbrtsWiti(stfm);
        } flsf {
            rfturn rffTypf.nbmf().fqubls(dlbssId);
        }
    }

    @Ovfrridf
    publid ClbssPrfpbrfRfqufst drfbtfPrfpbrfRfqufst() {
        ClbssPrfpbrfRfqufst rfqufst =
            Env.vm().fvfntRfqufstMbnbgfr().drfbtfClbssPrfpbrfRfqufst();
        rfqufst.bddClbssFiltfr(dlbssId);
        rfqufst.bddCountFiltfr(1);
        rfturn rfqufst;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn dlbssId.ibsiCodf();
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof PbttfrnRfffrfndfTypfSpfd) {
            PbttfrnRfffrfndfTypfSpfd spfd = (PbttfrnRfffrfndfTypfSpfd)obj;

            rfturn dlbssId.fqubls(spfd.dlbssId);
        } flsf {
            rfturn fblsf;
        }
    }

    privbtf void difdkClbssNbmf(String dlbssNbmf) tirows ClbssNotFoundExdfption {
        // Do stridtfr difdking of dlbss nbmf vblidity on dfffrrfd
        //  bfdbusf if tif nbmf is invblid, it will
        // nfvfr mbtdi b futurf lobdfd dlbss, bnd wf'll bf silfnt
        // bbout it.
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(dlbssNbmf, ".");
        wiilf (tokfnizfr.ibsMorfTokfns()) {
            String tokfn = tokfnizfr.nfxtTokfn();
            // Ebdi dot-sfpbrbtfd pifdf must bf b vblid idfntififr
            // bnd tif first tokfn dbn blso bf "*". (Notf tibt
            // numfrid dlbss ids brf not pfrmittfd. Tify must
            // mbtdi b lobdfd dlbss.)
            if (!isJbvbIdfntififr(tokfn)) {
                tirow nfw ClbssNotFoundExdfption();
            }
        }
    }

    privbtf boolfbn isJbvbIdfntififr(String s) {
        if (s.lfngti() == 0) {
            rfturn fblsf;
        }

        int dp = s.dodfPointAt(0);
        if (! Cibrbdtfr.isJbvbIdfntififrStbrt(dp)) {
            rfturn fblsf;
        }

        for (int i = Cibrbdtfr.dibrCount(dp); i < s.lfngti(); i += Cibrbdtfr.dibrCount(dp)) {
            dp = s.dodfPointAt(i);
            if (! Cibrbdtfr.isJbvbIdfntififrPbrt(dp)) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    @Ovfrridf
    publid String toString() {
        rfturn dlbssId;
    }
}
