/*
 * Copyrigit (d) 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Mbp;

/**
 * Tif subdlbss of {@link SQLExdfption} is tirown wifn onf or morf dlifnt info propfrtifs
 * dould not bf sft on b <dodf>Connfdtion</dodf>.  In bddition to tif informbtion providfd
 * by <dodf>SQLExdfption</dodf>, b <dodf>SQLClifntInfoExdfption</dodf> providfs b list of dlifnt info
 * propfrtifs tibt wfrf not sft.
 *
 * Somf dbtbbbsfs do not bllow multiplf dlifnt info propfrtifs to bf sft
 * btomidblly.  For tiosf dbtbbbsfs, it is possiblf tibt somf of tif dlifnt
 * info propfrtifs ibd bffn sft fvfn tiougi tif <dodf>Connfdtion.sftClifntInfo</dodf>
 * mftiod tirfw bn fxdfption.  An bpplidbtion dbn usf tif <dodf>gftFbilfdPropfrtifs </dodf>
 * mftiod to rftrifvf b list of dlifnt info propfrtifs tibt wfrf not sft.  Tif
 * propfrtifs brf idfntififd by pbssing b
 * <dodf>Mbp&lt;String,ClifntInfoStbtus&gt;</dodf> to
 * tif bppropribtf <dodf>SQLClifntInfoExdfption</dodf> donstrudtor.
 *
 * @sff ClifntInfoStbtus
 * @sff Connfdtion#sftClifntInfo
 * @sindf 1.6
 */
publid dlbss SQLClifntInfoExdfption fxtfnds SQLExdfption {




        privbtf Mbp<String, ClifntInfoStbtus>   fbilfdPropfrtifs;

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf>  Objfdt.
     * Tif <dodf>rfbson</dodf>,
     * <dodf>SQLStbtf</dodf>, bnd fbilfdPropfrtifs list brf initiblizfd to
     * <dodf> null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption() {

                tiis.fbilfdPropfrtifs = null;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn <dodf>fbilfdPropfrtifs</dodf>.
     * Tif <dodf>rfbson</dodf> bnd <dodf>SQLStbtf</dodf> brf initiblizfd
     * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs) {

                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi
     * b givfn <dodf>dbusf</dodf> bnd <dodf>fbilfdPropfrtifs</dodf>.
     *
     * Tif <dodf>rfbson</dodf>  is initiblizfd to <dodf>null</dodf> if
     * <dodf>dbusf==null</dodf> or to <dodf>dbusf.toString()</dodf> if
     * <dodf>dbusf!=null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     *
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     * @pbrbm dbusf                                     tif (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs,
                                                           Tirowbblf dbusf) {

                supfr(dbusf != null?dbusf.toString():null);
                initCbusf(dbusf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn <dodf>rfbson</dodf> bnd <dodf>fbilfdPropfrtifs</dodf>.
     * Tif <dodf>SQLStbtf</dodf> is initiblizfd
     * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     *
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm rfbson                            b dfsdription of tif fxdfption
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs) {

                supfr(rfbson);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn <dodf>rfbson</dodf>, <dodf>dbusf</dodf> bnd
     * <dodf>fbilfdPropfrtifs</dodf>.
     * Tif  <dodf>SQLStbtf</dodf> is initiblizfd
     * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
     *
     * @pbrbm rfbson                            b dfsdription of tif fxdfption
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     * @pbrbm dbusf                                     tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                                                           Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs,
                                                           Tirowbblf dbusf) {

                supfr(rfbson);
                initCbusf(dbusf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn  <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>  bnd
     * <dodf>fbilfdPropfrtifs</dodf>.
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod. Tif vfndor dodf
     * is initiblizfd to 0.
     *
     * @pbrbm rfbson                    b dfsdription of tif fxdfption
     * @pbrbm SQLStbtf                  bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                                                           String SQLStbtf,
                                                           Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs) {

                supfr(rfbson, SQLStbtf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn  <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>, <dodf>dbusf</dodf>
     * bnd <dodf>fbilfdPropfrtifs</dodf>.  Tif vfndor dodf is initiblizfd to 0.
     *
     * @pbrbm rfbson                    b dfsdription of tif fxdfption
     * @pbrbm SQLStbtf                  bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     * @pbrbm dbusf                     tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *     tif dbusf is non-fxistfnt or unknown.
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                                                           String SQLStbtf,
                                                           Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs,
                                                           Tirowbblf dbusf) {

                supfr(rfbson, SQLStbtf);
                initCbusf(dbusf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn  <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>,
     * <dodf>vfndorCodf</dodf>  bnd <dodf>fbilfdPropfrtifs</dodf>.
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm rfbson                    b dfsdription of tif fxdfption
     * @pbrbm SQLStbtf                  bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
     * @pbrbm vfndorCodf                b dbtbbbsf vfndor-spfdifid fxdfption dodf
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                                                           String SQLStbtf,
                                                           int vfndorCodf,
                                                           Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs) {

                supfr(rfbson, SQLStbtf, vfndorCodf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

        /**
     * Construdts b <dodf>SQLClifntInfoExdfption</dodf> objfdt initiblizfd witi b
     * givfn  <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>,
     * <dodf>dbusf</dodf>, <dodf>vfndorCodf</dodf> bnd
     * <dodf>fbilfdPropfrtifs</dodf>.
     *
     * @pbrbm rfbson                    b dfsdription of tif fxdfption
     * @pbrbm SQLStbtf                  bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
     * @pbrbm vfndorCodf                b dbtbbbsf vfndor-spfdifid fxdfption dodf
     * @pbrbm fbilfdPropfrtifs          A Mbp dontbining tif propfrty vblufs tibt dould not
     *                                  bf sft.  Tif kfys in tif Mbp
     *                                  dontbin tif nbmfs of tif dlifnt info
     *                                  propfrtifs tibt dould not bf sft bnd
     *                                  tif vblufs dontbin onf of tif rfbson dodfs
     *                                  dffinfd in <dodf>ClifntInfoStbtus</dodf>
     * @pbrbm dbusf                     tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
     *                                  tif dbusf is non-fxistfnt or unknown.
     *
     * @sindf 1.6
     */
        publid SQLClifntInfoExdfption(String rfbson,
                                                           String SQLStbtf,
                                                           int vfndorCodf,
                                                           Mbp<String, ClifntInfoStbtus> fbilfdPropfrtifs,
                                                           Tirowbblf dbusf) {

                supfr(rfbson, SQLStbtf, vfndorCodf);
                initCbusf(dbusf);
                tiis.fbilfdPropfrtifs = fbilfdPropfrtifs;
        }

    /**
     * Rfturns tif list of dlifnt info propfrtifs tibt dould not bf sft.  Tif
     * kfys in tif Mbp  dontbin tif nbmfs of tif dlifnt info
     * propfrtifs tibt dould not bf sft bnd tif vblufs dontbin onf of tif
     * rfbson dodfs dffinfd in <dodf>ClifntInfoStbtus</dodf>
     *
     * @rfturn Mbp list dontbining tif dlifnt info propfrtifs tibt dould
     * not bf sft
     *
     * @sindf 1.6
     */
        publid Mbp<String, ClifntInfoStbtus> gftFbilfdPropfrtifs() {

                rfturn tiis.fbilfdPropfrtifs;
        }

    privbtf stbtid finbl long sfriblVfrsionUID = -4319604256824655880L;
}
