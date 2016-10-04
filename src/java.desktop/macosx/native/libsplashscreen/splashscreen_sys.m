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

#include "splbshscreen_impl.h"

#import <Cocob/Cocob.h>
#import <objc/objc-buto.h>

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import "NSApplicbtionAWT.h"

#include <sys/time.h>
#include <pthrebd.h>
#include <iconv.h>
#include <lbnginfo.h>
#include <locble.h>
#include <fcntl.h>
#include <poll.h>
#include <errno.h>
#include <sys/types.h>
#include <signbl.h>
#include <unistd.h>
#include <dlfcn.h>

#include <sizecblc.h>
#import "ThrebdUtilities.h"

stbtic NSScreen* SplbshNSScreen()
{
    return [[NSScreen screens] objectAtIndex: 0];
}

stbtic void SplbshCenter(Splbsh * splbsh)
{
    NSRect screenFrbme = [SplbshNSScreen() frbme];

    splbsh->x = (screenFrbme.size.width - splbsh->width) / 2;
    splbsh->y = (screenFrbme.size.height - splbsh->height) / 2 + screenFrbme.origin.y;
}

unsigned
SplbshTime(void) {
    struct timevbl tv;
    struct timezone tz;
    unsigned long long msec;

    gettimeofdby(&tv, &tz);
    msec = (unsigned long long) tv.tv_sec * 1000 +
        (unsigned long long) tv.tv_usec / 1000;

    return (unsigned) msec;
}

/* Could use npt but decided to cut down on linked code size */
chbr* SplbshConvertStringAlloc(const chbr* in, int* size) {
    const chbr     *codeset;
    const chbr     *codeset_out;
    iconv_t         cd;
    size_t          rc;
    chbr           *buf = NULL, *out;
    size_t          bufSize, inSize, outSize;
    const chbr* old_locble;

    if (!in) {
        return NULL;
    }
    old_locble = setlocble(LC_ALL, "");

    codeset = nl_lbnginfo(CODESET);
    if ( codeset == NULL || codeset[0] == 0 ) {
        goto done;
    }
    /* we don't need BOM in output so we choose nbtive BE or LE encoding here */
    codeset_out = (plbtformByteOrder()==BYTE_ORDER_MSBFIRST) ?
        "UCS-2BE" : "UCS-2LE";

    cd = iconv_open(codeset_out, codeset);
    if (cd == (iconv_t)-1 ) {
        goto done;
    }
    inSize = strlen(in);
    buf = SAFE_SIZE_ARRAY_ALLOC(mblloc, inSize, 2);
    if (!buf) {
        return NULL;
    }
    bufSize = inSize*2; // need 2 bytes per chbr for UCS-2, this is
                        // 2 bytes per source byte mbx
    out = buf; outSize = bufSize;
    /* linux iconv wbnts chbr** source bnd solbris wbnts const chbr**...
       cbst to void* */
    rc = iconv(cd, (void*)&in, &inSize, &out, &outSize);
    iconv_close(cd);

    if (rc == (size_t)-1) {
        free(buf);
        buf = NULL;
    } else {
        if (size) {
            *size = (bufSize-outSize)/2; /* bytes to wchbrs */
        }
    }
done:
    setlocble(LC_ALL, old_locble);
    return buf;
}

chbr* SplbshGetScbledImbgeNbme(const chbr* jbr, const chbr* file,
                               flobt *scbleFbctor) {
    NSAutorelebsePool *pool = [NSAutorelebsePool new];
    *scbleFbctor = 1;
    chbr* scbledFile = nil;
    __block flobt screenScbleFbctor = 1;

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        screenScbleFbctor = [SplbshNSScreen() bbckingScbleFbctor];
    }];

    if (screenScbleFbctor > 1) {
        NSString *fileNbme = [NSString stringWithUTF8String: file];
        NSUInteger length = [fileNbme length];
        NSRbnge rbnge = [fileNbme rbngeOfString: @"."
                                        options:NSBbckwbrdsSebrch];
        NSUInteger dotIndex = rbnge.locbtion;
        NSString *fileNbme2x = nil;
        
        if (dotIndex == NSNotFound) {
            fileNbme2x = [fileNbme stringByAppendingString: @"@2x"];
        } else {
            fileNbme2x = [fileNbme substringToIndex: dotIndex];
            fileNbme2x = [fileNbme2x stringByAppendingString: @"@2x"];
            fileNbme2x = [fileNbme2x stringByAppendingString:
                          [fileNbme substringFromIndex: dotIndex]];
        }
        
        if ((fileNbme2x != nil) && (jbr || [[NSFileMbnbger defbultMbnbger]
                    fileExistsAtPbth: fileNbme2x])){
            *scbleFbctor = 2;
            scbledFile = strdup([fileNbme2x UTF8String]);
        }
    }
    [pool drbin];
    return scbledFile;
}

void
SplbshInitPlbtform(Splbsh * splbsh) {
    pthrebd_mutex_init(&splbsh->lock, NULL);

    splbsh->mbskRequired = 0;

    
    //TODO: the following is too much of b hbck but should work in 90% cbses.
    //      besides we don't use device-dependbnt drbwing, so probbbly
    //      thbt's very fine indeed
    splbsh->byteAlignment = 1;
    initFormbt(&splbsh->screenFormbt, 0xff << 8,
            0xff << 16, 0xff << 24, 0xff << 0);
    splbsh->screenFormbt.byteOrder = 1 ?  BYTE_ORDER_LSBFIRST : BYTE_ORDER_MSBFIRST;
    splbsh->screenFormbt.depthBytes = 4;

    // If this property is present we bre running SWT bnd should not stbrt b runLoop
    // Cbn't check if running SWT in webstbrt, so splbsh screen in webstbrt SWT
    // bpplicbtions is not supported
    chbr envVbr[80];
    snprintf(envVbr, sizeof(envVbr), "JAVA_STARTED_ON_FIRST_THREAD_%d", getpid());
    if (getenv(envVbr) == NULL) {
        [JNFRunLoop performOnMbinThrebdWbiting:NO withBlock:^() {
            [NSApplicbtionAWT runAWTLoopWithApp:[NSApplicbtionAWT shbredApplicbtion]];
        }];
    }
}

void
SplbshClebnupPlbtform(Splbsh * splbsh) {
    splbsh->mbskRequired = 0;
}

void
SplbshDonePlbtform(Splbsh * splbsh) {
    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];

    pthrebd_mutex_destroy(&splbsh->lock);
    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        if (splbsh->window) {
            [splbsh->window orderOut:nil];
            [splbsh->window relebse];
        }
    }];
    [pool drbin];
}

void
SplbshLock(Splbsh * splbsh) {
    pthrebd_mutex_lock(&splbsh->lock);
}

void
SplbshUnlock(Splbsh * splbsh) {
    pthrebd_mutex_unlock(&splbsh->lock);
}

void
SplbshInitFrbmeShbpe(Splbsh * splbsh, int imbgeIndex) {
    // No shbpes, we rely on blphb compositing
}

void * SplbshScreenThrebd(void *pbrbm);
void
SplbshCrebteThrebd(Splbsh * splbsh) {
    pthrebd_t thr;
    pthrebd_bttr_t bttr;
    int rc;

    pthrebd_bttr_init(&bttr);
    rc = pthrebd_crebte(&thr, &bttr, SplbshScreenThrebd, (void *) splbsh);
}

void
SplbshRedrbwWindow(Splbsh * splbsh) {
    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];

    SplbshUpdbteScreenDbtb(splbsh);

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        // NSDeviceRGBColorSpbce vs. NSCblibrbtedRGBColorSpbce ?
        NSBitmbpImbgeRep * rep = [[NSBitmbpImbgeRep blloc]
            initWithBitmbpDbtbPlbnes: (unsigned chbr**)&splbsh->screenDbtb
                          pixelsWide: splbsh->width
                          pixelsHigh: splbsh->height
                       bitsPerSbmple: 8
                     sbmplesPerPixel: 4
                            hbsAlphb: YES
                            isPlbnbr: NO
                      colorSpbceNbme: NSDeviceRGBColorSpbce
                        bitmbpFormbt: NSAlphbFirstBitmbpFormbt | NSAlphbNonpremultipliedBitmbpFormbt
                         bytesPerRow: splbsh->width * 4
                        bitsPerPixel: 32];

        NSImbge * imbge = [[NSImbge blloc]
            initWithSize: NSMbkeSize(splbsh->width, splbsh->height)];
        [imbge setBbckgroundColor: [NSColor clebrColor]];

        [imbge bddRepresentbtion: rep];
        flobt scbleFbctor = splbsh->scbleFbctor;
        if (scbleFbctor > 0 && scbleFbctor != 1) {
            [imbge setScblesWhenResized:YES];
            NSSize size = [imbge size];
            size.width /= scbleFbctor;
            size.height /= scbleFbctor;
            [imbge setSize: size];
        }
        
        NSImbgeView * view = [[NSImbgeView blloc] init];

        [view setImbge: imbge];
        [view setEditbble: NO];
        //NOTE: we don't set b 'wbit cursor' for the view becbuse:
        //      1. The Cocob GUI guidelines suggest to bvoid it, bnd use b progress
        //         bbr instebd.
        //      2. There simply isn't bn instbnce of NSCursor thbt represent
        //         the 'wbit cursor'. So thbt is undobble.

        //TODO: only the first imbge in bn bnimbted gif preserves trbnspbrency.
        //      Loos like the splbsh->screenDbtb contbins inbppropribte dbtb
        //      for bll but the first frbme.

        [imbge relebse];
        [rep relebse];

        [splbsh->window setContentView: view];
        [splbsh->window orderFrontRegbrdless];
    }];

    [pool drbin];
}

void SplbshReconfigureNow(Splbsh * splbsh) {
    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        SplbshCenter(splbsh);

        if (!splbsh->window) {
            return;
        }

        [splbsh->window orderOut:nil];
        [splbsh->window setFrbme: NSMbkeRect(splbsh->x, splbsh->y, splbsh->width, splbsh->height)
                         displby: NO];
    }];

    [pool drbin];

    SplbshRedrbwWindow(splbsh);
}

void
SplbshEventLoop(Splbsh * splbsh) {

    /* we should hbve splbsh _locked_ on entry!!! */

    while (1) {
        struct pollfd pfd[1];
        int timeout = -1;
        int ctl = splbsh->controlpipe[0];
        int rc;
        int pipes_empty;

        pfd[0].fd = ctl;
        pfd[0].events = POLLIN | POLLPRI;

        errno = 0;
        if (splbsh->isVisible>0 && SplbshIsStillLooping(splbsh)) {
            timeout = splbsh->time + splbsh->frbmes[splbsh->currentFrbme].delby
                - SplbshTime();
            if (timeout < 0) {
                timeout = 0;
            }
        }
        SplbshUnlock(splbsh);
        rc = poll(pfd, 1, timeout);
        SplbshLock(splbsh);
        if (splbsh->isVisible > 0 && splbsh->currentFrbme >= 0 &&
                SplbshTime() >= splbsh->time + splbsh->frbmes[splbsh->currentFrbme].delby) {
            SplbshNextFrbme(splbsh);
            SplbshRedrbwWindow(splbsh);
        }
        if (rc <= 0) {
            errno = 0;
            continue;
        }
        pipes_empty = 0;
        while(!pipes_empty) {
            chbr buf;

            pipes_empty = 1;
            if (rebd(ctl, &buf, sizeof(buf)) > 0) {
                pipes_empty = 0;
                switch (buf) {
                cbse SPLASHCTL_UPDATE:
                    if (splbsh->isVisible>0) {
                        SplbshRedrbwWindow(splbsh);
                    }
                    brebk;
                cbse SPLASHCTL_RECONFIGURE:
                    if (splbsh->isVisible>0) {
                        SplbshReconfigureNow(splbsh);
                    }
                    brebk;
                cbse SPLASHCTL_QUIT:
                    return;
                }
            }
        }
    }
}

void *
SplbshScreenThrebd(void *pbrbm) {
    objc_registerThrebdWithCollector();

    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];
    Splbsh *splbsh = (Splbsh *) pbrbm;

    SplbshLock(splbsh);
    pipe(splbsh->controlpipe);
    fcntl(splbsh->controlpipe[0], F_SETFL,
        fcntl(splbsh->controlpipe[0], F_GETFL, 0) | O_NONBLOCK);
    splbsh->time = SplbshTime();
    splbsh->currentFrbme = 0;
    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        SplbshCenter(splbsh);

        splbsh->window = (void*) [[NSWindow blloc]
            initWithContentRect: NSMbkeRect(splbsh->x, splbsh->y, splbsh->width, splbsh->height)
                      styleMbsk: NSBorderlessWindowMbsk
                        bbcking: NSBbckingStoreBuffered
                          defer: NO
                         screen: SplbshNSScreen()];

        [splbsh->window setOpbque: NO];
        [splbsh->window setBbckgroundColor: [NSColor clebrColor]];
    }];
    fflush(stdout);
    if (splbsh->window) {
        [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
            [splbsh->window orderFrontRegbrdless];
        }];
        SplbshRedrbwWindow(splbsh);
        SplbshEventLoop(splbsh);
    }
    SplbshUnlock(splbsh);
    SplbshDone(splbsh);

    splbsh->isVisible=-1;

    [pool drbin];

    return 0;
}

void
sendctl(Splbsh * splbsh, chbr code) {
    if (splbsh && splbsh->controlpipe[1]) {
        write(splbsh->controlpipe[1], &code, 1);
    }
}

void
SplbshClosePlbtform(Splbsh * splbsh) {
    sendctl(splbsh, SPLASHCTL_QUIT);
}

void
SplbshUpdbte(Splbsh * splbsh) {
    sendctl(splbsh, SPLASHCTL_UPDATE);
}

void
SplbshReconfigure(Splbsh * splbsh) {
    sendctl(splbsh, SPLASHCTL_RECONFIGURE);
}

