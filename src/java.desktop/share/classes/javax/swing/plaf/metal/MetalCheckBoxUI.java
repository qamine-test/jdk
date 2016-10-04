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

import sun.bwt.AppContext;

import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.BbsicCheckBoxUI;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.plbf.*;
import jbvb.io.Seriblizbble;


/**
 * CheckboxUI implementbtion for MetblCheckboxUI
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
 * @buthor Michbel C. Albers
 *
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblCheckBoxUI extends MetblRbdioButtonUI {

    // NOTE: MetblCheckBoxUI inherts from MetblRbdioButtonUI instebd
    // of BbsicCheckBoxUI becbuse we wbnt to pick up bll the
    // pbinting chbnges mbde in MetblRbdioButtonUI.

    privbte stbtic finbl Object METAL_CHECK_BOX_UI_KEY = new Object();

    privbte finbl stbtic String propertyPrefix = "CheckBox" + ".";

    privbte boolebn defbults_initiblized = fblse;

    // ********************************
    //         Crebte PlAF
    // ********************************

    /**
     * Returns bn instbnce of {@code MetblCheckBoxUI}.
     *
     * @pbrbm b b component
     * @return b new instbnce of {@code MetblCheckBoxUI}
     */
    public stbtic ComponentUI crebteUI(JComponent b) {
        AppContext bppContext = AppContext.getAppContext();
        MetblCheckBoxUI checkboxUI =
                (MetblCheckBoxUI) bppContext.get(METAL_CHECK_BOX_UI_KEY);
        if (checkboxUI == null) {
            checkboxUI = new MetblCheckBoxUI();
            bppContext.put(METAL_CHECK_BOX_UI_KEY, checkboxUI);
        }
        return checkboxUI;
    }

    public String getPropertyPrefix() {
        return propertyPrefix;
    }

    // ********************************
    //          Defbults
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            icon = UIMbnbger.getIcon(getPropertyPrefix() + "icon");
            defbults_initiblized = true;
        }
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

}
