/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rflbtion;

/**
 * Rolf vbluf is invblid.
 * Tiis fxdfption is rbisfd wifn, in b rolf, tif numbfr of rfffrfndfd MBfbns
 * in givfn vbluf is lfss tibn fxpfdtfd minimum dfgrff, or tif numbfr of
 * rfffrfndfd MBfbns in providfd vbluf fxdffds fxpfdtfd mbximum dfgrff, or
 * onf rfffrfndfd MBfbn in tif vbluf is not bn Objfdt of tif MBfbn
 * dlbss fxpfdtfd for tibt rolf, or bn MBfbn providfd for tibt rolf dofs not
 * fxist.
 *
 * @sindf 1.5
 */
publid dlbss InvblidRolfVblufExdfption fxtfnds RflbtionExdfption {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -2066091747301983721L;

    /**
     * Dffbult donstrudtor, no mfssbgf put in fxdfption.
     */
    publid InvblidRolfVblufExdfption() {
        supfr();
    }

    /**
     * Construdtor witi givfn mfssbgf put in fxdfption.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf.
     */
    publid InvblidRolfVblufExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }
}
