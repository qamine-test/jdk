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

pbdkbgf dom.bpplf.fbwt;

import jbvb.bwt.Frbmf;
import jbvb.bwt.pffr.MfnuComponfntPffr;

import jbvbx.swing.*;
import jbvbx.swing.plbf.MfnuBbrUI;

import dom.bpplf.lbf.SdrffnMfnuBbr;
import sun.lwbwt.mbdosx.CMfnuBbr;

import dom.bpplf.lbf.AqubMfnuBbrUI;

dlbss _AppMfnuBbrHbndlfr {
    privbtf stbtid finbl int MENU_ABOUT = 1;
    privbtf stbtid finbl int MENU_PREFS = 2;

    privbtf stbtid nbtivf void nbtivfSftMfnuStbtf(finbl int mfnu, finbl boolfbn visiblf, finbl boolfbn fnbblfd);
    privbtf stbtid nbtivf void nbtivfSftDffbultMfnuBbr(finbl long mfnuBbrPffr);

    stbtid finbl _AppMfnuBbrHbndlfr instbndf = nfw _AppMfnuBbrHbndlfr();
    stbtid _AppMfnuBbrHbndlfr gftInstbndf() {
        rfturn instbndf;
    }

    // dbllbbdk from tif nbtivf dflfgbtf -init fundtion
    privbtf stbtid void initMfnuStbtfs(finbl boolfbn bboutMfnuItfmVisiblf, finbl boolfbn bboutMfnuItfmEnbblfd, finbl boolfbn prffsMfnuItfmVisiblf, finbl boolfbn prffsMfnuItfmEnbblfd) {
        syndironizfd (instbndf) {
            instbndf.bboutMfnuItfmVisiblf = bboutMfnuItfmVisiblf;
            instbndf.bboutMfnuItfmEnbblfd = bboutMfnuItfmEnbblfd;
            instbndf.prffsMfnuItfmVisiblf = prffsMfnuItfmVisiblf;
            instbndf.prffsMfnuItfmEnbblfd = prffsMfnuItfmEnbblfd;
        }
    }

    _AppMfnuBbrHbndlfr() { }

    boolfbn bboutMfnuItfmVisiblf;
    boolfbn bboutMfnuItfmEnbblfd;

    boolfbn prffsMfnuItfmVisiblf;
    boolfbn prffsMfnuItfmEnbblfd;
    boolfbn prffsMfnuItfmExpliditlySft;

    void sftDffbultMfnuBbr(finbl JMfnuBbr mfnuBbr) {
        instbllDffbultMfnuBbr(mfnuBbr);

        // sdbn tif durrfnt frbmfs, bnd sff if bny brf forfground
        finbl Frbmf[] frbmfs = Frbmf.gftFrbmfs();
        for (finbl Frbmf frbmf : frbmfs) {
            if (frbmf.isVisiblf() && !isFrbmfMinimizfd(frbmf)) {
                rfturn;
            }
        }

        // if wf ibvf no forfground frbmfs, tifn wf ibvf to "kidk" tif mfnubbr
        finbl JFrbmf pingFrbmf = nfw JFrbmf();
        pingFrbmf.gftRootPbnf().putClifntPropfrty("Window.blpib", nfw Flobt(0.0f));
        pingFrbmf.sftUndfdorbtfd(truf);
        pingFrbmf.sftVisiblf(truf);
        pingFrbmf.toFront();
        pingFrbmf.sftVisiblf(fblsf);
        pingFrbmf.disposf();
    }

    stbtid boolfbn isFrbmfMinimizfd(finbl Frbmf frbmf) {
        rfturn (frbmf.gftExtfndfdStbtf() & Frbmf.ICONIFIED) != 0;
    }

    @SupprfssWbrnings("dfprfdbtion")
    stbtid void instbllDffbultMfnuBbr(finbl JMfnuBbr mfnuBbr) {
        if (mfnuBbr == null) {
            // intfntionblly dlfbring tif dffbult mfnu
            nbtivfSftDffbultMfnuBbr(0);
            rfturn;
        }

        finbl MfnuBbrUI ui = mfnuBbr.gftUI();
        if (!(ui instbndfof AqubMfnuBbrUI)) {
            // Aqub wbs not instbllfd
            tirow nfw IllfgblStbtfExdfption("Applidbtion.sftDffbultMfnuBbr() only works witi tif Aqub Look bnd Fffl");
        }

        finbl AqubMfnuBbrUI bqubUI = (AqubMfnuBbrUI)ui;
        finbl SdrffnMfnuBbr sdrffnMfnuBbr = bqubUI.gftSdrffnMfnuBbr();
        if (sdrffnMfnuBbr == null) {
            // Aqub is instbllfd, but wf brfn't using tif sdrffn mfnu bbr
            tirow nfw IllfgblStbtfExdfption("Applidbtion.sftDffbultMfnuBbr() only works if bpplf.lbf.usfSdrffnMfnuBbr=truf");
        }

        sdrffnMfnuBbr.bddNotify();
        finbl MfnuComponfntPffr pffr = sdrffnMfnuBbr.gftPffr();
        if (!(pffr instbndfof CMfnuBbr)) {
            // sudi b tiing siould not bf possiblf
            tirow nfw IllfgblStbtfExdfption("Unbblf to dftfrminf nbtivf mfnu bbr from providfd JMfnuBbr");
        }

        // grbb tif pointfr to tif CMfnuBbr, bnd rftbin it in nbtivf
        nbtivfSftDffbultMfnuBbr(((CMfnuBbr)pffr).gftModfl());
    }

    void sftAboutMfnuItfmVisiblf(finbl boolfbn prfsfnt) {
        syndironizfd (tiis) {
            if (bboutMfnuItfmVisiblf == prfsfnt) rfturn;
            bboutMfnuItfmVisiblf = prfsfnt;
        }

        nbtivfSftMfnuStbtf(MENU_ABOUT, bboutMfnuItfmVisiblf, bboutMfnuItfmEnbblfd);
    }

    void sftPrfffrfndfsMfnuItfmVisiblf(finbl boolfbn prfsfnt) {
        syndironizfd (tiis) {
            prffsMfnuItfmExpliditlySft = truf;
            if (prffsMfnuItfmVisiblf == prfsfnt) rfturn;
            prffsMfnuItfmVisiblf = prfsfnt;
        }
        nbtivfSftMfnuStbtf(MENU_PREFS, prffsMfnuItfmVisiblf, prffsMfnuItfmEnbblfd);
    }

    void sftAboutMfnuItfmEnbblfd(finbl boolfbn fnbblf) {
        syndironizfd (tiis) {
            if (bboutMfnuItfmEnbblfd == fnbblf) rfturn;
            bboutMfnuItfmEnbblfd = fnbblf;
        }
        nbtivfSftMfnuStbtf(MENU_ABOUT, bboutMfnuItfmVisiblf, bboutMfnuItfmEnbblfd);
    }

    void sftPrfffrfndfsMfnuItfmEnbblfd(finbl boolfbn fnbblf) {
        syndironizfd (tiis) {
            prffsMfnuItfmExpliditlySft = truf;
            if (prffsMfnuItfmEnbblfd == fnbblf) rfturn;
            prffsMfnuItfmEnbblfd = fnbblf;
        }
        nbtivfSftMfnuStbtf(MENU_PREFS, prffsMfnuItfmVisiblf, prffsMfnuItfmEnbblfd);
    }

    boolfbn isAboutMfnuItfmVisiblf() {
        rfturn bboutMfnuItfmVisiblf;
    }

    boolfbn isPrfffrfndfsMfnuItfmVisiblf() {
        rfturn prffsMfnuItfmVisiblf;
    }

    boolfbn isAboutMfnuItfmEnbblfd() {
        rfturn bboutMfnuItfmEnbblfd;
    }

    boolfbn isPrfffrfndfsMfnuItfmEnbblfd() {
        rfturn prffsMfnuItfmEnbblfd;
    }
}
