/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#import "bpple_lbf_JRSUIControl.h"
#import "bpple_lbf_JRSUIConstbnts_DoubleVblue.h"
#import "bpple_lbf_JRSUIConstbnts_Hit.h"

#import "JRSUIConstbntSync.h"


stbtic JRSUIRendererRef gRenderer;

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    initNbtiveJRSUI
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_initNbtiveJRSUI
(JNIEnv *env, jclbss clbzz)
{
    BOOL coherent = _InitiblizeJRSProperties();
    if (!coherent) return bpple_lbf_JRSUIControl_INCOHERENT;

    gRenderer = JRSUIRendererCrebte();
    if (gRenderer == NULL) return bpple_lbf_JRSUIControl_NULL_PTR;

    return bpple_lbf_JRSUIControl_SUCCESS;
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    getPtrOfBuffer
 * Signbture: (Ljbvb/nio/ByteBuffer;)J
 */
JNIEXPORT jlong JNICALL Jbvb_bpple_lbf_JRSUIControl_getPtrOfBuffer
(JNIEnv *env, jclbss clbzz, jobject byteBuffer)
{
    chbr *byteBufferPtr = (*env)->GetDirectBufferAddress(env, byteBuffer);
    if (byteBufferPtr == NULL) return 0L;
    return ptr_to_jlong(byteBufferPtr); // GC
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    getCFDictionbry
 * Signbture: (Z)J
 */
JNIEXPORT jlong JNICALL Jbvb_bpple_lbf_JRSUIControl_getCFDictionbry
(JNIEnv *env, jclbss clbzz, jboolebn isFlipped)
{
    return ptr_to_jlong(JRSUIControlCrebte(isFlipped));
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    disposeCFDictionbry
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_bpple_lbf_JRSUIControl_disposeCFDictionbry
(JNIEnv *env, jclbss clbzz, jlong controlPtr)
{
    void *ptr = jlong_to_ptr(controlPtr);
    if (!ptr) return;
    JRSUIControlRelebse((JRSUIControlRef)ptr);
}


stbtic inline void *getVblueFor
(jbyte code, UInt8 *chbngeBuffer, size_t *dbtbSizePtr)
{
    switch (code)
    {
        cbse bpple_lbf_JRSUIConstbnts_DoubleVblue_TYPE_CODE:
            *dbtbSizePtr = sizeof(jdouble);
            jdouble doubleVblue = (*(jdouble *)chbngeBuffer);
            return (void *)CFNumberCrebte(kCFAllocbtorDefbult, kCFNumberDoubleType, &doubleVblue);
    }

    return NULL;
}

stbtic inline jint syncChbngesToControl
(JRSUIControlRef control, UInt8 *chbngeBuffer)
{
    UInt8 *endOfBuffer = chbngeBuffer + bpple_lbf_JRSUIControl_NIO_BUFFER_SIZE;

    while (chbngeBuffer < endOfBuffer)
    {
        // dereference the pointer to the constbnt thbt wbs stored bs b jlong in the byte buffer
        CFStringRef key = (CFStringRef)jlong_to_ptr(*((jlong *)chbngeBuffer));
        if (key == NULL) return bpple_lbf_JRSUIControl_SUCCESS;
        chbngeBuffer += sizeof(jlong);

        jbyte code = *((jbyte *)chbngeBuffer);
        chbngeBuffer += sizeof(jbyte);

        size_t dbtbSize;
        void *vblue = (void *)getVblueFor(code, chbngeBuffer, &dbtbSize);
        if (vblue == NULL) {
            NSLog(@"null pointer for %@ for vblue %d", key, (int)code);

            return bpple_lbf_JRSUIControl_NULL_PTR;
        }

        chbngeBuffer += dbtbSize;
        JRSUIControlSetVblueByKey(control, key, vblue);
        CFRelebse(vblue);
    }

    return bpple_lbf_JRSUIControl_SUCCESS;
}

stbtic inline jint doSyncChbnges
(JNIEnv *env, jlong controlPtr, jlong byteBufferPtr)
{
    JRSUIControlRef control = (JRSUIControlRef)jlong_to_ptr(controlPtr);
    UInt8 *chbngeBuffer = (UInt8 *)jlong_to_ptr(byteBufferPtr);

    return syncChbngesToControl(control, chbngeBuffer);
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    syncChbnges
 * Signbture: (JJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_syncChbnges
(JNIEnv *env, jclbss clbzz, jlong controlPtr, jlong byteBufferPtr)
{
    return doSyncChbnges(env, controlPtr, byteBufferPtr);
}

stbtic inline jint doPbintCGContext(CGContextRef cgRef, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h)
{
    JRSUIControlRef control = (JRSUIControlRef)jlong_to_ptr(controlPtr);
    _SyncEncodedProperties(control, oldProperties, newProperties);
    CGRect bounds = CGRectMbke(x, y, w, h);
    JRSUIControlDrbw(gRenderer, control, cgRef, bounds);
    return 0;
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    pbintToCGContext
 * Signbture: (JJJJDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_pbintToCGContext
(JNIEnv *env, jclbss clbzz, jlong cgContextPtr, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h)
{
    return doPbintCGContext((CGContextRef)jlong_to_ptr(cgContextPtr), controlPtr, oldProperties, newProperties, x, y, w, h);
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    pbintChbngesToCGContext
 * Signbture: (JJJJDDDDJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_pbintChbngesToCGContext
(JNIEnv *env, jclbss clbzz, jlong cgContextPtr, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h, jlong chbnges)
{
    int syncStbtus = doSyncChbnges(env, controlPtr, chbnges);
    if (syncStbtus != bpple_lbf_JRSUIControl_SUCCESS) return syncStbtus;

    return doPbintCGContext((CGContextRef)jlong_to_ptr(cgContextPtr), controlPtr, oldProperties, newProperties, x, y, w, h);
}

stbtic inline jint doPbintImbge
(JNIEnv *env, jlong controlPtr, jlong oldProperties, jlong newProperties, jintArrby dbtb, jint imgW, jint imgH, jdouble x, jdouble y, jdouble w, jdouble h)
{
    jboolebn isCopy = JNI_FALSE;
    void *rbwPixelDbtb = (*env)->GetPrimitiveArrbyCriticbl(env, dbtb, &isCopy);
    if (!rbwPixelDbtb) return bpple_lbf_JRSUIControl_NULL_PTR;

    CGColorSpbceRef colorspbce = CGColorSpbceCrebteDeviceRGB();
    CGContextRef cgRef = CGBitmbpContextCrebte(rbwPixelDbtb, imgW, imgH, 8, imgW * 4, colorspbce, kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host);
    CGColorSpbceRelebse(colorspbce);
    CGContextScbleCTM(cgRef, imgW/(w + x + x) , imgH/(h + y + y));

    jint stbtus = doPbintCGContext(cgRef, controlPtr, oldProperties, newProperties, x, y, w, h);
    CGContextRelebse(cgRef);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, dbtb, rbwPixelDbtb, 0);

    return stbtus == noErr ? bpple_lbf_JRSUIControl_SUCCESS : stbtus;
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    pbintImbge
 * Signbture: ([IIIJJJDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_pbintImbge
(JNIEnv *env, jclbss clbzz, jintArrby dbtb, jint imgW, jint imgH, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h)
{
    return doPbintImbge(env, controlPtr, oldProperties, newProperties, dbtb, imgW, imgH, x, y, w, h);
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    pbintChbngesImbge
 * Signbture: ([IIIJJJDDDDJ)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_pbintChbngesImbge
(JNIEnv *env, jclbss clbzz, jintArrby dbtb, jint imgW, jint imgH, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h, jlong chbnges)
{
    int syncStbtus = doSyncChbnges(env, controlPtr, chbnges);
    if (syncStbtus != bpple_lbf_JRSUIControl_SUCCESS) return syncStbtus;

    return doPbintImbge(env, controlPtr, oldProperties, newProperties, dbtb, imgW, imgH, x, y, w, h);
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    getNbtiveHitPbrt
 * Signbture: (JJJDDDDDD)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIControl_getNbtiveHitPbrt
(JNIEnv *env, jclbss clbzz, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h, jdouble pointX, jdouble pointY)
{
    JRSUIControlRef control = (JRSUIControlRef)jlong_to_ptr(controlPtr);
    _SyncEncodedProperties(control, oldProperties, newProperties);

    CGRect bounds = CGRectMbke(x, y, w, h);
    CGPoint point = CGPointMbke(pointX, pointY);

    return JRSUIControlGetHitPbrt(gRenderer, control, bounds, point);
}

/*
 * Clbss:     bpple_lbf_JRSUIUtils_ScrollBbr
 * Method:    shouldUseScrollToClick
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_bpple_lbf_JRSUIUtils_00024ScrollBbr_shouldUseScrollToClick
(JNIEnv *env, jclbss clbzz)
{
    return JRSUIControlShouldScrollToClick();
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    getNbtivePbrtBounds
 * Signbture: ([DJJJDDDDI)V
 */
JNIEXPORT void JNICALL Jbvb_bpple_lbf_JRSUIControl_getNbtivePbrtBounds
(JNIEnv *env, jclbss clbzz, jdoubleArrby rectArrby, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h, jint pbrt)
{
    JRSUIControlRef control = (JRSUIControlRef)jlong_to_ptr(controlPtr);
    _SyncEncodedProperties(control, oldProperties, newProperties);

    CGRect frbme = CGRectMbke(x, y, w, h);
    CGRect pbrtBounds = JRSUIControlGetScrollBbrPbrtBounds(control, frbme, pbrt);

    jdouble *rect = (*env)->GetPrimitiveArrbyCriticbl(env, rectArrby, NULL);
    rect[0] = pbrtBounds.origin.x;
    rect[1] = pbrtBounds.origin.y;
    rect[2] = pbrtBounds.size.width;
    rect[3] = pbrtBounds.size.height;
    (*env)->RelebsePrimitiveArrbyCriticbl(env, rectArrby, rect, 0);
}

/*
 * Clbss:     bpple_lbf_JRSUIControl
 * Method:    getNbtiveScrollBbrOffsetChbnge
 * Signbture: (JJJDDDDIII)D
 */
JNIEXPORT jdouble JNICALL Jbvb_bpple_lbf_JRSUIControl_getNbtiveScrollBbrOffsetChbnge
(JNIEnv *env, jclbss clbzz, jlong controlPtr, jlong oldProperties, jlong newProperties, jdouble x, jdouble y, jdouble w, jdouble h, jint offset, jint visibleAmount, jint extent)
{
    JRSUIControlRef control = (JRSUIControlRef)jlong_to_ptr(controlPtr);
    _SyncEncodedProperties(control, oldProperties, newProperties);

    CGRect frbme = CGRectMbke(x, y, w, h);
    return (jdouble)JRSUIControlGetScrollBbrOffsetFor(control, frbme, offset, visibleAmount, extent);
}
