/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * An bbstrbdt bdbptfr dlbss for rfdfiving drop tbrgft fvfnts. Tif mftiods in
 * tiis dlbss brf fmpty. Tiis dlbss fxists only bs b donvfnifndf for drfbting
 * listfnfr objfdts.
 * <p>
 * Extfnd tiis dlbss to drfbtf b <dodf>DropTbrgftEvfnt</dodf> listfnfr
 * bnd ovfrridf tif mftiods for tif fvfnts of intfrfst. (If you implfmfnt tif
 * <dodf>DropTbrgftListfnfr</dodf> intfrfbdf, you ibvf to dffinf bll of
 * tif mftiods in it. Tiis bbstrbdt dlbss dffinfs b null implfmfntbtion for
 * fvfry mftiod fxdfpt <dodf>drop(DropTbrgftDropEvfnt)</dodf>, so you only ibvf
 * to dffinf mftiods for fvfnts you dbrf bbout.) You must providf bn
 * implfmfntbtion for bt lfbst <dodf>drop(DropTbrgftDropEvfnt)</dodf>. Tiis
 * mftiod dbnnot ibvf b null implfmfntbtion bfdbusf its spfdifidbtion rfquirfs
 * tibt you fitifr bddfpt or rfjfdt tif drop, bnd, if bddfptfd, indidbtf
 * wiftifr tif drop wbs suddfssful.
 * <p>
 * Crfbtf b listfnfr objfdt using tif fxtfndfd dlbss bnd tifn rfgistfr it witi
 * b <dodf>DropTbrgft</dodf>. Wifn tif drbg fntfrs, movfs ovfr, or fxits
 * tif opfrbblf pbrt of tif drop sitf for tibt <dodf>DropTbrgft</dodf>, wifn
 * tif drop bdtion dibngfs, bnd wifn tif drop oddurs, tif rflfvbnt mftiod in
 * tif listfnfr objfdt is invokfd, bnd tif <dodf>DropTbrgftEvfnt</dodf> is
 * pbssfd to it.
 * <p>
 * Tif opfrbblf pbrt of tif drop sitf for tif <dodf>DropTbrgft</dodf> is
 * tif pbrt of tif bssodibtfd <dodf>Componfnt</dodf>'s gfomftry tibt is not
 * obsdurfd by bn ovfrlbpping top-lfvfl window or by bnotifr
 * <dodf>Componfnt</dodf> iigifr in tif Z-ordfr tibt ibs bn bssodibtfd bdtivf
 * <dodf>DropTbrgft</dodf>.
 * <p>
 * During tif drbg, tif dbtb bssodibtfd witi tif durrfnt drbg opfrbtion dbn bf
 * rftrifvfd by dblling <dodf>gftTrbnsffrbblf()</dodf> on
 * <dodf>DropTbrgftDrbgEvfnt</dodf> instbndfs pbssfd to tif listfnfr's
 * mftiods.
 * <p>
 * Notf tibt <dodf>gftTrbnsffrbblf()</dodf> on tif
 * <dodf>DropTbrgftDrbgEvfnt</dodf> instbndf siould only bf dbllfd witiin tif
 * rfspfdtivf listfnfr's mftiod bnd bll tif nfdfssbry dbtb siould bf rftrifvfd
 * from tif rfturnfd <dodf>Trbnsffrbblf</dodf> bfforf tibt mftiod rfturns.
 *
 * @sff DropTbrgftEvfnt
 * @sff DropTbrgftListfnfr
 *
 * @butior Dbvid Mfndfnibll
 * @sindf 1.4
 */
publid bbstrbdt dlbss DropTbrgftAdbptfr implfmfnts DropTbrgftListfnfr {

    /**
     * Cbllfd wiilf b drbg opfrbtion is ongoing, wifn tif mousf pointfr fntfrs
     * tif opfrbblf pbrt of tif drop sitf for tif <dodf>DropTbrgft</dodf>
     * rfgistfrfd witi tiis listfnfr.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     */
    publid void drbgEntfr(DropTbrgftDrbgEvfnt dtdf) {}

    /**
     * Cbllfd wifn b drbg opfrbtion is ongoing, wiilf tif mousf pointfr is still
     * ovfr tif opfrbblf pbrt of tif drop sitf for tif <dodf>DropTbrgft</dodf>
     * rfgistfrfd witi tiis listfnfr.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     */
    publid void drbgOvfr(DropTbrgftDrbgEvfnt dtdf) {}

    /**
     * Cbllfd if tif usfr ibs modififd
     * tif durrfnt drop gfsturf.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     */
    publid void dropAdtionCibngfd(DropTbrgftDrbgEvfnt dtdf) {}

    /**
     * Cbllfd wiilf b drbg opfrbtion is ongoing, wifn tif mousf pointfr ibs
     * fxitfd tif opfrbblf pbrt of tif drop sitf for tif
     * <dodf>DropTbrgft</dodf> rfgistfrfd witi tiis listfnfr.
     *
     * @pbrbm dtf tif <dodf>DropTbrgftEvfnt</dodf>
     */
    publid void drbgExit(DropTbrgftEvfnt dtf) {}
}
