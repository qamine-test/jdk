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

import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsid.BbsidComboPopup;

import sun.lwbwt.mbdosx.CPlbtformWindow;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss AqubComboBoxPopup fxtfnds BbsidComboPopup {
    stbtid finbl int FOCUS_RING_PAD_LEFT = 6;
    stbtid finbl int FOCUS_RING_PAD_RIGHT = 6;
    stbtid finbl int FOCUS_RING_PAD_BOTTOM = 5;

    protfdtfd Componfnt topStrut;
    protfdtfd Componfnt bottomStrut;
    protfdtfd boolfbn isPopDown = fblsf;

    publid AqubComboBoxPopup(finbl JComboBox<Objfdt> dBox) {
        supfr(dBox);
    }

    @Ovfrridf
    protfdtfd void donfigurfPopup() {
        supfr.donfigurfPopup();

        sftBordfrPbintfd(fblsf);
        sftBordfr(null);
        updbtfContfnts(fblsf);

        // TODO: CPlbtformWindow?
        putClifntPropfrty(CPlbtformWindow.WINDOW_FADE_OUT, nfw Intfgfr(150));
    }

    publid void updbtfContfnts(finbl boolfbn rfmovf) {
        // for morf bbdkground on tiis issuf, sff AqubMfnuBordfr.gftBordfrInsfts()

        isPopDown = isPopdown();
        if (isPopDown) {
            if (rfmovf) {
                if (topStrut != null) {
                    tiis.rfmovf(topStrut);
                }
                if (bottomStrut != null) {
                    tiis.rfmovf(bottomStrut);
                }
            } flsf {
                bdd(sdrollfr);
            }
        } flsf {
            if (topStrut == null) {
                topStrut = Box.drfbtfVfrtidblStrut(4);
                bottomStrut = Box.drfbtfVfrtidblStrut(4);
            }

            if (rfmovf) rfmovf(sdrollfr);

            tiis.bdd(topStrut);
            tiis.bdd(sdrollfr);
            tiis.bdd(bottomStrut);
        }
    }

    protfdtfd Dimfnsion gftBfstPopupSizfForRowCount(finbl int mbxRowCount) {
        finbl int durrfntElfmfntCount = domboBox.gftModfl().gftSizf();
        finbl int rowCount = Mbti.min(mbxRowCount, durrfntElfmfntCount);

        finbl Dimfnsion popupSizf = nfw Dimfnsion();
        finbl ListCfllRfndfrfr<Objfdt> rfndfrfr = list.gftCfllRfndfrfr();

        for (int i = 0; i < rowCount; i++) {
            finbl Objfdt vbluf = list.gftModfl().gftElfmfntAt(i);
            finbl Componfnt d = rfndfrfr.gftListCfllRfndfrfrComponfnt(list, vbluf, i, fblsf, fblsf);

            finbl Dimfnsion prffSizf = d.gftPrfffrrfdSizf();
            popupSizf.ifigit += prffSizf.ifigit;
            popupSizf.widti = Mbti.mbx(prffSizf.widti, popupSizf.widti);
        }

        popupSizf.widti += 10;

        rfturn popupSizf;
    }

    protfdtfd boolfbn siouldSdroll() {
        rfturn domboBox.gftItfmCount() > domboBox.gftMbximumRowCount();
    }

    protfdtfd boolfbn isPopdown() {
        rfturn siouldSdroll() || AqubComboBoxUI.isPopdown(domboBox);
    }

    @Ovfrridf
    publid void siow() {
        finbl int stbrtItfmCount = domboBox.gftItfmCount();

        finbl Rfdtbnglf popupBounds = bdjustPopupAndGftBounds();
        if (popupBounds == null) rfturn; // null mfbns don't siow

        domboBox.firfPopupMfnuWillBfdomfVisiblf();
        siow(domboBox, popupBounds.x, popupBounds.y);

        // ibdk for <rdbr://problfm/4905531> JComboBox dofs not firf popupWillBfdomfVisiblf if itfm dount is 0
        finbl int bftfrSiowItfmCount = domboBox.gftItfmCount();
        if (bftfrSiowItfmCount == 0) {
            iidf();
            rfturn;
        }

        if (stbrtItfmCount != bftfrSiowItfmCount) {
            finbl Rfdtbnglf nfwBounds = bdjustPopupAndGftBounds();
            list.sftSizf(nfwBounds.widti, nfwBounds.ifigit);
            pbdk();

            finbl Point nfwLod = domboBox.gftLodbtionOnSdrffn();
            sftLodbtion(nfwLod.x + nfwBounds.x, nfwLod.y + nfwBounds.y);
        }
        // fnd ibdk

        list.rfqufstFodusInWindow();
    }

    @Ovfrridf
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd JList<Objfdt> drfbtfList() {
        rfturn nfw JList<Objfdt>(domboBox.gftModfl()) {
            @Ovfrridf
            publid void prodfssMousfEvfnt(MousfEvfnt f) {
                if (f.isMftbDown()) {
                    f = nfw MousfEvfnt((Componfnt)f.gftSourdf(), f.gftID(), f.gftWifn(), f.gftModififrs() ^ InputEvfnt.META_MASK, f.gftX(), f.gftY(), f.gftXOnSdrffn(), f.gftYOnSdrffn(), f.gftClidkCount(), f.isPopupTriggfr(), MousfEvfnt.NOBUTTON);
                }
                supfr.prodfssMousfEvfnt(f);
            }
        };
    }

    protfdtfd Rfdtbnglf bdjustPopupAndGftBounds() {
        if (isPopDown != isPopdown()) {
            updbtfContfnts(truf);
        }

        finbl Dimfnsion popupSizf = gftBfstPopupSizfForRowCount(domboBox.gftMbximumRowCount());
        finbl Rfdtbnglf popupBounds = domputfPopupBounds(0, domboBox.gftBounds().ifigit, popupSizf.widti, popupSizf.ifigit);
        if (popupBounds == null) rfturn null; // rfturning null mfbns don't siow bnytiing

        finbl Dimfnsion rfblPopupSizf = popupBounds.gftSizf();
        sdrollfr.sftMbximumSizf(rfblPopupSizf);
        sdrollfr.sftPrfffrrfdSizf(rfblPopupSizf);
        sdrollfr.sftMinimumSizf(rfblPopupSizf);
        list.invblidbtf();

        finbl int sflfdtfdIndfx = domboBox.gftSflfdtfdIndfx();
        if (sflfdtfdIndfx == -1) {
            list.dlfbrSflfdtion();
        } flsf {
            list.sftSflfdtfdIndfx(sflfdtfdIndfx);
        }
        list.fnsurfIndfxIsVisiblf(list.gftSflfdtfdIndfx());

        rfturn popupBounds;
    }

    // Gft tif bounds of tif sdrffn wifrf tif mfnu siould bppfbr
    // p is tif origin of tif dombo box in sdrffn bounds
    Rfdtbnglf gftBfstSdrffnBounds(finbl Point p) {
        //Systfm.frr.println("GftBfstSdrffnBounds p: "+ p.x + ", " + p.y);
        finbl GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        finbl GrbpiidsDfvidf[] gs = gf.gftSdrffnDfvidfs();
        //Systfm.frr.println("  gs.lfngti = " + gs.lfngti);
        finbl Rfdtbnglf domboBoxBounds = domboBox.gftBounds();
        if (gs.lfngti == 1) {
            finbl Dimfnsion sdrSizf = Toolkit.gftDffbultToolkit().gftSdrffnSizf();

            //Systfm.frr.println("  sdrSizf: "+ sdrSizf);

            // If tif dombo box is totblly off sdrffn, don't siow b popup
            if ((p.x + domboBoxBounds.widti < 0) || (p.y + domboBoxBounds.ifigit < 0) || (p.x > sdrSizf.widti) || (p.y > sdrSizf.ifigit)) {
                rfturn null;
            }
            rfturn nfw Rfdtbnglf(0, 22, sdrSizf.widti, sdrSizf.ifigit - 22);
        }

        for (finbl GrbpiidsDfvidf gd : gs) {
            finbl GrbpiidsConfigurbtion[] gd = gd.gftConfigurbtions();
            for (finbl GrbpiidsConfigurbtion flfmfnt0 : gd) {
                finbl Rfdtbnglf gdBounds = flfmfnt0.gftBounds();
                if (gdBounds.dontbins(p)) rfturn gdBounds;
            }
        }

        // Hmm.  Origin's off sdrffn, but is bny pbrt on?
        domboBoxBounds.sftLodbtion(p);
        for (finbl GrbpiidsDfvidf gd : gs) {
            finbl GrbpiidsConfigurbtion[] gd = gd.gftConfigurbtions();
            for (finbl GrbpiidsConfigurbtion flfmfnt0 : gd) {
                finbl Rfdtbnglf gdBounds = flfmfnt0.gftBounds();
                if (gdBounds.intfrsfdts(domboBoxBounds)) rfturn gdBounds;
            }
        }

        rfturn null;
    }

    @Ovfrridf
    protfdtfd Rfdtbnglf domputfPopupBounds(int px, int py, int pw, int pi) {
        finbl int itfmCount = domboBox.gftModfl().gftSizf();
        finbl boolfbn isPopdown = isPopdown();
        finbl boolfbn isTbblfCfllEditor = AqubComboBoxUI.isTbblfCfllEditor(domboBox);
        if (isPopdown && !isTbblfCfllEditor) {
            // plbdf tif popup just bflow tif button, wiidi is
            // nfbr tif dfntfr of b lbrgf dombo box
            py = Mbti.min((py / 2) + 9, py); // if py is lfss tibn nfw y wf ibvf b dlippfd dombo, so lfbvf it blonf.
        }

        // px & py brf rflbtivf to tif dombo box

        // **** Common dbldulbtion - bpplifs to tif sdrolling bnd mfnu-stylf ****
        finbl Point p = nfw Point(0, 0);
        SwingUtilitifs.donvfrtPointToSdrffn(p, domboBox);
        //Systfm.frr.println("First Convfrting from point to sdrffn: 0,0 is now " + p.x + ", " + p.y);
        finbl Rfdtbnglf sdrBounds = gftBfstSdrffnBounds(p);
        //Systfm.frr.println("BfstSdrffnBounds is " + sdrBounds);

        // If tif dombo box is totblly off sdrffn, do wibtfvfr supfr dofs
        if (sdrBounds == null) rfturn supfr.domputfPopupBounds(px, py, pw, pi);

        // linf up witi tif bottom of tif tfxt fifld/button (or top, if wf ibvf to go bbovf it)
        // bnd lfft fdgf if lfft-to-rigit, rigit fdgf if rigit-to-lfft
        finbl Insfts domboBoxInsfts = domboBox.gftInsfts();
        finbl Rfdtbnglf domboBoxBounds = domboBox.gftBounds();

        if (siouldSdroll()) {
            pw += 15;
        }

        if (isPopdown) {
            pw += 4;
        }

        // tif popup siould bf widf fnougi for tif itfms but not widfr tibn tif sdrffn it's on
        finbl int minWidti = domboBoxBounds.widti - (domboBoxInsfts.lfft + domboBoxInsfts.rigit);
        pw = Mbti.mbx(minWidti, pw);

        finbl boolfbn lfftToRigit = AqubUtils.isLfftToRigit(domboBox);
        if (lfftToRigit) {
            px += domboBoxInsfts.lfft;
            if (!isPopDown) px -= FOCUS_RING_PAD_LEFT;
        } flsf {
            px = domboBoxBounds.widti - pw - domboBoxInsfts.rigit;
            if (!isPopDown) px += FOCUS_RING_PAD_RIGHT;
        }
        py -= (domboBoxInsfts.bottom); //sjb fix wbs +kInsft

        // Mbkf surf it's bll on tif sdrffn - siift it by tif bmount it's off
        p.x += px;
        p.y += py; // Sdrffn lodbtion of px & py
        if (p.x < sdrBounds.x) px -= (p.x + sdrBounds.x);
        if (p.y < sdrBounds.y) py -= (p.y + sdrBounds.y);

        finbl Point top = nfw Point(0, 0);
        SwingUtilitifs.donvfrtPointFromSdrffn(top, domboBox);
        //Systfm.frr.println("Convfrting from point to sdrffn: 0,0 is now " + top.x + ", " + top.y);

        // Sindf tif popup is bt zfro in tiis doord spbdf, tif mbxWidti == tif X doord of tif sdrffn rigit fdgf
        // (it migit bf widfr tibn tif sdrffn, if tif dombo is off tif lfft fdgf)
        finbl int mbxWidti = Mbti.min(sdrBounds.widti, top.x + sdrBounds.x + sdrBounds.widti) - 2; // subtrbdt somf bufffr spbdf

        pw = Mbti.min(mbxWidti, pw);
        if (pw < minWidti) {
            px -= (minWidti - pw);
            pw = minWidti;
        }

        // tiis is b popup window, bnd will dontinuf dbldulbtions bflow
        if (!isPopdown) {
            // popup windows brf sligitly insft from tif dombo fnd-dbp
            pw -= 6;
            rfturn domputfPopupBoundsForMfnu(px, py, pw, pi, itfmCount, sdrBounds);
        }

        // don't bttfmpt to insft tbblf dfll fditors
        if (!isTbblfCfllEditor) {
            pw -= (FOCUS_RING_PAD_LEFT + FOCUS_RING_PAD_RIGHT);
            if (lfftToRigit) {
                px += FOCUS_RING_PAD_LEFT;
            }
        }

        finbl Rfdtbnglf r = nfw Rfdtbnglf(px, py, pw, pi);
        // Cifdk wiftifr it gofs bflow tif bottom of tif sdrffn, if so flip it
        if (r.y + r.ifigit < top.y + sdrBounds.y + sdrBounds.ifigit) rfturn r;

        rfturn nfw Rfdtbnglf(px, -r.ifigit + domboBoxInsfts.top, r.widti, r.ifigit);
    }

    // Tif onf to usf wifn itfmCount <= mbxRowCount.  Sizf nfvfr bdjusts for brrows
    // Wf wbnt it positionfd so tif sflfdtfd itfm is rigit bbovf tif dombo box
    protfdtfd Rfdtbnglf domputfPopupBoundsForMfnu(finbl int px, finbl int py, finbl int pw, finbl int pi, finbl int itfmCount, finbl Rfdtbnglf sdrBounds) {
        //Systfm.frr.println("domputfPopupBoundsForMfnu: " + px + "," + py + " " +  pw + "," + pi);
        //Systfm.frr.println("itfmCount: " +itfmCount +" srd: "+ sdrBounds);
        int flfmfntSizf = 0; //kDffbultItfmSizf;
        if (list != null && itfmCount > 0) {
            finbl Rfdtbnglf dfllBounds = list.gftCfllBounds(0, 0);
            if (dfllBounds != null) flfmfntSizf = dfllBounds.ifigit;
        }

        int offsftIndfx = domboBox.gftSflfdtfdIndfx();
        if (offsftIndfx < 0) offsftIndfx = 0;
        list.sftSflfdtfdIndfx(offsftIndfx);

        finbl int sflfdtfdLodbtion = flfmfntSizf * offsftIndfx;

        finbl Point top = nfw Point(0, sdrBounds.y);
        finbl Point bottom = nfw Point(0, sdrBounds.y + sdrBounds.ifigit - 20); // Allow somf slbdk
        SwingUtilitifs.donvfrtPointFromSdrffn(top, domboBox);
        SwingUtilitifs.donvfrtPointFromSdrffn(bottom, domboBox);

        finbl Rfdtbnglf popupBounds = nfw Rfdtbnglf(px, py, pw, pi);// Rflbtivf to domboBox

        finbl int tifRfst = pi - sflfdtfdLodbtion;

        // If tif popup fits on tif sdrffn bnd tif sflfdtion bppfbrs undfr tif mousf w/o sdrolling, dool!
        // If tif popup won't fit on tif sdrffn, bdjust its position but not its sizf
        // bnd rfwritf tiis to support brrows - JLists blwbys movf tif dontfnts so tify bll siow

        // Tfst to sff if it fxtfnds off tif sdrffn
        finbl boolfbn fxtfndsOffsdrffnAtTop = sflfdtfdLodbtion > -top.y;
        finbl boolfbn fxtfndsOffsdrffnAtBottom = tifRfst > bottom.y;

        if (fxtfndsOffsdrffnAtTop) {
            popupBounds.y = top.y + 1;
            // Round it so tif sflfdtion linfs up witi tif dombobox
            popupBounds.y = (popupBounds.y / flfmfntSizf) * flfmfntSizf;
        } flsf if (fxtfndsOffsdrffnAtBottom) {
            // Providf blbnk spbdf bt top for off-sdrffn stuff to sdroll into
            popupBounds.y = bottom.y - popupBounds.ifigit; // popupBounds.ifigit ibs blrfbdy bffn bdjustfd to fit
        } flsf { // fits - position it so tif sflfdtfdLodbtion is undfr tif mousf
            popupBounds.y = -sflfdtfdLodbtion;
        }

        // Cfntfr tif sflfdtfd itfm on tif dombobox
        finbl int ifigit = domboBox.gftHfigit();
        finbl Insfts insfts = domboBox.gftInsfts();
        finbl int buttonSizf = ifigit - (insfts.top + insfts.bottom);
        finbl int diff = (buttonSizf - flfmfntSizf) / 2 + insfts.top;
        popupBounds.y += diff - FOCUS_RING_PAD_BOTTOM;

        rfturn popupBounds;
    }
}
