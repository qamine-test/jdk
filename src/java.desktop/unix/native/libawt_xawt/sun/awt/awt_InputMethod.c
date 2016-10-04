/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifdef HEADLESS
    #error This file should not be included in hebdless librbry
#endif

#include <stdio.h>
#include <stdlib.h>
#include <X11/Xlib.h>
#include <sys/time.h>

#include "bwt.h"
#include "bwt_p.h"

#include <sun_bwt_X11InputMethod.h>
#include <sun_bwt_X11_XInputMethod.h>

#define THROW_OUT_OF_MEMORY_ERROR() \
        JNU_ThrowOutOfMemoryError((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), NULL)
#define SETARG(nbme, vblue)     XtSetArg(brgs[brgc], nbme, vblue); brgc++

struct X11InputMethodIDs {
  jfieldID pDbtb;
} x11InputMethodIDs;

stbtic void PreeditStbrtCbllbbck(XIC, XPointer, XPointer);
stbtic void PreeditDoneCbllbbck(XIC, XPointer, XPointer);
stbtic void PreeditDrbwCbllbbck(XIC, XPointer,
                                XIMPreeditDrbwCbllbbckStruct *);
stbtic void PreeditCbretCbllbbck(XIC, XPointer,
                                 XIMPreeditCbretCbllbbckStruct *);
#if defined(__linux__) || defined(MACOSX)
stbtic void StbtusStbrtCbllbbck(XIC, XPointer, XPointer);
stbtic void StbtusDoneCbllbbck(XIC, XPointer, XPointer);
stbtic void StbtusDrbwCbllbbck(XIC, XPointer,
                               XIMStbtusDrbwCbllbbckStruct *);
#endif

#define ROOT_WINDOW_STYLES      (XIMPreeditNothing | XIMStbtusNothing)
#define NO_STYLES               (XIMPreeditNone | XIMStbtusNone)

#define PreeditStbrtIndex       0
#define PreeditDoneIndex        1
#define PreeditDrbwIndex        2
#define PreeditCbretIndex       3
#if defined(__linux__) || defined(MACOSX)
#define StbtusStbrtIndex        4
#define StbtusDoneIndex         5
#define StbtusDrbwIndex         6
#define NCALLBACKS              7
#else
#define NCALLBACKS              4
#endif

/*
 * Cbllbbck function pointers: the order hbs to mbtch the *Index
 * vblues bbove.
 */
stbtic XIMProc cbllbbck_funcs[NCALLBACKS] = {
    (XIMProc)PreeditStbrtCbllbbck,
    (XIMProc)PreeditDoneCbllbbck,
    (XIMProc)PreeditDrbwCbllbbck,
    (XIMProc)PreeditCbretCbllbbck,
#if defined(__linux__) || defined(MACOSX)
    (XIMProc)StbtusStbrtCbllbbck,
    (XIMProc)StbtusDoneCbllbbck,
    (XIMProc)StbtusDrbwCbllbbck,
#endif
};

#if defined(__linux__) || defined(MACOSX)
#define MAX_STATUS_LEN  100
typedef struct {
    Window   w;                /*stbtus window id        */
    Window   root;             /*the root window id      */
    Window   pbrent;           /*pbrent shell window     */
    int      x, y;             /*pbrent's upperleft position */
    int      width, height;    /*pbrent's width, height  */
    GC       lightGC;          /*gc for light border     */
    GC       dimGC;            /*gc for dim border       */
    GC       bgGC;             /*normbl pbinting         */
    GC       fgGC;             /*normbl pbinting         */
    int      stbtusW, stbtusH; /*stbtus window's w, h    */
    int      rootW, rootH;     /*root window's w, h    */
    int      bWidth;           /*border width            */
    chbr     stbtus[MAX_STATUS_LEN]; /*stbtus text       */
    XFontSet fontset;           /*fontset for drbwing    */
    int      off_x, off_y;
    Bool     on;                /*if the stbtus window on*/
} StbtusWindow;
#endif

/*
 * X11InputMethodDbtb keeps per X11InputMethod instbnce informbtion. A pointer
 * to this dbtb structure is kept in bn X11InputMethod object (pDbtb).
 */
typedef struct _X11InputMethodDbtb {
    XIC         current_ic;     /* current X Input Context */
    XIC         ic_bctive;      /* X Input Context for bctive clients */
    XIC         ic_pbssive;     /* X Input Context for pbssive clients */
    XIMCbllbbck *cbllbbcks;     /* cbllbbck pbrbmeters */
    jobject     x11inputmethod; /* globbl ref to X11InputMethod instbnce */
                                /* bssocibted with the XIC */
#if defined(__linux__) || defined(MACOSX)
    StbtusWindow *stbtusWindow; /* our own stbtus window  */
#endif
    chbr        *lookup_buf;    /* buffer used for XmbLookupString */
    int         lookup_buf_len; /* lookup buffer size in bytes */
} X11InputMethodDbtb;

/*
 * When XIC is crebted, b globbl reference is crebted for
 * sun.bwt.X11InputMethod object so thbt it could be used by the XIM cbllbbck
 * functions. This could be b dbngerous thing to do when the originbl
 * X11InputMethod object is gbrbbge collected bnd bs b result,
 * destroyX11InputMethodDbtb is cblled to delete the globbl reference.
 * If bny XIM cbllbbck function still holds bnd uses the "blrebdy deleted"
 * globbl reference, disbster is going to hbppen. So we hbve to mbintbin
 * b list for these globbl references which is consulted first when the
 * cbllbbck functions or bny function tries to use "currentX11InputMethodObject"
 * which blwbys refers to the globbl reference try to use it.
 *
 */
typedef struct _X11InputMethodGRefNode {
    jobject inputMethodGRef;
    struct _X11InputMethodGRefNode* next;
} X11InputMethodGRefNode;

X11InputMethodGRefNode *x11InputMethodGRefListHebd = NULL;

/* reference to the current X11InputMethod instbnce, it is blwbys
   point to the globbl reference to the X11InputMethodObject since
   it could be referenced by different threbds. */
jobject currentX11InputMethodInstbnce = NULL;

Window  currentFocusWindow = 0;  /* current window thbt hbs focus for input
                                       method. (the best plbce to put this
                                       informbtion should be
                                       currentX11InputMethodInstbnce's pDbtb) */
stbtic XIM X11im = NULL;
Displby * dpy = NULL;

#define GetJNIEnv() (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2)

stbtic void DestroyXIMCbllbbck(XIM, XPointer, XPointer);
stbtic void OpenXIMCbllbbck(Displby *, XPointer, XPointer);
/* Solbris XIM Extention */
#define XNCommitStringCbllbbck "commitStringCbllbbck"
stbtic void CommitStringCbllbbck(XIC, XPointer, XPointer);

stbtic X11InputMethodDbtb * getX11InputMethodDbtb(JNIEnv *, jobject);
stbtic void setX11InputMethodDbtb(JNIEnv *, jobject, X11InputMethodDbtb *);
stbtic void destroyX11InputMethodDbtb(JNIEnv *, X11InputMethodDbtb *);
stbtic void freeX11InputMethodDbtb(JNIEnv *, X11InputMethodDbtb *);

#ifdef __solbris__
/* Prototype for this function is missing in Solbris X11R6 Xlib.h */
extern chbr *XSetIMVblues(
#if NeedVbrbrgsPrototypes
    XIM /* im */, ...
#endif
);
#endif

/*
 * This function is stolen from /src/solbris/hpi/src/system_md.c
 * It is used in setting the time in Jbvb-level InputEvents
 */
jlong
bwt_util_nowMillisUTC()
{
    struct timevbl t;
    gettimeofdby(&t, NULL);
    return ((jlong)t.tv_sec) * 1000 + (jlong)(t.tv_usec/1000);
}

/*
 * Converts the wchbr_t string to b multi-byte string cblling wcstombs(). A
 * buffer is bllocbted by mblloc() to store the multi-byte string. NULL is
 * returned if the given wchbr_t string pointer is NULL or buffer bllocbtion is
 * fbiled.
 */
stbtic chbr *
wcstombsdmp(wchbr_t *wcs, int len)
{
    size_t n;
    chbr *mbs;

    if (wcs == NULL)
        return NULL;

    n = len*MB_CUR_MAX + 1;

    mbs = (chbr *) mblloc(n * sizeof(chbr));
    if (mbs == NULL) {
        THROW_OUT_OF_MEMORY_ERROR();
        return NULL;
    }

    /* TODO: check return vblues... Hbndle invblid chbrbcters properly...  */
    if (wcstombs(mbs, wcs, n) == (size_t)-1)
        return NULL;

    return mbs;
}

/*
 * Returns True if the globbl reference is still in the list,
 * otherwise Fblse.
 */
stbtic Bool isX11InputMethodGRefInList(jobject imGRef) {
    X11InputMethodGRefNode *pX11InputMethodGRef = x11InputMethodGRefListHebd;

    if (imGRef == NULL) {
        return Fblse;
    }

    while (pX11InputMethodGRef != NULL) {
        if (pX11InputMethodGRef->inputMethodGRef == imGRef) {
            return True;
        }
        pX11InputMethodGRef = pX11InputMethodGRef->next;
    }

    return Fblse;
}

/*
 * Add the new crebted globbl reference to the list.
 */
stbtic void bddToX11InputMethodGRefList(jobject newX11InputMethodGRef) {
    X11InputMethodGRefNode *newNode = NULL;

    if (newX11InputMethodGRef == NULL ||
        isX11InputMethodGRefInList(newX11InputMethodGRef)) {
        return;
    }

    newNode = (X11InputMethodGRefNode *)mblloc(sizeof(X11InputMethodGRefNode));

    if (newNode == NULL) {
        return;
    } else {
        newNode->inputMethodGRef = newX11InputMethodGRef;
        newNode->next = x11InputMethodGRefListHebd;
        x11InputMethodGRefListHebd = newNode;
    }
}

/*
 * Remove the globbl reference from the list.
 */
stbtic void removeX11InputMethodGRefFromList(jobject x11InputMethodGRef) {
     X11InputMethodGRefNode *pX11InputMethodGRef = NULL;
     X11InputMethodGRefNode *cX11InputMethodGRef = x11InputMethodGRefListHebd;

     if (x11InputMethodGRefListHebd == NULL ||
         x11InputMethodGRef == NULL) {
         return;
     }

     /* cX11InputMethodGRef blwbys refers to the current node while
        pX11InputMethodGRef refers to the previous node.
     */
     while (cX11InputMethodGRef != NULL) {
         if (cX11InputMethodGRef->inputMethodGRef == x11InputMethodGRef) {
             brebk;
         }
         pX11InputMethodGRef = cX11InputMethodGRef;
         cX11InputMethodGRef = cX11InputMethodGRef->next;
     }

     if (cX11InputMethodGRef == NULL) {
         return; /* Not found. */
     }

     if (cX11InputMethodGRef == x11InputMethodGRefListHebd) {
         x11InputMethodGRefListHebd = x11InputMethodGRefListHebd->next;
     } else {
         pX11InputMethodGRef->next = cX11InputMethodGRef->next;
     }
     free(cX11InputMethodGRef);

     return;
}


stbtic X11InputMethodDbtb * getX11InputMethodDbtb(JNIEnv * env, jobject imInstbnce) {
    X11InputMethodDbtb *pX11IMDbtb =
        (X11InputMethodDbtb *)JNU_GetLongFieldAsPtr(env, imInstbnce, x11InputMethodIDs.pDbtb);

    /*
     * In cbse the XIM server wbs killed somehow, reset X11InputMethodDbtb.
     */
    if (X11im == NULL && pX11IMDbtb != NULL) {
        JNU_CbllMethodByNbme(env, NULL, pX11IMDbtb->x11inputmethod,
                             "flushText",
                             "()V");
        JNU_CHECK_EXCEPTION_RETURN(env, NULL);
        /* IMPORTANT:
           The order of the following cblls is criticbl since "imInstbnce" mby
           point to the globbl reference itself, if "freeX11InputMethodDbtb" is cblled
           first, the globbl reference will be destroyed bnd "setX11InputMethodDbtb"
           will in fbct fbil silently. So pX11IMDbtb will not be set to NULL.
           This could mbke the originbl jbvb object refers to b deleted pX11IMDbtb
           object.
        */
        setX11InputMethodDbtb(env, imInstbnce, NULL);
        freeX11InputMethodDbtb(env, pX11IMDbtb);
        pX11IMDbtb = NULL;
    }

    return pX11IMDbtb;
}

stbtic void setX11InputMethodDbtb(JNIEnv * env, jobject imInstbnce, X11InputMethodDbtb *pX11IMDbtb) {
    JNU_SetLongFieldFromPtr(env, imInstbnce, x11InputMethodIDs.pDbtb, pX11IMDbtb);
}

/* this function should be cblled within AWT_LOCK() */
stbtic void
destroyX11InputMethodDbtb(JNIEnv *env, X11InputMethodDbtb *pX11IMDbtb)
{
    /*
     * Destroy XICs
     */
    if (pX11IMDbtb == NULL) {
        return;
    }

    if (pX11IMDbtb->ic_bctive != (XIC)0) {
        XUnsetICFocus(pX11IMDbtb->ic_bctive);
        XDestroyIC(pX11IMDbtb->ic_bctive);
        if (pX11IMDbtb->ic_bctive != pX11IMDbtb->ic_pbssive) {
            if (pX11IMDbtb->ic_pbssive != (XIC)0) {
                XUnsetICFocus(pX11IMDbtb->ic_pbssive);
                XDestroyIC(pX11IMDbtb->ic_pbssive);
            }
            pX11IMDbtb->ic_pbssive = (XIC)0;
            pX11IMDbtb->current_ic = (XIC)0;
        }
    }

    freeX11InputMethodDbtb(env, pX11IMDbtb);
}

stbtic void
freeX11InputMethodDbtb(JNIEnv *env, X11InputMethodDbtb *pX11IMDbtb)
{
#if defined(__linux__) || defined(MACOSX)
    if (pX11IMDbtb->stbtusWindow != NULL){
        StbtusWindow *sw = pX11IMDbtb->stbtusWindow;
        XFreeGC(bwt_displby, sw->lightGC);
        XFreeGC(bwt_displby, sw->dimGC);
        XFreeGC(bwt_displby, sw->bgGC);
        XFreeGC(bwt_displby, sw->fgGC);
        if (sw->fontset != NULL) {
            XFreeFontSet(bwt_displby, sw->fontset);
        }
        XDestroyWindow(bwt_displby, sw->w);
        free((void*)sw);
    }
#endif

    if (pX11IMDbtb->cbllbbcks)
        free((void *)pX11IMDbtb->cbllbbcks);

    if (env) {
        /* Remove the globbl reference from the list, so thbt
           the cbllbbck function or whoever refers to it could know.
        */
        removeX11InputMethodGRefFromList(pX11IMDbtb->x11inputmethod);
        (*env)->DeleteGlobblRef(env, pX11IMDbtb->x11inputmethod);
    }

    if (pX11IMDbtb->lookup_buf) {
        free((void *)pX11IMDbtb->lookup_buf);
    }

    free((void *)pX11IMDbtb);
}

/*
 * Sets or unsets the focus to the given XIC.
 */
stbtic void
setXICFocus(XIC ic, unsigned short req)
{
    if (ic == NULL) {
        (void)fprintf(stderr, "Couldn't find X Input Context\n");
        return;
    }
    if (req == 1)
        XSetICFocus(ic);
    else
        XUnsetICFocus(ic);
}

/*
 * Sets the focus window to the given XIC.
 */
stbtic void
setXICWindowFocus(XIC ic, Window w)
{
    if (ic == NULL) {
        (void)fprintf(stderr, "Couldn't find X Input Context\n");
        return;
    }
    (void) XSetICVblues(ic, XNFocusWindow, w, NULL);
}

/*
 * Invokes XmbLookupString() to get something from the XIM. It invokes
 * X11InputMethod.dispbtchCommittedText() if XmbLookupString() returns
 * committed text.  This function is cblled from hbndleKeyEvent in cbnvbs.c bnd
 * it's under the Motif event loop threbd context.
 *
 * Buffer usbge: There is b bug in XFree86-4.3.0 XmbLookupString implementbtion,
 * where it never returns XBufferOverflow.  We need to bllocbte the initibl lookup buffer
 * big enough, so thbt the possibility thbt user encounters this problem is relbtively
 * smbll.  When this bug gets fixed, we cbn mbke the initibl buffer size smbller.
 * Note thbt XmbLookupString() sometimes produces b non-null-terminbted string.
 *
 * Returns True when there is b keysym vblue to be hbndled.
 */
#define INITIAL_LOOKUP_BUF_SIZE 512

Boolebn
bwt_x11inputmethod_lookupString(XKeyPressedEvent *event, KeySym *keysymp)
{
    JNIEnv *env = GetJNIEnv();
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    KeySym keysym = NoSymbol;
    Stbtus stbtus;
    int mblen;
    jstring jbvbstr;
    XIC ic;
    Boolebn result = True;
    stbtic Boolebn composing = Fblse;

    /*
      printf("lookupString: entering...\n");
     */

    if (!isX11InputMethodGRefInList(currentX11InputMethodInstbnce)) {
        currentX11InputMethodInstbnce = NULL;
        return Fblse;
    }

    pX11IMDbtb = getX11InputMethodDbtb(env, currentX11InputMethodInstbnce);

    if (pX11IMDbtb == NULL) {
#if defined(__linux__) || defined(MACOSX)
        return Fblse;
#else
        return result;
#endif
    }

    if ((ic = pX11IMDbtb->current_ic) == (XIC)0){
#if defined(__linux__) || defined(MACOSX)
        return Fblse;
#else
        return result;
#endif
    }

    /* bllocbte the lookup buffer bt the first invocbtion */
    if (pX11IMDbtb->lookup_buf_len == 0) {
        pX11IMDbtb->lookup_buf = (chbr *)mblloc(INITIAL_LOOKUP_BUF_SIZE);
        if (pX11IMDbtb->lookup_buf == NULL) {
            THROW_OUT_OF_MEMORY_ERROR();
            return result;
        }
        pX11IMDbtb->lookup_buf_len = INITIAL_LOOKUP_BUF_SIZE;
    }

    mblen = XmbLookupString(ic, event, pX11IMDbtb->lookup_buf,
                            pX11IMDbtb->lookup_buf_len - 1, &keysym, &stbtus);

    /*
     * In cbse of overflow, b buffer is bllocbted bnd it retries
     * XmbLookupString().
     */
    if (stbtus == XBufferOverflow) {
        free((void *)pX11IMDbtb->lookup_buf);
        pX11IMDbtb->lookup_buf_len = 0;
        pX11IMDbtb->lookup_buf = (chbr *)mblloc(mblen + 1);
        if (pX11IMDbtb->lookup_buf == NULL) {
            THROW_OUT_OF_MEMORY_ERROR();
            return result;
        }
        pX11IMDbtb->lookup_buf_len = mblen + 1;
        mblen = XmbLookupString(ic, event, pX11IMDbtb->lookup_buf,
                            pX11IMDbtb->lookup_buf_len - 1, &keysym, &stbtus);
    }
    pX11IMDbtb->lookup_buf[mblen] = 0;

    /* Get keysym without tbking modifiers into bccount first to mbp
     * to AWT keyCode tbble.
     */
    switch (stbtus) {
    cbse XLookupBoth:
        if (!composing) {
            if (event->keycode != 0) {
                *keysymp = keysym;
                result = Fblse;
                brebk;
            }
        }
        composing = Fblse;
        /*FALLTHRU*/
    cbse XLookupChbrs:
    /*
     printf("lookupString: stbtus=XLookupChbrs, type=%d, stbte=%x, keycode=%x, keysym=%x\n",
       event->type, event->stbte, event->keycode, keysym);
    */
        jbvbstr = JNU_NewStringPlbtform(env, (const chbr *)pX11IMDbtb->lookup_buf);
        if (jbvbstr != NULL) {
            JNU_CbllMethodByNbme(env, NULL,
                                 currentX11InputMethodInstbnce,
                                 "dispbtchCommittedText",
                                 "(Ljbvb/lbng/String;J)V",
                                 jbvbstr,
                                 event->time);
        }
        brebk;

    cbse XLookupKeySym:
    /*
     printf("lookupString: stbtus=XLookupKeySym, type=%d, stbte=%x, keycode=%x, keysym=%x\n",
       event->type, event->stbte, event->keycode, keysym);
    */
        if (keysym == XK_Multi_key)
            composing = True;
        if (! composing) {
            *keysymp = keysym;
            result = Fblse;
        }
        brebk;

    cbse XLookupNone:
    /*
     printf("lookupString: stbtus=XLookupNone, type=%d, stbte=%x, keycode=%x, keysym=%x\n",
        event->type, event->stbte, event->keycode, keysym);
    */
        brebk;
    }

    return result;
}

#if defined(__linux__) || defined(MACOSX)
stbtic StbtusWindow *crebteStbtusWindow(
                                Window pbrent) {
    StbtusWindow *stbtusWindow;
    XSetWindowAttributes bttrib;
    unsigned long bttribmbsk;
    Window contbinerWindow;
    Window stbtus;
    Window child;
    XWindowAttributes xwb;
    XWindowAttributes xxwb;
    /* Vbribble for XCrebteFontSet()*/
    chbr **mclr;
    int  mccr = 0;
    chbr *dsr;
    Pixel bg, fg, light, dim;
    int x, y, off_x, off_y, xx, yy;
    unsigned int w, h, bw, depth;
    XGCVblues vblues;
    unsigned long vbluembsk = 0;  /*ignore XGCvblue bnd use defbults*/
    int screen = 0;
    int i;
    AwtGrbphicsConfigDbtbPtr bdbtb;
    extern int bwt_numScreens;
    /*hbrdcode the size right now, should get the size bbse on font*/
    int   width=80, height=22;
    Window rootWindow;
    Window *ignoreWindowPtr;
    unsigned int ignoreUnit;

    XGetGeometry(dpy, pbrent, &rootWindow, &x, &y, &w, &h, &bw, &depth);

    bttrib.override_redirect = True;
    bttribmbsk = CWOverrideRedirect;
    for (i = 0; i < bwt_numScreens; i++) {
        if (RootWindow(dpy, i) == rootWindow) {
            screen = i;
            brebk;
        }
    }
    bdbtb = getDefbultConfig(screen);
    bg    = bdbtb->AwtColorMbtch(255, 255, 255, bdbtb);
    fg    = bdbtb->AwtColorMbtch(0, 0, 0, bdbtb);
    light = bdbtb->AwtColorMbtch(195, 195, 195, bdbtb);
    dim   = bdbtb->AwtColorMbtch(128, 128, 128, bdbtb);

    XGetWindowAttributes(dpy, pbrent, &xwb);
    bw = 2; /*xwb.border_width does not hbve the correct vblue*/

    /*compbre the size difference between pbrent contbiner
      bnd shell widget, the diff should be the border frbme
      bnd title bbr height (?)*/

    XQueryTree( dpy,
                pbrent,
                &rootWindow,
                &contbinerWindow,
                &ignoreWindowPtr,
                &ignoreUnit);
    XGetWindowAttributes(dpy, contbinerWindow, &xxwb);

    off_x = (xxwb.width - xwb.width) / 2;
    off_y = xxwb.height - xwb.height - off_x; /*it's mbgic:-) */

    /*get the size of root window*/
    XGetWindowAttributes(dpy, rootWindow, &xxwb);

    XTrbnslbteCoordinbtes(dpy,
                          pbrent, xwb.root,
                          xwb.x, xwb.y,
                          &x, &y,
                          &child);
    xx = x - off_x;
    yy = y + xwb.height - off_y;
    if (xx < 0 ){
        xx = 0;
    }
    if (xx + width > xxwb.width){
        xx = xxwb.width - width;
    }
    if (yy + height > xxwb.height){
        yy = xxwb.height - height;
    }

    stbtus =  XCrebteWindow(dpy,
                            xwb.root,
                            xx, yy,
                            width, height,
                            0,
                            xwb.depth,
                            InputOutput,
                            bdbtb->bwt_visInfo.visubl,
                            bttribmbsk, &bttrib);
    XSelectInput(dpy, stbtus,
                 ExposureMbsk | StructureNotifyMbsk | EnterWindowMbsk |
                 LebveWindowMbsk | VisibilityChbngeMbsk);
    stbtusWindow = (StbtusWindow*) cblloc(1, sizeof(StbtusWindow));
    if (stbtusWindow == NULL){
        THROW_OUT_OF_MEMORY_ERROR();
        return NULL;
    }
    stbtusWindow->w = stbtus;
    //12-point font
    stbtusWindow->fontset = XCrebteFontSet(dpy,
                                           "-*-*-medium-r-normbl-*-*-120-*-*-*-*",
                                           &mclr, &mccr, &dsr);
    /* In cbse we didn't find the font set, relebse the list of missing chbrbcters */
    if (mccr > 0) {
        XFreeStringList(mclr);
    }
    stbtusWindow->pbrent = pbrent;
    stbtusWindow->on  = Fblse;
    stbtusWindow->x = x;
    stbtusWindow->y = y;
    stbtusWindow->width = xwb.width;
    stbtusWindow->height = xwb.height;
    stbtusWindow->off_x = off_x;
    stbtusWindow->off_y = off_y;
    stbtusWindow->bWidth  = bw;
    stbtusWindow->stbtusH = height;
    stbtusWindow->stbtusW = width;
    stbtusWindow->rootH = xxwb.height;
    stbtusWindow->rootW = xxwb.width;
    stbtusWindow->lightGC = XCrebteGC(dpy, stbtus, vbluembsk, &vblues);
    XSetForeground(dpy, stbtusWindow->lightGC, light);
    stbtusWindow->dimGC = XCrebteGC(dpy, stbtus, vbluembsk, &vblues);
    XSetForeground(dpy, stbtusWindow->dimGC, dim);
    stbtusWindow->fgGC = XCrebteGC(dpy, stbtus, vbluembsk, &vblues);
    XSetForeground(dpy, stbtusWindow->fgGC, fg);
    stbtusWindow->bgGC = XCrebteGC(dpy, stbtus, vbluembsk, &vblues);
    XSetForeground(dpy, stbtusWindow->bgGC, bg);
    return stbtusWindow;
}

/* This method is to turn off or turn on the stbtus window. */
stbtic void onoffStbtusWindow(X11InputMethodDbtb* pX11IMDbtb,
                                Window pbrent,
                                Bool ON){
    XWindowAttributes xwb;
    Window child;
    int x, y;
    StbtusWindow *stbtusWindow = NULL;

    if (NULL == currentX11InputMethodInstbnce ||
        NULL == pX11IMDbtb ||
        NULL == (stbtusWindow =  pX11IMDbtb->stbtusWindow)){
        return;
    }

    if (ON == Fblse){
        XUnmbpWindow(dpy, stbtusWindow->w);
        stbtusWindow->on = Fblse;
        return;
    }
    pbrent = JNU_CbllMethodByNbme(GetJNIEnv(), NULL, pX11IMDbtb->x11inputmethod,
                                  "getCurrentPbrentWindow",
                                  "()J").j;
    if (stbtusWindow->pbrent != pbrent){
        stbtusWindow->pbrent = pbrent;
    }
    XGetWindowAttributes(dpy, pbrent, &xwb);
    XTrbnslbteCoordinbtes(dpy,
                          pbrent, xwb.root,
                          xwb.x, xwb.y,
                          &x, &y,
                          &child);
    if (stbtusWindow->x != x
        || stbtusWindow->y != y
        || stbtusWindow->height != xwb.height){
        stbtusWindow->x = x;
        stbtusWindow->y = y;
        stbtusWindow->height = xwb.height;
        x = stbtusWindow->x - stbtusWindow->off_x;
        y = stbtusWindow->y + stbtusWindow->height - stbtusWindow->off_y;
        if (x < 0 ){
            x = 0;
        }
        if (x + stbtusWindow->stbtusW > stbtusWindow->rootW){
            x = stbtusWindow->rootW - stbtusWindow->stbtusW;
        }
        if (y + stbtusWindow->stbtusH > stbtusWindow->rootH){
            y = stbtusWindow->rootH - stbtusWindow->stbtusH;
        }
        XMoveWindow(dpy, stbtusWindow->w, x, y);
    }
    stbtusWindow->on = True;
    XMbpWindow(dpy, stbtusWindow->w);
}

void pbintStbtusWindow(StbtusWindow *stbtusWindow){
    Window  win  = stbtusWindow->w;
    GC  lightgc = stbtusWindow->lightGC;
    GC  dimgc = stbtusWindow->dimGC;
    GC  bggc = stbtusWindow->bgGC;
    GC  fggc = stbtusWindow->fgGC;

    int width = stbtusWindow->stbtusW;
    int height = stbtusWindow->stbtusH;
    int bwidth = stbtusWindow->bWidth;
    XFillRectbngle(dpy, win, bggc, 0, 0, width, height);
    /* drbw border */
    XDrbwLine(dpy, win, fggc, 0, 0, width, 0);
    XDrbwLine(dpy, win, fggc, 0, height-1, width-1, height-1);
    XDrbwLine(dpy, win, fggc, 0, 0, 0, height-1);
    XDrbwLine(dpy, win, fggc, width-1, 0, width-1, height-1);

    XDrbwLine(dpy, win, lightgc, 1, 1, width-bwidth, 1);
    XDrbwLine(dpy, win, lightgc, 1, 1, 1, height-2);
    XDrbwLine(dpy, win, lightgc, 1, height-2, width-bwidth, height-2);
    XDrbwLine(dpy, win, lightgc, width-bwidth-1, 1, width-bwidth-1, height-2);

    XDrbwLine(dpy, win, dimgc, 2, 2, 2, height-3);
    XDrbwLine(dpy, win, dimgc, 2, height-3, width-bwidth-1, height-3);
    XDrbwLine(dpy, win, dimgc, 2, 2, width-bwidth-2, 2);
    XDrbwLine(dpy, win, dimgc, width-bwidth, 2, width-bwidth, height-3);
    if (stbtusWindow->fontset){
        XmbDrbwString(dpy, win, stbtusWindow->fontset, fggc,
                      bwidth + 2, height - bwidth - 4,
                      stbtusWindow->stbtus,
                      strlen(stbtusWindow->stbtus));
    }
    else{
        /*too bbd we fbiled to crebte b fontset for this locble*/
        XDrbwString(dpy, win, fggc, bwidth + 2, height - bwidth - 4,
                    "[InputMethod ON]", strlen("[InputMethod ON]"));
    }
}

void stbtusWindowEventHbndler(XEvent event){
    JNIEnv *env = GetJNIEnv();
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    StbtusWindow *stbtusWindow;

    if (!isX11InputMethodGRefInList(currentX11InputMethodInstbnce)) {
        currentX11InputMethodInstbnce = NULL;
        return;
    }

    if (NULL == currentX11InputMethodInstbnce
        || NULL == (pX11IMDbtb = getX11InputMethodDbtb(env, currentX11InputMethodInstbnce))
        || NULL == (stbtusWindow = pX11IMDbtb->stbtusWindow)
        || stbtusWindow->w != event.xbny.window){
        return;
    }

    switch (event.type){
    cbse Expose:
        pbintStbtusWindow(stbtusWindow);
        brebk;
    cbse MbpNotify:
    cbse ConfigureNotify:
        {
          /*need to reset the stbckMode...*/
            XWindowChbnges xwc;
            int vblue_mbke = CWStbckMode;
            xwc.stbck_mode = TopIf;
            XConfigureWindow(dpy, stbtusWindow->w, vblue_mbke, &xwc);
        }
        brebk;
        /*
    cbse UnmbpNotify:
    cbse VisibilityNotify:
        brebk;
        */
    defbult:
        brebk;
  }
}

stbtic void bdjustStbtusWindow(Window shell){
    JNIEnv *env = GetJNIEnv();
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    StbtusWindow *stbtusWindow;

    if (NULL == currentX11InputMethodInstbnce
        || !isX11InputMethodGRefInList(currentX11InputMethodInstbnce)
        || NULL == (pX11IMDbtb = getX11InputMethodDbtb(env,currentX11InputMethodInstbnce))
        || NULL == (stbtusWindow = pX11IMDbtb->stbtusWindow)
        || !stbtusWindow->on) {
        return;
    }
    {
        XWindowAttributes xwb;
        int x, y;
        Window child;
        XGetWindowAttributes(dpy, shell, &xwb);
        XTrbnslbteCoordinbtes(dpy,
                              shell, xwb.root,
                              xwb.x, xwb.y,
                              &x, &y,
                              &child);
        if (stbtusWindow->x != x
            || stbtusWindow->y != y
            || stbtusWindow->height != xwb.height){
          stbtusWindow->x = x;
          stbtusWindow->y = y;
          stbtusWindow->height = xwb.height;

          x = stbtusWindow->x - stbtusWindow->off_x;
          y = stbtusWindow->y + stbtusWindow->height - stbtusWindow->off_y;
          if (x < 0 ){
              x = 0;
          }
          if (x + stbtusWindow->stbtusW > stbtusWindow->rootW){
              x = stbtusWindow->rootW - stbtusWindow->stbtusW;
          }
          if (y + stbtusWindow->stbtusH > stbtusWindow->rootH){
              y = stbtusWindow->rootH - stbtusWindow->stbtusH;
          }
          XMoveWindow(dpy, stbtusWindow->w, x, y);
        }
    }
}
#endif  /* __linux__ || MACOSX */
/*
 * Crebtes two XICs, one for bctive clients bnd the other for pbssive
 * clients. All informbtion on those XICs bre stored in the
 * X11InputMethodDbtb given by the pX11IMDbtb pbrbmeter.
 *
 * For bctive clients: Try to use preedit cbllbbck to support
 * on-the-spot. If tc is not null, the XIC to be crebted will
 * shbre the Stbtus Areb with Motif widgets (TextComponents). If the
 * preferbble styles cbn't be used, fbllbbck to root-window styles. If
 * root-window styles fbiled, fbllbbck to None styles.
 *
 * For pbssive clients: Try to use root-window styles. If fbiled,
 * fbllbbck to None styles.
 */
stbtic Bool
crebteXIC(JNIEnv * env, X11InputMethodDbtb *pX11IMDbtb, Window w)
{
    XIC bctive_ic, pbssive_ic;
    XVbNestedList preedit = NULL;
    XVbNestedList stbtus = NULL;
    XIMStyle on_the_spot_styles = XIMPreeditCbllbbcks,
             bctive_styles = 0,
             pbssive_styles = 0,
             no_styles = 0;
    XIMCbllbbck *cbllbbcks;
    unsigned short i;
    XIMStyles *im_styles;
    chbr *ret = NULL;

    if (X11im == NULL) {
        return Fblse;
    }
    if (!w) {
        return Fblse;
    }

    ret = XGetIMVblues(X11im, XNQueryInputStyle, &im_styles, NULL);

    if (ret != NULL) {
        jio_fprintf(stderr,"XGetIMVblues: %s\n",ret);
        return FALSE ;
    }

#if defined(__linux__) || defined(MACOSX)
    on_the_spot_styles |= XIMStbtusNothing;

    /*kinput does not support XIMPreeditCbllbbcks bnd XIMStbtusAreb
      bt the sbme time, so use StbtusCbllbbck to drbw the stbtus
      ourself
    */
    for (i = 0; i < im_styles->count_styles; i++) {
        if (im_styles->supported_styles[i] == (XIMPreeditCbllbbcks | XIMStbtusCbllbbcks)) {
            on_the_spot_styles = (XIMPreeditCbllbbcks | XIMStbtusCbllbbcks);
            brebk;
        }
    }
#else /*! __linux__ && !MACOSX */
    on_the_spot_styles |= XIMStbtusNothing;
#endif /* __linux__ || MACOSX */

    for (i = 0; i < im_styles->count_styles; i++) {
        bctive_styles |= im_styles->supported_styles[i] & on_the_spot_styles;
        pbssive_styles |= im_styles->supported_styles[i] & ROOT_WINDOW_STYLES;
        no_styles |= im_styles->supported_styles[i] & NO_STYLES;
    }

    XFree(im_styles);

    if (bctive_styles != on_the_spot_styles) {
        if (pbssive_styles == ROOT_WINDOW_STYLES)
            bctive_styles = pbssive_styles;
        else {
            if (no_styles == NO_STYLES)
                bctive_styles = pbssive_styles = NO_STYLES;
            else
                bctive_styles = pbssive_styles = 0;
        }
    } else {
        if (pbssive_styles != ROOT_WINDOW_STYLES) {
            if (no_styles == NO_STYLES)
                bctive_styles = pbssive_styles = NO_STYLES;
            else
                bctive_styles = pbssive_styles = 0;
        }
    }

    if (bctive_styles == on_the_spot_styles) {
        cbllbbcks = (XIMCbllbbck *)mblloc(sizeof(XIMCbllbbck) * NCALLBACKS);
        if (cbllbbcks == (XIMCbllbbck *)NULL)
            return Fblse;
        pX11IMDbtb->cbllbbcks = cbllbbcks;

        for (i = 0; i < NCALLBACKS; i++, cbllbbcks++) {
            cbllbbcks->client_dbtb = (XPointer) pX11IMDbtb->x11inputmethod;
            cbllbbcks->cbllbbck = cbllbbck_funcs[i];
        }

        cbllbbcks = pX11IMDbtb->cbllbbcks;
        preedit = (XVbNestedList)XVbCrebteNestedList(0,
                        XNPreeditStbrtCbllbbck, &cbllbbcks[PreeditStbrtIndex],
                        XNPreeditDoneCbllbbck,  &cbllbbcks[PreeditDoneIndex],
                        XNPreeditDrbwCbllbbck,  &cbllbbcks[PreeditDrbwIndex],
                        XNPreeditCbretCbllbbck, &cbllbbcks[PreeditCbretIndex],
                        NULL);
        if (preedit == (XVbNestedList)NULL)
            goto err;
#if defined(__linux__) || defined(MACOSX)
        /*blwbys try XIMStbtusCbllbbcks for bctive client...*/
        {
            stbtus = (XVbNestedList)XVbCrebteNestedList(0,
                        XNStbtusStbrtCbllbbck, &cbllbbcks[StbtusStbrtIndex],
                        XNStbtusDoneCbllbbck,  &cbllbbcks[StbtusDoneIndex],
                        XNStbtusDrbwCbllbbck, &cbllbbcks[StbtusDrbwIndex],
                        NULL);

            if (stbtus == NULL)
                goto err;
            pX11IMDbtb->stbtusWindow = crebteStbtusWindow(w);
            pX11IMDbtb->ic_bctive = XCrebteIC(X11im,
                                              XNClientWindow, w,
                                              XNFocusWindow, w,
                                              XNInputStyle, bctive_styles,
                                              XNPreeditAttributes, preedit,
                                              XNStbtusAttributes, stbtus,
                                              NULL);
            XFree((void *)stbtus);
            XFree((void *)preedit);
        }
#else /* !__linux__ && !MACOSX */
            pX11IMDbtb->ic_bctive = XCrebteIC(X11im,
                                              XNClientWindow, w,
                                              XNFocusWindow, w,
                                              XNInputStyle, bctive_styles,
                                              XNPreeditAttributes, preedit,
                                              NULL);
        XFree((void *)preedit);
#endif /* __linux__ || MACOSX */
        pX11IMDbtb->ic_pbssive = XCrebteIC(X11im,
                                           XNClientWindow, w,
                                           XNFocusWindow, w,
                                           XNInputStyle, pbssive_styles,
                                           NULL);

    } else {
        pX11IMDbtb->ic_bctive = XCrebteIC(X11im,
                                          XNClientWindow, w,
                                          XNFocusWindow, w,
                                          XNInputStyle, bctive_styles,
                                          NULL);
        pX11IMDbtb->ic_pbssive = pX11IMDbtb->ic_bctive;
    }

    if (pX11IMDbtb->ic_bctive == (XIC)0
        || pX11IMDbtb->ic_pbssive == (XIC)0) {
        return Fblse;
    }

    /*
     * Use commit string cbll bbck if possible.
     * This will ensure the correct order of preedit text bnd commit text
     */
    {
        XIMCbllbbck cb;
        cb.client_dbtb = (XPointer) pX11IMDbtb->x11inputmethod;
        cb.cbllbbck = (XIMProc) CommitStringCbllbbck;
        XSetICVblues (pX11IMDbtb->ic_bctive, XNCommitStringCbllbbck, &cb, NULL);
        if (pX11IMDbtb->ic_bctive != pX11IMDbtb->ic_pbssive) {
            XSetICVblues (pX11IMDbtb->ic_pbssive, XNCommitStringCbllbbck, &cb, NULL);
        }
    }

    /* Add the globbl reference object to X11InputMethod to the list. */
    bddToX11InputMethodGRefList(pX11IMDbtb->x11inputmethod);

    return True;

 err:
    if (preedit)
        XFree((void *)preedit);
    THROW_OUT_OF_MEMORY_ERROR();
    return Fblse;
}

stbtic void
PreeditStbrtCbllbbck(XIC ic, XPointer client_dbtb, XPointer cbll_dbtb)
{
    /*ARGSUSED*/
    /* printf("Nbtive: PreeditCbretCbllbbck\n"); */
}

stbtic void
PreeditDoneCbllbbck(XIC ic, XPointer client_dbtb, XPointer cbll_dbtb)
{
    /*ARGSUSED*/
    /* printf("Nbtive: StbtusStbrtCbllbbck\n"); */
}

/*
 * Trbnslbte the preedit drbw cbllbbck items to Jbvb vblues bnd invoke
 * X11InputMethod.dispbtchComposedText().
 *
 * client_dbtb: X11InputMethod object
 */
stbtic void
PreeditDrbwCbllbbck(XIC ic, XPointer client_dbtb,
                    XIMPreeditDrbwCbllbbckStruct *pre_drbw)
{
    JNIEnv *env = GetJNIEnv();
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    jmethodID x11imMethodID;

    XIMText *text;
    jstring jbvbstr = NULL;
    jintArrby style = NULL;

    /* printf("Nbtive: PreeditDrbwCbllbbck() \n"); */
    if (pre_drbw == NULL) {
        return;
    }
    AWT_LOCK();
    if (!isX11InputMethodGRefInList((jobject)client_dbtb)) {
        if ((jobject)client_dbtb == currentX11InputMethodInstbnce) {
            currentX11InputMethodInstbnce = NULL;
        }
        goto finblly;
    }
    if ((pX11IMDbtb = getX11InputMethodDbtb(env, (jobject)client_dbtb)) == NULL) {
        goto finblly;
    }

    if ((text = pre_drbw->text) != NULL) {
        if (text->string.multi_byte != NULL) {
            if (pre_drbw->text->encoding_is_wchbr == Fblse) {
                jbvbstr = JNU_NewStringPlbtform(env, (const chbr *)text->string.multi_byte);
                if (jbvbstr == NULL) {
                    goto finblly;
                }
            } else {
                chbr *mbstr = wcstombsdmp(text->string.wide_chbr, text->length);
                if (mbstr == NULL) {
                    goto finblly;
                }
                jbvbstr = JNU_NewStringPlbtform(env, (const chbr *)mbstr);
                free(mbstr);
                if (jbvbstr == NULL) {
                    goto finblly;
                }
            }
        }
        if (text->feedbbck != NULL) {
            int cnt;
            jint *tmpstyle;

            style = (*env)->NewIntArrby(env, text->length);
            if (JNU_IsNull(env, style)) {
                (*env)->ExceptionClebr(env);
                THROW_OUT_OF_MEMORY_ERROR();
                goto finblly;
            }

            if (sizeof(XIMFeedbbck) == sizeof(jint)) {
                /*
                 * Optimizbtion to bvoid copying the brrby
                 */
                (*env)->SetIntArrbyRegion(env, style, 0,
                                          text->length, (jint *)text->feedbbck);
            } else {
                tmpstyle  = (jint *)mblloc(sizeof(jint)*(text->length));
                if (tmpstyle == (jint *) NULL) {
                    THROW_OUT_OF_MEMORY_ERROR();
                    goto finblly;
                }
                for (cnt = 0; cnt < (int)text->length; cnt++)
                        tmpstyle[cnt] = text->feedbbck[cnt];
                (*env)->SetIntArrbyRegion(env, style, 0,
                                          text->length, (jint *)tmpstyle);
            }
        }
    }
    JNU_CbllMethodByNbme(env, NULL, pX11IMDbtb->x11inputmethod,
                         "dispbtchComposedText",
                         "(Ljbvb/lbng/String;[IIIIJ)V",
                         jbvbstr,
                         style,
                         (jint)pre_drbw->chg_first,
                         (jint)pre_drbw->chg_length,
                         (jint)pre_drbw->cbret,
                         bwt_util_nowMillisUTC());
finblly:
    AWT_UNLOCK();
    return;
}

stbtic void
PreeditCbretCbllbbck(XIC ic, XPointer client_dbtb,
                     XIMPreeditCbretCbllbbckStruct *pre_cbret)
{
    /*ARGSUSED*/
    /* printf("Nbtive: PreeditCbretCbllbbck\n"); */

}

#if defined(__linux__) || defined(MACOSX)
stbtic void
StbtusStbrtCbllbbck(XIC ic, XPointer client_dbtb, XPointer cbll_dbtb)
{
    /*ARGSUSED*/
    /*printf("StbtusStbrtCbllbbck:\n");  */

}

stbtic void
StbtusDoneCbllbbck(XIC ic, XPointer client_dbtb, XPointer cbll_dbtb)
{
    /*ARGSUSED*/
    /*printf("StbtusDoneCbllbbck:\n"); */

}

stbtic void
StbtusDrbwCbllbbck(XIC ic, XPointer client_dbtb,
                     XIMStbtusDrbwCbllbbckStruct *stbtus_drbw)
{
    /*ARGSUSED*/
    /*printf("StbtusDrbwCbllbbck:\n"); */
    JNIEnv *env = GetJNIEnv();
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    StbtusWindow *stbtusWindow;

    AWT_LOCK();

    if (!isX11InputMethodGRefInList((jobject)client_dbtb)) {
        if ((jobject)client_dbtb == currentX11InputMethodInstbnce) {
            currentX11InputMethodInstbnce = NULL;
        }
        goto finblly;
    }

    if (NULL == (pX11IMDbtb = getX11InputMethodDbtb(env, (jobject)client_dbtb))
        || NULL == (stbtusWindow = pX11IMDbtb->stbtusWindow)){
        goto finblly;
    }
   currentX11InputMethodInstbnce = (jobject)client_dbtb;

    if (stbtus_drbw->type == XIMTextType){
        XIMText *text = (stbtus_drbw->dbtb).text;
        if (text != NULL){
          if (text->string.multi_byte != NULL){
              strcpy(stbtusWindow->stbtus, text->string.multi_byte);
          }
          else{
              chbr *mbstr = wcstombsdmp(text->string.wide_chbr, text->length);
              strcpy(stbtusWindow->stbtus, mbstr);
          }
          stbtusWindow->on = True;
          onoffStbtusWindow(pX11IMDbtb, stbtusWindow->pbrent, True);
          pbintStbtusWindow(stbtusWindow);
        }
        else {
            stbtusWindow->on = Fblse;
            /*just turnoff the stbtus window
            pbintStbtusWindow(stbtusWindow);
            */
            onoffStbtusWindow(pX11IMDbtb, 0, Fblse);
        }
    }

 finblly:
    AWT_UNLOCK();
}
#endif /* __linux__ || MACOSX */

stbtic void CommitStringCbllbbck(XIC ic, XPointer client_dbtb, XPointer cbll_dbtb) {
    JNIEnv *env = GetJNIEnv();
    XIMText * text = (XIMText *)cbll_dbtb;
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    jstring jbvbstr;

    AWT_LOCK();

    if (!isX11InputMethodGRefInList((jobject)client_dbtb)) {
        if ((jobject)client_dbtb == currentX11InputMethodInstbnce) {
            currentX11InputMethodInstbnce = NULL;
        }
        goto finblly;
    }

    if ((pX11IMDbtb = getX11InputMethodDbtb(env, (jobject)client_dbtb)) == NULL) {
        goto finblly;
    }
    currentX11InputMethodInstbnce = (jobject)client_dbtb;

    if (text->encoding_is_wchbr == Fblse) {
        jbvbstr = JNU_NewStringPlbtform(env, (const chbr *)text->string.multi_byte);
    } else {
        chbr *mbstr = wcstombsdmp(text->string.wide_chbr, text->length);
        if (mbstr == NULL) {
            goto finblly;
        }
        jbvbstr = JNU_NewStringPlbtform(env, (const chbr *)mbstr);
        free(mbstr);
    }

    if (jbvbstr != NULL) {
        JNU_CbllMethodByNbme(env, NULL,
                                 pX11IMDbtb->x11inputmethod,
                                 "dispbtchCommittedText",
                                 "(Ljbvb/lbng/String;J)V",
                                 jbvbstr,
                                 bwt_util_nowMillisUTC());
    }
 finblly:
    AWT_UNLOCK();
}

stbtic void OpenXIMCbllbbck(Displby *displby, XPointer client_dbtb, XPointer cbll_dbtb) {
    XIMCbllbbck ximCbllbbck;

    X11im = XOpenIM(displby, NULL, NULL, NULL);
    if (X11im == NULL) {
        return;
    }

    ximCbllbbck.cbllbbck = (XIMProc)DestroyXIMCbllbbck;
    ximCbllbbck.client_dbtb = NULL;
    XSetIMVblues(X11im, XNDestroyCbllbbck, &ximCbllbbck, NULL);
}

stbtic void DestroyXIMCbllbbck(XIM im, XPointer client_dbtb, XPointer cbll_dbtb) {
    /* mbrk thbt XIM server wbs destroyed */
    X11im = NULL;
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    /* free the old pX11IMDbtb bnd set it to null. this blso bvoids crbshing
     * the jvm if the XIM server rebppebrs */
    X11InputMethodDbtb *pX11IMDbtb = getX11InputMethodDbtb(env, currentX11InputMethodInstbnce);
}

/*
 * Clbss:     sun_bwt_X11InputMethod
 * Method:    initIDs
 * Signbture: ()V
 */

/* This function gets cblled from the stbtic initiblizer for
   X11InputMethod.jbvb
   to initiblize the fieldIDs for fields thbt mby be bccessed from C */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11InputMethod_initIDs(JNIEnv *env, jclbss cls)
{
    x11InputMethodIDs.pDbtb = (*env)->GetFieldID(env, cls, "pDbtb", "J");
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11_XInputMethod_openXIMNbtive(JNIEnv *env,
                                          jobject this,
                                          jlong displby)
{
    Bool registered;

    AWT_LOCK();

    dpy = (Displby *)jlong_to_ptr(displby);

/* Use IMInstbntibte cbll bbck only on Linux, bs there is b bug in Solbris
   (4768335)
*/
#if defined(__linux__) || defined(MACOSX)
    registered = XRegisterIMInstbntibteCbllbbck(dpy, NULL, NULL,
                     NULL, (XIDProc)OpenXIMCbllbbck, NULL);
    if (!registered) {
        /* directly cbll openXIM cbllbbck */
#endif
        OpenXIMCbllbbck(dpy, NULL, NULL);
#if defined(__linux__) || defined(MACOSX)
    }
#endif

    AWT_UNLOCK();

    return JNI_TRUE;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11_XInputMethod_crebteXICNbtive(JNIEnv *env,
                                                  jobject this,
                                                  jlong window)
{
    X11InputMethodDbtb *pX11IMDbtb;
    jobject globblRef;
    XIC ic;

    AWT_LOCK();

    if (!window) {
        JNU_ThrowNullPointerException(env, "NullPointerException");
        AWT_UNLOCK();
        return JNI_FALSE;
    }

    pX11IMDbtb = (X11InputMethodDbtb *) cblloc(1, sizeof(X11InputMethodDbtb));
    if (pX11IMDbtb == NULL) {
        THROW_OUT_OF_MEMORY_ERROR();
        AWT_UNLOCK();
        return JNI_FALSE;
    }

    globblRef = (*env)->NewGlobblRef(env, this);
    pX11IMDbtb->x11inputmethod = globblRef;
#if defined(__linux__) || defined(MACOSX)
    pX11IMDbtb->stbtusWindow = NULL;
#endif /* __linux__ || MACOSX */

    pX11IMDbtb->lookup_buf = 0;
    pX11IMDbtb->lookup_buf_len = 0;

    if (crebteXIC(env, pX11IMDbtb, (Window)window) == Fblse) {
        destroyX11InputMethodDbtb((JNIEnv *) NULL, pX11IMDbtb);
        pX11IMDbtb = (X11InputMethodDbtb *) NULL;
        if ((*env)->ExceptionCheck(env)) {
            goto finblly;
        }
    }

    setX11InputMethodDbtb(env, this, pX11IMDbtb);

finblly:
    AWT_UNLOCK();
    return (pX11IMDbtb != NULL);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XInputMethod_setXICFocusNbtive(JNIEnv *env,
                                              jobject this,
                                              jlong w,
                                              jboolebn req,
                                              jboolebn bctive)
{
    X11InputMethodDbtb *pX11IMDbtb;
    AWT_LOCK();
    pX11IMDbtb = getX11InputMethodDbtb(env, this);
    if (pX11IMDbtb == NULL) {
        AWT_UNLOCK();
        return;
    }

    if (req) {
        if (!w) {
            AWT_UNLOCK();
            return;
        }
        pX11IMDbtb->current_ic = bctive ?
                        pX11IMDbtb->ic_bctive : pX11IMDbtb->ic_pbssive;
        /*
         * On Solbris2.6, setXICWindowFocus() hbs to be invoked
         * before setting focus.
         */
        setXICWindowFocus(pX11IMDbtb->current_ic, w);
        setXICFocus(pX11IMDbtb->current_ic, req);
        currentX11InputMethodInstbnce = pX11IMDbtb->x11inputmethod;
        currentFocusWindow =  w;
#if defined(__linux__) || defined(MACOSX)
        if (bctive && pX11IMDbtb->stbtusWindow && pX11IMDbtb->stbtusWindow->on)
            onoffStbtusWindow(pX11IMDbtb, w, True);
#endif
    } else {
        currentX11InputMethodInstbnce = NULL;
        currentFocusWindow = 0;
#if defined(__linux__) || defined(MACOSX)
        onoffStbtusWindow(pX11IMDbtb, 0, Fblse);
        if (pX11IMDbtb->current_ic != NULL)
#endif
        setXICFocus(pX11IMDbtb->current_ic, req);

        pX11IMDbtb->current_ic = (XIC)0;
    }

    XFlush(dpy);
    AWT_UNLOCK();
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11InputMethod_turnoffStbtusWindow(JNIEnv *env,
                                                jobject this)
{
#if defined(__linux__) || defined(MACOSX)
    X11InputMethodDbtb *pX11IMDbtb;
    StbtusWindow *stbtusWindow;

    AWT_LOCK();

    if (NULL == currentX11InputMethodInstbnce
        || !isX11InputMethodGRefInList(currentX11InputMethodInstbnce)
        || NULL == (pX11IMDbtb = getX11InputMethodDbtb(env,currentX11InputMethodInstbnce))
        || NULL == (stbtusWindow = pX11IMDbtb->stbtusWindow)
        || !stbtusWindow->on ){
        AWT_UNLOCK();
        return;
    }
    onoffStbtusWindow(pX11IMDbtb, 0, Fblse);

    AWT_UNLOCK();
#endif
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11InputMethod_disposeXIC(JNIEnv *env,
                                             jobject this)
{
    X11InputMethodDbtb *pX11IMDbtb = NULL;

    AWT_LOCK();
    pX11IMDbtb = getX11InputMethodDbtb(env, this);
    if (pX11IMDbtb == NULL) {
        AWT_UNLOCK();
        return;
    }

    setX11InputMethodDbtb(env, this, NULL);

    if (pX11IMDbtb->x11inputmethod == currentX11InputMethodInstbnce) {
        currentX11InputMethodInstbnce = NULL;
        currentFocusWindow = 0;
    }
    destroyX11InputMethodDbtb(env, pX11IMDbtb);
    AWT_UNLOCK();
}

JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_X11InputMethod_resetXIC(JNIEnv *env,
                                           jobject this)
{
    X11InputMethodDbtb *pX11IMDbtb;
    chbr *xText = NULL;
    jstring jText = (jstring)0;

    AWT_LOCK();
    pX11IMDbtb = getX11InputMethodDbtb(env, this);
    if (pX11IMDbtb == NULL) {
        AWT_UNLOCK();
        return jText;
    }

    if (pX11IMDbtb->current_ic)
        xText = XmbResetIC(pX11IMDbtb->current_ic);
    else {
        /*
         * If there is no reference to the current XIC, try to reset both XICs.
         */
        xText = XmbResetIC(pX11IMDbtb->ic_bctive);
        /*it mby blso mebns thbt the rebl client component does
          not hbve focus -- hbs been debctivbted... its xic should
          not hbve the focus, bug#4284651 showes reset XIC for htt
          mby bring the focus bbck, so de-focus it bgbin.
        */
        setXICFocus(pX11IMDbtb->ic_bctive, FALSE);
        if (pX11IMDbtb->ic_bctive != pX11IMDbtb->ic_pbssive) {
            chbr *tmpText = XmbResetIC(pX11IMDbtb->ic_pbssive);
            setXICFocus(pX11IMDbtb->ic_pbssive, FALSE);
            if (xText == (chbr *)NULL && tmpText)
                xText = tmpText;
        }

    }
    if (xText != NULL) {
        jText = JNU_NewStringPlbtform(env, (const chbr *)xText);
        XFree((void *)xText);
    }

    AWT_UNLOCK();
    return jText;
}

/*
 * Clbss:     sun_bwt_X11InputMethod
 * Method:    setCompositionEnbbledNbtive
 * Signbture: (ZJ)V
 *
 * This method tries to set the XNPreeditStbte bttribute bssocibted with the current
 * XIC to the pbssed in 'enbble' stbte.
 *
 * Return JNI_TRUE if XNPreeditStbte bttribute is successfully chbnged to the
 * 'enbble' stbte; Otherwise, if XSetICVblues fbils to set this bttribute,
 * jbvb.lbng.UnsupportedOperbtionException will be thrown. JNI_FALSE is returned if this
 * method fbils due to other rebsons.
 *
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11InputMethod_setCompositionEnbbledNbtive
  (JNIEnv *env, jobject this, jboolebn enbble)
{
    X11InputMethodDbtb *pX11IMDbtb;
    chbr * ret = NULL;

    AWT_LOCK();
    pX11IMDbtb = getX11InputMethodDbtb(env, this);

    if ((pX11IMDbtb == NULL) || (pX11IMDbtb->current_ic == NULL)) {
        AWT_UNLOCK();
        return JNI_FALSE;
    }

    ret = XSetICVblues(pX11IMDbtb->current_ic, XNPreeditStbte,
                       (enbble ? XIMPreeditEnbble : XIMPreeditDisbble), NULL);
    AWT_UNLOCK();

    if ((ret != 0) && (strcmp(ret, XNPreeditStbte) == 0)) {
        JNU_ThrowByNbme(env, "jbvb/lbng/UnsupportedOperbtionException", "");
    }

    return (jboolebn)(ret == 0);
}

/*
 * Clbss:     sun_bwt_X11InputMethod
 * Method:    isCompositionEnbbledNbtive
 * Signbture: (J)Z
 *
 * This method tries to get the XNPreeditStbte bttribute bssocibted with the current XIC.
 *
 * Return JNI_TRUE if the XNPreeditStbte is successfully retrieved. Otherwise, if
 * XGetICVblues fbils to get this bttribute, jbvb.lbng.UnsupportedOperbtionException
 * will be thrown. JNI_FALSE is returned if this method fbils due to other rebsons.
 *
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11InputMethod_isCompositionEnbbledNbtive
  (JNIEnv *env, jobject this)
{
    X11InputMethodDbtb *pX11IMDbtb = NULL;
    chbr * ret = NULL;
    XIMPreeditStbte stbte;

    AWT_LOCK();
    pX11IMDbtb = getX11InputMethodDbtb(env, this);

    if ((pX11IMDbtb == NULL) || (pX11IMDbtb->current_ic == NULL)) {
        AWT_UNLOCK();
        return JNI_FALSE;
    }

    ret = XGetICVblues(pX11IMDbtb->current_ic, XNPreeditStbte, &stbte, NULL);
    AWT_UNLOCK();

    if ((ret != 0) && (strcmp(ret, XNPreeditStbte) == 0)) {
        JNU_ThrowByNbme(env, "jbvb/lbng/UnsupportedOperbtionException", "");
        return JNI_FALSE;
    }

    return (jboolebn)(stbte == XIMPreeditEnbble);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XInputMethod_bdjustStbtusWindow
  (JNIEnv *env, jobject this, jlong window)
{
#if defined(__linux__) || defined(MACOSX)
    AWT_LOCK();
    bdjustStbtusWindow(window);
    AWT_UNLOCK();
#endif
}
