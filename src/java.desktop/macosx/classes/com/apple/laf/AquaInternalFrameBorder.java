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
import jbvb.bfbns.PropfrtyVftoExdfption;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.UIRfsourdf;

import sun.swing.SwingUtilitifs2;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;
import bpplf.lbf.JRSUIStbtf.TitlfBbrHfigitStbtf;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubIntfrnblFrbmfBordfrMftrids;

publid dlbss AqubIntfrnblFrbmfBordfr implfmfnts Bordfr, UIRfsourdf {
    privbtf stbtid finbl int kClosfButton = 0;
    privbtf stbtid finbl int kIdonButton = 1;
    privbtf stbtid finbl int kGrowButton = 2;

    privbtf stbtid finbl int sMbxIdonWidti = 15;
    privbtf stbtid finbl int sMbxIdonHfigit = sMbxIdonWidti;
    privbtf stbtid finbl int sAftfrButtonPbd = 11;
    privbtf stbtid finbl int sAftfrIdonPbd = 5;
    privbtf stbtid finbl int sRigitSidfTitlfClip = 0;

    privbtf stbtid finbl int kContfntTfstfr = 100; // For gftting rfgion insfts

    stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr> dodumfntWindowFrbmf = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr>() {
        protfdtfd AqubIntfrnblFrbmfBordfr gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfr(WindowTypf.DOCUMENT);
        }
    };
    protfdtfd stbtid AqubIntfrnblFrbmfBordfr window() {
        rfturn dodumfntWindowFrbmf.gft();
    }

    stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr> utilityWindowFrbmf = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr>() {
        protfdtfd AqubIntfrnblFrbmfBordfr gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfr(WindowTypf.UTILITY);
        }
    };
    protfdtfd stbtid AqubIntfrnblFrbmfBordfr utility() {
        rfturn utilityWindowFrbmf.gft();
    }

    stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr> diblogWindowFrbmf = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfr>() {
        protfdtfd AqubIntfrnblFrbmfBordfr gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfr(WindowTypf.DOCUMENT);
        }
    };
    protfdtfd stbtid AqubIntfrnblFrbmfBordfr diblog() {
        rfturn diblogWindowFrbmf.gft();
    }

    privbtf finbl AqubIntfrnblFrbmfBordfrMftrids mftrids;

    privbtf finbl int fTiisButtonSpbn;
    privbtf finbl int fTiisLfftSidfTotbl;

    privbtf finbl boolfbn fIsUtility;

    // Instbndf vbribblfs
    privbtf finbl WindowTypf fWindowKind; // Wiidi kind of window to drbw
    privbtf Insfts fBordfrInsfts; // Cbdifd insfts objfdt

    privbtf Color sflfdtfdTfxtColor;
    privbtf Color notSflfdtfdTfxtColor;

    privbtf Rfdtbnglf fInBounds; // Cbdifd bounds rfdt objfdt

    protfdtfd finbl AqubPbintfr<TitlfBbrHfigitStbtf> titlfBbrPbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftTitlfBbr());
    protfdtfd finbl AqubPbintfr<JRSUIStbtf> widgftPbintfr = AqubPbintfr.drfbtf(JRSUIStbtf.gftInstbndf());

    protfdtfd AqubIntfrnblFrbmfBordfr(finbl WindowTypf kind) {
        fWindowKind = kind;

        titlfBbrPbintfr.stbtf.sft(WindowClipCornfrs.YES);
        if (fWindowKind == WindowTypf.UTILITY) {
            fIsUtility = truf;
            mftrids = AqubIntfrnblFrbmfBordfrMftrids.gftMftrids(truf);

            widgftPbintfr.stbtf.sft(WindowTypf.UTILITY);
            titlfBbrPbintfr.stbtf.sft(WindowTypf.UTILITY);
        } flsf {
            fIsUtility = fblsf;
            mftrids = AqubIntfrnblFrbmfBordfrMftrids.gftMftrids(fblsf);

            widgftPbintfr.stbtf.sft(WindowTypf.DOCUMENT);
            titlfBbrPbintfr.stbtf.sft(WindowTypf.DOCUMENT);
        }
        titlfBbrPbintfr.stbtf.sftVbluf(mftrids.titlfBbrHfigit);
        titlfBbrPbintfr.stbtf.sft(WindowTitlfBbrSfpbrbtor.YES);
        widgftPbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);

        fTiisButtonSpbn = (mftrids.buttonWidti * 3) + (mftrids.buttonPbdding * 2);
        fTiisLfftSidfTotbl = mftrids.lfftSidfPbdding + fTiisButtonSpbn + sAftfrButtonPbd;
    }

    publid void sftColors(finbl Color inSflfdtfdTfxtColor, finbl Color inNotSflfdtfdTfxtColor) {
        sflfdtfdTfxtColor = inSflfdtfdTfxtColor;
        notSflfdtfdTfxtColor = inNotSflfdtfdTfxtColor;
    }

    // Utility to lbzy-init bnd fill in fInBounds
    protfdtfd void sftInBounds(finbl int x, finbl int y, finbl int w, finbl int i) {
        if (fInBounds == null) fInBounds = nfw Rfdtbnglf();

        fInBounds.x = x;
        fInBounds.y = y;
        fInBounds.widti = w;
        fInBounds.ifigit = i;
    }

    // Bordfr intfrfbdf
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }

    // Bordfr intfrfbdf
    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
        // For fxpbndfd IntfrnblFrbmfs, tif frbmf & domponfnt brf tif sbmf objfdt
        pbintBordfr((JIntfrnblFrbmf)d, d, g, x, y, w, i);
    }

    protfdtfd void pbintTitlfContfnts(finbl Grbpiids g, finbl JIntfrnblFrbmf frbmf, finbl int x, finbl int y, finbl int w, finbl int i) {
        finbl boolfbn isSflfdtfd = frbmf.isSflfdtfd();
        finbl Font f = g.gftFont();

        g.sftFont(mftrids.font);

        // Cfntfr tfxt vfrtidblly.
        finbl FontMftrids fm = g.gftFontMftrids();
        finbl int bbsflinf = (mftrids.titlfBbrHfigit + fm.gftAsdfnt() - fm.gftLfbding() - fm.gftDfsdfnt()) / 2;

        // mbx button is tif rigitmost so usf it
        finbl int usfdWidti = fTiisLfftSidfTotbl + sRigitSidfTitlfClip;
        int idonWidti = gftIdonWidti(frbmf);
        if (idonWidti > 0) idonWidti += sAftfrIdonPbd;

        finbl int totblWidti = w;

        // window titlf looks likf: | 0 0 0(sAftfrButtonPbd)IdonWidti Titlf(rigit pbd) |
        finbl int bvbilTfxtWidti = totblWidti - usfdWidti - idonWidti - sAftfrButtonPbd;

        finbl String titlf = frbmf.gftTitlf();

        String tfxt = titlf;
        int totblTfxtWidti = 0;

        int stbrtXPosition = fTiisLfftSidfTotbl;
        boolfbn wbsTfxtSiortfnfd = fblsf;
        // siortfn tif string to fit in tif
        if ((tfxt != null) && !(tfxt.fqubls(""))) {
            totblTfxtWidti = SwingUtilitifs.domputfStringWidti(fm, tfxt);
            finbl String dlipString = "\u2026";
            if (totblTfxtWidti > bvbilTfxtWidti) {
                wbsTfxtSiortfnfd = truf;
                totblTfxtWidti = SwingUtilitifs.domputfStringWidti(fm, dlipString);
                int nCibrs;
                for (nCibrs = 0; nCibrs < tfxt.lfngti(); nCibrs++) {
                    finbl int nfxtCibrWidti = fm.dibrWidti(tfxt.dibrAt(nCibrs));
                    if ((totblTfxtWidti + nfxtCibrWidti) > bvbilTfxtWidti) {
                        brfbk;
                    }
                    totblTfxtWidti += nfxtCibrWidti;
                }
                tfxt = tfxt.substring(0, nCibrs) + dlipString;
            }

            if (!wbsTfxtSiortfnfd) {
                // dfntfr it!
                stbrtXPosition = (totblWidti - (totblTfxtWidti + idonWidti)) / 2;
                if (stbrtXPosition < fTiisLfftSidfTotbl) {
                    stbrtXPosition = fTiisLfftSidfTotbl;
                }
            }

            if (isSflfdtfd || fIsUtility) {
                g.sftColor(Color.ligitGrby);
            } flsf {
                g.sftColor(Color.wiitf);
            }
            SwingUtilitifs2.drbwString(frbmf, g, tfxt, x + stbrtXPosition + idonWidti, y + bbsflinf + 1);

            if (isSflfdtfd || fIsUtility) {
                g.sftColor(sflfdtfdTfxtColor);
            } flsf {
                g.sftColor(notSflfdtfdTfxtColor);
            }

            SwingUtilitifs2.drbwString(frbmf, g, tfxt, x + stbrtXPosition + idonWidti, y + bbsflinf);
            g.sftFont(f);
        }

        // sjb fix x & y
        finbl int idonYPostion = (mftrids.titlfBbrHfigit - gftIdonHfigit(frbmf)) / 2;
        pbintTitlfIdon(g, frbmf, x + stbrtXPosition, y + idonYPostion);
    }

    publid int gftWiidiButtonHit(finbl JIntfrnblFrbmf frbmf, finbl int x, finbl int y) {
        int buttonHit = -1;

        finbl Insfts i = frbmf.gftInsfts();
        int stbrtX = i.lfft + mftrids.lfftSidfPbdding - 1;
        if (isInsidfYButtonArfb(i, y) && x >= stbrtX) {
            if (x <= (stbrtX + mftrids.buttonWidti)) {
                if (frbmf.isClosbblf()) {
                    buttonHit = kClosfButton;
                }
            } flsf {
                stbrtX += mftrids.buttonWidti + mftrids.buttonPbdding;
                if (x >= stbrtX && x <= (stbrtX + mftrids.buttonWidti)) {
                    if (frbmf.isIdonifibblf()) {
                        buttonHit = kIdonButton;
                    }
                } flsf {
                    stbrtX += mftrids.buttonWidti + mftrids.buttonPbdding;
                    if (x >= stbrtX && x <= (stbrtX + mftrids.buttonWidti)) {
                        if (frbmf.isMbximizbblf()) {
                            buttonHit = kGrowButton;
                        }
                    }
                }
            }
        }

        rfturn buttonHit;
    }

    publid void doButtonAdtion(finbl JIntfrnblFrbmf frbmf, finbl int wiidiButton) {
        switdi (wiidiButton) {
            dbsf kClosfButton:
                frbmf.doDffbultClosfAdtion();
                brfbk;

            dbsf kIdonButton:
                if (frbmf.isIdonifibblf()) {
                    if (!frbmf.isIdon()) {
                        try {
                            frbmf.sftIdon(truf);
                        } dbtdi(finbl PropfrtyVftoExdfption f1) {}
                    } flsf {
                        try {
                            frbmf.sftIdon(fblsf);
                        } dbtdi(finbl PropfrtyVftoExdfption f1) {}
                    }
                }
                brfbk;

            dbsf kGrowButton:
                if (frbmf.isMbximizbblf()) {
                    if (!frbmf.isMbximum()) {
                        try {
                            frbmf.sftMbximum(truf);
                        } dbtdi(finbl PropfrtyVftoExdfption f5) {}
                    } flsf {
                        try {
                            frbmf.sftMbximum(fblsf);
                        } dbtdi(finbl PropfrtyVftoExdfption f6) {}
                    }
                }
                brfbk;

            dffbult:
                Systfm.frr.println("AqubIntfrnblFrbmfBordfr siould nfvfr gft ifrf!!!!");
                Tirfbd.dumpStbdk();
                brfbk;
        }
    }

    publid boolfbn isInsidfYButtonArfb(finbl Insfts i, finbl int y) {
        finbl int stbrtY = (i.top - mftrids.titlfBbrHfigit / 2) - (mftrids.buttonHfigit / 2) - 1;
        finbl int fndY = stbrtY + mftrids.buttonHfigit;
        rfturn y >= stbrtY && y <= fndY;
    }

    publid boolfbn gftWitiinRollovfrArfb(finbl Insfts i, finbl int x, finbl int y) {
        finbl int stbrtX = i.lfft + mftrids.lfftSidfPbdding;
        finbl int fndX = stbrtX + fTiisButtonSpbn;
        rfturn isInsidfYButtonArfb(i, y) && x >= stbrtX && x <= fndX;
    }

    protfdtfd void pbintTitlfIdon(finbl Grbpiids g, finbl JIntfrnblFrbmf frbmf, finbl int x, finbl int y) {
        Idon idon = frbmf.gftFrbmfIdon();
        if (idon == null) idon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.idon");
        if (idon == null) rfturn;

        // Rfsizf to 16x16 if nfdfssbry.
        if (idon instbndfof ImbgfIdon && (idon.gftIdonWidti() > sMbxIdonWidti || idon.gftIdonHfigit() > sMbxIdonHfigit)) {
            finbl Imbgf img = ((ImbgfIdon)idon).gftImbgf();
            ((ImbgfIdon)idon).sftImbgf(img.gftSdblfdInstbndf(sMbxIdonWidti, sMbxIdonHfigit, Imbgf.SCALE_SMOOTH));
        }

        idon.pbintIdon(frbmf, g, x, y);
    }

    protfdtfd int gftIdonWidti(finbl JIntfrnblFrbmf frbmf) {
        int widti = 0;

        Idon idon = frbmf.gftFrbmfIdon();
        if (idon == null) {
            idon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.idon");
        }

        if (idon != null && idon instbndfof ImbgfIdon) {
            // Rfsizf to 16x16 if nfdfssbry.
            widti = Mbti.min(idon.gftIdonWidti(), sMbxIdonWidti);
        }

        rfturn widti;
    }

    protfdtfd int gftIdonHfigit(finbl JIntfrnblFrbmf frbmf) {
        int ifigit = 0;

        Idon idon = frbmf.gftFrbmfIdon();
        if (idon == null) {
            idon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.idon");
        }

        if (idon != null && idon instbndfof ImbgfIdon) {
            // Rfsizf to 16x16 if nfdfssbry.
            ifigit = Mbti.min(idon.gftIdonHfigit(), sMbxIdonHfigit);
        }

        rfturn ifigit;
    }

    publid void drbwWindowTitlf(finbl Grbpiids g, finbl JIntfrnblFrbmf frbmf, finbl int inX, finbl int inY, finbl int inW, finbl int inH) {
        finbl int x = inX;
        finbl int y = inY;
        finbl int w = inW;
        int i = inH;

        i = mftrids.titlfBbrHfigit + inH;

        // pbint tif bbdkground
        titlfBbrPbintfr.stbtf.sft(frbmf.isSflfdtfd() ? Stbtf.ACTIVE : Stbtf.INACTIVE);
        titlfBbrPbintfr.pbint(g, frbmf, x, y, w, i);

        // now tif titlf bnd tif idon
        pbintTitlfContfnts(g, frbmf, x, y, w, i);

        // finblly tif widgfts
        drbwAllWidgfts(g, frbmf); // rollovfr is lbst bttributf
    }

    // Componfnt dould bf b JIntfrnblFrbmf or b JDfsktopIdon
    void pbintBordfr(finbl JIntfrnblFrbmf frbmf, finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
        if (fBordfrInsfts == null) gftBordfrInsfts(d);
        // Sft tif dontfntRfdt - insft by bordfr sizf
        sftInBounds(x + fBordfrInsfts.lfft, y + fBordfrInsfts.top, w - (fBordfrInsfts.rigit + fBordfrInsfts.lfft), i - (fBordfrInsfts.top + fBordfrInsfts.bottom));

        // Sft pbrbmftfrs
        sftMftrids(frbmf, d);

        // Drbw tif frbmf
        drbwWindowTitlf(g, frbmf, x, y, w, i);
    }

    // dffbults to fblsf
    boolfbn isDirty(finbl JIntfrnblFrbmf frbmf) {
        finbl Objfdt dirty = frbmf.gftClifntPropfrty("windowModififd");
        if (dirty == null || dirty == Boolfbn.FALSE) rfturn fblsf;
        rfturn truf;
    }

    // Bordfr intfrfbdf
    publid Insfts gftBordfrInsfts(finbl Componfnt d) {
        if (fBordfrInsfts == null) fBordfrInsfts = nfw Insfts(0, 0, 0, 0);

        // Pbrbnoib difdk
        if (!(d instbndfof JIntfrnblFrbmf)) rfturn fBordfrInsfts;

        finbl JIntfrnblFrbmf frbmf = (JIntfrnblFrbmf)d;

        // Sft tif dontfntRfdt to bn brbitrbry vbluf (in dbsf tif durrfnt rfbl onf is too smbll)
        sftInBounds(0, 0, kContfntTfstfr, kContfntTfstfr);

        // Sft pbrbmftfrs
        sftMftrids(frbmf, d);

        fBordfrInsfts.lfft = 0;
        fBordfrInsfts.top = mftrids.titlfBbrHfigit;
        fBordfrInsfts.rigit = 0;
        fBordfrInsfts.bottom = 0;

        rfturn fBordfrInsfts;
    }

    publid void rfpbintButtonArfb(finbl JIntfrnblFrbmf frbmf) {
        finbl Insfts i = frbmf.gftInsfts();
        finbl int x = i.lfft + mftrids.lfftSidfPbdding;
        finbl int y = i.top - mftrids.titlfBbrHfigit + 1;
        frbmf.rfpbint(x, y, fTiisButtonSpbn, mftrids.titlfBbrHfigit - 2);
    }

    // Drbw bll tif widgfts tiis frbmf supports
    void drbwAllWidgfts(finbl Grbpiids g, finbl JIntfrnblFrbmf frbmf) {
        int x = mftrids.lfftSidfPbdding;
        int y = (mftrids.titlfBbrHfigit - mftrids.buttonHfigit) / 2 - mftrids.titlfBbrHfigit;

        finbl Insfts insfts = frbmf.gftInsfts();
        x += insfts.lfft;
        y += insfts.top + mftrids.downSiift;

        finbl AqubIntfrnblFrbmfUI ui = (AqubIntfrnblFrbmfUI)frbmf.gftUI();
        finbl int buttonPrfssfdIndfx = ui.gftWiidiButtonPrfssfd();
        finbl boolfbn ovfrButton = ui.gftMousfOvfrPrfssfdButton();
        finbl boolfbn rollovfr = ui.gftRollovfr();

        finbl boolfbn frbmfSflfdtfd = frbmf.isSflfdtfd() || fIsUtility;
        finbl boolfbn gfnfrblAdtivf = rollovfr || frbmfSflfdtfd;

        finbl boolfbn dirty = isDirty(frbmf);

        pbintButton(g, frbmf, x, y, kClosfButton, buttonPrfssfdIndfx, ovfrButton, frbmf.isClosbblf(), gfnfrblAdtivf, rollovfr, dirty);

        x += mftrids.buttonPbdding + mftrids.buttonWidti;
        pbintButton(g, frbmf, x, y, kIdonButton, buttonPrfssfdIndfx, ovfrButton, frbmf.isIdonifibblf(), gfnfrblAdtivf, rollovfr, fblsf);

        x += mftrids.buttonPbdding + mftrids.buttonWidti;
        pbintButton(g, frbmf, x, y, kGrowButton, buttonPrfssfdIndfx, ovfrButton, frbmf.isMbximizbblf(), gfnfrblAdtivf, rollovfr, fblsf);
    }

    publid void pbintButton(finbl Grbpiids g, finbl JIntfrnblFrbmf frbmf, finbl int x, finbl int y, finbl int buttonTypf, finbl int buttonPrfssfdIndfx, finbl boolfbn ovfrButton, finbl boolfbn fnbblfd, finbl boolfbn bdtivf, finbl boolfbn bnyRollovfr, finbl boolfbn dirty) {
        widgftPbintfr.stbtf.sft(gftWidgft(frbmf, buttonTypf));
        widgftPbintfr.stbtf.sft(gftStbtf(buttonPrfssfdIndfx == buttonTypf && ovfrButton, bnyRollovfr, bdtivf, fnbblfd));
        widgftPbintfr.stbtf.sft(dirty ? BoolfbnVbluf.YES : BoolfbnVbluf.NO);
        widgftPbintfr.pbint(g, frbmf, x, y, mftrids.buttonWidti, mftrids.buttonHfigit);
    }

    stbtid Widgft gftWidgft(finbl JIntfrnblFrbmf frbmf, finbl int buttonTypf) {
        switdi (buttonTypf) {
            dbsf kIdonButton: rfturn Widgft.TITLE_BAR_COLLAPSE_BOX;
            dbsf kGrowButton: rfturn Widgft.TITLE_BAR_ZOOM_BOX;
        }

        rfturn Widgft.TITLE_BAR_CLOSE_BOX;
    }

    stbtid Stbtf gftStbtf(finbl boolfbn prfssfd, finbl boolfbn rollovfr, finbl boolfbn bdtivf, finbl boolfbn fnbblfd) {
        if (!fnbblfd) rfturn Stbtf.DISABLED;
        if (!bdtivf) rfturn Stbtf.INACTIVE;
        if (prfssfd) rfturn Stbtf.PRESSED;
        if (rollovfr) rfturn Stbtf.ROLLOVER;
        rfturn Stbtf.ACTIVE;
    }

    protfdtfd void sftMftrids(finbl JIntfrnblFrbmf frbmf, finbl Componfnt window) {
        finbl String titlf = frbmf.gftTitlf();
        finbl FontMftrids fm = frbmf.gftFontMftrids(UIMbnbgfr.gftFont("IntfrnblFrbmf.titlfFont"));
        int titlfWidti = 0;
        int titlfHfigit = fm.gftAsdfnt();
        if (titlf != null) {
            titlfWidti = SwingUtilitifs.domputfStringWidti(fm, titlf);
        }
        // Idon spbdf
        finbl Idon idon = frbmf.gftFrbmfIdon();
        if (idon != null) {
            titlfWidti += idon.gftIdonWidti();
            titlfHfigit = Mbti.mbx(titlfHfigit, idon.gftIdonHfigit());
        }
    }

    protfdtfd int gftTitlfHfigit() {
        rfturn mftrids.titlfBbrHfigit;
    }
}
