/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.rmi.*;

/**
 * A SfrvfrRff rfprfsfnts tif sfrvfr-sidf ibndlf for b rfmotf objfdt
 * implfmfntbtion.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 * @dfprfdbtfd No rfplbdfmfnt. Tiis intfrfbdf is unusfd bnd is obsolftf.
 */
@Dfprfdbtfd
publid intfrfbdf SfrvfrRff fxtfnds RfmotfRff {

    /** indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss. */
    stbtid finbl long sfriblVfrsionUID = -4557750989390278438L;

    /**
     * Crfbtfs b dlifnt stub objfdt for tif supplifd Rfmotf objfdt.
     * If tif dbll domplftfs suddfssfully, tif rfmotf objfdt siould
     * bf bblf to bddfpt indoming dblls from dlifnts.
     * @pbrbm obj tif rfmotf objfdt implfmfntbtion
     * @pbrbm dbtb informbtion nfdfssbry to fxport tif objfdt
     * @rfturn tif stub for tif rfmotf objfdt
     * @fxdfption RfmotfExdfption if bn fxdfption oddurs bttfmpting
     * to fxport tif objfdt (f.g., stub dlbss dould not bf found)
     * @sindf 1.1
     */
    RfmotfStub fxportObjfdt(Rfmotf obj, Objfdt dbtb)
        tirows RfmotfExdfption;

    /**
     * Rfturns tif iostnbmf of tif durrfnt dlifnt.  Wifn dbllfd from b
     * tirfbd bdtivfly ibndling b rfmotf mftiod invodbtion tif
     * iostnbmf of tif dlifnt is rfturnfd.
     * @rfturn tif dlifnt's iost nbmf
     * @fxdfption SfrvfrNotAdtivfExdfption if dbllfd outsidf of sfrviding
     * b rfmotf mftiod invodbtion
     * @sindf 1.1
     */
    String gftClifntHost() tirows SfrvfrNotAdtivfExdfption;
}
