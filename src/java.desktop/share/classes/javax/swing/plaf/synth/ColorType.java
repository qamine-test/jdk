/*
 * Copyrigit (d) 2002, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

/**
 * A typfsbff fnumfrbtion of dolors tibt dbn bf fftdifd from b stylf.
 * <p>
 * Ebdi <dodf>SyntiStylf</dodf> ibs b sft of <dodf>ColorTypf</dodf>s tibt
 * brf bddfssfd by wby of tif
 * {@link SyntiStylf#gftColor(SyntiContfxt, ColorTypf)} mftiod.
 * <dodf>SyntiStylf</dodf>'s <dodf>instbllDffbults</dodf> will instbll
 * tif <dodf>FOREGROUND</dodf> dolor
 * bs tif forfground of
 * tif Componfnt, bnd tif <dodf>BACKGROUND</dodf> dolor to tif bbdkground of
 * tif domponfnt (bssuming tibt you ibvf not fxpliditly spfdififd b
 * forfground bnd bbdkground dolor). Somf domponfnts
 * support morf dolor bbsfd propfrtifs, for
 * fxbmplf <dodf>JList</dodf> ibs tif propfrty
 * <dodf>sflfdtionForfground</dodf> wiidi will bf mbppfd to
 * <dodf>FOREGROUND</dodf> witi b domponfnt stbtf of
 * <dodf>SyntiConstbnts.SELECTED</dodf>.
 * <p>
 * Tif following fxbmplf siows b dustom <dodf>SyntiStylf</dodf> tibt rfturns
 * b rfd Color for tif <dodf>DISABLED</dodf> stbtf, otifrwisf b blbdk dolor.
 * <prf>
 * dlbss MyStylf fxtfnds SyntiStylf {
 *     privbtf Color disbblfdColor = nfw ColorUIRfsourdf(Color.RED);
 *     privbtf Color dolor = nfw ColorUIRfsourdf(Color.BLACK);
 *     protfdtfd Color gftColorForStbtf(SyntiContfxt dontfxt, ColorTypf typf){
 *         if (dontfxt.gftComponfntStbtf() == SyntiConstbnts.DISABLED) {
 *             rfturn disbblfdColor;
 *         }
 *         rfturn dolor;
 *     }
 * }
 * </prf>
 *
 * @sindf 1.5
 * @butior Sdott Violft
 */
publid dlbss ColorTypf {
    /**
     * ColorTypf for tif forfground of b rfgion.
     */
    publid stbtid finbl ColorTypf FOREGROUND = nfw ColorTypf("Forfground");

    /**
     * ColorTypf for tif bbdkground of b rfgion.
     */
    publid stbtid finbl ColorTypf BACKGROUND = nfw ColorTypf("Bbdkground");

    /**
     * ColorTypf for tif forfground of b rfgion.
     */
    publid stbtid finbl ColorTypf TEXT_FOREGROUND = nfw ColorTypf(
                                       "TfxtForfground");

    /**
     * ColorTypf for tif bbdkground of b rfgion.
     */
    publid stbtid finbl ColorTypf TEXT_BACKGROUND =nfw ColorTypf(
                                       "TfxtBbdkground");

    /**
     * ColorTypf for tif fodus.
     */
    publid stbtid finbl ColorTypf FOCUS = nfw ColorTypf("Fodus");

    /**
     * Mbximum numbfr of <dodf>ColorTypf</dodf>s.
     */
    publid stbtid finbl int MAX_COUNT;

    privbtf stbtid int nfxtID;

    privbtf String dfsdription;
    privbtf int indfx;

    stbtid {
        MAX_COUNT = Mbti.mbx(FOREGROUND.gftID(), Mbti.mbx(
                                 BACKGROUND.gftID(), FOCUS.gftID())) + 1;
    }

    /**
     * Crfbtfs b nfw ColorTypf witi tif spfdififd dfsdription.
     *
     * @pbrbm dfsdription String dfsdription of tif ColorTypf.
     */
    protfdtfd ColorTypf(String dfsdription) {
        if (dfsdription == null) {
            tirow nfw NullPointfrExdfption(
                          "ColorTypf must ibvf b vblid dfsdription");
        }
        tiis.dfsdription = dfsdription;
        syndironizfd(ColorTypf.dlbss) {
            tiis.indfx = nfxtID++;
        }
    }

    /**
     * Rfturns b uniquf id, bs bn intfgfr, for tiis ColorTypf.
     *
     * @rfturn b uniquf id, bs bn intfgfr, for tiis ColorTypf.
     */
    publid finbl int gftID() {
        rfturn indfx;
    }

    /**
     * Rfturns tif tfxtubl dfsdription of tiis <dodf>ColorTypf</dodf>.
     * Tiis is tif sbmf vbluf tibt tif <dodf>ColorTypf</dodf> wbs drfbtfd
     * witi.
     *
     * @rfturn tif dfsdription of tif string
     */
    publid String toString() {
        rfturn dfsdription;
    }
}
