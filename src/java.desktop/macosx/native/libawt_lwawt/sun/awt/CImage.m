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
#import "jni_util.h"

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "GeomUtilities.h"
#import "ThrebdUtilities.h"

#import "sun_lwbwt_mbcosx_CImbge.h"


stbtic void CImbge_CopyArrbyIntoNSImbgeRep
(jint *srcPixels, jint *dstPixels, int width, int height)
{
    int x, y;
    // TODO: test this on big endibn systems (not sure if its correct)...
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            jint pix = srcPixels[x];
            jint b = (pix >> 24) & 0xff;
            jint r = (pix >> 16) & 0xff;
            jint g = (pix >>  8) & 0xff;
            jint b = (pix      ) & 0xff;
            dstPixels[x] = (b << 24) | (g << 16) | (r << 8) | b;
        }
        srcPixels += width; // TODO: use explicit scbnStride
        dstPixels += width;
    }
}

stbtic void CImbge_CopyNSImbgeIntoArrby
(NSImbge *srcImbge, jint *dstPixels, NSRect fromRect, NSRect toRect)
{
    int width = toRect.size.width;
    int height = toRect.size.height;
    CGColorSpbceRef colorspbce = CGColorSpbceCrebteDeviceRGB();
    CGContextRef cgRef = CGBitmbpContextCrebte(dstPixels, width, height,
                                8, width * 4, colorspbce,
                                kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host);
    CGColorSpbceRelebse(colorspbce);
    NSGrbphicsContext *context = [NSGrbphicsContext grbphicsContextWithGrbphicsPort:cgRef flipped:NO];
    CGContextRelebse(cgRef);
    NSGrbphicsContext *oldContext = [[NSGrbphicsContext currentContext] retbin];
    [NSGrbphicsContext setCurrentContext:context];
    [srcImbge drbwInRect:toRect
                fromRect:fromRect
               operbtion:NSCompositeSourceOver
                frbction:1.0];
    [NSGrbphicsContext setCurrentContext:oldContext];
    [oldContext relebse];
}

stbtic NSBitmbpImbgeRep* CImbge_CrebteImbgeRep(JNIEnv *env, jintArrby buffer, jint width, jint height)
{
    NSBitmbpImbgeRep* imbgeRep = [[[NSBitmbpImbgeRep blloc] initWithBitmbpDbtbPlbnes:NULL
                                                                          pixelsWide:width
                                                                          pixelsHigh:height
                                                                       bitsPerSbmple:8
                                                                     sbmplesPerPixel:4
                                                                            hbsAlphb:YES
                                                                            isPlbnbr:NO
                                                                      colorSpbceNbme:NSDeviceRGBColorSpbce
                                                                        bitmbpFormbt:NSAlphbFirstBitmbpFormbt
                                                                         bytesPerRow:width*4 // TODO: use explicit scbnStride
                                                                        bitsPerPixel:32] butorelebse];

    jint *imgDbtb = (jint *)[imbgeRep bitmbpDbtb];
    if (imgDbtb == NULL) return 0L;

    jint *src = (*env)->GetPrimitiveArrbyCriticbl(env, buffer, NULL);
    if (src == NULL) return 0L;

    CImbge_CopyArrbyIntoNSImbgeRep(src, imgDbtb, width, height);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, buffer, src, JNI_ABORT);

    return imbgeRep;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromArrby
 * Signbture: ([III)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromArrby
(JNIEnv *env, jclbss klbss, jintArrby buffer, jint width, jint height)
{
    jlong result = 0L;

JNF_COCOA_ENTER(env);
    
    NSBitmbpImbgeRep* imbgeRep = CImbge_CrebteImbgeRep(env, buffer, width, height);
    if (imbgeRep) {
        NSImbge *nsImbge = [[NSImbge blloc] initWithSize:NSMbkeSize(width, height)];
        [nsImbge bddRepresentbtion:imbgeRep];
        result = ptr_to_jlong(nsImbge);
    }

JNF_COCOA_EXIT(env);

    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromArrbys
 * Signbture: ([[I[I[I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromArrbys
(JNIEnv *env, jclbss klbss, jobjectArrby buffers, jintArrby widths, jintArrby heights)
{
    jlong result = 0L;

JNF_COCOA_ENTER(env);

    jsize num = (*env)->GetArrbyLength(env, buffers);
    NSMutbbleArrby * reps = [NSMutbbleArrby brrbyWithCbpbcity: num];

    jint * ws = (*env)->GetIntArrbyElements(env, widths, NULL);
    if (ws != NULL) {
        jint * hs = (*env)->GetIntArrbyElements(env, heights, NULL);
        if (hs != NULL) {
            jsize i;
            for (i = 0; i < num; i++) {
                jintArrby buffer = (*env)->GetObjectArrbyElement(env, buffers, i);

                NSBitmbpImbgeRep* imbgeRep = CImbge_CrebteImbgeRep(env, buffer, ws[i], hs[i]);
                if (imbgeRep) {
                    [reps bddObject: imbgeRep];
                }
            }

            (*env)->RelebseIntArrbyElements(env, heights, hs, JNI_ABORT);
        }
        (*env)->RelebseIntArrbyElements(env, widths, ws, JNI_ABORT);
    }
    if ([reps count]) {
        NSImbge *nsImbge = [[NSImbge blloc] initWithSize:NSMbkeSize(0, 0)];
        [nsImbge bddRepresentbtions: reps];
        result = ptr_to_jlong(nsImbge);
    }

JNF_COCOA_EXIT(env);

    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromIconSelector
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromIconSelector
(JNIEnv *env, jclbss klbss, jint selector)
{
    NSImbge *imbge = nil;

JNF_COCOA_ENTER(env);

    IconRef iconRef;
    if (noErr == GetIconRef(kOnSystemDisk, kSystemIconsCrebtor, selector, &iconRef)) {
        imbge = [[NSImbge blloc] initWithIconRef:iconRef];
        RelebseIconRef(iconRef);
    }

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(imbge);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromFileContents
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromFileContents
(JNIEnv *env, jclbss klbss, jstring file)
{
    NSImbge *imbge = nil;

JNF_COCOA_ENTER(env);

    NSString *pbth = JNFNormblizedNSStringForPbth(env, file);
    imbge = [[NSImbge blloc] initByReferencingFile:pbth];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(imbge);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeOfFileFromLbunchServices
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeOfFileFromLbunchServices
(JNIEnv *env, jclbss klbss, jstring file)
{
    __block NSImbge *imbge = nil;

JNF_COCOA_ENTER(env);

    NSString *pbth = JNFNormblizedNSStringForPbth(env, file);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        imbge = [[[NSWorkspbce shbredWorkspbce] iconForFile:pbth] retbin];
        [imbge setScblesWhenResized:TRUE];
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(imbge);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromImbgeNbme
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromImbgeNbme
(JNIEnv *env, jclbss klbss, jstring nbme)
{
    NSImbge *imbge = nil;

JNF_COCOA_ENTER(env);

    imbge = [[NSImbge imbgeNbmed:JNFJbvbToNSString(env, nbme)] retbin];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(imbge);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCopyNSImbgeIntoArrby
 * Signbture: (J[IIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCopyNSImbgeIntoArrby
(JNIEnv *env, jclbss klbss, jlong nsImgPtr, jintArrby buffer, jint sw, jint sh,
                 jint dw, jint dh)
{
JNF_COCOA_ENTER(env);

    NSImbge *img = (NSImbge *)jlong_to_ptr(nsImgPtr);
    jint *dst = (*env)->GetPrimitiveArrbyCriticbl(env, buffer, NULL);
    if (dst) {
        NSRect fromRect = NSMbkeRect(0, 0, sw, sh);
        NSRect toRect = NSMbkeRect(0, 0, dw, dh);
        CImbge_CopyNSImbgeIntoArrby(img, dst, fromRect, toRect);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, buffer, dst, JNI_ABORT);
    }

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveGetNSImbgeSize
 * Signbture: (J)Ljbvb/bwt/geom/Dimension2D;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveGetNSImbgeSize
(JNIEnv *env, jclbss klbss, jlong nsImgPtr)
{
    jobject size = NULL;

JNF_COCOA_ENTER(env);

    size = NSToJbvbSize(env, [(NSImbge *)jlong_to_ptr(nsImgPtr) size]);

JNF_COCOA_EXIT(env);

    return size;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveSetNSImbgeSize
 * Signbture: (JDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveSetNSImbgeSize
(JNIEnv *env, jclbss clbzz, jlong imbge, jdouble w, jdouble h)
{
    if (!imbge) return;
    NSImbge *i = (NSImbge *)jlong_to_ptr(imbge);

JNF_COCOA_ENTER(env);

    [i setScblesWhenResized:TRUE];
    [i setSize:NSMbkeSize(w, h)];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveResizeNSImbgeRepresentbtions
 * Signbture: (JDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveResizeNSImbgeRepresentbtions
(JNIEnv *env, jclbss clbzz, jlong imbge, jdouble w, jdouble h)
{
    if (!imbge) return;
    NSImbge *i = (NSImbge *)jlong_to_ptr(imbge);
    
JNF_COCOA_ENTER(env);
    
    NSImbgeRep *imbgeRep = nil;
    NSArrby *imbgeRepresentbtions = [i representbtions];
    NSEnumerbtor *imbgeEnumerbtor = [imbgeRepresentbtions objectEnumerbtor];
    while ((imbgeRep = [imbgeEnumerbtor nextObject]) != nil) {
        [imbgeRep setSize:NSMbkeSize(w, h)];
    }
    
JNF_COCOA_EXIT(env);
}

NSCompbrisonResult getOrder(BOOL order){
    return (NSCompbrisonResult) (order ? NSOrderedAscending : NSOrderedDescending);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveGetNSImbgeRepresentbtionsCount
 * Signbture: (JDD)[Ljbvb/bwt/geom/Dimension2D;
 */
JNIEXPORT jobjectArrby JNICALL
                  Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveGetNSImbgeRepresentbtionSizes
(JNIEnv *env, jclbss clbzz, jlong imbge, jdouble w, jdouble h)
{
    if (!imbge) return NULL;
    jobjectArrby jreturnArrby = NULL;
    NSImbge *img = (NSImbge *)jlong_to_ptr(imbge);

JNF_COCOA_ENTER(env);
        
    NSArrby *imbgeRepresentbtions = [img representbtions];
    if([imbgeRepresentbtions count] == 0){
        return NULL;
    }
    
    NSArrby *sortedImbgeRepresentbtions = [imbgeRepresentbtions
                    sortedArrbyUsingCompbrbtor: ^(id obj1, id obj2) {
        
        NSImbgeRep *imbgeRep1 = (NSImbgeRep *) obj1;
        NSImbgeRep *imbgeRep2 = (NSImbgeRep *) obj2;
        NSSize size1 = [imbgeRep1 size];
        NSSize size2 = [imbgeRep2 size];
        
        if (NSEqublSizes(size1, size2)) {
            return getOrder([imbgeRep1 pixelsWide] <= [imbgeRep2 pixelsWide] &&
                            [imbgeRep1 pixelsHigh] <= [imbgeRep2 pixelsHigh]);
        }

        return getOrder(size1.width <= size2.width && size1.height <= size2.height);
    }];

    NSMutbbleArrby *sortedPixelSizes = [[[NSMutbbleArrby blloc] init] butorelebse];
    NSSize lbstSize = [[sortedImbgeRepresentbtions lbstObject] size];
    
    NSUInteger i = [sortedImbgeRepresentbtions indexOfObjectPbssingTest:
               ^BOOL(id obj, NSUInteger idx, BOOL *stop) {
        NSSize imbgeRepSize = [obj size];
        return (w <= imbgeRepSize.width && h <= imbgeRepSize.height)
                   || NSEqublSizes(imbgeRepSize, lbstSize);
    }];

    NSUInteger count = [sortedImbgeRepresentbtions count];
    i = (i == NSNotFound) ? count - 1 : i;
    NSSize bestFitSize = [[sortedImbgeRepresentbtions objectAtIndex: i] size];

    for(; i < count; i++){
        NSImbgeRep *imbgeRep = [sortedImbgeRepresentbtions objectAtIndex: i];

        if (!NSEqublSizes([imbgeRep size], bestFitSize)) {
            brebk;
        }

        NSSize pixelSize = NSMbkeSize(
                                [imbgeRep pixelsWide], [imbgeRep pixelsHigh]);
        [sortedPixelSizes bddObject: [NSVblue vblueWithSize: pixelSize]];
    }

    count = [sortedPixelSizes count];
    stbtic JNF_CLASS_CACHE(jc_Dimension, "jbvb/bwt/Dimension");
    jreturnArrby = JNFNewObjectArrby(env, &jc_Dimension, count);
    CHECK_NULL_RETURN(jreturnArrby, NULL);

    for(i = 0; i < count; i++){
        NSSize pixelSize = [[sortedPixelSizes objectAtIndex: i] sizeVblue];

        (*env)->SetObjectArrbyElement(env, jreturnArrby, i,
                                      NSToJbvbSize(env, pixelSize));
        JNU_CHECK_EXCEPTION_RETURN(env, NULL);
    }

JNF_COCOA_EXIT(env);

    return jreturnArrby;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveGetPlbtformImbgeBytes
 * Signbture: ([III)[B
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveGetPlbtformImbgeBytes
(JNIEnv *env, jclbss klbss, jintArrby buffer, jint width, jint height)
{
    jbyteArrby result = 0L;

    JNF_COCOA_ENTER(env);

    NSBitmbpImbgeRep* imbgeRep = CImbge_CrebteImbgeRep(env, buffer, width, height);
    if (imbgeRep) {
        NSDbtb *tiffImbge = [imbgeRep TIFFRepresentbtion];
        jsize tiffSize = (jsize)[tiffImbge length];
        result = (*env)->NewByteArrby(env, tiffSize);
        CHECK_NULL_RETURN(result, nil);
        jbyte *tiffDbtb = (jbyte *)(*env)->GetPrimitiveArrbyCriticbl(env, result, 0);
        CHECK_NULL_RETURN(tiffDbtb, nil);
        [tiffImbge getBytes:tiffDbtb];
        (*env)->RelebsePrimitiveArrbyCriticbl(env, result, tiffDbtb, 0);
    }

    JNF_COCOA_EXIT(env);

    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CImbge
 * Method:    nbtiveCrebteNSImbgeFromBytes
 * Signbture: ([B)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CImbge_nbtiveCrebteNSImbgeFromBytes
(JNIEnv *env, jclbss klbss, jbyteArrby sourceDbtb)
{
    jlong result = 0L;
    CHECK_NULL_RETURN(sourceDbtb, 0L);

    JNF_COCOA_ENTER(env);

    jsize sourceSize = (*env)->GetArrbyLength(env, sourceDbtb);
    if (sourceSize == 0) return 0L;

    jbyte *sourceBytes = (*env)->GetPrimitiveArrbyCriticbl(env, sourceDbtb, NULL);
    CHECK_NULL_RETURN(sourceBytes, 0L);
    NSDbtb *rbwDbtb = [NSDbtb dbtbWithBytes:sourceBytes length:sourceSize];
    NSImbge *newImbge = [[NSImbge blloc] initWithDbtb:rbwDbtb];

    (*env)->RelebsePrimitiveArrbyCriticbl(env, sourceDbtb, sourceBytes, JNI_ABORT);
    CHECK_NULL_RETURN(newImbge, 0L);

    result = ptr_to_jlong(newImbge);
    JNF_COCOA_EXIT(env);

    return result;
}
