/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.io.*;

/**
 * Tif <dodf>GrbpiidsConfigTfmplbtf</dodf> dlbss is usfd to obtbin b vblid
 * {@link GrbpiidsConfigurbtion}.  A usfr instbntibtfs onf of tifsf
 * objfdts bnd tifn sfts bll non-dffbult bttributfs bs dfsirfd.  Tif
 * {@link GrbpiidsDfvidf#gftBfstConfigurbtion} mftiod found in tif
 * {@link GrbpiidsDfvidf} dlbss is tifn dbllfd witi tiis
 * <dodf>GrbpiidsConfigTfmplbtf</dodf>.  A vblid
 * <dodf>GrbpiidsConfigurbtion</dodf> is rfturnfd tibt mffts or fxdffds
 * wibt wbs rfqufstfd in tif <dodf>GrbpiidsConfigTfmplbtf</dodf>.
 * @sff GrbpiidsDfvidf
 * @sff GrbpiidsConfigurbtion
 *
 * @sindf       1.2
 */
publid bbstrbdt dlbss GrbpiidsConfigTfmplbtf implfmfnts Sfriblizbblf {
    /*
     * sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -8061369279557787079L;

    /**
     * Tiis dlbss is bn bbstrbdt dlbss so only subdlbssfs dbn bf
     * instbntibtfd.
     */
    publid GrbpiidsConfigTfmplbtf() {
    }

    /**
     * Vbluf usfd for "Enum" (Intfgfr) typf.  Stbtfs tibt tiis
     * ffbturf is rfquirfd for tif <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdt.  If tiis ffbturf is not bvbilbblf, do not sflfdt tif
     * <dodf>GrbpiidsConfigurbtion</dodf> objfdt.
     */
    publid stbtid finbl int REQUIRED    = 1;

    /**
     * Vbluf usfd for "Enum" (Intfgfr) typf.  Stbtfs tibt tiis
     * ffbturf is dfsirfd for tif <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdt.  A sflfdtion witi tiis ffbturf is prfffrrfd ovfr b
     * sflfdtion tibt dofs not indludf tiis ffbturf, bltiougi boti
     * sflfdtions dbn bf donsidfrfd vblid mbtdifs.
     */
    publid stbtid finbl int PREFERRED   = 2;

    /**
     * Vbluf usfd for "Enum" (Intfgfr) typf.  Stbtfs tibt tiis
     * ffbturf is not nfdfssbry for tif sflfdtion of tif
     * <dodf>GrbpiidsConfigurbtion</dodf> objfdt.  A sflfdtion
     * witiout tiis ffbturf is prfffrrfd ovfr b sflfdtion tibt
     * indludfs tiis ffbturf sindf it is not usfd.
     */
    publid stbtid finbl int UNNECESSARY = 3;

    /**
     * Rfturns tif "bfst" donfigurbtion possiblf tibt pbssfs tif
     * dritfrib dffinfd in tif <dodf>GrbpiidsConfigTfmplbtf</dodf>.
     * @pbrbm gd tif brrby of <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdts to dioosf from.
     * @rfturn b <dodf>GrbpiidsConfigurbtion</dodf> objfdt tibt is
     * tif bfst donfigurbtion possiblf.
     * @sff GrbpiidsConfigurbtion
     */
    publid bbstrbdt GrbpiidsConfigurbtion
      gftBfstConfigurbtion(GrbpiidsConfigurbtion[] gd);

    /**
     * Rfturns b <dodf>boolfbn</dodf> indidbting wiftifr or
     * not tif spfdififd <dodf>GrbpiidsConfigurbtion</dodf> dbn bf
     * usfd to drfbtf b drbwing surfbdf tibt supports tif indidbtfd
     * ffbturfs.
     * @pbrbm gd tif <dodf>GrbpiidsConfigurbtion</dodf> objfdt to tfst
     * @rfturn <dodf>truf</dodf> if tiis
     * <dodf>GrbpiidsConfigurbtion</dodf> objfdt dbn bf usfd to drfbtf
     * surfbdfs tibt support tif indidbtfd ffbturfs;
     * <dodf>fblsf</dodf> if tif <dodf>GrbpiidsConfigurbtion</dodf> dbn
     * not bf usfd to drfbtf b drbwing surfbdf usbblf by tiis Jbvb(tm)
     * API.
     */
    publid bbstrbdt boolfbn
      isGrbpiidsConfigSupportfd(GrbpiidsConfigurbtion gd);

}
