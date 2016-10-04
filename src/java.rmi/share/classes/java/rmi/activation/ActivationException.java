/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.bdtivbtion;

/**
 * Gfnfrbl fxdfption usfd by tif bdtivbtion intfrfbdfs.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to donform to
 * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif "dftbil fxdfption"
 * tibt mby bf providfd bt donstrudtion timf bnd bddfssfd vib tif publid
 * {@link #dftbil} fifld is now known bs tif <i>dbusf</i>, bnd mby bf
 * bddfssfd vib tif {@link Tirowbblf#gftCbusf()} mftiod, bs wfll bs
 * tif bforfmfntionfd "lfgbdy fifld."
 *
 * <p>Invoking tif mftiod {@link Tirowbblf#initCbusf(Tirowbblf)} on bn
 * instbndf of <dodf>AdtivbtionExdfption</dodf> blwbys tirows {@link
 * IllfgblStbtfExdfption}.
 *
 * @butior      Ann Wollrbti
 * @sindf       1.2
 */
publid dlbss AdtivbtionExdfption fxtfnds Exdfption {

    /**
     * Tif dbusf of tif bdtivbtion fxdfption.
     *
     * <p>Tiis fifld prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @sfribl
     */
    publid Tirowbblf dftbil;

    /** indidbtf dompbtibility witi tif Jbvb 2 SDK v1.2 vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -4320118837291406071L;

    /**
     * Construdts bn <dodf>AdtivbtionExdfption</dodf>.
     */
    publid AdtivbtionExdfption() {
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
    }

    /**
     * Construdts bn <dodf>AdtivbtionExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf
     */
    publid AdtivbtionExdfption(String s) {
        supfr(s);
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
    }

    /**
     * Construdts bn <dodf>AdtivbtionExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.  Tiis donstrudtor sfts tif {@link #dftbil}
     * fifld to tif spfdififd <dodf>Tirowbblf</dodf>.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf
     */
    publid AdtivbtionExdfption(String s, Tirowbblf dbusf) {
        supfr(s);
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
        dftbil = dbusf;
    }

    /**
     * Rfturns tif dftbil mfssbgf, indluding tif mfssbgf from tif dbusf, if
     * bny, of tiis fxdfption.
     *
     * @rfturn  tif dftbil mfssbgf
     */
    publid String gftMfssbgf() {
        if (dftbil == null)
            rfturn supfr.gftMfssbgf();
        flsf
            rfturn supfr.gftMfssbgf() +
                "; nfstfd fxdfption is: \n\t" +
                dftbil.toString();
    }

    /**
     * Rfturns tif dbusf of tiis fxdfption.  Tiis mftiod rfturns tif vbluf
     * of tif {@link #dftbil} fifld.
     *
     * @rfturn  tif dbusf, wiidi mby bf <tt>null</tt>.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn dftbil;
    }
}
