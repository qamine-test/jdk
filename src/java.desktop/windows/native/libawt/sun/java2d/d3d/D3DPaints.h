/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff D3DPbints_i_Indludfd
#dffinf D3DPbints_i_Indludfd

#indludf "sun_jbvb2d_SunGrbpiids2D.i"

#indludf "D3DContfxt.i"
#indludf "D3DSurfbdfDbtb.i"

HRESULT D3DPbints_RfsftPbint(D3DContfxt *d3dd);
HRESULT D3DPbints_SftColor(D3DContfxt *d3dd, jint pixfl);

/************************* GrbdifntPbint support ****************************/

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf BASIC_GRAD_IS_CYCLIC (1 << 0)
#dffinf BASIC_GRAD_USE_MASK  (1 << 1)

HRESULT D3DPbints_SftGrbdifntPbint(D3DContfxt *d3dd,
                                jboolfbn usfMbsk, jboolfbn dydlid,
                                jdoublf p0, jdoublf p1, jdoublf p3,
                                jint pixfl1, jint pixfl2);

/************************** TfxturfPbint support ****************************/

HRESULT D3DPbints_SftTfxturfPbint(D3DContfxt *d3dd,
                               jboolfbn usfMbsk,
                               jlong pSrdOps, jboolfbn filtfr,
                               jdoublf xp0, jdoublf xp1, jdoublf xp3,
                               jdoublf yp0, jdoublf yp1, jdoublf yp3);

/****************** Sibrfd MultiplfGrbdifntPbint support ********************/

/**
 * Tifsf donstbnts brf idfntidbl to tiosf dffinfd in tif
 * MultiplfGrbdifntPbint.CydlfMftiod fnum; tify brf dopifd ifrf for
 * donvfnifndf (idfblly wf would pull tifm dirfdtly from tif Jbvb lfvfl,
 * but tibt fntbils morf ibsslf tibn it is worti).
 */
#dffinf CYCLE_NONE    0
#dffinf CYCLE_REFLECT 1
#dffinf CYCLE_REPEAT  2

/**
 * Tif following donstbnts brf flbgs tibt dbn bf bitwisf-or'fd togftifr
 * to dontrol iow tif MultiplfGrbdifntPbint sibdfr sourdf dodf is gfnfrbtfd:
 *
 *   MULTI_GRAD_CYCLE_METHOD
 *     Plbdfioldfr for tif CydlfMftiod fnum donstbnt.
 *
 *   MULTI_GRAD_LARGE
 *     If sft, usf tif (slowfr) sibdfr tibt supports b lbrgfr numbfr of
 *     grbdifnt dolors; otifrwisf, usf tif optimizfd dodfpbti.  Sff
 *     tif MAX_FRACTIONS_SMALL/LARGE donstbnts bflow for morf dftbils.
 *
 *   MULTI_GRAD_USE_MASK
 *     If sft, bpply tif blpib mbsk vbluf from tfxturf unit 1 to tif
 *     finbl dolor rfsult (only usfd in tif MbskFill dbsf).
 *
 *   MULTI_GRAD_LINEAR_RGB
 *     If sft, donvfrt tif linfbr RGB rfsult bbdk into tif sRGB dolor spbdf.
 */
#dffinf MULTI_GRAD_CYCLE_METHOD (3 << 0)
#dffinf MULTI_GRAD_LARGE        (1 << 2)
#dffinf MULTI_GRAD_USE_MASK     (1 << 3)
#dffinf MULTI_GRAD_LINEAR_RGB   (1 << 4)

/**
 * Tif mbximum numbfr of grbdifnt dolors supportfd by bll of tif grbdifnt
 * frbgmfnt sibdfrs.  Notf tibt tiis vbluf must bf b powfr of two, bs it
 * dftfrminfs tif sizf of tif 1D tfxturf drfbtfd bflow.  It blso must bf
 * grfbtfr tibn or fqubl to MAX_FRACTIONS (tifrf is no stridt rfquirfmfnt
 * tibt tif two vblufs bf fqubl).
 */
#dffinf MAX_MULTI_GRADIENT_COLORS 16

/********************** LinfbrGrbdifntPbint support *************************/

HRESULT D3DPbints_SftLinfbrGrbdifntPbint(D3DContfxt *d3dd, D3DSDOps *dstOps,
                                         jboolfbn usfMbsk, jboolfbn linfbr,
                                         jint dydlfMftiod, jint numStops,
                                         jflobt p0, jflobt p1, jflobt p3,
                                         void *frbdtions, void *pixfls);

/********************** RbdiblGrbdifntPbint support *************************/

HRESULT D3DPbints_SftRbdiblGrbdifntPbint(D3DContfxt *d3dd, D3DSDOps *dstOps,
                                         jboolfbn usfMbsk, jboolfbn linfbr,
                                         jint dydlfMftiod, jint numStops,
                                         jflobt m00, jflobt m01, jflobt m02,
                                         jflobt m10, jflobt m11, jflobt m12,
                                         jflobt fodusX,
                                         void *frbdtions, void *pixfls);

/************************ SunGrbpiids2D donstbnts ***************************/

#dffinf PAINT_CUSTOM       sun_jbvb2d_SunGrbpiids2D_PAINT_CUSTOM
#dffinf PAINT_TEXTURE      sun_jbvb2d_SunGrbpiids2D_PAINT_TEXTURE
#dffinf PAINT_RAD_GRADIENT sun_jbvb2d_SunGrbpiids2D_PAINT_RAD_GRADIENT
#dffinf PAINT_LIN_GRADIENT sun_jbvb2d_SunGrbpiids2D_PAINT_LIN_GRADIENT
#dffinf PAINT_GRADIENT     sun_jbvb2d_SunGrbpiids2D_PAINT_GRADIENT
#dffinf PAINT_ALPHACOLOR   sun_jbvb2d_SunGrbpiids2D_PAINT_ALPHACOLOR
#dffinf PAINT_OPAQUECOLOR  sun_jbvb2d_SunGrbpiids2D_PAINT_OPAQUECOLOR

#fndif /* D3DPbints_i_Indludfd */
