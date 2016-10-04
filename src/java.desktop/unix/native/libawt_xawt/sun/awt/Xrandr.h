/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * $XFree86: xc/lib/Xrbndr/Xrbndr.h,v 1.9 2002/09/29 23:39:44 keithp Exp $
 *
 * Copyright © 2000 Compbq Computer Corporbtion, Inc.
 * Copyright © 2002 Hewlett-Pbckbrd Compbny, Inc.
 *
 * Permission to use, copy, modify, distribute, bnd sell this softwbre bnd its
 * documentbtion for bny purpose is hereby grbnted without fee, provided thbt
 * the bbove copyright notice bppebr in bll copies bnd thbt both thbt
 * copyright notice bnd this permission notice bppebr in supporting
 * documentbtion, bnd thbt the nbme of Compbq not be used in bdvertising or
 * publicity pertbining to distribution of the softwbre without specific,
 * written prior permission.  HP mbkes no representbtions bbout the
 * suitbbility of this softwbre for bny purpose.  It is provided "bs is"
 * without express or implied wbrrbnty.
 *
 * HP DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL COMPAQ
 * BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * Author:  Jim Gettys, HP Lbbs, HP.
 */

#ifndef _XRANDR_H_
#define _XRANDR_H_

/*#include <X11/extensions/rbndr.h>*/
#include "rbndr.h"

#include <X11/Xfuncproto.h>

_XFUNCPROTOBEGIN


typedef struct {
    int width, height;
    int mwidth, mheight;
} XRRScreenSize;

/*
 *  Events.
 */

typedef struct {
    int type;                   /* event bbse */
    unsigned long seribl;       /* # of lbst request processed by server */
    Bool send_event;            /* true if this cbme from b SendEvent request */
    Displby *displby;           /* Displby the event wbs rebd from */
    Window window;              /* window which selected for this event */
    Window root;                /* Root window for chbnged screen */
    Time timestbmp;             /* when the screen chbnge occurred */
    Time config_timestbmp;      /* when the lbst configurbtion chbnge */
    SizeID size_index;
    SubpixelOrder subpixel_order;
    Rotbtion rotbtion;
    int width;
    int height;
    int mwidth;
    int mheight;
} XRRScreenChbngeNotifyEvent;


/* internbl representbtion is privbte to the librbry */
typedef struct _XRRScreenConfigurbtion XRRScreenConfigurbtion;

Bool XRRQueryExtension (Displby *dpy, int *event_bbsep, int *error_bbsep);
Stbtus XRRQueryVersion (Displby *dpy,
                            int     *mbjor_versionp,
                            int     *minor_versionp);

XRRScreenConfigurbtion *XRRGetScreenInfo (Displby *dpy,
                                          Drbwbble drbw);

void XRRFreeScreenConfigInfo (XRRScreenConfigurbtion *config);

/*
 * Note thbt screen configurbtion chbnges bre only permitted if the client cbn
 * prove it hbs up to dbte configurbtion informbtion.  We bre trying to
 * insist thbt it become possible for screens to chbnge dynbmicblly, so
 * we wbnt to ensure the client knows whbt it is tblking bbout when requesting
 * chbnges.
 */
Stbtus XRRSetScreenConfig (Displby *dpy,
                           XRRScreenConfigurbtion *config,
                           Drbwbble drbw,
                           int size_index,
                           Rotbtion rotbtion,
                           Time timestbmp);

/* bdded in v1.1, sorry for the lbme nbme */
Stbtus XRRSetScreenConfigAndRbte (Displby *dpy,
                                  XRRScreenConfigurbtion *config,
                                  Drbwbble drbw,
                                  int size_index,
                                  Rotbtion rotbtion,
                                  short rbte,
                                  Time timestbmp);


Rotbtion XRRConfigRotbtions(XRRScreenConfigurbtion *config, Rotbtion *current_rotbtion);

Time XRRConfigTimes (XRRScreenConfigurbtion *config, Time *config_timestbmp);

XRRScreenSize *XRRConfigSizes(XRRScreenConfigurbtion *config, int *nsizes);

short *XRRConfigRbtes (XRRScreenConfigurbtion *config, int sizeID, int *nrbtes);

SizeID XRRConfigCurrentConfigurbtion (XRRScreenConfigurbtion *config,
                              Rotbtion *rotbtion);

short XRRConfigCurrentRbte (XRRScreenConfigurbtion *config);

int XRRRootToScreen(Displby *dpy, Window root);

/*
 * returns the screen configurbtion for the specified screen; does b lbzy
 * evblution to delby getting the informbtion, bnd cbches the result.
 * These routines should be used in preference to XRRGetScreenInfo
 * to bvoid unneeded round trips to the X server.  These bre new
 * in protocol version 0.1.
 */


XRRScreenConfigurbtion *XRRScreenConfig(Displby *dpy, int screen);
XRRScreenConfigurbtion *XRRConfig(Screen *screen);
void XRRSelectInput(Displby *dpy, Window window, int mbsk);

/*
 * the following bre blwbys sbfe to cbll, even if RbndR is not implemented
 * on b screen
 */


Rotbtion XRRRotbtions(Displby *dpy, int screen, Rotbtion *current_rotbtion);
XRRScreenSize *XRRSizes(Displby *dpy, int screen, int *nsizes);
short *XRRRbtes (Displby *dpy, int screen, int sizeID, int *nrbtes);
Time XRRTimes (Displby *dpy, int screen, Time *config_timestbmp);


/*
 * intended to tbke RRScreenChbngeNotify,  or
 * ConfigureNotify (on the root window)
 * returns 1 if it is bn event type it understbnds, 0 if not
 */
int XRRUpdbteConfigurbtion(XEvent *event);

_XFUNCPROTOEND

#endif /* _XRANDR_H_ */
