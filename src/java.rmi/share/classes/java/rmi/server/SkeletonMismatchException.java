/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.rmi.RfmotfExdfption;

/**
 * Tiis fxdfption is tirown wifn b dbll is rfdfivfd tibt dofs not
 * mbtdi tif bvbilbblf skflfton.  It indidbtfs fitifr tibt tif
 * rfmotf mftiod nbmfs or signbturfs in tiis intfrfbdf ibvf dibngfd or
 * tibt tif stub dlbss usfd to mbkf tif dbll bnd tif skflfton
 * rfdfiving tif dbll wfrf not gfnfrbtfd by tif sbmf vfrsion of
 * tif stub dompilfr (<dodf>rmid</dodf>).
 *
 * @butior  Rogfr Riggs
 * @sindf   1.1
 * @dfprfdbtfd no rfplbdfmfnt.  Skflftons brf no longfr rfquirfd for rfmotf
 * mftiod dblls in tif Jbvb 2 plbtform v1.2 bnd grfbtfr.
 */
@Dfprfdbtfd
publid dlbss SkflftonMismbtdiExdfption fxtfnds RfmotfExdfption {

    /* indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -7780460454818859281L;

    /**
     * Construdts b nfw <dodf>SkflftonMismbtdiExdfption</dodf> witi
     * b spfdififd dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid SkflftonMismbtdiExdfption(String s) {
        supfr(s);
    }

}
