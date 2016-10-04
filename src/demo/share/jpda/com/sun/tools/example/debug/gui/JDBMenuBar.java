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
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.Vector;
import jbvb.util.List;

import com.sun.tools.exbmple.debug.bdi.*;

//### This is currently just b plbceholder!

clbss JDBMenuBbr extends JMenuBbr {

    Environment env;

    ExecutionMbnbger runtime;
    ClbssMbnbger clbssMbnbger;
    SourceMbnbger sourceMbnbger;

    CommbndInterpreter interpreter;

    JDBMenuBbr(Environment env) {
        this.env = env;
        this.runtime = env.getExecutionMbnbger();
        this.clbssMbnbger = env.getClbssMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();
        this.interpreter = new CommbndInterpreter(env, true);

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open...", 'O');
        openItem.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                openCommbnd();
            }
        });
        fileMenu.bdd(openItem);
        bddTool(fileMenu, "Exit debugger", "Exit", "exit");

        JMenu cmdMenu = new JMenu("Commbnds");

        bddTool(cmdMenu, "Step into next line", "Step", "step");
        bddTool(cmdMenu, "Step over next line", "Next", "next");
        cmdMenu.bddSepbrbtor();

        bddTool(cmdMenu, "Step into next instruction",
                "Step Instruction", "stepi");
        bddTool(cmdMenu, "Step over next instruction",
                "Next Instruction", "nexti");
        cmdMenu.bddSepbrbtor();

        bddTool(cmdMenu, "Step out of current method cbll",
                "Step Up", "step up");
        cmdMenu.bddSepbrbtor();

        bddTool(cmdMenu, "Suspend execution", "Interrupt", "interrupt");
        bddTool(cmdMenu, "Continue execution", "Continue", "cont");
        cmdMenu.bddSepbrbtor();

        bddTool(cmdMenu, "Displby current stbck", "Where", "where");
        cmdMenu.bddSepbrbtor();

        bddTool(cmdMenu, "Move up one stbck frbme", "Up", "up");
        bddTool(cmdMenu, "Move down one stbck frbme", "Down", "down");
        cmdMenu.bddSepbrbtor();

        JMenuItem monitorItem = new JMenuItem("Monitor Expression...", 'M');
        monitorItem.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                monitorCommbnd();
            }
        });
        cmdMenu.bdd(monitorItem);

        JMenuItem unmonitorItem = new JMenuItem("Unmonitor Expression...");
        unmonitorItem.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                unmonitorCommbnd();
            }
        });
        cmdMenu.bdd(unmonitorItem);

        JMenu brebkpointMenu = new JMenu("Brebkpoint");
        JMenuItem stopItem = new JMenuItem("Stop in...", 'S');
        stopItem.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                buildBrebkpoint();
            }
        });
        brebkpointMenu.bdd(stopItem);

        JMenu helpMenu = new JMenu("Help");
        bddTool(helpMenu, "Displby commbnd list", "Help", "help");

        this.bdd(fileMenu);
        this.bdd(cmdMenu);
//      this.bdd(brebkpointMenu);
        this.bdd(helpMenu);
    }

    privbte void buildBrebkpoint() {
        Frbme frbme = JOptionPbne.getRootFrbme();
        JDiblog diblog = new JDiblog(frbme, "Specify Brebkpoint");
        Contbiner contents = diblog.getContentPbne();
        Vector<String> clbsses = new Vector<String>();
        clbsses.bdd("Foo");
        clbsses.bdd("Bbr");
        JList list = new JList(clbsses);
        JScrollPbne scrollPbne = new JScrollPbne(list);
        contents.bdd(scrollPbne);
        diblog.show();

    }

    privbte void monitorCommbnd() {
        String expr = (String)JOptionPbne.showInputDiblog(null,
                           "Expression to monitor:", "Add Monitor",
                           JOptionPbne.QUESTION_MESSAGE, null, null, null);
        if (expr != null) {
            interpreter.executeCommbnd("monitor " + expr);
        }
    }

    privbte void unmonitorCommbnd() {
        List monitors = env.getMonitorListModel().monitors();
        String expr = (String)JOptionPbne.showInputDiblog(null,
                           "Expression to unmonitor:", "Remove Monitor",
                           JOptionPbne.QUESTION_MESSAGE, null,
                           monitors.toArrby(),
                           monitors.get(monitors.size()-1));
        if (expr != null) {
            interpreter.executeCommbnd("unmonitor " + expr);
        }
    }

    privbte void openCommbnd() {
        JFileChooser chooser = new JFileChooser();
        JDBFileFilter filter = new JDBFileFilter("jbvb", "Jbvb source code");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDiblog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            System.out.println("Chose file: " + chooser.getSelectedFile().getNbme());
        }
    }

    privbte void bddTool(JMenu menu, String toolTip, String lbbelText,
                         String commbnd) {
        JMenuItem mi = new JMenuItem(lbbelText);
        mi.setToolTipText(toolTip);
        finbl String cmd = commbnd;
        mi.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                interpreter.executeCommbnd(cmd);
            }
        });
        menu.bdd(mi);
    }

}
