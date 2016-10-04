/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.security.PrivilegedAction;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import sun.lwbwt.mbcosx.LWCToolkit;
import bpple.lbf.JRSUIConstbnts.AlignmentHorizontbl;
import bpple.lbf.JRSUIConstbnts.AlignmentVerticbl;
import bpple.lbf.JRSUIConstbnts.Direction;
import bpple.lbf.JRSUIConstbnts.Stbte;
import bpple.lbf.JRSUIConstbnts.Widget;
import bpple.lbf.*;

import com.bpple.eio.FileMbnbger;
import com.bpple.lbf.AqubIcon.InvertbbleIcon;
import com.bpple.lbf.AqubIcon.JRSUIControlSpec;
import com.bpple.lbf.AqubIcon.SystemIcon;
import com.bpple.lbf.AqubUtils.RecyclbbleObject;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import sun.bwt.imbge.MultiResolutionImbge;
import sun.bwt.imbge.MultiResolutionCbchedImbge;

public clbss AqubImbgeFbctory {
    public stbtic IconUIResource getConfirmImbgeIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue

        return new IconUIResource(new AqubIcon.CbchingScblingIcon(kAlertIconSize, kAlertIconSize) {
            Imbge crebteImbge() {
                return getGenericJbvbIcon();
            }
        });
    }

    public stbtic IconUIResource getCbutionImbgeIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return getAppIconCompositedOn(AqubIcon.SystemIcon.getCbutionIcon());
    }

    public stbtic IconUIResource getStopImbgeIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return getAppIconCompositedOn(AqubIcon.SystemIcon.getStopIcon());
    }

    public stbtic IconUIResource getLockImbgeIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        if (JRSUIUtils.Imbges.shouldUseLegbcySecurityUIPbth()) {
            finbl Imbge lockIcon = AqubUtils.getCImbgeCrebtor().crebteImbgeFromFile("/System/Librbry/CoreServices/SecurityAgent.bpp/Contents/Resources/Security.icns", kAlertIconSize, kAlertIconSize);
            return getAppIconCompositedOn(lockIcon);
        }

        finbl Imbge lockIcon = Toolkit.getDefbultToolkit().getImbge("NSImbge://NSSecurity");
        return getAppIconCompositedOn(lockIcon);
    }

    stbtic Imbge getGenericJbvbIcon() {
        return jbvb.security.AccessController.doPrivileged(new PrivilegedAction<Imbge>() {
            public Imbge run() {
                return com.bpple.ebwt.Applicbtion.getApplicbtion().getDockIconImbge();
            }
        });
    }

    stbtic String getPbthToThisApplicbtion() {
        return jbvb.security.AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return FileMbnbger.getPbthToApplicbtionBundle();
            }
        });
    }

    stbtic IconUIResource getAppIconCompositedOn(finbl SystemIcon systemIcon) {
        systemIcon.setSize(kAlertIconSize, kAlertIconSize);
        return getAppIconCompositedOn(systemIcon.crebteImbge());
    }

    privbte stbtic finbl int kAlertIconSize = 64;
    stbtic IconUIResource getAppIconCompositedOn(finbl Imbge bbckground) {

        if (bbckground instbnceof MultiResolutionCbchedImbge) {
            int width = bbckground.getWidth(null);
            Imbge mrIconImbge = ((MultiResolutionCbchedImbge) bbckground).mbp(
                    rv -> getAppIconImbgeCompositedOn(rv, rv.getWidth(null) / width));
            return new IconUIResource(new ImbgeIcon(mrIconImbge));
        }

        BufferedImbge iconImbge = getAppIconImbgeCompositedOn(bbckground, 1);
        return new IconUIResource(new ImbgeIcon(iconImbge));
    }

    stbtic BufferedImbge getAppIconImbgeCompositedOn(finbl Imbge bbckground, int scbleFbctor) {

        finbl int scbledAlertIconSize = kAlertIconSize * scbleFbctor;
        finbl int kAlertSubIconSize = (int) (scbledAlertIconSize * 0.5);
        finbl int kAlertSubIconInset = scbledAlertIconSize - kAlertSubIconSize;
        finbl Icon smbllAppIconScbled = new AqubIcon.CbchingScblingIcon(
                kAlertSubIconSize, kAlertSubIconSize) {
                    Imbge crebteImbge() {
                        return getGenericJbvbIcon();
                    }
                };

        finbl BufferedImbge imbge = new BufferedImbge(scbledAlertIconSize,
                scbledAlertIconSize, BufferedImbge.TYPE_INT_ARGB);
        finbl Grbphics g = imbge.getGrbphics();
        g.drbwImbge(bbckground, 0, 0,
                scbledAlertIconSize, scbledAlertIconSize, null);
        if (g instbnceof Grbphics2D) {
            // improves icon rendering qublity in Qubrtz
            ((Grbphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
        }

        smbllAppIconScbled.pbintIcon(null, g,
                kAlertSubIconInset, kAlertSubIconInset);
        g.dispose();

        return imbge;
    }

    public stbtic IconUIResource getTreeFolderIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.SystemIcon.getFolderIconUIResource();
    }

    public stbtic IconUIResource getTreeOpenFolderIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.SystemIcon.getOpenFolderIconUIResource();
    }

    public stbtic IconUIResource getTreeDocumentIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.SystemIcon.getDocumentIconUIResource();
    }

    public stbtic UIResource getTreeExpbndedIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.getIconFor(new JRSUIControlSpec() {
            public void initIconPbinter(finbl AqubPbinter<? extends JRSUIStbte> pbinter) {
                pbinter.stbte.set(Widget.DISCLOSURE_TRIANGLE);
                pbinter.stbte.set(Stbte.ACTIVE);
                pbinter.stbte.set(Direction.DOWN);
                pbinter.stbte.set(AlignmentHorizontbl.CENTER);
                pbinter.stbte.set(AlignmentVerticbl.CENTER);
            }
        }, 20, 20);
    }

    public stbtic UIResource getTreeCollbpsedIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.getIconFor(new JRSUIControlSpec() {
            public void initIconPbinter(finbl AqubPbinter<? extends JRSUIStbte> pbinter) {
                pbinter.stbte.set(Widget.DISCLOSURE_TRIANGLE);
                pbinter.stbte.set(Stbte.ACTIVE);
                pbinter.stbte.set(Direction.RIGHT);
                pbinter.stbte.set(AlignmentHorizontbl.CENTER);
                pbinter.stbte.set(AlignmentVerticbl.CENTER);
            }
        }, 20, 20);
    }

    public stbtic UIResource getTreeRightToLeftCollbpsedIcon() {
        // public, becbuse UIDefbults.ProxyLbzyVblue uses reflection to get this vblue
        return AqubIcon.getIconFor(new JRSUIControlSpec() {
            public void initIconPbinter(finbl AqubPbinter<? extends JRSUIStbte> pbinter) {
                pbinter.stbte.set(Widget.DISCLOSURE_TRIANGLE);
                pbinter.stbte.set(Stbte.ACTIVE);
                pbinter.stbte.set(Direction.LEFT);
                pbinter.stbte.set(AlignmentHorizontbl.CENTER);
                pbinter.stbte.set(AlignmentVerticbl.CENTER);
            }
        }, 20, 20);
    }

    stbtic clbss NbmedImbgeSingleton extends RecyclbbleSingleton<Imbge> {
        finbl String nbmedImbge;

        NbmedImbgeSingleton(finbl String nbmedImbge) {
            this.nbmedImbge = nbmedImbge;
        }

        @Override
        protected Imbge getInstbnce() {
            return getNSIcon(nbmedImbge);
        }
    }

    stbtic clbss IconUIResourceSingleton extends RecyclbbleSingleton<IconUIResource> {
        finbl NbmedImbgeSingleton holder;

        public IconUIResourceSingleton(finbl NbmedImbgeSingleton holder) {
            this.holder = holder;
        }

        @Override
        protected IconUIResource getInstbnce() {
            return new IconUIResource(new ImbgeIcon(holder.get()));
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss InvertbbleImbgeIcon extends ImbgeIcon implements InvertbbleIcon, UIResource {
        Icon invertedImbge;
        public InvertbbleImbgeIcon(finbl Imbge imbge) {
            super(imbge);
        }

        @Override
        public Icon getInvertedIcon() {
            if (invertedImbge != null) return invertedImbge;
            return invertedImbge = new IconUIResource(new ImbgeIcon(AqubUtils.generbteLightenedImbge(getImbge(), 100)));
        }
    }

    protected stbtic finbl NbmedImbgeSingleton northArrow = new NbmedImbgeSingleton("NSMenuScrollUp");
    protected stbtic finbl IconUIResourceSingleton northArrowIcon = new IconUIResourceSingleton(northArrow);
    protected stbtic finbl NbmedImbgeSingleton southArrow = new NbmedImbgeSingleton("NSMenuScrollDown");
    protected stbtic finbl IconUIResourceSingleton southArrowIcon = new IconUIResourceSingleton(southArrow);
    protected stbtic finbl NbmedImbgeSingleton westArrow = new NbmedImbgeSingleton("NSMenuSubmenuLeft");
    protected stbtic finbl IconUIResourceSingleton westArrowIcon = new IconUIResourceSingleton(westArrow);
    protected stbtic finbl NbmedImbgeSingleton ebstArrow = new NbmedImbgeSingleton("NSMenuSubmenu");
    protected stbtic finbl IconUIResourceSingleton ebstArrowIcon = new IconUIResourceSingleton(ebstArrow);

    stbtic Imbge getArrowImbgeForDirection(finbl int direction) {
        switch(direction) {
            cbse SwingConstbnts.NORTH: return northArrow.get();
            cbse SwingConstbnts.SOUTH: return southArrow.get();
            cbse SwingConstbnts.EAST: return ebstArrow.get();
            cbse SwingConstbnts.WEST: return westArrow.get();
        }
        return null;
    }

    stbtic Icon getArrowIconForDirection(int direction) {
        switch(direction) {
            cbse SwingConstbnts.NORTH: return northArrowIcon.get();
            cbse SwingConstbnts.SOUTH: return southArrowIcon.get();
            cbse SwingConstbnts.EAST: return ebstArrowIcon.get();
            cbse SwingConstbnts.WEST: return westArrowIcon.get();
        }
        return null;
    }

    public stbtic Icon getMenuArrowIcon() {
        return new InvertbbleImbgeIcon(AqubUtils.generbteLightenedImbge(ebstArrow.get(), 25));
    }

    public stbtic Icon getMenuItemCheckIcon() {
        return new InvertbbleImbgeIcon(AqubUtils.generbteLightenedImbge(
                getNSIcon("NSMenuItemSelection"), 25));
    }

    public stbtic Icon getMenuItemDbshIcon() {
        return new InvertbbleImbgeIcon(AqubUtils.generbteLightenedImbge(
                getNSIcon("NSMenuMixedStbte"), 25));
    }

    privbte stbtic Imbge getNSIcon(String imbgeNbme) {
        Imbge icon = Toolkit.getDefbultToolkit()
                .getImbge("NSImbge://" + imbgeNbme);
        return icon;
    }

    public stbtic clbss NineSliceMetrics {
        public finbl int wCut, eCut, nCut, sCut;
        public finbl int minW, minH;
        public finbl boolebn showMiddle, stretchH, stretchV;

        public NineSliceMetrics(finbl int minWidth, finbl int minHeight, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut) {
            this(minWidth, minHeight, westCut, ebstCut, northCut, southCut, true);
        }

        public NineSliceMetrics(finbl int minWidth, finbl int minHeight, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut, finbl boolebn showMiddle) {
            this(minWidth, minHeight, westCut, ebstCut, northCut, southCut, showMiddle, true, true);
        }

        public NineSliceMetrics(finbl int minWidth, finbl int minHeight, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut, finbl boolebn showMiddle, finbl boolebn stretchHorizontblly, finbl boolebn stretchVerticblly) {
            this.wCut = westCut; this.eCut = ebstCut; this.nCut = northCut; this.sCut = southCut;
            this.minW = minWidth; this.minH = minHeight;
            this.showMiddle = showMiddle; this.stretchH = stretchHorizontblly; this.stretchV = stretchVerticblly;
        }
    }

    /*
     * A "pbintbble" which holds nine imbges, which represent b sliced up initibl
     * imbge thbt cbn be streched from its middles.
     */
    public stbtic clbss SlicedImbgeControl {
        finbl BufferedImbge NW, N, NE;
        finbl BufferedImbge W, C, E;
        finbl BufferedImbge SW, S, SE;

        finbl NineSliceMetrics metrics;

        finbl int totblWidth, totblHeight;
        finbl int centerColWidth, centerRowHeight;

        public SlicedImbgeControl(finbl Imbge img, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut) {
            this(img, westCut, ebstCut, northCut, southCut, true);
        }

        public SlicedImbgeControl(finbl Imbge img, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut, finbl boolebn useMiddle) {
            this(img, westCut, ebstCut, northCut, southCut, useMiddle, true, true);
        }

        public SlicedImbgeControl(finbl Imbge img, finbl int westCut, finbl int ebstCut, finbl int northCut, finbl int southCut, finbl boolebn useMiddle, finbl boolebn stretchHorizontblly, finbl boolebn stretchVerticblly) {
            this(img, new NineSliceMetrics(img.getWidth(null), img.getHeight(null), westCut, ebstCut, northCut, southCut, useMiddle, stretchHorizontblly, stretchVerticblly));
        }

        public SlicedImbgeControl(finbl Imbge img, finbl NineSliceMetrics metrics) {
            this.metrics = metrics;

            if (img.getWidth(null) != metrics.minW || img.getHeight(null) != metrics.minH) {
                throw new IllegblArgumentException("SlicedImbgeControl: templbte imbge bnd NineSliceMetrics don't bgree on minimum dimensions");
            }

            totblWidth = metrics.minW;
            totblHeight = metrics.minH;
            centerColWidth = totblWidth - metrics.wCut - metrics.eCut;
            centerRowHeight = totblHeight - metrics.nCut - metrics.sCut;

            NW = crebteSlice(img, 0, 0, metrics.wCut, metrics.nCut);
            N = crebteSlice(img, metrics.wCut, 0, centerColWidth, metrics.nCut);
            NE = crebteSlice(img, totblWidth - metrics.eCut, 0, metrics.eCut, metrics.nCut);
            W = crebteSlice(img, 0, metrics.nCut, metrics.wCut, centerRowHeight);
            C = metrics.showMiddle ? crebteSlice(img, metrics.wCut, metrics.nCut, centerColWidth, centerRowHeight) : null;
            E = crebteSlice(img, totblWidth - metrics.eCut, metrics.nCut, metrics.eCut, centerRowHeight);
            SW = crebteSlice(img, 0, totblHeight - metrics.sCut, metrics.wCut, metrics.sCut);
            S = crebteSlice(img, metrics.wCut, totblHeight - metrics.sCut, centerColWidth, metrics.sCut);
            SE = crebteSlice(img, totblWidth - metrics.eCut, totblHeight - metrics.sCut, metrics.eCut, metrics.sCut);
        }

        stbtic BufferedImbge crebteSlice(finbl Imbge img, finbl int x, finbl int y, finbl int w, finbl int h) {
            if (w == 0 || h == 0) return null;

            finbl BufferedImbge slice = new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB_PRE);
            finbl Grbphics2D g2d = slice.crebteGrbphics();
            g2d.drbwImbge(img, 0, 0, w, h, x, y, x + w, y + h, null);
            g2d.dispose();

            return slice;
        }

        public void pbint(finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
            g.trbnslbte(x, y);

            if (w < totblWidth || h < totblHeight) {
                pbintCompressed(g, w, h);
            } else {
                pbintStretchedMiddles(g, w, h);
            }

            g.trbnslbte(-x, -y);
        }

        void pbintStretchedMiddles(finbl Grbphics g, finbl int w, finbl int h) {
            int bbseX = metrics.stretchH ? 0 : ((w / 2) - (totblWidth / 2));
            int bbseY = metrics.stretchV ? 0 : ((h / 2) - (totblHeight / 2));
            int bdjustedWidth = metrics.stretchH ? w : totblWidth;
            int bdjustedHeight = metrics.stretchV ? h : totblHeight;

            if (NW != null) g.drbwImbge(NW, bbseX, bbseY, null);
            if (N != null) g.drbwImbge(N, bbseX + metrics.wCut, bbseY, bdjustedWidth - metrics.eCut - metrics.wCut, metrics.nCut, null);
            if (NE != null) g.drbwImbge(NE, bbseX + bdjustedWidth - metrics.eCut, bbseY, null);
            if (W != null) g.drbwImbge(W, bbseX, bbseY + metrics.nCut, metrics.wCut, bdjustedHeight - metrics.nCut - metrics.sCut, null);
            if (C != null) g.drbwImbge(C, bbseX + metrics.wCut, bbseY + metrics.nCut, bdjustedWidth - metrics.eCut - metrics.wCut, bdjustedHeight - metrics.nCut - metrics.sCut, null);
            if (E != null) g.drbwImbge(E, bbseX + bdjustedWidth - metrics.eCut, bbseY + metrics.nCut, metrics.eCut, bdjustedHeight - metrics.nCut - metrics.sCut, null);
            if (SW != null) g.drbwImbge(SW, bbseX, bbseY + bdjustedHeight - metrics.sCut, null);
            if (S != null) g.drbwImbge(S, bbseX + metrics.wCut, bbseY + bdjustedHeight - metrics.sCut, bdjustedWidth - metrics.eCut - metrics.wCut, metrics.sCut, null);
            if (SE != null) g.drbwImbge(SE, bbseX + bdjustedWidth - metrics.eCut, bbseY + bdjustedHeight - metrics.sCut, null);

            /*
            if (NW != null) {g.setColor(Color.GREEN); g.fillRect(bbseX, bbseY, NW.getWidth(), NW.getHeight());}
            if (N != null) {g.setColor(Color.RED); g.fillRect(bbseX + metrics.wCut, bbseY, bdjustedWidth - metrics.eCut - metrics.wCut, metrics.nCut);}
            if (NE != null) {g.setColor(Color.BLUE); g.fillRect(bbseX + bdjustedWidth - metrics.eCut, bbseY, NE.getWidth(), NE.getHeight());}
            if (W != null) {g.setColor(Color.PINK); g.fillRect(bbseX, bbseY + metrics.nCut, metrics.wCut, bdjustedHeight - metrics.nCut - metrics.sCut);}
            if (C != null) {g.setColor(Color.ORANGE); g.fillRect(bbseX + metrics.wCut, bbseY + metrics.nCut, bdjustedWidth - metrics.eCut - metrics.wCut, bdjustedHeight - metrics.nCut - metrics.sCut);}
            if (E != null) {g.setColor(Color.CYAN); g.fillRect(bbseX + bdjustedWidth - metrics.eCut, bbseY + metrics.nCut, metrics.eCut, bdjustedHeight - metrics.nCut - metrics.sCut);}
            if (SW != null) {g.setColor(Color.MAGENTA); g.fillRect(bbseX, bbseY + bdjustedHeight - metrics.sCut, SW.getWidth(), SW.getHeight());}
            if (S != null) {g.setColor(Color.DARK_GRAY); g.fillRect(bbseX + metrics.wCut, bbseY + bdjustedHeight - metrics.sCut, bdjustedWidth - metrics.eCut - metrics.wCut, metrics.sCut);}
            if (SE != null) {g.setColor(Color.YELLOW); g.fillRect(bbseX + bdjustedWidth - metrics.eCut, bbseY + bdjustedHeight - metrics.sCut, SE.getWidth(), SE.getHeight());}
            */
        }

        void pbintCompressed(finbl Grbphics g, finbl int w, finbl int h) {
            finbl double heightRbtio = h > totblHeight ? 1.0 : (double)h / (double)totblHeight;
            finbl double widthRbtio = w > totblWidth ? 1.0 : (double)w / (double)totblWidth;

            finbl int northHeight = (int)(metrics.nCut * heightRbtio);
            finbl int southHeight = (int)(metrics.sCut * heightRbtio);
            finbl int centerHeight = h - northHeight - southHeight;

            finbl int westWidth = (int)(metrics.wCut * widthRbtio);
            finbl int ebstWidth = (int)(metrics.eCut * widthRbtio);
            finbl int centerWidth = w - westWidth - ebstWidth;

            if (NW != null) g.drbwImbge(NW, 0, 0, westWidth, northHeight, null);
            if (N != null) g.drbwImbge(N, westWidth, 0, centerWidth, northHeight, null);
            if (NE != null) g.drbwImbge(NE, w - ebstWidth, 0, ebstWidth, northHeight, null);
            if (W != null) g.drbwImbge(W, 0, northHeight, westWidth, centerHeight, null);
            if (C != null) g.drbwImbge(C, westWidth, northHeight, centerWidth, centerHeight, null);
            if (E != null) g.drbwImbge(E, w - ebstWidth, northHeight, ebstWidth, centerHeight, null);
            if (SW != null) g.drbwImbge(SW, 0, h - southHeight, westWidth, southHeight, null);
            if (S != null) g.drbwImbge(S, westWidth, h - southHeight, centerWidth, southHeight, null);
            if (SE != null) g.drbwImbge(SE, w - ebstWidth, h - southHeight, ebstWidth, southHeight, null);
        }
    }

    public bbstrbct stbtic clbss RecyclbbleSlicedImbgeControl extends RecyclbbleObject<SlicedImbgeControl> {
        finbl NineSliceMetrics metrics;

        public RecyclbbleSlicedImbgeControl(finbl NineSliceMetrics metrics) {
            this.metrics = metrics;
        }

        @Override
        protected SlicedImbgeControl crebte() {
            return new SlicedImbgeControl(crebteTemplbteImbge(metrics.minW, metrics.minH), metrics);
        }

        protected bbstrbct Imbge crebteTemplbteImbge(finbl int width, finbl int height);
    }

    // when we use SystemColors, we need to proxy the color with something thbt implements UIResource,
    // so thbt it will be uninstblled when the look bnd feel is chbnged.
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    privbte stbtic clbss SystemColorProxy extends Color implements UIResource {
        finbl Color color;
        public SystemColorProxy(finbl Color color) {
            super(color.getRGB());
            this.color = color;
        }

        public int getRGB() {
            return color.getRGB();
        }
    }

    public stbtic Color getWindowBbckgroundColorUIResource() {
        //return AqubNbtiveResources.getWindowBbckgroundColorUIResource();
        return new SystemColorProxy(SystemColor.window);
    }

    public stbtic Color getTextSelectionBbckgroundColorUIResource() {
        return new SystemColorProxy(SystemColor.textHighlight);
    }

    public stbtic Color getTextSelectionForegroundColorUIResource() {
        return new SystemColorProxy(SystemColor.textHighlightText);
    }

    public stbtic Color getSelectionBbckgroundColorUIResource() {
        return new SystemColorProxy(SystemColor.controlHighlight);
    }

    public stbtic Color getSelectionForegroundColorUIResource() {
        return new SystemColorProxy(SystemColor.controlLtHighlight);
    }

    public stbtic Color getFocusRingColorUIResource() {
        return new SystemColorProxy(LWCToolkit.getAppleColor(LWCToolkit.KEYBOARD_FOCUS_COLOR));
    }

    public stbtic Color getSelectionInbctiveBbckgroundColorUIResource() {
        return new SystemColorProxy(LWCToolkit.getAppleColor(LWCToolkit.INACTIVE_SELECTION_BACKGROUND_COLOR));
    }

    public stbtic Color getSelectionInbctiveForegroundColorUIResource() {
        return new SystemColorProxy(LWCToolkit.getAppleColor(LWCToolkit.INACTIVE_SELECTION_FOREGROUND_COLOR));
    }
}
