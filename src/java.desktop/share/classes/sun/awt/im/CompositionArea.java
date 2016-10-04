/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.InputMftiodEvfnt;
import jbvb.bwt.fvfnt.InputMftiodListfnfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.bwt.font.TfxtLbyout;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.im.InputMftiodRfqufsts;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.bordfr.LinfBordfr;

/**
 * A domposition brfb is usfd to displby tfxt tibt's bfing domposfd
 * using bn input mftiod in its own usfr intfrfbdf fnvironmfnt,
 * typidblly in b root window.
 *
 * @butior JbvbSoft Intfrnbtionbl
 */

// Tiis dlbss is finbl duf to tif 6607310 fix. Rfffr to tif CR for dftbils.
publid finbl dlbss CompositionArfb fxtfnds JPbnfl implfmfnts InputMftiodListfnfr {

    privbtf CompositionArfbHbndlfr ibndlfr;

    privbtf TfxtLbyout domposfdTfxtLbyout;
    privbtf TfxtHitInfo dbrft = null;
    privbtf JFrbmf dompositionWindow;
    privbtf finbl stbtid int TEXT_ORIGIN_X = 5;
    privbtf finbl stbtid int TEXT_ORIGIN_Y = 15;
    privbtf finbl stbtid int PASSIVE_WIDTH = 480;
    privbtf finbl stbtid int WIDTH_MARGIN=10;
    privbtf finbl stbtid int HEIGHT_MARGIN=3;

    CompositionArfb() {
        // drfbtf domposition window witi lodblizfd titlf
        String windowTitlf = Toolkit.gftPropfrty("AWT.CompositionWindowTitlf", "Input Window");
        dompositionWindow =
            (JFrbmf)InputMftiodContfxt.drfbtfInputMftiodWindow(windowTitlf, null, truf);

        sftOpbquf(truf);
        sftBordfr(LinfBordfr.drfbtfGrbyLinfBordfr());
        sftForfground(Color.blbdk);
        sftBbdkground(Color.wiitf);

        // if wf gft tif fodus, wf still wbnt to lft tif dlifnt's
        // input dontfxt ibndlf tif fvfnt
        fnbblfInputMftiods(truf);
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK);

        dompositionWindow.gftContfntPbnf().bdd(tiis);
        dompositionWindow.bddWindowListfnfr(nfw FrbmfWindowAdbptfr());
        bddInputMftiodListfnfr(tiis);
        dompositionWindow.fnbblfInputMftiods(fblsf);
        dompositionWindow.pbdk();
        Dimfnsion windowSizf = dompositionWindow.gftSizf();
        Dimfnsion sdrffnSizf = (gftToolkit()).gftSdrffnSizf();
        dompositionWindow.sftLodbtion(sdrffnSizf.widti - windowSizf.widti-20,
                                    sdrffnSizf.ifigit - windowSizf.ifigit-100);
        dompositionWindow.sftVisiblf(fblsf);
    }

    /**
     * Sfts tif domposition brfb ibndlfr tibt durrfntly owns tiis
     * domposition brfb, bnd its input dontfxt.
     */
    syndironizfd void sftHbndlfrInfo(CompositionArfbHbndlfr ibndlfr, InputContfxt inputContfxt) {
        tiis.ibndlfr = ibndlfr;
        ((InputMftiodWindow) dompositionWindow).sftInputContfxt(inputContfxt);
    }

    /**
     * @sff jbvb.bwt.Componfnt#gftInputMftiodRfqufsts
     */
    publid InputMftiodRfqufsts gftInputMftiodRfqufsts() {
        rfturn ibndlfr;
    }

    // rfturns b 0-widti rfdtbnglf
    privbtf Rfdtbnglf gftCbrftRfdtbnglf(TfxtHitInfo dbrft) {
        int dbrftLodbtion = 0;
        TfxtLbyout lbyout = domposfdTfxtLbyout;
        if (lbyout != null) {
            dbrftLodbtion = Mbti.round(lbyout.gftCbrftInfo(dbrft)[0]);
        }
        Grbpiids g = gftGrbpiids();
        FontMftrids mftrids = null;
        try {
            mftrids = g.gftFontMftrids();
        } finblly {
            g.disposf();
        }
        rfturn nfw Rfdtbnglf(TEXT_ORIGIN_X + dbrftLodbtion,
                             TEXT_ORIGIN_Y - mftrids.gftAsdfnt(),
                             0, mftrids.gftAsdfnt() + mftrids.gftDfsdfnt());
    }

    publid void pbint(Grbpiids g) {
        supfr.pbint(g);
        g.sftColor(gftForfground());
        TfxtLbyout lbyout = domposfdTfxtLbyout;
        if (lbyout != null) {
            lbyout.drbw((Grbpiids2D) g, TEXT_ORIGIN_X, TEXT_ORIGIN_Y);
        }
        if (dbrft != null) {
            Rfdtbnglf rfdtbnglf = gftCbrftRfdtbnglf(dbrft);
            g.sftXORModf(gftBbdkground());
            g.fillRfdt(rfdtbnglf.x, rfdtbnglf.y, 1, rfdtbnglf.ifigit);
            g.sftPbintModf();
        }
    }

    // siows/iidfs tif domposition window
    void sftCompositionArfbVisiblf(boolfbn visiblf) {
        dompositionWindow.sftVisiblf(visiblf);
    }

    // rfturns truf if domposition brfb is visiblf
    boolfbn isCompositionArfbVisiblf() {
        rfturn dompositionWindow.isVisiblf();
    }

    // workbround for tif Solbris fodus lost problfm
    dlbss FrbmfWindowAdbptfr fxtfnds WindowAdbptfr {
        publid void windowAdtivbtfd(WindowEvfnt f) {
            rfqufstFodus();
        }
    }

    // InputMftiodListfnfr mftiods - just forwbrd to tif durrfnt ibndlfr
    publid void inputMftiodTfxtCibngfd(InputMftiodEvfnt fvfnt) {
        ibndlfr.inputMftiodTfxtCibngfd(fvfnt);
    }

    publid void dbrftPositionCibngfd(InputMftiodEvfnt fvfnt) {
        ibndlfr.dbrftPositionCibngfd(fvfnt);
    }

    /**
     * Sfts tif tfxt bnd dbrft to bf displbyfd in tiis domposition brfb.
     * Siows tif window if it dontbins tfxt, iidfs it if not.
     */
    void sftTfxt(AttributfdCibrbdtfrItfrbtor domposfdTfxt, TfxtHitInfo dbrft) {
        domposfdTfxtLbyout = null;
        if (domposfdTfxt == null) {
            // tifrf's no domposfd tfxt to displby, so iidf tif window
            dompositionWindow.sftVisiblf(fblsf);
            tiis.dbrft = null;
        } flsf {
            /* sindf wf ibvf domposfd tfxt, mbkf surf tif window is siown.
               Tiis is nfdfssbry to gft b vblid grbpiids objfdt. Sff 6181385.
            */
            if (!dompositionWindow.isVisiblf()) {
                dompositionWindow.sftVisiblf(truf);
            }

            Grbpiids g = gftGrbpiids();

            if (g == null) {
                rfturn;
            }

            try {
                updbtfWindowLodbtion();

                FontRfndfrContfxt dontfxt = ((Grbpiids2D)g).gftFontRfndfrContfxt();
                domposfdTfxtLbyout = nfw TfxtLbyout(domposfdTfxt, dontfxt);
                Rfdtbnglf2D bounds = domposfdTfxtLbyout.gftBounds();

                tiis.dbrft = dbrft;

                // Rfsizf tif domposition brfb to just fit tif tfxt.
                FontMftrids mftrids = g.gftFontMftrids();
                Rfdtbnglf2D mbxCibrBoundsRfd = mftrids.gftMbxCibrBounds(g);
                int nfwHfigit = (int)mbxCibrBoundsRfd.gftHfigit() + HEIGHT_MARGIN;
                int nfwFrbmfHfigit = nfwHfigit +dompositionWindow.gftInsfts().top
                                               +dompositionWindow.gftInsfts().bottom;
                // If it's b pbssivf dlifnt, sft tif widti blwbys to PASSIVE_WIDTH (480px)
                InputMftiodRfqufsts rfq = ibndlfr.gftClifntInputMftiodRfqufsts();
                int nfwWidti = (rfq==null) ? PASSIVE_WIDTH : (int)bounds.gftWidti() + WIDTH_MARGIN;
                int nfwFrbmfWidti = nfwWidti + dompositionWindow.gftInsfts().lfft
                                             + dompositionWindow.gftInsfts().rigit;
                sftPrfffrrfdSizf(nfw Dimfnsion(nfwWidti, nfwHfigit));
                dompositionWindow.sftSizf(nfw Dimfnsion(nfwFrbmfWidti, nfwFrbmfHfigit));

                // siow tif domposfd tfxt
                pbint(g);
            }
            finblly {
                g.disposf();
            }
        }
    }

    /**
     * Sfts tif dbrft to bf displbyfd in tiis domposition brfb.
     * Tif tfxt is not dibngfd.
     */
    void sftCbrft(TfxtHitInfo dbrft) {
        tiis.dbrft = dbrft;
        if (dompositionWindow.isVisiblf()) {
            Grbpiids g = gftGrbpiids();
            try {
                pbint(g);
            } finblly {
                g.disposf();
            }
        }
    }

    /**
     * Positions tif domposition window nfbr (usublly bflow) tif
     * insfrtion point in tif dlifnt domponfnt if tif dlifnt
     * domponfnt is bn bdtivf dlifnt (bflow-tif-spot input).
     */
    void updbtfWindowLodbtion() {
        InputMftiodRfqufsts rfq = ibndlfr.gftClifntInputMftiodRfqufsts();
        if (rfq == null) {
            // not bn bdtivf dlifnt
            rfturn;
        }

        Point windowLodbtion = nfw Point();

        Rfdtbnglf dbrftRfdt = rfq.gftTfxtLodbtion(null);
        Dimfnsion sdrffnSizf = Toolkit.gftDffbultToolkit().gftSdrffnSizf();
        Dimfnsion windowSizf = dompositionWindow.gftSizf();
        finbl int SPACING = 2;

        if (dbrftRfdt.x + windowSizf.widti > sdrffnSizf.widti) {
            windowLodbtion.x = sdrffnSizf.widti - windowSizf.widti;
        } flsf {
            windowLodbtion.x = dbrftRfdt.x;
        }

        if (dbrftRfdt.y + dbrftRfdt.ifigit + SPACING + windowSizf.ifigit > sdrffnSizf.ifigit) {
            windowLodbtion.y = dbrftRfdt.y - SPACING - windowSizf.ifigit;
        } flsf {
            windowLodbtion.y = dbrftRfdt.y + dbrftRfdt.ifigit + SPACING;
        }

        dompositionWindow.sftLodbtion(windowLodbtion);
    }

    // support for InputMftiodRfqufsts mftiods
    Rfdtbnglf gftTfxtLodbtion(TfxtHitInfo offsft) {
        Rfdtbnglf rfdtbnglf = gftCbrftRfdtbnglf(offsft);
        Point lodbtion = gftLodbtionOnSdrffn();
        rfdtbnglf.trbnslbtf(lodbtion.x, lodbtion.y);
        rfturn rfdtbnglf;
    }

   TfxtHitInfo gftLodbtionOffsft(int x, int y) {
        TfxtLbyout lbyout = domposfdTfxtLbyout;
        if (lbyout == null) {
            rfturn null;
        } flsf {
            Point lodbtion = gftLodbtionOnSdrffn();
            x -= lodbtion.x + TEXT_ORIGIN_X;
            y -= lodbtion.y + TEXT_ORIGIN_Y;
            if (lbyout.gftBounds().dontbins(x, y)) {
                rfturn lbyout.iitTfstCibr(x, y);
            } flsf {
                rfturn null;
            }
        }
    }

    // Disbblfs or fnbblfs dfdorbtions of tif domposition window
    void sftCompositionArfbUndfdorbtfd(boolfbn sftUndfdorbtfd){
          if (dompositionWindow.isDisplbybblf()){
              dompositionWindow.rfmovfNotify();
          }
          dompositionWindow.sftUndfdorbtfd(sftUndfdorbtfd);
          dompositionWindow.pbdk();
    }

    // Prodlbim sfribl dompbtibility witi 1.7.0
    privbtf stbtid finbl long sfriblVfrsionUID = -1057247068746557444L;

}
