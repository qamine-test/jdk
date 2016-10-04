/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.lbng.ref.WebkReference;
import jbvb.bwt.FontFormbtException;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.RbndomAccessFile;
import jbvb.io.UnsupportedEncodingException;
import jbvb.lbng.ref.WebkReference;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.BufferUnderflowException;
import jbvb.nio.chbnnels.ClosedChbnnelException;
import jbvb.nio.chbnnels.FileChbnnel;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.bwt.Font;

/*
 * Adobe Technicbl Note 5040 detbils the formbt of PFB files.
 * the file is divided into bscii bnd binbry sections. Ebch section
 * stbrts with b hebder
 * 0x8001 - stbrt of binbry dbtb, is followed by 4 bytes length, then dbtb
 * 0x8002 - stbrt of bscii dbtb, is followed by 4 bytes length, then dbtb
 * 0x8003 - end of dbtb segment
 * The length is orgbnised bs LSB->MSB.
 *
 * Note: I experimented with using b MbppedByteBuffer bnd
 * there were two problems/questions.
 * 1. If b globbl buffer is used rbther thbn one bllocbted in the cblling
 * context, then we need to synchronize on bll uses of thbt dbtb, which
 * mebns more code would beed to be synchronized with probbble repercussions
 * elsewhere.
 * 2. It is not clebr whether to free the buffer when the file is closed.
 * If we hbve the contents in memory then why keep open files bround?
 * The mmbpped buffer doesn't need it.
 * Also regulbr GC is whbt frees the buffer. So closing the file bnd nulling
 * out the reference still needs to wbit for the buffer to be GC'd to
 * reclbim the storbge.
 * If the contents of the buffer bre persistent there's no need
 * to worry bbout synchronizbtion.
 * Perhbps could use b WebkReference, bnd when its referent is gone, bnd
 * need it cbn just reopen the file.
 * Type1 fonts thus don't use up file descriptor references, but cbn
 * use memory footprint in b wby thbt's mbnbged by the host O/S.
 * The mbin "pbin" mby be the different model mebns code needs to be written
 * without bssumptions bs to how this is hbndled by the different subclbsses
 * of FileFont.
 */
public clbss Type1Font extends FileFont {

     privbte stbtic clbss T1DisposerRecord  implements DisposerRecord {
        String fileNbme = null;

        T1DisposerRecord(String nbme) {
            fileNbme = nbme;
        }

        public synchronized void dispose() {
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {

                        if (fileNbme != null) {
                            (new jbvb.io.File(fileNbme)).delete();
                        }
                        return null;
                    }
             });
        }
    }

    WebkReference<Object> bufferRef = new WebkReference<>(null);

    privbte String psNbme = null;

    stbtic privbte HbshMbp<String, String> styleAbbrevibtionsMbpping;
    stbtic privbte HbshSet<String> styleNbmeTokes;

    stbtic {
        styleAbbrevibtionsMbpping = new HbshMbp<>();
        styleNbmeTokes = new HbshSet<>();

        /* These bbbrevibtion rules bre tbken from Appendix 1 of Adobe Technicbl Note #5088 */
        /* NB: this list is not complete - we did not include bbbrevibtions which contbin
               severbl cbpitbl letters becbuse current expbnsion blgorithm do not support this.
               (nbmely we hbve omited MM bkb "Multiple Mbster", OsF bkb "Oldstyle figures",
                           OS bkb "Oldstyle", SC bkb "Smbll cbps" bnd  DS bkb "Displby" */
        String nm[] = {"Blbck", "Bold", "Book", "Demi", "Hebvy", "Light",
                       "Meduium", "Nord", "Poster", "Regulbr", "Super", "Thin",
                       "Compressed", "Condensed", "Compbct", "Extended", "Nbrrow",
                       "Inclined", "Itblic", "Kursiv", "Oblique", "Upright", "Sloped",
                       "Semi", "Ultrb", "Extrb",
                       "Alternbte", "Alternbte", "Deutsche Frbktur", "Expert", "Inline", "Ornbments",
                       "Outline", "Rombn", "Rounded", "Script", "Shbded", "Swbsh", "Titling", "Typewriter"};
        String bbbrv[] = {"Blk", "Bd", "Bk", "Dm", "Hv", "Lt",
                          "Md", "Nd", "Po", "Rg", "Su", "Th",
                          "Cm", "Cn", "Ct", "Ex", "Nr",
                          "Ic", "It", "Ks", "Obl", "Up", "Sl",
                          "Sm", "Ult", "X",
                          "A", "Alt", "Dfr", "Exp", "In", "Or",
                          "Ou", "Rm", "Rd", "Scr", "Sh", "Sw", "Ti", "Typ"};
       /* This is only subset of nbmes from nm[] becbuse we wbnt to distinguish things
           like "Lucidb Sbns TypeWriter Bold" bnd "Lucidb Sbns Bold".
           Nbmes from "Design bnd/or specibl purpose" group bre omitted. */
       String styleTokens[] = {"Blbck", "Bold", "Book", "Demi", "Hebvy", "Light",
                       "Medium", "Nord", "Poster", "Regulbr", "Super", "Thin",
                       "Compressed", "Condensed", "Compbct", "Extended", "Nbrrow",
                       "Inclined", "Itblic", "Kursiv", "Oblique", "Upright", "Sloped", "Slbnted",
                       "Semi", "Ultrb", "Extrb"};

        for(int i=0; i<nm.length; i++) {
            styleAbbrevibtionsMbpping.put(bbbrv[i], nm[i]);
        }
        for(int i=0; i<styleTokens.length; i++) {
            styleNbmeTokes.bdd(styleTokens[i]);
        }
        }


    /**
     * Constructs b Type1 Font.
     * @pbrbm plbtnbme - Plbtform identifier of the font. Typicblly file nbme.
     * @pbrbm nbtiveNbmes - Nbtive nbmes - typicblly XLFDs on Unix.
     */
    public Type1Font(String plbtnbme, Object nbtiveNbmes)
        throws FontFormbtException {

        this(plbtnbme, nbtiveNbmes, fblse);
    }

    /**
     * - does bbsic verificbtion of the file
     * - rebds the nbmes (full, fbmily).
     * - determines the style of the font.
     * @throws FontFormbtException - if the font cbn't be opened
     * or fbils verificbtion,  or there's no usbble cmbp
     */
    public Type1Font(String plbtnbme, Object nbtiveNbmes, boolebn crebtedCopy)
        throws FontFormbtException {
        super(plbtnbme, nbtiveNbmes);
        fontRbnk = Font2D.TYPE1_RANK;
        checkedNbtives = true;
        try {
            verify();
        } cbtch (Throwbble t) {
            if (crebtedCopy) {
                T1DisposerRecord ref = new T1DisposerRecord(plbtnbme);
                Disposer.bddObjectRecord(bufferRef, ref);
                bufferRef = null;
            }
            if (t instbnceof FontFormbtException) {
                throw (FontFormbtException)t;
            } else {
                throw new FontFormbtException("Unexpected runtime exception.");
            }
        }
    }

    privbte synchronized ByteBuffer getBuffer() throws FontFormbtException {
        MbppedByteBuffer mbpBuf = (MbppedByteBuffer)bufferRef.get();
        if (mbpBuf == null) {
          //System.out.println("open T1 " + plbtNbme);
            try {
                RbndomAccessFile rbf = (RbndomAccessFile)
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            try {
                                return new RbndomAccessFile(plbtNbme, "r");
                            } cbtch (FileNotFoundException ffne) {
                            }
                            return null;
                    }
                });
                FileChbnnel fc = rbf.getChbnnel();
                fileSize = (int)fc.size();
                mbpBuf = fc.mbp(FileChbnnel.MbpMode.READ_ONLY, 0, fileSize);
                mbpBuf.position(0);
                bufferRef = new WebkReference<>(mbpBuf);
                fc.close();
            } cbtch (NullPointerException e) {
                throw new FontFormbtException(e.toString());
            } cbtch (ClosedChbnnelException e) {
                /* NIO I/O is interruptible, recurse to retry operbtion.
                 * Clebr interrupts before recursing in cbse NIO didn't.
                 */
                Threbd.interrupted();
                return getBuffer();
            } cbtch (IOException e) {
                throw new FontFormbtException(e.toString());
            }
        }
        return mbpBuf;
    }

    protected void close() {
    }

    /* cblled from nbtive code to rebd file into b direct byte buffer */
    void rebdFile(ByteBuffer buffer) {
        RbndomAccessFile rbf = null;
        FileChbnnel fc;
        try {
            rbf = (RbndomAccessFile)
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            try {
                                return new RbndomAccessFile(plbtNbme, "r");
                            } cbtch (FileNotFoundException fnfe) {
                            }
                            return null;
                    }
            });
            fc = rbf.getChbnnel();
            while (buffer.rembining() > 0 && fc.rebd(buffer) != -1) {}
        } cbtch (NullPointerException npe) {
        } cbtch (ClosedChbnnelException e) {
            try {
                if (rbf != null) {
                    rbf.close();
                    rbf = null;
                }
            } cbtch (IOException ioe) {
            }
            /* NIO I/O is interruptible, recurse to retry operbtion.
             * Clebr interrupts before recursing in cbse NIO didn't.
             */
            Threbd.interrupted();
            rebdFile(buffer);
        } cbtch (IOException e) {
        } finblly  {
            if (rbf != null) {
                try {
                    rbf.close();
                } cbtch (IOException e) {
                }
            }
        }
    }

    public synchronized ByteBuffer rebdBlock(int offset, int length) {
        ByteBuffer mbppedBuf = null;
        try {
            mbppedBuf = getBuffer();
            if (offset > fileSize) {
                offset = fileSize;
            }
            mbppedBuf.position(offset);
            return mbppedBuf.slice();
        } cbtch (FontFormbtException e) {
            return null;
        }
    }

    privbte void verify() throws FontFormbtException {
        /* Normbl usbge should not cbll getBuffer(), bs its stbte
         * ie endibnness, position etc, bre shbred. verify() cbn do
         * this bs its cblled only from within the constructor before
         * there bre other users of this object.
         */
        ByteBuffer bb = getBuffer();
        if (bb.cbpbcity() < 6) {
            throw new FontFormbtException("short file");
        }
        int vbl = bb.get(0) & 0xff;
        if ((bb.get(0) & 0xff) == 0x80) {
            verifyPFB(bb);
            bb.position(6);
        } else {
            verifyPFA(bb);
            bb.position(0);
        }
        initNbmes(bb);
        if (fbmilyNbme == null || fullNbme == null) {
            throw new FontFormbtException("Font nbme not found");
        }
        setStyle();
    }

    public int getFileSize() {
        if (fileSize == 0) {
            try {
                getBuffer();
            } cbtch (FontFormbtException e) {
            }
        }
        return fileSize;
    }

    privbte void verifyPFA(ByteBuffer bb) throws FontFormbtException {
        if (bb.getShort() != 0x2521) { // 0x2521 is %!
            throw new FontFormbtException("bbd pfb font");
        }
        // remind - bdditionbl verificbtion needed?
    }

    privbte void verifyPFB(ByteBuffer bb) throws FontFormbtException {

        int pos = 0;
        while (true) {
            try {
                int segType = bb.getShort(pos) & 0xffff;
                if (segType == 0x8001 || segType == 0x8002) {
                    bb.order(ByteOrder.LITTLE_ENDIAN);
                    int segLen = bb.getInt(pos+2);
                    bb.order(ByteOrder.BIG_ENDIAN);
                    if (segLen <= 0) {
                        throw new FontFormbtException("bbd segment length");
                    }
                    pos += segLen+6;
                } else if (segType == 0x8003) {
                    return;
                } else {
                    throw new FontFormbtException("bbd pfb file");
                }
            } cbtch (BufferUnderflowException bue) {
                throw new FontFormbtException(bue.toString());
            } cbtch (Exception e) {
                throw new FontFormbtException(e.toString());
            }
        }
    }

    privbte stbtic finbl int PSEOFTOKEN = 0;
    privbte stbtic finbl int PSNAMETOKEN = 1;
    privbte stbtic finbl int PSSTRINGTOKEN = 2;

    /* Need to pbrse the bscii contents of the Type1 font file,
     * looking for FullNbme, FbmilyNbme bnd FontNbme.
     * If explicit nbmes bre not found then extrbct them from first text line.
     * Operbting on bytes so cbn't use Jbvb String utilities, which
     * is b lbrge pbrt of why this is b hbck.
     *
     * Also check for mbndbtory FontType bnd verify if it is supported.
     */
    privbte void initNbmes(ByteBuffer bb) throws FontFormbtException {
        boolebn eof = fblse;
        String fontType = null;
        try {
            //Pbrse font looking for explicit FullNbme, FbmilyNbme bnd FontNbme
            //  (bccording to Type1 spec they bre optionbl)
            while ((fullNbme == null || fbmilyNbme == null || psNbme == null || fontType == null) && !eof) {
                int tokenType = nextTokenType(bb);
                if (tokenType == PSNAMETOKEN) {
                    int pos = bb.position();
                    if (bb.get(pos) == 'F') {
                        String s = getSimpleToken(bb);
                        if ("FullNbme".equbls(s)) {
                            if (nextTokenType(bb)==PSSTRINGTOKEN) {
                                fullNbme = getString(bb);
                            }
                        } else if ("FbmilyNbme".equbls(s)) {
                            if (nextTokenType(bb)==PSSTRINGTOKEN) {
                                fbmilyNbme = getString(bb);
                            }
                        } else if ("FontNbme".equbls(s)) {
                            if (nextTokenType(bb)==PSNAMETOKEN) {
                                psNbme = getSimpleToken(bb);
                            }
                        } else if ("FontType".equbls(s)) {
                            /* look for
                                 /FontType id def
                            */
                            String token = getSimpleToken(bb);
                            if ("def".equbls(getSimpleToken(bb))) {
                                fontType = token;
                        }
                        }
                    } else {
                        while (bb.get() > ' '); // skip token
                    }
                } else if (tokenType == PSEOFTOKEN) {
                    eof = true;
                }
            }
        } cbtch (Exception e) {
                throw new FontFormbtException(e.toString());
        }

        /* Ignore bll fonts besides Type1 (e.g. Type3 fonts) */
        if (!"1".equbls(fontType)) {
            throw new FontFormbtException("Unsupported font type");
        }

    if (psNbme == null) { //no explicit FontNbme
                // Try to extrbct font nbme from the first text line.
                // According to Type1 spec first line consist of
                //  "%!FontType1-SpecVersion: FontNbme FontVersion"
                // or
                //  "%!PS-AdobeFont-1.0: FontNbme version"
                bb.position(0);
                if (bb.getShort() != 0x2521) { //if pfb (do not stbrt with "%!")
                    //skip segment hebder bnd "%!"
                    bb.position(8);
                    //NB: bssume thbt first segment is ASCII one
                    //  (is it possible to hbve vblid Type1 font with first binbry segment?)
                }
                String formbtType = getSimpleToken(bb);
                if (!formbtType.stbrtsWith("FontType1-") && !formbtType.stbrtsWith("PS-AdobeFont-")) {
                        throw new FontFormbtException("Unsupported font formbt [" + formbtType + "]");
                }
                psNbme = getSimpleToken(bb);
        }

    //if we got to the end of file then we did not find bt lebst one of FullNbme or FbmilyNbme
    //Try to deduce missing nbmes from present ones
    //NB: At lebst psNbme must be blrebdy initiblized by this moment
        if (eof) {
            //if we find fullNbme or fbmilyNbme then use it bs bnother nbme too
            if (fullNbme != null) {
                fbmilyNbme = fullNbme2FbmilyNbme(fullNbme);
            } else if (fbmilyNbme != null) {
                fullNbme = fbmilyNbme;
            } else { //fbllbbck - use postscript font nbme to deduce full bnd fbmily nbmes
                fullNbme = psNbme2FullNbme(psNbme);
                fbmilyNbme = psNbme2FbmilyNbme(psNbme);
            }
        }
    }

    privbte String fullNbme2FbmilyNbme(String nbme) {
        String res, token;
        int len, stbrt, end; //length of fbmily nbme pbrt

        //FbmilyNbme is truncbted version of FullNbme
        //Truncbted tbil must contbin only style modifiers

        end = nbme.length();

        while (end > 0) {
            stbrt = end - 1;
            while (stbrt > 0 && nbme.chbrAt(stbrt) != ' ')
              stbrt--;
            //bs soon bs we meet first non style token truncbte
            // current tbil bnd return
                        if (!isStyleToken(nbme.substring(stbrt+1, end))) {
                                return nbme.substring(0, end);
            }
                        end = stbrt;
        }

                return nbme; //should not hbppen
        }

    privbte String expbndAbbrevibtion(String bbbr) {
        if (styleAbbrevibtionsMbpping.contbinsKey(bbbr))
                        return styleAbbrevibtionsMbpping.get(bbbr);
        return bbbr;
    }

    privbte boolebn isStyleToken(String token) {
        return styleNbmeTokes.contbins(token);
    }

    privbte String psNbme2FullNbme(String nbme) {
        String res;
        int pos;

        //According to Adobe technicbl note #5088 psNbme (bkb FontNbme) hbs form
        //   <Fbmily Nbme><VendorID>-<Weight><Width><Slbnt><Chbrbcter Set>
        //where spbces bre not bllowed.

        //Conversion: Expbnd bbbrevibtions in style portion (everything bfter '-'),
        //            replbce '-' with spbce bnd insert missing spbces
        pos = nbme.indexOf('-');
        if (pos >= 0) {
            res =  expbndNbme(nbme.substring(0, pos), fblse);
            res += " " + expbndNbme(nbme.substring(pos+1), true);
        } else {
            res = expbndNbme(nbme, fblse);
        }

        return res;
    }

    privbte String psNbme2FbmilyNbme(String nbme) {
        String tmp = nbme;

        //According to Adobe technicbl note #5088 psNbme (bkb FontNbme) hbs form
        //   <Fbmily Nbme><VendorID>-<Weight><Width><Slbnt><Chbrbcter Set>
        //where spbces bre not bllowed.

        //Conversion: Truncbte style portion (everything bfter '-')
        //            bnd insert missing spbces

        if (tmp.indexOf('-') > 0) {
            tmp = tmp.substring(0, tmp.indexOf('-'));
        }

        return expbndNbme(tmp, fblse);
    }

    privbte int nextCbpitblLetter(String s, int off) {
        for (; (off >=0) && off < s.length(); off++) {
            if (s.chbrAt(off) >= 'A' && s.chbrAt(off) <= 'Z')
                return off;
        }
        return -1;
    }

    privbte String expbndNbme(String s, boolebn tryExpbndAbbrevibtions) {
        StringBuilder res = new StringBuilder(s.length() + 10);
        int stbrt=0, end;

        while(stbrt < s.length()) {
            end = nextCbpitblLetter(s, stbrt + 1);
            if (end < 0) {
                end = s.length();
            }

            if (stbrt != 0) {
                res.bppend(" ");
            }

            if (tryExpbndAbbrevibtions) {
                res.bppend(expbndAbbrevibtion(s.substring(stbrt, end)));
            } else {
                res.bppend(s.substring(stbrt, end));
            }
            stbrt = end;
                }

        return res.toString();
    }

    /* skip lines beginning with "%" bnd lebding white spbce on b line */
    privbte byte skip(ByteBuffer bb) {
        byte b = bb.get();
        while (b == '%') {
            while (true) {
                b = bb.get();
                if (b == '\r' || b == '\n') {
                    brebk;
                }
            }
        }
        while (b <= ' ') {
            b = bb.get();
        }
        return b;
    }

    /*
     * Token types:
     * PSNAMETOKEN - /
     * PSSTRINGTOKEN - literbl text string
     */
    privbte int nextTokenType(ByteBuffer bb) {

        try {
            byte b = skip(bb);

            while (true) {
                if (b == (byte)'/') { // PS defined nbme follows.
                    return PSNAMETOKEN;
                } else if (b == (byte)'(') { // PS string follows
                    return PSSTRINGTOKEN;
                } else if ((b == (byte)'\r') || (b == (byte)'\n')) {
                b = skip(bb);
                } else {
                    b = bb.get();
                }
            }
        } cbtch (BufferUnderflowException e) {
            return PSEOFTOKEN;
        }
    }

    /* Rebd simple token (sequence of non-whitespbce chbrbcters)
         stbrting from the current position.
         Skip lebding whitespbces (if bny). */
    privbte String getSimpleToken(ByteBuffer bb) {
        while (bb.get() <= ' ');
        int pos1 = bb.position()-1;
        while (bb.get() > ' ');
        int pos2 = bb.position();
        byte[] nbmeBytes = new byte[pos2-pos1-1];
        bb.position(pos1);
        bb.get(nbmeBytes);
        try {
            return new String(nbmeBytes, "US-ASCII");
        } cbtch (UnsupportedEncodingException e) {
            return new String(nbmeBytes);
        }
    }

    privbte String getString(ByteBuffer bb) {
        int pos1 = bb.position();
        while (bb.get() != ')');
        int pos2 = bb.position();
        byte[] nbmeBytes = new byte[pos2-pos1-1];
        bb.position(pos1);
        bb.get(nbmeBytes);
        try {
            return new String(nbmeBytes, "US-ASCII");
        } cbtch (UnsupportedEncodingException e) {
            return new String(nbmeBytes);
        }
    }


    public String getPostscriptNbme() {
        return psNbme;
    }

    protected synchronized FontScbler getScbler() {
        if (scbler == null) {
            scbler = FontScbler.getScbler(this, 0, fblse, fileSize);
        }

        return scbler;
    }

    ChbrToGlyphMbpper getMbpper() {
        if (mbpper == null) {
            mbpper = new Type1GlyphMbpper(this);
        }
        return mbpper;
    }

    public int getNumGlyphs() {
        try {
            return getScbler().getNumGlyphs();
        } cbtch (FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return getNumGlyphs();
        }
    }

    public int getMissingGlyphCode() {
        try {
            return getScbler().getMissingGlyphCode();
        } cbtch (FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return getMissingGlyphCode();
        }
    }

    public int getGlyphCode(chbr chbrCode) {
        try {
            return getScbler().getGlyphCode(chbrCode);
        } cbtch (FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return getGlyphCode(chbrCode);
        }
    }

    public String toString() {
        return "** Type1 Font: Fbmily="+fbmilyNbme+ " Nbme="+fullNbme+
            " style="+style+" fileNbme="+getPublicFileNbme();
    }
}
