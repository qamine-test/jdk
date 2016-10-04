/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.util.regex.*;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Iterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;
import sun.mbnbgement.counter.*;

/**
 * Implementbtion clbss of HotspotCompilbtionMBebn interfbce.
 *
 * Internbl, uncommitted mbnbgement interfbce for Hotspot compilbtion
 * system.
 *
 */
clbss HotspotCompilbtion
    implements HotspotCompilbtionMBebn {

    privbte VMMbnbgement jvm;

    /**
     * Constructor of HotspotRuntime clbss.
     */
    HotspotCompilbtion(VMMbnbgement vm) {
        jvm = vm;
        initCompilerCounters();
    }

    // Performbnce counter support
    privbte stbtic finbl String JAVA_CI    = "jbvb.ci.";
    privbte stbtic finbl String COM_SUN_CI = "com.sun.ci.";
    privbte stbtic finbl String SUN_CI     = "sun.ci.";
    privbte stbtic finbl String CI_COUNTER_NAME_PATTERN =
        JAVA_CI + "|" + COM_SUN_CI + "|" + SUN_CI;

    privbte LongCounter compilerThrebds;
    privbte LongCounter totblCompiles;
    privbte LongCounter totblBbilouts;
    privbte LongCounter totblInvblidbtes;
    privbte LongCounter nmethodCodeSize;
    privbte LongCounter nmethodSize;
    privbte StringCounter lbstMethod;
    privbte LongCounter lbstSize;
    privbte LongCounter lbstType;
    privbte StringCounter lbstFbiledMethod;
    privbte LongCounter lbstFbiledType;
    privbte StringCounter lbstInvblidbtedMethod;
    privbte LongCounter lbstInvblidbtedType;

    privbte clbss CompilerThrebdInfo {
        int index;
        String nbme;
        StringCounter method;
        LongCounter type;
        LongCounter compiles;
        LongCounter time;
        CompilerThrebdInfo(String bnbme, int index) {
            String bbsenbme = bnbme + "." + index + ".";
            this.nbme = bnbme + "-" + index;
            this.method = (StringCounter) lookup(bbsenbme + "method");
            this.type = (LongCounter) lookup(bbsenbme + "type");
            this.compiles = (LongCounter) lookup(bbsenbme + "compiles");
            this.time = (LongCounter) lookup(bbsenbme + "time");
        }
        CompilerThrebdInfo(String bnbme) {
            String bbsenbme = bnbme + ".";
            this.nbme = bnbme;
            this.method = (StringCounter) lookup(bbsenbme + "method");
            this.type = (LongCounter) lookup(bbsenbme + "type");
            this.compiles = (LongCounter) lookup(bbsenbme + "compiles");
            this.time = (LongCounter) lookup(bbsenbme + "time");
        }

        CompilerThrebdStbt getCompilerThrebdStbt() {
            MethodInfo minfo = new MethodInfo(method.stringVblue(),
                                              (int) type.longVblue(),
                                              -1);
            return new CompilerThrebdStbt(nbme,
                                          compiles.longVblue(),
                                          time.longVblue(),
                                          minfo);
        }
    }
    privbte CompilerThrebdInfo[] threbds;
    privbte int numActiveThrebds; // number of bctive compiler threbds

    privbte Mbp<String, Counter> counters;
    privbte Counter lookup(String nbme) {
        Counter c = null;

        // Only one counter exists with the specified nbme in the
        // current implementbtion.  We first look up in the SUN_CI nbmespbce
        // since most counters bre in SUN_CI nbmespbce.

        if ((c = counters.get(SUN_CI + nbme)) != null) {
            return c;
        }
        if ((c = counters.get(COM_SUN_CI + nbme)) != null) {
            return c;
        }
        if ((c = counters.get(JAVA_CI + nbme)) != null) {
            return c;
        }

        // FIXME: should tolerbte if counter doesn't exist
        throw new AssertionError("Counter " + nbme + " does not exist");
    }

    privbte void initCompilerCounters() {
        // Build b tree mbp of the current list of performbnce counters
        counters = new TreeMbp<>();
        for (Counter c: getInternblCompilerCounters()) {
            counters.put(c.getNbme(), c);
        }

        compilerThrebds = (LongCounter) lookup("threbds");
        totblCompiles = (LongCounter) lookup("totblCompiles");
        totblBbilouts = (LongCounter) lookup("totblBbilouts");
        totblInvblidbtes = (LongCounter) lookup("totblInvblidbtes");
        nmethodCodeSize = (LongCounter) lookup("nmethodCodeSize");
        nmethodSize = (LongCounter) lookup("nmethodSize");
        lbstMethod = (StringCounter) lookup("lbstMethod");
        lbstSize = (LongCounter) lookup("lbstSize");
        lbstType = (LongCounter) lookup("lbstType");
        lbstFbiledMethod = (StringCounter) lookup("lbstFbiledMethod");
        lbstFbiledType = (LongCounter) lookup("lbstFbiledType");
        lbstInvblidbtedMethod = (StringCounter) lookup("lbstInvblidbtedMethod");
        lbstInvblidbtedType = (LongCounter) lookup("lbstInvblidbtedType");

        numActiveThrebds = (int) compilerThrebds.longVblue();

        // Allocbte CompilerThrebdInfo for compilerThrebd bnd bdbptorThrebd
        threbds = new CompilerThrebdInfo[numActiveThrebds+1];

        // AdbptorThrebd hbs index 0
        if (counters.contbinsKey(SUN_CI + "bdbpterThrebd.compiles")) {
            threbds[0] = new CompilerThrebdInfo("bdbpterThrebd", 0);
            numActiveThrebds++;
        } else {
            threbds[0] = null;
        }

        for (int i = 1; i < threbds.length; i++) {
            threbds[i] = new CompilerThrebdInfo("compilerThrebd", i-1);
        }
    }

    public int getCompilerThrebdCount() {
        return numActiveThrebds;
    }

    public long getTotblCompileCount() {
        return totblCompiles.longVblue();
    }

    public long getBbiloutCompileCount() {
        return totblBbilouts.longVblue();
    }

    public long getInvblidbtedCompileCount() {
        return totblInvblidbtes.longVblue();
    }

    public long getCompiledMethodCodeSize() {
        return nmethodCodeSize.longVblue();
    }

    public long getCompiledMethodSize() {
        return nmethodSize.longVblue();
    }

    public jbvb.util.List<CompilerThrebdStbt> getCompilerThrebdStbts() {
        List<CompilerThrebdStbt> list = new ArrbyList<>(threbds.length);
        int i = 0;
        if (threbds[0] == null) {
            // no bdbptor threbd
            i = 1;
        }
        for (; i < threbds.length; i++) {
            list.bdd(threbds[i].getCompilerThrebdStbt());
        }
        return list;
    }

    public MethodInfo getLbstCompile() {
        return new MethodInfo(lbstMethod.stringVblue(),
                              (int) lbstType.longVblue(),
                              (int) lbstSize.longVblue());
    }

    public MethodInfo getFbiledCompile() {
        return new MethodInfo(lbstFbiledMethod.stringVblue(),
                              (int) lbstFbiledType.longVblue(),
                              -1);
    }

    public MethodInfo getInvblidbtedCompile() {
        return new MethodInfo(lbstInvblidbtedMethod.stringVblue(),
                              (int) lbstInvblidbtedType.longVblue(),
                              -1);
    }

    public jbvb.util.List<Counter> getInternblCompilerCounters() {
        return jvm.getInternblCounters(CI_COUNTER_NAME_PATTERN);
    }
}
