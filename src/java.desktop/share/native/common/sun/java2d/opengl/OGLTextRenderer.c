/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff HEADLESS

#indludf <stdlib.i>
#indludf <mbti.i>
#indludf <jlong.i>

#indludf "sun_jbvb2d_opfngl_OGLTfxtRfndfrfr.i"

#indludf "SurfbdfDbtb.i"
#indludf "OGLContfxt.i"
#indludf "OGLSurfbdfDbtb.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "OGLTfxtRfndfrfr.i"
#indludf "OGLVfrtfxCbdif.i"
#indludf "AddflGlypiCbdif.i"
#indludf "fontsdblfrdffs.i"

/**
 * Tif following donstbnts dffinf tif innfr bnd outfr bounds of tif
 * bddflfrbtfd glypi dbdif.
 */
#dffinf OGLTR_CACHE_WIDTH       512
#dffinf OGLTR_CACHE_HEIGHT      512
#dffinf OGLTR_CACHE_CELL_WIDTH  16
#dffinf OGLTR_CACHE_CELL_HEIGHT 16

/**
 * Tif durrfnt "glypi modf" stbtf.  Tiis vbribblf is usfd to trbdk tif
 * dodfpbti usfd to rfndfr b pbrtidulbr glypi.  Tiis vbribblf is rfsft to
 * MODE_NOT_INITED bt tif bfginning of fvfry dbll to OGLTR_DrbwGlypiList().
 * As fbdi glypi is rfndfrfd, tif glypiModf vbribblf is updbtfd to rfflfdt
 * tif durrfnt modf, so if tif durrfnt modf is tif sbmf bs tif modf usfd
 * to rfndfr tif prfvious glypi, wf dbn bvoid doing dostly sftup opfrbtions
 * fbdi timf.
 */
typfdff fnum {
    MODE_NOT_INITED,
    MODE_USE_CACHE_GRAY,
    MODE_USE_CACHE_LCD,
    MODE_NO_CACHE_GRAY,
    MODE_NO_CACHE_LCD
} GlypiModf;
stbtid GlypiModf glypiModf = MODE_NOT_INITED;

/**
 * Tiis fnum indidbtfs tif durrfnt stbtf of tif ibrdwbrf glypi dbdif.
 * Initiblly tif CbdifStbtus is sft to CACHE_NOT_INITED, bnd tifn it is
 * sft to fitifr GRAY or LCD wifn tif glypi dbdif is initiblizfd.
 */
typfdff fnum {
    CACHE_NOT_INITED,
    CACHE_GRAY,
    CACHE_LCD
} CbdifStbtus;
stbtid CbdifStbtus dbdifStbtus = CACHE_NOT_INITED;

/**
 * Tiis is tif onf glypi dbdif.  Ondf it is initiblizfd bs fitifr GRAY or
 * LCD, it stbys in tibt modf for tif durbtion of tif bpplidbtion.  It siould
 * bf sbff to usf tiis onf glypi dbdif for bll sdrffns in b multimon
 * fnvironmfnt, sindf tif glypi dbdif tfxturf is sibrfd bftwffn bll dontfxts,
 * bnd (in tifory) OpfnGL drivfrs siould bf smbrt fnougi to mbnbgf tibt
 * tfxturf bdross bll sdrffns.
 */
stbtid GlypiCbdifInfo *glypiCbdif = NULL;

/**
 * Tif ibndlf to tif LCD tfxt frbgmfnt progrbm objfdt.
 */
stbtid GLibndlfARB lddTfxtProgrbm = 0;

/**
 * Tif sizf of onf of tif gbmmb LUT tfxturfs in bny onf dimfnsion blong
 * tif fdgf, in tfxfls.
 */
#dffinf LUT_EDGE 16

/**
 * Tifsf brf tif tfxturf objfdt ibndlfs for tif gbmmb bnd invfrsf gbmmb
 * lookup tbblfs.
 */
stbtid GLuint gbmmbLutTfxturfID = 0;
stbtid GLuint invGbmmbLutTfxturfID = 0;

/**
 * Tiis vbluf trbdks tif prfvious LCD dontrbst sftting, so if tif dontrbst
 * vbluf ibsn't dibngfd sindf tif lbst timf tif lookup tbblfs wfrf
 * gfnfrbtfd (not vfry dommon), tifn wf dbn skip updbting tif tbblfs.
 */
stbtid jint lbstLCDContrbst = -1;

/**
 * Tiis vbluf trbdks tif prfvious LCD rgbOrdfr sftting, so if tif rgbOrdfr
 * vbluf ibs dibngfd sindf tif lbst timf, it indidbtfs tibt wf nffd to
 * invblidbtf tif dbdif, wiidi mby blrfbdy storf glypi imbgfs in tif rfvfrsf
 * ordfr.  Notf tibt in most rfbl world bpplidbtions tiis vbluf will not
 * dibngf ovfr tif doursf of tif bpplidbtion, but tfsts likf Font2DTfst
 * bllow for dibnging tif ordfring bt runtimf, so wf nffd to ibndlf tibt dbsf.
 */
stbtid jboolfbn lbstRGBOrdfr = JNI_TRUE;

/**
 * Tiis donstbnt dffinfs tif sizf of tif tilf to usf in tif
 * OGLTR_DrbwLCDGlypiNoCbdif() mftiod.  Sff bflow for morf on wiy wf
 * rfstridt tiis vbluf to b pbrtidulbr sizf.
 */
#dffinf OGLTR_NOCACHE_TILE_SIZE 32

/**
 * Tifsf donstbnts dffinf tif sizf of tif "dbdifd dfstinbtion" tfxturf.
 * Tiis tfxturf is only usfd wifn rfndfring LCD-optimizfd tfxt, bs tibt
 * dodfpbti nffds dirfdt bddfss to tif dfstinbtion.  Tifrf is no wby to
 * bddfss tif frbmfbufffr dirfdtly from bn OpfnGL sibdfr, so wf nffd to first
 * dopy tif dfstinbtion rfgion dorrfsponding to b pbrtidulbr glypi into
 * tiis dbdifd tfxturf, bnd tifn tibt tfxturf will bf bddfssfd insidf tif
 * sibdfr.  Copying tif dfstinbtion into tiis dbdifd tfxturf dbn bf b vfry
 * fxpfnsivf opfrbtion (bddounting for bbout iblf tif rfndfring timf for
 * LCD tfxt), so to mitigbtf tiis dost wf try to bulk rfbd b iorizontbl
 * rfgion of tif dfstinbtion bt b timf.  (Tifsf vblufs brf fmpiridblly
 * dfrivfd for tif dommon dbsf wifrf tfxt runs iorizontblly.)
 *
 * Notf: It is bssumfd in vbrious dbldulbtions bflow tibt:
 *     (OGLTR_CACHED_DEST_WIDTH  >= OGLTR_CACHE_CELL_WIDTH)  &&
 *     (OGLTR_CACHED_DEST_WIDTH  >= OGLTR_NOCACHE_TILE_SIZE) &&
 *     (OGLTR_CACHED_DEST_HEIGHT >= OGLTR_CACHE_CELL_HEIGHT) &&
 *     (OGLTR_CACHED_DEST_HEIGHT >= OGLTR_NOCACHE_TILE_SIZE)
 */
#dffinf OGLTR_CACHED_DEST_WIDTH  512
#dffinf OGLTR_CACHED_DEST_HEIGHT 32

/**
 * Tif ibndlf to tif "dbdifd dfstinbtion" tfxturf objfdt.
 */
stbtid GLuint dbdifdDfstTfxturfID = 0;

/**
 * Tif durrfnt bounds of tif "dbdifd dfstinbtion" tfxturf, in dfstinbtion
 * doordinbtf spbdf.  Tif widti/ifigit of tifsf bounds will not fxdffd tif
 * OGLTR_CACHED_DEST_WIDTH/HEIGHT vblufs dffinfd bbovf.  Tifsf bounds brf
 * only donsidfrfd vblid wifn tif isCbdifdDfstVblid flbg is JNI_TRUE.
 */
stbtid SurfbdfDbtbBounds dbdifdDfstBounds;

/**
 * Tiis flbg indidbtfs wiftifr tif "dbdifd dfstinbtion" tfxturf dontbins
 * vblid dbtb.  Tiis flbg is rfsft to JNI_FALSE bt tif bfginning of fvfry
 * dbll to OGLTR_DrbwGlypiList().  Ondf wf dopy vblid dfstinbtion dbtb
 * into tif dbdifd tfxturf, tiis flbg is sft to JNI_TRUE.  Tiis wby, wf dbn
 * limit tif numbfr of timfs wf nffd to dopy dfstinbtion dbtb, wiidi is b
 * vfry dostly opfrbtion.
 */
stbtid jboolfbn isCbdifdDfstVblid = JNI_FALSE;

/**
 * Tif bounds of tif prfviously rfndfrfd LCD glypi, in dfstinbtion
 * doordinbtf spbdf.  Wf usf tifsf bounds to dftfrminf wiftifr tif glypi
 * durrfntly bfing rfndfrfd ovfrlbps tif prfviously rfndfrfd glypi (i.f.
 * its bounding box intfrsfdts tibt of tif prfviously rfndfrfd glypi).  If
 * so, wf nffd to rf-rfbd tif dfstinbtion brfb bssodibtfd witi tibt prfvious
 * glypi so tibt wf dbn dorrfdtly blfnd witi tif bdtubl dfstinbtion dbtb.
 */
stbtid SurfbdfDbtbBounds prfviousGlypiBounds;

/**
 * Initiblizfs tif onf glypi dbdif (tfxturf bnd dbtb strudturf).
 * If lddCbdif is JNI_TRUE, tif tfxturf will dontbin RGB dbtb,
 * otifrwisf wf will simply storf tif grbysdblf/monodiromf glypi imbgfs
 * bs intfnsity vblufs (wiidi work wfll witi tif GL_MODULATE fundtion).
 */
stbtid jboolfbn
OGLTR_InitGlypiCbdif(jboolfbn lddCbdif)
{
    GlypiCbdifInfo *gdinfo;
    GLdlbmpf priority = 1.0f;
    GLfnum intfrnblFormbt = lddCbdif ? GL_RGB8 : GL_INTENSITY8;
    GLfnum pixflFormbt = lddCbdif ? GL_RGB : GL_LUMINANCE;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_InitGlypiCbdif");

    // init glypi dbdif dbtb strudturf
    gdinfo = AddflGlypiCbdif_Init(OGLTR_CACHE_WIDTH,
                                  OGLTR_CACHE_HEIGHT,
                                  OGLTR_CACHE_CELL_WIDTH,
                                  OGLTR_CACHE_CELL_HEIGHT,
                                  OGLVfrtfxCbdif_FlusiVfrtfxCbdif);
    if (gdinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLTR_InitGlypiCbdif: dould not init OGL glypi dbdif");
        rfturn JNI_FALSE;
    }

    // init dbdif tfxturf objfdt
    j2d_glGfnTfxturfs(1, &gdinfo->dbdifID);
    j2d_glBindTfxturf(GL_TEXTURE_2D, gdinfo->dbdifID);
    j2d_glPrioritizfTfxturfs(1, &gdinfo->dbdifID, &priority);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

    j2d_glTfxImbgf2D(GL_TEXTURE_2D, 0, intfrnblFormbt,
                     OGLTR_CACHE_WIDTH, OGLTR_CACHE_HEIGHT, 0,
                     pixflFormbt, GL_UNSIGNED_BYTE, NULL);

    dbdifStbtus = (lddCbdif ? CACHE_LCD : CACHE_GRAY);
    glypiCbdif = gdinfo;

    rfturn JNI_TRUE;
}

/**
 * Adds tif givfn glypi to tif glypi dbdif (tfxturf bnd dbtb strudturf)
 * bssodibtfd witi tif givfn OGLContfxt.
 */
stbtid void
OGLTR_AddToGlypiCbdif(GlypiInfo *glypi, jboolfbn rgbOrdfr)
{
    GLfnum pixflFormbt;
    CbdifCfllInfo *ddinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_AddToGlypiCbdif");

    if ((glypiCbdif == NULL) || (glypi->imbgf == NULL)) {
        rfturn;
    }

    if (dbdifStbtus == CACHE_LCD) {
        pixflFormbt = rgbOrdfr ? GL_RGB : GL_BGR;
    } flsf {
        pixflFormbt = GL_LUMINANCE;
    }

    AddflGlypiCbdif_AddGlypi(glypiCbdif, glypi);
    ddinfo = (CbdifCfllInfo *) glypi->dfllInfo;

    if (ddinfo != NULL) {
        // storf glypi imbgf in tfxturf dfll
        j2d_glTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                            ddinfo->x, ddinfo->y,
                            glypi->widti, glypi->ifigit,
                            pixflFormbt, GL_UNSIGNED_BYTE, glypi->imbgf);
    }
}

/**
 * Tiis is tif GLSL frbgmfnt sibdfr sourdf dodf for rfndfring LCD-optimizfd
 * tfxt.  Do not bf frigitfnfd; it is mudi fbsifr to undfrstbnd tibn tif
 * fquivblfnt ASM-likf frbgmfnt progrbm!
 *
 * Tif "uniform" vbribblfs bt tif top brf initiblizfd ondf tif progrbm is
 * linkfd, bnd brf updbtfd bt runtimf bs nffdfd (f.g. wifn tif sourdf dolor
 * dibngfs, wf will modify tif "srd_bdj" vbluf in OGLTR_UpdbtfLCDTfxtColor()).
 *
 * Tif "mbin" fundtion is fxfdutfd for fbdi "frbgmfnt" (or pixfl) in tif
 * glypi imbgf.  Wf ibvf dftfrminfd tibt tif pow() fundtion dbn bf quitf
 * slow bnd it only opfrbtfs on sdblbr vblufs, not vfdtors bs wf rfquirf.
 * So instfbd wf build two 3D tfxturfs dontbining gbmmb (bnd invfrsf gbmmb)
 * lookup tbblfs tibt bllow us to bpproximbtf b domponfnt-wisf pow() fundtion
 * witi b singlf 3D tfxturf lookup.  Tiis bpprobdi is bt lfbst 2x fbstfr
 * tibn tif fquivblfnt pow() dblls.
 *
 * Tif vbribblfs involvfd in tif fqubtion dbn bf fxprfssfd bs follows:
 *
 *   Cs = Color domponfnt of tif sourdf (forfground dolor) [0.0, 1.0]
 *   Cd = Color domponfnt of tif dfstinbtion (bbdkground dolor) [0.0, 1.0]
 *   Cr = Color domponfnt to bf writtfn to tif dfstinbtion [0.0, 1.0]
 *   Ag = Glypi blpib (bkb intfnsity or dovfrbgf) [0.0, 1.0]
 *   Gb = Gbmmb bdjustmfnt in tif rbngf [1.0, 2.5]
 *   (^ mfbns rbisfd to tif powfr)
 *
 * And ifrf is tif tiforftidbl fqubtion bpproximbtfd by tiis sibdfr:
 *
 *            Cr = (Ag*(Cs^Gb) + (1-Ag)*(Cd^Gb)) ^ (1/Gb)
 */
stbtid donst dibr *lddTfxtSibdfrSourdf =
    "uniform vfd3 srd_bdj;"
    "uniform sbmplfr2D glypi_tfx;"
    "uniform sbmplfr2D dst_tfx;"
    "uniform sbmplfr3D invgbmmb_tfx;"
    "uniform sbmplfr3D gbmmb_tfx;"
    ""
    "void mbin(void)"
    "{"
         // lobd tif RGB vbluf from tif glypi imbgf bt tif durrfnt tfxdoord
    "    vfd3 glypi_dlr = vfd3(tfxturf2D(glypi_tfx, gl_TfxCoord[0].st));"
    "    if (glypi_dlr == vfd3(0.0)) {"
             // zfro dovfrbgf, so skip tiis frbgmfnt
    "        disdbrd;"
    "    }"
         // lobd tif RGB vbluf from tif dorrfsponding dfstinbtion pixfl
    "    vfd3 dst_dlr = vfd3(tfxturf2D(dst_tfx, gl_TfxCoord[1].st));"
         // gbmmb bdjust tif dfst dolor using tif invgbmmb LUT
    "    vfd3 dst_bdj = vfd3(tfxturf3D(invgbmmb_tfx, dst_dlr.stp));"
         // linfbrly intfrpolbtf tif tirff dolor vblufs
    "    vfd3 rfsult = mix(dst_bdj, srd_bdj, glypi_dlr);"
         // gbmmb rf-bdjust tif rfsulting dolor (blpib is blwbys sft to 1.0)
    "    gl_FrbgColor = vfd4(vfd3(tfxturf3D(gbmmb_tfx, rfsult.stp)), 1.0);"
    "}";

/**
 * Compilfs bnd links tif LCD tfxt sibdfr progrbm.  If suddfssful, tiis
 * fundtion rfturns b ibndlf to tif nfwly drfbtfd sibdfr progrbm; otifrwisf
 * rfturns 0.
 */
stbtid GLibndlfARB
OGLTR_CrfbtfLCDTfxtProgrbm()
{
    GLibndlfARB lddTfxtProgrbm;
    GLint lod;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_CrfbtfLCDTfxtProgrbm");

    lddTfxtProgrbm = OGLContfxt_CrfbtfFrbgmfntProgrbm(lddTfxtSibdfrSourdf);
    if (lddTfxtProgrbm == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLTR_CrfbtfLCDTfxtProgrbm: frror drfbting progrbm");
        rfturn 0;
    }

    // "usf" tif progrbm objfdt tfmporbrily so tibt wf dbn sft tif uniforms
    j2d_glUsfProgrbmObjfdtARB(lddTfxtProgrbm);

    // sft tif "uniform" vblufs
    lod = j2d_glGftUniformLodbtionARB(lddTfxtProgrbm, "glypi_tfx");
    j2d_glUniform1iARB(lod, 0); // tfxturf unit 0
    lod = j2d_glGftUniformLodbtionARB(lddTfxtProgrbm, "dst_tfx");
    j2d_glUniform1iARB(lod, 1); // tfxturf unit 1
    lod = j2d_glGftUniformLodbtionARB(lddTfxtProgrbm, "invgbmmb_tfx");
    j2d_glUniform1iARB(lod, 2); // tfxturf unit 2
    lod = j2d_glGftUniformLodbtionARB(lddTfxtProgrbm, "gbmmb_tfx");
    j2d_glUniform1iARB(lod, 3); // tfxturf unit 3

    // "unusf" tif progrbm objfdt; it will bf rf-bound lbtfr bs nffdfd
    j2d_glUsfProgrbmObjfdtARB(0);

    rfturn lddTfxtProgrbm;
}

/**
 * Initiblizfs b 3D tfxturf objfdt for usf bs b tirff-dimfnsionbl gbmmb
 * lookup tbblf.  Notf tibt tif wrbp modf is initiblizfd to GL_LINEAR so
 * tibt tif tbblf will intfrpolbtf bdjbdfnt vblufs wifn tif indfx fblls
 * somfwifrf in bftwffn.
 */
stbtid GLuint
OGLTR_InitGbmmbLutTfxturf()
{
    GLuint lutTfxturfID;

    j2d_glGfnTfxturfs(1, &lutTfxturfID);
    j2d_glBindTfxturf(GL_TEXTURE_3D, lutTfxturfID);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_3D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_3D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    j2d_glTfxPbrbmftfri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);

    rfturn lutTfxturfID;
}

/**
 * Updbtfs tif lookup tbblf in tif givfn tfxturf objfdt witi tif flobt
 * vblufs in tif givfn systfm mfmory bufffr.  Notf tibt wf dould usf
 * glTfxSubImbgf3D() wifn updbting tif tfxturf bftfr its first
 * initiblizbtion, but sindf wf'rf updbting tif fntirf tbblf (witi
 * powfr-of-two dimfnsions) bnd tiis is b rflbtivfly rbrf fvfnt, wf'll
 * just stidk witi glTfxImbgf3D().
 */
stbtid void
OGLTR_UpdbtfGbmmbLutTfxturf(GLuint tfxID, GLflobt *lut, jint sizf)
{
    j2d_glBindTfxturf(GL_TEXTURE_3D, tfxID);
    j2d_glTfxImbgf3D(GL_TEXTURE_3D, 0, GL_RGB8,
                     sizf, sizf, sizf, 0, GL_RGB, GL_FLOAT, lut);
}

/**
 * (Rf)Initiblizfs tif gbmmb lookup tbblf tfxturfs.
 *
 * Tif givfn dontrbst vbluf is bn int in tif rbngf [100, 250] wiidi wf will
 * tifn sdblf to fit in tif rbngf [1.0, 2.5].  Wf drfbtf two LUTs, onf
 * tibt fssfntiblly dbldulbtfs pow(x, gbmmb) bnd tif otifr dbldulbtfs
 * pow(x, 1/gbmmb).  Tifsf vblufs brf rfplidbtfd in bll tirff dimfnsions, so
 * givfn b singlf 3D tfxturf doordinbtf (typidblly tiis will bf b triplft
 * in tif form (r,g,b)), tif 3D tfxturf lookup will rfturn bn RGB triplft:
 *
 *     (pow(r,g), pow(y,g), pow(z,g)
 *
 * wifrf g is fitifr gbmmb or 1/gbmmb, dfpfnding on tif tbblf.
 */
stbtid jboolfbn
OGLTR_UpdbtfLCDTfxtContrbst(jint dontrbst)
{
    doublf gbmmb = ((doublf)dontrbst) / 100.0;
    doublf ig = gbmmb;
    doublf g = 1.0 / ig;
    GLflobt lut[LUT_EDGE][LUT_EDGE][LUT_EDGE][3];
    GLflobt invlut[LUT_EDGE][LUT_EDGE][LUT_EDGE][3];
    int min = 0;
    int mbx = LUT_EDGE - 1;
    int x, y, z;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLTR_UpdbtfLCDTfxtContrbst: dontrbst=%d", dontrbst);

    for (z = min; z <= mbx; z++) {
        doublf zvbl = ((doublf)z) / mbx;
        GLflobt gz = (GLflobt)pow(zvbl, g);
        GLflobt igz = (GLflobt)pow(zvbl, ig);

        for (y = min; y <= mbx; y++) {
            doublf yvbl = ((doublf)y) / mbx;
            GLflobt gy = (GLflobt)pow(yvbl, g);
            GLflobt igy = (GLflobt)pow(yvbl, ig);

            for (x = min; x <= mbx; x++) {
                doublf xvbl = ((doublf)x) / mbx;
                GLflobt gx = (GLflobt)pow(xvbl, g);
                GLflobt igx = (GLflobt)pow(xvbl, ig);

                lut[z][y][x][0] = gx;
                lut[z][y][x][1] = gy;
                lut[z][y][x][2] = gz;

                invlut[z][y][x][0] = igx;
                invlut[z][y][x][1] = igy;
                invlut[z][y][x][2] = igz;
            }
        }
    }

    if (gbmmbLutTfxturfID == 0) {
        gbmmbLutTfxturfID = OGLTR_InitGbmmbLutTfxturf();
    }
    OGLTR_UpdbtfGbmmbLutTfxturf(gbmmbLutTfxturfID, (GLflobt *)lut, LUT_EDGE);

    if (invGbmmbLutTfxturfID == 0) {
        invGbmmbLutTfxturfID = OGLTR_InitGbmmbLutTfxturf();
    }
    OGLTR_UpdbtfGbmmbLutTfxturf(invGbmmbLutTfxturfID,
                                (GLflobt *)invlut, LUT_EDGE);

    rfturn JNI_TRUE;
}

/**
 * Updbtfs tif durrfnt gbmmb-bdjustfd sourdf dolor ("srd_bdj") of tif LCD
 * tfxt sibdfr progrbm.  Notf tibt wf dould dbldulbtf tiis vbluf in tif
 * sibdfr (f.g. just bs wf do for "dst_bdj"), but would bf unnfdfssbry work
 * (bnd b mfbsurbblf pfrformbndf iit, mbybf bround 5%) sindf tiis vbluf is
 * donstbnt ovfr tif fntirf glypi list.  So instfbd wf just dbldulbtf tif
 * gbmmb-bdjustfd vbluf ondf bnd updbtf tif uniform pbrbmftfr of tif LCD
 * sibdfr bs nffdfd.
 */
stbtid jboolfbn
OGLTR_UpdbtfLCDTfxtColor(jint dontrbst)
{
    doublf gbmmb = ((doublf)dontrbst) / 100.0;
    GLflobt rbdj, gbdj, bbdj;
    GLflobt dlr[4];
    GLint lod;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLTR_UpdbtfLCDTfxtColor: dontrbst=%d", dontrbst);

    /*
     * Notf: Idfblly wf would updbtf tif "srd_bdj" uniform pbrbmftfr only
     * wifn tifrf is b dibngf in tif sourdf dolor.  Fortunbtfly, tif dost
     * of qufrying tif durrfnt OpfnGL dolor stbtf bnd updbting tif uniform
     * vbluf is quitf smbll, bnd in tif dommon dbsf wf only nffd to do tiis
     * ondf pfr GlypiList, so wf gbin littlf from trying to optimizf too
     * fbgfrly ifrf.
     */

    // gft tif durrfnt OpfnGL primbry dolor stbtf
    j2d_glGftFlobtv(GL_CURRENT_COLOR, dlr);

    // gbmmb bdjust tif primbry dolor
    rbdj = (GLflobt)pow(dlr[0], gbmmb);
    gbdj = (GLflobt)pow(dlr[1], gbmmb);
    bbdj = (GLflobt)pow(dlr[2], gbmmb);

    // updbtf tif "srd_bdj" pbrbmftfr of tif sibdfr progrbm witi tiis vbluf
    lod = j2d_glGftUniformLodbtionARB(lddTfxtProgrbm, "srd_bdj");
    j2d_glUniform3fARB(lod, rbdj, gbdj, bbdj);

    rfturn JNI_TRUE;
}

/**
 * Enbblfs tif LCD tfxt sibdfr bnd updbtfs bny rflbtfd stbtf, sudi bs tif
 * gbmmb lookup tbblf tfxturfs.
 */
stbtid jboolfbn
OGLTR_EnbblfLCDGlypiModfStbtf(GLuint glypiTfxturfID, jint dontrbst)
{
    // bind tif tfxturf dontbining glypi dbtb to tfxturf unit 0
    j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);
    j2d_glBindTfxturf(GL_TEXTURE_2D, glypiTfxturfID);

    // bind tif tfxturf tilf dontbining dfstinbtion dbtb to tfxturf unit 1
    j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
    if (dbdifdDfstTfxturfID == 0) {
        dbdifdDfstTfxturfID =
            OGLContfxt_CrfbtfBlitTfxturf(GL_RGB8, GL_RGB,
                                         OGLTR_CACHED_DEST_WIDTH,
                                         OGLTR_CACHED_DEST_HEIGHT);
        if (dbdifdDfstTfxturfID == 0) {
            rfturn JNI_FALSE;
        }
    }
    j2d_glBindTfxturf(GL_TEXTURE_2D, dbdifdDfstTfxturfID);

    // notf tibt GL_TEXTURE_2D wbs blrfbdy fnbblfd for tfxturf unit 0,
    // but wf nffd to fxpliditly fnbblf it for tfxturf unit 1
    j2d_glEnbblf(GL_TEXTURE_2D);

    // drfbtf tif LCD tfxt sibdfr, if nfdfssbry
    if (lddTfxtProgrbm == 0) {
        lddTfxtProgrbm = OGLTR_CrfbtfLCDTfxtProgrbm();
        if (lddTfxtProgrbm == 0) {
            rfturn JNI_FALSE;
        }
    }

    // fnbblf tif LCD tfxt sibdfr
    j2d_glUsfProgrbmObjfdtARB(lddTfxtProgrbm);

    // updbtf tif durrfnt dontrbst sfttings, if nfdfssbry
    if (lbstLCDContrbst != dontrbst) {
        if (!OGLTR_UpdbtfLCDTfxtContrbst(dontrbst)) {
            rfturn JNI_FALSE;
        }
        lbstLCDContrbst = dontrbst;
    }

    // updbtf tif durrfnt dolor sfttings
    if (!OGLTR_UpdbtfLCDTfxtColor(dontrbst)) {
        rfturn JNI_FALSE;
    }

    // bind tif gbmmb LUT tfxturfs
    j2d_glAdtivfTfxturfARB(GL_TEXTURE2_ARB);
    j2d_glBindTfxturf(GL_TEXTURE_3D, invGbmmbLutTfxturfID);
    j2d_glEnbblf(GL_TEXTURE_3D);
    j2d_glAdtivfTfxturfARB(GL_TEXTURE3_ARB);
    j2d_glBindTfxturf(GL_TEXTURE_3D, gbmmbLutTfxturfID);
    j2d_glEnbblf(GL_TEXTURE_3D);

    rfturn JNI_TRUE;
}

void
OGLTR_EnbblfGlypiVfrtfxCbdif(OGLContfxt *ogld)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_EnbblfGlypiVfrtfxCbdif");

    if (!OGLVfrtfxCbdif_InitVfrtfxCbdif(ogld)) {
        rfturn;
    }

    if (glypiCbdif == NULL) {
        if (!OGLTR_InitGlypiCbdif(JNI_FALSE)) {
            rfturn;
        }
    }

    j2d_glEnbblf(GL_TEXTURE_2D);
    j2d_glBindTfxturf(GL_TEXTURE_2D, glypiCbdif->dbdifID);
    j2d_glPixflStorfi(GL_UNPACK_ALIGNMENT, 1);

    // for grbysdblf/monodiromf tfxt, tif durrfnt OpfnGL sourdf dolor
    // is modulbtfd witi tif glypi imbgf bs pbrt of tif tfxturf
    // bpplidbtion stbgf, so wf usf GL_MODULATE ifrf
    OGLC_UPDATE_TEXTURE_FUNCTION(ogld, GL_MODULATE);
}

void
OGLTR_DisbblfGlypiVfrtfxCbdif(OGLContfxt *ogld)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_DisbblfGlypiVfrtfxCbdif");

    OGLVfrtfxCbdif_FlusiVfrtfxCbdif();
    OGLVfrtfxCbdif_RfstorfColorStbtf(ogld);

    j2d_glDisbblf(GL_TEXTURE_2D);
    j2d_glPixflStorfi(GL_UNPACK_ALIGNMENT, 4);
    j2d_glPixflStorfi(GL_UNPACK_SKIP_PIXELS, 0);
    j2d_glPixflStorfi(GL_UNPACK_SKIP_ROWS, 0);
    j2d_glPixflStorfi(GL_UNPACK_ROW_LENGTH, 0);
}

/**
 * Disbblfs bny pfnding stbtf bssodibtfd witi tif durrfnt "glypi modf".
 */
stbtid void
OGLTR_DisbblfGlypiModfStbtf()
{
    switdi (glypiModf) {
    dbsf MODE_NO_CACHE_LCD:
        j2d_glPixflStorfi(GL_UNPACK_SKIP_PIXELS, 0);
        j2d_glPixflStorfi(GL_UNPACK_SKIP_ROWS, 0);
        /* FALLTHROUGH */

    dbsf MODE_USE_CACHE_LCD:
        j2d_glPixflStorfi(GL_UNPACK_ROW_LENGTH, 0);
        j2d_glPixflStorfi(GL_UNPACK_ALIGNMENT, 4);
        j2d_glUsfProgrbmObjfdtARB(0);
        j2d_glAdtivfTfxturfARB(GL_TEXTURE3_ARB);
        j2d_glDisbblf(GL_TEXTURE_3D);
        j2d_glAdtivfTfxturfARB(GL_TEXTURE2_ARB);
        j2d_glDisbblf(GL_TEXTURE_3D);
        j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
        j2d_glDisbblf(GL_TEXTURE_2D);
        j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);
        brfbk;

    dbsf MODE_NO_CACHE_GRAY:
    dbsf MODE_USE_CACHE_GRAY:
    dbsf MODE_NOT_INITED:
    dffbult:
        brfbk;
    }
}

stbtid jboolfbn
OGLTR_DrbwGrbysdblfGlypiVibCbdif(OGLContfxt *ogld,
                                 GlypiInfo *ginfo, jint x, jint y)
{
    CbdifCfllInfo *dfll;
    jflobt x1, y1, x2, y2;

    if (glypiModf != MODE_USE_CACHE_GRAY) {
        OGLTR_DisbblfGlypiModfStbtf();
        CHECK_PREVIOUS_OP(OGL_STATE_GLYPH_OP);
        glypiModf = MODE_USE_CACHE_GRAY;
    }

    if (ginfo->dfllInfo == NULL) {
        // bttfmpt to bdd glypi to bddflfrbtfd glypi dbdif
        OGLTR_AddToGlypiCbdif(ginfo, JNI_FALSE);

        if (ginfo->dfllInfo == NULL) {
            // wf'll just no-op in tif rbrf dbsf tibt tif dfll is NULL
            rfturn JNI_TRUE;
        }
    }

    dfll = (CbdifCfllInfo *) (ginfo->dfllInfo);
    dfll->timfsRfndfrfd++;

    x1 = (jflobt)x;
    y1 = (jflobt)y;
    x2 = x1 + ginfo->widti;
    y2 = y1 + ginfo->ifigit;

    OGLVfrtfxCbdif_AddGlypiQubd(ogld,
                                dfll->tx1, dfll->ty1,
                                dfll->tx2, dfll->ty2,
                                x1, y1, x2, y2);

    rfturn JNI_TRUE;
}

/**
 * Evblubtfs to truf if tif rfdtbnglf dffinfd by gx1/gy1/gx2/gy2 is
 * insidf outfrBounds.
 */
#dffinf INSIDE(gx1, gy1, gx2, gy2, outfrBounds) \
    (((gx1) >= outfrBounds.x1) && ((gy1) >= outfrBounds.y1) && \
     ((gx2) <= outfrBounds.x2) && ((gy2) <= outfrBounds.y2))

/**
 * Evblubtfs to truf if tif rfdtbnglf dffinfd by gx1/gy1/gx2/gy2 intfrsfdts
 * tif rfdtbnglf dffinfd by bounds.
 */
#dffinf INTERSECTS(gx1, gy1, gx2, gy2, bounds) \
    ((bounds.x2 > (gx1)) && (bounds.y2 > (gy1)) && \
     (bounds.x1 < (gx2)) && (bounds.y1 < (gy2)))

/**
 * Tiis mftiod difdks to sff if tif givfn LCD glypi bounds fbll witiin tif
 * dbdifd dfstinbtion tfxturf bounds.  If so, tiis mftiod dbn rfturn
 * immfdibtfly.  If not, tiis mftiod will dopy b diunk of frbmfbufffr dbtb
 * into tif dbdifd dfstinbtion tfxturf bnd tifn updbtf tif durrfnt dbdifd
 * dfstinbtion bounds bfforf rfturning.
 */
stbtid void
OGLTR_UpdbtfCbdifdDfstinbtion(OGLSDOps *dstOps, GlypiInfo *ginfo,
                              jint gx1, jint gy1, jint gx2, jint gy2,
                              jint glypiIndfx, jint totblGlypis)
{
    jint dx1, dy1, dx2, dy2;
    jint dx1bdj, dy1bdj;

    if (isCbdifdDfstVblid && INSIDE(gx1, gy1, gx2, gy2, dbdifdDfstBounds)) {
        // glypi is blrfbdy witiin tif dbdifd dfstinbtion bounds; no nffd
        // to rfbd bbdk tif fntirf dfstinbtion rfgion bgbin, but wf do
        // nffd to sff if tif durrfnt glypi ovfrlbps tif prfvious glypi...

        if (INTERSECTS(gx1, gy1, gx2, gy2, prfviousGlypiBounds)) {
            // tif durrfnt glypi ovfrlbps tif dfstinbtion rfgion toudifd
            // by tif prfvious glypi, so now wf nffd to rfbd bbdk tif pbrt
            // of tif dfstinbtion dorrfsponding to tif prfvious glypi
            dx1 = prfviousGlypiBounds.x1;
            dy1 = prfviousGlypiBounds.y1;
            dx2 = prfviousGlypiBounds.x2;
            dy2 = prfviousGlypiBounds.y2;

            // tiis bddounts for lowfr-lfft origin of tif dfstinbtion rfgion
            dx1bdj = dstOps->xOffsft + dx1;
            dy1bdj = dstOps->yOffsft + dstOps->ifigit - dy2;

            // dopy dfstinbtion into subrfgion of dbdifd tfxturf tilf:
            //   dx1-dbdifdDfstBounds.x1 == +xoffsft from lfft sidf of tfxturf
            //   dbdifdDfstBounds.y2-dy2 == +yoffsft from bottom of tfxturf
            j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
            j2d_glCopyTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                                    dx1 - dbdifdDfstBounds.x1,
                                    dbdifdDfstBounds.y2 - dy2,
                                    dx1bdj, dy1bdj,
                                    dx2-dx1, dy2-dy1);
        }
    } flsf {
        jint rfmbiningWidti;

        // dfstinbtion rfgion is not vblid, so wf nffd to rfbd bbdk b
        // diunk of tif dfstinbtion into our dbdifd tfxturf

        // position tif uppfr-lfft dornfr of tif dfstinbtion rfgion on tif
        // "top" linf of glypi list
        // REMIND: tiis isn't idfbl; it would bf bfttfr if wf ibd somf idfb
        //         of tif bounding box of tif wiolf glypi list (tiis is
        //         do-bblf, but would rfquirf itfrbting tirougi tif wiolf
        //         list up front, wiidi mby prfsfnt its own problfms)
        dx1 = gx1;
        dy1 = gy1;

        if (ginfo->bdvbndfX > 0) {
            // fstimbtf tif widti bbsfd on our durrfnt position in tif glypi
            // list bnd using tif x bdvbndf of tif durrfnt glypi (tiis is just
            // b quidk bnd dirty ifuristid; if tiis is b "tiin" glypi imbgf,
            // tifn wf'rf likfly to undfrfstimbtf, bnd if it's "tiidk" tifn wf
            // mby fnd up rfbding bbdk morf tibn wf nffd to)
            rfmbiningWidti =
                (jint)(ginfo->bdvbndfX * (totblGlypis - glypiIndfx));
            if (rfmbiningWidti > OGLTR_CACHED_DEST_WIDTH) {
                rfmbiningWidti = OGLTR_CACHED_DEST_WIDTH;
            } flsf if (rfmbiningWidti < ginfo->widti) {
                // in somf dbsfs, tif x-bdvbndf mby bf sligitly smbllfr
                // tibn tif bdtubl widti of tif glypi; if so, bdjust our
                // fstimbtf so tibt wf dbn bddommodbtf tif fntirf glypi
                rfmbiningWidti = ginfo->widti;
            }
        } flsf {
            // b nfgbtivf bdvbndf is possiblf wifn rfndfring rotbtfd tfxt,
            // in wiidi dbsf it is diffidult to fstimbtf bn bppropribtf
            // rfgion for rfbdbbdk, so wf will pidk b rfgion tibt
            // fndompbssfs just tif durrfnt glypi
            rfmbiningWidti = ginfo->widti;
        }
        dx2 = dx1 + rfmbiningWidti;

        // fstimbtf tif ifigit (tiis is bnotifr sloppy ifuristid; wf'll
        // mbkf tif dbdifd dfstinbtion rfgion tbll fnougi to fndompbss most
        // glypis tibt brf smbll fnougi to fit in tif glypi dbdif, bnd tifn
        // wf bdd b littlf somftiing fxtrb to bddount for dfsdfndfrs
        dy2 = dy1 + OGLTR_CACHE_CELL_HEIGHT + 2;

        // tiis bddounts for lowfr-lfft origin of tif dfstinbtion rfgion
        dx1bdj = dstOps->xOffsft + dx1;
        dy1bdj = dstOps->yOffsft + dstOps->ifigit - dy2;

        // dopy dfstinbtion into dbdifd tfxturf tilf (tif lowfr-lfft dornfr
        // of tif dfstinbtion rfgion will bf positionfd bt tif lowfr-lfft
        // dornfr (0,0) of tif tfxturf)
        j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
        j2d_glCopyTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                                0, 0, dx1bdj, dy1bdj,
                                dx2-dx1, dy2-dy1);

        // updbtf tif dbdifd bounds bnd mbrk it vblid
        dbdifdDfstBounds.x1 = dx1;
        dbdifdDfstBounds.y1 = dy1;
        dbdifdDfstBounds.x2 = dx2;
        dbdifdDfstBounds.y2 = dy2;
        isCbdifdDfstVblid = JNI_TRUE;
    }

    // blwbys updbtf tif prfvious glypi bounds
    prfviousGlypiBounds.x1 = gx1;
    prfviousGlypiBounds.y1 = gy1;
    prfviousGlypiBounds.x2 = gx2;
    prfviousGlypiBounds.y2 = gy2;
}

stbtid jboolfbn
OGLTR_DrbwLCDGlypiVibCbdif(OGLContfxt *ogld, OGLSDOps *dstOps,
                           GlypiInfo *ginfo, jint x, jint y,
                           jint glypiIndfx, jint totblGlypis,
                           jboolfbn rgbOrdfr, jint dontrbst)
{
    CbdifCfllInfo *dfll;
    jint dx1, dy1, dx2, dy2;
    jflobt dtx1, dty1, dtx2, dty2;

    if (glypiModf != MODE_USE_CACHE_LCD) {
        OGLTR_DisbblfGlypiModfStbtf();
        CHECK_PREVIOUS_OP(GL_TEXTURE_2D);
        j2d_glPixflStorfi(GL_UNPACK_ALIGNMENT, 1);

        if (glypiCbdif == NULL) {
            if (!OGLTR_InitGlypiCbdif(JNI_TRUE)) {
                rfturn JNI_FALSE;
            }
        }

        if (rgbOrdfr != lbstRGBOrdfr) {
            // nffd to invblidbtf tif dbdif in tiis dbsf; sff dommfnts
            // for lbstRGBOrdfr bbovf
            AddflGlypiCbdif_Invblidbtf(glypiCbdif);
            lbstRGBOrdfr = rgbOrdfr;
        }

        if (!OGLTR_EnbblfLCDGlypiModfStbtf(glypiCbdif->dbdifID, dontrbst)) {
            rfturn JNI_FALSE;
        }

        // wifn b frbgmfnt sibdfr is fnbblfd, tif tfxturf fundtion stbtf is
        // ignorfd, so tif following linf is not nffdfd...
        // OGLC_UPDATE_TEXTURE_FUNCTION(ogld, GL_MODULATE);

        glypiModf = MODE_USE_CACHE_LCD;
    }

    if (ginfo->dfllInfo == NULL) {
        // rowBytfs will blwbys bf b multiplf of 3, so tif following is sbff
        j2d_glPixflStorfi(GL_UNPACK_ROW_LENGTH, ginfo->rowBytfs / 3);

        // mbkf surf tif glypi dbdif tfxturf is bound to tfxturf unit 0
        j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);

        // bttfmpt to bdd glypi to bddflfrbtfd glypi dbdif
        OGLTR_AddToGlypiCbdif(ginfo, rgbOrdfr);

        if (ginfo->dfllInfo == NULL) {
            // wf'll just no-op in tif rbrf dbsf tibt tif dfll is NULL
            rfturn JNI_TRUE;
        }
    }

    dfll = (CbdifCfllInfo *) (ginfo->dfllInfo);
    dfll->timfsRfndfrfd++;

    // lodbtion of tif glypi in tif dfstinbtion's doordinbtf spbdf
    dx1 = x;
    dy1 = y;
    dx2 = dx1 + ginfo->widti;
    dy2 = dy1 + ginfo->ifigit;

    // dopy dfstinbtion into sfdond dbdifd tfxturf, if nfdfssbry
    OGLTR_UpdbtfCbdifdDfstinbtion(dstOps, ginfo,
                                  dx1, dy1, dx2, dy2,
                                  glypiIndfx, totblGlypis);

    // tfxturf doordinbtfs of tif dfstinbtion tilf
    dtx1 = ((jflobt)(dx1 - dbdifdDfstBounds.x1)) / OGLTR_CACHED_DEST_WIDTH;
    dty1 = ((jflobt)(dbdifdDfstBounds.y2 - dy1)) / OGLTR_CACHED_DEST_HEIGHT;
    dtx2 = ((jflobt)(dx2 - dbdifdDfstBounds.x1)) / OGLTR_CACHED_DEST_WIDTH;
    dty2 = ((jflobt)(dbdifdDfstBounds.y2 - dy2)) / OGLTR_CACHED_DEST_HEIGHT;

    // rfndfr domposfd tfxturf to tif dfstinbtion surfbdf
    j2d_glBfgin(GL_QUADS);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, dfll->tx1, dfll->ty1);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty1);
    j2d_glVfrtfx2i(dx1, dy1);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, dfll->tx2, dfll->ty1);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty1);
    j2d_glVfrtfx2i(dx2, dy1);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, dfll->tx2, dfll->ty2);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty2);
    j2d_glVfrtfx2i(dx2, dy2);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, dfll->tx1, dfll->ty2);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty2);
    j2d_glVfrtfx2i(dx1, dy2);
    j2d_glEnd();

    rfturn JNI_TRUE;
}

stbtid jboolfbn
OGLTR_DrbwGrbysdblfGlypiNoCbdif(OGLContfxt *ogld,
                                GlypiInfo *ginfo, jint x, jint y)
{
    jint tw, ti;
    jint sx, sy, sw, si;
    jint x0;
    jint w = ginfo->widti;
    jint i = ginfo->ifigit;

    if (glypiModf != MODE_NO_CACHE_GRAY) {
        OGLTR_DisbblfGlypiModfStbtf();
        CHECK_PREVIOUS_OP(OGL_STATE_MASK_OP);
        glypiModf = MODE_NO_CACHE_GRAY;
    }

    x0 = x;
    tw = OGLVC_MASK_CACHE_TILE_WIDTH;
    ti = OGLVC_MASK_CACHE_TILE_HEIGHT;

    for (sy = 0; sy < i; sy += ti, y += ti) {
        x = x0;
        si = ((sy + ti) > i) ? (i - sy) : ti;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            OGLVfrtfxCbdif_AddMbskQubd(ogld,
                                       sx, sy, x, y, sw, si,
                                       w, ginfo->imbgf);
        }
    }

    rfturn JNI_TRUE;
}

stbtid jboolfbn
OGLTR_DrbwLCDGlypiNoCbdif(OGLContfxt *ogld, OGLSDOps *dstOps,
                          GlypiInfo *ginfo, jint x, jint y,
                          jint rowBytfsOffsft,
                          jboolfbn rgbOrdfr, jint dontrbst)
{
    GLflobt tx1, ty1, tx2, ty2;
    GLflobt dtx1, dty1, dtx2, dty2;
    jint tw, ti;
    jint sx, sy, sw, si, dxbdj, dybdj;
    jint x0;
    jint w = ginfo->widti;
    jint i = ginfo->ifigit;
    GLfnum pixflFormbt = rgbOrdfr ? GL_RGB : GL_BGR;

    if (glypiModf != MODE_NO_CACHE_LCD) {
        OGLTR_DisbblfGlypiModfStbtf();
        CHECK_PREVIOUS_OP(GL_TEXTURE_2D);
        j2d_glPixflStorfi(GL_UNPACK_ALIGNMENT, 1);

        if (ogld->blitTfxturfID == 0) {
            if (!OGLContfxt_InitBlitTilfTfxturf(ogld)) {
                rfturn JNI_FALSE;
            }
        }

        if (!OGLTR_EnbblfLCDGlypiModfStbtf(ogld->blitTfxturfID, dontrbst)) {
            rfturn JNI_FALSE;
        }

        // wifn b frbgmfnt sibdfr is fnbblfd, tif tfxturf fundtion stbtf is
        // ignorfd, so tif following linf is not nffdfd...
        // OGLC_UPDATE_TEXTURE_FUNCTION(ogld, GL_MODULATE);

        glypiModf = MODE_NO_CACHE_LCD;
    }

    // rowBytfs will blwbys bf b multiplf of 3, so tif following is sbff
    j2d_glPixflStorfi(GL_UNPACK_ROW_LENGTH, ginfo->rowBytfs / 3);

    x0 = x;
    tx1 = 0.0f;
    ty1 = 0.0f;
    dtx1 = 0.0f;
    dty2 = 0.0f;
    tw = OGLTR_NOCACHE_TILE_SIZE;
    ti = OGLTR_NOCACHE_TILE_SIZE;

    for (sy = 0; sy < i; sy += ti, y += ti) {
        x = x0;
        si = ((sy + ti) > i) ? (i - sy) : ti;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            // updbtf tif sourdf pointfr offsfts
            j2d_glPixflStorfi(GL_UNPACK_SKIP_PIXELS, sx);
            j2d_glPixflStorfi(GL_UNPACK_SKIP_ROWS, sy);

            // dopy LCD mbsk into glypi tfxturf tilf
            j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);
            j2d_glTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                                0, 0, sw, si,
                                pixflFormbt, GL_UNSIGNED_BYTE,
                                ginfo->imbgf + rowBytfsOffsft);

            // updbtf tif lowfr-rigit glypi tfxturf doordinbtfs
            tx2 = ((GLflobt)sw) / OGLC_BLIT_TILE_SIZE;
            ty2 = ((GLflobt)si) / OGLC_BLIT_TILE_SIZE;

            // tiis bddounts for lowfr-lfft origin of tif dfstinbtion rfgion
            dxbdj = dstOps->xOffsft + x;
            dybdj = dstOps->yOffsft + dstOps->ifigit - (y + si);

            // dopy dfstinbtion into dbdifd tfxturf tilf (tif lowfr-lfft
            // dornfr of tif dfstinbtion rfgion will bf positionfd bt tif
            // lowfr-lfft dornfr (0,0) of tif tfxturf)
            j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
            j2d_glCopyTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                                    0, 0,
                                    dxbdj, dybdj,
                                    sw, si);

            // updbtf tif rfmbining dfstinbtion tfxturf doordinbtfs
            dtx2 = ((GLflobt)sw) / OGLTR_CACHED_DEST_WIDTH;
            dty1 = ((GLflobt)si) / OGLTR_CACHED_DEST_HEIGHT;

            // rfndfr domposfd tfxturf to tif dfstinbtion surfbdf
            j2d_glBfgin(GL_QUADS);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, tx1, ty1);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty1);
            j2d_glVfrtfx2i(x, y);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, tx2, ty1);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty1);
            j2d_glVfrtfx2i(x + sw, y);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, tx2, ty2);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty2);
            j2d_glVfrtfx2i(x + sw, y + si);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, tx1, ty2);
            j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty2);
            j2d_glVfrtfx2i(x, y + si);
            j2d_glEnd();
        }
    }

    rfturn JNI_TRUE;
}

// sff DrbwGlypiList.d for morf on tiis mbdro...
#dffinf FLOOR_ASSIGN(l, r) \
    if ((r)<0) (l) = ((int)floor(r)); flsf (l) = ((int)(r))

void
OGLTR_DrbwGlypiList(JNIEnv *fnv, OGLContfxt *ogld, OGLSDOps *dstOps,
                    jint totblGlypis, jboolfbn usfPositions,
                    jboolfbn subPixPos, jboolfbn rgbOrdfr, jint lddContrbst,
                    jflobt glypiListOrigX, jflobt glypiListOrigY,
                    unsignfd dibr *imbgfs, unsignfd dibr *positions)
{
    int glypiCountfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTR_DrbwGlypiList");

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(dstOps);
    RETURN_IF_NULL(imbgfs);
    if (usfPositions) {
        RETURN_IF_NULL(positions);
    }

    glypiModf = MODE_NOT_INITED;
    isCbdifdDfstVblid = JNI_FALSE;

    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) {
        jint x, y;
        jflobt glypix, glypiy;
        jboolfbn grbysdblf, ok;
        GlypiInfo *ginfo = (GlypiInfo *)jlong_to_ptr(NEXT_LONG(imbgfs));

        if (ginfo == NULL) {
            // tiis siouldn't ibppfn, but if it dofs wf'll just brfbk out...
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                          "OGLTR_DrbwGlypiList: glypi info is null");
            brfbk;
        }

        grbysdblf = (ginfo->rowBytfs == ginfo->widti);

        if (usfPositions) {
            jflobt posx = NEXT_FLOAT(positions);
            jflobt posy = NEXT_FLOAT(positions);
            glypix = glypiListOrigX + posx + ginfo->topLfftX;
            glypiy = glypiListOrigY + posy + ginfo->topLfftY;
            FLOOR_ASSIGN(x, glypix);
            FLOOR_ASSIGN(y, glypiy);
        } flsf {
            glypix = glypiListOrigX + ginfo->topLfftX;
            glypiy = glypiListOrigY + ginfo->topLfftY;
            FLOOR_ASSIGN(x, glypix);
            FLOOR_ASSIGN(y, glypiy);
            glypiListOrigX += ginfo->bdvbndfX;
            glypiListOrigY += ginfo->bdvbndfY;
        }

        if (ginfo->imbgf == NULL) {
            dontinuf;
        }

        if (grbysdblf) {
            // grbysdblf or monodiromf glypi dbtb
            if (dbdifStbtus != CACHE_LCD &&
                ginfo->widti <= OGLTR_CACHE_CELL_WIDTH &&
                ginfo->ifigit <= OGLTR_CACHE_CELL_HEIGHT)
            {
                ok = OGLTR_DrbwGrbysdblfGlypiVibCbdif(ogld, ginfo, x, y);
            } flsf {
                ok = OGLTR_DrbwGrbysdblfGlypiNoCbdif(ogld, ginfo, x, y);
            }
        } flsf {
            // LCD-optimizfd glypi dbtb
            jint rowBytfsOffsft = 0;

            if (subPixPos) {
                jint frbd = (jint)((glypix - x) * 3);
                if (frbd != 0) {
                    rowBytfsOffsft = 3 - frbd;
                    x += 1;
                }
            }

            if (rowBytfsOffsft == 0 &&
                dbdifStbtus != CACHE_GRAY &&
                ginfo->widti <= OGLTR_CACHE_CELL_WIDTH &&
                ginfo->ifigit <= OGLTR_CACHE_CELL_HEIGHT)
            {
                ok = OGLTR_DrbwLCDGlypiVibCbdif(ogld, dstOps,
                                                ginfo, x, y,
                                                glypiCountfr, totblGlypis,
                                                rgbOrdfr, lddContrbst);
            } flsf {
                ok = OGLTR_DrbwLCDGlypiNoCbdif(ogld, dstOps,
                                               ginfo, x, y,
                                               rowBytfsOffsft,
                                               rgbOrdfr, lddContrbst);
            }
        }

        if (!ok) {
            brfbk;
        }
    }

    OGLTR_DisbblfGlypiModfStbtf();
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLTfxtRfndfrfr_drbwGlypiList
    (JNIEnv *fnv, jobjfdt sflf,
     jint numGlypis, jboolfbn usfPositions,
     jboolfbn subPixPos, jboolfbn rgbOrdfr, jint lddContrbst,
     jflobt glypiListOrigX, jflobt glypiListOrigY,
     jlongArrby imgArrby, jflobtArrby posArrby)
{
    unsignfd dibr *imbgfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLTfxtRfndfrfr_drbwGlypiList");

    imbgfs = (unsignfd dibr *)
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, imgArrby, NULL);
    if (imbgfs != NULL) {
        OGLContfxt *ogld = OGLRfndfrQufuf_GftCurrfntContfxt();
        OGLSDOps *dstOps = OGLRfndfrQufuf_GftCurrfntDfstinbtion();

        if (usfPositions) {
            unsignfd dibr *positions = (unsignfd dibr *)
                (*fnv)->GftPrimitivfArrbyCritidbl(fnv, posArrby, NULL);
            if (positions != NULL) {
                OGLTR_DrbwGlypiList(fnv, ogld, dstOps,
                                    numGlypis, usfPositions,
                                    subPixPos, rgbOrdfr, lddContrbst,
                                    glypiListOrigX, glypiListOrigY,
                                    imbgfs, positions);
                (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, posArrby,
                                                      positions, JNI_ABORT);
            }
        } flsf {
            OGLTR_DrbwGlypiList(fnv, ogld, dstOps,
                                numGlypis, usfPositions,
                                subPixPos, rgbOrdfr, lddContrbst,
                                glypiListOrigX, glypiListOrigY,
                                imbgfs, NULL);
        }

        // 6358147: rfsft durrfnt stbtf, bnd fnsurf rfndfring is
        // flusifd to dfst
        if (ogld != NULL) {
            RESET_PREVIOUS_OP();
            j2d_glFlusi();
        }

        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, imgArrby,
                                              imbgfs, JNI_ABORT);
    }
}

#fndif /* !HEADLESS */
