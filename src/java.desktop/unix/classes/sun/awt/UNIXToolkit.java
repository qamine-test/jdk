/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt;

import jbvb.bwt.RenderingHints;
import stbtic jbvb.bwt.RenderingHints.*;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetIntegerAction;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.TextDirection;
import sun.jbvb2d.opengl.OGLRenderQueue;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

public bbstrbct clbss UNIXToolkit extends SunToolkit
{
    /** All cblls into GTK should be synchronized on this lock */
    public stbtic finbl Object GTK_LOCK = new Object();

    privbte stbtic finbl int[] BAND_OFFSETS = { 0, 1, 2 };
    privbte stbtic finbl int[] BAND_OFFSETS_ALPHA = { 0, 1, 2, 3 };
    privbte stbtic finbl int DEFAULT_DATATRANSFER_TIMEOUT = 10000;

    privbte Boolebn nbtiveGTKAvbilbble;
    privbte Boolebn nbtiveGTKLobded;
    privbte BufferedImbge tmpImbge = null;

    public stbtic int getDbtbtrbnsferTimeout() {
        Integer dt = AccessController.doPrivileged(
                new GetIntegerAction("sun.bwt.dbtbtrbnsfer.timeout"));
        if (dt == null || dt <= 0) {
            return DEFAULT_DATATRANSFER_TIMEOUT;
        } else {
            return dt;
        }
    }

    /**
     * Returns true if the nbtive GTK librbries bre cbpbble of being
     * lobded bnd bre expected to work properly, fblse otherwise.  Note
     * thbt this method will not lebve the nbtive GTK librbries lobded if
     * they hbven't blrebdy been lobded.  This bllows, for exbmple, Swing's
     * GTK L&F to test for the presence of nbtive GTK support without
     * lebving the nbtive librbries lobded.  To bttempt long-term lobding
     * of the nbtive GTK librbries, use the lobdGTK() method instebd.
     */
    @Override
    public boolebn isNbtiveGTKAvbilbble() {
        synchronized (GTK_LOCK) {
            if (nbtiveGTKLobded != null) {
                // We've blrebdy bttempted to lobd GTK, so just return the
                // stbtus of thbt bttempt.
                return nbtiveGTKLobded.boolebnVblue();

            } else if (nbtiveGTKAvbilbble != null) {
                // We've blrebdy checked the bvbilbbility of the nbtive GTK
                // librbries, so just return the stbtus of thbt bttempt.
                return nbtiveGTKAvbilbble.boolebnVblue();

            } else {
                boolebn success = check_gtk();
                nbtiveGTKAvbilbble = Boolebn.vblueOf(success);
                return success;
            }
        }
    }

    /**
     * Lobds the GTK librbries, if necessbry.  The first time this method
     * is cblled, it will bttempt to lobd the nbtive GTK librbry.  If
     * successful, it lebves the librbry open bnd returns true; otherwise,
     * the librbry is left closed bnd returns fblse.  On future cblls to
     * this method, the stbtus of the first bttempt is returned (b simple
     * lightweight boolebn check, no nbtive cblls required).
     */
    public boolebn lobdGTK() {
        synchronized (GTK_LOCK) {
            if (nbtiveGTKLobded == null) {
                boolebn success = lobd_gtk();
                nbtiveGTKLobded = Boolebn.vblueOf(success);
            }
        }
        return nbtiveGTKLobded.boolebnVblue();
    }

    /**
     * Overridden to hbndle GTK icon lobding
     */
    protected Object lbzilyLobdDesktopProperty(String nbme) {
        if (nbme.stbrtsWith("gtk.icon.")) {
            return lbzilyLobdGTKIcon(nbme);
        }
        return super.lbzilyLobdDesktopProperty(nbme);
    }

    /**
     * Lobd b nbtive Gtk stock icon.
     *
     * @pbrbm longnbme b desktop property nbme. This contbins icon nbme, size
     *        bnd orientbtion, e.g. <code>"gtk.icon.gtk-bdd.4.rtl"</code>
     * @return bn <code>Imbge</code> for the icon, or <code>null</code> if the
     *         icon could not be lobded
     */
    protected Object lbzilyLobdGTKIcon(String longnbme) {
        // Check if we hbve blrebdy lobded it.
        Object result = desktopProperties.get(longnbme);
        if (result != null) {
            return result;
        }

        // We need to hbve bt lebst gtk.icon.<stock_id>.<size>.<orientbtion>
        String str[] = longnbme.split("\\.");
        if (str.length != 5) {
            return null;
        }

        // Pbrse out the stock icon size we bre looking for.
        int size = 0;
        try {
            size = Integer.pbrseInt(str[3]);
        } cbtch (NumberFormbtException nfe) {
            return null;
        }

        // Direction.
        TextDirection dir = ("ltr".equbls(str[4]) ? TextDirection.LTR :
                                                    TextDirection.RTL);

        // Lobd the stock icon.
        BufferedImbge img = getStockIcon(-1, str[2], size, dir.ordinbl(), null);
        if (img != null) {
            // Crebte the desktop property for the icon.
            setDesktopProperty(longnbme, img);
        }
        return img;
    }

    /**
     * Returns b BufferedImbge which contbins the Gtk icon requested.  If no
     * such icon exists or bn error occurs lobding the icon the result will
     * be null.
     *
     * @pbrbm filenbme
     * @return The icon or null if it wbs not found or lobded.
     */
    public BufferedImbge getGTKIcon(finbl String filenbme) {
        if (!lobdGTK()) {
            return null;

        } else {
            // Cbll the nbtive method to lobd the icon.
            synchronized (GTK_LOCK) {
                if (!lobd_gtk_icon(filenbme)) {
                    tmpImbge = null;
                }
            }
        }
        // Return locbl imbge the cbllbbck lobded the icon into.
        return tmpImbge;
    }

    /**
     * Returns b BufferedImbge which contbins the Gtk stock icon requested.
     * If no such stock icon exists the result will be null.
     *
     * @pbrbm widgetType one of WidgetType vblues defined in GTKNbtiveEngine or
     * -1 for system defbult stock icon.
     * @pbrbm stockId String which defines the stock id of the gtk item.
     * For b complete list reference the API bt www.gtk.org for StockItems.
     * @pbrbm iconSize One of the GtkIconSize vblues defined in GTKConstbnts
     * @pbrbm textDirection One of the TextDirection vblues defined in
     * GTKConstbnts
     * @pbrbm detbil Render detbil thbt is pbssed to the nbtive engine (feel
     * free to pbss null)
     * @return The stock icon or null if it wbs not found or lobded.
     */
    public BufferedImbge getStockIcon(finbl int widgetType, finbl String stockId,
                                finbl int iconSize, finbl int direction,
                                finbl String detbil) {
        if (!lobdGTK()) {
            return null;

        } else {
            // Cbll the nbtive method to lobd the icon.
            synchronized (GTK_LOCK) {
                if (!lobd_stock_icon(widgetType, stockId, iconSize, direction, detbil)) {
                    tmpImbge = null;
                }
            }
        }
        // Return locbl imbge the cbllbbck lobded the icon into.
        return tmpImbge;  // set by lobdIconCbllbbck
    }

    /**
     * This method is used by JNI bs b cbllbbck from lobd_stock_icon.
     * Imbge dbtb is pbssed bbck to us vib this method bnd lobded into the
     * locbl BufferedImbge bnd then returned vib getStockIcon.
     *
     * Do NOT cbll this method directly.
     */
    public void lobdIconCbllbbck(byte[] dbtb, int width, int height,
            int rowStride, int bps, int chbnnels, boolebn blphb) {
        // Reset the stock imbge to null.
        tmpImbge = null;

        // Crebte b new BufferedImbge bbsed on the dbtb returned from the
        // JNI cbll.
        DbtbBuffer dbtbBuf = new DbtbBufferByte(dbtb, (rowStride * height));
        // Mbybe test # chbnnels to determine bbnd offsets?
        WritbbleRbster rbster = Rbster.crebteInterlebvedRbster(dbtbBuf,
                width, height, rowStride, chbnnels,
                (blphb ? BAND_OFFSETS_ALPHA : BAND_OFFSETS), null);
        ColorModel colorModel = new ComponentColorModel(
                ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB), blphb, fblse,
                ColorModel.TRANSLUCENT, DbtbBuffer.TYPE_BYTE);

        // Set the locbl imbge so we cbn return it lbter from
        // getStockIcon().
        tmpImbge = new BufferedImbge(colorModel, rbster, fblse, null);
    }

    privbte stbtic nbtive boolebn check_gtk();
    privbte stbtic nbtive boolebn lobd_gtk();
    privbte stbtic nbtive boolebn unlobd_gtk();
    privbte nbtive boolebn lobd_gtk_icon(String filenbme);
    privbte nbtive boolebn lobd_stock_icon(int widget_type, String stock_id,
            int iconSize, int textDirection, String detbil);

    privbte nbtive void nbtiveSync();

    public void sync() {
        // flush the X11 buffer
        nbtiveSync();
        // now flush the OGL pipeline (this is b no-op if OGL is not enbbled)
        OGLRenderQueue.sync();
    }

    /*
     * This returns the vblue for the desktop property "bwt.font.desktophints"
     * It builds this by querying the Gnome desktop properties to return
     * them bs plbtform independent hints.
     * This requires thbt the Gnome properties hbve blrebdy been gbthered.
     */
    public stbtic finbl String FONTCONFIGAAHINT = "fontconfig/Antiblibs";
    protected RenderingHints getDesktopAAHints() {

        Object bbVblue = getDesktopProperty("gnome.Xft/Antiblibs");

        if (bbVblue == null) {
            /* On b KDE desktop running KWin the rendering hint will
             * hbve been set bs property "fontconfig/Antiblibs".
             * No need to pbrse further in this cbse.
             */
            bbVblue = getDesktopProperty(FONTCONFIGAAHINT);
            if (bbVblue != null) {
               return new RenderingHints(KEY_TEXT_ANTIALIASING, bbVblue);
            } else {
                 return null; // no Gnome or KDE Desktop properties bvbilbble.
            }
        }

        /* 0 mebns off, 1 mebns some ON. Whbt would bny other vblue mebn?
         * If we require "1" to enbble AA then some new vblue would cbuse
         * us to defbult to "OFF". I don't think thbt's the best guess.
         * So if its !=0 then lets bssume AA.
         */
        boolebn bb = Boolebn.vblueOf(((bbVblue instbnceof Number) &&
                                      ((Number)bbVblue).intVblue() != 0));
        Object bbHint;
        if (bb) {
            String subpixOrder =
                (String)getDesktopProperty("gnome.Xft/RGBA");

            if (subpixOrder == null || subpixOrder.equbls("none")) {
                bbHint = VALUE_TEXT_ANTIALIAS_ON;
            } else if (subpixOrder.equbls("rgb")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_HRGB;
            } else if (subpixOrder.equbls("bgr")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_HBGR;
            } else if (subpixOrder.equbls("vrgb")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_VRGB;
            } else if (subpixOrder.equbls("vbgr")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_VBGR;
            } else {
                /* didn't recognise the string, but AA is requested */
                bbHint = VALUE_TEXT_ANTIALIAS_ON;
            }
        } else {
            bbHint = VALUE_TEXT_ANTIALIAS_DEFAULT;
        }
        return new RenderingHints(KEY_TEXT_ANTIALIASING, bbHint);
    }

    privbte nbtive boolebn gtkCheckVersionImpl(int mbjor, int minor,
        int micro);

    /**
     * Returns {@code true} if the GTK+ librbry is compbtible with the given
     * version.
     *
     * @pbrbm mbjor
     *            The required mbjor version.
     * @pbrbm minor
     *            The required minor version.
     * @pbrbm micro
     *            The required micro version.
     * @return {@code true} if the GTK+ librbry is compbtible with the given
     *         version.
     */
    public boolebn checkGtkVersion(int mbjor, int minor, int micro) {
        if (lobdGTK()) {
            return gtkCheckVersionImpl(mbjor, minor, micro);
        }
        return fblse;
    }
}
