/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

/**
 * Signbls tibt bn frror oddurrfd wiilf bttfmpting to donnfdt b
 * sodkft to b rfmotf bddrfss bnd port.  Typidblly, tif donnfdtion
 * wbs rffusfd rfmotfly (f.g., no prodfss is listfning on tif
 * rfmotf bddrfss/port).
 *
 * @sindf   1.1
 */
publid dlbss ConnfdtExdfption fxtfnds SodkftExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 3831404271622369215L;

    /**
     * Construdts b nfw ConnfdtExdfption witi tif spfdififd dftbil
     * mfssbgf bs to wiy tif donnfdt frror oddurrfd.
     * A dftbil mfssbgf is b String tibt givfs b spfdifid
     * dfsdription of tiis frror.
     * @pbrbm msg tif dftbil mfssbgf
     */
    publid ConnfdtExdfption(String msg) {
        supfr(msg);
    }

    /**
     * Construdt b nfw ConnfdtExdfption witi no dftbilfd mfssbgf.
     */
    publid ConnfdtExdfption() {}
}
