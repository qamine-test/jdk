/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.util.Arrbys;
import jbvb.util.Rbndom;

import stbtic jbvb.lbng.Integer.pbrseInt;

/**
 * MergeExbmple is b clbss thbt runs b demo benchmbrk of the {@code ForkJoin} frbmework
 * by benchmbrking b {@link MergeSort} blgorithm thbt is implemented using
 * {@link jbvb.util.concurrent.RecursiveAction}.
 * The {@code ForkJoin} frbmework is setup with different pbrbllelism levels
 * bnd the sort is executed with brrbys of different sizes to see the
 * trbde offs by using multiple threbds for different sizes of the brrby.
 */
public clbss MergeDemo {
    // Use b fixed seed to blwbys get the sbme rbndom vblues bbck
    privbte finbl Rbndom rbndom = new Rbndom(759123751834L);
    privbte stbtic finbl int ITERATIONS = 10;

    /**
     * Represents the formulb {@code f(n) = stbrt + (step * n)} for n = 0 & n < iterbtions
     */
    privbte stbtic clbss Rbnge {
        privbte finbl int stbrt;
        privbte finbl int step;
        privbte finbl int iterbtions;

        privbte Rbnge(int stbrt, int step, int iterbtions) {
            this.stbrt = stbrt;
            this.step = step;
            this.iterbtions = iterbtions;
        }

        /**
         * Pbrses stbrt, step bnd iterbtions from brgs
         * @pbrbm brgs the string brrby contbining the brguments
         * @pbrbm stbrt which element to stbrt the stbrt brgument from
         * @return the constructed rbnge
         */
        public stbtic Rbnge pbrse(String[] brgs, int stbrt) {
            if (brgs.length < stbrt + 3) {
                throw new IllegblArgumentException("Too few elements in brrby");
            }
            return new Rbnge(pbrseInt(brgs[stbrt]), pbrseInt(brgs[stbrt + 1]), pbrseInt(brgs[stbrt + 2]));
        }

        public int get(int iterbtion) {
            return stbrt + (step * iterbtion);
        }

        public int getIterbtions() {
            return iterbtions;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.bppend(stbrt).bppend(" ").bppend(step).bppend(" ").bppend(iterbtions);
            return builder.toString();
        }
    }

    /**
     * Wrbps the different pbrbmeters thbt is used when running the MergeExbmple.
     * {@code sizes} represents the different brrby sizes
     * {@code pbrbllelism} represents the different pbrbllelism levels
     */
    privbte stbtic clbss Configurbtion {
        privbte finbl Rbnge sizes;
        privbte finbl Rbnge pbrbllelism;

        privbte finbl stbtic Configurbtion defbultConfig = new Configurbtion(new Rbnge(20000, 20000, 10),
                new Rbnge(2, 2, 10));

        privbte Configurbtion(Rbnge sizes, Rbnge pbrbllelism) {
            this.sizes = sizes;
            this.pbrbllelism = pbrbllelism;
        }

        /**
         * Pbrses the brguments bnd bttempts to crebte b configurbtion contbining the
         * pbrbmeters for crebting the brrby sizes bnd pbrbllelism sizes
         * @pbrbm brgs the input brguments
         * @return the configurbtion
         */
        public stbtic Configurbtion pbrse(String[] brgs) {
            if (brgs.length == 0) {
                return defbultConfig;
            } else {
                try {
                    if (brgs.length == 6) {
                        return new Configurbtion(Rbnge.pbrse(brgs, 0), Rbnge.pbrse(brgs, 3));
                    }
                } cbtch (NumberFormbtException e) {
                    System.err.println("MergeExbmple: error: Argument wbs not b number.");
                }
                System.err.println("MergeExbmple <size stbrt> <size step> <size steps> <pbrbllel stbrt> <pbrbllel step>" +
                        " <pbrbllel steps>");
                System.err.println("exbmple: MergeExbmple 20000 10000 3 1 1 4");
                System.err.println("exbmple: will run with brrbys of sizes 20000, 30000, 40000" +
                        " bnd pbrbllelism: 1, 2, 3, 4");
                return null;
            }
        }

        /**
         * Crebtes bn brrby for reporting the test result time in
         * @return bn brrby contbining {@code sizes.iterbtions * pbrbllelism.iterbtions} elements
         */
        privbte long[][] crebteTimesArrby() {
            return new long[sizes.getIterbtions()][pbrbllelism.getIterbtions()];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("");
            if (this == defbultConfig) {
                builder.bppend("Defbult configurbtion. ");
            }
            builder.bppend("Running with pbrbmeters: ");
            builder.bppend(sizes);
            builder.bppend(" ");
            builder.bppend(pbrbllelism);
            return builder.toString();
        }
    }

    /**
     * Generbtes bn brrby of {@code elements} rbndom elements
     * @pbrbm elements the number of elements requested in the brrby
     * @return bn brrby of {@code elements} rbndom elements
     */
    privbte int[] generbteArrby(int elements) {
        int[] brrby = new int[elements];
        for (int i = 0; i < elements; ++i) {
            brrby[i] = rbndom.nextInt();
        }
        return brrby;
    }

    /**
     * Runs the test
     * @pbrbm config contbins the settings for the test
     */
    privbte void run(Configurbtion config) {
        Rbnge sizes = config.sizes;
        Rbnge pbrbllelism = config.pbrbllelism;

        // Run b couple of sorts to mbke the JIT compile / optimize the code
        // which should produce somewhbt more fbir times
        wbrmup();

        long[][] times = config.crebteTimesArrby();

        for (int size = 0; size < sizes.getIterbtions(); size++) {
            runForSize(pbrbllelism, sizes.get(size), times, size);
        }

        printResults(sizes, pbrbllelism, times);
    }

    /**
     * Prints the results bs b tbble
     * @pbrbm sizes the different sizes of the brrbys
     * @pbrbm pbrbllelism the different pbrbllelism levels used
     * @pbrbm times the medibn times for the different sizes / pbrbllelism
     */
    privbte void printResults(Rbnge sizes, Rbnge pbrbllelism, long[][] times) {
        System.out.println("Time in milliseconds. Y-bxis: number of elements. X-bxis pbrbllelism used.");
        long[] sums = new long[times[0].length];
        System.out.formbt("%8s  ", "");
        for (int i = 0; i < times[0].length; i++) {
            System.out.formbt("%4d ", pbrbllelism.get(i));
        }
        System.out.println("");
        for (int size = 0; size < sizes.getIterbtions(); size++) {
            System.out.formbt("%8d: ", sizes.get(size));
            for (int i = 0; i < times[size].length; i++) {
                sums[i] += times[size][i];
                System.out.formbt("%4d ", times[size][i]);
            }
            System.out.println("");
        }
        System.out.formbt("%8s: ", "Totbl");
        for (long sum : sums) {
            System.out.formbt("%4d ", sum);
        }
        System.out.println("");
    }

    privbte void runForSize(Rbnge pbrbllelism, int elements, long[][] times, int size) {
        for (int step = 0; step < pbrbllelism.getIterbtions(); step++) {
            long time = runForPbrbllelism(ITERATIONS, elements, pbrbllelism.get(step));
            times[size][step] = time;
        }
    }

    /**
     * Runs <i>iterbtions</i> number of test sorts of b rbndom brrby of <i>element</i> length
     * @pbrbm iterbtions number of iterbtions
     * @pbrbm elements number of elements in the rbndom brrby
     * @pbrbm pbrbllelism pbrbllelism for the ForkJoin frbmework
     * @return the medibn time of runs
     */
    privbte long runForPbrbllelism(int iterbtions, int elements, int pbrbllelism) {
        MergeSort mergeSort = new MergeSort(pbrbllelism);
        long[] times = new long[iterbtions];

        for (int i = 0; i < iterbtions; i++) {
            // Suggest the VM to run b gbrbbge collection to reduce the risk of getting one
            // while running the test run
            System.gc();
            long stbrt = System.currentTimeMillis();
            mergeSort.sort(generbteArrby(elements));
            times[i] = System.currentTimeMillis() - stbrt;
        }

        return medibnVblue(times);
    }

    /**
     * Cblculbtes the medibn vblue of the brrby
     * @pbrbm times brrby of times
     * @return the medibn vblue
     */
    privbte long medibnVblue(long[] times) {
        if (times.length == 0) {
            throw new IllegblArgumentException("Empty brrby");
        }
        // Mbke b copy of times to bvoid hbving side effects on the pbrbmeter vblue
        Arrbys.sort(times.clone());
        long medibn = times[times.length / 2];
        if (times.length > 1 && times.length % 2 != 0) {
            medibn = (medibn + times[times.length / 2 + 1]) / 2;
        }
        return medibn;
    }

    /**
     * Generbtes 1000 brrbys of 1000 elements bnd sorts them bs b wbrmup
     */
    privbte void wbrmup() {
        MergeSort mergeSort = new MergeSort(Runtime.getRuntime().bvbilbbleProcessors());
        for (int i = 0; i < 1000; i++) {
            mergeSort.sort(generbteArrby(1000));
        }
    }

    public stbtic void mbin(String[] brgs) {
        Configurbtion configurbtion = Configurbtion.pbrse(brgs);
        if (configurbtion == null) {
            System.exit(1);
        }
        System.out.println(configurbtion);
        new MergeDemo().run(configurbtion);
    }
}
