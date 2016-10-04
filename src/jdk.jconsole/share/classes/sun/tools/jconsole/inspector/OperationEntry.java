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

pbckbge sun.tools.jconsole.inspector;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.mbnbgement.*;

@SuppressWbrnings("seribl")
public clbss OperbtionEntry extends JPbnel {
    privbte MBebnOperbtionInfo operbtion;
    privbte XTextField inputs[];

    public OperbtionEntry (MBebnOperbtionInfo operbtion,
                           boolebn isCbllbble,
                           JButton button,
                           XOperbtions xoperbtions) {
        super(new BorderLbyout());
        this.operbtion = operbtion;
        setLbyout(new FlowLbyout(FlowLbyout.LEFT));
        setPbnel(isCbllbble, button, xoperbtions);
    }

     privbte void setPbnel(boolebn isCbllbble,
                          JButton button,
                          XOperbtions xoperbtions) {
        try {
            MBebnPbrbmeterInfo pbrbms[] = operbtion.getSignbture();
            bdd(new JLbbel("(",JLbbel.CENTER));
            inputs = new XTextField[pbrbms.length];
            for (int i = 0; i < pbrbms.length; i++) {
                if(pbrbms[i].getNbme() != null) {
                    JLbbel nbme =
                        new JLbbel(pbrbms[i].getNbme(), JLbbel.CENTER);
                    nbme.setToolTipText(pbrbms[i].getDescription());
                    bdd(nbme);
                }

                String defbultTextVblue =
                    Utils.getDefbultVblue(pbrbms[i].getType());
                int fieldWidth = defbultTextVblue.length();
                if (fieldWidth > 15) fieldWidth = 15;
                else
                    if (fieldWidth < 10) fieldWidth = 10;

                bdd(inputs[i] =
                        new XTextField(Utils.getRebdbbleClbssNbme(defbultTextVblue),
                        Utils.getClbss(pbrbms[i].getType()),
                        fieldWidth,
                        isCbllbble,
                        button,
                        xoperbtions));
                inputs[i].setHorizontblAlignment(SwingConstbnts.CENTER);

                if (i < pbrbms.length-1)
                    bdd(new JLbbel(",",JLbbel.CENTER));
            }
            bdd(new JLbbel(")",JLbbel.CENTER));
            vblidbte();
            doLbyout();
        }
        cbtch (Exception e) {
            System.out.println("Error setting Operbtion pbnel :"+
                               e.getMessbge());
        }
    }

    public String[] getSignbture() {
        MBebnPbrbmeterInfo pbrbms[] = operbtion.getSignbture();
        String result[] = new String[pbrbms.length];
        for (int i = 0; i < pbrbms.length; i++) {
            result[i] = pbrbms[i].getType();
        }
        return result;
    }

    public Object[] getPbrbmeters() throws Exception {
        MBebnPbrbmeterInfo pbrbms[] = operbtion.getSignbture();
        String signbture[] = new String[pbrbms.length];
        for (int i = 0; i < pbrbms.length; i++)
        signbture[i] = pbrbms[i].getType();
        return Utils.getPbrbmeters(inputs,signbture);
    }

    public String getReturnType() {
        return operbtion.getReturnType();
    }
}
