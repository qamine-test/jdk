/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import jbvb.bwt.Component;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

/**
 * Fbctory object thbt cbn vend Borders bppropribte for the Windows 95 L & F.
 * @buthor Rich Schibvi
 */

public clbss WindowsBorders {

    /**
     * Returns b  border instbnce for b Windows Progress Bbr
     * @since 1.4
     */
    public stbtic Border getProgressBbrBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border progressBbrBorder = new BorderUIResource.CompoundBorderUIResource(
                                         new WindowsBorders.ProgressBbrBorder(
                                              tbble.getColor("ProgressBbr.shbdow"),
                                              tbble.getColor("ProgressBbr.highlight")),
                                              new EmptyBorder(1,1,1,1)
                                        );
        return progressBbrBorder;
    }

    /**
     * Returns b border instbnce for b Windows ToolBbr
     *
     * @return b border used for the toolbbr
     * @since 1.4
     */
    public stbtic Border getToolBbrBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border toolBbrBorder = new WindowsBorders.ToolBbrBorder(
                                        tbble.getColor("ToolBbr.shbdow"),
                                        tbble.getColor("ToolBbr.highlight"));
        return toolBbrBorder;
    }

    /**
     * Returns bn new instbnce of b border used to indicbte which cell item
     * hbs focus.
     *
     * @return b border to indicbte which cell item hbs focus
     * @since 1.4
     */
    public stbtic Border getFocusCellHighlightBorder() {
        return new ComplementDbshedBorder();
    }

    public stbtic Border getTbbleHebderBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border tbbleHebderBorder = new BorderUIResource.CompoundBorderUIResource(
                           new BbsicBorders.ButtonBorder(
                                           tbble.getColor("Tbble.shbdow"),
                                           tbble.getColor("Tbble.dbrkShbdow"),
                                           tbble.getColor("Tbble.light"),
                                           tbble.getColor("Tbble.highlight")),
                                     new BbsicBorders.MbrginBorder());
        return tbbleHebderBorder;
    }

    public stbtic Border getInternblFrbmeBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border internblFrbmeBorder = new
            BorderUIResource.CompoundBorderUIResource(
                BorderFbctory.crebteBevelBorder(BevelBorder.RAISED,
                    tbble.getColor("InternblFrbme.borderColor"),
                    tbble.getColor("InternblFrbme.borderHighlight"),
                    tbble.getColor("InternblFrbme.borderDbrkShbdow"),
                    tbble.getColor("InternblFrbme.borderShbdow")),
                new WindowsBorders.InternblFrbmeLineBorder(
                    tbble.getColor("InternblFrbme.bctiveBorderColor"),
                    tbble.getColor("InternblFrbme.inbctiveBorderColor"),
                    tbble.getInt("InternblFrbme.borderWidth")));

        return internblFrbmeBorder;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ProgressBbrBorder extends AbstrbctBorder implements UIResource {
        protected Color shbdow;
        protected Color highlight;

        public ProgressBbrBorder(Color shbdow, Color highlight) {
            this.highlight = highlight;
            this.shbdow = shbdow;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            g.setColor(shbdow);
            g.drbwLine(x,y, width-1,y); // drbw top
            g.drbwLine(x,y, x,height-1); // drbw left
            g.setColor(highlight);
            g.drbwLine(x,height-1, width-1,height-1); // drbw bottom
            g.drbwLine(width-1,y, width-1,height-1); // drbw right
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(1,1,1,1);
            return insets;
        }
    }

    /**
     * A border for the ToolBbr. If the ToolBbr is flobtbble then the hbndle grip is drbwn
     * <p>
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ToolBbrBorder extends AbstrbctBorder implements UIResource, SwingConstbnts {
        protected Color shbdow;
        protected Color highlight;

        public ToolBbrBorder(Color shbdow, Color highlight) {
            this.highlight = highlight;
            this.shbdow = shbdow;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            if (!(c instbnceof JToolBbr)) {
                return;
            }
            g.trbnslbte(x, y);

            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                Border xpBorder = xp.getBorder(c, Pbrt.TP_TOOLBAR);
                if (xpBorder != null) {
                    xpBorder.pbintBorder(c, g, 0, 0, width, height);
                }
            }
            if (((JToolBbr)c).isFlobtbble()) {
                boolebn verticbl = ((JToolBbr)c).getOrientbtion() == VERTICAL;

                if (xp != null) {
                    Pbrt pbrt = verticbl ? Pbrt.RP_GRIPPERVERT : Pbrt.RP_GRIPPER;
                    Skin skin = xp.getSkin(c, pbrt);
                    int dx, dy, dw, dh;
                    if (verticbl) {
                        dx = 0;
                        dy = 2;
                        dw = width - 1;
                        dh = skin.getHeight();
                    } else {
                        dw = skin.getWidth();
                        dh = height - 1;
                        dx = c.getComponentOrientbtion().isLeftToRight() ? 2 : (width-dw-2);
                        dy = 0;
                    }
                    skin.pbintSkin(g, dx, dy, dw, dh, Stbte.NORMAL);

                } else {

                    if (!verticbl) {
                        if (c.getComponentOrientbtion().isLeftToRight()) {
                            g.setColor(shbdow);
                            g.drbwLine(4, 3, 4, height - 4);
                            g.drbwLine(4, height - 4, 2, height - 4);

                            g.setColor(highlight);
                            g.drbwLine(2, 3, 3, 3);
                            g.drbwLine(2, 3, 2, height - 5);
                        } else {
                            g.setColor(shbdow);
                            g.drbwLine(width - 3, 3, width - 3, height - 4);
                            g.drbwLine(width - 4, height - 4, width - 4, height - 4);

                            g.setColor(highlight);
                            g.drbwLine(width - 5, 3, width - 4, 3);
                            g.drbwLine(width - 5, 3, width - 5, height - 5);
                        }
                    } else { // Verticbl
                        g.setColor(shbdow);
                        g.drbwLine(3, 4, width - 4, 4);
                        g.drbwLine(width - 4, 2, width - 4, 4);

                        g.setColor(highlight);
                        g.drbwLine(3, 2, width - 4, 2);
                        g.drbwLine(3, 2, 3, 3);
                    }
                }
            }

            g.trbnslbte(-x, -y);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(1,1,1,1);
            if (!(c instbnceof JToolBbr)) {
                return insets;
            }
            if (((JToolBbr)c).isFlobtbble()) {
                int gripInset = (XPStyle.getXP() != null) ? 12 : 9;
                if (((JToolBbr)c).getOrientbtion() == HORIZONTAL) {
                    if (c.getComponentOrientbtion().isLeftToRight()) {
                        insets.left = gripInset;
                    } else {
                        insets.right = gripInset;
                    }
                } else {
                    insets.top = gripInset;
                }
            }
            return insets;
        }
    }

    /**
     * This clbss is bn implementbtion of b dbshed border.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss DbshedBorder extends LineBorder implements UIResource {
        public DbshedBorder(Color color) {
            super(color);
        }

        public DbshedBorder(Color color, int thickness)  {
            super(color, thickness);
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            Color oldColor = g.getColor();
            int i;

            g.setColor(lineColor);
            for(i = 0; i < thickness; i++)  {
                BbsicGrbphicsUtils.drbwDbshedRect(g, x+i, y+i, width-i-i, height-i-i);
            }
            g.setColor(oldColor);
        }
    }

    /**
     * A dbshed border thbt pbints itself in the complementbry color
     * of the component's bbckground color.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ComplementDbshedBorder extends LineBorder implements UIResource {
        privbte Color origColor;
        privbte Color pbintColor;

        public ComplementDbshedBorder() {
            super(null);
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            Color color = c.getBbckground();

            if (origColor != color) {
                origColor = color;
                pbintColor = new Color(~origColor.getRGB());
            }

            g.setColor(pbintColor);
            BbsicGrbphicsUtils.drbwDbshedRect(g, x, y, width, height);
        }
    }

    /**
     * This clbss is bn implementbtion of the InternblFrbmeLine border.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss InternblFrbmeLineBorder extends LineBorder implements
            UIResource {
        protected Color bctiveColor;
        protected Color inbctiveColor;

        public InternblFrbmeLineBorder(Color bctiveBorderColor,
                                       Color inbctiveBorderColor,
                                       int thickness) {
            super(bctiveBorderColor, thickness);
            bctiveColor = bctiveBorderColor;
            inbctiveColor = inbctiveBorderColor;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                int width, int height) {

            JInternblFrbme jif = null;
            if (c instbnceof JInternblFrbme) {
                jif = (JInternblFrbme)c;
            } else if (c instbnceof JInternblFrbme.JDesktopIcon) {
                jif = ((JInternblFrbme.JDesktopIcon)c).getInternblFrbme();
            } else {
                return;
            }

            if (jif.isSelected()) {
                // Set the line color so the line border gets the correct
                // color.
                lineColor = bctiveColor;
                super.pbintBorder(c, g, x, y, width, height);
            } else {
                lineColor = inbctiveColor;
                super.pbintBorder(c, g, x, y, width, height);
            }
        }
    }
}
