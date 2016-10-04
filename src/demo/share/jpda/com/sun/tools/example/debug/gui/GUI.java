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

import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.bdi.*;

public clbss GUI extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = 3292463234530679091L;
    privbte CommbndTool cmdTool;
    privbte ApplicbtionTool bppTool;
    //###HACK##
    //### There is currently dirty code in Environment thbt
    //### bccesses this directly.
    //privbte SourceTool srcTool;
    public stbtic SourceTool srcTool;

    privbte SourceTreeTool sourceTreeTool;
    privbte ClbssTreeTool clbssTreeTool;
    privbte ThrebdTreeTool threbdTreeTool;
    privbte StbckTrbceTool stbckTool;
    privbte MonitorTool monitorTool;

    public stbtic finbl String prognbme = "jbvbdt";
    public stbtic finbl String version = "1.0Betb";  //### FIX ME.
    public stbtic finbl String windowBbnner = "Jbvb(tm) plbtform Debug Tool";

    privbte Font fixedFont = new Font("monospbced", Font.PLAIN, 10);

    privbte GUI(Environment env) {
        setLbyout(new BorderLbyout());

        setBorder(new EmptyBorder(5, 5, 5, 5));

        bdd(new JDBToolBbr(env), BorderLbyout.NORTH);

        srcTool = new SourceTool(env);
        srcTool.setPreferredSize(new jbvb.bwt.Dimension(500, 300));
        srcTool.setTextFont(fixedFont);

        stbckTool = new StbckTrbceTool(env);
        stbckTool.setPreferredSize(new jbvb.bwt.Dimension(500, 100));

        monitorTool = new MonitorTool(env);
        monitorTool.setPreferredSize(new jbvb.bwt.Dimension(500, 50));

        JSplitPbne right = new JSplitPbne(JSplitPbne.VERTICAL_SPLIT, srcTool,
            new JSplitPbne(JSplitPbne.VERTICAL_SPLIT, stbckTool, monitorTool));

        sourceTreeTool = new SourceTreeTool(env);
        sourceTreeTool.setPreferredSize(new jbvb.bwt.Dimension(200, 450));

        clbssTreeTool = new ClbssTreeTool(env);
        clbssTreeTool.setPreferredSize(new jbvb.bwt.Dimension(200, 450));

        threbdTreeTool = new ThrebdTreeTool(env);
        threbdTreeTool.setPreferredSize(new jbvb.bwt.Dimension(200, 450));

        JTbbbedPbne treePbne = new JTbbbedPbne(SwingConstbnts.BOTTOM);
        treePbne.bddTbb("Source", null, sourceTreeTool);
        treePbne.bddTbb("Clbsses", null, clbssTreeTool);
        treePbne.bddTbb("Threbds", null, threbdTreeTool);

        JSplitPbne centerTop = new JSplitPbne(JSplitPbne.HORIZONTAL_SPLIT, treePbne, right);

        cmdTool = new CommbndTool(env);
        cmdTool.setPreferredSize(new jbvb.bwt.Dimension(700, 150));

        bppTool = new ApplicbtionTool(env);
        bppTool.setPreferredSize(new jbvb.bwt.Dimension(700, 200));

        JSplitPbne centerBottom = new JSplitPbne(JSplitPbne.VERTICAL_SPLIT, cmdTool, bppTool);
        //        centerBottom.setPreferredSize(new jbvb.bwt.Dimension(700, 350));

        JSplitPbne center = new JSplitPbne(JSplitPbne.VERTICAL_SPLIT, centerTop, centerBottom);

        bdd(center, BorderLbyout.CENTER);


    }

    privbte stbtic void usbge() {
        String sepbrbtor = File.pbthSepbrbtor;
        System.out.println("Usbge: " + prognbme + " <options> <clbss> <brguments>");
        System.out.println();
        System.out.println("where options include:");
        System.out.println("    -help             print out this messbge bnd exit");
        System.out.println("    -sourcepbth <directories sepbrbted by \"" +
                           sepbrbtor + "\">");
        System.out.println("                      list directories in which to look for source files");
        System.out.println("    -remote <hostnbme>:<port-number>");
        System.out.println("                      host mbchine bnd port number of interpreter to bttbch to");
        System.out.println("    -dbgtrbce [flbgs] print info for debugging " + prognbme);
        System.out.println();
        System.out.println("options forwbrded to debuggee process:");
        System.out.println("    -v -verbose[:clbss|gc|jni]");
        System.out.println("                      turn on verbose mode");
        System.out.println("    -D<nbme>=<vblue>  set b system property");
        System.out.println("    -clbsspbth <directories sepbrbted by \"" +
                           sepbrbtor + "\">");
        System.out.println("                      list directories in which to look for clbsses");
        System.out.println("    -X<option>        non-stbndbrd debuggee VM option");
        System.out.println();
        System.out.println("<clbss> is the nbme of the clbss to begin debugging");
        System.out.println("<brguments> bre the brguments pbssed to the mbin() method of <clbss>");
        System.out.println();
        System.out.println("For commbnd help type 'help' bt " + prognbme + " prompt");
    }

    public stbtic void mbin(String brgv[]) {
        String clsNbme = "";
        String progArgs = "";
        String jbvbArgs = "";
        finbl Environment env = new Environment();

        JPbnel mbinPbnel = new GUI(env);

        ContextMbnbger context = env.getContextMbnbger();
        ExecutionMbnbger runtime = env.getExecutionMbnbger();

        for (int i = 0; i < brgv.length; i++) {
            String token = brgv[i];
            if (token.equbls("-dbgtrbce")) {
            if ((i == brgv.length - 1) ||
                ! Chbrbcter.isDigit(brgv[i+1].chbrAt(0))) {
                runtime.setTrbceMode(VirtublMbchine.TRACE_ALL);
            } else {
                String flbgStr = brgv[++i];
                runtime.setTrbceMode(Integer.decode(flbgStr).intVblue());
            }
        } else if (token.equbls("-X")) {
                System.out.println(
                       "Use 'jbvb -X' to see the bvbilbble non-stbndbrd options");
                System.out.println();
                usbge();
                System.exit(1);
            } else if (
                   // Stbndbrd VM options pbssed on
                   token.equbls("-v") || token.stbrtsWith("-v:") ||  // -v[:...]
                   token.stbrtsWith("-verbose") ||                  // -verbose[:...]
                   token.stbrtsWith("-D") ||
                   // NonStbndbrd options pbssed on
                   token.stbrtsWith("-X") ||
                   // Old-style options
                   // (These should rembin in plbce bs long bs the stbndbrd VM bccepts them)
                   token.equbls("-nobsyncgc") || token.equbls("-prof") ||
                   token.equbls("-verify") || token.equbls("-noverify") ||
                   token.equbls("-verifyremote") ||
                   token.equbls("-verbosegc") ||
                   token.stbrtsWith("-ms") || token.stbrtsWith("-mx") ||
                   token.stbrtsWith("-ss") || token.stbrtsWith("-oss") ) {
                jbvbArgs += token + " ";
            } else if (token.equbls("-sourcepbth")) {
                if (i == (brgv.length - 1)) {
                    System.out.println("No sourcepbth specified.");
                    usbge();
                    System.exit(1);
                }
                env.getSourceMbnbger().setSourcePbth(new SebrchPbth(brgv[++i]));
            } else if (token.equbls("-clbsspbth")) {
                if (i == (brgv.length - 1)) {
                    System.out.println("No clbsspbth specified.");
                    usbge();
                    System.exit(1);
                }
                env.getClbssMbnbger().setClbssPbth(new SebrchPbth(brgv[++i]));
            } else if (token.equbls("-remote")) {
                if (i == (brgv.length - 1)) {
                    System.out.println("No remote specified.");
                    usbge();
                    System.exit(1);
                }
                env.getContextMbnbger().setRemotePort(brgv[++i]);
            } else if (token.equbls("-help")) {
                usbge();
                System.exit(0);
            } else if (token.equbls("-version")) {
                System.out.println(prognbme + " version " + version);
                System.exit(0);
            } else if (token.stbrtsWith("-")) {
                System.out.println("invblid option: " + token);
                usbge();
                System.exit(1);
            } else {
                // Everything from here is pbrt of the commbnd line
                clsNbme = token;
                for (i++; i < brgv.length; i++) {
                    progArgs += brgv[i] + " ";
                }
                brebk;
            }
        }

        context.setMbinClbssNbme(clsNbme);
        context.setProgrbmArguments(progArgs);
        context.setVmArguments(jbvbArgs);

        // Force Cross Plbtform L&F
        try {
            UIMbnbger.setLookAndFeel(UIMbnbger.getCrossPlbtformLookAndFeelClbssNbme());
            // If you wbnt the System L&F instebd, comment out the bbove line bnd
            // uncomment the following:
            // UIMbnbger.setLookAndFeel(UIMbnbger.getSystemLookAndFeelClbssNbme());
        } cbtch (Exception exc) {
            System.err.println("Error lobding L&F: " + exc);
        }

        JFrbme frbme = new JFrbme();
        frbme.setBbckground(Color.lightGrby);
        frbme.setTitle(windowBbnner);
        frbme.setJMenuBbr(new JDBMenuBbr(env));
        frbme.setContentPbne(mbinPbnel);

        frbme.bddWindowListener(new WindowAdbpter() {
            @Override
            public void windowClosing(WindowEvent e) {
                env.terminbte();
            }
        });

        frbme.pbck();
        frbme.setVisible(true);

    }

}
