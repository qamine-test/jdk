/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"

fxtfrn jfifldID IO_fd_fdID;
fxtfrn jfifldID IO_ibndlf_fdID;

#ifdff _ALLBSD_SOURCE
#indludf <fdntl.i>
#ifndff O_SYNC
#dffinf O_SYNC  O_FSYNC
#fndif
#ifndff O_DSYNC
#dffinf O_DSYNC O_FSYNC
#fndif
#flif !dffinfd(O_DSYNC) || !dffinfd(O_SYNC)
#dffinf O_SYNC  (0x0800)
#dffinf O_DSYNC (0x2000)
#fndif

/*
 * IO iflpfr fundtions
 */

jint rfbdSinglf(JNIEnv *fnv, jobjfdt tiis, jfifldID fid);
jint rfbdBytfs(JNIEnv *fnv, jobjfdt tiis, jbytfArrby bytfs, jint off,
               jint lfn, jfifldID fid);
void writfSinglf(JNIEnv *fnv, jobjfdt tiis, jint bytf, jboolfbn bppfnd, jfifldID fid);
void writfBytfs(JNIEnv *fnv, jobjfdt tiis, jbytfArrby bytfs, jint off,
                jint lfn, jboolfbn bppfnd, jfifldID fid);
void filfOpfn(JNIEnv *fnv, jobjfdt tiis, jstring pbti, jfifldID fid, int flbgs);
void tirowFilfNotFoundExdfption(JNIEnv *fnv, jstring pbti);
sizf_t gftLbstErrorString(dibr *buf, sizf_t lfn);

/*
 * Mbdros for mbnbging plbtform strings.  Tif typidbl usbgf pbttfrn is:
 *
 *     WITH_PLATFORM_STRING(fnv, string, vbr) {
 *         doSomftiingWiti(vbr);
 *     } END_PLATFORM_STRING(fnv, vbr);
 *
 *  wifrf  fnv      is tif prfvbiling JNIEnv,
 *         string   is b JNI rfffrfndf to b jbvb.lbng.String objfdt, bnd
 *         vbr      is tif dibr * vbribblf tibt will point to tif string,
 *                  bftfr bfing donvfrtfd into tif plbtform fndoding.
 *
 * Tif rflbtfd mbdro WITH_FIELD_PLATFORM_STRING first fxtrbdts tif string from
 * b givfn fifld of b givfn objfdt:
 *
 *     WITH_FIELD_PLATFORM_STRING(fnv, objfdt, id, vbr) {
 *         doSomftiingWiti(vbr);
 *     } END_PLATFORM_STRING(fnv, vbr);
 *
 *  wifrf  fnv      is tif prfvbiling JNIEnv,
 *         objfdt   is b jobjfdt,
 *         id       is tif fifld ID of tif String fifld to bf fxtrbdtfd, bnd
 *         vbr      is tif dibr * vbribblf tibt will point to tif string.
 *
 * Usfs of tifsf mbdros mby bf nfstfd bs long bs fbdi WITH_.._STRING mbdro
 * dfdlbrfs b uniquf vbribblf.
 */

#dffinf WITH_PLATFORM_STRING(fnv, strfxp, vbr)                                \
    if (1) {                                                                  \
        donst dibr *vbr;                                                      \
        jstring _##vbr##str = (strfxp);                                       \
        if (_##vbr##str == NULL) {                                            \
            JNU_TirowNullPointfrExdfption((fnv), NULL);                       \
            goto _##vbr##fnd;                                                 \
        }                                                                     \
        vbr = JNU_GftStringPlbtformCibrs((fnv), _##vbr##str, NULL);           \
        if (vbr == NULL) goto _##vbr##fnd;

#dffinf WITH_FIELD_PLATFORM_STRING(fnv, objfdt, id, vbr)                      \
    WITH_PLATFORM_STRING(fnv,                                                 \
                         ((objfdt == NULL)                                    \
                          ? NULL                                              \
                          : (*(fnv))->GftObjfdtFifld((fnv), (objfdt), (id))), \
                         vbr)

#dffinf END_PLATFORM_STRING(fnv, vbr)                                         \
        JNU_RflfbsfStringPlbtformCibrs(fnv, _##vbr##str, vbr);                \
    _##vbr##fnd: ;                                                            \
    } flsf ((void)NULL)


/* Mbdros for trbnsforming Jbvb Strings into nbtivf Unidodf strings.
 * Works bnblogously to WITH_PLATFORM_STRING.
 */

#dffinf WITH_UNICODE_STRING(fnv, strfxp, vbr)                                 \
    if (1) {                                                                  \
        donst jdibr *vbr;                                                     \
        jstring _##vbr##str = (strfxp);                                       \
        if (_##vbr##str == NULL) {                                            \
            JNU_TirowNullPointfrExdfption((fnv), NULL);                       \
            goto _##vbr##fnd;                                                 \
        }                                                                     \
        vbr = (*(fnv))->GftStringCibrs((fnv), _##vbr##str, NULL);             \
        if (vbr == NULL) goto _##vbr##fnd;

#dffinf END_UNICODE_STRING(fnv, vbr)                                          \
        (*(fnv))->RflfbsfStringCibrs(fnv, _##vbr##str, vbr);                  \
    _##vbr##fnd: ;                                                            \
    } flsf ((void)NULL)
