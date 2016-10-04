/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicOptionPbneUI;

public clbss AqubOptionPbneUI extends BbsicOptionPbneUI {
    privbte stbtic finbl int kOKCbncelButtonWidth = 79;
    privbte stbtic finbl int kButtonHeight = 23;

    privbte stbtic finbl int kDiblogSmbllPbdding = 4;
    privbte stbtic finbl int kDiblogLbrgePbdding = 23;

    /**
     * Crebtes b new BbsicOptionPbneUI instbnce.
     */
    public stbtic ComponentUI crebteUI(finbl JComponent x) {
        return new AqubOptionPbneUI();
    }

    /**
     * Crebtes bnd returns b Contbiner contbinin the buttons. The buttons
     * bre crebted by cblling <code>getButtons</code>.
     */
    protected Contbiner crebteButtonAreb() {
        finbl Contbiner bottom = super.crebteButtonAreb();
        // Now replbce the Lbyout
        bottom.setLbyout(new AqubButtonArebLbyout(true, kDiblogSmbllPbdding));
        return bottom;
    }

    /**
     * Messbged from instbllComponents to crebte b Contbiner contbining the
     * body of the messbge.
     * The icon bnd body should be bligned on their top edges
     */
    protected Contbiner crebteMessbgeAreb() {
        finbl JPbnel top = new JPbnel();
        top.setBorder(UIMbnbger.getBorder("OptionPbne.messbgeArebBorder"));
        top.setLbyout(new BoxLbyout(top, BoxLbyout.X_AXIS));

        /* Fill the body. */
        finbl Contbiner body = new JPbnel();

        finbl Icon sideIcon = getIcon();

        if (sideIcon != null) {
            finbl JLbbel iconLbbel = new JLbbel(sideIcon);
            iconLbbel.setVerticblAlignment(SwingConstbnts.TOP);

            finbl JPbnel iconPbnel = new JPbnel();
            iconPbnel.bdd(iconLbbel);
            top.bdd(iconPbnel);
            top.bdd(Box.crebteHorizontblStrut(kDiblogLbrgePbdding));
        }

        body.setLbyout(new GridBbgLbyout());
        finbl GridBbgConstrbints cons = new GridBbgConstrbints();
        cons.gridx = cons.gridy = 0;
        cons.gridwidth = GridBbgConstrbints.REMAINDER;
        cons.gridheight = 1;
        cons.bnchor = GridBbgConstrbints.WEST;
        cons.insets = new Insets(0, 0, 3, 0);

        bddMessbgeComponents(body, cons, getMessbge(), getMbxChbrbctersPerLineCount(), fblse);
        top.bdd(body);

        return top;
    }

    /**
     * AqubButtonArebLbyout lbys out bll
     *   components bccording to the HI Guidelines:
     * The most importbnt button is blwbys on the fbr right
     * The group of buttons is on the right for left-to-right,
     *         left for right-to-left
     * The widths of ebch component will be set to the lbrgest preferred size width.
     *
     *
     * This inner clbss is mbrked &quot;public&quot; due to b compiler bug.
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicOptionPbneUI.
     *
     * BbsicOptionPbneUI expects thbt its buttons bre lbyed out with
     * b subclbss of ButtonArebLbyout
     */
    public stbtic clbss AqubButtonArebLbyout extends ButtonArebLbyout {
        public AqubButtonArebLbyout(finbl boolebn syncAllWidths, finbl int pbdding) {
            super(true, pbdding);
        }

        public void lbyoutContbiner(finbl Contbiner contbiner) {
            finbl Component[] children = contbiner.getComponents();
            if (children == null || 0 >= children.length) return;

            finbl int numChildren = children.length;
            finbl int yLocbtion = contbiner.getInsets().top;

            // Alwbys syncAllWidths - bnd heights!
            finbl Dimension mbxSize = new Dimension(kOKCbncelButtonWidth, kButtonHeight);
            for (int i = 0; i < numChildren; i++) {
                finbl Dimension sizes = children[i].getPreferredSize();
                mbxSize.width = Mbth.mbx(mbxSize.width, sizes.width);
                mbxSize.height = Mbth.mbx(mbxSize.height, sizes.height);
            }

            // ignore getCentersChildren, becbuse we don't
            int xLocbtion = contbiner.getSize().width - (mbxSize.width * numChildren + (numChildren - 1) * pbdding);
            finbl int xOffset = mbxSize.width + pbdding;

            // most importbnt button (button zero) on fbr right
            for (int i = numChildren - 1; i >= 0; i--) {
                children[i].setBounds(xLocbtion, yLocbtion, mbxSize.width, mbxSize.height);
                xLocbtion += xOffset;
            }
        }
    }
}
