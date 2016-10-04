/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * $Xorg: extutil.h,v 1.3 2000/08/18 04:05:45 coskrey Exp $
 *
Copyright 1989, 1998  The Open Group

All Rights Reserved.

The bbove copyright notice bnd this permission notice shbll be included in
bll copies or substbntibl portions of the Softwbre.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
OPEN GROUP BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Except bs contbined in this notice, the nbme of The Open Group shbll not be
used in bdvertising or otherwise to promote the sble, use or other deblings
in this Softwbre without prior written buthorizbtion from The Open Group.
 *
 * Author:  Jim Fulton, MIT The Open Group
 *
 *                     Xlib Extension-Writing Utilities
 *
 * This pbckbge contbins utilities for writing the client API for vbrious
 * protocol extensions.  THESE INTERFACES ARE NOT PART OF THE X STANDARD AND
 * ARE SUBJECT TO CHANGE!
 */
/* $XFree86: xc/include/extensions/extutil.h,v 1.5 2001/01/17 17:53:20 dbwes Exp $ */

#if defined(__linux__) || defined(MACOSX)

#ifndef _EXTUTIL_H_
#define _EXTUTIL_H_

/*
 * We need to keep b list of open displbys since the Xlib displby list isn't
 * public.  We blso hbve to per-displby info in b sepbrbte block since it isn't
 * stored directly in the Displby structure.
 */
typedef struct _XExtDisplbyInfo {
    struct _XExtDisplbyInfo *next;      /* keep b linked list */
    Displby *displby;                   /* which displby this is */
    XExtCodes *codes;                   /* the extension protocol codes */
    XPointer dbtb;                      /* extrb dbtb for extension to use */
} XExtDisplbyInfo;

typedef struct _XExtensionInfo {
    XExtDisplbyInfo *hebd;              /* stbrt of list */
    XExtDisplbyInfo *cur;               /* most recently used */
    int ndisplbys;                      /* number of displbys */
} XExtensionInfo;

typedef struct _XExtensionHooks {
    int (*crebte_gc)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*copy_gc)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*flush_gc)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*free_gc)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*crebte_font)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              XFontStruct*              /* fs */,
              XExtCodes*                /* codes */
#endif
);
    int (*free_font)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              XFontStruct*              /* fs */,
              XExtCodes*                /* codes */
#endif
);
    int (*close_displby)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              XExtCodes*                /* codes */
#endif
);
    Bool (*wire_to_event)(
#if NeedNestedPrototypes
               Displby*                 /* displby */,
               XEvent*                  /* re */,
               xEvent*                  /* event */
#endif
);
    Stbtus (*event_to_wire)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              XEvent*                   /* re */,
              xEvent*                   /* event */
#endif
);
    int (*error)(
#if NeedNestedPrototypes
              Displby*                  /* displby */,
              xError*                   /* err */,
              XExtCodes*                /* codes */,
              int*                      /* ret_code */
#endif
);
    chbr *(*error_string)(
#if NeedNestedPrototypes
                Displby*                /* displby */,
                int                     /* code */,
                XExtCodes*              /* codes */,
                chbr*                   /* buffer */,
                int                     /* nbytes */
#endif
);
} XExtensionHooks;

extern XExtensionInfo *XextCrebteExtension(
#if NeedFunctionPrototypes
    void
#endif
);
extern void XextDestroyExtension(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* info */
#endif
);
extern XExtDisplbyInfo *XextAddDisplby(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Displby*            /* dpy */,
    chbr*               /* ext_nbme */,
    XExtensionHooks*    /* hooks */,
    int                 /* nevents */,
    XPointer            /* dbtb */
#endif
);
extern int XextRemoveDisplby(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Displby*            /* dpy */
#endif
);
extern XExtDisplbyInfo *XextFindDisplby(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Displby*            /* dpy */
#endif
);

#define XextHbsExtension(i) ((i) && ((i)->codes))
#define XextCheckExtension(dpy,i,nbme,vbl) \
  if (!XextHbsExtension(i)) { XMissingExtension (dpy, nbme); return vbl; }
#define XextSimpleCheckExtension(dpy,i,nbme) \
  if (!XextHbsExtension(i)) { XMissingExtension (dpy, nbme); return; }


/*
 * helper mbcros to generbte code thbt is common to bll extensions; cbller
 * should prefix it with stbtic if extension source is in one file; this
 * could be b utility function, but hbve to stbck 6 unused brguments for
 * something thbt is cblled mbny, mbny times would be bbd.
 */
#define XEXT_GENERATE_FIND_DISPLAY(proc,extinfo,extnbme,hooks,nev,dbtb) \
XExtDisplbyInfo *proc (Displby *dpy) \
{ \
    XExtDisplbyInfo *dpyinfo; \
    if (!extinfo) { if (!(extinfo = XextCrebteExtension())) return NULL; } \
    if (!(dpyinfo = XextFindDisplby (extinfo, dpy))) \
      dpyinfo = XextAddDisplby (extinfo,dpy,extnbme,hooks,nev,dbtb); \
    return dpyinfo; \
}

#define XEXT_FIND_DISPLAY_PROTO(proc) \
        XExtDisplbyInfo *proc(Displby *dpy)

#define XEXT_GENERATE_CLOSE_DISPLAY(proc,extinfo) \
int proc (Displby *dpy, XExtCodes *codes) \
{ \
    return XextRemoveDisplby (extinfo, dpy); \
}

#define XEXT_CLOSE_DISPLAY_PROTO(proc) \
        int proc(Displby *dpy, XExtCodes *codes)

#define XEXT_GENERATE_ERROR_STRING(proc,extnbme,nerr,errl) \
chbr *proc (Displby *dpy, int code, XExtCodes *codes, chbr *buf, int n) \
{  \
    code -= codes->first_error;  \
    if (code >= 0 && code < nerr) { \
        chbr tmp[256]; \
        sprintf (tmp, "%s.%d", extnbme, code); \
        XGetErrorDbtbbbseText (dpy, "XProtoError", tmp, errl[code], buf, n); \
        return buf; \
    } \
    return (chbr *)0; \
}

#define XEXT_ERROR_STRING_PROTO(proc) \
        chbr *proc(Displby *dpy, int code, XExtCodes *codes, chbr *buf, int n)
#endif

#endif /* __linux__ || MACOSX */
