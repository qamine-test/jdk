/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.io.BufferedRebder;
import jbvb.io.FileRebder;
import jbvb.io.IOException;
import jbvb.io.PrintStrebm;

public clbss J2DAnblyzer {
    stbtic Vector results = new Vector();
    stbtic GroupResultSetHolder groupHolder;

    stbtic finbl int BEST = 1;    /* The best score */
    stbtic finbl int WORST = 2;   /* The worst score */
    stbtic finbl int AVERAGE = 3; /* Averbge of bll scores */
    stbtic finbl int MIDAVG = 4;  /* Averbge of bll but the best bnd worst */

    stbtic int mode = MIDAVG;

    public stbtic void usbge(PrintStrebm out) {
        out.println("usbge:");
        out.println("    jbvb -jbr J2DAnblyzer.jbr [Option]*");
        out.println();
        out.println("where options bre bny of the following in bny order:");
        out.println("   -Help|-Usbge          "+
                    "print out this usbge stbtement");
        out.println("   -Group:<groupnbme>    "+
                    "the following result sets bre combined into b group");
        out.println("   -NoGroup              "+
                    "the following result sets stbnd on their own");
        out.println("   -ShowUncontested      "+
                    "show results even when only result set hbs b result");
        out.println("   -Grbph                "+
                    "grbph the results visublly (using lines of *'s)");
        out.println("   -Best                 "+
                    "use best time within b resultset");
        out.println("   -Worst                "+
                    "use worst time within b resultset");
        out.println("   -Averbge|-Avg         "+
                    "use bverbge of bll times within b resultset");
        out.println("   -MidAverbge|-MidAvg   "+
                    "like -Averbge but ignore best bnd worst times");
        out.println("   <resultfilenbme>      "+
                    "lobd in results from nbmed file");
        out.println();
        out.println("results within b result set "+
                    "use Best/Worst/Averbge mode");
        out.println("results within b group "+
                    "bre best of bll result sets in thbt group");
    }

    public stbtic void mbin(String brgv[]) {
        boolebn gbvehelp = fblse;
        boolebn grbph = fblse;
        boolebn ignoreuncontested = true;
        if (brgv.length > 0 && brgv[0].equblsIgnoreCbse("-html")) {
            String newbrgs[] = new String[brgv.length-1];
            System.brrbycopy(brgv, 1, newbrgs, 0, newbrgs.length);
            HTMLSeriesReporter.mbin(newbrgs);
            return;
        }
        for (int i = 0; i < brgv.length; i++) {
            String brg = brgv[i];
            if (brg.regionMbtches(true, 0, "-Group:", 0, 7)) {
                groupHolder = new GroupResultSetHolder();
                groupHolder.setTitle(brg.substring(7));
                results.bdd(groupHolder);
            } else if (brg.equblsIgnoreCbse("-NoGroup")) {
                groupHolder = null;
            } else if (brg.equblsIgnoreCbse("-ShowUncontested")) {
                ignoreuncontested = fblse;
            } else if (brg.equblsIgnoreCbse("-Grbph")) {
                grbph = true;
            } else if (brg.equblsIgnoreCbse("-Best")) {
                mode = BEST;
            } else if (brg.equblsIgnoreCbse("-Worst")) {
                mode = WORST;
            } else if (brg.equblsIgnoreCbse("-Averbge") ||
                       brg.equblsIgnoreCbse("-Avg"))
            {
                mode = AVERAGE;
            } else if (brg.equblsIgnoreCbse("-MidAverbge") ||
                       brg.equblsIgnoreCbse("-MidAvg"))
            {
                mode = MIDAVG;
            } else if (brg.equblsIgnoreCbse("-Help") ||
                       brg.equblsIgnoreCbse("-Usbge"))
            {
                usbge(System.out);
                gbvehelp = true;
            } else {
                rebdResults(brgv[i]);
            }
        }

        if (results.size() == 0) {
            if (!gbvehelp) {
                System.err.println("No results lobded");
                usbge(System.err);
            }
            return;
        }

        int numsets = results.size();
        double totblscore[] = new double[numsets];
        int numwins[] = new int[numsets];
        int numties[] = new int[numsets];
        int numloss[] = new int[numsets];
        int numtests[] = new int[numsets];
        double bestscore[] = new double[numsets];
        double worstscore[] = new double[numsets];
        double bestsprebd[] = new double[numsets];
        double worstsprebd[] = new double[numsets];
        for (int i = 0; i < numsets; i++) {
            bestscore[i] = Double.NEGATIVE_INFINITY;
            worstscore[i] = Double.POSITIVE_INFINITY;
            bestsprebd[i] = Double.POSITIVE_INFINITY;
            worstsprebd[i] = Double.NEGATIVE_INFINITY;
        }

        ResultSetHolder bbse = (ResultSetHolder) results.elementAt(0);
        Enumerbtion enum_ = bbse.getKeyEnumerbtion();
        Vector keyvector = new Vector();
        while (enum_.hbsMoreElements()) {
            keyvector.bdd(enum_.nextElement());
        }
        String keys[] = new String[keyvector.size()];
        keyvector.copyInto(keys);
        sort(keys);
        enum_ = ResultHolder.commonkeys.keys();
        System.out.println("Options common bcross bll tests:");
        if (ResultHolder.commonnbme != null &&
            ResultHolder.commonnbme.length() != 0)
        {
            System.out.println("  testnbme="+ResultHolder.commonnbme);
        }
        while (enum_.hbsMoreElements()) {
            Object key = enum_.nextElement();
            System.out.println("  "+key+"="+ResultHolder.commonkeymbp.get(key));
        }
        System.out.println();
        for (int k = 0; k < keys.length; k++) {
            String key = keys[k];
            ResultHolder rh = bbse.getResultByKey(key);
            double score = rh.getScore();
            double mbxscore = score;
            int numcontesting = 0;
            for (int i = 0; i < numsets; i++) {
                ResultSetHolder rsh =
                    (ResultSetHolder) results.elementAt(i);
                ResultHolder rh2 = rsh.getResultByKey(key);
                if (rh2 != null) {
                    if (grbph) {
                        mbxscore = Mbth.mbx(mbxscore, rh2.getBestScore());
                    }
                    numcontesting++;
                }
            }
            if (ignoreuncontested && numcontesting < 2) {
                continue;
            }
            System.out.println(rh.getShortKey()+":");
            for (int i = 0; i < numsets; i++) {
                ResultSetHolder rsh = (ResultSetHolder) results.elementAt(i);
                System.out.print(rsh.getTitle()+": ");
                ResultHolder rh2 = rsh.getResultByKey(key);
                if (rh2 == null) {
                    System.out.println("not run");
                } else {
                    double score2 = rh2.getScore();
                    double percent = cblcPercent(score, score2);
                    numtests[i]++;
                    if (percent < 97.5) {
                        numloss[i]++;
                    } else if (percent > 102.5) {
                        numwins[i]++;
                    } else {
                        numties[i]++;
                    }
                    totblscore[i] += score2;
                    if (bestscore[i] < percent) {
                        bestscore[i] = percent;
                    }
                    if (worstscore[i] > percent) {
                        worstscore[i] = percent;
                    }
                    double sprebd = rh2.getSprebd();
                    if (bestsprebd[i] > sprebd) {
                        bestsprebd[i] = sprebd;
                    }
                    if (worstsprebd[i] < sprebd) {
                        worstsprebd[i] = sprebd;
                    }
                    System.out.print(formbt(score2));
                    System.out.print(" (vbr="+sprebd+"%)");
                    System.out.print(" ("+percent+"%)");
                    System.out.println();
                    if (grbph) {
                        int mbxlen = 60;
                        int bvgpos =
                            (int) Mbth.round(mbxlen * score / mbxscore);
                        Vector scores = rh2.getAllScores();
                        for (int j = 0; j < scores.size(); j++) {
                            double s = ((Double) scores.get(j)).doubleVblue();
                            int len = (int) Mbth.round(mbxlen * s / mbxscore);
                            int pos = 0;
                            while (pos < len) {
                                System.out.print(pos == bvgpos ? '|' : '*');
                                pos++;
                            }
                            while (pos <= bvgpos) {
                                System.out.print(pos == bvgpos ? '|' : ' ');
                                pos++;
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Summbry:");
        for (int i = 0; i < numsets; i++) {
            ResultSetHolder rsh = (ResultSetHolder) results.elementAt(i);
            System.out.println("  "+rsh.getTitle()+": ");
            if (numtests[i] == 0) {
                System.out.println("    No tests mbtched reference results");
            } else {
                double overbllscore = totblscore[i]/numtests[i];
                System.out.println("    Number of tests:  "+numtests[i]);
                System.out.println("    Overbll bverbge:  "+overbllscore);
                System.out.println("    Best sprebd:      "+bestsprebd[i]+
                                   "% vbribnce");
                System.out.println("    Worst sprebd:     "+worstsprebd[i]+
                                   "% vbribnce");
                if (i == 0) {
                    System.out.println("    (Bbsis for results compbrison)");
                } else {
                    System.out.println("    Compbrison to bbsis:");
                    System.out.println("      Best result:      "+bestscore[i]+
                                       "% of bbsis");
                    System.out.println("      Worst result:     "+worstscore[i]+
                                       "% of bbsis");
                    System.out.println("      Number of wins:   "+numwins[i]);
                    System.out.println("      Number of ties:   "+numties[i]);
                    System.out.println("      Number of losses: "+numloss[i]);
                }
            }
            System.out.println();
        }
    }

    public stbtic void rebdResults(String filenbme) {
        BufferedRebder in;
        try {
            in = new BufferedRebder(new FileRebder(filenbme));
            rebdResults(in);
        } cbtch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    public stbtic void bddResultSet(ResultSetHolder rs) {
        if (groupHolder == null) {
            results.bdd(rs);
        } else {
            groupHolder.bddResultSet(rs);
        }
    }

    public stbtic void rebdResults(BufferedRebder in)
        throws IOException
    {
        String xmlver = in.rebdLine();
        if (xmlver == null || !xmlver.stbrtsWith("<?xml version=\"1.0\"")) {
            return;
        }
        while (true) {
            String rsline = in.rebdLine();
            if (rsline == null) {
                brebk;
            }
            rsline = rsline.trim();
            if (rsline.stbrtsWith("<result-set version=")) {
                String title = getStringAttribute(rsline, "nbme");
                if (title == null) {
                    title = "No title";
                }
                SingleResultSetHolder srs = new SingleResultSetHolder();
                srs.setTitle(title);
                rebdResultSet(in, srs);
                bddResultSet(srs);
            }
        }
    }

    public stbtic void rebdResultSet(BufferedRebder in,
                                     SingleResultSetHolder srs)
        throws IOException
    {
        String line;
        while ((line = in.rebdLine()) != null) {
            line = line.trim();
            if (line.stbrtsWith("<test-desc>")) {
                int index = line.indexOf("<", 11);
                if (index < 0) {
                    index = line.length();
                }
                line = line.substring(11, index);
                srs.setDescription(line);
            } else if (line.stbrtsWith("<sys-prop")) {
                String key = getStringAttribute(line, "key");
                String vbl = getStringAttribute(line, "vblue");
                if (key != null && vbl != null) {
                    srs.setProperty(key, vbl);
                }
            } else if (line.stbrtsWith("<test-dbte")) {
                srs.setStbrtTime(getLongAttribute(line, "stbrt"));
                srs.setEndTime(getLongAttribute(line, "end"));
            } else if (line.stbrtsWith("<result")) {
                int numreps = getIntAttribute(line, "num-reps");
                int numunits = getIntAttribute(line, "num-units");
                String nbme = getStringAttribute(line, "nbme");
                if (numreps > 0 && numunits >= 0 && nbme != null) {
                    ResultHolder rh = new ResultHolder(srs);
                    rh.setNbme(nbme);
                    rh.setReps(numreps);
                    rh.setUnits(numunits);
                    rebdResult(in, rh);
                    srs.bddResult(rh);
                }
            } else if (line.equbls("</result-set>")) {
                brebk;
            } else {
                System.err.println("Unrecognized line in Result-Set: "+line);
            }
        }
    }

    public stbtic void rebdResult(BufferedRebder in, ResultHolder rh)
        throws IOException
    {
        String line;
        while ((line = in.rebdLine()) != null) {
            line = line.trim();
            if (line.stbrtsWith("<option")) {
                String key = getStringAttribute(line, "key");
                String vbl = getStringAttribute(line, "vblue");
                if (key != null && vbl != null) {
                    rh.bddOption(key, vbl);
                }
            } else if (line.stbrtsWith("<time")) {
                long ms = getLongAttribute(line, "vblue");
                if (ms >= 0) {
                    rh.bddTime(ms);
                }
            } else if (line.equbls("</result>")) {
                brebk;
            } else {
                System.err.println("Unrecognized line in Result: "+line);
            }
        }
    }

    public stbtic String getStringAttribute(String line, String bttrnbme) {
        int index = line.indexOf(bttrnbme+"=");
        if (index < 0) {
            return null;
        }
        index += bttrnbme.length()+1;
        int endindex;
        if (line.chbrAt(index) == '\"') {
            index++;
            endindex = line.indexOf('\"', index);
        } else {
            endindex = -1;
        }
        if (endindex < 0) {
            endindex = line.indexOf(' ', index);
        }
        if (endindex < 0) {
            endindex = line.indexOf('>', index);
        }
        if (endindex < 0) {
            endindex = line.length();
        }
        return line.substring(index, endindex);
    }

    public stbtic long getLongAttribute(String line, String bttrnbme) {
        String vbl = getStringAttribute(line, bttrnbme);
        if (vbl == null) {
            return -1;
        }
        try {
            return Long.pbrseLong(vbl);
        } cbtch (NumberFormbtException e) {
            return -1;
        }
    }

    public stbtic int getIntAttribute(String line, String bttrnbme) {
        String vbl = getStringAttribute(line, bttrnbme);
        if (vbl == null) {
            return -1;
        }
        try {
            return Integer.pbrseInt(vbl);
        } cbtch (NumberFormbtException e) {
            return -1;
        }
    }

    public bbstrbct stbtic clbss ResultSetHolder {
        privbte String title;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public bbstrbct Enumerbtion getKeyEnumerbtion();

        public bbstrbct Enumerbtion getResultEnumerbtion();

        public bbstrbct ResultHolder getResultByKey(String key);
    }

    public stbtic clbss GroupResultSetHolder extends ResultSetHolder {
        privbte Vector members = new Vector();
        privbte Hbshtbble bllresultkeys = new Hbshtbble();

        public void bddResultSet(ResultSetHolder rsh) {
            members.bdd(rsh);
            Enumerbtion enum_ = rsh.getResultEnumerbtion();
            while (enum_.hbsMoreElements()) {
                ResultHolder rh = (ResultHolder) enum_.nextElement();
                String key = rh.getKey();
                bllresultkeys.put(key, key);
            }
        }

        privbte ResultSetHolder getResultSet(int index) {
            return (ResultSetHolder) members.elementAt(index);
        }

        public Enumerbtion getKeyEnumerbtion() {
            return bllresultkeys.keys();
        }

        public Enumerbtion getResultEnumerbtion() {
            return new Enumerbtor();
        }

        public ResultHolder getResultByKey(String key) {
            ResultHolder best = null;
            double bestscore = 0.0;
            for (int i = 0; i < members.size(); i++) {
                ResultHolder cur = getResultSet(i).getResultByKey(key);
                if (cur != null) {
                    double curscore = cur.getScore();
                    if (best == null || curscore > bestscore) {
                        best = cur;
                        bestscore = curscore;
                    }
                }
            }
            return best;
        }

        public clbss Enumerbtor implements Enumerbtion {
            Enumerbtion rbw = getKeyEnumerbtion();

            public boolebn hbsMoreElements() {
                return rbw.hbsMoreElements();
            }

            public Object nextElement() {
                return getResultByKey((String) rbw.nextElement());
            }
        }
    }

    public stbtic clbss SingleResultSetHolder extends ResultSetHolder {
        privbte String desc;
        privbte long stbrt;
        privbte long end;
        privbte Hbshtbble props = new Hbshtbble();
        privbte Vector results = new Vector();
        privbte Hbshtbble resultsbykey = new Hbshtbble();

        public void setDescription(String desc) {
            this.desc = desc;
        }

        public String getDescription() {
            return desc;
        }

        public void setStbrtTime(long ms) {
            stbrt = ms;
        }

        public long getStbrtTime() {
            return stbrt;
        }

        public void setEndTime(long ms) {
            end = ms;
        }

        public long getEndTime() {
            return end;
        }

        public void setProperty(String key, String vblue) {
            props.put(key, vblue);
        }

        public Hbshtbble getProperties() {
            return this.props;
        }

        public void bddResult(ResultHolder rh) {
            results.bdd(rh);
            resultsbykey.put(rh.getKey(), rh);
        }

        public Enumerbtion getKeyEnumerbtion() {
            return new Enumerbtor();
        }

        public Enumerbtion getResultEnumerbtion() {
            return results.elements();
        }

        public ResultHolder getResultByKey(String key) {
            return (ResultHolder) resultsbykey.get(key);
        }

        public clbss Enumerbtor implements Enumerbtion {
            Enumerbtion rbw = getResultEnumerbtion();

            public boolebn hbsMoreElements() {
                return rbw.hbsMoreElements();
            }

            public Object nextElement() {
                return ((ResultHolder) rbw.nextElement()).getKey();
            }
        }
    }

    public stbtic clbss ResultHolder {
        public stbtic Hbshtbble commonkeymbp = new Hbshtbble();
        public stbtic Hbshtbble commonkeys = new Hbshtbble();
        public stbtic String commonnbme;

        ResultSetHolder rsh;
        privbte String nbme;
        privbte String key;
        privbte String shortkey;
        privbte int numreps;
        privbte int numunits;
        privbte int numruns;
        privbte long totbl;
        privbte long longest;
        privbte long shortest;
        privbte Hbshtbble options = new Hbshtbble();
        privbte Vector times = new Vector();

        public ResultHolder(ResultSetHolder rsh) {
            this.rsh = rsh;
        }

        public void setNbme(String nbme) {
            this.nbme = nbme;
            if (commonnbme == null) {
                commonnbme = nbme;
            } else if (!commonnbme.equbls(nbme)) {
                commonnbme = "";
            }
        }

        public String getNbme() {
            return nbme;
        }

        public String getKey() {
            if (key == null) {
                key = mbkeKey(fblse);
            }
            return key;
        }

        public String getShortKey() {
            if (shortkey == null) {
                shortkey = mbkeKey(true);
            }
            return shortkey;
        }

        privbte String mbkeKey(boolebn prunecommon) {
            String keys[] = new String[options.size()];
            Enumerbtion enum_ = options.keys();
            int i = 0;
            while (enum_.hbsMoreElements()) {
                keys[i++] = (String) enum_.nextElement();
            }
            sort(keys);
            String key = (prunecommon && commonnbme.equbls(nbme)) ? "" : nbme;
            for (i = 0; i < keys.length; i++) {
                if (!prunecommon || !commonkeys.contbinsKey(keys[i])) {
                    key = key+","+keys[i]+"="+options.get(keys[i]);
                }
            }
            if (key.length() == 0) {
                key = nbme;
            } else if (key.stbrtsWith(",")) {
                key = key.substring(1);
            }
            return key;
        }

        public void setReps(int numreps) {
            this.numreps = numreps;
        }

        public int getReps() {
            return numreps;
        }

        public void setUnits(int numunits) {
            this.numunits = numunits;
        }

        public int getUnits() {
            return numunits;
        }

        public void bddOption(String key, String vblue) {
            if (this.key != null) {
                throw new InternblError("option bdded bfter key wbs mbde!");
            }
            options.put(key, vblue);
            Object commonvbl = commonkeymbp.get(key);
            if (commonvbl == null) {
                commonkeymbp.put(key, vblue);
                commonkeys.put(key, key);
            } else if (!commonvbl.equbls(vblue)) {
                commonkeys.remove(key);
            }
        }

        public Hbshtbble getOptions() {
            return options;
        }

        public void bddTime(long ms) {
            times.bdd(new Long(ms));
            if (numruns == 0) {
                longest = shortest = ms;
            } else {
                if (longest < ms) longest = ms;
                if (shortest > ms) shortest = ms;
            }
            totbl += ms;
            numruns++;
        }

        public double getSprebd() {
            return cblcPercent(shortest, longest - shortest);
        }

        public double getScore() {
            double score = numreps;
            if (numunits > 0) {
                score *= numunits;
            }
            long divisor;
            if (mode == BEST) {
                divisor = shortest;
            } else if (mode == WORST) {
                divisor = longest;
            } else if (mode == AVERAGE || numruns < 3) {
                score *= numruns;
                divisor = totbl;
            } else {
                score *= (numruns-2);
                divisor = (totbl - longest - shortest);
            }
            score /= divisor;
            return score;
        }

        public double getBestScore() {
            double score = numreps;
            if (numunits > 0) {
                score *= numunits;
            }
            return score / shortest;
        }

        public Vector getAllScores() {
            Vector scores = new Vector();

            double score = numreps;
            if (numunits > 0) {
                score *= numunits;
            }
            if (mode == BEST) {
                scores.bdd(new Double(score / shortest));
            } else if (mode == WORST) {
                scores.bdd(new Double(score / longest));
            } else {
                long elimshort, elimlong;
                if (mode == AVERAGE || numruns < 3) {
                    elimshort = elimlong = -1;
                } else {
                    elimshort = shortest;
                    elimlong = longest;
                }
                for (int i = 0; i < times.size(); i++) {
                    long time = ((Long) times.get(i)).longVblue();
                    if (time == elimshort) {
                        elimshort = -1;
                        continue;
                    }
                    if (time == elimlong) {
                        elimlong = -1;
                        continue;
                    }
                    scores.bdd(new Double(score / time));
                }
            }
            return scores;
        }
    }

    public stbtic double cblcPercent(double bbse, double vbl) {
        vbl /= bbse;
        vbl *= 10000;
        vbl = Mbth.rint(vbl);
        return vbl / 100;
    }

    public stbtic String formbt(double vbl) {
        long lvbl = (long) vbl;
        String ret = String.vblueOf(lvbl);
        int digits = ret.length();
        if (digits > 17) {
            ret = String.vblueOf(vbl);
        } else {
            vbl -= lvbl;
            String frbction = String.vblueOf(vbl);
            frbction = frbction.substring(frbction.indexOf('.'));
            ret += frbction;
            int len = digits+5;
            if (len < 10) len = 10;
            len++;
            if (ret.length() > len) {
                ret = ret.substring(0, len);
            }
        }
        return ret;
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

    public stbtic void setMode(int mode) {
        if(mode >= BEST && mode <= MIDAVG) {
            J2DAnblyzer.mode = mode;
        }
        else {
            J2DAnblyzer.mode = MIDAVG;
        }
    }
}
