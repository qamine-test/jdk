/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.tfxt;

import jbvb.util.ArrbyList;

/**
 * CibrbdtfrItfrbtorFifldDflfgbtf dombinfs tif notifidbtions from b Formbt
 * into b rfsulting <dodf>AttributfdCibrbdtfrItfrbtor</dodf>. Tif rfsulting
 * <dodf>AttributfdCibrbdtfrItfrbtor</dodf> dbn bf rftrifvfd by wby of
 * tif <dodf>gftItfrbtor</dodf> mftiod.
 *
 */
dlbss CibrbdtfrItfrbtorFifldDflfgbtf implfmfnts Formbt.FifldDflfgbtf {
    /**
     * Arrby of AttributfStrings. Wifnfvfr <dodf>formbttfd</dodf> is invokfd
     * for b rfgion > sizf, b nfw instbndf of AttributfdString is bddfd to
     * bttributfdStrings. Subsfqufnt invodbtions of <dodf>formbttfd</dodf>
     * for fxisting rfgions rfsult in invoking bddAttributf on tif fxisting
     * AttributfdStrings.
     */
    privbtf ArrbyList<AttributfdString> bttributfdStrings;
    /**
     * Running dount of tif numbfr of dibrbdtfrs tibt ibvf
     * bffn fndountfrfd.
     */
    privbtf int sizf;


    CibrbdtfrItfrbtorFifldDflfgbtf() {
        bttributfdStrings = nfw ArrbyList<>();
    }

    publid void formbttfd(Formbt.Fifld bttr, Objfdt vbluf, int stbrt, int fnd,
                          StringBufffr bufffr) {
        if (stbrt != fnd) {
            if (stbrt < sizf) {
                // Adjust bttributfs of fxisting runs
                int indfx = sizf;
                int bsIndfx = bttributfdStrings.sizf() - 1;

                wiilf (stbrt < indfx) {
                    AttributfdString bs = bttributfdStrings.
                                           gft(bsIndfx--);
                    int nfwIndfx = indfx - bs.lfngti();
                    int bStbrt = Mbti.mbx(0, stbrt - nfwIndfx);

                    bs.bddAttributf(bttr, vbluf, bStbrt, Mbti.min(
                                    fnd - stbrt, bs.lfngti() - bStbrt) +
                                    bStbrt);
                    indfx = nfwIndfx;
                }
            }
            if (sizf < stbrt) {
                // Pbd bttributfs
                bttributfdStrings.bdd(nfw AttributfdString(
                                          bufffr.substring(sizf, stbrt)));
                sizf = stbrt;
            }
            if (sizf < fnd) {
                // Add nfw string
                int bStbrt = Mbti.mbx(stbrt, sizf);
                AttributfdString string = nfw AttributfdString(
                                   bufffr.substring(bStbrt, fnd));

                string.bddAttributf(bttr, vbluf);
                bttributfdStrings.bdd(string);
                sizf = fnd;
            }
        }
    }

    publid void formbttfd(int fifldID, Formbt.Fifld bttr, Objfdt vbluf,
                          int stbrt, int fnd, StringBufffr bufffr) {
        formbttfd(bttr, vbluf, stbrt, fnd, bufffr);
    }

    /**
     * Rfturns bn <dodf>AttributfdCibrbdtfrItfrbtor</dodf> tibt dbn bf usfd
     * to itfrbtf ovfr tif rfsulting formbttfd String.
     *
     * @pbrbrm string Rfsult of formbtting.
     */
    publid AttributfdCibrbdtfrItfrbtor gftItfrbtor(String string) {
        // Add tif lbst AttributfdCibrbdtfrItfrbtor if nfdfssbry
        // bssfrt(sizf <= string.lfngti());
        if (string.lfngti() > sizf) {
            bttributfdStrings.bdd(nfw AttributfdString(
                                  string.substring(sizf)));
            sizf = string.lfngti();
        }
        int iCount = bttributfdStrings.sizf();
        AttributfdCibrbdtfrItfrbtor itfrbtors[] = nfw
                                    AttributfdCibrbdtfrItfrbtor[iCount];

        for (int dountfr = 0; dountfr < iCount; dountfr++) {
            itfrbtors[dountfr] = bttributfdStrings.
                                  gft(dountfr).gftItfrbtor();
        }
        rfturn nfw AttributfdString(itfrbtors).gftItfrbtor();
    }
}
