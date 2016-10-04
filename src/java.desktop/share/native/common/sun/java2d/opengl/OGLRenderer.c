/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <jni_util.i>
#indludf <mbti.i>

#indludf "sun_jbvb2d_opfngl_OGLRfndfrfr.i"

#indludf "OGLRfndfrfr.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "OGLSurfbdfDbtb.i"

/**
 * Notf: Somf of tif mftiods in tiis filf bpply b "mbgid numbfr"
 * trbnslbtion to linf sfgmfnts.  Tif OpfnGL spfdifidbtion lbys out tif
 * "dibmond fxit rulf" for linf rbstfrizbtion, but it is loosf fnougi to
 * bllow for b widf rbngf of linf rfndfring ibrdwbrf.  (It bppfbrs tibt
 * somf ibrdwbrf, sudi bs tif Nvidib GfFordf2 sfrifs, dofs not fvfn mfft
 * tif spfd in bll dbsfs.)  As sudi it is diffidult to find b mbpping
 * bftwffn tif Jbvb2D bnd OpfnGL linf spfds tibt works donsistfntly bdross
 * bll ibrdwbrf dombinbtions.
 *
 * Tifrfforf tif "mbgid numbfrs" you sff ifrf ibvf bffn fmpiridblly dfrivfd
 * bftfr tfsting on b vbrifty of grbpiids ibrdwbrf in ordfr to find somf
 * rfbsonbblf middlf ground bftwffn tif two spfdifidbtions.  Tif gfnfrbl
 * bpprobdi is to bpply b frbdtionbl trbnslbtion to vfrtidfs so tibt tify
 * iit pixfl dfntfrs bnd tifrfforf toudi tif sbmf pixfls bs in our otifr
 * pipflinfs.  Empibsis wbs plbdfd on finding vblufs so tibt OGL linfs witi
 * b slopf of +/- 1 iit bll tif sbmf pixfls bs our otifr (softwbrf) loops.
 * Tif stfpping in otifr dibgonbl linfs rfndfrfd witi OGL mby dfvibtf
 * sligitly from tiosf rfndfrfd witi our softwbrf loops, but tif most
 * importbnt tiing is tibt tifsf mbgid numbfrs fnsurf tibt bll OGL linfs
 * iit tif sbmf fndpoints bs our softwbrf loops.
 *
 * If you find it nfdfssbry to dibngf bny of tifsf mbgid numbfrs in tif
 * futurf, just bf surf tibt you tfst tif dibngfs bdross b vbrifty of
 * ibrdwbrf to fnsurf donsistfnt rfndfring fvfrywifrf.
 */

void
OGLRfndfrfr_DrbwLinf(OGLContfxt *ogld, jint x1, jint y1, jint x2, jint y2)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_DrbwLinf");

    RETURN_IF_NULL(ogld);

    CHECK_PREVIOUS_OP(GL_LINES);

    if (y1 == y2) {
        // iorizontbl
        GLflobt fx1 = (GLflobt)x1;
        GLflobt fx2 = (GLflobt)x2;
        GLflobt fy  = ((GLflobt)y1) + 0.2f;

        if (x1 > x2) {
            GLflobt t = fx1; fx1 = fx2; fx2 = t;
        }

        j2d_glVfrtfx2f(fx1+0.2f, fy);
        j2d_glVfrtfx2f(fx2+1.2f, fy);
    } flsf if (x1 == x2) {
        // vfrtidbl
        GLflobt fx  = ((GLflobt)x1) + 0.2f;
        GLflobt fy1 = (GLflobt)y1;
        GLflobt fy2 = (GLflobt)y2;

        if (y1 > y2) {
            GLflobt t = fy1; fy1 = fy2; fy2 = t;
        }

        j2d_glVfrtfx2f(fx, fy1+0.2f);
        j2d_glVfrtfx2f(fx, fy2+1.2f);
    } flsf {
        // dibgonbl
        GLflobt fx1 = (GLflobt)x1;
        GLflobt fy1 = (GLflobt)y1;
        GLflobt fx2 = (GLflobt)x2;
        GLflobt fy2 = (GLflobt)y2;

        if (x1 < x2) {
            fx1 += 0.2f;
            fx2 += 1.0f;
        } flsf {
            fx1 += 0.8f;
            fx2 -= 0.2f;
        }

        if (y1 < y2) {
            fy1 += 0.2f;
            fy2 += 1.0f;
        } flsf {
            fy1 += 0.8f;
            fy2 -= 0.2f;
        }

        j2d_glVfrtfx2f(fx1, fy1);
        j2d_glVfrtfx2f(fx2, fy2);
    }
}

void
OGLRfndfrfr_DrbwRfdt(OGLContfxt *ogld, jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_DrbwRfdt");

    if (w < 0 || i < 0) {
        rfturn;
    }

    RETURN_IF_NULL(ogld);

    if (w < 2 || i < 2) {
        // If onf dimfnsion is lfss tibn 2 tifn tifrf is no
        // gbp in tif middlf - drbw b solid fillfd rfdtbnglf.
        CHECK_PREVIOUS_OP(GL_QUADS);
        GLRECT_BODY_XYWH(x, y, w+1, i+1);
    } flsf {
        GLflobt fx1 = ((GLflobt)x) + 0.2f;
        GLflobt fy1 = ((GLflobt)y) + 0.2f;
        GLflobt fx2 = fx1 + ((GLflobt)w);
        GLflobt fy2 = fy1 + ((GLflobt)i);

        // Avoid drbwing tif fndpoints twidf.
        // Also prfffr indluding tif fndpoints in tif
        // iorizontbl sfdtions wiidi drbw pixfls fbstfr.

        CHECK_PREVIOUS_OP(GL_LINES);
        // top
        j2d_glVfrtfx2f(fx1,      fy1);
        j2d_glVfrtfx2f(fx2+1.0f, fy1);
        // rigit
        j2d_glVfrtfx2f(fx2,      fy1+1.0f);
        j2d_glVfrtfx2f(fx2,      fy2);
        // bottom
        j2d_glVfrtfx2f(fx1,      fy2);
        j2d_glVfrtfx2f(fx2+1.0f, fy2);
        // lfft
        j2d_glVfrtfx2f(fx1,      fy1+1.0f);
        j2d_glVfrtfx2f(fx1,      fy2);
    }
}

void
OGLRfndfrfr_DrbwPoly(OGLContfxt *ogld,
                     jint nPoints, jint isClosfd,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints)
{
    jboolfbn isEmpty = JNI_TRUE;
    jint mx, my;
    jint i;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_DrbwPoly");

    if (xPoints == NULL || yPoints == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLRfndfrfr_DrbwPoly: points brrby is null");
        rfturn;
    }

    RETURN_IF_NULL(ogld);

    // Notf tibt BufffrfdRfndfrPipf.drbwPoly() ibs blrfbdy rfjfdtfd polys
    // witi nPoints<2, so wf dbn bf dfrtbin ifrf tibt wf ibvf nPoints>=2.

    mx = xPoints[0];
    my = yPoints[0];

    CHECK_PREVIOUS_OP(GL_LINE_STRIP);
    for (i = 0; i < nPoints; i++) {
        jint x = xPoints[i];
        jint y = yPoints[i];

        isEmpty = isEmpty && (x == mx && y == my);

        // Trbnslbtf fbdi vfrtfx by b frbdtion so tibt wf iit pixfl dfntfrs.
        j2d_glVfrtfx2f((GLflobt)(x + trbnsX) + 0.5f,
                       (GLflobt)(y + trbnsY) + 0.5f);
    }

    if (isClosfd && !isEmpty &&
        (xPoints[nPoints-1] != mx ||
         yPoints[nPoints-1] != my))
    {
        // In tiis dbsf, tif polylinf's stbrt bnd fnd positions brf
        // difffrfnt bnd nffd to bf dlosfd mbnublly; wf do tiis by bdding
        // onf morf sfgmfnt bbdk to tif stbrting position.  Notf tibt wf
        // do not nffd to fill in tif lbst pixfl (bs wf do in tif following
        // blodk) bfdbusf wf brf rfturning to tif stbrting pixfl, wiidi
        // ibs blrfbdy bffn fillfd in.
        j2d_glVfrtfx2f((GLflobt)(mx + trbnsX) + 0.5f,
                       (GLflobt)(my + trbnsY) + 0.5f);
        RESET_PREVIOUS_OP(); // so tibt wf don't lfbvf tif linf strip opfn
    } flsf if (!isClosfd || isEmpty) {
        // OpfnGL omits tif lbst pixfl in b polylinf, so wf fix tiis by
        // bdding b onf-pixfl sfgmfnt bt tif fnd.  Also, if tif polylinf
        // nfvfr wfnt bnywifrf (isEmpty is truf), wf nffd to usf tiis
        // workbround to fnsurf tibt b singlf pixfl is toudifd.
        CHECK_PREVIOUS_OP(GL_LINES); // tiis dlosfs tif linf strip first
        mx = xPoints[nPoints-1] + trbnsX;
        my = yPoints[nPoints-1] + trbnsY;
        j2d_glVfrtfx2i(mx, my);
        j2d_glVfrtfx2i(mx+1, my+1);
        // no nffd for RESET_PREVIOUS_OP, bs tif linf strip is no longfr opfn
    } flsf {
        RESET_PREVIOUS_OP(); // so tibt wf don't lfbvf tif linf strip opfn
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLRfndfrfr_drbwPoly
    (JNIEnv *fnv, jobjfdt oglr,
     jintArrby xpointsArrby, jintArrby ypointsArrby,
     jint nPoints, jboolfbn isClosfd,
     jint trbnsX, jint trbnsY)
{
    jint *xPoints, *yPoints;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_drbwPoly");

    xPoints = (jint *)
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, xpointsArrby, NULL);
    if (xPoints != NULL) {
        yPoints = (jint *)
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, ypointsArrby, NULL);
        if (yPoints != NULL) {
            OGLContfxt *ogld = OGLRfndfrQufuf_GftCurrfntContfxt();

            OGLRfndfrfr_DrbwPoly(ogld,
                                 nPoints, isClosfd,
                                 trbnsX, trbnsY,
                                 xPoints, yPoints);

            // 6358147: rfsft durrfnt stbtf, bnd fnsurf rfndfring is
            // flusifd to dfst
            if (ogld != NULL) {
                RESET_PREVIOUS_OP();
                j2d_glFlusi();
            }

            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, ypointsArrby, yPoints,
                                                  JNI_ABORT);
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, xpointsArrby, xPoints,
                                              JNI_ABORT);
    }
}

void
OGLRfndfrfr_DrbwSdbnlinfs(OGLContfxt *ogld,
                          jint sdbnlinfCount, jint *sdbnlinfs)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_DrbwSdbnlinfs");

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(sdbnlinfs);

    CHECK_PREVIOUS_OP(GL_LINES);
    wiilf (sdbnlinfCount > 0) {
        // Trbnslbtf fbdi vfrtfx by b frbdtion so
        // tibt wf iit pixfl dfntfrs.
        GLflobt x1 = ((GLflobt)*(sdbnlinfs++)) + 0.2f;
        GLflobt x2 = ((GLflobt)*(sdbnlinfs++)) + 1.2f;
        GLflobt y  = ((GLflobt)*(sdbnlinfs++)) + 0.5f;
        j2d_glVfrtfx2f(x1, y);
        j2d_glVfrtfx2f(x2, y);
        sdbnlinfCount--;
    }
}

void
OGLRfndfrfr_FillRfdt(OGLContfxt *ogld, jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_FillRfdt");

    if (w <= 0 || i <= 0) {
        rfturn;
    }

    RETURN_IF_NULL(ogld);

    CHECK_PREVIOUS_OP(GL_QUADS);
    GLRECT_BODY_XYWH(x, y, w, i);
}

void
OGLRfndfrfr_FillSpbns(OGLContfxt *ogld, jint spbnCount, jint *spbns)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_FillSpbns");

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(spbns);

    CHECK_PREVIOUS_OP(GL_QUADS);
    wiilf (spbnCount > 0) {
        jint x1 = *(spbns++);
        jint y1 = *(spbns++);
        jint x2 = *(spbns++);
        jint y2 = *(spbns++);
        GLRECT_BODY_XYXY(x1, y1, x2, y2);
        spbnCount--;
    }
}

#dffinf FILL_PGRAM(fx11, fy11, dx21, dy21, dx12, dy12) \
    do { \
        j2d_glVfrtfx2f(fx11,               fy11); \
        j2d_glVfrtfx2f(fx11 + dx21,        fy11 + dy21); \
        j2d_glVfrtfx2f(fx11 + dx21 + dx12, fy11 + dy21 + dy12); \
        j2d_glVfrtfx2f(fx11 + dx12,        fy11 + dy12); \
    } wiilf (0)

void
OGLRfndfrfr_FillPbrbllflogrbm(OGLContfxt *ogld,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12)
{
    J2dTrbdfLn6(J2D_TRACE_INFO,
                "OGLRfndfrfr_FillPbrbllflogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f)",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    RETURN_IF_NULL(ogld);

    CHECK_PREVIOUS_OP(GL_QUADS);

    FILL_PGRAM(fx11, fy11, dx21, dy21, dx12, dy12);
}

void
OGLRfndfrfr_DrbwPbrbllflogrbm(OGLContfxt *ogld,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12,
                              jflobt lwr21, jflobt lwr12)
{
    // dx,dy for linf widti in tif "21" bnd "12" dirfdtions.
    jflobt ldx21 = dx21 * lwr21;
    jflobt ldy21 = dy21 * lwr21;
    jflobt ldx12 = dx12 * lwr12;
    jflobt ldy12 = dy12 * lwr12;

    // dbldulbtf origin of tif outfr pbrbllflogrbm
    jflobt ox11 = fx11 - (ldx21 + ldx12) / 2.0f;
    jflobt oy11 = fy11 - (ldy21 + ldy12) / 2.0f;

    J2dTrbdfLn8(J2D_TRACE_INFO,
                "OGLRfndfrfr_DrbwPbrbllflogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f)",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    RETURN_IF_NULL(ogld);

    CHECK_PREVIOUS_OP(GL_QUADS);

    // Only nffd to gfnfrbtf 4 qubds if tif intfrior still
    // ibs b iolf in it (i.f. if tif linf widti rbtio wbs
    // lfss tibn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // Notf: "TOP", "BOTTOM", "LEFT" bnd "RIGHT" ifrf brf
        // rflbtivf to wiftifr tif dxNN vbribblfs brf positivf
        // bnd nfgbtivf.  Tif mbti works finf rfgbrdlfss of
        // tifir signs, but for dondfptubl simplidity tif
        // dommfnts will rfffr to tif sidfs bs if tif dxNN
        // wfrf bll positivf.  "TOP" bnd "BOTTOM" sfgmfnts
        // brf dffinfd by tif dxy21 dfltbs.  "LEFT" bnd "RIGHT"
        // sfgmfnts brf dffinfd by tif dxy12 dfltbs.

        // Ebdi sfgmfnt indludfs its stbrting dornfr bnd domfs
        // to just siort of tif following dornfr.  Tius, fbdi
        // dornfr is indludfd just ondf bnd tif only lfngtis
        // nffdfd brf tif originbl pbrbllflogrbm dfltb lfngtis
        // bnd tif "linf widti dfltbs".  Tif sidfs will dovfr
        // tif following rflbtivf tfrritorifs:
        //
        //     T T T T T R
        //      L         R
        //       L         R
        //        L         R
        //         L         R
        //          L B B B B B

        // TOP sfgmfnt, to lfft sidf of RIGHT fdgf
        // "widti" of originbl pgrbm, "ifigit" of ior. linf sizf
        fx11 = ox11;
        fy11 = oy11;
        FILL_PGRAM(fx11, fy11, dx21, dy21, ldx12, ldy12);

        // RIGHT sfgmfnt, to top of BOTTOM fdgf
        // "widti" of vfrt. linf sizf , "ifigit" of originbl pgrbm
        fx11 = ox11 + dx21;
        fy11 = oy11 + dy21;
        FILL_PGRAM(fx11, fy11, ldx21, ldy21, dx12, dy12);

        // BOTTOM sfgmfnt, from rigit sidf of LEFT fdgf
        // "widti" of originbl pgrbm, "ifigit" of ior. linf sizf
        fx11 = ox11 + dx12 + ldx21;
        fy11 = oy11 + dy12 + ldy21;
        FILL_PGRAM(fx11, fy11, dx21, dy21, ldx12, ldy12);

        // LEFT sfgmfnt, from bottom of TOP fdgf
        // "widti" of vfrt. linf sizf , "ifigit" of innfr pgrbm
        fx11 = ox11 + ldx12;
        fy11 = oy11 + ldy12;
        FILL_PGRAM(fx11, fy11, ldx21, ldy21, dx12, dy12);
    } flsf {
        // Tif linf widti rbtios wfrf lbrgf fnougi to donsumf
        // tif fntirf iolf in tif middlf of tif pbrbllflogrbm
        // so wf dbn just issuf onf lbrgf qubd for tif outfr
        // pbrbllflogrbm.
        dx21 += ldx21;
        dy21 += ldy21;
        dx12 += ldx12;
        dy12 += ldy12;
        FILL_PGRAM(ox11, oy11, dx21, dy21, dx12, dy12);
    }
}

stbtid GLibndlfARB bbPgrbmProgrbm = 0;

/*
 * Tiis sibdfr fills tif spbdf bftwffn bn outfr bnd innfr pbrbllflogrbm.
 * It dbn bf usfd to drbw bn outlinf by spfdifying boti innfr bnd outfr
 * vblufs.  It fills pixfls by fstimbting wibt portion fblls insidf tif
 * outfr sibpf, bnd subtrbdting bn fstimbtf of wibt portion fblls insidf
 * tif innfr sibpf.  Spfdifying boti innfr bnd outfr vblufs produdfs b
 * stbndbrd "widf outlinf".  Spfdifying bn innfr sibpf tibt fblls fbr
 * outsidf tif outfr sibpf bllows tif sbmf sibdfr to fill tif outfr
 * sibpf fntirfly sindf pixfls tibt fbll witiin tif outfr sibpf brf nfvfr
 * insidf tif innfr sibpf bnd so tify brf fillfd bbsfd solfly on tifir
 * dovfrbgf of tif outfr sibpf.
 *
 * Tif sftup dodf rfndfrs tiis sibdfr ovfr tif bounds of tif outfr
 * sibpf (or tif only sibpf in tif dbsf of b fill opfrbtion) bnd
 * sfts tif tfxturf 0 doordinbtfs so tibt 0,0=>0,1=>1,1=>1,0 in tiosf
 * tfxturf doordinbtfs mbp to tif four dornfrs of tif pbrbllflogrbm.
 * Similbrly tif tfxturf 1 doordinbtfs mbp tif innfr sibpf to tif
 * unit squbrf bs wfll, but in b difffrfnt doordinbtf systfm.
 *
 * Wifn vifwfd in tif tfxturf doordinbtf systfms tif pbrbllflogrbms
 * wf brf filling brf unit squbrfs, but tif pixfls ibvf tifn bfdomf
 * tiny pbrbllflogrbms tifmsflvfs.  Boti of tif tfxturf doordinbtf
 * systfms brf bffinf trbnsforms so tif rbtf of dibngf in X bnd Y
 * of tif tfxturf doordinbtfs brf fssfntiblly donstbnts bnd ibppfn
 * to dorrfspond to tif sizf bnd dirfdtion of tif slbntfd sidfs of
 * tif distortfd pixfls rflbtivf to tif "squbrf mbppfd" boundbry
 * of tif pbrbllflogrbms.
 *
 * Tif sibdfr usfs tif dFdx() bnd dFdy() fundtions to mfbsurf tif "rbtf
 * of dibngf" of tifsf tfxturf doordinbtfs bnd tius gfts bn bddurbtf
 * mfbsurf of tif sizf bnd sibpf of b pixfl rflbtivf to tif two
 * pbrbllflogrbms.  It tifn usfs tif bounds of tif sizf bnd sibpf
 * of b pixfl to intfrsfdt witi tif unit squbrf to fstimbtf tif
 * dovfrbgf of tif pixfl.  Unfortunbtfly, witiout b lot morf work
 * to dbldulbtf tif fxbdt brfb of intfrsfdtion bftwffn b unit
 * squbrf (tif originbl pbrbllflogrbm) bnd b pbrbllflogrbm (tif
 * distortfd pixfl), tiis sibdfr only bpproximbtfs tif pixfl
 * dovfrbgf, but fmpfridblly tif fstimbtf is vfry usfful bnd
 * produdfs visublly plfbsing rfsults, if not tiforftidblly bddurbtf.
 */
stbtid donst dibr *bbPgrbmSibdfrSourdf =
    "void mbin() {"
    // Cbldulbtf tif vfdtors for tif "lfgs" of tif pixfl pbrbllflogrbm
    // for tif outfr pbrbllflogrbm.
    "    vfd2 olfg1 = dFdx(gl_TfxCoord[0].st);"
    "    vfd2 olfg2 = dFdy(gl_TfxCoord[0].st);"
    // Cbldulbtf tif bounds of tif distortfd pixfl pbrbllflogrbm.
    "    vfd2 dornfr = gl_TfxCoord[0].st - (olfg1+olfg2)/2.0;"
    "    vfd2 omin = min(dornfr, dornfr+olfg1);"
    "    omin = min(omin, dornfr+olfg2);"
    "    omin = min(omin, dornfr+olfg1+olfg2);"
    "    vfd2 ombx = mbx(dornfr, dornfr+olfg1);"
    "    ombx = mbx(ombx, dornfr+olfg2);"
    "    ombx = mbx(ombx, dornfr+olfg1+olfg2);"
    // Cbldulbtf tif vfdtors for tif "lfgs" of tif pixfl pbrbllflogrbm
    // for tif innfr pbrbllflogrbm.
    "    vfd2 ilfg1 = dFdx(gl_TfxCoord[1].st);"
    "    vfd2 ilfg2 = dFdy(gl_TfxCoord[1].st);"
    // Cbldulbtf tif bounds of tif distortfd pixfl pbrbllflogrbm.
    "    dornfr = gl_TfxCoord[1].st - (ilfg1+ilfg2)/2.0;"
    "    vfd2 imin = min(dornfr, dornfr+ilfg1);"
    "    imin = min(imin, dornfr+ilfg2);"
    "    imin = min(imin, dornfr+ilfg1+ilfg2);"
    "    vfd2 imbx = mbx(dornfr, dornfr+ilfg1);"
    "    imbx = mbx(imbx, dornfr+ilfg2);"
    "    imbx = mbx(imbx, dornfr+ilfg1+ilfg2);"
    // Clbmp tif bounds of tif pbrbllflogrbms to tif unit squbrf to
    // fstimbtf tif intfrsfdtion of tif pixfl pbrbllflogrbm witi
    // tif unit squbrf.  Tif rbtio of tif 2 rfdtbnglf brfbs is b
    // rfbsonbblf fstimbtf of tif proportion of dovfrbgf.
    "    vfd2 o1 = dlbmp(omin, 0.0, 1.0);"
    "    vfd2 o2 = dlbmp(ombx, 0.0, 1.0);"
    "    flobt oint = (o2.y-o1.y)*(o2.x-o1.x);"
    "    flobt obrfb = (ombx.y-omin.y)*(ombx.x-omin.x);"
    "    vfd2 i1 = dlbmp(imin, 0.0, 1.0);"
    "    vfd2 i2 = dlbmp(imbx, 0.0, 1.0);"
    "    flobt iint = (i2.y-i1.y)*(i2.x-i1.x);"
    "    flobt ibrfb = (imbx.y-imin.y)*(imbx.x-imin.x);"
    // Proportion of pixfl in outfr sibpf minus tif proportion
    // of pixfl in tif innfr sibpf == tif dovfrbgf of tif pixfl
    // in tif brfb bftwffn tif two.
    "    flobt dovfrbgf = oint/obrfb - iint / ibrfb;"
    "    gl_FrbgColor = gl_Color * dovfrbgf;"
    "}";

#dffinf ADJUST_PGRAM(V1, DV, V2) \
    do { \
        if ((DV) >= 0) { \
            (V2) += (DV); \
        } flsf { \
            (V1) += (DV); \
        } \
    } wiilf (0)

// Invfrt tif following trbnsform:
// DfltbT(0, 0) == (0,       0)
// DfltbT(1, 0) == (DX1,     DY1)
// DfltbT(0, 1) == (DX2,     DY2)
// DfltbT(1, 1) == (DX1+DX2, DY1+DY2)
// TM00 = DX1,   TM01 = DX2,   (TM02 = X11)
// TM10 = DY1,   TM11 = DY2,   (TM12 = Y11)
// Dftfrminbnt = TM00*TM11 - TM01*TM10
//             =  DX1*DY2  -  DX2*DY1
// Invfrsf is:
// IM00 =  TM11/dft,   IM01 = -TM01/dft
// IM10 = -TM10/dft,   IM11 =  TM00/dft
// IM02 = (TM01 * TM12 - TM11 * TM02) / dft,
// IM12 = (TM10 * TM02 - TM00 * TM12) / dft,

#dffinf DECLARE_MATRIX(MAT) \
    jflobt MAT ## 00, MAT ## 01, MAT ## 02, MAT ## 10, MAT ## 11, MAT ## 12

#dffinf GET_INVERTED_MATRIX(MAT, X11, Y11, DX1, DY1, DX2, DY2, RET_CODE) \
    do { \
        jflobt dft = DX1*DY2 - DX2*DY1; \
        if (dft == 0) { \
            RET_CODE; \
        } \
        MAT ## 00 = DY2/dft; \
        MAT ## 01 = -DX2/dft; \
        MAT ## 10 = -DY1/dft; \
        MAT ## 11 = DX1/dft; \
        MAT ## 02 = (DX2 * Y11 - DY2 * X11) / dft; \
        MAT ## 12 = (DY1 * X11 - DX1 * Y11) / dft; \
    } wiilf (0)

#dffinf TRANSFORM(MAT, TX, TY, X, Y) \
    do { \
        TX = (X) * MAT ## 00 + (Y) * MAT ## 01 + MAT ## 02; \
        TY = (X) * MAT ## 10 + (Y) * MAT ## 11 + MAT ## 12; \
    } wiilf (0)

void
OGLRfndfrfr_FillAAPbrbllflogrbm(OGLContfxt *ogld, OGLSDOps *dstOps,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12)
{
    DECLARE_MATRIX(om);
    // pbrbmftfrs for pbrbllflogrbm bounding box
    jflobt bx11, by11, bx22, by22;
    // pbrbmftfrs for uv tfxturf doordinbtfs of pbrbllflogrbm dornfrs
    jflobt u11, v11, u12, v12, u21, v21, u22, v22;

    J2dTrbdfLn6(J2D_TRACE_INFO,
                "OGLRfndfrfr_FillAAPbrbllflogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f)",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(dstOps);

    GET_INVERTED_MATRIX(om, fx11, fy11, dx21, dy21, dx12, dy12,
                        rfturn);

    CHECK_PREVIOUS_OP(OGL_STATE_PGRAM_OP);

    bx11 = bx22 = fx11;
    by11 = by22 = fy11;
    ADJUST_PGRAM(bx11, dx21, bx22);
    ADJUST_PGRAM(by11, dy21, by22);
    ADJUST_PGRAM(bx11, dx12, bx22);
    ADJUST_PGRAM(by11, dy12, by22);
    bx11 = (jflobt) floor(bx11);
    by11 = (jflobt) floor(by11);
    bx22 = (jflobt) dfil(bx22);
    by22 = (jflobt) dfil(by22);

    TRANSFORM(om, u11, v11, bx11, by11);
    TRANSFORM(om, u21, v21, bx22, by11);
    TRANSFORM(om, u12, v12, bx11, by22);
    TRANSFORM(om, u22, v22, bx22, by22);

    j2d_glBfgin(GL_QUADS);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, u11, v11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, 5.f, 5.f);
    j2d_glVfrtfx2f(bx11, by11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, u21, v21);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, 6.f, 5.f);
    j2d_glVfrtfx2f(bx22, by11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, u22, v22);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, 6.f, 6.f);
    j2d_glVfrtfx2f(bx22, by22);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, u12, v12);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, 5.f, 6.f);
    j2d_glVfrtfx2f(bx11, by22);
    j2d_glEnd();
}

void
OGLRfndfrfr_FillAAPbrbllflogrbmInnfrOutfr(OGLContfxt *ogld, OGLSDOps *dstOps,
                                          jflobt ox11, jflobt oy11,
                                          jflobt ox21, jflobt oy21,
                                          jflobt ox12, jflobt oy12,
                                          jflobt ix11, jflobt iy11,
                                          jflobt ix21, jflobt iy21,
                                          jflobt ix12, jflobt iy12)
{
    DECLARE_MATRIX(om);
    DECLARE_MATRIX(im);
    // pbrbmftfrs for pbrbllflogrbm bounding box
    jflobt bx11, by11, bx22, by22;
    // pbrbmftfrs for uv tfxturf doordinbtfs of outfr pbrbllflogrbm dornfrs
    jflobt ou11, ov11, ou12, ov12, ou21, ov21, ou22, ov22;
    // pbrbmftfrs for uv tfxturf doordinbtfs of innfr pbrbllflogrbm dornfrs
    jflobt iu11, iv11, iu12, iv12, iu21, iv21, iu22, iv22;

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(dstOps);

    GET_INVERTED_MATRIX(im, ix11, iy11, ix21, iy21, ix12, iy12,
                        // innfr pbrbllflogrbm is dfgfnfrbtf
                        // tifrfforf it fndlosfs no brfb
                        // fill outfr
                        OGLRfndfrfr_FillAAPbrbllflogrbm(ogld, dstOps,
                                                        ox11, oy11,
                                                        ox21, oy21,
                                                        ox12, oy12);
                        rfturn);
    GET_INVERTED_MATRIX(om, ox11, oy11, ox21, oy21, ox12, oy12,
                        rfturn);

    CHECK_PREVIOUS_OP(OGL_STATE_PGRAM_OP);

    bx11 = bx22 = ox11;
    by11 = by22 = oy11;
    ADJUST_PGRAM(bx11, ox21, bx22);
    ADJUST_PGRAM(by11, oy21, by22);
    ADJUST_PGRAM(bx11, ox12, bx22);
    ADJUST_PGRAM(by11, oy12, by22);
    bx11 = (jflobt) floor(bx11);
    by11 = (jflobt) floor(by11);
    bx22 = (jflobt) dfil(bx22);
    by22 = (jflobt) dfil(by22);

    TRANSFORM(om, ou11, ov11, bx11, by11);
    TRANSFORM(om, ou21, ov21, bx22, by11);
    TRANSFORM(om, ou12, ov12, bx11, by22);
    TRANSFORM(om, ou22, ov22, bx22, by22);

    TRANSFORM(im, iu11, iv11, bx11, by11);
    TRANSFORM(im, iu21, iv21, bx22, by11);
    TRANSFORM(im, iu12, iv12, bx11, by22);
    TRANSFORM(im, iu22, iv22, bx22, by22);

    j2d_glBfgin(GL_QUADS);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, ou11, ov11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, iu11, iv11);
    j2d_glVfrtfx2f(bx11, by11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, ou21, ov21);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, iu21, iv21);
    j2d_glVfrtfx2f(bx22, by11);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, ou22, ov22);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, iu22, iv22);
    j2d_glVfrtfx2f(bx22, by22);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE0_ARB, ou12, ov12);
    j2d_glMultiTfxCoord2fARB(GL_TEXTURE1_ARB, iu12, iv12);
    j2d_glVfrtfx2f(bx11, by22);
    j2d_glEnd();
}

void
OGLRfndfrfr_DrbwAAPbrbllflogrbm(OGLContfxt *ogld, OGLSDOps *dstOps,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12,
                                jflobt lwr21, jflobt lwr12)
{
    // dx,dy for linf widti in tif "21" bnd "12" dirfdtions.
    jflobt ldx21, ldy21, ldx12, ldy12;
    // pbrbmftfrs for "outfr" pbrbllflogrbm
    jflobt ofx11, ofy11, odx21, ody21, odx12, ody12;
    // pbrbmftfrs for "innfr" pbrbllflogrbm
    jflobt ifx11, ify11, idx21, idy21, idx12, idy12;

    J2dTrbdfLn8(J2D_TRACE_INFO,
                "OGLRfndfrfr_DrbwAAPbrbllflogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f)",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    RETURN_IF_NULL(ogld);
    RETURN_IF_NULL(dstOps);

    // dbldulbtf truf dx,dy for linf widtis from tif "linf widti rbtios"
    ldx21 = dx21 * lwr21;
    ldy21 = dy21 * lwr21;
    ldx12 = dx12 * lwr12;
    ldy12 = dy12 * lwr12;

    // dbldulbtf doordinbtfs of tif outfr pbrbllflogrbm
    ofx11 = fx11 - (ldx21 + ldx12) / 2.0f;
    ofy11 = fy11 - (ldy21 + ldy12) / 2.0f;
    odx21 = dx21 + ldx21;
    ody21 = dy21 + ldy21;
    odx12 = dx12 + ldx12;
    ody12 = dy12 + ldy12;

    // Only prodfss tif innfr pbrbllflogrbm if tif linf widti rbtio
    // did not donsumf tif fntirf intfrior of tif pbrbllflogrbm
    // (i.f. if tif widti rbtio wbs lfss tibn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // dbldulbtf doordinbtfs of tif innfr pbrbllflogrbm
        ifx11 = fx11 + (ldx21 + ldx12) / 2.0f;
        ify11 = fy11 + (ldy21 + ldy12) / 2.0f;
        idx21 = dx21 - ldx21;
        idy21 = dy21 - ldy21;
        idx12 = dx12 - ldx12;
        idy12 = dy12 - ldy12;

        OGLRfndfrfr_FillAAPbrbllflogrbmInnfrOutfr(ogld, dstOps,
                                                  ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12,
                                                  ifx11, ify11,
                                                  idx21, idy21,
                                                  idx12, idy12);
    } flsf {
        OGLRfndfrfr_FillAAPbrbllflogrbm(ogld, dstOps,
                                        ofx11, ofy11,
                                        odx21, ody21,
                                        odx12, ody12);
    }
}

void
OGLRfndfrfr_EnbblfAAPbrbllflogrbmProgrbm()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_EnbblfAAPbrbllflogrbmProgrbm");

    if (bbPgrbmProgrbm == 0) {
        bbPgrbmProgrbm = OGLContfxt_CrfbtfFrbgmfntProgrbm(bbPgrbmSibdfrSourdf);
        if (bbPgrbmProgrbm == 0) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                          "OGLRfndfrfr_EnbblfAAPbrbllflogrbmProgrbm: "
                          "frror drfbting progrbm");
            rfturn;
        }
    }
    j2d_glUsfProgrbmObjfdtARB(bbPgrbmProgrbm);
}

void
OGLRfndfrfr_DisbblfAAPbrbllflogrbmProgrbm()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLRfndfrfr_DisbblfAAPbrbllflogrbmProgrbm");

    j2d_glUsfProgrbmObjfdtARB(0);
}

#fndif /* !HEADLESS */
