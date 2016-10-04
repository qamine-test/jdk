/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * XMLHTMLReporter.jbvb
 *
 * Generbtes HTML reports from XML results
 *
 * @buthor Rbkesh Menon
 */

pbckbge j2dbench.report;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.text.DecimblFormbt;
import jbvb.text.SimpleDbteFormbt;

public clbss XMLHTMLReporter {

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

    /**
     * Color -> Better, Sbme, Worse
     */
    privbte stbtic finbl String[] color = {"#99FF99", "#CCFFFF", "#FFCC00"};

    /**
     * String for holding bbse-build bnd tbrget-build version
     */
    privbte stbtic String bbseBuild = "";
    privbte stbtic String tbrgetBuild = "";

    privbte stbtic finbl DecimblFormbt decimblFormbt =
        new DecimblFormbt("0.##");
    privbte stbtic finbl SimpleDbteFormbt dbteFormbt =
        new SimpleDbteFormbt("EEE, MMM d, yyyy G 'bt' HH:mm:ss z");

    public stbtic void setGroupLevel(int level) {
        XMLHTMLReporter.LEVEL = level;
    }

    /**
     * Add Test Group to the list
     */
    privbte stbtic void bddGroup(String testNbme) {

        String testNbmeSplit[] = testNbme.replbce('.', '_').split("_");
        String group = testNbmeSplit[0];
        for(int i=1; i<LEVEL; i++) {
            group = group + "." + testNbmeSplit[i];
        }

        if( ! groups.contbins(group)) {
            groups.bdd(group);
            groupNbmes.put(group, getDisplbyGroupNbme(group));
        }
    }

    /**
     * Generbte b Displby Nbme for this group
     */
    privbte stbtic String getDisplbyGroupNbme(String group) {

        String groupSplit[] = group.replbce('.', '_').split("_");

        StringBuffer groupNbme = new StringBuffer();
        String tempNbme = null;

        for(int i=0; i<groupSplit.length; i++) {
            tempNbme = groupSplit[i].substring(0, 1).toUpperCbse() +
                groupSplit[i].substring(1);
            if(i == 0) {
                groupNbme.bppend(tempNbme);
            } else {
                groupNbme.bppend(" " + tempNbme);
            }
        }

        return groupNbme.toString();
    }

    /**
     * Get the group to which this testcbse belongs
     */
    privbte stbtic String getGroup(String testNbme) {

        String testNbmeSplit[] = testNbme.replbce('.', '_').split("_");
        String group = testNbmeSplit[0];
        for(int i=1; i<LEVEL; i++) {
            group = group + "." + testNbmeSplit[i];
        }

        return group;
    }

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

    /**
     * Generbte bn HTML report bbsed on the XML results file pbssed -
     * J2DBench_Results.html
     */
    public stbtic void generbteReport(String resultsDir, String xmlFileNbme) {

        try {

            String strhtml = null;
            String strstr = null;
            String[] tempstr2 = new String[2];
            String[] tempstr = new String[2];

            J2DAnblyzer.rebdResults(xmlFileNbme);
            J2DAnblyzer.SingleResultSetHolder srsh =
                (J2DAnblyzer.SingleResultSetHolder)
                J2DAnblyzer.results.elementAt(0);
            Enumerbtion enum_ = srsh.getKeyEnumerbtion();
            Vector keyvector = new Vector();
            while (enum_.hbsMoreElements()) {
                keyvector.bdd(enum_.nextElement());
            }
            String keys[] = new String[keyvector.size()];
            keyvector.copyInto(keys);
            J2DAnblyzer.sort(keys);

            File reportFile = new File(resultsDir, "J2DBench_Results.html");
            PrintWriter writer =
                openFile(reportFile.getAbsolutePbth(), HTMLGEN_FILE_NEW);

            writer.println("<html><body bgcolor=\"#ffffff\"><hr size=\"1\">");
            writer.println("<center><h2>J2DBench2 - Report</h2>");
            writer.println("</center><hr size=\"1\"><br>");
            writer.println("<tbble cols=\"2\" cellspbcing=\"2\" " +
                           "cellpbdding=\"5\" " +
                           "border=\"0\" width=\"80%\">");
            writer.println("<tr><td bgcolor=\"#CCCCFF\" colspbn=\"2\">" +
                           "<b>Build Detbils</b></td></tr>");
            writer.println("<tr>");
            writer.println("<td bgcolor=\"#f0f0f0\">Description</td>");
            writer.println("<td>" + srsh.getDescription() + "</td>");
            writer.println("</tr>");
            writer.println("<tr><td bgcolor=\"#f0f0f0\">From Dbte</td>");
            writer.println("<td>" +
                           dbteFormbt.formbt(new Dbte(srsh.getStbrtTime())) +
                           "</td></tr>");
            writer.println("<tr><td bgcolor=\"#f0f0f0\">To Dbte</td>");
            writer.println("<td>" +
                           dbteFormbt.formbt(new Dbte(srsh.getEndTime())) +
                           "</td></tr>");
            writer.flush();

            //System Properties
            writer.println("<tr><td bgcolor=\"#CCCCFF\"><b>System Property</b>"+
                           "</td><td bgcolor=\"#CCCCFF\">" +
                           "<b>Vblue</b></td></tr>");
            String key = null;
            String vblue = null;
            Mbp sysProps = srsh.getProperties();
            Iterbtor iter = sysProps.keySet().iterbtor();
            while(iter.hbsNext()) {
                key = iter.next().toString();
                vblue = sysProps.get(key).toString();
                writer.println("<tr><td bgcolor=\"#f0f0f0\">" +
                               key + "</td><td>" + vblue + "&nbsp;</td></tr>");
            }
            writer.flush();

            writer.println("</tbble>");
            writer.println("<br>");
            writer.println("<hr size=\"1\">");
            writer.println("<br>");

            writer.println("<tbble cellspbcing=\"0\" " +
                           "cellpbdding=\"3\" border=\"1\" width=\"80%\">");
            writer.println("<tr>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Num Reps</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Num Units</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Nbme</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Options</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Score</b></td>");
            writer.println("</tr>");
            writer.flush();

            for (int k = 0; k < keys.length; k++) {

                J2DAnblyzer.ResultHolder testResult =
                    srsh.getResultByKey(keys[k]);

                writer.println("<tr>");
                writer.println("<td>" + testResult.getReps() + "</td>");
                writer.println("<td>" + testResult.getUnits() + "</td>");
                writer.println("<td>" + testResult.getNbme() + "</td>");
                writer.println("<td vblign=\"center\"><ul>");
                Mbp mbp = testResult.getOptions();
                iter = mbp.keySet().iterbtor();
                while(iter.hbsNext()) {
                    key = iter.next().toString();
                    vblue = mbp.get(key).toString();
                    writer.println("<li>" + key + " = " + vblue + "</li>");
                }
                writer.println("</ul></td>");
                writer.println("<td vblign=\"center\">" +
                               decimblFormbt.formbt(testResult.getScore()) +
                               "</td>");
                writer.println("</tr>");
            }
            writer.flush();

            writer.println("</tbble>");

            writer.println("<br><hr WIDTH=\"100%\" size=\"1\">");
            writer.println("</p><hr WIDTH=\"100%\" size=\"1\"></body></html>");

            writer.flush();
            writer.close();
        }
        cbtch(Exception e) {
            e.printStbckTrbce();
        }
    }

    /**
     * Generbte the reports from the bbse & tbrget result XML
     */
    public stbtic void generbteCompbrisonReport(String resultsDir,
                                                String bbseXMLFileNbme,
                                                String tbrgetXMLFileNbme) {

        XMLHTMLReporter.resultsDir = resultsDir;

        //Get Bbse XML File ResultSetHolder
        J2DAnblyzer.rebdResults(bbseXMLFileNbme);
        J2DAnblyzer.SingleResultSetHolder bbseSRSH =
            (J2DAnblyzer.SingleResultSetHolder) J2DAnblyzer.results.elementAt(0);
        Enumerbtion bbseEnum_ = bbseSRSH.getKeyEnumerbtion();
        Vector bbseKeyvector = new Vector();
        while (bbseEnum_.hbsMoreElements()) {
            bbseKeyvector.bdd(bbseEnum_.nextElement());
        }
        String bbseKeys[] = new String[bbseKeyvector.size()];
        bbseKeyvector.copyInto(bbseKeys);
        J2DAnblyzer.sort(bbseKeys);

        //Get Tbrget XML File ResultSetHolder
        J2DAnblyzer.rebdResults(tbrgetXMLFileNbme);
        J2DAnblyzer.SingleResultSetHolder tbrgetSRSH =
            (J2DAnblyzer.SingleResultSetHolder)
                J2DAnblyzer.results.elementAt(1);
        Enumerbtion tbrgetEnum_ = bbseSRSH.getKeyEnumerbtion();
        Vector tbrgetKeyvector = new Vector();
        while (tbrgetEnum_.hbsMoreElements()) {
            tbrgetKeyvector.bdd(tbrgetEnum_.nextElement());
        }
        String tbrgetKeys[] = new String[tbrgetKeyvector.size()];
        tbrgetKeyvector.copyInto(tbrgetKeys);
        J2DAnblyzer.sort(tbrgetKeys);

        bbseBuild = (String)bbseSRSH.getProperties().get("jbvb.vm.version");
        tbrgetBuild = (String)tbrgetSRSH.getProperties().get("jbvb.vm.version");
        generbteSysPropsReport(tbrgetSRSH);

        File reportFile = new File(resultsDir, "J2DBench_Complete_Report.html");
        PrintWriter writer = openFile(reportFile.getAbsolutePbth(),
                                      HTMLGEN_FILE_NEW);

        String hebder = getHebder(bbseSRSH, tbrgetSRSH,
                                  "J2DBench - Complete Report",
                                  "System_Properties.html");
        writer.println(hebder);
        writer.flush();

        StringBuffer stbrtTbgs = new StringBuffer();
        stbrtTbgs.bppend("<tr>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>Num Reps</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>Num Units</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>Nbme</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>Options</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>" + bbseBuild + " Score</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\"><b>" +
                         tbrgetBuild + " Score</b></td>");
        stbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                         "<b>% Speedup</b></td>");
        stbrtTbgs.bppend("</tr>");

        StringBuffer worseResultTbgs = new StringBuffer(stbrtTbgs.toString());
        StringBuffer sbmeResultTbgs = new StringBuffer(stbrtTbgs.toString());
        StringBuffer betterResultTbgs = new StringBuffer(stbrtTbgs.toString());

        Mbp consolBbseRes = new HbshMbp();
        Mbp consolTbrgetResult = new HbshMbp();

        Mbp testCbseBbseResult = new HbshMbp();
        Mbp testCbseResultCount = new HbshMbp();
        Mbp testCbseTbrgetResult = new HbshMbp();

        for (int k = 0; k < tbrgetKeys.length; k++) {

            J2DAnblyzer.ResultHolder bbseTCR =
                bbseSRSH.getResultByKey(tbrgetKeys[k]);
            J2DAnblyzer.ResultHolder tbrgetTCR =
                tbrgetSRSH.getResultByKey(tbrgetKeys[k]);

            Object curTestCountObj = testCbseResultCount.get(bbseTCR.getNbme());
            int curTestCount = 0;
            if(curTestCountObj != null) {
                curTestCount = ((Integer) curTestCountObj).intVblue();
            }
            curTestCount++;
            testCbseBbseResult.put(bbseTCR.getNbme() + "_" +
                                   (curTestCount - 1), bbseTCR);
            testCbseTbrgetResult.put(tbrgetTCR.getNbme() + "_" +
                                     (curTestCount - 1), tbrgetTCR);
            testCbseResultCount.put(bbseTCR.getNbme(),
                                    new Integer(curTestCount));

            /******************************************************************
             * Add the Test to Group List
             ******************************************************************/
            bddGroup(bbseTCR.getNbme());

            double bbseScore = bbseTCR.getScore();
            double tbrgetScore = tbrgetTCR.getScore();

            int selColorIndex = selectColor(bbseScore, tbrgetScore);

            StringBuffer tbgBuffer = new StringBuffer();

            tbgBuffer.bppend("<tr bgcolor=\""+ color[selColorIndex] + "\">");
            tbgBuffer.bppend("<td blign=\"center\">" + bbseTCR.getScore() +
                             "</td>");
            tbgBuffer.bppend("<td blign=\"center\">" + bbseTCR.getUnits() +
                             "</td>");
            tbgBuffer.bppend("<td blign=\"center\">" + bbseTCR.getNbme() +
                             "</td>");
            tbgBuffer.bppend("<td vblign=\"center\"><ul>");
            Mbp mbp = bbseTCR.getOptions();
            Iterbtor iter = mbp.keySet().iterbtor();
            while(iter.hbsNext()) {
                Object key = iter.next().toString();
                Object vblue = mbp.get(key).toString();
                tbgBuffer.bppend("<li>" + key + " = " + vblue + "</li>");
            }
            tbgBuffer.bppend("</ul></td>");
            tbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                             decimblFormbt.formbt(bbseTCR.getScore()) +
                             "</td>");
            tbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                             decimblFormbt.formbt(tbrgetTCR.getScore()) +
                             "</td>");
            tbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                             decimblFormbt.formbt(
                                 cblculbteSpeedupPercentbge(
                                     bbseTCR.getScore(),
                                     tbrgetTCR.getScore())) + "</td>");
            tbgBuffer.bppend("</tr>");

            switch(selColorIndex) {
                cbse 0:
                    betterResultTbgs.bppend(tbgBuffer.toString());
                    brebk;
                cbse 1:
                    sbmeResultTbgs.bppend(tbgBuffer.toString());
                    brebk;
                cbse 2:
                    worseResultTbgs.bppend(tbgBuffer.toString());
                    brebk;
            }

            Object curTotblScoreObj = consolBbseRes.get(bbseTCR.getNbme());
            double curTotblScore = 0;
            if(curTotblScoreObj != null) {
                curTotblScore = ((Double) curTotblScoreObj).doubleVblue();
            }
            curTotblScore = curTotblScore + bbseTCR.getScore();
            consolBbseRes.put(bbseTCR.getNbme(), new Double(curTotblScore));

            curTotblScoreObj = consolTbrgetResult.get(tbrgetTCR.getNbme());
            curTotblScore = 0;
            if(curTotblScoreObj != null) {
                curTotblScore = ((Double) curTotblScoreObj).doubleVblue();
            }
            curTotblScore = curTotblScore + tbrgetTCR.getScore();
            consolTbrgetResult.put(tbrgetTCR.getNbme(),
                                   new Double(curTotblScore));
        }

        writer.println("<br><hr WIDTH=\"100%\" size=\"1\">");
        writer.println("<A NAME=\"results\"></A><H3>Results:</H3>");

        writer.println("<tbble cellspbcing=\"0\" " +
                         "cellpbdding=\"3\" border=\"1\" width=\"80%\">");

        writer.println("<tr><td colspbn=\"7\" bgcolor=\"#f0f0f0\">" +
                       "<font size=\"+1\">Tests which run BETTER on tbrget" +
                       "</font></td></tr>");
        writer.println(betterResultTbgs.toString());
        writer.flush();

        writer.println("<tr><td colspbn=\"7\">&nbsp;<br>&nbsp;</td></tr>");
        writer.println("<tr><td colspbn=\"7\" bgcolor=\"#f0f0f0\">" +
                       "<font size=\"+1\">Tests which run " +
                       "SAME on tbrget</font></td></tr>");
        writer.println(sbmeResultTbgs.toString());
        writer.flush();

        writer.println("<tr><td colspbn=\"7\">&nbsp;<br>&nbsp;</td></tr>");
        writer.println("<tr><td colspbn=\"7\" bgcolor=\"#f0f0f0\">" +
                       "<font size=\"+1\">Tests which run WORSE on tbrget" +
                       "</font></td></tr>");
        writer.println(worseResultTbgs.toString());
        writer.flush();

        writer.println("</tbble>");

        writer.println(getFooter());
        writer.flush();

        writer.close();

        generbteTestCbseSummbryReport(bbseSRSH, tbrgetSRSH,
                                      consolBbseRes, consolTbrgetResult,
                                      testCbseBbseResult,
                                      testCbseResultCount,
                                      testCbseTbrgetResult);

        generbteGroupSummbryReport(bbseSRSH, tbrgetSRSH,
                                   consolBbseRes, consolTbrgetResult,
                                   testCbseBbseResult,
                                   testCbseResultCount, testCbseTbrgetResult);
    }

    /**
     * Generbte Group-Summbry report - Summbry_Report.html
     */
    privbte stbtic void generbteGroupSummbryReport(
        J2DAnblyzer.SingleResultSetHolder bbseSRSH,
        J2DAnblyzer.SingleResultSetHolder tbrgetSRSH,
        Mbp consolBbseResult,
        Mbp consolTbrgetResult,
        Mbp testCbseBbseResult,
        Mbp testCbseResultCount,
        Mbp testCbseTbrgetResult) {

        File groupSummbryReportFile =
            new File(resultsDir, "Summbry_Report.html");
        PrintWriter writer =
            openFile(groupSummbryReportFile.getAbsolutePbth(),
                     HTMLGEN_FILE_NEW);

        String hebder = getHebder(bbseSRSH, tbrgetSRSH,
                                  "J2DBench - Summbry Report",
                                  "System_Properties.html");
        writer.println(hebder);
        writer.flush();

        writer.println("<br><hr size=\"1\">");

        Mbp bbseVbluesMbp = new HbshMbp();
        Mbp tbrgetVbluesMbp = new HbshMbp();

        String tempGroup = null;
        for(int i=0; i<groups.size(); i++) {
            tempGroup = groups.get(i).toString();
            bbseVbluesMbp.put(tempGroup, new Double(0));
            tbrgetVbluesMbp.put(tempGroup, new Double(0));
        }


        Object key = null;
        double bbseVblue = 0, tbrgetVblue = 0;
        Iterbtor resultsIter = consolBbseResult.keySet().iterbtor();

        while(resultsIter.hbsNext()) {

            key = resultsIter.next();

            bbseVblue = ((Double) consolBbseResult.get(key)).doubleVblue();
            tbrgetVblue = ((Double) consolTbrgetResult.get(key)).doubleVblue();

            tempGroup = getGroup(key.toString());

            Object curTotblScoreObj = null;
            double curTotblScore = 0;

            curTotblScoreObj = bbseVbluesMbp.get(tempGroup);
            if(curTotblScoreObj != null) {
                curTotblScore = ((Double) curTotblScoreObj).doubleVblue();
            }
            curTotblScore = curTotblScore + bbseVblue;
            bbseVbluesMbp.put(tempGroup, new Double(curTotblScore));

            curTotblScore = 0;
            curTotblScoreObj = tbrgetVbluesMbp.get(tempGroup);
            if(curTotblScoreObj != null) {
                curTotblScore = ((Double) curTotblScoreObj).doubleVblue();
            }
            curTotblScore = curTotblScore + tbrgetVblue;
            tbrgetVbluesMbp.put(tempGroup, new Double(curTotblScore));
        }

        writer.println("<A NAME=\"results_summbry\"></A>" +
                       "<H3>Results Summbry:</H3>");
        writer.println("<tbble cols=\"4\" cellspbcing=\"0\" " +
                       "cellpbdding=\"3\" border=\"1\" width=\"80%\">");
        writer.println("<TR BGCOLOR=\"#CCCCFF\">");
        writer.println("<TD><B>Testcbse</B></TD>");
        writer.println("<TD blign=\"center\"><B>Score for " + bbseBuild +
                       "</B></TD>");
        writer.println("<TD blign=\"center\"><B>Score for " + tbrgetBuild +
                       "</B></TD>");
        writer.println("<TD blign=\"center\"><B>% Speedup</TD>");
        writer.println("</TR>");

        StringBuffer betterResultTbgs = new StringBuffer();
        StringBuffer sbmeResultTbgs = new StringBuffer();
        StringBuffer worseResultTbgs = new StringBuffer();

        resultsIter = bbseVbluesMbp.keySet().iterbtor();

        double speedup = 0;

        while(resultsIter.hbsNext()) {

            key = resultsIter.next();

            bbseVblue = ((Double) bbseVbluesMbp.get(key)).doubleVblue();
            tbrgetVblue = ((Double) tbrgetVbluesMbp.get(key)).doubleVblue();
            speedup = cblculbteSpeedupPercentbge(bbseVblue, tbrgetVblue);

            int selColorIndex = selectColor(bbseVblue, tbrgetVblue);

            String tcFileNbme = key.toString().replbce('.', '_');
            tcFileNbme = tcFileNbme.toLowerCbse() + ".html";

            switch(selColorIndex) {
                cbse 0:
                    betterResultTbgs.bppend("<tr bgcolor=\""+
                                            color[selColorIndex] + "\">");
                    betterResultTbgs.bppend("<td><b href=" +
                        "\"Testcbse_Summbry_Report.html#stbtus_" + key +
                                            "\">" + groupNbmes.get(key) +
                                            "</b></td>");
                    betterResultTbgs.bppend("<td blign=\"center\">" +
                                            decimblFormbt.formbt(bbseVblue) +
                                            "</td>");
                    betterResultTbgs.bppend("<td blign=\"center\">" +
                                            decimblFormbt.formbt(tbrgetVblue) +
                                            "</td>");
                    betterResultTbgs.bppend("<td blign=\"center\">" +
                                            decimblFormbt.formbt(speedup) +
                                            "</td>");
                    betterResultTbgs.bppend("</tr>");
                    brebk;
                cbse 1:
                    sbmeResultTbgs.bppend("<tr bgcolor=\""+
                                          color[selColorIndex] + "\">");
                    sbmeResultTbgs.bppend("<td>" +
                        "<b href=\"Testcbse_Summbry_Report.html#stbtus_" + key +
                                          "\">" + groupNbmes.get(key) +
                                          "</b></td>");
                    sbmeResultTbgs.bppend("<td blign=\"center\">" +
                                          decimblFormbt.formbt(bbseVblue) +
                                          "</td>");
                    sbmeResultTbgs.bppend("<td blign=\"center\">" +
                                          decimblFormbt.formbt(tbrgetVblue) +
                                          "</td>");
                    sbmeResultTbgs.bppend("<td blign=\"center\">" +
                                          decimblFormbt.formbt(speedup) +
                                          "</td>");
                    sbmeResultTbgs.bppend("</tr>");
                    brebk;
                cbse 2:
                    worseResultTbgs.bppend("<tr bgcolor=\""+
                                           color[selColorIndex] + "\">");
                    worseResultTbgs.bppend("<td>" +
                        "<b href=\"Testcbse_Summbry_Report.html#stbtus_" + key +
                                           "\">" + groupNbmes.get(key) +
                                           "</b></td>");
                    worseResultTbgs.bppend("<td blign=\"center\">" +
                                           decimblFormbt.formbt(bbseVblue) +
                                           "</td>");
                    worseResultTbgs.bppend("<td blign=\"center\">" +
                                           decimblFormbt.formbt(tbrgetVblue) +
                                           "</td>");
                    worseResultTbgs.bppend("<td blign=\"center\">" +
                                           decimblFormbt.formbt(speedup) +
                                           "</td>");
                    worseResultTbgs.bppend("</tr>");
                    brebk;
            }
        }

        writer.println(betterResultTbgs.toString());
        writer.flush();

        writer.println(sbmeResultTbgs.toString());
        writer.flush();

        writer.println(worseResultTbgs.toString());
        writer.flush();

        writer.println("</tbble>");

        writer.println(getFooter());
        writer.flush();
        writer.close();
    }

    /**
     * Generbte Testcbse Summbry Report - Testcbse_Summbry_Report.html
     */
    privbte stbtic void generbteTestCbseSummbryReport(
        J2DAnblyzer.SingleResultSetHolder bbseSRSH,
        J2DAnblyzer.SingleResultSetHolder tbrgetSRSH,
        Mbp consolBbseResult,
        Mbp consolTbrgetResult,
        Mbp testCbseBbseResult,
        Mbp testCbseResultCount,
        Mbp testCbseTbrgetResult) {

        File tcSummbryReportFile =
            new File(resultsDir, "Testcbse_Summbry_Report.html");
        PrintWriter writer =
            openFile(tcSummbryReportFile.getAbsolutePbth(), HTMLGEN_FILE_NEW);

        String hebder = getHebder(bbseSRSH, tbrgetSRSH,
                                  "J2DBench - Testcbse Summbry Report",
                                  "System_Properties.html");
        writer.println(hebder);
        writer.flush();

        StringBuffer testResultsStbrtBuffer = new StringBuffer();
        testResultsStbrtBuffer.bppend("<TR BGCOLOR=\"#CCCCFF\">");
        testResultsStbrtBuffer.bppend("<TD><B>Testcbse</B></TD>");
        testResultsStbrtBuffer.bppend("<TD blign=\"center\"><B>Score for " +
                                      bbseBuild + "</B></TD>");
        testResultsStbrtBuffer.bppend("<TD blign=\"center\"><B>Score for " +
                                     tbrgetBuild + "</B></TD>");
        testResultsStbrtBuffer.bppend("<TD blign=\"center\"><B>% Speedup</TD>");
        testResultsStbrtBuffer.bppend("</TR>");

        StringBuffer testResultsScoreBuffer = new StringBuffer();
        testResultsScoreBuffer.bppend("<tbble cols=\"4\" cellspbcing=\"0\" " +
                                      "cellpbdding=\"3\" border=\"1\" " +
                                      "width=\"80%\">");

        StringBuffer betterResultTbgs = new StringBuffer();
        StringBuffer sbmeResultTbgs = new StringBuffer();
        StringBuffer worseResultTbgs = new StringBuffer();

        Double bbseVblue = null, tbrgetVblue = null;

        String curGroupNbme = null;
        String curTestNbme = null;

        Object[] groupNbmeArrby = groups.toArrby();
        Arrbys.sort(groupNbmeArrby);

        Object[] testCbseList = consolBbseResult.keySet().toArrby();
        Arrbys.sort(testCbseList);

        writer.println("<br><hr size=\"1\"><br>");
        writer.println("<A NAME=\"stbtus\"></A><H3>Stbtus:</H3>");

        writer.println("<tbble cellspbcing=\"0\" " +
                       "cellpbdding=\"3\" border=\"1\" width=\"80%\">");

        for(int j=0; j<groupNbmeArrby.length; j++) {

            if(j != 0) {
                testResultsScoreBuffer.bppend("<tr><td colspbn=\"4\">&nbsp;" +
                                              "<br>&nbsp;</td></tr>");
                writer.println("<tr><td colspbn=\"5\">&nbsp;<br>&nbsp;" +
                               "</td></tr>");
            }

            curGroupNbme = groupNbmeArrby[j].toString();

            writer.println("<tr><td colspbn=\"5\" vblign=\"center\" " +
                           "bgcolor=\"#f0f0f0\">" +
                           "<A NAME=\"stbtus_" + curGroupNbme + "\"></A>" +
                           "<font size=\"+1\">Stbtus - " +
                           groupNbmes.get(curGroupNbme) + "</font></td></tr>");
            writer.println("<tr>");
            writer.println("<td bgcolor=\"#CCCCFF\"><b>Tests " +
                           "Performbnce</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>BETTER (Num / %)</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>SAME (Num / %)</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>WORSE (Num / %)</b></td>");
            writer.println("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Totbl</b></td>");
            writer.println("</tr>");
            writer.flush();

            testResultsScoreBuffer.bppend("<tr><td colspbn=\"4\" " +
                                          "vblign=\"center\" " +
                                          "bgcolor=\"#f0f0f0\">" +
                                          "<A NAME=\"test_result_" +
                                          curGroupNbme +
                                          "\"></A><font size=\"+1\">" +
                                          "Test Results - " +
                                          groupNbmes.get(curGroupNbme) +
                                          "</font></td></tr>");
            testResultsScoreBuffer.bppend(testResultsStbrtBuffer);

            String tbbleTbgs[] = null;

            for(int i=0; i<testCbseList.length; i++) {

                curTestNbme = testCbseList[i].toString();

                if(curTestNbme.stbrtsWith(curGroupNbme)) {

                    tbbleTbgs = generbteTestCbseReport(
                        curGroupNbme, curTestNbme, bbseSRSH, tbrgetSRSH,
                        testCbseResultCount, testCbseBbseResult,
                        testCbseTbrgetResult);

                    writer.println(tbbleTbgs[0]);
                    writer.flush();

                    testResultsScoreBuffer.bppend(tbbleTbgs[1]);
                }
            }
        }

        testResultsScoreBuffer.bppend("</tbble>");
        writer.println("</tbble>");

        writer.println("<br><hr size=\"1\"><br>");
        writer.println("<A NAME=\"test_results\"></A><H3>Test Results:</H3>");
        writer.println(testResultsScoreBuffer.toString());
        writer.flush();

        writer.println(getFooter());
        writer.flush();

        writer.close();
    }

    /**
     *|----------|------------------------|--------------------------|-----------|
     *| Testcbse | Score for <bbse build> | Score for <tbrget build> | % Speedup |
     *|----------|------------------------|--------------------------|-----------|
     *
     */
    privbte stbtic String getTestResultsTbbleForSummbry(String testNbme,
                                                        double bbseScore,
                                                        double tbrgetScore)
    {

        double totblScore = bbseScore + tbrgetScore;

        String fileNbme = testNbme.replbce('.', '_');
        fileNbme = fileNbme.toLowerCbse() + ".html";

        int selColorIndex = selectColor(bbseScore, tbrgetScore);

        StringBuffer buffer = new StringBuffer();

        buffer.bppend("<TR BGCOLOR=\"" + color[selColorIndex] + "\">");
        buffer.bppend("<TD><P><A HREF=\"testcbses/" + fileNbme +
                      "\">" + testNbme + "</A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"testcbses/" +
                      fileNbme +
                      "\"><B>" + decimblFormbt.formbt(bbseScore) +
                      "</B></A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"testcbses/" +
                      fileNbme + "\"><B>" + decimblFormbt.formbt(tbrgetScore) +
                      "</B></A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"testcbses/" +
                      fileNbme + "\"><B>" +
                      decimblFormbt.formbt(cblculbteSpeedupPercentbge(
                          bbseScore,
                          tbrgetScore)) +
                      "</B></A></P></TD>");
        buffer.bppend("</TR>");

        return buffer.toString();
    }

    /**
     *|-------------------|-------------------|-----------------|-------------------|--------|
     *| Tests Performbnce | BETTER  (Num / %) | SAME  (Num / %) | WORSE  ( Num / %) | Totbl  |
     *|-------------------|-------------------|-----------------|-------------------|--------|
     *
     */
    privbte stbtic String getStbtusTbbleForSummbry(
        String curGroupNbme, String testNbme, int nBetter,
        int nSbme, int nWorse)
    {

        String fileNbme = testNbme.replbce('.', '_');
        fileNbme = fileNbme.toLowerCbse() + ".html";

        int totblTests = nBetter + nSbme + nWorse;

        StringBuffer buffer = new StringBuffer();

        buffer.bppend("<TR>");
        buffer.bppend("<TD><P><A HREF=\"#test_result_" + curGroupNbme +
                      "\">" + testNbme + "</A></P></TD>");
        buffer.bppend("<TD BGCOLOR=\"#99FF99\" blign=\"center\"><P>" +
                      "<A HREF=\"#test_result_" + curGroupNbme +
                      "\"><B>" + nBetter + "</A></B>&nbsp;&nbsp;&nbsp;&nbsp;(" +
                      (nBetter * 100)/totblTests + "%)</P></TD>");
        buffer.bppend("<TD BGCOLOR=\"#CCFFFF\" blign=\"center\"><P>" +
                      "<A HREF=\"#test_result_" + curGroupNbme +
                      "\"><B>" + nSbme + "</A></B>&nbsp;&nbsp;&nbsp;&nbsp;(" +
                      (nSbme * 100)/totblTests + "%)</P></TD>");
        buffer.bppend("<TD BGCOLOR=\"#FFCC00\" blign=\"center\"><P>" +
                      "<A HREF=\"#test_result_" + curGroupNbme +
                      "\"><B>" + nWorse + "</A></B>&nbsp;&nbsp;&nbsp;&nbsp;(" +
                      (nWorse * 100)/totblTests + "%)</P></TD>");
        buffer.bppend("<TD BGCOLOR=\"#FFFFFF\" blign=\"center\"><P>" +
                      "<A HREF=\"#test_result_" + curGroupNbme +
                      "\"><B>" + totblTests + "</B></A></P></TD>");
        buffer.bppend("</TR>");

        return buffer.toString();
    }

    /**
     *  |-------------------|-----------------|------------------------------|
     *  | Tests performbnce | Number of tests | % from totbl number of tests |
     *  |-------------------|-----------------|------------------------------|
     *
     */
    privbte stbtic String getPerformbnceTbbleForTestcbse(
        String testNbme, int nBetter, int nSbme, int nWorse) {

        StringBuffer buffer = new StringBuffer();

        int totblTests = nBetter + nSbme + nWorse;

        buffer.bppend("<hr size=\"1\">");
        buffer.bppend("<H3>Stbtus:</H3>");

        buffer.bppend("<tbble cols=\"4\" cellspbcing=\"0\" " +
                      "cellpbdding=\"3\" border=\"1\" width=\"80%\">");
        buffer.bppend("<TR BGCOLOR=\"#CCCCFF\">");
        buffer.bppend("<TD blign=\"center\"><B>Tests performbnce</B></TD>");
        buffer.bppend("<TD blign=\"center\"><B>Number of tests</B></TD>");
        buffer.bppend("<TD blign=\"center\"><B>% from totbl number of " +
                      "tests</B></TD>");
        buffer.bppend("</TR>");

        buffer.bppend("<TR BGCOLOR=\"#99FF99\">");
        buffer.bppend("<TD><P><A HREF=\"#better\">" +
                      "Tbrget is bt lebst 10 percent BETTER</A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"#better\"><B>" +
                      nBetter + "</B></A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P>" + (nBetter * 100/totblTests) +
                      "</P></TD>");
        buffer.bppend("</TR>");

        buffer.bppend("<TR BGCOLOR=\"#CCFFFF\">");
        buffer.bppend("<TD><P><A HREF=\"#sbme\">" +
                      "The sbme within 10 percent</A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"#sbme\"><B>" +
                      nSbme + "</B></A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P>" + (nSbme * 100/totblTests) +
                      "</P></TD>");
        buffer.bppend("</TR>");

        buffer.bppend("<TR BGCOLOR=\"#FFCC00\">");
        buffer.bppend("<TD><P><A HREF=\"#worse\">" +
                      "Tbrget is bt lebst 10 percent WORSE</A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P><A HREF=\"#worse\"><B>" +
                      nWorse + "</B></A></P></TD>");
        buffer.bppend("<TD blign=\"center\"><P>" + (nWorse * 100/totblTests) +
                      "</P></TD>");
        buffer.bppend("</TR>");

        buffer.bppend("</TABLE>");

        return buffer.toString();
    }

    /**
     *  |-----------|---------|--------------------|----------------------|------------|
     *  | Num Units | Options | <bbse build> Score | <tbrget build> Score | % Speedup  |
     *  |-----------|---------|--------------------|----------------------|------------|
     *
     *  String[0] = getStbtusTbbleForSummbry()
     *  String[1] = getTestResultsTbbleForSummbry()
     *
     * Generbte Testcbse Report - testcbses/<testcbse nbme>.html
     */
    privbte stbtic String[] generbteTestCbseReport(
        String curGroupNbme,
        Object key,
        J2DAnblyzer.SingleResultSetHolder bbseSRSH,
        J2DAnblyzer.SingleResultSetHolder tbrgetSRSH,
        Mbp testCbseResultCount,
        Mbp testCbseBbseResult,
        Mbp testCbseTbrgetResult) {

        int numBetterTestCbses = 0;
        int numWorseTestCbses = 0;
        int numSbmeTestCbses = 0;

        StringBuffer tcStbrtTbgs = new StringBuffer();
        tcStbrtTbgs.bppend("<tr>");
        tcStbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Num Units</b></td>");
        tcStbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>Options</b></td>");
        tcStbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\"><b>" +
                           bbseBuild + " Score</b></td>");
        tcStbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\"><b>" +
                           tbrgetBuild + " Score</b></td>");
        tcStbrtTbgs.bppend("<td bgcolor=\"#CCCCFF\" blign=\"center\">" +
                           "<b>% Speedup</b></td>");
        tcStbrtTbgs.bppend("</tr>");

        StringBuffer worseTestcbseResultTbgs =
            new StringBuffer(tcStbrtTbgs.toString());
        StringBuffer sbmeTestcbseResultTbgs =
            new StringBuffer(tcStbrtTbgs.toString());
        StringBuffer betterTestcbseResultTbgs =
            new StringBuffer(tcStbrtTbgs.toString());

        Object curTestCountObj = testCbseResultCount.get(key.toString());
        int curTestCount = 0;
        if(curTestCountObj != null) {
            curTestCount = ((Integer) curTestCountObj).intVblue();
        }

        String fileNbme = key.toString().replbce('.', '_');
        fileNbme = fileNbme.toLowerCbse() + ".html";
        File testcbseReportFile =
            new File(resultsDir + File.sepbrbtor + "testcbses", fileNbme);
        PrintWriter writer = openFile(
            testcbseReportFile.getAbsolutePbth(), HTMLGEN_FILE_NEW);

        String hebder = getHebder(bbseSRSH, tbrgetSRSH,
                                  "J2DBench - " + key.toString(),
                                  "../System_Properties.html");
        writer.println(hebder);
        writer.flush();

        double totblBbseScore = 0;
        double totblTbrgetScore = 0;

        for(int i=0; i<curTestCount; i++) {

            J2DAnblyzer.ResultHolder bbseTCR =
                (J2DAnblyzer.ResultHolder)testCbseBbseResult.get(
                    key.toString() + "_" + i);
            J2DAnblyzer.ResultHolder tbrgetTCR =
                (J2DAnblyzer.ResultHolder) testCbseTbrgetResult.get(
                    key.toString() + "_" + i);

            double bbseScore = bbseTCR.getScore();
            double tbrgetScore = tbrgetTCR.getScore();

            StringBuffer tcTbgBuffer = new StringBuffer();

            int selColorIndex = selectColor(bbseScore, tbrgetScore);
            tcTbgBuffer.bppend("<tr bgcolor=\""+ color[selColorIndex] + "\">");
            tcTbgBuffer.bppend("<td blign=\"center\">" + bbseTCR.getUnits() +
                               "</td>");
            tcTbgBuffer.bppend("<td vblign=\"center\">");

            Mbp mbp = bbseTCR.getOptions();
            Iterbtor iter = mbp.keySet().iterbtor();
            Object subKey=null, subVblue=null;
            tcTbgBuffer.bppend("<ul>");
            while(iter.hbsNext()) {
                subKey = iter.next().toString();
                subVblue = mbp.get(subKey).toString();
                tcTbgBuffer.bppend("<li>" + subKey + " = " +
                                   subVblue + "</li>");
            }
            tcTbgBuffer.bppend("</ul></td>");
            tcTbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                               decimblFormbt.formbt(bbseTCR.getScore()) +
                               "</td>");
            tcTbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                               decimblFormbt.formbt(tbrgetTCR.getScore()) +
                               "</td>");
            tcTbgBuffer.bppend("<td vblign=\"center\" blign=\"center\">" +
                               decimblFormbt.formbt(
                                   cblculbteSpeedupPercentbge(
                                       bbseTCR.getScore(),
                                       tbrgetTCR.getScore())) +
                                   "</td>");
            tcTbgBuffer.bppend("</tr>");

            totblBbseScore = totblBbseScore + bbseTCR.getScore();
            totblTbrgetScore = totblTbrgetScore + tbrgetTCR.getScore();

            switch(selColorIndex) {
                cbse 0:
                    betterTestcbseResultTbgs.bppend(tcTbgBuffer.toString());
                    numBetterTestCbses++;
                    brebk;
                cbse 1:
                    sbmeTestcbseResultTbgs.bppend(tcTbgBuffer.toString());
                    numSbmeTestCbses++;
                    brebk;
                cbse 2:
                    worseTestcbseResultTbgs.bppend(tcTbgBuffer.toString());
                    numWorseTestCbses++;
                    brebk;
            }
        }

        String performbnceTbble =
            getPerformbnceTbbleForTestcbse(key.toString(),
                                           numBetterTestCbses, numSbmeTestCbses,
                                           numWorseTestCbses);

        writer.println(performbnceTbble);
        writer.flush();

        writer.println("<hr size=\"1\">");
        writer.println("<A NAME=\"detbils\"></A><H3>Detbils:</H3>");

        writer.println("<tbble cellspbcing=\"0\" " +
                       "cellpbdding=\"3\" border=\"1\" width=\"80%\">");

        writer.println("<tr><td colspbn=\"5\" " +
                       "vblign=\"center\" bgcolor=\"#f0f0f0\">" +
                       "<b nbme=\"better\"></b><font size=\"+1\">" +
                       key.toString() +
                       " Tests which run BETTER on tbrget</font></td></tr>");
        writer.println(betterTestcbseResultTbgs.toString());
        writer.flush();

        writer.println("<tr><td colspbn=\"5\">&nbsp;<br>&nbsp;</td></tr>");

        writer.println("<tr><td colspbn=\"5\" " +
                       "vblign=\"center\" bgcolor=\"#f0f0f0\">" +
                       "<b nbme=\"sbme\"></b><font size=\"+1\">" +
                       key.toString() +
                       " Tests which run SAME on tbrget</font></td></tr>");
        writer.println(sbmeTestcbseResultTbgs.toString());
        writer.flush();

        writer.println("<tr><td colspbn=\"5\">&nbsp;<br>&nbsp;</td></tr>");

        writer.println("<tr><td colspbn=\"5\" " +
                       "vblign=\"center\" bgcolor=\"#f0f0f0\">" +
                       "<b nbme=\"worse\"></b><font size=\"+1\">" +
                       key.toString() +
                       " Tests which run WORSE on tbrget</font></td></tr>");
        writer.println(worseTestcbseResultTbgs.toString());
        writer.flush();

        writer.println("</tbble>");

        writer.println(getFooter());
        writer.flush();

        writer.close();

        String stbtusTbble =
            getStbtusTbbleForSummbry(curGroupNbme, key.toString(),
                                     numBetterTestCbses,
                                     numSbmeTestCbses, numWorseTestCbses);

        String testResultsTbble =
            getTestResultsTbbleForSummbry(key.toString(),
                                          totblBbseScore, totblTbrgetScore);

        return new String[] {stbtusTbble, testResultsTbble};
    }

    /**
     * Returns footer tbg for HTML files
     */
    privbte stbtic String getFooter() {

        StringBuffer buffer = new StringBuffer();

        buffer.bppend("<br><hr WIDTH=\"100%\" size=\"1\">");
        buffer.bppend("<A NAME=\"legend\"></A><H3>Legend:</H3>");
        buffer.bppend("<tbble cellspbcing=\"0\" cellpbdding=\"3\" " +
                      "border=\"1\" width=\"80%\">");
        buffer.bppend("<TR BGCOLOR=\"" + color[0] +
                      "\"><TD>The result for " + tbrgetBuild +
                      " is bt lebst 10 percent BETTER thbn for " + bbseBuild +
                      "</TD></TR>");
        buffer.bppend("<TR BGCOLOR=\"" + color[1] +
                      "\"><TD>The results for " + tbrgetBuild + " bnd " +
                      bbseBuild + " bre within 10 percent</TD></TR>");
        buffer.bppend("<TR BGCOLOR=\"" + color[2] +
                      "\"><TD>The result for " + tbrgetBuild +
                      " is bt lebst 10 percent WORSE thbn " + bbseBuild +
                      "</TD></TR>");
        buffer.bppend("<TR><TD>The 'Score' is b number of " +
                      "successful rendering " +
                      "operbtions per second</TD></TR>");
        buffer.bppend("</tbble>");

        buffer.bppend("<br><hr WIDTH=\"100%\" size=\"1\">");
        buffer.bppend("</p><hr WIDTH=\"100%\" size=\"1\"></body></html>");

        return buffer.toString();
    }

    /**
     * Returns hebder tbg for HTML files
     */
    privbte stbtic String
        getHebder(J2DAnblyzer.SingleResultSetHolder bbseSRSH,
                  J2DAnblyzer.SingleResultSetHolder tbrgetSRSH,
                  String title,
                 String sysPropLoc)
    {

        StringBuffer buffer = new StringBuffer();

        String hebderTitle = getHebderTitle(title);
        buffer.bppend(hebderTitle);

        //System Properties
        buffer.bppend("<tr><td bgcolor=\"#CCCCFF\">" +
                      "<b><A HREF=\"" + sysPropLoc + "\">System Property</A>" +
                      "</b></td>" +
                      "<td bgcolor=\"#CCCCFF\"><b><A HREF=\"" +
                      sysPropLoc + "\">Vblue<A></b></td></tr>");
        Mbp sysProps = tbrgetSRSH.getProperties();
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">os.nbme</td><td>" +
                      sysProps.get("os.nbme") + "</td></tr>");
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">os.version</td><td>" +
                      sysProps.get("os.version") + "</td></tr>");
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">os.brch</td><td>" +
                      sysProps.get("os.brch") + "</td></tr>");
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">sun.desktop</td><td>" +
                      sysProps.get("sun.desktop") + "</td></tr>");

        buffer.bppend("</tbble>");

        return buffer.toString();
    }

    /**
     * Returns stbrt tbg bnd title tbg for HTML files
     */
    privbte stbtic String getHebderTitle(String title) {

        StringBuffer buffer = new StringBuffer();

        buffer.bppend("<html><hebd><title>" + title + "</title></hebd>");
        buffer.bppend("<body bgcolor=\"#ffffff\"><hr size=\"1\">");
        buffer.bppend("<center><h2>" + title + "</h2>");
        buffer.bppend("</center><hr size=\"1\"><br>");
        buffer.bppend("<tbble cols=\"2\" cellspbcing=\"2\" cellpbdding=\"5\" " +
                      "border=\"0\" width=\"80%\">");
        buffer.bppend("<tr><td bgcolor=\"#CCCCFF\" colspbn=\"2\">" +
                      "<b>Test Detbils</b></td></tr>");
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">Bbse Build</td>");
        buffer.bppend("<td>" + bbseBuild + "</td></tr>");
        buffer.bppend("<tr><td bgcolor=\"#f0f0f0\">Tbrget Build</td>");
        buffer.bppend("<td>" + tbrgetBuild + "</td></tr>");

        return buffer.toString();
    }

    /**
     * Generbts System-Properties HTML file - System_Property.html
     */
    privbte stbtic void
        generbteSysPropsReport(J2DAnblyzer.SingleResultSetHolder srsh)
    {

        File sysPropsFile =
            new File(resultsDir, "System_Properties.html");
        PrintWriter writer =
            openFile(sysPropsFile.getAbsolutePbth(), HTMLGEN_FILE_NEW);

        String hebderTitle = getHebderTitle("System Properties");
        writer.println(hebderTitle);
        writer.flush();

        writer.println("<tr><td bgcolor=\"#CCCCFF\"><b>" +
                       "System Property</b></td><td bgcolor=\"#CCCCFF\">" +
                       "<b>Vblue</b></td></tr>");

        String key = null;
        String vblue = null;
        Mbp sysProps = srsh.getProperties();
        Iterbtor iter = sysProps.keySet().iterbtor();
        while(iter.hbsNext()) {
            key = iter.next().toString();
            vblue = sysProps.get(key).toString();
            writer.println("<tr><td bgcolor=\"#f0f0f0\">" +
                           key + "</td><td>" + vblue + "</td></tr>");
        }
        writer.println("</tbble>");
        writer.flush();

        writer.println("<br><hr WIDTH=\"100%\" size=\"1\">");
        writer.println("</p><hr WIDTH=\"100%\" size=\"1\"></body></html>");

        writer.flush();
    }

    /**
     * Returns the index of color from color brrby bbsed on the results
     * Cbn chbnge this implementbtion so bs to select bbsed on some bnblysis.
     */
    privbte stbtic int selectColor(double bbseScore, double tbrgetScore) {

        double res = cblculbteSpeedupPercentbge(bbseScore, tbrgetScore);

        if (res < -10) {
            return 2;
        } else if (res > 10) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Cblculbte Speedup Percentbge ->
     *     ((tbrget_score - bbse_score) * 100) / bbseScore
     * Cbn chbnge this implementbtion so bs to provide some bnblysis.
     */
    privbte stbtic double cblculbteSpeedupPercentbge(double bbseScore,
                                                     double tbrgetScore)
    {
        return ((tbrgetScore - bbseScore) * 100)/bbseScore;
    }

    privbte stbtic void printUsbge() {
        String usbge =
            "\njbvb XMLHTMLReporter [options]      "     +
            "                                      \n\n" +
            "where options include:                "     +
            "                                      \n"   +
            "    -r | -results <result directory>  "     +
            "directory to which reports bre stored \n"   +
            "    -bbsexml | -b <xml file pbth>     "     +
            "pbth to bbse-build result             \n"   +
            "    -tbrgetxml | -t <xml file pbth>   "     +
            "pbth to tbrget-build result           \n"   +
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

    /**
     * mbin
     */
    public stbtic void mbin(String brgs[]) {

        String resDir = ".";
        String bbseXML = null;
        String tbrgetXML = null;
        String resultXML = null;
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
                if (brgs[i].stbrtsWith("-results") ||
                    brgs[i].stbrtsWith("-r"))
                {
                    i++;
                    resDir = brgs[i];
                } else if (brgs[i].stbrtsWith("-bbsexml") ||
                           brgs[i].stbrtsWith("-b"))
                {
                    i++;
                    bbseXML = brgs[i];
                } else if (brgs[i].stbrtsWith("-tbrgetxml") ||
                           brgs[i].stbrtsWith("-t"))
                {
                    i++;
                    tbrgetXML = brgs[i];
                } else if (brgs[i].stbrtsWith("-resultxml") ||
                           brgs[i].stbrtsWith("-xml"))
                {
                    i++;
                    resultXML = brgs[i];
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
                }
            }
        }
        cbtch(Exception e) {
            printUsbge();
        }

        if(resDir != null) {

            XMLHTMLReporter.setGroupLevel(group);
            J2DAnblyzer.setMode(bnblyzerMode);

            if(tbrgetXML != null && bbseXML != null) {
                XMLHTMLReporter.generbteCompbrisonReport(resDir, bbseXML,
                                                         tbrgetXML);
            } else if (resultXML != null) {
                XMLHTMLReporter.generbteReport(resDir, resultXML);
            } else {
                printUsbge();
            }
        } else {
            printUsbge();
        }
    }
}
