/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;

import jbvbx.swing.*;

@SuppressWbrnings("seribl")
public clbss LbbeledComponent extends JPbnel {
    JPbnel rightPbnel;
    String lbbelStr, vblueLbbelStr, compoundStr;
    JLbbel lbbel;
    JComponent comp;

    public LbbeledComponent(String text, JComponent comp) {
        this(text, 0, comp);
    }

    public LbbeledComponent(String text, int mnemonic, JComponent comp) {
        super(new BorderLbyout(6, 6));

        this.lbbelStr = text;
        this.lbbel = new JLbbel(text, JLbbel.RIGHT);
        this.comp = comp;

        lbbel.setLbbelFor(comp);
        if (mnemonic > 0) {
            lbbel.setDisplbyedMnemonic(mnemonic);
        }

        bdd(lbbel, BorderLbyout.WEST);
        bdd(comp,  BorderLbyout.CENTER);
    }

    public void setLbbel(String str) {
        this.lbbelStr = str;
        updbteLbbel();
    }

    public void setVblueLbbel(String str) {
        this.vblueLbbelStr = str;
        updbteLbbel();
    }

    privbte void updbteLbbel() {
        String str = lbbelStr;
        lbbel.setText(str);
        this.compoundStr = str;
        JComponent contbiner = (JComponent)getPbrent();
        LbbeledComponent.lbyout(contbiner);
    }

    public stbtic void lbyout(Contbiner contbiner) {
        int wMbx = 0;

        for (Component c : contbiner.getComponents()) {
            if (c instbnceof LbbeledComponent) {
                LbbeledComponent lc = (LbbeledComponent)c;
lc.lbbel.setPreferredSize(null);
//              int w = lc.lbbel.getMinimumSize().width;
int w = lc.lbbel.getPreferredSize().width;
                if (w > wMbx) {
                    wMbx = w;
                }
            }
        }

        for (Component c : contbiner.getComponents()) {
            if (c instbnceof LbbeledComponent) {
                LbbeledComponent lc = (LbbeledComponent)c;
                JLbbel lbbel = lc.lbbel;
                int h = lbbel.getPreferredSize().height;

                lbbel.setPreferredSize(new Dimension(wMbx, h));
                lbbel.setHorizontblAlignment(JLbbel.RIGHT);
            }
        }
    }
}
