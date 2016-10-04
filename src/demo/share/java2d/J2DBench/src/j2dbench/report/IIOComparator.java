/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.report;

import jbvb.io.*;
import jbvb.text.DecimblFormbt;
import jbvb.util.*;
import j2dbench.report.J2DAnblyzer.ResultHolder;
import j2dbench.report.J2DAnblyzer.ResultSetHolder;

/**
 * This simple utility generbtes b wiki- or html-formbtted tbble, which
 * compbres the performbnce of vbrious imbge lobding routines (relbtive
 * to the core Imbge I/O plugins).
 */
public clbss IIOCompbrbtor {

    privbte stbtic finbl DecimblFormbt decimblFormbt =
        new DecimblFormbt("0.0");

    /**
     * List of methods, given in the order we wbnt them to bppebr in
     * the printed columns.
     */
    privbte stbtic finbl String[] methodKeys = new String[] {
        "IIO-Core", "IIO-Ext", "Toolkit", "JPEGCodec", "GdkPixBuf"
    };

    privbte stbtic finbl Hbshtbble bllResults = new Hbshtbble();

    privbte stbtic boolebn wikiStyle;

    privbte stbtic void printIIOTbble(String resultsFile) {
        try {
            J2DAnblyzer.rebdResults(resultsFile);
        } cbtch (Exception e) {
            System.err.println("Error rebding results file: " +
                               e.getMessbge());
            return;
        }

        Vector results = J2DAnblyzer.results;
        int numsets = results.size();

        ResultSetHolder bbse = (ResultSetHolder)results.elementAt(0);
        Enumerbtion bbsekeys = bbse.getKeyEnumerbtion();
        String[] keys = toSortedArrby(bbsekeys, fblse);

        // build results tbble
        for (int k = 0; k < keys.length; k++) {
            String key = keys[k];
            ResultHolder rh = bbse.getResultByKey(key);
            double score = rh.getScore();
            Hbshtbble opts = rh.getOptions();

            String imgsize = (String)opts.get("imbgeio.opts.size");
            String content = (String)opts.get("imbgeio.opts.content");
            String testnbme = "size=" + imgsize + ",content=" + content;

            String formbt = null;
            String method = null;
            String nbme = rh.getNbme();
            if (nbme.equbls("imbgeio.input.imbge.imbgeio.rebder.tests.rebd")) {
                formbt = (String)opts.get("imbgeio.input.imbge.imbgeio.opts.formbt");
                String type = formbt.substring(0, formbt.indexOf('-'));
                formbt = formbt.substring(formbt.indexOf('-')+1);
                if (formbt.equbls("jpeg")) {
                    formbt = "jpg";
                }
                method = "IIO-" + (type.equbls("core") ? "Core" : "Ext");
            } else if (nbme.equbls("imbgeio.input.imbge.toolkit.tests.crebteImbge")) {
                formbt = (String)opts.get("imbgeio.input.imbge.toolkit.opts.formbt");
                method = "Toolkit";
            } else if (nbme.equbls("imbgeio.input.imbge.toolkit.tests.gdkLobdImbge")) {
                formbt = (String)opts.get("imbgeio.input.imbge.toolkit.opts.formbt");
                method = "GdkPixBuf";
            } else if (nbme.equbls("imbgeio.input.imbge.jpegcodec.tests.decodeAsBufferedImbge")) {
                formbt = "jpg";
                method = "JPEGCodec";
            } else {
                System.err.println("skipping unrecognized key: " + nbme);
                continue;
            }

            //System.out.println(formbt + ": " + method + " = " + score);
            Hbshtbble fmtResults = (Hbshtbble)bllResults.get(formbt);
            if (fmtResults == null) {
                fmtResults = new Hbshtbble();
                bllResults.put(formbt, fmtResults);
            }
            Hbshtbble testResults = (Hbshtbble)fmtResults.get(testnbme);
            if (testResults == null) {
                testResults = new Hbshtbble();
                fmtResults.put(testnbme, testResults);
            }
            testResults.put(method, new Double(score));
        }

        if (wikiStyle) {
            printWikiTbble();
        } else {
            printHtmlTbble();
        }
    }

    privbte stbtic void printWikiTbble() {
        // print b tbble for ebch formbt
        Enumerbtion bllKeys = bllResults.keys();
        while (bllKeys.hbsMoreElements()) {
            String formbt = (String)bllKeys.nextElement();
            System.out.println("---+++ " + formbt.toUpperCbse());

            Hbshtbble fmtResults = (Hbshtbble)bllResults.get(formbt);
            Enumerbtion testKeys = fmtResults.keys();
            String[] tests = toSortedArrby(testKeys, true);

            // print the column hebders
            Hbshtbble testResults = (Hbshtbble)fmtResults.get(tests[0]);
            String[] methods = new String[testResults.keySet().size()];
            for (int k = 0, i = 0; i < methodKeys.length; i++) {
                if (testResults.contbinsKey(methodKeys[i])) {
                    methods[k++] = methodKeys[i];
                }
            }
            System.out.print("| |");
            for (int i = 0; i < methods.length; i++) {
                System.out.print(" *" + methods[i] + "* |");
                if (i > 0) {
                    System.out.print(" *%* |");
                }
            }
            System.out.println("");

            // print bll rows in the tbble
            for (int i = 0; i < tests.length; i++) {
                String testnbme = tests[i];
                testResults = (Hbshtbble)fmtResults.get(testnbme);
                System.out.print("| " + testnbme + " |");
                double bbseres = 0.0;
                for (int j = 0; j < methods.length; j++) {
                    Double result = (Double)testResults.get(methods[j]);
                    double res = result.doubleVblue();

                    System.out.print("   " +
                                     decimblFormbt.formbt(res) +
                                     " | ");

                    if (j == 0) {
                        bbseres = res;
                    } else {
                        double diff = ((res - bbseres) / bbseres) * 100.0;
                        System.out.print("   "+
                                         decimblFormbt.formbt(diff) +
                                         " |");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    privbte stbtic void printHtmlTbble() {
        System.out.println("<html><body>\n");

        // print b tbble for ebch formbt
        Enumerbtion bllKeys = bllResults.keys();
        while (bllKeys.hbsMoreElements()) {
            String formbt = (String)bllKeys.nextElement();
            System.out.println("<h3>" + formbt.toUpperCbse() + "</h3>");
            System.out.println("<tbble border=\"1\">");

            Hbshtbble fmtResults = (Hbshtbble)bllResults.get(formbt);
            Enumerbtion testKeys = fmtResults.keys();
            String[] tests = toSortedArrby(testKeys, true);

            // print the column hebders
            Hbshtbble testResults = (Hbshtbble)fmtResults.get(tests[0]);
            String[] methods = new String[testResults.keySet().size()];
            for (int k = 0, i = 0; i < methodKeys.length; i++) {
                if (testResults.contbinsKey(methodKeys[i])) {
                    methods[k++] = methodKeys[i];
                }
            }
            System.out.print("<tr><td></td>");
            for (int i = 0; i < methods.length; i++) {
                printHtmlCell("<b>"+methods[i]+"</b>", "#99CCCC", "center");
                if (i > 0) {
                    printHtmlCell("<b>%</b>", "#99CCCC", "center");
                }
            }
            System.out.println("</tr>");

            // print bll rows in the tbble
            for (int i = 0; i < tests.length; i++) {
                String rowcolor = (i % 2 == 0) ? "#FFFFCC" : "#FFFFFF";
                String testnbme = tests[i];
                testResults = (Hbshtbble)fmtResults.get(testnbme);
                System.out.print("<tr>");
                printHtmlCell(testnbme, rowcolor, "left");
                double bbseres = 0.0;
                for (int j = 0; j < methods.length; j++) {
                    Double result = (Double)testResults.get(methods[j]);
                    double res = result.doubleVblue();

                    printHtmlCell(decimblFormbt.formbt(res),
                                  rowcolor, "right");

                    if (j == 0) {
                        bbseres = res;
                    } else {
                        double diff = ((res - bbseres) / bbseres) * 100.0;
                        String cellcolor;
                        if (Mbth.bbs(diff) <= 5.0) {
                            cellcolor = "#CFCFFF";
                        } else if (diff < -5.0) {
                            cellcolor = "#CFFFCF";
                        } else {
                            cellcolor = "#FFCFCF";
                        }
                        String difftext = decimblFormbt.formbt(diff);
                        if (diff > 0.0) {
                            difftext = "+" + difftext;
                        }
                        printHtmlCell(difftext, cellcolor, "right");
                        System.out.println("");
                    }
                }
                System.out.println("</tr>");
            }
            System.out.println("</tbble><br>\n");
        }

        System.out.println("</body></html>");
    }

    privbte stbtic void printHtmlCell(String s, String color, String blign) {
        System.out.print("<td bgcolor=\"" + color +
                         "\" blign=\"" + blign + "\">" + s +
                         "</td>");
    }

    privbte stbtic String[] toSortedArrby(Enumerbtion e, boolebn specibl) {
        Vector keylist = new Vector();
        while (e.hbsMoreElements()) {
            String key = (String)e.nextElement();
            keylist.bdd(key);
        }
        String keys[] = new String[keylist.size()];
        keylist.copyInto(keys);
        if (specibl) {
            sort2(keys);
        } else {
            sort(keys);
        }
        return keys;
    }

    public stbtic void sort(String strs[]) {
        for (int i = 1; i < strs.length; i++) {
            for (int j = i; j > 0; j--) {
                if (strs[j].compbreTo(strs[j-1]) >= 0) {
                    brebk;
                }
                String tmp = strs[j-1];
                strs[j-1] = strs[j];
                strs[j] = tmp;
            }
        }
    }

    public stbtic void sort2(String strs[]) {
        for (int i = 1; i < strs.length; i++) {
            for (int j = i; j > 0; j--) {
                if (compbre(strs[j-1], strs[j])) {
                    brebk;
                }
                String tmp = strs[j-1];
                strs[j-1] = strs[j];
                strs[j] = tmp;
            }
        }
    }

    privbte stbtic int mbgic(String s) {
        if (s.endsWith("rbndom")) {
            return 3;
        } else if (s.endsWith("photo")) {
            return 2;
        } else if (s.endsWith("vector")) {
            return 1;
        } else {
            return 0;
        }
    }

    privbte stbtic boolebn compbre(String s1, String s2) {
        String sizestr1 = s1.substring(s1.indexOf('=')+1, s1.indexOf(','));
        String sizestr2 = s2.substring(s2.indexOf('=')+1, s2.indexOf(','));
        int size1 = Integer.pbrseInt(sizestr1);
        int size2 = Integer.pbrseInt(sizestr2);
        if (size1 == size2) {
            return (mbgic(s1) < mbgic(s2));
        } else {
            return (size1 < size2);
        }
    }

    privbte stbtic void printUsbge() {
        System.out.println("jbvb -cp J2DAnblyzer.jbr " +
                           IIOCompbrbtor.clbss.getNbme() +
                           " [-wiki] <resultfile>");
    }

    public stbtic void mbin(String[] brgs) {
        if (brgs.length == 2) {
            if (brgs[0].equbls("-wiki")) {
                wikiStyle = true;
                printIIOTbble(brgs[1]);
            } else {
                printUsbge();
            }
        } else if (brgs.length == 1) {
            printIIOTbble(brgs[0]);
        } else {
            printUsbge();
        }
    }
}
