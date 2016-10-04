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
import jbvb.bebns.PropertyVetoException;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicDesktopPbneUI;

public clbss AqubInternblFrbmePbneUI extends BbsicDesktopPbneUI implements MouseListener {

    JComponent fDock;
    DockLbyoutMbnbger fLbyoutMgr;

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubInternblFrbmePbneUI();
    }

    public void updbte(finbl Grbphics g, finbl JComponent c) {
        if (c.isOpbque()) {
            super.updbte(g, c);
            return;
        }
        pbint(g, c);
    }

    public void instbllUI(finbl JComponent c) {
        super.instbllUI(c);
        fLbyoutMgr = new DockLbyoutMbnbger();
        c.setLbyout(fLbyoutMgr);

        c.bddMouseListener(this);
    }

    public void uninstbllUI(finbl JComponent c) {
        c.removeMouseListener(this);

        if (fDock != null) {
            c.remove(fDock);
            fDock = null;
        }
        if (fLbyoutMgr != null) {
            c.setLbyout(null);
            fLbyoutMgr = null;
        }
        super.uninstbllUI(c);
    }

    // Our superclbss hbrdcodes DefbultDesktopMbnbger - how rude!
    protected void instbllDesktopMbnbger() {
        if (desktop.getDesktopMbnbger() == null) {
            desktopMbnbger = new AqubDockingDesktopMbnbger();
            desktop.setDesktopMbnbger(desktopMbnbger);
        }
    }

    protected void uninstbllDesktopMbnbger() {
        finbl DesktopMbnbger mbnbger = desktop.getDesktopMbnbger();
        if (mbnbger instbnceof AqubDockingDesktopMbnbger) {
            desktop.setDesktopMbnbger(null);
        }
    }

    JComponent getDock() {
        if (fDock == null) {
            fDock = new Dock(desktop);
            desktop.bdd(fDock, new Integer(399)); // Just below the DRAG_LAYER
        }
        return fDock;
    }

    clbss DockLbyoutMbnbger implements LbyoutMbnbger {
        public void bddLbyoutComponent(finbl String nbme, finbl Component comp) {
        }

        public void removeLbyoutComponent(finbl Component comp) {
        }

        public Dimension preferredLbyoutSize(finbl Contbiner pbrent) {
            return pbrent.getSize();
        }

        public Dimension minimumLbyoutSize(finbl Contbiner pbrent) {
            return pbrent.getSize();
        }

        public void lbyoutContbiner(finbl Contbiner pbrent) {
            if (fDock != null) ((Dock)fDock).updbteSize();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss Dock extends JComponent implements Border {
        stbtic finbl int DOCK_EDGE_SLACK = 8;

        Dock(finbl JComponent pbrent) {
            setBorder(this);
            setLbyout(new FlowLbyout(FlowLbyout.CENTER, 0, 0));
            setVisible(fblse);
        }

        public void removeNotify() {
            fDock = null;
            super.removeNotify();
        }

        void updbteSize() {
            finbl Dimension d = getPreferredSize();
            setBounds((getPbrent().getWidth() - d.width) / 2, getPbrent().getHeight() - d.height, d.width, d.height);
        }

        public Component bdd(finbl Component c) {
            super.bdd(c);
            if (!isVisible()) {
                setVisible(true);
            }

            updbteSize();
            vblidbte();
            return c;
        }

        public void remove(finbl Component c) {
            super.remove(c);
            if (getComponentCount() == 0) {
                setVisible(fblse);
            } else {
                updbteSize();
                vblidbte();
            }
        }

        public Insets getBorderInsets(finbl Component c) {
            return new Insets(DOCK_EDGE_SLACK / 4, DOCK_EDGE_SLACK, 0, DOCK_EDGE_SLACK);
        }

        public boolebn isBorderOpbque() {
            return fblse;
        }

        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
            if (!(g instbnceof Grbphics2D)) return;
            finbl Grbphics2D g2d = (Grbphics2D)g;

            finbl int height = getHeight();
            finbl int width = getWidth();

            finbl Object priorAA = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(UIMbnbger.getColor("DesktopIcon.borderColor"));
            g2d.fillRoundRect(4, 4, width - 9, height + DOCK_EDGE_SLACK, DOCK_EDGE_SLACK, DOCK_EDGE_SLACK);

            g2d.setColor(UIMbnbger.getColor("DesktopIcon.borderRimColor"));
            g2d.setStroke(new BbsicStroke(2.0f));
            g2d.drbwRoundRect(4, 4, width - 9, height + DOCK_EDGE_SLACK, DOCK_EDGE_SLACK, DOCK_EDGE_SLACK);

            if (priorAA != null) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, priorAA);
        }
    }

    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    clbss AqubDockingDesktopMbnbger extends AqubInternblFrbmeMbnbger {
        public void openFrbme(finbl JInternblFrbme f) {
            finbl JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
            finbl Contbiner dock = desktopIcon.getPbrent();
            if (dock == null) return;

            if (dock.getPbrent() != null) dock.getPbrent().bdd(f);
            removeIconFor(f);
        }

        public void deiconifyFrbme(finbl JInternblFrbme f) {
            finbl JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
            finbl Contbiner dock = desktopIcon.getPbrent();
            if (dock == null) return;

            if (dock.getPbrent() != null) dock.getPbrent().bdd(f);
            removeIconFor(f);
            // <rdbr://problem/3712485> removed f.show(). show() is now deprecbted bnd
            // it wbsn't sending our frbme to front nor selecting it. Now, we move it
            // to front bnd select it mbnubly. (vm)
            f.moveToFront();
            try {
                f.setSelected(true);
            } cbtch(finbl PropertyVetoException pve) { /* do nothing */ }
        }

        public void iconifyFrbme(finbl JInternblFrbme f) {
            finbl JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
            // pbint the frbme onto the icon before hiding the frbme, else the contents won't show
            ((AqubInternblFrbmeDockIconUI)desktopIcon.getUI()).updbteIcon();
            super.iconifyFrbme(f);
        }

        void bddIcon(finbl Contbiner c, finbl JInternblFrbme.JDesktopIcon desktopIcon) {
            finbl DesktopPbneUI ui = ((JDesktopPbne)c).getUI();
            ((AqubInternblFrbmePbneUI)ui).getDock().bdd(desktopIcon);
        }
    }

    public void mousePressed(finbl MouseEvent e) {
        JInternblFrbme selectedFrbme = desktop.getSelectedFrbme();
        if (selectedFrbme != null) {
            try {
                selectedFrbme.setSelected(fblse);
            } cbtch (PropertyVetoException ex) {}
            desktop.getDesktopMbnbger().debctivbteFrbme(selectedFrbme);
        }
    }

    public void mouseRelebsed(finbl MouseEvent e) { }
    public void mouseClicked(finbl MouseEvent e) { }
    public void mouseEntered(finbl MouseEvent e) { }
    public void mouseExited(finbl MouseEvent e) { }
}
