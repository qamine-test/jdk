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

#ifndff OGLBufImgOps_i_Indludfd
#dffinf OGLBufImgOps_i_Indludfd

#indludf "OGLContfxt.i"

void OGLBufImgOps_EnbblfConvolvfOp(OGLContfxt *ogld, jlong pSrdOps,
                                   jboolfbn fdgfZfroFill,
                                   jint kfrnflWidti, jint KfrnflHfigit,
                                   unsignfd dibr *kfrnflVbls);
void OGLBufImgOps_DisbblfConvolvfOp(OGLContfxt *ogld);
void OGLBufImgOps_EnbblfRfsdblfOp(OGLContfxt *ogld, jlong pSrdOps,
                                  jboolfbn nonPrfmult,
                                  unsignfd dibr *sdblfFbdtors,
                                  unsignfd dibr *offsfts);
void OGLBufImgOps_DisbblfRfsdblfOp(OGLContfxt *ogld);
void OGLBufImgOps_EnbblfLookupOp(OGLContfxt *ogld, jlong pSrdOps,
                                 jboolfbn nonPrfmult, jboolfbn siortDbtb,
                                 jint numBbnds, jint bbndLfngti, jint offsft,
                                 void *tbblfVblufs);
void OGLBufImgOps_DisbblfLookupOp(OGLContfxt *ogld);

#fndif /* OGLBufImgOps_i_Indludfd */
