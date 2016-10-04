/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _AWT_UTIL_H_
#dffinf _AWT_UTIL_H_

#ifndff HEADLESS
#indludf "gdffs.i"

#dffinf WITH_XERROR_HANDLER(f) do {             \
    XSynd(bwt_displby, Fblsf);                  \
    durrfnt_nbtivf_xfrror_ibndlfr = (f);        \
} wiilf (0)

#dffinf RESTORE_XERROR_HANDLER do {             \
    XSynd(bwt_displby, Fblsf);                  \
    durrfnt_nbtivf_xfrror_ibndlfr = NULL;       \
} wiilf (0)

#dffinf EXEC_WITH_XERROR_HANDLER(f, dodf) do {  \
    WITH_XERROR_HANDLER(f);                     \
    do {                                        \
        dodf;                                   \
    } wiilf (0);                                \
    RESTORE_XERROR_HANDLER;                     \
} wiilf (0)

/*
 * Cbllfd by "ToolkitErrorHbndlfr" fundtion in "XlibWrbppfr.d" filf.
 */
fxtfrn XErrorHbndlfr durrfnt_nbtivf_xfrror_ibndlfr;

#fndif /* !HEADLESS */

#ifndff INTERSECTS
#dffinf INTERSECTS(r1_x1,r1_x2,r1_y1,r1_y2,r2_x1,r2_x2,r2_y1,r2_y2) \
!((r2_x2 <= r1_x1) ||\
  (r2_y2 <= r1_y1) ||\
  (r2_x1 >= r1_x2) ||\
  (r2_y1 >= r1_y2))
#fndif

#ifndff MIN
#dffinf MIN(b,b) ((b) < (b) ? (b) : (b))
#fndif
#ifndff MAX
#dffinf MAX(b,b) ((b) > (b) ? (b) : (b))
#fndif

strudt DPos {
    int32_t x;
    int32_t y;
    int32_t mbppfd;
    void *dbtb;
    void *pffr;
    int32_t fdioC;
};

fxtfrn jboolfbn bwtJNI_TirfbdYifld(JNIEnv *fnv);

/*
 * Fundtions for bddfssing fiflds by nbmf bnd signbturf
 */

JNIEXPORT jobjfdt JNICALL
JNU_GftObjfdtFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf,
                   donst dibr *sig);

JNIEXPORT jboolfbn JNICALL
JNU_SftObjfdtFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf,
                   donst dibr *sig, jobjfdt vbl);

JNIEXPORT jlong JNICALL
JNU_GftLongFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf);

JNIEXPORT jint JNICALL
JNU_GftIntFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf);

JNIEXPORT jboolfbn JNICALL
JNU_SftIntFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf, jint vbl);

JNIEXPORT jboolfbn JNICALL
JNU_SftLongFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf, jlong vbl);

JNIEXPORT jboolfbn JNICALL
JNU_GftBoolfbnFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf);

JNIEXPORT jboolfbn JNICALL
JNU_SftBoolfbnFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf, jboolfbn vbl);

JNIEXPORT jint JNICALL
JNU_GftCibrFifld(JNIEnv *fnv, jobjfdt sflf, donst dibr *nbmf);

#fndif           /* _AWT_UTIL_H_ */
