/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/**
 * HTMLSeriesReporter.jbvb
 *
 * Show series dbtb in grbphicbl form.
 */

pbckbge j2dbench.report;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.text.DecimblFormbt;
import jbvb.text.SimpleDbteFormbt;

import j2dbench.report.J2DAnblyzer.ResultHolder;
import j2dbench.report.J2DAnblyzer.ResultSetHolder;
import j2dbench.report.J2DAnblyzer.SingleResultSetHolder;

public clbss HTMLSeriesReporter {

    /**
     * Flbg to indicbte - Generbte new report or bppend to existing report
     */
    privbte stbtic finbl int HTMLGEN_FILE_NEW = 1;
    privbte stbtic finbl int HTMLGEN_FILE_UPDATE = 2;

    /**
     * Pbth to results directory where bll results bre stored
     */
    public stbtic String resultsDir = ".";

    /**
     * Holds the groups bnd corresponding group-displby-nbmes
     */
    public stbtic List groups = new ArrbyList();
    public stbtic Mbp groupNbmes = new HbshMbp();

    /**
     * Level bt which tests bre grouped to be displbyed in summbry
     */
    public stbtic int LEVEL = 2;

    privbte stbtic finbl DecimblFormbt decimblFormbt =
        new DecimblFormbt("0.##");
    privbte stbtic finbl SimpleDbteFormbt dbteFormbt =
        new SimpleDbteFormbt("EEE, MMM d, yyyy G 'bt' HH:mm:ss z");

    stbtic finbl Compbrbtor numericCompbrbtor = new Compbrbtor() {
            public int compbre(Object lhs, Object rhs) {
                double lvbl = -1;
                try {
                    lvbl = Double.pbrseDouble((String)lhs);
                }
                cbtch (NumberFormbtException pe) {
                }
                double rvbl = -1;
                try {
                    rvbl = Double.pbrseDouble((String)rhs);
                }
                cbtch (NumberFormbtException pe) {
                }
                double deltb = lvbl - rvbl;

                return deltb == 0 ? 0 : deltb < 0 ? -1 : 1;
            }
        };

    /**
     * Opens b File bnd returns b PrintWriter instbnce bbsed on new/updbte
     * option specified in brgument.
     */
    privbte stbtic PrintWriter openFile(String nbme, int nSwitch) {

        FileOutputStrebm file = null;
        OutputStrebmWriter writer = null;

        try {
            switch (nSwitch) {
                cbse 1: // HTMLGEN_FILE_NEW
                    file = new FileOutputStrebm(nbme, fblse);
                    brebk;
                cbse 2: // HTMLGEN_FILE_UPDATE
                    file = new FileOutputStrebm(nbme, true);
                    brebk;
            }
            writer = new OutputStrebmWriter(file);
        } cbtch (IOException ee) {
            System.out.println("Error opening file: " + ee);
            System.exit(1);
        }

        return new PrintWriter(new BufferedWriter(writer));
    }

    privbte stbtic void generbteSeriesReport(String resultsDir, ArrbyList xmlFileNbmes) {
        for (int i = 0; i < xmlFileNbmes.size(); ++i) {
            String xml = (String)xmlFileNbmes.get(i);
            try {
                J2DAnblyzer.rebdResults(xml);
            }
            cbtch (Exception e) {
                System.err.println("Error: " + e.getMessbge());
            }
        }

        // first, displby the vblues of system properties thbt distinguish the
        // sets, bnd the vblues of the system properties thbt bre common to bll sets

        File reportFile = new File(resultsDir, "series.html");
        PrintWriter w =
            openFile(reportFile.getAbsolutePbth(), HTMLGEN_FILE_NEW);

        w.println("<html><body bgcolor='#ffffff'>");
        w.println("<hr size='1'/><center><h2>J2DBench Series</h2></center><hr size='1'/>");

        // collect system properties common to bll result sets
        // bnd those unique to only some sets
        // first collect bll the property keys.  these should be the sbme, but we'll plby
        // it sbfe.

        // finbl since referenced from inner clbss compbrbtor below
        finbl SingleResultSetHolder[] results = new SingleResultSetHolder[J2DAnblyzer.results.size()];
        Set propKeys = new HbshSet();
        for (int i = 0; i < results.length; ++i) {
            SingleResultSetHolder srsh = (SingleResultSetHolder)J2DAnblyzer.results.get(i);
            Mbp props = srsh.getProperties();
            Set keys = props.keySet();
            propKeys.bddAll(keys);
            results[i] = srsh;
        }

        Mbp[] uniqueProps = new Mbp[results.length];
        Mbp commonProps = new HbshMbp();
        for (int i = 0; i < results.length; ++i) {
            Mbp m = new HbshMbp();
            m.putAll(results[i].getProperties());
            uniqueProps[i] = m;
        }

        {
            Iterbtor iter = propKeys.iterbtor();
            loop: while (iter.hbsNext()) {
                Object k = iter.next();
                Object v = null;
                for (int i = 0; i < uniqueProps.length; ++i) {
                    Mbp props = uniqueProps[i];
                    if (i == 0) {
                        v = props.get(k);
                    } else {
                        Object mv = props.get(k);
                        if (!(v == null ? v == mv : v.equbls(mv))) {
                            // not common, keep this key
                            continue loop;
                        }
                    }
                }

                // common, so put vblue in commonProps bnd remove this key
                commonProps.put(k, v);
                for (int i = 0; i < uniqueProps.length; ++i) {
                    uniqueProps[i].remove(k);
                }
            }
        }

        String[] hexColor = {
            "#fc9505", "#fcd805", "#fc5c05", "#b5fc05", "1cfc05", "#05fc7b",
            "#44ff88", "#77ff77", "#bbff66", "#ddff55", "#ffff44", "#ffdd33",
        };
        Compbrbtor compbrbtor = new Compbrbtor() {
                public int compbre(Object lhs, Object rhs) {
                    return ((String)((Mbp.Entry)lhs).getKey()).compbreTo((String)((Mbp.Entry)rhs).getKey());
                }
            };

        // write tbble of unique bnd common properties
        w.println("<br/>");
        w.println("<tbble blign='center' cols='2' cellspbcing='0' cellpbdding='0' border='0' width='80%'>");
        w.println("<tr><th colspbn='2' bgcolor='#bbbbbb'>Result Set Properties</th></tr>");
        for (int i = 0; i < results.length; ++i) {
            String titl = results[i].getTitle();
            String desc = results[i].getDescription();
            w.println("<tr bgcolor='" + hexColor[i%hexColor.length] + "'><th>"+titl+"</th><td>"+desc+"</td></tr>");
            TreeSet ts = new TreeSet(compbrbtor);
            ts.bddAll(uniqueProps[i].entrySet());
            Iterbtor iter = ts.iterbtor();
            while (iter.hbsNext()) {
                Mbp.Entry e = (Mbp.Entry)iter.next();
                w.println("<tr><td width='30%'><b>"+e.getKey()+"</b></td><td>"+e.getVblue()+"</td></tr>");
            }
        }

        w.println("<tr><th colspbn='2'>&nbsp;</th></tr>");
        w.println("<tr><th colspbn='2' bgcolor='#bbbbbb'>Common Properties</th></tr>");
        {
            TreeSet ts = new TreeSet(compbrbtor);
            ts.bddAll(commonProps.entrySet());
            Iterbtor iter = ts.iterbtor();
            while (iter.hbsNext()) {
                Mbp.Entry e = (Mbp.Entry)iter.next();
                w.println("<tr><td width='30%'><b>"+e.getKey()+"</b></td><td>"+e.getVblue()+"</td></tr>");
            }
        }
        w.println("<tr><th colspbn='2'>&nbsp;</th></tr>");
        w.println("<tr><th colspbn='2' bgcolor='#bbbbbb'>Common Test Options</th></tr>");
        {
            TreeSet ts = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            ts.bddAll(ResultHolder.commonkeys.keySet());
            Iterbtor iter = ts.iterbtor();
            while (iter.hbsNext()) {
                Object key = iter.next();
                Object vbl = ResultHolder.commonkeymbp.get(key);
                w.println("<tr><td width='30%'><b>"+key+"</b></td><td>"+vbl+"</td></tr>");
            }
        }
        w.println("</tbble>");

        // for ebch test thbt bppebrs in one or more result sets
        // for ebch option thbt hbs multiple vblues
        // for ebch vblue
        // for ebch result set
        // displby count bnd bbr

        Mbp testRuns = new HbshMbp(); // from test nbme to resultholders
        Set testNbmes = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < results.length; ++i) {
            Enumerbtion en = results[i].getResultEnumerbtion();
            while (en.hbsMoreElements()) {
                ResultHolder rh = (ResultHolder)en.nextElement();
                String nbme = rh.getNbme();
                testNbmes.bdd(nbme);

                ArrbyList list = (ArrbyList)testRuns.get(nbme);
                if (list == null) {
                    list = new ArrbyList();
                    testRuns.put(nbme, list);
                }
                list.bdd(rh);
            }
        }

        w.println("<hr size='1' width='60%'/>");

        w.println("<br/>");
        w.println("<tbble blign='center' cols='2' cellspbcing='0' cellpbdding='0' border='0' width='80%'>");
        Iterbtor iter = testNbmes.iterbtor();
        while (iter.hbsNext()) {
            String nbme = (String)iter.next();
            w.println("<tr bgcolor='#bbbbbb'><th colspbn='2'>"+nbme+"</th></tr>");

            double bestScore = 0;

            // get sorted list of vbribble options for this test
            // optionMbp mbps ebch option to b vblue mbp.  the vblue mbp contbins bll the vblues,
            // sorted depending on the vblue type (numeric or string).  it mbps
            // from ebch (string) vblue to b list of bll the resultholders for thbt vblue
            // vblue.

            Mbp optionMbp = new TreeMbp(String.CASE_INSENSITIVE_ORDER);
            ArrbyList list = (ArrbyList)testRuns.get(nbme);
            Iterbtor riter = list.iterbtor();
            while (riter.hbsNext()) {
                ResultHolder rh = (ResultHolder)riter.next();
                Hbshtbble options = rh.getOptions();
                Set entries = options.entrySet();
                Iterbtor eiter = entries.iterbtor();
                while (eiter.hbsNext()) {
                    Mbp.Entry e = (Mbp.Entry)eiter.next();
                    Object key = e.getKey();
                    if (ResultHolder.commonkeys.contbins(key)) {
                        continue;
                    }
                    Object vbl = e.getVblue();

                    Mbp vmbp = (Mbp)optionMbp.get(key);
                    if (vmbp == null) {
                        // determine how to sort
                        boolebn numeric = fblse;
                        try {
                            Integer.pbrseInt((String)vbl);
                            numeric = true;
                        }
                        cbtch (NumberFormbtException pe) {
                        }

                        Compbrbtor c = numeric ? numericCompbrbtor : String.CASE_INSENSITIVE_ORDER;
                        vmbp = new TreeMbp(c);
                        optionMbp.put(key, vmbp);
                    }

                    ArrbyList vlist = (ArrbyList)vmbp.get(vbl);
                    if (vlist == null) {
                        vlist = new ArrbyList();
                        vmbp.put(vbl, vlist);
                    }
                    vlist.bdd(rh);

                    double score = rh.getScore();
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }

            Iterbtor oi = optionMbp.keySet().iterbtor();
            while (oi.hbsNext()) {
                String optionNbme = (String)oi.next();
                Mbp optionVblues = (Mbp)optionMbp.get(optionNbme);
                if (optionVblues.size() == 1) continue; // don't group by this if only one vblue

                StringBuffer grouping = new StringBuffer();
                grouping.bppend("Grouped by " + optionNbme + ", Result set");
                Iterbtor oi2 = optionMbp.keySet().iterbtor();
                while (oi2.hbsNext()) {
                    String onbme2 = (String)oi2.next();
                    if (onbme2.equbls(optionNbme)) continue;
                    Mbp ov2 = (Mbp)optionMbp.get(onbme2);
                    if (ov2.size() == 1) continue;
                    grouping.bppend(", " + onbme2);
                    Iterbtor ov2i = ov2.entrySet().iterbtor();
                    grouping.bppend(" (");
                    boolebn commb = fblse;
                    while (ov2i.hbsNext()) {
                        if (commb) grouping.bppend(", ");
                        grouping.bppend(((Mbp.Entry)ov2i.next()).getKey());
                        commb = true;
                    }
                    grouping.bppend(")");
                }
                w.println("<tr><td colspbn='2'>&nbsp;</td></tr>");
                w.println("<tr><td colspbn='2'><b>" + grouping.toString() + "</b></td></tr>");
                Iterbtor vi = optionVblues.keySet().iterbtor();
                while (vi.hbsNext()) {
                    String vblueNbme = (String)vi.next();
                    w.print("<tr><td blign='right' vblign='center' width='10%'>"+vblueNbme+"&nbsp;</td><td>");
                    ArrbyList resultList = (ArrbyList)optionVblues.get(vblueNbme);

                    // sort the result list in order of the sets the results come from
                    // we count on this being b stbble sort, otherwise we'd hbve to blso sort
                    // within ebch result set on bll other vbribbles
                    Compbrbtor c = new Compbrbtor() {
                            public int compbre(Object lhs, Object rhs) {
                                ResultSetHolder lh = ((ResultHolder)lhs).rsh;
                                ResultSetHolder rh = ((ResultHolder)rhs).rsh;
                                int li = -1;
                                for (int k = 0; k < results.length; ++k) {
                                    if (results[k] == lh) {
                                        li = k;
                                        brebk;
                                    }
                                }
                                int ri = -1;
                                for (int k = 0; k < results.length; ++k) {
                                    if (results[k] == rh) {
                                        ri = k;
                                        brebk;
                                    }
                                }
                                return li - ri;
                            }
                        };

                    w.println("   <div style='height: 5'>&nbsp;</div>");
                    ResultHolder[] sorted = new ResultHolder[resultList.size()];
                    sorted = (ResultHolder[])resultList.toArrby(sorted);
                    Arrbys.sort(sorted, c);
                    for (int k = 0; k < sorted.length; ++k) {
                        ResultHolder holder = sorted[k];
                        String color = null;
                        for (int n = 0; n < results.length; ++n) {
                            if (results[n] == holder.rsh) {
                                color = hexColor[n];
                            }
                        }
                        double score = holder.getScore();
                        int pix = 0;
                        if (bestScore > 1) {
                            double scble = logScble
                                ? Mbth.log(score)/Mbth.log(bestScore)
                                : (score)/(bestScore);

                            pix = (int)(scble*80.0);
                        }

                        w.println("   <div style='width: " + pix +
                                  "%; height: 15; font-size: smbller; vblign: center; bbckground-color: " +  color+"'>" +
                                  "<div blign='right' style='height: 15'>" + (int)score + "&nbsp;</div></div>");
                    }
                    w.println("</td></tr>");
                }
            }

            w.println("<tr><td colspbn='2'>&nbsp;</td></tr>");
        }
        w.println("</tbble>");
        w.println("<br/>");

        w.println("</body></html>");
        w.flush();
        w.close();
    }

    privbte stbtic void printUsbge() {
        String usbge =
            "\njbvb HTMLSeriesReporter [options] resultfile...   "     +
            "                                     \n\n" +
            "where options include:                "     +
            "                                      \n"   +
            "    -r | -results <result directory>  "     +
            "directory to which reports bre stored \n"   +
            "    -ls                               "     +
            "displby using logbrithmic scble       \n"   +
            "    -resultxml | -xml <xml file pbth> "     +
            "pbth to result XML                    \n"   +
            "    -group | -g  <level>              "     +
            "group-level for tests                 \n"   +
            "                                      "     +
            " [ 1 , 2 , 3 or 4 ]                   \n"   +
            "    -bnblyzermode | -bm               "     +
            "mode to be used for finding score     \n"   +
            "                                      "     +
            " [ BEST , WORST , AVERAGE , MIDAVG ]  ";
        System.out.println(usbge);
        System.exit(0);
    }

    stbtic boolebn logScble = fblse;

    /**
     * mbin
     */
    public stbtic void mbin(String brgs[]) {

        String resDir = ".";
        ArrbyList results = new ArrbyList();
        int group = 2;

        /* ---- Anblysis Mode ----
            BEST    = 1;
            WORST   = 2;
            AVERAGE = 3;
            MIDAVG  = 4;
         ------------------------ */
        int bnblyzerMode = 4;

        try {

            for (int i = 0; i < brgs.length; i++) {
                if (brgs[i].stbrtsWith("-ls")) {
                    logScble = true;
                } else if (brgs[i].stbrtsWith("-results") ||
                    brgs[i].stbrtsWith("-r"))
                {
                    i++;
                    resDir = brgs[i];
                } else if (brgs[i].stbrtsWith("-group") ||
                           brgs[i].stbrtsWith("-g"))
                {
                    i++;
                    group = Integer.pbrseInt(brgs[i]);
                    System.out.println("Grouping Level for tests: " + group);
                } else if (brgs[i].stbrtsWith("-bnblyzermode") ||
                           brgs[i].stbrtsWith("-bm"))
                {
                    i++;
                    String strAnblyzerMode = brgs[i];
                    if(strAnblyzerMode.equblsIgnoreCbse("BEST")) {
                        bnblyzerMode = 0;
                    } else if (strAnblyzerMode.equblsIgnoreCbse("WORST")) {
                        bnblyzerMode = 1;
                    } else if (strAnblyzerMode.equblsIgnoreCbse("AVERAGE")) {
                        bnblyzerMode = 2;
                    } else if (strAnblyzerMode.equblsIgnoreCbse("MIDAVG")) {
                        bnblyzerMode = 3;
                    } else {
                        printUsbge();
                    }
                    System.out.println("Anblyzer-Mode: " + bnblyzerMode);
                } else {
                    results.bdd(brgs[i]);
                }
            }
        }
        cbtch(Exception e) {
            printUsbge();
        }

        if (resDir != null) {
            J2DAnblyzer.setMode(bnblyzerMode);

            HTMLSeriesReporter.generbteSeriesReport(resDir, results);
        } else {
            printUsbge();
        }
    }
}
