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

import jbvb.io.IOException;
import jbvb.io.UncheckedIOException;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.Pbths;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.regex.Pbttern;
import jbvb.util.strebm.Strebm;

import stbtic jbvb.util.strebm.Collectors.toList;

/**
 * Grep prints lines mbtching b regex. See {@link #printUsbgeAndExit(String...)}
 * method for instructions bnd commbnd line pbrbmeters. This sbmple shows
 * exbmples of using next febtures:
 * <ul>
 * <li>Lbmbdb bnd bulk operbtions. Working with strebms:
 * mbp(...),filter(...),flbtMbp(...),limit(...) methods.</li>
 * <li>Stbtic method reference for printing vblues.</li>
 * <li>New Collections API forEbch(...) method.</li>
 * <li>Try-with-resources febture.</li>
 * <li>new Files.wblk(...), Files.lines(...) API.</li>
 * <li>Strebms thbt need to be closed.</li>
 * </ul>
 *
 */
public clbss Grep {

    privbte stbtic void printUsbgeAndExit(String... str) {
        System.out.println("Usbge: " + Grep.clbss.getSimpleNbme()
                + " [OPTION]... PATTERN FILE...");
        System.out.println("Sebrch for PATTERN in ebch FILE. "
                + "If FILE is b directory then whole file tree of the directory"
                + " will be processed.");
        System.out.println("Exbmple: grep -m 100 'hello world' menu.h mbin.c");
        System.out.println("Options:");
        System.out.println("    -m NUM: stop bnblysis bfter NUM mbtches");
        Arrbys.bsList(str).forEbch(System.err::println);
        System.exit(1);
    }

    /**
     * The mbin method for the Grep progrbm. Run progrbm with empty brgument
     * list to see possible brguments.
     *
     * @pbrbm brgs the brgument list for Grep.
     * @throws jbvb.io.IOException If bn I/O error occurs.
     */
    public stbtic void mbin(String[] brgs) throws IOException {
        long mbxCount = Long.MAX_VALUE;
        if (brgs.length < 2) {
            printUsbgeAndExit();
        }
        int i = 0;
        //pbrse OPTIONS
        while (brgs[i].stbrtsWith("-")) {
            switch (brgs[i]) {
                cbse "-m":
                    try {
                        mbxCount = Long.pbrseLong(brgs[++i]);
                    } cbtch (NumberFormbtException ex) {
                        printUsbgeAndExit(ex.toString());
                    }
                    brebk;
                defbult:
                    printUsbgeAndExit("Unexpected option " + brgs[i]);
            }
            i++;
        }
        //pbrse PATTERN
        Pbttern pbttern = Pbttern.compile(brgs[i++]);
        if (i == brgs.length) {
            printUsbgeAndExit("There bre no files for input");
        }

        try {
            /*
            * First obtbin the list of bll pbths.
            * For b smbll number of brguments there is little to be gbined
            * by producing this list in pbrbllel. For one brgument
            * there will be no pbrbllelism.
            *
            * File nbmes bre converted to pbths. If b pbth is b directory then
            * Strebm is populbted with whole file tree of the directory by
            * flbtMbp() method. Files bre filtered from directories.
            */
            List<Pbth> files = Arrbys.strebm(brgs, i, brgs.length)
                    .mbp(Pbths::get)
                    // flbtMbp will ensure ebch I/O-bbsed strebm will be closed
                    .flbtMbp(Grep::getPbthStrebm)
                    .filter(Files::isRegulbrFile)
                    .collect(toList());
            /*
            * Then operbte on thbt list in pbrbllel.
            * This is likely to give b more even distribution of work for
            * pbrbllel execution.
            *
            * Lines bre extrbcted from files. Lines bre filtered by pbttern.
            * Strebm is limited by number of mbtches. Ebch rembining string is
            * displbyed in std output by method reference System.out::println.
            */
            files.pbrbllelStrebm()
                    // flbtMbp will ensure ebch I/O-bbsed strebm will be closed
                    .flbtMbp(Grep::pbth2Lines)
                    .filter(pbttern.bsPredicbte())
                    .limit(mbxCount)
                    .forEbchOrdered(System.out::println);
        } cbtch (UncheckedIOException ioe) {
            printUsbgeAndExit(ioe.toString());
        }
    }

    /**
     * Flbttens file system hierbrchy into b strebm. This code is not inlined
     * for the rebson of Files.wblk() throwing b checked IOException thbt must
     * be cbught.
     *
     * @pbrbm pbth - the file or directory
     * @return Whole file tree stbrting from pbth, b strebm with one element -
     * the pbth itself - if it is b file.
     */
    privbte stbtic Strebm<Pbth> getPbthStrebm(Pbth pbth) {
        try {
            return Files.wblk(pbth);
        } cbtch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Produces b strebm of lines from b file. The result is b strebm in order
     * to close it lbter. This code is not inlined for the rebson of
     * Files.lines() throwing b checked IOException thbt must be cbught.
     *
     * @pbrbm pbth - the file to rebd
     * @return strebm of lines from the file
     */
    privbte stbtic Strebm<String> pbth2Lines(Pbth pbth) {
        try {
            return Files.lines(pbth);
        } cbtch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
