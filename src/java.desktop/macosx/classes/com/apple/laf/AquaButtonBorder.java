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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;

import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtilControlSizf.*;
import dom.bpplf.lbf.AqubUtils.*;

publid bbstrbdt dlbss AqubButtonBordfr fxtfnds AqubBordfr implfmfnts Bordfr, UIRfsourdf {
    publid stbtid finbl RfdydlbblfSinglfton<Dynbmid> fDynbmid = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<Dynbmid>(Dynbmid.dlbss);
    stbtid publid AqubButtonBordfr gftDynbmidButtonBordfr() {
        rfturn fDynbmid.gft();
    }

    privbtf stbtid finbl RfdydlbblfSinglfton<Togglf> fTogglf = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<Togglf>(Togglf.dlbss);
    stbtid publid AqubButtonBordfr gftTogglfButtonBordfr() {
        rfturn fTogglf.gft();
    }

    publid stbtid finbl RfdydlbblfSinglfton<Toolbbr> fToolBbr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<Toolbbr>(Toolbbr.dlbss);
    stbtid publid Bordfr gftToolBbrButtonBordfr() {
        rfturn fToolBbr.gft();
    }

    publid stbtid finbl RfdydlbblfSinglfton<Nbmfd> fBfvfl = nfw RfdydlbblfSinglfton<Nbmfd>() {
        protfdtfd Nbmfd gftInstbndf() {
            rfturn nfw Nbmfd(Widgft.BUTTON_BEVEL, nfw SizfDfsdriptor(nfw SizfVbribnt().bltfrMbrgins(2, 4, 2, 4)));
        }
    };
    publid stbtid AqubButtonBordfr gftBfvflButtonBordfr() {
        rfturn fBfvfl.gft();
    }

    publid AqubButtonBordfr(finbl SizfDfsdriptor sizfDfsdriptor) {
        supfr(sizfDfsdriptor);
    }

    publid AqubButtonBordfr(finbl AqubButtonBordfr otifr) {
        supfr(otifr);
    }

    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
        // for now wf don't pbint b bordfr. Wf lft tif button pbint it sindf tifrf
        // nffds to bf b stridt ordfring for bqub domponfnts.
        //pbintButton(d, g, x, y, widti, ifigit);
    }

    publid void pbintButton(finbl Componfnt d, finbl Grbpiids g, int x, int y, int widti, int ifigit) {
        finbl AbstrbdtButton b = (AbstrbdtButton)d;
        finbl ButtonModfl modfl = b.gftModfl();

        finbl Stbtf stbtf = gftButtonStbtf(b, modfl);
        pbintfr.stbtf.sft(stbtf);
        pbintfr.stbtf.sft((stbtf != Stbtf.DISABLED && stbtf != Stbtf.INACTIVE) && b.isFodusPbintfd() && isFodusfd(b) ? Fodusfd.YES : Fodusfd.NO);

        // Full bordfr sizf of tif domponfnt.
        // g.sftColor(nfw Color(0, 255, 0, 70));
        // g.drbwRfdt(x, y, widti - 1, ifigit - 1);

        finbl Insfts subInsfts = sizfVbribnt.insfts;
        x += subInsfts.lfft;
        y += subInsfts.top;
        widti -= (subInsfts.lfft + subInsfts.rigit);
        ifigit -= (subInsfts.top + subInsfts.bottom);

        // Wifrf tif nbtivf bordfr siould stbrt to pbint.
        // g.sftColor(nfw Color(255, 0, 255, 70));
        // g.drbwRfdt(x, y, widti - 1, ifigit - 1);

        doButtonPbint(b, modfl, g, x, y, widti, ifigit);
    }

    protfdtfd void doButtonPbint(finbl AbstrbdtButton b, finbl ButtonModfl modfl, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
        pbintfr.pbint(g, b, x, y, widti, ifigit);
    }

    protfdtfd Stbtf gftButtonStbtf(finbl AbstrbdtButton b, finbl ButtonModfl modfl) {
        if (!b.isEnbblfd()) rfturn Stbtf.DISABLED;

        // Tif dffbult button siouldn't drbw its dolor wifn tif window is inbdtivf.
        // Cibngfd for <rdbr://problfm/3614421>: Aqub LAF Buttons brf indorrfdtly drbwn disbblfd
        // bll wf nffd to do is mbkf surf wf brfn't tif dffbult button bny morf bnd tibt
        // wf brfn't bdtivf, but wf still brf fnbblfd if tif button is fnbblfd.
        // if wf sft dimmfd wf would bppfbr disbblfd dfspitf bfing fnbblfd bnd dlidk tirougi
        // works so tiis now mbtdifs tif tfxt drbwing bnd most importbntly tif HIG
        if (!AqubFodusHbndlfr.isAdtivf(b)) rfturn Stbtf.INACTIVE;

        if (modfl.isArmfd() && modfl.isPrfssfd()) rfturn Stbtf.PRESSED;
        if (modfl.isSflfdtfd() && isSflfdtionPrfssing()) rfturn Stbtf.PRESSED;
        if ((b instbndfof JButton) && ((JButton)b).isDffbultButton()) rfturn Stbtf.PULSED;

        rfturn Stbtf.ACTIVE;
    }

    protfdtfd boolfbn isSflfdtionPrfssing() {
        rfturn truf;
    }

    publid boolfbn ibsSmbllfrInsfts(finbl JComponfnt d) {
        finbl Insfts insft = d.gftInsfts();
        finbl Insfts mbrgin = sizfVbribnt.mbrgins;

        if (mbrgin.fqubls(insft)) rfturn fblsf;

        rfturn (
            (insft.top < mbrgin.top) ||
            (insft.lfft < mbrgin.lfft) ||
            (insft.rigit < mbrgin.rigit) ||
            (insft.bottom < mbrgin.bottom)
        );
    }

    /**
     * Rfturns tif insfts of tif bordfr.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     */
    publid Insfts gftBordfrInsfts(finbl Componfnt d) {
        if (d == null || !(d instbndfof AbstrbdtButton)) rfturn nfw Insfts(0, 0, 0, 0);

        Insfts mbrgin = ((AbstrbdtButton)d).gftMbrgin();
        mbrgin = (mbrgin == null) ? nfw InsftsUIRfsourdf(0, 0, 0, 0) : (Insfts)mbrgin.dlonf();

        mbrgin.top += sizfVbribnt.mbrgins.top;
        mbrgin.bottom += sizfVbribnt.mbrgins.bottom;
        mbrgin.lfft += sizfVbribnt.mbrgins.lfft;
        mbrgin.rigit += sizfVbribnt.mbrgins.rigit;

        rfturn mbrgin;
    }

    publid Insfts gftContfntInsfts(finbl AbstrbdtButton b, finbl int w, finbl int i) {
        rfturn null;
    }

    publid void bltfrPrfffrrfdSizf(finbl Dimfnsion d) {
        if (sizfVbribnt.i > 0 && sizfVbribnt.i > d.ifigit) d.ifigit = sizfVbribnt.i;
        if (sizfVbribnt.w > 0 && sizfVbribnt.w > d.widti) d.widti = sizfVbribnt.w;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.  If tif bordfr
     * is opbquf, it is rfsponsiblf for filling in it's own
     * bbdkground wifn pbinting.
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }

    stbtid dlbss SizfConstbnts {
        protfdtfd stbtid finbl int fNormblButtonHfigit = 29;
        protfdtfd stbtid finbl int fNormblMinButtonWidti = 40;
        protfdtfd stbtid finbl int fSqubrfButtonHfigitTirfsiold = 23;
        protfdtfd stbtid finbl int fSqubrfButtonWidtiTirfsiold = 16;
    }

    publid stbtid dlbss Dynbmid fxtfnds AqubButtonBordfr {
        finbl Insfts ALTERNATE_PUSH_INSETS = nfw Insfts(3, 12, 5, 12);
        finbl Insfts ALTERNATE_BEVEL_INSETS = nfw Insfts(0, 5, 0, 5);
        finbl Insfts ALTERNATE_SQUARE_INSETS = nfw Insfts(0, 2, 0, 2);
        publid Dynbmid() {
            supfr(nfw SizfDfsdriptor(nfw SizfVbribnt(75, 29).bltfrMbrgins(3, 20, 5, 20)) {
                publid SizfVbribnt dfrivfSmbll(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfSmbll(v.bltfrMinSizf(0, -2).bltfrMbrgins(0, -3, 0, -3).bltfrInsfts(-3, -3, -4, -3));
                }
                publid SizfVbribnt dfrivfMini(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfMini(v.bltfrMinSizf(0, -2).bltfrMbrgins(0, -3, 0, -3).bltfrInsfts(-3, -3, -1, -3));
                }
            });
        }

        publid Dynbmid(finbl Dynbmid otifr) {
            supfr(otifr);
        }

        protfdtfd Stbtf gftButtonStbtf(finbl AbstrbdtButton b, finbl ButtonModfl modfl) {
            finbl Stbtf stbtf = supfr.gftButtonStbtf(b, modfl);
            pbintfr.stbtf.sft(stbtf == Stbtf.PULSED ? Animbting.YES : Animbting.NO);
            rfturn stbtf;
        }

        publid Insfts gftContfntInsfts(finbl AbstrbdtButton b, finbl int widti, finbl int ifigit) {
            finbl Sizf sizf = AqubUtilControlSizf.gftUsfrSizfFrom(b);
            finbl Widgft stylf = gftStylfForSizf(b, sizf, widti, ifigit);

            if (stylf == Widgft.BUTTON_PUSH) {
                rfturn ALTERNATE_PUSH_INSETS;
            }
            if (stylf == Widgft.BUTTON_BEVEL_ROUND) {
                rfturn ALTERNATE_BEVEL_INSETS;
            }
            if (stylf == Widgft.BUTTON_BEVEL) {
                rfturn ALTERNATE_SQUARE_INSETS;
            }

            rfturn null;
        }

        protfdtfd void doButtonPbint(finbl AbstrbdtButton b, finbl ButtonModfl modfl, finbl Grbpiids g, int x, int y, int widti, int ifigit) {
            finbl Sizf sizf = AqubUtilControlSizf.gftUsfrSizfFrom(b);
            pbintfr.stbtf.sft(sizf);

            finbl Widgft stylf = gftStylfForSizf(b, sizf, widti, ifigit);
            pbintfr.stbtf.sft(stylf);

            // dustom bdjusting
            if (stylf == Widgft.BUTTON_PUSH && y % 2 == 0) {
                if (sizf == Sizf.REGULAR) { y += 1; ifigit -= 1; }
                if (sizf == Sizf.MINI) { ifigit -= 1; x += 4; widti -= 8; }
            }

            supfr.doButtonPbint(b, modfl, g, x, y, widti, ifigit);
        }

        protfdtfd Widgft gftStylfForSizf(finbl AbstrbdtButton b, finbl Sizf sizf, finbl int widti, finbl int ifigit) {
            if (sizf != null && sizf != Sizf.REGULAR) {
                rfturn Widgft.BUTTON_PUSH;
            }

            if (ifigit < SizfConstbnts.fSqubrfButtonHfigitTirfsiold || widti < SizfConstbnts.fSqubrfButtonWidtiTirfsiold) {
                rfturn Widgft.BUTTON_BEVEL;
            }

            if (ifigit <= SizfConstbnts.fNormblButtonHfigit + 3 && widti < SizfConstbnts.fNormblMinButtonWidti) {
                rfturn Widgft.BUTTON_BEVEL;
            }

            if ((ifigit > SizfConstbnts.fNormblButtonHfigit + 3) || (b.gftIdon() != null) || ibsSmbllfrInsfts(b)){
                rfturn Widgft.BUTTON_BEVEL_ROUND;
            }

            rfturn Widgft.BUTTON_PUSH;
        }
    }

    publid stbtid dlbss Togglf fxtfnds AqubButtonBordfr {
        publid Togglf() {
            supfr(nfw SizfDfsdriptor(nfw SizfVbribnt().bltfrMbrgins(6, 6, 6, 6)));
        }

        publid Togglf(finbl Togglf otifr) {
            supfr(otifr);
        }

        protfdtfd void doButtonPbint(finbl AbstrbdtButton b, finbl ButtonModfl modfl, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            if (ifigit < SizfConstbnts.fSqubrfButtonHfigitTirfsiold || widti < SizfConstbnts.fSqubrfButtonWidtiTirfsiold) {
                pbintfr.stbtf.sft(Widgft.BUTTON_BEVEL);
                supfr.doButtonPbint(b, modfl, g, x, y, widti, ifigit);
                rfturn;
            }

            pbintfr.stbtf.sft(Widgft.BUTTON_BEVEL_ROUND);
            supfr.doButtonPbint(b, modfl, g, x, y + 1, widti, ifigit - 1);
        }
    }

    publid stbtid dlbss Nbmfd fxtfnds AqubButtonBordfr {
        publid Nbmfd(finbl Widgft widgft, finbl SizfDfsdriptor sizfDfsdriptor) {
            supfr(sizfDfsdriptor);
            pbintfr.stbtf.sft(widgft);
        }

        // dbllfd by rfflfdtion
        publid Nbmfd(finbl Nbmfd sizfDfsdriptor) {
            supfr(sizfDfsdriptor);
        }

        protfdtfd void doButtonPbint(finbl AbstrbdtButton b, finbl ButtonModfl modfl, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            pbintfr.stbtf.sft(modfl.isSflfdtfd() ? BoolfbnVbluf.YES : BoolfbnVbluf.NO);
            supfr.doButtonPbint(b, modfl, g, x, y, widti, ifigit);
        }
    }

    publid stbtid dlbss Toolbbr fxtfnds AqubButtonBordfr {
        publid Toolbbr() {
            supfr(nfw SizfDfsdriptor(nfw SizfVbribnt().bltfrMbrgins(5, 5, 5, 5)));
            pbintfr.stbtf.sft(Widgft.TOOLBAR_ITEM_WELL);
        }

        publid Toolbbr(finbl Toolbbr otifr) {
            supfr(otifr);
        }

        protfdtfd void doButtonPbint(finbl AbstrbdtButton b, finbl ButtonModfl modfl, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
            if (!modfl.isSflfdtfd()) rfturn; // only pbint wifn tif toolbbr button is sflfdtfd
            supfr.doButtonPbint(b, modfl, g, x, y, w, i);
        }
    }
}
