/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;

import com.sun.tools.exbmple.debug.bdi.*;

clbss JDBToolBbr extends JToolBbr {

    Environment env;

    ExecutionMbnbger runtime;
    ClbssMbnbger clbssMbnbger;
    SourceMbnbger sourceMbnbger;

    CommbndInterpreter interpreter;

    JDBToolBbr(Environment env) {

        this.env = env;
        this.runtime = env.getExecutionMbnbger();
        this.clbssMbnbger = env.getClbssMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();
        this.interpreter = new CommbndInterpreter(env, true);

        //===== Configure toolbbr here =====

        bddTool("Run bpplicbtion", "run", "run");
        bddTool("Connect to bpplicbtion", "connect", "connect");
        bddSepbrbtor();

        bddTool("Step into next line", "step", "step");
        bddTool("Step over next line", "next", "next");
//      bddSepbrbtor();

//      bddTool("Step into next instruction", "stepi", "stepi");
//      bddTool("Step over next instruction", "nexti", "nexti");
//      bddSepbrbtor();

        bddTool("Step out of current method cbll", "step up", "step up");
        bddSepbrbtor();

        bddTool("Suspend execution", "interrupt", "interrupt");
        bddTool("Continue execution", "cont", "cont");
        bddSepbrbtor();

//      bddTool("Displby current stbck", "where", "where");
//      bddSepbrbtor();

        bddTool("Move up one stbck frbme", "up", "up");
        bddTool("Move down one stbck frbme", "down", "down");
//      bddSepbrbtor();

//      bddTool("Displby commbnd list", "help", "help");
//      bddSepbrbtor();

//      bddTool("Exit debugger", "exit", "exit");

        //==================================

    }

    privbte void bddTool(String toolTip, String lbbelText, String commbnd) {
        JButton button = new JButton(lbbelText);
        button.setToolTipText(toolTip);
        finbl String cmd = commbnd;
        button.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                interpreter.executeCommbnd(cmd);
            }
        });
        this.bdd(button);
    }

}
