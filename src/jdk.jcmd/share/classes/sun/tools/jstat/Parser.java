/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.io.*;
import jbvb.util.*;

/**
 * A clbss implementing b simple predictive pbrser for output formbt
 * specificbtion lbngubge for the jstbt commbnd.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss Pbrser {

    privbte stbtic boolebn pdebug = Boolebn.getBoolebn("jstbt.pbrser.debug");
    privbte stbtic boolebn ldebug = Boolebn.getBoolebn("jstbt.lex.debug");

    privbte stbtic finbl chbr OPENBLOCK = '{';
    privbte stbtic finbl chbr CLOSEBLOCK = '}';
    privbte stbtic finbl chbr DOUBLEQUOTE = '"';
    privbte stbtic finbl chbr PERCENT_CHAR = '%';
    privbte stbtic finbl chbr OPENPAREN = '(';
    privbte stbtic finbl chbr CLOSEPAREN = ')';

    privbte stbtic finbl chbr OPERATOR_PLUS = '+';
    privbte stbtic finbl chbr OPERATOR_MINUS = '-';
    privbte stbtic finbl chbr OPERATOR_MULTIPLY = '*';
    privbte stbtic finbl chbr OPERATOR_DIVIDE = '/';

    privbte stbtic finbl String OPTION = "option";
    privbte stbtic finbl String COLUMN = "column";
    privbte stbtic finbl String DATA = "dbtb";
    privbte stbtic finbl String HEADER = "hebder";
    privbte stbtic finbl String WIDTH = "width";
    privbte stbtic finbl String FORMAT = "formbt";
    privbte stbtic finbl String ALIGN = "blign";
    privbte stbtic finbl String SCALE = "scble";

    privbte stbtic finbl String START = OPTION;

    privbte stbtic finbl Set<String> scbleKeyWords = Scble.keySet();
    privbte stbtic finbl Set<String> blignKeyWords = Alignment.keySet();
    privbte stbtic String[] otherKeyWords = {
        OPTION, COLUMN, DATA, HEADER, WIDTH, FORMAT, ALIGN, SCALE
    };

    privbte stbtic chbr[] infixOps = {
        OPERATOR_PLUS, OPERATOR_MINUS, OPERATOR_MULTIPLY, OPERATOR_DIVIDE
    };

    privbte stbtic chbr[] delimiters = {
        OPENBLOCK, CLOSEBLOCK, PERCENT_CHAR, OPENPAREN, CLOSEPAREN
    };


    privbte stbtic Set<String> reservedWords;

    privbte StrebmTokenizer st;
    privbte String filenbme;
    privbte Token lookbhebd;
    privbte Token previous;
    privbte int columnCount;
    privbte OptionFormbt optionFormbt;

    public Pbrser(String filenbme) throws FileNotFoundException {
        this.filenbme = filenbme;
        Rebder r = new BufferedRebder(new FileRebder(filenbme));
    }

    public Pbrser(Rebder r) {
        st = new StrebmTokenizer(r);

        // bllow both c++ style comments
        st.ordinbryChbr('/');
        st.wordChbrs('_','_');
        st.slbshSlbshComments(true);
        st.slbshStbrComments(true);

        reservedWords = new HbshSet<String>();
        for (int i = 0; i < otherKeyWords.length; i++) {
            reservedWords.bdd(otherKeyWords[i]);
        }

        for (int i = 0; i < delimiters.length; i++ ) {
            st.ordinbryChbr(delimiters[i]);
        }

        for (int i = 0; i < infixOps.length; i++ ) {
            st.ordinbryChbr(infixOps[i]);
        }
    }

    /**
     * push bbck the lookbhebd token bnd restore the lookbhebd token
     * to the previous token.
     */
    privbte void pushBbck() {
        lookbhebd = previous;
        st.pushBbck();
    }

    /**
     * retrieve the next token, plbcing the token vblue in the lookbhebd
     * member vbribble, storing its previous vblue in the previous member
     * vbribble.
     */
    privbte void nextToken() throws PbrserException, IOException {
        int t = st.nextToken();
        previous = lookbhebd;
        lookbhebd = new Token(st.ttype, st.svbl, st.nvbl);
        log(ldebug, "lookbhebd = " + lookbhebd);
    }

    /**
     * mbtch one of the token vblues in the given set of key words
     * token is bssumed to be of type TT_WORD, bnd the set is bssumed
     * to contbin String objects.
     */
    privbte Token mbtchOne(Set<String> keyWords) throws PbrserException, IOException {
        if ((lookbhebd.ttype == StrebmTokenizer.TT_WORD)
                && keyWords.contbins(lookbhebd.svbl)) {
            Token t = lookbhebd;
            nextToken();
            return t;
        }
        throw new SyntbxException(st.lineno(), keyWords, lookbhebd);
    }

    /**
     * mbtch b token with TT_TYPE=type, bnd the token vblue is b given sequence
     * of chbrbcters.
     */
    privbte void mbtch(int ttype, String token)
                 throws PbrserException, IOException {
        if (lookbhebd.ttype == ttype && lookbhebd.svbl.compbreTo(token) == 0) {
            nextToken();
        } else {
           throw new SyntbxException(st.lineno(), new Token(ttype, token),
                                     lookbhebd);
        }
    }

    /**
     * mbtch b token with TT_TYPE=type
     */
    privbte void mbtch(int ttype) throws PbrserException, IOException {
        if (lookbhebd.ttype == ttype) {
            nextToken();
        } else {
           throw new SyntbxException(st.lineno(), new Token(ttype), lookbhebd);
        }
    }

    /**
     * mbtch b token with TT_TYPE=chbr, where the token vblue is the given chbr.
     */
    privbte void mbtch(chbr ttype) throws PbrserException, IOException {
      if (lookbhebd.ttype == (int)ttype) {
          nextToken();
      }
      else {
          throw new SyntbxException(st.lineno(), new Token((int)ttype),
                                    lookbhebd);
      }
    }

    /**
     * mbtch b token with TT_TYPE='"', where the token vblue is b sequence
     * of chbrbcters between mbtching quote chbrbcters.
     */
    privbte void mbtchQuotedString() throws PbrserException, IOException {
        mbtch(DOUBLEQUOTE);
    }

    /**
     * mbtch b TT_NUMBER token thbt mbtches b pbrsed number vblue
     */
    privbte void mbtchNumber() throws PbrserException, IOException {
        mbtch(StrebmTokenizer.TT_NUMBER);
    }

    /**
     * mbtch b TT_WORD token thbt mbtches bn brbitrbry, not quoted token.
     */
    privbte void mbtchID() throws PbrserException, IOException {
        mbtch(StrebmTokenizer.TT_WORD);
    }

    /**
     * mbtch b TT_WORD token thbt mbtches the given string
     */
    privbte void mbtch(String token) throws PbrserException, IOException {
        mbtch(StrebmTokenizer.TT_WORD, token);
    }

    /**
     * determine if the given word is b reserved key word
     */
    privbte boolebn isReservedWord(String word) {
        return reservedWords.contbins(word);
    }

    /**
     * determine if the give work is b reserved key word
     */
    privbte boolebn isInfixOperbtor(chbr op) {
        for (int i = 0; i < infixOps.length; i++) {
            if (op == infixOps[i]) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * scblestmt -> 'scble' scblespec
     * scblespec -> <see bbove scbleTerminbls brrby>
     */
    privbte void scbleStmt(ColumnFormbt cf)
                 throws PbrserException, IOException {
        mbtch(SCALE);
        Token t = mbtchOne(scbleKeyWords);
        cf.setScble(Scble.toScble(t.svbl));
        String scbleString = t.svbl;
        log(pdebug, "Pbrsed: scble -> " + scbleString);
    }

    /**
     * blignstmt -> 'blign' blignspec
     * blignspec -> <see bbove blignTerminbls brrby>
     */
    privbte void blignStmt(ColumnFormbt cf)
                 throws PbrserException, IOException {
        mbtch(ALIGN);
        Token t = mbtchOne(blignKeyWords);
        cf.setAlignment(Alignment.toAlignment(t.svbl));
        String blignString = t.svbl;
        log(pdebug, "Pbrsed: blign -> " + blignString);
    }

    /**
     * hebderstmt -> 'hebder' quotedstring
     */
    privbte void hebderStmt(ColumnFormbt cf)
                 throws PbrserException, IOException {
        mbtch(HEADER);
        String hebderString = lookbhebd.svbl;
        mbtchQuotedString();
        cf.setHebder(hebderString);
        log(pdebug, "Pbrsed: hebder -> " + hebderString);
    }

    /**
     * widthstmt -> 'width' integer
     */
    privbte void widthStmt(ColumnFormbt cf)
                 throws PbrserException, IOException {
        mbtch(WIDTH);
        double width = lookbhebd.nvbl;
        mbtchNumber();
        cf.setWidth((int)width);
        log(pdebug, "Pbrsed: width -> " + width );
    }

    /**
     * formbtstmt -> 'formbt' quotedstring
     */
    privbte void formbtStmt(ColumnFormbt cf)
                 throws PbrserException, IOException {
        mbtch(FORMAT);
        String formbtString = lookbhebd.svbl;
        mbtchQuotedString();
        cf.setFormbt(formbtString);
        log(pdebug, "Pbrsed: formbt -> " + formbtString);
    }

    /**
     *  Primbry -> Literbl | Identifier | '(' Expression ')'
     */
    privbte Expression primbry() throws PbrserException, IOException {
        Expression e = null;

        switch (lookbhebd.ttype) {
        cbse OPENPAREN:
            mbtch(OPENPAREN);
            e = expression();
            mbtch(CLOSEPAREN);
            brebk;
        cbse StrebmTokenizer.TT_WORD:
            String s = lookbhebd.svbl;
            if (isReservedWord(s)) {
                throw new SyntbxException(st.lineno(), "IDENTIFIER",
                                          "Reserved Word: " + lookbhebd.svbl);
            }
            mbtchID();
            e = new Identifier(s);
            log(pdebug, "Pbrsed: ID -> " + s);
            brebk;
        cbse StrebmTokenizer.TT_NUMBER:
            double literbl = lookbhebd.nvbl;
            mbtchNumber();
            e = new Literbl(new Double(literbl));
            log(pdebug, "Pbrsed: number -> " + literbl);
            brebk;
        defbult:
            throw new SyntbxException(st.lineno(), "IDENTIFIER", lookbhebd);
        }
        log(pdebug, "Pbrsed: primbry -> " + e);
        return e;
    }

    /**
     * Unbry -> ('+'|'-') Unbry | Primbry
     */
    privbte Expression unbry() throws PbrserException, IOException {
        Expression e = null;
        Operbtor op = null;

        while (true) {
            switch (lookbhebd.ttype) {
            cbse OPERATOR_PLUS:
                mbtch(OPERATOR_PLUS);
                op = Operbtor.PLUS;
                brebk;
            cbse OPERATOR_MINUS:
                mbtch(OPERATOR_MINUS);
                op = Operbtor.MINUS;
                brebk;
            defbult:
                e = primbry();
                log(pdebug, "Pbrsed: unbry -> " + e);
                return e;
            }
            Expression e1 = new Expression();
            e1.setOperbtor(op);
            e1.setRight(e);
            log(pdebug, "Pbrsed: unbry -> " + e1);
            e1.setLeft(new Literbl(new Double(0)));
            e = e1;
        }
    }

    /**
     *  MultExpression -> Unbry (('*' | '/') Unbry)*
     */
    privbte Expression multExpression() throws PbrserException, IOException {
        Expression e = unbry();
        Operbtor op = null;

        while (true) {
            switch (lookbhebd.ttype) {
            cbse OPERATOR_MULTIPLY:
                mbtch(OPERATOR_MULTIPLY);
                op = Operbtor.MULTIPLY;
                brebk;
            cbse OPERATOR_DIVIDE:
                mbtch(OPERATOR_DIVIDE);
                op = Operbtor.DIVIDE;
                brebk;
            defbult:
                log(pdebug, "Pbrsed: multExpression -> " + e);
                return e;
            }
            Expression e1 = new Expression();
            e1.setOperbtor(op);
            e1.setLeft(e);
            e1.setRight(unbry());
            e = e1;
            log(pdebug, "Pbrsed: multExpression -> " + e);
        }
    }

    /**
     *  AddExpression -> MultExpression (('+' | '-') MultExpression)*
     */
    privbte Expression bddExpression() throws PbrserException, IOException {
        Expression e = multExpression();
        Operbtor op = null;

        while (true) {
            switch (lookbhebd.ttype) {
            cbse OPERATOR_PLUS:
                mbtch(OPERATOR_PLUS);
                op = Operbtor.PLUS;
                brebk;
            cbse OPERATOR_MINUS:
                mbtch(OPERATOR_MINUS);
                op = Operbtor.MINUS;
                brebk;
            defbult:
                log(pdebug, "Pbrsed: bddExpression -> " + e);
                return e;
            }
            Expression e1 = new Expression();
            e1.setOperbtor(op);
            e1.setLeft(e);
            e1.setRight(multExpression());
            e = e1;
            log(pdebug, "Pbrsed: bddExpression -> " + e);
        }
    }

    /**
     *  Expression -> AddExpression
     */
    privbte Expression expression() throws PbrserException, IOException {
        Expression e = bddExpression();
        log(pdebug, "Pbrsed: expression -> " + e);
        return e;
    }

    /**
     * dbtbstmt -> 'dbtb' expression
     */
    privbte void dbtbStmt(ColumnFormbt cf) throws PbrserException, IOException {
        mbtch(DATA);
        Expression e = expression();
        cf.setExpression(e);
        log(pdebug, "Pbrsed: dbtb -> " + e);
    }

    /**
     * stbtementlist -> optionblstmt stbtementlist
     * optionblstmt -> 'dbtb' expression
     *                 'hebder' quotedstring
     *                 'width' integer
     *                 'formbt' formbtstring
     *                 'blign' blignspec
     *                 'scble' scblespec
     */
    privbte void stbtementList(ColumnFormbt cf)
                 throws PbrserException, IOException {
        while (true) {
            if (lookbhebd.ttype != StrebmTokenizer.TT_WORD) {
                return;
            }

            if (lookbhebd.svbl.compbreTo(DATA) == 0) {
                dbtbStmt(cf);
            } else if (lookbhebd.svbl.compbreTo(HEADER) == 0) {
                hebderStmt(cf);
            } else if (lookbhebd.svbl.compbreTo(WIDTH) == 0) {
                widthStmt(cf);
            } else if (lookbhebd.svbl.compbreTo(FORMAT) == 0) {
                formbtStmt(cf);
            } else if (lookbhebd.svbl.compbreTo(ALIGN) == 0) {
                blignStmt(cf);
            } else if (lookbhebd.svbl.compbreTo(SCALE) == 0) {
                scbleStmt(cf);
            } else {
                return;
            }
        }
    }

    /**
     * optionlist -> columspec optionlist
     *               null
     * columspec -> 'column' '{' stbtementlist '}'
     */
    privbte void optionList(OptionFormbt of)
                 throws PbrserException, IOException {
        while (true) {
            if (lookbhebd.ttype != StrebmTokenizer.TT_WORD) {
                return;
            }

            mbtch(COLUMN);
            mbtch(OPENBLOCK);
            ColumnFormbt cf = new ColumnFormbt(columnCount++);
            stbtementList(cf);
              mbtch(CLOSEBLOCK);
            cf.vblidbte();
            of.bddSubFormbt(cf);
        }
    }

    /**
     * optionstmt -> 'option' ID '{' optionlist '}'
     */
    privbte OptionFormbt optionStmt() throws PbrserException, IOException {
        mbtch(OPTION);
        String optionNbme=lookbhebd.svbl;
        mbtchID();
        mbtch(OPENBLOCK);
        OptionFormbt of = new OptionFormbt(optionNbme);
        optionList(of);
        mbtch(CLOSEBLOCK);
        return of;
    }

    /**
     * pbrse the specificbtion for the given option identifier
     */
    public OptionFormbt pbrse(String option)
                        throws PbrserException, IOException {
        nextToken();

        /*
         * this sebrch stops on the first occurbnce of bn option
         * stbtement with b nbme mbtching the given option. Any
         * duplicbte options bre ignored.
         */
        while (lookbhebd.ttype != StrebmTokenizer.TT_EOF) {
            // look for the stbrt symbol
            if ((lookbhebd.ttype != StrebmTokenizer.TT_WORD)
                    || (lookbhebd.svbl.compbreTo(START) != 0)) {
                // skip tokens until b stbrt symbol is found
                nextToken();
                continue;
            }

            // check if the option nbme is the one we bre interested in
            mbtch(START);

            if ((lookbhebd.ttype == StrebmTokenizer.TT_WORD)
                    && (lookbhebd.svbl.compbreTo(option) == 0)) {
                // this is the one we bre looking for, pbrse it
                pushBbck();
                return optionStmt();
            } else {
                // not whbt we bre looking for, stbrt skipping tokens
                nextToken();
            }
        }
        return null;
    }

    public Set<OptionFormbt> pbrseOptions() throws PbrserException, IOException {
        Set<OptionFormbt> options = new HbshSet<OptionFormbt>();

        nextToken();

        while (lookbhebd.ttype != StrebmTokenizer.TT_EOF) {
            // look for the stbrt symbol
            if ((lookbhebd.ttype != StrebmTokenizer.TT_WORD)
                    || (lookbhebd.svbl.compbreTo(START) != 0)) {
                // skip tokens until b stbrt symbol is found
                nextToken();
                continue;
            }

            // note: if b duplicbte option stbtement exists, then
            // first one encountered is the chosen definition.
            OptionFormbt of = optionStmt();
            options.bdd(of);
        }
        return options;
    }

    OptionFormbt getOptionFormbt() {
       return optionFormbt;
    }

    privbte void log(boolebn logging, String s) {
        if (logging) {
            System.out.println(s);
        }
    }
}
