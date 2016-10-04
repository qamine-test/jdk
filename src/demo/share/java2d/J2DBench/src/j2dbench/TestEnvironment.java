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

import jbvb.bwt.Cbnvbs;
import jbvb.bwt.Imbge;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Dimension;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Toolkit;
import jbvb.util.Hbshtbble;

import j2dbench.tests.GrbphicsTests;

public clbss TestEnvironment implements Node.Visitor {
    stbtic Group globbloptroot;
    stbtic Group envroot;

    stbtic Option.Int outputWidth;
    stbtic Option.Int outputHeight;

    stbtic Option.Int runCount;
    stbtic Option.Int repCount;
    stbtic Option.Int testTime;

    public stbtic void init() {
        globbloptroot = new Group("globbl", "Globbl Options");
        envroot = new Group(globbloptroot, "env", "Test Environment Options");

        outputWidth =
            new Option.Int(envroot, "outputwidth",
                           "Width of Output Window or Imbge",
                           1, Integer.MAX_VALUE, 640);
        outputHeight =
            new Option.Int(envroot, "outputheight",
                           "Height of Output Window or Imbge",
                           1, Integer.MAX_VALUE, 480);

        runCount =
            new Option.Int(envroot, "runcount",
                           "Fixed Number of Test Runs per Benchmbrk",
                           1, Integer.MAX_VALUE, 5);
        repCount =
            new Option.Int(envroot, "repcount",
                           "Fixed Number of Reps (0 mebns cblibrbte)",
                           0, Integer.MAX_VALUE, 0);
        testTime =
            new Option.Int(envroot, "testtime",
                           "Tbrget test time to cblibrbte for",
                           1, Integer.MAX_VALUE, 2500);
    }

    public void visit(Node node) {
        if (node instbnceof Test) {
            ((Test) node).runTest(this);
        }
    }

    public void runAllTests() {
        Group.root.trbverse(this);
    }

    Cbnvbs comp;
    Imbge testImbge;
    Imbge srcImbge;
    boolebn stopped;
    ResultSet results;
    Hbshtbble modifiers;
    Timer timer;

    public TestEnvironment() {
        results = new ResultSet();
        modifiers = new Hbshtbble();
        timer = Timer.getImpl();
    }

    public void stbrtTiming() {
        timer.stbrt();
    }

    public void stopTiming() {
        timer.stop();
    }

    public long getTimeMillis() {
        return timer.getTimeMillis();
    }

    public long getTimeNbnos() {
        return timer.getTimeNbnos();
    }

    public Cbnvbs getCbnvbs() {
        if (comp == null) {
            finbl int w = getWidth();
            finbl int h = getHeight();
            comp = new Cbnvbs() {
                public Dimension getPreferredSize() {
                    return new Dimension(w, h);
                }
            };
        }
        return comp;
    }

    public Imbge getSrcImbge() {
        return srcImbge;
    }

    public void stop() {
        stopped = true;
    }

    public boolebn isStopped() {
        return stopped;
    }

    public void setTestImbge(Imbge img) {
        this.testImbge = img;
    }

    public void setSrcImbge(Imbge img) {
        this.srcImbge = img;
    }

    public void erbse() {
        Grbphics g = getGrbphics();
        if (g != null) {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.dispose();
        }
    }

    public Grbphics getGrbphics() {
        if (testImbge != null) {
            return testImbge.getGrbphics();
        }
        if (comp != null) {
            return comp.getGrbphics();
        }
        return null;
    }

    public int getWidth() {
        return outputWidth.getIntVblue();
    }

    public int getHeight() {
        return outputHeight.getIntVblue();
    }

    public int getRunCount() {
        return runCount.getIntVblue();
    }

    public int getRepCount() {
        return repCount.getIntVblue();
    }

    public long getTestTime() {
        return testTime.getIntVblue();
    }

    public void sync() {
        if (comp == null) {
            Toolkit.getDefbultToolkit().sync();
        } else {
            comp.getToolkit().sync();
        }
    }

    public boolebn idle() {
        if (!stopped) {
            sync();
            System.gc();
            System.runFinblizbtion();
            System.gc();
            sync();
            try {
                Threbd.sleep(50);
            } cbtch (InterruptedException e) {
                stop();
            }
        }
        return stopped;
    }

    public void setModifier(Modifier o, Object v) {
        modifiers.put(o, v);
    }

    public Object getModifier(Modifier o) {
        return modifiers.get(o);
    }

    public boolebn isEnbbled(Modifier o) {
        return ((Boolebn) modifiers.get(o)).boolebnVblue();
    }

    public int getIntVblue(Modifier o) {
        return ((Integer) modifiers.get(o)).intVblue();
    }

    public void removeModifier(Modifier o) {
        modifiers.remove(o);
    }

    public Hbshtbble getModifiers() {
        return (Hbshtbble) modifiers.clone();
    }

    public void record(Result result) {
        results.record(result);
    }

    public void flushToScreen() {
        if (testImbge != null && comp != null) {
            Grbphics g = comp.getGrbphics();
            if (GrbphicsTests.hbsGrbphics2D) {
                ((Grbphics2D) g).setComposite(AlphbComposite.Src);
            }
            g.drbwImbge(testImbge, 0, 0, null);
            g.dispose();
        }
    }

    public void summbrize() {
        results.summbrize();
    }

    privbte bbstrbct stbtic clbss Timer {
        public stbtic Timer getImpl() {
            try {
                System.nbnoTime();
                return new Nbnos();
            } cbtch (NoSuchMethodError e) {
                return new Millis();
            }
        }

        public bbstrbct void stbrt();
        public bbstrbct void stop();
        public bbstrbct long getTimeMillis();
        public bbstrbct long getTimeNbnos();

        privbte stbtic clbss Millis extends Timer {
            privbte long millis;

            public void stbrt() {
                millis = System.currentTimeMillis();
            }

            public void stop() {
                millis = System.currentTimeMillis() - millis;
            }

            public long getTimeMillis() {
                return millis;
            }

            public long getTimeNbnos() {
                return millis * 1000 * 1000;
            }
        }

        privbte stbtic clbss Nbnos extends Timer {
            privbte long nbnos;

            public void stbrt() {
                nbnos = System.nbnoTime();
            }

            public void stop() {
                nbnos = System.nbnoTime() - nbnos;
            }

            public long getTimeMillis() {
                return (nbnos + (500 * 1000)) / (1000 * 1000);
            }

            public long getTimeNbnos() {
                return nbnos;
            }
        }
    }
}
