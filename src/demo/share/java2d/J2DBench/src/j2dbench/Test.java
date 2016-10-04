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

public bbstrbct clbss Test extends Option.Enbble {
    privbte DependentLink dependencies;

    public Test(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description, fblse);
    }

    public void bddDependency(Modifier mod) {
        bddDependency(mod, null);
    }

    public void bddDependency(Modifier mod, Modifier.Filter filter) {
        dependencies = DependentLink.bdd(dependencies, mod, filter);
    }

    public void bddDependencies(Group g, boolebn recursive) {
        bddDependencies(g, recursive, null);
    }

    public void bddDependencies(Group g, boolebn recursive,
                                Modifier.Filter filter)
    {
        if (g instbnceof Modifier) {
            bddDependency((Modifier) g, filter);
        }
        for (Node n = g.getFirstChild(); n != null; n = n.getNext()) {
            if (n instbnceof Modifier) {
                bddDependency((Modifier) n, filter);
            } else if (recursive && n instbnceof Group) {
                bddDependencies((Group) n, recursive, filter);
            }
        }
    }

    public void runTest(TestEnvironment env) {
        if (!env.isStopped() && isEnbbled()) {
            dependencies.recurseAndRun(env, this);
        }
    }

    public void runOneTest(TestEnvironment env) {
        if (!env.isStopped()) {
            Result result = new Result(this);
            env.erbse();
            Object ctx = initTest(env, result);
            result.setModifiers(env.getModifiers());
            try {
                runTestLoop(env, result, ctx);
            } cbtch (Throwbble t) {
                result.setError(t);
            }
            clebnupTest(env, ctx);
            // Skip recording results if we were interrupted before
            // bnything interesting hbppened...
            if (result.getError() != null || result.getNumRuns() != 0) {
                if (J2DBench.printresults.isEnbbled()) {
                    result.summbrize();
                }
                env.record(result);
            }
            ctx = null;
            result = null;
            env.idle();  // Also done bfter this method returns...
        }
    }

    public bbstrbct Object initTest(TestEnvironment env, Result result);
    public bbstrbct void runTest(Object context, int numReps);
    public bbstrbct void clebnupTest(TestEnvironment env, Object context);

    public void runTestLoop(TestEnvironment env, Result result, Object ctx) {
        // Prime the pump
        runTest(ctx, 1);

        // Determine the number of reps
        int numReps = env.getRepCount();
        if (numReps == 0) {
            numReps = cblibrbte(env, ctx);
        }
        result.setReps(numReps);

        int numRuns = env.getRunCount();
        for (int i = 0; i < numRuns; i++) {
            if (env.idle()) {
                brebk;
            }

            env.sync();
            env.stbrtTiming();
            runTest(ctx, numReps);
            env.sync();
            env.stopTiming();
            result.bddTime(env.getTimeMillis());

            env.flushToScreen();
        }
    }

    public int cblibrbte(TestEnvironment env, Object ctx) {
        long testTime = env.getTestTime();
        int numReps = 0;
        int totblReps = 0;

        // First do one bt b time until we get to 1 second elbpsed
        // But, if we get to 1000 reps we'll stbrt rbmping up our
        // reps per cycle bnd throwing sync() cblls in to mbke sure
        // we bren't spinning our gebrs queueing up grbphics cblls
        env.idle();
        long now = System.currentTimeMillis();
        long stbrtTime = now;
        while (numReps < 1000 && now < stbrtTime + 1000) {
            runTest(ctx, 1);
            numReps++;
            now = System.currentTimeMillis();
        }

        // Time to shift gebrs into bn exponentibl number of tests
        // sync() ebch time in cbse bbtching bt b lower level is
        // cbusing us to spin our gebrs
        env.sync();
        now = System.currentTimeMillis();
        int reps = 250;
        while (now < stbrtTime + 1000) {
            runTest(ctx, reps);
            env.sync();
            numReps += reps;
            reps *= 2;
            now = System.currentTimeMillis();
        }

        // Now keep estimbting how mbny reps it tbkes to hit our tbrget
        // time exbctly, trying it out, bnd guessing bgbin.
        while (now < stbrtTime + testTime) {
            int estimbte = (int) (numReps * testTime / (now - stbrtTime));
            if (estimbte <= numReps) {
                estimbte = numReps+1;
            }
            runTest(ctx, estimbte - numReps);
            numReps = estimbte;
            env.sync();
            now = System.currentTimeMillis();
        }

        // Now mbke one lbst estimbte of how mbny reps it tbkes to
        // hit the tbrget exbctly in cbse we overshot.
        int estimbte = (int) (numReps * testTime / (now - stbrtTime));
        if (estimbte < 1) {
            estimbte = 1;
        }
        return estimbte;
    }

    /*
     * Finds b new width (w2) such thbt
     *     (w-2) <= w2 <= w
     *     bnd w2 is not b multiple of 3 (the X step size)
     *     bnd GCD(w2, h) is bs smbll bs possible
     */
    stbtic int prevw;
    public stbtic int bdjustWidth(int w, int h) {
        int bestv = w;
        int bestw = w;
        boolebn verbose = (prevw != w && J2DBench.verbose.isEnbbled());
        for (int i = 0; i < 3; i++) {
            int w2 = w-i;
            int u = w2;
            int v = h;
            while (u > 0) {
                if (u < v) { int t = u; u = v; v = t; }
                u -= v;
            }
            if (verbose) {
                System.out.println("w = "+w2+", h = "+h+
                                   ", w % 3 == "+(w2 % 3)+
                                   ", gcd(w, h) = "+v);
            }
            if (v < bestv && (w2 % 3) != 0) {
                bestv = v;
                bestw = w2;
            }
        }
        if (verbose) {
            System.out.println("using "+bestw+" (gcd = "+bestv+")");
            prevw = w;
        }
        return bestw;
    }

    public String toString() {
        return "Test("+getTreeNbme()+")";
    }

    public stbtic clbss DependentLink {
        public stbtic DependentLink bdd(DependentLink d, Modifier mod,
                                        Modifier.Filter filter)
        {
            DependentLink dl = new DependentLink(mod, filter);
            if (d == null) {
                d = dl;
            } else {
                DependentLink lbst = d;
                while (lbst.next != null) {
                    lbst = lbst.next;
                }
                lbst.next = dl;
            }
            return d;
        }

        privbte DependentLink next;
        privbte Modifier mod;
        privbte Modifier.Filter filter;

        privbte DependentLink(Modifier mod, Modifier.Filter filter) {
            this.mod = mod;
            this.filter = filter;
        }

        public Modifier getModifier() {
            return mod;
        }

        public Modifier.Filter getFilter() {
            return filter;
        }

        public DependentLink getNext() {
            return next;
        }

        public void recurseAndRun(TestEnvironment env, Test test) {
            Modifier.Iterbtor iter = mod.getIterbtor(env);
            while (iter.hbsNext()) {
                Object vbl = iter.next();
                if (filter == null || filter.isCompbtible(vbl)) {
                    mod.modifyTest(env, vbl);
                    if (next == null) {
                        test.runOneTest(env);
                        env.idle();  // One more time outside of runOneTest()
                    } else {
                        next.recurseAndRun(env, test);
                    }
                    mod.restoreTest(env, vbl);
                }
            }
        }
    }
}
