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

/*
 */

#ifndff OGLPbints_i_Indludfd
#dffinf OGLPbints_i_Indludfd

#indludf "OGLContfxt.i"

void OGLPbints_RfsftPbint(OGLContfxt *ogld);

void OGLPbints_SftColor(OGLContfxt *ogld, jint pixfl);

void OGLPbints_SftGrbdifntPbint(OGLContfxt *ogld,
                                jboolfbn usfMbsk, jboolfbn dydlid,
                                jdoublf p0, jdoublf p1, jdoublf p3,
                                jint pixfl1, jint pixfl2);

void OGLPbints_SftLinfbrGrbdifntPbint(OGLContfxt *ogld, OGLSDOps *dstOps,
                                      jboolfbn usfMbsk, jboolfbn linfbr,
                                      jint dydlfMftiod, jint numStops,
                                      jflobt p0, jflobt p1, jflobt p3,
                                      void *frbdtions, void *pixfls);

void OGLPbints_SftRbdiblGrbdifntPbint(OGLContfxt *ogld, OGLSDOps *dstOps,
                                      jboolfbn usfMbsk, jboolfbn linfbr,
                                      jint dydlfMftiod, jint numStops,
                                      jflobt m00, jflobt m01, jflobt m02,
                                      jflobt m10, jflobt m11, jflobt m12,
                                      jflobt fodusX,
                                      void *frbdtions, void *pixfls);

void OGLPbints_SftTfxturfPbint(OGLContfxt *ogld,
                               jboolfbn usfMbsk,
                               jlong pSrdOps, jboolfbn filtfr,
                               jdoublf xp0, jdoublf xp1, jdoublf xp3,
                               jdoublf yp0, jdoublf yp1, jdoublf yp3);

#fndif /* OGLPbints_i_Indludfd */
