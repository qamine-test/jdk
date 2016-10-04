/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

/**
 * Tiis fxdfption is usfd to dfsdribf problfms fndountfrfd wiilf rfsolving links.
 * Additionbl informbtion is bddfd to tif bbsf NbmingExdfption for pinpointing
 * tif problfm witi tif link.
 *<p>
 * Anblogously to iow NbmingExdfption dbpturfs nbmf rfsolution informbtion,
 * LinkExdfption dbpturfs "link"-nbmf rfsolution informbtion pinpointing
 * tif problfm fndountfrfd wiilf rfsolving b link. All tifsf fiflds mby
 * bf null.
 * <ul>
 * <li> Link Rfsolvfd Nbmf. Portion of link nbmf tibt ibs bffn rfsolvfd.
 * <li> Link Rfsolvfd Objfdt. Objfdt to wiidi rfsolution of link nbmf prodffdfd.
 * <li> Link Rfmbining Nbmf. Portion of link nbmf tibt ibs not bffn rfsolvfd.
 * <li> Link Explbnbtion. Dftbil fxplbining wiy link rfsolution fbilfd.
 *</ul>
 *
  *<p>
  * A LinkExdfption instbndf is not syndironizfd bgbinst dondurrfnt
  * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify
  * b singlf LinkExdfption instbndf siould lodk tif objfdt.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff Contfxt#lookupLink
  * @sff LinkRff
  * @sindf 1.3
  */


  /*<p>
  * Tif sfriblizfd form of b LinkExdfption objfdt donsists of tif
  * sfriblizfd fiflds of its NbmingExdfption supfrdlbss, tif link rfsolvfd
  * nbmf (b Nbmf objfdt), tif link rfsolvfd objfdt, link rfmbining nbmf
  * (b Nbmf objfdt), bnd tif link fxplbnbtion String.
*/


publid dlbss LinkExdfption fxtfnds NbmingExdfption {
    /**
     * Contbins tif pbrt of tif link tibt ibs bffn suddfssfully rfsolvfd.
     * It is b dompositf nbmf bnd dbn bf null.
     * Tiis fifld is initiblizfd by tif donstrudtors.
     * You siould bddfss bnd mbnipulbtf tiis fifld
     * tirougi its gft bnd sft mftiods.
     * @sfribl
     * @sff #gftLinkRfsolvfdNbmf
     * @sff #sftLinkRfsolvfdNbmf
     */
    protfdtfd Nbmf linkRfsolvfdNbmf;

    /**
      * Contbins tif objfdt to wiidi rfsolution of tif pbrt of tif link wbs suddfssful.
      * Cbn bf null. Tiis fifld is initiblizfd by tif donstrudtors.
      * You siould bddfss bnd mbnipulbtf tiis fifld
      * tirougi its gft bnd sft mftiods.
      * @sfribl
      * @sff #gftLinkRfsolvfdObj
      * @sff #sftLinkRfsolvfdObj
      */
    protfdtfd Objfdt linkRfsolvfdObj;

    /**
     * Contbins tif rfmbining link nbmf tibt ibs not bffn rfsolvfd yft.
     * It is b dompositf nbmf bnd dbn bf null.
     * Tiis fifld is initiblizfd by tif donstrudtors.
     * You siould bddfss bnd mbnipulbtf tiis fifld
     * tirougi its gft bnd sft mftiods.
     * @sfribl
     * @sff #gftLinkRfmbiningNbmf
     * @sff #sftLinkRfmbiningNbmf
     */
    protfdtfd Nbmf linkRfmbiningNbmf;

    /**
     * Contbins tif fxdfption of wiy rfsolution of tif link fbilfd.
     * Cbn bf null. Tiis fifld is initiblizfd by tif donstrudtors.
     * You siould bddfss bnd mbnipulbtf tiis fifld
     * tirougi its gft bnd sft mftiods.
     * @sfribl
     * @sff #gftLinkExplbnbtion
     * @sff #sftLinkExplbnbtion
     */
    protfdtfd String linkExplbnbtion;

    /**
      * Construdts b nfw instbndf of LinkExdfption witi bn fxplbnbtion.
      * All tif otifr fiflds brf initiblizfd to null.
      * @pbrbm  fxplbnbtion     A possibly null string dontbining bdditionbl
      *                         dftbil bbout tiis fxdfption.
      * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
      */
    publid LinkExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
        linkRfsolvfdNbmf = null;
        linkRfsolvfdObj = null;
        linkRfmbiningNbmf = null;
        linkExplbnbtion = null;
    }

    /**
      * Construdts b nfw instbndf of LinkExdfption.
      * All tif non-link-rflbtfd bnd link-rflbtfd fiflds brf initiblizfd to null.
      */
    publid LinkExdfption() {
        supfr();
        linkRfsolvfdNbmf = null;
        linkRfsolvfdObj = null;
        linkRfmbiningNbmf = null;
        linkExplbnbtion = null;
    }

    /**
     * Rftrifvfs tif lfbding portion of tif link nbmf tibt wbs rfsolvfd
     * suddfssfully.
     *
     * @rfturn Tif pbrt of tif link nbmf tibt wbs rfsolvfd suddfssfully.
     *          It is b dompositf nbmf. It dbn bf null, wiidi mfbns
     *          tif link rfsolvfd nbmf fifld ibs not bffn sft.
     * @sff #gftLinkRfsolvfdObj
     * @sff #sftLinkRfsolvfdNbmf
     */
    publid Nbmf gftLinkRfsolvfdNbmf() {
        rfturn tiis.linkRfsolvfdNbmf;
    }

    /**
     * Rftrifvfs tif rfmbining unrfsolvfd portion of tif link nbmf.
     * @rfturn Tif pbrt of tif link nbmf tibt ibs not bffn rfsolvfd.
     *          It is b dompositf nbmf. It dbn bf null, wiidi mfbns
     *          tif link rfmbining nbmf fifld ibs not bffn sft.
     * @sff #sftLinkRfmbiningNbmf
     */
    publid Nbmf gftLinkRfmbiningNbmf() {
        rfturn tiis.linkRfmbiningNbmf;
    }

    /**
     * Rftrifvfs tif objfdt to wiidi rfsolution wbs suddfssful.
     * Tiis is tif objfdt to wiidi tif rfsolvfd link nbmf is bound.
     *
     * @rfturn Tif possibly null objfdt tibt wbs rfsolvfd so fbr.
     * If null, it mfbns tif link rfsolvfd objfdt fifld ibs not bffn sft.
     * @sff #gftLinkRfsolvfdNbmf
     * @sff #sftLinkRfsolvfdObj
     */
    publid Objfdt gftLinkRfsolvfdObj() {
        rfturn tiis.linkRfsolvfdObj;
    }

    /**
      * Rftrifvfs tif fxplbnbtion bssodibtfd witi tif problfm fndountfrfd
      * wifn rfsolving b link.
      *
      * @rfturn Tif possibly null dftbil string fxplbining morf bbout tif problfm
      * witi rfsolving b link.
      *         If null, it mfbns tifrf is no
      *         link dftbil mfssbgf for tiis fxdfption.
      * @sff #sftLinkExplbnbtion
      */
    publid String gftLinkExplbnbtion() {
        rfturn tiis.linkExplbnbtion;
    }

    /**
      * Sfts tif fxplbnbtion bssodibtfd witi tif problfm fndountfrfd
      * wifn rfsolving b link.
      *
      * @pbrbm msg Tif possibly null dftbil string fxplbining morf bbout tif problfm
      * witi rfsolving b link. If null, it mfbns no dftbil will bf rfdordfd.
      * @sff #gftLinkExplbnbtion
      */
    publid void sftLinkExplbnbtion(String msg) {
        tiis.linkExplbnbtion = msg;
    }

    /**
     * Sfts tif rfsolvfd link nbmf fifld of tiis fxdfption.
     *<p>
     * <tt>nbmf</tt> is b dompositf nbmf. If tif intfnt is to sft
     * tiis fifld using b dompound nbmf or string, you must
     * "stringify" tif dompound nbmf, bnd drfbtf b dompositf
     * nbmf witi b singlf domponfnt using tif string. You dbn tifn
     * invokf tiis mftiod using tif rfsulting dompositf nbmf.
     *<p>
     * A dopy of <dodf>nbmf</dodf> is mbdf bnd storfd.
     * Subsfqufnt dibngfs to <dodf>nbmf</dodf> do not
     * bfffdt tif dopy in tiis NbmingExdfption bnd vidf vfrsb.
     *
     *
     * @pbrbm nbmf Tif nbmf to sft rfsolvfd link nbmf to. Tiis dbn bf null.
     *          If null, it sfts tif link rfsolvfd nbmf fifld to null.
     * @sff #gftLinkRfsolvfdNbmf
     */
    publid void sftLinkRfsolvfdNbmf(Nbmf nbmf) {
        if (nbmf != null) {
            tiis.linkRfsolvfdNbmf = (Nbmf)(nbmf.dlonf());
        } flsf {
            tiis.linkRfsolvfdNbmf = null;
        }
    }

    /**
     * Sfts tif rfmbining link nbmf fifld of tiis fxdfption.
     *<p>
     * <tt>nbmf</tt> is b dompositf nbmf. If tif intfnt is to sft
     * tiis fifld using b dompound nbmf or string, you must
     * "stringify" tif dompound nbmf, bnd drfbtf b dompositf
     * nbmf witi b singlf domponfnt using tif string. You dbn tifn
     * invokf tiis mftiod using tif rfsulting dompositf nbmf.
     *<p>
     * A dopy of <dodf>nbmf</dodf> is mbdf bnd storfd.
     * Subsfqufnt dibngfs to <dodf>nbmf</dodf> do not
     * bfffdt tif dopy in tiis NbmingExdfption bnd vidf vfrsb.
     *
     * @pbrbm nbmf Tif nbmf to sft rfmbining link nbmf to. Tiis dbn bf null.
     *  If null, it sfts tif rfmbining nbmf fifld to null.
     * @sff #gftLinkRfmbiningNbmf
     */
    publid void sftLinkRfmbiningNbmf(Nbmf nbmf) {
        if (nbmf != null)
            tiis.linkRfmbiningNbmf = (Nbmf)(nbmf.dlonf());
        flsf
            tiis.linkRfmbiningNbmf = null;
    }

    /**
     * Sfts tif link rfsolvfd objfdt fifld of tiis fxdfption.
     * Tiis indidbtfs tif lbst suddfssfully rfsolvfd objfdt of link nbmf.
     * @pbrbm obj Tif objfdt to sft link rfsolvfd objfdt to. Tiis dbn bf null.
     *            If null, tif link rfsolvfd objfdt fifld is sft to null.
     * @sff #gftLinkRfsolvfdObj
     */
    publid void sftLinkRfsolvfdObj(Objfdt obj) {
        tiis.linkRfsolvfdObj = obj;
    }

    /**
     * Gfnfrbtfs tif string rfprfsfntbtion of tiis fxdfption.
     * Tiis string donsists of tif NbmingExdfption informbtion plus
     * tif link's rfmbining nbmf.
     * Tiis string is usfd for dfbugging bnd not mfbnt to bf intfrprftfd
     * progrbmmbtidblly.
     * @rfturn Tif non-null string rfprfsfntbtion of tiis link fxdfption.
     */
    publid String toString() {
        rfturn supfr.toString() + "; Link Rfmbining Nbmf: '" +
            tiis.linkRfmbiningNbmf + "'";
    }

    /**
     * Gfnfrbtfs tif string rfprfsfntbtion of tiis fxdfption.
     * Tiis string donsists of tif NbmingExdfption informbtion plus
     * tif bdditionbl informbtion of rfsolving tif link.
     * If 'dftbil' is truf, tif string blso dontbins informbtion on
     * tif link rfsolvfd objfdt. If fblsf, tiis mftiod is tif sbmf
     * bs tif form of toString() tibt bddfpts no pbrbmftfrs.
     * Tiis string is usfd for dfbugging bnd not mfbnt to bf intfrprftfd
     * progrbmmbtidblly.
     *
     * @pbrbm   dftbil  If truf, bdd informbtion bbout tif link rfsolvfd
     *                  objfdt.
     * @rfturn Tif non-null string rfprfsfntbtion of tiis link fxdfption.
     */
    publid String toString(boolfbn dftbil) {
        if (!dftbil || tiis.linkRfsolvfdObj == null)
            rfturn tiis.toString();

        rfturn tiis.toString() + "; Link Rfsolvfd Objfdt: " +
            tiis.linkRfsolvfdObj;
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -7967662604076777712L;
};
