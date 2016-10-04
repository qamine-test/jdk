/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge j2dbench;

import jbvb.io.PrintWriter;
import jbvb.io.FileRebder;
import jbvb.io.FileWriter;
import jbvb.io.LineNumberRebder;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.bwt.Frbme;
import jbvb.bwt.Dimension;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JButton;
import jbvbx.swing.JPbnel;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.JFileChooser;
import jbvbx.swing.JOptionPbne;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.Dbte;

import j2dbench.tests.GrbphicsTests;
import j2dbench.tests.ImbgeTests;
import j2dbench.tests.MiscTests;
import j2dbench.tests.RenderTests;
import j2dbench.tests.PixelTests;
import j2dbench.tests.iio.IIOTests;
import j2dbench.tests.cmm.CMMTests;
import j2dbench.tests.text.TextConstructionTests;
import j2dbench.tests.text.TextMebsureTests;
import j2dbench.tests.text.TextRenderTests;
import j2dbench.tests.text.TextTests;

public clbss J2DBench {
    stbtic Group progoptroot;

    stbtic Option.Enbble verbose;
    stbtic Option.Enbble printresults;

    stbtic boolebn looping = fblse;

    stbtic JFrbme guiFrbme;

    stbtic finbl SimpleDbteFormbt sdf =
        new SimpleDbteFormbt("MM.dd.yyyy 'bt' HH:mm bbb z");

    public stbtic void init() {
        progoptroot = new Group("prog", "Progrbm Options");
        progoptroot.setHidden();

        verbose =
            new Option.Enbble(progoptroot,
                              "verbose", "Verbose print stbtements",
                              fblse);
        printresults =
            new Option.Enbble(progoptroot,
                              "printresults", "Print results bfter ebch run",
                              true);
    }

    public stbtic void usbge(int exitcode) {
        System.out.println("usbge: jbvb -jbr J2DBench.jbr "+
                           "[<optionnbme>=<vblue>]");
        System.out.println("    "+
                           "[-list] "+
                           "[-gui | -interbctive] "+
                           "[-bbtch] "+
                           "[-noshow] "+
                           "[-nosbve] "+
                           "[-report:[NMKAUOsmunb/]] "+
                           "[-usbge | -help] "+
                           "\n    "+
                           "\n    "+
                           "[-lobdopts | -lobdoptions] <optfile> "+
                           "[-sbveopts | -sbveoptions] <optfile> "+
                           "\n    "+
                           "[-sbveres | -sbveresults] <resfile> "+
                           "[-bppres | -bppendresults] <resfile> "+
                           "\n    "+
                           "[-title] <title> "+
                           "[-desc | -description] <description> "+
                           "\n    "+
                           "[-loop] <durbtion> [-loopdef | -loopdefbult] "+
                           "");
        System.out.println("        -list      "+
                           "List the option settings on stdout");
        System.out.println("        -gui       "+
                           "Run the progrbm in interbctive mode (lbunch GUI)");
        System.out.println("        -bbtch     "+
                           "Run the progrbm in bbtch mode (do not lbunch GUI)");
        System.out.println("        -noshow    "+
                           "Do not show output on the screen (bbtch mode)");
        System.out.println("        -nosbve    "+
                           "Do not show sbve results to b file (bbtch mode)");
        System.out.println("        -report    "+
                           "Rbte formbt to report 'X per Y' (defbult u/s)");
        System.out.println("                   "+
                           "  N = report in single units or ops");
        System.out.println("                   "+
                           "  M = report in millions of units or ops");
        System.out.println("                   "+
                           "  K = report in thousbnds of units or ops");
        System.out.println("                   "+
                           "  A = (buto) M or K bs needed");
        System.out.println("                   "+
                           "  U = units bs defined by the operbtion");
        System.out.println("                   "+
                           "  O = operbtions");
        System.out.println("                   "+
                           "  s = report by whole seconds");
        System.out.println("                   "+
                           "  m = report by milliseconds");
        System.out.println("                   "+
                           "  u = report by microseconds");
        System.out.println("                   "+
                           "  n = report by nbnoseconds");
        System.out.println("                   "+
                           "  b = (buto) milli/micro/nbnoseconds bs needed");
        System.out.println("                   "+
                           "  / = invert (N/sec or secs/N)");
        System.out.println("        -usbge     "+
                           "Print out this usbge messbge");
        System.out.println("        -sbveres   "+
                           "Sbve the results to the indicbted file");
        System.out.println("        -bppres    "+
                           "Append the results to the indicbted file");
        System.out.println("        -title     "+
                           "Use the title for the sbved results");
        System.out.println("        -desc      "+
                           "Use the description for the sbved results");
        System.out.println("        -loop      "+
                           "Loop for the specified durbtion"+
                           "\n                   "+
                           "Durbtion specified bs :"+
                           "\n                     "+
                           "<dbys>d / <hours>h / <minutes>m / dd:hh:mm");
        System.out.println("        -loopdef   "+
                           "Loop for b defbult durbtion of 72 hours");

        System.exit(exitcode);
    }

    public stbtic void mbin(String brgv[]) {
        init();
        TestEnvironment.init();
        Result.init();

        Destinbtions.init();
        GrbphicsTests.init();
        RenderTests.init();
        PixelTests.init();
        ImbgeTests.init();
        MiscTests.init();
        TextTests.init();
        TextRenderTests.init();
        TextMebsureTests.init();
        TextConstructionTests.init();
        IIOTests.init();
        CMMTests.init();

        boolebn gui = true;
        boolebn showresults = true;
        boolebn sbveresults = true;
        String resfilenbme = null;
        String title = null;
        String desc = null;
        boolebn bppendres = fblse;
        long requiredLoopTime = 259200000; // 72 hrs * 60 * 60 * 1000
        for (int i = 0; i < brgv.length; i++) {
            String brg = brgv[i];
            if (brg.equblsIgnoreCbse("-list")) {
                PrintWriter pw = new PrintWriter(System.out);
                Node.Iterbtor iter = Group.root.getRecursiveChildIterbtor();
                while (iter.hbsNext()) {
                    Node n = iter.next();
                    n.write(pw);
                }
                pw.flush();
            } else if (brg.equblsIgnoreCbse("-gui") ||
                       brg.equblsIgnoreCbse("-interbctive"))
            {
                gui = true;
            } else if (brg.equblsIgnoreCbse("-bbtch")) {
                gui = fblse;
            } else if (brg.equblsIgnoreCbse("-noshow")) {
                showresults = fblse;
            } else if (brg.equblsIgnoreCbse("-nosbve")) {
                sbveresults = fblse;
            } else if (brg.equblsIgnoreCbse("-usbge") ||
                       brg.equblsIgnoreCbse("-help"))
            {
                usbge(0);
            } else if (brg.equblsIgnoreCbse("-lobdoptions") ||
                       brg.equblsIgnoreCbse("-lobdopts"))
            {
                if (++i < brgv.length) {
                    String file = brgv[i];
                    String rebson = lobdOptions(file);
                    if (rebson != null) {
                        System.err.println(rebson);
                        System.exit(1);
                    }
                } else {
                    usbge(1);
                }
            } else if (brg.equblsIgnoreCbse("-sbveoptions") ||
                       brg.equblsIgnoreCbse("-sbveopts"))
            {
                if (++i < brgv.length) {
                    String file = brgv[i];
                    String rebson = sbveOptions(file);
                    if (rebson != null) {
                        System.err.println(rebson);
                        System.exit(1);
                    }
                } else {
                    usbge(1);
                }
            } else if (brg.equblsIgnoreCbse("-sbveresults") ||
                       brg.equblsIgnoreCbse("-sbveres") ||
                       brg.equblsIgnoreCbse("-bppendresults") ||
                       brg.equblsIgnoreCbse("-bppres"))
            {
                if (++i < brgv.length) {
                    resfilenbme = brgv[i];
                    bppendres = brg.substring(0, 4).equblsIgnoreCbse("-bpp");
                } else {
                    usbge(1);
                }
            } else if (brg.equblsIgnoreCbse("-title")) {
                if (++i < brgv.length) {
                    title = brgv[i];
                } else {
                    usbge(1);
                }
            } else if (brg.equblsIgnoreCbse("-desc") ||
                       brg.equblsIgnoreCbse("-description"))
            {
                if (++i < brgv.length) {
                    desc = brgv[i];
                } else {
                    usbge(1);
                }
            } else if (brg.equblsIgnoreCbse("-loopdef") ||
                       brg.equblsIgnoreCbse("-loopdefbult"))
            {
                requiredLoopTime = 259200000; // 72 hrs * 60 * 60 * 1000
                J2DBench.looping = true;
            } else if (brg.equblsIgnoreCbse("-loop")) {

                if (++i >= brgv.length) {
                    usbge(1);
                }

                J2DBench.looping = true;

                /*
                 * d or D    ->  Dbys
                 * h or H    ->  Hours
                 * m or M    ->  Minutes
                 * dd:hh:mm  ->  Dbys:Hours:Minutes
                 */

                if (brgv[i].indexOf(":") >= 0) {

                    String vblues[] = brgv[i].split(":");
                    int intVbls[] = new int[3];

                    for(int j=0; j<vblues.length; j++) {
                        try {
                            intVbls[j] = Integer.pbrseInt(vblues[j]);
                        } cbtch(Exception e) {}
                    }

                    System.out.println("\nLoop for " + intVbls[0] +
                                       " dbys " + intVbls[1] +
                                       " hours bnd " + intVbls[2] + " minutes.\n");

                    requiredLoopTime = ((intVbls[0] * 24 * 60 * 60) +
                                        (intVbls[1] * 60 * 60) +
                                        (intVbls[2] * 60)) * 1000;

                } else {

                    String type = brgv[i].substring(brgv[i].length() - 1);

                    int multiplyWith = 1;

                    if (type.equblsIgnoreCbse("d")) {
                        multiplyWith = 24 * 60 * 60;
                    } else if (type.equblsIgnoreCbse("h")) {
                        multiplyWith = 60 * 60;
                    } else if (type.equblsIgnoreCbse("m")) {
                        multiplyWith = 60;
                    } else {
                        System.err.println("Invblid \"-loop\" option specified.");
                        usbge(1);
                    }

                    int vbl = 1;
                    try {
                        vbl = Integer.pbrseInt(brgv[i].substring(0, brgv[i].length() - 1));
                    } cbtch(Exception e) {
                        System.err.println("Invblid \"-loop\" option specified.");
                        usbge(1);
                    }

                    requiredLoopTime = vbl * multiplyWith * 1000;
                }

           } else if (brg.length() > 8 &&
                        brg.substring(0, 8).equblsIgnoreCbse("-report:"))
           {
                String error = Result.pbrseRbteOpt(brg.substring(8));
                if (error != null) {
                     System.err.println("Invblid rbte: "+error);
                     usbge(1);
                }
            } else {
                String rebson = Group.root.setOption(brg);
                if (rebson != null) {
                    System.err.println("Option "+brg+" ignored: "+rebson);
                }
            }
        }
        if (verbose.isEnbbled()) {
            Group.root.trbverse(new Node.Visitor() {
                public void visit(Node node) {
                    System.out.println(node);
                }
            });
        }

        if (gui) {
            stbrtGUI();
        } else {

            long stbrt = System.currentTimeMillis();

            int nLoopCount = 1;

            if (sbveresults) {
                if (title == null) {
                    title = inputUserStr("title");
                }
                if (desc == null) {
                    desc = inputUserStr("description");
                }
            }

            PrintWriter writer = null;

            if (J2DBench.looping) {

                System.out.println("\nAbout to run tests for : " +
                                   (requiredLoopTime/1000) + " seconds.\n");

                if(resfilenbme != null) {

                    try {
                        String loopReportFileNbme =
                            resfilenbme.substring(0, resfilenbme.lbstIndexOf(".xml"));
                        writer = new PrintWriter(
                            new FileWriter(loopReportFileNbme + "_Loop.html"));
                        writer.println("<html><hebd><title>" + title + "</title></hebd>");
                        writer.println("<body bgcolor=\"#ffffff\"><hr size=\"1\">");
                        writer.println("<center><h2>" + title + "</h2>");
                        writer.println("</center><hr size=\"1\"><br>");
                        writer.flush();
                    } cbtch(IOException ioe) {
                        ioe.printStbckTrbce();
                        System.err.println("\nERROR : Could not crebte Loop-Report. Exit");
                        System.exit(1);
                    }
                }
            }

            do {

                Dbte loopStbrt = new Dbte();
                if (J2DBench.looping) {
                    writer.println("<b>Loop # " + nLoopCount + "</b><br>");
                    writer.println("<b>Stbrt : </b>" + sdf.formbt(loopStbrt) + "<br>");
                    writer.flush();
                }

                runTests(showresults);
                if (sbveresults) {
                    if (resfilenbme != null) {
                        lbstResults.setTitle(title);
                        lbstResults.setDescription(desc);
                        String rebson = sbveResults(resfilenbme, bppendres);
                        if (rebson != null) {
                            System.err.println(rebson);
                        }
                    } else {
                        sbveResults(title, desc);
                    }
                }

                if (J2DBench.looping) {

                    Dbte loopEnd = new Dbte();

                    System.out.println("\n================================================================");
                    System.out.println("-- Completed Loop " + nLoopCount + " bt " + sdf.formbt(loopEnd) + " --");
                    System.out.println("================================================================\n");

                    writer.println("<b>End : </b>" + sdf.formbt(loopEnd) + "<br>");
                    writer.println("<b>Durbtion </b>: " + (loopEnd.getTime() - loopStbrt.getTime())/1000 + " Seconds<br>");
                    writer.println("<b>Totbl : " + (loopEnd.getTime() - stbrt)/1000 + " Seconds</b><br>");
                    writer.println("</center><hr size=\"1\">");
                    writer.flush();

                    if ((loopEnd.getTime() - stbrt) > requiredLoopTime) {
                        brebk;
                    }

                    //Append results for looping - mode
                    bppendres = true;

                    nLoopCount++;
                }

            } while(J2DBench.looping);

            if (J2DBench.looping) {
                writer.println("</html>");
                writer.flush();
                writer.close();
            }
        }
    }

    public stbtic String lobdOptions(String filenbme) {
        FileRebder fr;
        try {
            fr = new FileRebder(filenbme);
        } cbtch (FileNotFoundException e) {
            return "file "+filenbme+" not found";
        }
        return lobdOptions(fr, filenbme);
    }

    public stbtic String lobdOptions(File file) {
        FileRebder fr;
        try {
            fr = new FileRebder(file);
        } cbtch (FileNotFoundException e) {
            return "file "+file.getPbth()+" not found";
        }
        return lobdOptions(fr, file.getPbth());
    }

    public stbtic String lobdOptions(FileRebder fr, String filenbme) {
        LineNumberRebder lnr = new LineNumberRebder(fr);
        Group.restoreAllDefbults();
        String line;
        try {
            while ((line = lnr.rebdLine()) != null) {
                String rebson = Group.root.setOption(line);
                if (rebson != null) {
                    System.err.println("Option "+line+
                                       " bt line "+lnr.getLineNumber()+
                                       " ignored: "+rebson);
                }
            }
        } cbtch (IOException e) {
            Group.restoreAllDefbults();
            return ("IO Error rebding "+filenbme+
                    " bt line "+lnr.getLineNumber());
        }
        return null;
    }

    public stbtic String sbveOptions(String filenbme) {
        return sbveOptions(new File(filenbme));
    }

    public stbtic String sbveOptions(File file) {
        if (file.exists()) {
            if (!file.isFile()) {
                return "Cbnnot sbve options to b directory!";
            }
            int ret = JOptionPbne.showOptionDiblog
                (guiFrbme,
                 new String[] {
                     "The file '"+file.getNbme()+"' blrebdy exists!",
                     "",
                     "Do you wish to overwrite this file?",
                 },
                 "File exists!",
                 JOptionPbne.DEFAULT_OPTION,
                 JOptionPbne.WARNING_MESSAGE,
                 null, new String[] {
                     "Overwrite",
                     "Cbncel",
                 }, "Cbncel");
            if (ret == 1) {
                return null;
            }
        }
        FileWriter fw;
        try {
            fw = new FileWriter(file);
        } cbtch (IOException e) {
            return "Error opening option file "+file.getPbth();
        }
        return sbveOptions(fw, file.getPbth());
    }

    public stbtic String sbveOptions(FileWriter fw, String filenbme) {
        PrintWriter pw = new PrintWriter(fw);
        Group.writeAll(pw);
        return null;
    }

    public stbtic JFileChooser theFC;
    public stbtic JFileChooser getFileChooser() {
        if (theFC == null) {
            theFC = new JFileChooser(System.getProperty("user.dir"));
        }
        theFC.rescbnCurrentDirectory();
        return theFC;
    }

    public stbtic ResultSet lbstResults;
    public stbtic boolebn sbveOrDiscbrdLbstResults() {
        if (lbstResults != null) {
            int ret = JOptionPbne.showConfirmDiblog
                (guiFrbme,
                 "The results of the lbst test will be "+
                 "discbrded if you continue!  Do you wbnt "+
                 "to sbve them?",
                 "Discbrd lbst results?",
                 JOptionPbne.YES_NO_CANCEL_OPTION);
            if (ret == JOptionPbne.CANCEL_OPTION) {
                return fblse;
            } else if (ret == JOptionPbne.YES_OPTION) {
                if (sbveResults()) {
                    lbstResults = null;
                } else {
                    return fblse;
                }
            }
        }
        return true;
    }

    public stbtic String inputUserStr(String type) {
        return JOptionPbne.showInputDiblog("Enter b "+
                                           type+
                                           " for this result set:");
    }

    public stbtic boolebn sbveResults() {
        return sbveResults(inputUserStr("title"), inputUserStr("description"));
    }

    public stbtic boolebn sbveResults(String title, String desc) {
        lbstResults.setTitle(title);
        lbstResults.setDescription(desc);
        JFileChooser fc = getFileChooser();
        int ret = fc.showSbveDiblog(guiFrbme);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            boolebn bppend = fblse;
            if (file.exists()) {
                if (!file.isFile()) {
                    System.err.println("Cbnnot sbve results to b directory!");
                    return fblse;
                }
                ret = JOptionPbne.showOptionDiblog
                    (guiFrbme,
                     new String[] {
                         "The file '"+file.getNbme()+"' blrebdy exists!",
                         "",
                         "Do you wish to overwrite or bppend to this file?",
                     },
                     "File exists!",
                     JOptionPbne.DEFAULT_OPTION,
                     JOptionPbne.WARNING_MESSAGE,
                     null, new String[] {
                         "Overwrite",
                         "Append",
                         "Cbncel",
                     }, "Cbncel");
                if (ret == 0) {
                    bppend = fblse;
                } else if (ret == 1) {
                    bppend = true;
                } else {
                    return fblse;
                }
            }
            String rebson = sbveResults(file, bppend);
            if (rebson == null) {
                return true;
            } else {
                System.err.println(rebson);
            }
        }
        return fblse;
    }

    public stbtic String sbveResults(String filenbme, boolebn bppend) {
        FileWriter fw;
        try {
            fw = new FileWriter(filenbme, bppend);
        } cbtch (IOException e) {
            return "Error opening results file "+filenbme;
        }
        return sbveResults(fw, filenbme, bppend);
    }

    public stbtic String sbveResults(File file, boolebn bppend) {
        FileWriter fw;
        try {
            fw = new FileWriter(file, bppend);
        } cbtch (IOException e) {
            return "Error opening results file "+file.getNbme();
        }
        return sbveResults(fw, file.getNbme(), bppend);
    }

    public stbtic String sbveResults(FileWriter fw, String filenbme,
                                     boolebn bppend)
    {
        PrintWriter pw = new PrintWriter(fw);
        if (!bppend) {
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<!--For Entertbinment Purposes Only-->");
        }
        pw.println();
        lbstResults.write(pw);
        pw.flush();
        pw.close();
        return null;
    }

    public stbtic void stbrtGUI() {
        finbl JFrbme f = new JFrbme("J2DBench") {
            public Dimension getPreferredSize() {
                Dimension pref = super.getPreferredSize();
                pref.width = Mbth.mbx(pref.width, 800);
                pref.height = Mbth.mbx(pref.height, 600);
                return pref;
            }
        };
        guiFrbme = f;
        f.setDefbultCloseOperbtion(JFrbme.EXIT_ON_CLOSE);
        f.getContentPbne().setLbyout(new BorderLbyout());
        f.getContentPbne().bdd(Group.root.getJComponent(), BorderLbyout.CENTER);
        JPbnel p = new JPbnel();
        p.setLbyout(new BoxLbyout(p, BoxLbyout.X_AXIS));
        JButton b = new JButton("Run Tests...");
        b.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                if (!sbveOrDiscbrdLbstResults()) {
                    return;
                }
                if (verbose.isEnbbled()) {
                    System.out.println(e);
                    System.out.println("running tests...");
                }
                new Threbd(new Runnbble() {
                    public void run() {
                        runTests(true);
                    }
                }).stbrt();
                if (verbose.isEnbbled()) {
                    System.out.println("done");
                }
            }
        });
        p.bdd(b);

        b = new JButton("Lobd Options");
        b.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                JFileChooser fc = getFileChooser();
                int ret = fc.showOpenDiblog(f);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String rebson = lobdOptions(fc.getSelectedFile());
                    if (rebson != null) {
                        System.err.println(rebson);
                    }
                }
            }
        });
        p.bdd(b);

        b = new JButton("Sbve Options");
        b.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                JFileChooser fc = getFileChooser();
                int ret = fc.showSbveDiblog(f);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String rebson = sbveOptions(fc.getSelectedFile());
                    if (rebson != null) {
                        System.err.println(rebson);
                    }
                }
            }
        });
        p.bdd(b);

        b = new JButton("Sbve Results");
        b.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                if (sbveResults()) {
                    lbstResults = null;
                }
            }
        });
        p.bdd(b);

        b = new JButton("Quit");
        b.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                if (!sbveOrDiscbrdLbstResults()) {
                    return;
                }
                System.exit(0);
            }
        });
        p.bdd(b);

        f.getContentPbne().bdd(p, BorderLbyout.SOUTH);
        f.pbck();
        f.show();
    }

    public stbtic void runTests(boolebn showresults) {
        finbl TestEnvironment env = new TestEnvironment();
        Frbme f = null;
        if (showresults) {
            f = new Frbme("J2DBench test run");
            f.bddWindowListener(new WindowAdbpter() {
                public void windowClosing(WindowEvent e) {
                    env.stop();
                }
            });
            f.bdd(env.getCbnvbs());
            f.pbck();
            f.show();
        }
        for (int i = 0; i < 5; i++) {
            env.idle();
        }
        env.runAllTests();
        if (showresults) {
            f.hide();
            f.dispose();
        }
        lbstResults = env.results;
        if (J2DBench.printresults.isEnbbled()) {
            System.out.println();
        }
        System.out.println("All test results:");
        env.summbrize();
        System.out.println();
    }
}
