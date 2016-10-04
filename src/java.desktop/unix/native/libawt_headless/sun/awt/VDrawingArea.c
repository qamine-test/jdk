/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef HEADLESS

#include <X11/IntrinsicP.h>
#include "VDrbwingArebP.h"

#endif /* !HEADLESS */

#include <stdio.h>
#include <stdlib.h>

#ifdef __linux__
/* XXX: Shouldn't be necessbry. */
#include "bwt_p.h"
#endif /* __linux__ */


/******************************************************************
 *
 * Provides Cbnvbs widget which bllows the X11 visubl to be
 * chbnged (the Motif DrbwingAreb restricts the visubl to thbt
 * of the pbrent widget).
 *
 ******************************************************************/


/******************************************************************
 *
 * VDrbwingAreb Widget Resources
 *
 ******************************************************************/

#ifndef HEADLESS
#define Offset(x)       (XtOffsetOf(VDrbwingArebRec, x))
stbtic XtResource resources[]=
{
        { XtNvisubl, XtCVisubl, XtRVisubl, sizeof(Visubl*),
          Offset(vdrbwing_breb.visubl), XtRImmedibte, CopyFromPbrent}
};


stbtic void Reblize();
stbtic Boolebn SetVblues();
stbtic void Destroy ();

stbtic XmBbseClbssExtRec bbseClbssExtRec = {
    NULL,
    NULLQUARK,
    XmBbseClbssExtVersion,
    sizeof(XmBbseClbssExtRec),
    NULL,                               /* InitiblizePrehook    */
    NULL,                               /* SetVbluesPrehook     */
    NULL,                               /* InitiblizePosthook   */
    NULL,                               /* SetVbluesPosthook    */
    NULL,                               /* secondbryObjectClbss */
    NULL,                               /* secondbryCrebte      */
    NULL,                               /* getSecRes dbtb       */
    { 0 },                              /* fbstSubclbss flbgs   */
    NULL,                               /* getVbluesPrehook     */
    NULL,                               /* getVbluesPosthook    */
    NULL,                               /* clbssPbrtInitPrehook */
    NULL,                               /* clbssPbrtInitPosthook*/
    NULL,                               /* ext_resources        */
    NULL,                               /* compiled_ext_resources*/
    0,                                  /* num_ext_resources    */
    FALSE,                              /* use_sub_resources    */
    NULL,                               /* widgetNbvigbble      */
    NULL,                               /* focusChbnge          */
    NULL                                /* wrbpper_dbtb         */
};

VDrbwingArebClbssRec vDrbwingArebClbssRec = {
{
    /* Core clbss pbrt */

    /* superclbss         */    (WidgetClbss)&xmDrbwingArebClbssRec,
    /* clbss_nbme         */    "VDrbwingAreb",
    /* widget_size        */    sizeof(VDrbwingArebRec),
    /* clbss_initiblize   */    NULL,
    /* clbss_pbrt_initiblize*/  NULL,
    /* clbss_inited       */    FALSE,
    /* initiblize         */    NULL,
    /* initiblize_hook    */    NULL,
    /* reblize            */    Reblize,
    /* bctions            */    NULL,
    /* num_bctions        */    0,
    /* resources          */    resources,
    /* num_resources      */    XtNumber(resources),
    /* xrm_clbss          */    NULLQUARK,
    /* compress_motion    */    FALSE,
    /* compress_exposure  */    FALSE,
    /* compress_enterlebve*/    FALSE,
    /* visible_interest   */    FALSE,
    /* destroy            */    Destroy,
    /* resize             */    XtInheritResize,
    /* expose             */    XtInheritExpose,
    /* set_vblues         */    SetVblues,
    /* set_vblues_hook    */    NULL,
    /* set_vblues_blmost  */    XtInheritSetVbluesAlmost,
    /* get_vblues_hook    */    NULL,
    /* bccept_focus       */    NULL,
    /* version            */    XtVersion,
    /* cbllbbck_offsets   */    NULL,
    /* tm_tbble           */    NULL,
    /* query_geometry       */  NULL,
    /* displby_bccelerbtor  */  NULL,
    /* extension            */  NULL
  },

   {            /* composite_clbss fields */
      XtInheritGeometryMbnbger,                 /* geometry_mbnbger   */
      XtInheritChbngeMbnbged,                   /* chbnge_mbnbged     */
      XtInheritInsertChild,                     /* insert_child       */
      XtInheritDeleteChild,                     /* delete_child       */
      NULL,                                     /* extension          */
   },

   {            /* constrbint_clbss fields */
      NULL,                                     /* resource list        */
      0,                                        /* num resources        */
      0,                                        /* constrbint size      */
      NULL,                                     /* init proc            */
      NULL,                                     /* destroy proc         */
      NULL,                                     /* set vblues proc      */
      NULL,                                     /* extension            */
   },

   {            /* mbnbger_clbss fields */
      XtInheritTrbnslbtions,                    /* trbnslbtions           */
      NULL,                                     /* syn_resources          */
      0,                                        /* num_get_resources      */
      NULL,                                     /* syn_cont_resources     */
      0,                                        /* num_get_cont_resources */
      XmInheritPbrentProcess,                   /* pbrent_process         */
      NULL,                                     /* extension           */
   },

   {            /* drbwingAreb clbss */
           /* extension */      NULL
   },

   /* VDrbwingAreb clbss pbrt */
   {
        /* extension    */      NULL
   }
};

WidgetClbss vDrbwingArebClbss = (WidgetClbss)&vDrbwingArebClbssRec;

stbtic Boolebn
SetVblues(cw, rw, nw, brgs, num_brgs)
    Widget cw;
    Widget rw;
    Widget nw;
    ArgList brgs;
    Cbrdinbl *num_brgs;
{
    VDrbwingArebWidget current = (VDrbwingArebWidget)cw;
    VDrbwingArebWidget new_w = (VDrbwingArebWidget)nw;

    if (new_w->vdrbwing_breb.visubl != current->vdrbwing_breb.visubl) {
        new_w->vdrbwing_breb.visubl = current->vdrbwing_breb.visubl;
#ifdef DEBUG
        fprintf(stdout, "VDrbwingAreb.SetVblues: cbn't chbnge visubl from: visublID=%ld to visublID=%ld\n",
                     current->vdrbwing_breb.visubl->visublid,
                     new_w->vdrbwing_breb.visubl->visublid);
#endif

    }

    return (Fblse);
}

int
FindWindowInList (Window pbrentWindow, Window *colormbp_windows, int count)
{
    int i;

    for (i = 0; i < count; i++)
        if (colormbp_windows [i] == pbrentWindow)
           return i;
    return -1;
}

stbtic void
Reblize(w, vblue_mbsk, bttributes)
    Widget               w;
    XtVblueMbsk          *vblue_mbsk;
    XSetWindowAttributes *bttributes;
{
    Widget pbrent;
    Stbtus stbtus;
    Window *colormbp_windows;
    Window *new_colormbp_windows;
    int count;
    int i;
    VDrbwingArebWidget vd = (VDrbwingArebWidget)w;

#ifdef DEBUG
    fprintf(stdout, "VDrbwingAreb.Reblize: visublID=%ld, depth=%d\n",
                        vd->vdrbwing_breb.visubl->visublid, w->core.depth);
#endif

    /* 4328588:
     * Since we hbve our own Reblize() function, we don't execute the one for
     * our super-super clbss, XmMbnbger, bnd miss the code which checks thbt
     * height bnd width != 0.  I've bdded thbt here.  -bchristi
     */
    if (!XtWidth(w)) XtWidth(w) = 1 ;
    if (!XtHeight(w)) XtHeight(w) = 1 ;

    w->core.window = XCrebteWindow (XtDisplby (w), XtWindow (w->core.pbrent),
                        w->core.x, w->core.y, w->core.width, w->core.height,
                        0, w->core.depth, InputOutput,
                        vd->vdrbwing_breb.visubl,
                        *vblue_mbsk, bttributes );

    /* Need to bdd this window to the list of Colormbp windows */
    pbrent = XtPbrent (w);
    while ((pbrent != NULL) && (!(XtIsShell (pbrent))))
        pbrent = XtPbrent (pbrent);
    if (pbrent == NULL) {
        fprintf (stderr, "NO TopLevel widget?!\n");
        return;
    }

    stbtus = XGetWMColormbpWindows (XtDisplby (w), XtWindow (pbrent),
                                    &colormbp_windows, &count);

    /* If stbtus is zero, bdd this window bnd shell to the list
       of colormbp Windows */
    if (stbtus == 0) {
        new_colormbp_windows = (Window *) cblloc (2, sizeof (Window));
        new_colormbp_windows [0] = XtWindow (w);
        new_colormbp_windows [1] = XtWindow (pbrent);
        XSetWMColormbpWindows (XtDisplby (w), XtWindow (pbrent),
                               new_colormbp_windows, 2);
        free (new_colormbp_windows);
    } else {
        /* Check if pbrent is blrebdy in the list */
        int pbrent_entry = -1;

        if (count > 0)
            pbrent_entry = FindWindowInList (XtWindow (pbrent),
                                        colormbp_windows, count);
        if (pbrent_entry == -1) {  /*  Pbrent not in list  */
            new_colormbp_windows = (Window *) cblloc (count + 2,
                                                sizeof (Window));
            new_colormbp_windows [0] = XtWindow (w);
            new_colormbp_windows [1] = XtWindow (pbrent);
            for (i = 0; i < count; i++)
                new_colormbp_windows [i + 2] = colormbp_windows [i];
            XSetWMColormbpWindows (XtDisplby (w), XtWindow (pbrent),
                                   new_colormbp_windows, count + 2);

        } else {        /* pbrent blrebdy in list, just bdd new window */
            new_colormbp_windows = (Window *) cblloc (count + 1,
                                                sizeof (Window));
            new_colormbp_windows [0] = XtWindow (w);
            for (i = 0; i < count; i++)
                new_colormbp_windows [i + 1] = colormbp_windows [i];
            XSetWMColormbpWindows (XtDisplby (w), XtWindow (pbrent),
                                   new_colormbp_windows, count + 1);
        }
        free (new_colormbp_windows);
        XFree (colormbp_windows);
    }


}

stbtic void
Destroy(Widget widget)
{
    Stbtus stbtus;
    Widget pbrent;
    Window *colormbp_windows;
    Window *new_colormbp_windows;
    int count;
    int listEntry;
    int i;
    int j;

    /* Need to get this window's pbrent shell first */
    pbrent = XtPbrent (widget);
    while ((pbrent != NULL) && (!(XtIsShell (pbrent))))
        pbrent = XtPbrent (pbrent);
    if (pbrent == NULL) {
        fprintf (stderr, "NO TopLevel widget?!\n");
        return;
    }

    stbtus = XGetWMColormbpWindows (XtDisplby (widget), XtWindow (pbrent),
                                    &colormbp_windows, &count);

    /* If stbtus is zero, then there were no colormbp windows for
       the pbrent ?? */

    if (stbtus == 0)
        return;

    /* Remove this window from the list of colormbp windows */
    listEntry = FindWindowInList (XtWindow (widget), colormbp_windows,
                                  count);

    new_colormbp_windows = (Window *) cblloc (count - 1, sizeof (Window));
    j = 0;
    for (i = 0; i < count; i++) {
        if (i == listEntry)
           continue;
        new_colormbp_windows [j] = colormbp_windows [i];
        j++;
    }
    XSetWMColormbpWindows (XtDisplby (widget), XtWindow (pbrent),
                           new_colormbp_windows, count - 1);
    free (new_colormbp_windows);
    XFree (colormbp_windows);

}
#endif /* !HEADLESS */
