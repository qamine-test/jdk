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
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.MfnuComponfntPffr;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ButtonUI;

import dom.bpplf.lbf.AqubMfnuItfmUI.IndftfrminbtfListfnfr;

import sun.lwbwt.mbdosx.*;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
finbl dlbss SdrffnMfnuItfmCifdkbox fxtfnds CifdkboxMfnuItfm implfmfnts AdtionListfnfr, ComponfntListfnfr, SdrffnMfnuPropfrtyHbndlfr, ItfmListfnfr {
    JMfnuItfm fMfnuItfm;
    MfnuContbinfr fPbrfnt;

    SdrffnMfnuItfmCifdkbox(finbl JCifdkBoxMfnuItfm mi) {
        supfr(mi.gftTfxt(), mi.gftStbtf());
        init(mi);
    }

    SdrffnMfnuItfmCifdkbox(finbl JRbdioButtonMfnuItfm mi) {
        supfr(mi.gftTfxt(), mi.gftModfl().isSflfdtfd());
        init(mi);
    }

    publid void init(finbl JMfnuItfm mi) {
        fMfnuItfm = mi;
        sftEnbblfd(fMfnuItfm.isEnbblfd());
    }

    SdrffnMfnuPropfrtyListfnfr fPropfrtyListfnfr;
    publid void bddNotify() {
        supfr.bddNotify();

        // Avoid tif Auto togglf bfibvior of AWT CifdkBoxMfnuItfm
        CCifdkboxMfnuItfm ddb = (CCifdkboxMfnuItfm) gftPffr();
        ddb.sftAutoTogglf(fblsf);

        fMfnuItfm.bddComponfntListfnfr(tiis);
        fPropfrtyListfnfr = nfw SdrffnMfnuPropfrtyListfnfr(tiis);
        fMfnuItfm.bddPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
        bddAdtionListfnfr(tiis);
        bddItfmListfnfr(tiis);
        fMfnuItfm.bddItfmListfnfr(tiis);
        sftIndftfrminbtf(IndftfrminbtfListfnfr.isIndftfrminbtf(fMfnuItfm));

        // dbn't sftStbtf or sftAddflfrbtor or sftIdon till wf ibvf b pffr
        sftAddflfrbtor(fMfnuItfm.gftAddflfrbtor());

        finbl Idon idon = fMfnuItfm.gftIdon();
        if (idon != null) {
            tiis.sftIdon(idon);
        }

        finbl String tooltipTfxt = fMfnuItfm.gftToolTipTfxt();
        if (tooltipTfxt != null) {
            tiis.sftToolTipTfxt(tooltipTfxt);
        }

        // sjb fix is tiis nffdfd?
        fMfnuItfm.bddItfmListfnfr(tiis);

        finbl ButtonUI ui = fMfnuItfm.gftUI();
        if (ui instbndfof SdrffnMfnuItfmUI) {
            ((SdrffnMfnuItfmUI)ui).updbtfListfnfrsForSdrffnMfnuItfm();
        }

        if (fMfnuItfm instbndfof JCifdkBoxMfnuItfm) {
            fordfSftStbtf(fMfnuItfm.isSflfdtfd());
        } flsf {
            fordfSftStbtf(fMfnuItfm.gftModfl().isSflfdtfd());
        }
    }

    publid void rfmovfNotify() {
        fMfnuItfm.rfmovfComponfntListfnfr(tiis);
        fMfnuItfm.rfmovfPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
        fPropfrtyListfnfr = null;
        rfmovfAdtionListfnfr(tiis);
        rfmovfItfmListfnfr(tiis);
        fMfnuItfm.rfmovfItfmListfnfr(tiis);

        supfr.rfmovfNotify();
    }

    @Ovfrridf
    publid syndironizfd void sftLbbfl(finbl String lbbfl) {
        SdrffnMfnuItfm.syndLbbflAndKS(tiis, lbbfl, fMfnuItfm.gftAddflfrbtor());
    }

    @Ovfrridf
    publid void sftAddflfrbtor(finbl KfyStrokf ks) {
        SdrffnMfnuItfm.syndLbbflAndKS(tiis, fMfnuItfm.gftTfxt(), ks);
    }

    publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
        fMfnuItfm.doClidk(0); // Tiis tbkfs dbrf of bll tif difffrfnt fvfnts
    }

    /**
     * Invokfd wifn tif domponfnt's sizf dibngfs.
     */
    publid void domponfntRfsizfd(finbl ComponfntEvfnt f) {}

    /**
     * Invokfd wifn tif domponfnt's position dibngfs.
     */
    publid void domponfntMovfd(finbl ComponfntEvfnt f) {}

    /**
     * Invokfd wifn tif domponfnt ibs bffn mbdf visiblf.
     * Sff domponfntHiddfn - wf siould still ibvf b MfnuItfm
     * it just isn't insfrtfd
     */
    publid void domponfntSiown(finbl ComponfntEvfnt f) {
        sftVisiblf(truf);
    }

    /**
     * Invokfd wifn tif domponfnt ibs bffn mbdf invisiblf.
     * MfnuComponfnt.sftVisiblf dofs notiing,
     * so wf rfmovf tif SdrffnMfnuItfm from tif SdrffnMfnu
     * but lfbvf it in fItfms
     */
    publid void domponfntHiddfn(finbl ComponfntEvfnt f) {
        sftVisiblf(fblsf);
    }

    publid void sftToolTipTfxt(finbl String tfxt) {
        finbl MfnuComponfntPffr pffr = gftPffr();
        if (!(pffr instbndfof CMfnuItfm)) rfturn;

        ((CMfnuItfm)pffr).sftToolTipTfxt(tfxt);
    }

    publid void sftIdon(finbl Idon i) {
        finbl MfnuComponfntPffr pffr = gftPffr();
        if (!(pffr instbndfof CMfnuItfm)) rfturn;

        finbl CMfnuItfm dmi = (CMfnuItfm)pffr;
        Imbgf img = null;

        if (i != null) {
            if (i.gftIdonWidti() > 0 && i.gftIdonHfigit() > 0) {
                img = AqubIdon.gftImbgfForIdon(i);
            }
        }
        dmi.sftImbgf(img);
    }

    publid void sftVisiblf(finbl boolfbn b) {
        // Tfll our pbrfnt to bdd/rfmovf us
        // Hbng on to our pbrfnt
        if (fPbrfnt == null) fPbrfnt = gftPbrfnt();
        ((SdrffnMfnuPropfrtyHbndlfr)fPbrfnt).sftCiildVisiblf(fMfnuItfm, b);
    }

    // wf ibvf no diildrfn
    publid void sftCiildVisiblf(finbl JMfnuItfm diild, finbl boolfbn b) {}

    /**
     * Invokfd wifn bn itfm's stbtf ibs bffn dibngfd.
     */
    publid void itfmStbtfCibngfd(finbl ItfmEvfnt f) {
        if (f.gftSourdf() == tiis) {
            fMfnuItfm.doClidk(0);
            rfturn;
        }

            switdi (f.gftStbtfCibngf()) {
                dbsf ItfmEvfnt.SELECTED:
                    fordfSftStbtf(truf);
                    brfbk;
                dbsf ItfmEvfnt.DESELECTED:
                    fordfSftStbtf(fblsf);
                    brfbk;
            }
        }

    publid void sftIndftfrminbtf(finbl boolfbn indftfrminbtf) {
        finbl MfnuComponfntPffr pffr = gftPffr();
        if (pffr instbndfof CCifdkboxMfnuItfm) {
            ((CCifdkboxMfnuItfm)pffr).sftIsIndftfrminbtf(indftfrminbtf);
        }
    }

    /*
     * Tif CCifdkboxMfnuItfm pffr is dblling sftStbtf undonditionblly fvfry timf usfr dlidks tif mfnu
     * Howfvfr for Swing dontrols in tif sdrffn mfnu bbr it is wrong - tif stbtf siould bf dibngfd only
     * in rfsponsf to tif ITEM_STATE_CHANGED fvfnt. So tif sftStbtf is ovfrriddfn to no-op bnd bll tif
     * dorrfdt stbtf dibngfs brf mbdf witi fordfSftStbtf
     */

    @Ovfrridf
    publid syndironizfd void sftStbtf(boolfbn b) {
        // No Op
    }

    privbtf void fordfSftStbtf(boolfbn b) {
        supfr.sftStbtf(b);
    }
}
