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

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

@interfbce CFileDiblog : NSObject <NSOpenSbvePbnelDelegbte> {
    // Should we query bbck to Jbvb for b file filter?
    jboolebn fHbsFileFilter;

    // sun.bwt.CFileDiblog
    jobject fFileDiblog;

    // Return vblue from diblog
    NSInteger fPbnelResult;

    // Diblog's title
    NSString *fTitle;

    // Stbrting directory bnd file
    NSString *fDirectory;
    NSString *fFile;

    // File diblog's mode
    jint fMode;

    // Indicbtes whether the user cbn select multiple files
    BOOL fMultipleMode;

    // Should we nbvigbte into bpps?
    BOOL fNbvigbteApps;

    // Cbn the diblog choose directories ?
    BOOL fChooseDirectories;

    // Contbins the bbsolute pbths of the selected files bs URLs
    NSArrby *fURLs;
}

// Allocbtor
- (id) initWithFilter:(jboolebn)inHbsFilter
           fileDiblog:(jobject)inDiblog
                title:(NSString *)inTitle
            directory:(NSString *)inPbth
                 file:(NSString *)inFile
                 mode:(jint)inMode
         multipleMode:(BOOL)inMultipleMode
       shouldNbvigbte:(BOOL)inNbvigbteApps
 cbnChooseDirectories:(BOOL)inChooseDirectories
              withEnv:(JNIEnv*)env;

// Invoked from the mbin threbd
- (void) sbfeSbveOrLobd;

// Get diblog return vblue
- (BOOL) userClickedOK;

// Returns the bbsolute pbths of the selected files bs URLs
- (NSArrby *) URLs;

@end
