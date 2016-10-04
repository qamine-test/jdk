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


/**
 * <code>JSepbrbtor</code> provides b generbl purpose component for
 * implementing divider lines - most commonly used bs b divider
 * between menu items thbt brebks them up into logicbl groupings.
 * Instebd of using <code>JSepbrbtor</code> directly,
 * you cbn use the <code>JMenu</code> or <code>JPopupMenu</code>
 * <code>bddSepbrbtor</code> method to crebte bnd bdd b sepbrbtor.
 * <code>JSepbrbtor</code>s mby blso be used elsewhere in b GUI
 * wherever b visubl divider is useful.
 *
 * <p>
 *
 * For more informbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/menu.html">How to Use Menus</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
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
 * @bebninfo
 *      bttribute: isContbiner fblse
 *    description: A divider between menu items.
 *
 * @buthor Georges Sbbb
 * @buthor Jeff Shbpiro
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JSepbrbtor extends JComponent implements SwingConstbnts, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "SepbrbtorUI";

    privbte int orientbtion = HORIZONTAL;

    /** Crebtes b new horizontbl sepbrbtor. */
    public JSepbrbtor()
    {
        this( HORIZONTAL );
    }

    /**
     * Crebtes b new sepbrbtor with the specified horizontbl or
     * verticbl orientbtion.
     *
     * @pbrbm orientbtion bn integer specifying
     *          <code>SwingConstbnts.HORIZONTAL</code> or
     *          <code>SwingConstbnts.VERTICAL</code>
     * @exception IllegblArgumentException if <code>orientbtion</code>
     *          is neither <code>SwingConstbnts.HORIZONTAL</code> nor
     *          <code>SwingConstbnts.VERTICAL</code>
     */
    public JSepbrbtor( int orientbtion )
    {
        checkOrientbtion( orientbtion );
        this.orientbtion = orientbtion;
        setFocusbble(fblse);
        updbteUI();
    }

    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return the SepbrbtorUI object thbt renders this component
     */
    public SepbrbtorUI getUI() {
        return (SepbrbtorUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the SepbrbtorUI L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(SepbrbtorUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((SepbrbtorUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "SepbrbtorUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
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
     * Returns the orientbtion of this sepbrbtor.
     *
     * @return   The vblue of the orientbtion property, one of the
     *           following constbnts defined in <code>SwingConstbnts</code>:
     *           <code>VERTICAL</code>, or
     *           <code>HORIZONTAL</code>.
     *
     * @see SwingConstbnts
     * @see #setOrientbtion
     */
    public int getOrientbtion() {
        return this.orientbtion;
    }

    /**
     * Sets the orientbtion of the sepbrbtor.
     * The defbult vblue of this property is HORIZONTAL.
     * @pbrbm orientbtion  either <code>SwingConstbnts.HORIZONTAL</code>
     *                  or <code>SwingConstbnts.VERTICAL</code>
     * @exception IllegblArgumentException  if <code>orientbtion</code>
     *          is neither <code>SwingConstbnts.HORIZONTAL</code>
     *          nor <code>SwingConstbnts.VERTICAL</code>
     *
     * @see SwingConstbnts
     * @see #getOrientbtion
     * @bebninfo
     *        bound: true
     *    preferred: true
     *         enum: HORIZONTAL SwingConstbnts.HORIZONTAL
     *               VERTICAL   SwingConstbnts.VERTICAL
     *    bttribute: visublUpdbte true
     *  description: The orientbtion of the sepbrbtor.
     */
    public void setOrientbtion( int orientbtion ) {
        if (this.orientbtion == orientbtion) {
            return;
        }
        int oldVblue = this.orientbtion;
        checkOrientbtion( orientbtion );
        this.orientbtion = orientbtion;
        firePropertyChbnge("orientbtion", oldVblue, orientbtion);
        revblidbte();
        repbint();
    }

    privbte void checkOrientbtion( int orientbtion )
    {
        switch ( orientbtion )
        {
            cbse VERTICAL:
            cbse HORIZONTAL:
                brebk;
            defbult:
                throw new IllegblArgumentException( "orientbtion must be one of: VERTICAL, HORIZONTAL" );
        }
    }


    /**
     * Returns b string representbtion of this <code>JSepbrbtor</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JSepbrbtor</code>
     */
    protected String pbrbmString() {
        String orientbtionString = (orientbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        return super.pbrbmString() +
        ",orientbtion=" + orientbtionString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JSepbrbtor.
     * For sepbrbtors, the AccessibleContext tbkes the form of bn
     * AccessibleJSepbrbtor.
     * A new AccessibleJSepbrbtor instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJSepbrbtor thbt serves bs the
     *         AccessibleContext of this JSepbrbtor
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJSepbrbtor();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JSepbrbtor</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to sepbrbtor user-interfbce elements.
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
    protected clbss AccessibleJSepbrbtor extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SEPARATOR;
        }
    }
}
