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
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Objects;


/**
 * Used to displby b "Tip" for b Component. Typicblly components provide bpi
 * to butombte the process of using <code>ToolTip</code>s.
 * For exbmple, bny Swing component cbn use the <code>JComponent</code>
 * <code>setToolTipText</code> method to specify the text
 * for b stbndbrd tooltip. A component thbt wbnts to crebte b custom
 * <code>ToolTip</code>
 * displby cbn override <code>JComponent</code>'s <code>crebteToolTip</code>
 * method bnd use b subclbss of this clbss.
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tooltip.html">How to Use Tool Tips</b>
 * in <em>The Jbvb Tutoribl</em>
 * for further documentbtion.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @see JComponent#setToolTipText
 * @see JComponent#crebteToolTip
 * @buthor Dbve Moore
 * @buthor Rich Shibvi
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JToolTip extends JComponent implements Accessible {
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ToolTipUI";

    String tipText;
    JComponent component;

    /** Crebtes b tool tip. */
    public JToolTip() {
        setOpbque(true);
        updbteUI();
    }

    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return the <code>ToolTipUI</code> object thbt renders this component
     */
    public ToolTipUI getUI() {
        return (ToolTipUI)ui;
    }

    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ToolTipUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "ToolTipUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Sets the text to show when the tool tip is displbyed.
     * The string <code>tipText</code> mby be <code>null</code>.
     *
     * @pbrbm tipText the <code>String</code> to displby
     * @bebninfo
     *    preferred: true
     *        bound: true
     *  description: Sets the text of the tooltip
     */
    public void setTipText(String tipText) {
        String oldVblue = this.tipText;
        this.tipText = tipText;
        firePropertyChbnge("tiptext", oldVblue, tipText);

        if (!Objects.equbls(oldVblue, tipText)) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the text thbt is shown when the tool tip is displbyed.
     * The returned vblue mby be <code>null</code>.
     *
     * @return the <code>String</code> thbt is displbyed
     */
    public String getTipText() {
        return tipText;
    }

    /**
     * Specifies the component thbt the tooltip describes.
     * The component <code>c</code> mby be <code>null</code>
     * bnd will hbve no effect.
     * <p>
     * This is b bound property.
     *
     * @pbrbm c the <code>JComponent</code> being described
     * @see JComponent#crebteToolTip
     * @bebninfo
     *       bound: true
     * description: Sets the component thbt the tooltip describes.
     */
    public void setComponent(JComponent c) {
        JComponent oldVblue = this.component;

        component = c;
        firePropertyChbnge("component", oldVblue, c);
    }

    /**
     * Returns the component the tooltip bpplies to.
     * The returned vblue mby be <code>null</code>.
     *
     * @return the component thbt the tooltip describes
     *
     * @see JComponent#crebteToolTip
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * Alwbys returns true since tooltips, by definition,
     * should blwbys be on top of bll other windows.
     */
    // pbckbge privbte
    boolebn blwbysOnTop() {
        return true;
    }


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code>
     * in <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this <code>JToolTip</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JToolTip</code>
     */
    protected String pbrbmString() {
        String tipTextString = (tipText != null ?
                                tipText : "");

        return super.pbrbmString() +
        ",tipText=" + tipTextString;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JToolTip.
     * For tool tips, the AccessibleContext tbkes the form of bn
     * AccessibleJToolTip.
     * A new AccessibleJToolTip instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJToolTip thbt serves bs the
     *         AccessibleContext of this JToolTip
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJToolTip();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JToolTip</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to tool tip user-interfbce elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJToolTip extends AccessibleJComponent {

        /**
         * Get the bccessible description of this object.
         *
         * @return b locblized String describing this object.
         */
        public String getAccessibleDescription() {
            String description = bccessibleDescription;

            // fbllbbck to client property
            if (description == null) {
                description = (String)getClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY);
            }
            if (description == null) {
                description = getTipText();
            }
            return description;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TOOL_TIP;
        }
    }
}
