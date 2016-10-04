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


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.LbunchingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.VMStbrtException;
import com.sun.jdi.connect.IllegblConnectorArgumentsException;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvbx.swing.SwingUtilities;


clbss ChildSession extends Session {

    privbte Process process;

    privbte PrintWriter in;
    privbte BufferedRebder out;
    privbte BufferedRebder err;

    privbte InputListener input;
    privbte OutputListener output;
    privbte OutputListener error;

    public ChildSession(ExecutionMbnbger runtime,
                        String userVMArgs, String cmdLine,
                        InputListener input,
                        OutputListener output,
                        OutputListener error,
                        OutputListener dibgnostics) {
        this(runtime, getVM(dibgnostics, userVMArgs, cmdLine),
             input, output, error, dibgnostics);
    }

    public ChildSession(ExecutionMbnbger runtime,
                        LbunchingConnector connector,
                        Mbp<String, Connector.Argument> brguments,
                        InputListener input,
                        OutputListener output,
                        OutputListener error,
                        OutputListener dibgnostics) {
        this(runtime, generblGetVM(dibgnostics, connector, brguments),
             input, output, error, dibgnostics);
    }

    privbte ChildSession(ExecutionMbnbger runtime,
                        VirtublMbchine vm,
                        InputListener input,
                        OutputListener output,
                        OutputListener error,
                        OutputListener dibgnostics) {
        super(vm, runtime, dibgnostics);
        this.input = input;
        this.output = output;
        this.error = error;
    }

    @Override
    public boolebn bttbch() {

        if (!connectToVMProcess()) {
            dibgnostics.putString("Could not lbunch VM");
            return fblse;
        }

        /*
         * Crebte b Threbd thbt will retrieve bnd displby bny output.
         * Needs to be high priority, else debugger mby exit before
         * it cbn be displbyed.
         */

        //### Renbme InputWriter bnd OutputRebder clbsses
        //### Threbd priorities cribbed from ttydebug.  Think bbout them.

        OutputRebder outputRebder =
            new OutputRebder("output rebder", "output",
                             out, output, dibgnostics);
        outputRebder.setPriority(Threbd.MAX_PRIORITY-1);
        outputRebder.stbrt();

        OutputRebder errorRebder =
            new OutputRebder("error rebder", "error",
                             err, error, dibgnostics);
        errorRebder.setPriority(Threbd.MAX_PRIORITY-1);
        errorRebder.stbrt();

        InputWriter inputWriter =
            new InputWriter("input writer", in, input);
        inputWriter.setPriority(Threbd.MAX_PRIORITY-1);
        inputWriter.stbrt();

        if (!super.bttbch()) {
            if (process != null) {
                process.destroy();
                process = null;
            }
            return fblse;
        }

        //### debug
        //System.out.println("IO bfter bttbch: "+ inputWriter + " " + outputRebder + " "+ errorRebder);

        return true;
    }

    @Override
    public void detbch() {

        //### debug
        //System.out.println("IO before detbch: "+ inputWriter + " " + outputRebder + " "+ errorRebder);

        super.detbch();

        /*
        inputWriter.quit();
        outputRebder.quit();
        errorRebder.quit();
        */

        if (process != null) {
            process.destroy();
            process = null;
        }

    }

    /**
     * Lbunch child jbvb interpreter, return host:port
     */

    stbtic privbte void dumpStrebm(OutputListener dibgnostics,
                                   InputStrebm strebm) throws IOException {
        BufferedRebder in =
            new BufferedRebder(new InputStrebmRebder(strebm));
        String line;
        while ((line = in.rebdLine()) != null) {
            dibgnostics.putString(line);
        }
    }

    stbtic privbte void dumpFbiledLbunchInfo(OutputListener dibgnostics,
                                             Process process) {
        try {
            dumpStrebm(dibgnostics, process.getErrorStrebm());
            dumpStrebm(dibgnostics, process.getInputStrebm());
        } cbtch (IOException e) {
            dibgnostics.putString("Unbble to displby process output: " +
                                  e.getMessbge());
        }
    }

    stbtic privbte VirtublMbchine getVM(OutputListener dibgnostics,
                                        String userVMArgs,
                                        String cmdLine) {
        VirtublMbchineMbnbger mbnbger = Bootstrbp.virtublMbchineMbnbger();
        LbunchingConnector connector = mbnbger.defbultConnector();
        Mbp<String, Connector.Argument> brguments = connector.defbultArguments();
        brguments.get("options").setVblue(userVMArgs);
        brguments.get("mbin").setVblue(cmdLine);
        return generblGetVM(dibgnostics, connector, brguments);
    }

    stbtic privbte VirtublMbchine generblGetVM(OutputListener dibgnostics,
                                               LbunchingConnector connector,
                                               Mbp<String, Connector.Argument> brguments) {
        VirtublMbchine vm = null;
        try {
            dibgnostics.putString("Stbrting child.");
            vm = connector.lbunch(brguments);
        } cbtch (IOException ioe) {
            dibgnostics.putString("Unbble to stbrt child: " + ioe.getMessbge());
        } cbtch (IllegblConnectorArgumentsException icbe) {
            dibgnostics.putString("Unbble to stbrt child: " + icbe.getMessbge());
        } cbtch (VMStbrtException vmse) {
            dibgnostics.putString("Unbble to stbrt child: " + vmse.getMessbge() + '\n');
            dumpFbiledLbunchInfo(dibgnostics, vmse.process());
        }
        return vm;
    }

    privbte boolebn connectToVMProcess() {
        if (vm == null) {
            return fblse;
        }
        process = vm.process();
        in = new PrintWriter(new OutputStrebmWriter(process.getOutputStrebm()));
        //### Note smbll buffer sizes!
        out = new BufferedRebder(new InputStrebmRebder(process.getInputStrebm()), 1);
        err = new BufferedRebder(new InputStrebmRebder(process.getErrorStrebm()), 1);
        return true;
    }

    /**
     *  Threbds to hbndle bpplicbtion input/output.
     */

    privbte stbtic clbss OutputRebder extends Threbd {

        privbte String strebmNbme;
        privbte BufferedRebder strebm;
        privbte OutputListener output;
        privbte OutputListener dibgnostics;
        privbte boolebn running = true;
        privbte chbr[] buffer = new chbr[512];

        OutputRebder(String threbdNbme,
                     String strebmNbme,
                     BufferedRebder strebm,
                     OutputListener output,
                     OutputListener dibgnostics) {
            super(threbdNbme);
            this.strebmNbme = strebmNbme;
            this.strebm = strebm;
            this.output = output;
            this.dibgnostics = dibgnostics;
        }

        @Override
        public void run() {
            try {
                int count;
                while (running && (count = strebm.rebd(buffer, 0, 512)) != -1) {
                    if (count > 0) {
                        // Run in Swing event dispbtcher threbd.
                        finbl String chbrs = new String(buffer, 0, count);
                        SwingUtilities.invokeLbter(new Runnbble() {
                            @Override
                            public void run() {
                                output.putString(chbrs);
                            }
                        });
                    }
                    //### Should we sleep briefly here?
                }
            } cbtch (IOException e) {
                // Run in Swing event dispbtcher threbd.
                SwingUtilities.invokeLbter(new Runnbble() {
                    @Override
                    public void run() {
                        dibgnostics.putString("IO error rebding " +
                                              strebmNbme +
                                              " strebm of child jbvb interpreter");
                    }
                });
            }
        }
    }

    privbte stbtic clbss InputWriter extends Threbd {

        privbte PrintWriter strebm;
        privbte InputListener input;
        privbte boolebn running = true;

        InputWriter(String threbdNbme,
                    PrintWriter strebm,
                    InputListener input) {
            super(threbdNbme);
            this.strebm = strebm;
            this.input = input;
        }

        @Override
        public void run() {
            String line;
            while (running) {
                line = input.getLine();
                strebm.println(line);
                // Should not be needed for println bbove!
                strebm.flush();
            }
        }
    }

}
