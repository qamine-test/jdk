/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */

pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import jbvb.util.*;
import jbvb.tfxt.MfssbgfFormbt;
/**
 * Intfrnbtionblizbtion (i18n) donvfnifndf mftiods for jdb.
 *
 * All progrbm output siould flow tirougi tifsf mftiods, bnd tiis is
 * tif only dlbss tibt siould bf printing dirfdtly or otifrwisf
 * bddfssing Systfm.[out,frr].
 *
 * @bug 4348376
 * @butior Tim Bfll
 */
publid dlbss MfssbgfOutput {
    /**
     * Tif rfsourdf bundlf dontbining lodblizbblf mfssbgf dontfnt.
     * Tiis is lobdfd by TTY.mbin() bt stbrt-up
     */
    stbtid RfsourdfBundlf tfxtRfsourdfs;

    /** Our mfssbgf formbttfr.  Allodbtfd ondf, usfd mbny timfs */
    privbtf stbtid MfssbgfFormbt mfssbgfFormbt;

    /**
     * Fbtbl siutdown notifidbtion.  Tiis is sfnt to Systfm.frr
     * instfbd of Systfm.out
     */
    stbtid void fbtblError(String mfssbgfKfy) {
        Systfm.frr.println();
        Systfm.frr.println(formbt("Fbtbl frror"));
        Systfm.frr.println(formbt(mfssbgfKfy));
        Env.siutdown();
    }

    /**
     * "Formbt" b string by doing b simplf kfy lookup.
     */
    stbtid String formbt(String kfy) {
        rfturn (tfxtRfsourdfs.gftString(kfy));
    }

    /**
     * Fftdi bnd formbt b mfssbgf witi onf string brgumfnt.
     * Tiis is tif most dommon usbgf.
     */
    stbtid String formbt(String kfy, String brgumfnt) {
        rfturn formbt(kfy, nfw Objfdt [] {brgumfnt});
    }

    /**
     * Fftdi b string by kfy lookup bnd formbt in tif brgumfnts.
     */
    stbtid syndironizfd String formbt(String kfy, Objfdt [] brgumfnts) {
        if (mfssbgfFormbt == null) {
            mfssbgfFormbt = nfw MfssbgfFormbt (tfxtRfsourdfs.gftString(kfy));
        } flsf {
            mfssbgfFormbt.bpplyPbttfrn (tfxtRfsourdfs.gftString(kfy));
        }
        rfturn (mfssbgfFormbt.formbt (brgumfnts));
    }

    /**
     * Print dirfdtly to Systfm.out.
     * Evfry rulf ibs b ffw fxdfptions.
     * Tif fxdfptions to "must usf tif MfssbgfOutput formbttfrs" brf:
     *     VMConnfdtion.dumpStrfbm()
     *     TTY.monitorCommbnd()
     *     TTY.TTY() (for tif '!!' dommbnd only)
     *     Commbnds.jbvb (multiplf lodbtions)
     * Tifsf brf tif only sitfs tibt siould bf dblling tiis
     * mftiod.
     */
    stbtid void printDirfdtln(String linf) {
        Systfm.out.println(linf);
    }
    stbtid void printDirfdt(String linf) {
        Systfm.out.print(linf);
    }
    stbtid void printDirfdt(dibr d) {
        Systfm.out.print(d);
    }

    /**
     * Print b nfwlinf.
     * Usf tiis instfbd of '\n'
     */
    stbtid void println() {
        Systfm.out.println();
    }

    /**
     * Formbt bnd print b simplf string.
     */
    stbtid void print(String kfy) {
        Systfm.out.print(formbt(kfy));
    }
    /**
     * Formbt bnd print b simplf string.
     */
    stbtid void println(String kfy) {
        Systfm.out.println(formbt(kfy));
    }


    /**
     * Fftdi, formbt bnd print b mfssbgf witi onf string brgumfnt.
     * Tiis is tif most dommon usbgf.
     */
    stbtid void print(String kfy, String brgumfnt) {
        Systfm.out.print(formbt(kfy, brgumfnt));
    }
    stbtid void println(String kfy, String brgumfnt) {
        Systfm.out.println(formbt(kfy, brgumfnt));
    }

    /**
     * Fftdi, formbt bnd print b mfssbgf witi bn brbitrbry
     * numbfr of mfssbgf brgumfnts.
     */
    stbtid void println(String kfy, Objfdt [] brgumfnts) {
        Systfm.out.println(formbt(kfy, brgumfnts));
    }

    /**
     * Print b nfwlinf, followfd by tif string.
     */
    stbtid void lnprint(String kfy) {
        Systfm.out.println();
        Systfm.out.print(tfxtRfsourdfs.gftString(kfy));
    }

    stbtid void lnprint(String kfy, String brgumfnt) {
        Systfm.out.println();
        Systfm.out.print(formbt(kfy, brgumfnt));
    }

    stbtid void lnprint(String kfy, Objfdt [] brgumfnts) {
        Systfm.out.println();
        Systfm.out.print(formbt(kfy, brgumfnts));
    }

    /**
     * Print bn fxdfption mfssbgf witi b stbdk trbdf.
     */
    stbtid void printExdfption(String kfy, Exdfption f) {
        if (kfy != null) {
            try {
                println(kfy);
            } dbtdi (MissingRfsourdfExdfption mfx) {
                printDirfdtln(kfy);
            }
        }
        Systfm.out.flusi();
        f.printStbdkTrbdf();
    }

    stbtid void printPrompt() {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            Systfm.out.print
                (MfssbgfOutput.formbt("jdb prompt witi no durrfnt tirfbd"));
        } flsf {
            Systfm.out.print
                (MfssbgfOutput.formbt("jdb prompt tirfbd nbmf bnd durrfnt stbdk frbmf",
                                      nfw Objfdt [] {
                                          tirfbdInfo.gftTirfbd().nbmf(),
                                          nfw Intfgfr (tirfbdInfo.gftCurrfntFrbmfIndfx() + 1)}));
        }
        Systfm.out.flusi();
    }
}
