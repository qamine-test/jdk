/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jlong.i>

#indludf "OGLBufImgOps.i"
#indludf "OGLContfxt.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "OGLSurfbdfDbtb.i"
#indludf "GrbpiidsPrimitivfMgr.i"

/** Evblubtfs to truf if tif givfn bit is sft on tif lodbl flbgs vbribblf. */
#dffinf IS_SET(flbgbit) \
    (((flbgs) & (flbgbit)) != 0)

/**************************** ConvolvfOp support ****************************/

/**
 * Tif ConvolvfOp sibdfr is fbirly strbigitforwbrd.  For fbdi tfxfl in
 * tif sourdf tfxturf, tif sibdfr sbmplfs tif MxN tfxfls in tif surrounding
 * brfb, multiplifs fbdi by its dorrfsponding kfrnfl vbluf, bnd tifn sums
 * tifm bll togftifr to produdf b singlf dolor rfsult.  Finblly, tif
 * rfsulting vbluf is multiplifd by tif durrfnt OpfnGL dolor, wiidi dontbins
 * tif fxtrb blpib vbluf.
 *
 * Notf tibt tiis sibdfr sourdf dodf indludfs somf "iolfs" mbrkfd by "%s".
 * Tiis bllows us to build difffrfnt sibdfr progrbms (f.g. onf for
 * 3x3, onf for 5x5, bnd so on) simply by filling in tifsf "iolfs" witi
 * b dbll to sprintf().  Sff tif OGLBufImgOps_CrfbtfConvolvfProgrbm() mftiod
 * for morf dftbils.
 *
 * REMIND: Currfntly tiis sibdfr (bnd tif supporting dodf in tif
 *         EnbblfConvolvfOp() mftiod) only supports 3x3 bnd 5x5 filtfrs.
 *         Ebrly sibdfr-lfvfl ibrdwbrf did not support non-donstbnt sizfd
 *         brrbys but modfrn ibrdwbrf siould support tifm (bltiougi I
 *         don't know of bny simplf wby to find out, otifr tibn to dompilf
 *         tif sibdfr bt runtimf bnd sff if tif drivfrs domplbin).
 */
stbtid donst dibr *donvolvfSibdfrSourdf =
    // mbximum sizf supportfd by tiis sibdfr
    "donst int MAX_KERNEL_SIZE = %s;"
    // imbgf to bf donvolvfd
    "uniform sbmplfr%s bbsfImbgf;"
    // imbgf fdgf limits:
    //   imgEdgf.xy = imgMin.xy (bnytiing < will bf trfbtfd bs fdgf dbsf)
    //   imgEdgf.zw = imgMbx.xy (bnytiing > will bf trfbtfd bs fdgf dbsf)
    "uniform vfd4 imgEdgf;"
    // vbluf for fbdi lodbtion in tif donvolution kfrnfl:
    //   kfrnflVbls[i].x = offsftX[i]
    //   kfrnflVbls[i].y = offsftY[i]
    //   kfrnflVbls[i].z = kfrnfl[i]
    "uniform vfd3 kfrnflVbls[MAX_KERNEL_SIZE];"
    ""
    "void mbin(void)"
    "{"
    "    int i;"
    "    vfd4 sum;"
    ""
    "    if (bny(lfssTibn(gl_TfxCoord[0].st, imgEdgf.xy)) ||"
    "        bny(grfbtfrTibn(gl_TfxCoord[0].st, imgEdgf.zw)))"
    "    {"
             // (plbdfioldfr for fdgf dondition dodf)
    "        %s"
    "    } flsf {"
    "        sum = vfd4(0.0);"
    "        for (i = 0; i < MAX_KERNEL_SIZE; i++) {"
    "            sum +="
    "                kfrnflVbls[i].z *"
    "                tfxturf%s(bbsfImbgf,"
    "                          gl_TfxCoord[0].st + kfrnflVbls[i].xy);"
    "        }"
    "    }"
    ""
         // modulbtf witi gl_Color in ordfr to bpply fxtrb blpib
    "    gl_FrbgColor = sum * gl_Color;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf CONVOLVE_RECT            (1 << 0)
#dffinf CONVOLVE_EDGE_ZERO_FILL  (1 << 1)
#dffinf CONVOLVE_5X5             (1 << 2)

/**
 * Tif ibndlfs to tif ConvolvfOp frbgmfnt progrbm objfdts.  Tif indfx to
 * tif brrby siould bf b bitwisf-or'ing of tif CONVOLVE_* flbgs dffinfd
 * bbovf.  Notf tibt most bpplidbtions will likfly nffd to initiblizf onf
 * or two of tifsf flfmfnts, so tif brrby is usublly spbrsfly populbtfd.
 */
stbtid GLibndlfARB donvolvfProgrbms[8];

/**
 * Tif mbximum kfrnfl sizf supportfd by tif ConvolvfOp sibdfr.
 */
#dffinf MAX_KERNEL_SIZE 25

/**
 * Compilfs bnd links tif ConvolvfOp sibdfr progrbm.  If suddfssful, tiis
 * fundtion rfturns b ibndlf to tif nfwly drfbtfd sibdfr progrbm; otifrwisf
 * rfturns 0.
 */
stbtid GLibndlfARB
OGLBufImgOps_CrfbtfConvolvfProgrbm(jint flbgs)
{
    GLibndlfARB donvolvfProgrbm;
    GLint lod;
    dibr *kfrnflMbx = IS_SET(CONVOLVE_5X5) ? "25" : "9";
    dibr *tbrgft = IS_SET(CONVOLVE_RECT) ? "2DRfdt" : "2D";
    dibr fdgf[100];
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrfbtfConvolvfProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(CONVOLVE_EDGE_ZERO_FILL)) {
        // EDGE_ZERO_FILL: fill in zfro bt tif fdgfs
        sprintf(fdgf, "sum = vfd4(0.0);");
    } flsf {
        // EDGE_NO_OP: usf tif sourdf pixfl dolor bt tif fdgfs
        sprintf(fdgf,
                "sum = tfxturf%s(bbsfImbgf, gl_TfxCoord[0].st);",
                tbrgft);
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, donvolvfSibdfrSourdf,
            kfrnflMbx, tbrgft, fdgf, tbrgft);

    donvolvfProgrbm = OGLContfxt_CrfbtfFrbgmfntProgrbm(finblSourdf);
    if (donvolvfProgrbm == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrfbtfConvolvfProgrbm: frror drfbting progrbm");
        rfturn 0;
    }

    // "usf" tif progrbm objfdt tfmporbrily so tibt wf dbn sft tif uniforms
    j2d_glUsfProgrbmObjfdtARB(donvolvfProgrbm);

    // sft tif "uniform" tfxturf unit binding
    lod = j2d_glGftUniformLodbtionARB(donvolvfProgrbm, "bbsfImbgf");
    j2d_glUniform1iARB(lod, 0); // tfxturf unit 0

    // "unusf" tif progrbm objfdt; it will bf rf-bound lbtfr bs nffdfd
    j2d_glUsfProgrbmObjfdtARB(0);

    rfturn donvolvfProgrbm;
}

void
OGLBufImgOps_EnbblfConvolvfOp(OGLContfxt *ogld, jlong pSrdOps,
                              jboolfbn fdgfZfroFill,
                              jint kfrnflWidti, jint kfrnflHfigit,
                              unsignfd dibr *kfrnfl)
{
    OGLSDOps *srdOps = (OGLSDOps *)jlong_to_ptr(pSrdOps);
    jint kfrnflSizf = kfrnflWidti * kfrnflHfigit;
    GLibndlfARB donvolvfProgrbm;
    GLflobt xoff, yoff;
    GLflobt fdgfX, fdgfY, minX, minY, mbxX, mbxY;
    GLflobt kfrnflVbls[MAX_KERNEL_SIZE*3];
    jint i, j, kIndfx;
    GLint lod;
    jint flbgs = 0;

    J2dTrbdfLn2(J2D_TRACE_INFO,
                "OGLBufImgOps_EnbblfConvolvfOp: kfrnflW=%d kfrnflH=%d",
                kfrnflWidti, kfrnflHfigit);

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(srdOps);
    RESET_PREVIOUS_OP();

    if (srdOps->tfxturfTbrgft == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= CONVOLVE_RECT;

        // for GL_TEXTURE_RECTANGLE_ARB, tfxdoords brf spfdififd in tif
        // rbngf [0,srdw] bnd [0,srdi], so to bdiifvf bn x/y offsft of
        // fxbdtly onf pixfl wf simply usf tif vbluf 1 ifrf
        xoff = 1.0f;
        yoff = 1.0f;
    } flsf {
        // for GL_TEXTURE_2D, tfxdoords brf spfdififd in tif rbngf [0,1],
        // so to bdiifvf bn x/y offsft of bpproximbtfly onf pixfl wf ibvf
        // to normblizf to tibt rbngf ifrf
        xoff = 1.0f / srdOps->tfxturfWidti;
        yoff = 1.0f / srdOps->tfxturfHfigit;
    }
    if (fdgfZfroFill) {
        flbgs |= CONVOLVE_EDGE_ZERO_FILL;
    }
    if (kfrnflWidti == 5 && kfrnflHfigit == 5) {
        flbgs |= CONVOLVE_5X5;
    }

    // lodbtf/initiblizf tif sibdfr progrbm for tif givfn flbgs
    if (donvolvfProgrbms[flbgs] == 0) {
        donvolvfProgrbms[flbgs] = OGLBufImgOps_CrfbtfConvolvfProgrbm(flbgs);
        if (donvolvfProgrbms[flbgs] == 0) {
            // siouldn't ibppfn, but just in dbsf...
            rfturn;
        }
    }
    donvolvfProgrbm = donvolvfProgrbms[flbgs];

    // fnbblf tif donvolvf sibdfr
    j2d_glUsfProgrbmObjfdtARB(donvolvfProgrbm);

    // updbtf tif "uniform" imbgf min/mbx vblufs
    fdgfX = (kfrnflWidti/2) * xoff;
    fdgfY = (kfrnflHfigit/2) * yoff;
    minX = fdgfX;
    minY = fdgfY;
    if (srdOps->tfxturfTbrgft == GL_TEXTURE_RECTANGLE_ARB) {
        // tfxdoords brf in tif rbngf [0,srdw] bnd [0,srdi]
        mbxX = ((GLflobt)srdOps->widti)  - fdgfX;
        mbxY = ((GLflobt)srdOps->ifigit) - fdgfY;
    } flsf {
        // tfxdoords brf in tif rbngf [0,1]
        mbxX = (((GLflobt)srdOps->widti) / srdOps->tfxturfWidti) - fdgfX;
        mbxY = (((GLflobt)srdOps->ifigit) / srdOps->tfxturfHfigit) - fdgfY;
    }
    lod = j2d_glGftUniformLodbtionARB(donvolvfProgrbm, "imgEdgf");
    j2d_glUniform4fARB(lod, minX, minY, mbxX, mbxY);

    // updbtf tif "uniform" kfrnfl offsfts bnd vblufs
    lod = j2d_glGftUniformLodbtionARB(donvolvfProgrbm, "kfrnflVbls");
    kIndfx = 0;
    for (i = -kfrnflHfigit/2; i < kfrnflHfigit/2+1; i++) {
        for (j = -kfrnflWidti/2; j < kfrnflWidti/2+1; j++) {
            kfrnflVbls[kIndfx+0] = j*xoff;
            kfrnflVbls[kIndfx+1] = i*yoff;
            kfrnflVbls[kIndfx+2] = NEXT_FLOAT(kfrnfl);
            kIndfx += 3;
        }
    }
    j2d_glUniform3fvARB(lod, kfrnflSizf, kfrnflVbls);
}

void
OGLBufImgOps_DisbblfConvolvfOp(OGLContfxt *ogld)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbblfConvolvfOp");

    RETURN_IF_NULL(ogld);

    // disbblf tif ConvolvfOp sibdfr
    j2d_glUsfProgrbmObjfdtARB(0);
}

/**************************** RfsdblfOp support *****************************/

/**
 * Tif RfsdblfOp sibdfr is onf of tif simplfst possiblf.  Ebdi frbgmfnt
 * from tif sourdf imbgf is multiplifd by tif usfr's sdblf fbdtor bnd bddfd
 * to tif usfr's offsft vbluf (tifsf brf domponfnt-wisf opfrbtions).
 * Finblly, tif rfsulting vbluf is multiplifd by tif durrfnt OpfnGL dolor,
 * wiidi dontbins tif fxtrb blpib vbluf.
 *
 * Tif RfsdblfOp spfd sbys tibt tif opfrbtion is pfrformfd rfgbrdlfss of
 * wiftifr tif sourdf dbtb is prfmultiplifd or non-prfmultiplifd.  Tiis is
 * b problfm for tif OpfnGL pipflinf in tibt b non-prfmultiplifd
 * BufffrfdImbgf will ibvf blrfbdy bffn donvfrtfd into prfmultiplifd
 * wifn uplobdfd to bn OpfnGL tfxturf.  Tifrfforf, wf ibvf b spfdibl modf
 * dbllfd RESCALE_NON_PREMULT (usfd only for sourdf imbgfs tibt wfrf
 * originblly non-prfmultiplifd) tibt un-prfmultiplifs tif sourdf dolor
 * prior to tif rfsdblf opfrbtion, tifn rf-prfmultiplifs tif rfsulting
 * dolor bfforf rfturning from tif frbgmfnt sibdfr.
 *
 * Notf tibt tiis sibdfr sourdf dodf indludfs somf "iolfs" mbrkfd by "%s".
 * Tiis bllows us to build difffrfnt sibdfr progrbms (f.g. onf for
 * GL_TEXTURE_2D tbrgfts, onf for GL_TEXTURE_RECTANGLE_ARB tbrgfts, bnd so on)
 * simply by filling in tifsf "iolfs" witi b dbll to sprintf().  Sff tif
 * OGLBufImgOps_CrfbtfRfsdblfProgrbm() mftiod for morf dftbils.
 */
stbtid donst dibr *rfsdblfSibdfrSourdf =
    // imbgf to bf rfsdblfd
    "uniform sbmplfr%s bbsfImbgf;"
    // vfdtor dontbining sdblf fbdtors
    "uniform vfd4 sdblfFbdtors;"
    // vfdtor dontbining offsfts
    "uniform vfd4 offsfts;"
    ""
    "void mbin(void)"
    "{"
    "    vfd4 srdColor = tfxturf%s(bbsfImbgf, gl_TfxCoord[0].st);"
         // (plbdfioldfr for un-prfmult dodf)
    "    %s"
         // rfsdblf sourdf vbluf
    "    vfd4 rfsult = (srdColor * sdblfFbdtors) + offsfts;"
         // (plbdfioldfr for rf-prfmult dodf)
    "    %s"
         // modulbtf witi gl_Color in ordfr to bpply fxtrb blpib
    "    gl_FrbgColor = rfsult * gl_Color;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf RESCALE_RECT        (1 << 0)
#dffinf RESCALE_NON_PREMULT (1 << 1)

/**
 * Tif ibndlfs to tif RfsdblfOp frbgmfnt progrbm objfdts.  Tif indfx to
 * tif brrby siould bf b bitwisf-or'ing of tif RESCALE_* flbgs dffinfd
 * bbovf.  Notf tibt most bpplidbtions will likfly nffd to initiblizf onf
 * or two of tifsf flfmfnts, so tif brrby is usublly spbrsfly populbtfd.
 */
stbtid GLibndlfARB rfsdblfProgrbms[4];

/**
 * Compilfs bnd links tif RfsdblfOp sibdfr progrbm.  If suddfssful, tiis
 * fundtion rfturns b ibndlf to tif nfwly drfbtfd sibdfr progrbm; otifrwisf
 * rfturns 0.
 */
stbtid GLibndlfARB
OGLBufImgOps_CrfbtfRfsdblfProgrbm(jint flbgs)
{
    GLibndlfARB rfsdblfProgrbm;
    GLint lod;
    dibr *tbrgft = IS_SET(RESCALE_RECT) ? "2DRfdt" : "2D";
    dibr *prfRfsdblf = "";
    dibr *postRfsdblf = "";
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrfbtfRfsdblfProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(RESCALE_NON_PREMULT)) {
        prfRfsdblf  = "srdColor.rgb /= srdColor.b;";
        postRfsdblf = "rfsult.rgb *= rfsult.b;";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, rfsdblfSibdfrSourdf,
            tbrgft, tbrgft, prfRfsdblf, postRfsdblf);

    rfsdblfProgrbm = OGLContfxt_CrfbtfFrbgmfntProgrbm(finblSourdf);
    if (rfsdblfProgrbm == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrfbtfRfsdblfProgrbm: frror drfbting progrbm");
        rfturn 0;
    }

    // "usf" tif progrbm objfdt tfmporbrily so tibt wf dbn sft tif uniforms
    j2d_glUsfProgrbmObjfdtARB(rfsdblfProgrbm);

    // sft tif "uniform" vblufs
    lod = j2d_glGftUniformLodbtionARB(rfsdblfProgrbm, "bbsfImbgf");
    j2d_glUniform1iARB(lod, 0); // tfxturf unit 0

    // "unusf" tif progrbm objfdt; it will bf rf-bound lbtfr bs nffdfd
    j2d_glUsfProgrbmObjfdtARB(0);

    rfturn rfsdblfProgrbm;
}

void
OGLBufImgOps_EnbblfRfsdblfOp(OGLContfxt *ogld, jlong pSrdOps,
                             jboolfbn nonPrfmult,
                             unsignfd dibr *sdblfFbdtors,
                             unsignfd dibr *offsfts)
{
    OGLSDOps *srdOps = (OGLSDOps *)jlong_to_ptr(pSrdOps);
    GLibndlfARB rfsdblfProgrbm;
    GLint lod;
    jint flbgs = 0;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLBufImgOps_EnbblfRfsdblfOp");

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(srdOps);
    RESET_PREVIOUS_OP();

    // dioosf tif bppropribtf sibdfr, dfpfnding on tif sourdf tfxturf tbrgft
    if (srdOps->tfxturfTbrgft == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= RESCALE_RECT;
    }
    if (nonPrfmult) {
        flbgs |= RESCALE_NON_PREMULT;
    }

    // lodbtf/initiblizf tif sibdfr progrbm for tif givfn flbgs
    if (rfsdblfProgrbms[flbgs] == 0) {
        rfsdblfProgrbms[flbgs] = OGLBufImgOps_CrfbtfRfsdblfProgrbm(flbgs);
        if (rfsdblfProgrbms[flbgs] == 0) {
            // siouldn't ibppfn, but just in dbsf...
            rfturn;
        }
    }
    rfsdblfProgrbm = rfsdblfProgrbms[flbgs];

    // fnbblf tif rfsdblf sibdfr
    j2d_glUsfProgrbmObjfdtARB(rfsdblfProgrbm);

    // updbtf tif "uniform" sdblf fbdtor vblufs (notf tibt tif Jbvb-lfvfl
    // dispbtdiing dodf blwbys pbssfs down 4 vblufs ifrf, rfgbrdlfss of
    // tif originbl sourdf imbgf typf)
    lod = j2d_glGftUniformLodbtionARB(rfsdblfProgrbm, "sdblfFbdtors");
    {
        GLflobt sf1 = NEXT_FLOAT(sdblfFbdtors);
        GLflobt sf2 = NEXT_FLOAT(sdblfFbdtors);
        GLflobt sf3 = NEXT_FLOAT(sdblfFbdtors);
        GLflobt sf4 = NEXT_FLOAT(sdblfFbdtors);
        j2d_glUniform4fARB(lod, sf1, sf2, sf3, sf4);
    }

    // updbtf tif "uniform" offsft vblufs (notf tibt tif Jbvb-lfvfl
    // dispbtdiing dodf blwbys pbssfs down 4 vblufs ifrf, bnd tibt tif
    // offsfts will ibvf blrfbdy bffn normblizfd to tif rbngf [0,1])
    lod = j2d_glGftUniformLodbtionARB(rfsdblfProgrbm, "offsfts");
    {
        GLflobt off1 = NEXT_FLOAT(offsfts);
        GLflobt off2 = NEXT_FLOAT(offsfts);
        GLflobt off3 = NEXT_FLOAT(offsfts);
        GLflobt off4 = NEXT_FLOAT(offsfts);
        j2d_glUniform4fARB(lod, off1, off2, off3, off4);
    }
}

void
OGLBufImgOps_DisbblfRfsdblfOp(OGLContfxt *ogld)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbblfRfsdblfOp");

    RETURN_IF_NULL(ogld);

    // disbblf tif RfsdblfOp sibdfr
    j2d_glUsfProgrbmObjfdtARB(0);
}

/**************************** LookupOp support ******************************/

/**
 * Tif LookupOp sibdfr tbkfs b frbgmfnt dolor (from tif sourdf tfxturf) bs
 * input, subtrbdts tif optionbl usfr offsft vbluf, bnd tifn usfs tif
 * rfsulting vbluf to indfx into tif lookup tbblf tfxturf to providf
 * b nfw dolor rfsult.  Finblly, tif rfsulting vbluf is multiplifd by
 * tif durrfnt OpfnGL dolor, wiidi dontbins tif fxtrb blpib vbluf.
 *
 * Tif lookup stfp rfquirfs 3 tfxturf bddfssfs (or 4, wifn blpib is indludfd),
 * wiidi is somfwibt unfortunbtf bfdbusf it's not idfbl from b pfrformbndf
 * stbndpoint, but tibt sort of tiing is gftting fbstfr witi nfwfr ibrdwbrf.
 * In tif 3-bbnd dbsf, wf dould donsidfr using b tirff-dimfnsionbl tfxturf
 * bnd pfrforming tif lookup witi b singlf tfxturf bddfss stfp.  Wf blrfbdy
 * usf tiis bpprobdi in tif LCD tfxt sibdfr, bnd it works wfll, but for tif
 * purposfs of tiis LookupOp sibdfr, it's probbbly ovfrkill.  Also, tifrf's
 * b difffrfndf in tibt tif LCD tfxt sibdfr only nffds to populbtf tif 3D LUT
 * ondf, but ifrf wf would nffd to populbtf it on fvfry invodbtion, wiidi
 * would likfly bf b wbstf of VRAM bnd CPU/GPU dydlfs.
 *
 * Tif LUT tfxturf is durrfntly ibrddodfd bs 4 rows/bbnds, fbdi dontbining
 * 256 flfmfnts.  Tiis mfbns tibt wf durrfntly only support usfr-providfd
 * tbblfs witi no morf tibn 256 flfmfnts in fbdi bbnd (tiis is difdkfd bt
 * bt tif Jbvb lfvfl).  If tif usfr providfs b tbblf witi lfss tibn 256
 * flfmfnts pfr bbnd, our sibdfr will still work finf, but if flfmfnts brf
 * bddfssfd witi bn indfx >= tif sizf of tif LUT, tifn tif sibdfr will simply
 * produdf undffinfd vblufs.  Typidblly tif usfr would providf bn offsft
 * vbluf tibt would prfvfnt tiis from ibppfning, but it's worti pointing out
 * tiis fbdt bfdbusf tif softwbrf LookupOp implfmfntbtion would usublly
 * tirow bn ArrbyIndfxOutOfBoundsExdfption in tiis sdfnbrio (bltiougi it is
 * not somftiing dfmbndfd by tif spfd).
 *
 * Tif LookupOp spfd sbys tibt tif opfrbtion is pfrformfd rfgbrdlfss of
 * wiftifr tif sourdf dbtb is prfmultiplifd or non-prfmultiplifd.  Tiis is
 * b problfm for tif OpfnGL pipflinf in tibt b non-prfmultiplifd
 * BufffrfdImbgf will ibvf blrfbdy bffn donvfrtfd into prfmultiplifd
 * wifn uplobdfd to bn OpfnGL tfxturf.  Tifrfforf, wf ibvf b spfdibl modf
 * dbllfd LOOKUP_NON_PREMULT (usfd only for sourdf imbgfs tibt wfrf
 * originblly non-prfmultiplifd) tibt un-prfmultiplifs tif sourdf dolor
 * prior to tif lookup opfrbtion, tifn rf-prfmultiplifs tif rfsulting
 * dolor bfforf rfturning from tif frbgmfnt sibdfr.
 *
 * Notf tibt tiis sibdfr sourdf dodf indludfs somf "iolfs" mbrkfd by "%s".
 * Tiis bllows us to build difffrfnt sibdfr progrbms (f.g. onf for
 * GL_TEXTURE_2D tbrgfts, onf for GL_TEXTURE_RECTANGLE_ARB tbrgfts, bnd so on)
 * simply by filling in tifsf "iolfs" witi b dbll to sprintf().  Sff tif
 * OGLBufImgOps_CrfbtfLookupProgrbm() mftiod for morf dftbils.
 */
stbtid donst dibr *lookupSibdfrSourdf =
    // sourdf imbgf (bound to tfxturf unit 0)
    "uniform sbmplfr%s bbsfImbgf;"
    // lookup tbblf (bound to tfxturf unit 1)
    "uniform sbmplfr2D lookupTbblf;"
    // offsft subtrbdtfd from sourdf indfx prior to lookup stfp
    "uniform vfd4 offsft;"
    ""
    "void mbin(void)"
    "{"
    "    vfd4 srdColor = tfxturf%s(bbsfImbgf, gl_TfxCoord[0].st);"
         // (plbdfioldfr for un-prfmult dodf)
    "    %s"
         // subtrbdt offsft from originbl indfx
    "    vfd4 srdIndfx = srdColor - offsft;"
         // usf sourdf vbluf bs input to lookup tbblf (notf tibt
         // "v" tfxdoords brf ibrddodfd to iit tfxfl dfntfrs of
         // fbdi row/bbnd in tfxturf)
    "    vfd4 rfsult;"
    "    rfsult.r = tfxturf2D(lookupTbblf, vfd2(srdIndfx.r, 0.125)).r;"
    "    rfsult.g = tfxturf2D(lookupTbblf, vfd2(srdIndfx.g, 0.375)).r;"
    "    rfsult.b = tfxturf2D(lookupTbblf, vfd2(srdIndfx.b, 0.625)).r;"
         // (plbdfioldfr for blpib storf dodf)
    "    %s"
         // (plbdfioldfr for rf-prfmult dodf)
    "    %s"
         // modulbtf witi gl_Color in ordfr to bpply fxtrb blpib
    "    gl_FrbgColor = rfsult * gl_Color;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf LOOKUP_RECT          (1 << 0)
#dffinf LOOKUP_USE_SRC_ALPHA (1 << 1)
#dffinf LOOKUP_NON_PREMULT   (1 << 2)

/**
 * Tif ibndlfs to tif LookupOp frbgmfnt progrbm objfdts.  Tif indfx to
 * tif brrby siould bf b bitwisf-or'ing of tif LOOKUP_* flbgs dffinfd
 * bbovf.  Notf tibt most bpplidbtions will likfly nffd to initiblizf onf
 * or two of tifsf flfmfnts, so tif brrby is usublly spbrsfly populbtfd.
 */
stbtid GLibndlfARB lookupProgrbms[8];

/**
 * Tif ibndlf to tif lookup tbblf tfxturf objfdt usfd by tif sibdfr.
 */
stbtid GLuint lutTfxturfID = 0;

/**
 * Compilfs bnd links tif LookupOp sibdfr progrbm.  If suddfssful, tiis
 * fundtion rfturns b ibndlf to tif nfwly drfbtfd sibdfr progrbm; otifrwisf
 * rfturns 0.
 */
stbtid GLibndlfARB
OGLBufImgOps_CrfbtfLookupProgrbm(jint flbgs)
{
    GLibndlfARB lookupProgrbm;
    GLint lod;
    dibr *tbrgft = IS_SET(LOOKUP_RECT) ? "2DRfdt" : "2D";
    dibr *blpib;
    dibr *prfLookup = "";
    dibr *postLookup = "";
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrfbtfLookupProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(LOOKUP_USE_SRC_ALPHA)) {
        // wifn numComps is 1 or 3, tif blpib is not lookfd up in tif tbblf;
        // just kffp tif blpib from tif sourdf frbgmfnt
        blpib = "rfsult.b = srdColor.b;";
    } flsf {
        // wifn numComps is 4, tif blpib is lookfd up in tif tbblf, just
        // likf tif otifr dolor domponfnts from tif sourdf frbgmfnt
        blpib =
            "rfsult.b = tfxturf2D(lookupTbblf, vfd2(srdIndfx.b, 0.875)).r;";
    }
    if (IS_SET(LOOKUP_NON_PREMULT)) {
        prfLookup  = "srdColor.rgb /= srdColor.b;";
        postLookup = "rfsult.rgb *= rfsult.b;";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, lookupSibdfrSourdf,
            tbrgft, tbrgft, prfLookup, blpib, postLookup);

    lookupProgrbm = OGLContfxt_CrfbtfFrbgmfntProgrbm(finblSourdf);
    if (lookupProgrbm == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrfbtfLookupProgrbm: frror drfbting progrbm");
        rfturn 0;
    }

    // "usf" tif progrbm objfdt tfmporbrily so tibt wf dbn sft tif uniforms
    j2d_glUsfProgrbmObjfdtARB(lookupProgrbm);

    // sft tif "uniform" vblufs
    lod = j2d_glGftUniformLodbtionARB(lookupProgrbm, "bbsfImbgf");
    j2d_glUniform1iARB(lod, 0); // tfxturf unit 0
    lod = j2d_glGftUniformLodbtionARB(lookupProgrbm, "lookupTbblf");
    j2d_glUniform1iARB(lod, 1); // tfxturf unit 1

    // "unusf" tif progrbm objfdt; it will bf rf-bound lbtfr bs nffdfd
    j2d_glUsfProgrbmObjfdtARB(0);

    rfturn lookupProgrbm;
}

void
OGLBufImgOps_EnbblfLookupOp(OGLContfxt *ogld, jlong pSrdOps,
                            jboolfbn nonPrfmult, jboolfbn siortDbtb,
                            jint numBbnds, jint bbndLfngti, jint offsft,
                            void *tbblfVblufs)
{
    OGLSDOps *srdOps = (OGLSDOps *)jlong_to_ptr(pSrdOps);
    int bytfsPfrElfm = (siortDbtb ? 2 : 1);
    GLibndlfARB lookupProgrbm;
    GLflobt foff;
    GLint lod;
    void *bbnds[4];
    int i;
    jint flbgs = 0;

    J2dTrbdfLn4(J2D_TRACE_INFO,
                "OGLBufImgOps_EnbblfLookupOp: siort=%d num=%d lfn=%d off=%d",
                siortDbtb, numBbnds, bbndLfngti, offsft);

    for (i = 0; i < 4; i++) {
        bbnds[i] = NULL;
    }
    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(srdOps);
    RESET_PREVIOUS_OP();

    // dioosf tif bppropribtf sibdfr, dfpfnding on tif sourdf tfxturf tbrgft
    // bnd tif numbfr of bbnds involvfd
    if (srdOps->tfxturfTbrgft == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= LOOKUP_RECT;
    }
    if (numBbnds != 4) {
        flbgs |= LOOKUP_USE_SRC_ALPHA;
    }
    if (nonPrfmult) {
        flbgs |= LOOKUP_NON_PREMULT;
    }

    // lodbtf/initiblizf tif sibdfr progrbm for tif givfn flbgs
    if (lookupProgrbms[flbgs] == 0) {
        lookupProgrbms[flbgs] = OGLBufImgOps_CrfbtfLookupProgrbm(flbgs);
        if (lookupProgrbms[flbgs] == 0) {
            // siouldn't ibppfn, but just in dbsf...
            rfturn;
        }
    }
    lookupProgrbm = lookupProgrbms[flbgs];

    // fnbblf tif lookup sibdfr
    j2d_glUsfProgrbmObjfdtARB(lookupProgrbm);

    // updbtf tif "uniform" offsft vbluf
    lod = j2d_glGftUniformLodbtionARB(lookupProgrbm, "offsft");
    foff = offsft / 255.0f;
    j2d_glUniform4fARB(lod, foff, foff, foff, foff);

    // bind tif lookup tbblf to tfxturf unit 1 bnd fnbblf tfxturing
    j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
    if (lutTfxturfID == 0) {
        /*
         * Crfbtf tif lookup tbblf tfxturf witi 4 rows (onf bbnd pfr row)
         * bnd 256 dolumns (onf LUT bbnd flfmfnt pfr dolumn) bnd witi bn
         * intfrnbl formbt of 16-bit luminbndf vblufs, wiidi will bf
         * suffidifnt for fitifr bytf or siort LUT dbtb.  Notf tibt tif
         * tfxturf wrbp modf will bf sft to tif dffbult of GL_CLAMP_TO_EDGE,
         * wiidi mfbns tibt out-of-rbngf indfx vbluf will bf dlbmpfd
         * bppropribtfly.
         */
        lutTfxturfID =
            OGLContfxt_CrfbtfBlitTfxturf(GL_LUMINANCE16, GL_LUMINANCE,
                                         256, 4);
        if (lutTfxturfID == 0) {
            // siould nfvfr ibppfn, but just to bf sbff...
            rfturn;
        }
    }
    j2d_glBindTfxturf(GL_TEXTURE_2D, lutTfxturfID);
    j2d_glEnbblf(GL_TEXTURE_2D);

    // updbtf tif lookup tbblf witi tif usfr-providfd vblufs
    if (numBbnds == 1) {
        // rfplidbtf tif singlf bbnd for R/G/B; blpib bbnd is unusfd
        for (i = 0; i < 3; i++) {
            bbnds[i] = tbblfVblufs;
        }
        bbnds[3] = NULL;
    } flsf if (numBbnds == 3) {
        // usfr supplifd bbnd for fbdi of R/G/B; blpib bbnd is unusfd
        for (i = 0; i < 3; i++) {
            bbnds[i] = PtrAddBytfs(tbblfVblufs, i*bbndLfngti*bytfsPfrElfm);
        }
        bbnds[3] = NULL;
    } flsf if (numBbnds == 4) {
        // usfr supplifd bbnd for fbdi of R/G/B/A
        for (i = 0; i < 4; i++) {
            bbnds[i] = PtrAddBytfs(tbblfVblufs, i*bbndLfngti*bytfsPfrElfm);
        }
    }

    // uplobd tif bbnds onf row bt b timf into our lookup tbblf tfxturf
    for (i = 0; i < 4; i++) {
        if (bbnds[i] == NULL) {
            dontinuf;
        }
        j2d_glTfxSubImbgf2D(GL_TEXTURE_2D, 0,
                            0, i, bbndLfngti, 1,
                            GL_LUMINANCE,
                            siortDbtb ? GL_UNSIGNED_SHORT : GL_UNSIGNED_BYTE,
                            bbnds[i]);
    }

    // rfstorf tfxturf unit 0 (tif dffbult) bs tif bdtivf onf sindf
    // tif OGLBlitTfxturfToSurfbdf() mftiod is rfsponsiblf for binding tif
    // sourdf imbgf tfxturf, wiidi will ibppfn lbtfr
    j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);
}

void
OGLBufImgOps_DisbblfLookupOp(OGLContfxt *ogld)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbblfLookupOp");

    RETURN_IF_NULL(ogld);

    // disbblf tif LookupOp sibdfr
    j2d_glUsfProgrbmObjfdtARB(0);

    // disbblf tif lookup tbblf on tfxturf unit 1
    j2d_glAdtivfTfxturfARB(GL_TEXTURE1_ARB);
    j2d_glDisbblf(GL_TEXTURE_2D);
    j2d_glAdtivfTfxturfARB(GL_TEXTURE0_ARB);
}

#fndif /* !HEADLESS */
