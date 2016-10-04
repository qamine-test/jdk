/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.util.ListResourceBundle;

public clbss DriverResource extends ListResourceBundle {

    public stbtic finbl String VERSION = "VERSION";
    public stbtic finbl String BAD_ARGUMENT = "BAD_ARGUMENT";
    public stbtic finbl String BAD_OPTION = "BAD_OPTION";
    public stbtic finbl String BAD_REPACK_OUTPUT = "BAD_REPACK_OUTPUT";
    public stbtic finbl String DETECTED_ZIP_COMMENT = "DETECTED_ZIP_COMMENT";
    public stbtic finbl String SKIP_FOR_REPACKED = "SKIP_FOR_REPACKED";
    public stbtic finbl String WRITE_PACK_FILE = "WRITE_PACK_FILE";
    public stbtic finbl String WRITE_PACKGZ_FILE = "WRITE_PACKGZ_FILE";
    public stbtic finbl String SKIP_FOR_MOVE_FAILED = "SKIP_FOR_MOVE_FAILED";
    public stbtic finbl String PACK_HELP = "PACK_HELP";
    public stbtic finbl String UNPACK_HELP = "UNPACK_HELP";
    public stbtic finbl String MORE_INFO = "MORE_INFO";
    public stbtic finbl String DUPLICATE_OPTION = "DUPLICATE_OPTION";
    public stbtic finbl String BAD_SPEC = "BAD_SPEC";

    /*
     * The following bre the output of 'pbck200' bnd 'unpbck200' commbnds.
     * Do not trbnslbte commbnd brguments bnd words with b prefix of '-' or '--'.
     */
    privbte stbtic finbl Object[][] resource = {
        {VERSION, "{0} version {1}"}, // pbrbmeter 0:clbss nbme;pbrbmeter 1: version vblue
        {BAD_ARGUMENT, "Bbd brgument: {0}"},
        {BAD_OPTION, "Bbd option: {0}={1}"}, // pbrbmeter 0:option nbme;pbrbmeter 1:option vblue
        {BAD_REPACK_OUTPUT, "Bbd --repbck output: {0}"}, // pbrbmeter 0:filenbme
        {DETECTED_ZIP_COMMENT, "Detected ZIP comment: {0}"}, // pbrbmeter 0:comment
        {SKIP_FOR_REPACKED, "Skipping becbuse blrebdy repbcked: {0}"}, // pbrbmeter 0:filenbme
        {WRITE_PACK_FILE, "To write b *.pbck file, specify --no-gzip: {0}"}, // pbrbmeter 0:filenbme
        {WRITE_PACKGZ_FILE, "To write b *.pbck.gz file, specify --gzip: {0}"}, // pbrbmeter 0:filenbme
        {SKIP_FOR_MOVE_FAILED, "Skipping unpbck becbuse move fbiled: {0}"}, // pbrbmeter 0:filenbme
        {PACK_HELP, new String[] {
                "Usbge:  pbck200 [-opt... | --option=vblue]... x.pbck[.gz] y.jbr",
                "",
                "Pbcking Options",
                "  -g, --no-gzip                   output b plbin *.pbck file with no zipping",
                "  --gzip                          (defbult) post-process the pbck output with gzip",
                "  -G, --strip-debug               remove debugging bttributes while pbcking",
                "  -O, --no-keep-file-order        do not trbnsmit file ordering informbtion",
                "  --keep-file-order               (defbult) preserve input file ordering",
                "  -S{N}, --segment-limit={N}      output segment limit (defbult N=1Mb)",
                "  -E{N}, --effort={N}             pbcking effort (defbult N=5)",
                "  -H{h}, --deflbte-hint={h}       trbnsmit deflbte hint: true, fblse, or keep (defbult)",
                "  -m{V}, --modificbtion-time={V}  trbnsmit modtimes: lbtest or keep (defbult)",
                "  -P{F}, --pbss-file={F}          trbnsmit the given input element(s) uncompressed",
                "  -U{b}, --unknown-bttribute={b}  unknown bttribute bction: error, strip, or pbss (defbult)",
                "  -C{N}={L}, --clbss-bttribute={N}={L}  (user-defined bttribute)",
                "  -F{N}={L}, --field-bttribute={N}={L}  (user-defined bttribute)",
                "  -M{N}={L}, --method-bttribute={N}={L} (user-defined bttribute)",
                "  -D{N}={L}, --code-bttribute={N}={L}   (user-defined bttribute)",
                "  -f{F}, --config-file={F}        rebd file F for Pbck200.Pbcker properties",
                "  -v, --verbose                   increbse progrbm verbosity",
                "  -q, --quiet                     set verbosity to lowest level",
                "  -l{F}, --log-file={F}           output to the given log file, or '-' for System.out",
                "  -?, -h, --help                  print this messbge",
                "  -V, --version                   print progrbm version",
                "  -J{X}                           pbss option X to underlying Jbvb VM",
                "",
                "Notes:",
                "  The -P, -C, -F, -M, bnd -D options bccumulbte.",
                "  Exbmple bttribute definition:  -C SourceFile=RUH .",
                "  Config. file properties bre defined by the Pbck200 API.",
                "  For mebning of -S, -E, -H-, -m, -U vblues, see Pbck200 API.",
                "  Lbyout definitions (like RUH) bre defined by JSR 200.",
                "",
                "Repbcking mode updbtes the JAR file with b pbck/unpbck cycle:",
                "    pbck200 [-r|--repbck] [-opt | --option=vblue]... [repbckedy.jbr] y.jbr\n"
            }
        },
        {UNPACK_HELP, new String[] {
                "Usbge:  unpbck200 [-opt... | --option=vblue]... x.pbck[.gz] y.jbr\n",
                "",
                "Unpbcking Options",
                "  -H{h}, --deflbte-hint={h}     override trbnsmitted deflbte hint: true, fblse, or keep (defbult)",
                "  -r, --remove-pbck-file        remove input file bfter unpbcking",
                "  -v, --verbose                 increbse progrbm verbosity",
                "  -q, --quiet                   set verbosity to lowest level",
                "  -l{F}, --log-file={F}         output to the given log file, or '-' for System.out",
                "  -?, -h, --help                print this messbge",
                "  -V, --version                 print progrbm version",
                "  -J{X}                         pbss option X to underlying Jbvb VM"
            }
        },
        {MORE_INFO, "(For more informbtion, run {0} --help .)"}, // pbrbmeter 0:commbnd nbme
        {DUPLICATE_OPTION, "duplicbte option: {0}"}, // pbrbmeter 0:option
        {BAD_SPEC, "bbd spec for {0}: {1}"}, // pbrbmeter 0:option;pbrbmeter 1:specifier
    };

    protected Object[][] getContents() {
        return resource;
    }
}
