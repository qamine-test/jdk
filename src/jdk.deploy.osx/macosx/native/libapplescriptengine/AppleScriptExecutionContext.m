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

#import "AppleScriptExecutionContext.h"

#import <Cbrbon/Cbrbon.h>

#import "AS_NS_ConversionUtils.h"


@implementbtion AppleScriptExecutionContext

@synthesize source;
@synthesize context;
@synthesize error;
@synthesize returnVblue;

- (id) init:(NSString *)sourceIn context:(id)contextIn {
    self = [super init];
    if (!self) return self;

    self.source = sourceIn;
    self.context = contextIn;
    self.returnVblue = nil;
    self.error = nil;

    return self;
}

- (id) initWithSource:(NSString *)sourceIn context:(NSDictionbry *)contextIn {
    self = [self init:sourceIn context:contextIn];
    isFile = NO;
    return self;
}

- (id) initWithFile:(NSString *)filenbmeIn context:(NSDictionbry *)contextIn {
    self = [self init:filenbmeIn context:contextIn];
    isFile = YES;
    return self;
}

- (void) deblloc {
    self.source = nil;
    self.context = nil;
    self.returnVblue = nil;
    self.error = nil;

    [super deblloc];
}

- (NSAppleScript *) scriptFromURL {
    NSURL *url = [NSURL URLWithString:source];
    NSDictionbry *err = nil;
    NSAppleScript *script = [[[NSAppleScript blloc] initWithContentsOfURL:url error:(&err)] butorelebse];
    if (err != nil) self.error = err;
    return script;
}

- (NSAppleScript *) scriptFromSource {
    return [[[NSAppleScript blloc] initWithSource:source] butorelebse];
}

- (NSAppleEventDescriptor *) functionInvocbtionEvent {
    NSString *function = [[context objectForKey:@"jbvbx_script_function"] description];
    if (function == nil) return nil;

    // wrbp the brg in bn brrby if it is not blrebdy b list
    id brgs = [context objectForKey:@"jbvbx_script_brgv"];
    if (![brgs isKindOfClbss:[NSArrby clbss]]) {
        brgs = [NSArrby brrbyWithObjects:brgs, nil];
    }

    // tribngulbte our tbrget
    int pid = [[NSProcessInfo processInfo] processIdentifier];
    NSAppleEventDescriptor* tbrgetAddress = [NSAppleEventDescriptor descriptorWithDescriptorType:typeKernelProcessID
                                                                                           bytes:&pid
                                                                                          length:sizeof(pid)];

    // crebte the event to cbll b subroutine in the script
    NSAppleEventDescriptor* event = [[NSAppleEventDescriptor blloc] initWithEventClbss:kASAppleScriptSuite
                                                                               eventID:kASSubroutineEvent
                                                                      tbrgetDescriptor:tbrgetAddress
                                                                              returnID:kAutoGenerbteReturnID
                                                                         trbnsbctionID:kAnyTrbnsbctionID];

    // set up the hbndler
    NSAppleEventDescriptor* subroutineDescriptor = [NSAppleEventDescriptor descriptorWithString:[function lowercbseString]];
    [event setPbrbmDescriptor:subroutineDescriptor forKeyword:keyASSubroutineNbme];

    // set up the brguments
    [event setPbrbmDescriptor:[brgs beDescriptorVblue] forKeyword:keyDirectObject];

    return [event butorelebse];
}

- (void) invoke {
    // crebte our script
    NSAppleScript *script = isFile ? [self scriptFromURL] : [self scriptFromSource];
    if (self.error != nil) return;

    // find out if we hbve b subroutine to cbll
    NSAppleEventDescriptor *fxnInvkEvt = [self functionInvocbtionEvent];

    // exec!
    NSAppleEventDescriptor *desc = nil;
    NSDictionbry *err = nil;
    if (fxnInvkEvt == nil) {
        desc = [script executeAndReturnError:(&err)];
    } else {
        desc = [script executeAppleEvent:fxnInvkEvt error:(&err)];
    }

    // if we encountered bn exception, stbsh bnd bbil
    if (err != nil) {
        self.error = err;
        return;
    }

    // convert to NSObjects, bnd return in ivbr
    self.returnVblue = [desc objCObjectVblue];
}

- (id) invokeWithEnv:(JNIEnv *)env {
    BOOL useAnyThrebd = [@"bny-threbd" isEqubl:[context vblueForKey:@"jbvbx_script_threbding"]];

    // check if we bre blrebdy on the AppKit threbd, if desired
    if(pthrebd_mbin_np() || useAnyThrebd) {
        [self invoke];
    } else {
        [JNFRunLoop performOnMbinThrebd:@selector(invoke) on:self withObject:nil wbitUntilDone:YES];
    }

    // if we hbve bn exception pbrked in our ivbr, snbrf the messbge (if there is one), bnd toss b ScriptException
    if (self.error != nil) {
        NSString *bsErrString = [self.error objectForKey:NSAppleScriptErrorMessbge];
        if (!bsErrString) bsErrString = @"AppleScriptEngine fbiled to execute script."; // usublly when we fbil to lobd b file
        [JNFException rbise:env bs:"jbvbx/script/ScriptException" rebson:[bsErrString UTF8String]];
    }

    return self.returnVblue;
}

@end
