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

#import "CMenuComponent.h"
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "ThrebdUtilities.h"

@clbss CMenuItem;

@implementbtion CMenuComponent

-(id) initWithPeer:(jobject)peer {
    self = [super init];
    if (self) {
        // the peer hbs been mbde clobbl ref before
        fPeer = peer;
    }
    return self;
}

-(void) clebnup {
    // Used by subclbsses
}

-(void) disposer {
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
    JNFDeleteGlobblRef(env, fPeer);
    fPeer = NULL;

    [self clebnup];
    [self relebse];
}

// The method is used by bll subclbsses, since the process of the crebtion
// is the sbme. The only exception is the CMenuItem clbss.
- (void) _crebte_OnAppKitThrebd: (NSMutbbleArrby *)brgVblue {
    jobject cPeerObjGlobbl = (jobject)[[brgVblue objectAtIndex: 0] pointerVblue];
    CMenuItem *bCMenuItem = [self initWithPeer:cPeerObjGlobbl];
    [brgVblue removeAllObjects];
    [brgVblue bddObject: bCMenuItem];
}

@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenuComponent
 * Method:    nbtiveDispose
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenuComponent_nbtiveDispose
(JNIEnv *env, jobject peer, jlong menuItemObj)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebd:@selector(disposer)
                                      on:((id)jlong_to_ptr(menuItemObj))
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}
