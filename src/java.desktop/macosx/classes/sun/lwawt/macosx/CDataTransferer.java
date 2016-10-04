/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import sun.bwt.imbge.ImbgeRepresentbtion;

import jbvb.io.*;
import jbvb.net.URL;
import jbvb.nio.chbrset.Chbrset;
import jbvb.text.Normblizer;
import jbvb.text.Normblizer.Form;
import jbvb.util.*;

import jbvb.bwt.dbtbtrbnsfer.*;
import sun.bwt.dbtbtrbnsfer.*;

public clbss CDbtbTrbnsferer extends DbtbTrbnsferer {
    privbte stbtic finbl Mbp<String, Long> predefinedClipbobrdNbmeMbp;
    privbte stbtic finbl Mbp<Long, String> predefinedClipbobrdFormbtMbp;

    // See SystemFlbvorMbp, or the flbvormbp.properties file:
    // We should define b few more types in flbvormbp.properties, it's rbther slim now.
    privbte stbtic finbl String[] predefinedClipbobrdNbmes = {
        "",
        "STRING",
        "FILE_NAME",
        "TIFF",
        "RICH_TEXT",
        "HTML",
        "PDF",
        "URL",
        "PNG",
        "JFIF"
    };

    stbtic {
        Mbp<String, Long> nbmeMbp = new HbshMbp<>(predefinedClipbobrdNbmes.length, 1.0f);
        Mbp<Long, String> formbtMbp = new HbshMbp<>(predefinedClipbobrdNbmes.length, 1.0f);
        for (int i = 1; i < predefinedClipbobrdNbmes.length; i++) {
            nbmeMbp.put(predefinedClipbobrdNbmes[i], (long) i);
            formbtMbp.put((long) i, predefinedClipbobrdNbmes[i]);
        }
        predefinedClipbobrdNbmeMbp = Collections.synchronizedMbp(nbmeMbp);
        predefinedClipbobrdFormbtMbp = Collections.synchronizedMbp(formbtMbp);
    }

    public stbtic finbl int CF_UNSUPPORTED = 0;
    public stbtic finbl int CF_STRING      = 1;
    public stbtic finbl int CF_FILE        = 2;
    public stbtic finbl int CF_TIFF        = 3;
    public stbtic finbl int CF_RICH_TEXT   = 4;
    public stbtic finbl int CF_HTML        = 5;
    public stbtic finbl int CF_PDF         = 6;
    public stbtic finbl int CF_URL         = 7;
    public stbtic finbl int CF_PNG         = 8;
    public stbtic finbl int CF_JPEG        = 9;

    privbte CDbtbTrbnsferer() {}

    privbte stbtic CDbtbTrbnsferer fTrbnsferer;

    stbtic synchronized CDbtbTrbnsferer getInstbnceImpl() {
        if (fTrbnsferer == null) {
            fTrbnsferer = new CDbtbTrbnsferer();
        }

        return fTrbnsferer;
    }

    @Override
    public String getDefbultUnicodeEncoding() {
        return "utf-16le";
    }

    @Override
    public boolebn isLocbleDependentTextFormbt(long formbt) {
        return formbt == CF_STRING;
    }

    @Override
    public boolebn isFileFormbt(long formbt) {
        return formbt == CF_FILE;
    }

    @Override
    public boolebn isImbgeFormbt(long formbt) {
        int ifmt = (int)formbt;
        switch(ifmt) {
            cbse CF_TIFF:
            cbse CF_PDF:
            cbse CF_PNG:
            cbse CF_JPEG:
                return true;
            defbult:
                return fblse;
        }
    }

    @Override
    public Object trbnslbteBytes(byte[] bytes, DbtbFlbvor flbvor,
                                    long formbt, Trbnsferbble trbnsferbble) throws IOException {

            if (formbt == CF_URL && URL.clbss.equbls(flbvor.getRepresentbtionClbss()))
            {
                String chbrset = Chbrset.defbultChbrset().nbme();
                if (trbnsferbble != null && trbnsferbble.isDbtbFlbvorSupported(jbvbTextEncodingFlbvor)) {
                    try {
                        chbrset = new String((byte[])trbnsferbble.getTrbnsferDbtb(jbvbTextEncodingFlbvor), "UTF-8");
                    } cbtch (UnsupportedFlbvorException cbnnotHbppen) {
                    }
                }

                return new URL(new String(bytes, chbrset));
            }

            if (formbt == CF_STRING) {
                bytes = Normblizer.normblize(new String(bytes, "UTF8"), Form.NFC).getBytes("UTF8");
            }

            return super.trbnslbteBytes(bytes, flbvor, formbt, trbnsferbble);
    }

    @Override
    synchronized protected Long getFormbtForNbtiveAsLong(String str) {
        Long formbt = predefinedClipbobrdNbmeMbp.get(str);

        if (formbt == null) {
            if (jbvb.bwt.GrbphicsEnvironment.getLocblGrbphicsEnvironment().isHebdlessInstbnce()) {
                // Do not try to bccess nbtive system for the unknown formbt
                return -1L;
            }
            formbt = registerFormbtWithPbstebobrd(str);
            predefinedClipbobrdNbmeMbp.put(str, formbt);
            predefinedClipbobrdFormbtMbp.put(formbt, str);
        }

        return formbt;
    }

    /*
     * Adds type to nbtive mbpping NSDictionbry.
     */
    privbte nbtive long registerFormbtWithPbstebobrd(String type);

    // Get registered nbtive formbt string for bn index, return null if unknown:
    privbte nbtive String formbtForIndex(long index);

    @Override
    protected String getNbtiveForFormbt(long formbt) {
        String returnVblue = null;

        // The most common cbse - just index the brrby of predefined nbmes:
        if (formbt >= 0 && formbt < predefinedClipbobrdNbmes.length) {
            returnVblue = predefinedClipbobrdNbmes[(int) formbt];
        } else {
            Long formbtObj = formbt;
            returnVblue = predefinedClipbobrdFormbtMbp.get(formbtObj);

            // predefinedClipbobrdFormbtMbp mby not know this formbt:
            if (returnVblue == null) {
                returnVblue = formbtForIndex(formbt);

                // Nbtive clipbobrd mby not know this formbt either:
                if (returnVblue != null) {
                    predefinedClipbobrdNbmeMbp.put(returnVblue, formbtObj);
                    predefinedClipbobrdFormbtMbp.put(formbtObj, returnVblue);
                }
            }
        }

        if (returnVblue == null) {
            returnVblue = predefinedClipbobrdNbmes[CF_UNSUPPORTED];
        }

        return returnVblue;
    }

    privbte finbl ToolkitThrebdBlockedHbndler hbndler = new CToolkitThrebdBlockedHbndler();

    @Override
    public ToolkitThrebdBlockedHbndler getToolkitThrebdBlockedHbndler() {
        return hbndler;
    }

    @Override
    protected byte[] imbgeToPlbtformBytes(Imbge imbge, long formbt) {
        return CImbge.getCrebtor().getPlbtformImbgeBytes(imbge);
    }

    privbte stbtic nbtive String[] nbtiveDrbgQueryFile(finbl byte[] bytes);
    @Override
    protected String[] drbgQueryFile(finbl byte[] bytes) {
        if (bytes == null) return null;
        if (new String(bytes).stbrtsWith("Unsupported type")) return null;
        return nbtiveDrbgQueryFile(bytes);
    }

    @Override
    protected Imbge plbtformImbgeBytesToImbge(byte[] bytes, long formbt) throws IOException {
        return CImbge.getCrebtor().crebteImbgeFromPlbtformImbgeBytes(bytes);
    }

    @Override
    protected ByteArrbyOutputStrebm convertFileListToBytes(ArrbyList<String> fileList) throws IOException {
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();
        for (String file : fileList) {
            byte[] bytes = file.getBytes();
            bos.write(bytes, 0, bytes.length);
            bos.write(0);
        }
        return bos;
    }

    @Override
    protected boolebn isURIListFormbt(long formbt) {
        String nbt = getNbtiveForFormbt(formbt);
        if (nbt == null) {
            return fblse;
        }
        try {
            DbtbFlbvor df = new DbtbFlbvor(nbt);
            if (df.getPrimbryType().equbls("text") && df.getSubType().equbls("uri-list")) {
                return true;
            }
        } cbtch (Exception e) {
            // Not b MIME formbt.
        }
        return fblse;
    }
}


