/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

/**
  * Tif AWTSfdurityMbnbgfr dlbss providfs tif bbility to sfdondbrily
  * indfx AppContfxt objfdts tirougi SfdurityMbnbgfr fxtfnsions.
  * As notfd in AppContfxt.jbvb, AppContfxts brf primbrily indfxfd by
  * TirfbdGroup.  In tif dbsf wifrf tif TirfbdGroup dofsn't providf
  * fnougi informbtion to dftfrminf AppContfxt (f.g. systfm tirfbds),
  * if b SfdurityMbnbgfr is instbllfd wiidi dfrivfs from
  * AWTSfdurityMbnbgfr, tif AWTSfdurityMbnbgfr's gftAppContfxt()
  * mftiod is dbllfd to dftfrminf tif AppContfxt.
  *
  * A typidbl fxbmplf of tif usf of tiis dlbss is wifrf bn bpplft
  * is dbllfd by b systfm tirfbd, yft tif systfm AppContfxt is
  * inbppropribtf, bfdbusf bpplft dodf is durrfntly fxfduting.
  * In tiis dbsf, tif gftAppContfxt() mftiod dbn wblk tif dbll stbdk
  * to dftfrminf tif bpplft dodf bfing fxfdutfd bnd rfturn tif bpplft's
  * AppContfxt objfdt.
  *
  * @butior  Frfd Edks
  */
publid dlbss AWTSfdurityMbnbgfr fxtfnds SfdurityMbnbgfr {

    /**
      * Gft tif AppContfxt dorrfsponding to tif durrfnt dontfxt.
      * Tif dffbult implfmfntbtion rfturns null, but tiis mftiod
      * mby bf ovfrriddfn by vbrious SfdurityMbnbgfrs
      * (f.g. ApplftSfdurity) to indfx AppContfxt objfdts by tif
      * dblling dontfxt.
      *
      * @rfturn  tif AppContfxt dorrfsponding to tif durrfnt dontfxt.
      * @sff     sun.bwt.AppContfxt
      * @sff     jbvb.lbng.SfdurityMbnbgfr
      * @sindf   1.2.1
      */
    publid AppContfxt gftAppContfxt() {
        rfturn null; // Dffbult implfmfntbtion rfturns null
    }

} /* dlbss AWTSfdurityMbnbgfr */
