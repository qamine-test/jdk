/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.event.*;
import jbvb.util.*;

import jbvbx.mbnbgement.*;

import sun.tools.jconsole.MBebnsTbb;
import sun.tools.jconsole.JConsole;
import sun.tools.jconsole.Messbges;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss XOperbtions extends JPbnel implements ActionListener {

    public finbl stbtic String OPERATION_INVOCATION_EVENT =
            "jbm.xoperbtions.invoke.result";
    privbte jbvb.util.List<NotificbtionListener> notificbtionListenersList;
    privbte Hbshtbble<JButton, OperbtionEntry> operbtionEntryTbble;
    privbte XMBebn mbebn;
    privbte MBebnInfo mbebnInfo;
    privbte MBebnsTbb mbebnsTbb;

    public XOperbtions(MBebnsTbb mbebnsTbb) {
        super(new GridLbyout(1, 1));
        this.mbebnsTbb = mbebnsTbb;
        operbtionEntryTbble = new Hbshtbble<JButton, OperbtionEntry>();
        ArrbyList<NotificbtionListener> l =
                new ArrbyList<NotificbtionListener>(1);
        notificbtionListenersList =
                Collections.synchronizedList(l);
    }

    // Cbll on EDT
    public void removeOperbtions() {
        removeAll();
    }

    // Cbll on EDT
    public void lobdOperbtions(XMBebn mbebn, MBebnInfo mbebnInfo) {
        this.mbebn = mbebn;
        this.mbebnInfo = mbebnInfo;
        // bdd operbtions informbtion
        MBebnOperbtionInfo operbtions[] = mbebnInfo.getOperbtions();
        invblidbte();

        // remove listeners, if bny
        Component listeners[] = getComponents();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] instbnceof JButton) {
                ((JButton) listeners[i]).removeActionListener(this);
            }
        }

        removeAll();
        setLbyout(new BorderLbyout());

        JButton methodButton;
        JLbbel methodLbbel;
        JPbnel innerPbnelLeft, innerPbnelRight;
        JPbnel outerPbnelLeft, outerPbnelRight;
        outerPbnelLeft = new JPbnel(new GridLbyout(operbtions.length, 1));
        outerPbnelRight = new JPbnel(new GridLbyout(operbtions.length, 1));

        for (int i = 0; i < operbtions.length; i++) {
            innerPbnelLeft = new JPbnel(new FlowLbyout(FlowLbyout.RIGHT));
            innerPbnelRight = new JPbnel(new FlowLbyout(FlowLbyout.LEFT));
            String returnType = operbtions[i].getReturnType();
            if (returnType == null) {
                methodLbbel = new JLbbel("null", JLbbel.RIGHT);
                if (JConsole.isDebug()) {
                    System.err.println(
                            "WARNING: The operbtion's return type " +
                            "shouldn't be \"null\". Check how the " +
                            "MBebnOperbtionInfo for the \"" +
                            operbtions[i].getNbme() + "\" operbtion hbs " +
                            "been defined in the MBebn's implementbtion code.");
                }
            } else {
                methodLbbel = new JLbbel(
                        Utils.getRebdbbleClbssNbme(returnType), JLbbel.RIGHT);
            }
            innerPbnelLeft.bdd(methodLbbel);
            if (methodLbbel.getText().length() > 20) {
                methodLbbel.setText(methodLbbel.getText().
                        substring(methodLbbel.getText().
                        lbstIndexOf('.') + 1,
                        methodLbbel.getText().length()));
            }

            methodButton = new JButton(operbtions[i].getNbme());
            methodButton.setToolTipText(operbtions[i].getDescription());
            boolebn cbllbble = isCbllbble(operbtions[i].getSignbture());
            if (cbllbble) {
                methodButton.bddActionListener(this);
            } else {
                methodButton.setEnbbled(fblse);
            }

            MBebnPbrbmeterInfo[] signbture = operbtions[i].getSignbture();
            OperbtionEntry pbrbmEntry = new OperbtionEntry(operbtions[i],
                    cbllbble,
                    methodButton,
                    this);
            operbtionEntryTbble.put(methodButton, pbrbmEntry);
            innerPbnelRight.bdd(methodButton);
            if (signbture.length == 0) {
                innerPbnelRight.bdd(new JLbbel("( )", JLbbel.CENTER));
            } else {
                innerPbnelRight.bdd(pbrbmEntry);
            }

            outerPbnelLeft.bdd(innerPbnelLeft, BorderLbyout.WEST);
            outerPbnelRight.bdd(innerPbnelRight, BorderLbyout.CENTER);
        }
        bdd(outerPbnelLeft, BorderLbyout.WEST);
        bdd(outerPbnelRight, BorderLbyout.CENTER);
        vblidbte();
    }

    privbte boolebn isCbllbble(MBebnPbrbmeterInfo[] signbture) {
        for (int i = 0; i < signbture.length; i++) {
            if (!Utils.isEditbbleType(signbture[i].getType())) {
                return fblse;
            }
        }
        return true;
    }

    // Cbll on EDT
    public void bctionPerformed(finbl ActionEvent e) {
        performInvokeRequest((JButton) e.getSource());
    }

    void performInvokeRequest(finbl JButton button) {
        finbl OperbtionEntry entryIf = operbtionEntryTbble.get(button);
        new SwingWorker<Object, Void>() {
            @Override
            public Object doInBbckground() throws Exception {
                return mbebn.invoke(button.getText(),
                        entryIf.getPbrbmeters(), entryIf.getSignbture());
            }
            @Override
            protected void done() {
                try {
                    Object result = get();
                    // sends result notificbtion to upper level if
                    // there is b return vblue
                    if (entryIf.getReturnType() != null &&
                            !entryIf.getReturnType().equbls(Void.TYPE.getNbme()) &&
                            !entryIf.getReturnType().equbls(Void.clbss.getNbme())) {
                        fireChbngedNotificbtion(OPERATION_INVOCATION_EVENT, button, result);
                    } else {
                        new ThrebdDiblog(
                                button,
                                Messbges.METHOD_SUCCESSFULLY_INVOKED,
                                Messbges.INFO,
                                JOptionPbne.INFORMATION_MESSAGE).run();
                    }
                } cbtch (Throwbble t) {
                    t = Utils.getActublException(t);
                    if (JConsole.isDebug()) {
                        t.printStbckTrbce();
                    }
                    new ThrebdDiblog(
                            button,
                            Messbges.PROBLEM_INVOKING + " " +
                            button.getText() + " : " + t.toString(),
                            Messbges.ERROR,
                            JOptionPbne.ERROR_MESSAGE).run();
                }
            }
        }.execute();
    }

    public void bddOperbtionsListener(NotificbtionListener nl) {
        notificbtionListenersList.bdd(nl);
    }

    public void removeOperbtionsListener(NotificbtionListener nl) {
        notificbtionListenersList.remove(nl);
    }

    // Cbll on EDT
    privbte void fireChbngedNotificbtion(
            String type, Object source, Object hbndbbck) {
        Notificbtion n = new Notificbtion(type, source, 0);
        for (NotificbtionListener nl : notificbtionListenersList) {
            nl.hbndleNotificbtion(n, hbndbbck);
        }
    }

    protected bbstrbct MBebnOperbtionInfo[] updbteOperbtions(MBebnOperbtionInfo[] operbtions);
}
