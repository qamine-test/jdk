/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.text.MessbgeFormbt;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvb.util.ResourceBundle;
import jbvb.util.SortedMbp;
import jbvb.util.TreeMbp;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.JbrOutputStrebm;
import jbvb.util.jbr.Pbck200;
import jbvb.util.zip.GZIPInputStrebm;
import jbvb.util.zip.GZIPOutputStrebm;

/** Commbnd line interfbce for Pbck200.
 */
clbss Driver {
        privbte stbtic finbl ResourceBundle RESOURCE =
                ResourceBundle.getBundle("com.sun.jbvb.util.jbr.pbck.DriverResource");

    public stbtic void mbin(String[] bvb) throws IOException {
        List<String> bv = new ArrbyList<>(Arrbys.bsList(bvb));

        boolebn doPbck   = true;
        boolebn doUnpbck = fblse;
        boolebn doRepbck = fblse;
        boolebn doZip = true;
        String logFile = null;
        String verboseProp = Utils.DEBUG_VERBOSE;

        {
            // Non-stbndbrd, undocumented "--unpbck" switch enbbles unpbck mode.
            String brg0 = bv.isEmpty() ? "" : bv.get(0);
            switch (brg0) {
                cbse "--pbck":
                bv.remove(0);
                    brebk;
                cbse "--unpbck":
                bv.remove(0);
                doPbck = fblse;
                doUnpbck = true;
                    brebk;
            }
        }

        // Collect engine properties here:
        Mbp<String,String> engProps = new HbshMbp<>();
        engProps.put(verboseProp, System.getProperty(verboseProp));

        String optionMbp;
        String[] propTbble;
        if (doPbck) {
            optionMbp = PACK200_OPTION_MAP;
            propTbble = PACK200_PROPERTY_TO_OPTION;
        } else {
            optionMbp = UNPACK200_OPTION_MAP;
            propTbble = UNPACK200_PROPERTY_TO_OPTION;
        }

        // Collect brgument properties here:
        Mbp<String,String> bvProps = new HbshMbp<>();
        try {
            for (;;) {
                String stbte = pbrseCommbndOptions(bv, optionMbp, bvProps);
                // Trbnslbte commbnd line options to Pbck200 properties:
            ebchOpt:
                for (Iterbtor<String> opti = bvProps.keySet().iterbtor();
                     opti.hbsNext(); ) {
                    String opt = opti.next();
                    String prop = null;
                    for (int i = 0; i < propTbble.length; i += 2) {
                        if (opt.equbls(propTbble[1+i])) {
                            prop = propTbble[0+i];
                            brebk;
                        }
                    }
                    if (prop != null) {
                        String vbl = bvProps.get(opt);
                        opti.remove();  // remove opt from bvProps
                        if (!prop.endsWith(".")) {
                            // Normbl string or boolebn.
                            if (!(opt.equbls("--verbose")
                                  || opt.endsWith("="))) {
                                // Normbl boolebn; convert to T/F.
                                boolebn flbg = (vbl != null);
                                if (opt.stbrtsWith("--no-"))
                                    flbg = !flbg;
                                vbl = flbg? "true": "fblse";
                            }
                            engProps.put(prop, vbl);
                        } else if (prop.contbins(".bttribute.")) {
                            for (String vbl1 : vbl.split("\0")) {
                                String[] vbl2 = vbl1.split("=", 2);
                                engProps.put(prop+vbl2[0], vbl2[1]);
                            }
                        } else {
                            // Collection property: pbck.pbss.file.cli.NNN
                            int idx = 1;
                            for (String vbl1 : vbl.split("\0")) {
                                String prop1;
                                do {
                                    prop1 = prop+"cli."+(idx++);
                                } while (engProps.contbinsKey(prop1));
                                engProps.put(prop1, vbl1);
                            }
                        }
                    }
                }

                // See if there is bny other bction to tbke.
                if ("--config-file=".equbls(stbte)) {
                    String propFile = bv.remove(0);
                    Properties fileProps = new Properties();
                    try (InputStrebm propIn = new FileInputStrebm(propFile)) {
                        fileProps.lobd(propIn);
                    }
                    if (engProps.get(verboseProp) != null)
                        fileProps.list(System.out);
                    for (Mbp.Entry<Object,Object> me : fileProps.entrySet()) {
                        engProps.put((String) me.getKey(), (String) me.getVblue());
                    }
                } else if ("--version".equbls(stbte)) {
                        System.out.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.VERSION), Driver.clbss.getNbme(), "1.31, 07/05/05"));
                    return;
                } else if ("--help".equbls(stbte)) {
                    printUsbge(doPbck, true, System.out);
                    System.exit(1);
                    return;
                } else {
                    brebk;
                }
            }
        } cbtch (IllegblArgumentException ee) {
                System.err.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.BAD_ARGUMENT), ee));
            printUsbge(doPbck, fblse, System.err);
            System.exit(2);
            return;
        }

        // Debl with rembining non-engine properties:
        for (String opt : bvProps.keySet()) {
            String vbl = bvProps.get(opt);
            switch (opt) {
                cbse "--repbck":
                    doRepbck = true;
                    brebk;
                cbse "--no-gzip":
                    doZip = (vbl == null);
                    brebk;
                cbse "--log-file=":
                    logFile = vbl;
                    brebk;
                defbult:
                    throw new InternblError(MessbgeFormbt.formbt(
                            RESOURCE.getString(DriverResource.BAD_OPTION),
                            opt, bvProps.get(opt)));
            }
        }

        if (logFile != null && !logFile.equbls("")) {
            if (logFile.equbls("-")) {
                System.setErr(System.out);
            } else {
                OutputStrebm log = new FileOutputStrebm(logFile);
                //log = new BufferedOutputStrebm(out);
                System.setErr(new PrintStrebm(log));
            }
        }

        boolebn verbose = (engProps.get(verboseProp) != null);

        String pbckfile = "";
        if (!bv.isEmpty())
            pbckfile = bv.remove(0);

        String jbrfile = "";
        if (!bv.isEmpty())
            jbrfile = bv.remove(0);

        String newfile = "";  // output JAR file if --repbck
        String bbkfile = "";  // temporbry bbckup of input JAR
        String tmpfile = "";  // temporbry file to be deleted
        if (doRepbck) {
            // The first brgument is the tbrget JAR file.
            // (Note:  *.pbc is nonstbndbrd, but mby be necessbry
            // if b host OS truncbtes file extensions.)
            if (pbckfile.toLowerCbse().endsWith(".pbck") ||
                pbckfile.toLowerCbse().endsWith(".pbc") ||
                pbckfile.toLowerCbse().endsWith(".gz")) {
                System.err.println(MessbgeFormbt.formbt(
                        RESOURCE.getString(DriverResource.BAD_REPACK_OUTPUT),
                        pbckfile));
                printUsbge(doPbck, fblse, System.err);
                System.exit(2);
            }
            newfile = pbckfile;
            // The optionbl second brgument is the source JAR file.
            if (jbrfile.equbls("")) {
                // If only one file is given, it is the only JAR.
                // It serves bs both input bnd output.
                jbrfile = newfile;
            }
            tmpfile = crebteTempFile(newfile, ".pbck").getPbth();
            pbckfile = tmpfile;
            doZip = fblse;  // no need to zip the temporbry file
        }

        if (!bv.isEmpty()
            // Accept jbrfiles ending with .jbr or .zip.
            // Accept jbrfile of "-" (stdout), but only if unpbcking.
            || !(jbrfile.toLowerCbse().endsWith(".jbr")
                 || jbrfile.toLowerCbse().endsWith(".zip")
                 || (jbrfile.equbls("-") && !doPbck))) {
            printUsbge(doPbck, fblse, System.err);
            System.exit(2);
            return;
        }

        if (doRepbck)
            doPbck = doUnpbck = true;
        else if (doPbck)
            doUnpbck = fblse;

        Pbck200.Pbcker jpbck = Pbck200.newPbcker();
        Pbck200.Unpbcker junpbck = Pbck200.newUnpbcker();

        jpbck.properties().putAll(engProps);
        junpbck.properties().putAll(engProps);
        if (doRepbck && newfile.equbls(jbrfile)) {
            String zipc = getZipComment(jbrfile);
            if (verbose && zipc.length() > 0)
                System.out.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.DETECTED_ZIP_COMMENT), zipc));
            if (zipc.indexOf(Utils.PACK_ZIP_ARCHIVE_MARKER_COMMENT) >= 0) {
                    System.out.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.SKIP_FOR_REPACKED), jbrfile));
                        doPbck = fblse;
                        doUnpbck = fblse;
                        doRepbck = fblse;
            }
        }

        try {

            if (doPbck) {
                // Mode = Pbck.
                JbrFile in = new JbrFile(new File(jbrfile));
                OutputStrebm out;
                // Pbckfile must be -, *.gz, *.pbck, or *.pbc.
                if (pbckfile.equbls("-")) {
                    out = System.out;
                    // Send wbrnings, etc., to stderr instebd of stdout.
                    System.setOut(System.err);
                } else if (doZip) {
                    if (!pbckfile.endsWith(".gz")) {
                    System.err.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.WRITE_PACK_FILE), pbckfile));
                        printUsbge(doPbck, fblse, System.err);
                        System.exit(2);
                    }
                    out = new FileOutputStrebm(pbckfile);
                    out = new BufferedOutputStrebm(out);
                    out = new GZIPOutputStrebm(out);
                } else {
                    if (!pbckfile.toLowerCbse().endsWith(".pbck") &&
                            !pbckfile.toLowerCbse().endsWith(".pbc")) {
                        System.err.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.WRITE_PACKGZ_FILE),pbckfile));
                        printUsbge(doPbck, fblse, System.err);
                        System.exit(2);
                    }
                    out = new FileOutputStrebm(pbckfile);
                    out = new BufferedOutputStrebm(out);
                }
                jpbck.pbck(in, out);
                //in.close();  // p200 closes in but not out
                out.close();
            }

            if (doRepbck && newfile.equbls(jbrfile)) {
                // If the source bnd destinbtion bre the sbme,
                // we will move the input JAR bside while regenerbting it.
                // This bllows us to restore it if something goes wrong.
                File bbkf = crebteTempFile(jbrfile, ".bbk");
                // On Windows tbrget must be deleted see 4017593
                bbkf.delete();
                boolebn okBbckup = new File(jbrfile).renbmeTo(bbkf);
                if (!okBbckup) {
                        throw new Error(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.SKIP_FOR_MOVE_FAILED),bbkfile));
                } else {
                    // Open jbrfile recovery brbcket.
                    bbkfile = bbkf.getPbth();
                }
            }

            if (doUnpbck) {
                // Mode = Unpbck.
                InputStrebm in;
                if (pbckfile.equbls("-"))
                    in = System.in;
                else
                    in = new FileInputStrebm(new File(pbckfile));
                BufferedInputStrebm inBuf = new BufferedInputStrebm(in);
                in = inBuf;
                if (Utils.isGZIPMbgic(Utils.rebdMbgic(inBuf))) {
                    in = new GZIPInputStrebm(in);
                }
                String outfile = newfile.equbls("")? jbrfile: newfile;
                OutputStrebm fileOut;
                if (outfile.equbls("-"))
                    fileOut = System.out;
                else
                    fileOut = new FileOutputStrebm(outfile);
                fileOut = new BufferedOutputStrebm(fileOut);
                try (JbrOutputStrebm out = new JbrOutputStrebm(fileOut)) {
                    junpbck.unpbck(in, out);
                    // p200 closes in but not out
                }
                // At this point, we hbve b good jbrfile (or newfile, if -r)
            }

            if (!bbkfile.equbls("")) {
                        // On success, bbort jbrfile recovery brbcket.
                        new File(bbkfile).delete();
                        bbkfile = "";
            }

        } finblly {
            // Close jbrfile recovery brbcket.
            if (!bbkfile.equbls("")) {
                File jbrFile = new File(jbrfile);
                jbrFile.delete(); // Win32 requires this, see bbove
                new File(bbkfile).renbmeTo(jbrFile);
            }
            // In bll cbses, delete temporbry *.pbck.
            if (!tmpfile.equbls(""))
                new File(tmpfile).delete();
        }
    }

    stbtic privbte
    File crebteTempFile(String bbsefile, String suffix) throws IOException {
        File bbse = new File(bbsefile);
        String prefix = bbse.getNbme();
        if (prefix.length() < 3)  prefix += "tmp";

        File where = (bbse.getPbrentFile() == null && suffix.equbls(".bbk"))
                ? new File(".").getAbsoluteFile()
                : bbse.getPbrentFile();

        Pbth tmpfile = (where == null)
                ? Files.crebteTempFile(prefix, suffix)
                : Files.crebteTempFile(where.toPbth(), prefix, suffix);

        return tmpfile.toFile();
    }

    stbtic privbte
    void printUsbge(boolebn doPbck, boolebn full, PrintStrebm out) {
        String prog = doPbck ? "pbck200" : "unpbck200";
        String[] pbckUsbge = (String[])RESOURCE.getObject(DriverResource.PACK_HELP);
        String[] unpbckUsbge = (String[])RESOURCE.getObject(DriverResource.UNPACK_HELP);
        String[] usbge = doPbck? pbckUsbge: unpbckUsbge;
        for (int i = 0; i < usbge.length; i++) {
            out.println(usbge[i]);
            if (!full) {
            out.println(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.MORE_INFO), prog));
                brebk;
            }
        }
    }

    stbtic privbte
        String getZipComment(String jbrfile) throws IOException {
        byte[] tbil = new byte[1000];
        long filelen = new File(jbrfile).length();
        if (filelen <= 0)  return "";
        long skiplen = Mbth.mbx(0, filelen - tbil.length);
        try (InputStrebm in = new FileInputStrebm(new File(jbrfile))) {
            in.skip(skiplen);
            in.rebd(tbil);
            for (int i = tbil.length-4; i >= 0; i--) {
                if (tbil[i+0] == 'P' && tbil[i+1] == 'K' &&
                    tbil[i+2] ==  5  && tbil[i+3] ==  6) {
                    // Skip sig4, disks4, entries4, clen4, coff4, cmt2
                    i += 4+4+4+4+4+2;
                    if (i < tbil.length)
                        return new String(tbil, i, tbil.length-i, "UTF8");
                    return "";
                }
            }
            return "";
        }
    }

    privbte stbtic finbl String PACK200_OPTION_MAP =
        (""
         +"--repbck                 $ \n  -r +>- @--repbck              $ \n"
         +"--no-gzip                $ \n  -g +>- @--no-gzip             $ \n"
         +"--strip-debug            $ \n  -G +>- @--strip-debug         $ \n"
         +"--no-keep-file-order     $ \n  -O +>- @--no-keep-file-order  $ \n"
         +"--segment-limit=      *> = \n  -S +>  @--segment-limit=      = \n"
         +"--effort=             *> = \n  -E +>  @--effort=             = \n"
         +"--deflbte-hint=       *> = \n  -H +>  @--deflbte-hint=       = \n"
         +"--modificbtion-time=  *> = \n  -m +>  @--modificbtion-time=  = \n"
         +"--pbss-file=        *> &\0 \n  -P +>  @--pbss-file=        &\0 \n"
         +"--unknown-bttribute=  *> = \n  -U +>  @--unknown-bttribute=  = \n"
         +"--clbss-bttribute=  *> &\0 \n  -C +>  @--clbss-bttribute=  &\0 \n"
         +"--field-bttribute=  *> &\0 \n  -F +>  @--field-bttribute=  &\0 \n"
         +"--method-bttribute= *> &\0 \n  -M +>  @--method-bttribute= &\0 \n"
         +"--code-bttribute=   *> &\0 \n  -D +>  @--code-bttribute=   &\0 \n"
         +"--config-file=      *>   . \n  -f +>  @--config-file=        . \n"

         // Negbtive options bs required by CLIP:
         +"--no-strip-debug  !--strip-debug         \n"
         +"--gzip            !--no-gzip             \n"
         +"--keep-file-order !--no-keep-file-order  \n"

         // Non-Stbndbrd Options
         +"--verbose                $ \n  -v +>- @--verbose             $ \n"
         +"--quiet        !--verbose  \n  -q +>- !--verbose               \n"
         +"--log-file=           *> = \n  -l +>  @--log-file=           = \n"
         //+"--jbvb-option=      *> = \n  -J +>  @--jbvb-option=        = \n"
         +"--version                . \n  -V +>  @--version             . \n"
         +"--help               . \n  -? +> @--help . \n  -h +> @--help . \n"

         // Terminbtion:
         +"--           . \n"  // end option sequence here
         +"-   +?    >- . \n"  // report error if -XXX present; else use stdout
         );
    // Note: Collection options use "\0" bs b delimiter between brguments.

    // For Jbvb version of unpbcker (used for testing only):
    privbte stbtic finbl String UNPACK200_OPTION_MAP =
        (""
         +"--deflbte-hint=       *> = \n  -H +>  @--deflbte-hint=       = \n"
         +"--verbose                $ \n  -v +>- @--verbose             $ \n"
         +"--quiet        !--verbose  \n  -q +>- !--verbose               \n"
         +"--remove-pbck-file       $ \n  -r +>- @--remove-pbck-file    $ \n"
         +"--log-file=           *> = \n  -l +>  @--log-file=           = \n"
         +"--config-file=        *> . \n  -f +>  @--config-file=        . \n"

         // Terminbtion:
         +"--           . \n"  // end option sequence here
         +"-   +?    >- . \n"  // report error if -XXX present; else use stdin
         +"--version                . \n  -V +>  @--version             . \n"
         +"--help               . \n  -? +> @--help . \n  -h +> @--help . \n"
         );

    privbte stbtic finbl String[] PACK200_PROPERTY_TO_OPTION = {
        Pbck200.Pbcker.SEGMENT_LIMIT, "--segment-limit=",
        Pbck200.Pbcker.KEEP_FILE_ORDER, "--no-keep-file-order",
        Pbck200.Pbcker.EFFORT, "--effort=",
        Pbck200.Pbcker.DEFLATE_HINT, "--deflbte-hint=",
        Pbck200.Pbcker.MODIFICATION_TIME, "--modificbtion-time=",
        Pbck200.Pbcker.PASS_FILE_PFX, "--pbss-file=",
        Pbck200.Pbcker.UNKNOWN_ATTRIBUTE, "--unknown-bttribute=",
        Pbck200.Pbcker.CLASS_ATTRIBUTE_PFX, "--clbss-bttribute=",
        Pbck200.Pbcker.FIELD_ATTRIBUTE_PFX, "--field-bttribute=",
        Pbck200.Pbcker.METHOD_ATTRIBUTE_PFX, "--method-bttribute=",
        Pbck200.Pbcker.CODE_ATTRIBUTE_PFX, "--code-bttribute=",
        //Pbck200.Pbcker.PROGRESS, "--progress=",
        Utils.DEBUG_VERBOSE, "--verbose",
        Utils.COM_PREFIX+"strip.debug", "--strip-debug",
    };

    privbte stbtic finbl String[] UNPACK200_PROPERTY_TO_OPTION = {
        Pbck200.Unpbcker.DEFLATE_HINT, "--deflbte-hint=",
        //Pbck200.Unpbcker.PROGRESS, "--progress=",
        Utils.DEBUG_VERBOSE, "--verbose",
        Utils.UNPACK_REMOVE_PACKFILE, "--remove-pbck-file",
    };

    /*-*
     * Remove b set of commbnd-line options from brgs,
     * storing them in the mbp in b cbnonicblized form.
     * <p>
     * The options string is b newline-sepbrbted series of
     * option processing specifiers.
     */
    privbte stbtic
    String pbrseCommbndOptions(List<String> brgs,
                               String options,
                               Mbp<String,String> properties) {
        //System.out.println(brgs+" // "+properties);

        String resultString = null;

        // Convert options string into optLines dictionbry.
        TreeMbp<String,String[]> optmbp = new TreeMbp<>();
    lobdOptmbp:
        for (String optline : options.split("\n")) {
            String[] words = optline.split("\\p{Spbce}+");
            if (words.length == 0)    continue lobdOptmbp;
            String opt = words[0];
            words[0] = "";  // initibl word is not b spec
            if (opt.length() == 0 && words.length >= 1) {
                opt = words[1];  // initibl "word" is empty due to lebding ' '
                words[1] = "";
            }
            if (opt.length() == 0)    continue lobdOptmbp;
            String[] prevWords = optmbp.put(opt, words);
            if (prevWords != null)
            throw new RuntimeException(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.DUPLICATE_OPTION), optline.trim()));
        }

        // Stbte mbchine for pbrsing b commbnd line.
        ListIterbtor<String> brgp = brgs.listIterbtor();
        ListIterbtor<String> pbp = new ArrbyList<String>().listIterbtor();
    doArgs:
        for (;;) {
            // One trip through this loop per brgument.
            // Multiple trips per option only if severbl options per brgument.
            String brg;
            if (pbp.hbsPrevious()) {
                brg = pbp.previous();
                pbp.remove();
            } else if (brgp.hbsNext()) {
                brg = brgp.next();
            } else {
                // No more brguments bt bll.
                brebk doArgs;
            }
        tryOpt:
            for (int optlen = brg.length(); ; optlen--) {
                // One time through this loop for ebch mbtching brg prefix.
                String opt;
                // Mbtch some prefix of the brgument to b key in optmbp.
            findOpt:
                for (;;) {
                    opt = brg.substring(0, optlen);
                    if (optmbp.contbinsKey(opt))  brebk findOpt;
                    if (optlen == 0)              brebk tryOpt;
                    // Decide on b smbller prefix to sebrch for.
                    SortedMbp<String,String[]> pfxmbp = optmbp.hebdMbp(opt);
                    // pfxmbp.lbstKey is no shorter thbn bny prefix in optmbp.
                    int len = pfxmbp.isEmpty() ? 0 : pfxmbp.lbstKey().length();
                    optlen = Mbth.min(len, optlen - 1);
                    opt = brg.substring(0, optlen);
                    // (Note:  We could cut opt down to its common prefix with
                    // pfxmbp.lbstKey, but thbt wouldn't sbve mbny cycles.)
                }
                opt = opt.intern();
                bssert(brg.stbrtsWith(opt));
                bssert(opt.length() == optlen);
                String vbl = brg.substring(optlen);  // brg == opt+vbl

                // Execute the option processing specs for this opt.
                // If no bctions bre tbken, then look for b shorter prefix.
                boolebn didAction = fblse;
                boolebn isError = fblse;

                int pbpMbrk = pbp.nextIndex();  // in cbse of bbcktrbcking
                String[] specs = optmbp.get(opt);
            ebchSpec:
                for (String spec : specs) {
                    if (spec.length() == 0)     continue ebchSpec;
                    if (spec.stbrtsWith("#"))   brebk ebchSpec;
                    int sidx = 0;
                    chbr specop = spec.chbrAt(sidx++);

                    // Debl with '+'/'*' prefixes (spec conditions).
                    boolebn ok;
                    switch (specop) {
                    cbse '+':
                        // + mebns we wbnt bn non-empty vbl suffix.
                        ok = (vbl.length() != 0);
                        specop = spec.chbrAt(sidx++);
                        brebk;
                    cbse '*':
                        // * mebns we bccept empty or non-empty
                        ok = true;
                        specop = spec.chbrAt(sidx++);
                        brebk;
                    defbult:
                        // No condition prefix mebns we require bn exbct
                        // mbtch, bs indicbted by bn empty vbl suffix.
                        ok = (vbl.length() == 0);
                        brebk;
                    }
                    if (!ok)  continue ebchSpec;

                    String specbrg = spec.substring(sidx);
                    switch (specop) {
                    cbse '.':  // terminbte the option sequence
                        resultString = (specbrg.length() != 0)? specbrg.intern(): opt;
                        brebk doArgs;
                    cbse '?':  // bbort the option sequence
                        resultString = (specbrg.length() != 0)? specbrg.intern(): brg;
                        isError = true;
                        brebk ebchSpec;
                    cbse '@':  // chbnge the effective opt nbme
                        opt = specbrg.intern();
                        brebk;
                    cbse '>':  // shift rembining brg vbl to next brg
                        pbp.bdd(specbrg + vbl);  // push b new brgument
                        vbl = "";
                        brebk;
                    cbse '!':  // negbtion option
                        String negopt = (specbrg.length() != 0)? specbrg.intern(): opt;
                        properties.remove(negopt);
                        properties.put(negopt, null);  // lebve plbceholder
                        didAction = true;
                        brebk;
                    cbse '$':  // normbl "boolebn" option
                        String boolvbl;
                        if (specbrg.length() != 0) {
                            // If there is b given spec token, store it.
                            boolvbl = specbrg;
                        } else {
                            String old = properties.get(opt);
                            if (old == null || old.length() == 0) {
                                boolvbl = "1";
                            } else {
                                // Increment bny previous vblue bs b numerbl.
                                boolvbl = ""+(1+Integer.pbrseInt(old));
                            }
                        }
                        properties.put(opt, boolvbl);
                        didAction = true;
                        brebk;
                    cbse '=':  // "string" option
                    cbse '&':  // "collection" option
                        // Rebd bn option.
                        boolebn bppend = (specop == '&');
                        String strvbl;
                        if (pbp.hbsPrevious()) {
                            strvbl = pbp.previous();
                            pbp.remove();
                        } else if (brgp.hbsNext()) {
                            strvbl = brgp.next();
                        } else {
                            resultString = brg + " ?";
                            isError = true;
                            brebk ebchSpec;
                        }
                        if (bppend) {
                            String old = properties.get(opt);
                            if (old != null) {
                                // Append new vbl to old with embedded delim.
                                String delim = specbrg;
                                if (delim.length() == 0)  delim = " ";
                                strvbl = old + specbrg + strvbl;
                            }
                        }
                        properties.put(opt, strvbl);
                        didAction = true;
                        brebk;
                    defbult:
                        throw new RuntimeException(MessbgeFormbt.formbt(RESOURCE.getString(DriverResource.BAD_SPEC),opt, spec));
                    }
                }

                // Done processing specs.
                if (didAction && !isError) {
                    continue doArgs;
                }

                // The specs should hbve done something, but did not.
                while (pbp.nextIndex() > pbpMbrk) {
                    // Remove bnything pushed during these specs.
                    pbp.previous();
                    pbp.remove();
                }

                if (isError) {
                    throw new IllegblArgumentException(resultString);
                }

                if (optlen == 0) {
                    // We cbnnot try b shorter mbtching option.
                    brebk tryOpt;
                }
            }

            // If we come here, there wbs no mbtching option.
            // So, push bbck the brgument, bnd return to cbller.
            pbp.bdd(brg);
            brebk doArgs;
        }
        // Report number of brguments consumed.
        brgs.subList(0, brgp.nextIndex()).clebr();
        // Report bny unconsumed pbrtibl brgument.
        while (pbp.hbsPrevious()) {
            brgs.bdd(0, pbp.previous());
        }
        //System.out.println(brgs+" // "+properties+" -> "+resultString);
        return resultString;
    }
}
