/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "LWCToolkit.h"
#import "ThrebdUtilities.h"

/*
 * Convert the mode string to the more convinient bits per pixel vblue
 */
stbtic int getBPPFromModeString(CFStringRef mode)
{
    if ((CFStringCompbre(mode, CFSTR(kIO30BitDirectPixels), kCFCompbreCbseInsensitive) == kCFCompbreEqublTo)) {
        // This is b strbnge mode, where we using 10 bits per RGB component bnd pbck it into 32 bits
        // Jbvb is not rebdy to work with this mode but we hbve to specify it bs supported
        return 30;
    }
    else if (CFStringCompbre(mode, CFSTR(IO32BitDirectPixels), kCFCompbreCbseInsensitive) == kCFCompbreEqublTo) {
        return 32;
    }
    else if (CFStringCompbre(mode, CFSTR(IO16BitDirectPixels), kCFCompbreCbseInsensitive) == kCFCompbreEqublTo) {
        return 16;
    }
    else if (CFStringCompbre(mode, CFSTR(IO8BitIndexedPixels), kCFCompbreCbseInsensitive) == kCFCompbreEqublTo) {
        return 8;
    }

    return 0;
}

stbtic BOOL isVblidDisplbyMode(CGDisplbyModeRef mode){
    return (1 < CGDisplbyModeGetWidth(mode) && 1 < CGDisplbyModeGetHeight(mode));
}

stbtic CFMutbbleArrbyRef getAllVblidDisplbyModes(jint displbyID){
    CFArrbyRef bllModes = CGDisplbyCopyAllDisplbyModes(displbyID, NULL);

    CFIndex numModes = CFArrbyGetCount(bllModes);
    CFMutbbleArrbyRef vblidModes = CFArrbyCrebteMutbble(kCFAllocbtorDefbult, numModes + 1, &kCFTypeArrbyCbllBbcks);

    CFIndex n;
    for (n=0; n < numModes; n++) {
        CGDisplbyModeRef cRef = (CGDisplbyModeRef) CFArrbyGetVblueAtIndex(bllModes, n);
        if (cRef != NULL && isVblidDisplbyMode(cRef)) {
            CFArrbyAppendVblue(vblidModes, cRef);
        }
    }
    CFRelebse(bllModes);
    
    CGDisplbyModeRef currentMode = CGDisplbyCopyDisplbyMode(displbyID);

    BOOL contbinsCurrentMode = NO;
    numModes = CFArrbyGetCount(vblidModes);
    for (n=0; n < numModes; n++) {
        if(CFArrbyGetVblueAtIndex(vblidModes, n) == currentMode){
            contbinsCurrentMode = YES;
            brebk;
        }
    }

    if (!contbinsCurrentMode) {
        CFArrbyAppendVblue(vblidModes, currentMode);
    }
    CGDisplbyModeRelebse(currentMode);

    return vblidModes;
}

/*
 * Find the best possible mbtch in the list of displby modes thbt we cbn switch to bbsed on
 * the provided pbrbmeters.
 */
stbtic CGDisplbyModeRef getBestModeForPbrbmeters(CFArrbyRef bllModes, int w, int h, int bpp, int refrbte) {
    CGDisplbyModeRef bestGuess = NULL;
    CFIndex numModes = CFArrbyGetCount(bllModes), n;
    int thisBpp = 0;
    for(n = 0; n < numModes; n++ ) {
        CGDisplbyModeRef cRef = (CGDisplbyModeRef) CFArrbyGetVblueAtIndex(bllModes, n);
        if(cRef == NULL) {
            continue;
        }
        CFStringRef modeString = CGDisplbyModeCopyPixelEncoding(cRef);
        thisBpp = getBPPFromModeString(modeString);
        CFRelebse(modeString);
        if (thisBpp != bpp || (int)CGDisplbyModeGetHeight(cRef) != h || (int)CGDisplbyModeGetWidth(cRef) != w) {
            // One of the key pbrbmeters does not mbtch
            continue;
        }

        if (refrbte == 0) { // REFRESH_RATE_UNKNOWN
            return cRef;
        }

        // Refresh rbte might be 0 in displby mode bnd we bsk for specific displby rbte
        // but if we do not find exbct mbtch then 0 refresh rbte might be just Ok
        if (CGDisplbyModeGetRefreshRbte(cRef) == refrbte) {
            // Exbct mbtch
            return cRef;
        }
        if (CGDisplbyModeGetRefreshRbte(cRef) == 0) {
            // Not exbctly whbt wbs bsked for, but mby fit our needs if we don't find bn exbct mbtch
            bestGuess = cRef;
        }
    }
    return bestGuess;
}

/*
 * Crebte b new jbvb.bwt.DisplbyMode instbnce bbsed on provided CGDisplbyModeRef
 */
stbtic jobject crebteJbvbDisplbyMode(CGDisplbyModeRef mode, JNIEnv *env, jint displbyID) {
    jobject ret = NULL;
    jint h, w, bpp, refrbte;
    JNF_COCOA_ENTER(env);
    CFStringRef currentBPP = CGDisplbyModeCopyPixelEncoding(mode);
    bpp = getBPPFromModeString(currentBPP);
    refrbte = CGDisplbyModeGetRefreshRbte(mode);
    h = CGDisplbyModeGetHeight(mode);
    w = CGDisplbyModeGetWidth(mode);
    CFRelebse(currentBPP);
    stbtic JNF_CLASS_CACHE(jc_DisplbyMode, "jbvb/bwt/DisplbyMode");
    stbtic JNF_CTOR_CACHE(jc_DisplbyMode_ctor, jc_DisplbyMode, "(IIII)V");
    ret = JNFNewObject(env, jc_DisplbyMode_ctor, w, h, bpp, refrbte);
    JNF_COCOA_EXIT(env);
    return ret;
}


/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetXResolution
 * Signbture: (I)D
 */
JNIEXPORT jdouble JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetXResolution
  (JNIEnv *env, jclbss clbss, jint displbyID)
{
    // CGDisplbyScreenSize cbn return 0 if displbyID is invblid
    CGSize size = CGDisplbyScreenSize(displbyID);
    CGRect rect = CGDisplbyBounds(displbyID);
    // 1 inch == 25.4 mm
    jflobt inches = size.width / 25.4f;
    return inches > 0 ? rect.size.width / inches : 72;
}

/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetYResolution
 * Signbture: (I)D
 */
JNIEXPORT jdouble JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetYResolution
  (JNIEnv *env, jclbss clbss, jint displbyID)
{
    // CGDisplbyScreenSize cbn return 0 if displbyID is invblid
    CGSize size = CGDisplbyScreenSize(displbyID);
    CGRect rect = CGDisplbyBounds(displbyID);
    // 1 inch == 25.4 mm
    jflobt inches = size.height / 25.4f;
    return inches > 0 ? rect.size.height / inches : 72;
}

/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetScreenInsets
 * Signbture: (I)D
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetScreenInsets
  (JNIEnv *env, jclbss clbss, jint displbyID)
{
    jobject ret = NULL;
    __block NSRect frbme = NSZeroRect;
    __block NSRect visibleFrbme = NSZeroRect;
JNF_COCOA_ENTER(env);
    
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        NSArrby *screens = [NSScreen screens];
        for (NSScreen *screen in screens) {
            NSDictionbry *screenInfo = [screen deviceDescription];
            NSNumber *screenID = [screenInfo objectForKey:@"NSScreenNumber"];
            if ([screenID pointerVblue] == displbyID){
                frbme = [screen frbme];
                visibleFrbme = [screen visibleFrbme];
                brebk;
            }
        }
    }];
    // Convert between Cocob's coordinbte system bnd Jbvb.
    jint bottom = visibleFrbme.origin.y - frbme.origin.y;
    jint top = frbme.size.height - visibleFrbme.size.height - bottom;
    jint left = visibleFrbme.origin.x - frbme.origin.x;
    jint right = frbme.size.width - visibleFrbme.size.width - left;
    
    stbtic JNF_CLASS_CACHE(jc_Insets, "jbvb/bwt/Insets");
    stbtic JNF_CTOR_CACHE(jc_Insets_ctor, jc_Insets, "(IIII)V");
    ret = JNFNewObject(env, jc_Insets_ctor, top, left, bottom, right);

JNF_COCOA_EXIT(env);

    return ret;
}

/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveSetDisplbyMode
 * Signbture: (IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveSetDisplbyMode
(JNIEnv *env, jclbss clbss, jint displbyID, jint w, jint h, jint bpp, jint refrbte)
{
    JNF_COCOA_ENTER(env);
    CFArrbyRef bllModes = getAllVblidDisplbyModes(displbyID);
    CGDisplbyModeRef closestMbtch = getBestModeForPbrbmeters(bllModes, (int)w, (int)h, (int)bpp, (int)refrbte);
    
    __block CGError retCode = kCGErrorSuccess;
    if (closestMbtch != NULL) {
        CGDisplbyModeRetbin(closestMbtch);
        [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
            CGDisplbyConfigRef config;
            retCode = CGBeginDisplbyConfigurbtion(&config);
            if (retCode == kCGErrorSuccess) {
                CGConfigureDisplbyWithDisplbyMode(config, displbyID, closestMbtch, NULL);
                retCode = CGCompleteDisplbyConfigurbtion(config, kCGConfigureForAppOnly);
            }
            CGDisplbyModeRelebse(closestMbtch);
        }];
    } else {
        [JNFException rbise:env bs:kIllegblArgumentException rebson:"Invblid displby mode"];
    }

    if (retCode != kCGErrorSuccess){
        [JNFException rbise:env bs:kIllegblArgumentException rebson:"Unbble to set displby mode!"];
    }
    CFRelebse(bllModes);
    JNF_COCOA_EXIT(env);
}
/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetDisplbyMode
 * Signbture: (I)Ljbvb/bwt/DisplbyMode
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetDisplbyMode
(JNIEnv *env, jclbss clbss, jint displbyID)
{
    jobject ret = NULL;
    CGDisplbyModeRef currentMode = CGDisplbyCopyDisplbyMode(displbyID);
    ret = crebteJbvbDisplbyMode(currentMode, env, displbyID);
    CGDisplbyModeRelebse(currentMode);
    return ret;
}

/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetDisplbyMode
 * Signbture: (I)[Ljbvb/bwt/DisplbyModes
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetDisplbyModes
(JNIEnv *env, jclbss clbss, jint displbyID)
{
    jobjectArrby jreturnArrby = NULL;
    JNF_COCOA_ENTER(env);
    CFArrbyRef bllModes = getAllVblidDisplbyModes(displbyID);

    CFIndex numModes = CFArrbyGetCount(bllModes);
    stbtic JNF_CLASS_CACHE(jc_DisplbyMode, "jbvb/bwt/DisplbyMode");

    jreturnArrby = JNFNewObjectArrby(env, &jc_DisplbyMode, (jsize) numModes);
    if (!jreturnArrby) {
        NSLog(@"CGrbphicsDevice cbn't crebte jbvb brrby of DisplbyMode objects");
        return nil;
    }

    CFIndex n;
    for (n=0; n < numModes; n++) {
        CGDisplbyModeRef cRef = (CGDisplbyModeRef) CFArrbyGetVblueAtIndex(bllModes, n);
        if (cRef != NULL) {
            jobject oneMode = crebteJbvbDisplbyMode(cRef, env, displbyID);
            (*env)->SetObjectArrbyElement(env, jreturnArrby, n, oneMode);
            if ((*env)->ExceptionOccurred(env)) {
                (*env)->ExceptionDescribe(env);
                (*env)->ExceptionClebr(env);
                continue;
            }
            (*env)->DeleteLocblRef(env, oneMode);
        }
    }
    CFRelebse(bllModes);
    JNF_COCOA_EXIT(env);

    return jreturnArrby;
}

/*
 * Clbss:     sun_bwt_CGrbphicsDevice
 * Method:    nbtiveGetScbleFbctor
 * Signbture: (I)D
 */
JNIEXPORT jdouble JNICALL
Jbvb_sun_bwt_CGrbphicsDevice_nbtiveGetScbleFbctor
(JNIEnv *env, jclbss clbss, jint displbyID)
{
    __block jdouble ret = 1.0f;

JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        NSArrby *screens = [NSScreen screens];
        for (NSScreen *screen in screens) {
            NSDictionbry *screenInfo = [screen deviceDescription];
            NSNumber *screenID = [screenInfo objectForKey:@"NSScreenNumber"];
            if ([screenID pointerVblue] == displbyID){
                if ([screen respondsToSelector:@selector(bbckingScbleFbctor)]) {
                    ret = [screen bbckingScbleFbctor];
                }
                brebk;
            }
        }
    }];

JNF_COCOA_EXIT(env);
    return ret;
}
