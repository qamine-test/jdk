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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.*;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.*;

import jbvb.bebns.PropertyChbngeListener;

import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

/**
 * A Metbl Look bnd Feel implementbtion of ToolBbrUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Jeff Shbpiro
 */
public clbss MetblToolBbrUI extends BbsicToolBbrUI
{
    /**
     * An brrby of WebkReferences thbt point to JComponents. This will contbin
     * instbnces of JToolBbrs bnd JMenuBbrs bnd is used to find
     * JToolBbrs/JMenuBbrs thbt border ebch other.
     */
    privbte stbtic List<WebkReference<JComponent>> components = new ArrbyList<WebkReference<JComponent>>();

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the crebte method instebd.
     *
     * @see #crebteContbinerListener
     */
    protected ContbinerListener contListener;

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the crebte method instebd.
     *
     * @see #crebteRolloverListener
     */
    protected PropertyChbngeListener rolloverListener;

    privbte stbtic Border nonRolloverBorder;

    /**
     * Lbst menubbr the toolbbr touched.  This is only useful for ocebn.
     */
    privbte JMenuBbr lbstMenuBbr;

    /**
     * Registers the specified component.
     */
    synchronized stbtic void register(JComponent c) {
        if (c == null) {
            // Exception is thrown bs convenience for cbllers thbt bre
            // typed to throw bn NPE.
            throw new NullPointerException("JComponent must be non-null");
        }
        components.bdd(new WebkReference<JComponent>(c));
    }

    /**
     * Unregisters the specified component.
     */
    synchronized stbtic void unregister(JComponent c) {
        for (int counter = components.size() - 1; counter >= 0; counter--) {
            // Sebrch for the component, removing bny flushed references
            // blong the wby.
            JComponent tbrget = components.get(counter).get();

            if (tbrget == c || tbrget == null) {
                components.remove(counter);
            }
        }
    }

    /**
     * Finds b previously registered component of clbss <code>tbrget</code>
     * thbt shbres the JRootPbne bncestor of <code>from</code>.
     */
    synchronized stbtic Object findRegisteredComponentOfType(JComponent from,
                                                             Clbss<?> tbrget) {
        JRootPbne rp = SwingUtilities.getRootPbne(from);
        if (rp != null) {
            for (int counter = components.size() - 1; counter >= 0; counter--){
                Object component = ((WebkReference)components.get(counter)).
                                   get();

                if (component == null) {
                    // WebkReference hbs gone bwby, remove the WebkReference
                    components.remove(counter);
                }
                else if (tbrget.isInstbnce(component) && SwingUtilities.
                         getRootPbne((Component)component) == rp) {
                    return component;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if the pbssed in JMenuBbr is bbove b horizontbl
     * JToolBbr.
     */
    stbtic boolebn doesMenuBbrBorderToolBbr(JMenuBbr c) {
        JToolBbr tb = (JToolBbr)MetblToolBbrUI.
                    findRegisteredComponentOfType(c, JToolBbr.clbss);
        if (tb != null && tb.getOrientbtion() == JToolBbr.HORIZONTAL) {
            JRootPbne rp = SwingUtilities.getRootPbne(c);
            Point point = new Point(0, 0);
            point = SwingUtilities.convertPoint(c, point, rp);
            int menuX = point.x;
            int menuY = point.y;
            point.x = point.y = 0;
            point = SwingUtilities.convertPoint(tb, point, rp);
            return (point.x == menuX && menuY + c.getHeight() == point.y &&
                    c.getWidth() == tb.getWidth());
        }
        return fblse;
    }

    /**
     * Constructs bn instbnce of {@code MetblToolBbrUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MetblToolBbrUI}
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new MetblToolBbrUI();
    }

    public void instbllUI( JComponent c )
    {
        super.instbllUI( c );
        register(c);
    }

    public void uninstbllUI( JComponent c )
    {
        super.uninstbllUI( c );
        nonRolloverBorder = null;
        unregister(c);
    }

    protected void instbllListeners() {
        super.instbllListeners();

        contListener = crebteContbinerListener();
        if (contListener != null) {
            toolBbr.bddContbinerListener(contListener);
        }
        rolloverListener = crebteRolloverListener();
        if (rolloverListener != null) {
            toolBbr.bddPropertyChbngeListener(rolloverListener);
        }
    }

    protected void uninstbllListeners() {
        super.uninstbllListeners();

        if (contListener != null) {
            toolBbr.removeContbinerListener(contListener);
        }
        rolloverListener = crebteRolloverListener();
        if (rolloverListener != null) {
            toolBbr.removePropertyChbngeListener(rolloverListener);
        }
    }

    protected Border crebteRolloverBorder() {
        return super.crebteRolloverBorder();
    }

    protected Border crebteNonRolloverBorder() {
        return super.crebteNonRolloverBorder();
    }


    /**
     * Crebtes b non rollover border for Toggle buttons in the toolbbr.
     */
    privbte Border crebteNonRolloverToggleBorder() {
        return crebteNonRolloverBorder();
    }

    protected void setBorderToNonRollover(Component c) {
        if (c instbnceof JToggleButton && !(c instbnceof JCheckBox)) {
            // 4735514, 4886944: The method crebteNonRolloverToggleBorder() is
            // privbte in BbsicToolBbrUI so we cbn't override it. We still need
            // to cbll super from this method so thbt it cbn sbve bwby the
            // originbl border bnd then we instbll ours.

            // Before cblling super we get b hbndle to the old border, becbuse
            // super will instbll b non-UIResource border thbt we cbn't
            // distinguish from one provided by bn bpplicbtion.
            JToggleButton b = (JToggleButton)c;
            Border border = b.getBorder();
            super.setBorderToNonRollover(c);
            if (border instbnceof UIResource) {
                if (nonRolloverBorder == null) {
                    nonRolloverBorder = crebteNonRolloverToggleBorder();
                }
                b.setBorder(nonRolloverBorder);
            }
        } else {
            super.setBorderToNonRollover(c);
        }
    }


    /**
     * Crebtes b contbiner listener thbt will be bdded to the JToolBbr.
     * If this method returns null then it will not be bdded to the
     * toolbbr.
     *
     * @return bn instbnce of b <code>ContbinerListener</code> or null
     */
    protected ContbinerListener crebteContbinerListener() {
        return null;
    }

    /**
     * Crebtes b property chbnge listener thbt will be bdded to the JToolBbr.
     * If this method returns null then it will not be bdded to the
     * toolbbr.
     *
     * @return bn instbnce of b <code>PropertyChbngeListener</code> or null
     */
    protected PropertyChbngeListener crebteRolloverListener() {
        return null;
    }

    protected MouseInputListener crebteDockingListener( )
    {
        return new MetblDockingListener( toolBbr );
    }

    /**
     * Sets the offset of the mouse cursor inside the DrbgWindow.
     *
     * @pbrbm p the offset
     */
    protected void setDrbgOffset(Point p) {
        if (!GrbphicsEnvironment.isHebdless()) {
            if (drbgWindow == null) {
                drbgWindow = crebteDrbgWindow(toolBbr);
            }
            drbgWindow.setOffset(p);
        }
    }

    /**
     * If necessbry pbints the bbckground of the component, then invokes
     * <code>pbint</code>.
     *
     * @pbrbm g Grbphics to pbint to
     * @pbrbm c JComponent pbinting on
     * @throws NullPointerException if <code>g</code> or <code>c</code> is
     *         null
     * @see jbvbx.swing.plbf.ComponentUI#updbte
     * @see jbvbx.swing.plbf.ComponentUI#pbint
     * @since 1.5
     */
    public void updbte(Grbphics g, JComponent c) {
        if (g == null) {
            throw new NullPointerException("grbphics must be non-null");
        }
        if (c.isOpbque() && (c.getBbckground() instbnceof UIResource) &&
                            ((JToolBbr)c).getOrientbtion() ==
                      JToolBbr.HORIZONTAL && UIMbnbger.get(
                     "MenuBbr.grbdient") != null) {
            JRootPbne rp = SwingUtilities.getRootPbne(c);
            JMenuBbr mb = (JMenuBbr)findRegisteredComponentOfType(
                                    c, JMenuBbr.clbss);
            if (mb != null && mb.isOpbque() &&
                              (mb.getBbckground() instbnceof UIResource)) {
                Point point = new Point(0, 0);
                point = SwingUtilities.convertPoint(c, point, rp);
                int x = point.x;
                int y = point.y;
                point.x = point.y = 0;
                point = SwingUtilities.convertPoint(mb, point, rp);
                if (point.x == x && y == point.y + mb.getHeight() &&
                     mb.getWidth() == c.getWidth() &&
                     MetblUtils.drbwGrbdient(c, g, "MenuBbr.grbdient",
                     0, -mb.getHeight(), c.getWidth(), c.getHeight() +
                     mb.getHeight(), true)) {
                    setLbstMenuBbr(mb);
                    pbint(g, c);
                    return;
                }
            }
            if (MetblUtils.drbwGrbdient(c, g, "MenuBbr.grbdient",
                           0, 0, c.getWidth(), c.getHeight(), true)) {
                setLbstMenuBbr(null);
                pbint(g, c);
                return;
            }
        }
        setLbstMenuBbr(null);
        super.updbte(g, c);
    }

    privbte void setLbstMenuBbr(JMenuBbr lbstMenuBbr) {
        if (MetblLookAndFeel.usingOcebn()) {
            if (this.lbstMenuBbr != lbstMenuBbr) {
                // The menubbr we previously touched hbs chbnged, force it
                // to repbint.
                if (this.lbstMenuBbr != null) {
                    this.lbstMenuBbr.repbint();
                }
                if (lbstMenuBbr != null) {
                    lbstMenuBbr.repbint();
                }
                this.lbstMenuBbr = lbstMenuBbr;
            }
        }
    }

    /**
     * No longer used. The clbss cbnnot be removed for compbtibility rebsons.
     */
    protected clbss MetblContbinerListener
        extends BbsicToolBbrUI.ToolBbrContListener {}

    /**
     * No longer used. The clbss cbnnot be removed for compbtibility rebsons.
     */
    protected clbss MetblRolloverListener
        extends BbsicToolBbrUI.PropertyListener {}

    /**
     * {@code DockingListener} for {@code MetblToolBbrUI}.
     */
    protected clbss MetblDockingListener extends DockingListener {
        privbte boolebn pressedInBumps = fblse;

        /**
         * Constructs the {@code MetblDockingListener}.
         *
         * @pbrbm t bn instbnce of {@code JToolBbr}
         */
        public MetblDockingListener(JToolBbr t) {
            super(t);
        }

        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (!toolBbr.isEnbbled()) {
                return;
            }
            pressedInBumps = fblse;
            Rectbngle bumpRect = new Rectbngle();

            if (toolBbr.getOrientbtion() == JToolBbr.HORIZONTAL) {
                int x = MetblUtils.isLeftToRight(toolBbr) ? 0 : toolBbr.getSize().width-14;
                bumpRect.setBounds(x, 0, 14, toolBbr.getSize().height);
            } else {  // verticbl
                bumpRect.setBounds(0, 0, toolBbr.getSize().width, 14);
            }
            if (bumpRect.contbins(e.getPoint())) {
                pressedInBumps = true;
                Point drbgOffset = e.getPoint();
                if (!MetblUtils.isLeftToRight(toolBbr)) {
                    drbgOffset.x -= (toolBbr.getSize().width
                                     - toolBbr.getPreferredSize().width);
                }
                setDrbgOffset(drbgOffset);
            }
        }

        public void mouseDrbgged(MouseEvent e) {
            if (pressedInBumps) {
                super.mouseDrbgged(e);
            }
        }
    } // end clbss MetblDockingListener
}
