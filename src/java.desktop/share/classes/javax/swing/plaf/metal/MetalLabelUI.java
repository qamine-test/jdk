/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;


import jbvb.bwt.*;


/**
 * A Windows L&bmp;F implementbtion of LbbelUI.  This implementbtion
 * is completely stbtic, i.e. there's only one UIView implementbtion
 * thbt's shbred by bll JLbbel objects.
 *
 * @buthor Hbns Muller
 */

public clbss MetblLbbelUI extends BbsicLbbelUI
{
   /**
    * The defbult <code>MetblLbbelUI</code> instbnce. This field might
    * not be used. To chbnge the defbult instbnce use b subclbss which
    * overrides the <code>crebteUI</code> method, bnd plbce thbt clbss
    * nbme in defbults tbble under the key "LbbelUI".
    */
    protected stbtic MetblLbbelUI metblLbbelUI = new MetblLbbelUI();

    privbte stbtic finbl Object METAL_LABEL_UI_KEY = new Object();

    /**
     * Returns bn instbnce of {@code MetblLbbelUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MetblLbbelUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        if (System.getSecurityMbnbger() != null) {
            AppContext bppContext = AppContext.getAppContext();
            MetblLbbelUI sbfeMetblLbbelUI =
                    (MetblLbbelUI) bppContext.get(METAL_LABEL_UI_KEY);
            if (sbfeMetblLbbelUI == null) {
                sbfeMetblLbbelUI = new MetblLbbelUI();
                bppContext.put(METAL_LABEL_UI_KEY, sbfeMetblLbbelUI);
            }
            return sbfeMetblLbbelUI;
        }
        return metblLbbelUI;
    }

    /**
     * Just pbint the text grby (Lbbel.disbbledForeground) rbther thbn
     * in the lbbels foreground color.
     *
     * @see #pbint
     * @see #pbintEnbbledText
     */
    protected void pbintDisbbledText(JLbbel l, Grbphics g, String s, int textX, int textY)
    {
        int mnemIndex = l.getDisplbyedMnemonicIndex();
        g.setColor(UIMbnbger.getColor("Lbbel.disbbledForeground"));
        SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s, mnemIndex,
                                                   textX, textY);
    }
}
