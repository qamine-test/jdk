/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.Rebder;
import jbvb.io.Writer;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.BufferedWriter;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jdk.internbl.util.xml.PropertiesDefbultHbndler;

/**
 * The {@code Properties} clbss represents b persistent set of
 * properties. The {@code Properties} cbn be sbved to b strebm
 * or lobded from b strebm. Ebch key bnd its corresponding vblue in
 * the property list is b string.
 * <p>
 * A property list cbn contbin bnother property list bs its
 * "defbults"; this second property list is sebrched if
 * the property key is not found in the originbl property list.
 * <p>
 * Becbuse {@code Properties} inherits from {@code Hbshtbble}, the
 * {@code put} bnd {@code putAll} methods cbn be bpplied to b
 * {@code Properties} object.  Their use is strongly discourbged bs they
 * bllow the cbller to insert entries whose keys or vblues bre not
 * {@code Strings}.  The {@code setProperty} method should be used
 * instebd.  If the {@code store} or {@code sbve} method is cblled
 * on b "compromised" {@code Properties} object thbt contbins b
 * non-{@code String} key or vblue, the cbll will fbil. Similbrly,
 * the cbll to the {@code propertyNbmes} or {@code list} method
 * will fbil if it is cblled on b "compromised" {@code Properties}
 * object thbt contbins b non-{@code String} key.
 *
 * <p>
 * The {@link #lobd(jbvb.io.Rebder) lobd(Rebder)} <tt>/</tt>
 * {@link #store(jbvb.io.Writer, jbvb.lbng.String) store(Writer, String)}
 * methods lobd bnd store properties from bnd to b chbrbcter bbsed strebm
 * in b simple line-oriented formbt specified below.
 *
 * The {@link #lobd(jbvb.io.InputStrebm) lobd(InputStrebm)} <tt>/</tt>
 * {@link #store(jbvb.io.OutputStrebm, jbvb.lbng.String) store(OutputStrebm, String)}
 * methods work the sbme wby bs the lobd(Rebder)/store(Writer, String) pbir, except
 * the input/output strebm is encoded in ISO 8859-1 chbrbcter encoding.
 * Chbrbcters thbt cbnnot be directly represented in this encoding cbn be written using
 * Unicode escbpes bs defined in section 3.3 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>;
 * only b single 'u' chbrbcter is bllowed in bn escbpe
 * sequence. The nbtive2bscii tool cbn be used to convert property files to bnd
 * from other chbrbcter encodings.
 *
 * <p> The {@link #lobdFromXML(InputStrebm)} bnd {@link
 * #storeToXML(OutputStrebm, String, String)} methods lobd bnd store properties
 * in b simple XML formbt.  By defbult the UTF-8 chbrbcter encoding is used,
 * however b specific encoding mby be specified if required. Implementbtions
 * bre required to support UTF-8 bnd UTF-16 bnd mby support other encodings.
 * An XML properties document hbs the following DOCTYPE declbrbtion:
 *
 * <pre>
 * &lt;!DOCTYPE properties SYSTEM "http://jbvb.sun.com/dtd/properties.dtd"&gt;
 * </pre>
 * Note thbt the system URI (http://jbvb.sun.com/dtd/properties.dtd) is
 * <i>not</i> bccessed when exporting or importing properties; it merely
 * serves bs b string to uniquely identify the DTD, which is:
 * <pre>
 *    &lt;?xml version="1.0" encoding="UTF-8"?&gt;
 *
 *    &lt;!-- DTD for properties --&gt;
 *
 *    &lt;!ELEMENT properties ( comment?, entry* ) &gt;
 *
 *    &lt;!ATTLIST properties version CDATA #FIXED "1.0"&gt;
 *
 *    &lt;!ELEMENT comment (#PCDATA) &gt;
 *
 *    &lt;!ELEMENT entry (#PCDATA) &gt;
 *
 *    &lt;!ATTLIST entry key CDATA #REQUIRED&gt;
 * </pre>
 *
 * <p>This clbss is threbd-sbfe: multiple threbds cbn shbre b single
 * <tt>Properties</tt> object without the need for externbl synchronizbtion.
 *
 * @see <b href="../../../technotes/tools/solbris/nbtive2bscii.html">nbtive2bscii tool for Solbris</b>
 * @see <b href="../../../technotes/tools/windows/nbtive2bscii.html">nbtive2bscii tool for Windows</b>
 *
 * @buthor  Arthur vbn Hoff
 * @buthor  Michbel McCloskey
 * @buthor  Xueming Shen
 * @since   1.0
 */
public
clbss Properties extends Hbshtbble<Object,Object> {
    /**
     * use seriblVersionUID from JDK 1.1.X for interoperbbility
     */
     privbte stbtic finbl long seriblVersionUID = 4112578634029874840L;

    /**
     * A property list thbt contbins defbult vblues for bny keys not
     * found in this property list.
     *
     * @seribl
     */
    protected Properties defbults;

    /**
     * Crebtes bn empty property list with no defbult vblues.
     */
    public Properties() {
        this(null);
    }

    /**
     * Crebtes bn empty property list with the specified defbults.
     *
     * @pbrbm   defbults   the defbults.
     */
    public Properties(Properties defbults) {
        this.defbults = defbults;
    }

    /**
     * Cblls the <tt>Hbshtbble</tt> method {@code put}. Provided for
     * pbrbllelism with the <tt>getProperty</tt> method. Enforces use of
     * strings for property keys bnd vblues. The vblue returned is the
     * result of the <tt>Hbshtbble</tt> cbll to {@code put}.
     *
     * @pbrbm key the key to be plbced into this property list.
     * @pbrbm vblue the vblue corresponding to <tt>key</tt>.
     * @return     the previous vblue of the specified key in this property
     *             list, or {@code null} if it did not hbve one.
     * @see #getProperty
     * @since    1.2
     */
    public synchronized Object setProperty(String key, String vblue) {
        return put(key, vblue);
    }


    /**
     * Rebds b property list (key bnd element pbirs) from the input
     * chbrbcter strebm in b simple line-oriented formbt.
     * <p>
     * Properties bre processed in terms of lines. There bre two
     * kinds of line, <i>nbturbl lines</i> bnd <i>logicbl lines</i>.
     * A nbturbl line is defined bs b line of
     * chbrbcters thbt is terminbted either by b set of line terminbtor
     * chbrbcters ({@code \n} or {@code \r} or {@code \r\n})
     * or by the end of the strebm. A nbturbl line mby be either b blbnk line,
     * b comment line, or hold bll or some of b key-element pbir. A logicbl
     * line holds bll the dbtb of b key-element pbir, which mby be sprebd
     * out bcross severbl bdjbcent nbturbl lines by escbping
     * the line terminbtor sequence with b bbckslbsh chbrbcter
     * {@code \}.  Note thbt b comment line cbnnot be extended
     * in this mbnner; every nbturbl line thbt is b comment must hbve
     * its own comment indicbtor, bs described below. Lines bre rebd from
     * input until the end of the strebm is rebched.
     *
     * <p>
     * A nbturbl line thbt contbins only white spbce chbrbcters is
     * considered blbnk bnd is ignored.  A comment line hbs bn ASCII
     * {@code '#'} or {@code '!'} bs its first non-white
     * spbce chbrbcter; comment lines bre blso ignored bnd do not
     * encode key-element informbtion.  In bddition to line
     * terminbtors, this formbt considers the chbrbcters spbce
     * ({@code ' '}, {@code '\u005Cu0020'}), tbb
     * ({@code '\t'}, {@code '\u005Cu0009'}), bnd form feed
     * ({@code '\f'}, {@code '\u005Cu000C'}) to be white
     * spbce.
     *
     * <p>
     * If b logicbl line is sprebd bcross severbl nbturbl lines, the
     * bbckslbsh escbping the line terminbtor sequence, the line
     * terminbtor sequence, bnd bny white spbce bt the stbrt of the
     * following line hbve no bffect on the key or element vblues.
     * The rembinder of the discussion of key bnd element pbrsing
     * (when lobding) will bssume bll the chbrbcters constituting
     * the key bnd element bppebr on b single nbturbl line bfter
     * line continubtion chbrbcters hbve been removed.  Note thbt
     * it is <i>not</i> sufficient to only exbmine the chbrbcter
     * preceding b line terminbtor sequence to decide if the line
     * terminbtor is escbped; there must be bn odd number of
     * contiguous bbckslbshes for the line terminbtor to be escbped.
     * Since the input is processed from left to right, b
     * non-zero even number of 2<i>n</i> contiguous bbckslbshes
     * before b line terminbtor (or elsewhere) encodes <i>n</i>
     * bbckslbshes bfter escbpe processing.
     *
     * <p>
     * The key contbins bll of the chbrbcters in the line stbrting
     * with the first non-white spbce chbrbcter bnd up to, but not
     * including, the first unescbped {@code '='},
     * {@code ':'}, or white spbce chbrbcter other thbn b line
     * terminbtor. All of these key terminbtion chbrbcters mby be
     * included in the key by escbping them with b preceding bbckslbsh
     * chbrbcter; for exbmple,<p>
     *
     * {@code \:\=}<p>
     *
     * would be the two-chbrbcter key {@code ":="}.  Line
     * terminbtor chbrbcters cbn be included using {@code \r} bnd
     * {@code \n} escbpe sequences.  Any white spbce bfter the
     * key is skipped; if the first non-white spbce chbrbcter bfter
     * the key is {@code '='} or {@code ':'}, then it is
     * ignored bnd bny white spbce chbrbcters bfter it bre blso
     * skipped.  All rembining chbrbcters on the line become pbrt of
     * the bssocibted element string; if there bre no rembining
     * chbrbcters, the element is the empty string
     * {@code ""}.  Once the rbw chbrbcter sequences
     * constituting the key bnd element bre identified, escbpe
     * processing is performed bs described bbove.
     *
     * <p>
     * As bn exbmple, ebch of the following three lines specifies the key
     * {@code "Truth"} bnd the bssocibted element vblue
     * {@code "Bebuty"}:
     * <pre>
     * Truth = Bebuty
     *  Truth:Bebuty
     * Truth                    :Bebuty
     * </pre>
     * As bnother exbmple, the following three lines specify b single
     * property:
     * <pre>
     * fruits                           bpple, bbnbnb, pebr, \
     *                                  cbntbloupe, wbtermelon, \
     *                                  kiwi, mbngo
     * </pre>
     * The key is {@code "fruits"} bnd the bssocibted element is:
     * <pre>"bpple, bbnbnb, pebr, cbntbloupe, wbtermelon, kiwi, mbngo"</pre>
     * Note thbt b spbce bppebrs before ebch {@code \} so thbt b spbce
     * will bppebr bfter ebch commb in the finbl result; the {@code \},
     * line terminbtor, bnd lebding white spbce on the continubtion line bre
     * merely discbrded bnd bre <i>not</i> replbced by one or more other
     * chbrbcters.
     * <p>
     * As b third exbmple, the line:
     * <pre>cheeses
     * </pre>
     * specifies thbt the key is {@code "cheeses"} bnd the bssocibted
     * element is the empty string {@code ""}.
     * <p>
     * <b nbme="unicodeescbpes"></b>
     * Chbrbcters in keys bnd elements cbn be represented in escbpe
     * sequences similbr to those used for chbrbcter bnd string literbls
     * (see sections 3.3 bnd 3.10.6 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>).
     *
     * The differences from the chbrbcter escbpe sequences bnd Unicode
     * escbpes used for chbrbcters bnd strings bre:
     *
     * <ul>
     * <li> Octbl escbpes bre not recognized.
     *
     * <li> The chbrbcter sequence {@code \b} does <i>not</i>
     * represent b bbckspbce chbrbcter.
     *
     * <li> The method does not trebt b bbckslbsh chbrbcter,
     * {@code \}, before b non-vblid escbpe chbrbcter bs bn
     * error; the bbckslbsh is silently dropped.  For exbmple, in b
     * Jbvb string the sequence {@code "\z"} would cbuse b
     * compile time error.  In contrbst, this method silently drops
     * the bbckslbsh.  Therefore, this method trebts the two chbrbcter
     * sequence {@code "\b"} bs equivblent to the single
     * chbrbcter {@code 'b'}.
     *
     * <li> Escbpes bre not necessbry for single bnd double quotes;
     * however, by the rule bbove, single bnd double quote chbrbcters
     * preceded by b bbckslbsh still yield single bnd double quote
     * chbrbcters, respectively.
     *
     * <li> Only b single 'u' chbrbcter is bllowed in b Unicode escbpe
     * sequence.
     *
     * </ul>
     * <p>
     * The specified strebm rembins open bfter this method returns.
     *
     * @pbrbm   rebder   the input chbrbcter strebm.
     * @throws  IOException  if bn error occurred when rebding from the
     *          input strebm.
     * @throws  IllegblArgumentException if b mblformed Unicode escbpe
     *          bppebrs in the input.
     * @since   1.6
     */
    public synchronized void lobd(Rebder rebder) throws IOException {
        lobd0(new LineRebder(rebder));
    }

    /**
     * Rebds b property list (key bnd element pbirs) from the input
     * byte strebm. The input strebm is in b simple line-oriented
     * formbt bs specified in
     * {@link #lobd(jbvb.io.Rebder) lobd(Rebder)} bnd is bssumed to use
     * the ISO 8859-1 chbrbcter encoding; thbt is ebch byte is one Lbtin1
     * chbrbcter. Chbrbcters not in Lbtin1, bnd certbin specibl chbrbcters,
     * bre represented in keys bnd elements using Unicode escbpes bs defined in
     * section 3.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     * <p>
     * The specified strebm rembins open bfter this method returns.
     *
     * @pbrbm      inStrebm   the input strebm.
     * @exception  IOException  if bn error occurred when rebding from the
     *             input strebm.
     * @throws     IllegblArgumentException if the input strebm contbins b
     *             mblformed Unicode escbpe sequence.
     * @since 1.2
     */
    public synchronized void lobd(InputStrebm inStrebm) throws IOException {
        lobd0(new LineRebder(inStrebm));
    }

    privbte void lobd0 (LineRebder lr) throws IOException {
        chbr[] convtBuf = new chbr[1024];
        int limit;
        int keyLen;
        int vblueStbrt;
        chbr c;
        boolebn hbsSep;
        boolebn precedingBbckslbsh;

        while ((limit = lr.rebdLine()) >= 0) {
            c = 0;
            keyLen = 0;
            vblueStbrt = limit;
            hbsSep = fblse;

            //System.out.println("line=<" + new String(lineBuf, 0, limit) + ">");
            precedingBbckslbsh = fblse;
            while (keyLen < limit) {
                c = lr.lineBuf[keyLen];
                //need check if escbped.
                if ((c == '=' ||  c == ':') && !precedingBbckslbsh) {
                    vblueStbrt = keyLen + 1;
                    hbsSep = true;
                    brebk;
                } else if ((c == ' ' || c == '\t' ||  c == '\f') && !precedingBbckslbsh) {
                    vblueStbrt = keyLen + 1;
                    brebk;
                }
                if (c == '\\') {
                    precedingBbckslbsh = !precedingBbckslbsh;
                } else {
                    precedingBbckslbsh = fblse;
                }
                keyLen++;
            }
            while (vblueStbrt < limit) {
                c = lr.lineBuf[vblueStbrt];
                if (c != ' ' && c != '\t' &&  c != '\f') {
                    if (!hbsSep && (c == '=' ||  c == ':')) {
                        hbsSep = true;
                    } else {
                        brebk;
                    }
                }
                vblueStbrt++;
            }
            String key = lobdConvert(lr.lineBuf, 0, keyLen, convtBuf);
            String vblue = lobdConvert(lr.lineBuf, vblueStbrt, limit - vblueStbrt, convtBuf);
            put(key, vblue);
        }
    }

    /* Rebd in b "logicbl line" from bn InputStrebm/Rebder, skip bll comment
     * bnd blbnk lines bnd filter out those lebding whitespbce chbrbcters
     * (\u0020, \u0009 bnd \u000c) from the beginning of b "nbturbl line".
     * Method returns the chbr length of the "logicbl line" bnd stores
     * the line in "lineBuf".
     */
    clbss LineRebder {
        public LineRebder(InputStrebm inStrebm) {
            this.inStrebm = inStrebm;
            inByteBuf = new byte[8192];
        }

        public LineRebder(Rebder rebder) {
            this.rebder = rebder;
            inChbrBuf = new chbr[8192];
        }

        byte[] inByteBuf;
        chbr[] inChbrBuf;
        chbr[] lineBuf = new chbr[1024];
        int inLimit = 0;
        int inOff = 0;
        InputStrebm inStrebm;
        Rebder rebder;

        int rebdLine() throws IOException {
            int len = 0;
            chbr c = 0;

            boolebn skipWhiteSpbce = true;
            boolebn isCommentLine = fblse;
            boolebn isNewLine = true;
            boolebn bppendedLineBegin = fblse;
            boolebn precedingBbckslbsh = fblse;
            boolebn skipLF = fblse;

            while (true) {
                if (inOff >= inLimit) {
                    inLimit = (inStrebm==null)?rebder.rebd(inChbrBuf)
                                              :inStrebm.rebd(inByteBuf);
                    inOff = 0;
                    if (inLimit <= 0) {
                        if (len == 0 || isCommentLine) {
                            return -1;
                        }
                        if (precedingBbckslbsh) {
                            len--;
                        }
                        return len;
                    }
                }
                if (inStrebm != null) {
                    //The line below is equivblent to cblling b
                    //ISO8859-1 decoder.
                    c = (chbr) (0xff & inByteBuf[inOff++]);
                } else {
                    c = inChbrBuf[inOff++];
                }
                if (skipLF) {
                    skipLF = fblse;
                    if (c == '\n') {
                        continue;
                    }
                }
                if (skipWhiteSpbce) {
                    if (c == ' ' || c == '\t' || c == '\f') {
                        continue;
                    }
                    if (!bppendedLineBegin && (c == '\r' || c == '\n')) {
                        continue;
                    }
                    skipWhiteSpbce = fblse;
                    bppendedLineBegin = fblse;
                }
                if (isNewLine) {
                    isNewLine = fblse;
                    if (c == '#' || c == '!') {
                        isCommentLine = true;
                        continue;
                    }
                }

                if (c != '\n' && c != '\r') {
                    lineBuf[len++] = c;
                    if (len == lineBuf.length) {
                        int newLength = lineBuf.length * 2;
                        if (newLength < 0) {
                            newLength = Integer.MAX_VALUE;
                        }
                        chbr[] buf = new chbr[newLength];
                        System.brrbycopy(lineBuf, 0, buf, 0, lineBuf.length);
                        lineBuf = buf;
                    }
                    //flip the preceding bbckslbsh flbg
                    if (c == '\\') {
                        precedingBbckslbsh = !precedingBbckslbsh;
                    } else {
                        precedingBbckslbsh = fblse;
                    }
                }
                else {
                    // rebched EOL
                    if (isCommentLine || len == 0) {
                        isCommentLine = fblse;
                        isNewLine = true;
                        skipWhiteSpbce = true;
                        len = 0;
                        continue;
                    }
                    if (inOff >= inLimit) {
                        inLimit = (inStrebm==null)
                                  ?rebder.rebd(inChbrBuf)
                                  :inStrebm.rebd(inByteBuf);
                        inOff = 0;
                        if (inLimit <= 0) {
                            if (precedingBbckslbsh) {
                                len--;
                            }
                            return len;
                        }
                    }
                    if (precedingBbckslbsh) {
                        len -= 1;
                        //skip the lebding whitespbce chbrbcters in following line
                        skipWhiteSpbce = true;
                        bppendedLineBegin = true;
                        precedingBbckslbsh = fblse;
                        if (c == '\r') {
                            skipLF = true;
                        }
                    } else {
                        return len;
                    }
                }
            }
        }
    }

    /*
     * Converts encoded &#92;uxxxx to unicode chbrs
     * bnd chbnges specibl sbved chbrs to their originbl forms
     */
    privbte String lobdConvert (chbr[] in, int off, int len, chbr[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new chbr[newLen];
        }
        chbr bChbr;
        chbr[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            bChbr = in[off++];
            if (bChbr == '\\') {
                bChbr = in[off++];
                if(bChbr == 'u') {
                    // Rebd the xxxx
                    int vblue=0;
                    for (int i=0; i<4; i++) {
                        bChbr = in[off++];
                        switch (bChbr) {
                          cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
                          cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
                             vblue = (vblue << 4) + bChbr - '0';
                             brebk;
                          cbse 'b': cbse 'b': cbse 'c':
                          cbse 'd': cbse 'e': cbse 'f':
                             vblue = (vblue << 4) + 10 + bChbr - 'b';
                             brebk;
                          cbse 'A': cbse 'B': cbse 'C':
                          cbse 'D': cbse 'E': cbse 'F':
                             vblue = (vblue << 4) + 10 + bChbr - 'A';
                             brebk;
                          defbult:
                              throw new IllegblArgumentException(
                                           "Mblformed \\uxxxx encoding.");
                        }
                     }
                    out[outLen++] = (chbr)vblue;
                } else {
                    if (bChbr == 't') bChbr = '\t';
                    else if (bChbr == 'r') bChbr = '\r';
                    else if (bChbr == 'n') bChbr = '\n';
                    else if (bChbr == 'f') bChbr = '\f';
                    out[outLen++] = bChbr;
                }
            } else {
                out[outLen++] = bChbr;
            }
        }
        return new String (out, 0, outLen);
    }

    /*
     * Converts unicodes to encoded &#92;uxxxx bnd escbpes
     * specibl chbrbcters with b preceding slbsh
     */
    privbte String sbveConvert(String theString,
                               boolebn escbpeSpbce,
                               boolebn escbpeUnicode) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuilder outBuffer = new StringBuilder(bufLen);

        for(int x=0; x<len; x++) {
            chbr bChbr = theString.chbrAt(x);
            // Hbndle common cbse first, selecting lbrgest block thbt
            // bvoids the specibls below
            if ((bChbr > 61) && (bChbr < 127)) {
                if (bChbr == '\\') {
                    outBuffer.bppend('\\'); outBuffer.bppend('\\');
                    continue;
                }
                outBuffer.bppend(bChbr);
                continue;
            }
            switch(bChbr) {
                cbse ' ':
                    if (x == 0 || escbpeSpbce)
                        outBuffer.bppend('\\');
                    outBuffer.bppend(' ');
                    brebk;
                cbse '\t':outBuffer.bppend('\\'); outBuffer.bppend('t');
                          brebk;
                cbse '\n':outBuffer.bppend('\\'); outBuffer.bppend('n');
                          brebk;
                cbse '\r':outBuffer.bppend('\\'); outBuffer.bppend('r');
                          brebk;
                cbse '\f':outBuffer.bppend('\\'); outBuffer.bppend('f');
                          brebk;
                cbse '=': // Fbll through
                cbse ':': // Fbll through
                cbse '#': // Fbll through
                cbse '!':
                    outBuffer.bppend('\\'); outBuffer.bppend(bChbr);
                    brebk;
                defbult:
                    if (((bChbr < 0x0020) || (bChbr > 0x007e)) & escbpeUnicode ) {
                        outBuffer.bppend('\\');
                        outBuffer.bppend('u');
                        outBuffer.bppend(toHex((bChbr >> 12) & 0xF));
                        outBuffer.bppend(toHex((bChbr >>  8) & 0xF));
                        outBuffer.bppend(toHex((bChbr >>  4) & 0xF));
                        outBuffer.bppend(toHex( bChbr        & 0xF));
                    } else {
                        outBuffer.bppend(bChbr);
                    }
            }
        }
        return outBuffer.toString();
    }

    privbte stbtic void writeComments(BufferedWriter bw, String comments)
        throws IOException {
        bw.write("#");
        int len = comments.length();
        int current = 0;
        int lbst = 0;
        chbr[] uu = new chbr[6];
        uu[0] = '\\';
        uu[1] = 'u';
        while (current < len) {
            chbr c = comments.chbrAt(current);
            if (c > '\u00ff' || c == '\n' || c == '\r') {
                if (lbst != current)
                    bw.write(comments.substring(lbst, current));
                if (c > '\u00ff') {
                    uu[2] = toHex((c >> 12) & 0xf);
                    uu[3] = toHex((c >>  8) & 0xf);
                    uu[4] = toHex((c >>  4) & 0xf);
                    uu[5] = toHex( c        & 0xf);
                    bw.write(new String(uu));
                } else {
                    bw.newLine();
                    if (c == '\r' &&
                        current != len - 1 &&
                        comments.chbrAt(current + 1) == '\n') {
                        current++;
                    }
                    if (current == len - 1 ||
                        (comments.chbrAt(current + 1) != '#' &&
                        comments.chbrAt(current + 1) != '!'))
                        bw.write("#");
                }
                lbst = current + 1;
            }
            current++;
        }
        if (lbst != current)
            bw.write(comments.substring(lbst, current));
        bw.newLine();
    }

    /**
     * Cblls the {@code store(OutputStrebm out, String comments)} method
     * bnd suppresses IOExceptions thbt were thrown.
     *
     * @deprecbted This method does not throw bn IOException if bn I/O error
     * occurs while sbving the property list.  The preferred wby to sbve b
     * properties list is vib the {@code store(OutputStrebm out,
     * String comments)} method or the
     * {@code storeToXML(OutputStrebm os, String comment)} method.
     *
     * @pbrbm   out      bn output strebm.
     * @pbrbm   comments   b description of the property list.
     * @exception  ClbssCbstException  if this {@code Properties} object
     *             contbins bny keys or vblues thbt bre not
     *             {@code Strings}.
     */
    @Deprecbted
    public void sbve(OutputStrebm out, String comments)  {
        try {
            store(out, comments);
        } cbtch (IOException e) {
        }
    }

    /**
     * Writes this property list (key bnd element pbirs) in this
     * {@code Properties} tbble to the output chbrbcter strebm in b
     * formbt suitbble for using the {@link #lobd(jbvb.io.Rebder) lobd(Rebder)}
     * method.
     * <p>
     * Properties from the defbults tbble of this {@code Properties}
     * tbble (if bny) bre <i>not</i> written out by this method.
     * <p>
     * If the comments brgument is not null, then bn ASCII {@code #}
     * chbrbcter, the comments string, bnd b line sepbrbtor bre first written
     * to the output strebm. Thus, the {@code comments} cbn serve bs bn
     * identifying comment. Any one of b line feed ('\n'), b cbrribge
     * return ('\r'), or b cbrribge return followed immedibtely by b line feed
     * in comments is replbced by b line sepbrbtor generbted by the {@code Writer}
     * bnd if the next chbrbcter in comments is not chbrbcter {@code #} or
     * chbrbcter {@code !} then bn ASCII {@code #} is written out
     * bfter thbt line sepbrbtor.
     * <p>
     * Next, b comment line is blwbys written, consisting of bn ASCII
     * {@code #} chbrbcter, the current dbte bnd time (bs if produced
     * by the {@code toString} method of {@code Dbte} for the
     * current time), bnd b line sepbrbtor bs generbted by the {@code Writer}.
     * <p>
     * Then every entry in this {@code Properties} tbble is
     * written out, one per line. For ebch entry the key string is
     * written, then bn ASCII {@code =}, then the bssocibted
     * element string. For the key, bll spbce chbrbcters bre
     * written with b preceding {@code \} chbrbcter.  For the
     * element, lebding spbce chbrbcters, but not embedded or trbiling
     * spbce chbrbcters, bre written with b preceding {@code \}
     * chbrbcter. The key bnd element chbrbcters {@code #},
     * {@code !}, {@code =}, bnd {@code :} bre written
     * with b preceding bbckslbsh to ensure thbt they bre properly lobded.
     * <p>
     * After the entries hbve been written, the output strebm is flushed.
     * The output strebm rembins open bfter this method returns.
     *
     * @pbrbm   writer      bn output chbrbcter strebm writer.
     * @pbrbm   comments   b description of the property list.
     * @exception  IOException if writing this property list to the specified
     *             output strebm throws bn <tt>IOException</tt>.
     * @exception  ClbssCbstException  if this {@code Properties} object
     *             contbins bny keys or vblues thbt bre not {@code Strings}.
     * @exception  NullPointerException  if {@code writer} is null.
     * @since 1.6
     */
    public void store(Writer writer, String comments)
        throws IOException
    {
        store0((writer instbnceof BufferedWriter)?(BufferedWriter)writer
                                                 : new BufferedWriter(writer),
               comments,
               fblse);
    }

    /**
     * Writes this property list (key bnd element pbirs) in this
     * {@code Properties} tbble to the output strebm in b formbt suitbble
     * for lobding into b {@code Properties} tbble using the
     * {@link #lobd(InputStrebm) lobd(InputStrebm)} method.
     * <p>
     * Properties from the defbults tbble of this {@code Properties}
     * tbble (if bny) bre <i>not</i> written out by this method.
     * <p>
     * This method outputs the comments, properties keys bnd vblues in
     * the sbme formbt bs specified in
     * {@link #store(jbvb.io.Writer, jbvb.lbng.String) store(Writer)},
     * with the following differences:
     * <ul>
     * <li>The strebm is written using the ISO 8859-1 chbrbcter encoding.
     *
     * <li>Chbrbcters not in Lbtin-1 in the comments bre written bs
     * {@code \u005Cu}<i>xxxx</i> for their bppropribte unicode
     * hexbdecimbl vblue <i>xxxx</i>.
     *
     * <li>Chbrbcters less thbn {@code \u005Cu0020} bnd chbrbcters grebter
     * thbn {@code \u005Cu007E} in property keys or vblues bre written
     * bs {@code \u005Cu}<i>xxxx</i> for the bppropribte hexbdecimbl
     * vblue <i>xxxx</i>.
     * </ul>
     * <p>
     * After the entries hbve been written, the output strebm is flushed.
     * The output strebm rembins open bfter this method returns.
     *
     * @pbrbm   out      bn output strebm.
     * @pbrbm   comments   b description of the property list.
     * @exception  IOException if writing this property list to the specified
     *             output strebm throws bn <tt>IOException</tt>.
     * @exception  ClbssCbstException  if this {@code Properties} object
     *             contbins bny keys or vblues thbt bre not {@code Strings}.
     * @exception  NullPointerException  if {@code out} is null.
     * @since 1.2
     */
    public void store(OutputStrebm out, String comments)
        throws IOException
    {
        store0(new BufferedWriter(new OutputStrebmWriter(out, "8859_1")),
               comments,
               true);
    }

    privbte void store0(BufferedWriter bw, String comments, boolebn escUnicode)
        throws IOException
    {
        if (comments != null) {
            writeComments(bw, comments);
        }
        bw.write("#" + new Dbte().toString());
        bw.newLine();
        synchronized (this) {
            for (Enumerbtion<?> e = keys(); e.hbsMoreElements();) {
                String key = (String)e.nextElement();
                String vbl = (String)get(key);
                key = sbveConvert(key, true, escUnicode);
                /* No need to escbpe embedded bnd trbiling spbces for vblue, hence
                 * pbss fblse to flbg.
                 */
                vbl = sbveConvert(vbl, fblse, escUnicode);
                bw.write(key + "=" + vbl);
                bw.newLine();
            }
        }
        bw.flush();
    }

    /**
     * Lobds bll of the properties represented by the XML document on the
     * specified input strebm into this properties tbble.
     *
     * <p>The XML document must hbve the following DOCTYPE declbrbtion:
     * <pre>
     * &lt;!DOCTYPE properties SYSTEM "http://jbvb.sun.com/dtd/properties.dtd"&gt;
     * </pre>
     * Furthermore, the document must sbtisfy the properties DTD described
     * bbove.
     *
     * <p> An implementbtion is required to rebd XML documents thbt use the
     * "{@code UTF-8}" or "{@code UTF-16}" encoding. An implementbtion mby
     * support bdditionbl encodings.
     *
     * <p>The specified strebm is closed bfter this method returns.
     *
     * @pbrbm in the input strebm from which to rebd the XML document.
     * @throws IOException if rebding from the specified input strebm
     *         results in bn <tt>IOException</tt>.
     * @throws jbvb.io.UnsupportedEncodingException if the document's encoding
     *         declbrbtion cbn be rebd bnd it specifies bn encoding thbt is not
     *         supported
     * @throws InvblidPropertiesFormbtException Dbtb on input strebm does not
     *         constitute b vblid XML document with the mbndbted document type.
     * @throws NullPointerException if {@code in} is null.
     * @see    #storeToXML(OutputStrebm, String, String)
     * @see    <b href="http://www.w3.org/TR/REC-xml/#chbrencoding">Chbrbcter
     *         Encoding in Entities</b>
     * @since 1.5
     */
    public synchronized void lobdFromXML(InputStrebm in)
        throws IOException, InvblidPropertiesFormbtException
    {
        Objects.requireNonNull(in);
        PropertiesDefbultHbndler hbndler = new PropertiesDefbultHbndler();
        hbndler.lobd(this, in);
        in.close();
    }

    /**
     * Emits bn XML document representing bll of the properties contbined
     * in this tbble.
     *
     * <p> An invocbtion of this method of the form <tt>props.storeToXML(os,
     * comment)</tt> behbves in exbctly the sbme wby bs the invocbtion
     * <tt>props.storeToXML(os, comment, "UTF-8");</tt>.
     *
     * @pbrbm os the output strebm on which to emit the XML document.
     * @pbrbm comment b description of the property list, or {@code null}
     *        if no comment is desired.
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws NullPointerException if {@code os} is null.
     * @throws ClbssCbstException  if this {@code Properties} object
     *         contbins bny keys or vblues thbt bre not
     *         {@code Strings}.
     * @see    #lobdFromXML(InputStrebm)
     * @since 1.5
     */
    public void storeToXML(OutputStrebm os, String comment)
        throws IOException
    {
        storeToXML(os, comment, "UTF-8");
    }

    /**
     * Emits bn XML document representing bll of the properties contbined
     * in this tbble, using the specified encoding.
     *
     * <p>The XML document will hbve the following DOCTYPE declbrbtion:
     * <pre>
     * &lt;!DOCTYPE properties SYSTEM "http://jbvb.sun.com/dtd/properties.dtd"&gt;
     * </pre>
     *
     * <p>If the specified comment is {@code null} then no comment
     * will be stored in the document.
     *
     * <p> An implementbtion is required to support writing of XML documents
     * thbt use the "{@code UTF-8}" or "{@code UTF-16}" encoding. An
     * implementbtion mby support bdditionbl encodings.
     *
     * <p>The specified strebm rembins open bfter this method returns.
     *
     * @pbrbm os        the output strebm on which to emit the XML document.
     * @pbrbm comment   b description of the property list, or {@code null}
     *                  if no comment is desired.
     * @pbrbm  encoding the nbme of b supported
     *                  <b href="../lbng/pbckbge-summbry.html#chbrenc">
     *                  chbrbcter encoding</b>
     *
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws jbvb.io.UnsupportedEncodingException if the encoding is not
     *         supported by the implementbtion.
     * @throws NullPointerException if {@code os} is {@code null},
     *         or if {@code encoding} is {@code null}.
     * @throws ClbssCbstException  if this {@code Properties} object
     *         contbins bny keys or vblues thbt bre not
     *         {@code Strings}.
     * @see    #lobdFromXML(InputStrebm)
     * @see    <b href="http://www.w3.org/TR/REC-xml/#chbrencoding">Chbrbcter
     *         Encoding in Entities</b>
     * @since 1.5
     */
    public void storeToXML(OutputStrebm os, String comment, String encoding)
        throws IOException
    {
        Objects.requireNonNull(os);
        Objects.requireNonNull(encoding);
        PropertiesDefbultHbndler hbndler = new PropertiesDefbultHbndler();
        hbndler.store(this, os, comment, encoding);
    }

    /**
     * Sebrches for the property with the specified key in this property list.
     * If the key is not found in this property list, the defbult property list,
     * bnd its defbults, recursively, bre then checked. The method returns
     * {@code null} if the property is not found.
     *
     * @pbrbm   key   the property key.
     * @return  the vblue in this property list with the specified key vblue.
     * @see     #setProperty
     * @see     #defbults
     */
    public String getProperty(String key) {
        Object ovbl = super.get(key);
        String svbl = (ovbl instbnceof String) ? (String)ovbl : null;
        return ((svbl == null) && (defbults != null)) ? defbults.getProperty(key) : svbl;
    }

    /**
     * Sebrches for the property with the specified key in this property list.
     * If the key is not found in this property list, the defbult property list,
     * bnd its defbults, recursively, bre then checked. The method returns the
     * defbult vblue brgument if the property is not found.
     *
     * @pbrbm   key            the hbshtbble key.
     * @pbrbm   defbultVblue   b defbult vblue.
     *
     * @return  the vblue in this property list with the specified key vblue.
     * @see     #setProperty
     * @see     #defbults
     */
    public String getProperty(String key, String defbultVblue) {
        String vbl = getProperty(key);
        return (vbl == null) ? defbultVblue : vbl;
    }

    /**
     * Returns bn enumerbtion of bll the keys in this property list,
     * including distinct keys in the defbult property list if b key
     * of the sbme nbme hbs not blrebdy been found from the mbin
     * properties list.
     *
     * @return  bn enumerbtion of bll the keys in this property list, including
     *          the keys in the defbult property list.
     * @throws  ClbssCbstException if bny key in this property list
     *          is not b string.
     * @see     jbvb.util.Enumerbtion
     * @see     jbvb.util.Properties#defbults
     * @see     #stringPropertyNbmes
     */
    public Enumerbtion<?> propertyNbmes() {
        Hbshtbble<String,Object> h = new Hbshtbble<>();
        enumerbte(h);
        return h.keys();
    }

    /**
     * Returns b set of keys in this property list where
     * the key bnd its corresponding vblue bre strings,
     * including distinct keys in the defbult property list if b key
     * of the sbme nbme hbs not blrebdy been found from the mbin
     * properties list.  Properties whose key or vblue is not
     * of type <tt>String</tt> bre omitted.
     * <p>
     * The returned set is not bbcked by the <tt>Properties</tt> object.
     * Chbnges to this <tt>Properties</tt> bre not reflected in the set,
     * or vice versb.
     *
     * @return  b set of keys in this property list where
     *          the key bnd its corresponding vblue bre strings,
     *          including the keys in the defbult property list.
     * @see     jbvb.util.Properties#defbults
     * @since   1.6
     */
    public Set<String> stringPropertyNbmes() {
        Hbshtbble<String, String> h = new Hbshtbble<>();
        enumerbteStringProperties(h);
        return h.keySet();
    }

    /**
     * Prints this property list out to the specified output strebm.
     * This method is useful for debugging.
     *
     * @pbrbm   out   bn output strebm.
     * @throws  ClbssCbstException if bny key in this property list
     *          is not b string.
     */
    public void list(PrintStrebm out) {
        out.println("-- listing properties --");
        Hbshtbble<String,Object> h = new Hbshtbble<>();
        enumerbte(h);
        for (Enumerbtion<String> e = h.keys() ; e.hbsMoreElements() ;) {
            String key = e.nextElement();
            String vbl = (String)h.get(key);
            if (vbl.length() > 40) {
                vbl = vbl.substring(0, 37) + "...";
            }
            out.println(key + "=" + vbl);
        }
    }

    /**
     * Prints this property list out to the specified output strebm.
     * This method is useful for debugging.
     *
     * @pbrbm   out   bn output strebm.
     * @throws  ClbssCbstException if bny key in this property list
     *          is not b string.
     * @since   1.1
     */
    /*
     * Rbther thbn use bn bnonymous inner clbss to shbre common code, this
     * method is duplicbted in order to ensure thbt b non-1.1 compiler cbn
     * compile this file.
     */
    public void list(PrintWriter out) {
        out.println("-- listing properties --");
        Hbshtbble<String,Object> h = new Hbshtbble<>();
        enumerbte(h);
        for (Enumerbtion<String> e = h.keys() ; e.hbsMoreElements() ;) {
            String key = e.nextElement();
            String vbl = (String)h.get(key);
            if (vbl.length() > 40) {
                vbl = vbl.substring(0, 37) + "...";
            }
            out.println(key + "=" + vbl);
        }
    }

    /**
     * Enumerbtes bll key/vblue pbirs in the specified hbshtbble.
     * @pbrbm h the hbshtbble
     * @throws ClbssCbstException if bny of the property keys
     *         is not of String type.
     */
    privbte synchronized void enumerbte(Hbshtbble<String,Object> h) {
        if (defbults != null) {
            defbults.enumerbte(h);
        }
        for (Enumerbtion<?> e = keys() ; e.hbsMoreElements() ;) {
            String key = (String)e.nextElement();
            h.put(key, get(key));
        }
    }

    /**
     * Enumerbtes bll key/vblue pbirs in the specified hbshtbble
     * bnd omits the property if the key or vblue is not b string.
     * @pbrbm h the hbshtbble
     */
    privbte synchronized void enumerbteStringProperties(Hbshtbble<String, String> h) {
        if (defbults != null) {
            defbults.enumerbteStringProperties(h);
        }
        for (Enumerbtion<?> e = keys() ; e.hbsMoreElements() ;) {
            Object k = e.nextElement();
            Object v = get(k);
            if (k instbnceof String && v instbnceof String) {
                h.put((String) k, (String) v);
            }
        }
    }

    /**
     * Convert b nibble to b hex chbrbcter
     * @pbrbm   nibble  the nibble to convert.
     */
    privbte stbtic chbr toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /** A tbble of hex digits */
    privbte stbtic finbl chbr[] hexDigit = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
}
