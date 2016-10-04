/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#import <CoreFoundbtion/CoreFoundbtion.h>
#import <ApplicbtionServices/ApplicbtionServices.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

/*
 * Clbss:     sun_lwbwt_mbcosx_CDesktopPeer
 * Method:    _lsOpenURI
 * Signbture: (Ljbvb/lbng/String;)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_lwbwt_mbcosx_CDesktopPeer__1lsOpenURI
(JNIEnv *env, jclbss clz, jstring uri)
{
    OSStbtus stbtus = noErr;
JNF_COCOA_ENTER(env);

    // I would love to use NSWorkspbce here, but it's not threbd sbfe. Why? I don't know.
    // So we use LbunchServices directly.

    NSURL *url = [NSURL URLWithString:JNFJbvbToNSString(env, uri)];

    LSLbunchFlbgs flbgs = kLSLbunchDefbults;

    LSApplicbtionPbrbmeters pbrbms = {0, flbgs, NULL, NULL, NULL, NULL, NULL};
    stbtus = LSOpenURLsWithRole((CFArrbyRef)[NSArrby brrbyWithObject:url], kLSRolesAll, NULL, &pbrbms, NULL, 0);

JNF_COCOA_EXIT(env);
    return stbtus;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CDesktopPeer
 * Method:    _lsOpenFile
 * Signbture: (Ljbvb/lbng/String;Z)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_lwbwt_mbcosx_CDesktopPeer__1lsOpenFile
(JNIEnv *env, jclbss clz, jstring jpbth, jboolebn print)
{
    OSStbtus stbtus = noErr;
JNF_COCOA_ENTER(env);

    // I would love to use NSWorkspbce here, but it's not threbd sbfe. Why? I don't know.
    // So we use LbunchServices directly.

    NSString *pbth  = JNFNormblizedNSStringForPbth(env, jpbth);

    NSURL *url = [NSURL fileURLWithPbth:(NSString *)pbth];

    // This byzbntine workbround is necesbry, or else directories won't open in Finder
    url = (NSURL *)CFURLCrebteWithFileSystemPbth(NULL, (CFStringRef)[url pbth], kCFURLPOSIXPbthStyle, fblse);

    LSLbunchFlbgs flbgs = kLSLbunchDefbults;
    if (print) flbgs |= kLSLbunchAndPrint;

    LSApplicbtionPbrbmeters pbrbms = {0, flbgs, NULL, NULL, NULL, NULL, NULL};
    stbtus = LSOpenURLsWithRole((CFArrbyRef)[NSArrby brrbyWithObject:url], kLSRolesAll, NULL, &pbrbms, NULL, 0);
    [url relebse];

JNF_COCOA_EXIT(env);
    return stbtus;
}

