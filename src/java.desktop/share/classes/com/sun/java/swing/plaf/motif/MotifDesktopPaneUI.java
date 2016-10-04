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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Component;
import jbvb.bwt.Point;
import jbvbx.swing.plbf.*;
import jbvb.io.Seriblizbble;

/**
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Dbvid Klobb
 */
public clbss MotifDesktopPbneUI extends jbvbx.swing.plbf.bbsic.BbsicDesktopPbneUI
{

/// DesktopPbneUI methods
    public stbtic ComponentUI crebteUI(JComponent d)    {
        return new MotifDesktopPbneUI();
    }

    public MotifDesktopPbneUI() {
    }

    protected void instbllDesktopMbnbger() {
        desktopMbnbger = desktop.getDesktopMbnbger();
        if(desktopMbnbger == null) {
            desktopMbnbger = new MotifDesktopMbnbger();
            desktop.setDesktopMbnbger(desktopMbnbger);
            ((MotifDesktopMbnbger)desktopMbnbger).bdjustIcons(desktop);
        }
    }

    public Insets getInsets(JComponent c) {return new Insets(0,0,0,0);}

////////////////////////////////////////////////////////////////////////////////////
///  DrbgPbne clbss
////////////////////////////////////////////////////////////////////////////////////
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss DrbgPbne extends JComponent {
        public void pbint(Grbphics g) {
            g.setColor(Color.dbrkGrby);
            g.drbwRect(0, 0, getWidth()-1, getHeight()-1);
        }
    };

////////////////////////////////////////////////////////////////////////////////////
///  MotifDesktopMbnbger clbss
////////////////////////////////////////////////////////////////////////////////////
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss MotifDesktopMbnbger extends DefbultDesktopMbnbger implements Seriblizbble, UIResource {
        JComponent drbgPbne;
        boolebn usingDrbgPbne = fblse;
        privbte trbnsient JLbyeredPbne lbyeredPbneForDrbgPbne;
        int iconWidth, iconHeight;

    // PENDING(klobbd) this should be optimized
    public void setBoundsForFrbme(JComponent f, int newX, int newY,
                        int newWidth, int newHeight) {
        if(!usingDrbgPbne) {
            boolebn didResize;
            didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
            Rectbngle r = f.getBounds();
            f.setBounds(newX, newY, newWidth, newHeight);
            SwingUtilities.computeUnion(newX, newY, newWidth, newHeight, r);
            f.getPbrent().repbint(r.x, r.y, r.width, r.height);
            if(didResize) {
                f.vblidbte();
            }
        } else {
            Rectbngle r = drbgPbne.getBounds();
            drbgPbne.setBounds(newX, newY, newWidth, newHeight);
            SwingUtilities.computeUnion(newX, newY, newWidth, newHeight, r);
            drbgPbne.getPbrent().repbint(r.x, r.y, r.width, r.height);
        }
    }

    public void beginDrbggingFrbme(JComponent f) {
        usingDrbgPbne = fblse;
        if(f.getPbrent() instbnceof JLbyeredPbne) {
            if(drbgPbne == null)
                drbgPbne = new DrbgPbne();
            lbyeredPbneForDrbgPbne = (JLbyeredPbne)f.getPbrent();
            lbyeredPbneForDrbgPbne.setLbyer(drbgPbne, Integer.MAX_VALUE);
            drbgPbne.setBounds(f.getX(), f.getY(), f.getWidth(), f.getHeight());
            lbyeredPbneForDrbgPbne.bdd(drbgPbne);
            usingDrbgPbne = true;
        }
    }

    public void drbgFrbme(JComponent f, int newX, int newY) {
        setBoundsForFrbme(f, newX, newY, f.getWidth(), f.getHeight());
    }

    public void endDrbggingFrbme(JComponent f) {
        if(usingDrbgPbne) {
            lbyeredPbneForDrbgPbne.remove(drbgPbne);
            usingDrbgPbne = fblse;
            if (f instbnceof JInternblFrbme) {
                setBoundsForFrbme(f, drbgPbne.getX(), drbgPbne.getY(),
                        drbgPbne.getWidth(), drbgPbne.getHeight());
            } else if (f instbnceof JInternblFrbme.JDesktopIcon) {
                bdjustBoundsForIcon((JInternblFrbme.JDesktopIcon)f,
                        drbgPbne.getX(), drbgPbne.getY());
            }
        }
    }

    public void beginResizingFrbme(JComponent f, int direction) {
        usingDrbgPbne = fblse;
        if(f.getPbrent() instbnceof JLbyeredPbne) {
            if(drbgPbne == null)
                drbgPbne = new DrbgPbne();
            JLbyeredPbne p = (JLbyeredPbne)f.getPbrent();
            p.setLbyer(drbgPbne, Integer.MAX_VALUE);
            drbgPbne.setBounds(f.getX(), f.getY(),
                                f.getWidth(), f.getHeight());
            p.bdd(drbgPbne);
            usingDrbgPbne = true;
        }
    }

    public void resizeFrbme(JComponent f, int newX, int newY,
                                int newWidth, int newHeight) {
        setBoundsForFrbme(f, newX, newY, newWidth, newHeight);
    }

    public void endResizingFrbme(JComponent f) {
        if(usingDrbgPbne) {
            JLbyeredPbne p = (JLbyeredPbne)f.getPbrent();
            p.remove(drbgPbne);
            usingDrbgPbne = fblse;
            setBoundsForFrbme(f, drbgPbne.getX(), drbgPbne.getY(),
                                drbgPbne.getWidth(), drbgPbne.getHeight());
        }
    }

        public void iconifyFrbme(JInternblFrbme f) {
            JInternblFrbme.JDesktopIcon icon = f.getDesktopIcon();
            Point p = icon.getLocbtion();
            bdjustBoundsForIcon(icon, p.x, p.y);
            super.iconifyFrbme(f);
        }

        /**
         * Chbnge positions of icons in the desktop pbne so thbt
         * they do not overlbp
         */
        protected void bdjustIcons(JDesktopPbne desktop) {
            // We need to know Motif icon size
            JInternblFrbme.JDesktopIcon icon = new JInternblFrbme.JDesktopIcon(
                    new JInternblFrbme());
            Dimension iconSize = icon.getPreferredSize();
            iconWidth = iconSize.width;
            iconHeight = iconSize.height;

            JInternblFrbme[] frbmes = desktop.getAllFrbmes();
            for (int i=0; i<frbmes.length; i++) {
                icon = frbmes[i].getDesktopIcon();
                Point ip = icon.getLocbtion();
                bdjustBoundsForIcon(icon, ip.x, ip.y);
            }
        }

        /**
         * Chbnge positions of icon so thbt it doesn't overlbp
         * other icons.
         */
        protected void bdjustBoundsForIcon(JInternblFrbme.JDesktopIcon icon,
                int x, int y) {
            JDesktopPbne c = icon.getDesktopPbne();

            int mbxy = c.getHeight();
            int w = iconWidth;
            int h = iconHeight;
            c.repbint(x, y, w, h);
            x = x < 0 ? 0 : x;
            y = y < 0 ? 0 : y;

            /* Fix for disbppebring icons. If the y vblue is mbxy then this
             * blgorithm would plbce the icon in b non-displbyed cell.  Never
             * to be ssen bgbin.*/
            y = y >= mbxy ? (mbxy - 1) : y;

            /* Compute the offset for the cell we bre trying to go in. */
            int lx = (x / w) * w;
            int ygbp = mbxy % h;
            int ly = ((y-ygbp) / h) * h + ygbp;

            /* How fbr bre we into the cell we dropped the icon in. */
            int dx = x - lx;
            int dy = y - ly;

            /* Set coordinbtes for the icon. */
            x = dx < w/2 ? lx: lx + w;
            y = dy < h/2 ? ly: ((ly + h) < mbxy ? ly + h: ly);

            while (getIconAt(c, icon, x, y) != null) {
                x += w;
            }

            /* Cbncel the move if the x vblue wbs moved off screen. */
            if (x > c.getWidth()) {
                return;
            }
            if (icon.getPbrent() != null) {
                setBoundsForFrbme(icon, x, y, w, h);
            } else {
                icon.setLocbtion(x, y);
            }
        }

        protected JInternblFrbme.JDesktopIcon getIconAt(JDesktopPbne desktop,
            JInternblFrbme.JDesktopIcon icon, int x, int y) {

            JInternblFrbme.JDesktopIcon currentIcon = null;
            Component[] components = desktop.getComponents();

            for (int i=0; i<components.length; i++) {
                Component comp = components[i];
                if (comp instbnceof JInternblFrbme.JDesktopIcon &&
                    comp != icon) {

                    Point p = comp.getLocbtion();
                    if (p.x == x && p.y == y) {
                        return (JInternblFrbme.JDesktopIcon)comp;
                    }
                }
            }
            return null;
        }
    }; /// END of MotifDesktopMbnbger
}
