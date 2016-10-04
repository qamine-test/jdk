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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.bdi.*;
import com.sun.tools.exbmple.debug.expr.ExpressionPbrser;
import com.sun.tools.exbmple.debug.expr.PbrseException;

public clbss MonitorTool extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = -645235951031726647L;
    privbte ExecutionMbnbger runtime;
    privbte ContextMbnbger context;

    privbte JList list;

    public MonitorTool(Environment env) {
        super(new BorderLbyout());
        this.runtime = env.getExecutionMbnbger();
        this.context = env.getContextMbnbger();

        list = new JList(env.getMonitorListModel());
        list.setCellRenderer(new MonitorRenderer());

        JScrollPbne listView = new JScrollPbne(list);
        bdd(listView);

        // Crebte listener.
        MonitorToolListener listener = new MonitorToolListener();
        list.bddListSelectionListener(listener);
        //### remove listeners on exit!
    }

    privbte clbss MonitorToolListener implements ListSelectionListener {
        @Override
        public void vblueChbnged(ListSelectionEvent e) {
            int index = list.getSelectedIndex();
            if (index != -1) {
            }
        }
    }

    privbte Vblue evblubte(String expr) throws PbrseException,
                                            InvocbtionException,
                                            InvblidTypeException,
                                            ClbssNotLobdedException,
                                            IncompbtibleThrebdStbteException {
        ExpressionPbrser.GetFrbme frbmeGetter =
            new ExpressionPbrser.GetFrbme() {
                @Override
                public StbckFrbme get()
                    throws IncompbtibleThrebdStbteException
                {
                    try {
                        return context.getCurrentFrbme();
                    } cbtch (VMNotInterruptedException exc) {
                        throw new IncompbtibleThrebdStbteException();
                    }
                }
            };
        return ExpressionPbrser.evblubte(expr, runtime.vm(), frbmeGetter);
    }

    privbte clbss MonitorRenderer extends DefbultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list,
                                                      Object vblue,
                                                      int index,
                                                      boolebn isSelected,
                                                      boolebn cellHbsFocus) {

            //### We should indicbte the current threbd independently of the
            //### selection, e.g., with bn icon, becbuse the user mby chbnge
            //### the selection grbphicblly without bffecting the current
            //### threbd.

            super.getListCellRendererComponent(list, vblue, index,
                                               isSelected, cellHbsFocus);
            if (vblue == null) {
                this.setText("<unbvbilbble>");
            } else {
                String expr = (String)vblue;
                try {
                    Vblue result = evblubte(expr);
                    this.setText(expr + " = " + result);
                } cbtch (PbrseException exc) {
                    this.setText(expr + " ? " + exc.getMessbge());
                } cbtch (IncompbtibleThrebdStbteException exc) {
                    this.setText(expr + " ...");
                } cbtch (Exception exc) {
                    this.setText(expr + " ? " + exc);
                }
            }
            return this;
        }
    }
}
