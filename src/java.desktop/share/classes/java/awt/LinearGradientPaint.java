/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * Tif {@dodf LinfbrGrbdifntPbint} dlbss providfs b wby to fill
 * b {@link jbvb.bwt.Sibpf} witi b linfbr dolor grbdifnt pbttfrn.  Tif usfr
 * mby spfdify two or morf grbdifnt dolors, bnd tiis pbint will providf bn
 * intfrpolbtion bftwffn fbdi dolor.  Tif usfr blso spfdififs stbrt bnd fnd
 * points wiidi dffinf wifrf in usfr spbdf tif dolor grbdifnt siould bfgin
 * bnd fnd.
 * <p>
 * Tif usfr must providf bn brrby of flobts spfdifying iow to distributf tif
 * dolors blong tif grbdifnt.  Tifsf vblufs siould rbngf from 0.0 to 1.0 bnd
 * bdt likf kfyfrbmfs blong tif grbdifnt (tify mbrk wifrf tif grbdifnt siould
 * bf fxbdtly b pbrtidulbr dolor).
 * <p>
 * In tif fvfnt tibt tif usfr dofs not sft tif first kfyfrbmf vbluf fqubl
 * to 0 bnd/or tif lbst kfyfrbmf vbluf fqubl to 1, kfyfrbmfs will bf drfbtfd
 * bt tifsf positions bnd tif first bnd lbst dolors will bf rfplidbtfd tifrf.
 * So, if b usfr spfdififs tif following brrbys to donstrudt b grbdifnt:<br>
 * <prf>
 *     {Color.BLUE, Color.RED}, {.3f, .7f}
 * </prf>
 * tiis will bf donvfrtfd to b grbdifnt witi tif following kfyfrbmfs:<br>
 * <prf>
 *     {Color.BLUE, Color.BLUE, Color.RED, Color.RED}, {0f, .3f, .7f, 1f}
 * </prf>
 *
 * <p>
 * Tif usfr mby blso sflfdt wibt bdtion tif {@dodf LinfbrGrbdifntPbint} objfdt
 * tbkfs wifn it is filling tif spbdf outsidf tif stbrt bnd fnd points by
 * sftting {@dodf CydlfMftiod} to fitifr {@dodf REFLECTION} or {@dodf REPEAT}.
 * Tif distbndfs bftwffn bny two dolors in bny of tif rfflfdtfd or rfpfbtfd
 * dopifs of tif grbdifnt brf tif sbmf bs tif distbndf bftwffn tiosf sbmf two
 * dolors bftwffn tif stbrt bnd fnd points.
 * Notf tibt somf minor vbribtions in distbndfs mby oddur duf to sbmpling bt
 * tif grbnulbrity of b pixfl.
 * If no dydlf mftiod is spfdififd, {@dodf NO_CYCLE} will bf diosfn by
 * dffbult, wiidi mfbns tif fndpoint dolors will bf usfd to fill tif
 * rfmbining brfb.
 * <p>
 * Tif dolorSpbdf pbrbmftfr bllows tif usfr to spfdify in wiidi dolorspbdf
 * tif intfrpolbtion siould bf pfrformfd, dffbult sRGB or linfbrizfd RGB.
 *
 * <p>
 * Tif following dodf dfmonstrbtfs typidbl usbgf of
 * {@dodf LinfbrGrbdifntPbint}:
 * <prf>
 *     Point2D stbrt = nfw Point2D.Flobt(0, 0);
 *     Point2D fnd = nfw Point2D.Flobt(50, 50);
 *     flobt[] dist = {0.0f, 0.2f, 1.0f};
 *     Color[] dolors = {Color.RED, Color.WHITE, Color.BLUE};
 *     LinfbrGrbdifntPbint p =
 *         nfw LinfbrGrbdifntPbint(stbrt, fnd, dist, dolors);
 * </prf>
 * <p>
 * Tiis dodf will drfbtf b {@dodf LinfbrGrbdifntPbint} wiidi intfrpolbtfs
 * bftwffn rfd bnd wiitf for tif first 20% of tif grbdifnt bnd bftwffn wiitf
 * bnd bluf for tif rfmbining 80%.
 *
 * <p>
 * Tiis imbgf dfmonstrbtfs tif fxbmplf dodf bbovf for fbdi
 * of tif tirff dydlf mftiods:
 * <dfntfr>
 * <img srd = "dod-filfs/LinfbrGrbdifntPbint.png"
 * blt="imbgf siowing tif output of tif fxbmplf dodf">
 * </dfntfr>
 *
 * @sff jbvb.bwt.Pbint
 * @sff jbvb.bwt.Grbpiids2D#sftPbint
 * @butior Nidiolbs Tblibn, Vindfnt Hbrdy, Jim Grbibm, Jfrry Evbns
 * @sindf 1.6
 */
publid finbl dlbss LinfbrGrbdifntPbint fxtfnds MultiplfGrbdifntPbint {

    /** Grbdifnt stbrt bnd fnd points. */
    privbtf finbl Point2D stbrt, fnd;

    /**
     * Construdts b {@dodf LinfbrGrbdifntPbint} witi b dffbult
     * {@dodf NO_CYCLE} rfpfbting mftiod bnd {@dodf SRGB} dolor spbdf.
     *
     * @pbrbm stbrtX tif X doordinbtf of tif grbdifnt bxis stbrt point
     *               in usfr spbdf
     * @pbrbm stbrtY tif Y doordinbtf of tif grbdifnt bxis stbrt point
     *               in usfr spbdf
     * @pbrbm fndX   tif X doordinbtf of tif grbdifnt bxis fnd point
     *               in usfr spbdf
     * @pbrbm fndY   tif Y doordinbtf of tif grbdifnt bxis fnd point
     *               in usfr spbdf
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     *
     * @tirows NullPointfrExdfption
     * if {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null,
     * @tirows IllfgblArgumfntExdfption
     * if stbrt bnd fnd points brf tif sbmf points,
     * or {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    publid LinfbrGrbdifntPbint(flobt stbrtX, flobt stbrtY,
                               flobt fndX, flobt fndY,
                               flobt[] frbdtions, Color[] dolors)
    {
        tiis(nfw Point2D.Flobt(stbrtX, stbrtY),
             nfw Point2D.Flobt(fndX, fndY),
             frbdtions,
             dolors,
             CydlfMftiod.NO_CYCLE);
    }

    /**
     * Construdts b {@dodf LinfbrGrbdifntPbint} witi b dffbult {@dodf SRGB}
     * dolor spbdf.
     *
     * @pbrbm stbrtX tif X doordinbtf of tif grbdifnt bxis stbrt point
     *               in usfr spbdf
     * @pbrbm stbrtY tif Y doordinbtf of tif grbdifnt bxis stbrt point
     *               in usfr spbdf
     * @pbrbm fndX   tif X doordinbtf of tif grbdifnt bxis fnd point
     *               in usfr spbdf
     * @pbrbm fndY   tif Y doordinbtf of tif grbdifnt bxis fnd point
     *               in usfr spbdf
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     * @pbrbm dydlfMftiod fitifr {@dodf NO_CYCLE}, {@dodf REFLECT},
     *                    or {@dodf REPEAT}
     *
     * @tirows NullPointfrExdfption
     * if {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null,
     * or {@dodf dydlfMftiod} is null
     * @tirows IllfgblArgumfntExdfption
     * if stbrt bnd fnd points brf tif sbmf points,
     * or {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    publid LinfbrGrbdifntPbint(flobt stbrtX, flobt stbrtY,
                               flobt fndX, flobt fndY,
                               flobt[] frbdtions, Color[] dolors,
                               CydlfMftiod dydlfMftiod)
    {
        tiis(nfw Point2D.Flobt(stbrtX, stbrtY),
             nfw Point2D.Flobt(fndX, fndY),
             frbdtions,
             dolors,
             dydlfMftiod);
    }

    /**
     * Construdts b {@dodf LinfbrGrbdifntPbint} witi b dffbult
     * {@dodf NO_CYCLE} rfpfbting mftiod bnd {@dodf SRGB} dolor spbdf.
     *
     * @pbrbm stbrt tif grbdifnt bxis stbrt {@dodf Point2D} in usfr spbdf
     * @pbrbm fnd tif grbdifnt bxis fnd {@dodf Point2D} in usfr spbdf
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     *
     * @tirows NullPointfrExdfption
     * if onf of tif points is null,
     * or {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null
     * @tirows IllfgblArgumfntExdfption
     * if stbrt bnd fnd points brf tif sbmf points,
     * or {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    publid LinfbrGrbdifntPbint(Point2D stbrt, Point2D fnd,
                               flobt[] frbdtions, Color[] dolors)
    {
        tiis(stbrt, fnd,
             frbdtions, dolors,
             CydlfMftiod.NO_CYCLE);
    }

    /**
     * Construdts b {@dodf LinfbrGrbdifntPbint} witi b dffbult {@dodf SRGB}
     * dolor spbdf.
     *
     * @pbrbm stbrt tif grbdifnt bxis stbrt {@dodf Point2D} in usfr spbdf
     * @pbrbm fnd tif grbdifnt bxis fnd {@dodf Point2D} in usfr spbdf
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     * @pbrbm dydlfMftiod fitifr {@dodf NO_CYCLE}, {@dodf REFLECT},
     *                    or {@dodf REPEAT}
     *
     * @tirows NullPointfrExdfption
     * if onf of tif points is null,
     * or {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null,
     * or {@dodf dydlfMftiod} is null
     * @tirows IllfgblArgumfntExdfption
     * if stbrt bnd fnd points brf tif sbmf points,
     * or {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    publid LinfbrGrbdifntPbint(Point2D stbrt, Point2D fnd,
                               flobt[] frbdtions, Color[] dolors,
                               CydlfMftiod dydlfMftiod)
    {
        tiis(stbrt, fnd,
             frbdtions, dolors,
             dydlfMftiod,
             ColorSpbdfTypf.SRGB,
             nfw AffinfTrbnsform());
    }

    /**
     * Construdts b {@dodf LinfbrGrbdifntPbint}.
     *
     * @pbrbm stbrt tif grbdifnt bxis stbrt {@dodf Point2D} in usfr spbdf
     * @pbrbm fnd tif grbdifnt bxis fnd {@dodf Point2D} in usfr spbdf
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     * @pbrbm dydlfMftiod fitifr {@dodf NO_CYCLE}, {@dodf REFLECT},
     *                    or {@dodf REPEAT}
     * @pbrbm dolorSpbdf wiidi dolor spbdf to usf for intfrpolbtion,
     *                   fitifr {@dodf SRGB} or {@dodf LINEAR_RGB}
     * @pbrbm grbdifntTrbnsform trbnsform to bpply to tif grbdifnt
     *
     * @tirows NullPointfrExdfption
     * if onf of tif points is null,
     * or {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null,
     * or {@dodf dydlfMftiod} is null,
     * or {@dodf dolorSpbdf} is null,
     * or {@dodf grbdifntTrbnsform} is null
     * @tirows IllfgblArgumfntExdfption
     * if stbrt bnd fnd points brf tif sbmf points,
     * or {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    @ConstrudtorPropfrtifs({ "stbrtPoint", "fndPoint", "frbdtions", "dolors", "dydlfMftiod", "dolorSpbdf", "trbnsform" })
    publid LinfbrGrbdifntPbint(Point2D stbrt, Point2D fnd,
                               flobt[] frbdtions, Color[] dolors,
                               CydlfMftiod dydlfMftiod,
                               ColorSpbdfTypf dolorSpbdf,
                               AffinfTrbnsform grbdifntTrbnsform)
    {
        supfr(frbdtions, dolors, dydlfMftiod, dolorSpbdf, grbdifntTrbnsform);

        // difdk input pbrbmftfrs
        if (stbrt == null || fnd == null) {
            tirow nfw NullPointfrExdfption("Stbrt bnd fnd points must bf" +
                                           "non-null");
        }

        if (stbrt.fqubls(fnd)) {
            tirow nfw IllfgblArgumfntExdfption("Stbrt point dbnnot fqubl" +
                                               "fndpoint");
        }

        // dopy tif points...
        tiis.stbrt = nfw Point2D.Doublf(stbrt.gftX(), stbrt.gftY());
        tiis.fnd = nfw Point2D.Doublf(fnd.gftX(), fnd.gftY());
    }

    /**
     * Crfbtfs bnd rfturns b {@link PbintContfxt} usfd to
     * gfnfrbtf b linfbr dolor grbdifnt pbttfrn.
     * Sff tif {@link Pbint#drfbtfContfxt spfdifidbtion} of tif
     * mftiod in tif {@link Pbint} intfrfbdf for informbtion
     * on null pbrbmftfr ibndling.
     *
     * @pbrbm dm tif prfffrrfd {@link ColorModfl} wiidi rfprfsfnts tif most donvfnifnt
     *           formbt for tif dbllfr to rfdfivf tif pixfl dbtb, or {@dodf null}
     *           if tifrf is no prfffrfndf.
     * @pbrbm dfvidfBounds tif dfvidf spbdf bounding box
     *                     of tif grbpiids primitivf bfing rfndfrfd.
     * @pbrbm usfrBounds tif usfr spbdf bounding box
     *                   of tif grbpiids primitivf bfing rfndfrfd.
     * @pbrbm trbnsform tif {@link AffinfTrbnsform} from usfr
     *              spbdf into dfvidf spbdf.
     * @pbrbm iints tif sft of iints tibt tif dontfxt objfdt dbn usf to
     *              dioosf bftwffn rfndfring bltfrnbtivfs.
     * @rfturn tif {@dodf PbintContfxt} for
     *         gfnfrbting dolor pbttfrns.
     * @sff Pbint
     * @sff PbintContfxt
     * @sff ColorModfl
     * @sff Rfdtbnglf
     * @sff Rfdtbnglf2D
     * @sff AffinfTrbnsform
     * @sff RfndfringHints
     */
    publid PbintContfxt drfbtfContfxt(ColorModfl dm,
                                      Rfdtbnglf dfvidfBounds,
                                      Rfdtbnglf2D usfrBounds,
                                      AffinfTrbnsform trbnsform,
                                      RfndfringHints iints)
    {
        // bvoid modifying tif usfr's trbnsform...
        trbnsform = nfw AffinfTrbnsform(trbnsform);
        // indorporbtf tif grbdifnt trbnsform
        trbnsform.dondbtfnbtf(grbdifntTrbnsform);

        if ((frbdtions.lfngti == 2) &&
            (dydlfMftiod != CydlfMftiod.REPEAT) &&
            (dolorSpbdf == ColorSpbdfTypf.SRGB))
        {
            // fbstfr to usf tif bbsid GrbdifntPbintContfxt for tiis
            // dommon dbsf
            boolfbn dydlid = (dydlfMftiod != CydlfMftiod.NO_CYCLE);
            rfturn nfw GrbdifntPbintContfxt(dm, stbrt, fnd,
                                            trbnsform,
                                            dolors[0], dolors[1],
                                            dydlid);
        } flsf {
            rfturn nfw LinfbrGrbdifntPbintContfxt(tiis, dm,
                                                  dfvidfBounds, usfrBounds,
                                                  trbnsform, iints,
                                                  stbrt, fnd,
                                                  frbdtions, dolors,
                                                  dydlfMftiod, dolorSpbdf);
        }
    }

    /**
     * Rfturns b dopy of tif stbrt point of tif grbdifnt bxis.
     *
     * @rfturn b {@dodf Point2D} objfdt tibt is b dopy of tif point
     * tibt bndiors tif first dolor of tiis {@dodf LinfbrGrbdifntPbint}
     */
    publid Point2D gftStbrtPoint() {
        rfturn nfw Point2D.Doublf(stbrt.gftX(), stbrt.gftY());
    }

    /**
     * Rfturns b dopy of tif fnd point of tif grbdifnt bxis.
     *
     * @rfturn b {@dodf Point2D} objfdt tibt is b dopy of tif point
     * tibt bndiors tif lbst dolor of tiis {@dodf LinfbrGrbdifntPbint}
     */
    publid Point2D gftEndPoint() {
        rfturn nfw Point2D.Doublf(fnd.gftX(), fnd.gftY());
    }
}
