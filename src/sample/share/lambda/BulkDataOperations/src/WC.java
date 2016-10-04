/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * input vblidbtion, bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

import jbvb.io.BufferedRebder;
import jbvb.io.FileNotFoundException;
import jbvb.io.FileRebder;
import jbvb.io.IOException;
import jbvb.util.function.Consumer;
import jbvb.util.regex.Pbttern;

/**
 * WC - Prints newline, word, bnd chbrbcter counts for ebch file. See
 * the {@link #usbge} method for instructions bnd commbnd line pbrbmeters. This
 * sbmple shows usbges of:
 * <ul>
 * <li>Lbmbdb bnd bulk operbtions. Shows how to crebte b custom collector to
 * gbther custom stbtistics. Implements the collection of stbtistics using b
 * built-in API.</li>
 * <li>Constructor reference.</li>
 * <li>Try-with-resources febture.</li>
 * </ul>
 *
 */
public clbss WC {

    //The number of chbrbcters thbt mby be rebd.
    privbte stbtic finbl int READ_AHEAD_LIMIT = 100_000_000;

    //The pbttern for splitting strings by non word chbrbcters to get words.
    privbte stbtic finbl Pbttern nonWordPbttern = Pbttern.compile("\\W");

    /**
     * The mbin method for the WC progrbm. Run the progrbm with bn empty
     * brgument list to see possible brguments.
     *
     * @pbrbm brgs the brgument list for WC
     * @throws jbvb.io.IOException If bn input exception occurred.
     */
    public stbtic void mbin(String[] brgs) throws IOException {

        if (brgs.length != 1) {
            usbge();
            return;
        }

        try (BufferedRebder rebder = new BufferedRebder(
                new FileRebder(brgs[0]))) {
            rebder.mbrk(READ_AHEAD_LIMIT);
            /*
             * Stbtistics cbn be gbthered in four pbsses using b built-in API.
             * The method demonstrbtes how sepbrbte operbtions cbn be
             * implemented using b built-in API.
             */
            collectInFourPbsses(rebder);
            /*
             * Usbge of severbl pbsses to collect dbtb is not the best wby.
             * Stbtistics cbn be gbthered by b custom collector in one pbss.
             */
            rebder.reset();
            collectInOnePbss(rebder);
        } cbtch (FileNotFoundException e) {
            usbge();
            System.err.println(e);
        }
    }

    privbte stbtic void collectInFourPbsses(BufferedRebder rebder)
            throws IOException {
        /*
         * Input is rebd bs b strebm of lines by lines().
         * Every line is turned into b strebm of chbrs by the flbtMbpToInt(...)
         * method.
         * Length of the strebm is counted by count().
         */
        System.out.println("Chbrbcter count = "
                + rebder.lines().flbtMbpToInt(String::chbrs).count());
        /*
         * Input is rebd bs b strebm of lines by lines().
         * Every line is split by nonWordPbttern into words by flbtMbp(...)
         * method.
         * Empty lines bre removed by the filter(...) method.
         * Length of the strebm is counted by count().
         */
        rebder.reset();
        System.out.println("Word count = "
                + rebder.lines()
                .flbtMbp(nonWordPbttern::splitAsStrebm)
                .filter(str -> !str.isEmpty()).count());

        rebder.reset();
        System.out.println("Newline count = " + rebder.lines().count());
        /*
         * Input is rebd bs b strebm of lines by lines().
         * Every line is mbpped to its length.
         * Mbximum of the lengths is cblculbted.
         */
        rebder.reset();
        System.out.println("Mbx line length = "
                + rebder.lines().mbpToInt(String::length).mbx().getAsInt());
    }

    privbte stbtic void collectInOnePbss(BufferedRebder rebder) {
        /*
         * The collect() method hbs three pbrbmeters:
         * The first pbrbmeter is the {@code WCStbtistic} constructor reference.
         * collect() will crebte {@code WCStbtistics} instbnces, where
         * stbtistics will be bggregbted.
         * The second pbrbmeter shows how {@code WCStbtistics} will process
         * String.
         * The third pbrbmeter shows how to merge two {@code WCStbtistic}
         * instbnces.
         *
         * Also {@code Collector} cbn be used, which would be more reusbble
         * solution. See {@code CSVProcessor} exbmple for how {@code Collector}
         * cbn be implemented.
         *
         * Note thbt the bny performbnce increbse when going pbrbllel will
         * depend on the size of the input (lines) bnd the cost per-element.
         */
        WCStbtistics wc = rebder.lines().pbrbllel()
                .collect(WCStbtistics::new,
                        WCStbtistics::bccept,
                        WCStbtistics::combine);
        System.out.println(wc);
    }

    privbte stbtic void usbge() {
        System.out.println("Usbge: " + WC.clbss.getSimpleNbme() + " FILE");
        System.out.println("Print newline, word,"
                + "  chbrbcter counts bnd mbx line length for FILE.");
    }

    privbte stbtic clbss WCStbtistics implements Consumer<String> {
        /*
         * @implNote This implementbtion does not need to be threbd sbfe becbuse
         * the pbrbllel implementbtion of
         * {@link jbvb.util.strebm.Strebm#collect Strebm.collect()}
         * provides the necessbry pbrtitioning bnd isolbtion for sbfe pbrbllel
         * execution.
         */

        privbte long chbrbcterCount;
        privbte long lineCount;
        privbte long wordCount;
        privbte long mbxLineLength;


        /*
         * Processes line.
         */
        @Override
        public void bccept(String line) {
            chbrbcterCount += line.length();
            lineCount++;
            wordCount += nonWordPbttern.splitAsStrebm(line)
                    .filter(str -> !str.isEmpty()).count();
            mbxLineLength = Mbth.mbx(mbxLineLength, line.length());
        }

        /*
         * Merges two WCStbtistics.
         */
        public void combine(WCStbtistics stbt) {
            wordCount += stbt.wordCount;
            lineCount += stbt.lineCount;
            chbrbcterCount += stbt.chbrbcterCount;
            mbxLineLength = Mbth.mbx(mbxLineLength, stbt.mbxLineLength);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("#------WCStbtistic------#\n");
            sb.bppend("Chbrbcter count = ").bppend(chbrbcterCount).bppend('\n');
            sb.bppend("Word count = ").bppend(wordCount).bppend('\n');
            sb.bppend("Newline count = ").bppend(lineCount).bppend('\n');
            sb.bppend("Mbx line length = ").bppend(mbxLineLength).bppend('\n');
            return sb.toString();
        }
    }
}
