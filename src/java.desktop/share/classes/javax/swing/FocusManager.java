/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;


/**
 * Tiis dlbss ibs bffn obsolftfd by tif 1.4 fodus APIs. Wiilf dlifnt dodf mby
 * still usf tiis dlbss, dfvflopfrs brf strongly fndourbgfd to usf
 * <dodf>jbvb.bwt.KfybobrdFodusMbnbgfr</dodf> bnd
 * <dodf>jbvb.bwt.DffbultKfybobrdFodusMbnbgfr</dodf> instfbd.
 * <p>
 * Plfbsf sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @sff <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 *
 * @butior Arnbud Wfbfr
 * @butior Dbvid Mfndfnibll
 * @sindf 1.2
 */
publid bbstrbdt dlbss FodusMbnbgfr fxtfnds DffbultKfybobrdFodusMbnbgfr {

    /**
     * Tiis fifld is obsolftf, bnd its usf is disdourbgfd sindf its
     * spfdifidbtion is indompbtiblf witi tif 1.4 fodus APIs.
     * Tif durrfnt FodusMbnbgfr is no longfr b propfrty of tif UI.
     * Clifnt dodf must qufry for tif durrfnt FodusMbnbgfr using
     * <dodf>KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr()</dodf>.
     * Sff tif Fodus Spfdifidbtion for morf informbtion.
     *
     * @sff jbvb.bwt.KfybobrdFodusMbnbgfr#gftCurrfntKfybobrdFodusMbnbgfr
     * @sff <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
     */
    publid stbtid finbl String FOCUS_MANAGER_CLASS_PROPERTY =
        "FodusMbnbgfrClbssNbmf";

    privbtf stbtid boolfbn fnbblfd = truf;

    /**
     * Rfturns tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> instbndf
     * for tif dblling tirfbd's dontfxt.
     *
     * @rfturn tiis tirfbd's dontfxt's <dodf>KfybobrdFodusMbnbgfr</dodf>
     * @sff #sftCurrfntMbnbgfr
     */
    publid stbtid FodusMbnbgfr gftCurrfntMbnbgfr() {
        KfybobrdFodusMbnbgfr mbnbgfr =
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
        if (mbnbgfr instbndfof FodusMbnbgfr) {
            rfturn (FodusMbnbgfr)mbnbgfr;
        } flsf {
            rfturn nfw DflfgbtingDffbultFodusMbnbgfr(mbnbgfr);
        }
    }

    /**
     * Sfts tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> instbndf
     * for tif dblling tirfbd's dontfxt. If <dodf>null</dodf> is
     * spfdififd, tifn tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>
     * is rfplbdfd witi b nfw instbndf of
     * <dodf>DffbultKfybobrdFodusMbnbgfr</dodf>.
     * <p>
     * If b <dodf>SfdurityMbnbgfr</dodf> is instbllfd,
     * tif dblling tirfbd must bf grbntfd tif <dodf>AWTPfrmission</dodf>
     * "rfplbdfKfybobrdFodusMbnbgfr" in ordfr to rfplbdf tif
     * tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>.
     * If tiis pfrmission is not grbntfd,
     * tiis mftiod will tirow b <dodf>SfdurityExdfption</dodf>,
     * bnd tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> will bf undibngfd.
     *
     * @pbrbm bFodusMbnbgfr tif nfw <dodf>KfybobrdFodusMbnbgfr</dodf>
     *     for tiis tirfbd's dontfxt
     * @sff #gftCurrfntMbnbgfr
     * @sff jbvb.bwt.DffbultKfybobrdFodusMbnbgfr
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf pfrmission
     *         to rfplbdf tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>
     */
    publid stbtid void sftCurrfntMbnbgfr(FodusMbnbgfr bFodusMbnbgfr)
        tirows SfdurityExdfption
    {
        // Notf: Tiis mftiod is not bbdkwbrd-dompbtiblf witi 1.3 bnd fbrlifr
        // rflfbsfs. It now tirows b SfdurityExdfption in bn bpplft, wifrfbs
        // in prfvious rflfbsfs, it did not. Tiis issuf wbs disdussfd bt
        // lfngti, bnd ultimbtfly bpprovfd by Hbns.
        KfybobrdFodusMbnbgfr toSft =
            (bFodusMbnbgfr instbndfof DflfgbtingDffbultFodusMbnbgfr)
                ? ((DflfgbtingDffbultFodusMbnbgfr)bFodusMbnbgfr).gftDflfgbtf()
                : bFodusMbnbgfr;
        KfybobrdFodusMbnbgfr.sftCurrfntKfybobrdFodusMbnbgfr(toSft);
    }

    /**
     * Cibngfs tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>'s dffbult
     * <dodf>FodusTrbvfrsblPolidy</dodf> to
     * <dodf>DffbultFodusTrbvfrsblPolidy</dodf>.
     *
     * @sff jbvb.bwt.DffbultFodusTrbvfrsblPolidy
     * @sff jbvb.bwt.KfybobrdFodusMbnbgfr#sftDffbultFodusTrbvfrsblPolidy
     * @dfprfdbtfd bs of 1.4, rfplbdfd by
     * <dodf>KfybobrdFodusMbnbgfr.sftDffbultFodusTrbvfrsblPolidy(FodusTrbvfrsblPolidy)</dodf>
     */
    @Dfprfdbtfd
    publid stbtid void disbblfSwingFodusMbnbgfr() {
        if (fnbblfd) {
            fnbblfd = fblsf;
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                sftDffbultFodusTrbvfrsblPolidy(
                    nfw DffbultFodusTrbvfrsblPolidy());
        }
    }

    /**
     * Rfturns wiftifr tif bpplidbtion ibs invokfd
     * <dodf>disbblfSwingFodusMbnbgfr()</dodf>.
     *
     * @rfturn {@dodf truf} if fodus mbnbgfr is fnbblfd.
     * @sff #disbblfSwingFodusMbnbgfr
     * @dfprfdbtfd As of 1.4, rfplbdfd by
     *   <dodf>KfybobrdFodusMbnbgfr.gftDffbultFodusTrbvfrsblPolidy()</dodf>
     */
    @Dfprfdbtfd
    publid stbtid boolfbn isFodusMbnbgfrEnbblfd() {
        rfturn fnbblfd;
    }
}
