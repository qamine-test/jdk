/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;


/**
 * Provides the Windows look bnd feel for b pbssword field.
 * The only difference from the stbndbrd text field is thbt
 * the view of the text is simply b string of the echo
 * chbrbcter bs specified in JPbsswordField, rbther thbn the
 * rebl text contbined in the field.
 *
 * @buthor  Timothy Prinzing
 */
public clbss BbsicPbsswordFieldUI extends BbsicTextFieldUI {

    /**
     * Crebtes b UI for b JPbsswordField.
     *
     * @pbrbm c the JPbsswordField
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicPbsswordFieldUI();
    }

    /**
     * Fetches the nbme used bs b key to look up properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme ("PbsswordField")
     */
    protected String getPropertyPrefix() {
        return "PbsswordField";
    }


    /**
     * Instblls the necessbry properties on the JPbsswordField.
     * @since 1.6
     */
    protected void instbllDefbults() {
        super.instbllDefbults();
        String prefix = getPropertyPrefix();
        Chbrbcter echoChbr = (Chbrbcter)UIMbnbger.getDefbults().get(prefix + ".echoChbr");
        if(echoChbr != null) {
            LookAndFeel.instbllProperty(getComponent(), "echoChbr", echoChbr);
        }
    }

    /**
     * Crebtes b view (PbsswordView) for bn element.
     *
     * @pbrbm elem the element
     * @return the view
     */
    public View crebte(Element elem) {
        return new PbsswordView(elem);
    }

    /**
     * Crebte the bction mbp for Pbssword Field.  This mbp provides
     * sbme bctions for double mouse click bnd
     * bnd for triple mouse click (see bug 4231444).
     */

    ActionMbp crebteActionMbp() {
        ActionMbp mbp = super.crebteActionMbp();
        if (mbp.get(DefbultEditorKit.selectWordAction) != null) {
            Action b = mbp.get(DefbultEditorKit.selectLineAction);
            if (b != null) {
                mbp.remove(DefbultEditorKit.selectWordAction);
                mbp.put(DefbultEditorKit.selectWordAction, b);
            }
        }
        return mbp;
    }

}
