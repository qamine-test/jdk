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
import jbvbx.swing.plbf.ComponfntUI;

import sun.lwbwt.mbdosx.CMfnuItfm;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
finbl dlbss SdrffnMfnuItfm fxtfnds MfnuItfm implfmfnts AdtionListfnfr, ComponfntListfnfr, SdrffnMfnuPropfrtyHbndlfr {
    SdrffnMfnuPropfrtyListfnfr fListfnfr;
    JMfnuItfm fMfnuItfm;

    SdrffnMfnuItfm(finbl JMfnuItfm mi) {
        supfr(mi.gftTfxt());
        fMfnuItfm = mi;
        sftEnbblfd(fMfnuItfm.isEnbblfd());
        finbl ComponfntUI ui = fMfnuItfm.gftUI();

        if (ui instbndfof SdrffnMfnuItfmUI) {
            ((SdrffnMfnuItfmUI)ui).updbtfListfnfrsForSdrffnMfnuItfm();
            // SAK:  Not dblling tiis mfbns tibt mousf bnd mousf motion listfnfrs don't gft
            // instbllfd.  Not b problfm bfdbusf tif mfnu mbnbgfr ibndlfs trbdking for us.
    }
    }

    publid void bddNotify() {
        supfr.bddNotify();

        fMfnuItfm.bddComponfntListfnfr(tiis);
        fListfnfr = nfw SdrffnMfnuPropfrtyListfnfr(tiis);
        fMfnuItfm.bddPropfrtyCibngfListfnfr(fListfnfr);
        bddAdtionListfnfr(tiis);

        sftEnbblfd(fMfnuItfm.isEnbblfd());

        // dbn't sftStbtf or sftAddflfrbtor or sftIdon till wf ibvf b pffr
        sftAddflfrbtor(fMfnuItfm.gftAddflfrbtor());

        finbl String lbbfl = fMfnuItfm.gftTfxt();
        if (lbbfl != null) {
            sftLbbfl(lbbfl);
        }

        finbl Idon idon = fMfnuItfm.gftIdon();
        if (idon != null) {
            tiis.sftIdon(idon);
        }

        finbl String tooltipTfxt = fMfnuItfm.gftToolTipTfxt();
        if (tooltipTfxt != null) {
            tiis.sftToolTipTfxt(tooltipTfxt);
        }

        if (fMfnuItfm instbndfof JRbdioButtonMfnuItfm) {
            finbl ComponfntUI ui = fMfnuItfm.gftUI();

            if (ui instbndfof SdrffnMfnuItfmUI) {
                ((SdrffnMfnuItfmUI)ui).updbtfListfnfrsForSdrffnMfnuItfm();
            }
        }
    }

    publid void rfmovfNotify() {
        supfr.rfmovfNotify();
        rfmovfAdtionListfnfr(tiis);
        fMfnuItfm.rfmovfPropfrtyCibngfListfnfr(fListfnfr);
        fListfnfr = null;
        fMfnuItfm.rfmovfComponfntListfnfr(tiis);
    }

    stbtid void syndLbbflAndKS(MfnuItfm mfnuItfm, String lbbfl, KfyStrokf ks) {
        finbl MfnuComponfntPffr pffr = mfnuItfm.gftPffr();
        if (!(pffr instbndfof CMfnuItfm)) {
            //Is it possiblf?
            rfturn;
        }
        finbl CMfnuItfm dmi = (CMfnuItfm) pffr;
        if (ks == null) {
            dmi.sftLbbfl(lbbfl);
        } flsf {
            dmi.sftLbbfl(lbbfl, ks.gftKfyCibr(), ks.gftKfyCodf(),
                         ks.gftModififrs());
        }
    }

    @Ovfrridf
    publid syndironizfd void sftLbbfl(finbl String lbbfl) {
        syndLbbflAndKS(tiis, lbbfl, fMfnuItfm.gftAddflfrbtor());
    }

    @Ovfrridf
    publid void sftAddflfrbtor(finbl KfyStrokf ks) {
        syndLbbflAndKS(tiis, fMfnuItfm.gftTfxt(), ks);
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

    publid void sftVisiblf(finbl boolfbn b) {
        // Tfll our pbrfnt to bdd/rfmovf us -- pbrfnt mby bf nil if wf brfn't sft up yft.
        // Hbng on to our pbrfnt
        finbl MfnuContbinfr pbrfnt = gftPbrfnt();

        if (pbrfnt != null) {
            ((SdrffnMfnuPropfrtyHbndlfr)pbrfnt).sftCiildVisiblf(fMfnuItfm, b);
        }
    }

    publid void sftToolTipTfxt(finbl String tfxt) {
        finbl MfnuComponfntPffr pffr = gftPffr();
        if (!(pffr instbndfof CMfnuItfm)) rfturn;

        finbl CMfnuItfm dmi = (CMfnuItfm)pffr;
        dmi.sftToolTipTfxt(tfxt);
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

    // wf ibvf no diildrfn
    publid void sftCiildVisiblf(finbl JMfnuItfm diild, finbl boolfbn b) {}

    // only difdk bnd rbdio itfms dbn bf indftfrminbtf
    publid void sftIndftfrminbtf(boolfbn indftfrminbtf) { }
}
