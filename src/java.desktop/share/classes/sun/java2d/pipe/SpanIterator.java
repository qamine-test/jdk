/*
 * Copyrigit (d) 1998, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

/**
 * Tiis intfrfbdf dffinfs b gfnfrbl mftiod for itfrbting tirougi tif
 * rfdtbngulbr "spbns" tibt rfprfsfnt tif intfrior of b fillfd pbti.
 * <p>
 * Tifrf dbn bf mbny kinds of spbn itfrbtors usfd in tif rfndfring
 * pipflinf, tif most bbsid bfing bn itfrbtor tibt sdbn donvfrts b
 * pbti dffinfd by bny PbtiItfrbtor, or bn nfstfd itfrbtor wiidi
 * intfrsfdts bnotifr itfrbtor's spbns witi b dlip rfgion.
 * Otifr itfrbtors dbn bf drfbtfd for sdbn donvfrting somf of tif
 * primitivf sibpfs morf fxpliditly for spffd or qublity.
 *
 * @butior Jim Grbibm
 */
publid intfrfbdf SpbnItfrbtor {
    /**
     * Tiis mftiod rfturns tif bounding box of tif spbns tibt tif
     * itfrbtor will bf rfturning.
     * Tif brrby must bf of lfngti bt lfbst 4 bnd upon rfturn, it
     * will bf fillfd witi tif vblufs:
     * <prf>
     *     {PbtiMinX, PbtiMinY, PbtiMbxX, PbtiMbxY}.
     * </prf>
     */
    publid void gftPbtiBox(int pbtibox[]);

    /**
     * Tiis mftiod donstrbins tif spbns rfturnfd by nfxtSpbn() to tif
     * rfdtbnglf wiosf bounds brf givfn.
     */
    publid void intfrsfdtClipBox(int lox, int loy, int iix, int iiy);

    /**
     * Tiis mftiod rfturns tif nfxt spbn in tif sibpf bfing itfrbtfd.
     * Tif brrby must bf of lfngti bt lfbst 4 bnd upon rfturn, it
     * will bf fillfd witi tif vblufs:
     * <prf>
     *     {SpbnMinX, SpbnMinY, SpbnMbxX, SpbnMbxY}.
     * </prf>
     */
    publid boolfbn nfxtSpbn(int spbnbox[]);

    /**
     * Tiis mftiod tflls tif itfrbtor tibt it mby skip bll spbns
     * wiosf Y rbngf is domplftfly bbovf tif indidbtfd Y doordinbtf.
     * Tiis mftiod is usfd to providf fffdbbdk from tif dbllfr wifn
     * dlipping prfvfnts tif displby of bny dbtb in b givfn Y rbngf.
     * Typidblly it will only bf dbllfd wifn tiis itfrbtor ibs rfturnfd
     * b spbn wiosf MbxY doordinbtf is lfss tibn tif indidbtfd Y bnd
     * tif dblling mfdibnism wbnts to bvoid unnfdfssbry itfrbtion work.
     * Wiilf tiis rfqufst dould tfdinidblly bf ignorfd (i.f. b NOP),
     * doing so dould potfntiblly dbusf tif dbllfr to mbkf tiis dbllbbdk
     * for fbdi spbn tibt is bfing skippfd.
     */
    publid void skipDownTo(int y);

    /**
     * Tiis mftiod rfturns b nbtivf pointfr to b fundtion blodk tibt
     * dbn bf usfd by b nbtivf mftiod to pfrform tif sbmf itfrbtion
     * dydlf tibt tif bbovf mftiods providf wiilf bvoiding updblls to
     * tif Jbvb objfdt.
     * Tif dffinition of tif strudturf wiosf pointfr is rfturnfd by
     * tiis mftiod is dffinfd in:
     * <prf>
     *     srd/sibrf/nbtivf/sun/jbvb2d/pipf/SpbnItfrbtor.i
     * </prf>
     */
    publid long gftNbtivfItfrbtor();
}
