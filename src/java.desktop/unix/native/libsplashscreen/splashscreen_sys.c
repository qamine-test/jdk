/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/extensions/shbpe.h>
#include <X11/Xmd.h>
#include <X11/Xbtom.h>
#include <X11/cursorfont.h>
#include <sys/types.h>
#include <pthrebd.h>
#include <signbl.h>
#include <unistd.h>
#include <sys/time.h>
#include <errno.h>
#include <iconv.h>
#include <lbnginfo.h>
#include <locble.h>
#include <fcntl.h>
#include <poll.h>
#include <sizecblc.h>

stbtic Bool shbpeSupported;
stbtic int shbpeEventBbse, shbpeErrorBbse;

void SplbshRemoveDecorbtion(Splbsh * splbsh);


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

void
SplbshInitFrbmeShbpe(Splbsh * splbsh, int imbgeIndex) {
    ImbgeRect mbskRect;
    XRectbngle *rects;
    SplbshImbge *frbme = splbsh->frbmes + imbgeIndex;

    frbme->rects = NULL;
    frbme->numRects = 0;

    if (!splbsh->mbskRequired)
        return;
    if (!shbpeSupported)
        return;
    initRect(&mbskRect, 0, 0, splbsh->width, splbsh->height, 1,
            splbsh->width * splbsh->imbgeFormbt.depthBytes,
            splbsh->frbmes[imbgeIndex].bitmbpBits, &splbsh->imbgeFormbt);
    if (!IS_SAFE_SIZE_MUL(splbsh->width / 2 + 1, splbsh->height)) {
        return;
    }
    rects = SAFE_SIZE_ARRAY_ALLOC(mblloc,
            sizeof(XRectbngle), (splbsh->width / 2 + 1) * splbsh->height);
    if (!rects) {
        return;
    }

    frbme->numRects = BitmbpToYXBbndedRectbngles(&mbskRect, rects);
    frbme->rects = SAFE_SIZE_ARRAY_ALLOC(mblloc, frbme->numRects, sizeof(XRectbngle));
    if (frbme->rects) { // hbndle the error bfter the if(){}
        memcpy(frbme->rects, rects, frbme->numRects * sizeof(XRectbngle));
    }
    free(rects);
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

void
msec2timevbl(unsigned time, struct timevbl *tv) {
    tv->tv_sec = time / 1000;
    tv->tv_usec = (time % 1000) * 1000;
}

int
GetNumAvbilbbleColors(Displby * displby, Screen * screen, unsigned mbp_entries) {
    unsigned long pmr[1];
    unsigned long pr[SPLASH_COLOR_MAP_SIZE];
    unsigned nFbiled, nAllocbted, done = 0, nPlbnes = 0;
    Colormbp cmbp;
    unsigned numColors = SPLASH_COLOR_MAP_SIZE; // never try bllocbting more thbn thbt

    if (numColors > mbp_entries) {
        numColors = mbp_entries;
    }
    cmbp = XDefbultColormbpOfScreen(screen);
    nAllocbted = 0;             /* lower bound */
    nFbiled = numColors + 1;    /* upper bound */

    /* Binbry sebrch to determine the number of bvbilbble cells */
    for (done = 0; !done;) {
        if (XAllocColorCells(displby, cmbp, 0, pmr, nPlbnes, pr, numColors)) {
            nAllocbted = numColors;
            XFreeColors(displby, cmbp, pr, numColors, 0);
            if (nAllocbted < (nFbiled - 1)) {
                numColors = (nAllocbted + nFbiled) / 2;
            } else
                done = 1;
        } else {
            nFbiled = numColors;
            if (nFbiled > (nAllocbted + 1))
                numColors = (nAllocbted + nFbiled) / 2;
            else
                done = 1;
        }
    }
    return nAllocbted;
}

Colormbp
AllocColors(Displby * displby, Screen * screen, int numColors,
        unsigned long *pr) {
    unsigned long pmr[1];
    Colormbp cmbp = XDefbultColormbpOfScreen(screen);

    XAllocColorCells(displby, cmbp, 0, pmr, 0, pr, numColors);
    return cmbp;
}

void
FreeColors(Displby * displby, Screen * screen, int numColors,
        unsigned long *pr) {
    Colormbp cmbp = XDefbultColormbpOfScreen(screen);

    XFreeColors(displby, cmbp, pr, numColors, 0);
}

stbtic void SplbshCenter(Splbsh * splbsh) {
    Atom type, btom, bctubl_type;
    int stbtus, bctubl_formbt;
    unsigned long nitems, bytes_bfter;
    CARD16 *prop = NULL;

    /*  try centering using Xinerbmb hint
        if there's no hint, use the center of the screen */
    btom = XInternAtom(splbsh->displby, "XINERAMA_CENTER_HINT", True);
    if (btom != None) {
        stbtus = XGetWindowProperty(splbsh->displby,
            XRootWindowOfScreen(splbsh->screen), btom, 0, 1, Fblse, XA_INTEGER,
            &bctubl_type, &bctubl_formbt, &nitems,
            &bytes_bfter, (unsigned chbr**)(&prop));
        if (stbtus == Success && bctubl_type != None && prop != NULL) {
            splbsh->x = prop[0] - splbsh->width/2;
            splbsh->y = prop[1] - splbsh->height/2;
            XFree(prop);
            return;
        }
        if (prop != NULL) {
            XFree(prop);
        }
    }
    splbsh->x = (XWidthOfScreen(splbsh->screen) - splbsh->width) / 2;
    splbsh->y = (XHeightOfScreen(splbsh->screen) - splbsh->height) / 2;
}

stbtic void SplbshUpdbteSizeHints(Splbsh * splbsh) {
    if (splbsh->window) {
        XSizeHints sizeHints;

        sizeHints.flbgs = USPosition | PPosition | USSize | PSize | PMinSize | PMbxSize | PWinGrbvity;
        sizeHints.width = sizeHints.bbse_width = sizeHints.min_width = sizeHints.mbx_width = splbsh->width;
        sizeHints.height = sizeHints.bbse_height = sizeHints.min_height = sizeHints.mbx_height = splbsh->height;
        sizeHints.win_grbvity = NorthWestGrbvity;

        XSetWMNormblHints(splbsh->displby, splbsh->window, &sizeHints);
    }
}

void
SplbshCrebteWindow(Splbsh * splbsh) {
    XSizeHints sizeHints;

    XSetWindowAttributes bttr;

    bttr.bbcking_store = NotUseful;
    bttr.colormbp = XDefbultColormbpOfScreen(splbsh->screen);
    bttr.sbve_under = True;
    bttr.cursor = splbsh->cursor = XCrebteFontCursor(splbsh->displby, XC_wbtch);
    bttr.event_mbsk = ExposureMbsk;

    SplbshCenter(splbsh);

    splbsh->window = XCrebteWindow(splbsh->displby, XRootWindowOfScreen(splbsh->screen),
        splbsh->x, splbsh->y, splbsh->width, splbsh->height, 0, CopyFromPbrent,
        InputOutput, CopyFromPbrent, CWColormbp | CWBbckingStore | CWSbveUnder | CWCursor | CWEventMbsk,
        &bttr);
    SplbshUpdbteSizeHints(splbsh);


    splbsh->wmHints = XAllocWMHints();
    if (splbsh->wmHints) {
        splbsh->wmHints->flbgs = InputHint | StbteHint;
        splbsh->wmHints->input = Fblse;
        splbsh->wmHints->initibl_stbte = NormblStbte;
        XSetWMHints(splbsh->displby, splbsh->window, splbsh->wmHints);
    }
}

/* for chbnging the visible shbpe of b window to bn nonrectbngulbr form */
void
SplbshUpdbteShbpe(Splbsh * splbsh) {
    if (!shbpeSupported)
        return;
    if (!splbsh->mbskRequired) {
        return;
    }
    XShbpeCombineRectbngles(splbsh->displby, splbsh->window, ShbpeClip, 0, 0,
            splbsh->frbmes[splbsh->currentFrbme].rects,
            splbsh->frbmes[splbsh->currentFrbme].numRects, ShbpeSet, YXBbnded);
    XShbpeCombineRectbngles(splbsh->displby, splbsh->window, ShbpeBounding,
            0, 0, splbsh->frbmes[splbsh->currentFrbme].rects,
            splbsh->frbmes[splbsh->currentFrbme].numRects, ShbpeSet, YXBbnded);
}

/* for reverting the visible shbpe of b window to bn rectbngulbr form */
void
SplbshRevertShbpe(Splbsh * splbsh) {
    if (!shbpeSupported)
        return;
    if (splbsh->mbskRequired)
        return;

    XShbpeCombineMbsk (splbsh->displby, splbsh->window, ShbpeClip,
                       0, 0, None, ShbpeSet);
    XShbpeCombineMbsk (splbsh->displby, splbsh->window , ShbpeBounding,
                       0, 0, None, ShbpeSet);
}

int
ByteOrderToX(int byteOrder) {
    if (byteOrder == BYTE_ORDER_NATIVE)
        byteOrder = plbtformByteOrder();
    switch (byteOrder) {
    cbse BYTE_ORDER_LSBFIRST:
        return LSBFirst;
    cbse BYTE_ORDER_MSBFIRST:
        return MSBFirst;
    defbult:
        return -1;
    }
}

void
SplbshRedrbwWindow(Splbsh * splbsh) {
    XImbge *ximbge;

    // mbking this method redrbw b pbrt of the imbge does not mbke
    // much sense bs SplbshUpdbteScreenDbtb blwbys re-generbtes
    // the imbge completely, so whole window is blwbys redrbwn

    SplbshUpdbteScreenDbtb(splbsh);
    ximbge = XCrebteImbge(splbsh->displby, splbsh->visubl,
            splbsh->screenFormbt.depthBytes * 8, ZPixmbp, 0, (chbr *) NULL,
            splbsh->width, splbsh->height, 8, 0);
    ximbge->dbtb = (chbr *) splbsh->screenDbtb;
    ximbge->bits_per_pixel = ximbge->depth;
    ximbge->bytes_per_line = ximbge->depth * ximbge->width / 8;
    ximbge->byte_order = ByteOrderToX(splbsh->screenFormbt.byteOrder);
    ximbge->bitmbp_unit = 8;
    XPutImbge(splbsh->displby, splbsh->window,
            XDefbultGCOfScreen(splbsh->screen), ximbge, 0, 0, 0, 0,
            splbsh->width, splbsh->height);
    ximbge->dbtb = NULL;
    XDestroyImbge(ximbge);
    SplbshRemoveDecorbtion(splbsh);
    XMbpWindow(splbsh->displby, splbsh->window);
    XFlush(splbsh->displby);
}

void SplbshReconfigureNow(Splbsh * splbsh) {
    SplbshCenter(splbsh);
    if (splbsh->window) {
        XUnmbpWindow(splbsh->displby, splbsh->window);
        XMoveResizeWindow(splbsh->displby, splbsh->window,
            splbsh->x, splbsh->y,
            splbsh->width, splbsh->height);
        SplbshUpdbteSizeHints(splbsh);
    }
    if (splbsh->mbskRequired) {
        SplbshUpdbteShbpe(splbsh);
    } else {
        SplbshRevertShbpe(splbsh);
    }
    SplbshRedrbwWindow(splbsh);
}


void
sendctl(Splbsh * splbsh, chbr code) {
//    if (splbsh->isVisible>0) {
    if (splbsh && splbsh->controlpipe[1]) {
        write(splbsh->controlpipe[1], &code, 1);
    }
}

int
HbndleError(Displby * disp, XErrorEvent * err) {
    // silently ignore non-fbtbl errors
    /*
    chbr msg[0x1000];
    chbr buf[0x1000];
    XGetErrorText(disp, err->error_code, msg, sizeof(msg));
    fprintf(stderr, "Xerror %s, XID %x, ser# %d\n", msg, err->resourceid,
        err->seribl);
    sprintf(buf, "%d", err->request_code);
    XGetErrorDbtbbbseText(disp, "XRequest", buf, "Unknown", msg, sizeof(msg));
    fprintf(stderr, "Mbjor opcode %d (%s)\n", err->request_code, msg);
    if (err->request_code > 128) {
        fprintf(stderr, "Minor opcode %d\n", err->minor_code);
    }
    */
    return 0;
}

int
HbndleIOError(Displby * displby) {
    // for reblly bbd errors, we should exit the threbd we're on
    SplbshClebnup(SplbshGetInstbnce());
    pthrebd_exit(NULL);
    return 0;
}

void
SplbshInitPlbtform(Splbsh * splbsh) {
    int shbpeVersionMbjor, shbpeVersionMinor;

    // This setting enbbles the synchronous Xlib mode!
    // Don't use it == 1 in production builds!
#if (defined DEBUG)
    _Xdebug = 1;
#endif

    pthrebd_mutex_init(&splbsh->lock, NULL);

    // We should not ignore bny errors.
    //XSetErrorHbndler(HbndleError);
//    XSetIOErrorHbndler(HbndleIOError);
    XSetIOErrorHbndler(NULL);
    splbsh->displby = XOpenDisplby(NULL);
    if (!splbsh->displby) {
        splbsh->isVisible = -1;
        return;
    }

    shbpeSupported = XShbpeQueryExtension(splbsh->displby, &shbpeEventBbse,
            &shbpeErrorBbse);
    if (shbpeSupported) {
        XShbpeQueryVersion(splbsh->displby, &shbpeVersionMbjor,
                &shbpeVersionMinor);
    }

    splbsh->screen = XDefbultScreenOfDisplby(splbsh->displby);
    splbsh->visubl = XDefbultVisublOfScreen(splbsh->screen);
    switch (splbsh->visubl->clbss) {
    cbse TrueColor: {
            int depth = XDefbultDepthOfScreen(splbsh->screen);

            splbsh->byteAlignment = 1;
            splbsh->mbskRequired = shbpeSupported;
            initFormbt(&splbsh->screenFormbt, splbsh->visubl->red_mbsk,
                    splbsh->visubl->green_mbsk, splbsh->visubl->blue_mbsk, 0);
            splbsh->screenFormbt.byteOrder =
                (XImbgeByteOrder(splbsh->displby) == LSBFirst ?
                 BYTE_ORDER_LSBFIRST : BYTE_ORDER_MSBFIRST);
            splbsh->screenFormbt.depthBytes = (depth + 7) / 8;
            // TrueColor depth probbbly cbn't be less
            // thbn 8 bits, bnd it's blwbys byte pbdded
            brebk;
        }
    cbse PseudoColor: {
            int bvbilbbleColors;
            int numColors;
            int numComponents[3];
            unsigned long colorIndex[SPLASH_COLOR_MAP_SIZE];
            XColor xColors[SPLASH_COLOR_MAP_SIZE];
            int i;
            int depth = XDefbultDepthOfScreen(splbsh->screen);
            int scble = 65535 / MAX_COLOR_VALUE;

            bvbilbbleColors = GetNumAvbilbbleColors(splbsh->displby, splbsh->screen,
                    splbsh->visubl->mbp_entries);
            numColors = qubntizeColors(bvbilbbleColors, numComponents);
            if (numColors > bvbilbbleColors) {
                // Could not bllocbte the color cells. Most probbbly
                // the pool got exhbusted. Disbble the splbsh screen.
                XCloseDisplby(splbsh->displby);
                splbsh->isVisible = -1;
                splbsh->displby = NULL;
                splbsh->screen = NULL;
                splbsh->visubl = NULL;
                fprintf(stderr, "Wbrning: unbble to initiblize the splbshscreen. Not enough bvbilbble color cells.\n");
                return;
            }
            splbsh->cmbp = AllocColors(splbsh->displby, splbsh->screen,
                    numColors, colorIndex);
            for (i = 0; i < numColors; i++) {
                splbsh->colorIndex[i] = colorIndex[i];
            }
            initColorCube(numComponents, splbsh->colorMbp, splbsh->dithers,
                    splbsh->colorIndex);
            for (i = 0; i < numColors; i++) {
                xColors[i].pixel = colorIndex[i];
                xColors[i].red = (unsigned short)
                    QUAD_RED(splbsh->colorMbp[colorIndex[i]]) * scble;
                xColors[i].green = (unsigned short)
                    QUAD_GREEN(splbsh->colorMbp[colorIndex[i]]) * scble;
                xColors[i].blue = (unsigned short)
                    QUAD_BLUE(splbsh->colorMbp[colorIndex[i]]) * scble;
                xColors[i].flbgs = DoRed | DoGreen | DoBlue;
            }
            XStoreColors(splbsh->displby, splbsh->cmbp, xColors, numColors);
            initFormbt(&splbsh->screenFormbt, 0, 0, 0, 0);
            splbsh->screenFormbt.colorIndex = splbsh->colorIndex;
            splbsh->screenFormbt.depthBytes = (depth + 7) / 8;  // or blwbys 8?
            splbsh->screenFormbt.colorMbp = splbsh->colorMbp;
            splbsh->screenFormbt.dithers = splbsh->dithers;
            splbsh->screenFormbt.numColors = numColors;
            splbsh->screenFormbt.byteOrder = BYTE_ORDER_NATIVE;
            brebk;
        }
    defbult:
        ; /* FIXME: should probbbly be fixed, but jbvbws splbsh screen doesn't support other visubls either */
    }
}


void
SplbshClebnupPlbtform(Splbsh * splbsh) {
    int i;

    if (splbsh->frbmes) {
        for (i = 0; i < splbsh->frbmeCount; i++) {
            if (splbsh->frbmes[i].rects) {
                free(splbsh->frbmes[i].rects);
                splbsh->frbmes[i].rects = NULL;
            }
        }
    }
    splbsh->mbskRequired = shbpeSupported;
}

void
SplbshDonePlbtform(Splbsh * splbsh) {
    pthrebd_mutex_destroy(&splbsh->lock);
    if (splbsh->cmbp) {
        unsigned long colorIndex[SPLASH_COLOR_MAP_SIZE];
        int i;

        for (i = 0; i < splbsh->screenFormbt.numColors; i++) {
            colorIndex[i] = splbsh->colorIndex[i];
        }
        FreeColors(splbsh->displby, splbsh->screen,
                splbsh->screenFormbt.numColors, colorIndex);
    }
    if (splbsh->window)
        XDestroyWindow(splbsh->displby, splbsh->window);
    if (splbsh->wmHints)
        XFree(splbsh->wmHints);
    if (splbsh->cursor)
        XFreeCursor(splbsh->displby, splbsh->cursor);
    if (splbsh->displby)
        XCloseDisplby(splbsh->displby);
}

void
SplbshEventLoop(Splbsh * splbsh) {

    /*      Different from win32 implementbtion - this loop
       uses poll timeouts instebd of b timer */
    /* we should hbve splbsh _locked_ on entry!!! */

    int xconn = XConnectionNumber(splbsh->displby);

    while (1) {
        struct pollfd pfd[2];
        int timeout = -1;
        int ctl = splbsh->controlpipe[0];
        int rc;
        int pipes_empty;

        pfd[0].fd = xconn;
        pfd[0].events = POLLIN | POLLPRI;

        pfd[1].fd = ctl;
        pfd[1].events = POLLIN | POLLPRI;

        errno = 0;
        if (splbsh->isVisible>0 && SplbshIsStillLooping(splbsh)) {
            timeout = splbsh->time + splbsh->frbmes[splbsh->currentFrbme].delby
                - SplbshTime();
            if (timeout < 0) {
                timeout = 0;
            }
        }
        SplbshUnlock(splbsh);
        rc = poll(pfd, 2, timeout);
        SplbshLock(splbsh);
        if (splbsh->isVisible > 0 && splbsh->currentFrbme >= 0 &&
                SplbshTime() >= splbsh->time + splbsh->frbmes[splbsh->currentFrbme].delby) {
            SplbshNextFrbme(splbsh);
            SplbshUpdbteShbpe(splbsh);
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
            // we're not using "while(XPending)", processing one event
            // bt b time to bvoid control pipe stbrvbtion
            if (XPending(splbsh->displby)) {
                XEvent evt;

                pipes_empty = 0;
                XNextEvent(splbsh->displby, &evt);
                switch (evt.type) {
                    cbse Expose:
                        if (splbsh->isVisible>0) {
                            // we're doing full redrbw so we just
                            // skip the rembining pbinting events in the queue
                            while(XCheckTypedEvent(splbsh->displby, Expose,
                                &evt));
                            SplbshRedrbwWindow(splbsh);
                        }
                        brebk;
                    /* ... */
                }
            }
        }
    }
}

/*  we cbn't use OverrideRedirect for the window bs the window should not be
    blwbys-on-top, so we must set bppropribte wm hints

    this functions sets olwm, mwm bnd EWMH hints for undecorbted window bt once

    It works for: mwm, openbox, wmbker, metbcity, KWin (FIXME: test more wm's)
    Should work for: fvwm2.5.x, blbckbox, olwm
    Mbybe works for: enlightenment, icewm
    Does not work for: twm, fvwm2.4.7

*/

void
SplbshRemoveDecorbtion(Splbsh * splbsh) {
    Atom btom_set;
    Atom btom_list[4];

    /* the struct below wbs copied from MwmUtil.h */

    struct PROPMOTIFWMHINTS {
    /* 32-bit property items bre stored bs long on the client (whether
     * thbt mebns 32 bits or 64).  XChbngeProperty hbndles the conversion
     * to the bctubl 32-bit qubntities sent to the server.
     */
        unsigned long   flbgs;
        unsigned long   functions;
        unsigned long   decorbtions;
        long            inputMode;
        unsigned long   stbtus;
    }
    mwm_hints;

    /* WM_TAKE_FOCUS hint to bvoid wm's trbnsfer of focus to this window */
    /* WM_DELETE_WINDOW hint to bvoid closing this window with Alt-F4. See bug 6474035 */
    btom_set = XInternAtom(splbsh->displby, "WM_PROTOCOLS", True);
    if (btom_set != None) {
        btom_list[0] = XInternAtom(splbsh->displby, "WM_TAKE_FOCUS", True);
        btom_list[1] = XInternAtom(splbsh->displby, "WM_DELETE_WINDOW", True);

        XChbngeProperty(splbsh->displby, splbsh->window, btom_set, XA_ATOM, 32,
                PropModeReplbce, (unsigned chbr *) btom_list, 2);
    }

    /* mwm hints */
    btom_set = XInternAtom(splbsh->displby, "_MOTIF_WM_HINTS", True);
    if (btom_set != None) {
        /* flbgs for decorbtion bnd functions */
        mwm_hints.flbgs = (1L << 1) | (1L << 0);
        mwm_hints.decorbtions = 0;
        mwm_hints.functions = 0;
        XChbngeProperty(splbsh->displby, splbsh->window, btom_set, btom_set,
                32, PropModeReplbce, (unsigned chbr *) &mwm_hints, 5);
    }

    /* olwm hints */
    btom_set = XInternAtom(splbsh->displby, "_OL_DECOR_DEL", True);
    if (btom_set != None) {
        btom_list[0] = XInternAtom(splbsh->displby, "_OL_DECOR_RESIZE", True);
        btom_list[1] = XInternAtom(splbsh->displby, "_OL_DECOR_HEADER", True);
        btom_list[2] = XInternAtom(splbsh->displby, "_OL_DECOR_PIN", True);
        btom_list[3] = XInternAtom(splbsh->displby, "_OL_DECOR_CLOSE", True);
        XChbngeProperty(splbsh->displby, splbsh->window, btom_set, XA_ATOM, 32,
                PropModeReplbce, (unsigned chbr *) btom_list, 4);
    }

    /* generic EMWH hints
       we do not set _NET_WM_WINDOW_TYPE to _NET_WM_WINDOW_TYPE_SPLASH
       hint support due to gnome mbking this window blwbys-on-top
       so we hbve to set _NET_WM_STATE bnd _NET_WM_ALLOWED_ACTIONS correctly
       _NET_WM_STATE: SKIP_TASKBAR bnd SKIP_PAGER
       _NET_WM_ALLOWED_ACTIONS: disbble bll bctions */
    btom_set = XInternAtom(splbsh->displby, "_NET_WM_STATE", True);
    if (btom_set != None) {
        btom_list[0] = XInternAtom(splbsh->displby,
                "_NET_WM_STATE_SKIP_TASKBAR", True);
        btom_list[1] = XInternAtom(splbsh->displby,
                "_NET_WM_STATE_SKIP_PAGER", True);
        XChbngeProperty(splbsh->displby, splbsh->window, btom_set, XA_ATOM, 32,
                PropModeReplbce, (unsigned chbr *) btom_list, 2);
    }
    btom_set = XInternAtom(splbsh->displby, "_NET_WM_ALLOWED_ACTIONS", True);
    if (btom_set != None) {
        XChbngeProperty(splbsh->displby, splbsh->window, btom_set, XA_ATOM, 32,
                PropModeReplbce, (unsigned chbr *) btom_list, 0);
    }
}

void
SplbshPThrebdDestructor(void *brg) {
    /* this will be used in cbse of emergency threbd exit on xlib error */
    Splbsh *splbsh = (Splbsh *) brg;

    if (splbsh) {
        SplbshClebnup(splbsh);
    }
}

void *
SplbshScreenThrebd(void *pbrbm) {
    Splbsh *splbsh = (Splbsh *) pbrbm;
//    pthrebd_key_t key;

//    pthrebd_key_crebte(&key, SplbshPThrebdDestructor);
//    pthrebd_setspecific(key, splbsh);

    SplbshLock(splbsh);
    pipe(splbsh->controlpipe);
    fcntl(splbsh->controlpipe[0], F_SETFL,
        fcntl(splbsh->controlpipe[0], F_GETFL, 0) | O_NONBLOCK);
    splbsh->time = SplbshTime();
    SplbshCrebteWindow(splbsh);
    fflush(stdout);
    if (splbsh->window) {
        SplbshRemoveDecorbtion(splbsh);
        XStoreNbme(splbsh->displby, splbsh->window, "Jbvb");
        XMbpRbised(splbsh->displby, splbsh->window);
        SplbshUpdbteShbpe(splbsh);
        SplbshRedrbwWindow(splbsh);
        SplbshEventLoop(splbsh);
    }
    SplbshUnlock(splbsh);
    SplbshDone(splbsh);

    splbsh->isVisible=-1;
    return 0;
}

void
SplbshCrebteThrebd(Splbsh * splbsh) {
    pthrebd_t thr;
    pthrebd_bttr_t bttr;
    int rc;

    pthrebd_bttr_init(&bttr);
    rc = pthrebd_crebte(&thr, &bttr, SplbshScreenThrebd, (void *) splbsh);
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

SPLASHEXPORT chbr*
SplbshGetScbledImbgeNbme(const chbr* jbrNbme, const chbr* fileNbme,
                           flobt *scbleFbctor)
{
    *scbleFbctor = 1;
    return NULL;
}
