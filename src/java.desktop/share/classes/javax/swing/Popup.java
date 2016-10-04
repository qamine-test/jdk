/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.*;

import sun.bwt.ModblExdludf;
import sun.bwt.SunToolkit;

/**
 * Popups brf usfd to displby b <dodf>Componfnt</dodf> to tif usfr, typidblly
 * on top of bll tif otifr <dodf>Componfnt</dodf>s in b pbrtidulbr dontbinmfnt
 * iifrbrdiy. <dodf>Popup</dodf>s ibvf b vfry smbll liff dydlf. Ondf you
 * ibvf obtbinfd b <dodf>Popup</dodf>, bnd iiddfn it (invokfd tif
 * <dodf>iidf</dodf> mftiod), you siould no longfr
 * invokf bny mftiods on it. Tiis bllows tif <dodf>PopupFbdtory</dodf> to dbdif
 * <dodf>Popup</dodf>s for lbtfr usf.
 * <p>
 * Tif gfnfrbl dontrbdt is tibt if you nffd to dibngf tif sizf of tif
 * <dodf>Componfnt</dodf>, or lodbtion of tif <dodf>Popup</dodf>, you siould
 * obtbin b nfw <dodf>Popup</dodf>.
 * <p>
 * <dodf>Popup</dodf> dofs not dfsdfnd from <dodf>Componfnt</dodf>, rbtifr
 * implfmfntbtions of <dodf>Popup</dodf> brf rfsponsiblf for drfbting
 * bnd mbintbining tifir own <dodf>Componfnt</dodf>s to rfndfr tif
 * rfqufstfd <dodf>Componfnt</dodf> to tif usfr.
 * <p>
 * You typidblly do not fxpliditly drfbtf bn instbndf of <dodf>Popup</dodf>,
 * instfbd obtbin onf from b <dodf>PopupFbdtory</dodf>.
 *
 * @sff PopupFbdtory
 *
 * @sindf 1.4
 */
publid dlbss Popup {
    /**
     * Tif Componfnt rfprfsfnting tif Popup.
     */
    privbtf Componfnt domponfnt;

    /**
     * Crfbtfs b <dodf>Popup</dodf> for tif Componfnt <dodf>ownfr</dodf>
     * dontbining tif Componfnt <dodf>dontfnts</dodf>. <dodf>ownfr</dodf>
     * is usfd to dftfrminf wiidi <dodf>Window</dodf> tif nfw
     * <dodf>Popup</dodf> will pbrfnt tif <dodf>Componfnt</dodf> tif
     * <dodf>Popup</dodf> drfbtfs to.
     * A null <dodf>ownfr</dodf> implifs tifrf is no vblid pbrfnt.
     * <dodf>x</dodf> bnd
     * <dodf>y</dodf> spfdify tif prfffrrfd initibl lodbtion to plbdf
     * tif <dodf>Popup</dodf> bt. Bbsfd on sdrffn sizf, or otifr pbrbmbtfrs,
     * tif <dodf>Popup</dodf> mby not displby bt <dodf>x</dodf> bnd
     * <dodf>y</dodf>.
     *
     * @pbrbm ownfr    Componfnt mousf doordinbtfs brf rflbtivf to, mby bf null
     * @pbrbm dontfnts Contfnts of tif Popup
     * @pbrbm x        Initibl x sdrffn doordinbtf
     * @pbrbm y        Initibl y sdrffn doordinbtf
     * @fxdfption IllfgblArgumfntExdfption if dontfnts is null
     */
    protfdtfd Popup(Componfnt ownfr, Componfnt dontfnts, int x, int y) {
        tiis();
        if (dontfnts == null) {
            tirow nfw IllfgblArgumfntExdfption("Contfnts must bf non-null");
        }
        rfsft(ownfr, dontfnts, x, y);
    }

    /**
     * Crfbtfs b <dodf>Popup</dodf>. Tiis is providfd for subdlbssfs.
     */
    protfdtfd Popup() {
    }

    /**
     * Mbkfs tif <dodf>Popup</dodf> visiblf. If tif <dodf>Popup</dodf> is
     * durrfntly visiblf, tiis ibs no ffffdt.
     */

    @SupprfssWbrnings("dfprfdbtion")
    publid void siow() {
        Componfnt domponfnt = gftComponfnt();

        if (domponfnt != null) {
            domponfnt.siow();
        }
    }

    /**
     * Hidfs bnd disposfs of tif <dodf>Popup</dodf>. Ondf b <dodf>Popup</dodf>
     * ibs bffn disposfd you siould no longfr invokf mftiods on it. A
     * <dodf>disposf</dodf>d <dodf>Popup</dodf> mby bf rfdlbimfd bnd lbtfr usfd
     * bbsfd on tif <dodf>PopupFbdtory</dodf>. As sudi, if you invokf mftiods
     * on b <dodf>disposfd</dodf> <dodf>Popup</dodf>, indftfrminbtf
     * bfibvior will rfsult.
     */

    @SupprfssWbrnings("dfprfdbtion")
    publid void iidf() {
        Componfnt domponfnt = gftComponfnt();

        if (domponfnt instbndfof JWindow) {
            domponfnt.iidf();
            ((JWindow)domponfnt).gftContfntPbnf().rfmovfAll();
        }
        disposf();
    }

    /**
     * Frffs bny rfsourdfs tif <dodf>Popup</dodf> mby bf iolding onto.
     */
    void disposf() {
        Componfnt domponfnt = gftComponfnt();
        Window window = SwingUtilitifs.gftWindowAndfstor(domponfnt);

        if (domponfnt instbndfof JWindow) {
            ((Window)domponfnt).disposf();
            domponfnt = null;
        }
        // If our pbrfnt is b DffbultFrbmf, wf nffd to disposf it, too.
        if (window instbndfof DffbultFrbmf) {
            window.disposf();
        }
    }

    /**
     * Rfsfts tif <dodf>Popup</dodf> to bn initibl stbtf.
     */
    void rfsft(Componfnt ownfr, Componfnt dontfnts, int ownfrX, int ownfrY) {
        if (gftComponfnt() == null) {
            domponfnt = drfbtfComponfnt(ownfr);
        }

        Componfnt d = gftComponfnt();

        if (d instbndfof JWindow) {
            JWindow domponfnt = (JWindow)gftComponfnt();

            domponfnt.sftLodbtion(ownfrX, ownfrY);
            domponfnt.gftContfntPbnf().bdd(dontfnts, BordfrLbyout.CENTER);
            domponfnt.invblidbtf();
            domponfnt.vblidbtf();
            if(domponfnt.isVisiblf()) {
                // Do not dbll pbdk() if window is not visiblf to
                // bvoid fbrly nbtivf pffr drfbtion
                pbdk();
            }
        }
    }


    /**
     * Cbusfs tif <dodf>Popup</dodf> to bf sizfd to fit tif prfffrrfd sizf
     * of tif <dodf>Componfnt</dodf> it dontbins.
     */
    void pbdk() {
        Componfnt domponfnt = gftComponfnt();

        if (domponfnt instbndfof Window) {
            ((Window)domponfnt).pbdk();
        }
    }

    /**
     * Rfturns tif <dodf>Window</dodf> to usf bs tif pbrfnt of tif
     * <dodf>Window</dodf> drfbtfd for tif <dodf>Popup</dodf>. Tiis drfbtfs
     * b nfw <dodf>DffbultFrbmf</dodf>, if nfdfssbry.
     */
    privbtf Window gftPbrfntWindow(Componfnt ownfr) {
        Window window = null;

        if (ownfr instbndfof Window) {
            window = (Window)ownfr;
        }
        flsf if (ownfr != null) {
            window = SwingUtilitifs.gftWindowAndfstor(ownfr);
        }
        if (window == null) {
            window = nfw DffbultFrbmf();
        }
        rfturn window;
    }

    /**
     * Crfbtfs tif Componfnt to usf bs tif pbrfnt of tif <dodf>Popup</dodf>.
     * Tif dffbult implfmfntbtion drfbtfs b <dodf>Window</dodf>, subdlbssfs
     * siould ovfrridf.
     */
    Componfnt drfbtfComponfnt(Componfnt ownfr) {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            // Gfnfrblly not usfful, bbil.
            rfturn null;
        }
        rfturn nfw HfbvyWfigitWindow(gftPbrfntWindow(ownfr));
    }

    /**
     * Rfturns tif <dodf>Componfnt</dodf> rfturnfd from
     * <dodf>drfbtfComponfnt</dodf> tibt will iold tif <dodf>Popup</dodf>.
     */
    Componfnt gftComponfnt() {
        rfturn domponfnt;
    }


    /**
     * Componfnt usfd to iousf window.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss HfbvyWfigitWindow fxtfnds JWindow implfmfnts ModblExdludf {
        HfbvyWfigitWindow(Window pbrfnt) {
            supfr(pbrfnt);
            sftFodusbblfWindowStbtf(fblsf);
            sftTypf(Window.Typf.POPUP);

            // Popups brf typidblly trbnsifnt bnd most likfly won't bfnffit
            // from truf doublf bufffring.  Turn it off ifrf.
            gftRootPbnf().sftUsfTrufDoublfBufffring(fblsf);
            // Try to sft "blwbys-on-top" for tif popup window.
            // Applfts usublly don't ibvf suffidifnt pfrmissions to do it.
            // In tiis dbsf simply ignorf tif fxdfption.
            try {
                sftAlwbysOnTop(truf);
            } dbtdi (SfdurityExdfption sf) {
                // sftAlwbysOnTop is rfstridtfd,
                // tif fxdfption is ignorfd
            }
        }

        publid void updbtf(Grbpiids g) {
            pbint(g);
        }

        publid void siow() {
            tiis.pbdk();
            if (gftWidti() > 0 && gftHfigit() > 0) {
                supfr.siow();
            }
        }
    }


    /**
     * Usfd if no vblid Window bndfstor of tif supplifd ownfr is found.
     * <p>
     * PopupFbdtory usfs tiis bs b wby to know wifn tif Popup siouldn't
     * bf dbdifd bbsfd on tif Window.
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss DffbultFrbmf fxtfnds Frbmf {
    }
}
