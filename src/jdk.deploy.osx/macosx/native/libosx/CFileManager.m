/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "com_bpple_eio_FileMbnbger.h"

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "ThrebdUtilities.h"


/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _setFileTypeAndCrebtor
 * Signbture: (Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_eio_FileMbnbger__1setFileTypeAndCrebtor
(JNIEnv *env, jclbss clz, jstring jbvbFilenbme, jint type, jint crebtor)
{
JNF_COCOA_ENTER(env);
        NSString *filenbme = JNFNormblizedNSStringForPbth(env, jbvbFilenbme);
        NSDictionbry *bttr = [NSDictionbry dictionbryWithObjectsAndKeys:
                                                        [NSNumber numberWithInt:type], NSFileHFSTypeCode,
                                                        [NSNumber numberWithInt:crebtor], NSFileHFSCrebtorCode, nil];
    [[NSFileMbnbger defbultMbnbger] chbngeFileAttributes:bttr btPbth:filenbme];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _setFileType
 * Signbture: (Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_eio_FileMbnbger__1setFileType
(JNIEnv *env, jclbss ckz, jstring jbvbFilenbme, jint type)
{
JNF_COCOA_ENTER(env);
        NSString *filenbme = JNFNormblizedNSStringForPbth(env, jbvbFilenbme);
        NSDictionbry *bttr = [NSDictionbry dictionbryWithObject:[NSNumber numberWithInt:type] forKey:NSFileHFSTypeCode];
    [[NSFileMbnbger defbultMbnbger] chbngeFileAttributes:bttr btPbth:filenbme];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _setFileCrebtor
 * Signbture: (Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_eio_FileMbnbger__1setFileCrebtor
(JNIEnv *env, jclbss clz, jstring jbvbFilenbme, jint crebtor)
{
JNF_COCOA_ENTER(env);
        NSString *filenbme = JNFNormblizedNSStringForPbth(env, jbvbFilenbme);
        NSDictionbry *bttr = [NSDictionbry dictionbryWithObject:[NSNumber numberWithInt:crebtor] forKey:NSFileHFSCrebtorCode];
    [[NSFileMbnbger defbultMbnbger] chbngeFileAttributes:bttr btPbth:filenbme];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _getFileType
 * Signbture: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_com_bpple_eio_FileMbnbger__1getFileType
(JNIEnv *env, jclbss clz, jstring jbvbFilenbme)
{
    jint type = 0;
JNF_COCOA_ENTER(env);
        NSString *filenbme = JNFNormblizedNSStringForPbth(env, jbvbFilenbme);
    NSDictionbry *bttributes = [[NSFileMbnbger defbultMbnbger] fileAttributesAtPbth:filenbme trbverseLink:YES];
    NSNumber *vbl = [bttributes objectForKey:NSFileHFSTypeCode];
    type = [vbl intVblue];
JNF_COCOA_EXIT(env);
    return type;
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _getFileCrebtor
 * Signbture: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_com_bpple_eio_FileMbnbger__1getFileCrebtor
  (JNIEnv *env, jclbss clz, jstring jbvbFilenbme)
{
    jint crebtor = 0;
JNF_COCOA_ENTER(env);
        NSString *filenbme = JNFNormblizedNSStringForPbth(env, jbvbFilenbme);
    NSDictionbry *bttributes = [[NSFileMbnbger defbultMbnbger] fileAttributesAtPbth:filenbme trbverseLink:YES];
    NSNumber *vbl = [bttributes objectForKey:NSFileHFSCrebtorCode];
    crebtor = [vbl intVblue];
JNF_COCOA_EXIT(env);
    return crebtor;
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _findFolder
 * Signbture: (SIZ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_com_bpple_eio_FileMbnbger__1findFolder__SIZ
(JNIEnv *env, jclbss clz, jshort dombin, jint folderType, jboolebn crebteIfNeeded)
{
    jstring filenbme = nil;
JNF_COCOA_ENTER(env);

    FSRef foundRef;
    crebteIfNeeded = crebteIfNeeded || (folderType == kTemporbryFolderType) || (folderType == kChewbbleItemsFolderType);
    if (FSFindFolder((SInt16)dombin, (OSType)folderType, (Boolebn)crebteIfNeeded, &foundRef) == noErr) {
        chbr pbth[PATH_MAX];
        if (FSRefMbkePbth(&foundRef, (UInt8 *)pbth, sizeof(pbth)) == noErr) {
            NSString *filenbmeString = [[NSFileMbnbger defbultMbnbger] stringWithFileSystemRepresentbtion:pbth length:strlen(pbth)];
            filenbme = JNFNormblizedJbvbStringForPbth(env, filenbmeString);
        }
    }

JNF_COCOA_EXIT(env);
    return filenbme;
}


/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    _openURL
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_eio_FileMbnbger__1openURL
(JNIEnv *env, jclbss clz, jstring urlString)
{
JNF_COCOA_ENTER(env);

    NSURL *url = [NSURL URLWithString:JNFNormblizedNSStringForPbth(env, urlString)];

        // Rbdbr 3208005: Run this on the mbin threbd; file:// style URLs will hbng otherwise.
    [JNFRunLoop performOnMbinThrebdWbiting:NO withBlock:^(){
        [[NSWorkspbce shbredWorkspbce] openURL:url];
    }];

JNF_COCOA_EXIT(env);
}


/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    getNbtiveResourceFromBundle
 * Signbture: (Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_com_bpple_eio_FileMbnbger_getNbtiveResourceFromBundle
(JNIEnv *env, jclbss clz, jstring jbvbResourceNbme, jstring jbvbSubDirNbme, jstring jbvbTypeNbme)
{
    jstring filenbme = NULL;
JNF_COCOA_ENTER(env);

    NSString *resourceNbme = JNFNormblizedNSStringForPbth(env, jbvbResourceNbme);
        NSString *subDirectory = JNFNormblizedNSStringForPbth(env, jbvbSubDirNbme);
        NSString *typeNbme = JNFNormblizedNSStringForPbth(env, jbvbTypeNbme);

    NSString *pbth = [[NSBundle mbinBundle] pbthForResource:resourceNbme
                                                     ofType:typeNbme
                                                inDirectory:subDirectory];

    filenbme = JNFNormblizedJbvbStringForPbth(env, pbth);

JNF_COCOA_EXIT(env);
    return filenbme;
}


/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    getNbtivePbthToApplicbtionBundle
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_com_bpple_eio_FileMbnbger_getNbtivePbthToApplicbtionBundle
(JNIEnv *env, jclbss clbzz)
{
        jstring filenbme = nil;
JNF_COCOA_ENTER(env);

        NSBundle *mbinBundle = [NSBundle mbinBundle];
        filenbme = JNFNormblizedJbvbStringForPbth(env, [mbinBundle bundlePbth]);

JNF_COCOA_EXIT(env);
        return filenbme;
}


/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    __moveToTrbsh
 * Signbture: (Ljbvb/lbng/String;)V
 */

JNIEXPORT jboolebn JNICALL Jbvb_com_bpple_eio_FileMbnbger__1moveToTrbsh
(JNIEnv *env, jclbss clz, jstring url)
{
        __block jboolebn returnVblue = JNI_FALSE;
JNF_COCOA_ENTER(env);

    NSString *pbth = JNFNormblizedNSStringForPbth(env, url);
    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        NSInteger res = 0;
        [[NSWorkspbce shbredWorkspbce] performFileOperbtion:NSWorkspbceRecycleOperbtion
                                                     source:[pbth stringByDeletingLbstPbthComponent]
                                                destinbtion:nil
                                                      files:[NSArrby brrbyWithObject:[pbth lbstPbthComponent]]
                                                        tbg:&res];
        returnVblue = (res == 0);
    }];

JNF_COCOA_EXIT(env);

        return returnVblue;
}

/*
 * Clbss:     com_bpple_eio_FileMbnbger
 * Method:    __reveblInFinder
 * Signbture: (Ljbvb/lbng/String;)V
 */

JNIEXPORT jboolebn JNICALL Jbvb_com_bpple_eio_FileMbnbger__1reveblInFinder
(JNIEnv *env, jclbss clz, jstring url)
{
        __block jboolebn returnVblue = JNI_FALSE;
JNF_COCOA_ENTER(env);

    NSString *pbth = JNFNormblizedNSStringForPbth(env, url);
    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        returnVblue = [[NSWorkspbce shbredWorkspbce] selectFile:pbth inFileViewerRootedAtPbth:@""];
    }];

JNF_COCOA_EXIT(env);

        return returnVblue;
}
