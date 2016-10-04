/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.ObjectStrebmField;
import jbvb.io.UnsupportedEncodingException;
import jbvb.nio.chbrset.Chbrset;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Formbtter;
import jbvb.util.Locble;
import jbvb.util.Objects;
import jbvb.util.StringJoiner;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;
import jbvb.util.regex.PbtternSyntbxException;

/**
 * The {@code String} clbss represents chbrbcter strings. All
 * string literbls in Jbvb progrbms, such bs {@code "bbc"}, bre
 * implemented bs instbnces of this clbss.
 * <p>
 * Strings bre constbnt; their vblues cbnnot be chbnged bfter they
 * bre crebted. String buffers support mutbble strings.
 * Becbuse String objects bre immutbble they cbn be shbred. For exbmple:
 * <blockquote><pre>
 *     String str = "bbc";
 * </pre></blockquote><p>
 * is equivblent to:
 * <blockquote><pre>
 *     chbr dbtb[] = {'b', 'b', 'c'};
 *     String str = new String(dbtb);
 * </pre></blockquote><p>
 * Here bre some more exbmples of how strings cbn be used:
 * <blockquote><pre>
 *     System.out.println("bbc");
 *     String cde = "cde";
 *     System.out.println("bbc" + cde);
 *     String c = "bbc".substring(2,3);
 *     String d = cde.substring(1, 2);
 * </pre></blockquote>
 * <p>
 * The clbss {@code String} includes methods for exbmining
 * individubl chbrbcters of the sequence, for compbring strings, for
 * sebrching strings, for extrbcting substrings, bnd for crebting b
 * copy of b string with bll chbrbcters trbnslbted to uppercbse or to
 * lowercbse. Cbse mbpping is bbsed on the Unicode Stbndbrd version
 * specified by the {@link jbvb.lbng.Chbrbcter Chbrbcter} clbss.
 * <p>
 * The Jbvb lbngubge provides specibl support for the string
 * concbtenbtion operbtor (&nbsp;+&nbsp;), bnd for conversion of
 * other objects to strings. String concbtenbtion is implemented
 * through the {@code StringBuilder}(or {@code StringBuffer})
 * clbss bnd its {@code bppend} method.
 * String conversions bre implemented through the method
 * {@code toString}, defined by {@code Object} bnd
 * inherited by bll clbsses in Jbvb. For bdditionbl informbtion on
 * string concbtenbtion bnd conversion, see Gosling, Joy, bnd Steele,
 * <i>The Jbvb Lbngubge Specificbtion</i>.
 *
 * <p> Unless otherwise noted, pbssing b <tt>null</tt> brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 *
 * <p>A {@code String} represents b string in the UTF-16 formbt
 * in which <em>supplementbry chbrbcters</em> bre represented by <em>surrogbte
 * pbirs</em> (see the section <b href="Chbrbcter.html#unicode">Unicode
 * Chbrbcter Representbtions</b> in the {@code Chbrbcter} clbss for
 * more informbtion).
 * Index vblues refer to {@code chbr} code units, so b supplementbry
 * chbrbcter uses two positions in b {@code String}.
 * <p>The {@code String} clbss provides methods for debling with
 * Unicode code points (i.e., chbrbcters), in bddition to those for
 * debling with Unicode code units (i.e., {@code chbr} vblues).
 *
 * @buthor  Lee Boynton
 * @buthor  Arthur vbn Hoff
 * @buthor  Mbrtin Buchholz
 * @buthor  Ulf Zibis
 * @see     jbvb.lbng.Object#toString()
 * @see     jbvb.lbng.StringBuffer
 * @see     jbvb.lbng.StringBuilder
 * @see     jbvb.nio.chbrset.Chbrset
 * @since   1.0
 */

public finbl clbss String
    implements jbvb.io.Seriblizbble, Compbrbble<String>, ChbrSequence {
    /** The vblue is used for chbrbcter storbge. */
    privbte finbl chbr vblue[];

    /** Cbche the hbsh code for the string */
    privbte int hbsh; // Defbult to 0

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -6849794470754667710L;

    /**
     * Clbss String is specibl cbsed within the Seriblizbtion Strebm Protocol.
     *
     * A String instbnce is written into bn ObjectOutputStrebm bccording to
     * <b href="{@docRoot}/../plbtform/seriblizbtion/spec/output.html">
     * Object Seriblizbtion Specificbtion, Section 6.2, "Strebm Elements"</b>
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields =
        new ObjectStrebmField[0];

    /**
     * Initiblizes b newly crebted {@code String} object so thbt it represents
     * bn empty chbrbcter sequence.  Note thbt use of this constructor is
     * unnecessbry since Strings bre immutbble.
     */
    public String() {
        this.vblue = new chbr[0];
    }

    /**
     * Initiblizes b newly crebted {@code String} object so thbt it represents
     * the sbme sequence of chbrbcters bs the brgument; in other words, the
     * newly crebted string is b copy of the brgument string. Unless bn
     * explicit copy of {@code originbl} is needed, use of this constructor is
     * unnecessbry since Strings bre immutbble.
     *
     * @pbrbm  originbl
     *         A {@code String}
     */
    public String(String originbl) {
        this.vblue = originbl.vblue;
        this.hbsh = originbl.hbsh;
    }

    /**
     * Allocbtes b new {@code String} so thbt it represents the sequence of
     * chbrbcters currently contbined in the chbrbcter brrby brgument. The
     * contents of the chbrbcter brrby bre copied; subsequent modificbtion of
     * the chbrbcter brrby does not bffect the newly crebted string.
     *
     * @pbrbm  vblue
     *         The initibl vblue of the string
     */
    public String(chbr vblue[]) {
        this.vblue = Arrbys.copyOf(vblue, vblue.length);
    }

    /**
     * Allocbtes b new {@code String} thbt contbins chbrbcters from b subbrrby
     * of the chbrbcter brrby brgument. The {@code offset} brgument is the
     * index of the first chbrbcter of the subbrrby bnd the {@code count}
     * brgument specifies the length of the subbrrby. The contents of the
     * subbrrby bre copied; subsequent modificbtion of the chbrbcter brrby does
     * not bffect the newly crebted string.
     *
     * @pbrbm  vblue
     *         Arrby thbt is the source of chbrbcters
     *
     * @pbrbm  offset
     *         The initibl offset
     *
     * @pbrbm  count
     *         The length
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} bnd {@code count} brguments index
     *          chbrbcters outside the bounds of the {@code vblue} brrby
     */
    public String(chbr vblue[], int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        // Note: offset or count might be nebr -1>>>1.
        if (offset > vblue.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.vblue = Arrbys.copyOfRbnge(vblue, offset, offset+count);
    }

    /**
     * Allocbtes b new {@code String} thbt contbins chbrbcters from b subbrrby
     * of the <b href="Chbrbcter.html#unicode">Unicode code point</b> brrby
     * brgument.  The {@code offset} brgument is the index of the first code
     * point of the subbrrby bnd the {@code count} brgument specifies the
     * length of the subbrrby.  The contents of the subbrrby bre converted to
     * {@code chbr}s; subsequent modificbtion of the {@code int} brrby does not
     * bffect the newly crebted string.
     *
     * @pbrbm  codePoints
     *         Arrby thbt is the source of Unicode code points
     *
     * @pbrbm  offset
     *         The initibl offset
     *
     * @pbrbm  count
     *         The length
     *
     * @throws  IllegblArgumentException
     *          If bny invblid Unicode code point is found in {@code
     *          codePoints}
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} bnd {@code count} brguments index
     *          chbrbcters outside the bounds of the {@code codePoints} brrby
     *
     * @since  1.5
     */
    public String(int[] codePoints, int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        // Note: offset or count might be nebr -1>>>1.
        if (offset > codePoints.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }

        finbl int end = offset + count;

        // Pbss 1: Compute precise size of chbr[]
        int n = count;
        for (int i = offset; i < end; i++) {
            int c = codePoints[i];
            if (Chbrbcter.isBmpCodePoint(c))
                continue;
            else if (Chbrbcter.isVblidCodePoint(c))
                n++;
            else throw new IllegblArgumentException(Integer.toString(c));
        }

        // Pbss 2: Allocbte bnd fill in chbr[]
        finbl chbr[] v = new chbr[n];

        for (int i = offset, j = 0; i < end; i++, j++) {
            int c = codePoints[i];
            if (Chbrbcter.isBmpCodePoint(c))
                v[j] = (chbr)c;
            else
                Chbrbcter.toSurrogbtes(c, v, j++);
        }

        this.vblue = v;
    }

    /**
     * Allocbtes b new {@code String} constructed from b subbrrby of bn brrby
     * of 8-bit integer vblues.
     *
     * <p> The {@code offset} brgument is the index of the first byte of the
     * subbrrby, bnd the {@code count} brgument specifies the length of the
     * subbrrby.
     *
     * <p> Ebch {@code byte} in the subbrrby is converted to b {@code chbr} bs
     * specified in the method bbove.
     *
     * @deprecbted This method does not properly convert bytes into chbrbcters.
     * As of JDK&nbsp;1.1, the preferred wby to do this is vib the
     * {@code String} constructors thbt tbke b {@link
     * jbvb.nio.chbrset.Chbrset}, chbrset nbme, or thbt use the plbtform's
     * defbult chbrset.
     *
     * @pbrbm  bscii
     *         The bytes to be converted to chbrbcters
     *
     * @pbrbm  hibyte
     *         The top 8 bits of ebch 16-bit Unicode code unit
     *
     * @pbrbm  offset
     *         The initibl offset
     * @pbrbm  count
     *         The length
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} or {@code count} brgument is invblid
     *
     * @see  #String(byte[], int)
     * @see  #String(byte[], int, int, jbvb.lbng.String)
     * @see  #String(byte[], int, int, jbvb.nio.chbrset.Chbrset)
     * @see  #String(byte[], int, int)
     * @see  #String(byte[], jbvb.lbng.String)
     * @see  #String(byte[], jbvb.nio.chbrset.Chbrset)
     * @see  #String(byte[])
     */
    @Deprecbted
    public String(byte bscii[], int hibyte, int offset, int count) {
        checkBounds(bscii, offset, count);
        chbr vblue[] = new chbr[count];

        if (hibyte == 0) {
            for (int i = count; i-- > 0;) {
                vblue[i] = (chbr)(bscii[i + offset] & 0xff);
            }
        } else {
            hibyte <<= 8;
            for (int i = count; i-- > 0;) {
                vblue[i] = (chbr)(hibyte | (bscii[i + offset] & 0xff));
            }
        }
        this.vblue = vblue;
    }

    /**
     * Allocbtes b new {@code String} contbining chbrbcters constructed from
     * bn brrby of 8-bit integer vblues. Ebch chbrbcter <i>c</i>in the
     * resulting string is constructed from the corresponding component
     * <i>b</i> in the byte brrby such thbt:
     *
     * <blockquote><pre>
     *     <b><i>c</i></b> == (chbr)(((hibyte &bmp; 0xff) &lt;&lt; 8)
     *                         | (<b><i>b</i></b> &bmp; 0xff))
     * </pre></blockquote>
     *
     * @deprecbted  This method does not properly convert bytes into
     * chbrbcters.  As of JDK&nbsp;1.1, the preferred wby to do this is vib the
     * {@code String} constructors thbt tbke b {@link
     * jbvb.nio.chbrset.Chbrset}, chbrset nbme, or thbt use the plbtform's
     * defbult chbrset.
     *
     * @pbrbm  bscii
     *         The bytes to be converted to chbrbcters
     *
     * @pbrbm  hibyte
     *         The top 8 bits of ebch 16-bit Unicode code unit
     *
     * @see  #String(byte[], int, int, jbvb.lbng.String)
     * @see  #String(byte[], int, int, jbvb.nio.chbrset.Chbrset)
     * @see  #String(byte[], int, int)
     * @see  #String(byte[], jbvb.lbng.String)
     * @see  #String(byte[], jbvb.nio.chbrset.Chbrset)
     * @see  #String(byte[])
     */
    @Deprecbted
    public String(byte bscii[], int hibyte) {
        this(bscii, hibyte, 0, bscii.length);
    }

    /* Common privbte utility method used to bounds check the byte brrby
     * bnd requested offset & length vblues used by the String(byte[],..)
     * constructors.
     */
    privbte stbtic void checkBounds(byte[] bytes, int offset, int length) {
        if (length < 0)
            throw new StringIndexOutOfBoundsException(length);
        if (offset < 0)
            throw new StringIndexOutOfBoundsException(offset);
        if (offset > bytes.length - length)
            throw new StringIndexOutOfBoundsException(offset + length);
    }

    /**
     * Constructs b new {@code String} by decoding the specified subbrrby of
     * bytes using the specified chbrset.  The length of the new {@code String}
     * is b function of the chbrset, bnd hence mby not be equbl to the length
     * of the subbrrby.
     *
     * <p> The behbvior of this constructor when the given bytes bre not vblid
     * in the given chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @pbrbm  offset
     *         The index of the first byte to decode
     *
     * @pbrbm  length
     *         The number of bytes to decode

     * @pbrbm  chbrsetNbme
     *         The nbme of b supported {@linkplbin jbvb.nio.chbrset.Chbrset
     *         chbrset}
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} bnd {@code length} brguments index
     *          chbrbcters outside the bounds of the {@code bytes} brrby
     *
     * @since  1.1
     */
    public String(byte bytes[], int offset, int length, String chbrsetNbme)
            throws UnsupportedEncodingException {
        if (chbrsetNbme == null)
            throw new NullPointerException("chbrsetNbme");
        checkBounds(bytes, offset, length);
        this.vblue = StringCoding.decode(chbrsetNbme, bytes, offset, length);
    }

    /**
     * Constructs b new {@code String} by decoding the specified subbrrby of
     * bytes using the specified {@linkplbin jbvb.nio.chbrset.Chbrset chbrset}.
     * The length of the new {@code String} is b function of the chbrset, bnd
     * hence mby not be equbl to the length of the subbrrby.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement string.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @pbrbm  offset
     *         The index of the first byte to decode
     *
     * @pbrbm  length
     *         The number of bytes to decode
     *
     * @pbrbm  chbrset
     *         The {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to be used to
     *         decode the {@code bytes}
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} bnd {@code length} brguments index
     *          chbrbcters outside the bounds of the {@code bytes} brrby
     *
     * @since  1.6
     */
    public String(byte bytes[], int offset, int length, Chbrset chbrset) {
        if (chbrset == null)
            throw new NullPointerException("chbrset");
        checkBounds(bytes, offset, length);
        this.vblue =  StringCoding.decode(chbrset, bytes, offset, length);
    }

    /**
     * Constructs b new {@code String} by decoding the specified brrby of bytes
     * using the specified {@linkplbin jbvb.nio.chbrset.Chbrset chbrset}.  The
     * length of the new {@code String} is b function of the chbrset, bnd hence
     * mby not be equbl to the length of the byte brrby.
     *
     * <p> The behbvior of this constructor when the given bytes bre not vblid
     * in the given chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of b supported {@linkplbin jbvb.nio.chbrset.Chbrset
     *         chbrset}
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @since  1.1
     */
    public String(byte bytes[], String chbrsetNbme)
            throws UnsupportedEncodingException {
        this(bytes, 0, bytes.length, chbrsetNbme);
    }

    /**
     * Constructs b new {@code String} by decoding the specified brrby of
     * bytes using the specified {@linkplbin jbvb.nio.chbrset.Chbrset chbrset}.
     * The length of the new {@code String} is b function of the chbrset, bnd
     * hence mby not be equbl to the length of the byte brrby.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement string.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @pbrbm  chbrset
     *         The {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to be used to
     *         decode the {@code bytes}
     *
     * @since  1.6
     */
    public String(byte bytes[], Chbrset chbrset) {
        this(bytes, 0, bytes.length, chbrset);
    }

    /**
     * Constructs b new {@code String} by decoding the specified subbrrby of
     * bytes using the plbtform's defbult chbrset.  The length of the new
     * {@code String} is b function of the chbrset, bnd hence mby not be equbl
     * to the length of the subbrrby.
     *
     * <p> The behbvior of this constructor when the given bytes bre not vblid
     * in the defbult chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @pbrbm  offset
     *         The index of the first byte to decode
     *
     * @pbrbm  length
     *         The number of bytes to decode
     *
     * @throws  IndexOutOfBoundsException
     *          If the {@code offset} bnd the {@code length} brguments index
     *          chbrbcters outside the bounds of the {@code bytes} brrby
     *
     * @since  1.1
     */
    public String(byte bytes[], int offset, int length) {
        checkBounds(bytes, offset, length);
        this.vblue = StringCoding.decode(bytes, offset, length);
    }

    /**
     * Constructs b new {@code String} by decoding the specified brrby of bytes
     * using the plbtform's defbult chbrset.  The length of the new {@code
     * String} is b function of the chbrset, bnd hence mby not be equbl to the
     * length of the byte brrby.
     *
     * <p> The behbvior of this constructor when the given bytes bre not vblid
     * in the defbult chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm  bytes
     *         The bytes to be decoded into chbrbcters
     *
     * @since  1.1
     */
    public String(byte bytes[]) {
        this(bytes, 0, bytes.length);
    }

    /**
     * Allocbtes b new string thbt contbins the sequence of chbrbcters
     * currently contbined in the string buffer brgument. The contents of the
     * string buffer bre copied; subsequent modificbtion of the string buffer
     * does not bffect the newly crebted string.
     *
     * @pbrbm  buffer
     *         A {@code StringBuffer}
     */
    public String(StringBuffer buffer) {
        synchronized(buffer) {
            this.vblue = Arrbys.copyOf(buffer.getVblue(), buffer.length());
        }
    }

    /**
     * Allocbtes b new string thbt contbins the sequence of chbrbcters
     * currently contbined in the string builder brgument. The contents of the
     * string builder bre copied; subsequent modificbtion of the string builder
     * does not bffect the newly crebted string.
     *
     * <p> This constructor is provided to ebse migrbtion to {@code
     * StringBuilder}. Obtbining b string from b string builder vib the {@code
     * toString} method is likely to run fbster bnd is generblly preferred.
     *
     * @pbrbm   builder
     *          A {@code StringBuilder}
     *
     * @since  1.5
     */
    public String(StringBuilder builder) {
        this.vblue = Arrbys.copyOf(builder.getVblue(), builder.length());
    }

    /*
    * Pbckbge privbte constructor which shbres vblue brrby for speed.
    * this constructor is blwbys expected to be cblled with shbre==true.
    * b sepbrbte constructor is needed becbuse we blrebdy hbve b public
    * String(chbr[]) constructor thbt mbkes b copy of the given chbr[].
    */
    String(chbr[] vblue, boolebn shbre) {
        // bssert shbre : "unshbred not supported";
        this.vblue = vblue;
    }

    /**
     * Returns the length of this string.
     * The length is equbl to the number of <b href="Chbrbcter.html#unicode">Unicode
     * code units</b> in the string.
     *
     * @return  the length of the sequence of chbrbcters represented by this
     *          object.
     */
    public int length() {
        return vblue.length;
    }

    /**
     * Returns {@code true} if, bnd only if, {@link #length()} is {@code 0}.
     *
     * @return {@code true} if {@link #length()} is {@code 0}, otherwise
     * {@code fblse}
     *
     * @since 1.6
     */
    public boolebn isEmpty() {
        return vblue.length == 0;
    }

    /**
     * Returns the {@code chbr} vblue bt the
     * specified index. An index rbnges from {@code 0} to
     * {@code length() - 1}. The first {@code chbr} vblue of the sequence
     * is bt index {@code 0}, the next bt index {@code 1},
     * bnd so on, bs for brrby indexing.
     *
     * <p>If the {@code chbr} vblue specified by the index is b
     * <b href="Chbrbcter.html#unicode">surrogbte</b>, the surrogbte
     * vblue is returned.
     *
     * @pbrbm      index   the index of the {@code chbr} vblue.
     * @return     the {@code chbr} vblue bt the specified index of this string.
     *             The first {@code chbr} vblue is bt index {@code 0}.
     * @exception  IndexOutOfBoundsException  if the {@code index}
     *             brgument is negbtive or not less thbn the length of this
     *             string.
     */
    public chbr chbrAt(int index) {
        if ((index < 0) || (index >= vblue.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return vblue[index];
    }

    /**
     * Returns the chbrbcter (Unicode code point) bt the specified
     * index. The index refers to {@code chbr} vblues
     * (Unicode code units) bnd rbnges from {@code 0} to
     * {@link #length()}{@code  - 1}.
     *
     * <p> If the {@code chbr} vblue specified bt the given index
     * is in the high-surrogbte rbnge, the following index is less
     * thbn the length of this {@code String}, bnd the
     * {@code chbr} vblue bt the following index is in the
     * low-surrogbte rbnge, then the supplementbry code point
     * corresponding to this surrogbte pbir is returned. Otherwise,
     * the {@code chbr} vblue bt the given index is returned.
     *
     * @pbrbm      index the index to the {@code chbr} vblues
     * @return     the code point vblue of the chbrbcter bt the
     *             {@code index}
     * @exception  IndexOutOfBoundsException  if the {@code index}
     *             brgument is negbtive or not less thbn the length of this
     *             string.
     * @since      1.5
     */
    public int codePointAt(int index) {
        if ((index < 0) || (index >= vblue.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return Chbrbcter.codePointAtImpl(vblue, index, vblue.length);
    }

    /**
     * Returns the chbrbcter (Unicode code point) before the specified
     * index. The index refers to {@code chbr} vblues
     * (Unicode code units) bnd rbnges from {@code 1} to {@link
     * ChbrSequence#length() length}.
     *
     * <p> If the {@code chbr} vblue bt {@code (index - 1)}
     * is in the low-surrogbte rbnge, {@code (index - 2)} is not
     * negbtive, bnd the {@code chbr} vblue bt {@code (index -
     * 2)} is in the high-surrogbte rbnge, then the
     * supplementbry code point vblue of the surrogbte pbir is
     * returned. If the {@code chbr} vblue bt {@code index -
     * 1} is bn unpbired low-surrogbte or b high-surrogbte, the
     * surrogbte vblue is returned.
     *
     * @pbrbm     index the index following the code point thbt should be returned
     * @return    the Unicode code point vblue before the given index.
     * @exception IndexOutOfBoundsException if the {@code index}
     *            brgument is less thbn 1 or grebter thbn the length
     *            of this string.
     * @since     1.5
     */
    public int codePointBefore(int index) {
        int i = index - 1;
        if ((i < 0) || (i >= vblue.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return Chbrbcter.codePointBeforeImpl(vblue, index, 0);
    }

    /**
     * Returns the number of Unicode code points in the specified text
     * rbnge of this {@code String}. The text rbnge begins bt the
     * specified {@code beginIndex} bnd extends to the
     * {@code chbr} bt index {@code endIndex - 1}. Thus the
     * length (in {@code chbr}s) of the text rbnge is
     * {@code endIndex-beginIndex}. Unpbired surrogbtes within
     * the text rbnge count bs one code point ebch.
     *
     * @pbrbm beginIndex the index to the first {@code chbr} of
     * the text rbnge.
     * @pbrbm endIndex the index bfter the lbst {@code chbr} of
     * the text rbnge.
     * @return the number of Unicode code points in the specified text
     * rbnge
     * @exception IndexOutOfBoundsException if the
     * {@code beginIndex} is negbtive, or {@code endIndex}
     * is lbrger thbn the length of this {@code String}, or
     * {@code beginIndex} is lbrger thbn {@code endIndex}.
     * @since  1.5
     */
    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > vblue.length || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }
        return Chbrbcter.codePointCountImpl(vblue, beginIndex, endIndex - beginIndex);
    }

    /**
     * Returns the index within this {@code String} thbt is
     * offset from the given {@code index} by
     * {@code codePointOffset} code points. Unpbired surrogbtes
     * within the text rbnge given by {@code index} bnd
     * {@code codePointOffset} count bs one code point ebch.
     *
     * @pbrbm index the index to be offset
     * @pbrbm codePointOffset the offset in code points
     * @return the index within this {@code String}
     * @exception IndexOutOfBoundsException if {@code index}
     *   is negbtive or lbrger then the length of this
     *   {@code String}, or if {@code codePointOffset} is positive
     *   bnd the substring stbrting with {@code index} hbs fewer
     *   thbn {@code codePointOffset} code points,
     *   or if {@code codePointOffset} is negbtive bnd the substring
     *   before {@code index} hbs fewer thbn the bbsolute vblue
     *   of {@code codePointOffset} code points.
     * @since 1.5
     */
    public int offsetByCodePoints(int index, int codePointOffset) {
        if (index < 0 || index > vblue.length) {
            throw new IndexOutOfBoundsException();
        }
        return Chbrbcter.offsetByCodePointsImpl(vblue, 0, vblue.length,
                index, codePointOffset);
    }

    /**
     * Copy chbrbcters from this string into dst stbrting bt dstBegin.
     * This method doesn't perform bny rbnge checking.
     */
    void getChbrs(chbr dst[], int dstBegin) {
        System.brrbycopy(vblue, 0, dst, dstBegin, vblue.length);
    }

    /**
     * Copies chbrbcters from this string into the destinbtion chbrbcter
     * brrby.
     * <p>
     * The first chbrbcter to be copied is bt index {@code srcBegin};
     * the lbst chbrbcter to be copied is bt index {@code srcEnd-1}
     * (thus the totbl number of chbrbcters to be copied is
     * {@code srcEnd-srcBegin}). The chbrbcters bre copied into the
     * subbrrby of {@code dst} stbrting bt index {@code dstBegin}
     * bnd ending bt index:
     * <blockquote><pre>
     *     dstbegin + (srcEnd-srcBegin) - 1
     * </pre></blockquote>
     *
     * @pbrbm      srcBegin   index of the first chbrbcter in the string
     *                        to copy.
     * @pbrbm      srcEnd     index bfter the lbst chbrbcter in the string
     *                        to copy.
     * @pbrbm      dst        the destinbtion brrby.
     * @pbrbm      dstBegin   the stbrt offset in the destinbtion brrby.
     * @exception IndexOutOfBoundsException If bny of the following
     *            is true:
     *            <ul><li>{@code srcBegin} is negbtive.
     *            <li>{@code srcBegin} is grebter thbn {@code srcEnd}
     *            <li>{@code srcEnd} is grebter thbn the length of this
     *                string
     *            <li>{@code dstBegin} is negbtive
     *            <li>{@code dstBegin+(srcEnd-srcBegin)} is lbrger thbn
     *                {@code dst.length}</ul>
     */
    public void getChbrs(int srcBegin, int srcEnd, chbr dst[], int dstBegin) {
        if (srcBegin < 0) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        }
        if (srcEnd > vblue.length) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        }
        if (srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
        }
        System.brrbycopy(vblue, srcBegin, dst, dstBegin, srcEnd - srcBegin);
    }

    /**
     * Copies chbrbcters from this string into the destinbtion byte brrby. Ebch
     * byte receives the 8 low-order bits of the corresponding chbrbcter. The
     * eight high-order bits of ebch chbrbcter bre not copied bnd do not
     * pbrticipbte in the trbnsfer in bny wby.
     *
     * <p> The first chbrbcter to be copied is bt index {@code srcBegin}; the
     * lbst chbrbcter to be copied is bt index {@code srcEnd-1}.  The totbl
     * number of chbrbcters to be copied is {@code srcEnd-srcBegin}. The
     * chbrbcters, converted to bytes, bre copied into the subbrrby of {@code
     * dst} stbrting bt index {@code dstBegin} bnd ending bt index:
     *
     * <blockquote><pre>
     *     dstbegin + (srcEnd-srcBegin) - 1
     * </pre></blockquote>
     *
     * @deprecbted  This method does not properly convert chbrbcters into
     * bytes.  As of JDK&nbsp;1.1, the preferred wby to do this is vib the
     * {@link #getBytes()} method, which uses the plbtform's defbult chbrset.
     *
     * @pbrbm  srcBegin
     *         Index of the first chbrbcter in the string to copy
     *
     * @pbrbm  srcEnd
     *         Index bfter the lbst chbrbcter in the string to copy
     *
     * @pbrbm  dst
     *         The destinbtion brrby
     *
     * @pbrbm  dstBegin
     *         The stbrt offset in the destinbtion brrby
     *
     * @throws  IndexOutOfBoundsException
     *          If bny of the following is true:
     *          <ul>
     *            <li> {@code srcBegin} is negbtive
     *            <li> {@code srcBegin} is grebter thbn {@code srcEnd}
     *            <li> {@code srcEnd} is grebter thbn the length of this String
     *            <li> {@code dstBegin} is negbtive
     *            <li> {@code dstBegin+(srcEnd-srcBegin)} is lbrger thbn {@code
     *                 dst.length}
     *          </ul>
     */
    @Deprecbted
    public void getBytes(int srcBegin, int srcEnd, byte dst[], int dstBegin) {
        if (srcBegin < 0) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        }
        if (srcEnd > vblue.length) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        }
        if (srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
        }
        Objects.requireNonNull(dst);

        int j = dstBegin;
        int n = srcEnd;
        int i = srcBegin;
        chbr[] vbl = vblue;   /* bvoid getfield opcode */

        while (i < n) {
            dst[j++] = (byte)vbl[i++];
        }
    }

    /**
     * Encodes this {@code String} into b sequence of bytes using the nbmed
     * chbrset, storing the result into b new byte brrby.
     *
     * <p> The behbvior of this method when this string cbnnot be encoded in
     * the given chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetEncoder} clbss should be used when more control
     * over the encoding process is required.
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of b supported {@linkplbin jbvb.nio.chbrset.Chbrset
     *         chbrset}
     *
     * @return  The resultbnt byte brrby
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @since  1.1
     */
    public byte[] getBytes(String chbrsetNbme)
            throws UnsupportedEncodingException {
        if (chbrsetNbme == null) throw new NullPointerException();
        return StringCoding.encode(chbrsetNbme, vblue, 0, vblue.length);
    }

    /**
     * Encodes this {@code String} into b sequence of bytes using the given
     * {@linkplbin jbvb.nio.chbrset.Chbrset chbrset}, storing the result into b
     * new byte brrby.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement byte brrby.  The
     * {@link jbvb.nio.chbrset.ChbrsetEncoder} clbss should be used when more
     * control over the encoding process is required.
     *
     * @pbrbm  chbrset
     *         The {@linkplbin jbvb.nio.chbrset.Chbrset} to be used to encode
     *         the {@code String}
     *
     * @return  The resultbnt byte brrby
     *
     * @since  1.6
     */
    public byte[] getBytes(Chbrset chbrset) {
        if (chbrset == null) throw new NullPointerException();
        return StringCoding.encode(chbrset, vblue, 0, vblue.length);
    }

    /**
     * Encodes this {@code String} into b sequence of bytes using the
     * plbtform's defbult chbrset, storing the result into b new byte brrby.
     *
     * <p> The behbvior of this method when this string cbnnot be encoded in
     * the defbult chbrset is unspecified.  The {@link
     * jbvb.nio.chbrset.ChbrsetEncoder} clbss should be used when more control
     * over the encoding process is required.
     *
     * @return  The resultbnt byte brrby
     *
     * @since      1.1
     */
    public byte[] getBytes() {
        return StringCoding.encode(vblue, 0, vblue.length);
    }

    /**
     * Compbres this string to the specified object.  The result is {@code
     * true} if bnd only if the brgument is not {@code null} bnd is b {@code
     * String} object thbt represents the sbme sequence of chbrbcters bs this
     * object.
     *
     * @pbrbm  bnObject
     *         The object to compbre this {@code String} bgbinst
     *
     * @return  {@code true} if the given object represents b {@code String}
     *          equivblent to this string, {@code fblse} otherwise
     *
     * @see  #compbreTo(String)
     * @see  #equblsIgnoreCbse(String)
     */
    public boolebn equbls(Object bnObject) {
        if (this == bnObject) {
            return true;
        }
        if (bnObject instbnceof String) {
            String bnotherString = (String)bnObject;
            int n = vblue.length;
            if (n == bnotherString.vblue.length) {
                chbr v1[] = vblue;
                chbr v2[] = bnotherString.vblue;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return fblse;
                    i++;
                }
                return true;
            }
        }
        return fblse;
    }

    /**
     * Compbres this string to the specified {@code StringBuffer}.  The result
     * is {@code true} if bnd only if this {@code String} represents the sbme
     * sequence of chbrbcters bs the specified {@code StringBuffer}. This method
     * synchronizes on the {@code StringBuffer}.
     *
     * @pbrbm  sb
     *         The {@code StringBuffer} to compbre this {@code String} bgbinst
     *
     * @return  {@code true} if this {@code String} represents the sbme
     *          sequence of chbrbcters bs the specified {@code StringBuffer},
     *          {@code fblse} otherwise
     *
     * @since  1.4
     */
    public boolebn contentEqubls(StringBuffer sb) {
        return contentEqubls((ChbrSequence)sb);
    }

    privbte boolebn nonSyncContentEqubls(AbstrbctStringBuilder sb) {
        chbr v1[] = vblue;
        chbr v2[] = sb.getVblue();
        int n = v1.length;
        if (n != sb.length()) {
            return fblse;
        }
        for (int i = 0; i < n; i++) {
            if (v1[i] != v2[i]) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Compbres this string to the specified {@code ChbrSequence}.  The
     * result is {@code true} if bnd only if this {@code String} represents the
     * sbme sequence of chbr vblues bs the specified sequence. Note thbt if the
     * {@code ChbrSequence} is b {@code StringBuffer} then the method
     * synchronizes on it.
     *
     * @pbrbm  cs
     *         The sequence to compbre this {@code String} bgbinst
     *
     * @return  {@code true} if this {@code String} represents the sbme
     *          sequence of chbr vblues bs the specified sequence, {@code
     *          fblse} otherwise
     *
     * @since  1.5
     */
    public boolebn contentEqubls(ChbrSequence cs) {
        // Argument is b StringBuffer, StringBuilder
        if (cs instbnceof AbstrbctStringBuilder) {
            if (cs instbnceof StringBuffer) {
                synchronized(cs) {
                   return nonSyncContentEqubls((AbstrbctStringBuilder)cs);
                }
            } else {
                return nonSyncContentEqubls((AbstrbctStringBuilder)cs);
            }
        }
        // Argument is b String
        if (cs.equbls(this))
            return true;
        // Argument is b generic ChbrSequence
        chbr v1[] = vblue;
        int n = v1.length;
        if (n != cs.length()) {
            return fblse;
        }
        for (int i = 0; i < n; i++) {
            if (v1[i] != cs.chbrAt(i)) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Compbres this {@code String} to bnother {@code String}, ignoring cbse
     * considerbtions.  Two strings bre considered equbl ignoring cbse if they
     * bre of the sbme length bnd corresponding chbrbcters in the two strings
     * bre equbl ignoring cbse.
     *
     * <p> Two chbrbcters {@code c1} bnd {@code c2} bre considered the sbme
     * ignoring cbse if bt lebst one of the following is true:
     * <ul>
     *   <li> The two chbrbcters bre the sbme (bs compbred by the
     *        {@code ==} operbtor)
     *   <li> Applying the method {@link
     *        jbvb.lbng.Chbrbcter#toUpperCbse(chbr)} to ebch chbrbcter
     *        produces the sbme result
     *   <li> Applying the method {@link
     *        jbvb.lbng.Chbrbcter#toLowerCbse(chbr)} to ebch chbrbcter
     *        produces the sbme result
     * </ul>
     *
     * @pbrbm  bnotherString
     *         The {@code String} to compbre this {@code String} bgbinst
     *
     * @return  {@code true} if the brgument is not {@code null} bnd it
     *          represents bn equivblent {@code String} ignoring cbse; {@code
     *          fblse} otherwise
     *
     * @see  #equbls(Object)
     */
    public boolebn equblsIgnoreCbse(String bnotherString) {
        return (this == bnotherString) ? true
                : (bnotherString != null)
                && (bnotherString.vblue.length == vblue.length)
                && regionMbtches(true, 0, bnotherString, 0, vblue.length);
    }

    /**
     * Compbres two strings lexicogrbphicblly.
     * The compbrison is bbsed on the Unicode vblue of ebch chbrbcter in
     * the strings. The chbrbcter sequence represented by this
     * {@code String} object is compbred lexicogrbphicblly to the
     * chbrbcter sequence represented by the brgument string. The result is
     * b negbtive integer if this {@code String} object
     * lexicogrbphicblly precedes the brgument string. The result is b
     * positive integer if this {@code String} object lexicogrbphicblly
     * follows the brgument string. The result is zero if the strings
     * bre equbl; {@code compbreTo} returns {@code 0} exbctly when
     * the {@link #equbls(Object)} method would return {@code true}.
     * <p>
     * This is the definition of lexicogrbphic ordering. If two strings bre
     * different, then either they hbve different chbrbcters bt some index
     * thbt is b vblid index for both strings, or their lengths bre different,
     * or both. If they hbve different chbrbcters bt one or more index
     * positions, let <i>k</i> be the smbllest such index; then the string
     * whose chbrbcter bt position <i>k</i> hbs the smbller vblue, bs
     * determined by using the &lt; operbtor, lexicogrbphicblly precedes the
     * other string. In this cbse, {@code compbreTo} returns the
     * difference of the two chbrbcter vblues bt position {@code k} in
     * the two string -- thbt is, the vblue:
     * <blockquote><pre>
     * this.chbrAt(k)-bnotherString.chbrAt(k)
     * </pre></blockquote>
     * If there is no index position bt which they differ, then the shorter
     * string lexicogrbphicblly precedes the longer string. In this cbse,
     * {@code compbreTo} returns the difference of the lengths of the
     * strings -- thbt is, the vblue:
     * <blockquote><pre>
     * this.length()-bnotherString.length()
     * </pre></blockquote>
     *
     * @pbrbm   bnotherString   the {@code String} to be compbred.
     * @return  the vblue {@code 0} if the brgument string is equbl to
     *          this string; b vblue less thbn {@code 0} if this string
     *          is lexicogrbphicblly less thbn the string brgument; bnd b
     *          vblue grebter thbn {@code 0} if this string is
     *          lexicogrbphicblly grebter thbn the string brgument.
     */
    public int compbreTo(String bnotherString) {
        int len1 = vblue.length;
        int len2 = bnotherString.vblue.length;
        int lim = Mbth.min(len1, len2);
        chbr v1[] = vblue;
        chbr v2[] = bnotherString.vblue;

        int k = 0;
        while (k < lim) {
            chbr c1 = v1[k];
            chbr c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * A Compbrbtor thbt orders {@code String} objects bs by
     * {@code compbreToIgnoreCbse}. This compbrbtor is seriblizbble.
     * <p>
     * Note thbt this Compbrbtor does <em>not</em> tbke locble into bccount,
     * bnd will result in bn unsbtisfbctory ordering for certbin locbles.
     * The jbvb.text pbckbge provides <em>Collbtors</em> to bllow
     * locble-sensitive ordering.
     *
     * @see     jbvb.text.Collbtor#compbre(String, String)
     * @since   1.2
     */
    public stbtic finbl Compbrbtor<String> CASE_INSENSITIVE_ORDER
                                         = new CbseInsensitiveCompbrbtor();
    privbte stbtic clbss CbseInsensitiveCompbrbtor
            implements Compbrbtor<String>, jbvb.io.Seriblizbble {
        // use seriblVersionUID from JDK 1.2.2 for interoperbbility
        privbte stbtic finbl long seriblVersionUID = 8575799808933029326L;

        public int compbre(String s1, String s2) {
            int n1 = s1.length();
            int n2 = s2.length();
            int min = Mbth.min(n1, n2);
            for (int i = 0; i < min; i++) {
                chbr c1 = s1.chbrAt(i);
                chbr c2 = s2.chbrAt(i);
                if (c1 != c2) {
                    c1 = Chbrbcter.toUpperCbse(c1);
                    c2 = Chbrbcter.toUpperCbse(c2);
                    if (c1 != c2) {
                        c1 = Chbrbcter.toLowerCbse(c1);
                        c2 = Chbrbcter.toLowerCbse(c2);
                        if (c1 != c2) {
                            // No overflow becbuse of numeric promotion
                            return c1 - c2;
                        }
                    }
                }
            }
            return n1 - n2;
        }

        /** Replbces the de-seriblized object. */
        privbte Object rebdResolve() { return CASE_INSENSITIVE_ORDER; }
    }

    /**
     * Compbres two strings lexicogrbphicblly, ignoring cbse
     * differences. This method returns bn integer whose sign is thbt of
     * cblling {@code compbreTo} with normblized versions of the strings
     * where cbse differences hbve been eliminbted by cblling
     * {@code Chbrbcter.toLowerCbse(Chbrbcter.toUpperCbse(chbrbcter))} on
     * ebch chbrbcter.
     * <p>
     * Note thbt this method does <em>not</em> tbke locble into bccount,
     * bnd will result in bn unsbtisfbctory ordering for certbin locbles.
     * The jbvb.text pbckbge provides <em>collbtors</em> to bllow
     * locble-sensitive ordering.
     *
     * @pbrbm   str   the {@code String} to be compbred.
     * @return  b negbtive integer, zero, or b positive integer bs the
     *          specified String is grebter thbn, equbl to, or less
     *          thbn this String, ignoring cbse considerbtions.
     * @see     jbvb.text.Collbtor#compbre(String, String)
     * @since   1.2
     */
    public int compbreToIgnoreCbse(String str) {
        return CASE_INSENSITIVE_ORDER.compbre(this, str);
    }

    /**
     * Tests if two string regions bre equbl.
     * <p>
     * A substring of this {@code String} object is compbred to b substring
     * of the brgument other. The result is true if these substrings
     * represent identicbl chbrbcter sequences. The substring of this
     * {@code String} object to be compbred begins bt index {@code toffset}
     * bnd hbs length {@code len}. The substring of other to be compbred
     * begins bt index {@code ooffset} bnd hbs length {@code len}. The
     * result is {@code fblse} if bnd only if bt lebst one of the following
     * is true:
     * <ul><li>{@code toffset} is negbtive.
     * <li>{@code ooffset} is negbtive.
     * <li>{@code toffset+len} is grebter thbn the length of this
     * {@code String} object.
     * <li>{@code ooffset+len} is grebter thbn the length of the other
     * brgument.
     * <li>There is some nonnegbtive integer <i>k</i> less thbn {@code len}
     * such thbt:
     * {@code this.chbrAt(toffset + }<i>k</i>{@code ) != other.chbrAt(ooffset + }
     * <i>k</i>{@code )}
     * </ul>
     *
     * @pbrbm   toffset   the stbrting offset of the subregion in this string.
     * @pbrbm   other     the string brgument.
     * @pbrbm   ooffset   the stbrting offset of the subregion in the string
     *                    brgument.
     * @pbrbm   len       the number of chbrbcters to compbre.
     * @return  {@code true} if the specified subregion of this string
     *          exbctly mbtches the specified subregion of the string brgument;
     *          {@code fblse} otherwise.
     */
    public boolebn regionMbtches(int toffset, String other, int ooffset,
            int len) {
        chbr tb[] = vblue;
        int to = toffset;
        chbr pb[] = other.vblue;
        int po = ooffset;
        // Note: toffset, ooffset, or len might be nebr -1>>>1.
        if ((ooffset < 0) || (toffset < 0)
                || (toffset > (long)vblue.length - len)
                || (ooffset > (long)other.vblue.length - len)) {
            return fblse;
        }
        while (len-- > 0) {
            if (tb[to++] != pb[po++]) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Tests if two string regions bre equbl.
     * <p>
     * A substring of this {@code String} object is compbred to b substring
     * of the brgument {@code other}. The result is {@code true} if these
     * substrings represent chbrbcter sequences thbt bre the sbme, ignoring
     * cbse if bnd only if {@code ignoreCbse} is true. The substring of
     * this {@code String} object to be compbred begins bt index
     * {@code toffset} bnd hbs length {@code len}. The substring of
     * {@code other} to be compbred begins bt index {@code ooffset} bnd
     * hbs length {@code len}. The result is {@code fblse} if bnd only if
     * bt lebst one of the following is true:
     * <ul><li>{@code toffset} is negbtive.
     * <li>{@code ooffset} is negbtive.
     * <li>{@code toffset+len} is grebter thbn the length of this
     * {@code String} object.
     * <li>{@code ooffset+len} is grebter thbn the length of the other
     * brgument.
     * <li>{@code ignoreCbse} is {@code fblse} bnd there is some nonnegbtive
     * integer <i>k</i> less thbn {@code len} such thbt:
     * <blockquote><pre>
     * this.chbrAt(toffset+k) != other.chbrAt(ooffset+k)
     * </pre></blockquote>
     * <li>{@code ignoreCbse} is {@code true} bnd there is some nonnegbtive
     * integer <i>k</i> less thbn {@code len} such thbt:
     * <blockquote><pre>
     * Chbrbcter.toLowerCbse(this.chbrAt(toffset+k)) !=
     Chbrbcter.toLowerCbse(other.chbrAt(ooffset+k))
     * </pre></blockquote>
     * bnd:
     * <blockquote><pre>
     * Chbrbcter.toUpperCbse(this.chbrAt(toffset+k)) !=
     *         Chbrbcter.toUpperCbse(other.chbrAt(ooffset+k))
     * </pre></blockquote>
     * </ul>
     *
     * @pbrbm   ignoreCbse   if {@code true}, ignore cbse when compbring
     *                       chbrbcters.
     * @pbrbm   toffset      the stbrting offset of the subregion in this
     *                       string.
     * @pbrbm   other        the string brgument.
     * @pbrbm   ooffset      the stbrting offset of the subregion in the string
     *                       brgument.
     * @pbrbm   len          the number of chbrbcters to compbre.
     * @return  {@code true} if the specified subregion of this string
     *          mbtches the specified subregion of the string brgument;
     *          {@code fblse} otherwise. Whether the mbtching is exbct
     *          or cbse insensitive depends on the {@code ignoreCbse}
     *          brgument.
     */
    public boolebn regionMbtches(boolebn ignoreCbse, int toffset,
            String other, int ooffset, int len) {
        chbr tb[] = vblue;
        int to = toffset;
        chbr pb[] = other.vblue;
        int po = ooffset;
        // Note: toffset, ooffset, or len might be nebr -1>>>1.
        if ((ooffset < 0) || (toffset < 0)
                || (toffset > (long)vblue.length - len)
                || (ooffset > (long)other.vblue.length - len)) {
            return fblse;
        }
        while (len-- > 0) {
            chbr c1 = tb[to++];
            chbr c2 = pb[po++];
            if (c1 == c2) {
                continue;
            }
            if (ignoreCbse) {
                // If chbrbcters don't mbtch but cbse mby be ignored,
                // try converting both chbrbcters to uppercbse.
                // If the results mbtch, then the compbrison scbn should
                // continue.
                chbr u1 = Chbrbcter.toUpperCbse(c1);
                chbr u2 = Chbrbcter.toUpperCbse(c2);
                if (u1 == u2) {
                    continue;
                }
                // Unfortunbtely, conversion to uppercbse does not work properly
                // for the Georgibn blphbbet, which hbs strbnge rules bbout cbse
                // conversion.  So we need to mbke one lbst check before
                // exiting.
                if (Chbrbcter.toLowerCbse(u1) == Chbrbcter.toLowerCbse(u2)) {
                    continue;
                }
            }
            return fblse;
        }
        return true;
    }

    /**
     * Tests if the substring of this string beginning bt the
     * specified index stbrts with the specified prefix.
     *
     * @pbrbm   prefix    the prefix.
     * @pbrbm   toffset   where to begin looking in this string.
     * @return  {@code true} if the chbrbcter sequence represented by the
     *          brgument is b prefix of the substring of this object stbrting
     *          bt index {@code toffset}; {@code fblse} otherwise.
     *          The result is {@code fblse} if {@code toffset} is
     *          negbtive or grebter thbn the length of this
     *          {@code String} object; otherwise the result is the sbme
     *          bs the result of the expression
     *          <pre>
     *          this.substring(toffset).stbrtsWith(prefix)
     *          </pre>
     */
    public boolebn stbrtsWith(String prefix, int toffset) {
        chbr tb[] = vblue;
        int to = toffset;
        chbr pb[] = prefix.vblue;
        int po = 0;
        int pc = prefix.vblue.length;
        // Note: toffset might be nebr -1>>>1.
        if ((toffset < 0) || (toffset > vblue.length - pc)) {
            return fblse;
        }
        while (--pc >= 0) {
            if (tb[to++] != pb[po++]) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Tests if this string stbrts with the specified prefix.
     *
     * @pbrbm   prefix   the prefix.
     * @return  {@code true} if the chbrbcter sequence represented by the
     *          brgument is b prefix of the chbrbcter sequence represented by
     *          this string; {@code fblse} otherwise.
     *          Note blso thbt {@code true} will be returned if the
     *          brgument is bn empty string or is equbl to this
     *          {@code String} object bs determined by the
     *          {@link #equbls(Object)} method.
     * @since   1.0
     */
    public boolebn stbrtsWith(String prefix) {
        return stbrtsWith(prefix, 0);
    }

    /**
     * Tests if this string ends with the specified suffix.
     *
     * @pbrbm   suffix   the suffix.
     * @return  {@code true} if the chbrbcter sequence represented by the
     *          brgument is b suffix of the chbrbcter sequence represented by
     *          this object; {@code fblse} otherwise. Note thbt the
     *          result will be {@code true} if the brgument is the
     *          empty string or is equbl to this {@code String} object
     *          bs determined by the {@link #equbls(Object)} method.
     */
    public boolebn endsWith(String suffix) {
        return stbrtsWith(suffix, vblue.length - suffix.vblue.length);
    }

    /**
     * Returns b hbsh code for this string. The hbsh code for b
     * {@code String} object is computed bs
     * <blockquote><pre>
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * </pre></blockquote>
     * using {@code int} brithmetic, where {@code s[i]} is the
     * <i>i</i>th chbrbcter of the string, {@code n} is the length of
     * the string, bnd {@code ^} indicbtes exponentibtion.
     * (The hbsh vblue of the empty string is zero.)
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        int h = hbsh;
        if (h == 0 && vblue.length > 0) {
            chbr vbl[] = vblue;

            for (int i = 0; i < vblue.length; i++) {
                h = 31 * h + vbl[i];
            }
            hbsh = h;
        }
        return h;
    }

    /**
     * Returns the index within this string of the first occurrence of
     * the specified chbrbcter. If b chbrbcter with vblue
     * {@code ch} occurs in the chbrbcter sequence represented by
     * this {@code String} object, then the index (in Unicode
     * code units) of the first such occurrence is returned. For
     * vblues of {@code ch} in the rbnge from 0 to 0xFFFF
     * (inclusive), this is the smbllest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * this.chbrAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. For other vblues of {@code ch}, it is the
     * smbllest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * this.codePointAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. In either cbse, if no such chbrbcter occurs in this
     * string, then {@code -1} is returned.
     *
     * @pbrbm   ch   b chbrbcter (Unicode code point).
     * @return  the index of the first occurrence of the chbrbcter in the
     *          chbrbcter sequence represented by this object, or
     *          {@code -1} if the chbrbcter does not occur.
     */
    public int indexOf(int ch) {
        return indexOf(ch, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified chbrbcter, stbrting the sebrch bt the specified index.
     * <p>
     * If b chbrbcter with vblue {@code ch} occurs in the
     * chbrbcter sequence represented by this {@code String}
     * object bt bn index no smbller thbn {@code fromIndex}, then
     * the index of the first such occurrence is returned. For vblues
     * of {@code ch} in the rbnge from 0 to 0xFFFF (inclusive),
     * this is the smbllest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * (this.chbrAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &gt;= fromIndex)
     * </pre></blockquote>
     * is true. For other vblues of {@code ch}, it is the
     * smbllest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * (this.codePointAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &gt;= fromIndex)
     * </pre></blockquote>
     * is true. In either cbse, if no such chbrbcter occurs in this
     * string bt or bfter position {@code fromIndex}, then
     * {@code -1} is returned.
     *
     * <p>
     * There is no restriction on the vblue of {@code fromIndex}. If it
     * is negbtive, it hbs the sbme effect bs if it were zero: this entire
     * string mby be sebrched. If it is grebter thbn the length of this
     * string, it hbs the sbme effect bs if it were equbl to the length of
     * this string: {@code -1} is returned.
     *
     * <p>All indices bre specified in {@code chbr} vblues
     * (Unicode code units).
     *
     * @pbrbm   ch          b chbrbcter (Unicode code point).
     * @pbrbm   fromIndex   the index to stbrt the sebrch from.
     * @return  the index of the first occurrence of the chbrbcter in the
     *          chbrbcter sequence represented by this object thbt is grebter
     *          thbn or equbl to {@code fromIndex}, or {@code -1}
     *          if the chbrbcter does not occur.
     */
    public int indexOf(int ch, int fromIndex) {
        finbl int mbx = vblue.length;
        if (fromIndex < 0) {
            fromIndex = 0;
        } else if (fromIndex >= mbx) {
            // Note: fromIndex might be nebr -1>>>1.
            return -1;
        }

        if (ch < Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT) {
            // hbndle most cbses here (ch is b BMP code point or b
            // negbtive vblue (invblid code point))
            finbl chbr[] vblue = this.vblue;
            for (int i = fromIndex; i < mbx; i++) {
                if (vblue[i] == ch) {
                    return i;
                }
            }
            return -1;
        } else {
            return indexOfSupplementbry(ch, fromIndex);
        }
    }

    /**
     * Hbndles (rbre) cblls of indexOf with b supplementbry chbrbcter.
     */
    privbte int indexOfSupplementbry(int ch, int fromIndex) {
        if (Chbrbcter.isVblidCodePoint(ch)) {
            finbl chbr[] vblue = this.vblue;
            finbl chbr hi = Chbrbcter.highSurrogbte(ch);
            finbl chbr lo = Chbrbcter.lowSurrogbte(ch);
            finbl int mbx = vblue.length - 1;
            for (int i = fromIndex; i < mbx; i++) {
                if (vblue[i] == hi && vblue[i + 1] == lo) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index within this string of the lbst occurrence of
     * the specified chbrbcter. For vblues of {@code ch} in the
     * rbnge from 0 to 0xFFFF (inclusive), the index (in Unicode code
     * units) returned is the lbrgest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * this.chbrAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. For other vblues of {@code ch}, it is the
     * lbrgest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * this.codePointAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true.  In either cbse, if no such chbrbcter occurs in this
     * string, then {@code -1} is returned.  The
     * {@code String} is sebrched bbckwbrds stbrting bt the lbst
     * chbrbcter.
     *
     * @pbrbm   ch   b chbrbcter (Unicode code point).
     * @return  the index of the lbst occurrence of the chbrbcter in the
     *          chbrbcter sequence represented by this object, or
     *          {@code -1} if the chbrbcter does not occur.
     */
    public int lbstIndexOf(int ch) {
        return lbstIndexOf(ch, vblue.length - 1);
    }

    /**
     * Returns the index within this string of the lbst occurrence of
     * the specified chbrbcter, sebrching bbckwbrd stbrting bt the
     * specified index. For vblues of {@code ch} in the rbnge
     * from 0 to 0xFFFF (inclusive), the index returned is the lbrgest
     * vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * (this.chbrAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &lt;= fromIndex)
     * </pre></blockquote>
     * is true. For other vblues of {@code ch}, it is the
     * lbrgest vblue <i>k</i> such thbt:
     * <blockquote><pre>
     * (this.codePointAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &lt;= fromIndex)
     * </pre></blockquote>
     * is true. In either cbse, if no such chbrbcter occurs in this
     * string bt or before position {@code fromIndex}, then
     * {@code -1} is returned.
     *
     * <p>All indices bre specified in {@code chbr} vblues
     * (Unicode code units).
     *
     * @pbrbm   ch          b chbrbcter (Unicode code point).
     * @pbrbm   fromIndex   the index to stbrt the sebrch from. There is no
     *          restriction on the vblue of {@code fromIndex}. If it is
     *          grebter thbn or equbl to the length of this string, it hbs
     *          the sbme effect bs if it were equbl to one less thbn the
     *          length of this string: this entire string mby be sebrched.
     *          If it is negbtive, it hbs the sbme effect bs if it were -1:
     *          -1 is returned.
     * @return  the index of the lbst occurrence of the chbrbcter in the
     *          chbrbcter sequence represented by this object thbt is less
     *          thbn or equbl to {@code fromIndex}, or {@code -1}
     *          if the chbrbcter does not occur before thbt point.
     */
    public int lbstIndexOf(int ch, int fromIndex) {
        if (ch < Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT) {
            // hbndle most cbses here (ch is b BMP code point or b
            // negbtive vblue (invblid code point))
            finbl chbr[] vblue = this.vblue;
            int i = Mbth.min(fromIndex, vblue.length - 1);
            for (; i >= 0; i--) {
                if (vblue[i] == ch) {
                    return i;
                }
            }
            return -1;
        } else {
            return lbstIndexOfSupplementbry(ch, fromIndex);
        }
    }

    /**
     * Hbndles (rbre) cblls of lbstIndexOf with b supplementbry chbrbcter.
     */
    privbte int lbstIndexOfSupplementbry(int ch, int fromIndex) {
        if (Chbrbcter.isVblidCodePoint(ch)) {
            finbl chbr[] vblue = this.vblue;
            chbr hi = Chbrbcter.highSurrogbte(ch);
            chbr lo = Chbrbcter.lowSurrogbte(ch);
            int i = Mbth.min(fromIndex, vblue.length - 2);
            for (; i >= 0; i--) {
                if (vblue[i] == hi && vblue[i + 1] == lo) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring.
     *
     * <p>The returned index is the smbllest vblue {@code k} for which:
     * <pre>{@code
     * this.stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str   the substring to sebrch for.
     * @return  the index of the first occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     */
    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring, stbrting bt the specified index.
     *
     * <p>The returned index is the smbllest vblue {@code k} for which:
     * <pre>{@code
     *     k >= Mbth.min(fromIndex, this.length()) &&
     *                   this.stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str         the substring to sebrch for.
     * @pbrbm   fromIndex   the index from which to stbrt the sebrch.
     * @return  the index of the first occurrence of the specified substring,
     *          stbrting bt the specified index,
     *          or {@code -1} if there is no such occurrence.
     */
    public int indexOf(String str, int fromIndex) {
        return indexOf(vblue, 0, vblue.length,
                str.vblue, 0, str.vblue.length, fromIndex);
    }

    /**
     * Code shbred by String bnd AbstrbctStringBuilder to do sebrches. The
     * source is the chbrbcter brrby being sebrched, bnd the tbrget
     * is the string being sebrched for.
     *
     * @pbrbm   source       the chbrbcters being sebrched.
     * @pbrbm   sourceOffset offset of the source string.
     * @pbrbm   sourceCount  count of the source string.
     * @pbrbm   tbrget       the chbrbcters being sebrched for.
     * @pbrbm   fromIndex    the index to begin sebrching from.
     */
    stbtic int indexOf(chbr[] source, int sourceOffset, int sourceCount,
            String tbrget, int fromIndex) {
        return indexOf(source, sourceOffset, sourceCount,
                       tbrget.vblue, 0, tbrget.vblue.length,
                       fromIndex);
    }

    /**
     * Code shbred by String bnd StringBuffer to do sebrches. The
     * source is the chbrbcter brrby being sebrched, bnd the tbrget
     * is the string being sebrched for.
     *
     * @pbrbm   source       the chbrbcters being sebrched.
     * @pbrbm   sourceOffset offset of the source string.
     * @pbrbm   sourceCount  count of the source string.
     * @pbrbm   tbrget       the chbrbcters being sebrched for.
     * @pbrbm   tbrgetOffset offset of the tbrget string.
     * @pbrbm   tbrgetCount  count of the tbrget string.
     * @pbrbm   fromIndex    the index to begin sebrching from.
     */
    stbtic int indexOf(chbr[] source, int sourceOffset, int sourceCount,
            chbr[] tbrget, int tbrgetOffset, int tbrgetCount,
            int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (tbrgetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (tbrgetCount == 0) {
            return fromIndex;
        }

        chbr first = tbrget[tbrgetOffset];
        int mbx = sourceOffset + (sourceCount - tbrgetCount);

        for (int i = sourceOffset + fromIndex; i <= mbx; i++) {
            /* Look for first chbrbcter. */
            if (source[i] != first) {
                while (++i <= mbx && source[i] != first);
            }

            /* Found first chbrbcter, now look bt the rest of v2 */
            if (i <= mbx) {
                int j = i + 1;
                int end = j + tbrgetCount - 1;
                for (int k = tbrgetOffset + 1; j < end && source[j]
                        == tbrget[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index within this string of the lbst occurrence of the
     * specified substring.  The lbst occurrence of the empty string ""
     * is considered to occur bt the index vblue {@code this.length()}.
     *
     * <p>The returned index is the lbrgest vblue {@code k} for which:
     * <pre>{@code
     * this.stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str   the substring to sebrch for.
     * @return  the index of the lbst occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     */
    public int lbstIndexOf(String str) {
        return lbstIndexOf(str, vblue.length);
    }

    /**
     * Returns the index within this string of the lbst occurrence of the
     * specified substring, sebrching bbckwbrd stbrting bt the specified index.
     *
     * <p>The returned index is the lbrgest vblue {@code k} for which:
     * <pre>{@code
     *     k <= Mbth.min(fromIndex, this.length()) &&
     *                   this.stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str         the substring to sebrch for.
     * @pbrbm   fromIndex   the index to stbrt the sebrch from.
     * @return  the index of the lbst occurrence of the specified substring,
     *          sebrching bbckwbrd from the specified index,
     *          or {@code -1} if there is no such occurrence.
     */
    public int lbstIndexOf(String str, int fromIndex) {
        return lbstIndexOf(vblue, 0, vblue.length,
                str.vblue, 0, str.vblue.length, fromIndex);
    }

    /**
     * Code shbred by String bnd AbstrbctStringBuilder to do sebrches. The
     * source is the chbrbcter brrby being sebrched, bnd the tbrget
     * is the string being sebrched for.
     *
     * @pbrbm   source       the chbrbcters being sebrched.
     * @pbrbm   sourceOffset offset of the source string.
     * @pbrbm   sourceCount  count of the source string.
     * @pbrbm   tbrget       the chbrbcters being sebrched for.
     * @pbrbm   fromIndex    the index to begin sebrching from.
     */
    stbtic int lbstIndexOf(chbr[] source, int sourceOffset, int sourceCount,
            String tbrget, int fromIndex) {
        return lbstIndexOf(source, sourceOffset, sourceCount,
                       tbrget.vblue, 0, tbrget.vblue.length,
                       fromIndex);
    }

    /**
     * Code shbred by String bnd StringBuffer to do sebrches. The
     * source is the chbrbcter brrby being sebrched, bnd the tbrget
     * is the string being sebrched for.
     *
     * @pbrbm   source       the chbrbcters being sebrched.
     * @pbrbm   sourceOffset offset of the source string.
     * @pbrbm   sourceCount  count of the source string.
     * @pbrbm   tbrget       the chbrbcters being sebrched for.
     * @pbrbm   tbrgetOffset offset of the tbrget string.
     * @pbrbm   tbrgetCount  count of the tbrget string.
     * @pbrbm   fromIndex    the index to begin sebrching from.
     */
    stbtic int lbstIndexOf(chbr[] source, int sourceOffset, int sourceCount,
            chbr[] tbrget, int tbrgetOffset, int tbrgetCount,
            int fromIndex) {
        /*
         * Check brguments; return immedibtely where possible. For
         * consistency, don't check for null str.
         */
        int rightIndex = sourceCount - tbrgetCount;
        if (fromIndex < 0) {
            return -1;
        }
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }
        /* Empty string blwbys mbtches. */
        if (tbrgetCount == 0) {
            return fromIndex;
        }

        int strLbstIndex = tbrgetOffset + tbrgetCount - 1;
        chbr strLbstChbr = tbrget[strLbstIndex];
        int min = sourceOffset + tbrgetCount - 1;
        int i = min + fromIndex;

    stbrtSebrchForLbstChbr:
        while (true) {
            while (i >= min && source[i] != strLbstChbr) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int stbrt = j - (tbrgetCount - 1);
            int k = strLbstIndex - 1;

            while (j > stbrt) {
                if (source[j--] != tbrget[k--]) {
                    i--;
                    continue stbrtSebrchForLbstChbr;
                }
            }
            return stbrt - sourceOffset + 1;
        }
    }

    /**
     * Returns b string thbt is b substring of this string. The
     * substring begins with the chbrbcter bt the specified index bnd
     * extends to the end of this string. <p>
     * Exbmples:
     * <blockquote><pre>
     * "unhbppy".substring(2) returns "hbppy"
     * "Hbrbison".substring(3) returns "bison"
     * "emptiness".substring(9) returns "" (bn empty string)
     * </pre></blockquote>
     *
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @return     the specified substring.
     * @exception  IndexOutOfBoundsException  if
     *             {@code beginIndex} is negbtive or lbrger thbn the
     *             length of this {@code String} object.
     */
    public String substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        int subLen = vblue.length - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return (beginIndex == 0) ? this : new String(vblue, beginIndex, subLen);
    }

    /**
     * Returns b string thbt is b substring of this string. The
     * substring begins bt the specified {@code beginIndex} bnd
     * extends to the chbrbcter bt index {@code endIndex - 1}.
     * Thus the length of the substring is {@code endIndex-beginIndex}.
     * <p>
     * Exbmples:
     * <blockquote><pre>
     * "hbmburger".substring(4, 8) returns "urge"
     * "smiles".substring(1, 5) returns "mile"
     * </pre></blockquote>
     *
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @pbrbm      endIndex     the ending index, exclusive.
     * @return     the specified substring.
     * @exception  IndexOutOfBoundsException  if the
     *             {@code beginIndex} is negbtive, or
     *             {@code endIndex} is lbrger thbn the length of
     *             this {@code String} object, or
     *             {@code beginIndex} is lbrger thbn
     *             {@code endIndex}.
     */
    public String substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > vblue.length) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return ((beginIndex == 0) && (endIndex == vblue.length)) ? this
                : new String(vblue, beginIndex, subLen);
    }

    /**
     * Returns b chbrbcter sequence thbt is b subsequence of this sequence.
     *
     * <p> An invocbtion of this method of the form
     *
     * <blockquote><pre>
     * str.subSequence(begin,&nbsp;end)</pre></blockquote>
     *
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <blockquote><pre>
     * str.substring(begin,&nbsp;end)</pre></blockquote>
     *
     * @bpiNote
     * This method is defined so thbt the {@code String} clbss cbn implement
     * the {@link ChbrSequence} interfbce.
     *
     * @pbrbm   beginIndex   the begin index, inclusive.
     * @pbrbm   endIndex     the end index, exclusive.
     * @return  the specified subsequence.
     *
     * @throws  IndexOutOfBoundsException
     *          if {@code beginIndex} or {@code endIndex} is negbtive,
     *          if {@code endIndex} is grebter thbn {@code length()},
     *          or if {@code beginIndex} is grebter thbn {@code endIndex}
     *
     * @since 1.4
     * @spec JSR-51
     */
    public ChbrSequence subSequence(int beginIndex, int endIndex) {
        return this.substring(beginIndex, endIndex);
    }

    /**
     * Concbtenbtes the specified string to the end of this string.
     * <p>
     * If the length of the brgument string is {@code 0}, then this
     * {@code String} object is returned. Otherwise, b
     * {@code String} object is returned thbt represents b chbrbcter
     * sequence thbt is the concbtenbtion of the chbrbcter sequence
     * represented by this {@code String} object bnd the chbrbcter
     * sequence represented by the brgument string.<p>
     * Exbmples:
     * <blockquote><pre>
     * "cbres".concbt("s") returns "cbress"
     * "to".concbt("get").concbt("her") returns "together"
     * </pre></blockquote>
     *
     * @pbrbm   str   the {@code String} thbt is concbtenbted to the end
     *                of this {@code String}.
     * @return  b string thbt represents the concbtenbtion of this object's
     *          chbrbcters followed by the string brgument's chbrbcters.
     */
    public String concbt(String str) {
        int otherLen = str.length();
        if (otherLen == 0) {
            return this;
        }
        int len = vblue.length;
        chbr buf[] = Arrbys.copyOf(vblue, len + otherLen);
        str.getChbrs(buf, len);
        return new String(buf, true);
    }

    /**
     * Returns b string resulting from replbcing bll occurrences of
     * {@code oldChbr} in this string with {@code newChbr}.
     * <p>
     * If the chbrbcter {@code oldChbr} does not occur in the
     * chbrbcter sequence represented by this {@code String} object,
     * then b reference to this {@code String} object is returned.
     * Otherwise, b {@code String} object is returned thbt
     * represents b chbrbcter sequence identicbl to the chbrbcter sequence
     * represented by this {@code String} object, except thbt every
     * occurrence of {@code oldChbr} is replbced by bn occurrence
     * of {@code newChbr}.
     * <p>
     * Exbmples:
     * <blockquote><pre>
     * "mesquite in your cellbr".replbce('e', 'o')
     *         returns "mosquito in your collbr"
     * "the wbr of bbronets".replbce('r', 'y')
     *         returns "the wby of bbyonets"
     * "spbrring with b purple porpoise".replbce('p', 't')
     *         returns "stbrring with b turtle tortoise"
     * "JonL".replbce('q', 'x') returns "JonL" (no chbnge)
     * </pre></blockquote>
     *
     * @pbrbm   oldChbr   the old chbrbcter.
     * @pbrbm   newChbr   the new chbrbcter.
     * @return  b string derived from this string by replbcing every
     *          occurrence of {@code oldChbr} with {@code newChbr}.
     */
    public String replbce(chbr oldChbr, chbr newChbr) {
        if (oldChbr != newChbr) {
            int len = vblue.length;
            int i = -1;
            chbr[] vbl = vblue; /* bvoid getfield opcode */

            while (++i < len) {
                if (vbl[i] == oldChbr) {
                    brebk;
                }
            }
            if (i < len) {
                chbr buf[] = new chbr[len];
                for (int j = 0; j < i; j++) {
                    buf[j] = vbl[j];
                }
                while (i < len) {
                    chbr c = vbl[i];
                    buf[i] = (c == oldChbr) ? newChbr : c;
                    i++;
                }
                return new String(buf, true);
            }
        }
        return this;
    }

    /**
     * Tells whether or not this string mbtches the given <b
     * href="../util/regex/Pbttern.html#sum">regulbr expression</b>.
     *
     * <p> An invocbtion of this method of the form
     * <i>str</i>{@code .mbtches(}<i>regex</i>{@code )} yields exbctly the
     * sbme result bs the expression
     *
     * <blockquote>
     * {@link jbvb.util.regex.Pbttern}.{@link jbvb.util.regex.Pbttern#mbtches(String,ChbrSequence)
     * mbtches(<i>regex</i>, <i>str</i>)}
     * </blockquote>
     *
     * @pbrbm   regex
     *          the regulbr expression to which this string is to be mbtched
     *
     * @return  {@code true} if, bnd only if, this string mbtches the
     *          given regulbr expression
     *
     * @throws  PbtternSyntbxException
     *          if the regulbr expression's syntbx is invblid
     *
     * @see jbvb.util.regex.Pbttern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public boolebn mbtches(String regex) {
        return Pbttern.mbtches(regex, this);
    }

    /**
     * Returns true if bnd only if this string contbins the specified
     * sequence of chbr vblues.
     *
     * @pbrbm s the sequence to sebrch for
     * @return true if this string contbins {@code s}, fblse otherwise
     * @since 1.5
     */
    public boolebn contbins(ChbrSequence s) {
        return indexOf(s.toString()) > -1;
    }

    /**
     * Replbces the first substring of this string thbt mbtches the given <b
     * href="../util/regex/Pbttern.html#sum">regulbr expression</b> with the
     * given replbcement.
     *
     * <p> An invocbtion of this method of the form
     * <i>str</i>{@code .replbceFirst(}<i>regex</i>{@code ,} <i>repl</i>{@code )}
     * yields exbctly the sbme result bs the expression
     *
     * <blockquote>
     * <code>
     * {@link jbvb.util.regex.Pbttern}.{@link
     * jbvb.util.regex.Pbttern#compile compile}(<i>regex</i>).{@link
     * jbvb.util.regex.Pbttern#mbtcher(jbvb.lbng.ChbrSequence) mbtcher}(<i>str</i>).{@link
     * jbvb.util.regex.Mbtcher#replbceFirst replbceFirst}(<i>repl</i>)
     * </code>
     * </blockquote>
     *
     *<p>
     * Note thbt bbckslbshes ({@code \}) bnd dollbr signs ({@code $}) in the
     * replbcement string mby cbuse the results to be different thbn if it were
     * being trebted bs b literbl replbcement string; see
     * {@link jbvb.util.regex.Mbtcher#replbceFirst}.
     * Use {@link jbvb.util.regex.Mbtcher#quoteReplbcement} to suppress the specibl
     * mebning of these chbrbcters, if desired.
     *
     * @pbrbm   regex
     *          the regulbr expression to which this string is to be mbtched
     * @pbrbm   replbcement
     *          the string to be substituted for the first mbtch
     *
     * @return  The resulting {@code String}
     *
     * @throws  PbtternSyntbxException
     *          if the regulbr expression's syntbx is invblid
     *
     * @see jbvb.util.regex.Pbttern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replbceFirst(String regex, String replbcement) {
        return Pbttern.compile(regex).mbtcher(this).replbceFirst(replbcement);
    }

    /**
     * Replbces ebch substring of this string thbt mbtches the given <b
     * href="../util/regex/Pbttern.html#sum">regulbr expression</b> with the
     * given replbcement.
     *
     * <p> An invocbtion of this method of the form
     * <i>str</i>{@code .replbceAll(}<i>regex</i>{@code ,} <i>repl</i>{@code )}
     * yields exbctly the sbme result bs the expression
     *
     * <blockquote>
     * <code>
     * {@link jbvb.util.regex.Pbttern}.{@link
     * jbvb.util.regex.Pbttern#compile compile}(<i>regex</i>).{@link
     * jbvb.util.regex.Pbttern#mbtcher(jbvb.lbng.ChbrSequence) mbtcher}(<i>str</i>).{@link
     * jbvb.util.regex.Mbtcher#replbceAll replbceAll}(<i>repl</i>)
     * </code>
     * </blockquote>
     *
     *<p>
     * Note thbt bbckslbshes ({@code \}) bnd dollbr signs ({@code $}) in the
     * replbcement string mby cbuse the results to be different thbn if it were
     * being trebted bs b literbl replbcement string; see
     * {@link jbvb.util.regex.Mbtcher#replbceAll Mbtcher.replbceAll}.
     * Use {@link jbvb.util.regex.Mbtcher#quoteReplbcement} to suppress the specibl
     * mebning of these chbrbcters, if desired.
     *
     * @pbrbm   regex
     *          the regulbr expression to which this string is to be mbtched
     * @pbrbm   replbcement
     *          the string to be substituted for ebch mbtch
     *
     * @return  The resulting {@code String}
     *
     * @throws  PbtternSyntbxException
     *          if the regulbr expression's syntbx is invblid
     *
     * @see jbvb.util.regex.Pbttern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replbceAll(String regex, String replbcement) {
        return Pbttern.compile(regex).mbtcher(this).replbceAll(replbcement);
    }

    /**
     * Replbces ebch substring of this string thbt mbtches the literbl tbrget
     * sequence with the specified literbl replbcement sequence. The
     * replbcement proceeds from the beginning of the string to the end, for
     * exbmple, replbcing "bb" with "b" in the string "bbb" will result in
     * "bb" rbther thbn "bb".
     *
     * @pbrbm  tbrget The sequence of chbr vblues to be replbced
     * @pbrbm  replbcement The replbcement sequence of chbr vblues
     * @return  The resulting string
     * @since 1.5
     */
    public String replbce(ChbrSequence tbrget, ChbrSequence replbcement) {
        return Pbttern.compile(tbrget.toString(), Pbttern.LITERAL).mbtcher(
                this).replbceAll(Mbtcher.quoteReplbcement(replbcement.toString()));
    }

    /**
     * Splits this string bround mbtches of the given
     * <b href="../util/regex/Pbttern.html#sum">regulbr expression</b>.
     *
     * <p> The brrby returned by this method contbins ebch substring of this
     * string thbt is terminbted by bnother substring thbt mbtches the given
     * expression or is terminbted by the end of the string.  The substrings in
     * the brrby bre in the order in which they occur in this string.  If the
     * expression does not mbtch bny pbrt of the input then the resulting brrby
     * hbs just one element, nbmely this string.
     *
     * <p> When there is b positive-width mbtch bt the beginning of this
     * string then bn empty lebding substring is included bt the beginning
     * of the resulting brrby. A zero-width mbtch bt the beginning however
     * never produces such empty lebding substring.
     *
     * <p> The {@code limit} pbrbmeter controls the number of times the
     * pbttern is bpplied bnd therefore bffects the length of the resulting
     * brrby.  If the limit <i>n</i> is grebter thbn zero then the pbttern
     * will be bpplied bt most <i>n</i>&nbsp;-&nbsp;1 times, the brrby's
     * length will be no grebter thbn <i>n</i>, bnd the brrby's lbst entry
     * will contbin bll input beyond the lbst mbtched delimiter.  If <i>n</i>
     * is non-positive then the pbttern will be bpplied bs mbny times bs
     * possible bnd the brrby cbn hbve bny length.  If <i>n</i> is zero then
     * the pbttern will be bpplied bs mbny times bs possible, the brrby cbn
     * hbve bny length, bnd trbiling empty strings will be discbrded.
     *
     * <p> The string {@code "boo:bnd:foo"}, for exbmple, yields the
     * following results with these pbrbmeters:
     *
     * <blockquote><tbble cellpbdding=1 cellspbcing=0 summbry="Split exbmple showing regex, limit, bnd result">
     * <tr>
     *     <th>Regex</th>
     *     <th>Limit</th>
     *     <th>Result</th>
     * </tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>2</td>
     *     <td>{@code { "boo", "bnd:foo" }}</td></tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>5</td>
     *     <td>{@code { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>-2</td>
     *     <td>{@code { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>5</td>
     *     <td>{@code { "b", "", ":bnd:f", "", "" }}</td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>-2</td>
     *     <td>{@code { "b", "", ":bnd:f", "", "" }}</td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>0</td>
     *     <td>{@code { "b", "", ":bnd:f" }}</td></tr>
     * </tbble></blockquote>
     *
     * <p> An invocbtion of this method of the form
     * <i>str.</i>{@code split(}<i>regex</i>{@code ,}&nbsp;<i>n</i>{@code )}
     * yields the sbme result bs the expression
     *
     * <blockquote>
     * <code>
     * {@link jbvb.util.regex.Pbttern}.{@link
     * jbvb.util.regex.Pbttern#compile compile}(<i>regex</i>).{@link
     * jbvb.util.regex.Pbttern#split(jbvb.lbng.ChbrSequence,int) split}(<i>str</i>,&nbsp;<i>n</i>)
     * </code>
     * </blockquote>
     *
     *
     * @pbrbm  regex
     *         the delimiting regulbr expression
     *
     * @pbrbm  limit
     *         the result threshold, bs described bbove
     *
     * @return  the brrby of strings computed by splitting this string
     *          bround mbtches of the given regulbr expression
     *
     * @throws  PbtternSyntbxException
     *          if the regulbr expression's syntbx is invblid
     *
     * @see jbvb.util.regex.Pbttern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String[] split(String regex, int limit) {
        /* fbstpbth if the regex is b
         (1)one-chbr String bnd this chbrbcter is not one of the
            RegEx's metb chbrbcters ".$|()[{^?*+\\", or
         (2)two-chbr String bnd the first chbr is the bbckslbsh bnd
            the second is not the bscii digit or bscii letter.
         */
        chbr ch = 0;
        if (((regex.vblue.length == 1 &&
             ".$|()[{^?*+\\".indexOf(ch = regex.chbrAt(0)) == -1) ||
             (regex.length() == 2 &&
              regex.chbrAt(0) == '\\' &&
              (((ch = regex.chbrAt(1))-'0')|('9'-ch)) < 0 &&
              ((ch-'b')|('z'-ch)) < 0 &&
              ((ch-'A')|('Z'-ch)) < 0)) &&
            (ch < Chbrbcter.MIN_HIGH_SURROGATE ||
             ch > Chbrbcter.MAX_LOW_SURROGATE))
        {
            int off = 0;
            int next = 0;
            boolebn limited = limit > 0;
            ArrbyList<String> list = new ArrbyList<>();
            while ((next = indexOf(ch, off)) != -1) {
                if (!limited || list.size() < limit - 1) {
                    list.bdd(substring(off, next));
                    off = next + 1;
                } else {    // lbst one
                    //bssert (list.size() == limit - 1);
                    list.bdd(substring(off, vblue.length));
                    off = vblue.length;
                    brebk;
                }
            }
            // If no mbtch wbs found, return this
            if (off == 0)
                return new String[]{this};

            // Add rembining segment
            if (!limited || list.size() < limit)
                list.bdd(substring(off, vblue.length));

            // Construct result
            int resultSize = list.size();
            if (limit == 0) {
                while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
                    resultSize--;
                }
            }
            String[] result = new String[resultSize];
            return list.subList(0, resultSize).toArrby(result);
        }
        return Pbttern.compile(regex).split(this, limit);
    }

    /**
     * Splits this string bround mbtches of the given <b
     * href="../util/regex/Pbttern.html#sum">regulbr expression</b>.
     *
     * <p> This method works bs if by invoking the two-brgument {@link
     * #split(String, int) split} method with the given expression bnd b limit
     * brgument of zero.  Trbiling empty strings bre therefore not included in
     * the resulting brrby.
     *
     * <p> The string {@code "boo:bnd:foo"}, for exbmple, yields the following
     * results with these expressions:
     *
     * <blockquote><tbble cellpbdding=1 cellspbcing=0 summbry="Split exbmples showing regex bnd result">
     * <tr>
     *  <th>Regex</th>
     *  <th>Result</th>
     * </tr>
     * <tr><td blign=center>:</td>
     *     <td>{@code { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=center>o</td>
     *     <td>{@code { "b", "", ":bnd:f" }}</td></tr>
     * </tbble></blockquote>
     *
     *
     * @pbrbm  regex
     *         the delimiting regulbr expression
     *
     * @return  the brrby of strings computed by splitting this string
     *          bround mbtches of the given regulbr expression
     *
     * @throws  PbtternSyntbxException
     *          if the regulbr expression's syntbx is invblid
     *
     * @see jbvb.util.regex.Pbttern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String[] split(String regex) {
        return split(regex, 0);
    }

    /**
     * Returns b new String composed of copies of the
     * {@code ChbrSequence elements} joined together with b copy of
     * the specified {@code delimiter}.
     *
     * <blockquote>For exbmple,
     * <pre>{@code
     *     String messbge = String.join("-", "Jbvb", "is", "cool");
     *     // messbge returned is: "Jbvb-is-cool"
     * }</pre></blockquote>
     *
     * Note thbt if bn element is null, then {@code "null"} is bdded.
     *
     * @pbrbm  delimiter the delimiter thbt sepbrbtes ebch element
     * @pbrbm  elements the elements to join together.
     *
     * @return b new {@code String} thbt is composed of the {@code elements}
     *         sepbrbted by the {@code delimiter}
     *
     * @throws NullPointerException If {@code delimiter} or {@code elements}
     *         is {@code null}
     *
     * @see jbvb.util.StringJoiner
     * @since 1.8
     */
    public stbtic String join(ChbrSequence delimiter, ChbrSequence... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrbys.strebm overhebd.
        StringJoiner joiner = new StringJoiner(delimiter);
        for (ChbrSequence cs: elements) {
            joiner.bdd(cs);
        }
        return joiner.toString();
    }

    /**
     * Returns b new {@code String} composed of copies of the
     * {@code ChbrSequence elements} joined together with b copy of the
     * specified {@code delimiter}.
     *
     * <blockquote>For exbmple,
     * <pre>{@code
     *     List<String> strings = new LinkedList<>();
     *     strings.bdd("Jbvb");strings.bdd("is");
     *     strings.bdd("cool");
     *     String messbge = String.join(" ", strings);
     *     //messbge returned is: "Jbvb is cool"
     *
     *     Set<String> strings = new LinkedHbshSet<>();
     *     strings.bdd("Jbvb"); strings.bdd("is");
     *     strings.bdd("very"); strings.bdd("cool");
     *     String messbge = String.join("-", strings);
     *     //messbge returned is: "Jbvb-is-very-cool"
     * }</pre></blockquote>
     *
     * Note thbt if bn individubl element is {@code null}, then {@code "null"} is bdded.
     *
     * @pbrbm  delimiter b sequence of chbrbcters thbt is used to sepbrbte ebch
     *         of the {@code elements} in the resulting {@code String}
     * @pbrbm  elements bn {@code Iterbble} thbt will hbve its {@code elements}
     *         joined together.
     *
     * @return b new {@code String} thbt is composed from the {@code elements}
     *         brgument
     *
     * @throws NullPointerException If {@code delimiter} or {@code elements}
     *         is {@code null}
     *
     * @see    #join(ChbrSequence,ChbrSequence...)
     * @see    jbvb.util.StringJoiner
     * @since 1.8
     */
    public stbtic String join(ChbrSequence delimiter,
            Iterbble<? extends ChbrSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        for (ChbrSequence cs: elements) {
            joiner.bdd(cs);
        }
        return joiner.toString();
    }

    /**
     * Converts bll of the chbrbcters in this {@code String} to lower
     * cbse using the rules of the given {@code Locble}.  Cbse mbpping is bbsed
     * on the Unicode Stbndbrd version specified by the {@link jbvb.lbng.Chbrbcter Chbrbcter}
     * clbss. Since cbse mbppings bre not blwbys 1:1 chbr mbppings, the resulting
     * {@code String} mby be b different length thbn the originbl {@code String}.
     * <p>
     * Exbmples of lowercbse  mbppings bre in the following tbble:
     * <tbble border="1" summbry="Lowercbse mbpping exbmples showing lbngubge code of locble, upper cbse, lower cbse, bnd description">
     * <tr>
     *   <th>Lbngubge Code of Locble</th>
     *   <th>Upper Cbse</th>
     *   <th>Lower Cbse</th>
     *   <th>Description</th>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <td>&#92;u0130</td>
     *   <td>&#92;u0069</td>
     *   <td>cbpitbl letter I with dot bbove -&gt; smbll letter i</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <td>&#92;u0049</td>
     *   <td>&#92;u0131</td>
     *   <td>cbpitbl letter I -&gt; smbll letter dotless i </td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>French Fries</td>
     *   <td>french fries</td>
     *   <td>lowercbsed bll chbrs in String</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td><img src="doc-files/cbpiotb.gif" blt="cbpiotb"><img src="doc-files/cbpchi.gif" blt="cbpchi">
     *       <img src="doc-files/cbpthetb.gif" blt="cbpthetb"><img src="doc-files/cbpupsil.gif" blt="cbpupsil">
     *       <img src="doc-files/cbpsigmb.gif" blt="cbpsigmb"></td>
     *   <td><img src="doc-files/iotb.gif" blt="iotb"><img src="doc-files/chi.gif" blt="chi">
     *       <img src="doc-files/thetb.gif" blt="thetb"><img src="doc-files/upsilon.gif" blt="upsilon">
     *       <img src="doc-files/sigmb1.gif" blt="sigmb"></td>
     *   <td>lowercbsed bll chbrs in String</td>
     * </tr>
     * </tbble>
     *
     * @pbrbm locble use the cbse trbnsformbtion rules for this locble
     * @return the {@code String}, converted to lowercbse.
     * @see     jbvb.lbng.String#toLowerCbse()
     * @see     jbvb.lbng.String#toUpperCbse()
     * @see     jbvb.lbng.String#toUpperCbse(Locble)
     * @since   1.1
     */
    public String toLowerCbse(Locble locble) {
        if (locble == null) {
            throw new NullPointerException();
        }
        int first;
        boolebn hbsSurr = fblse;
        finbl int len = vblue.length;

        // Now check if there bre bny chbrbcters thbt need to be chbnged, or bre surrogbte
        for (first = 0 ; first < len; first++) {
            int cp = (int)vblue[first];
            if (Chbrbcter.isSurrogbte((chbr)cp)) {
                hbsSurr = true;
                brebk;
            }
            if (cp != Chbrbcter.toLowerCbse(cp)) {  // no need to check Chbrbcter.ERROR
                brebk;
            }
        }
        if (first == len)
            return this;
        chbr[] result = new chbr[len];
        System.brrbycopy(vblue, 0, result, 0, first);  // Just copy the first few
                                                       // lowerCbse chbrbcters.
        String lbng = locble.getLbngubge();
        if (lbng == "tr" || lbng == "bz" || lbng == "lt") {
            return toLowerCbseEx(result, first, locble, true);
        }
        if (hbsSurr) {
            return toLowerCbseEx(result, first, locble, fblse);
        }
        for (int i = first; i < len; i++) {
            int cp = (int)vblue[i];
            if (cp == '\u03A3' ||                       // GREEK CAPITAL LETTER SIGMA
                Chbrbcter.isSurrogbte((chbr)cp)) {
                return toLowerCbseEx(result, i, locble, fblse);
            }
            if (cp == '\u0130') {                       // LATIN CAPITAL LETTER I WITH DOT ABOVE
                return toLowerCbseEx(result, i, locble, true);
            }
            cp = Chbrbcter.toLowerCbse(cp);
            if (!Chbrbcter.isBmpCodePoint(cp)) {
                return toLowerCbseEx(result, i, locble, fblse);
            }
            result[i] = (chbr)cp;
        }
        return new String(result, true);
    }

    privbte String toLowerCbseEx(chbr[] result, int first, Locble locble, boolebn locbleDependent) {
        int resultOffset = first;
        int srcCount;
        for (int i = first; i < vblue.length; i += srcCount) {
            int srcChbr = (int)vblue[i];
            int lowerChbr;
            chbr[] lowerChbrArrby;
            srcCount = 1;
            if (Chbrbcter.isSurrogbte((chbr)srcChbr)) {
                srcChbr = codePointAt(i);
                srcCount = Chbrbcter.chbrCount(srcChbr);
            }
            if (locbleDependent || srcChbr == '\u03A3') { // GREEK CAPITAL LETTER SIGMA
                lowerChbr = ConditionblSpeciblCbsing.toLowerCbseEx(this, i, locble);
            } else {
                lowerChbr = Chbrbcter.toLowerCbse(srcChbr);
            }
            if (Chbrbcter.isBmpCodePoint(lowerChbr)) {    // Chbrbcter.ERROR is not b bmp
                result[resultOffset++] = (chbr)lowerChbr;
            } else {
                if (lowerChbr == Chbrbcter.ERROR) {
                    lowerChbrArrby = ConditionblSpeciblCbsing.toLowerCbseChbrArrby(this, i, locble);
                } else if (srcCount == 2) {
                    resultOffset += Chbrbcter.toChbrs(lowerChbr, result, resultOffset);
                    continue;
                } else {
                    lowerChbrArrby = Chbrbcter.toChbrs(lowerChbr);
                }
                /* Grow result if needed */
                int mbpLen = lowerChbrArrby.length;
                if (mbpLen > srcCount) {
                    chbr[] result2 = new chbr[result.length + mbpLen - srcCount];
                    System.brrbycopy(result, 0, result2, 0, resultOffset);
                    result = result2;
                }
                for (int x = 0; x < mbpLen; ++x) {
                    result[resultOffset++] = lowerChbrArrby[x];
                }
            }
        }
        return new String(result, 0, resultOffset);
    }

    /**
     * Converts bll of the chbrbcters in this {@code String} to lower
     * cbse using the rules of the defbult locble. This is equivblent to cblling
     * {@code toLowerCbse(Locble.getDefbult())}.
     * <p>
     * <b>Note:</b> This method is locble sensitive, bnd mby produce unexpected
     * results if used for strings thbt bre intended to be interpreted locble
     * independently.
     * Exbmples bre progrbmming lbngubge identifiers, protocol keys, bnd HTML
     * tbgs.
     * For instbnce, {@code "TITLE".toLowerCbse()} in b Turkish locble
     * returns {@code "t\u005Cu0131tle"}, where '\u005Cu0131' is the
     * LATIN SMALL LETTER DOTLESS I chbrbcter.
     * To obtbin correct results for locble insensitive strings, use
     * {@code toLowerCbse(Locble.ROOT)}.
     *
     * @return  the {@code String}, converted to lowercbse.
     * @see     jbvb.lbng.String#toLowerCbse(Locble)
     */
    public String toLowerCbse() {
        return toLowerCbse(Locble.getDefbult());
    }

    /**
     * Converts bll of the chbrbcters in this {@code String} to upper
     * cbse using the rules of the given {@code Locble}. Cbse mbpping is bbsed
     * on the Unicode Stbndbrd version specified by the {@link jbvb.lbng.Chbrbcter Chbrbcter}
     * clbss. Since cbse mbppings bre not blwbys 1:1 chbr mbppings, the resulting
     * {@code String} mby be b different length thbn the originbl {@code String}.
     * <p>
     * Exbmples of locble-sensitive bnd 1:M cbse mbppings bre in the following tbble.
     *
     * <tbble border="1" summbry="Exbmples of locble-sensitive bnd 1:M cbse mbppings. Shows Lbngubge code of locble, lower cbse, upper cbse, bnd description.">
     * <tr>
     *   <th>Lbngubge Code of Locble</th>
     *   <th>Lower Cbse</th>
     *   <th>Upper Cbse</th>
     *   <th>Description</th>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <td>&#92;u0069</td>
     *   <td>&#92;u0130</td>
     *   <td>smbll letter i -&gt; cbpitbl letter I with dot bbove</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <td>&#92;u0131</td>
     *   <td>&#92;u0049</td>
     *   <td>smbll letter dotless i -&gt; cbpitbl letter I</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>&#92;u00df</td>
     *   <td>&#92;u0053 &#92;u0053</td>
     *   <td>smbll letter shbrp s -&gt; two letters: SS</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>Fbhrvergn&uuml;gen</td>
     *   <td>FAHRVERGN&Uuml;GEN</td>
     *   <td></td>
     * </tr>
     * </tbble>
     * @pbrbm locble use the cbse trbnsformbtion rules for this locble
     * @return the {@code String}, converted to uppercbse.
     * @see     jbvb.lbng.String#toUpperCbse()
     * @see     jbvb.lbng.String#toLowerCbse()
     * @see     jbvb.lbng.String#toLowerCbse(Locble)
     * @since   1.1
     */
    public String toUpperCbse(Locble locble) {
        if (locble == null) {
            throw new NullPointerException();
        }
        int first;
        boolebn hbsSurr = fblse;
        finbl int len = vblue.length;

        // Now check if there bre bny chbrbcters thbt need to be chbnged, or bre surrogbte
        for (first = 0 ; first < len; first++ ) {
            int cp = (int)vblue[first];
            if (Chbrbcter.isSurrogbte((chbr)cp)) {
                hbsSurr = true;
                brebk;
            }
            if (cp != Chbrbcter.toUpperCbseEx(cp)) {   // no need to check Chbrbcter.ERROR
                brebk;
            }
        }
        if (first == len) {
            return this;
        }
        chbr[] result = new chbr[len];
        System.brrbycopy(vblue, 0, result, 0, first);  // Just copy the first few
                                                       // upperCbse chbrbcters.
        String lbng = locble.getLbngubge();
        if (lbng == "tr" || lbng == "bz" || lbng == "lt") {
            return toUpperCbseEx(result, first, locble, true);
        }
        if (hbsSurr) {
            return toUpperCbseEx(result, first, locble, fblse);
        }
        for (int i = first; i < len; i++) {
            int cp = (int)vblue[i];
            if (Chbrbcter.isSurrogbte((chbr)cp)) {
                return toUpperCbseEx(result, i, locble, fblse);
            }
            cp = Chbrbcter.toUpperCbseEx(cp);
            if (!Chbrbcter.isBmpCodePoint(cp)) {    // Chbrbcter.ERROR is not bmp
                return toUpperCbseEx(result, i, locble, fblse);
            }
            result[i] = (chbr)cp;
        }
        return new String(result, true);
    }

    privbte String toUpperCbseEx(chbr[] result, int first, Locble locble,
                                 boolebn locbleDependent) {
        int resultOffset = first;
        int srcCount;
        for (int i = first; i < vblue.length; i += srcCount) {
            int srcChbr = (int)vblue[i];
            int upperChbr;
            chbr[] upperChbrArrby;
            srcCount = 1;
            if (Chbrbcter.isSurrogbte((chbr)srcChbr)) {
                srcChbr = codePointAt(i);
                srcCount = Chbrbcter.chbrCount(srcChbr);
            }
            if (locbleDependent) {
                upperChbr = ConditionblSpeciblCbsing.toUpperCbseEx(this, i, locble);
            } else {
                upperChbr = Chbrbcter.toUpperCbseEx(srcChbr);
            }
            if (Chbrbcter.isBmpCodePoint(upperChbr)) {
                result[resultOffset++] = (chbr)upperChbr;
            } else {
                if (upperChbr == Chbrbcter.ERROR) {
                    if (locbleDependent) {
                        upperChbrArrby =
                            ConditionblSpeciblCbsing.toUpperCbseChbrArrby(this, i, locble);
                    } else {
                        upperChbrArrby = Chbrbcter.toUpperCbseChbrArrby(srcChbr);
                    }
                } else if (srcCount == 2) {
                    resultOffset += Chbrbcter.toChbrs(upperChbr, result, resultOffset);
                    continue;
                } else {
                    upperChbrArrby = Chbrbcter.toChbrs(upperChbr);
                }
                /* Grow result if needed */
                int mbpLen = upperChbrArrby.length;
                if (mbpLen > srcCount) {
                    chbr[] result2 = new chbr[result.length + mbpLen - srcCount];
                    System.brrbycopy(result, 0, result2, 0, resultOffset);
                    result = result2;
                 }
                 for (int x = 0; x < mbpLen; ++x) {
                    result[resultOffset++] = upperChbrArrby[x];
                 }
            }
        }
        return new String(result, 0, resultOffset);
    }

    /**
     * Converts bll of the chbrbcters in this {@code String} to upper
     * cbse using the rules of the defbult locble. This method is equivblent to
     * {@code toUpperCbse(Locble.getDefbult())}.
     * <p>
     * <b>Note:</b> This method is locble sensitive, bnd mby produce unexpected
     * results if used for strings thbt bre intended to be interpreted locble
     * independently.
     * Exbmples bre progrbmming lbngubge identifiers, protocol keys, bnd HTML
     * tbgs.
     * For instbnce, {@code "title".toUpperCbse()} in b Turkish locble
     * returns {@code "T\u005Cu0130TLE"}, where '\u005Cu0130' is the
     * LATIN CAPITAL LETTER I WITH DOT ABOVE chbrbcter.
     * To obtbin correct results for locble insensitive strings, use
     * {@code toUpperCbse(Locble.ROOT)}.
     *
     * @return  the {@code String}, converted to uppercbse.
     * @see     jbvb.lbng.String#toUpperCbse(Locble)
     */
    public String toUpperCbse() {
        return toUpperCbse(Locble.getDefbult());
    }

    /**
     * Returns b string whose vblue is this string, with bny lebding bnd trbiling
     * whitespbce removed.
     * <p>
     * If this {@code String} object represents bn empty chbrbcter
     * sequence, or the first bnd lbst chbrbcters of chbrbcter sequence
     * represented by this {@code String} object both hbve codes
     * grebter thbn {@code '\u005Cu0020'} (the spbce chbrbcter), then b
     * reference to this {@code String} object is returned.
     * <p>
     * Otherwise, if there is no chbrbcter with b code grebter thbn
     * {@code '\u005Cu0020'} in the string, then b
     * {@code String} object representing bn empty string is
     * returned.
     * <p>
     * Otherwise, let <i>k</i> be the index of the first chbrbcter in the
     * string whose code is grebter thbn {@code '\u005Cu0020'}, bnd let
     * <i>m</i> be the index of the lbst chbrbcter in the string whose code
     * is grebter thbn {@code '\u005Cu0020'}. A {@code String}
     * object is returned, representing the substring of this string thbt
     * begins with the chbrbcter bt index <i>k</i> bnd ends with the
     * chbrbcter bt index <i>m</i>-thbt is, the result of
     * {@code this.substring(k, m + 1)}.
     * <p>
     * This method mby be used to trim whitespbce (bs defined bbove) from
     * the beginning bnd end of b string.
     *
     * @return  A string whose vblue is this string, with bny lebding bnd trbiling white
     *          spbce removed, or this string if it hbs no lebding or
     *          trbiling white spbce.
     */
    public String trim() {
        int len = vblue.length;
        int st = 0;
        chbr[] vbl = vblue;    /* bvoid getfield opcode */

        while ((st < len) && (vbl[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (vbl[len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < vblue.length)) ? substring(st, len) : this;
    }

    /**
     * This object (which is blrebdy b string!) is itself returned.
     *
     * @return  the string itself.
     */
    public String toString() {
        return this;
    }

    /**
     * Converts this string to b new chbrbcter brrby.
     *
     * @return  b newly bllocbted chbrbcter brrby whose length is the length
     *          of this string bnd whose contents bre initiblized to contbin
     *          the chbrbcter sequence represented by this string.
     */
    public chbr[] toChbrArrby() {
        // Cbnnot use Arrbys.copyOf becbuse of clbss initiblizbtion order issues
        chbr result[] = new chbr[vblue.length];
        System.brrbycopy(vblue, 0, result, 0, vblue.length);
        return result;
    }

    /**
     * Returns b formbtted string using the specified formbt string bnd
     * brguments.
     *
     * <p> The locble blwbys used is the one returned by {@link
     * jbvb.util.Locble#getDefbult() Locble.getDefbult()}.
     *
     * @pbrbm  formbt
     *         A <b href="../util/Formbtter.html#syntbx">formbt string</b>
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         {@code null} brgument depends on the <b
     *         href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion.
     *
     * @return  A formbtted string
     *
     * @see  jbvb.util.Formbtter
     * @since  1.5
     */
    public stbtic String formbt(String formbt, Object... brgs) {
        return new Formbtter().formbt(formbt, brgs).toString();
    }

    /**
     * Returns b formbtted string using the specified locble, formbt string,
     * bnd brguments.
     *
     * @pbrbm  l
     *         The {@linkplbin jbvb.util.Locble locble} to bpply during
     *         formbtting.  If {@code l} is {@code null} then no locblizbtion
     *         is bpplied.
     *
     * @pbrbm  formbt
     *         A <b href="../util/Formbtter.html#syntbx">formbt string</b>
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         {@code null} brgument depends on the
     *         <b href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion
     *
     * @return  A formbtted string
     *
     * @see  jbvb.util.Formbtter
     * @since  1.5
     */
    public stbtic String formbt(Locble l, String formbt, Object... brgs) {
        return new Formbtter(l).formbt(formbt, brgs).toString();
    }

    /**
     * Returns the string representbtion of the {@code Object} brgument.
     *
     * @pbrbm   obj   bn {@code Object}.
     * @return  if the brgument is {@code null}, then b string equbl to
     *          {@code "null"}; otherwise, the vblue of
     *          {@code obj.toString()} is returned.
     * @see     jbvb.lbng.Object#toString()
     */
    public stbtic String vblueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }

    /**
     * Returns the string representbtion of the {@code chbr} brrby
     * brgument. The contents of the chbrbcter brrby bre copied; subsequent
     * modificbtion of the chbrbcter brrby does not bffect the returned
     * string.
     *
     * @pbrbm   dbtb     the chbrbcter brrby.
     * @return  b {@code String} thbt contbins the chbrbcters of the
     *          chbrbcter brrby.
     */
    public stbtic String vblueOf(chbr dbtb[]) {
        return new String(dbtb);
    }

    /**
     * Returns the string representbtion of b specific subbrrby of the
     * {@code chbr} brrby brgument.
     * <p>
     * The {@code offset} brgument is the index of the first
     * chbrbcter of the subbrrby. The {@code count} brgument
     * specifies the length of the subbrrby. The contents of the subbrrby
     * bre copied; subsequent modificbtion of the chbrbcter brrby does not
     * bffect the returned string.
     *
     * @pbrbm   dbtb     the chbrbcter brrby.
     * @pbrbm   offset   initibl offset of the subbrrby.
     * @pbrbm   count    length of the subbrrby.
     * @return  b {@code String} thbt contbins the chbrbcters of the
     *          specified subbrrby of the chbrbcter brrby.
     * @exception IndexOutOfBoundsException if {@code offset} is
     *          negbtive, or {@code count} is negbtive, or
     *          {@code offset+count} is lbrger thbn
     *          {@code dbtb.length}.
     */
    public stbtic String vblueOf(chbr dbtb[], int offset, int count) {
        return new String(dbtb, offset, count);
    }

    /**
     * Equivblent to {@link #vblueOf(chbr[], int, int)}.
     *
     * @pbrbm   dbtb     the chbrbcter brrby.
     * @pbrbm   offset   initibl offset of the subbrrby.
     * @pbrbm   count    length of the subbrrby.
     * @return  b {@code String} thbt contbins the chbrbcters of the
     *          specified subbrrby of the chbrbcter brrby.
     * @exception IndexOutOfBoundsException if {@code offset} is
     *          negbtive, or {@code count} is negbtive, or
     *          {@code offset+count} is lbrger thbn
     *          {@code dbtb.length}.
     */
    public stbtic String copyVblueOf(chbr dbtb[], int offset, int count) {
        return new String(dbtb, offset, count);
    }

    /**
     * Equivblent to {@link #vblueOf(chbr[])}.
     *
     * @pbrbm   dbtb   the chbrbcter brrby.
     * @return  b {@code String} thbt contbins the chbrbcters of the
     *          chbrbcter brrby.
     */
    public stbtic String copyVblueOf(chbr dbtb[]) {
        return new String(dbtb);
    }

    /**
     * Returns the string representbtion of the {@code boolebn} brgument.
     *
     * @pbrbm   b   b {@code boolebn}.
     * @return  if the brgument is {@code true}, b string equbl to
     *          {@code "true"} is returned; otherwise, b string equbl to
     *          {@code "fblse"} is returned.
     */
    public stbtic String vblueOf(boolebn b) {
        return b ? "true" : "fblse";
    }

    /**
     * Returns the string representbtion of the {@code chbr}
     * brgument.
     *
     * @pbrbm   c   b {@code chbr}.
     * @return  b string of length {@code 1} contbining
     *          bs its single chbrbcter the brgument {@code c}.
     */
    public stbtic String vblueOf(chbr c) {
        chbr dbtb[] = {c};
        return new String(dbtb, true);
    }

    /**
     * Returns the string representbtion of the {@code int} brgument.
     * <p>
     * The representbtion is exbctly the one returned by the
     * {@code Integer.toString} method of one brgument.
     *
     * @pbrbm   i   bn {@code int}.
     * @return  b string representbtion of the {@code int} brgument.
     * @see     jbvb.lbng.Integer#toString(int, int)
     */
    public stbtic String vblueOf(int i) {
        return Integer.toString(i);
    }

    /**
     * Returns the string representbtion of the {@code long} brgument.
     * <p>
     * The representbtion is exbctly the one returned by the
     * {@code Long.toString} method of one brgument.
     *
     * @pbrbm   l   b {@code long}.
     * @return  b string representbtion of the {@code long} brgument.
     * @see     jbvb.lbng.Long#toString(long)
     */
    public stbtic String vblueOf(long l) {
        return Long.toString(l);
    }

    /**
     * Returns the string representbtion of the {@code flobt} brgument.
     * <p>
     * The representbtion is exbctly the one returned by the
     * {@code Flobt.toString} method of one brgument.
     *
     * @pbrbm   f   b {@code flobt}.
     * @return  b string representbtion of the {@code flobt} brgument.
     * @see     jbvb.lbng.Flobt#toString(flobt)
     */
    public stbtic String vblueOf(flobt f) {
        return Flobt.toString(f);
    }

    /**
     * Returns the string representbtion of the {@code double} brgument.
     * <p>
     * The representbtion is exbctly the one returned by the
     * {@code Double.toString} method of one brgument.
     *
     * @pbrbm   d   b {@code double}.
     * @return  b  string representbtion of the {@code double} brgument.
     * @see     jbvb.lbng.Double#toString(double)
     */
    public stbtic String vblueOf(double d) {
        return Double.toString(d);
    }

    /**
     * Returns b cbnonicbl representbtion for the string object.
     * <p>
     * A pool of strings, initiblly empty, is mbintbined privbtely by the
     * clbss {@code String}.
     * <p>
     * When the intern method is invoked, if the pool blrebdy contbins b
     * string equbl to this {@code String} object bs determined by
     * the {@link #equbls(Object)} method, then the string from the pool is
     * returned. Otherwise, this {@code String} object is bdded to the
     * pool bnd b reference to this {@code String} object is returned.
     * <p>
     * It follows thbt for bny two strings {@code s} bnd {@code t},
     * {@code s.intern() == t.intern()} is {@code true}
     * if bnd only if {@code s.equbls(t)} is {@code true}.
     * <p>
     * All literbl strings bnd string-vblued constbnt expressions bre
     * interned. String literbls bre defined in section 3.10.5 of the
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @return  b string thbt hbs the sbme contents bs this string, but is
     *          gubrbnteed to be from b pool of unique strings.
     */
    public nbtive String intern();
}
