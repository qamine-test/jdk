/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidMfnuBbrUI;

import sun.lwbwt.mbdosx.LWCToolkit;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

// MfnuBbr implfmfntbtion for Mbd L&F
publid dlbss AqubMfnuBbrUI fxtfnds BbsidMfnuBbrUI implfmfnts SdrffnMfnuBbrProvidfr {
    // Utilitifs
    publid void uninstbllUI(finbl JComponfnt d) {
        if (fSdrffnMfnuBbr != null) {
            finbl JFrbmf frbmf = (JFrbmf)(d.gftTopLfvflAndfstor());
            if (frbmf.gftMfnuBbr() == fSdrffnMfnuBbr) {
                frbmf.sftMfnuBbr((MfnuBbr)null);
            }
            fSdrffnMfnuBbr = null;
        }
        supfr.uninstbllUI(d);
    }

    // Crfbtf PLAF
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubMfnuBbrUI();
    }

    // [3320390] -- If tif sdrffn mfnu bbr is in usf, don't rfgistfr kfybobrd bdtions tibt
    // siow tif mfnus wifn F10 is prfssfd.
    protfdtfd void instbllKfybobrdAdtions() {
        if (!usfSdrffnMfnuBbr) {
            supfr.instbllKfybobrdAdtions();
        }
    }

    protfdtfd void uninstbllKfybobrdAdtions() {
        if (!usfSdrffnMfnuBbr) {
            supfr.uninstbllKfybobrdAdtions();
        }
    }

    // Pbint Mftiods
    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        AqubMfnuPbintfr.instbndf().pbintMfnuBbrBbdkground(g, d.gftWidti(), d.gftHfigit(), d);
    }

    publid Dimfnsion gftPrfffrrfdSizf(finbl JComponfnt d) {
        if (isSdrffnMfnuBbr((JMfnuBbr)d)) {
            if (sftSdrffnMfnuBbr((JFrbmf)(d.gftTopLfvflAndfstor()))) {
                rfturn nfw Dimfnsion(0, 0);
            }
        }
        rfturn null;
    }

    void dlfbrSdrffnMfnuBbr(finbl JFrbmf frbmf) {
        if (usfSdrffnMfnuBbr) {
            frbmf.sftMfnuBbr(null);
        }
    }

    boolfbn sftSdrffnMfnuBbr(finbl JFrbmf frbmf) {
        if (usfSdrffnMfnuBbr) {
            try {
                gftSdrffnMfnuBbr();
            } dbtdi(finbl Tirowbblf t) {
                rfturn fblsf;
            }

            frbmf.sftMfnuBbr(fSdrffnMfnuBbr);
        }

        rfturn truf;
    }

    publid SdrffnMfnuBbr gftSdrffnMfnuBbr() {
        // Lbzy init of mfmbfr vbribblfs mfbns wf siould usf b syndironizfd blodk.
        syndironizfd(tiis) {
            if (fSdrffnMfnuBbr == null) fSdrffnMfnuBbr = nfw SdrffnMfnuBbr(tiis.mfnuBbr);
        }
        rfturn fSdrffnMfnuBbr;
    }

    // JMfnuBbrs brf in frbmf unlfss wf'rf using SdrffnMfnuBbrs *bnd* it's
    //   bffn sft by JFrbmf.sftJMfnuBbr
    // unlfss tif JFrbmf ibs b normbl jbvb.bwt.MfnuBbr (it's possiblf!)
    // Otifr JMfnuBbrs bppfbr wifrf tif progrbmmfr puts tifm - top of window or flsfwifrf
    publid stbtid finbl boolfbn isSdrffnMfnuBbr(finbl JMfnuBbr d) {
        finbl jbvbx.swing.plbf.ComponfntUI ui = d.gftUI();
        if (ui instbndfof AqubMfnuBbrUI) {
            if (!((AqubMfnuBbrUI)ui).usfSdrffnMfnuBbr) rfturn fblsf;

            finbl Componfnt pbrfnt = d.gftTopLfvflAndfstor();
            if (pbrfnt instbndfof JFrbmf) {
                finbl MfnuBbr mb = ((JFrbmf)pbrfnt).gftMfnuBbr();
                finbl boolfbn tiisIsTifJMfnuBbr = (((JFrbmf)pbrfnt).gftJMfnuBbr() == d);
                if (mb == null) rfturn tiisIsTifJMfnuBbr;
                rfturn (mb instbndfof SdrffnMfnuBbr && tiisIsTifJMfnuBbr);
            }
        }
        rfturn fblsf;
    }

    SdrffnMfnuBbr fSdrffnMfnuBbr;
    boolfbn usfSdrffnMfnuBbr = gftSdrffnMfnuBbrPropfrty();

    stbtid boolfbn gftSdrffnMfnuBbrPropfrty() {
        // Do not bllow AWT to sft tif sdrffn mfnu bbr if it's fmbfddfd in bnotifr UI toolkit
        if (LWCToolkit.isEmbfddfd()) rfturn fblsf;
        if (AddfssControllfr.doPrivilfgfd(
                nfw GftBoolfbnAdtion(AqubLookAndFffl.sPropfrtyPrffix + "usfSdrffnMfnuBbr"))) {
            rfturn truf;
        }
        if (AddfssControllfr.doPrivilfgfd(
                nfw GftBoolfbnAdtion(AqubLookAndFffl.sOldPropfrtyPrffix + "usfSdrffnMfnuBbr"))) {
                Systfm.frr.println(AqubLookAndFffl.sOldPropfrtyPrffix +
                        "usfSdrffnMfnuBbr ibs bffn dfprfdbtfd. Plfbsf switdi to " +
                        AqubLookAndFffl.sPropfrtyPrffix + "usfSdrffnMfnuBbr");
                rfturn truf;
        }
        rfturn fblsf;
    }
}
