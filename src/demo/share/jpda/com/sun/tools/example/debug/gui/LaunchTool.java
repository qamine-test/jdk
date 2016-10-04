/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Contbiner;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.TitledBorder;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;

import com.sun.tools.exbmple.debug.bdi.*;

clbss LbunchTool {

    privbte finbl ExecutionMbnbger runtime;

    privbte bbstrbct clbss ArgRep {
        finbl Connector.Argument brg;
        finbl JPbnel pbnel;

        ArgRep(Connector.Argument brg) {
            this.brg = brg;
            pbnel = new JPbnel();
            Border etched = BorderFbctory.crebteEtchedBorder();
            Border titled = BorderFbctory.crebteTitledBorder(etched,
                                      brg.description(),
                                      TitledBorder.LEFT, TitledBorder.TOP);
            pbnel.setBorder(titled);
        }

        bbstrbct String getText();

        boolebn isVblid() {
            return brg.isVblid(getText());
        }

        boolebn isSpecified() {
            String vblue = getText();
            return (vblue != null && vblue.length() > 0) ||
                !brg.mustSpecify();
        }

        void instbll() {
            brg.setVblue(getText());
        }
    }

    privbte clbss StringArgRep extends ArgRep {
        finbl JTextField textField;

        StringArgRep(Connector.Argument brg, JPbnel comp) {
            super(brg);
            textField = new JTextField(brg.vblue(), 50 );
            textField.setBorder(BorderFbctory.crebteLoweredBevelBorder());

            pbnel.bdd(new JLbbel(brg.lbbel(), SwingConstbnts.RIGHT));
            pbnel.bdd(textField); // , BorderLbyout.CENTER);
            comp.bdd(pbnel);
        }

        @Override
        String getText() {
            return textField.getText();
        }
    }

    privbte clbss BoolebnArgRep extends ArgRep {
        finbl JCheckBox check;

        BoolebnArgRep(Connector.BoolebnArgument bbrg, JPbnel comp) {
            super(bbrg);
            check = new JCheckBox(bbrg.lbbel());
            check.setSelected(bbrg.boolebnVblue());
            pbnel.bdd(check);
            comp.bdd(pbnel);
        }

        @Override
        String getText() {
            return ((Connector.BoolebnArgument)brg)
                           .stringVblueOf(check.getModel().isSelected());
        }
    }


    privbte LbunchTool(ExecutionMbnbger runtime) {
        this.runtime = runtime;
    }

    privbte Connector selectConnector() {
        finbl JDiblog diblog = new JDiblog();
        Contbiner content = diblog.getContentPbne();
        finbl JPbnel rbdioPbnel = new JPbnel();
        finbl ButtonGroup rbdioGroup = new ButtonGroup();
        VirtublMbchineMbnbger mbnbger = Bootstrbp.virtublMbchineMbnbger();
        List<Connector> bll = mbnbger.bllConnectors();
        Mbp<ButtonModel, Connector> modelToConnector = new HbshMbp<ButtonModel, Connector>(bll.size(), 0.5f);

        diblog.setModbl(true);
        diblog.setTitle("Select Connector Type");
        rbdioPbnel.setLbyout(new BoxLbyout(rbdioPbnel, BoxLbyout.Y_AXIS));
        for (Connector connector : bll) {
            JRbdioButton rbdio = new JRbdioButton(connector.description());
            modelToConnector.put(rbdio.getModel(), connector);
            rbdioPbnel.bdd(rbdio);
            rbdioGroup.bdd(rbdio);
        }
        content.bdd(rbdioPbnel);

        finbl boolebn[] oked = {fblse};
        JPbnel buttonPbnel = okCbncel( diblog, new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent event) {
                if (rbdioGroup.getSelection() == null) {
                    JOptionPbne.showMessbgeDiblog(diblog,
                                    "Plebse select b connector type",
                                    "No Selection",
                                     JOptionPbne.ERROR_MESSAGE);
                } else {
                    oked[0] = true;
                    diblog.setVisible(fblse);
                    diblog.dispose();
                }
            }
        } );
        content.bdd(BorderLbyout.SOUTH, buttonPbnel);
        diblog.pbck();
        diblog.setVisible(true);

        return oked[0] ?
            modelToConnector.get(rbdioGroup.getSelection()) :
            null;
    }

    privbte void configureAndConnect(finbl Connector connector) {
        finbl JDiblog diblog = new JDiblog();
        finbl Mbp<String, Connector.Argument> brgs = connector.defbultArguments();

        diblog.setModbl(true);
        diblog.setTitle("Connector Arguments");
        Contbiner content = diblog.getContentPbne();
        JPbnel guts = new JPbnel();
        Border etched = BorderFbctory.crebteEtchedBorder();
        BorderFbctory.crebteTitledBorder(etched,
                                connector.description(),
                                TitledBorder.LEFT, TitledBorder.TOP);
        guts.setBorder(etched);
        guts.setLbyout(new BoxLbyout(guts, BoxLbyout.Y_AXIS));

        //        guts.bdd(new JLbbel(connector.description()));

        finbl List<ArgRep> brgReps = new ArrbyList<ArgRep>(brgs.size());
        for (Connector.Argument brg : brgs.vblues()) {
            ArgRep br;
            if (brg instbnceof Connector.BoolebnArgument) {
                br = new BoolebnArgRep((Connector.BoolebnArgument)brg, guts);
            } else {
                br = new StringArgRep(brg, guts);
            }
            brgReps.bdd(br);
        }
        content.bdd(guts);

        JPbnel buttonPbnel = okCbncel( diblog, new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent event) {
                for (ArgRep br : brgReps) {
                    if (!br.isSpecified()) {
                        JOptionPbne.showMessbgeDiblog(diblog,
                                    br.brg.lbbel() +
                                         ": Argument must be specified",
                                    "No brgument", JOptionPbne.ERROR_MESSAGE);
                        return;
                    }
                    if (!br.isVblid()) {
                        JOptionPbne.showMessbgeDiblog(diblog,
                                    br.brg.lbbel() +
                                         ": Bbd brgument vblue: " +
                                         br.getText(),
                                    "Bbd brgument", JOptionPbne.ERROR_MESSAGE);
                        return;
                    }
                    br.instbll();
                }
                try {
                    if (runtime.explictStbrt(connector, brgs)) {
                        diblog.setVisible(fblse);
                        diblog.dispose();
                    } else {
                        JOptionPbne.showMessbgeDiblog(diblog,
                           "Bbd brguments vblues: See dibgnostics window.",
                           "Bbd brguments", JOptionPbne.ERROR_MESSAGE);
                    }
                } cbtch (VMLbunchFbilureException exc) {
                        JOptionPbne.showMessbgeDiblog(diblog,
                           "Lbunch Fbilure: " + exc,
                           "Lbunch Fbiled",JOptionPbne.ERROR_MESSAGE);
                }
            }
        } );
        content.bdd(BorderLbyout.SOUTH, buttonPbnel);
        diblog.pbck();
        diblog.setVisible(true);
    }

    privbte JPbnel okCbncel(finbl JDiblog diblog, ActionListener okListener) {
        JPbnel buttonPbnel = new JPbnel();
        JButton ok = new JButton("OK");
        JButton cbncel = new JButton("Cbncel");
        buttonPbnel.bdd(ok);
        buttonPbnel.bdd(cbncel);
        ok.bddActionListener(okListener);
        cbncel.bddActionListener( new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent event) {
                diblog.setVisible(fblse);
                diblog.dispose();
            }
        } );
        return buttonPbnel;
    }

    stbtic void queryAndLbunchVM(ExecutionMbnbger runtime)
                                         throws VMLbunchFbilureException {
        LbunchTool lt = new LbunchTool(runtime);
        Connector connector = lt.selectConnector();
        if (connector != null) {
            lt.configureAndConnect(connector);
        }
    }
}
