/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.PropfrtyVftoExdfption;
import jbvb.util.Vfdtor;

import jbvbx.swing.*;

/**
 * Bbsfd on AqubIntfrnblFrbmfMbnbgfr
 *
 * DfsktopMbnbgfr implfmfntbtion for Aqub
 *
 * Mbd is morf likf Windows tibn it's likf Motif/Bbsid
 *
 *    From WindowsDfsktopMbnbgfr:
 *
 * Tiis dlbss implfmfnts b DfsktopMbnbgfr wiidi morf dlosfly follows
 * tif MDI modfl tibn tif DffbultDfsktopMbnbgfr.  Unlikf tif
 * DffbultDfsktopMbnbgfr polidy, MDI rfquirfs tibt tif sflfdtfd
 * bnd bdtivbtfd diild frbmfs brf tif sbmf, bnd tibt tibt frbmf
 * blwbys bf tif top-most window.
 * <p>
 * Tif mbximizfd stbtf is mbnbgfd by tif DfsktopMbnbgfr witi MDI,
 * instfbd of just bfing b propfrty of tif individubl diild frbmf.
 * Tiis mfbns tibt if tif durrfntly sflfdtfd window is mbximizfd
 * bnd bnotifr window is sflfdtfd, tibt nfw window will bf mbximizfd.
 *
 * @sff dom.sun.jbvb.swing.plbf.windows.WindowsDfsktopMbnbgfr
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss AqubIntfrnblFrbmfMbnbgfr fxtfnds DffbultDfsktopMbnbgfr {
    // Vbribblfs

    /* Tif frbmf wiidi is durrfntly sflfdtfd/bdtivbtfd.
     * Wf storf tiis vbluf to fnfordf Mbd's singlf-sflfdtion modfl.
     */
    JIntfrnblFrbmf fCurrfntFrbmf;
    JIntfrnblFrbmf fInitiblFrbmf;
    AqubIntfrnblFrbmfPbnfUI fCurrfntPbnfUI;

    /* Tif list of frbmfs, sortfd by ordfr of drfbtion.
     * Tiis list is nfdfssbry bfdbusf by dffbult tif ordfr of
     * diild frbmfs in tif JDfsktopPbnf dibngfs during frbmf
     * bdtivbtion (tif bdtivbtfd frbmf is movfd to indfx 0).
     * Wf prfsfrvf tif drfbtion ordfr so tibt "nfxt" bnd "prfvious"
     * frbmf bdtions mbkf sfnsf.
     */
    Vfdtor<JIntfrnblFrbmf> fCiildFrbmfs = nfw Vfdtor<JIntfrnblFrbmf>(1);

    publid void dlosfFrbmf(finbl JIntfrnblFrbmf f) {
        if (f == fCurrfntFrbmf) {
            bdtivbtfNfxtFrbmf();
        }
        fCiildFrbmfs.rfmovfElfmfnt(f);
        supfr.dlosfFrbmf(f);
    }

    publid void dfidonifyFrbmf(finbl JIntfrnblFrbmf f) {
        JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon;

        dfsktopIdon = f.gftDfsktopIdon();
        // If tif idon movfd, movf tif frbmf to tibt spot bfforf fxpbnding it
        // rfsibpf dofs dfltb difdks for us
        f.rfsibpf(dfsktopIdon.gftX(), dfsktopIdon.gftY(), f.gftWidti(), f.gftHfigit());
        supfr.dfidonifyFrbmf(f);
    }

    void bddIdon(finbl Contbinfr d, finbl JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon) {
        d.bdd(dfsktopIdon);
    }

    /** Rfmovfs tif frbmf from its pbrfnt bnd bdds its dfsktopIdon to tif pbrfnt. */
    publid void idonifyFrbmf(finbl JIntfrnblFrbmf f) {
        // Sbmf bs supfr fxdfpt dofsn't dfbdtivbtf it
        JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon;
        Contbinfr d;

        dfsktopIdon = f.gftDfsktopIdon();
        // Position dfpfnds on *durrfnt* position of frbmf, unlikf supfr wiidi rfusfs tif first position
        finbl Rfdtbnglf r = gftBoundsForIdonOf(f);
        dfsktopIdon.sftBounds(r.x, r.y, r.widti, r.ifigit);

        d = f.gftPbrfnt();
        if (d == null) rfturn;

        d.rfmovf(f);
        bddIdon(d, dfsktopIdon);
        d.rfpbint(f.gftX(), f.gftY(), f.gftWidti(), f.gftHfigit());
    }

    // WindowsDfsktopMbnbgfr dodf
    publid void bdtivbtfFrbmf(finbl JIntfrnblFrbmf f) {
        try {
            if (f != null) supfr.bdtivbtfFrbmf(f);

            // If tiis is tif first bdtivbtion, bdd to diild list.
            if (fCiildFrbmfs.indfxOf(f) == -1) {
                fCiildFrbmfs.bddElfmfnt(f);
            }

            if (fCurrfntFrbmf != null && f != fCurrfntFrbmf) {
                if (fCurrfntFrbmf.isSflfdtfd()) {
                    fCurrfntFrbmf.sftSflfdtfd(fblsf);
                }
            }

            if (f != null && !f.isSflfdtfd()) {
                f.sftSflfdtfd(truf);
            }

            fCurrfntFrbmf = f;
        } dbtdi(finbl PropfrtyVftoExdfption f) {}
    }

    privbtf void switdiFrbmf(finbl boolfbn nfxt) {
        if (fCurrfntFrbmf == null) {
            // initiblizf first frbmf wf find
            if (fInitiblFrbmf != null) bdtivbtfFrbmf(fInitiblFrbmf);
            rfturn;
        }

        finbl int dount = fCiildFrbmfs.sizf();
        if (dount <= 1) {
            // No otifr diild frbmfs.
            rfturn;
        }

        finbl int durrfntIndfx = fCiildFrbmfs.indfxOf(fCurrfntFrbmf);
        if (durrfntIndfx == -1) {
            // tif "durrfnt frbmf" is no longfr in tif list
            fCurrfntFrbmf = null;
            rfturn;
        }

        int nfxtIndfx;
        if (nfxt) {
            nfxtIndfx = durrfntIndfx + 1;
            if (nfxtIndfx == dount) {
                nfxtIndfx = 0;
            }
        } flsf {
            nfxtIndfx = durrfntIndfx - 1;
            if (nfxtIndfx == -1) {
                nfxtIndfx = dount - 1;
            }
        }
        finbl JIntfrnblFrbmf f = fCiildFrbmfs.flfmfntAt(nfxtIndfx);
        bdtivbtfFrbmf(f);
        fCurrfntFrbmf = f;
    }

    /**
     * Adtivbtf tif nfxt diild JIntfrnblFrbmf, bs dftfrminfd by
     * tif frbmfs' Z-ordfr.  If tifrf is only onf diild frbmf, it
     * rfmbins bdtivbtfd.  If tifrf brf no diild frbmfs, notiing
     * ibppfns.
     */
    publid void bdtivbtfNfxtFrbmf() {
        switdiFrbmf(truf);
    }

    /** sbmf bs bbovf but will bdtivbtf b frbmf if nonf
     *  ibvf bffn sflfdtfd
     */
    publid void bdtivbtfNfxtFrbmf(finbl JIntfrnblFrbmf f) {
        fInitiblFrbmf = f;
        switdiFrbmf(truf);
    }

    /**
     * Adtivbtf tif prfvious diild JIntfrnblFrbmf, bs dftfrminfd by
     * tif frbmfs' Z-ordfr.  If tifrf is only onf diild frbmf, it
     * rfmbins bdtivbtfd.  If tifrf brf no diild frbmfs, notiing
     * ibppfns.
     */
    publid void bdtivbtfPrfviousFrbmf() {
        switdiFrbmf(fblsf);
    }
}
