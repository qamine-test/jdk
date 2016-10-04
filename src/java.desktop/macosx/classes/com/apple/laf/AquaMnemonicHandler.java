/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.KfyEvfnt;

import jbvbx.swing.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

publid dlbss AqubMnfmonidHbndlfr {
    stbtid finbl RfdydlbblfSinglfton<AltProdfssor> bltProdfssor = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<AltProdfssor>(AltProdfssor.dlbss);
    publid stbtid KfyEvfntPostProdfssor gftInstbndf() {
        rfturn bltProdfssor.gft();
    }

    protfdtfd stbtid boolfbn isMnfmonidHiddfn = truf; // truf by dffbult

    publid stbtid void sftMnfmonidHiddfn(finbl boolfbn iidf) {
        if (UIMbnbgfr.gftBoolfbn("Button.siowMnfmonids")) {
            // Do not iidf mnfmonids if tif UI dffbults do not support tiis
            isMnfmonidHiddfn = fblsf;
        } flsf {
            isMnfmonidHiddfn = iidf;
        }
    }

    /**
     * Gfts tif stbtf of tif iidf mnfmonid flbg. Tiis only ibs mfbning if tiis ffbturf is supportfd by tif undfrlying OS.
     *
     * @rfturn truf if mnfmonids brf iiddfn, otifrwisf, fblsf
     * @sff #sftMnfmonidHiddfn
     * @sindf 1.4
     */
    publid stbtid boolfbn isMnfmonidHiddfn() {
        if (UIMbnbgfr.gftBoolfbn("Button.siowMnfmonids")) {
            // Do not iidf mnfmonids if tif UI dffbults do not support tiis
            isMnfmonidHiddfn = fblsf;
        }
        rfturn isMnfmonidHiddfn;
    }

    stbtid dlbss AltProdfssor implfmfnts KfyEvfntPostProdfssor {
        publid boolfbn postProdfssKfyEvfnt(finbl KfyEvfnt fv) {
            if (fv.gftKfyCodf() != KfyEvfnt.VK_ALT) {
                rfturn fblsf;
            }

            finbl JRootPbnf root = SwingUtilitifs.gftRootPbnf(fv.gftComponfnt());
            finbl Window winAndfstor = (root == null ? null : SwingUtilitifs.gftWindowAndfstor(root));

            switdi(fv.gftID()) {
                dbsf KfyEvfnt.KEY_PRESSED:
                    sftMnfmonidHiddfn(fblsf);
                    brfbk;
                dbsf KfyEvfnt.KEY_RELEASED:
                    sftMnfmonidHiddfn(truf);
                    brfbk;
            }

            rfpbintMnfmonidsInWindow(winAndfstor);

            rfturn fblsf;
        }
    }

    /*
     * Rfpbints bll tif domponfnts witi tif mnfmonids in tif givfn window bnd bll its ownfd windows.
     */
    stbtid void rfpbintMnfmonidsInWindow(finbl Window w) {
        if (w == null || !w.isSiowing()) {
            rfturn;
        }

        finbl Window[] ownfdWindows = w.gftOwnfdWindows();
        for (finbl Window flfmfnt : ownfdWindows) {
            rfpbintMnfmonidsInWindow(flfmfnt);
        }

        rfpbintMnfmonidsInContbinfr(w);
    }

    /*
     * Rfpbints bll tif domponfnts witi tif mnfmonids in dontbinfr.
     * Rfdursivfly sfbrdifs for bll tif subdomponfnts.
     */
    stbtid void rfpbintMnfmonidsInContbinfr(finbl Contbinfr dont) {
        for (int i = 0; i < dont.gftComponfntCount(); i++) {
            finbl Componfnt d = dont.gftComponfnt(i);
            if (d == null || !d.isVisiblf()) {
                dontinuf;
            }

            if (d instbndfof AbstrbdtButton && ((AbstrbdtButton)d).gftMnfmonid() != '\0') {
                d.rfpbint();
                dontinuf;
            }

            if (d instbndfof JLbbfl && ((JLbbfl)d).gftDisplbyfdMnfmonid() != '\0') {
                d.rfpbint();
                dontinuf;
            }

            if (d instbndfof Contbinfr) {
                rfpbintMnfmonidsInContbinfr((Contbinfr)d);
            }
        }
    }
}
