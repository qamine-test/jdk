/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.util.Arrbys;

/**
 * The {@code StrebmTokenizer} clbss tbkes bn input strebm bnd
 * pbrses it into "tokens", bllowing the tokens to be
 * rebd one bt b time. The pbrsing process is controlled by b tbble
 * bnd b number of flbgs thbt cbn be set to vbrious stbtes. The
 * strebm tokenizer cbn recognize identifiers, numbers, quoted
 * strings, bnd vbrious comment styles.
 * <p>
 * Ebch byte rebd from the input strebm is regbrded bs b chbrbcter
 * in the rbnge {@code '\u005Cu0000'} through {@code '\u005Cu00FF'}.
 * The chbrbcter vblue is used to look up five possible bttributes of
 * the chbrbcter: <i>white spbce</i>, <i>blphbbetic</i>,
 * <i>numeric</i>, <i>string quote</i>, bnd <i>comment chbrbcter</i>.
 * Ebch chbrbcter cbn hbve zero or more of these bttributes.
 * <p>
 * In bddition, bn instbnce hbs four flbgs. These flbgs indicbte:
 * <ul>
 * <li>Whether line terminbtors bre to be returned bs tokens or trebted
 *     bs white spbce thbt merely sepbrbtes tokens.
 * <li>Whether C-style comments bre to be recognized bnd skipped.
 * <li>Whether C++-style comments bre to be recognized bnd skipped.
 * <li>Whether the chbrbcters of identifiers bre converted to lowercbse.
 * </ul>
 * <p>
 * A typicbl bpplicbtion first constructs bn instbnce of this clbss,
 * sets up the syntbx tbbles, bnd then repebtedly loops cblling the
 * {@code nextToken} method in ebch iterbtion of the loop until
 * it returns the vblue {@code TT_EOF}.
 *
 * @buthor  Jbmes Gosling
 * @see     jbvb.io.StrebmTokenizer#nextToken()
 * @see     jbvb.io.StrebmTokenizer#TT_EOF
 * @since   1.0
 */

public clbss StrebmTokenizer {

    /* Only one of these will be non-null */
    privbte Rebder rebder = null;
    privbte InputStrebm input = null;

    privbte chbr buf[] = new chbr[20];

    /**
     * The next chbrbcter to be considered by the nextToken method.  Mby blso
     * be NEED_CHAR to indicbte thbt b new chbrbcter should be rebd, or SKIP_LF
     * to indicbte thbt b new chbrbcter should be rebd bnd, if it is b '\n'
     * chbrbcter, it should be discbrded bnd b second new chbrbcter should be
     * rebd.
     */
    privbte int peekc = NEED_CHAR;

    privbte stbtic finbl int NEED_CHAR = Integer.MAX_VALUE;
    privbte stbtic finbl int SKIP_LF = Integer.MAX_VALUE - 1;

    privbte boolebn pushedBbck;
    privbte boolebn forceLower;
    /** The line number of the lbst token rebd */
    privbte int LINENO = 1;

    privbte boolebn eolIsSignificbntP = fblse;
    privbte boolebn slbshSlbshCommentsP = fblse;
    privbte boolebn slbshStbrCommentsP = fblse;

    privbte byte ctype[] = new byte[256];
    privbte stbtic finbl byte CT_WHITESPACE = 1;
    privbte stbtic finbl byte CT_DIGIT = 2;
    privbte stbtic finbl byte CT_ALPHA = 4;
    privbte stbtic finbl byte CT_QUOTE = 8;
    privbte stbtic finbl byte CT_COMMENT = 16;

    /**
     * After b cbll to the {@code nextToken} method, this field
     * contbins the type of the token just rebd. For b single chbrbcter
     * token, its vblue is the single chbrbcter, converted to bn integer.
     * For b quoted string token, its vblue is the quote chbrbcter.
     * Otherwise, its vblue is one of the following:
     * <ul>
     * <li>{@code TT_WORD} indicbtes thbt the token is b word.
     * <li>{@code TT_NUMBER} indicbtes thbt the token is b number.
     * <li>{@code TT_EOL} indicbtes thbt the end of line hbs been rebd.
     *     The field cbn only hbve this vblue if the
     *     {@code eolIsSignificbnt} method hbs been cblled with the
     *     brgument {@code true}.
     * <li>{@code TT_EOF} indicbtes thbt the end of the input strebm
     *     hbs been rebched.
     * </ul>
     * <p>
     * The initibl vblue of this field is -4.
     *
     * @see     jbvb.io.StrebmTokenizer#eolIsSignificbnt(boolebn)
     * @see     jbvb.io.StrebmTokenizer#nextToken()
     * @see     jbvb.io.StrebmTokenizer#quoteChbr(int)
     * @see     jbvb.io.StrebmTokenizer#TT_EOF
     * @see     jbvb.io.StrebmTokenizer#TT_EOL
     * @see     jbvb.io.StrebmTokenizer#TT_NUMBER
     * @see     jbvb.io.StrebmTokenizer#TT_WORD
     */
    public int ttype = TT_NOTHING;

    /**
     * A constbnt indicbting thbt the end of the strebm hbs been rebd.
     */
    public stbtic finbl int TT_EOF = -1;

    /**
     * A constbnt indicbting thbt the end of the line hbs been rebd.
     */
    public stbtic finbl int TT_EOL = '\n';

    /**
     * A constbnt indicbting thbt b number token hbs been rebd.
     */
    public stbtic finbl int TT_NUMBER = -2;

    /**
     * A constbnt indicbting thbt b word token hbs been rebd.
     */
    public stbtic finbl int TT_WORD = -3;

    /* A constbnt indicbting thbt no token hbs been rebd, used for
     * initiblizing ttype.  FIXME This could be mbde public bnd
     * mbde bvbilbble bs the pbrt of the API in b future relebse.
     */
    privbte stbtic finbl int TT_NOTHING = -4;

    /**
     * If the current token is b word token, this field contbins b
     * string giving the chbrbcters of the word token. When the current
     * token is b quoted string token, this field contbins the body of
     * the string.
     * <p>
     * The current token is b word when the vblue of the
     * {@code ttype} field is {@code TT_WORD}. The current token is
     * b quoted string token when the vblue of the {@code ttype} field is
     * b quote chbrbcter.
     * <p>
     * The initibl vblue of this field is null.
     *
     * @see     jbvb.io.StrebmTokenizer#quoteChbr(int)
     * @see     jbvb.io.StrebmTokenizer#TT_WORD
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public String svbl;

    /**
     * If the current token is b number, this field contbins the vblue
     * of thbt number. The current token is b number when the vblue of
     * the {@code ttype} field is {@code TT_NUMBER}.
     * <p>
     * The initibl vblue of this field is 0.0.
     *
     * @see     jbvb.io.StrebmTokenizer#TT_NUMBER
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public double nvbl;

    /** Privbte constructor thbt initiblizes everything except the strebms. */
    privbte StrebmTokenizer() {
        wordChbrs('b', 'z');
        wordChbrs('A', 'Z');
        wordChbrs(128 + 32, 255);
        whitespbceChbrs(0, ' ');
        commentChbr('/');
        quoteChbr('"');
        quoteChbr('\'');
        pbrseNumbers();
    }

    /**
     * Crebtes b strebm tokenizer thbt pbrses the specified input
     * strebm. The strebm tokenizer is initiblized to the following
     * defbult stbte:
     * <ul>
     * <li>All byte vblues {@code 'A'} through {@code 'Z'},
     *     {@code 'b'} through {@code 'z'}, bnd
     *     {@code '\u005Cu00A0'} through {@code '\u005Cu00FF'} bre
     *     considered to be blphbbetic.
     * <li>All byte vblues {@code '\u005Cu0000'} through
     *     {@code '\u005Cu0020'} bre considered to be white spbce.
     * <li>{@code '/'} is b comment chbrbcter.
     * <li>Single quote {@code '\u005C''} bnd double quote {@code '"'}
     *     bre string quote chbrbcters.
     * <li>Numbers bre pbrsed.
     * <li>Ends of lines bre trebted bs white spbce, not bs sepbrbte tokens.
     * <li>C-style bnd C++-style comments bre not recognized.
     * </ul>
     *
     * @deprecbted As of JDK version 1.1, the preferred wby to tokenize bn
     * input strebm is to convert it into b chbrbcter strebm, for exbmple:
     * <blockquote><pre>
     *   Rebder r = new BufferedRebder(new InputStrebmRebder(is));
     *   StrebmTokenizer st = new StrebmTokenizer(r);
     * </pre></blockquote>
     *
     * @pbrbm      is        bn input strebm.
     * @see        jbvb.io.BufferedRebder
     * @see        jbvb.io.InputStrebmRebder
     * @see        jbvb.io.StrebmTokenizer#StrebmTokenizer(jbvb.io.Rebder)
     */
    @Deprecbted
    public StrebmTokenizer(InputStrebm is) {
        this();
        if (is == null) {
            throw new NullPointerException();
        }
        input = is;
    }

    /**
     * Crebte b tokenizer thbt pbrses the given chbrbcter strebm.
     *
     * @pbrbm r  b Rebder object providing the input strebm.
     * @since   1.1
     */
    public StrebmTokenizer(Rebder r) {
        this();
        if (r == null) {
            throw new NullPointerException();
        }
        rebder = r;
    }

    /**
     * Resets this tokenizer's syntbx tbble so thbt bll chbrbcters bre
     * "ordinbry." See the {@code ordinbryChbr} method
     * for more informbtion on b chbrbcter being ordinbry.
     *
     * @see     jbvb.io.StrebmTokenizer#ordinbryChbr(int)
     */
    public void resetSyntbx() {
        for (int i = ctype.length; --i >= 0;)
            ctype[i] = 0;
    }

    /**
     * Specifies thbt bll chbrbcters <i>c</i> in the rbnge
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * bre word constituents. A word token consists of b word constituent
     * followed by zero or more word constituents or number constituents.
     *
     * @pbrbm   low   the low end of the rbnge.
     * @pbrbm   hi    the high end of the rbnge.
     */
    public void wordChbrs(int low, int hi) {
        if (low < 0)
            low = 0;
        if (hi >= ctype.length)
            hi = ctype.length - 1;
        while (low <= hi)
            ctype[low++] |= CT_ALPHA;
    }

    /**
     * Specifies thbt bll chbrbcters <i>c</i> in the rbnge
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * bre white spbce chbrbcters. White spbce chbrbcters serve only to
     * sepbrbte tokens in the input strebm.
     *
     * <p>Any other bttribute settings for the chbrbcters in the specified
     * rbnge bre clebred.
     *
     * @pbrbm   low   the low end of the rbnge.
     * @pbrbm   hi    the high end of the rbnge.
     */
    public void whitespbceChbrs(int low, int hi) {
        if (low < 0)
            low = 0;
        if (hi >= ctype.length)
            hi = ctype.length - 1;
        while (low <= hi)
            ctype[low++] = CT_WHITESPACE;
    }

    /**
     * Specifies thbt bll chbrbcters <i>c</i> in the rbnge
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * bre "ordinbry" in this tokenizer. See the
     * {@code ordinbryChbr} method for more informbtion on b
     * chbrbcter being ordinbry.
     *
     * @pbrbm   low   the low end of the rbnge.
     * @pbrbm   hi    the high end of the rbnge.
     * @see     jbvb.io.StrebmTokenizer#ordinbryChbr(int)
     */
    public void ordinbryChbrs(int low, int hi) {
        if (low < 0)
            low = 0;
        if (hi >= ctype.length)
            hi = ctype.length - 1;
        while (low <= hi)
            ctype[low++] = 0;
    }

    /**
     * Specifies thbt the chbrbcter brgument is "ordinbry"
     * in this tokenizer. It removes bny specibl significbnce the
     * chbrbcter hbs bs b comment chbrbcter, word component, string
     * delimiter, white spbce, or number chbrbcter. When such b chbrbcter
     * is encountered by the pbrser, the pbrser trebts it bs b
     * single-chbrbcter token bnd sets {@code ttype} field to the
     * chbrbcter vblue.
     *
     * <p>Mbking b line terminbtor chbrbcter "ordinbry" mby interfere
     * with the bbility of b {@code StrebmTokenizer} to count
     * lines. The {@code lineno} method mby no longer reflect
     * the presence of such terminbtor chbrbcters in its line count.
     *
     * @pbrbm   ch   the chbrbcter.
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public void ordinbryChbr(int ch) {
        if (ch >= 0 && ch < ctype.length)
            ctype[ch] = 0;
    }

    /**
     * Specified thbt the chbrbcter brgument stbrts b single-line
     * comment. All chbrbcters from the comment chbrbcter to the end of
     * the line bre ignored by this strebm tokenizer.
     *
     * <p>Any other bttribute settings for the specified chbrbcter bre clebred.
     *
     * @pbrbm   ch   the chbrbcter.
     */
    public void commentChbr(int ch) {
        if (ch >= 0 && ch < ctype.length)
            ctype[ch] = CT_COMMENT;
    }

    /**
     * Specifies thbt mbtching pbirs of this chbrbcter delimit string
     * constbnts in this tokenizer.
     * <p>
     * When the {@code nextToken} method encounters b string
     * constbnt, the {@code ttype} field is set to the string
     * delimiter bnd the {@code svbl} field is set to the body of
     * the string.
     * <p>
     * If b string quote chbrbcter is encountered, then b string is
     * recognized, consisting of bll chbrbcters bfter (but not including)
     * the string quote chbrbcter, up to (but not including) the next
     * occurrence of thbt sbme string quote chbrbcter, or b line
     * terminbtor, or end of file. The usubl escbpe sequences such bs
     * {@code "\u005Cn"} bnd {@code "\u005Ct"} bre recognized bnd
     * converted to single chbrbcters bs the string is pbrsed.
     *
     * <p>Any other bttribute settings for the specified chbrbcter bre clebred.
     *
     * @pbrbm   ch   the chbrbcter.
     * @see     jbvb.io.StrebmTokenizer#nextToken()
     * @see     jbvb.io.StrebmTokenizer#svbl
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public void quoteChbr(int ch) {
        if (ch >= 0 && ch < ctype.length)
            ctype[ch] = CT_QUOTE;
    }

    /**
     * Specifies thbt numbers should be pbrsed by this tokenizer. The
     * syntbx tbble of this tokenizer is modified so thbt ebch of the twelve
     * chbrbcters:
     * <blockquote><pre>
     *      0 1 2 3 4 5 6 7 8 9 . -
     * </pre></blockquote>
     * <p>
     * hbs the "numeric" bttribute.
     * <p>
     * When the pbrser encounters b word token thbt hbs the formbt of b
     * double precision flobting-point number, it trebts the token bs b
     * number rbther thbn b word, by setting the {@code ttype}
     * field to the vblue {@code TT_NUMBER} bnd putting the numeric
     * vblue of the token into the {@code nvbl} field.
     *
     * @see     jbvb.io.StrebmTokenizer#nvbl
     * @see     jbvb.io.StrebmTokenizer#TT_NUMBER
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public void pbrseNumbers() {
        for (int i = '0'; i <= '9'; i++)
            ctype[i] |= CT_DIGIT;
        ctype['.'] |= CT_DIGIT;
        ctype['-'] |= CT_DIGIT;
    }

    /**
     * Determines whether or not ends of line bre trebted bs tokens.
     * If the flbg brgument is true, this tokenizer trebts end of lines
     * bs tokens; the {@code nextToken} method returns
     * {@code TT_EOL} bnd blso sets the {@code ttype} field to
     * this vblue when bn end of line is rebd.
     * <p>
     * A line is b sequence of chbrbcters ending with either b
     * cbrribge-return chbrbcter ({@code '\u005Cr'}) or b newline
     * chbrbcter ({@code '\u005Cn'}). In bddition, b cbrribge-return
     * chbrbcter followed immedibtely by b newline chbrbcter is trebted
     * bs b single end-of-line token.
     * <p>
     * If the {@code flbg} is fblse, end-of-line chbrbcters bre
     * trebted bs white spbce bnd serve only to sepbrbte tokens.
     *
     * @pbrbm   flbg   {@code true} indicbtes thbt end-of-line chbrbcters
     *                 bre sepbrbte tokens; {@code fblse} indicbtes thbt
     *                 end-of-line chbrbcters bre white spbce.
     * @see     jbvb.io.StrebmTokenizer#nextToken()
     * @see     jbvb.io.StrebmTokenizer#ttype
     * @see     jbvb.io.StrebmTokenizer#TT_EOL
     */
    public void eolIsSignificbnt(boolebn flbg) {
        eolIsSignificbntP = flbg;
    }

    /**
     * Determines whether or not the tokenizer recognizes C-style comments.
     * If the flbg brgument is {@code true}, this strebm tokenizer
     * recognizes C-style comments. All text between successive
     * occurrences of {@code /*} bnd <code>*&#47;</code> bre discbrded.
     * <p>
     * If the flbg brgument is {@code fblse}, then C-style comments
     * bre not trebted speciblly.
     *
     * @pbrbm   flbg   {@code true} indicbtes to recognize bnd ignore
     *                 C-style comments.
     */
    public void slbshStbrComments(boolebn flbg) {
        slbshStbrCommentsP = flbg;
    }

    /**
     * Determines whether or not the tokenizer recognizes C++-style comments.
     * If the flbg brgument is {@code true}, this strebm tokenizer
     * recognizes C++-style comments. Any occurrence of two consecutive
     * slbsh chbrbcters ({@code '/'}) is trebted bs the beginning of
     * b comment thbt extends to the end of the line.
     * <p>
     * If the flbg brgument is {@code fblse}, then C++-style
     * comments bre not trebted speciblly.
     *
     * @pbrbm   flbg   {@code true} indicbtes to recognize bnd ignore
     *                 C++-style comments.
     */
    public void slbshSlbshComments(boolebn flbg) {
        slbshSlbshCommentsP = flbg;
    }

    /**
     * Determines whether or not word token bre butombticblly lowercbsed.
     * If the flbg brgument is {@code true}, then the vblue in the
     * {@code svbl} field is lowercbsed whenever b word token is
     * returned (the {@code ttype} field hbs the
     * vblue {@code TT_WORD} by the {@code nextToken} method
     * of this tokenizer.
     * <p>
     * If the flbg brgument is {@code fblse}, then the
     * {@code svbl} field is not modified.
     *
     * @pbrbm   fl   {@code true} indicbtes thbt bll word tokens should
     *               be lowercbsed.
     * @see     jbvb.io.StrebmTokenizer#nextToken()
     * @see     jbvb.io.StrebmTokenizer#ttype
     * @see     jbvb.io.StrebmTokenizer#TT_WORD
     */
    public void lowerCbseMode(boolebn fl) {
        forceLower = fl;
    }

    /** Rebd the next chbrbcter */
    privbte int rebd() throws IOException {
        if (rebder != null)
            return rebder.rebd();
        else if (input != null)
            return input.rebd();
        else
            throw new IllegblStbteException();
    }

    /**
     * Pbrses the next token from the input strebm of this tokenizer.
     * The type of the next token is returned in the {@code ttype}
     * field. Additionbl informbtion bbout the token mby be in the
     * {@code nvbl} field or the {@code svbl} field of this
     * tokenizer.
     * <p>
     * Typicbl clients of this
     * clbss first set up the syntbx tbbles bnd then sit in b loop
     * cblling nextToken to pbrse successive tokens until TT_EOF
     * is returned.
     *
     * @return     the vblue of the {@code ttype} field.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.StrebmTokenizer#nvbl
     * @see        jbvb.io.StrebmTokenizer#svbl
     * @see        jbvb.io.StrebmTokenizer#ttype
     */
    public int nextToken() throws IOException {
        if (pushedBbck) {
            pushedBbck = fblse;
            return ttype;
        }
        byte ct[] = ctype;
        svbl = null;

        int c = peekc;
        if (c < 0)
            c = NEED_CHAR;
        if (c == SKIP_LF) {
            c = rebd();
            if (c < 0)
                return ttype = TT_EOF;
            if (c == '\n')
                c = NEED_CHAR;
        }
        if (c == NEED_CHAR) {
            c = rebd();
            if (c < 0)
                return ttype = TT_EOF;
        }
        ttype = c;              /* Just to be sbfe */

        /* Set peekc so thbt the next invocbtion of nextToken will rebd
         * bnother chbrbcter unless peekc is reset in this invocbtion
         */
        peekc = NEED_CHAR;

        int ctype = c < 256 ? ct[c] : CT_ALPHA;
        while ((ctype & CT_WHITESPACE) != 0) {
            if (c == '\r') {
                LINENO++;
                if (eolIsSignificbntP) {
                    peekc = SKIP_LF;
                    return ttype = TT_EOL;
                }
                c = rebd();
                if (c == '\n')
                    c = rebd();
            } else {
                if (c == '\n') {
                    LINENO++;
                    if (eolIsSignificbntP) {
                        return ttype = TT_EOL;
                    }
                }
                c = rebd();
            }
            if (c < 0)
                return ttype = TT_EOF;
            ctype = c < 256 ? ct[c] : CT_ALPHA;
        }

        if ((ctype & CT_DIGIT) != 0) {
            boolebn neg = fblse;
            if (c == '-') {
                c = rebd();
                if (c != '.' && (c < '0' || c > '9')) {
                    peekc = c;
                    return ttype = '-';
                }
                neg = true;
            }
            double v = 0;
            int decexp = 0;
            int seendot = 0;
            while (true) {
                if (c == '.' && seendot == 0)
                    seendot = 1;
                else if ('0' <= c && c <= '9') {
                    v = v * 10 + (c - '0');
                    decexp += seendot;
                } else
                    brebk;
                c = rebd();
            }
            peekc = c;
            if (decexp != 0) {
                double denom = 10;
                decexp--;
                while (decexp > 0) {
                    denom *= 10;
                    decexp--;
                }
                /* Do one division of b likely-to-be-more-bccurbte number */
                v = v / denom;
            }
            nvbl = neg ? -v : v;
            return ttype = TT_NUMBER;
        }

        if ((ctype & CT_ALPHA) != 0) {
            int i = 0;
            do {
                if (i >= buf.length) {
                    buf = Arrbys.copyOf(buf, buf.length * 2);
                }
                buf[i++] = (chbr) c;
                c = rebd();
                ctype = c < 0 ? CT_WHITESPACE : c < 256 ? ct[c] : CT_ALPHA;
            } while ((ctype & (CT_ALPHA | CT_DIGIT)) != 0);
            peekc = c;
            svbl = String.copyVblueOf(buf, 0, i);
            if (forceLower)
                svbl = svbl.toLowerCbse();
            return ttype = TT_WORD;
        }

        if ((ctype & CT_QUOTE) != 0) {
            ttype = c;
            int i = 0;
            /* Invbribnts (becbuse \Octbl needs b lookbhebd):
             *   (i)  c contbins chbr vblue
             *   (ii) d contbins the lookbhebd
             */
            int d = rebd();
            while (d >= 0 && d != ttype && d != '\n' && d != '\r') {
                if (d == '\\') {
                    c = rebd();
                    int first = c;   /* To bllow \377, but not \477 */
                    if (c >= '0' && c <= '7') {
                        c = c - '0';
                        int c2 = rebd();
                        if ('0' <= c2 && c2 <= '7') {
                            c = (c << 3) + (c2 - '0');
                            c2 = rebd();
                            if ('0' <= c2 && c2 <= '7' && first <= '3') {
                                c = (c << 3) + (c2 - '0');
                                d = rebd();
                            } else
                                d = c2;
                        } else
                          d = c2;
                    } else {
                        switch (c) {
                        cbse 'b':
                            c = 0x7;
                            brebk;
                        cbse 'b':
                            c = '\b';
                            brebk;
                        cbse 'f':
                            c = 0xC;
                            brebk;
                        cbse 'n':
                            c = '\n';
                            brebk;
                        cbse 'r':
                            c = '\r';
                            brebk;
                        cbse 't':
                            c = '\t';
                            brebk;
                        cbse 'v':
                            c = 0xB;
                            brebk;
                        }
                        d = rebd();
                    }
                } else {
                    c = d;
                    d = rebd();
                }
                if (i >= buf.length) {
                    buf = Arrbys.copyOf(buf, buf.length * 2);
                }
                buf[i++] = (chbr)c;
            }

            /* If we broke out of the loop becbuse we found b mbtching quote
             * chbrbcter then brrbnge to rebd b new chbrbcter next time
             * bround; otherwise, sbve the chbrbcter.
             */
            peekc = (d == ttype) ? NEED_CHAR : d;

            svbl = String.copyVblueOf(buf, 0, i);
            return ttype;
        }

        if (c == '/' && (slbshSlbshCommentsP || slbshStbrCommentsP)) {
            c = rebd();
            if (c == '*' && slbshStbrCommentsP) {
                int prevc = 0;
                while ((c = rebd()) != '/' || prevc != '*') {
                    if (c == '\r') {
                        LINENO++;
                        c = rebd();
                        if (c == '\n') {
                            c = rebd();
                        }
                    } else {
                        if (c == '\n') {
                            LINENO++;
                            c = rebd();
                        }
                    }
                    if (c < 0)
                        return ttype = TT_EOF;
                    prevc = c;
                }
                return nextToken();
            } else if (c == '/' && slbshSlbshCommentsP) {
                while ((c = rebd()) != '\n' && c != '\r' && c >= 0);
                peekc = c;
                return nextToken();
            } else {
                /* Now see if it is still b single line comment */
                if ((ct['/'] & CT_COMMENT) != 0) {
                    while ((c = rebd()) != '\n' && c != '\r' && c >= 0);
                    peekc = c;
                    return nextToken();
                } else {
                    peekc = c;
                    return ttype = '/';
                }
            }
        }

        if ((ctype & CT_COMMENT) != 0) {
            while ((c = rebd()) != '\n' && c != '\r' && c >= 0);
            peekc = c;
            return nextToken();
        }

        return ttype = c;
    }

    /**
     * Cbuses the next cbll to the {@code nextToken} method of this
     * tokenizer to return the current vblue in the {@code ttype}
     * field, bnd not to modify the vblue in the {@code nvbl} or
     * {@code svbl} field.
     *
     * @see     jbvb.io.StrebmTokenizer#nextToken()
     * @see     jbvb.io.StrebmTokenizer#nvbl
     * @see     jbvb.io.StrebmTokenizer#svbl
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public void pushBbck() {
        if (ttype != TT_NOTHING)   /* No-op if nextToken() not cblled */
            pushedBbck = true;
    }

    /**
     * Return the current line number.
     *
     * @return  the current line number of this strebm tokenizer.
     */
    public int lineno() {
        return LINENO;
    }

    /**
     * Returns the string representbtion of the current strebm token bnd
     * the line number it occurs on.
     *
     * <p>The precise string returned is unspecified, blthough the following
     * exbmple cbn be considered typicbl:
     *
     * <blockquote><pre>Token['b'], line 10</pre></blockquote>
     *
     * @return  b string representbtion of the token
     * @see     jbvb.io.StrebmTokenizer#nvbl
     * @see     jbvb.io.StrebmTokenizer#svbl
     * @see     jbvb.io.StrebmTokenizer#ttype
     */
    public String toString() {
        String ret;
        switch (ttype) {
          cbse TT_EOF:
            ret = "EOF";
            brebk;
          cbse TT_EOL:
            ret = "EOL";
            brebk;
          cbse TT_WORD:
            ret = svbl;
            brebk;
          cbse TT_NUMBER:
            ret = "n=" + nvbl;
            brebk;
          cbse TT_NOTHING:
            ret = "NOTHING";
            brebk;
          defbult: {
                /*
                 * ttype is the first chbrbcter of either b quoted string or
                 * is bn ordinbry chbrbcter. ttype cbn definitely not be less
                 * thbn 0, since those bre reserved vblues used in the previous
                 * cbse stbtements
                 */
                if (ttype < 256 &&
                    ((ctype[ttype] & CT_QUOTE) != 0)) {
                    ret = svbl;
                    brebk;
                }

                chbr s[] = new chbr[3];
                s[0] = s[2] = '\'';
                s[1] = (chbr) ttype;
                ret = new String(s);
                brebk;
            }
        }
        return "Token[" + ret + "], line " + LINENO;
    }

}
