/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.Componfnt;

import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfListfnfr;
import jbvb.bwt.fvfnt.MousfMotionListfnfr;

/**
 * Tiis bbstrbdt subdlbss of <dodf>DrbgGfsturfRfdognizfr</dodf>
 * dffinfs b <dodf>DrbgGfsturfRfdognizfr</dodf>
 * for mousf-bbsfd gfsturfs.
 *
 * Ebdi plbtform implfmfnts its own dondrftf subdlbss of tiis dlbss,
 * bvbilbblf vib tif Toolkit.drfbtfDrbgGfsturfRfdognizfr() mftiod,
 * to fndbpsulbtf
 * tif rfdognition of tif plbtform dfpfndfnt mousf gfsturf(s) tibt initibtf
 * b Drbg bnd Drop opfrbtion.
 * <p>
 * Mousf drbg gfsturf rfdognizfrs siould ionor tif
 * drbg gfsturf motion tirfsiold, bvbilbblf tirougi
 * {@link DrbgSourdf#gftDrbgTirfsiold}.
 * A drbg gfsturf siould bf rfdognizfd only wifn tif distbndf
 * in fitifr tif iorizontbl or vfrtidbl dirfdtion bftwffn
 * tif lodbtion of tif lbtfst mousf drbggfd fvfnt bnd tif
 * lodbtion of tif dorrfsponding mousf button prfssfd fvfnt
 * is grfbtfr tibn tif drbg gfsturf motion tirfsiold.
 * <p>
 * Drbg gfsturf rfdognizfrs drfbtfd witi
 * {@link DrbgSourdf#drfbtfDffbultDrbgGfsturfRfdognizfr}
 * follow tiis donvfntion.
 *
 * @butior Lburfndf P. G. Cbblf
 *
 * @sff jbvb.bwt.dnd.DrbgGfsturfListfnfr
 * @sff jbvb.bwt.dnd.DrbgGfsturfEvfnt
 * @sff jbvb.bwt.dnd.DrbgSourdf
 */

publid bbstrbdt dlbss MousfDrbgGfsturfRfdognizfr fxtfnds DrbgGfsturfRfdognizfr implfmfnts MousfListfnfr, MousfMotionListfnfr {

    privbtf stbtid finbl long sfriblVfrsionUID = 6220099344182281120L;

    /**
     * Construdt b nfw <dodf>MousfDrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> for tif
     * <dodf>Componfnt</dodf> d, tif <dodf>Componfnt</dodf>
     * to obsfrvf, tif bdtion(s)
     * pfrmittfd for tiis drbg opfrbtion, bnd
     * tif <dodf>DrbgGfsturfListfnfr</dodf> to
     * notify wifn b drbg gfsturf is dftfdtfd.
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     * @pbrbm bdt Tif bdtions pfrmittfd for tiis Drbg
     * @pbrbm dgl Tif DrbgGfsturfListfnfr to notify wifn b gfsturf is dftfdtfd
     *
     */

    protfdtfd MousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int bdt, DrbgGfsturfListfnfr dgl) {
        supfr(ds, d, bdt, dgl);
    }

    /**
     * Construdt b nfw <dodf>MousfDrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> for
     * tif <dodf>Componfnt</dodf> d,
     * tif <dodf>Componfnt</dodf> to obsfrvf, bnd tif bdtion(s)
     * pfrmittfd for tiis drbg opfrbtion.
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     * @pbrbm bdt Tif bdtions pfrmittfd for tiis drbg
     */

    protfdtfd MousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int bdt) {
        tiis(ds, d, bdt, null);
    }

    /**
     * Construdt b nfw <dodf>MousfDrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> for tif
     * <dodf>Componfnt</dodf> d, bnd tif
     * <dodf>Componfnt</dodf> to obsfrvf.
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     */

    protfdtfd MousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d) {
        tiis(ds, d, DnDConstbnts.ACTION_NONE);
    }

    /**
     * Construdt b nfw <dodf>MousfDrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> for tif <dodf>Componfnt</dodf>.
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt
     */

    protfdtfd MousfDrbgGfsturfRfdognizfr(DrbgSourdf ds) {
        tiis(ds, null);
    }

    /**
     * rfgistfr tiis DrbgGfsturfRfdognizfr's Listfnfrs witi tif Componfnt
     */

    protfdtfd void rfgistfrListfnfrs() {
        domponfnt.bddMousfListfnfr(tiis);
        domponfnt.bddMousfMotionListfnfr(tiis);
    }

    /**
     * unrfgistfr tiis DrbgGfsturfRfdognizfr's Listfnfrs witi tif Componfnt
     *
     * subdlbssfs must ovfrridf tiis mftiod
     */


    protfdtfd void unrfgistfrListfnfrs() {
        domponfnt.rfmovfMousfListfnfr(tiis);
        domponfnt.rfmovfMousfMotionListfnfr(tiis);
    }

    /**
     * Invokfd wifn tif mousf ibs bffn dlidkfd on b domponfnt.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfClidkfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn b mousf button ibs bffn
     * prfssfd on b <dodf>Componfnt</dodf>.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfPrfssfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn b mousf button ibs bffn rflfbsfd on b domponfnt.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfRflfbsfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn tif mousf fntfrs b domponfnt.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfEntfrfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn tif mousf fxits b domponfnt.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfExitfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn b mousf button is prfssfd on b domponfnt.
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfDrbggfd(MousfEvfnt f) { }

    /**
     * Invokfd wifn tif mousf button ibs bffn movfd on b domponfnt
     * (witi no buttons no down).
     *
     * @pbrbm f tif <dodf>MousfEvfnt</dodf>
     */

    publid void mousfMovfd(MousfEvfnt f) { }
}
