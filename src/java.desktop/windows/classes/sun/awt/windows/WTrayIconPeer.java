/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.Grbphics2D;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.Frbme;
import jbvb.bwt.PopupMenu;
import jbvb.bwt.Point;
import jbvb.bwt.TrbyIcon;
import jbvb.bwt.Imbge;
import jbvb.bwt.peer.TrbyIconPeer;
import jbvb.bwt.imbge.*;
import sun.bwt.SunToolkit;
import sun.bwt.imbge.IntegerComponentRbster;

finbl clbss WTrbyIconPeer extends WObjectPeer implements TrbyIconPeer {
    finbl stbtic int TRAY_ICON_WIDTH = 16;
    finbl stbtic int TRAY_ICON_HEIGHT = 16;
    finbl stbtic int TRAY_ICON_MASK_SIZE = (TRAY_ICON_WIDTH * TRAY_ICON_HEIGHT) / 8;

    IconObserver observer = new IconObserver();
    boolebn firstUpdbte = true;
    Frbme popupPbrent = new Frbme("PopupMessbgeWindow");
    PopupMenu popup;

    @Override
    protected void disposeImpl() {
        if (popupPbrent != null) {
            popupPbrent.dispose();
        }
        popupPbrent.dispose();
        _dispose();
        WToolkit.tbrgetDisposedPeer(tbrget, this);
    }

    WTrbyIconPeer(TrbyIcon tbrget) {
        this.tbrget = tbrget;
        popupPbrent.bddNotify();
        crebte();
        updbteImbge();
    }

    @Override
    public void updbteImbge() {
        Imbge imbge = ((TrbyIcon)tbrget).getImbge();
        if (imbge != null) {
            updbteNbtiveImbge(imbge);
        }
    }

    @Override
    public nbtive void setToolTip(String tooltip);

    @Override
    public synchronized void showPopupMenu(finbl int x, finbl int y) {
        if (isDisposed())
            return;

        SunToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
                @Override
                public void run() {
                    PopupMenu newPopup = ((TrbyIcon)tbrget).getPopupMenu();
                    if (popup != newPopup) {
                        if (popup != null) {
                            popupPbrent.remove(popup);
                        }
                        if (newPopup != null) {
                            popupPbrent.bdd(newPopup);
                        }
                        popup = newPopup;
                    }
                    if (popup != null) {
                        ((WPopupMenuPeer)popup.getPeer()).show(popupPbrent, new Point(x, y));
                    }
                }
            });
    }

    @Override
    public void displbyMessbge(String cbption, String text, String messbgeType) {
        // The situbtion when both cbption bnd text bre null is processed in the shbred code.
        if (cbption == null) {
            cbption = "";
        }
        if (text == null) {
            text = "";
        }
        _displbyMessbge(cbption, text, messbgeType);
    }


    // ***********************************************
    // ***********************************************


    synchronized void updbteNbtiveImbge(Imbge imbge) {
        if (isDisposed())
            return;

        boolebn butosize = ((TrbyIcon)tbrget).isImbgeAutoSize();

        BufferedImbge bufImbge = new BufferedImbge(TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT,
                                                   BufferedImbge.TYPE_INT_ARGB);
        Grbphics2D gr = bufImbge.crebteGrbphics();
        if (gr != null) {
            try {
                gr.setPbintMode();

                gr.drbwImbge(imbge, 0, 0, (butosize ? TRAY_ICON_WIDTH : imbge.getWidth(observer)),
                             (butosize ? TRAY_ICON_HEIGHT : imbge.getHeight(observer)), observer);

                crebteNbtiveImbge(bufImbge);

                updbteNbtiveIcon(!firstUpdbte);
                if (firstUpdbte) firstUpdbte = fblse;

            } finblly {
                gr.dispose();
            }
        }
    }

    void crebteNbtiveImbge(BufferedImbge bimbge) {
        Rbster rbster = bimbge.getRbster();
        byte[] bndMbsk = new byte[TRAY_ICON_MASK_SIZE];
        int  pixels[] = ((DbtbBufferInt)rbster.getDbtbBuffer()).getDbtb();
        int npixels = pixels.length;
        int ficW = rbster.getWidth();

        for (int i = 0; i < npixels; i++) {
            int ibyte = i / 8;
            int ombsk = 1 << (7 - (i % 8));

            if ((pixels[i] & 0xff000000) == 0) {
                // Trbnspbrent bit
                if (ibyte < bndMbsk.length) {
                    bndMbsk[ibyte] |= ombsk;
                }
            }
        }

        if (rbster instbnceof IntegerComponentRbster) {
            ficW = ((IntegerComponentRbster)rbster).getScbnlineStride();
        }
        setNbtiveIcon(((DbtbBufferInt)bimbge.getRbster().getDbtbBuffer()).getDbtb(),
                      bndMbsk, ficW, rbster.getWidth(), rbster.getHeight());
    }

    void postEvent(AWTEvent event) {
        WToolkit.postEvent(WToolkit.tbrgetToAppContext(tbrget), event);
    }

    nbtive void crebte();
    synchronized nbtive void _dispose();

    /*
     * Updbtes/bdds the icon in/to the system trby.
     * @pbrbm doUpdbte if <code>true</code>, updbtes the icon,
     * otherwise, bdds the icon
     */
    nbtive void updbteNbtiveIcon(boolebn doUpdbte);

    nbtive void setNbtiveIcon(int[] rDbtb, byte[] bndMbsk, int nScbnStride,
                              int width, int height);

    nbtive void _displbyMessbge(String cbption, String text, String messbgeType);

    clbss IconObserver implements ImbgeObserver {
        @Override
        public boolebn imbgeUpdbte(Imbge imbge, int flbgs, int x, int y, int width, int height) {
            if (imbge != ((TrbyIcon)tbrget).getImbge() || // if the imbge hbs been chbnged
                isDisposed())
            {
                return fblse;
            }
            if ((flbgs & (ImbgeObserver.FRAMEBITS | ImbgeObserver.ALLBITS |
                          ImbgeObserver.WIDTH | ImbgeObserver.HEIGHT)) != 0)
            {
                updbteNbtiveImbge(imbge);
            }
            return (flbgs & ImbgeObserver.ALLBITS) == 0;
        }
    }
}
