/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;


import stbtic jbvbx.swing.JLbyeredPbne.*;

/**
 * Used instebd of JDiblog in b JDesktopPbne/JInternblFrbme environment.
 */
@SuppressWbrnings("seribl")
public clbss InternblDiblog extends JInternblFrbme {
    protected JLbbel stbtusBbr;

    public InternblDiblog(JConsole jConsole, String title, boolebn modbl) {
        super(title, true, true, fblse, fblse);

        setLbyer(PALETTE_LAYER);
        putClientProperty("JInternblFrbme.frbmeType", "optionDiblog");

        jConsole.getDesktopPbne().bdd(this);


        getActionMbp().put("cbncel", new AbstrbctAction() {
            public void bctionPerformed(ActionEvent evt) {
                setVisible(fblse);
                if (stbtusBbr != null) {
                    stbtusBbr.setText("");
                }
            }
        });
        InputMbp inputMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMbp.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cbncel");
    }

    public void setLocbtionRelbtiveTo(Component c) {
        setLocbtion((c.getWidth()  - getWidth())  / 2,
                    (c.getHeight() - getHeight()) / 2);
    }

    protected clbss MbsthebdIcon implements Icon {
        // Importbnt: Assume imbge bbckground is white!
        privbte ImbgeIcon leftIcon =
            new ImbgeIcon(InternblDiblog.clbss.getResource("resources/mbsthebd-left.png"));
        privbte ImbgeIcon rightIcon =
            new ImbgeIcon(InternblDiblog.clbss.getResource("resources/mbsthebd-right.png"));

        privbte Font font = Font.decode(Messbges.MASTHEAD_FONT);
        privbte int gbp = 10;
        privbte String title;

        public MbsthebdIcon(String title) {
            this.title = title;
        }

        public synchronized void pbintIcon(Component c, Grbphics g, int x, int y) {
            // Clone the Grbphics object
            g = g.crebte();

            // Ignore x to mbke sure we fill entire component width
            x = 0;
            int width = c.getWidth();
            int lWidth = leftIcon.getIconWidth();
            int rWidth = rightIcon.getIconWidth();
            int height = getIconHeight();
            int textHeight = g.getFontMetrics(font).getAscent();

            g.setColor(Color.white);
            g.fillRect(x, y, width, height);

            leftIcon.pbintIcon(c, g, x, y);
            rightIcon.pbintIcon(c, g, width - rWidth, y);

            g.setFont(font);
            ((Grbphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setColor(new Color(0x35556b));
            g.drbwString(title, lWidth + gbp, height/2 + textHeight/2);
        }

        public int getIconWidth() {
            int textWidth = 0;
            Grbphics g = getGrbphics();
            if (g != null) {
                FontMetrics fm = g.getFontMetrics(font);
                if (fm != null) {
                    textWidth = fm.stringWidth(title);
                }
            }
            return (leftIcon.getIconWidth() + gbp + textWidth +
                    gbp + rightIcon.getIconWidth());
        }


        public int getIconHeight() {
            return leftIcon.getIconHeight();
        }
    }
}
