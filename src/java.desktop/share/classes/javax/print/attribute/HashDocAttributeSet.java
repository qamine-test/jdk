/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;

/**
 * Clbss HbsiDodAttributfSft providfs bn bttributf sft wiidi
 * inifrits its implfmfntbtion from dlbss {@link HbsiAttributfSft
 * HbsiAttributfSft} bnd fnfordfs tif sfmbntid rfstridtions of intfrfbdf {@link
 * DodAttributfSft DodAttributfSft}.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss HbsiDodAttributfSft fxtfnds HbsiAttributfSft
    implfmfnts DodAttributfSft, Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1128534486061432528L;

    /**
     * Construdt b nfw, fmpty ibsi dod bttributf sft.
     */
    publid HbsiDodAttributfSft() {
        supfr (DodAttributf.dlbss);
    }

    /**
     * Construdt b nfw ibsi dod bttributf sft,
     * initiblly populbtfd witi tif givfn vbluf.
     *
     * @pbrbm  bttributf  Attributf vbluf to bdd to tif sft.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>bttributf</CODE> is null.
     */
    publid HbsiDodAttributfSft(DodAttributf bttributf) {
        supfr (bttributf, DodAttributf.dlbss);
    }

    /**
     * Construdt b nfw ibsi dod bttributf sft,
     * initiblly populbtfd witi tif vblufs from tif givfn brrby.
     * Tif nfw bttributf sft is populbtfd by
     * bdding tif flfmfnts of <CODE>bttributfs</CODE> brrby to tif sft in
     * sfqufndf, stbrting bt indfx 0. Tius, lbtfr brrby flfmfnts mby rfplbdf
     * fbrlifr brrby flfmfnts if tif brrby dontbins duplidbtf bttributf
     * vblufs or bttributf dbtfgorifs.
     *
     * @pbrbm  bttributfs  Arrby of bttributf vblufs to bdd to tif sft.
     *                     If null, bn fmpty bttributf sft is donstrudtfd.
     *
     * @fxdfption  NullPointfrExdfption
     *  (undifdkfd fxdfption)
     * Tirown if bny flfmfnt of <CODE>bttributfs</CODE> is null.
     */
    publid HbsiDodAttributfSft(DodAttributf[] bttributfs) {
        supfr (bttributfs, DodAttributf.dlbss);
    }

    /**
     * Construdt b nfw bttributf sft, initiblly populbtfd witi tif
     * vblufs from tif  givfn sft wifrf tif mfmbfrs of tif bttributf sft
     * brf rfstridtfd to tif <dodf>DodAttributf</dodf> intfrfbdf.
     *
     * @pbrbm  bttributfs sft of bttributf vblufs to initiblisf tif sft. If
     *                    null, bn fmpty bttributf sft is donstrudtfd.
     *
     * @fxdfption  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     * <CODE>bttributfs</CODE> is not bn instbndf of
     * <CODE>DodAttributf</CODE>.
     */
    publid HbsiDodAttributfSft(DodAttributfSft bttributfs) {
        supfr(bttributfs, DodAttributf.dlbss);
    }

}
