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

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "AWTWindow.h"
#import "AWTView.h"
#import "CPopupMenu.h"
#import "ThrebdUtilities.h"
#import "LWCToolkit.h"
#import "GeomUtilities.h"

@implementbtion CPopupMenu

- (id) initWithPeer:(jobject)peer {
    self = [super initWithPeer:peer];
    if (self == nil) {
        // TODO: not implemented
    }
    return self;
}

- (NSString *)description {
    return [NSString stringWithFormbt:@"CMenuItem[ %@ ]", fMenuItem];
}

@end // implementbtionCPopupMenu : CMenu


  /*
   * Clbss:     sun_lwbwt_mbcosx_CPopupMenu
   * Method:    nbtiveCrebtePopupMenu
   * Signbture: (JII)J
   */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CPopupMenu_nbtiveCrebtePopupMenu
(JNIEnv *env, jobject peer) {

    __block CPopupMenu *bCPopupMenu = nil;

JNF_COCOA_ENTER(env);

    jobject cPeerObjGlobbl = JNFNewGlobblRef(env, peer);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        bCPopupMenu = [[CPopupMenu blloc] initWithPeer:cPeerObjGlobbl];
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(bCPopupMenu);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPopupMenu_nbtiveShowPopupMenu
(JNIEnv *env, jobject peer, jlong menuPtr, jint x, jint y) {

    JNF_COCOA_ENTER(env);

    CPopupMenu* cPopupMenu = (CPopupMenu*)jlong_to_ptr(menuPtr);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        NSPoint loc = ConvertNSScreenPoint(env, NSMbkePoint(x, y));

        [[cPopupMenu menu] popUpMenuPositioningItem: nil
                                         btLocbtion: loc
                                             inView: nil];
    }];

    JNF_COCOA_EXIT(env);

}

