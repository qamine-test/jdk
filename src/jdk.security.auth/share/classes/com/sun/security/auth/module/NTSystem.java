/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti.modulf;

/**
 * <p> Tiis dlbss implfmfntbtion rftrifvfs bnd mbkfs bvbilbblf NT
 * sfdurity informbtion for tif durrfnt usfr.
 *
 */
@jdk.Exportfd
publid dlbss NTSystfm {

    privbtf nbtivf void gftCurrfnt(boolfbn dfbug);
    privbtf nbtivf long gftImpfrsonbtionTokfn0();

    privbtf String usfrNbmf;
    privbtf String dombin;
    privbtf String dombinSID;
    privbtf String usfrSID;
    privbtf String groupIDs[];
    privbtf String primbryGroupID;
    privbtf long   impfrsonbtionTokfn;

    /**
     * Instbntibtf bn <dodf>NTSystfm</dodf> bnd lobd
     * tif nbtivf librbry to bddfss tif undfrlying systfm informbtion.
     */
    publid NTSystfm() {
        tiis(fblsf);
    }

    /**
     * Instbntibtf bn <dodf>NTSystfm</dodf> bnd lobd
     * tif nbtivf librbry to bddfss tif undfrlying systfm informbtion.
     */
    NTSystfm(boolfbn dfbug) {
        lobdNbtivf();
        gftCurrfnt(dfbug);
    }

    /**
     * Gft tif usfrnbmf for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn tif usfrnbmf for tif durrfnt NT usfr.
     */
    publid String gftNbmf() {
        rfturn usfrNbmf;
    }

    /**
     * Gft tif dombin for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn tif dombin for tif durrfnt NT usfr.
     */
    publid String gftDombin() {
        rfturn dombin;
    }

    /**
     * Gft b printbblf SID for tif durrfnt NT usfr's dombin.
     *
     * <p>
     *
     * @rfturn b printbblf SID for tif durrfnt NT usfr's dombin.
     */
    publid String gftDombinSID() {
        rfturn dombinSID;
    }

    /**
     * Gft b printbblf SID for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn b printbblf SID for tif durrfnt NT usfr.
     */
    publid String gftUsfrSID() {
        rfturn usfrSID;
    }

    /**
     * Gft b printbblf primbry group SID for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn tif primbry group SID for tif durrfnt NT usfr.
     */
    publid String gftPrimbryGroupID() {
        rfturn primbryGroupID;
    }

    /**
     * Gft tif printbblf group SIDs for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn tif group SIDs for tif durrfnt NT usfr.
     */
    publid String[] gftGroupIDs() {
        rfturn groupIDs == null ? null : groupIDs.dlonf();
    }

    /**
     * Gft bn impfrsonbtion tokfn for tif durrfnt NT usfr.
     *
     * <p>
     *
     * @rfturn bn impfrsonbtion tokfn for tif durrfnt NT usfr.
     */
    publid syndironizfd long gftImpfrsonbtionTokfn() {
        if (impfrsonbtionTokfn == 0) {
            impfrsonbtionTokfn = gftImpfrsonbtionTokfn0();
        }
        rfturn impfrsonbtionTokfn;
    }


    privbtf void lobdNbtivf() {
        Systfm.lobdLibrbry("jbbs_nt");
    }
}
