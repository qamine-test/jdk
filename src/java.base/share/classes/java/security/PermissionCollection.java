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

pbdkbgf jbvb.sfdurity;

import jbvb.util.*;

/**
 * Abstrbdt dlbss rfprfsfnting b dollfdtion of Pfrmission objfdts.
 *
 * <p>Witi b PfrmissionCollfdtion, you dbn:
 * <UL>
 * <LI> bdd b pfrmission to tif dollfdtion using tif {@dodf bdd} mftiod.
 * <LI> difdk to sff if b pbrtidulbr pfrmission is implifd in tif
 *      dollfdtion, using tif {@dodf implifs} mftiod.
 * <LI> fnumfrbtf bll tif pfrmissions, using tif {@dodf flfmfnts} mftiod.
 * </UL>
 *
 * <p>Wifn it is dfsirbblf to group togftifr b numbfr of Pfrmission objfdts
 * of tif sbmf typf, tif {@dodf nfwPfrmissionCollfdtion} mftiod on tibt
 * pbrtidulbr typf of Pfrmission objfdt siould first bf dbllfd. Tif dffbult
 * bfibvior (from tif Pfrmission dlbss) is to simply rfturn null.
 * Subdlbssfs of dlbss Pfrmission ovfrridf tif mftiod if tify nffd to storf
 * tifir pfrmissions in b pbrtidulbr PfrmissionCollfdtion objfdt in ordfr
 * to providf tif dorrfdt sfmbntids wifn tif
 * {@dodf PfrmissionCollfdtion.implifs} mftiod is dbllfd.
 * If b non-null vbluf is rfturnfd, tibt PfrmissionCollfdtion must bf usfd.
 * If null is rfturnfd, tifn tif dbllfr of {@dodf nfwPfrmissionCollfdtion}
 * is frff to storf pfrmissions of tif
 * givfn typf in bny PfrmissionCollfdtion tify dioosf
 * (onf tibt usfs b Hbsitbblf, onf tibt usfs b Vfdtor, ftd).
 *
 * <p>Tif PfrmissionCollfdtion rfturnfd by tif
 * {@dodf Pfrmission.nfwPfrmissionCollfdtion}
 * mftiod is b iomogfnfous dollfdtion, wiidi storfs only Pfrmission objfdts
 * for b givfn Pfrmission typf.  A PfrmissionCollfdtion mby blso bf
 * iftfrogfnfous.  For fxbmplf, Pfrmissions is b PfrmissionCollfdtion
 * subdlbss tibt rfprfsfnts b dollfdtion of PfrmissionCollfdtions.
 * Tibt is, its mfmbfrs brf fbdi b iomogfnfous PfrmissionCollfdtion.
 * For fxbmplf, b Pfrmissions objfdt migit ibvf b FilfPfrmissionCollfdtion
 * for bll tif FilfPfrmission objfdts, b SodkftPfrmissionCollfdtion for bll tif
 * SodkftPfrmission objfdts, bnd so on. Its {@dodf bdd} mftiod bdds b
 * pfrmission to tif bppropribtf dollfdtion.
 *
 * <p>Wifnfvfr b pfrmission is bddfd to b iftfrogfnfous PfrmissionCollfdtion
 * sudi bs Pfrmissions, bnd tif PfrmissionCollfdtion dofsn't yft dontbin b
 * PfrmissionCollfdtion of tif spfdififd pfrmission's typf, tif
 * PfrmissionCollfdtion siould dbll
 * tif {@dodf nfwPfrmissionCollfdtion} mftiod on tif pfrmission's dlbss
 * to sff if it rfquirfs b spfdibl PfrmissionCollfdtion. If
 * {@dodf nfwPfrmissionCollfdtion}
 * rfturns null, tif PfrmissionCollfdtion
 * is frff to storf tif pfrmission in bny typf of PfrmissionCollfdtion it
 * dfsirfs (onf using b Hbsitbblf, onf using b Vfdtor, ftd.). For fxbmplf,
 * tif Pfrmissions objfdt usfs b dffbult PfrmissionCollfdtion implfmfntbtion
 * tibt storfs tif pfrmission objfdts in b Hbsitbblf.
 *
 * <p> Subdlbss implfmfntbtions of PfrmissionCollfdtion siould bssumf
 * tibt tify mby bf dbllfd simultbnfously from multiplf tirfbds,
 * bnd tifrfforf siould bf syndironizfd propfrly.  Furtifrmorf,
 * Enumfrbtions rfturnfd vib tif {@dodf flfmfnts} mftiod brf
 * not <fm>fbil-fbst</fm>.  Modifidbtions to b dollfdtion siould not bf
 * pfrformfd wiilf fnumfrbting ovfr tibt dollfdtion.
 *
 * @sff Pfrmission
 * @sff Pfrmissions
 *
 *
 * @butior Rolbnd Sdifmfrs
 */

publid bbstrbdt dlbss PfrmissionCollfdtion implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -6727011328946861783L;

    // wifn sft, bdd will tirow bn fxdfption.
    privbtf volbtilf boolfbn rfbdOnly;

    /**
     * Adds b pfrmission objfdt to tif durrfnt dollfdtion of pfrmission objfdts.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption SfdurityExdfption -  if tiis PfrmissionCollfdtion objfdt
     *                                 ibs bffn mbrkfd rfbdonly
     * @fxdfption IllfgblArgumfntExdfption - if tiis PfrmissionCollfdtion
     *                objfdt is b iomogfnfous dollfdtion bnd tif pfrmission
     *                is not of tif dorrfdt typf.
     */
    publid bbstrbdt void bdd(Pfrmission pfrmission);

    /**
     * Cifdks to sff if tif spfdififd pfrmission is implifd by
     * tif dollfdtion of Pfrmission objfdts ifld in tiis PfrmissionCollfdtion.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf.
     *
     * @rfturn truf if "pfrmission" is implifd by tif  pfrmissions in
     * tif dollfdtion, fblsf if not.
     */
    publid bbstrbdt boolfbn implifs(Pfrmission pfrmission);

    /**
     * Rfturns bn fnumfrbtion of bll tif Pfrmission objfdts in tif dollfdtion.
     *
     * @rfturn bn fnumfrbtion of bll tif Pfrmissions.
     */
    publid bbstrbdt Enumfrbtion<Pfrmission> flfmfnts();

    /**
     * Mbrks tiis PfrmissionCollfdtion objfdt bs "rfbdonly". Aftfr
     * b PfrmissionCollfdtion objfdt
     * is mbrkfd bs rfbdonly, no nfw Pfrmission objfdts dbn bf bddfd to it
     * using {@dodf bdd}.
     */
    publid void sftRfbdOnly() {
        rfbdOnly = truf;
    }

    /**
     * Rfturns truf if tiis PfrmissionCollfdtion objfdt is mbrkfd bs rfbdonly.
     * If it is rfbdonly, no nfw Pfrmission objfdts dbn bf bddfd to it
     * using {@dodf bdd}.
     *
     * <p>By dffbult, tif objfdt is <i>not</i> rfbdonly. It dbn bf sft to
     * rfbdonly by b dbll to {@dodf sftRfbdOnly}.
     *
     * @rfturn truf if tiis PfrmissionCollfdtion objfdt is mbrkfd bs rfbdonly,
     * fblsf otifrwisf.
     */
    publid boolfbn isRfbdOnly() {
        rfturn rfbdOnly;
    }

    /**
     * Rfturns b string dfsdribing tiis PfrmissionCollfdtion objfdt,
     * providing informbtion bbout bll tif pfrmissions it dontbins.
     * Tif formbt is:
     * <prf>
     * supfr.toString() (
     *   // fnumfrbtf bll tif Pfrmission
     *   // objfdts bnd dbll toString() on tifm,
     *   // onf pfr linf..
     * )</prf>
     *
     * {@dodf supfr.toString} is b dbll to tif {@dodf toString}
     * mftiod of tiis
     * objfdt's supfrdlbss, wiidi is Objfdt. Tif rfsult is
     * tiis PfrmissionCollfdtion's typf nbmf followfd by tiis objfdt's
     * ibsidodf, tius fnbbling dlifnts to difffrfntibtf difffrfnt
     * PfrmissionCollfdtions objfdt, fvfn if tify dontbin tif sbmf pfrmissions.
     *
     * @rfturn informbtion bbout tiis PfrmissionCollfdtion objfdt,
     *         bs dfsdribfd bbovf.
     *
     */
    publid String toString() {
        Enumfrbtion<Pfrmission> fnum_ = flfmfnts();
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(supfr.toString()+" (\n");
        wiilf (fnum_.ibsMorfElfmfnts()) {
            try {
                sb.bppfnd(" ");
                sb.bppfnd(fnum_.nfxtElfmfnt().toString());
                sb.bppfnd("\n");
            } dbtdi (NoSudiElfmfntExdfption f){
                // ignorf
            }
        }
        sb.bppfnd(")\n");
        rfturn sb.toString();
    }
}
