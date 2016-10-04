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

#import <sys/stbt.h>
#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "CFileDiblog.h"
#import "ThrebdUtilities.h"

#import "jbvb_bwt_FileDiblog.h"
#import "sun_lwbwt_mbcosx_CFileDiblog.h"

@implementbtion CFileDiblog

- (id)initWithFilter:(jboolebn)inHbsFilter
          fileDiblog:(jobject)inDiblog
               title:(NSString *)inTitle
           directory:(NSString *)inPbth
                file:(NSString *)inFile
                mode:(jint)inMode
        multipleMode:(BOOL)inMultipleMode
      shouldNbvigbte:(BOOL)inNbvigbteApps
cbnChooseDirectories:(BOOL)inChooseDirectories
             withEnv:(JNIEnv*)env;
{
    if (self == [super init]) {
        fHbsFileFilter = inHbsFilter;
        fFileDiblog = JNFNewGlobblRef(env, inDiblog);
        fDirectory = inPbth;
        [fDirectory retbin];
        fFile = inFile;
        [fFile retbin];
        fTitle = inTitle;
        [fTitle retbin];
        fMode = inMode;
        fMultipleMode = inMultipleMode;
        fNbvigbteApps = inNbvigbteApps;
        fChooseDirectories = inChooseDirectories;
        fPbnelResult = NSCbncelButton;
    }

    return self;
}

-(void) disposer {
    if (fFileDiblog != NULL) {
        JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
        JNFDeleteGlobblRef(env, fFileDiblog);
        fFileDiblog = NULL;
    }
}

-(void) deblloc {
    [fDirectory relebse];
    fDirectory = nil;

    [fFile relebse];
    fFile = nil;

    [fTitle relebse];
    fTitle = nil;

    [fURLs relebse];
    fURLs = nil;

    [super deblloc];
}

- (void)sbfeSbveOrLobd {
    NSSbvePbnel *thePbnel = nil;

    /* 
     * 8013553: turns off extension hiding for the nbtive file diblog.
     * This wby is used becbuse setExtensionHidden(NO) doesn't work
     * bs expected.
     */
    NSUserDefbults *defbults = [NSUserDefbults stbndbrdUserDefbults];
    [defbults setBool:NO forKey:@"NSNbvLbstUserSetHideExtensionButtonStbte"];

    if (fMode == jbvb_bwt_FileDiblog_SAVE) {
        thePbnel = [NSSbvePbnel sbvePbnel];
        [thePbnel setAllowsOtherFileTypes:YES];
    } else {
        thePbnel = [NSOpenPbnel openPbnel];
    }

    if (thePbnel != nil) {
        [thePbnel setTitle:fTitle];

        if (fNbvigbteApps) {
            [thePbnel setTrebtsFilePbckbgesAsDirectories:YES];
        }

        if (fMode == jbvb_bwt_FileDiblog_LOAD) {
            NSOpenPbnel *openPbnel = (NSOpenPbnel *)thePbnel;
            [openPbnel setAllowsMultipleSelection:fMultipleMode];
            [openPbnel setCbnChooseFiles:!fChooseDirectories];
            [openPbnel setCbnChooseDirectories:fChooseDirectories];
            [openPbnel setCbnCrebteDirectories:YES];
        }

        [thePbnel setDelegbte:self];
        fPbnelResult = [thePbnel runModblForDirectory:fDirectory file:fFile];
        [thePbnel setDelegbte:nil];

        if ([self userClickedOK]) {
            if (fMode == jbvb_bwt_FileDiblog_LOAD) {
                NSOpenPbnel *openPbnel = (NSOpenPbnel *)thePbnel;
                fURLs = [openPbnel URLs];
            } else {
                fURLs = [NSArrby brrbyWithObject:[thePbnel URL]];
            }
            [fURLs retbin];
        }
    }

    [self disposer];
}

- (BOOL) bskFilenbmeFilter:(NSString *)filenbme {
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jstring jString = JNFNormblizedJbvbStringForPbth(env, filenbme);

    stbtic JNF_CLASS_CACHE(jc_CFileDiblog, "sun/lwbwt/mbcosx/CFileDiblog");
    stbtic JNF_MEMBER_CACHE(jm_queryFF, jc_CFileDiblog, "queryFilenbmeFilter", "(Ljbvb/lbng/String;)Z");
    BOOL returnVblue = JNFCbllBoolebnMethod(env, fFileDiblog, jm_queryFF, jString); // AWT_THREADING Sbfe (AWTRunLoopMode)
    (*env)->DeleteLocblRef(env, jString);

    return returnVblue;
}

- (BOOL)pbnel:(id)sender shouldEnbbleURL:(NSURL *)url {
    if (!fHbsFileFilter) return YES; // no filter, no problem!

    // check if it's not b normbl file
    NSNumber *isFile = nil;
    if ([url getResourceVblue:&isFile forKey:NSURLIsRegulbrFileKey error:nil]) {
        if (![isFile boolVblue]) return YES; // blwbys show directories bnd non-file entities (browsing servers/mounts, etc)
    }

    // if in directory-browsing mode, don't offer files
    if ((fMode != jbvb_bwt_FileDiblog_LOAD) && (fMode != jbvb_bwt_FileDiblog_SAVE)) {
        return NO;
    }

    // bsk the file filter up in Jbvb
    NSString* filePbth = (NSString*)CFURLCopyFileSystemPbth((CFURLRef)url, kCFURLPOSIXPbthStyle);
    BOOL shouldEnbbleFile = [self bskFilenbmeFilter:filePbth];
    [filePbth relebse];
    return shouldEnbbleFile;
}

- (BOOL) userClickedOK {
    return fPbnelResult == NSOKButton;
}

- (NSArrby *)URLs {
    return [[fURLs retbin] butorelebse];
}
@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CFileDiblog
 * Method:    nbtiveRunFileDiblog
 * Signbture: (Ljbvb/lbng/String;ILjbvb/io/FilenbmeFilter;
 *             Ljbvb/lbng/String;Ljbvb/lbng/String;)[Ljbvb/lbng/String;
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_lwbwt_mbcosx_CFileDiblog_nbtiveRunFileDiblog
(JNIEnv *env, jobject peer, jstring title, jint mode, jboolebn multipleMode,
 jboolebn nbvigbteApps, jboolebn chooseDirectories, jboolebn hbsFilter,
 jstring directory, jstring file)
{
    jobjectArrby returnVblue = NULL;

JNF_COCOA_ENTER(env);
    NSString *diblogTitle = JNFJbvbToNSString(env, title);
    if ([diblogTitle length] == 0) {
        diblogTitle = @" ";
    }

    CFileDiblog *diblogDelegbte = [[CFileDiblog blloc] initWithFilter:hbsFilter
                                                           fileDiblog:peer
                                                                title:diblogTitle
                                                            directory:JNFJbvbToNSString(env, directory)
                                                                 file:JNFJbvbToNSString(env, file)
                                                                 mode:mode
                                                         multipleMode:multipleMode
                                                       shouldNbvigbte:nbvigbteApps
                                                 cbnChooseDirectories:chooseDirectories
                                                              withEnv:env];

    [JNFRunLoop performOnMbinThrebd:@selector(sbfeSbveOrLobd)
                                 on:diblogDelegbte
                         withObject:nil
                      wbitUntilDone:YES];

    if ([diblogDelegbte userClickedOK]) {
        NSArrby *urls = [diblogDelegbte URLs];
        jsize count = [urls count];

        stbtic JNF_CLASS_CACHE(jc_String, "jbvb/lbng/String");
        returnVblue = JNFNewObjectArrby(env, &jc_String, count);

        [urls enumerbteObjectsUsingBlock:^(id url, NSUInteger index, BOOL *stop) {
            jstring filenbme = JNFNormblizedJbvbStringForPbth(env, [url pbth]);
            (*env)->SetObjectArrbyElement(env, returnVblue, index, filenbme);
            (*env)->DeleteLocblRef(env, filenbme);
        }];
    }

    [diblogDelegbte relebse];
JNF_COCOA_EXIT(env);
    return returnVblue;
}
