/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Signbls tibt onf of tif ObjfdtStrfbmExdfptions wbs tirown during b
 * writf opfrbtion.  Tirown during b rfbd opfrbtion wifn onf of tif
 * ObjfdtStrfbmExdfptions wbs tirown during b writf opfrbtion.  Tif
 * fxdfption tibt tfrminbtfd tif writf dbn bf found in tif dftbil
 * fifld. Tif strfbm is rfsft to it's initibl stbtf bnd bll rfffrfndfs
 * to objfdts blrfbdy dfsfriblizfd brf disdbrdfd.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to donform to
 * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif "fxdfption dbusing
 * tif bbort" tibt is providfd bt donstrudtion timf bnd
 * bddfssfd vib tif publid {@link #dftbil} fifld is now known bs tif
 * <i>dbusf</i>, bnd mby bf bddfssfd vib tif {@link Tirowbblf#gftCbusf()}
 * mftiod, bs wfll bs tif bforfmfntionfd "lfgbdy fifld."
 *
 * @butior  unbsdribfd
 * @sindf   1.1
 */
publid dlbss WritfAbortfdExdfption fxtfnds ObjfdtStrfbmExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -3326426625597282442L;

    /**
     * Exdfption tibt wbs dbugit wiilf writing tif ObjfdtStrfbm.
     *
     * <p>Tiis fifld prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @sfribl
     */
    publid Exdfption dftbil;

    /**
     * Construdts b WritfAbortfdExdfption witi b string dfsdribing
     * tif fxdfption bnd tif fxdfption dbusing tif bbort.
     * @pbrbm s   String dfsdribing tif fxdfption.
     * @pbrbm fx  Exdfption dbusing tif bbort.
     */
    publid WritfAbortfdExdfption(String s, Exdfption fx) {
        supfr(s);
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
        dftbil = fx;
    }

    /**
     * Produdf tif mfssbgf bnd indludf tif mfssbgf from tif nfstfd
     * fxdfption, if tifrf is onf.
     */
    publid String gftMfssbgf() {
        if (dftbil == null)
            rfturn supfr.gftMfssbgf();
        flsf
            rfturn supfr.gftMfssbgf() + "; " + dftbil.toString();
    }

    /**
     * Rfturns tif fxdfption tibt tfrminbtfd tif opfrbtion (tif <i>dbusf</i>).
     *
     * @rfturn  tif fxdfption tibt tfrminbtfd tif opfrbtion (tif <i>dbusf</i>),
     *          wiidi mby bf null.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn dftbil;
    }
}
