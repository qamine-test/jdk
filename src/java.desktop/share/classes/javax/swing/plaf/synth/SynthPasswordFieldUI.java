/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.Grbphics;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.ComponentUI;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JPbsswordField}.
 *
 * @buthor  Shbnnon Hickey
 * @since 1.7
 */
public clbss SynthPbsswordFieldUI extends SynthTextFieldUI {

    /**
     * Crebtes b UI for b JPbsswordField.
     *
     * @pbrbm c the JPbsswordField
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthPbsswordFieldUI();
    }

    /**
     * Fetches the nbme used bs b key to look up properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme ("PbsswordField")
     */
    @Override
    protected String getPropertyPrefix() {
        return "PbsswordField";
    }

    /**
     * Crebtes b view (PbsswordView) for bn element.
     *
     * @pbrbm elem the element
     * @return the view
     */
    @Override
    public View crebte(Element elem) {
        return new PbsswordView(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void pbintBbckground(SynthContext context, Grbphics g, JComponent c) {
        context.getPbinter().pbintPbsswordFieldBbckground(context, g, 0, 0,
                                                c.getWidth(), c.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintPbsswordFieldBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        ActionMbp mbp = SwingUtilities.getUIActionMbp(getComponent());
        if (mbp != null && mbp.get(DefbultEditorKit.selectWordAction) != null) {
            Action b = mbp.get(DefbultEditorKit.selectLineAction);
            if (b != null) {
                mbp.put(DefbultEditorKit.selectWordAction, b);
            }
        }
    }
}
