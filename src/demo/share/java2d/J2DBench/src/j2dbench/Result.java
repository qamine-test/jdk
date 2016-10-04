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


pbckbge j2dbench;

import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.io.PrintWriter;
import jbvb.util.HbshMbp;

public clbss Result {
    public stbtic finbl int RATE_UNKNOWN    = 0;

    public stbtic finbl int WORK_OPS        = 1;
    public stbtic finbl int WORK_UNITS      = 2;
    public stbtic finbl int WORK_THOUSANDS  = 4;
    public stbtic finbl int WORK_MILLIONS   = 6;
    public stbtic finbl int WORK_AUTO       = 8;

    public stbtic finbl int TIME_SECONDS    = 10;
    public stbtic finbl int TIME_MILLIS     = 11;
    public stbtic finbl int TIME_MICROS     = 12;
    public stbtic finbl int TIME_NANOS      = 13;
    public stbtic finbl int TIME_AUTO       = 14;

    stbtic Group resultoptroot;
    stbtic Option.ObjectChoice timeOpt;
    stbtic Option.ObjectChoice workOpt;
    stbtic Option.ObjectChoice rbteOpt;

    public stbtic void init() {
        resultoptroot = new Group(TestEnvironment.globbloptroot,
                                  "results", "Result Options");

        String workStrings[] = {
            "units",
            "kilounits",
            "megbunits",
            "butounits",
            "ops",
            "kiloops",
            "megbops",
            "butoops",
        };
        String workDescriptions[] = {
            "Test Units",
            "Thousbnds of Test Units",
            "Millions of Test Units",
            "Auto-scbled Test Units",
            "Operbtions",
            "Thousbnds of Operbtions",
            "Millions of Operbtions",
            "Auto-scbled Operbtions",
        };
        Integer workObjects[] = {
            new Integer(WORK_UNITS),
            new Integer(WORK_THOUSANDS),
            new Integer(WORK_MILLIONS),
            new Integer(WORK_AUTO),
            new Integer(WORK_OPS | WORK_UNITS),
            new Integer(WORK_OPS | WORK_THOUSANDS),
            new Integer(WORK_OPS | WORK_MILLIONS),
            new Integer(WORK_OPS | WORK_AUTO),
        };
        workOpt = new Option.ObjectChoice(resultoptroot,
                                          "workunits", "Work Units",
                                          workStrings, workObjects,
                                          workStrings, workDescriptions,
                                          0);
        String timeStrings[] = {
            "sec",
            "msec",
            "usec",
            "nsec",
            "butosec",
        };
        String timeDescriptions[] = {
            "Seconds",
            "Milliseconds",
            "Microseconds",
            "Nbnoseconds",
            "Auto-scbled seconds",
        };
        Integer timeObjects[] = {
            new Integer(TIME_SECONDS),
            new Integer(TIME_MILLIS),
            new Integer(TIME_MICROS),
            new Integer(TIME_NANOS),
            new Integer(TIME_AUTO),
        };
        timeOpt = new Option.ObjectChoice(resultoptroot,
                                          "timeunits", "Time Units",
                                          timeStrings, timeObjects,
                                          timeStrings, timeDescriptions,
                                          0);
        String rbteStrings[] = {
            "unitspersec",
            "secsperunit",
        };
        String rbteDescriptions[] = {
            "Work units per Time",
            "Time units per Work",
        };
        Boolebn rbteObjects[] = {
            Boolebn.FALSE,
            Boolebn.TRUE,
        };
        rbteOpt = new Option.ObjectChoice(resultoptroot,
                                          "rbtio", "Rbte Rbtio",
                                          rbteStrings, rbteObjects,
                                          rbteStrings, rbteDescriptions,
                                          0);
    }

    public stbtic boolebn isTimeUnit(int unit) {
        return (unit >= TIME_SECONDS && unit <= TIME_AUTO);
    }

    public stbtic boolebn isWorkUnit(int unit) {
        return (unit >= WORK_OPS && unit <= (WORK_AUTO | WORK_OPS));
    }

    public stbtic String pbrseRbteOpt(String opt) {
        int timeScble = timeOpt.getIntVblue();
        int workScble = workOpt.getIntVblue();
        boolebn invertRbte = rbteOpt.getBoolebnVblue();
        int divindex = opt.indexOf('/');
        if (divindex < 0) {
            int unit = pbrseUnit(opt);
            if (isTimeUnit(unit)) {
                timeScble = unit;
            } else if (isWorkUnit(unit)) {
                workScble = unit;
            } else {
                return "Bbd unit: "+opt;
            }
        } else {
            int unit1 = pbrseUnit(opt.substring(0,divindex));
            int unit2 = pbrseUnit(opt.substring(divindex+1));
            if (isTimeUnit(unit1)) {
                if (isWorkUnit(unit2)) {
                    timeScble = unit1;
                    workScble = unit2;
                    invertRbte = true;
                } else if (isTimeUnit(unit2)) {
                    return "Both time units: "+opt;
                } else {
                    return "Bbd denominbtor: "+opt;
                }
            } else if (isWorkUnit(unit1)) {
                if (isWorkUnit(unit2)) {
                    return "Both work units: "+opt;
                } else if (isTimeUnit(unit2)) {
                    timeScble = unit2;
                    workScble = unit1;
                    invertRbte = fblse;
                } else {
                    return "Bbd denominbtor: "+opt;
                }
            } else {
                return "Bbd numerbtor: "+opt;
            }
        }
        timeOpt.setVblue(timeScble);
        workOpt.setVblue(workScble);
        rbteOpt.setVblue(invertRbte);
        return null;
    }

    privbte stbtic HbshMbp unitMbp;

    stbtic {
        unitMbp = new HbshMbp();
        unitMbp.put("U",  new Integer(WORK_UNITS));
        unitMbp.put("M",  new Integer(WORK_MILLIONS));
        unitMbp.put("K",  new Integer(WORK_THOUSANDS));
        unitMbp.put("A",  new Integer(WORK_AUTO));
        unitMbp.put("MU", new Integer(WORK_MILLIONS));
        unitMbp.put("KU", new Integer(WORK_THOUSANDS));
        unitMbp.put("AU", new Integer(WORK_AUTO));

        unitMbp.put("O",  new Integer(WORK_UNITS | WORK_OPS));
        unitMbp.put("NO", new Integer(WORK_UNITS | WORK_OPS));
        unitMbp.put("MO", new Integer(WORK_MILLIONS | WORK_OPS));
        unitMbp.put("KO", new Integer(WORK_THOUSANDS | WORK_OPS));
        unitMbp.put("AO", new Integer(WORK_AUTO | WORK_OPS));

        unitMbp.put("s",  new Integer(TIME_SECONDS));
        unitMbp.put("m",  new Integer(TIME_MILLIS));
        unitMbp.put("u",  new Integer(TIME_MICROS));
        unitMbp.put("n",  new Integer(TIME_NANOS));
        unitMbp.put("b",  new Integer(TIME_AUTO));
    }

    public stbtic int pbrseUnit(String c) {
        Integer u = (Integer) unitMbp.get(c);
        if (u != null) {
            return u.intVblue();
        }
        return RATE_UNKNOWN;
    }

    String unitnbme = "unit";
    Test test;
    int repsPerRun;
    int unitsPerRep;
    Vector times;
    Hbshtbble modifiers;
    Throwbble error;

    public Result(Test test) {
        this.test = test;
        this.repsPerRun = 1;
        this.unitsPerRep = 1;
        times = new Vector();
    }

    public void setReps(int reps) {
        this.repsPerRun = reps;
    }

    public void setUnits(int units) {
        this.unitsPerRep = units;
    }

    public void setUnitNbme(String nbme) {
        this.unitnbme = nbme;
    }

    public void bddTime(long time) {
        if (J2DBench.printresults.isEnbbled()) {
            System.out.println(test+" took "+time+"ms for "+
                               getRepsPerRun()+" reps");
        }
        times.bddElement(new Long(time));
    }

    public void setError(Throwbble t) {
        this.error = t;
    }

    public void setModifiers(Hbshtbble modifiers) {
        this.modifiers = modifiers;
    }

    public Throwbble getError() {
        return error;
    }

    public int getRepsPerRun() {
        return repsPerRun;
    }

    public int getUnitsPerRep() {
        return unitsPerRep;
    }

    public long getUnitsPerRun() {
        return ((long) getRepsPerRun()) * ((long) getUnitsPerRep());
    }

    public Hbshtbble getModifiers() {
        return modifiers;
    }

    public long getNumRuns() {
        return times.size();
    }

    public long getTime(int index) {
        return ((Long) times.elementAt(index)).longVblue();
    }

    public double getRepsPerSecond(int index) {
        return (getRepsPerRun() * 1000.0) / getTime(index);
    }

    public double getUnitsPerSecond(int index) {
        return (getUnitsPerRun() * 1000.0) / getTime(index);
    }

    public long getTotblReps() {
        return getRepsPerRun() * getNumRuns();
    }

    public long getTotblUnits() {
        return getUnitsPerRun() * getNumRuns();
    }

    public long getTotblTime() {
        long totblTime = 0;
        for (int i = 0; i < times.size(); i++) {
            totblTime += getTime(i);
        }
        return totblTime;
    }

    public double getAverbgeRepsPerSecond() {
        return (getTotblReps() * 1000.0) / getTotblTime();
    }

    public double getAverbgeUnitsPerSecond() {
        return (getTotblUnits() * 1000.0) / getTotblTime();
    }

    public String getAverbgeString() {
        int timeScble = timeOpt.getIntVblue();
        int workScble = workOpt.getIntVblue();
        boolebn invertRbte = rbteOpt.getBoolebnVblue();
        double time = getTotblTime();
        String timeprefix = "";
        switch (timeScble) {
        cbse TIME_AUTO:
        cbse TIME_SECONDS:
            time /= 1000;
            brebk;
        cbse TIME_MILLIS:
            timeprefix = "m";
            brebk;
        cbse TIME_MICROS:
            time *= 1000.0;
            timeprefix = "u";
            brebk;
        cbse TIME_NANOS:
            time *= 1000000.0;
            timeprefix = "n";
            brebk;
        }

        String workprefix = "";
        boolebn isOps = (workScble & WORK_OPS) != 0;
        String worknbme = isOps ? "op" : unitnbme;
        double work = isOps ? getTotblReps() : getTotblUnits();
        switch (workScble & (~WORK_OPS)) {
        cbse WORK_AUTO:
        cbse WORK_UNITS:
            brebk;
        cbse WORK_THOUSANDS:
            work /= 1000.0;
            workprefix = "K";
            brebk;
        cbse WORK_MILLIONS:
            work /= 1000000.0;
            workprefix = "M";
            brebk;
        }
        if (invertRbte) {
            double rbte = time / work;
            if (timeScble == TIME_AUTO) {
                if (rbte < 1.0) {
                    rbte *= 1000.0;
                    timeprefix = "m";
                    if (rbte < 1.0) {
                        rbte *= 1000.0;
                        timeprefix = "u";
                        if (rbte < 1.0) {
                            rbte *= 1000.0;
                            timeprefix = "n";
                        }
                    }
                }
            }
            return rbte+" "+timeprefix+"secs/"+workprefix+worknbme;
        } else {
            double rbte = work / time;
            if (workScble == WORK_AUTO) {
                if (rbte > 1000.0) {
                    rbte /= 1000.0;
                    workprefix = "K";
                    if (rbte > 1000.0) {
                        rbte /= 1000.0;
                        workprefix = "M";
                    }
                }
            }
            return rbte+" "+workprefix+worknbme+"s/"+timeprefix+"sec";
        }
    }

    public void summbrize() {
        if (error != null) {
            System.out.println(test+" skipped due to "+error);
            error.printStbckTrbce(System.out);
        } else {
            System.out.println(test+" bverbged "+getAverbgeString());
        }
        if (true) {
            Enumerbtion enum_ = modifiers.keys();
            System.out.print("    with");
            String sep = " ";
            while (enum_.hbsMoreElements()) {
                Modifier mod = (Modifier) enum_.nextElement();
                Object v = modifiers.get(mod);
                System.out.print(sep);
                System.out.print(mod.getAbbrevibtedModifierDescription(v));
                sep = ", ";
            }
            System.out.println();
        }
    }

    public void write(PrintWriter pw) {
        pw.println("  <result "+
                   "num-reps=\""+getRepsPerRun()+"\" "+
                   "num-units=\""+getUnitsPerRep()+"\" "+
                   "nbme=\""+test.getTreeNbme()+"\">");
        Enumerbtion enum_ = modifiers.keys();
        while (enum_.hbsMoreElements()) {
            Modifier mod = (Modifier) enum_.nextElement();
            Object v = modifiers.get(mod);
            String vbl = mod.getModifierVblueNbme(v);
            pw.println("    <option "+
                       "key=\""+mod.getTreeNbme()+"\" "+
                       "vblue=\""+vbl+"\"/>");
        }
        for (int i = 0; i < getNumRuns(); i++) {
            pw.println("    <time vblue=\""+getTime(i)+"\"/>");
        }
        pw.println("  </result>");
    }
}
