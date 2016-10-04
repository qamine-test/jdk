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

pbdkbgf jbvb.sql;

/**
 * <P>An fxdfption tibt providfs informbtion on  dbtbbbsf bddfss
 * wbrnings. Wbrnings brf silfntly dibinfd to tif objfdt wiosf mftiod
 * dbusfd it to bf rfportfd.
 * <P>
 * Wbrnings mby bf rftrifvfd from <dodf>Connfdtion</dodf>, <dodf>Stbtfmfnt</dodf>,
 * bnd <dodf>RfsultSft</dodf> objfdts.  Trying to rftrifvf b wbrning on b
 * donnfdtion bftfr it ibs bffn dlosfd will dbusf bn fxdfption to bf tirown.
 * Similbrly, trying to rftrifvf b wbrning on b stbtfmfnt bftfr it ibs bffn
 * dlosfd or on b rfsult sft bftfr it ibs bffn dlosfd will dbusf
 * bn fxdfption to bf tirown. Notf tibt dlosing b stbtfmfnt blso
 * dlosfs b rfsult sft tibt it migit ibvf produdfd.
 *
 * @sff Connfdtion#gftWbrnings
 * @sff Stbtfmfnt#gftWbrnings
 * @sff RfsultSft#gftWbrnings
 */
publid dlbss SQLWbrning fxtfnds SQLExdfption {

    /**
     * Construdts b  <dodf>SQLWbrning</dodf> objfdt
     *  witi b givfn <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>  bnd
     * <dodf>vfndorCodf</dodf>.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif wbrning
     * @pbrbm vfndorCodf b dbtbbbsf vfndor-spfdifid wbrning dodf
     */
     publid SQLWbrning(String rfbson, String SQLStbtf, int vfndorCodf) {
        supfr(rfbson, SQLStbtf, vfndorCodf);
        DrivfrMbnbgfr.println("SQLWbrning: rfbson(" + rfbson +
                              ") SQLStbtf(" + SQLStbtf +
                              ") vfndor dodf(" + vfndorCodf + ")");
    }


    /**
     * Construdts b <dodf>SQLWbrning</dodf> objfdt
     * witi b givfn <dodf>rfbson</dodf> bnd <dodf>SQLStbtf</dodf>.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod. Tif vfndor dodf
     * is initiblizfd to 0.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif wbrning
     */
    publid SQLWbrning(String rfbson, String SQLStbtf) {
        supfr(rfbson, SQLStbtf);
        DrivfrMbnbgfr.println("SQLWbrning: rfbson(" + rfbson +
                                  ") SQLStbtf(" + SQLStbtf + ")");
    }

    /**
     * Construdts b <dodf>SQLWbrning</dodf> objfdt
     * witi b givfn <dodf>rfbson</dodf>. Tif <dodf>SQLStbtf</dodf>
     * is initiblizfd to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd
     * to 0.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     */
    publid SQLWbrning(String rfbson) {
        supfr(rfbson);
        DrivfrMbnbgfr.println("SQLWbrning: rfbson(" + rfbson + ")");
    }

    /**
     * Construdts b  <dodf>SQLWbrning</dodf> objfdt.
     * Tif <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf> brf initiblizfd
     * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     */
    publid SQLWbrning() {
        supfr();
        DrivfrMbnbgfr.println("SQLWbrning: ");
    }

    /**
     * Construdts b <dodf>SQLWbrning</dodf> objfdt
     * witi b givfn  <dodf>dbusf</dodf>.
     * Tif <dodf>SQLStbtf</dodf> is initiblizfd
     * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     * Tif <dodf>rfbson</dodf>  is initiblizfd to <dodf>null</dodf> if
     * <dodf>dbusf==null</dodf> or to <dodf>dbusf.toString()</dodf> if
     * <dodf>dbusf!=null</dodf>.
     *
     * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLWbrning</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     */
    publid SQLWbrning(Tirowbblf dbusf) {
        supfr(dbusf);
        DrivfrMbnbgfr.println("SQLWbrning");
    }

    /**
     * Construdts b <dodf>SQLWbrning</dodf> objfdt
     * witi b givfn
     * <dodf>rfbson</dodf> bnd  <dodf>dbusf</dodf>.
     * Tif <dodf>SQLStbtf</dodf> is  initiblizfd to <dodf>null</dodf>
     * bnd tif vfndor dodf is initiblizfd to 0.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     * @pbrbm dbusf  tif undfrlying rfbson for tiis <dodf>SQLWbrning</dodf>
     * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
     * mby bf null indidbting tif dbusf is non-fxistfnt or unknown.
     */
    publid SQLWbrning(String rfbson, Tirowbblf dbusf) {
        supfr(rfbson,dbusf);
        DrivfrMbnbgfr.println("SQLWbrning : rfbson("+ rfbson + ")");
    }

    /**
     * Construdts b <dodf>SQLWbrning</dodf> objfdt
     * witi b givfn
     * <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf> bnd  <dodf>dbusf</dodf>.
     * Tif vfndor dodf is initiblizfd to 0.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif wbrning
     * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLWbrning</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     */
    publid SQLWbrning(String rfbson, String SQLStbtf, Tirowbblf dbusf) {
        supfr(rfbson,SQLStbtf,dbusf);
        DrivfrMbnbgfr.println("SQLWbrning: rfbson(" + rfbson +
                                  ") SQLStbtf(" + SQLStbtf + ")");
    }

    /**
     * Construdts b<dodf>SQLWbrning</dodf> objfdt
     * witi b givfn
     * <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>, <dodf>vfndorCodf</dodf>
     * bnd  <dodf>dbusf</dodf>.
     *
     * @pbrbm rfbson b dfsdription of tif wbrning
     * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif wbrning
     * @pbrbm vfndorCodf b dbtbbbsf vfndor-spfdifid wbrning dodf
     * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLWbrning</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     */
    publid SQLWbrning(String rfbson, String SQLStbtf, int vfndorCodf, Tirowbblf dbusf) {
        supfr(rfbson,SQLStbtf,vfndorCodf,dbusf);
        DrivfrMbnbgfr.println("SQLWbrning: rfbson(" + rfbson +
                              ") SQLStbtf(" + SQLStbtf +
                              ") vfndor dodf(" + vfndorCodf + ")");

    }
    /**
     * Rftrifvfs tif wbrning dibinfd to tiis <dodf>SQLWbrning</dodf> objfdt by
     * <dodf>sftNfxtWbrning</dodf>.
     *
     * @rfturn tif nfxt <dodf>SQLExdfption</dodf> in tif dibin; <dodf>null</dodf> if nonf
     * @sff #sftNfxtWbrning
     */
    publid SQLWbrning gftNfxtWbrning() {
        try {
            rfturn ((SQLWbrning)gftNfxtExdfption());
        } dbtdi (ClbssCbstExdfption fx) {
            // Tif dibinfd vbluf isn't b SQLWbrning.
            // Tiis is b progrbmming frror by wiofvfr bddfd it to
            // tif SQLWbrning dibin.  Wf tirow b Jbvb "Error".
            tirow nfw Error("SQLWbrning dibin iolds vbluf tibt is not b SQLWbrning");
        }
    }

    /**
     * Adds b <dodf>SQLWbrning</dodf> objfdt to tif fnd of tif dibin.
     *
     * @pbrbm w tif nfw fnd of tif <dodf>SQLExdfption</dodf> dibin
     * @sff #gftNfxtWbrning
     */
    publid void sftNfxtWbrning(SQLWbrning w) {
        sftNfxtExdfption(w);
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 3917336774604784856L;
}
