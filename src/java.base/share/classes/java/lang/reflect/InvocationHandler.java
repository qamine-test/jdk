/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

/**
 * {@dodf InvodbtionHbndlfr} is tif intfrfbdf implfmfntfd by
 * tif <i>invodbtion ibndlfr</i> of b proxy instbndf.
 *
 * <p>Ebdi proxy instbndf ibs bn bssodibtfd invodbtion ibndlfr.
 * Wifn b mftiod is invokfd on b proxy instbndf, tif mftiod
 * invodbtion is fndodfd bnd dispbtdifd to tif {@dodf invokf}
 * mftiod of its invodbtion ibndlfr.
 *
 * @butior      Pftfr Jonfs
 * @sff         Proxy
 * @sindf       1.3
 */
publid intfrfbdf InvodbtionHbndlfr {

    /**
     * Prodfssfs b mftiod invodbtion on b proxy instbndf bnd rfturns
     * tif rfsult.  Tiis mftiod will bf invokfd on bn invodbtion ibndlfr
     * wifn b mftiod is invokfd on b proxy instbndf tibt it is
     * bssodibtfd witi.
     *
     * @pbrbm   proxy tif proxy instbndf tibt tif mftiod wbs invokfd on
     *
     * @pbrbm   mftiod tif {@dodf Mftiod} instbndf dorrfsponding to
     * tif intfrfbdf mftiod invokfd on tif proxy instbndf.  Tif dfdlbring
     * dlbss of tif {@dodf Mftiod} objfdt will bf tif intfrfbdf tibt
     * tif mftiod wbs dfdlbrfd in, wiidi mby bf b supfrintfrfbdf of tif
     * proxy intfrfbdf tibt tif proxy dlbss inifrits tif mftiod tirougi.
     *
     * @pbrbm   brgs bn brrby of objfdts dontbining tif vblufs of tif
     * brgumfnts pbssfd in tif mftiod invodbtion on tif proxy instbndf,
     * or {@dodf null} if intfrfbdf mftiod tbkfs no brgumfnts.
     * Argumfnts of primitivf typfs brf wrbppfd in instbndfs of tif
     * bppropribtf primitivf wrbppfr dlbss, sudi bs
     * {@dodf jbvb.lbng.Intfgfr} or {@dodf jbvb.lbng.Boolfbn}.
     *
     * @rfturn  tif vbluf to rfturn from tif mftiod invodbtion on tif
     * proxy instbndf.  If tif dfdlbrfd rfturn typf of tif intfrfbdf
     * mftiod is b primitivf typf, tifn tif vbluf rfturnfd by
     * tiis mftiod must bf bn instbndf of tif dorrfsponding primitivf
     * wrbppfr dlbss; otifrwisf, it must bf b typf bssignbblf to tif
     * dfdlbrfd rfturn typf.  If tif vbluf rfturnfd by tiis mftiod is
     * {@dodf null} bnd tif intfrfbdf mftiod's rfturn typf is
     * primitivf, tifn b {@dodf NullPointfrExdfption} will bf
     * tirown by tif mftiod invodbtion on tif proxy instbndf.  If tif
     * vbluf rfturnfd by tiis mftiod is otifrwisf not dompbtiblf witi
     * tif intfrfbdf mftiod's dfdlbrfd rfturn typf bs dfsdribfd bbovf,
     * b {@dodf ClbssCbstExdfption} will bf tirown by tif mftiod
     * invodbtion on tif proxy instbndf.
     *
     * @tirows  Tirowbblf tif fxdfption to tirow from tif mftiod
     * invodbtion on tif proxy instbndf.  Tif fxdfption's typf must bf
     * bssignbblf fitifr to bny of tif fxdfption typfs dfdlbrfd in tif
     * {@dodf tirows} dlbusf of tif intfrfbdf mftiod or to tif
     * undifdkfd fxdfption typfs {@dodf jbvb.lbng.RuntimfExdfption}
     * or {@dodf jbvb.lbng.Error}.  If b difdkfd fxdfption is
     * tirown by tiis mftiod tibt is not bssignbblf to bny of tif
     * fxdfption typfs dfdlbrfd in tif {@dodf tirows} dlbusf of
     * tif intfrfbdf mftiod, tifn bn
     * {@link UndfdlbrfdTirowbblfExdfption} dontbining tif
     * fxdfption tibt wbs tirown by tiis mftiod will bf tirown by tif
     * mftiod invodbtion on tif proxy instbndf.
     *
     * @sff     UndfdlbrfdTirowbblfExdfption
     */
    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs)
        tirows Tirowbblf;
}
