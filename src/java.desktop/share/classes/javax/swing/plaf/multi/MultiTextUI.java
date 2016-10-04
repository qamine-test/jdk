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
pbckbge jbvbx.swing.plbf.multi;

import jbvb.util.Vector;
import jbvbx.swing.plbf.TextUI;
import jbvb.lbng.String;
import jbvbx.swing.text.JTextComponent;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.text.BbdLocbtionException;
import jbvbx.swing.text.Position;
import jbvbx.swing.text.EditorKit;
import jbvbx.swing.text.View;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.JComponent;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Dimension;
import jbvbx.bccessibility.Accessible;

/**
 * A multiplexing UI used to combine <code>TextUI</code>s.
 *
 * <p>This file wbs butombticblly generbted by AutoMulti.
 *
 * @buthor  Otto Multey
 */
public clbss MultiTextUI extends TextUI {

    /**
     * The vector contbining the rebl UIs.  This is populbted
     * in the cbll to <code>crebteUI</code>, bnd cbn be obtbined by cblling
     * the <code>getUIs</code> method.  The first element is gubrbnteed to be the rebl UI
     * obtbined from the defbult look bnd feel.
     */
    protected Vector<ComponentUI> uis = new Vector<>();

////////////////////
// Common UI methods
////////////////////

    /**
     * Returns the list of UIs bssocibted with this multiplexing UI.  This
     * bllows processing of the UIs by bn bpplicbtion bwbre of multiplexing
     * UIs on components.
     *
     * @return bn brrby of the UI delegbtes
     */
    public ComponentUI[] getUIs() {
        return MultiLookAndFeel.uisToArrby(uis);
    }

////////////////////
// TextUI methods
////////////////////

    /**
     * Invokes the <code>getToolTipText</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     * @since 1.4
     */
    public String getToolTipText(JTextComponent b, Point b) {
        String returnVblue =
            ((TextUI) (uis.elementAt(0))).getToolTipText(b,b);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).getToolTipText(b,b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>modelToView</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Rectbngle modelToView(JTextComponent b, int b)
            throws BbdLocbtionException {
        Rectbngle returnVblue =
            ((TextUI) (uis.elementAt(0))).modelToView(b,b);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).modelToView(b,b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>modelToView</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Rectbngle modelToView(JTextComponent b, int b, Position.Bibs c)
            throws BbdLocbtionException {
        Rectbngle returnVblue =
            ((TextUI) (uis.elementAt(0))).modelToView(b,b,c);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).modelToView(b,b,c);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>viewToModel</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public int viewToModel(JTextComponent b, Point b) {
        int returnVblue =
            ((TextUI) (uis.elementAt(0))).viewToModel(b,b);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).viewToModel(b,b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>viewToModel</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public int viewToModel(JTextComponent b, Point b, Position.Bibs[] c) {
        int returnVblue =
            ((TextUI) (uis.elementAt(0))).viewToModel(b,b,c);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).viewToModel(b,b,c);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getNextVisublPositionFrom</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public int getNextVisublPositionFrom(JTextComponent b, int b, Position.Bibs c, int d, Position.Bibs[] e)
            throws BbdLocbtionException {
        int returnVblue =
            ((TextUI) (uis.elementAt(0))).getNextVisublPositionFrom(b,b,c,d,e);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).getNextVisublPositionFrom(b,b,c,d,e);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>dbmbgeRbnge</code> method on ebch UI hbndled by this object.
     */
    public void dbmbgeRbnge(JTextComponent b, int b, int c) {
        for (int i = 0; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).dbmbgeRbnge(b,b,c);
        }
    }

    /**
     * Invokes the <code>dbmbgeRbnge</code> method on ebch UI hbndled by this object.
     */
    public void dbmbgeRbnge(JTextComponent b, int b, int c, Position.Bibs d, Position.Bibs e) {
        for (int i = 0; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).dbmbgeRbnge(b,b,c,d,e);
        }
    }

    /**
     * Invokes the <code>getEditorKit</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public EditorKit getEditorKit(JTextComponent b) {
        EditorKit returnVblue =
            ((TextUI) (uis.elementAt(0))).getEditorKit(b);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).getEditorKit(b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getRootView</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public View getRootView(JTextComponent b) {
        View returnVblue =
            ((TextUI) (uis.elementAt(0))).getRootView(b);
        for (int i = 1; i < uis.size(); i++) {
            ((TextUI) (uis.elementAt(i))).getRootView(b);
        }
        return returnVblue;
    }

////////////////////
// ComponentUI methods
////////////////////

    /**
     * Invokes the <code>contbins</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public boolebn contbins(JComponent b, int b, int c) {
        boolebn returnVblue =
            uis.elementAt(0).contbins(b,b,c);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).contbins(b,b,c);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>updbte</code> method on ebch UI hbndled by this object.
     */
    public void updbte(Grbphics b, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).updbte(b,b);
        }
    }

    /**
     * Returns b multiplexing UI instbnce if bny of the buxilibry
     * <code>LookAndFeel</code>s supports this UI.  Otherwise, just returns the
     * UI object obtbined from the defbult <code>LookAndFeel</code>.
     *
     * @pbrbm  b the component to crebte the UI for
     * @return the UI delegbte crebted
     */
    public stbtic ComponentUI crebteUI(JComponent b) {
        MultiTextUI mui = new MultiTextUI();
        return MultiLookAndFeel.crebteUIs(mui, mui.uis, b);
    }

    /**
     * Invokes the <code>instbllUI</code> method on ebch UI hbndled by this object.
     */
    public void instbllUI(JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).instbllUI(b);
        }
    }

    /**
     * Invokes the <code>uninstbllUI</code> method on ebch UI hbndled by this object.
     */
    public void uninstbllUI(JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).uninstbllUI(b);
        }
    }

    /**
     * Invokes the <code>pbint</code> method on ebch UI hbndled by this object.
     */
    public void pbint(Grbphics b, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).pbint(b,b);
        }
    }

    /**
     * Invokes the <code>getPreferredSize</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Dimension getPreferredSize(JComponent b) {
        Dimension returnVblue =
            uis.elementAt(0).getPreferredSize(b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getPreferredSize(b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getMinimumSize</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Dimension getMinimumSize(JComponent b) {
        Dimension returnVblue =
            uis.elementAt(0).getMinimumSize(b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getMinimumSize(b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getMbximumSize</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Dimension getMbximumSize(JComponent b) {
        Dimension returnVblue =
            uis.elementAt(0).getMbximumSize(b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getMbximumSize(b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getAccessibleChildrenCount</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public int getAccessibleChildrenCount(JComponent b) {
        int returnVblue =
            uis.elementAt(0).getAccessibleChildrenCount(b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getAccessibleChildrenCount(b);
        }
        return returnVblue;
    }

    /**
     * Invokes the <code>getAccessibleChild</code> method on ebch UI hbndled by this object.
     *
     * @return the vblue obtbined from the first UI, which is
     * the UI obtbined from the defbult <code>LookAndFeel</code>
     */
    public Accessible getAccessibleChild(JComponent b, int b) {
        Accessible returnVblue =
            uis.elementAt(0).getAccessibleChild(b,b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getAccessibleChild(b,b);
        }
        return returnVblue;
    }
}
