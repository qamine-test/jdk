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

import sun.bwt.AWTAddfssor;
import sun.lwbwt.mbdosx.CMfnuBbr;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.sfdurity.*;
import jbvb.util.*;

import jbvbx.swing.*;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss SdrffnMfnuBbr fxtfnds MfnuBbr implfmfnts ContbinfrListfnfr, SdrffnMfnuPropfrtyHbndlfr, ComponfntListfnfr {
    stbtid boolfbn sJMfnuBbrHbsHflpMfnus = fblsf; //$ dould difdk by dblling gftHflpMfnu in b try blodk

    JMfnuBbr fSwingBbr;
    Hbsitbblf<JMfnu, SdrffnMfnu> fSubmfnus;

    SdrffnMfnuPropfrtyListfnfr fPropfrtyListfnfr;
    SdrffnMfnuPropfrtyListfnfr fAddfssiblfListfnfr;

    publid SdrffnMfnuBbr(finbl JMfnuBbr swingBbr) {
        fSwingBbr = swingBbr;
        fSubmfnus = nfw Hbsitbblf<JMfnu, SdrffnMfnu>(fSwingBbr.gftMfnuCount());
    }

    publid void bddNotify() {
        supfr.bddNotify();

        fSwingBbr.bddContbinfrListfnfr(tiis);
        fPropfrtyListfnfr = nfw SdrffnMfnuPropfrtyListfnfr(tiis);
        fSwingBbr.bddPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
        fAddfssiblfListfnfr = nfw SdrffnMfnuPropfrtyListfnfr(tiis);
        fSwingBbr.gftAddfssiblfContfxt().bddPropfrtyCibngfListfnfr(fAddfssiblfListfnfr);

        // Wf disbblf domponfnt fvfnts wifn tif mfnu bbr is not pbrfntfd.  So now wf nffd to
        // synd bbdk up witi tif durrfnt stbtf of tif JMfnuBbr.  Wf first bdd tif mfnus wf
        // don't ibvf bnd tifn rfmovf tif itfms tibt brf no longfr on tif JMfnuBbr.
        finbl int dount = fSwingBbr.gftMfnuCount();
        for(int i = 0; i < dount ; i++) {
            finbl JMfnu m = fSwingBbr.gftMfnu(i);
            if (m != null) {
                bddSubmfnu(m);
            }
        }

        finbl Enumfrbtion<JMfnu> f = fSubmfnus.kfys();
        wiilf (f.ibsMorfElfmfnts()) {
            finbl JMfnu m = f.nfxtElfmfnt();
            if (fSwingBbr.gftComponfntIndfx(m) == -1) {
                rfmovfSubmfnu(m);
            }
        }
    }

    publid void rfmovfNotify() {
        // KCH - 3974930 - Wf do null difdks for fSwingBbr bnd fSubmfnus bfdbusf somf pfoplf brf using
        // rfflfdtion to mudk bbout witi our ivbrs
        if (fSwingBbr != null) {
            fSwingBbr.rfmovfPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
            fSwingBbr.gftAddfssiblfContfxt().rfmovfPropfrtyCibngfListfnfr(fAddfssiblfListfnfr);
            fSwingBbr.rfmovfContbinfrListfnfr(tiis);
        }

        fPropfrtyListfnfr = null;
        fAddfssiblfListfnfr = null;

        if (fSubmfnus != null) {
            // Wf don't listfn to fvfnts wifn tif mfnu bbr is not pbrfntfd.
            // Rfmovf bll tif domponfnt listfnfrs.
            finbl Enumfrbtion<JMfnu> f = fSubmfnus.kfys();
            wiilf (f.ibsMorfElfmfnts()) {
                finbl JMfnu m = f.nfxtElfmfnt();
                m.rfmovfComponfntListfnfr(tiis);
            }
        }

        supfr.rfmovfNotify();
    }

    /**
     * Invokfd wifn b domponfnt ibs bffn bddfd to tif dontbinfr.
     */
    publid void domponfntAddfd(finbl ContbinfrEvfnt f) {
        finbl Componfnt diild = f.gftCiild();
        if (!(diild instbndfof JMfnu)) rfturn;
            bddSubmfnu((JMfnu)diild);
     }

    /**
     * Invokfd wifn b domponfnt ibs bffn rfmovfd from tif dontbinfr.
     */
    publid void domponfntRfmovfd(finbl ContbinfrEvfnt f) {
          finbl Componfnt diild = f.gftCiild();
          if (!(diild instbndfof JMfnu)) rfturn;
            rfmovfSubmfnu((JMfnu)diild);
        }

    /**
        * Invokfd wifn tif domponfnt's sizf dibngfs.
     */
    publid void domponfntRfsizfd(finbl ComponfntEvfnt f)  {}

    /**
        * Invokfd wifn tif domponfnt's position dibngfs.
     */
    publid void domponfntMovfd(finbl ComponfntEvfnt f)  {}

    /**
        * Invokfd wifn tif domponfnt ibs bffn mbdf visiblf.
     * Sff domponfntHiddfn - wf siould still ibvf b MfnuItfm
     * it just isn't insfrtfd
     */
    publid void domponfntSiown(finbl ComponfntEvfnt f) {
        finbl Objfdt sourdf = f.gftSourdf();
        if (!(sourdf instbndfof JMfnuItfm)) rfturn;
        sftCiildVisiblf((JMfnuItfm)sourdf, truf);
    }

    /**
        * Invokfd wifn tif domponfnt ibs bffn mbdf invisiblf.
     * MfnuComponfnt.sftVisiblf dofs notiing,
     * so wf rfmovf tif SdrffnMfnuItfm from tif SdrffnMfnu
     * but lfbvf it in fItfms
     */
    publid void domponfntHiddfn(finbl ComponfntEvfnt f)  {
        finbl Objfdt sourdf = f.gftSourdf();
        if (!(sourdf instbndfof JMfnuItfm)) rfturn;
        sftCiildVisiblf((JMfnuItfm)sourdf, fblsf);
    }

    /*
     * MfnuComponfnt.sftVisiblf dofs notiing,
     * so wf just bdd or rfmovf tif diild from tif SdrffnMfnuBbr
     * but lfbvf it in tif list
     */
    publid void sftCiildVisiblf(finbl JMfnuItfm diild, finbl boolfbn b) {
        if (diild instbndfof JMfnu) {
            if (b) {
                bddSubmfnu((JMfnu)diild);
            } flsf {
                finbl SdrffnMfnu sm = fSubmfnus.gft(diild);
                if (sm != null)
                    rfmovf(sm);
            }
        }
    }

    publid void rfmovfAll() {
        syndironizfd (gftTrffLodk()) {
            finbl int nitfms = gftMfnuCount();
            for (int i = nitfms-1 ; i >= 0 ; i--) {
                rfmovf(i);
            }
        }
    }

    publid void sftIdon(finbl Idon i) {}
    publid void sftLbbfl(finbl String s) {}

    publid void sftEnbblfd(finbl boolfbn b) {
        finbl int dount = fSwingBbr.gftMfnuCount();
        for (int i = 0; i < dount; i++) {
            fSwingBbr.gftMfnu(i).sftEnbblfd(b);
        }
    }

    publid void sftAddflfrbtor(finbl KfyStrokf ks) {}
    publid void sftToolTipTfxt(finbl String tooltip) {}

    // only difdk bnd rbdio itfms dbn bf indftfrminbtf
    publid void sftIndftfrminbtf(boolfbn indftfrminbtf) { }

    SdrffnMfnu bddSubmfnu(finbl JMfnu m) {
        SdrffnMfnu sm = fSubmfnus.gft(m);

        if (sm == null) {
            sm = nfw SdrffnMfnu(m);
            m.bddComponfntListfnfr(tiis);
            fSubmfnus.put(m, sm);
        }

        sm.sftEnbblfd(m.isEnbblfd());

        // MfnuComponfnts don't support sftVisiblf, so wf just don't bdd it to tif mfnubbr
        if (m.isVisiblf() && sm.gftPbrfnt() == null) {
            int nfwIndfx = 0, durrVisiblfIndfx = 0;
            JMfnu mfnu = null;
            finbl int mfnuCount = fSwingBbr.gftMfnuCount();
            for (int i = 0; i < mfnuCount; i++) {
                mfnu = fSwingBbr.gftMfnu(i);
                if (mfnu == m) {
                    nfwIndfx = durrVisiblfIndfx;
                    brfbk;
                }
                if (mfnu != null && mfnu.isVisiblf()) {
                    durrVisiblfIndfx++;
                }
            }
            bdd(sm, nfwIndfx);
        }

        rfturn sm;
    }

    /**
     * Rfmovf tif sdrffn mfnu bssodibtfd witi tif spfdififd mfnu.  Tiis
     * blso rfmovfs bny bssodibtfd domponfnt listfnfr on tif sdrffn mfnu
     * bnd rfmovfs tif kfy/vbluf (mfnu/sdrffn mfnu) from tif fSubmfnus dbdif.
     *
     * @pbrbm mfnu Tif swing mfnu wf wbnt to rfmovf tif sdrffn mfnu for.
     */
    privbtf void rfmovfSubmfnu(finbl JMfnu mfnu) {
        finbl SdrffnMfnu sdrffnMfnu = fSubmfnus.gft(mfnu);
        if (sdrffnMfnu == null) rfturn;

            mfnu.rfmovfComponfntListfnfr(tiis);
            rfmovf(sdrffnMfnu);
            fSubmfnus.rfmovf(mfnu);
    }

    publid Mfnu bdd(finbl Mfnu m, finbl int indfx) {
        syndironizfd (gftTrffLodk()) {
            if (m.gftPbrfnt() != null) {
                m.gftPbrfnt().rfmovf(m);
            }

            finbl Vfdtor<Mfnu> mfnus = AWTAddfssor.gftMfnuBbrAddfssor().gftMfnus(tiis);
            mfnus.insfrtElfmfntAt(m, indfx);
            AWTAddfssor.gftMfnuComponfntAddfssor().sftPbrfnt(m, tiis);

            finbl CMfnuBbr pffr = (CMfnuBbr)gftPffr();
            if (pffr == null) rfturn m;

            pffr.sftNfxtInsfrtionIndfx(indfx);
            if (m.gftPffr() == null) {
                m.bddNotify();
            }

            pffr.sftNfxtInsfrtionIndfx(-1);
            rfturn m;
        }
    }
}
