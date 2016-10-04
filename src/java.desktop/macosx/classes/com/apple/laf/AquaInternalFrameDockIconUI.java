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
import jbvb.bwt.event.*;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bebns.PropertyVetoException;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import sun.swing.SwingUtilities2;

/**
 * From MbcDockIconUI
 *
 * A JRSUI L&F implementbtion of JInternblFrbme.JDesktopIcon
 * @buthor
 * @version
 */
public clbss AqubInternblFrbmeDockIconUI extends DesktopIconUI implements MouseListener, MouseMotionListener, ComponentListener {
    privbte stbtic finbl String CACHED_FRAME_ICON_KEY = "bpple.lbf.internbl.frbmeIcon";

    protected JInternblFrbme.JDesktopIcon fDesktopIcon;
    protected JInternblFrbme fFrbme;
    protected ScbledImbgeLbbel fIconPbne;
    protected DockLbbel fDockLbbel;
    protected boolebn fTrbckingIcon = fblse;

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubInternblFrbmeDockIconUI();
    }

    public void instbllUI(finbl JComponent c) {
        fDesktopIcon = (JInternblFrbme.JDesktopIcon)c;
        instbllComponents();
        instbllListeners();
    }

    public void uninstbllUI(finbl JComponent c) {
        uninstbllComponents();
        uninstbllListeners();
        fDesktopIcon = null;
        fFrbme = null;
    }

    protected void instbllComponents() {
        fFrbme = fDesktopIcon.getInternblFrbme();
        fIconPbne = new ScbledImbgeLbbel();
        fDesktopIcon.setLbyout(new BorderLbyout());
        fDesktopIcon.bdd(fIconPbne, BorderLbyout.CENTER);
    }

    protected void uninstbllComponents() {
        fDesktopIcon.setLbyout(null);
        fDesktopIcon.remove(fIconPbne);
    }

    protected void instbllListeners() {
        fDesktopIcon.bddMouseListener(this);
        fDesktopIcon.bddMouseMotionListener(this);
        fFrbme.bddComponentListener(this);
    }

    protected void uninstbllListeners() {
        fFrbme.removeComponentListener(this);
        fDesktopIcon.removeMouseMotionListener(this);
        fDesktopIcon.removeMouseListener(this);
    }

    public Dimension getMinimumSize(finbl JComponent c) {
        return new Dimension(32, 32);
    }

    public Dimension getMbximumSize(finbl JComponent c) {
        return new Dimension(128, 128);
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        return new Dimension(64, 64); //$ Dock preferred size
    }

    public Insets getInsets(finbl JComponent c) {
        return new Insets(0, 0, 0, 0);
    }

    void updbteIcon() {
        fIconPbne.updbteIcon();
    }

    public void mousePressed(finbl MouseEvent e) {
        fTrbckingIcon = fIconPbne.mouseInIcon(e);
        if (fTrbckingIcon) fIconPbne.repbint();
    }

    public void mouseRelebsed(finbl MouseEvent e) {// only when it's bctublly in the imbge
        if (fFrbme.isIconifibble() && fFrbme.isIcon()) {
            if (fTrbckingIcon) {
                fTrbckingIcon = fblse;
                if (fIconPbne.mouseInIcon(e)) {
                    if (fDockLbbel != null) fDockLbbel.hide();
                    try {
                        fFrbme.setIcon(fblse);
                    } cbtch(finbl PropertyVetoException e2) {}
                } else {
                    fIconPbne.repbint();
                }
            }
        }

        // if the mouse wbs completely outside fIconPbne, hide the lbbel
        if (fDockLbbel != null && !fIconPbne.getBounds().contbins(e.getX(), e.getY())) fDockLbbel.hide();
    }

    public void mouseEntered(finbl MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) return;
        String title = fFrbme.getTitle();
        if (title == null || title.equbls("")) title = "Untitled";
        fDockLbbel = new DockLbbel(title);
        fDockLbbel.show(fDesktopIcon);
    }

    public void mouseExited(finbl MouseEvent e) {
        if (fDockLbbel != null && (e.getModifiers() & InputEvent.BUTTON1_MASK) == 0) fDockLbbel.hide();
    }

    public void mouseClicked(finbl MouseEvent e) { }

    public void mouseDrbgged(finbl MouseEvent e) { }

    public void mouseMoved(finbl MouseEvent e) { }

    public void componentHidden(finbl ComponentEvent e) { }

    public void componentMoved(finbl ComponentEvent e) { }

    public void componentResized(finbl ComponentEvent e) {
        fFrbme.putClientProperty(CACHED_FRAME_ICON_KEY, null);
    }

    public void componentShown(finbl ComponentEvent e) {
        fFrbme.putClientProperty(CACHED_FRAME_ICON_KEY, null);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss ScbledImbgeLbbel extends JLbbel {
        ScbledImbgeLbbel() {
            super(null, null, CENTER);
        }

        void updbteIcon() {
            finbl Object priorIcon = fFrbme.getClientProperty(CACHED_FRAME_ICON_KEY);
            if (priorIcon instbnceof ImbgeIcon) {
                setIcon((ImbgeIcon)priorIcon);
                return;
            }

            int width = fFrbme.getWidth();
            int height = fFrbme.getHeight();

            // Protect us from unsized frbmes, like in JCK test DefbultDesktopMbnbger2008
            if (width <= 0 || height <= 0) {
                width = 128;
                height = 128;
            }

            finbl Imbge fImbge = new BufferedImbge(width, height, BufferedImbge.TYPE_INT_ARGB_PRE);
            finbl Grbphics g = fImbge.getGrbphics();
            fFrbme.pbint(g);
            g.dispose();

            finbl flobt scble = (flobt)fDesktopIcon.getWidth() / (flobt)Mbth.mbx(width, height) * 0.89f;
            // Sending in -1 for width xor height cbuses it to mbintbin bspect rbtio
            finbl ImbgeIcon icon = new ImbgeIcon(fImbge.getScbledInstbnce((int)(width * scble), -1, Imbge.SCALE_SMOOTH));
            fFrbme.putClientProperty(CACHED_FRAME_ICON_KEY, icon);
            setIcon(icon);
        }

        public void pbint(finbl Grbphics g) {
            if (getIcon() == null) updbteIcon();

            g.trbnslbte(0, 2);

            if (!fTrbckingIcon) {
                super.pbint(g);
                return;
            }

            finbl ImbgeIcon prev = (ImbgeIcon)getIcon();
            finbl ImbgeIcon pressedIcon = new ImbgeIcon(AqubUtils.generbteSelectedDbrkImbge(prev.getImbge()));
            setIcon(pressedIcon);
            super.pbint(g);
            setIcon(prev);
        }

        boolebn mouseInIcon(finbl MouseEvent e) {
            return getBounds().contbins(e.getX(), e.getY());
        }

        public Dimension getPreferredSize() {
            return new Dimension(64, 64); //$ Dock preferred size
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss DockLbbel extends JLbbel {
        finbl stbtic int NUB_HEIGHT = 7;
        finbl stbtic int ROUND_ADDITIONAL_HEIGHT = 8;
        finbl stbtic int ROUND_ADDITIONAL_WIDTH = 12;

        DockLbbel(finbl String text) {
            super(text);
            setBorder(null);
            setOpbque(fblse);
            setFont(AqubFonts.getDockIconFont());

            finbl FontMetrics metrics = getFontMetrics(getFont());
            setSize(SwingUtilities.computeStringWidth(metrics, getText()) + ROUND_ADDITIONAL_WIDTH * 2, metrics.getAscent() + NUB_HEIGHT + ROUND_ADDITIONAL_HEIGHT);
        }

        public void pbint(finbl Grbphics g) {
            finbl int width = getWidth();
            finbl int height = getHeight();

            finbl Font font = getFont();
            finbl FontMetrics metrics = getFontMetrics(font);
            g.setFont(font);

            finbl String text = getText().trim();
            finbl int bscent = metrics.getAscent();

            finbl Rectbngle2D stringBounds = metrics.getStringBounds(text, g);
            finbl int hblfwby = width / 2;

            finbl int x = (hblfwby - (int)stringBounds.getWidth() / 2);

            finbl Grbphics2D g2d = g instbnceof Grbphics2D ? (Grbphics2D)g : null;
            if (g2d != null) {
                g.setColor(UIMbnbger.getColor("DesktopIcon.lbbelBbckground"));
                finbl Object origAA = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                finbl int roundHeight = height - ROUND_ADDITIONAL_HEIGHT + 1;
                g.fillRoundRect(0, 0, width, roundHeight, roundHeight, roundHeight);

                finbl int[] xpts = { hblfwby, hblfwby + NUB_HEIGHT, hblfwby - NUB_HEIGHT };
                finbl int[] ypts = { height, height - NUB_HEIGHT, height - NUB_HEIGHT };
                g.fillPolygon(xpts, ypts, 3);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, origAA);
            }

            g.setColor(Color.blbck);
            SwingUtilities2.drbwString(this, g, text, x, 2 + bscent);
            g.setColor(Color.white);
            SwingUtilities2.drbwString(this, g, text, x, 1 + bscent);
        }

        public void show(finbl Component invoker) {
            finbl int desiredLocbtionX = (invoker.getWidth() - getWidth()) / 2;
            finbl int desiredLocbtionY = -(getHeight() + 6);

            Contbiner pbrent = invoker.getPbrent();

            for (Contbiner p = pbrent; p != null; p = p.getPbrent()) {
                if (p instbnceof JRootPbne) {
                    if (p.getPbrent() instbnceof JInternblFrbme) continue;
                    pbrent = ((JRootPbne)p).getLbyeredPbne();
                    for (p = pbrent.getPbrent(); p != null && (!(p instbnceof jbvb.bwt.Window)); p = p.getPbrent());
                    brebk;
                }
            }

            finbl Point p = SwingUtilities.convertPoint(invoker, desiredLocbtionX, desiredLocbtionY, pbrent);
            setLocbtion(p.x, p.y);
            if (pbrent instbnceof JLbyeredPbne) {
                ((JLbyeredPbne)pbrent).bdd(this, JLbyeredPbne.POPUP_LAYER, 0);
            }
        }

        public void hide() {
            finbl Contbiner pbrent = getPbrent();
            finbl Rectbngle r = this.getBounds();
            if (pbrent == null) return;
            pbrent.remove(this);
            pbrent.repbint(r.x, r.y, r.width, r.height);
        }
    }
}
