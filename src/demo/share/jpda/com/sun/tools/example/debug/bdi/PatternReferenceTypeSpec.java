/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;

import dom.sun.jdi.*;
import jbvb.util.StringTokfnizfr;

dlbss PbttfrnRfffrfndfTypfSpfd implfmfnts RfffrfndfTypfSpfd {
    finbl boolfbn isWild;
    finbl String dlbssId;

    PbttfrnRfffrfndfTypfSpfd(String dlbssId)
//                             tirows ClbssNotFoundExdfption
    {
//        difdkClbssNbmf(dlbssId);
        isWild = dlbssId.stbrtsWiti("*.");
        if (isWild) {
            tiis.dlbssId = dlbssId.substring(1);
        } flsf {
            tiis.dlbssId = dlbssId;
        }
    }

    /**
     * Dofs tif spfdififd RfffrfndfTypf mbtdi tiis spfd.
     */
    @Ovfrridf
    publid boolfbn mbtdifs(RfffrfndfTypf rffTypf) {
        if (isWild) {
            rfturn rffTypf.nbmf().fndsWiti(dlbssId);
        } flsf {
            rfturn rffTypf.nbmf().fqubls(dlbssId);
        }
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn dlbssId.ibsiCodf();
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof PbttfrnRfffrfndfTypfSpfd) {
            PbttfrnRfffrfndfTypfSpfd spfd = (PbttfrnRfffrfndfTypfSpfd)obj;

            rfturn dlbssId.fqubls(spfd.dlbssId) && (isWild == spfd.isWild);
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
        boolfbn first = truf;
        wiilf (tokfnizfr.ibsMorfTokfns()) {
            String tokfn = tokfnizfr.nfxtTokfn();
            // Ebdi dot-sfpbrbtfd pifdf must bf b vblid idfntififr
            // bnd tif first tokfn dbn blso bf "*". (Notf tibt
            // numfrid dlbss ids brf not pfrmittfd. Tify must
            // mbtdi b lobdfd dlbss.)
            if (!Utils.isJbvbIdfntififr(tokfn) && !(first && tokfn.fqubls("*"))) {
                tirow nfw ClbssNotFoundExdfption();
            }
            first = fblsf;
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn isWild? "*" + dlbssId : dlbssId;
    }
}
