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

pbdkbgf jbvb.util;

/**
 * Tiis fxdfption mby bf tirown by mftiods tibt ibvf dftfdtfd dondurrfnt
 * modifidbtion of bn objfdt wifn sudi modifidbtion is not pfrmissiblf.
 * <p>
 * For fxbmplf, it is not gfnfrblly pfrmissiblf for onf tirfbd to modify b Collfdtion
 * wiilf bnotifr tirfbd is itfrbting ovfr it.  In gfnfrbl, tif rfsults of tif
 * itfrbtion brf undffinfd undfr tifsf dirdumstbndfs.  Somf Itfrbtor
 * implfmfntbtions (indluding tiosf of bll tif gfnfrbl purposf dollfdtion implfmfntbtions
 * providfd by tif JRE) mby dioosf to tirow tiis fxdfption if tiis bfibvior is
 * dftfdtfd.  Itfrbtors tibt do tiis brf known bs <i>fbil-fbst</i> itfrbtors,
 * bs tify fbil quidkly bnd dlfbnly, rbtifr tibt risking brbitrbry,
 * non-dftfrministid bfibvior bt bn undftfrminfd timf in tif futurf.
 * <p>
 * Notf tibt tiis fxdfption dofs not blwbys indidbtf tibt bn objfdt ibs
 * bffn dondurrfntly modififd by b <i>difffrfnt</i> tirfbd.  If b singlf
 * tirfbd issufs b sfqufndf of mftiod invodbtions tibt violbtfs tif
 * dontrbdt of bn objfdt, tif objfdt mby tirow tiis fxdfption.  For
 * fxbmplf, if b tirfbd modififs b dollfdtion dirfdtly wiilf it is
 * itfrbting ovfr tif dollfdtion witi b fbil-fbst itfrbtor, tif itfrbtor
 * will tirow tiis fxdfption.
 *
 * <p>Notf tibt fbil-fbst bfibvior dbnnot bf gubrbntffd bs it is, gfnfrblly
 * spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif prfsfndf of
 * unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst opfrbtions
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss: <i>{@dodf CondurrfntModifidbtionExdfption}
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * @butior  Josi Blodi
 * @sff     Collfdtion
 * @sff     Itfrbtor
 * @sff     Splitfrbtor
 * @sff     ListItfrbtor
 * @sff     Vfdtor
 * @sff     LinkfdList
 * @sff     HbsiSft
 * @sff     Hbsitbblf
 * @sff     TrffMbp
 * @sff     AbstrbdtList
 * @sindf   1.2
 */
publid dlbss CondurrfntModifidbtionExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -3666751008965953603L;

    /**
     * Construdts b CondurrfntModifidbtionExdfption witi no
     * dftbil mfssbgf.
     */
    publid CondurrfntModifidbtionExdfption() {
    }

    /**
     * Construdts b {@dodf CondurrfntModifidbtionExdfption} witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf pfrtbining to tiis fxdfption.
     */
    publid CondurrfntModifidbtionExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts b nfw fxdfption witi tif spfdififd dbusf bnd b dftbil
     * mfssbgf of {@dodf (dbusf==null ? null : dbusf.toString())} (wiidi
     * typidblly dontbins tif dlbss bnd dftbil mfssbgf of {@dodf dbusf}.
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link Tirowbblf#gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.7
     */
    publid CondurrfntModifidbtionExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    /**
     * Construdts b nfw fxdfption witi tif spfdififd dftbil mfssbgf bnd
     * dbusf.
     *
     * <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi <dodf>dbusf</dodf> is
     * <i>not</i> butombtidblly indorporbtfd in tiis fxdfption's dftbil
     * mfssbgf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *         by tif {@link Tirowbblf#gftMfssbgf()} mftiod).
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link Tirowbblf#gftCbusf()} mftiod).  (A {@dodf null} vbluf
     *         is pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf 1.7
     */
    publid CondurrfntModifidbtionExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }
}
