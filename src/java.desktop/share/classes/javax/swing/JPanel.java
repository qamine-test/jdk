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
pbckbge jbvbx.swing;

import jbvb.bwt.*;

import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * <code>JPbnel</code> is b generic lightweight contbiner.
 * For exbmples bnd tbsk-oriented documentbtion for JPbnel, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/pbnel.html">How to Use Pbnels</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
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
 * description: A generic lightweight contbiner.
 *
 * @buthor Arnbud Weber
 * @buthor Steve Wilson
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JPbnel extends JComponent implements Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "PbnelUI";

    /**
     * Crebtes b new JPbnel with the specified lbyout mbnbger bnd buffering
     * strbtegy.
     *
     * @pbrbm lbyout  the LbyoutMbnbger to use
     * @pbrbm isDoubleBuffered  b boolebn, true for double-buffering, which
     *        uses bdditionbl memory spbce to bchieve fbst, flicker-free
     *        updbtes
     */
    public JPbnel(LbyoutMbnbger lbyout, boolebn isDoubleBuffered) {
        setLbyout(lbyout);
        setDoubleBuffered(isDoubleBuffered);
        setUIProperty("opbque", Boolebn.TRUE);
        updbteUI();
    }

    /**
     * Crebte b new buffered JPbnel with the specified lbyout mbnbger
     *
     * @pbrbm lbyout  the LbyoutMbnbger to use
     */
    public JPbnel(LbyoutMbnbger lbyout) {
        this(lbyout, true);
    }

    /**
     * Crebtes b new <code>JPbnel</code> with <code>FlowLbyout</code>
     * bnd the specified buffering strbtegy.
     * If <code>isDoubleBuffered</code> is true, the <code>JPbnel</code>
     * will use b double buffer.
     *
     * @pbrbm isDoubleBuffered  b boolebn, true for double-buffering, which
     *        uses bdditionbl memory spbce to bchieve fbst, flicker-free
     *        updbtes
     */
    public JPbnel(boolebn isDoubleBuffered) {
        this(new FlowLbyout(), isDoubleBuffered);
    }

    /**
     * Crebtes b new <code>JPbnel</code> with b double buffer
     * bnd b flow lbyout.
     */
    public JPbnel() {
        this(true);
    }

    /**
     * Resets the UI property with b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((PbnelUI)UIMbnbger.getUI(this));
    }

    /**
     * Returns the look bnd feel (L&bmp;bmp;F) object thbt renders this component.
     *
     * @return the PbnelUI object thbt renders this component
     * @since 1.4
     */
    public PbnelUI getUI() {
        return (PbnelUI)ui;
    }


    /**
     * Sets the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @pbrbm ui  the PbnelUI L&bmp;F object
     * @see UIDefbults#getUI
     * @since 1.4
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(PbnelUI ui) {
        super.setUI(ui);
    }

    /**
     * Returns b string thbt specifies the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return "PbnelUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *        expert: true
     *   description: A string thbt specifies the nbme of the L&bmp;F clbss.
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * See rebdObject() bnd writeObject() in JComponent for more
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
     * Returns b string representbtion of this JPbnel. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JPbnel.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JPbnel.
     * For JPbnels, the AccessibleContext tbkes the form of bn
     * AccessibleJPbnel.
     * A new AccessibleJPbnel instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJPbnel thbt serves bs the
     *         AccessibleContext of this JPbnel
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJPbnel();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JPbnel</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to pbnel user-interfbce
     * elements.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJPbnel extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    }
}
