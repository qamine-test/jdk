/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/**
 * This include file contbins informbtion on how to use b SurfbceDbtb
 * object from nbtive code.
 */

#ifndef _Included_SurfbceDbtb
#define _Included_SurfbceDbtb

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 * This structure is used to represent b rectbngulbr bounding box
 * throughout vbrious functions in the nbtive SurfbceDbtb API.
 *
 * All coordinbtes (x1 <= x < x2, y1 <= y < y2) bre considered to
 * be inside these bounds.
 */
typedef struct {
    jint x1;
    jint y1;
    jint x2;
    jint y2;
} SurfbceDbtbBounds;

#define SD_RASINFO_PRIVATE_SIZE         64

/*
 * The SurfbceDbtbRbsInfo structure is used to pbss in bnd return vbrious
 * pieces of informbtion bbout the destinbtion drbwbble.  In pbrticulbr:
 *
 *      SurfbceDbtbBounds bounds;
 * [Needed for SD_LOCK_READ or SD_LOCK_WRITE]
 * The 2 dimensionbl bounds of the rbster brrby thbt is needed.  Vblid
 * memory locbtions bre required bt:
 *      *(pixeltype *) (((chbr *)rbsBbse) + y * scbnStride + x * pixelStride)
 * for ebch x, y pbir such thbt (bounds.x1 <= x < bounds.x2) bnd
 * (bounds.y1 <= y < bounds.y2).
 *
 *      void *rbsBbse;
 * [Requires SD_LOCK_READ or SD_LOCK_WRITE]
 * A pointer to the device spbce origin (0, 0) of the indicbted rbster
 * dbtb.  This pointer mby point to b locbtion thbt is outside of the
 * bllocbted memory for the requested bounds bnd it mby even point
 * outside of bccessible memory.  Only the locbtions thbt fbll within
 * the coordinbtes indicbted by the requested bounds bre gubrbnteed
 * to be bccessible.
 *
 *      jint pixelBitOffset;
 * [Requires SD_LOCK_READ or SD_LOCK_WRITE]
 * The number of bits offset from the beginning of the first byte
 * of b scbnline to the first bit of the first pixel on thbt scbnline.
 * The bit offset must be less thbn 8 bnd it must be the sbme for ebch
 * scbnline.  This field is only needed by imbge types which pbck
 * multiple pixels into b byte, such bs ByteBinbry1Bit et bl.  For
 * imbge types which use whole bytes (or shorts or ints) to store
 * their pixels, this field will blwbys be 0.
 *
 *      jint pixelStride;
 * [Requires SD_LOCK_READ or SD_LOCK_WRITE]
 * The pixel stride is the distbnce in bytes from the dbtb for one pixel
 * to the dbtb for the pixel bt the next x coordinbte (x, y) => (x+1, y).
 * For dbtb types thbt pbck multiple pixels into b byte, such bs
 * ByteBinbry1Bit et bl, this field will be 0 bnd the loops which
 * render to bnd from such dbtb need to cblculbte their own offset
 * from the beginning of the scbnline using the bbsolute x coordinbte
 * combined with the pixelBitOffset field.
 * Bugfix 6220829 - this field used to be unsigned int, but some
 * primitives used negbtive pixel offsets bnd the corresponding
 * unsigned stride vblues cbused the resulting pixel offset to
 * to blwbys be b positive 32-bit qubntity - cbusing problems on
 * 64-bit brchitectures.
 *
 *      jint scbnStride;
 * [Requires SD_LOCK_READ or SD_LOCK_WRITE]
 * The scbn stride is the distbnce in bytes from the dbtb for one pixel
 * to the dbtb for the pixel bt the next y coordinbte (x, y) => (x, y+1).
 * Bugfix 6220829 - this field used to be unsigned int, but some
 * primitives used negbtive pixel offsets bnd the corresponding
 * unsigned stride vblues cbused the resulting pixel offset to
 * to blwbys be b positive 32-bit qubntity - cbusing problems on
 * 64-bit brchitectures.
 *
 *      unsigned int lutSize;
 * [Requires SD_LOCK_LUT]
 * The number of entries in the color lookup tbble.  The dbtb beyond the
 * end of the mbp will be undefined.
 *
 *      jint *lutBbse;
 * [Requires SD_LOCK_LUT]
 * A pointer to the beginning of the color lookup tbble for the colormbp.
 * The color lookup tbble is formbtted bs bn brrby of jint vblues ebch
 * representing the 32-bit ARGB color for the pixel representing by the
 * corresponding index.  The tbble is gubrbnteed to contbin bt lebst 256
 * vblid memory locbtions even if the size of the mbp is smbller thbn 256.
 *
 *      unsigned chbr *invColorTbble;
 * [Requires SD_LOCK_INVCOLOR]
 * A pointer to the beginning of the inverse color lookup tbble for the
 * colormbp.  The inverse color lookup tbble is formbtted bs b 32x32x32
 * brrby of bytes indexed by RxGxB where ebch component is reduced to 5
 * bits of precision before indexing.
 *
 *      chbr *redErrTbble;
 *      chbr *grnErrTbble;
 *      chbr *bluErrTbble;
 * [Requires SD_LOCK_INVCOLOR]
 * Pointers to the beginning of the ordered dither color error tbbles
 * for the colormbp.  The error tbbles bre formbtted bs bn 8x8 brrby
 * of bytes indexed by coordinbtes using the formulb [y & 7][x & 7].
 *
 *      int *invGrbyTbble;
 * [Requires SD_LOCK_INVGRAY]
 * A pointer to the beginning of the inverse grby lookup tbble for the
 * colormbp.  The inverse color lookup tbble is formbtted bs bn brrby
 * of 256 integers indexed by b byte grby level bnd storing bn index
 * into the colormbp of the closest mbtching grby pixel.
 *
 *      union priv {};
 * A buffer of privbte dbtb for the SurfbceDbtb implementbtion.
 * This field is b union of b dbtb block of the desired defbult
 * size (SD_RASINFO_PRIVATE_SIZE) bnd b (void *) pointer thbt
 * ensures proper "strictest" blignment on bll plbtforms.
 */
typedef struct {
    SurfbceDbtbBounds   bounds;                 /* bounds of rbster brrby */
    void                *rbsBbse;               /* Pointer to (0, 0) pixel */
    jint                pixelBitOffset;         /* bit offset to (0, *) pixel */
    jint                pixelStride;            /* bytes to next X pixel */
    jint                scbnStride;             /* bytes to next Y pixel */
    unsigned int        lutSize;                /* # colors in colormbp */
    jint                *lutBbse;               /* Pointer to colormbp[0] */
    unsigned chbr       *invColorTbble;         /* Inverse color tbble */
    chbr                *redErrTbble;           /* Red ordered dither tbble */
    chbr                *grnErrTbble;           /* Green ordered dither tbble */
    chbr                *bluErrTbble;           /* Blue ordered dither tbble */
    int                 *invGrbyTbble;          /* Inverse grby tbble */
    union {
        void            *blign;                 /* ensures strict blignment */
        chbr            dbtb[SD_RASINFO_PRIVATE_SIZE];
    } priv;
} SurfbceDbtbRbsInfo;

typedef struct _SurfbceDbtbOps SurfbceDbtbOps;

/*
 * This function is used to lock b pbrticulbr region of b pbrticulbr
 * destinbtion.  Once this method is cblled, no chbnges of bny of the
 * dbtb returned by bny of the other SurfbceDbtb vectored functions
 * mby chbnge until b corresponding cbll to Relebse is mbde.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * The rbsInfo pbrbmeter should be b pointer to b SurfbceDbtbRbsInfo
 * structure in which the bounds hbve been initiblized to the mbximum
 * bounds of the rbster dbtb thbt will need to be bccessed lbter.
 *
 * The lockflbgs pbrbmeter should indicbte which informbtion will be
 * needed by the cbller.  The vbrious flbgs which mby be OR'd together
 * mby consist of bny of the following:
 *      SD_LOCK_READ            The cbller needs to rebd pixels from the dest
 *      SD_LOCK_WRITE           The cbller needs to write pixels to the dest
 *      SD_LOCK_RD_WR           A combinbtion of (SD_LOCK_READ | SD_LOCK_WRITE)
 *      SD_LOCK_LUT             The cbller needs the colormbp (Lut)
 *      SD_LOCK_INVCOLOR        The cbller needs the inverse color tbble
 *      SD_LOCK_INVGRAY         The cbller needs the inverse grby tbble
 *      SD_LOCK_FASTEST         The cbller only wbnts direct pixel bccess
 * Note thbt the SD_LOCK_LUT, SD_LOCK_INVCOLOR, bnd SD_LOCK_INVGRAY flbgs
 * bre only vblid for destinbtions with IndexColorModels.
 * Also note thbt SD_LOCK_FASTEST will only succeed if the bccess to the
 * pixels will occur just bs fbst regbrdless of the size of the bounds.
 * This flbg is used by the Text rendering routines to determine if it
 * mbtters whether or not they hbve cblculbted b tight bounding box for
 * the pixels they will be touching.
 *
 * Return vblue:
 *
 * If this function succeeds, it will return SD_SUCCESS (0).
 *
 * If this function is unbble to honor the SD_LOCK_FASTEST flbg,
 * it will return SD_SLOWLOCK.  The bounds pbrbmeter of the
 * SurfbceDbtbRbsInfo object should be intersected with b tighter
 * bounding rectbngle before cblling the GetRbsInfo function so
 * bs to minimize the bmount pixel copying or conversion.  Note
 * thbt the Lock function mby hbve blrebdy intersected the
 * bounds with b tighter rectbngle bs it tried to honor the
 * SD_SLOWLOCK flbg bnd so the cbller should only use intersection
 * operbtions to further restrict the bounds.
 *
 * If this function fbils for bny rebson thbt is not recoverbble,
 * it will throw bn bppropribte Jbvb exception bnd return SD_FAILED.
 *
 * Operbtion:
 *
 * This function will intersect the bounds specified in the rbsInfo
 * pbrbmeter with the bvbilbble rbster dbtb in the destinbtion drbwbble
 * bnd modify the contents of the bounds field to represent the mbximum
 * bvbilbble rbster dbtb.
 *
 * If the bvbilbble rbster dbtb in the destinbtion drbwbble consists of
 * b non-rectbngulbr region of pixels, this method mby throw bn InvblidPipe
 * exception (optionblly the object mby decide to provide b copy of the
 * destinbtion pixel dbtb with undefined dbtb in the inbccessible portions).
 *
 * Further processing by the cbller mby discover thbt b smbller region of
 * dbtb is bctublly needed bnd the cbll to GetRbsDbtb cbn be mbde with b
 * still smbller bounds.
 *
 * Note to cbllers:
 *      This function mby use JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 * Note to implementers:
 *      The cbller mby blso continue to use JNI methods bfter this method
 *      is cblled so it is importbnt thbt implementbtions of SurfbceDbtb
 *      not return from this function with bny outstbnding JNI Criticbl
 *      locks thbt hbve not been relebsed.
 */
typedef jint LockFunc(JNIEnv *env,
                      SurfbceDbtbOps *ops,
                      SurfbceDbtbRbsInfo *rbsInfo,
                      jint lockflbgs);

/*
 * This function returns informbtion bbout the rbster dbtb for the drbwbble.
 * The function will fill in or modify the contents of the SurfbceDbtbRbsInfo
 * structure thbt is pbssed in with vbrious pieces of informbtion depending
 * on whbt wbs requested in the lockflbgs pbrbmeter thbt wbs hbnded into
 * the LockFunc.  For more informbtion on which pieces of informbtion bre
 * returned bbsed upon the lock flbgs see the documentbtion for the
 * RbsInfo structure bbove.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * The pRbsInfo pbrbmeter should be b pointer to the sbme structure of type
 * SurfbceDbtbRbsInfo.  The bounds member of thbt structure should be
 * initiblized to the bounding box of the rbster dbtb thbt is bctublly
 * needed for rebding or writing before cblling this function.  These
 * bounds must be b subset of the rbster bounds thbt were given to the
 * LockFunc or the results will be undefined.
 *
 * If the surfbce wbs locked with the flbg SD_LOCK_FASTEST then this
 * function mby reevblubte the bounds in the RbsInfo structure bnd
 * return b subset of whbt wbs requested.  Cbllers thbt use thbt flbg
 * should be prepbred to reevblubte their clipping bfter GetRbsInfo
 * returns.  If the SD_LOCK_FASTEST flbg wbs not specified, then this
 * function will return b buffer contbining bll of the pixels in the
 * requested bounds without reevblubting them.
 *
 * Any informbtion thbt wbs requested in the lockflbgs of the LockFunc
 * will be returned bnd NULL pointers will be returned for bll other
 * informbtion.
 *
 * Note to cbllers:
 *      This function mby use JNI Criticbl methods so it is importbnt
 *      thbt the cbller not cbll bny other JNI methods bfter this function
 *      returns until the Relebse function is cblled.
 */
typedef void GetRbsInfoFunc(JNIEnv *env,
                            SurfbceDbtbOps *ops,
                            SurfbceDbtbRbsInfo *pRbsInfo);

/*
 * This function relebses bll of the Criticbl dbtb for the specified
 * drbwbble.
 *
 * This function vector is bllowed to be NULL if b given SurfbceDbtb
 * implementbtion does not require the use of JNI Criticbl brrby locks.
 * Cbllers should use the "SurfbceDbtb_InvokeRelebse(env, ops)" mbcro
 * to hbndle the conditionbl invocbtion of this function.
 *
 * In pbrticulbr, this function will relebse bny outstbnding JNI Criticbl
 * locks thbt the SurfbceDbtb implementbtion mby hbve used so thbt it
 * will be sbfe for the cbller to stbrt using brbitrbry JNI cblls or
 * return from its cblling JNI function.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * The pRbsInfo pbrbmeter should be b pointer to the sbme structure of
 * type SurfbceDbtbRbsInfo thbt wbs pbssed to the GetRbsInfo function.
 * The bounds should be unchbnged since thbt cbll.
 *
 * Note to cbllers:
 *      This function will relebse bny outstbnding JNI Criticbl locks so
 *      it will once bgbin be sbfe to use brbitrbry JNI cblls or return
 *      to the enclosing JNI nbtive context.
 *
 * Note to implementers:
 *      This function mby not use bny JNI methods other thbn to relebse
 *      outstbnding JNI Criticbl brrby locks since there mby be other
 *      nested SurfbcDbtb objects holding locks with their own outstbnding
 *      JNI Criticbl locks.  This restriction includes the use of the
 *      JNI monitor cblls so thbt bll MonitorExit invocbtions must be
 *      done in the Unlock function.
 */
typedef void RelebseFunc(JNIEnv *env,
                         SurfbceDbtbOps *ops,
                         SurfbceDbtbRbsInfo *pRbsInfo);

/*
 * This function unlocks the specified drbwbble.
 *
 * This function vector is bllowed to be NULL if b given SurfbceDbtb
 * implementbtion does not require bny unlocking of the destinbtion.
 * Cbllers should use the "SurfbceDbtb_InvokeUnlock(env, ops)" mbcro
 * to hbndle the conditionbl invocbtion of this function.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * The pRbsInfo pbrbmeter should be b pointer to the sbme structure of
 * type SurfbceDbtbRbsInfo thbt wbs pbssed to the GetRbsInfo function.
 * The bounds should be unchbnged since thbt cbll.
 *
 * Note to cbllers:
 *      This function mby use JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 * Note to implementers:
 *      This function mby be used to relebse bny JNI monitors used to
 *      prevent the destinbtion from being modified.  It mby blso be
 *      used to perform operbtions which mby require blocking (such bs
 *      executing X11 operbtions which mby need to flush dbtb).
 */
typedef void UnlockFunc(JNIEnv *env,
                        SurfbceDbtbOps *ops,
                        SurfbceDbtbRbsInfo *pRbsInfo);

/*
 * This function sets up the specified drbwbble.  Some surfbces mby
 * need to perform certbin operbtions during Setup thbt cbnnot be
 * done bfter lbter operbtions such bs Lock.  For exbmple, on
 * win9x systems, when bny surfbce is locked we cbnnot mbke b cbll to
 * the messbge-hbndling threbd.
 *
 * This function vector is bllowed to be NULL if b given SurfbceDbtb
 * implementbtion does not require bny setup.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * Note to cbllers:
 *      This function mby use JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 */
typedef void SetupFunc(JNIEnv *env,
                       SurfbceDbtbOps *ops);

/*
 * This function disposes the specified SurfbceDbtbOps structure
 * bnd bssocibted nbtive resources.
 * The implementbtion is SurfbceDbtb-type specific.
 */
typedef void DisposeFunc(JNIEnv *env,
                         SurfbceDbtbOps *ops);

/*
 * Constbnts used for return vblues.  Constbnts less thbn 0 bre
 * unrecoverbble fbilures bnd indicbte thbt b Jbvb exception hbs
 * blrebdy been thrown.  Constbnts grebter thbn 0 bre conditionbl
 * successes which wbrn the cbller thbt vbrious optionbl febtures
 * were not bvbilbble so thbt workbrounds cbn be used.
 */
#define SD_FAILURE              -1
#define SD_SUCCESS              0
#define SD_SLOWLOCK             1

/*
 * Constbnts for the flbgs used in the Lock function.
 */
#define SD_LOCK_READ            (1 << 0)
#define SD_LOCK_WRITE           (1 << 1)
#define SD_LOCK_RD_WR           (SD_LOCK_READ | SD_LOCK_WRITE)
#define SD_LOCK_LUT             (1 << 2)
#define SD_LOCK_INVCOLOR        (1 << 3)
#define SD_LOCK_INVGRAY         (1 << 4)
#define SD_LOCK_FASTEST         (1 << 5)
#define SD_LOCK_PARTIAL         (1 << 6)
#define SD_LOCK_PARTIAL_WRITE   (SD_LOCK_WRITE | SD_LOCK_PARTIAL)
#define SD_LOCK_NEED_PIXELS     (SD_LOCK_READ | SD_LOCK_PARTIAL)

/*
 * This structure provides the function vectors for mbnipulbting
 * bnd retrieving informbtion bbout the destinbtion drbwbble.
 * There bre blso vbribbles for the surfbce dbtb object used by
 * nbtive code to trbck the stbte of the surfbce.
 * The sdObject is b pointer to the Jbvb SurfbceDbtb object;
 * this is set in SurfbceDbtb_InitOps() bnd used by bny object
 * using the ops structure to refer to elements in the Jbvb object
 * (such bs fields thbt we need to set from nbtive code).
 */
struct _SurfbceDbtbOps {
    LockFunc            *Lock;
    GetRbsInfoFunc      *GetRbsInfo;
    RelebseFunc         *Relebse;
    UnlockFunc          *Unlock;
    SetupFunc           *Setup;
    DisposeFunc         *Dispose;
    jobject             sdObject;
};

#define _ClrReduce(c)   (((unsigned chbr) c) >> 3)

/*
 * This mbcro performs b lookup in bn inverse color tbble given 3 8-bit
 * RGB primbries.  It butombtes the process of reducing the primbries
 * to 5-bits of precision bnd using them to index into the specified
 * inverse color lookup tbble.
 */
#define SurfbceDbtb_InvColorMbp(invcolortbl, r, g, b) \
    (invcolortbl)[(_ClrReduce(r)<<10) + (_ClrReduce(g)<<5) + _ClrReduce(b)]

/*
 * This mbcro invokes the SurfbceDbtb Relebse function only if the
 * function vector is not NULL.
 */
#define SurfbceDbtb_InvokeRelebse(env, ops, pRI)        \
    do {                                                \
        if ((ops)->Relebse != NULL) {                   \
            (ops)->Relebse(env, ops, pRI);              \
        }                                               \
    } while(0)

/*
 * This mbcro invokes the SurfbceDbtb Unlock function only if the
 * function vector is not NULL.
 */
#define SurfbceDbtb_InvokeUnlock(env, ops, pRI)         \
    do {                                                \
        if ((ops)->Unlock != NULL) {                    \
            (ops)->Unlock(env, ops, pRI);               \
        }                                               \
    } while(0)

/*
 * This mbcro invokes both the SurfbceDbtb Relebse bnd Unlock functions
 * only if the function vectors bre not NULL.  It cbn be used in cbses
 * where only one surfbce hbs been bccessed bnd where no other JNI
 * Criticbl locks (which would need to be relebsed bfter Relebse bnd
 * before Unlock) bre held by the cblling function.
 */
#define SurfbceDbtb_InvokeRelebseUnlock(env, ops, pRI)  \
    do {                                                \
        if ((ops)->Relebse != NULL) {                   \
            (ops)->Relebse(env, ops, pRI);              \
        }                                               \
        if ((ops)->Unlock != NULL) {                    \
            (ops)->Unlock(env, ops, pRI);               \
        }                                               \
    } while(0)

/*
 * This mbcro invokes both the SurfbceDbtb Relebse bnd Unlock functions
 * on two nested drbwbbles only if the function vectors bre not NULL.
 * It cbn be used in cbses where two surfbces hbve been bccessed bnd
 * where no other JNI Criticbl locks (which would need to be relebsed
 * bfter Relebse bnd before Unlock) bre held by the cblling function.  The
 * two ops vectors should be specified in the sbme order thbt they were
 * locked.  Both surfbces will be relebsed bnd then both unlocked.
 */
#define SurfbceDbtb_InvokeRelebseUnlock2(env, ops1, pRI1, ops2, pRI2)   \
    do {                                                        \
        if ((ops2)->Relebse != NULL) {                          \
            (ops2)->Relebse(env, ops2, pRI2);                   \
        }                                                       \
        if ((ops1)->Relebse != NULL) {                          \
            (ops1)->Relebse(env, ops1, pRI1);                   \
        }                                                       \
        if ((ops2)->Unlock != NULL) {                           \
            (ops2)->Unlock(env, ops2, pRI2);                    \
        }                                                       \
        if ((ops1)->Unlock != NULL) {                           \
            (ops1)->Unlock(env, ops1, pRI1);                    \
        }                                                       \
    } while(0)

#define SurfbceDbtb_InvokeDispose(env, ops)                     \
    do {                                                        \
        if ((ops)->Dispose != NULL) {                           \
            (ops)->Dispose(env, ops);                           \
        }                                                       \
    } while(0)

#define SurfbceDbtb_InvokeSetup(env, ops)                       \
    do {                                                        \
        if ((ops)->Setup != NULL) {                             \
            (ops)->Setup(env, ops);                             \
        }                                                       \
    } while(0)

/*
 * This function returns b pointer to b nbtive SurfbceDbtbOps
 * structure for bccessing the indicbted SurfbceDbtb Jbvb object.
 *
 * Note to cbllers:
 *      This function uses JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 *      The cbller mby continue to use JNI methods bfter this method
 *      is cblled since this function will not lebve bny outstbnding
 *      JNI Criticbl locks unrelebsed.
 */
JNIEXPORT SurfbceDbtbOps * JNICALL
SurfbceDbtb_GetOps(JNIEnv *env, jobject sDbtb);

/*
 * Does the sbme bs the bbove, but doesn't cbll Setup function
 * even if it's set.
 */
JNIEXPORT SurfbceDbtbOps * JNICALL
SurfbceDbtb_GetOpsNoSetup(JNIEnv *env, jobject sDbtb);

/*
 * This function stores b pointer to b nbtive SurfbceDbtbOps
 * structure into the indicbted Jbvb SurfbceDbtb object.
 *
 * Note to cbllers:
 *      This function uses JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 *      The cbller mby continue to use JNI methods bfter this method
 *      is cblled since this function will not lebve bny outstbnding
 *      JNI Criticbl locks unrelebsed.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_SetOps(JNIEnv *env, jobject sDbtb, SurfbceDbtbOps *ops);

/*
 * This function throws bn InvblidPipeException which will cbuse the
 * cblling SunGrbphics2D object to revblidbte its pipelines bnd cbll
 * bgbin.  This utility method should be cblled from the SurfbceDbtb
 * nbtive Lock routine when some bttribute of the surfbce hbs chbnged
 * thbt requires pipeline revblidbtion, including:
 *
 *      The bit depth or pixel formbt of the surfbce.
 *      The surfbce (window) hbs been disposed.
 *      The device clip of the surfbce hbs been chbnged (resize, visibility, etc.)
 *
 * Note to cbllers:
 *      This function uses JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 *      The cbller mby continue to use JNI methods bfter this method
 *      is cblled since this function will not lebve bny outstbnding
 *      JNI Criticbl locks unrelebsed.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_ThrowInvblidPipeException(JNIEnv *env, const chbr *msg);

/*
 * This function intersects two bounds objects which exist in the sbme
 * coordinbte spbce.  The contents of the first pbrbmeter (dst) bre
 * modified to contbin the intersection of the two bounds while the
 * contents of the second pbrbmeter (src) bre untouched.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBounds(SurfbceDbtbBounds *dst, SurfbceDbtbBounds *src);

/*
 * This function intersects b bounds object with b rectbngle specified
 * in lox, loy, hix, hiy formbt in the sbme coordinbte spbce.  The
 * contents of the first pbrbmeter (bounds) bre modified to contbin
 * the intersection of the two rectbngulbr regions.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYXY(SurfbceDbtbBounds *bounds,
                                jint lox, jint loy, jint hix, jint hiy);

/*
 * This function intersects b bounds object with b rectbngle specified
 * in XYWH formbt in the sbme coordinbte spbce.  The contents of the
 * first pbrbmeter (bounds) bre modified to contbin the intersection
 * of the two rectbngulbr regions.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYWH(SurfbceDbtbBounds *bounds,
                                jint x, jint y, jint w, jint h);

/*
 * This function intersects two bounds objects which exist in different
 * coordinbte spbces.  The coordinbte spbces of the two objects bre
 * relbted such thbt b given coordinbte in the spbce of the A bounds
 * is relbted to the bnblogous coordinbte in the spbce of the B bounds
 * by the formulb: (AX + BXminusAX, AY + BYminusAY) == (BX, BY).
 * The contents of both bounds objects bre modified to represent their
 * mutubl intersection.
 */
JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBlitBounds(SurfbceDbtbBounds *Abounds,
                                SurfbceDbtbBounds *Bbounds,
                                jint BXminusAX, jint BYminusAY);


/*
 * This function crebtes bnd initiblizes the ops structure.  The function
 * is cblled by "subclbsses" of SurfbceDbtb (e.g., BufImgSurfbceDbtb)
 * which pbss in the size of the structure to bllocbte (subclbsses generblly
 * need bdditionbl fields in the ops structure pbrticulbr to their usbge
 * of the structure).  The structure is bllocbted bnd initiblized
 * bnd is stored in the SurfbceDbtb jbvb object for lbter retrievbl.
 * Subclbsses of SurfbceDbtb should cbll this function instebd of bllocbting
 * the memory directly.
 */
SurfbceDbtbOps *SurfbceDbtb_InitOps(JNIEnv *env, jobject sDbtb, int opsSize);

/*
 * This function invokes the ops-specific disposbl function.
 * It is b pbrt of the finblizers-free disposbl mechbnism.
 * (see Disposer bnd DefbultDisposerRecord clbsses for more informbtion)
 * It blso destroys the ops structure crebted in SurfbceDbtb_InitOps.
 */
void SurfbceDbtb_DisposeOps(JNIEnv *env, jlong ops);

#ifdef __cplusplus
};
#endif

#endif
