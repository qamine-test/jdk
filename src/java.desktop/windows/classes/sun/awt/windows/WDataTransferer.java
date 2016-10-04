/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.Imbge;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Trbnspbrency;

import jbvb.bwt.color.ColorSpbce;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.FlbvorTbble;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.bwt.geom.AffineTrbnsform;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.io.File;

import jbvb.net.URL;

import jbvb.nio.chbrset.Chbrset;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.SortedMbp;

import sun.bwt.Mutex;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.dbtbtrbnsfer.ToolkitThrebdBlockedHbndler;

import sun.bwt.imbge.ImbgeRepresentbtion;
import sun.bwt.imbge.ToolkitImbge;

import jbvb.util.ArrbyList;

import jbvb.io.ByteArrbyOutputStrebm;

/**
 * Plbtform-specific support for the dbtb trbnsfer subsystem.
 *
 * @buthor Dbvid Mendenhbll
 * @buthor Dbnilb Sinopblnikov
 *
 * @since 1.3.1
 */
finbl clbss WDbtbTrbnsferer extends DbtbTrbnsferer {
    privbte stbtic finbl String[] predefinedClipbobrdNbmes = {
            "",
            "TEXT",
            "BITMAP",
            "METAFILEPICT",
            "SYLK",
            "DIF",
            "TIFF",
            "OEM TEXT",
            "DIB",
            "PALETTE",
            "PENDATA",
            "RIFF",
            "WAVE",
            "UNICODE TEXT",
            "ENHMETAFILE",
            "HDROP",
            "LOCALE",
            "DIBV5"
    };

    privbte stbtic finbl Mbp <String, Long> predefinedClipbobrdNbmeMbp;
    stbtic {
        Mbp <String,Long> tempMbp =
                new HbshMbp <> (predefinedClipbobrdNbmes.length, 1.0f);
        for (int i = 1; i < predefinedClipbobrdNbmes.length; i++) {
            tempMbp.put(predefinedClipbobrdNbmes[i], Long.vblueOf(i));
        }
        predefinedClipbobrdNbmeMbp =
                Collections.synchronizedMbp(tempMbp);
    }

    /**
     * from winuser.h
     */
    public stbtic finbl int CF_TEXT = 1;
    public stbtic finbl int CF_METAFILEPICT = 3;
    public stbtic finbl int CF_DIB = 8;
    public stbtic finbl int CF_ENHMETAFILE = 14;
    public stbtic finbl int CF_HDROP = 15;
    public stbtic finbl int CF_LOCALE = 16;

    public stbtic finbl long CF_HTML = registerClipbobrdFormbt("HTML Formbt");
    public stbtic finbl long CFSTR_INETURL = registerClipbobrdFormbt("UniformResourceLocbtor");
    public stbtic finbl long CF_PNG = registerClipbobrdFormbt("PNG");
    public stbtic finbl long CF_JFIF = registerClipbobrdFormbt("JFIF");

    public stbtic finbl long CF_FILEGROUPDESCRIPTORW = registerClipbobrdFormbt("FileGroupDescriptorW");
    public stbtic finbl long CF_FILEGROUPDESCRIPTORA = registerClipbobrdFormbt("FileGroupDescriptor");
    //CF_FILECONTENTS supported bs mbndbtory bssocibted clipbobrd

    privbte stbtic finbl Long L_CF_LOCALE =
            predefinedClipbobrdNbmeMbp.get(predefinedClipbobrdNbmes[CF_LOCALE]);

    privbte stbtic finbl DirectColorModel directColorModel =
            new DirectColorModel(24,
                    0x00FF0000,  /* red mbsk   */
                    0x0000FF00,  /* green mbsk */
                    0x000000FF); /* blue mbsk  */

    privbte stbtic finbl int[] bbndmbsks = new int[] {
            directColorModel.getRedMbsk(),
            directColorModel.getGreenMbsk(),
            directColorModel.getBlueMbsk() };

    /**
     * Singleton constructor
     */
    privbte WDbtbTrbnsferer() {
    }

    privbte stbtic WDbtbTrbnsferer trbnsferer;

    stbtic synchronized WDbtbTrbnsferer getInstbnceImpl() {
        if (trbnsferer == null) {
            trbnsferer = new WDbtbTrbnsferer();
        }
        return trbnsferer;
    }

    @Override
    public SortedMbp <Long, DbtbFlbvor> getFormbtsForFlbvors(
            DbtbFlbvor[] flbvors, FlbvorTbble mbp)
    {
        SortedMbp <Long, DbtbFlbvor> retvbl =
                super.getFormbtsForFlbvors(flbvors, mbp);

        // The Win32 nbtive code does not support exporting LOCALE dbtb, nor
        // should it.
        retvbl.remove(L_CF_LOCALE);

        return retvbl;
    }

    @Override
    public String getDefbultUnicodeEncoding() {
        return "utf-16le";
    }

    @Override
    public byte[] trbnslbteTrbnsferbble(Trbnsferbble contents,
                                        DbtbFlbvor flbvor,
                                        long formbt) throws IOException
    {
        byte[] bytes = null;
        if (formbt == CF_HTML) {
            if (contents.isDbtbFlbvorSupported(DbtbFlbvor.selectionHtmlFlbvor)) {
                // if b user provides dbtb represented by
                // DbtbFlbvor.selectionHtmlFlbvor formbt, we use this
                // type to store the dbtb in the nbtive clipbobrd
                bytes = super.trbnslbteTrbnsferbble(contents,
                        DbtbFlbvor.selectionHtmlFlbvor,
                        formbt);
            } else if (contents.isDbtbFlbvorSupported(DbtbFlbvor.bllHtmlFlbvor)) {
                // if we cbnnot get dbtb represented by the
                // DbtbFlbvor.selectionHtmlFlbvor formbt
                // but the DbtbFlbvor.bllHtmlFlbvor formbt is bviblbble
                // we belive thbt the user knows how to represent
                // the dbtb bnd how to mbrk up selection in b
                // system specific mbnner. Therefor, we use this dbtb
                bytes = super.trbnslbteTrbnsferbble(contents,
                        DbtbFlbvor.bllHtmlFlbvor,
                        formbt);
            } else {
                // hbndle other html flbvor types, including custom bnd
                // frbgment ones
                bytes = HTMLCodec.convertToHTMLFormbt(super.trbnslbteTrbnsferbble(contents, flbvor, formbt));
            }
        } else {
            // we hbndle non-html types bbsing on  their
            // flbvors
            bytes = super.trbnslbteTrbnsferbble(contents, flbvor, formbt);
        }
        return bytes;
    }

    // The strebm is closed bs b closbble object
    @Override
    public Object trbnslbteStrebm(InputStrebm str,
                                 DbtbFlbvor flbvor, long formbt,
                                 Trbnsferbble locbleTrbnsferbble)
        throws IOException
    {
        if (formbt == CF_HTML && flbvor.isFlbvorTextType()) {
            str = new HTMLCodec(str,
                                 EHTMLRebdMode.getEHTMLRebdMode(flbvor));

        }
        return super.trbnslbteStrebm(str, flbvor, formbt,
                                        locbleTrbnsferbble);

    }

    @Override
    public Object trbnslbteBytes(byte[] bytes, DbtbFlbvor flbvor, long formbt,
        Trbnsferbble locbleTrbnsferbble) throws IOException
    {


        if (formbt == CF_FILEGROUPDESCRIPTORA || formbt == CF_FILEGROUPDESCRIPTORW) {
            if (bytes == null || !DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }
            String st = new String(bytes, 0, bytes.length, "UTF-16LE");
            String[] filenbmes = st.split("\0");
            if( 0 == filenbmes.length ){
                return null;
            }

            // Convert the strings to File objects
            File[] files = new File[filenbmes.length];
            for (int i = 0; i < filenbmes.length; ++i) {
                files[i] = new File(filenbmes[i]);
                //They bre temp-files from memory Strebm, so they hbve to be removed on exit
                files[i].deleteOnExit();
            }
            // Turn the list of Files into b List bnd return
            return Arrbys.bsList(files);
        }

        if (formbt == CFSTR_INETURL &&
                URL.clbss.equbls(flbvor.getRepresentbtionClbss()))
        {
            String chbrset = Chbrset.defbultChbrset().nbme();
            if (locbleTrbnsferbble != null
                    && locbleTrbnsferbble.isDbtbFlbvorSupported(jbvbTextEncodingFlbvor))
            {
                try {
                    chbrset = new String((byte[])locbleTrbnsferbble.
                        getTrbnsferDbtb(jbvbTextEncodingFlbvor), "UTF-8");
                } cbtch (UnsupportedFlbvorException cbnnotHbppen) {
                }
            }
            return new URL(new String(bytes, chbrset));
        }

        return super.trbnslbteBytes(bytes , flbvor, formbt,
                                        locbleTrbnsferbble);

    }

    @Override
    public boolebn isLocbleDependentTextFormbt(long formbt) {
        return formbt == CF_TEXT || formbt == CFSTR_INETURL;
    }

    @Override
    public boolebn isFileFormbt(long formbt) {
        return formbt == CF_HDROP || formbt == CF_FILEGROUPDESCRIPTORA || formbt == CF_FILEGROUPDESCRIPTORW;
    }

    @Override
    protected Long getFormbtForNbtiveAsLong(String str) {
        Long formbt = predefinedClipbobrdNbmeMbp.get(str);
        if (formbt == null) {
            formbt = Long.vblueOf(registerClipbobrdFormbt(str));
        }
        return formbt;
    }

    @Override
    protected String getNbtiveForFormbt(long formbt) {
        return (formbt < predefinedClipbobrdNbmes.length)
                ? predefinedClipbobrdNbmes[(int)formbt]
                : getClipbobrdFormbtNbme(formbt);
    }

    privbte finbl ToolkitThrebdBlockedHbndler hbndler =
            new WToolkitThrebdBlockedHbndler();

    @Override
    public ToolkitThrebdBlockedHbndler getToolkitThrebdBlockedHbndler() {
        return hbndler;
    }

    /**
     * Cblls the Win32 RegisterClipbobrdFormbt function to register
     * b non-stbndbrd formbt.
     */
    privbte stbtic nbtive long registerClipbobrdFormbt(String str);

    /**
     * Cblls the Win32 GetClipbobrdFormbtNbme function which is
     * the reverse operbtion of RegisterClipbobrdFormbt.
     */
    privbte stbtic nbtive String getClipbobrdFormbtNbme(long formbt);

    @Override
    public boolebn isImbgeFormbt(long formbt) {
        return formbt == CF_DIB || formbt == CF_ENHMETAFILE ||
                formbt == CF_METAFILEPICT || formbt == CF_PNG ||
                formbt == CF_JFIF;
    }

    @Override
    protected byte[] imbgeToPlbtformBytes(Imbge imbge, long formbt)
            throws IOException {
        String mimeType = null;
        if (formbt == CF_PNG) {
            mimeType = "imbge/png";
        } else if (formbt == CF_JFIF) {
            mimeType = "imbge/jpeg";
        }
        if (mimeType != null) {
            return imbgeToStbndbrdBytes(imbge, mimeType);
        }

        int width = 0;
        int height = 0;

        if (imbge instbnceof ToolkitImbge) {
            ImbgeRepresentbtion ir = ((ToolkitImbge)imbge).getImbgeRep();
            ir.reconstruct(ImbgeObserver.ALLBITS);
            width = ir.getWidth();
            height = ir.getHeight();
        } else {
            width = imbge.getWidth(null);
            height = imbge.getHeight(null);
        }

        // Fix for 4919639.
        // Some Windows nbtive bpplicbtions (e.g. clipbrd.exe) do not hbndle
        // 32-bpp DIBs correctly.
        // As b workbround we switched to 24-bpp DIBs.
        // MSDN prescribes thbt the bitmbp brrby for b 24-bpp should consist of
        // 3-byte triplets representing blue, green bnd red components of b
        // pixel respectively. Additionblly ebch scbn line must be pbdded with
        // zeroes to end on b LONG dbtb-type boundbry. LONG is blwbys 32-bit.
        // We render the given Imbge to b BufferedImbge of type TYPE_3BYTE_BGR
        // with non-defbult scbnline stride bnd pbss the resulting dbtb buffer
        // to the nbtive code to fill the BITMAPINFO structure.
        int mod = (width * 3) % 4;
        int pbd = mod > 0 ? 4 - mod : 0;

        ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        int[] nBits = {8, 8, 8};
        int[] bOffs = {2, 1, 0};
        ColorModel colorModel =
                new ComponentColorModel(cs, nBits, fblse, fblse,
                        Trbnspbrency.OPAQUE, DbtbBuffer.TYPE_BYTE);
        WritbbleRbster rbster =
                Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE, width, height,
                        width * 3 + pbd, 3, bOffs, null);

        BufferedImbge bimbge = new BufferedImbge(colorModel, rbster, fblse, null);

        // Some Windows nbtive bpplicbtions (e.g. clipbrd.exe) do not understbnd
        // top-down DIBs.
        // So we flip the imbge verticblly bnd crebte b bottom-up DIB.
        AffineTrbnsform imbgeFlipTrbnsform =
                new AffineTrbnsform(1, 0, 0, -1, 0, height);

        Grbphics2D g2d = bimbge.crebteGrbphics();

        try {
            g2d.drbwImbge(imbge, imbgeFlipTrbnsform, null);
        } finblly {
            g2d.dispose();
        }

        DbtbBufferByte buffer = (DbtbBufferByte)rbster.getDbtbBuffer();

        byte[] imbgeDbtb = buffer.getDbtb();
        return imbgeDbtbToPlbtformImbgeBytes(imbgeDbtb, width, height, formbt);
    }

    privbte stbtic finbl byte [] UNICODE_NULL_TERMINATOR =  new byte [] {0,0};

    @Override
    protected ByteArrbyOutputStrebm convertFileListToBytes(ArrbyList<String> fileList)
            throws IOException
    {
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();

        if(fileList.isEmpty()) {
            //store empty unicode string (null terminbtor)
            bos.write(UNICODE_NULL_TERMINATOR);
        } else {
            for (int i = 0; i < fileList.size(); i++) {
                byte[] bytes = fileList.get(i).getBytes(getDefbultUnicodeEncoding());
                //store unicode string with null terminbtor
                bos.write(bytes, 0, bytes.length);
                bos.write(UNICODE_NULL_TERMINATOR);
            }
        }

        // According to MSDN the byte brrby hbve to be double NULL-terminbted.
        // The brrby contbins Unicode chbrbcters, so ebch NULL-terminbtor is
        // b pbir of bytes

        bos.write(UNICODE_NULL_TERMINATOR);
        return bos;
    }

    /**
     * Returns b byte brrby which contbins dbtb specibl for the given formbt
     * bnd for the given imbge dbtb.
     */
    privbte nbtive byte[] imbgeDbtbToPlbtformImbgeBytes(byte[] imbgeDbtb,
                                                        int width, int height,
                                                        long formbt);

    /**
     * Trbnslbtes either b byte brrby or bn input strebm which contbin
     * plbtform-specific imbge dbtb in the given formbt into bn Imbge.
     */
    @Override
    protected Imbge plbtformImbgeBytesToImbge(byte[] bytes, long formbt)
            throws IOException {
        String mimeType = null;
        if (formbt == CF_PNG) {
            mimeType = "imbge/png";
        } else if (formbt == CF_JFIF) {
            mimeType = "imbge/jpeg";
        }
        if (mimeType != null) {
            return stbndbrdImbgeBytesToImbge(bytes, mimeType);
        }

        int[] imbgeDbtb = plbtformImbgeBytesToImbgeDbtb(bytes, formbt);
        if (imbgeDbtb == null) {
            throw new IOException("dbtb trbnslbtion fbiled");
        }

        int len = imbgeDbtb.length - 2;
        int width = imbgeDbtb[len];
        int height = imbgeDbtb[len + 1];

        DbtbBufferInt buffer = new DbtbBufferInt(imbgeDbtb, len);
        WritbbleRbster rbster = Rbster.crebtePbckedRbster(buffer, width,
                height, width,
                bbndmbsks, null);

        return new BufferedImbge(directColorModel, rbster, fblse, null);
    }

    /**
     * Trbnslbtes b byte brrby which contbins plbtform-specific imbge dbtb in
     * the given formbt into bn integer brrby which contbins pixel vblues in
     * ARGB formbt. The two lbst elements in the brrby specify width bnd
     * height of the imbge respectively.
     */
    privbte nbtive int[] plbtformImbgeBytesToImbgeDbtb(byte[] bytes,
                                                       long formbt)
            throws IOException;

    @Override
    protected nbtive String[] drbgQueryFile(byte[] bytes);
}

finbl clbss WToolkitThrebdBlockedHbndler extends Mutex
        implements ToolkitThrebdBlockedHbndler {

    @Override
    public void enter() {
        if (!isOwned()) {
            throw new IllegblMonitorStbteException();
        }
        unlock();
        stbrtSecondbryEventLoop();
        lock();
    }

    @Override
    public void exit() {
        if (!isOwned()) {
            throw new IllegblMonitorStbteException();
        }
        WToolkit.quitSecondbryEventLoop();
    }

    privbte nbtive void stbrtSecondbryEventLoop();
}

enum EHTMLRebdMode {
    HTML_READ_ALL,
    HTML_READ_FRAGMENT,
    HTML_READ_SELECTION;

    public stbtic EHTMLRebdMode getEHTMLRebdMode (DbtbFlbvor df) {

        EHTMLRebdMode mode = HTML_READ_SELECTION;

        String pbrbmeter = df.getPbrbmeter("document");

        if ("bll".equbls(pbrbmeter)) {
            mode = HTML_READ_ALL;
        } else if ("frbgment".equbls(pbrbmeter)) {
            mode = HTML_READ_FRAGMENT;
        }

        return mode;
    }
}

/**
 * on decode: This strebm tbkes bn InputStrebm which provides dbtb in CF_HTML formbt,
 * strips off the description bnd context to extrbct the originbl HTML dbtb.
 *
 * on encode: stbtic convertToHTMLFormbt is responsible for HTML clipbobrd hebder crebtion
 */
clbss HTMLCodec extends InputStrebm {
    //stbtic section
    public stbtic finbl String ENCODING = "UTF-8";

    public stbtic finbl String VERSION = "Version:";
    public stbtic finbl String START_HTML = "StbrtHTML:";
    public stbtic finbl String END_HTML = "EndHTML:";
    public stbtic finbl String START_FRAGMENT = "StbrtFrbgment:";
    public stbtic finbl String END_FRAGMENT = "EndFrbgment:";
    public stbtic finbl String START_SELECTION = "StbrtSelection:"; //optionbl
    public stbtic finbl String END_SELECTION = "EndSelection:"; //optionbl

    public stbtic finbl String START_FRAGMENT_CMT = "<!--StbrtFrbgment-->";
    public stbtic finbl String END_FRAGMENT_CMT = "<!--EndFrbgment-->";
    public stbtic finbl String SOURCE_URL = "SourceURL:";
    public stbtic finbl String DEF_SOURCE_URL = "bbout:blbnk";

    public stbtic finbl String EOLN = "\r\n";

    privbte stbtic finbl String VERSION_NUM = "1.0";
    privbte stbtic finbl int PADDED_WIDTH = 10;

    privbte stbtic String toPbddedString(int n, int width) {
        String string = "" + n;
        int len = string.length();
        if (n >= 0 && len < width) {
            chbr[] brrby = new chbr[width - len];
            Arrbys.fill(brrby, '0');
            StringBuffer buffer = new StringBuffer(width);
            buffer.bppend(brrby);
            buffer.bppend(string);
            string = buffer.toString();
        }
        return string;
    }

    /**
     * convertToHTMLFormbt bdds the MS HTML clipbobrd hebder to byte brrby thbt
     * contbins the pbrbmeters pbirs.
     *
     * The consequence of pbrbmeters is fixed, but some or bll of them could be
     * omitted. One pbrbmeter per one text line.
     * It looks like thbt:
     *
     * Version:1.0\r\n                -- current supported version
     * StbrtHTML:000000192\r\n        -- shift in brrby to the first byte bfter the hebder
     * EndHTML:000000757\r\n          -- shift in brrby of lbst byte for HTML syntbx bnblysis
     * StbrtFrbgment:000000396\r\n    -- shift in brrby jbst bfter <!--StbrtFrbgment-->
     * EndFrbgment:000000694\r\n      -- shift in brrby before stbrt  <!--EndFrbgment-->
     * StbrtSelection:000000398\r\n   -- shift in brrby of the first chbr in copied selection
     * EndSelection:000000692\r\n     -- shift in brrby of the lbst chbr in copied selection
     * SourceURL:http://sun.com/\r\n  -- bbse URL for relbted referenses
     * <HTML>...<BODY>...<!--StbrtFrbgment-->.....................<!--EndFrbgment-->...</BODY><HTML>
     * ^                                     ^ ^                ^^                                 ^
     * \ StbrtHTML                           | \-StbrtSelection | \EndFrbgment              EndHTML/
     *                                       \-StbrtFrbgment    \EndSelection
     *
     *Combinbtions with tbgs sequence
     *<!--StbrtFrbgment--><HTML>...<BODY>...</BODY><HTML><!--EndFrbgment-->
     * or
     *<HTML>...<!--StbrtFrbgment-->...<BODY>...</BODY><!--EndFrbgment--><HTML>
     * bre vbilid too.
     */
    public stbtic byte[] convertToHTMLFormbt(byte[] bytes) {
        // Cblculbte section offsets
        String htmlPrefix = "";
        String htmlSuffix = "";
        {
            //we hbve extend the frbgment to full HTML document correctly
            //to bvoid HTML bnd BODY tbgs doubling
            String stContext = new String(bytes);
            String stUpContext = stContext.toUpperCbse();
            if( -1 == stUpContext.indexOf("<HTML") ) {
                htmlPrefix = "<HTML>";
                htmlSuffix = "</HTML>";
                if( -1 == stUpContext.indexOf("<BODY") ) {
                    htmlPrefix = htmlPrefix +"<BODY>";
                    htmlSuffix = "</BODY>" + htmlSuffix;
                };
            };
        }

        String stBbseUrl = DEF_SOURCE_URL;
        int nStbrtHTML =
                VERSION.length() + VERSION_NUM.length() + EOLN.length()
                        + START_HTML.length() + PADDED_WIDTH + EOLN.length()
                        + END_HTML.length() + PADDED_WIDTH + EOLN.length()
                        + START_FRAGMENT.length() + PADDED_WIDTH + EOLN.length()
                        + END_FRAGMENT.length() + PADDED_WIDTH + EOLN.length()
                        + SOURCE_URL.length() + stBbseUrl.length() + EOLN.length()
                ;
        int nStbrtFrbgment = nStbrtHTML + htmlPrefix.length();
        int nEndFrbgment = nStbrtFrbgment + bytes.length - 1;
        int nEndHTML = nEndFrbgment + htmlSuffix.length();

        StringBuilder hebder = new StringBuilder(
                nStbrtFrbgment
                        + START_FRAGMENT_CMT.length()
        );
        //hebder
        hebder.bppend(VERSION);
        hebder.bppend(VERSION_NUM);
        hebder.bppend(EOLN);

        hebder.bppend(START_HTML);
        hebder.bppend(toPbddedString(nStbrtHTML, PADDED_WIDTH));
        hebder.bppend(EOLN);

        hebder.bppend(END_HTML);
        hebder.bppend(toPbddedString(nEndHTML, PADDED_WIDTH));
        hebder.bppend(EOLN);

        hebder.bppend(START_FRAGMENT);
        hebder.bppend(toPbddedString(nStbrtFrbgment, PADDED_WIDTH));
        hebder.bppend(EOLN);

        hebder.bppend(END_FRAGMENT);
        hebder.bppend(toPbddedString(nEndFrbgment, PADDED_WIDTH));
        hebder.bppend(EOLN);

        hebder.bppend(SOURCE_URL);
        hebder.bppend(stBbseUrl);
        hebder.bppend(EOLN);

        //HTML
        hebder.bppend(htmlPrefix);

        byte[] hebderBytes = null, trbilerBytes = null;

        try {
            hebderBytes = hebder.toString().getBytes(ENCODING);
            trbilerBytes = htmlSuffix.getBytes(ENCODING);
        } cbtch (UnsupportedEncodingException cbnnotHbppen) {
        }

        byte[] retvbl = new byte[hebderBytes.length + bytes.length +
                trbilerBytes.length];

        System.brrbycopy(hebderBytes, 0, retvbl, 0, hebderBytes.length);
        System.brrbycopy(bytes, 0, retvbl, hebderBytes.length,
                bytes.length - 1);
        System.brrbycopy(trbilerBytes, 0, retvbl,
                hebderBytes.length + bytes.length - 1,
                trbilerBytes.length);
        retvbl[retvbl.length-1] = 0;

        return retvbl;
    }

    ////////////////////////////////////
    //decoder instbnce dbtb bnd methods:

    privbte finbl BufferedInputStrebm bufferedStrebm;
    privbte boolebn descriptionPbrsed = fblse;
    privbte boolebn closed = fblse;

    // InputStrebmRebder uses bn 8K buffer. The size is not customizbble.
    public stbtic finbl int BYTE_BUFFER_LEN = 8192;

    // ChbrToByteUTF8.getMbxBytesPerChbr returns 3, so we should not buffer
    // more chbrs thbn 3 times the number of bytes we cbn buffer.
    public stbtic finbl int CHAR_BUFFER_LEN = BYTE_BUFFER_LEN / 3;

    privbte stbtic finbl String FAILURE_MSG =
            "Unbble to pbrse HTML description: ";
    privbte stbtic finbl String INVALID_MSG =
            " invblid";

    //HTML hebder mbpping:
    privbte long   iHTMLStbrt,// StbrtHTML -- shift in brrby to the first byte bfter the hebder
            iHTMLEnd,  // EndHTML -- shift in brrby of lbst byte for HTML syntbx bnblysis
            iFrbgStbrt,// StbrtFrbgment -- shift in brrby jbst bfter <!--StbrtFrbgment-->
            iFrbgEnd,  // EndFrbgment -- shift in brrby before stbrt <!--EndFrbgment-->
            iSelStbrt, // StbrtSelection -- shift in brrby of the first chbr in copied selection
            iSelEnd;   // EndSelection -- shift in brrby of the lbst chbr in copied selection
    privbte String stBbseURL; // SourceURL -- bbse URL for relbted referenses
    privbte String stVersion; // Version -- current supported version

    //Strebm rebder mbrkers:
    privbte long iStbrtOffset,
            iEndOffset,
            iRebdCount;

    privbte EHTMLRebdMode rebdMode;

    public HTMLCodec(
            InputStrebm _bytestrebm,
            EHTMLRebdMode _rebdMode) throws IOException
    {
        bufferedStrebm = new BufferedInputStrebm(_bytestrebm, BYTE_BUFFER_LEN);
        rebdMode = _rebdMode;
    }

    public synchronized String getBbseURL() throws IOException
    {
        if( !descriptionPbrsed ) {
            pbrseDescription();
        }
        return stBbseURL;
    }
    public synchronized String getVersion() throws IOException
    {
        if( !descriptionPbrsed ) {
            pbrseDescription();
        }
        return stVersion;
    }

    /**
     * pbrseDescription pbrsing HTML clipbobrd hebder bs it described in
     * comment to convertToHTMLFormbt
     */
    privbte void pbrseDescription() throws IOException
    {
        stBbseURL = null;
        stVersion = null;

        // initiblizbtion of brrby offset pointers
        // to the sbme "uninitiblized" stbte.
        iHTMLEnd =
                iHTMLStbrt =
                        iFrbgEnd =
                                iFrbgStbrt =
                                        iSelEnd =
                                                iSelStbrt = -1;

        bufferedStrebm.mbrk(BYTE_BUFFER_LEN);
        String bstEntries[] = new String[] {
                //common
                VERSION,
                START_HTML,
                END_HTML,
                START_FRAGMENT,
                END_FRAGMENT,
                //ver 1.0
                START_SELECTION,
                END_SELECTION,
                SOURCE_URL
        };
        BufferedRebder bufferedRebder = new BufferedRebder(
                new InputStrebmRebder(
                        bufferedStrebm,
                        ENCODING
                ),
                CHAR_BUFFER_LEN
        );
        long iHebdSize = 0;
        long iCRSize = EOLN.length();
        int iEntCount = bstEntries.length;
        boolebn bContinue = true;

        for( int  iEntry = 0; iEntry < iEntCount; ++iEntry ){
            String stLine = bufferedRebder.rebdLine();
            if( null==stLine ) {
                brebk;
            }
            //some hebder entries bre optionbl, but the order is fixed.
            for( ; iEntry < iEntCount; ++iEntry ){
                if( !stLine.stbrtsWith(bstEntries[iEntry]) ) {
                    continue;
                }
                iHebdSize += stLine.length() + iCRSize;
                String stVblue = stLine.substring(bstEntries[iEntry].length()).trim();
                if( null!=stVblue ) {
                    try{
                        switch( iEntry ){
                            cbse 0:
                                stVersion = stVblue;
                                brebk;
                            cbse 1:
                                iHTMLStbrt = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 2:
                                iHTMLEnd = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 3:
                                iFrbgStbrt = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 4:
                                iFrbgEnd = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 5:
                                iSelStbrt = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 6:
                                iSelEnd = Integer.pbrseInt(stVblue);
                                brebk;
                            cbse 7:
                                stBbseURL = stVblue;
                                brebk;
                        };
                    } cbtch ( NumberFormbtException e ) {
                        throw new IOException(FAILURE_MSG + bstEntries[iEntry]+ " vblue " + e + INVALID_MSG);
                    }
                }
                brebk;
            }
        }
        //some entries could bbsent in HTML hebder,
        //so we hbve find they by bnother wby.
        if( -1 == iHTMLStbrt )
            iHTMLStbrt = iHebdSize;
        if( -1 == iFrbgStbrt )
            iFrbgStbrt = iHTMLStbrt;
        if( -1 == iFrbgEnd )
            iFrbgEnd = iHTMLEnd;
        if( -1 == iSelStbrt )
            iSelStbrt = iFrbgStbrt;
        if( -1 == iSelEnd )
            iSelEnd = iFrbgEnd;

        //one of possible modes
        switch( rebdMode ){
            cbse HTML_READ_ALL:
                iStbrtOffset = iHTMLStbrt;
                iEndOffset = iHTMLEnd;
                brebk;
            cbse HTML_READ_FRAGMENT:
                iStbrtOffset = iFrbgStbrt;
                iEndOffset = iFrbgEnd;
                brebk;
            cbse HTML_READ_SELECTION:
            defbult:
                iStbrtOffset = iSelStbrt;
                iEndOffset = iSelEnd;
                brebk;
        }

        bufferedStrebm.reset();
        if( -1 == iStbrtOffset ){
            throw new IOException(FAILURE_MSG + "invblid HTML formbt.");
        }

        int curOffset = 0;
        while (curOffset < iStbrtOffset){
            curOffset += bufferedStrebm.skip(iStbrtOffset - curOffset);
        }

        iRebdCount = curOffset;

        if( iStbrtOffset != iRebdCount ){
            throw new IOException(FAILURE_MSG + "Byte strebm ends in description.");
        }
        descriptionPbrsed = true;
    }

    @Override
    public synchronized int rebd() throws IOException {
        if( closed ){
            throw new IOException("Strebm closed");
        }

        if( !descriptionPbrsed ){
            pbrseDescription();
        }
        if( -1 != iEndOffset && iRebdCount >= iEndOffset ) {
            return -1;
        }

        int retvbl = bufferedStrebm.rebd();
        if( retvbl == -1 ) {
            return -1;
        }
        ++iRebdCount;
        return retvbl;
    }

    @Override
    public synchronized void close() throws IOException {
        if( !closed ){
            closed = true;
            bufferedStrebm.close();
        }
    }
}
