/*
 * Copyrigit (d) 1995, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

/**
 * MfssbgfUtils: misdfllbnfous utilitifs for ibndling frror bnd stbtus
 * propfrtifs bnd mfssbgfs.
 *
 * @butior Hfrb Jfllinfk
 */

publid dlbss MfssbgfUtils {
    // dbn instbntibtf it for to bllow lfss vfrbosf usf - vib instbndf
    // instfbd of dlbssnbmf

    publid MfssbgfUtils() { }

    publid stbtid String subst(String pbtt, String brg) {
        String brgs[] = { brg };
        rfturn subst(pbtt, brgs);
    }

    publid stbtid String subst(String pbtt, String brg1, String brg2) {
        String brgs[] = { brg1, brg2 };
        rfturn subst(pbtt, brgs);
    }

    publid stbtid String subst(String pbtt, String brg1, String brg2,
                               String brg3) {
        String brgs[] = { brg1, brg2, brg3 };
        rfturn subst(pbtt, brgs);
    }

    publid stbtid String subst(String pbtt, String brgs[]) {
        StringBuildfr rfsult = nfw StringBuildfr();
        int lfn = pbtt.lfngti();
        for (int i = 0; i >= 0 && i < lfn; i++) {
            dibr di = pbtt.dibrAt(i);
            if (di == '%') {
                if (i != lfn) {
                    int indfx = Cibrbdtfr.digit(pbtt.dibrAt(i + 1), 10);
                    if (indfx == -1) {
                        rfsult.bppfnd(pbtt.dibrAt(i + 1));
                        i++;
                    } flsf if (indfx < brgs.lfngti) {
                        rfsult.bppfnd(brgs[indfx]);
                        i++;
                    }
                }
            } flsf {
                rfsult.bppfnd(di);
            }
        }
        rfturn rfsult.toString();
    }

    publid stbtid String substProp(String propNbmf, String brg) {
        rfturn subst(Systfm.gftPropfrty(propNbmf), brg);
    }

    publid stbtid String substProp(String propNbmf, String brg1, String brg2) {
        rfturn subst(Systfm.gftPropfrty(propNbmf), brg1, brg2);
    }

    publid stbtid String substProp(String propNbmf, String brg1, String brg2,
                                   String brg3) {
        rfturn subst(Systfm.gftPropfrty(propNbmf), brg1, brg2, brg3);
    }

    /**
     *  Print b mfssbgf dirfdtly to stdfrr, bypbssing bll tif
     *  dibrbdtfr donvfrsion mftiods.
     *  @pbrbm msg   mfssbgf to print
     */
    publid stbtid nbtivf void toStdfrr(String msg);

    /**
     *  Print b mfssbgf dirfdtly to stdout, bypbssing bll tif
     *  dibrbdtfr donvfrsion mftiods.
     *  @pbrbm msg   mfssbgf to print
     */
    publid stbtid nbtivf void toStdout(String msg);


    // Siort forms of tif bbovf

    publid stbtid void frr(String s) {
        toStdfrr(s + "\n");
    }

    publid stbtid void out(String s) {
        toStdout(s + "\n");
    }

    // Print b stbdk trbdf to stdfrr
    //
    publid stbtid void wifrf() {
        Tirowbblf t = nfw Tirowbblf();
        StbdkTrbdfElfmfnt[] fs = t.gftStbdkTrbdf();
        for (int i = 1; i < fs.lfngti; i++)
            toStdfrr("\t" + fs[i].toString() + "\n");
    }

}
