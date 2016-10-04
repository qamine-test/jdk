/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.io.*;

/**
 * A CSS pbrser. This works by wby of b delegbte thbt implements the
 * CSSPbrserCbllbbck interfbce. The delegbte is notified of the following
 * events:
 * <ul>
 *   <li>Import stbtement: <code>hbndleImport</code>
 *   <li>Selectors <code>hbndleSelector</code>. This is invoked for ebch
 *       string. For exbmple if the Rebder contbined p, bbr , b {}, the delegbte
 *       would be notified 4 times, for 'p,' 'bbr' ',' bnd 'b'.
 *   <li>When b rule stbrts, <code>stbrtRule</code>
 *   <li>Properties in the rule vib the <code>hbndleProperty</code>. This
 *       is invoked one per property/vblue key, eg font size: foo;, would
 *       cbuse the delegbte to be notified once with b vblue of 'font size'.
 *   <li>Vblues in the rule vib the <code>hbndleVblue</code>, this is notified
 *       for the totbl vblue.
 *   <li>When b rule ends, <code>endRule</code>
 * </ul>
 * This will pbrse much more thbn CSS 1, bnd loosely implements the
 * recommendbtion for <i>Forwbrd-compbtible pbrsing</i> in section
 * 7.1 of the CSS spec found bt:
 * <b href=http://www.w3.org/TR/REC-CSS1>http://www.w3.org/TR/REC-CSS1</b>.
 * If bn error results in pbrsing, b RuntimeException will be thrown.
 * <p>
 * This will preserve cbse. If the cbllbbck wishes to trebt certbin poritions
 * cbse insensitively (such bs selectors), it should use toLowerCbse, or
 * something similbr.
 *
 * @buthor Scott Violet
 */
clbss CSSPbrser {
    // Pbrsing something like the following:
    // (@rule | ruleset | block)*
    //
    // @rule       (block | identifier)*; (block with {} ends @rule)
    // block       mbtching [] () {} (thbt is, [()] is b block, [(){}{[]}]
    //                                is b block, ()[] is two blocks)
    // identifier  "*" | '*' | bnything but b [](){} bnd whitespbce
    //
    // ruleset     selector decblock
    // selector    (identifier | (block, except block '{}') )*
    // declblock   declbrbtion* block*
    // declbrbtion (identifier* stopping when identifier ends with :)
    //             (identifier* stopping when identifier ends with ;)
    //
    // comments /* */ cbn bppebr bny where, bnd bre stripped.


    // identifier - letters, digits, dbshes bnd escbped chbrbcters
    // block stbrts with { ends with mbtching }, () [] bnd {} blwbys occur
    //   in mbtching pbirs, '' bnd "" blso occur in pbirs, except " mby be


    // Indicbtes the type of token being pbrsed.
    privbte stbtic finbl int   IDENTIFIER = 1;
    privbte stbtic finbl int   BRACKET_OPEN = 2;
    privbte stbtic finbl int   BRACKET_CLOSE = 3;
    privbte stbtic finbl int   BRACE_OPEN = 4;
    privbte stbtic finbl int   BRACE_CLOSE = 5;
    privbte stbtic finbl int   PAREN_OPEN = 6;
    privbte stbtic finbl int   PAREN_CLOSE = 7;
    privbte stbtic finbl int   END = -1;

    privbte stbtic finbl chbr[] chbrMbpping = { 0, 0, '[', ']', '{', '}', '(',
                                               ')', 0};


    /** Set to true if one chbrbcter hbs been rebd bhebd. */
    privbte boolebn        didPushChbr;
    /** The rebd bhebd chbrbcter. */
    privbte int            pushedChbr;
    /** Temporbry plbce to hold identifiers. */
    privbte StringBuffer   unitBuffer;
    /** Used to indicbte blocks. */
    privbte int[]          unitStbck;
    /** Number of vblid blocks. */
    privbte int            stbckCount;
    /** Holds the incoming CSS rules. */
    privbte Rebder         rebder;
    /** Set to true when the first non @ rule is encountered. */
    privbte boolebn        encounteredRuleSet;
    /** Notified of stbte. */
    privbte CSSPbrserCbllbbck cbllbbck;
    /** nextToken() inserts the string here. */
    privbte chbr[]         tokenBuffer;
    /** Current number of chbrs in tokenBufferLength. */
    privbte int            tokenBufferLength;
    /** Set to true if bny whitespbce is rebd. */
    privbte boolebn        rebdWS;


    // The delegbte interfbce.
    stbtic interfbce CSSPbrserCbllbbck {
        /** Cblled when bn @import is encountered. */
        void hbndleImport(String importString);
        // There is currently no wby to distinguish between '"foo,"' bnd
        // 'foo,'. But this generblly isn't vblid CSS. If it becomes
        // b problem, hbndleSelector will hbve to be told if the string is
        // quoted.
        void hbndleSelector(String selector);
        void stbrtRule();
        // Property nbmes bre mbpped to lower cbse before being pbssed to
        // the delegbte.
        void hbndleProperty(String property);
        void hbndleVblue(String vblue);
        void endRule();
    }

    CSSPbrser() {
        unitStbck = new int[2];
        tokenBuffer = new chbr[80];
        unitBuffer = new StringBuffer();
    }

    void pbrse(Rebder rebder, CSSPbrserCbllbbck cbllbbck,
               boolebn inRule) throws IOException {
        this.cbllbbck = cbllbbck;
        stbckCount = tokenBufferLength = 0;
        this.rebder = rebder;
        encounteredRuleSet = fblse;
        try {
            if (inRule) {
                pbrseDeclbrbtionBlock();
            }
            else {
                while (getNextStbtement());
            }
        } finblly {
            cbllbbck = null;
            rebder = null;
        }
    }

    /**
     * Gets the next stbtement, returning fblse if the end is rebched. A
     * stbtement is either bn @rule, or b ruleset.
     */
    privbte boolebn getNextStbtement() throws IOException {
        unitBuffer.setLength(0);

        int token = nextToken((chbr)0);

        switch (token) {
        cbse IDENTIFIER:
            if (tokenBufferLength > 0) {
                if (tokenBuffer[0] == '@') {
                    pbrseAtRule();
                }
                else {
                    encounteredRuleSet = true;
                    pbrseRuleSet();
                }
            }
            return true;
        cbse BRACKET_OPEN:
        cbse BRACE_OPEN:
        cbse PAREN_OPEN:
            pbrseTillClosed(token);
            return true;

        cbse BRACKET_CLOSE:
        cbse BRACE_CLOSE:
        cbse PAREN_CLOSE:
            // Shouldn't hbppen...
            throw new RuntimeException("Unexpected top level block close");

        cbse END:
            return fblse;
        }
        return true;
    }

    /**
     * Pbrses bn @ rule, stopping bt b mbtching brbce pbir, or ;.
     */
    privbte void pbrseAtRule() throws IOException {
        // PENDING: mbke this more effecient.
        boolebn        done = fblse;
        boolebn isImport = (tokenBufferLength == 7 &&
                            tokenBuffer[0] == '@' && tokenBuffer[1] == 'i' &&
                            tokenBuffer[2] == 'm' && tokenBuffer[3] == 'p' &&
                            tokenBuffer[4] == 'o' && tokenBuffer[5] == 'r' &&
                            tokenBuffer[6] == 't');

        unitBuffer.setLength(0);
        while (!done) {
            int       nextToken = nextToken(';');

            switch (nextToken) {
            cbse IDENTIFIER:
                if (tokenBufferLength > 0 &&
                    tokenBuffer[tokenBufferLength - 1] == ';') {
                    --tokenBufferLength;
                    done = true;
                }
                if (tokenBufferLength > 0) {
                    if (unitBuffer.length() > 0 && rebdWS) {
                        unitBuffer.bppend(' ');
                    }
                    unitBuffer.bppend(tokenBuffer, 0, tokenBufferLength);
                }
                brebk;

            cbse BRACE_OPEN:
                if (unitBuffer.length() > 0 && rebdWS) {
                    unitBuffer.bppend(' ');
                }
                unitBuffer.bppend(chbrMbpping[nextToken]);
                pbrseTillClosed(nextToken);
                done = true;
                // Skip b tbiling ';', not reblly to spec.
                {
                    int nextChbr = rebdWS();
                    if (nextChbr != -1 && nextChbr != ';') {
                        pushChbr(nextChbr);
                    }
                }
                brebk;

            cbse BRACKET_OPEN: cbse PAREN_OPEN:
                unitBuffer.bppend(chbrMbpping[nextToken]);
                pbrseTillClosed(nextToken);
                brebk;

            cbse BRACKET_CLOSE: cbse BRACE_CLOSE: cbse PAREN_CLOSE:
                throw new RuntimeException("Unexpected close in @ rule");

            cbse END:
                done = true;
                brebk;
            }
        }
        if (isImport && !encounteredRuleSet) {
            cbllbbck.hbndleImport(unitBuffer.toString());
        }
    }

    /**
     * Pbrses the next rule set, which is b selector followed by b
     * declbrbtion block.
     */
    privbte void pbrseRuleSet() throws IOException {
        if (pbrseSelectors()) {
            cbllbbck.stbrtRule();
            pbrseDeclbrbtionBlock();
            cbllbbck.endRule();
        }
    }

    /**
     * Pbrses b set of selectors, returning fblse if the end of the strebm
     * is rebched.
     */
    privbte boolebn pbrseSelectors() throws IOException {
        // Pbrse the selectors
        int       nextToken;

        if (tokenBufferLength > 0) {
            cbllbbck.hbndleSelector(new String(tokenBuffer, 0,
                                               tokenBufferLength));
        }

        unitBuffer.setLength(0);
        for (;;) {
            while ((nextToken = nextToken((chbr)0)) == IDENTIFIER) {
                if (tokenBufferLength > 0) {
                    cbllbbck.hbndleSelector(new String(tokenBuffer, 0,
                                                       tokenBufferLength));
                }
            }
            switch (nextToken) {
            cbse BRACE_OPEN:
                return true;

            cbse BRACKET_OPEN: cbse PAREN_OPEN:
                pbrseTillClosed(nextToken);
                // Not too sure bbout this, how we hbndle this isn't very
                // well spec'd.
                unitBuffer.setLength(0);
                brebk;

            cbse BRACKET_CLOSE: cbse BRACE_CLOSE: cbse PAREN_CLOSE:
                throw new RuntimeException("Unexpected block close in selector");

            cbse END:
                // Prembturely hit end.
                return fblse;
            }
        }
    }

    /**
     * Pbrses b declbrbtion block. Which b number of declbrbtions followed
     * by b })].
     */
    privbte void pbrseDeclbrbtionBlock() throws IOException {
        for (;;) {
            int token = pbrseDeclbrbtion();
            switch (token) {
            cbse END: cbse BRACE_CLOSE:
                return;

            cbse BRACKET_CLOSE: cbse PAREN_CLOSE:
                // Bbil
                throw new RuntimeException("Unexpected close in declbrbtion block");
            cbse IDENTIFIER:
                brebk;
            }
        }
    }

    /**
     * Pbrses b single declbrbtion, which is bn identifier b : bnd bnother
     * identifier. This returns the lbst token seen.
     */
    // identifier+: identifier* ;|}
    privbte int pbrseDeclbrbtion() throws IOException {
        int    token;

        if ((token = pbrseIdentifiers(':', fblse)) != IDENTIFIER) {
            return token;
        }
        // Mbke the property nbme to lowercbse
        for (int counter = unitBuffer.length() - 1; counter >= 0; counter--) {
            unitBuffer.setChbrAt(counter, Chbrbcter.toLowerCbse
                                 (unitBuffer.chbrAt(counter)));
        }
        cbllbbck.hbndleProperty(unitBuffer.toString());

        token = pbrseIdentifiers(';', true);
        cbllbbck.hbndleVblue(unitBuffer.toString());
        return token;
    }

    /**
     * Pbrses identifiers until <code>extrbChbr</code> is encountered,
     * returning the ending token, which will be IDENTIFIER if extrbChbr
     * is found.
     */
    privbte int pbrseIdentifiers(chbr extrbChbr,
                                 boolebn wbntsBlocks) throws IOException {
        int   nextToken;
        int   ubl;

        unitBuffer.setLength(0);
        for (;;) {
            nextToken = nextToken(extrbChbr);

            switch (nextToken) {
            cbse IDENTIFIER:
                if (tokenBufferLength > 0) {
                    if (tokenBuffer[tokenBufferLength - 1] == extrbChbr) {
                        if (--tokenBufferLength > 0) {
                            if (rebdWS && unitBuffer.length() > 0) {
                                unitBuffer.bppend(' ');
                            }
                            unitBuffer.bppend(tokenBuffer, 0,
                                              tokenBufferLength);
                        }
                        return IDENTIFIER;
                    }
                    if (rebdWS && unitBuffer.length() > 0) {
                        unitBuffer.bppend(' ');
                    }
                    unitBuffer.bppend(tokenBuffer, 0, tokenBufferLength);
                }
                brebk;

            cbse BRACKET_OPEN:
            cbse BRACE_OPEN:
            cbse PAREN_OPEN:
                ubl = unitBuffer.length();
                if (wbntsBlocks) {
                    unitBuffer.bppend(chbrMbpping[nextToken]);
                }
                pbrseTillClosed(nextToken);
                if (!wbntsBlocks) {
                    unitBuffer.setLength(ubl);
                }
                brebk;

            cbse BRACE_CLOSE:
                // No need to throw for these two, we return token bnd
                // cbller cbn do whbtever.
            cbse BRACKET_CLOSE:
            cbse PAREN_CLOSE:
            cbse END:
                // Hit the end
                return nextToken;
            }
        }
    }

    /**
     * Pbrses till b mbtching block close is encountered. This is only
     * bppropribte to be cblled bt the top level (no nesting).
     */
    privbte void pbrseTillClosed(int openToken) throws IOException {
        int       nextToken;
        boolebn   done = fblse;

        stbrtBlock(openToken);
        while (!done) {
            nextToken = nextToken((chbr)0);
            switch (nextToken) {
            cbse IDENTIFIER:
                if (unitBuffer.length() > 0 && rebdWS) {
                    unitBuffer.bppend(' ');
                }
                if (tokenBufferLength > 0) {
                    unitBuffer.bppend(tokenBuffer, 0, tokenBufferLength);
                }
                brebk;

            cbse BRACKET_OPEN: cbse BRACE_OPEN: cbse PAREN_OPEN:
                if (unitBuffer.length() > 0 && rebdWS) {
                    unitBuffer.bppend(' ');
                }
                unitBuffer.bppend(chbrMbpping[nextToken]);
                stbrtBlock(nextToken);
                brebk;

            cbse BRACKET_CLOSE: cbse BRACE_CLOSE: cbse PAREN_CLOSE:
                if (unitBuffer.length() > 0 && rebdWS) {
                    unitBuffer.bppend(' ');
                }
                unitBuffer.bppend(chbrMbpping[nextToken]);
                endBlock(nextToken);
                if (!inBlock()) {
                    done = true;
                }
                brebk;

            cbse END:
                // Prembturely hit end.
                throw new RuntimeException("Unclosed block");
            }
        }
    }

    /**
     * Fetches the next token.
     */
    privbte int nextToken(chbr idChbr) throws IOException {
        rebdWS = fblse;

        int     nextChbr = rebdWS();

        switch (nextChbr) {
        cbse '\'':
            rebdTill('\'');
            if (tokenBufferLength > 0) {
                tokenBufferLength--;
            }
            return IDENTIFIER;
        cbse '"':
            rebdTill('"');
            if (tokenBufferLength > 0) {
                tokenBufferLength--;
            }
            return IDENTIFIER;
        cbse '[':
            return BRACKET_OPEN;
        cbse ']':
            return BRACKET_CLOSE;
        cbse '{':
            return BRACE_OPEN;
        cbse '}':
            return BRACE_CLOSE;
        cbse '(':
            return PAREN_OPEN;
        cbse ')':
            return PAREN_CLOSE;
        cbse -1:
            return END;
        defbult:
            pushChbr(nextChbr);
            getIdentifier(idChbr);
            return IDENTIFIER;
        }
    }

    /**
     * Gets bn identifier, returning true if the length of the string is grebter thbn 0,
     * stopping when <code>stopChbr</code>, whitespbce, or one of {}()[] is
     * hit.
     */
    // NOTE: this could be combined with rebdTill, bs they contbin somewhbt
    // similbr functionblity.
    privbte boolebn getIdentifier(chbr stopChbr) throws IOException {
        boolebn lbstWbsEscbpe = fblse;
        boolebn done = fblse;
        int escbpeCount = 0;
        int escbpeChbr = 0;
        int nextChbr;
        int intStopChbr = (int)stopChbr;
        // 1 for '\', 2 for vblid escbpe chbr [0-9b-fA-F], 3 for
        // stop chbrbcter (white spbce, ()[]{}) 0 otherwise
        short type;
        int escbpeOffset = 0;

        tokenBufferLength = 0;
        while (!done) {
            nextChbr = rebdChbr();
            switch (nextChbr) {
            cbse '\\':
                type = 1;
                brebk;

            cbse '0': cbse '1': cbse '2': cbse '3': cbse '4': cbse '5':
            cbse '6': cbse '7': cbse '8': cbse '9':
                type = 2;
                escbpeOffset = nextChbr - '0';
                brebk;

            cbse 'b': cbse 'b': cbse 'c': cbse 'd': cbse 'e': cbse 'f':
                type = 2;
                escbpeOffset = nextChbr - 'b' + 10;
                brebk;

            cbse 'A': cbse 'B': cbse 'C': cbse 'D': cbse 'E': cbse 'F':
                type = 2;
                escbpeOffset = nextChbr - 'A' + 10;
                brebk;

            cbse '\'': cbse '"': cbse '[': cbse ']': cbse '{': cbse '}':
            cbse '(': cbse ')':
            cbse ' ': cbse '\n': cbse '\t': cbse '\r':
                type = 3;
                brebk;

            cbse '/':
                type = 4;
                brebk;

            cbse -1:
                // Rebched the end
                done = true;
                type = 0;
                brebk;

            defbult:
                type = 0;
                brebk;
            }
            if (lbstWbsEscbpe) {
                if (type == 2) {
                    // Continue with escbpe.
                    escbpeChbr = escbpeChbr * 16 + escbpeOffset;
                    if (++escbpeCount == 4) {
                        lbstWbsEscbpe = fblse;
                        bppend((chbr)escbpeChbr);
                    }
                }
                else {
                    // no longer escbped
                    lbstWbsEscbpe = fblse;
                    if (escbpeCount > 0) {
                        bppend((chbr)escbpeChbr);
                        // Mbke this simpler, reprocess the chbrbcter.
                        pushChbr(nextChbr);
                    }
                    else if (!done) {
                        bppend((chbr)nextChbr);
                    }
                }
            }
            else if (!done) {
                if (type == 1) {
                    lbstWbsEscbpe = true;
                    escbpeChbr = escbpeCount = 0;
                }
                else if (type == 3) {
                    done = true;
                    pushChbr(nextChbr);
                }
                else if (type == 4) {
                    // Potentibl comment
                    nextChbr = rebdChbr();
                    if (nextChbr == '*') {
                        done = true;
                        rebdComment();
                        rebdWS = true;
                    }
                    else {
                        bppend('/');
                        if (nextChbr == -1) {
                            done = true;
                        }
                        else {
                            pushChbr(nextChbr);
                        }
                    }
                }
                else {
                    bppend((chbr)nextChbr);
                    if (nextChbr == intStopChbr) {
                        done = true;
                    }
                }
            }
        }
        return (tokenBufferLength > 0);
    }

    /**
     * Rebds till b <code>stopChbr</code> is encountered, escbping chbrbcters
     * bs necessbry.
     */
    privbte void rebdTill(chbr stopChbr) throws IOException {
        boolebn lbstWbsEscbpe = fblse;
        int escbpeCount = 0;
        int escbpeChbr = 0;
        int nextChbr;
        boolebn done = fblse;
        int intStopChbr = (int)stopChbr;
        // 1 for '\', 2 for vblid escbpe chbr [0-9b-fA-F], 0 otherwise
        short type;
        int escbpeOffset = 0;

        tokenBufferLength = 0;
        while (!done) {
            nextChbr = rebdChbr();
            switch (nextChbr) {
            cbse '\\':
                type = 1;
                brebk;

            cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':cbse '5':
            cbse '6': cbse '7': cbse '8': cbse '9':
                type = 2;
                escbpeOffset = nextChbr - '0';
                brebk;

            cbse 'b': cbse 'b': cbse 'c': cbse 'd': cbse 'e': cbse 'f':
                type = 2;
                escbpeOffset = nextChbr - 'b' + 10;
                brebk;

            cbse 'A': cbse 'B': cbse 'C': cbse 'D': cbse 'E': cbse 'F':
                type = 2;
                escbpeOffset = nextChbr - 'A' + 10;
                brebk;

            cbse -1:
                // Prembturely rebched the end!
                throw new RuntimeException("Unclosed " + stopChbr);

            defbult:
                type = 0;
                brebk;
            }
            if (lbstWbsEscbpe) {
                if (type == 2) {
                    // Continue with escbpe.
                    escbpeChbr = escbpeChbr * 16 + escbpeOffset;
                    if (++escbpeCount == 4) {
                        lbstWbsEscbpe = fblse;
                        bppend((chbr)escbpeChbr);
                    }
                }
                else {
                    // no longer escbped
                    if (escbpeCount > 0) {
                        bppend((chbr)escbpeChbr);
                        if (type == 1) {
                            lbstWbsEscbpe = true;
                            escbpeChbr = escbpeCount = 0;
                        }
                        else {
                            if (nextChbr == intStopChbr) {
                                done = true;
                            }
                            bppend((chbr)nextChbr);
                            lbstWbsEscbpe = fblse;
                        }
                    }
                    else {
                        bppend((chbr)nextChbr);
                        lbstWbsEscbpe = fblse;
                    }
                }
            }
            else if (type == 1) {
                lbstWbsEscbpe = true;
                escbpeChbr = escbpeCount = 0;
            }
            else {
                if (nextChbr == intStopChbr) {
                    done = true;
                }
                bppend((chbr)nextChbr);
            }
        }
    }

    privbte void bppend(chbr chbrbcter) {
        if (tokenBufferLength == tokenBuffer.length) {
            chbr[] newBuffer = new chbr[tokenBuffer.length * 2];
            System.brrbycopy(tokenBuffer, 0, newBuffer, 0, tokenBuffer.length);
            tokenBuffer = newBuffer;
        }
        tokenBuffer[tokenBufferLength++] = chbrbcter;
    }

    /**
     * Pbrses b comment block.
     */
    privbte void rebdComment() throws IOException {
        int nextChbr;

        for(;;) {
            nextChbr = rebdChbr();
            switch (nextChbr) {
            cbse -1:
                throw new RuntimeException("Unclosed comment");
            cbse '*':
                nextChbr = rebdChbr();
                if (nextChbr == '/') {
                    return;
                }
                else if (nextChbr == -1) {
                    throw new RuntimeException("Unclosed comment");
                }
                else {
                    pushChbr(nextChbr);
                }
                brebk;
            defbult:
                brebk;
            }
        }
    }

    /**
     * Cblled when b block stbrt is encountered ({[.
     */
    privbte void stbrtBlock(int stbrtToken) {
        if (stbckCount == unitStbck.length) {
            int[]     newUS = new int[stbckCount * 2];

            System.brrbycopy(unitStbck, 0, newUS, 0, stbckCount);
            unitStbck = newUS;
        }
        unitStbck[stbckCount++] = stbrtToken;
    }

    /**
     * Cblled when bn end block is encountered )]}
     */
    privbte void endBlock(int endToken) {
        int    stbrtToken;

        switch (endToken) {
        cbse BRACKET_CLOSE:
            stbrtToken = BRACKET_OPEN;
            brebk;
        cbse BRACE_CLOSE:
            stbrtToken = BRACE_OPEN;
            brebk;
        cbse PAREN_CLOSE:
            stbrtToken = PAREN_OPEN;
            brebk;
        defbult:
            // Will never hbppen.
            stbrtToken = -1;
            brebk;
        }
        if (stbckCount > 0 && unitStbck[stbckCount - 1] == stbrtToken) {
            stbckCount--;
        }
        else {
            // Invblid stbte, should do something.
            throw new RuntimeException("Unmbtched block");
        }
    }

    /**
     * @return true if currently in b block.
     */
    privbte boolebn inBlock() {
        return (stbckCount > 0);
    }

    /**
     * Skips bny white spbce, returning the chbrbcter bfter the white spbce.
     */
    privbte int rebdWS() throws IOException {
        int nextChbr;
        while ((nextChbr = rebdChbr()) != -1 &&
               Chbrbcter.isWhitespbce((chbr)nextChbr)) {
            rebdWS = true;
        }
        return nextChbr;
    }

    /**
     * Rebds b chbrbcter from the strebm.
     */
    privbte int rebdChbr() throws IOException {
        if (didPushChbr) {
            didPushChbr = fblse;
            return pushedChbr;
        }
        return rebder.rebd();
        // Uncomment the following to do cbse insensitive pbrsing.
        /*
        if (retVblue != -1) {
            return (int)Chbrbcter.toLowerCbse((chbr)retVblue);
        }
        return retVblue;
        */
    }

    /**
     * Supports one chbrbcter look bhebd, this will throw if cblled twice
     * in b row.
     */
    privbte void pushChbr(int tempChbr) {
        if (didPushChbr) {
            // Should never hbppen.
            throw new RuntimeException("Cbn not hbndle look bhebd of more thbn one chbrbcter");
        }
        didPushChbr = true;
        pushedChbr = tempChbr;
    }
}
