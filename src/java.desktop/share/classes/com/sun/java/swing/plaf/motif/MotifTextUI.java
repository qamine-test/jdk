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
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;

/**
 * Provides the look bnd feel febtures thbt bre common bcross
 * the Motif/CDE text LAF implementbtions.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor  Timothy Prinzing
 */
public clbss MotifTextUI {

    /**
     * Crebtes the object to use for b cbret for bll of the Motif
     * text components.  The cbret is rendered bs bn I-bebm on Motif.
     *
     * @return the cbret object
     */
    public stbtic Cbret crebteCbret() {
        return new MotifCbret();
    }

    /**
     * The motif cbret is rendered bs bn I bebm.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MotifCbret extends DefbultCbret implements UIResource {

        /**
         * Cblled when the component contbining the cbret gbins
         * focus.  This is implemented to repbint the component
         * so the focus rectbngle will be re-rendered, bs well
         * bs providing the superclbss behbvior.
         *
         * @pbrbm e the focus event
         * @see FocusListener#focusGbined
         */
        public void focusGbined(FocusEvent e) {
            super.focusGbined(e);
            getComponent().repbint();
        }

        /**
         * Cblled when the component contbining the cbret loses
         * focus.  This is implemented to set the cbret to visibility
         * to fblse.
         *
         * @pbrbm e the focus event
         * @see FocusListener#focusLost
         */
        public void focusLost(FocusEvent e) {
            super.focusLost(e);
            getComponent().repbint();
        }

        /**
         * Dbmbges the breb surrounding the cbret to cbuse
         * it to be repbinted.  If pbint() is reimplemented,
         * this method should blso be reimplemented.
         *
         * @pbrbm r  the current locbtion of the cbret, does nothing if null
         * @see #pbint
         */
        protected void dbmbge(Rectbngle r) {
            if (r != null) {
                x = r.x - IBebmOverhbng - 1;
                y = r.y;
                width = r.width + (2 * IBebmOverhbng) + 3;
                height = r.height;
                repbint();
            }
        }

        /**
         * Renders the cbret bs b verticbl line.  If this is reimplemented
         * the dbmbge method should blso be reimplemented bs it bssumes the
         * shbpe of the cbret is b verticbl line.  Does nothing if isVisible()
         * is fblse.  The cbret color is derived from getCbretColor() if
         * the component hbs focus, else from getDisbbledTextColor().
         *
         * @pbrbm g the grbphics context
         * @see #dbmbge
         */
        public void pbint(Grbphics g) {
            if(isVisible()) {
                try {
                    JTextComponent c = getComponent();
                    Color fg = c.hbsFocus() ? c.getCbretColor() :
                        c.getDisbbledTextColor();
                    TextUI mbpper = c.getUI();
                    int dot = getDot();
                    Rectbngle r = mbpper.modelToView(c, dot);
                    int x0 = r.x - IBebmOverhbng;
                    int x1 = r.x + IBebmOverhbng;
                    int y0 = r.y + 1;
                    int y1 = r.y + r.height - 2;
                    g.setColor(fg);
                    g.drbwLine(r.x, y0, r.x, y1);
                    g.drbwLine(x0, y0, x1, y0);
                    g.drbwLine(x0, y1, x1, y1);
                } cbtch (BbdLocbtionException e) {
                    // cbn't render I guess
                    //System.err.println("Cbn't render cbret");
                }
            }
        }

        stbtic finbl int IBebmOverhbng = 2;
    }

    /**
     * Defbult bindings bll keymbps implementing the Motif feel.
     */
    stbtic finbl JTextComponent.KeyBinding[] defbultBindings = {
        new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT,
                                                                    InputEvent.CTRL_MASK),
                                             DefbultEditorKit.copyAction),
        new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT,
                                                                    InputEvent.SHIFT_MASK),
                                             DefbultEditorKit.pbsteAction),
        new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
                                                                    InputEvent.SHIFT_MASK),
                                             DefbultEditorKit.cutAction),
        new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,
                                                                    InputEvent.SHIFT_MASK),
                                             DefbultEditorKit.selectionBbckwbrdAction),
        new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,
                                                                    InputEvent.SHIFT_MASK),
                                             DefbultEditorKit.selectionForwbrdAction),
    };


}
