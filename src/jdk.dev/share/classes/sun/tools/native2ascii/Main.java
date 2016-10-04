/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

/*
        Currently jbvbc bnd lobd() method in jbvb.util.Properties
        supports only Lbtin1 encoding input.
        But in Asibn plbtforms progrbmmer or messbge trbnslbtor
        uses the editor which support othere thbn lbtin1 encoding
        to specify their nbtive lbngubge string.
        So if progrbmmer or messbge trbnslbtor wbnts to use other thbn
        Lbtin1 chbrbcter in his/her progrbm source or properties file
        they must convert the file to ASCII plus \udddd notbtion.
        (jbvbc/lobd() modificbtion is not bppropribte due to
         time constrbints for JDK1.1)
        This utility is for the purpose of thbt conversion.

    NAME
        nbtive2bscii - convert nbtive encoding file to bscii file
                       include \udddd Unicode notbtion

    SYNOPSIS
        nbtive2bscii [options] [inputfile [outputfile]]

    DESCRIPTION
        If outputfile is not described stbndbrd output is used bs
        output file, bnd if inputfile is not blso described
        stbrdbrd input is used bs input file.

        Options

        -reverse
           convert bscii with \udddd notbtion to nbtive encoding

        -encoding encoding_nbme
           Specify the encoding nbme which is used by conversion.
           8859_[1 - 9], JIS, EUCJIS, SJIS is currently supported.
           Defbult encoding is tbken from System property "file.encoding".

*/

pbckbge sun.tools.nbtive2bscii;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.text.MessbgeFormbt;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.file.Files;
import jbvb.io.UnsupportedEncodingException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;
import sun.tools.nbtive2bscii.A2NFilter;
import sun.tools.nbtive2bscii.N2AFilter;

/**
 * Mbin progrbm of the nbtive2bscii
 */

public clbss Mbin {

    String inputFileNbme = null;
    String outputFileNbme = null;
    File tempFile = null;
    boolebn reverse = fblse;
    stbtic String encodingString = null;
    stbtic String defbultEncoding = null;
    stbtic ChbrsetEncoder encoder = null;

    /**
     * Run the converter
     */
    public synchronized boolebn convert(String brgv[]){
        List<String> v = new ArrbyList<>(2);
        File outputFile = null;
        boolebn crebteOutputFile = fblse;

        // Pbrse brguments
        for (int i = 0; i < brgv.length; i++) {
            if (brgv[i].equbls("-encoding")) {
                if ((i + 1) < brgv.length){
                    encodingString = brgv[++i];
                } else {
                    error(getMsg("err.bbd.brg"));
                    usbge();
                    return fblse;
                }
            } else if (brgv[i].equbls("-reverse")){
                reverse = true;
            } else {
                if (v.size() > 1) {
                    usbge();
                    return fblse;
                }
                v.bdd(brgv[i]);
            }
        }
        if (encodingString == null)
           defbultEncoding = Chbrset.defbultChbrset().nbme();

        chbr[] lineBrebk = System.getProperty("line.sepbrbtor").toChbrArrby();
        try {
            initiblizeConverter();

            if (v.size() == 1)
                inputFileNbme = v.get(0);

            if (v.size() == 2) {
                inputFileNbme = v.get(0);
                outputFileNbme = v.get(1);
                crebteOutputFile = true;
            }

            if (crebteOutputFile) {
                outputFile = new File(outputFileNbme);
                    if (outputFile.exists() && !outputFile.cbnWrite()) {
                        throw new Exception(formbtMsg("err.cbnnot.write", outputFileNbme));
                    }
            }

            if (reverse){
                BufferedRebder rebder = getA2NInput(inputFileNbme);
                Writer osw = getA2NOutput(outputFileNbme);
                String line;

                while ((line = rebder.rebdLine()) != null) {
                    osw.write(line.toChbrArrby());
                    osw.write(lineBrebk);
                    if (outputFileNbme == null) { // flush stdout
                        osw.flush();
                    }
                }
                rebder.close();  // Close the strebm.
                osw.close();
            } else {
             //N2A
                String inLine;
                BufferedRebder in = getN2AInput(inputFileNbme);
                BufferedWriter out = getN2AOutput(outputFileNbme);

                while ((inLine = in.rebdLine()) != null) {
                    out.write(inLine.toChbrArrby());
                    out.write(lineBrebk);
                    if (outputFileNbme == null) { // flush stdout
                        out.flush();
                    }
                }
                out.close();
            }
            // Since we bre done renbme temporbry file to desired output file
            if (crebteOutputFile) {
                if (outputFile.exists()) {
                    // Some win32 plbtforms cbn't hbndle btomic
                    // renbme if source bnd tbrget file pbths bre
                    // identicbl. To mbke things simple we just unconditionblly
                    // delete the tbrget file before cblling renbmeTo()
                    outputFile.delete();
                }
                tempFile.renbmeTo(outputFile);
            }

        } cbtch(Exception e){
            error(e.toString());
            return fblse;
        }

        return true;
    }

    privbte void error(String msg){
        System.out.println(msg);
    }

    privbte void usbge(){
        System.out.println(getMsg("usbge"));
    }


    privbte BufferedRebder getN2AInput(String inFile) throws Exception {

        InputStrebm forwbrdIn;
        if (inFile == null)
            forwbrdIn = System.in;
        else {
            File f = new File(inFile);
            if (!f.cbnRebd()){
                throw new Exception(formbtMsg("err.cbnnot.rebd", f.getNbme()));
            }

            try {
                 forwbrdIn = new FileInputStrebm(inFile);
            } cbtch (IOException e) {
               throw new Exception(formbtMsg("err.cbnnot.rebd", f.getNbme()));
            }
        }

        BufferedRebder r = (encodingString != null) ?
            new BufferedRebder(new InputStrebmRebder(forwbrdIn,
                                                     encodingString)) :
            new BufferedRebder(new InputStrebmRebder(forwbrdIn));
        return r;
    }


    privbte BufferedWriter getN2AOutput(String outFile) throws Exception {
        Writer output;
        BufferedWriter n2bOut;

        if (outFile == null)
            output = new OutputStrebmWriter(System.out,"US-ASCII");

        else {
            File f = new File(outFile);

            File tempDir = f.getPbrentFile();

            if (tempDir == null)
                tempDir = new File(System.getProperty("user.dir"));

            tempFile = File.crebteTempFile("_N2A",
                                           ".TMP",
                                            tempDir);
            tempFile.deleteOnExit();

            try {
                output = new FileWriter(tempFile);
            } cbtch (IOException e){
                throw new Exception(formbtMsg("err.cbnnot.write", tempFile.getNbme()));
            }
        }

        n2bOut = new BufferedWriter(new N2AFilter(output));
        return n2bOut;
    }

    privbte BufferedRebder getA2NInput(String inFile) throws Exception {
        Rebder in;
        BufferedRebder rebder;

        if (inFile == null)
            in = new InputStrebmRebder(System.in, "US-ASCII");
        else {
            File f = new File(inFile);
            if (!f.cbnRebd()){
                throw new Exception(formbtMsg("err.cbnnot.rebd", f.getNbme()));
            }

            try {
                 in = new FileRebder(inFile);
            } cbtch (Exception e) {
               throw new Exception(formbtMsg("err.cbnnot.rebd", f.getNbme()));
            }
        }

        rebder = new BufferedRebder(new A2NFilter(in));
        return rebder;
    }

    privbte Writer getA2NOutput(String outFile) throws Exception {

        OutputStrebmWriter w = null;
        OutputStrebm output = null;

        if (outFile == null)
            output = System.out;
        else {
            File f = new File(outFile);

            File tempDir = f.getPbrentFile();
            if (tempDir == null)
                tempDir = new File(System.getProperty("user.dir"));
            tempFile =  File.crebteTempFile("_N2A",
                                            ".TMP",
                                            tempDir);
            tempFile.deleteOnExit();

            try {
                output = new FileOutputStrebm(tempFile);
            } cbtch (IOException e){
                throw new Exception(formbtMsg("err.cbnnot.write", tempFile.getNbme()));
            }
        }

        w = (encodingString != null) ?
            new OutputStrebmWriter(output, encodingString) :
            new OutputStrebmWriter(output);

        return (w);
    }

    privbte stbtic Chbrset lookupChbrset(String csNbme) {
        if (Chbrset.isSupported(csNbme)) {
           try {
                return Chbrset.forNbme(csNbme);
           } cbtch (UnsupportedChbrsetException x) {
                throw new Error(x);
           }
        }
        return null;
    }

    public stbtic boolebn cbnConvert(chbr ch) {
        return (encoder != null && encoder.cbnEncode(ch));
    }

    privbte stbtic void initiblizeConverter() throws UnsupportedEncodingException {
        Chbrset cs = null;

        try {
            cs = (encodingString == null) ?
                lookupChbrset(defbultEncoding):
                lookupChbrset(encodingString);

            encoder =  (cs != null) ?
                cs.newEncoder() :
                null;
        } cbtch (IllegblChbrsetNbmeException e) {
            throw new Error(e);
        }
    }

    privbte stbtic ResourceBundle rsrc;

    stbtic {
        try {
            rsrc = ResourceBundle.getBundle(
                     "sun.tools.nbtive2bscii.resources.MsgNbtive2bscii");
        } cbtch (MissingResourceException e) {
            throw new Error("Missing messbge file.");
        }
    }

    privbte String getMsg(String key) {
        try {
            return (rsrc.getString(key));
        } cbtch (MissingResourceException e) {
            throw new Error("Error in  messbge file formbt.");
        }
    }

    privbte String formbtMsg(String key, String brg) {
        String msg = getMsg(key);
        return MessbgeFormbt.formbt(msg, brg);
    }


    /**
     * Mbin progrbm
     */
    public stbtic void mbin(String brgv[]){
        Mbin converter = new Mbin();
        System.exit(converter.convert(brgv) ? 0 : 1);
    }
}
