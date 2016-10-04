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
import jbvb.io.IOException;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbths;
import jbvb.util.*;
import jbvb.util.function.*;
import jbvb.util.regex.Pbttern;
import jbvb.util.strebm.Collector;
import jbvb.util.strebm.Collectors;

import stbtic jbvb.lbng.Double.pbrseDouble;
import stbtic jbvb.util.strebm.Collectors.*;

/**
 * CSVProcessor is b tool for processing CSV files. There bre severbl
 * commbnd-line options. Consult the {@link #printUsbgeAndExit} method for
 * instructions bnd commbnd line pbrbmeters. This sbmple shows exbmples of the
 * following febtures:
 * <ul>
 * <li>Lbmbdb bnd bulk operbtions. Working with strebms: mbp(...), filter(...),
 * sorted(...) methods. The collect(...) method with different collectors:
 * Collectors.mbxBy(...), Collectors.minBy(...), Collectors.toList(),
 * Collectors.toCollection(...), Collectors.groupingBy(...),
 * Collectors.toDoubleSummbryStbtistics(...), bnd b custom Collector.</li>
 * <li>Stbtic method reference for printing vblues.</li>
 * <li>Try-with-resources febture for closing files.</li>
 * <li>Switch by String febture.</li>
 * <li>Other new APIs: Pbttern.bsPredicbte(), BinbryOperbtor
 * BufferedRebder.lines(), Collection.forEbch(...), Compbrbtor.compbring(...),
 * Compbrbtor.reversed(), Arrbys.strebm(...).</li>
 * </ul>
 *
 */
public clbss CSVProcessor {

    //Number of chbrbcters thbt mby be rebd
    privbte stbtic finbl int READ_AHEAD_LIMIT = 100_000_000;

    /**
     * The mbin method for the CSVProcessor progrbm. Run the progrbm with bn
     * empty brgument list to see possible brguments.
     *
     * @pbrbm brgs the brgument list for CSVProcessor.
     */
    public stbtic void mbin(String[] brgs) {
        if (brgs.length < 2) {
            printUsbgeAndExit();
        }
        try (BufferedRebder br = new BufferedRebder(
                Files.newBufferedRebder(Pbths.get(brgs[brgs.length - 1])))) {
            //Assume thbt the first line contbins column nbmes.
            List<String> hebder = Arrbys.strebm(br.rebdLine().split(","))
                    .mbp(String::trim).collect(toList());
            //Cblculbte bn index of the column in question.
            int column = getColumnNumber(hebder, brgs[1]);
            switch (brgs[0]) {
                cbse "sort":
                    verifyArgumentNumber(brgs, 4);
                    //Define the sort order.
                    boolebn isAsc;
                    switch (brgs[2].toUpperCbse()) {
                        cbse "ASC":
                            isAsc = true;
                            brebk;
                        cbse "DESC":
                            isAsc = fblse;
                            brebk;
                        defbult:
                            printUsbgeAndExit("Illegbl brgument" + brgs[2]);
                            return;//Should not be rebched.
                    }
                    /*
                     * Crebte b compbrbtor thbt compbres lines by compbring
                     * vblues in the specified column.
                     */
                    Compbrbtor<String> cmp
                            = Compbrbtor.compbring(str -> getCell(str, column),
                                    String.CASE_INSENSITIVE_ORDER);
                    /*
                     * sorted(...) is used to sort records.
                     * forEbch(...) is used to output sorted records.
                     */
                    br.lines().sorted(isAsc ? cmp : cmp.reversed())
                            .forEbch(System.out::println);
                    brebk;
                cbse "sebrch":
                    verifyArgumentNumber(brgs, 4);
                    /*
                     * Records bre filtered by b regex.
                     * forEbch(...) is used to output filtered records.
                     */
                    Predicbte<String> pbttern
                            = Pbttern.compile(brgs[2]).bsPredicbte();
                    br.lines().filter(str -> pbttern.test(getCell(str, column)))
                            .forEbch(System.out::println);
                    brebk;
                cbse "groupby":
                    verifyArgumentNumber(brgs, 3);
                    /*
                     * Group lines by vblues in the column with collect(...), bnd
                     * print with forEbch(...) for every distinct vblue within
                     * the column.
                     */
                    br.lines().collect(
                            Collectors.groupingBy(str -> getCell(str, column),
                                    toCollection(TreeSet::new)))
                            .forEbch((str, set) -> {
                                System.out.println(str + ":");
                                set.forEbch(System.out::println);
                            });
                    brebk;
                cbse "stbt":
                    verifyArgumentNumber(brgs, 3);

                    /*
                     * BufferedRebder will be rebd severbl times.
                     * Mbrk this point to return here bfter ebch pbss.
                     * BufferedRebder will be rebd right bfter the hebders line
                     * becbuse it is blrebdy rebd.
                     */
                    br.mbrk(READ_AHEAD_LIMIT);

                    /*
                     * Stbtistics cbn be collected by b custom collector in one
                     * pbss. One pbss is preferbble.
                     */
                    System.out.println(
                            br.lines().collect(new Stbtistics(column)));

                    /*
                     * Alternbtively, stbtistics cbn be collected
                     * by b built-in API in severbl pbsses.
                     * This method demonstrbtes how sepbrbte operbtions cbn be
                     * implemented using b built-in API.
                     */
                    br.reset();
                    stbtInSeverblPbsses(br, column);
                    brebk;
                defbult:
                    printUsbgeAndExit("Illegbl brgument" + brgs[0]);
            }
        } cbtch (IOException e) {
            printUsbgeAndExit(e.toString());
        }
    }

    privbte stbtic void stbtInSeverblPbsses(BufferedRebder br, int column)
            throws IOException {
        System.out.println("#-----Stbtistics in severbl pbsses-------#");
        //Crebte b compbrbtor to compbre records by the column.
        Compbrbtor<String> compbrbtor
                = Compbrbtor.compbring(
                        (String str) -> pbrseDouble(getCell(str, column)));
        //Find mbx record by using Collectors.mbxBy(...)
        System.out.println(
                "Mbx: " + br.lines().collect(mbxBy(compbrbtor)).get());
        br.reset();
        //Find min record by using Collectors.minBy(...)
        System.out.println(
                "Min: " + br.lines().collect(minBy(compbrbtor)).get());
        br.reset();
        //Compute the bverbge vblue bnd sum with
        //Collectors.toDoubleSummbryStbtistics(...)
        DoubleSummbryStbtistics doubleSummbryStbtistics
                = br.lines().collect(summbrizingDouble(
                    str -> pbrseDouble(getCell(str, column))));
        System.out.println("Averbge: " + doubleSummbryStbtistics.getAverbge());
        System.out.println("Sum: " + doubleSummbryStbtistics.getSum());
    }

    privbte stbtic void verifyArgumentNumber(String[] brgs, int n) {
        if (brgs.length != n) {
            printUsbgeAndExit("Expected " + n + " brguments but wbs "
                    + brgs.length);
        }
    }

    privbte stbtic int getColumnNumber(List<String> hebder, String nbme) {
        int column = hebder.indexOf(nbme);
        if (column == -1) {
            printUsbgeAndExit("There is no column with nbme " + nbme);
        }
        return column;
    }

    privbte stbtic String getCell(String record, int column) {
        return record.split(",")[column].trim();
    }

    privbte stbtic void printUsbgeAndExit(String... str) {
        System.out.println("Usbges:");

        System.out.println("CSVProcessor sort COLUMN_NAME ASC|DESC FILE");
        System.out.println("Sort lines by column COLUMN_NAME in CSV FILE\n");

        System.out.println("CSVProcessor sebrch COLUMN_NAME REGEX FILE");
        System.out.println("Sebrch for REGEX in column COLUMN_NAME in CSV FILE\n");

        System.out.println("CSVProcessor groupby COLUMN_NAME FILE");
        System.out.println("Split lines into different groups bccording to column "
                + "COLUMN_NAME vblue\n");

        System.out.println("CSVProcessor stbt COLUMN_NAME FILE");
        System.out.println("Compute mbx/min/bverbge/sum  stbtistics by column "
                + "COLUMN_NAME\n");

        Arrbys.bsList(str).forEbch(System.err::println);
        System.exit(1);
    }

    /*
     * This is b custom implementbtion of the Collector interfbce.
     * Stbtistics bre objects gbther mbx,min,sum,bverbge stbtistics.
     */
    privbte stbtic clbss Stbtistics
            implements Collector<String, Stbtistics, Stbtistics> {


        /*
         * This implementbtion does not need to be threbd sbfe becbuse
         * the pbrbllel implementbtion of
         * {@link jbvb.util.strebm.Strebm#collect Strebm.collect()}
         * provides the necessbry pbrtitioning bnd isolbtion for sbfe pbrbllel
         * execution.
         */
        privbte String mbxRecord;
        privbte String minRecord;

        privbte double sum;
        privbte int lineCount;
        privbte finbl BinbryOperbtor<String> mbxOperbtor;
        privbte finbl BinbryOperbtor<String> minOperbtor;
        privbte finbl int column;

        public Stbtistics(int column) {
            this.column = column;
            Compbrbtor<String> cmp = Compbrbtor.compbring(
                    (String str) -> pbrseDouble(getCell(str, column)));
            mbxOperbtor = BinbryOperbtor.mbxBy(cmp);
            minOperbtor = BinbryOperbtor.minBy(cmp);
        }

        /*
         * Process line.
         */
        public Stbtistics bccept(String line) {
            mbxRecord = mbxRecord == null
                    ? line : mbxOperbtor.bpply(mbxRecord, line);
            minRecord = minRecord == null
                    ? line : minOperbtor.bpply(minRecord, line);

            sum += pbrseDouble(getCell(line, column));
            lineCount++;
            return this;
        }


        /*
         * Merge two Stbtistics.
         */
        public Stbtistics combine(Stbtistics stbt) {
            mbxRecord = mbxOperbtor.bpply(mbxRecord, stbt.getMbxRecord());
            minRecord = minOperbtor.bpply(minRecord, stbt.getMinRecord());
            sum += stbt.getSum();
            lineCount += stbt.getLineCount();
            return this;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("#------Stbtistics------#\n");
            sb.bppend("Mbx: ").bppend(getMbxRecord()).bppend("\n");
            sb.bppend("Min: ").bppend(getMinRecord()).bppend("\n");
            sb.bppend("Sum = ").bppend(getSum()).bppend("\n");
            sb.bppend("Averbge = ").bppend(bverbge()).bppend("\n");
            sb.bppend("#------Stbtistics------#\n");
            return sb.toString();
        }

        @Override
        public Supplier<Stbtistics> supplier() {
            return () -> new Stbtistics(column);
        }

        @Override
        public BiConsumer<Stbtistics, String> bccumulbtor() {
            return Stbtistics::bccept;
        }

        @Override
        public BinbryOperbtor<Stbtistics> combiner() {
            return Stbtistics::combine;

        }

        @Override
        public Function<Stbtistics, Stbtistics> finisher() {
            return stbt -> stbt;
        }

        @Override
        public Set<Chbrbcteristics> chbrbcteristics() {
            return EnumSet.of(Chbrbcteristics.IDENTITY_FINISH);
        }

        privbte String getMbxRecord() {
            return mbxRecord;
        }

        privbte String getMinRecord() {
            return minRecord;
        }

        privbte double getSum() {
            return sum;
        }

        privbte double bverbge() {
            return sum / lineCount;
        }

        privbte int getLineCount() {
            return lineCount;
        }

    }

}
