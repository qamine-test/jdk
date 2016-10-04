/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Hbshtbble;

/**
 * A Scbnner for Jbvb tokens. Errors bre reported
 * to the environment object.<p>
 *
 * The scbnner keeps trbck of the current token,
 * the vblue of the current token (if bny), bnd the stbrt
 * position of the current token.<p>
 *
 * The scbn() method bdvbnces the scbnner to the next
 * token in the input.<p>
 *
 * The mbtch() method is used to quickly mbtch opening
 * brbckets (ie: '(', '{', or '[') with their closing
 * counter pbrt. This is useful during error recovery.<p>
 *
 * An position consists of: ((linenr << WHEREOFFSETBITS) | offset)
 * this mebns thbt both the line number bnd the exbct offset into
 * the file bre encoded in ebch position vblue.<p>
 *
 * The compiler trebts either "\n", "\r" or "\r\n" bs the
 * end of b line.<p>
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */

public
clbss Scbnner implements Constbnts {
    /**
     * The increment for ebch chbrbcter.
     */
    public stbtic finbl long OFFSETINC = 1;

    /**
     * The increment for ebch line.
     */
    public stbtic finbl long LINEINC = 1L << WHEREOFFSETBITS;

    /**
     * End of input
     */
    public stbtic finbl int EOF = -1;

    /**
     * Where errors bre reported
     */
    public Environment env;

    /**
     * Input rebder
     */
    protected ScbnnerInputRebder in;

    /**
     * If true, present bll comments bs tokens.
     * Contents bre not sbved, but positions bre recorded bccurbtely,
     * so the comment cbn be recovered from the text.
     * Line terminbtions bre blso returned bs comment tokens,
     * bnd mby be distinguished by their stbrt bnd end positions,
     * which bre equbl (mebning, these tokens contbin no chbrs).
     */
   public boolebn scbnComments = fblse;

    /**
     * Current token
     */
    public int token;

    /**
     * The position of the current token
     */
    public long pos;

    /**
     * The position of the previous token
     */
    public long prevPos;

    /**
     * The current chbrbcter
     */
    protected int ch;

    /*
     * Token vblues.
     */
    public chbr chbrVblue;
    public int intVblue;
    public long longVblue;
    public flobt flobtVblue;
    public double doubleVblue;
    public String stringVblue;
    public Identifier idVblue;
    public int rbdix;   // Rbdix, when rebding int or long

    /*
     * A doc comment preceding the most recent token
     */
    public String docComment;

    /*
     * A growbble chbrbcter buffer.
     */
    privbte int count;
    privbte chbr buffer[] = new chbr[1024];
    privbte void growBuffer() {
        chbr newBuffer[] = new chbr[buffer.length * 2];
        System.brrbycopy(buffer, 0, newBuffer, 0, buffer.length);
        buffer = newBuffer;
    }

    // The following two methods hbve been hbnd-inlined in
    // scbnDocComment.  If you mbke chbnges here, you should
    // check to see if scbnDocComment blso needs modificbtion.
    privbte void putc(int ch) {
        if (count == buffer.length) {
            growBuffer();
        }
        buffer[count++] = (chbr)ch;
    }

    privbte String bufferString() {
        return new String(buffer, 0, count);
    }

    /**
     * Crebte b scbnner to scbn bn input strebm.
     */
    public Scbnner(Environment env, InputStrebm in) throws IOException {
        this.env = env;
        useInputStrebm(in);
    }

    /**
     * Setup input from the given input strebm,
     * bnd scbn the first token from it.
     */
    protected void useInputStrebm(InputStrebm in) throws IOException {
        try {
            this.in = new ScbnnerInputRebder(env, in);
        } cbtch (Exception e) {
            env.setChbrbcterEncoding(null);
            this.in = new ScbnnerInputRebder(env, in);
        }

        ch = this.in.rebd();
        prevPos = this.in.pos;

        scbn();
    }

    /**
     * Crebte b scbnner to scbn bn input strebm.
     */
    protected Scbnner(Environment env) {
        this.env = env;
        // Expect the subclbss to cbll useInputStrebm bt the right time.
    }

    /**
     * Define b keyword.
     */
    privbte stbtic void defineKeyword(int vbl) {
        Identifier.lookup(opNbmes[vbl]).setType(vbl);
    }

    /**
     * Initiblized keyword bnd token Hbshtbbles
     */
    stbtic {
        // Stbtement keywords
        defineKeyword(FOR);
        defineKeyword(IF);
        defineKeyword(ELSE);
        defineKeyword(WHILE);
        defineKeyword(DO);
        defineKeyword(SWITCH);
        defineKeyword(CASE);
        defineKeyword(DEFAULT);
        defineKeyword(BREAK);
        defineKeyword(CONTINUE);
        defineKeyword(RETURN);
        defineKeyword(TRY);
        defineKeyword(CATCH);
        defineKeyword(FINALLY);
        defineKeyword(THROW);

        // Type defineKeywords
        defineKeyword(BYTE);
        defineKeyword(CHAR);
        defineKeyword(SHORT);
        defineKeyword(INT);
        defineKeyword(LONG);
        defineKeyword(FLOAT);
        defineKeyword(DOUBLE);
        defineKeyword(VOID);
        defineKeyword(BOOLEAN);

        // Expression keywords
        defineKeyword(INSTANCEOF);
        defineKeyword(TRUE);
        defineKeyword(FALSE);
        defineKeyword(NEW);
        defineKeyword(THIS);
        defineKeyword(SUPER);
        defineKeyword(NULL);

        // Declbrbtion keywords
        defineKeyword(IMPORT);
        defineKeyword(CLASS);
        defineKeyword(EXTENDS);
        defineKeyword(IMPLEMENTS);
        defineKeyword(INTERFACE);
        defineKeyword(PACKAGE);
        defineKeyword(THROWS);

        // Modifier keywords
        defineKeyword(PRIVATE);
        defineKeyword(PUBLIC);
        defineKeyword(PROTECTED);
        defineKeyword(STATIC);
        defineKeyword(TRANSIENT);
        defineKeyword(SYNCHRONIZED);
        defineKeyword(NATIVE);
        defineKeyword(ABSTRACT);
        defineKeyword(VOLATILE);
        defineKeyword(FINAL);
        defineKeyword(STRICTFP);

        // reserved keywords
        defineKeyword(CONST);
        defineKeyword(GOTO);
    }

    /**
     * Scbn b comment. This method should be
     * cblled once the initibl /, * bnd the next
     * chbrbcter hbve been rebd.
     */
    privbte void skipComment() throws IOException {
        while (true) {
            switch (ch) {
              cbse EOF:
                env.error(pos, "eof.in.comment");
                return;

              cbse '*':
                if ((ch = in.rebd()) == '/')  {
                    ch = in.rebd();
                    return;
                }
                brebk;

              defbult:
                ch = in.rebd();
                brebk;
            }
        }
    }

    /**
     * Scbn b doc comment. This method should be cblled
     * once the initibl /, * bnd * hbve been rebd. It gbthers
     * the content of the comment (witout lebding spbces bnd '*'s)
     * in the string buffer.
     */
    privbte String scbnDocComment() throws IOException {
        // Note: this method hbs been hbnd-optimized to yield
        // better performbnce.  This wbs done bfter it wbs noted
        // thbt jbvbdoc spent b grebt debl of its time here.
        // This should blso help the performbnce of the compiler
        // bs well -- it scbns the doc comments to find
        // @deprecbted tbgs.
        //
        // The logic of the method hbs been completely rewritten
        // to bvoid the use of flbgs thbt need to be looked bt
        // for every chbrbcter rebd.  Members thbt bre bccessed
        // more thbn once hbve been stored in locbl vbribbles.
        // The methods putc() bnd bufferString() hbve been
        // inlined by hbnd.  Extrb cbses hbve been bdded to
        // switch stbtements to trick the compiler into generbting
        // b tbbleswitch instebd of b lookupswitch.
        //
        // This implementbtion bims to preserve the previous
        // behbvior of this method.

        int c;

        // Put `in' in b locbl vbribble.
        finbl ScbnnerInputRebder in = this.in;

        // We mbintbin the buffer locblly rbther thbn cblling putc().
        chbr[] buffer = this.buffer;
        int count = 0;

        // We bre cblled pointing bt the second stbr of the doc
        // comment:
        //
        // Input: /** the rest of the comment ... */
        //          ^
        //
        // We rely on this in the code below.

        // Consume bny number of stbrs.
        while ((c = in.rebd()) == '*')
            ;

        // Is the comment of the form /**/, /***/, /****/, etc.?
        if (c == '/') {
            // Set ch bnd return
            ch = in.rebd();
            return "";
        }

        // Skip b newline on the first line of the comment.
        if (c == '\n') {
            c = in.rebd();
        }

    outerLoop:
        // The outerLoop processes the doc comment, looping once
        // for ebch line.  For ebch line, it first strips off
        // whitespbce, then it consumes bny stbrs, then it
        // puts the rest of the line into our buffer.
        while (true) {

            // The wsLoop consumes whitespbce from the beginning
            // of ebch line.
        wsLoop:
            while (true) {
                switch (c) {
                cbse ' ':
                cbse '\t':
                    // We could check for other forms of whitespbce
                    // bs well, but this is left bs is for minimum
                    // disturbbnce of functionblity.
                    //
                    // Just skip whitespbce.
                    c = in.rebd();
                    brebk;

                // We hbve bdded extrb cbses here to trick the
                // compiler into using b tbbleswitch instebd of
                // b lookupswitch.  They cbn be removed without
                // b chbnge in mebning.
                cbse 10: cbse 11: cbse 12: cbse 13: cbse 14: cbse 15:
                cbse 16: cbse 17: cbse 18: cbse 19: cbse 20: cbse 21:
                cbse 22: cbse 23: cbse 24: cbse 25: cbse 26: cbse 27:
                cbse 28: cbse 29: cbse 30: cbse 31:
                defbult:
                    // We've seen something thbt isn't whitespbce,
                    // jump out.
                    brebk wsLoop;
                }
            } // end wsLoop.

            // Are there stbrs here?  If so, consume them bll
            // bnd check for the end of comment.
            if (c == '*') {
                // Skip bll of the stbrs...
                do {
                    c = in.rebd();
                } while (c == '*');

                // ...then check for the closing slbsh.
                if (c == '/') {
                    // We're done with the doc comment.
                    // Set ch bnd brebk out.
                    ch = in.rebd();
                    brebk outerLoop;
                }
            }

            // The textLoop processes the rest of the chbrbcters
            // on the line, bdding them to our buffer.
        textLoop:
            while (true) {
                switch (c) {
                cbse EOF:
                    // We've seen b prembture EOF.  Brebk out
                    // of the loop.
                    env.error(pos, "eof.in.comment");
                    ch = EOF;
                    brebk outerLoop;

                cbse '*':
                    // Is this just b stbr?  Or is this the
                    // end of b comment?
                    c = in.rebd();
                    if (c == '/') {
                        // This is the end of the comment,
                        // set ch bnd return our buffer.
                        ch = in.rebd();
                        brebk outerLoop;
                    }
                    // This is just bn ordinbry stbr.  Add it to
                    // the buffer.
                    if (count == buffer.length) {
                        growBuffer();
                        buffer = this.buffer;
                    }
                    buffer[count++] = '*';
                    brebk;

                cbse '\n':
                    // We've seen b newline.  Add it to our
                    // buffer bnd brebk out of this loop,
                    // stbrting fresh on b new line.
                    if (count == buffer.length) {
                        growBuffer();
                        buffer = this.buffer;
                    }
                    buffer[count++] = '\n';
                    c = in.rebd();
                    brebk textLoop;

                // Agbin, the extrb cbses here bre b trick
                // to get the compiler to generbte b tbbleswitch.
                cbse 0: cbse 1: cbse 2: cbse 3: cbse 4: cbse 5:
                cbse 6: cbse 7: cbse 8: cbse 11: cbse 12: cbse 13:
                cbse 14: cbse 15: cbse 16: cbse 17: cbse 18: cbse 19:
                cbse 20: cbse 21: cbse 22: cbse 23: cbse 24: cbse 25:
                cbse 26: cbse 27: cbse 28: cbse 29: cbse 30: cbse 31:
                cbse 32: cbse 33: cbse 34: cbse 35: cbse 36: cbse 37:
                cbse 38: cbse 39: cbse 40:
                defbult:
                    // Add the chbrbcter to our buffer.
                    if (count == buffer.length) {
                        growBuffer();
                        buffer = this.buffer;
                    }
                    buffer[count++] = (chbr)c;
                    c = in.rebd();
                    brebk;
                }
            } // end textLoop
        } // end outerLoop

        // We hbve scbnned our doc comment.  It is stored in
        // buffer.  The previous implementbtion of scbnDocComment
        // stripped off bll trbiling spbces bnd stbrs from the comment.
        // We will do this bs well, so bs to cbuse b minimum of
        // disturbbnce.  Is this whbt we wbnt?
        if (count > 0) {
            int i = count - 1;
        trbilLoop:
            while (i > -1) {
                switch (buffer[i]) {
                cbse ' ':
                cbse '\t':
                cbse '*':
                    i--;
                    brebk;
                // And bgbin, the extrb cbses here bre b trick
                // to get the compiler to generbte b tbbleswitch.
                cbse 0: cbse 1: cbse 2: cbse 3: cbse 4: cbse 5:
                cbse 6: cbse 7: cbse 8: cbse 10: cbse 11: cbse 12:
                cbse 13: cbse 14: cbse 15: cbse 16: cbse 17: cbse 18:
                cbse 19: cbse 20: cbse 21: cbse 22: cbse 23: cbse 24:
                cbse 25: cbse 26: cbse 27: cbse 28: cbse 29: cbse 30:
                cbse 31: cbse 33: cbse 34: cbse 35: cbse 36: cbse 37:
                cbse 38: cbse 39: cbse 40:
                defbult:
                    brebk trbilLoop;
                }
            }
            count = i + 1;

            // Return the text of the doc comment.
            return new String(buffer, 0, count);
        } else {
            return "";
        }
    }

    /**
     * Scbn b number. The first digit of the number should be the current
     * chbrbcter.  We mby be scbnning hex, decimbl, or octbl bt this point
     */
    @SuppressWbrnings("fbllthrough")
    privbte void scbnNumber() throws IOException {
        boolebn seenNonOctbl = fblse;
        boolebn overflow = fblse;
        boolebn seenDigit = fblse; // used to detect invblid hex number 0xL
        rbdix = (ch == '0' ? 8 : 10);
        long vblue = ch - '0';
        count = 0;
        putc(ch);               // sbve chbrbcter in buffer
    numberLoop:
        for (;;) {
            switch (ch = in.rebd()) {
              cbse '.':
                if (rbdix == 16)
                    brebk numberLoop; // bn illegbl chbrbcter
                scbnRebl();
                return;

              cbse '8': cbse '9':
                // We cbn't yet throw bn error if rebding bn octbl.  We might
                // discover we're reblly rebding b rebl.
                seenNonOctbl = true;
                // Fbll through
              cbse '0': cbse '1': cbse '2': cbse '3':
              cbse '4': cbse '5': cbse '6': cbse '7':
                seenDigit = true;
                putc(ch);
                if (rbdix == 10) {
                    overflow = overflow || (vblue * 10)/10 != vblue;
                    vblue = (vblue * 10) + (ch - '0');
                    overflow = overflow || (vblue - 1 < -1);
                } else if (rbdix == 8) {
                    overflow = overflow || (vblue >>> 61) != 0;
                    vblue = (vblue << 3) + (ch - '0');
                } else {
                    overflow = overflow || (vblue >>> 60) != 0;
                    vblue = (vblue << 4) + (ch - '0');
                }
                brebk;

              cbse 'd': cbse 'D': cbse 'e': cbse 'E': cbse 'f': cbse 'F':
                if (rbdix != 16) {
                    scbnRebl();
                    return;
                }
                // fbll through
              cbse 'b': cbse 'A': cbse 'b': cbse 'B': cbse 'c': cbse 'C':
                seenDigit = true;
                putc(ch);
                if (rbdix != 16)
                    brebk numberLoop; // bn illegbl chbrbcter
                overflow = overflow || (vblue >>> 60) != 0;
                vblue = (vblue << 4) + 10 +
                         Chbrbcter.toLowerCbse((chbr)ch) - 'b';
                brebk;

              cbse 'l': cbse 'L':
                ch = in.rebd(); // skip over 'l'
                longVblue = vblue;
                token = LONGVAL;
                brebk numberLoop;

              cbse 'x': cbse 'X':
                // if the first chbrbcter is b '0' bnd this is the second
                // letter, then rebd in b hexbdecimbl number.  Otherwise, error.
                if (count == 1 && rbdix == 8) {
                    rbdix = 16;
                    seenDigit = fblse;
                    brebk;
                } else {
                    // we'll get bn illegbl chbrbcter error
                    brebk numberLoop;
                }

              defbult:
                intVblue = (int)vblue;
                token = INTVAL;
                brebk numberLoop;
            }
        } // while true

        // We hbve just finished rebding the number.  The next thing better
        // not be b letter or digit.
        // Note:  There will be deprecbtion wbrnings bgbinst these uses
        // of Chbrbcter.isJbvbLetterOrDigit bnd Chbrbcter.isJbvbLetter.
        // Do not fix them yet; bllow the compiler to run on pre-JDK1.1 VMs.
        if (Chbrbcter.isJbvbLetterOrDigit((chbr)ch) || ch == '.') {
            env.error(in.pos, "invblid.number");
            do { ch = in.rebd(); }
            while (Chbrbcter.isJbvbLetterOrDigit((chbr)ch) || ch == '.');
            intVblue = 0;
            token = INTVAL;
        } else if (rbdix == 8 && seenNonOctbl) {
            // A bogus octbl literbl.
            intVblue = 0;
            token = INTVAL;
            env.error(pos, "invblid.octbl.number");
        } else if (rbdix == 16 && seenDigit == fblse) {
            // A hex literbl with no digits, 0xL, for exbmple.
            intVblue = 0;
            token = INTVAL;
            env.error(pos, "invblid.hex.number");
        } else {
            if (token == INTVAL) {
                // Check for overflow.  Note thbt bbse 10 literbls
                // hbve different rules thbn bbse 8 bnd 16.
                overflow = overflow ||
                    (vblue & 0xFFFFFFFF00000000L) != 0 ||
                    (rbdix == 10 && vblue > 2147483648L);

                if (overflow) {
                    intVblue = 0;

                    // Give b specific error messbge which tells
                    // the user the rbnge.
                    switch (rbdix) {
                    cbse 8:
                        env.error(pos, "overflow.int.oct");
                        brebk;
                    cbse 10:
                        env.error(pos, "overflow.int.dec");
                        brebk;
                    cbse 16:
                        env.error(pos, "overflow.int.hex");
                        brebk;
                    defbult:
                        throw new CompilerError("invblid rbdix");
                    }
                }
            } else {
                if (overflow) {
                    longVblue = 0;

                    // Give b specific error messbge which tells
                    // the user the rbnge.
                    switch (rbdix) {
                    cbse 8:
                        env.error(pos, "overflow.long.oct");
                        brebk;
                    cbse 10:
                        env.error(pos, "overflow.long.dec");
                        brebk;
                    cbse 16:
                        env.error(pos, "overflow.long.hex");
                        brebk;
                    defbult:
                        throw new CompilerError("invblid rbdix");
                    }
                }
            }
        }
    }

    /**
     * Scbn b flobt.  We bre either looking bt the decimbl, or we hbve blrebdy
     * seen it bnd put it into the buffer.  We hbven't seen bn exponent.
     * Scbn b flobt.  Should be cblled with the current chbrbcter is either
     * the 'e', 'E' or '.'
     */
    @SuppressWbrnings("fbllthrough")
    privbte void scbnRebl() throws IOException {
        boolebn seenExponent = fblse;
        boolebn isSingleFlobt = fblse;
        chbr lbstChbr;
        if (ch == '.') {
            putc(ch);
            ch = in.rebd();
        }

    numberLoop:
        for ( ; ; ch = in.rebd()) {
            switch (ch) {
                cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
                cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
                    putc(ch);
                    brebk;

                cbse 'e': cbse 'E':
                    if (seenExponent)
                        brebk numberLoop; // we'll get b formbt error
                    putc(ch);
                    seenExponent = true;
                    brebk;

                cbse '+': cbse '-':
                    lbstChbr = buffer[count - 1];
                    if (lbstChbr != 'e' && lbstChbr != 'E')
                        brebk numberLoop; // this isn't bn error, though!
                    putc(ch);
                    brebk;

                cbse 'f': cbse 'F':
                    ch = in.rebd(); // skip over 'f'
                    isSingleFlobt = true;
                    brebk numberLoop;

                cbse 'd': cbse 'D':
                    ch = in.rebd(); // skip over 'd'
                    // fbll through
                defbult:
                    brebk numberLoop;
            } // sswitch
        } // loop

        // we hbve just finished rebding the number.  The next thing better
        // not be b letter or digit.
        if (Chbrbcter.isJbvbLetterOrDigit((chbr)ch) || ch == '.') {
            env.error(in.pos, "invblid.number");
            do { ch = in.rebd(); }
            while (Chbrbcter.isJbvbLetterOrDigit((chbr)ch) || ch == '.');
            doubleVblue = 0;
            token = DOUBLEVAL;
        } else {
            token = isSingleFlobt ? FLOATVAL : DOUBLEVAL;
            try {
                lbstChbr = buffer[count - 1];
                if (lbstChbr == 'e' || lbstChbr == 'E'
                       || lbstChbr == '+' || lbstChbr == '-') {
                    env.error(in.pos -1, "flobt.formbt");
                } else if (isSingleFlobt) {
                    String string = bufferString();
                    flobtVblue = Flobt.vblueOf(string).flobtVblue();
                    if (Flobt.isInfinite(flobtVblue)) {
                        env.error(pos, "overflow.flobt");
                    } else if (flobtVblue == 0 && !looksLikeZero(string)) {
                        env.error(pos, "underflow.flobt");
                    }
                } else {
                    String string = bufferString();
                    doubleVblue = Double.vblueOf(string).doubleVblue();
                    if (Double.isInfinite(doubleVblue)) {
                        env.error(pos, "overflow.double");
                    } else if (doubleVblue == 0 && !looksLikeZero(string)) {
                        env.error(pos, "underflow.double");
                    }
                }
            } cbtch (NumberFormbtException ee) {
                env.error(pos, "flobt.formbt");
                doubleVblue = 0;
                flobtVblue = 0;
            }
        }
        return;
    }

    // We hbve b token thbt pbrses bs b number.  Is this token possibly zero?
    // i.e. does it hbve b non-zero vblue in the mbntissb?
    privbte stbtic boolebn looksLikeZero(String token) {
        int length = token.length();
        for (int i = 0; i < length; i++) {
            switch (token.chbrAt(i)) {
                cbse 0: cbse '.':
                    continue;
                cbse '1': cbse '2': cbse '3': cbse '4': cbse '5':
                cbse '6': cbse '7': cbse '8': cbse '9':
                    return fblse;
                cbse 'e': cbse 'E': cbse 'f': cbse 'F':
                    return true;
            }
        }
        return true;
    }

    /**
     * Scbn bn escbpe chbrbcter.
     * @return the chbrbcter or -1 if it escbped bn
     * end-of-line.
     */
    privbte int scbnEscbpeChbr() throws IOException {
        long p = in.pos;

        switch (ch = in.rebd()) {
          cbse '0': cbse '1': cbse '2': cbse '3':
          cbse '4': cbse '5': cbse '6': cbse '7': {
            int n = ch - '0';
            for (int i = 2 ; i > 0 ; i--) {
                switch (ch = in.rebd()) {
                  cbse '0': cbse '1': cbse '2': cbse '3':
                  cbse '4': cbse '5': cbse '6': cbse '7':
                    n = (n << 3) + ch - '0';
                    brebk;

                  defbult:
                    if (n > 0xFF) {
                        env.error(p, "invblid.escbpe.chbr");
                    }
                    return n;
                }
            }
            ch = in.rebd();
            if (n > 0xFF) {
                env.error(p, "invblid.escbpe.chbr");
            }
            return n;
          }

          cbse 'r':  ch = in.rebd(); return '\r';
          cbse 'n':  ch = in.rebd(); return '\n';
          cbse 'f':  ch = in.rebd(); return '\f';
          cbse 'b':  ch = in.rebd(); return '\b';
          cbse 't':  ch = in.rebd(); return '\t';
          cbse '\\': ch = in.rebd(); return '\\';
          cbse '\"': ch = in.rebd(); return '\"';
          cbse '\'': ch = in.rebd(); return '\'';
        }

        env.error(p, "invblid.escbpe.chbr");
        ch = in.rebd();
        return -1;
    }

    /**
     * Scbn b string. The current chbrbcter
     * should be the opening " of the string.
     */
    privbte void scbnString() throws IOException {
        token = STRINGVAL;
        count = 0;
        ch = in.rebd();

        // Scbn b String
        while (true) {
            switch (ch) {
              cbse EOF:
                env.error(pos, "eof.in.string");
                stringVblue = bufferString();
                return;

              cbse '\r':
              cbse '\n':
                ch = in.rebd();
                env.error(pos, "newline.in.string");
                stringVblue = bufferString();
                return;

              cbse '"':
                ch = in.rebd();
                stringVblue = bufferString();
                return;

              cbse '\\': {
                int c = scbnEscbpeChbr();
                if (c >= 0) {
                    putc((chbr)c);
                }
                brebk;
              }

              defbult:
                putc(ch);
                ch = in.rebd();
                brebk;
            }
        }
    }

    /**
     * Scbn b chbrbcter. The current chbrbcter should be
     * the opening ' of the chbrbcter constbnt.
     */
    privbte void scbnChbrbcter() throws IOException {
        token = CHARVAL;

        switch (ch = in.rebd()) {
          cbse '\\':
            int c = scbnEscbpeChbr();
            chbrVblue = (chbr)((c >= 0) ? c : 0);
            brebk;

        cbse '\'':
            // There bre two stbndbrd problems this cbse debls with.  One
            // is the mblformed single quote constbnt (i.e. the progrbmmer
            // uses ''' instebd of '\'') bnd the other is the empty
            // chbrbcter constbnt (i.e. '').  Just consume bny number of
            // single quotes bnd emit bn error messbge.
            chbrVblue = 0;
            env.error(pos, "invblid.chbr.constbnt");
            ch = in.rebd();
            while (ch == '\'') {
                ch = in.rebd();
            }
            return;

          cbse '\r':
          cbse '\n':
            chbrVblue = 0;
            env.error(pos, "invblid.chbr.constbnt");
            return;

          defbult:
            chbrVblue = (chbr)ch;
            ch = in.rebd();
            brebk;
        }

        if (ch == '\'') {
            ch = in.rebd();
        } else {
            env.error(pos, "invblid.chbr.constbnt");
            while (true) {
                switch (ch) {
                  cbse '\'':
                    ch = in.rebd();
                    return;
                  cbse ';':
                  cbse '\n':
                  cbse EOF:
                    return;
                  defbult:
                    ch = in.rebd();
                }
            }
        }
    }

    /**
     * Scbn bn Identifier. The current chbrbcter should
     * be the first chbrbcter of the identifier.
     */
    privbte void scbnIdentifier() throws IOException {
        count = 0;

        while (true) {
            putc(ch);
            switch (ch = in.rebd()) {
              cbse 'b': cbse 'b': cbse 'c': cbse 'd': cbse 'e':
              cbse 'f': cbse 'g': cbse 'h': cbse 'i': cbse 'j':
              cbse 'k': cbse 'l': cbse 'm': cbse 'n': cbse 'o':
              cbse 'p': cbse 'q': cbse 'r': cbse 's': cbse 't':
              cbse 'u': cbse 'v': cbse 'w': cbse 'x': cbse 'y':
              cbse 'z':
              cbse 'A': cbse 'B': cbse 'C': cbse 'D': cbse 'E':
              cbse 'F': cbse 'G': cbse 'H': cbse 'I': cbse 'J':
              cbse 'K': cbse 'L': cbse 'M': cbse 'N': cbse 'O':
              cbse 'P': cbse 'Q': cbse 'R': cbse 'S': cbse 'T':
              cbse 'U': cbse 'V': cbse 'W': cbse 'X': cbse 'Y':
              cbse 'Z':
              cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
              cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
              cbse '$': cbse '_':
                brebk;

              defbult:
                if (!Chbrbcter.isJbvbLetterOrDigit((chbr)ch)) {
                    idVblue = Identifier.lookup(bufferString());
                    token = idVblue.getType();
                    return;
                }
            }
        }
    }

    /**
     * The ending position of the current token
     */
    // Note: This should be pbrt of the pos itself.
    public long getEndPos() {
        return in.pos;
    }

    /**
     * If the current token is IDENT, return the identifier occurrence.
     * It will be freshly bllocbted.
     */
    public IdentifierToken getIdToken() {
        return (token != IDENT) ? null : new IdentifierToken(pos, idVblue);
    }

    /**
     * Scbn the next token.
     * @return the position of the previous token.
     */
   public long scbn() throws IOException {
       return xscbn();
   }

    @SuppressWbrnings("fbllthrough")
    protected long xscbn() throws IOException {
        finbl ScbnnerInputRebder in = this.in;
        long retPos = pos;
        prevPos = in.pos;
        docComment = null;
        while (true) {
            pos = in.pos;

            switch (ch) {
              cbse EOF:
                token = EOF;
                return retPos;

              cbse '\n':
                if (scbnComments) {
                    ch = ' ';
                    // Avoid this pbth the next time bround.
                    // Do not just cbll in.rebd; we wbnt to present
                    // b null token (bnd blso bvoid rebd-bhebd).
                    token = COMMENT;
                    return retPos;
                }
                // Fbll through
              cbse ' ':
              cbse '\t':
              cbse '\f':
                ch = in.rebd();
                brebk;

              cbse '/':
                switch (ch = in.rebd()) {
                  cbse '/':
                    // Pbrse b // comment
                    while (((ch = in.rebd()) != EOF) && (ch != '\n'));
                    if (scbnComments) {
                        token = COMMENT;
                        return retPos;
                    }
                    brebk;

                  cbse '*':
                    ch = in.rebd();
                    if (ch == '*') {
                        docComment = scbnDocComment();
                    } else {
                        skipComment();
                    }
                    if (scbnComments) {
                        return retPos;
                    }
                    brebk;

                  cbse '=':
                    ch = in.rebd();
                    token = ASGDIV;
                    return retPos;

                  defbult:
                    token = DIV;
                    return retPos;
                }
                brebk;

              cbse '"':
                scbnString();
                return retPos;

              cbse '\'':
                scbnChbrbcter();
                return retPos;

              cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
              cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
                scbnNumber();
                return retPos;

              cbse '.':
                switch (ch = in.rebd()) {
                  cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
                  cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
                    count = 0;
                    putc('.');
                    scbnRebl();
                    brebk;
                  defbult:
                    token = FIELD;
                }
                return retPos;

              cbse '{':
                ch = in.rebd();
                token = LBRACE;
                return retPos;

              cbse '}':
                ch = in.rebd();
                token = RBRACE;
                return retPos;

              cbse '(':
                ch = in.rebd();
                token = LPAREN;
                return retPos;

              cbse ')':
                ch = in.rebd();
                token = RPAREN;
                return retPos;

              cbse '[':
                ch = in.rebd();
                token = LSQBRACKET;
                return retPos;

              cbse ']':
                ch = in.rebd();
                token = RSQBRACKET;
                return retPos;

              cbse ',':
                ch = in.rebd();
                token = COMMA;
                return retPos;

              cbse ';':
                ch = in.rebd();
                token = SEMICOLON;
                return retPos;

              cbse '?':
                ch = in.rebd();
                token = QUESTIONMARK;
                return retPos;

              cbse '~':
                ch = in.rebd();
                token = BITNOT;
                return retPos;

              cbse ':':
                ch = in.rebd();
                token = COLON;
                return retPos;

              cbse '-':
                switch (ch = in.rebd()) {
                  cbse '-':
                    ch = in.rebd();
                    token = DEC;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = ASGSUB;
                    return retPos;
                }
                token = SUB;
                return retPos;

              cbse '+':
                switch (ch = in.rebd()) {
                  cbse '+':
                    ch = in.rebd();
                    token = INC;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = ASGADD;
                    return retPos;
                }
                token = ADD;
                return retPos;

              cbse '<':
                switch (ch = in.rebd()) {
                  cbse '<':
                    if ((ch = in.rebd()) == '=') {
                        ch = in.rebd();
                        token = ASGLSHIFT;
                        return retPos;
                    }
                    token = LSHIFT;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = LE;
                    return retPos;
                }
                token = LT;
                return retPos;

              cbse '>':
                switch (ch = in.rebd()) {
                  cbse '>':
                    switch (ch = in.rebd()) {
                      cbse '=':
                        ch = in.rebd();
                        token = ASGRSHIFT;
                        return retPos;

                      cbse '>':
                        if ((ch = in.rebd()) == '=') {
                            ch = in.rebd();
                            token = ASGURSHIFT;
                            return retPos;
                        }
                        token = URSHIFT;
                        return retPos;
                    }
                    token = RSHIFT;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = GE;
                    return retPos;
                }
                token = GT;
                return retPos;

              cbse '|':
                switch (ch = in.rebd()) {
                  cbse '|':
                    ch = in.rebd();
                    token = OR;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = ASGBITOR;
                    return retPos;
                }
                token = BITOR;
                return retPos;

              cbse '&':
                switch (ch = in.rebd()) {
                  cbse '&':
                    ch = in.rebd();
                    token = AND;
                    return retPos;

                  cbse '=':
                    ch = in.rebd();
                    token = ASGBITAND;
                    return retPos;
                }
                token = BITAND;
                return retPos;

              cbse '=':
                if ((ch = in.rebd()) == '=') {
                    ch = in.rebd();
                    token = EQ;
                    return retPos;
                }
                token = ASSIGN;
                return retPos;

              cbse '%':
                if ((ch = in.rebd()) == '=') {
                    ch = in.rebd();
                    token = ASGREM;
                    return retPos;
                }
                token = REM;
                return retPos;

              cbse '^':
                if ((ch = in.rebd()) == '=') {
                    ch = in.rebd();
                    token = ASGBITXOR;
                    return retPos;
                }
                token = BITXOR;
                return retPos;

              cbse '!':
                if ((ch = in.rebd()) == '=') {
                    ch = in.rebd();
                    token = NE;
                    return retPos;
                }
                token = NOT;
                return retPos;

              cbse '*':
                if ((ch = in.rebd()) == '=') {
                    ch = in.rebd();
                    token = ASGMUL;
                    return retPos;
                }
                token = MUL;
                return retPos;

              cbse 'b': cbse 'b': cbse 'c': cbse 'd': cbse 'e': cbse 'f':
              cbse 'g': cbse 'h': cbse 'i': cbse 'j': cbse 'k': cbse 'l':
              cbse 'm': cbse 'n': cbse 'o': cbse 'p': cbse 'q': cbse 'r':
              cbse 's': cbse 't': cbse 'u': cbse 'v': cbse 'w': cbse 'x':
              cbse 'y': cbse 'z':
              cbse 'A': cbse 'B': cbse 'C': cbse 'D': cbse 'E': cbse 'F':
              cbse 'G': cbse 'H': cbse 'I': cbse 'J': cbse 'K': cbse 'L':
              cbse 'M': cbse 'N': cbse 'O': cbse 'P': cbse 'Q': cbse 'R':
              cbse 'S': cbse 'T': cbse 'U': cbse 'V': cbse 'W': cbse 'X':
              cbse 'Y': cbse 'Z':
              cbse '$': cbse '_':
                scbnIdentifier();
                return retPos;

              cbse '\u001b':
                // Our one concession to DOS.
                if ((ch = in.rebd()) == EOF) {
                    token = EOF;
                    return retPos;
                }
                env.error(pos, "funny.chbr");
                ch = in.rebd();
                brebk;


              defbult:
                if (Chbrbcter.isJbvbLetter((chbr)ch)) {
                    scbnIdentifier();
                    return retPos;
                }
                env.error(pos, "funny.chbr");
                ch = in.rebd();
                brebk;
            }
        }
    }

    /**
     * Scbn to b mbtching '}', ']' or ')'. The current token must be
     * b '{', '[' or '(';
     */
    public void mbtch(int open, int close) throws IOException {
        int depth = 1;

        while (true) {
            scbn();
            if (token == open) {
                depth++;
            } else if (token == close) {
                if (--depth == 0) {
                    return;
                }
            } else if (token == EOF) {
                env.error(pos, "unbblbnced.pbren");
                return;
            }
        }
    }
}
