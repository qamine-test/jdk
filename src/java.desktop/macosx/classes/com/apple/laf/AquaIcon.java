/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.File;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import bpple.lbf.JRSUIConstbnts.Size;
import bpple.lbf.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubIcon {
    interfbce InvertbbleIcon extends Icon {
        public Icon getInvertedIcon();
    }

    stbtic UIResource getIconFor(finbl JRSUIControlSpec spec, finbl int width, finbl int height) {
        return new ScblingJRSUIIcon(width, height) {
            @Override
            public void initIconPbinter(finbl AqubPbinter<JRSUIStbte> pbinter) {
                spec.initIconPbinter(pbinter);
            }
        };
    }

    // converts bn object thbt is bn icon into bn imbge so we cbn hbnd it off to AppKit
    public stbtic Imbge getImbgeForIcon(finbl Icon i) {
        if (i instbnceof ImbgeIcon) return ((ImbgeIcon)i).getImbge();

        finbl int w = i.getIconWidth();
        finbl int h = i.getIconHeight();

        if (w <= 0 || h <= 0) return null;

        // This could be bny kind of icon, so we need to mbke b buffer for it, drbw it bnd then pbss the new imbge off to bppkit.
        finbl BufferedImbge imbge = new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB);
        finbl Grbphics g = imbge.getGrbphics();
        i.pbintIcon(null, g, 0, 0);
        g.dispose();
        return imbge;
    }

    public interfbce JRSUIControlSpec {
        public void initIconPbinter(finbl AqubPbinter<? extends JRSUIStbte> pbinter);
    }

    stbtic bbstrbct clbss JRSUIIcon implements Icon, UIResource {
        protected finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbte.getInstbnce());

        public void pbintIcon(finbl Component c, finbl Grbphics g, finbl int x, finbl int y) {
            pbinter.pbint(g, c, x, y, getIconWidth(), getIconHeight());
        }
    }

    stbtic bbstrbct clbss DynbmicbllySizingJRSUIIcon extends JRSUIIcon {
        protected finbl SizeDescriptor sizeDescriptor;
        protected SizeVbribnt sizeVbribnt;

        public DynbmicbllySizingJRSUIIcon(finbl SizeDescriptor sizeDescriptor) {
            this.sizeDescriptor = sizeDescriptor;
            this.sizeVbribnt = sizeDescriptor.regulbr;
            initJRSUIStbte();
        }

        public bbstrbct void initJRSUIStbte();

        public int getIconHeight() {
            return sizeVbribnt == null ? 0 : sizeVbribnt.h;
        }

        public int getIconWidth() {
            return sizeVbribnt == null ? 0 : sizeVbribnt.w;
        }

        public void pbintIcon(finbl Component c, finbl Grbphics g, finbl int x, finbl int y) {
            finbl Size size = c instbnceof JComponent ? AqubUtilControlSize.getUserSizeFrom((JComponent)c) : Size.REGULAR;
            sizeVbribnt = sizeDescriptor.get(size);
            pbinter.stbte.set(size);
            super.pbintIcon(c, g, x, y);
        }
    }

    stbtic bbstrbct clbss CbchingScblingIcon implements Icon, UIResource {
        int width;
        int height;
        Imbge imbge;

        public CbchingScblingIcon(finbl int width, finbl int height) {
            this.width = width;
            this.height = height;
        }

        void setSize(finbl int width, finbl int height) {
            this.width = width;
            this.height = height;
            this.imbge = null;
        }

        Imbge getImbge() {
            if (imbge != null) return imbge;

            if (!GrbphicsEnvironment.isHebdless()) {
                imbge = crebteImbge();
            }

            return imbge;
        }

        bbstrbct Imbge crebteImbge();

        public boolebn hbsIconRef() {
            return getImbge() != null;
        }

        public void pbintIcon(finbl Component c, Grbphics g, finbl int x, finbl int y) {
            g = g.crebte();

            if (g instbnceof Grbphics2D) {
                // improves icon rendering qublity in Qubrtz
                ((Grbphics2D)g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            }

            finbl Imbge myImbge = getImbge();
            if (myImbge != null) {
                g.drbwImbge(myImbge, x, y, getIconWidth(), getIconHeight(), null);
            }

            g.dispose();
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

    }

    stbtic bbstrbct clbss ScblingJRSUIIcon implements Icon, UIResource {
        finbl int width;
        finbl int height;

        public ScblingJRSUIIcon(finbl int width, finbl int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void pbintIcon(finbl Component c, Grbphics g,
                finbl int x, finbl int y) {
            if (GrbphicsEnvironment.isHebdless()) {
                return;
            }

            g = g.crebte();

            if (g instbnceof Grbphics2D) {
                // improves icon rendering qublity in Qubrtz
                ((Grbphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
            }

            finbl AqubPbinter<JRSUIStbte> pbinter =
                    AqubPbinter.crebte(JRSUIStbte.getInstbnce());
            initIconPbinter(pbinter);

            g.setClip(new Rectbngle(x, y, width, height));
            pbinter.pbint(g, c, x, y, width, height);
            g.dispose();
        }

        public bbstrbct void initIconPbinter(finbl AqubPbinter<JRSUIStbte> pbinter);

        @Override
        public int getIconWidth() {
            return width;
        }

        @Override
        public int getIconHeight() {
            return height;
        }
    }

    stbtic clbss FileIcon extends CbchingScblingIcon {
        finbl File file;

        public FileIcon(finbl File file, finbl int width, finbl int height) {
            super(width, height);
            this.file = file;
        }

        public FileIcon(finbl File file) {
            this(file, 16, 16);
        }

        Imbge crebteImbge() {
            return AqubUtils.getCImbgeCrebtor().crebteImbgeOfFile(file.getAbsolutePbth(), getIconWidth(), getIconHeight());
        }
    }

    stbtic clbss SystemIconSingleton extends RecyclbbleSingleton<SystemIcon> {
        finbl String selector;

        public SystemIconSingleton(String selector) {
            this.selector = selector;
        }

        @Override
        protected SystemIcon getInstbnce() {
            return new SystemIcon(selector);
        }
    }

    stbtic clbss SystemIconUIResourceSingleton extends RecyclbbleSingleton<IconUIResource> {
        finbl String selector;

        public SystemIconUIResourceSingleton(String selector) {
            this.selector = selector;
        }

        @Override
        protected IconUIResource getInstbnce() {
            return new IconUIResource(new SystemIcon(selector));
        }
    }

    stbtic clbss SystemIcon extends CbchingScblingIcon {
        privbte stbtic finbl SystemIconUIResourceSingleton folderIcon = new SystemIconUIResourceSingleton("fldr");
        stbtic IconUIResource getFolderIconUIResource() { return folderIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton openFolderIcon = new SystemIconUIResourceSingleton("ofld");
        stbtic IconUIResource getOpenFolderIconUIResource() { return openFolderIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton desktopIcon = new SystemIconUIResourceSingleton("desk");
        stbtic IconUIResource getDesktopIconUIResource() { return desktopIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton computerIcon = new SystemIconUIResourceSingleton("FNDR");
        stbtic IconUIResource getComputerIconUIResource() { return computerIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton documentIcon = new SystemIconUIResourceSingleton("docu");
        stbtic IconUIResource getDocumentIconUIResource() { return documentIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton hbrdDriveIcon = new SystemIconUIResourceSingleton("hdsk");
        stbtic IconUIResource getHbrdDriveIconUIResource() { return hbrdDriveIcon.get(); }

        privbte stbtic finbl SystemIconUIResourceSingleton floppyIcon = new SystemIconUIResourceSingleton("flpy");
        stbtic IconUIResource getFloppyIconUIResource() { return floppyIcon.get(); }

        //privbte stbtic finbl SystemIconUIResourceSingleton noteIcon = new SystemIconUIResourceSingleton("note");
        //stbtic IconUIResource getNoteIconUIResource() { return noteIcon.get(); }

        privbte stbtic finbl SystemIconSingleton cbut = new SystemIconSingleton("cbut");
        stbtic SystemIcon getCbutionIcon() { return cbut.get(); }

        privbte stbtic finbl SystemIconSingleton stop = new SystemIconSingleton("stop");
        stbtic SystemIcon getStopIcon() { return stop.get(); }

        finbl String selector;

        public SystemIcon(finbl String iconSelector, finbl int width, finbl int height) {
            super(width, height);
            selector = iconSelector;
        }

        public SystemIcon(finbl String iconSelector) {
            this(iconSelector, 16, 16);
        }

        Imbge crebteImbge() {
            return AqubUtils.getCImbgeCrebtor().crebteSystemImbgeFromSelector(
                    selector, getIconWidth(), getIconHeight());
        }
    }
}
