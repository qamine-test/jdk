/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;


import jbvb.util.*;
import jbvb.bwt.*;
import jbvbx.swing.tfxt.*;


/**
 * <P>Tif AddfssiblfExtfndfdTfxt intfrfbdf dontbins bdditionbl mftiods
 * not providfd by tif AddfssiblfTfxt intfrfbdf
 *
 * Applidbtions dbn dftfrminf if bn objfdt supports tif AddfssiblfExtfndfdTfxt
 * intfrfbdf by first obtbining its AddfssiblfContfxt (sff {@link Addfssiblf})
 * bnd tifn dblling tif {@link AddfssiblfContfxt#gftAddfssiblfTfxt} mftiod of
 * AddfssiblfContfxt.  If tif rfturn vbluf is bn instbndf of
 * AddfssiblfExtfndfdTfxt, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfTfxt
 *
 * @butior       Pftfr Korn
 * @butior       Lynn Monsbnto
 * @sindf 1.5
 */
publid intfrfbdf AddfssiblfExtfndfdTfxt {

    /**
     * Constbnt usfd to indidbtf tibt tif pbrt of tif tfxt tibt siould bf
     * rftrifvfd is b linf of tfxt.
     *
     * @sff AddfssiblfTfxt#gftAtIndfx
     * @sff AddfssiblfTfxt#gftAftfrIndfx
     * @sff AddfssiblfTfxt#gftBfforfIndfx
     */
    publid stbtid finbl int LINE = 4; // BugID: 4849720

    /**
     * Constbnt usfd to indidbtf tibt tif pbrt of tif tfxt tibt siould bf
     * rftrifvfd is dontiguous tfxt witi tif sbmf tfxt bttributfs.
     *
     * @sff AddfssiblfTfxt#gftAtIndfx
     * @sff AddfssiblfTfxt#gftAftfrIndfx
     * @sff AddfssiblfTfxt#gftBfforfIndfx
     */
    publid stbtid finbl int ATTRIBUTE_RUN = 5; // BugID: 4849720

    /**
     * Rfturns tif tfxt bftwffn two indidfs
     *
     * @pbrbm stbrtIndfx tif stbrt indfx in tif tfxt
     * @pbrbm fndIndfx tif fnd indfx in tif tfxt
     * @rfturn tif tfxt string if tif indidfs brf vblid.
     * Otifrwisf, null is rfturnfd.
     */
    publid String gftTfxtRbngf(int stbrtIndfx, int fndIndfx);

    /**
     * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bt b givfn indfx.
     *
     * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
     * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf>
     * to rftrifvf
     * @pbrbm indfx bn indfx witiin tif tfxt
     * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
     * if pbrt bnd indfx brf vblid.  Otifrwisf, null is rfturnfd.
     *
     * @sff AddfssiblfTfxt#CHARACTER
     * @sff AddfssiblfTfxt#WORD
     * @sff AddfssiblfTfxt#SENTENCE
     */
    publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAt(int pbrt, int indfx);

    /**
     * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bftfr b givfn indfx.
     *
     * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
     * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf>
     * to rftrifvf
     * @pbrbm indfx bn indfx witiin tif tfxt
     * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
     * if pbrt bnd indfx brf vblid.  Otifrwisf, null is rfturnfd.
     *
     * @sff AddfssiblfTfxt#CHARACTER
     * @sff AddfssiblfTfxt#WORD
     * @sff AddfssiblfTfxt#SENTENCE
     */
    publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAftfr(int pbrt, int indfx);

    /**
     * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bfforf b givfn indfx.
     *
     * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
     * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf>
     * to rftrifvf
     * @pbrbm indfx bn indfx witiin tif tfxt
     * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
     * if pbrt bnd indfx brf vblid.  Otifrwisf, null is rfturnfd.
     *
     * @sff AddfssiblfTfxt#CHARACTER
     * @sff AddfssiblfTfxt#WORD
     * @sff AddfssiblfTfxt#SENTENCE
     */
    publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfBfforf(int pbrt, int indfx);

    /**
     * Rfturns tif bounding rfdtbnglf of tif tfxt bftwffn two indidfs.
     *
     * @pbrbm stbrtIndfx tif stbrt indfx in tif tfxt
     * @pbrbm fndIndfx tif fnd indfx in tif tfxt
     * @rfturn tif bounding rfdtbnglf of tif tfxt if tif indidfs brf vblid.
     * Otifrwisf, null is rfturnfd.
     */
    publid Rfdtbnglf gftTfxtBounds(int stbrtIndfx, int fndIndfx);
}
