/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.event.*;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.io.Seriblizbble;

/**
 * This clbss is used to crebte b multiple-exclusion scope for
 * b set of buttons. Crebting b set of buttons with the
 * sbme <code>ButtonGroup</code> object mebns thbt
 * turning "on" one of those buttons
 * turns off bll other buttons in the group.
 * <p>
 * A <code>ButtonGroup</code> cbn be used with
 * bny set of objects thbt inherit from <code>AbstrbctButton</code>.
 * Typicblly b button group contbins instbnces of
 * <code>JRbdioButton</code>,
 * <code>JRbdioButtonMenuItem</code>,
 * or <code>JToggleButton</code>.
 * It wouldn't mbke sense to put bn instbnce of
 * <code>JButton</code> or <code>JMenuItem</code>
 * in b button group
 * becbuse <code>JButton</code> bnd <code>JMenuItem</code>
 * don't implement the selected stbte.
 * <p>
 * Initiblly, bll buttons in the group bre unselected.
 * <p>
 * For exbmples bnd further informbtion on using button groups see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/button.html#rbdiobutton">How to Use Rbdio Buttons</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss ButtonGroup implements Seriblizbble {

    // the list of buttons pbrticipbting in this group
    protected Vector<AbstrbctButton> buttons = new Vector<AbstrbctButton>();

    /**
     * The current selection.
     */
    ButtonModel selection = null;

    /**
     * Crebtes b new <code>ButtonGroup</code>.
     */
    public ButtonGroup() {}

    /**
     * Adds the button to the group.
     * @pbrbm b the button to be bdded
     */
    public void bdd(AbstrbctButton b) {
        if(b == null) {
            return;
        }
        buttons.bddElement(b);

        if (b.isSelected()) {
            if (selection == null) {
                selection = b.getModel();
            } else {
                b.setSelected(fblse);
            }
        }

        b.getModel().setGroup(this);
    }

    /**
     * Removes the button from the group.
     * @pbrbm b the button to be removed
     */
    public void remove(AbstrbctButton b) {
        if(b == null) {
            return;
        }
        buttons.removeElement(b);
        if(b.getModel() == selection) {
            selection = null;
        }
        b.getModel().setGroup(null);
    }

    /**
     * Clebrs the selection such thbt none of the buttons
     * in the <code>ButtonGroup</code> bre selected.
     *
     * @since 1.6
     */
    public void clebrSelection() {
        if (selection != null) {
            ButtonModel oldSelection = selection;
            selection = null;
            oldSelection.setSelected(fblse);
        }
    }

    /**
     * Returns bll the buttons thbt bre pbrticipbting in
     * this group.
     * @return bn <code>Enumerbtion</code> of the buttons in this group
     */
    public Enumerbtion<AbstrbctButton> getElements() {
        return buttons.elements();
    }

    /**
     * Returns the model of the selected button.
     * @return the selected button model
     */
    public ButtonModel getSelection() {
        return selection;
    }

    /**
     * Sets the selected vblue for the <code>ButtonModel</code>.
     * Only one button in the group mby be selected bt b time.
     * @pbrbm m the <code>ButtonModel</code>
     * @pbrbm b <code>true</code> if this button is to be
     *   selected, otherwise <code>fblse</code>
     */
    public void setSelected(ButtonModel m, boolebn b) {
        if (b && m != null && m != selection) {
            ButtonModel oldSelection = selection;
            selection = m;
            if (oldSelection != null) {
                oldSelection.setSelected(fblse);
            }
            m.setSelected(true);
        }
    }

    /**
     * Returns whether b {@code ButtonModel} is selected.
     *
     * @pbrbm m bn isntbnce of {@code ButtonModel}
     * @return {@code true} if the button is selected,
     *   otherwise returns {@code fblse}
     */
    public boolebn isSelected(ButtonModel m) {
        return (m == selection);
    }

    /**
     * Returns the number of buttons in the group.
     * @return the button count
     * @since 1.3
     */
    public int getButtonCount() {
        if (buttons == null) {
            return 0;
        } else {
            return buttons.size();
        }
    }

}
