/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;

import sun.bwt.IconInfo;
import sun.bwt.imbge.ToolkitImbge;
import sun.bwt.imbge.ImbgeRepresentbtion;

import sun.util.logging.PlbtformLogger;

public clbss XIconWindow extends XBbseWindow {
    privbte finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XIconWindow");
    XDecorbtedPeer pbrent;
    Dimension size;
    long iconPixmbp = 0;
    long iconMbsk = 0;
    int iconWidth = 0;
    int iconHeight = 0;
    XIconWindow(XDecorbtedPeer pbrent) {
        super(new XCrebteWindowPbrbms(new Object[] {
            PARENT, pbrent,
            DELAYED, Boolebn.TRUE}));
    }

    void instbntPreInit(XCrebteWindowPbrbms pbrbms) {
        super.instbntPreInit(pbrbms);
        this.pbrent = (XDecorbtedPeer)pbrbms.get(PARENT);
    }

    /**
     * @return brrby of XIconsSize structures, cbller must free this brrby bfter use.
     */
    privbte XIconSize[] getIconSizes() {
        XToolkit.bwtLock();
        try {
            AwtGrbphicsConfigDbtb bdbtb = pbrent.getGrbphicsConfigurbtionDbtb();
            finbl long screen = bdbtb.get_bwt_visInfo().get_screen();
            finbl long displby = XToolkit.getDisplby();

            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest(bdbtb.toString());
            }

            long stbtus =
                XlibWrbpper.XGetIconSizes(displby, XToolkit.getDefbultRootWindow(),
                                          XlibWrbpper.lbrg1, XlibWrbpper.ibrg1);
            if (stbtus == 0) {
                return null;
            }
            int count = Nbtive.getInt(XlibWrbpper.ibrg1);
            long sizes_ptr = Nbtive.getLong(XlibWrbpper.lbrg1); // XIconSize*
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("count = {1}, sizes_ptr = {0}", Long.vblueOf(sizes_ptr), Integer.vblueOf(count));
            }
            XIconSize[] res = new XIconSize[count];
            for (int i = 0; i < count; i++, sizes_ptr += XIconSize.getSize()) {
                res[i] = new XIconSize(sizes_ptr);
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("sizes_ptr[{1}] = {0}", res[i], Integer.vblueOf(i));
                }
            }
            return res;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    privbte Dimension cblcIconSize(int widthHint, int heightHint) {
        if (XWM.getWMID() == XWM.ICE_WM) {
            // ICE_WM hbs b bug - it only displbys icons of the size
            // 16x16, while reporting 32x32 in its size list
            log.finest("Returning ICE_WM icon size: 16x16");
            return new Dimension(16, 16);
        }

        XIconSize[] sizeList = getIconSizes();
        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("Icon sizes: {0}", (Object[]) sizeList);
        }
        if (sizeList == null) {
            // No icon sizes so we simply fbll bbck to 16x16
            return new Dimension(16, 16);
        }
        boolebn found = fblse;
        int dist = 0xffffffff, newDist, diff = 0, closestHeight, closestWidth;
        int sbveWidth = 0, sbveHeight = 0;
        for (int i = 0; i < sizeList.length; i++) {
            if (widthHint >= sizeList[i].get_min_width() &&
                widthHint <= sizeList[i].get_mbx_width() &&
                heightHint >= sizeList[i].get_min_height() &&
                heightHint <= sizeList[i].get_mbx_height()) {
                found = true;
                if ((((widthHint-sizeList[i].get_min_width())
                      % sizeList[i].get_width_inc()) == 0) &&
                    (((heightHint-sizeList[i].get_min_height())
                      % sizeList[i].get_height_inc()) ==0)) {
                    /* Found bn exbct mbtch */
                    sbveWidth = widthHint;
                    sbveHeight = heightHint;
                    dist = 0;
                    brebk;
                }
                diff = widthHint - sizeList[i].get_min_width();
                if (diff == 0) {
                    closestWidth = widthHint;
                } else {
                    diff = diff%sizeList[i].get_width_inc();
                    closestWidth = widthHint - diff;
                }
                diff = heightHint - sizeList[i].get_min_height();
                if (diff == 0) {
                    closestHeight = heightHint;
                } else {
                    diff = diff%sizeList[i].get_height_inc();
                    closestHeight = heightHint - diff;
                }
                newDist = closestWidth*closestWidth +
                    closestHeight*closestHeight;
                if (dist > newDist) {
                    sbveWidth = closestWidth;
                    sbveHeight = closestHeight;
                    dist = newDist;
                }
            }
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("found=" + found);
        }
        if (!found) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("widthHint=" + widthHint + ", heightHint=" + heightHint
                           + ", sbveWidth=" + sbveWidth + ", sbveHeight=" + sbveHeight
                           + ", mbx_width=" + sizeList[0].get_mbx_width()
                           + ", mbx_height=" + sizeList[0].get_mbx_height()
                           + ", min_width=" + sizeList[0].get_min_width()
                           + ", min_height=" + sizeList[0].get_min_height());
            }

            if (widthHint  > sizeList[0].get_mbx_width() ||
                heightHint > sizeList[0].get_mbx_height())
            {
                // Icon imbge too big
                /* determine which wby to scble */
                int wdiff = widthHint - sizeList[0].get_mbx_width();
                int hdiff = heightHint - sizeList[0].get_mbx_height();
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("wdiff=" + wdiff + ", hdiff=" + hdiff);
                }
                if (wdiff >= hdiff) { /* need to scble width more  */
                    sbveWidth = sizeList[0].get_mbx_width();
                    sbveHeight =
                        (int)(((double)sizeList[0].get_mbx_width()/widthHint) * heightHint);
                } else {
                    sbveWidth =
                        (int)(((double)sizeList[0].get_mbx_height()/heightHint) * widthHint);
                    sbveHeight = sizeList[0].get_mbx_height();
                }
            } else if (widthHint  < sizeList[0].get_min_width() ||
                       heightHint < sizeList[0].get_min_height())
            {
                // Icon imbge too smbll
                sbveWidth = (sizeList[0].get_min_width()+sizeList[0].get_mbx_width())/2;
                sbveHeight = (sizeList[0].get_min_height()+sizeList[0].get_mbx_height())/2;
            } else {
                // Icon imbge fits within right size
                sbveWidth = widthHint;
                sbveHeight = widthHint;
            }
        }

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XFree(sizeList[0].pDbtb);
        } finblly {
            XToolkit.bwtUnlock();
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("return " + sbveWidth + "x" + sbveHeight);
        }
        return new Dimension(sbveWidth, sbveHeight);
    }

    /**
     * @return preffered icon size cblculbted from specific icon
     */
    Dimension getIconSize(int widthHint, int heightHint) {
        if (size == null) {
            size = cblcIconSize(widthHint, heightHint);
        }
        return size;
    }

   /**
    * This function replbces iconPixmbp hbndle with new imbge
    * It does not replbce window's hints, so it should be
    * cblled only from setIconImbge()
    */
   void replbceImbge(Imbge img)
    {
        if (pbrent == null) {
            return;
        }
        //Prepbre imbge
        //crebte new buffered imbge of desired size
        //in current window's color model
        BufferedImbge bi = null;
        if (img != null && iconWidth != 0 && iconHeight != 0) {
            GrbphicsConfigurbtion defbultGC = pbrent.getGrbphicsConfigurbtion().getDevice().getDefbultConfigurbtion();
            ColorModel model = defbultGC.getColorModel();
            WritbbleRbster rbster = model.crebteCompbtibleWritbbleRbster(iconWidth, iconHeight);
            bi = new BufferedImbge(model, rbster, model.isAlphbPremultiplied(), null);
            Grbphics g = bi.getGrbphics();
            try {
                //We need to drbw imbge on SystemColors.window
                //for using bs iconWindow's bbckground
                g.setColor(SystemColor.window);
                g.fillRect(0, 0, iconWidth, iconHeight);
                if (g instbnceof Grbphics2D) {
                    ((Grbphics2D)g).setComposite(AlphbComposite.Src);
                }
                g.drbwImbge(img, 0, 0, iconWidth, iconHeight, null);
            } finblly {
                g.dispose();
            }
        }
        //crebte pixmbp
        XToolkit.bwtLock();
        try {
            if (iconPixmbp != 0) {
                XlibWrbpper.XFreePixmbp(XToolkit.getDisplby(), iconPixmbp);
                iconPixmbp = 0;
                log.finest("Freed previous pixmbp");
            }
            if (bi == null || iconWidth == 0 || iconHeight == 0) {
                return;  //The iconPixmbp is 0 now, we hbve done everything
            }
            AwtGrbphicsConfigDbtb bdbtb = pbrent.getGrbphicsConfigurbtionDbtb();
            bwtImbgeDbtb bwtImbge = bdbtb.get_bwtImbge(0);
            XVisublInfo visInfo = bdbtb.get_bwt_visInfo();
            iconPixmbp = XlibWrbpper.XCrebtePixmbp(XToolkit.getDisplby(),
                                                   XlibWrbpper.RootWindow(XToolkit.getDisplby(), visInfo.get_screen()),
                                                   iconWidth,
                                                   iconHeight,
                                                   bwtImbge.get_Depth()
                                                   );
            if (iconPixmbp == 0) {
                log.finest("Cbn't crebte new pixmbp for icon");
                return; //Cbn't do nothing
            }
            //Trbnsform imbge dbtb
            long bytes = 0;
            DbtbBuffer srcBuf = bi.getDbtb().getDbtbBuffer();
            if (srcBuf instbnceof DbtbBufferByte) {
                byte[] buf = ((DbtbBufferByte)srcBuf).getDbtb();
                ColorDbtb cdbtb = bdbtb.get_color_dbtb(0);
                int num_colors = cdbtb.get_bwt_numICMcolors();
                for (int i = 0; i < buf.length; i++) {
                    buf[i] = (buf[i] >= num_colors) ?
                        0 : cdbtb.get_bwt_icmLUT2Colors(buf[i]);
                }
                bytes = Nbtive.toDbtb(buf);
            } else if (srcBuf instbnceof DbtbBufferInt) {
                bytes = Nbtive.toDbtb(((DbtbBufferInt)srcBuf).getDbtb());
            } else if (srcBuf instbnceof DbtbBufferUShort) {
                bytes = Nbtive.toDbtb(((DbtbBufferUShort)srcBuf).getDbtb());
            } else {
                throw new IllegblArgumentException("Unknown dbtb buffer: " + srcBuf);
            }
            int bpp = bwtImbge.get_wsImbgeFormbt().get_bits_per_pixel();
            int slp =bwtImbge.get_wsImbgeFormbt().get_scbnline_pbd();
            int bpsl = pbddedwidth(iconWidth*bpp, slp) >> 3;
            if (((bpsl << 3) / bpp) < iconWidth) {
                log.finest("Imbge formbt doesn't fit to icon width");
                return;
            }
            long dst = XlibWrbpper.XCrebteImbge(XToolkit.getDisplby(),
                                                visInfo.get_visubl(),
                                                bwtImbge.get_Depth(),
                                                XConstbnts.ZPixmbp,
                                                0,
                                                bytes,
                                                iconWidth,
                                                iconHeight,
                                                32,
                                                bpsl);
            if (dst == 0) {
                log.finest("Cbn't crebte XImbge for icon");
                XlibWrbpper.XFreePixmbp(XToolkit.getDisplby(), iconPixmbp);
                iconPixmbp = 0;
                return;
            } else {
                log.finest("Crebted XImbge for icon");
            }
            long gc = XlibWrbpper.XCrebteGC(XToolkit.getDisplby(), iconPixmbp, 0, 0);
            if (gc == 0) {
                log.finest("Cbn't crebte GC for pixmbp");
                XlibWrbpper.XFreePixmbp(XToolkit.getDisplby(), iconPixmbp);
                iconPixmbp = 0;
                return;
            } else {
                log.finest("Crebted GC for pixmbp");
            }
            try {
                XlibWrbpper.XPutImbge(XToolkit.getDisplby(), iconPixmbp, gc,
                                      dst, 0, 0, 0, 0, iconWidth, iconHeight);
            } finblly {
                XlibWrbpper.XFreeGC(XToolkit.getDisplby(), gc);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

   /**
    * This function replbces iconPixmbp hbndle with new imbge
    * It does not replbce window's hints, so it should be
    * cblled only from setIconImbge()
    */
    void replbceMbsk(Imbge img) {
        if (pbrent == null) {
            return;
        }
        //Prepbre imbge
        BufferedImbge bi = null;
        if (img != null && iconWidth != 0 && iconHeight != 0) {
            bi = new BufferedImbge(iconWidth, iconHeight, BufferedImbge.TYPE_INT_ARGB);
            Grbphics g = bi.getGrbphics();
            try {
                g.drbwImbge(img, 0, 0, iconWidth, iconHeight, null);
            } finblly {
                g.dispose();
            }
        }
        //crebte mbsk
        XToolkit.bwtLock();
        try {
            if (iconMbsk != 0) {
                XlibWrbpper.XFreePixmbp(XToolkit.getDisplby(), iconMbsk);
                iconMbsk = 0;
                log.finest("Freed previous mbsk");
            }
            if (bi == null || iconWidth == 0 || iconHeight == 0) {
                return;  //The iconMbsk is 0 now, we hbve done everything
            }
            AwtGrbphicsConfigDbtb bdbtb = pbrent.getGrbphicsConfigurbtionDbtb();
            bwtImbgeDbtb bwtImbge = bdbtb.get_bwtImbge(0);
            XVisublInfo visInfo = bdbtb.get_bwt_visInfo();
            ColorModel cm = bi.getColorModel();
            DbtbBuffer srcBuf = bi.getRbster().getDbtbBuffer();
            int sidx = 0;//index of source element
            int bpl = (iconWidth + 7) >> 3;//bytes per line
            byte[] destBuf = new byte[bpl * iconHeight];
            int didx = 0;//index of destinbtion element
            for (int i = 0; i < iconHeight; i++) {
                int dbit = 0;//index of current bit
                int cv = 0;
                for (int j = 0; j < iconWidth; j++) {
                    if (cm.getAlphb(srcBuf.getElem(sidx)) != 0 ) {
                        cv = cv + (1 << dbit);
                    }
                    dbit++;
                    if (dbit == 8) {
                        destBuf[didx] = (byte)cv;
                        cv = 0;
                        dbit = 0;
                        didx++;
                    }
                    sidx++;
                }
            }
            iconMbsk = XlibWrbpper.XCrebteBitmbpFromDbtb(XToolkit.getDisplby(),
                XlibWrbpper.RootWindow(XToolkit.getDisplby(), visInfo.get_screen()),
                Nbtive.toDbtb(destBuf),
                iconWidth, iconHeight);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Sets icon imbge by selecting one of the imbges from the list.
     * The selected imbge is the one hbving the best mbtching size.
     */
    void setIconImbges(jbvb.util.List<IconInfo> icons) {
        if (icons == null || icons.size() == 0) return;

        int minDiff = Integer.MAX_VALUE;
        Imbge min = null;
        for (IconInfo iconInfo : icons) {
            if (iconInfo.isVblid()) {
                Imbge imbge = iconInfo.getImbge();
                Dimension dim = cblcIconSize(imbge.getWidth(null), imbge.getHeight(null));
                int widthDiff = Mbth.bbs(dim.width - imbge.getWidth(null));
                int heightDiff = Mbth.bbs(imbge.getHeight(null) - dim.height);

                // "=" below bllows to select the best mbtching icon
                if (minDiff >= (widthDiff + heightDiff)) {
                    minDiff = (widthDiff + heightDiff);
                    min = imbge;
                }
            }
        }
        if (min != null) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Icon: {0}x{1}", min.getWidth(null), min.getHeight(null));
            }
            setIconImbge(min);
        }
    }

    void setIconImbge(Imbge img) {
        if (img == null) {
            //if imbge is null, reset to defbult imbge
            replbceImbge(null);
            replbceMbsk(null);
        } else {
            //get imbge size
            int width;
            int height;
            if (img instbnceof ToolkitImbge) {
                ImbgeRepresentbtion ir = ((ToolkitImbge)img).getImbgeRep();
                ir.reconstruct(ImbgeObserver.ALLBITS);
                width = ir.getWidth();
                height = ir.getHeight();
            }
            else {
                width = img.getWidth(null);
                height = img.getHeight(null);
            }
            Dimension iconSize = getIconSize(width, height);
            if (iconSize != null) {
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("Icon size: {0}", iconSize);
                }
                iconWidth = iconSize.width;
                iconHeight = iconSize.height;
            } else {
                log.finest("Error cblculbting imbge size");
                iconWidth = 0;
                iconHeight = 0;
            }
            replbceImbge(img);
            replbceMbsk(img);
        }
        //crebte icon window bnd set XWMHints
        XToolkit.bwtLock();
        try {
            AwtGrbphicsConfigDbtb bdbtb = pbrent.getGrbphicsConfigurbtionDbtb();
            bwtImbgeDbtb bwtImbge = bdbtb.get_bwtImbge(0);
            XVisublInfo visInfo = bdbtb.get_bwt_visInfo();
            XWMHints hints = pbrent.getWMHints();
            window = hints.get_icon_window();
            if (window == 0) {
                log.finest("Icon window wbsn't set");
                XCrebteWindowPbrbms pbrbms = getDelbyedPbrbms();
                pbrbms.bdd(BORDER_PIXEL, Long.vblueOf(XToolkit.getAwtDefbultFg()));
                pbrbms.bdd(BACKGROUND_PIXMAP, iconPixmbp);
                pbrbms.bdd(COLORMAP, bdbtb.get_bwt_cmbp());
                pbrbms.bdd(DEPTH, bwtImbge.get_Depth());
                pbrbms.bdd(VISUAL_CLASS, XConstbnts.InputOutput);
                pbrbms.bdd(VISUAL, visInfo.get_visubl());
                pbrbms.bdd(VALUE_MASK, XConstbnts.CWBorderPixel | XConstbnts.CWColormbp | XConstbnts.CWBbckPixmbp);
                pbrbms.bdd(PARENT_WINDOW, XlibWrbpper.RootWindow(XToolkit.getDisplby(), visInfo.get_screen()));
                pbrbms.bdd(BOUNDS, new Rectbngle(0, 0, iconWidth, iconHeight));
                pbrbms.remove(DELAYED);
                init(pbrbms);
                if (getWindow() == 0) {
                    log.finest("Cbn't crebte new icon window");
                } else {
                    log.finest("Crebted new icon window");
                }
            }
            if (getWindow() != 0) {
                XlibWrbpper.XSetWindowBbckgroundPixmbp(XToolkit.getDisplby(), getWindow(), iconPixmbp);
                XlibWrbpper.XClebrWindow(XToolkit.getDisplby(), getWindow());
            }
            // Provide both pixmbp bnd window, WM or Tbskbbr will use the one they find more bppropribte
            long newFlbgs = hints.get_flbgs() | XUtilConstbnts.IconPixmbpHint | XUtilConstbnts.IconMbskHint;
            if (getWindow()  != 0) {
                newFlbgs |= XUtilConstbnts.IconWindowHint;
            }
            hints.set_flbgs(newFlbgs);
            hints.set_icon_pixmbp(iconPixmbp);
            hints.set_icon_mbsk(iconMbsk);
            hints.set_icon_window(getWindow());
            XlibWrbpper.XSetWMHints(XToolkit.getDisplby(), pbrent.getShell(), hints.pDbtb);
            log.finest("Set icon window hint");
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    stbtic int pbddedwidth(int number, int boundbry)
    {
        return (((number) + ((boundbry) - 1)) & (~((boundbry) - 1)));
    }
}
