/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;
import sun.misc.*;

finbl public clbss XlibWrbpper
{
    stbtic Unsbfe unsbfe = Unsbfe.getUnsbfe();
    // strbnge constbnts
    stbtic finbl int MAXSIZE = 32767;
    stbtic finbl int MINSIZE = 1;

    // define b privbte constructor here to prevent this clbss bnd bll
    // its descendbnts from being crebted
    privbte XlibWrbpper()
    {
    }

/*
   Displby *XOpenDisplby(displby_nbme)
   chbr *displby_nbme;

*/
    public finbl stbtic String eventToString[]=
    {"<none:0>", "<none:1>", "KeyPress", "KeyRelebse", "ButtonPress", "ButtonRelebse",
     "MotionNotify", "EnterNotify", "LebveNotify", "FocusIn", "FocusOut",
     "KeymbpNotify", "Expose", "GrbphicsExpose", "NoExpose", "VisibilityNotify",
     "CrebteNotify", "DestroyNotify", "UnmbpNotify", "MbpNotify", "MbpRequest",
     "RepbrentNotify", "ConfigureNotify", "ConfigureRequest", "GrbvityNotify",
     "ResizeRequest", "CirculbteNotify", "CirculbteRequest", "PropertyNotify",
     "SelectionClebr", "SelectionRequest", "SelectionNotify", "ColormbpNotify",
     "ClientMessbge", "MbppingNotify", "LASTEvent"};

    stbtic nbtive void XFree(long ptr);
    stbtic nbtive void memcpy(long dest_ptr, long src_ptr, long length);
    stbtic nbtive long getAddress(Object o);
    stbtic nbtive void copyIntArrby(long dest_ptr, Object brrby, int size_bytes);
    stbtic nbtive void copyLongArrby(long dest_ptr, Object brrby, int size_bytes);

    /**
     * Gets byte string from str_ptr bnd copies it into byte brrby
     * String should be NULL terminbted
     */
    stbtic nbtive byte[] getStringBytes(long str_ptr);

    stbtic  nbtive long XOpenDisplby(long displby);

    stbtic  nbtive void XCloseDisplby(long displby);

    stbtic  nbtive long XDisplbyString(long displby);

    stbtic  nbtive void XSetCloseDownMode(long displby, int close_mode);

    stbtic  nbtive long DefbultScreen(long displby);

    stbtic nbtive long ScreenOfDisplby(long displby, long screen_number);

    stbtic nbtive int DoesBbckingStore(long screen);

    stbtic nbtive  long DisplbyWidth(long displby, long screen);
    stbtic nbtive  long DisplbyWidthMM(long displby, long screen);

    stbtic nbtive long DisplbyHeight(long displby, long screen);
    stbtic nbtive long DisplbyHeightMM(long displby, long screen);

    stbtic  nbtive long RootWindow(long displby, long screen_number);
    stbtic nbtive int ScreenCount(long displby);


/*
   Window XCrebteWindow(displby, pbrent, x, y, width, height,
   border_width, depth,
   clbss, visubl, vbluembsk, bttributes)
   Displby *displby;
   Window pbrent;
   int x, y;
   unsigned int width, height;
   unsigned int border_width;
   int depth;
   unsigned int clbss;
   Visubl *visubl
   unsigned long vbluembsk;
   XSetWindowAttributes *bttributes;
*/

    stbtic nbtive long XCrebteWindow(long displby, long pbrent, int x,int  y, int width, int height, int border_width, int depth, long wclbss, long visubl, long vbluembsk, long bttributes);

    stbtic nbtive void XDestroyWindow(long displby, long window);

    stbtic nbtive int XGrbbPointer(long displby, long grbb_window,
                                   int owner_events, int event_mbsk, int pointer_mode,
                                   int keybobrd_mode, long confine_to, long cursor, long time);

    stbtic nbtive void XUngrbbPointer(long displby, long time);

    stbtic nbtive int XGrbbKeybobrd(long displby, long grbb_window,
                                    int owner_events, int pointer_mode,
                                    int keybobrd_mode, long time);

    stbtic nbtive void XUngrbbKeybobrd(long displby, long time);

    stbtic nbtive void XGrbbServer(long displby);
    stbtic nbtive void XUngrbbServer(long displby);

/*

void XSetWMProperties(displby, w, window_nbme, icon_nbme,
brgv, brgc, normbl_hints, wm_hints, clbss_hints)
Displby *displby;
Window w;
XTextProperty *window_nbme;
XTextProperty *icon_nbme;
chbr **brgv;
int brgc;
XSizeHints *normbl_hints;
XWMHints *wm_hints;
XClbssHint *clbss_hints;
*/

/*

XMbpWindow(displby, w)
Displby *displby;
Window w;
*/

    stbtic  nbtive void XMbpWindow(long displby, long window);
    stbtic  nbtive void XMbpRbised(long displby, long window);
    stbtic  nbtive void XRbiseWindow(long displby, long window);

    stbtic nbtive void XLowerWindow(long displby, long window);
    stbtic nbtive void XRestbckWindows(long displby, long windows, int length);
    stbtic nbtive void XConfigureWindow(long displby, long window,
            long vblue_mbsk, long vblues);
    stbtic nbtive void XSetInputFocus(long displby, long window);
    stbtic nbtive void XSetInputFocus2(long displby, long window, long time);
    stbtic nbtive long XGetInputFocus(long displby);

/*

XUnmbpWindow(displby, w)
Displby *displby;
Window w;
*/

    stbtic  nbtive void XUnmbpWindow(long displby, long window);




/*
  XSelectInput(displby, w, event_mbsk)
  Displby *displby;
  Window w;
  long event_mbsk;

*/
    stbtic  nbtive void XSelectInput(long displby, long window, long event_mbsk);

    /*
       XNextEvent(displby, event_return)
       Displby *displby;
       XEvent *event_return;

    */

    stbtic  nbtive void XNextEvent(long displby,long ptr);

    /*
     XMbskEvent(displby, event_mbsk, event_return)
           Displby *displby;
           long event_mbsk;
           XEvent *event_return;
     */
    stbtic nbtive void XMbskEvent(long displby, long event_mbsk, long event_return);

    stbtic nbtive void XWindowEvent(long displby, long window, long event_mbsk, long event_return);

    /*
      Bool XFilterEvent(event, w)
           XEvent *event;
           Window w;
    */
    stbtic nbtive boolebn XFilterEvent(long ptr, long window);

/*
     Bool XSupportsLocble()
*/
stbtic nbtive boolebn XSupportsLocble();

/*
     chbr *XSetLocbleModifiers(modifier_list)
           chbr *modifier_list;
*/
stbtic nbtive String XSetLocbleModifiers(String modifier_list);


    stbtic  nbtive int XTrbnslbteCoordinbtes(
                                             long displby, long src_w, long dest_w,
                                             long src_x, long src_y,
                                             long dest_x_return, long dest_y_return,
                                             long child_return);

    /*
       XPeekEvent(displby, event_return)
       Displby *displby;
       XEvent *event_return;

    */

    stbtic  nbtive void XPeekEvent(long displby,long ptr);

/*
  XFlush(displby)
  Displby *displby;
*/

    stbtic nbtive void XFlush(long displby);

/*
  XSync(displby, discbrd)
  Displby *displby;
  Bool discbrd;
*/

    stbtic nbtive void XSync(long displby,int discbrd);


/*    XMoveResizeWindow(displby, w, x, y, width, height)
      Displby *displby;
      Window w;
      int x, y;
      unsigned int width, height;
*/
    stbtic nbtive void XMoveResizeWindow(long displby, long window, int x, int y, int width, int height);
    stbtic nbtive void XResizeWindow(long displby, long window, int width, int height);
    stbtic nbtive void XMoveWindow(long displby, long window, int x, int y);

    /*
     Bool XQueryPointer(displby, w, root_return, child_return,
     root_x_return, root_y_return,
                          win_x_return, win_y_return,
     mbsk_return)
           Displby *displby;
           Window w;
           Window *root_return, *child_return;
           int *root_x_return, *root_y_return;
           int *win_x_return, *win_y_return;
           unsigned int *mbsk_return;
*/

 stbtic nbtive boolebn  XQueryPointer (long displby, long window, long root_return, long child_return, long root_x_return, long root_y_return, long win_x_return, long win_y_return, long mbsk_return);

/*    XFreeCursor(displby, cursor)
           Displby *displby;
           Cursor cursor;
*/

 stbtic nbtive void XFreeCursor(long displby, long cursor);

/*
   XSetWindowBbckground(displby, w, bbckground_pixel)
   Displby *displby;
   Window w;
   unsigned long bbckground_pixel;
*/

    stbtic nbtive void XSetWindowBbckground(long displby, long window, long bbckground_pixel);

    stbtic nbtive int XEventsQueued(long displby, int mode);

/*
  Atom XInternAtom(displby, btom_nbme, only_if_exists)
  Displby *displby;
  chbr *btom_nbme;
  Bool only_if_exists;
*/

    stbtic nbtive int XInternAtoms(long displby, String[] nbmes, boolebn only_if_exists, long btoms);

    stbtic nbtive void SetProperty(long displby, long window, long btom, String str);
    stbtic nbtive String GetProperty(long displby ,long window, long btom);
    stbtic nbtive long InternAtom(long displby, String string, int only_if_exists);
    stbtic nbtive int XGetWindowProperty(long displby, long window, long btom,
                                         long long_offset, long long_length,
                                         long delete, long req_type, long bctubly_type,
                                         long bctubly_formbt, long nitems_ptr,
                                         long bytes_bfter, long dbtb_ptr);
    nbtive stbtic void XChbngePropertyImpl(long displby, long window, long btom,
                                           long type, int formbt, int mode, long dbtb,
                                           int nelements);
    stbtic void XChbngeProperty(long displby, long window, long btom,
                                long type, int formbt, int mode, long dbtb,
                                int nelements) {
        // TODO: hbndling of XChbngePropertyImpl return vblue, if not Success - don't cbche
        if (XPropertyCbche.isCbchingSupported() &&
            XToolkit.windowToXWindow(window) != null &&
            WindowPropertyGetter.isCbchebbleProperty(XAtom.get(btom)) &&
            mode == XConstbnts.PropModeReplbce)
        {
            int length = (formbt / 8) * nelements;
            XPropertyCbche.storeCbche(
                new XPropertyCbche.PropertyCbcheEntry(formbt,
                                                      nelements,
                                                      0,
                                                      dbtb,
                                                      length),
                window,
                XAtom.get(btom));
        }
        XChbngePropertyImpl(displby, window, btom, type, formbt, mode, dbtb, nelements);
    }

    stbtic nbtive void XChbngePropertyS(long displby, long window, long btom,
                                       long type, int formbt, int mode, String vblue);
    stbtic nbtive void XDeleteProperty(long displby, long window, long btom);

    stbtic nbtive void XSetTrbnsientFor(long displby, long window, long trbnsient_for_window);
    stbtic nbtive void XSetWMHints(long displby, long window, long wmhints);
    stbtic nbtive void XGetWMHints(long displby, long window, long wmhints);
    stbtic nbtive long XAllocWMHints();
    stbtic nbtive int XGetPointerMbpping(long displby, long mbp, int buttonNumber);
    stbtic nbtive String XGetDefbult(long displby, String progrbm, String option);
    stbtic nbtive long getScreenOfWindow(long displby, long window);
    stbtic nbtive long XScreenNumberOfScreen(long screen);
    stbtic nbtive int XIconifyWindow(long displby, long window, long screenNumber);
    stbtic nbtive String ServerVendor(long displby);
    stbtic nbtive int VendorRelebse(long displby);
    stbtic nbtive boolebn IsXsunKPBehbvior(long displby);
    stbtic nbtive boolebn IsSunKeybobrd(long displby);
    stbtic nbtive boolebn IsKbnbKeybobrd(long displby);

    stbtic nbtive void XBell(long displby, int percent);

 /*
          Cursor XCrebteFontCursor(displby, shbpe)
           Displby *displby;
           unsigned int shbpe;

           we blwbys pbss int bs shbpe pbrbm.
           perhbps lbter we will need to chbnge type of shbpe to long.
*/

    stbtic nbtive int XCrebteFontCursor(long displby, int shbpe);

/*
     Pixmbp XCrebteBitmbpFromDbtb(displby, d, dbtb, width,
     height)
           Displby *displby;
           Drbwbble d;
           chbr *dbtb;
           unsigned int width, height;
*/

    stbtic nbtive long XCrebteBitmbpFromDbtb(long displby, long drbwbble, long dbtb, int width, int height);

 /*
      XFreePixmbp(displby, pixmbp)
           Displby *displby;
           Pixmbp pixmbp;
  */

   stbtic nbtive void XFreePixmbp(long displby, long pixmbp);

  /*
     Cursor XCrebtePixmbpCursor(displby, source, mbsk,
     foreground_color, bbckground_color, x, y)
           Displby *displby;
           Pixmbp source;
           Pixmbp mbsk;
           XColor *foreground_color;
           XColor *bbckground_color;
           unsigned int x, y;
    */
   stbtic nbtive long XCrebtePixmbpCursor(long displby, long source, long mbsk, long fore, long bbck, int x, int y);


    /*
         Stbtus XQueryBestCursor(displby, d, width, height,
     width_return, height_return)
           Displby *displby;
           Drbwbble d;
           unsigned int width, height;
           unsigned int *width_return, *height_return;

    */

    stbtic nbtive boolebn XQueryBestCursor(long displby, long drbwbble, int width, int height, long width_return, long height_return);


    /*
     Stbtus XAllocColor(displby, colormbp, screen_in_out)
           Displby *displby;
           Colormbp colormbp;
           XColor *screen_in_out;
  */

    stbtic nbtive boolebn XAllocColor( long displby, long colormbp, long screen_in_out);


    stbtic nbtive long SetToolkitErrorHbndler();
    stbtic nbtive void XSetErrorHbndler(long hbndler);
    stbtic nbtive int CbllErrorHbndler(long hbndler, long displby, long event_ptr);

 /*
      XChbngeWindowAttributes(displby, w, vbluembsk, bttributes)
           Displby *displby;
           Window w;
           unsigned long vbluembsk;
           XSetWindowAttributes *bttributes;
  */

    stbtic nbtive void XChbngeWindowAttributes(long displby, long window, long vbluembsk, long bttributes);
    stbtic nbtive int XGetWindowAttributes(long displby, long window, long bttr_ptr);
    stbtic nbtive int XGetGeometry(long displby, long drbwbble, long root_return, long x_return, long y_return,
                                   long width_return, long height_return, long border_width_return, long depth_return);

    stbtic nbtive int XGetWMNormblHints(long displby, long window, long hints, long supplied_return);
    stbtic nbtive void XSetWMNormblHints(long displby, long window, long hints);
    stbtic nbtive void XSetMinMbxHints(long displby, long window, int x, int y, int width, int height, long flbgs);
    stbtic nbtive long XAllocSizeHints();

    stbtic nbtive int XSendEvent(long displby, long window, boolebn propbgbte, long event_mbsk, long event);
    stbtic nbtive void XPutBbckEvent(long displby, long event);
    stbtic nbtive int XQueryTree(long displby, long window, long root_return, long pbrent_return, long children_return, long nchildren_return);
    stbtic nbtive long XGetVisublInfo(long displby, long vinfo_mbsk, long vinfo_templbte, long nitems_return);
    stbtic nbtive void XRepbrentWindow(long displby, long window, long pbrent, int x, int y);

    stbtic nbtive void XConvertSelection(long displby, long selection,
                                         long tbrget, long property,
                                         long requestor, long time);

    stbtic nbtive void XSetSelectionOwner(long displby, long selection,
                                          long owner, long time);

    stbtic nbtive long XGetSelectionOwner(long displby, long selection);

    stbtic nbtive String XGetAtomNbme(long displby, long btom);

    stbtic nbtive long XMbxRequestSize(long displby);


    stbtic nbtive long XCrebtePixmbp(long displby, long drbwbble, int width, int height, int depth);
    stbtic nbtive long XCrebteImbge(long displby, long visubl_ptr, int depth, int formbt,
                                    int offset, long dbtb, int width, int height, int bitmbp_pbd,
                                    int bytes_per_line);
    stbtic nbtive void XDestroyImbge(long imbge);
    stbtic nbtive void XPutImbge(long displby, long drbwbble, long gc, long imbge,
                                 int src_x, int src_y, int dest_x, int dest_y,
                                 int width, int height);
    stbtic nbtive long XCrebteGC(long displby, long drbwbble, long vbluembsk, long vblues);
    stbtic nbtive void XFreeGC(long displby, long gc);
    stbtic nbtive void XSetWindowBbckgroundPixmbp(long displby, long window, long pixmbp);
    stbtic nbtive void XClebrWindow(long displby, long window);
    stbtic nbtive int XGetIconSizes(long displby, long window, long ret_sizes, long ret_count);
    stbtic nbtive int XdbeQueryExtension(long displby, long mbjor_version_return,
                                         long minor_version_return);
    stbtic nbtive boolebn XQueryExtension(long displby, String nbme, long mop_return,
                                         long feve_return, long err_return);
    stbtic nbtive boolebn IsKeypbdKey(long keysym);
    stbtic nbtive long XdbeAllocbteBbckBufferNbme(long displby, long window, int swbp_bction);
    stbtic nbtive int XdbeDebllocbteBbckBufferNbme(long displby, long buffer);
    stbtic nbtive int XdbeBeginIdiom(long displby);
    stbtic nbtive int XdbeEndIdiom(long displby);
    stbtic nbtive int XdbeSwbpBuffers(long displby, long swbp_info, int num_windows);

    stbtic nbtive void XQueryKeymbp(long displby, long vector);
    stbtic nbtive long XKeycodeToKeysym(long displby, int keycode, int index);

    stbtic nbtive int XKeysymToKeycode(long displby, long keysym);

    // xkb-relbted
    stbtic nbtive int XkbGetEffectiveGroup(long displby);
    stbtic nbtive long XkbKeycodeToKeysym(long displby, int keycode, int group, int level);
    stbtic nbtive void XkbSelectEvents(long displby, long device, long bits_to_chbnge, long vblues_for_bits);
    stbtic nbtive void XkbSelectEventDetbils(long displby, long device, long event_type,
                                              long bits_to_chbnge, long vblues_for_bits);
    stbtic nbtive boolebn XkbQueryExtension(long displby, long opcode_rtrn, long event_rtrn,
              long error_rtrn, long mbjor_in_out, long minor_in_out);
    stbtic nbtive boolebn XkbLibrbryVersion(long lib_mbjor_in_out, long lib_minor_in_out);
    stbtic nbtive long XkbGetMbp(long displby, long which, long device_spec);
    stbtic nbtive long XkbGetUpdbtedMbp(long displby, long which, long xkb);
    stbtic nbtive void XkbFreeKeybobrd(long xkb, long which, boolebn free_bll);
    stbtic nbtive boolebn XkbTrbnslbteKeyCode(long xkb, int keycode, long mods, long mods_rtrn, long keysym_rtrn);
    stbtic nbtive void XkbSetDetectbbleAutoRepebt(long displby, boolebn detectbble);


    stbtic nbtive void XConvertCbse(long keysym,
                                    long keysym_lowercbse,
                                    long keysym_uppercbse);

    stbtic nbtive long XGetModifierMbpping(long displby);
    stbtic nbtive void XFreeModifiermbp(long keymbp);
    stbtic nbtive void XRefreshKeybobrdMbpping(long event);


    stbtic nbtive void XChbngeActivePointerGrbb(long displby, int mbsk,
                                                long cursor, long time);

    /*
      int (*XSynchronize(Displby *displby, Bool onoff))();
          displby   Specifies the connection to the X server.
          onoff     Specifies b Boolebn vblue thbt indicbtes whether to enbble or disbble synchronizbtion.
     */
    public stbtic nbtive int XSynchronize(long displby, boolebn onoff);

    /**
     * Extrbcts bn X event thbt cbn be processed in b secondbry loop.
     * Should only be cblled on the toolkit threbd.
     * Returns fblse if this secondbry event wbs terminbted.
     */
    stbtic nbtive boolebn XNextSecondbryLoopEvent(long displby, long ptr);
    /**
     * Terminbtes the topmost secondbry loop (if bny).
     * Should never be cblled on the toolkit threbd.
     */
    stbtic nbtive void ExitSecondbryLoop();

    /**
     * Cblls XTextPropertyToStringList on the specified byte brrby bnd returns
     * the brrby of strings.
     */
    stbtic nbtive String[] XTextPropertyToStringList(byte[] bytes, long encoding_btom);

    /**
     * XSHAPE extension support.
     */
    stbtic nbtive boolebn XShbpeQueryExtension(long displby, long event_bbse_return, long error_bbse_return);
    stbtic nbtive void SetRectbngulbrShbpe(long displby, long window,
            int lox, int loy, int hix, int hiy,
            sun.jbvb2d.pipe.Region region);
    /** Ebch int in the bitmbp brrby is one pixel with b 32-bit color:
     *  R, G, B, bnd Alphb.
     */
    stbtic nbtive void SetBitmbpShbpe(long displby, long window,
             int width, int height, int[] bitmbp);

    stbtic nbtive void SetZOrder(long displby, long window, long bbove);

/* Globbl memory breb used for X lib pbrbmeter pbssing */

    finbl stbtic long lbuffer = unsbfe.bllocbteMemory(64);  // brrby to hold 8 longs
    finbl stbtic long ibuffer = unsbfe.bllocbteMemory(32);  // brrby to hold 8 ints

    stbtic finbl long lbrg1 = lbuffer;
    stbtic finbl long lbrg2 = lbrg1+8;
    stbtic finbl long lbrg3 = lbrg2+8;
    stbtic finbl long lbrg4 = lbrg3+8;
    stbtic finbl long lbrg5 = lbrg4+8;
    stbtic finbl long lbrg6 = lbrg5+8;
    stbtic finbl long lbrg7 = lbrg6+8;
    stbtic finbl long lbrg8 = lbrg7+8;

    stbtic finbl long ibrg1 = ibuffer;
    stbtic finbl long ibrg2 = ibrg1+4;
    stbtic finbl long ibrg3 = ibrg2+4;
    stbtic finbl long ibrg4 = ibrg3+4;
    stbtic finbl long ibrg5 = ibrg4+4;
    stbtic finbl long ibrg6 = ibrg5+4;
    stbtic finbl long ibrg7 = ibrg6+4;
    stbtic finbl long ibrg8 = ibrg7+4;


    stbtic int dbtbModel;
    stbtic finbl boolebn isBuildInternbl;

    stbtic {
        String dbtbModelProp = AccessController.doPrivileged(
            new GetPropertyAction("sun.brch.dbtb.model"));
        try {
            dbtbModel = Integer.pbrseInt(dbtbModelProp);
        } cbtch (Exception e) {
            dbtbModel = 32;
        }

        isBuildInternbl = getBuildInternbl();

//      System.lobdLibrbry("mbwt");
    }

    stbtic int getDbtbModel() {
        return dbtbModel;
    }

    stbtic String hintsToString(long flbgs) {
        StringBuffer buf = new StringBuffer();
        if ((flbgs & XUtilConstbnts.PMbxSize) != 0) {
            buf.bppend("PMbxSize ");
        }
        if ((flbgs & XUtilConstbnts.PMinSize) != 0) {
            buf.bppend("PMinSize ");
        }
        if ((flbgs & XUtilConstbnts.USSize) != 0) {
            buf.bppend("USSize ");
        }
        if ((flbgs & XUtilConstbnts.USPosition) != 0) {
            buf.bppend("USPosition ");
        }
        if ((flbgs & XUtilConstbnts.PPosition) != 0) {
            buf.bppend("PPosition ");
        }
        if ((flbgs & XUtilConstbnts.PSize) != 0) {
            buf.bppend("PSize ");
        }
        if ((flbgs & XUtilConstbnts.PWinGrbvity) != 0) {
            buf.bppend("PWinGrbvity ");
        }
        return buf.toString();
    }
    stbtic String getEventToString( int type ) {
        if( (type >= 0) && (type < eventToString.length)) {
            return eventToString[type];
        }else if( type == XToolkit.getXKBBbseEventCode() ) {
            //XXX TODO vbrious xkb types
            return "XkbEvent";
        }
        return eventToString[0];
    }

    privbte stbtic boolebn getBuildInternbl() {
        String jbvbVersion = AccessController.doPrivileged(
                                 new GetPropertyAction("jbvb.version"));
        return jbvbVersion != null && jbvbVersion.contbins("internbl");
    }

    stbtic nbtive void PrintXErrorEvent(long displby, long event_ptr);
}
