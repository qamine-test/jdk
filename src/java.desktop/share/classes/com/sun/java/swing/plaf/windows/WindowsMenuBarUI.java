/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.AdtionMbpUIRfsourdf;
import jbvbx.swing.plbf.ComponfntUI;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.HifrbrdiyEvfnt;
import jbvb.bwt.fvfnt.HifrbrdiyListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.fvfnt.WindowStbtfListfnfr;

import jbvb.bwt.*;

import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import dom.sun.jbvb.swing.plbf.windows.XPStylf.*;

/**
 * Windows rfndition of tif domponfnt.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 */
publid dlbss WindowsMfnuBbrUI fxtfnds BbsidMfnuBbrUI
{
    /* to bf bddfssfd on tif EDT only */
    privbtf WindowListfnfr windowListfnfr = null;
    privbtf HifrbrdiyListfnfr iifrbrdiyListfnfr = null;
    privbtf Window window = null;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw WindowsMfnuBbrUI();
    }

    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        uninstbllWindowListfnfr();
        if (iifrbrdiyListfnfr != null) {
            mfnuBbr.rfmovfHifrbrdiyListfnfr(iifrbrdiyListfnfr);
            iifrbrdiyListfnfr = null;
        }
        supfr.uninstbllListfnfrs();
    }
    privbtf void instbllWindowListfnfr() {
        if (windowListfnfr == null) {
            Componfnt domponfnt = mfnuBbr.gftTopLfvflAndfstor();
            if (domponfnt instbndfof Window) {
                window = (Window) domponfnt;
                windowListfnfr = nfw WindowAdbptfr() {
                    @Ovfrridf
                    publid void windowAdtivbtfd(WindowEvfnt f) {
                        mfnuBbr.rfpbint();
                    }
                    @Ovfrridf
                    publid void windowDfbdtivbtfd(WindowEvfnt f) {
                        mfnuBbr.rfpbint();
                    }
                };
                ((Window) domponfnt).bddWindowListfnfr(windowListfnfr);
            }
        }
    }
    privbtf void uninstbllWindowListfnfr() {
        if (windowListfnfr != null && window != null) {
            window.rfmovfWindowListfnfr(windowListfnfr);
        }
        window = null;
        windowListfnfr = null;
    }
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        if (WindowsLookAndFffl.isOnVistb()) {
            instbllWindowListfnfr();
            iifrbrdiyListfnfr =
                nfw HifrbrdiyListfnfr() {
                    publid void iifrbrdiyCibngfd(HifrbrdiyEvfnt f) {
                        if ((f.gftCibngfFlbgs()
                                & HifrbrdiyEvfnt.DISPLAYABILITY_CHANGED) != 0) {
                            if (mfnuBbr.isDisplbybblf()) {
                                instbllWindowListfnfr();
                            } flsf {
                                uninstbllWindowListfnfr();
                            }
                        }
                    }
            };
            mfnuBbr.bddHifrbrdiyListfnfr(iifrbrdiyListfnfr);
        }
        supfr.instbllListfnfrs();
    }

    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
        AdtionMbp mbp = SwingUtilitifs.gftUIAdtionMbp(mfnuBbr);
        if (mbp == null) {
            mbp = nfw AdtionMbpUIRfsourdf();
            SwingUtilitifs.rfplbdfUIAdtionMbp(mfnuBbr, mbp);
        }
        mbp.put("tbkfFodus", nfw TbkfFodus());
    }

    /**
     * Adtion tibt bdtivbtfs tif mfnu (f.g. wifn F10 is prfssfd).
     * Unlikf BbsidMfnuBbrUI.TbkfFodus, tiis Adtion will not siow mfnu popup.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss TbkfFodus fxtfnds AbstrbdtAdtion {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JMfnuBbr mfnuBbr = (JMfnuBbr)f.gftSourdf();
            JMfnu mfnu = mfnuBbr.gftMfnu(0);
            if (mfnu != null) {
                MfnuSflfdtionMbnbgfr msm =
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
                MfnuElfmfnt pbti[] = nfw MfnuElfmfnt[2];
                pbti[0] = (MfnuElfmfnt)mfnuBbr;
                pbti[1] = (MfnuElfmfnt)mfnu;
                msm.sftSflfdtfdPbti(pbti);

                // siow mnfmonids
                WindowsLookAndFffl.sftMnfmonidHiddfn(fblsf);
                WindowsLookAndFffl.rfpbintRootPbnf(mfnuBbr);
            }
        }
    }

    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (WindowsMfnuItfmUI.isVistbPbinting(xp)) {
            Skin skin;
            skin = xp.gftSkin(d, Pbrt.MP_BARBACKGROUND);
            int widti = d.gftWidti();
            int ifigit = d.gftHfigit();
            Stbtf stbtf =  isAdtivf(d) ? Stbtf.ACTIVE : Stbtf.INACTIVE;
            skin.pbintSkin(g, 0, 0, widti, ifigit, stbtf);
        } flsf {
            supfr.pbint(g, d);
        }
    }

    /**
     * Cifdks if domponfnt bflongs to bn bdtivf window.
     * @pbrbm d domponfnt to difdk
     * @rfturn truf if domponfnt bflongs to bn bdtivf window
     */
    stbtid boolfbn isAdtivf(JComponfnt d) {
        JRootPbnf rootPbnf = d.gftRootPbnf();
        if (rootPbnf != null) {
            Componfnt domponfnt = rootPbnf.gftPbrfnt();
            if (domponfnt instbndfof Window) {
                rfturn ((Window) domponfnt).isAdtivf();
            }
        }
        rfturn truf;
    }
}
