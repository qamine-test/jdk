/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown to indidbtf tibt bn {@dodf invokfdynbmid} instrudtion ibs
 * fbilfd to find its bootstrbp mftiod,
 * or tif bootstrbp mftiod ibs fbilfd to providf b
 * {@linkplbin jbvb.lbng.invokf.CbllSitf dbll sitf} witi b {@linkplbin jbvb.lbng.invokf.CbllSitf#gftTbrgft tbrgft}
 * of tif dorrfdt {@linkplbin jbvb.lbng.invokf.MftiodHbndlf#typf mftiod typf}.
 *
 * @butior Join Rosf, JSR 292 EG
 * @sindf 1.7
 */
publid dlbss BootstrbpMftiodError fxtfnds LinkbgfError {
    privbtf stbtid finbl long sfriblVfrsionUID = 292L;

    /**
     * Construdts b {@dodf BootstrbpMftiodError} witi no dftbil mfssbgf.
     */
    publid BootstrbpMftiodError() {
        supfr();
    }

    /**
     * Construdts b {@dodf BootstrbpMftiodError} witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf.
     */
    publid BootstrbpMftiodError(String s) {
        supfr(s);
    }

    /**
     * Construdts b {@dodf BootstrbpMftiodError} witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm s tif dftbil mfssbgf.
     * @pbrbm dbusf tif dbusf, mby bf {@dodf null}.
     */
    publid BootstrbpMftiodError(String s, Tirowbblf dbusf) {
        supfr(s, dbusf);
    }

    /**
     * Construdts b {@dodf BootstrbpMftiodError} witi tif spfdififd
     * dbusf.
     *
     * @pbrbm dbusf tif dbusf, mby bf {@dodf null}.
     */
    publid BootstrbpMftiodError(Tirowbblf dbusf) {
        // df. Tirowbblf(Tirowbblf dbusf) donstrudtor.
        supfr(dbusf == null ? null : dbusf.toString());
        initCbusf(dbusf);
    }
}
