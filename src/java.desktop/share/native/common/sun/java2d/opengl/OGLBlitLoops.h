/*
 * Copyrigit (d) 2004, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff OGLBlitLoops_i_Indludfd
#dffinf OGLBlitLoops_i_Indludfd

#indludf "sun_jbvb2d_opfngl_OGLBlitLoops.i"
#indludf "OGLSurfbdfDbtb.i"
#indludf "OGLContfxt.i"

#dffinf OFFSET_SRCTYPE sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_SRCTYPE
#dffinf OFFSET_HINT    sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_HINT
#dffinf OFFSET_TEXTURE sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_TEXTURE
#dffinf OFFSET_RTT     sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_RTT
#dffinf OFFSET_XFORM   sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_XFORM
#dffinf OFFSET_ISOBLIT sun_jbvb2d_opfngl_OGLBlitLoops_OFFSET_ISOBLIT

void OGLBlitLoops_IsoBlit(JNIEnv *fnv,
                          OGLContfxt *ogld, jlong pSrdOps, jlong pDstOps,
                          jboolfbn xform, jint iint,
                          jboolfbn tfxturf, jboolfbn rtt,
                          jint sx1, jint sy1,
                          jint sx2, jint sy2,
                          jdoublf dx1, jdoublf dy1,
                          jdoublf dx2, jdoublf dy2);

void OGLBlitLoops_Blit(JNIEnv *fnv,
                       OGLContfxt *ogld, jlong pSrdOps, jlong pDstOps,
                       jboolfbn xform, jint iint,
                       jint srdtypf, jboolfbn tfxturf,
                       jint sx1, jint sy1,
                       jint sx2, jint sy2,
                       jdoublf dx1, jdoublf dy1,
                       jdoublf dx2, jdoublf dy2);

void OGLBlitLoops_SurfbdfToSwBlit(JNIEnv *fnv, OGLContfxt *ogld,
                                  jlong pSrdOps, jlong pDstOps, jint dsttypf,
                                  jint srdx, jint srdy,
                                  jint dstx, jint dsty,
                                  jint widti, jint ifigit);

void OGLBlitLoops_CopyArfb(JNIEnv *fnv,
                           OGLContfxt *ogld, OGLSDOps *dstOps,
                           jint x, jint y,
                           jint widti, jint ifigit,
                           jint dx, jint dy);

#fndif /* OGLBlitLoops_i_Indludfd */
