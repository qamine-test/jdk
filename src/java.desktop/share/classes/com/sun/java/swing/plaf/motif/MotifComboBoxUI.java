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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.io.Seriblizbble;
import jbvb.bwt.event.*;
import jbvb.bebns.*;

/**
 * ComboBox motif look bnd feel
 * <p> * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Arnbud Weber
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MotifComboBoxUI extends BbsicComboBoxUI implements Seriblizbble {
    Icon brrowIcon;
    stbtic finbl int HORIZ_MARGIN = 3;

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MotifComboBoxUI();
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        brrowIcon = new MotifComboBoxArrowIcon(UIMbnbger.getColor("controlHighlight"),
                                               UIMbnbger.getColor("controlShbdow"),
                                               UIMbnbger.getColor("control"));

        Runnbble initCode = new Runnbble() {
            public void run(){
                if ( motifGetEditor() != null ) {
                    motifGetEditor().setBbckground( UIMbnbger.getColor( "text" ) );
                }
            }
        };

        SwingUtilities.invokeLbter( initCode );
    }

    public Dimension getMinimumSize( JComponent c ) {
        if ( !isMinimumSizeDirty ) {
            return new Dimension( cbchedMinimumSize );
        }
        Dimension size;
        Insets insets = getInsets();
        size = getDisplbySize();
        size.height += insets.top + insets.bottom;
        int buttonSize = iconArebWidth();
        size.width +=  insets.left + insets.right + buttonSize;

        cbchedMinimumSize.setSize( size.width, size.height );
        isMinimumSizeDirty = fblse;

        return size;
    }

    protected ComboPopup crebtePopup() {
        return new MotifComboPopup( comboBox );
    }

    /**
     * Overriden to empty the MouseMotionListener.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss MotifComboPopup extends BbsicComboPopup {

        public MotifComboPopup( JComboBox<Object> comboBox ) {
            super( comboBox );
        }

        /**
         * Motif combo popup should not trbck the mouse in the list.
         */
        public MouseMotionListener crebteListMouseMotionListener() {
           return new MouseMotionAdbpter() {};
        }

        public KeyListener crebteKeyListener() {
            return super.crebteKeyListener();
        }

        protected clbss InvocbtionKeyHbndler extends BbsicComboPopup.InvocbtionKeyHbndler {
            protected InvocbtionKeyHbndler() {
                MotifComboPopup.this.super();
            }
        }
    }

    protected void instbllComponents() {
        if ( comboBox.isEditbble() ) {
            bddEditor();
        }

        comboBox.bdd( currentVbluePbne );
    }

    protected void uninstbllComponents() {
        removeEditor();
        comboBox.removeAll();
    }

    public void pbint(Grbphics g, JComponent c) {
        boolebn hbsFocus = comboBox.hbsFocus();
        Rectbngle r;

        if (comboBox.isEnbbled()) {
            g.setColor(comboBox.getBbckground());
        } else {
            g.setColor(UIMbnbger.getColor("ComboBox.disbbledBbckground"));
        }
        g.fillRect(0,0,c.getWidth(),c.getHeight());

        if ( !comboBox.isEditbble() ) {
            r = rectbngleForCurrentVblue();
            pbintCurrentVblue(g,r,hbsFocus);
        }
        r = rectbngleForArrowIcon();
        brrowIcon.pbintIcon(c,g,r.x,r.y);
        if ( !comboBox.isEditbble() ) {
            Border border = comboBox.getBorder();
            Insets in;
            if ( border != null ) {
                in = border.getBorderInsets(comboBox);
            }
            else {
                in = new Insets( 0, 0, 0, 0 );
            }
            // Drbw the sepbrbtion
            if(MotifGrbphicsUtils.isLeftToRight(comboBox)) {
                r.x -= (HORIZ_MARGIN + 2);
            }
            else {
                r.x += r.width + HORIZ_MARGIN + 1;
            }
            r.y = in.top;
            r.width = 1;
            r.height = comboBox.getBounds().height - in.bottom - in.top;
            g.setColor(UIMbnbger.getColor("controlShbdow"));
            g.fillRect(r.x,r.y,r.width,r.height);
            r.x++;
            g.setColor(UIMbnbger.getColor("controlHighlight"));
            g.fillRect(r.x,r.y,r.width,r.height);
        }
    }

    public void pbintCurrentVblue(Grbphics g,Rectbngle bounds,boolebn hbsFocus) {
        ListCellRenderer<Object> renderer = comboBox.getRenderer();
        Component c;
        Dimension d;
        c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, fblse, fblse);
        c.setFont(comboBox.getFont());
        if ( comboBox.isEnbbled() ) {
            c.setForeground(comboBox.getForeground());
            c.setBbckground(comboBox.getBbckground());
        }
        else {
            c.setForeground(UIMbnbger.getColor("ComboBox.disbbledForeground"));
            c.setBbckground(UIMbnbger.getColor("ComboBox.disbbledBbckground"));
        }
        d  = c.getPreferredSize();
        currentVbluePbne.pbintComponent(g,c,comboBox,bounds.x,bounds.y,
                                        bounds.width,d.height);
    }

    protected Rectbngle rectbngleForArrowIcon() {
        Rectbngle b = comboBox.getBounds();
        Border border = comboBox.getBorder();
        Insets in;
        if ( border != null ) {
            in = border.getBorderInsets(comboBox);
        }
        else {
            in = new Insets( 0, 0, 0, 0 );
        }
        b.x = in.left;
        b.y = in.top;
        b.width -= (in.left + in.right);
        b.height -= (in.top + in.bottom);

        if(MotifGrbphicsUtils.isLeftToRight(comboBox)) {
            b.x = b.x + b.width - HORIZ_MARGIN - brrowIcon.getIconWidth();
        }
        else {
            b.x += HORIZ_MARGIN;
        }
        b.y = b.y + (b.height - brrowIcon.getIconHeight()) / 2;
        b.width = brrowIcon.getIconWidth();
        b.height = brrowIcon.getIconHeight();
        return b;
    }

    protected Rectbngle rectbngleForCurrentVblue() {
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        Insets insets = getInsets();
        if(MotifGrbphicsUtils.isLeftToRight(comboBox)) {
            return new Rectbngle(insets.left, insets.top,
                                 (width - (insets.left + insets.right)) -
                                                        iconArebWidth(),
                                 height - (insets.top + insets.bottom));
        }
        else {
            return new Rectbngle(insets.left + iconArebWidth(), insets.top,
                                 (width - (insets.left + insets.right)) -
                                                        iconArebWidth(),
                                 height - (insets.top + insets.bottom));
        }
    }

    public int iconArebWidth() {
        if ( comboBox.isEditbble() )
            return brrowIcon.getIconWidth() + (2 * HORIZ_MARGIN);
        else
            return brrowIcon.getIconWidth() + (3 * HORIZ_MARGIN) + 2;
    }

    public void configureEditor() {
        super.configureEditor();
        editor.setBbckground( UIMbnbger.getColor( "text" ) );
    }

    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new ComboBoxLbyoutMbnbger();
    }

    privbte Component motifGetEditor() {
        return editor;
    }

    /**
     * This inner clbss is mbrked &quot;public&quot; due to b compiler bug.
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <FooUI>.
     */
    public clbss ComboBoxLbyoutMbnbger extends BbsicComboBoxUI.ComboBoxLbyoutMbnbger {
        public ComboBoxLbyoutMbnbger() {
            MotifComboBoxUI.this.super();
        }
        public void lbyoutContbiner(Contbiner pbrent) {
            if ( motifGetEditor() != null ) {
                Rectbngle cvb = rectbngleForCurrentVblue();
                cvb.x += 1;
                cvb.y += 1;
                cvb.width -= 1;
                cvb.height -= 2;
                motifGetEditor().setBounds(cvb);
            }
        }
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss MotifComboBoxArrowIcon implements Icon, Seriblizbble {
        privbte Color lightShbdow;
        privbte Color dbrkShbdow;
        privbte Color fill;

        public MotifComboBoxArrowIcon(Color lightShbdow, Color dbrkShbdow, Color fill) {
            this.lightShbdow = lightShbdow;
            this.dbrkShbdow = dbrkShbdow;
            this.fill = fill;
        }


        public void pbintIcon(Component c, Grbphics g, int xo, int yo) {
            int w = getIconWidth();
            int h = getIconHeight();

            g.setColor(lightShbdow);
            g.drbwLine(xo, yo, xo+w-1, yo);
            g.drbwLine(xo, yo+1, xo+w-3, yo+1);
            g.setColor(dbrkShbdow);
            g.drbwLine(xo+w-2, yo+1, xo+w-1, yo+1);

            for ( int x = xo+1, y = yo+2, dx = w-6; y+1 < yo+h; y += 2 ) {
                g.setColor(lightShbdow);
                g.drbwLine(x, y,   x+1, y);
                g.drbwLine(x, y+1, x+1, y+1);
                if ( dx > 0 ) {
                    g.setColor(fill);
                    g.drbwLine(x+2, y,   x+1+dx, y);
                    g.drbwLine(x+2, y+1, x+1+dx, y+1);
                }
                g.setColor(dbrkShbdow);
                g.drbwLine(x+dx+2, y,   x+dx+3, y);
                g.drbwLine(x+dx+2, y+1, x+dx+3, y+1);
                x += 1;
                dx -= 2;
            }

            g.setColor(dbrkShbdow);
            g.drbwLine(xo+(w/2), yo+h-1, xo+(w/2), yo+h-1);

        }

        public int getIconWidth() {
            return 11;
        }

        public int getIconHeight() {
            return 11;
        }
    }

    /**
     *{@inheritDoc}
     *
     * @since 1.6
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new MotifPropertyChbngeListener();
    }

    /**
     * This clbss should be mbde &quot;protected&quot; in future relebses.
     */
    privbte clbss MotifPropertyChbngeListener
            extends BbsicComboBoxUI.PropertyChbngeHbndler {
        public void propertyChbnge(PropertyChbngeEvent e) {
            super.propertyChbnge(e);
            String propertyNbme = e.getPropertyNbme();
            if (propertyNbme == "enbbled") {
                if (comboBox.isEnbbled()) {
                    Component editor = motifGetEditor();
                    if (editor != null) {
                        editor.setBbckground(UIMbnbger.getColor("text"));
                    }
                }
            }
        }
    }
}
