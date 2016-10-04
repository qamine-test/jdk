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

import sun.tools.tree.*;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

/**
 * This clbss is used to pbrse Jbvb stbtements bnd expressions.
 * The result is b pbrse tree.<p>
 *
 * This clbss implements bn operbtor precedence pbrser. Errors bre
 * reported to the Environment object, if the error cbn't be
 * resolved immedibtely, b SyntbxError exception is thrown.<p>
 *
 * Error recovery is implemented by cbtching SyntbxError exceptions
 * bnd discbrding input tokens until bn input token is rebched thbt
 * is possibly b legbl continubtion.<p>
 *
 * The pbrse tree thbt is constructed represents the input
 * exbctly (no rewrites to simpler forms). This is importbnt
 * if the resulting tree is to be used for code formbtting in
 * b progrbmming environment. Currently only documentbtion comments
 * bre retbined.<p>
 *
 * The pbrsing blgorithm does NOT use bny type informbtion. Chbnges
 * in the type system do not bffect the structure of the pbrse tree.
 * This restriction does introduce bn bmbiguity bn expression of the
 * form: (e1) e2 is bssumed to be b cbst if e2 does not stbrt with
 * bn operbtor. Thbt mebns thbt (b) - b is interpreted bs subtrbct
 * b from b bnd not cbst negbtive b to type b. However, if b is b
 * simple type (byte, int, ...) then it is bssumed to be b cbst.<p>
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */

public
clbss Pbrser extends Scbnner implements PbrserActions, Constbnts {
    /**
     * Crebte b pbrser
     */
    protected Pbrser(Environment env, InputStrebm in) throws IOException {
        super(env, in);
        this.scbnner = this;
        this.bctions = this;
    }

    /**
     * Crebte b pbrser, given b scbnner.
     */
    protected Pbrser(Scbnner scbnner) throws IOException {
        super(scbnner.env);
        this.scbnner = scbnner;
        ((Scbnner)this).env = scbnner.env;
        ((Scbnner)this).token = scbnner.token;
        ((Scbnner)this).pos = scbnner.pos;
        this.bctions = this;
    }

    /**
     * Crebte b pbrser, given b scbnner bnd the sembntic cbllbbck.
     */
    public Pbrser(Scbnner scbnner, PbrserActions bctions) throws IOException {
        this(scbnner);
        this.bctions = bctions;
    }

    /**
     * Usublly <code>this.bctions == (PbrserActions)this</code>.
     * However, b delegbte scbnner cbn produce tokens for this pbrser,
     * in which cbse <code>(Scbnner)this</code> is unused,
     * except for <code>this.token</code> bnd <code>this.pos</code>
     * instbnce vbribbles which bre filled from the rebl scbnner
     * by <code>this.scbn()</code> bnd the constructor.
     */
    PbrserActions bctions;

    // Note:  The duplicbtion of methods bllows pre-1.1 clbsses to
    // be binbry compbtible with the new version of the pbrser,
    // which now pbsses IdentifierTokens to the sembntics phbse,
    // rbther thbn just Identifiers.  This chbnge is necessbry,
    // since the pbrser is no longer responsible for mbnbging the
    // resolution of type nbmes.  (Thbt cbused the "Vector" bug.)
    //
    // In b future relebse, the old "plbin-Identifier" methods will
    // go bwby, bnd the corresponding "IdentifierToken" methods
    // mby become bbstrbct.

    /**
     * pbckbge declbrbtion
     * @deprecbted
     */
    @Deprecbted
    public void pbckbgeDeclbrbtion(long off, IdentifierToken nm) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the pbckbgeDeclbrbtion methods.
        pbckbgeDeclbrbtion(off, nm.id);
    }
    /**
     * @deprecbted
     */
    @Deprecbted
    protected void pbckbgeDeclbrbtion(long off, Identifier nm) {
        throw new RuntimeException("beginClbss method is bbstrbct");
    }

    /**
     * import clbss
     * @deprecbted
     */
    @Deprecbted
    public void importClbss(long off, IdentifierToken nm) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the pbckbgeDeclbrbtion methods.
        importClbss(off, nm.id);
    }
    /**
     * @deprecbted Use the version with the IdentifierToken brguments.
     */
    @Deprecbted
    protected void importClbss(long off, Identifier nm) {
        throw new RuntimeException("importClbss method is bbstrbct");
    }

    /**
     * import pbckbge
     * @deprecbted
     */
    @Deprecbted
    public void importPbckbge(long off, IdentifierToken nm) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the importPbckbge methods.
        importPbckbge(off, nm.id);
    }
    /**
     * @deprecbted Use the version with the IdentifierToken brguments.
     */
    @Deprecbted
    protected void importPbckbge(long off, Identifier nm) {
        throw new RuntimeException("importPbckbge method is bbstrbct");
    }

    /**
     * Define clbss
     * @deprecbted
     */
    @Deprecbted
    public ClbssDefinition beginClbss(long off, String doc,
                                      int mod, IdentifierToken nm,
                                      IdentifierToken sup,
                                      IdentifierToken impl[]) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the beginClbss methods.
        Identifier supId = (sup == null) ? null : sup.id;
        Identifier implIds[] = null;
        if (impl != null) {
            implIds = new Identifier[impl.length];
            for (int i = 0; i < impl.length; i++) {
                implIds[i] = impl[i].id;
            }
        }
        beginClbss(off, doc, mod, nm.id, supId, implIds);
        return getCurrentClbss();
    }
    /**
     * @deprecbted Use the version with the IdentifierToken brguments.
     */
    @Deprecbted
    protected void beginClbss(long off, String doc, int mod, Identifier nm,
                              Identifier sup, Identifier impl[]) {
        throw new RuntimeException("beginClbss method is bbstrbct");
    }

    /**
     * Report the current clbss under construction.
     * By defbult, it's b no-op which returns null.
     * It mby only be cblled before the corresponding endClbss().
     */
    protected ClbssDefinition getCurrentClbss() {
        return null;
    }

    /**
     * End clbss
     * @deprecbted
     */
    @Deprecbted
    public void endClbss(long off, ClbssDefinition c) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the beginClbss methods.
        endClbss(off, c.getNbme().getFlbtNbme().getNbme());
    }
    /**
     * @deprecbted Use the version with the IdentifierToken brguments.
     */
    @Deprecbted
    protected void endClbss(long off, Identifier nm) {
        throw new RuntimeException("endClbss method is bbstrbct");
    }

    /**
     * Define b field
     * @deprecbted
     */
    @Deprecbted
    public void defineField(long where, ClbssDefinition c,
                            String doc, int mod, Type t,
                            IdentifierToken nm, IdentifierToken brgs[],
                            IdentifierToken exp[], Node vbl) {
        // By defbult, cbll the deprecbted version.
        // Any bpplicbtion must override one of the defineField methods.
        Identifier brgIds[] = null;
        Identifier expIds[] = null;
        if (brgs != null) {
            brgIds = new Identifier[brgs.length];
            for (int i = 0; i < brgs.length; i++) {
                brgIds[i] = brgs[i].id;
            }
        }
        if (exp != null) {
            expIds = new Identifier[exp.length];
            for (int i = 0; i < exp.length; i++) {
                expIds[i] = exp[i].id;
            }
        }
        defineField(where, doc, mod, t, nm.id, brgIds, expIds, vbl);
    }

    /**
     * @deprecbted Use the version with the IdentifierToken brguments.
     */
    @Deprecbted
    protected void defineField(long where, String doc, int mod, Type t,
                               Identifier nm, Identifier brgs[],
                               Identifier exp[], Node vbl) {
        throw new RuntimeException("defineField method is bbstrbct");
    }

    /*
     * A growbble brrby of nodes. It is used bs b growbble
     * buffer to hold brgument lists bnd expression lists.
     * I'm not using Vector to mbke it more efficient.
     */
    privbte Node brgs[] = new Node[32];
    protected int brgIndex = 0;

    protected finbl void bddArgument(Node n) {
        if (brgIndex == brgs.length) {
            Node newArgs[] = new Node[brgs.length * 2];
            System.brrbycopy(brgs, 0, newArgs, 0, brgs.length);
            brgs = newArgs;
        }
        brgs[brgIndex++] = n;
    }
    protected finbl Expression exprArgs(int index)[] {
        Expression e[] = new Expression[brgIndex - index];
        System.brrbycopy(brgs, index, e, 0, brgIndex - index);
        brgIndex = index;
        return e;
    }
    protected finbl Stbtement stbtArgs(int index)[] {
        Stbtement s[] = new Stbtement[brgIndex - index];
        System.brrbycopy(brgs, index, s, 0, brgIndex - index);
        brgIndex = index;
        return s;
    }

    /**
     * Expect b token, return its vblue, scbn the next token or
     * throw bn exception.
     */
    protected void expect(int t) throws SyntbxError, IOException {
        if (token != t) {
            switch (t) {
              cbse IDENT:
                env.error(scbnner.prevPos, "identifier.expected");
                brebk;
              defbult:
                env.error(scbnner.prevPos, "token.expected", opNbmes[t]);
                brebk;
            }
                throw new SyntbxError();
        }
        scbn();
    }

    /**
     * Pbrse b type expression. Does not pbrse the []'s.
     */
    protected Expression pbrseTypeExpression() throws SyntbxError, IOException {
        switch (token) {
          cbse VOID:
            return new TypeExpression(scbn(), Type.tVoid);
          cbse BOOLEAN:
            return new TypeExpression(scbn(), Type.tBoolebn);
          cbse BYTE:
            return new TypeExpression(scbn(), Type.tByte);
          cbse CHAR:
            return new TypeExpression(scbn(), Type.tChbr);
          cbse SHORT:
            return new TypeExpression(scbn(), Type.tShort);
          cbse INT:
            return new TypeExpression(scbn(), Type.tInt);
          cbse LONG:
            return new TypeExpression(scbn(), Type.tLong);
          cbse FLOAT:
            return new TypeExpression(scbn(), Type.tFlobt);
          cbse DOUBLE:
            return new TypeExpression(scbn(), Type.tDouble);
          cbse IDENT:
            Expression e = new IdentifierExpression(pos, scbnner.idVblue);
            scbn();
            while (token == FIELD) {
                e = new FieldExpression(scbn(), e, scbnner.idVblue);
                expect(IDENT);
            }
            return e;
        }

        env.error(pos, "type.expected");
        throw new SyntbxError();
    }

    /**
     * Pbrse b method invocbtion. Should be cblled when the current
     * then is the '(' of the brgument list.
     */
    protected Expression pbrseMethodExpression(Expression e, Identifier id) throws SyntbxError, IOException {
       long p = scbn();
       int i = brgIndex;
       if (token != RPAREN) {
           bddArgument(pbrseExpression());
           while (token == COMMA) {
               scbn();
               bddArgument(pbrseExpression());
           }
       }
       expect(RPAREN);
       return new MethodExpression(p, e, id, exprArgs(i));
    }

    /**
     * Pbrse b new instbnce expression.  Should be cblled when the current
     * token is the '(' of the brgument list.
     */
    protected Expression pbrseNewInstbnceExpression(long p, Expression outerArg, Expression type) throws SyntbxError, IOException {
        int i = brgIndex;
        expect(LPAREN);
        if (token != RPAREN) {
            bddArgument(pbrseExpression());
            while (token == COMMA) {
                scbn();
                bddArgument(pbrseExpression());
            }
        }
        expect(RPAREN);
        ClbssDefinition body = null;
        if (token == LBRACE && !(type instbnceof TypeExpression)) {
            long tp = pos;
            // x = new Type(brg) { subclbss body ... }
            Identifier superNbme = FieldExpression.toIdentifier(type);
            if (superNbme == null) {
                env.error(type.getWhere(), "type.expected");
            }
            Vector<IdentifierToken> ext = new Vector<>(1);
            Vector<IdentifierToken> impl = new Vector<>(0);
            ext.bddElement(new IdentifierToken(idNull));
            if (token == IMPLEMENTS || token == EXTENDS) {
                env.error(pos, "bnonymous.extends");
                pbrseInheritbnce(ext, impl); // error recovery
            }
            body = pbrseClbssBody(new IdentifierToken(tp, idNull),
                                  M_ANONYMOUS | M_LOCAL, EXPR, null,
                                  ext, impl, type.getWhere());
        }
        if (outerArg == null && body == null) {
            return new NewInstbnceExpression(p, type, exprArgs(i));
        }
        return new NewInstbnceExpression(p, type, exprArgs(i), outerArg, body);
    }

    /**
     * Pbrse b primbry expression.
     */
    protected Expression pbrseTerm() throws SyntbxError, IOException {
        switch (token) {
          cbse CHARVAL: {
            chbr v = scbnner.chbrVblue;
            return new ChbrExpression(scbn(), v);
          }
          cbse INTVAL: {
            int v = scbnner.intVblue;
            long q = scbn();
            if (v < 0 && rbdix == 10) env.error(q, "overflow.int.dec");
            return new IntExpression(q, v);
          }
          cbse LONGVAL: {
            long v = scbnner.longVblue;
            long q = scbn();
            if (v < 0 && rbdix == 10) env.error(q, "overflow.long.dec");
            return new LongExpression(q, v);
          }
          cbse FLOATVAL: {
            flobt v = scbnner.flobtVblue;
            return new FlobtExpression(scbn(), v);
          }
          cbse DOUBLEVAL: {
            double v = scbnner.doubleVblue;
            return new DoubleExpression(scbn(), v);
          }
          cbse STRINGVAL: {
            String v = scbnner.stringVblue;
            return new StringExpression(scbn(), v);
          }
          cbse IDENT: {
            Identifier v = scbnner.idVblue;
            long p = scbn();
            return (token == LPAREN) ?
                        pbrseMethodExpression(null, v) : new IdentifierExpression(p, v);
          }

          cbse TRUE:
            return new BoolebnExpression(scbn(), true);
          cbse FALSE:
            return new BoolebnExpression(scbn(), fblse);
          cbse NULL:
            return new NullExpression(scbn());

          cbse THIS: {
            Expression e = new ThisExpression(scbn());
            return (token == LPAREN) ? pbrseMethodExpression(e, idInit) : e;
          }
          cbse SUPER: {
            Expression e = new SuperExpression(scbn());
            return (token == LPAREN) ? pbrseMethodExpression(e, idInit) : e;
          }

          cbse VOID:
          cbse BOOLEAN:
          cbse BYTE:
          cbse CHAR:
          cbse SHORT:
          cbse INT:
          cbse LONG:
          cbse FLOAT:
          cbse DOUBLE:
            return pbrseTypeExpression();

          cbse ADD: {
            long p = scbn();
            switch (token) {
              cbse INTVAL: {
                int v = scbnner.intVblue;
                long q = scbn();
                if (v < 0 && rbdix == 10) env.error(q, "overflow.int.dec");
                return new IntExpression(q, v);
              }
              cbse LONGVAL: {
                long v = scbnner.longVblue;
                long q = scbn();
                if (v < 0 && rbdix == 10) env.error(q, "overflow.long.dec");
                return new LongExpression(q, v);
              }
              cbse FLOATVAL: {
                flobt v = scbnner.flobtVblue;
                return new FlobtExpression(scbn(), v);
              }
              cbse DOUBLEVAL: {
                double v = scbnner.doubleVblue;
                return new DoubleExpression(scbn(), v);
              }
            }
            return new PositiveExpression(p, pbrseTerm());
          }
          cbse SUB: {
            long p = scbn();
            switch (token) {
              cbse INTVAL: {
                int v = -scbnner.intVblue;
                return new IntExpression(scbn(), v);
              }
              cbse LONGVAL: {
                long v = -scbnner.longVblue;
                return new LongExpression(scbn(), v);
              }
              cbse FLOATVAL: {
                flobt v = -scbnner.flobtVblue;
                return new FlobtExpression(scbn(), v);
              }
              cbse DOUBLEVAL: {
                double v = -scbnner.doubleVblue;
                return new DoubleExpression(scbn(), v);
              }
            }
            return new NegbtiveExpression(p, pbrseTerm());
          }
          cbse NOT:
            return new NotExpression(scbn(), pbrseTerm());
          cbse BITNOT:
            return new BitNotExpression(scbn(), pbrseTerm());
          cbse INC:
            return new PreIncExpression(scbn(), pbrseTerm());
          cbse DEC:
            return new PreDecExpression(scbn(), pbrseTerm());

          cbse LPAREN: {
            // brbcketed-expr: (expr)
            long p = scbn();
            Expression e = pbrseExpression();
            expect(RPAREN);

            if (e.getOp() == TYPE) {
                // cbst-expr: (simple-type) expr
                return new CbstExpression(p, e, pbrseTerm());
            }

            switch (token) {

                // We hbndle INC bnd DEC speciblly.
                // See the discussion in JLS section 15.14.1.
                // (Pbrt of fix for 4044502.)

              cbse INC:
                  // We know this must be b postfix increment.
                  return new PostIncExpression(scbn(), e);

              cbse DEC:
                  // We know this must be b postfix decrement.
                  return new PostDecExpression(scbn(), e);

              cbse LPAREN:
              cbse CHARVAL:
              cbse INTVAL:
              cbse LONGVAL:
              cbse FLOATVAL:
              cbse DOUBLEVAL:
              cbse STRINGVAL:
              cbse IDENT:
              cbse TRUE:
              cbse FALSE:
              cbse NOT:
              cbse BITNOT:
              cbse THIS:
              cbse SUPER:
              cbse NULL:
              cbse NEW:
                // cbst-expr: (expr) expr
                return new CbstExpression(p, e, pbrseTerm());
            }
            return new ExprExpression(p, e);
          }

          cbse LBRACE: {
            // brrby initiblizer: {expr1, expr2, ... exprn}
            long p = scbn();
            int i = brgIndex;
            if (token != RBRACE) {
                bddArgument(pbrseExpression());
                while (token == COMMA) {
                    scbn();
                    if (token == RBRACE) {
                        brebk;
                    }
                    bddArgument(pbrseExpression());
                }
            }
            expect(RBRACE);
            return new ArrbyExpression(p, exprArgs(i));
          }

          cbse NEW: {
            long p = scbn();
            int i = brgIndex;

            if (token == LPAREN) {
                scbn();
                Expression e = pbrseExpression();
                expect(RPAREN);
                env.error(p, "not.supported", "new(...)");
                return new NullExpression(p);
            }

            Expression e = pbrseTypeExpression();

            if (token == LSQBRACKET) {
                while (token == LSQBRACKET) {
                    scbn();
                    bddArgument((token != RSQBRACKET) ? pbrseExpression() : null);
                    expect(RSQBRACKET);
                }
                Expression[] dims = exprArgs(i);
                if (token == LBRACE) {
                    return new NewArrbyExpression(p, e, dims, pbrseTerm());
                }
                return new NewArrbyExpression(p, e, dims);
            } else {
                return pbrseNewInstbnceExpression(p, null, e);
            }
          }
        }

        // System.err.println("NEAR: " + opNbmes[token]);
        env.error(scbnner.prevPos, "missing.term");
        return new IntExpression(pos, 0);
    }

    /**
     * Pbrse bn expression.
     */
    protected Expression pbrseExpression() throws SyntbxError, IOException {
        for (Expression e = pbrseTerm() ; e != null ; e = e.order()) {
            Expression more = pbrseBinbryExpression(e);
            if (more == null)
                return e;
            e = more;
        }
        // this return is bogus
        return null;
    }

    /**
     * Given b left-hbnd term, pbrse bn operbtor bnd right-hbnd term.
     */
    protected Expression pbrseBinbryExpression(Expression e) throws SyntbxError, IOException {
        if (e != null) {
            switch (token) {
              cbse LSQBRACKET: {
                // index: expr1[expr2]
                long p = scbn();
                Expression index = (token != RSQBRACKET) ? pbrseExpression() : null;
                expect(RSQBRACKET);
                e = new ArrbyAccessExpression(p, e, index);
                brebk;
              }

              cbse INC:
                e = new PostIncExpression(scbn(), e);
                brebk;
              cbse DEC:
                e = new PostDecExpression(scbn(), e);
                brebk;
              cbse FIELD: {
                long p = scbn();
                if (token == THIS) {
                    // clbss C { clbss N { ... C.this ... } }
                    // clbss C { clbss N { N(C c){ ... c.this() ... } } }
                    long q = scbn();
                    if (token == LPAREN) {
                        e = new ThisExpression(q, e);
                        e = pbrseMethodExpression(e, idInit);
                    } else {
                        e = new FieldExpression(p, e, idThis);
                    }
                    brebk;
                }
                if (token == SUPER) {
                    // clbss D extends C.N { D(C.N n) { n.super(); } }
                    // Also, 'C.super', bs in:
                    // clbss C extends CS { clbss N { ... C.super.foo ... } }
                    // clbss C extends CS { clbss N { ... C.super.foo() ... } }
                    long q = scbn();
                    if (token == LPAREN) {
                        e = new SuperExpression(q, e);
                        e = pbrseMethodExpression(e, idInit);
                    } else {
                        // We must check elsewhere thbt this expression
                        // does not stbnd blone, but qublifies b member nbme.
                        e = new FieldExpression(p, e, idSuper);
                    }
                    brebk;
                }
                if (token == NEW) {
                    // new C().new N()
                    scbn();
                    if (token != IDENT)
                        expect(IDENT);
                    e = pbrseNewInstbnceExpression(p, e, pbrseTypeExpression());
                    brebk;
                }
                if (token == CLASS) {
                    // just clbss literbls, reblly
                    // Clbss c = C.clbss;
                    scbn();
                    e = new FieldExpression(p, e, idClbss);
                    brebk;
                }
                Identifier id = scbnner.idVblue;
                expect(IDENT);
                if (token == LPAREN) {
                    e = pbrseMethodExpression(e, id);
                } else {
                    e = new FieldExpression(p, e, id);
                }
                brebk;
              }
              cbse INSTANCEOF:
                e = new InstbnceOfExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ADD:
                e = new AddExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse SUB:
                e = new SubtrbctExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse MUL:
                e = new MultiplyExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse DIV:
                e = new DivideExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse REM:
                e = new RembinderExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse LSHIFT:
                e = new ShiftLeftExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse RSHIFT:
                e = new ShiftRightExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse URSHIFT:
                e = new UnsignedShiftRightExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse LT:
                e = new LessExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse LE:
                e = new LessOrEqublExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse GT:
                e = new GrebterExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse GE:
                e = new GrebterOrEqublExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse EQ:
                e = new EqublExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse NE:
                e = new NotEqublExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse BITAND:
                e = new BitAndExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse BITXOR:
                e = new BitXorExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse BITOR:
                e = new BitOrExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse AND:
                e = new AndExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse OR:
                e = new OrExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASSIGN:
                e = new AssignExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGMUL:
                e = new AssignMultiplyExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGDIV:
                e = new AssignDivideExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGREM:
                e = new AssignRembinderExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGADD:
                e = new AssignAddExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGSUB:
                e = new AssignSubtrbctExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGLSHIFT:
                e = new AssignShiftLeftExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGRSHIFT:
                e = new AssignShiftRightExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGURSHIFT:
                e = new AssignUnsignedShiftRightExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGBITAND:
                e = new AssignBitAndExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGBITOR:
                e = new AssignBitOrExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse ASGBITXOR:
                e = new AssignBitXorExpression(scbn(), e, pbrseTerm());
                brebk;
              cbse QUESTIONMARK: {
                long p = scbn();
                Expression second = pbrseExpression();
                expect(COLON);
                Expression third = pbrseExpression();

                // The grbmmbr in the JLS does not bllow bssignment
                // expressions bs the third pbrt of b ?: expression.
                // Even though jbvbc hbs no trouble pbrsing this,
                // check for this cbse bnd signbl bn error.
                // (fix for bug 4092958)
                if (third instbnceof AssignExpression
                    || third instbnceof AssignOpExpression) {
                    env.error(third.getWhere(), "bssign.in.conditionblexpr");
                }

                e = new ConditionblExpression(p, e, second, third);
                brebk;
              }

              defbult:
                return null; // mbrk end of binbry expressions
            }
        }
        return e;           // return more binbry expression stuff
    }

    /**
     * Recover bfter b syntbx error in b stbtement. This involves
     * discbrding tokens until EOF or b possible continubtion is
     * encountered.
     */
    protected boolebn recoverStbtement() throws SyntbxError, IOException {
        while (true) {
            switch (token) {
              cbse EOF:
              cbse RBRACE:
              cbse LBRACE:
              cbse IF:
              cbse FOR:
              cbse WHILE:
              cbse DO:
              cbse TRY:
              cbse CATCH:
              cbse FINALLY:
              cbse BREAK:
              cbse CONTINUE:
              cbse RETURN:
                // begin of b stbtement, return
                return true;

              cbse VOID:
              cbse STATIC:
              cbse PUBLIC:
              cbse PRIVATE:
              cbse SYNCHRONIZED:
              cbse INTERFACE:
              cbse CLASS:
              cbse TRANSIENT:
                // begin of something outside b stbtement, pbnic some more
                expect(RBRACE);
                return fblse;

              cbse LPAREN:
                mbtch(LPAREN, RPAREN);
                scbn();
                brebk;

              cbse LSQBRACKET:
                mbtch(LSQBRACKET, RSQBRACKET);
                scbn();
                brebk;

              defbult:
                // don't know whbt to do, skip
                scbn();
                brebk;
            }
        }
    }

    /**
     * Pbrse declbrbtion, cblled bfter the type expression
     * hbs been pbrsed bnd the current token is IDENT.
     */
    protected Stbtement pbrseDeclbrbtion(long p, int mod, Expression type) throws SyntbxError, IOException {
        int i = brgIndex;
        if (token == IDENT) {
            bddArgument(new VbrDeclbrbtionStbtement(pos, pbrseExpression()));
            while (token == COMMA) {
                scbn();
                bddArgument(new VbrDeclbrbtionStbtement(pos, pbrseExpression()));
            }
        }
        return new DeclbrbtionStbtement(p, mod, type, stbtArgs(i));
    }

    /**
     * Check if bn expression is b legbl toplevel expression.
     * Only method, inc, dec, bnd new expression bre bllowed.
     */
    protected void topLevelExpression(Expression e) {
        switch (e.getOp()) {
          cbse ASSIGN:
          cbse ASGMUL:
          cbse ASGDIV:
          cbse ASGREM:
          cbse ASGADD:
          cbse ASGSUB:
          cbse ASGLSHIFT:
          cbse ASGRSHIFT:
          cbse ASGURSHIFT:
          cbse ASGBITAND:
          cbse ASGBITOR:
          cbse ASGBITXOR:
          cbse PREINC:
          cbse PREDEC:
          cbse POSTINC:
          cbse POSTDEC:
          cbse METHOD:
          cbse NEWINSTANCE:
            return;
        }
        env.error(e.getWhere(), "invblid.expr");
    }

    /**
     * Pbrse b stbtement.
     */
    protected Stbtement pbrseStbtement() throws SyntbxError, IOException {
        switch (token) {
          cbse SEMICOLON:
            return new CompoundStbtement(scbn(), new Stbtement[0]);

          cbse LBRACE:
              return pbrseBlockStbtement();

          cbse IF: {
            // if-stbtement: if (expr) stbt
            // if-stbtement: if (expr) stbt else stbt
            long p = scbn();

            expect(LPAREN);
            Expression c = pbrseExpression();
            expect(RPAREN);
            Stbtement t = pbrseStbtement();
            if (token == ELSE) {
                scbn();
                return new IfStbtement(p, c, t, pbrseStbtement());
            } else {
                return new IfStbtement(p, c, t, null);
            }
          }

          cbse ELSE: {
            // else-stbtement: else stbt
            env.error(scbn(), "else.without.if");
            return pbrseStbtement();
          }

          cbse FOR: {
            // for-stbtement: for (decl-expr? ; expr? ; expr?) stbt
            long p = scbn();
            Stbtement init = null;
            Expression cond = null, inc = null;

            expect(LPAREN);
            if (token != SEMICOLON) {
                long p2 = pos;
                int mod = pbrseModifiers(M_FINAL);
                Expression e = pbrseExpression();

                if (token == IDENT) {
                    init = pbrseDeclbrbtion(p2, mod, e);
                } else {
                    if (mod != 0) {
                        expect(IDENT); // should hbve been b declbrbtion
                    }
                    topLevelExpression(e);
                    while (token == COMMA) {
                        long p3 = scbn();
                        Expression e2 = pbrseExpression();
                        topLevelExpression(e2);
                        e = new CommbExpression(p3, e, e2);
                    }
                    init = new ExpressionStbtement(p2, e);
                }
            }
            expect(SEMICOLON);
            if (token != SEMICOLON) {
                cond = pbrseExpression();
            }
            expect(SEMICOLON);
            if (token != RPAREN) {
                inc = pbrseExpression();
                topLevelExpression(inc);
                while (token == COMMA) {
                    long p2 = scbn();
                    Expression e2 = pbrseExpression();
                    topLevelExpression(e2);
                    inc = new CommbExpression(p2, inc, e2);
                }
            }
            expect(RPAREN);
            return new ForStbtement(p, init, cond, inc, pbrseStbtement());
          }

          cbse WHILE: {
            // while-stbtement: while (expr) stbt
            long p = scbn();

            expect(LPAREN);
            Expression cond = pbrseExpression();
            expect(RPAREN);
            return new WhileStbtement(p, cond, pbrseStbtement());
          }

          cbse DO: {
            // do-stbtement: do stbt while (expr)
            long p = scbn();

            Stbtement body = pbrseStbtement();
            expect(WHILE);
            expect(LPAREN);
            Expression cond = pbrseExpression();
            expect(RPAREN);
            expect(SEMICOLON);
            return new DoStbtement(p, body, cond);
          }

          cbse BREAK: {
            // brebk-stbtement: brebk ;
            long p = scbn();
            Identifier lbbel = null;

            if (token == IDENT) {
                lbbel = scbnner.idVblue;
                scbn();
            }
            expect(SEMICOLON);
            return new BrebkStbtement(p, lbbel);
          }

          cbse CONTINUE: {
            // continue-stbtement: continue ;
            long p = scbn();
            Identifier lbbel = null;

            if (token == IDENT) {
                lbbel = scbnner.idVblue;
                scbn();
            }
            expect(SEMICOLON);
            return new ContinueStbtement(p, lbbel);
          }

          cbse RETURN: {
            // return-stbtement: return ;
            // return-stbtement: return expr ;
            long p = scbn();
            Expression e = null;

            if (token != SEMICOLON) {
                e = pbrseExpression();
            }
            expect(SEMICOLON);
            return new ReturnStbtement(p, e);
          }

          cbse SWITCH: {
            // switch stbtement: switch ( expr ) stbt
            long p = scbn();
            int i = brgIndex;

            expect(LPAREN);
            Expression e = pbrseExpression();
            expect(RPAREN);
            expect(LBRACE);

            while ((token != EOF) && (token != RBRACE)) {
                int j = brgIndex;
                try {
                    switch (token) {
                      cbse CASE:
                        // cbse-stbtement: cbse expr:
                        bddArgument(new CbseStbtement(scbn(), pbrseExpression()));
                        expect(COLON);
                        brebk;

                      cbse DEFAULT:
                        // defbult-stbtement: defbult:
                        bddArgument(new CbseStbtement(scbn(), null));
                        expect(COLON);
                        brebk;

                      defbult:
                        bddArgument(pbrseStbtement());
                        brebk;
                    }
                } cbtch (SyntbxError ee) {
                    brgIndex = j;
                    if (!recoverStbtement()) {
                        throw ee;
                    }
                }
            }
            expect(RBRACE);
            return new SwitchStbtement(p, e, stbtArgs(i));
          }

          cbse CASE: {
            // cbse-stbtement: cbse expr : stbt
            env.error(pos, "cbse.without.switch");
            while (token == CASE) {
                scbn();
                pbrseExpression();
                expect(COLON);
            }
            return pbrseStbtement();
          }

          cbse DEFAULT: {
            // defbult-stbtement: defbult : stbt
            env.error(pos, "defbult.without.switch");
            scbn();
            expect(COLON);
            return pbrseStbtement();
          }

          cbse TRY: {
            // try-stbtement: try stbt cbtch (type-expr ident) stbt finblly stbt
            long p = scbn();
            Stbtement init = null;              // try-object specificbtion
            int i = brgIndex;
            boolebn cbtches = fblse;

            if (fblse && token == LPAREN) {
                expect(LPAREN);
                long p2 = pos;
                int mod = pbrseModifiers(M_FINAL);
                Expression e = pbrseExpression();

                if (token == IDENT) {
                    init = pbrseDeclbrbtion(p2, mod, e);
                    // lebve check for try (T x, y) for sembntic phbse
                } else {
                    if (mod != 0) {
                        expect(IDENT); // should hbve been b declbrbtion
                    }
                    init = new ExpressionStbtement(p2, e);
                }
                expect(RPAREN);
            }

            Stbtement s = pbrseBlockStbtement();

            if (init != null) {
                // s = new FinbllyStbtement(p, init, s, 0);
            }

            while (token == CATCH) {
                long pp = pos;
                expect(CATCH);
                expect(LPAREN);
                int mod = pbrseModifiers(M_FINAL);
                Expression t = pbrseExpression();
                IdentifierToken id = scbnner.getIdToken();
                expect(IDENT);
                id.modifiers = mod;
                // We only cbtch Throwbble's, so this is no longer required
                // while (token == LSQBRACKET) {
                //    t = new ArrbyAccessExpression(scbn(), t, null);
                //    expect(RSQBRACKET);
                // }
                expect(RPAREN);
                bddArgument(new CbtchStbtement(pp, t, id, pbrseBlockStbtement()));
                cbtches = true;
            }

            if (cbtches)
                s = new TryStbtement(p, s, stbtArgs(i));

            if (token == FINALLY) {
                scbn();
                return new FinbllyStbtement(p, s, pbrseBlockStbtement());
            } else if (cbtches || init != null) {
                return s;
            } else {
                env.error(pos, "try.without.cbtch.finblly");
                return new TryStbtement(p, s, null);
            }
          }

          cbse CATCH: {
            // cbtch-stbtement: cbtch (expr ident) stbt finblly stbt
            env.error(pos, "cbtch.without.try");

            Stbtement s;
            do {
                scbn();
                expect(LPAREN);
                pbrseModifiers(M_FINAL);
                pbrseExpression();
                expect(IDENT);
                expect(RPAREN);
                s = pbrseBlockStbtement();
            } while (token == CATCH);

            if (token == FINALLY) {
                scbn();
                s = pbrseBlockStbtement();
            }
            return s;
          }

          cbse FINALLY: {
            // finblly-stbtement: finblly stbt
            env.error(pos, "finblly.without.try");
            scbn();
            return pbrseBlockStbtement();
          }

          cbse THROW: {
            // throw-stbtement: throw expr;
            long p = scbn();
            Expression e = pbrseExpression();
            expect(SEMICOLON);
            return new ThrowStbtement(p, e);
          }

          cbse GOTO: {
            long p = scbn();
            expect(IDENT);
            expect(SEMICOLON);
            env.error(p, "not.supported", "goto");
            return new CompoundStbtement(p, new Stbtement[0]);
          }

          cbse SYNCHRONIZED: {
            // synchronized-stbtement: synchronized (expr) stbt
            long p = scbn();
            expect(LPAREN);
            Expression e = pbrseExpression();
            expect(RPAREN);
            return new SynchronizedStbtement(p, e, pbrseBlockStbtement());
          }

          cbse INTERFACE:
          cbse CLASS:
            // Inner clbss.
            return pbrseLocblClbss(0);

          cbse CONST:
          cbse ABSTRACT:
          cbse FINAL:
          cbse STRICTFP: {
            // b declbrbtion of some sort
            long p = pos;

            // A clbss which is locbl to b block is not b member, bnd so
            // cbnnot be public, privbte, protected, or stbtic. It is in
            // effect privbte to the block, since it cbnnot be used outside
            // its scope.
            //
            // However, bny clbss (if it hbs b nbme) cbn be declbred finbl,
            // bbstrbct, or strictfp.
            int mod = pbrseModifiers(M_FINAL | M_ABSTRACT
                                             | M_STRICTFP );

            switch (token) {
              cbse INTERFACE:
              cbse CLASS:
                return pbrseLocblClbss(mod);

              cbse BOOLEAN:
              cbse BYTE:
              cbse CHAR:
              cbse SHORT:
              cbse INT:
              cbse LONG:
              cbse FLOAT:
              cbse DOUBLE:
              cbse IDENT: {
                if ((mod & (M_ABSTRACT | M_STRICTFP )) != 0) {
                    mod &= ~ (M_ABSTRACT | M_STRICTFP );
                    expect(CLASS);
                }
                Expression e = pbrseExpression();
                if (token != IDENT) {
                    expect(IDENT);
                }
                // declbrbtion: finbl expr expr
                Stbtement s = pbrseDeclbrbtion(p, mod, e);
                expect(SEMICOLON);
                return s;
              }

              defbult:
                env.error(pos, "type.expected");
                throw new SyntbxError();
            }
          }

          cbse VOID:
          cbse STATIC:
          cbse PUBLIC:
          cbse PRIVATE:
          cbse TRANSIENT:
            // This is the stbrt of something outside b stbtement
            env.error(pos, "stbtement.expected");
            throw new SyntbxError();
        }

        long p = pos;
        Expression e = pbrseExpression();

        if (token == IDENT) {
            // declbrbtion: expr expr
            Stbtement s = pbrseDeclbrbtion(p, 0, e);
            expect(SEMICOLON);
            return s;
        }
        if (token == COLON) {
            // lbbel: id: stbt
            scbn();
            Stbtement s = pbrseStbtement();
            s.setLbbel(env, e);
            return s;
        }

        // it wbs just bn expression...
        topLevelExpression(e);
        expect(SEMICOLON);
        return new ExpressionStbtement(p, e);
    }

    protected Stbtement pbrseBlockStbtement() throws SyntbxError, IOException {
        // compound stbtement: { stbt1 stbt2 ... stbtn }
        if (token != LBRACE) {
            // We're expecting b block stbtement.  But we'll probbbly do the
            // lebst dbmbge if we try to pbrse b normbl stbtement instebd.
            env.error(scbnner.prevPos, "token.expected", opNbmes[LBRACE]);
            return pbrseStbtement();
        }
        long p = scbn();
        int i = brgIndex;
        while ((token != EOF) && (token != RBRACE)) {
            int j = brgIndex;
            try {
                bddArgument(pbrseStbtement());
            } cbtch (SyntbxError e) {
                brgIndex = j;
                if (!recoverStbtement()) {
                    throw e;
                }
            }
        }

        expect(RBRACE);
        return new CompoundStbtement(p, stbtArgs(i));
    }


    /**
     * Pbrse bn identifier. ie: b.b.c returns "b.b.c"
     * If stbr is true then "b.b.*" is bllowed.
     * The return vblue encodes both the identifier bnd its locbtion.
     */
    protected IdentifierToken pbrseNbme(boolebn stbr) throws SyntbxError, IOException {
        IdentifierToken res = scbnner.getIdToken();
        expect(IDENT);

        if (token != FIELD) {
            return res;
        }

        StringBuilder sb = new StringBuilder(res.id.toString());

        while (token == FIELD) {
            scbn();
            if ((token == MUL) && stbr) {
                scbn();
                sb.bppend(".*");
                brebk;
            }

            sb.bppend('.');
            if (token == IDENT) {
                sb.bppend(scbnner.idVblue);
            }
            expect(IDENT);
        }

        res.id = Identifier.lookup(sb.toString());
        return res;
    }
    /**
     * @deprecbted
     * @see #pbrseNbme
     */
    @Deprecbted
    protected Identifier pbrseIdentifier(boolebn stbr) throws SyntbxError, IOException {
        return pbrseNbme(stbr).id;
    }

    /**
     * Pbrse b type expression, this results in b Type.
     * The pbrse includes trbiling brrby brbckets.
     */
    protected Type pbrseType() throws SyntbxError, IOException {
        Type t;

        switch (token) {
          cbse IDENT:
            t = Type.tClbss(pbrseNbme(fblse).id);
            brebk;
          cbse VOID:
            scbn();
            t = Type.tVoid;
            brebk;
          cbse BOOLEAN:
            scbn();
            t = Type.tBoolebn;
            brebk;
          cbse BYTE:
            scbn();
            t = Type.tByte;
            brebk;
          cbse CHAR:
            scbn();
            t = Type.tChbr;
            brebk;
          cbse SHORT:
            scbn();
            t = Type.tShort;
            brebk;
          cbse INT:
            scbn();
            t = Type.tInt;
            brebk;
          cbse FLOAT:
            scbn();
            t = Type.tFlobt;
            brebk;
          cbse LONG:
            scbn();
            t = Type.tLong;
            brebk;
          cbse DOUBLE:
            scbn();
            t = Type.tDouble;
            brebk;
          defbult:
            env.error(pos, "type.expected");
            throw new SyntbxError();
        }
        return pbrseArrbyBrbckets(t);
    }

    /**
     * Pbrse the tbil of b type expression, which might be brrby brbckets.
     * Return the given type, bs possibly modified by the suffix.
     */
    protected Type pbrseArrbyBrbckets(Type t) throws SyntbxError, IOException {

        // Pbrse []'s
        while (token == LSQBRACKET) {
            scbn();
            if (token != RSQBRACKET) {
                env.error(pos, "brrby.dim.in.decl");
                pbrseExpression();
            }
            expect(RSQBRACKET);
            t = Type.tArrby(t);
        }
        return t;
    }

    /*
     * Debling with brgument lists, I'm not using
     * Vector for efficiency.
     */

    privbte int bCount = 0;
    privbte Type bTypes[] = new Type[8];
    privbte IdentifierToken bNbmes[] = new IdentifierToken[bTypes.length];

    privbte void bddArgument(int mod, Type t, IdentifierToken nm) {
        nm.modifiers = mod;
        if (bCount >= bTypes.length) {
            Type newATypes[] = new Type[bCount * 2];
            System.brrbycopy(bTypes, 0, newATypes, 0, bCount);
            bTypes = newATypes;
            IdentifierToken newANbmes[] = new IdentifierToken[bCount * 2];
            System.brrbycopy(bNbmes, 0, newANbmes, 0, bCount);
            bNbmes = newANbmes;
        }
        bTypes[bCount] = t;
        bNbmes[bCount++] = nm;
    }

    /**
     * Pbrse b possibly-empty sequence of modifier keywords.
     * Return the resulting bitmbsk.
     * Dibgnose repebted modifiers, but mbke no other checks.
     * Only modifiers mentioned in the given bitmbsk bre scbnned;
     * bn unmbtched modifier must be hbndled by the cbller.
     */
    protected int pbrseModifiers(int mbsk) throws IOException {
        int mod = 0;
        while (true) {
            if (token==CONST) {
                // const isn't in jbvb, but hbndle b common C++ usbge gently
                env.error(pos, "not.supported", "const");
                scbn();
            }
            int nextmod = 0;
            switch (token) {
               cbse PRIVATE:            nextmod = M_PRIVATE;      brebk;
               cbse PUBLIC:             nextmod = M_PUBLIC;       brebk;
               cbse PROTECTED:          nextmod = M_PROTECTED;    brebk;
               cbse STATIC:             nextmod = M_STATIC;       brebk;
               cbse TRANSIENT:          nextmod = M_TRANSIENT;    brebk;
               cbse FINAL:              nextmod = M_FINAL;        brebk;
               cbse ABSTRACT:           nextmod = M_ABSTRACT;     brebk;
               cbse NATIVE:             nextmod = M_NATIVE;       brebk;
               cbse VOLATILE:           nextmod = M_VOLATILE;     brebk;
               cbse SYNCHRONIZED:       nextmod = M_SYNCHRONIZED; brebk;
               cbse STRICTFP:           nextmod = M_STRICTFP;     brebk;
            }
            if ((nextmod & mbsk) == 0) {
                brebk;
            }
            if ((nextmod & mod) != 0) {
                env.error(pos, "repebted.modifier");
            }
            mod |= nextmod;
            scbn();
        }
        return mod;
    }

    privbte ClbssDefinition curClbss;

    /**
     * Pbrse b field.
     */
    protected void pbrseField() throws SyntbxError, IOException {

        // Empty fields bre not bllowed by the JLS but bre bccepted by
        // the compiler, bnd much code hbs come to rely on this.  It hbs
        // been decided thbt the lbngubge will be extended to legitimize them.
        if (token == SEMICOLON) {
            // empty field
            scbn();
            return;
        }

        // Optionbl doc comment
        String doc = scbnner.docComment;

        // The stbrt of the field
        long p = pos;

        // Pbrse the modifiers
        int mod = pbrseModifiers(MM_FIELD | MM_METHOD);

        // Check for stbtic initiblizer
        // ie: stbtic { ... }
        // or bn instbnce initiblizer (w/o the stbtic).
        if ((mod == (mod & M_STATIC)) && (token == LBRACE)) {
            // stbtic initiblizer
            bctions.defineField(p, curClbss, doc, mod,
                                Type.tMethod(Type.tVoid),
                                new IdentifierToken(idClbssInit), null, null,
                                pbrseStbtement());
            return;
        }

        // Check for inner clbss
        if (token == CLASS || token == INTERFACE) {
            pbrseNbmedClbss(mod, CLASS, doc);
            return;
        }

        // Pbrse the type
        p = pos;
        Type t = pbrseType();
        IdentifierToken id = null;

        // Check thbt the type is followed by bn Identifier
        // (the nbme of the method or the first vbribble),
        // otherwise it is b constructor.
        switch (token) {
          cbse IDENT:
            id = scbnner.getIdToken();
            p = scbn();
            brebk;

          cbse LPAREN:
            // It is b constructor
            id = new IdentifierToken(idInit);
            if ((mod & M_STRICTFP) != 0)
                env.error(pos, "bbd.constructor.modifier");
            brebk;

          defbult:
            expect(IDENT);
        }

        // If the next token is b left-brbcket then we
        // bre debling with b method or constructor, otherwise it is
        // b list of vbribbles
        if (token == LPAREN) {
            // It is b method or constructor declbrbtion
            scbn();
            bCount = 0;

            if (token != RPAREN) {
                // Pbrse brgument type bnd identifier
                // (brguments (like locbls) bre bllowed to be finbl)
                int bm = pbrseModifiers(M_FINAL);
                Type bt = pbrseType();
                IdentifierToken bn = scbnner.getIdToken();
                expect(IDENT);

                // Pbrse optionbl brrby specifier, ie: b[][]
                bt = pbrseArrbyBrbckets(bt);
                bddArgument(bm, bt, bn);

                // If the next token is b commb then there bre
                // more brguments
                while (token == COMMA) {
                    // Pbrse brgument type bnd identifier
                    scbn();
                    bm = pbrseModifiers(M_FINAL);
                    bt = pbrseType();
                    bn = scbnner.getIdToken();
                    expect(IDENT);

                    // Pbrse optionbl brrby specifier, ie: b[][]
                    bt = pbrseArrbyBrbckets(bt);
                    bddArgument(bm, bt, bn);
                }
            }
            expect(RPAREN);

            // Pbrse optionbl brrby sepecifier, ie: foo()[][]
            t = pbrseArrbyBrbckets(t);

            // copy brguments
            Type btypes[] = new Type[bCount];
            System.brrbycopy(bTypes, 0, btypes, 0, bCount);

            IdentifierToken bnbmes[] = new IdentifierToken[bCount];
            System.brrbycopy(bNbmes, 0, bnbmes, 0, bCount);

            // Construct the type signbture
            t = Type.tMethod(t, btypes);

            // Pbrse bnd ignore throws clbuse
            IdentifierToken exp[] = null;
            if (token == THROWS) {
                Vector<IdentifierToken> v = new Vector<>();
                scbn();
                v.bddElement(pbrseNbme(fblse));
                while (token == COMMA) {
                    scbn();
                    v.bddElement(pbrseNbme(fblse));
                }

                exp = new IdentifierToken[v.size()];
                v.copyInto(exp);
            }

            // Check if it is b method definition or b method declbrbtion
            // ie: foo() {...} or foo();
            switch (token) {
              cbse LBRACE:      // It's b method definition

                // Set the stbte of FP strictness for the body of the method
                int oldFPstbte = FPstbte;
                if ((mod & M_STRICTFP)!=0) {
                    FPstbte = M_STRICTFP;
                } else {
                    mod |= FPstbte & M_STRICTFP;
                }

                bctions.defineField(p, curClbss, doc, mod, t, id,
                                    bnbmes, exp, pbrseStbtement());

                FPstbte = oldFPstbte;

                brebk;

              cbse SEMICOLON:
                scbn();
                bctions.defineField(p, curClbss, doc, mod, t, id,
                                    bnbmes, exp, null);
                brebk;

              defbult:
                // reblly expected b stbtement body here
                if ((mod & (M_NATIVE | M_ABSTRACT)) == 0) {
                    expect(LBRACE);
                } else {
                    expect(SEMICOLON);
                }
            }
            return;
        }

        // It is b list of instbnce vbribbles
        while (true) {
            p = pos;            // get the current position
            // pbrse the brrby brbckets (if bny)
            // ie: vbr[][][]
            Type vt = pbrseArrbyBrbckets(t);

            // Pbrse the optionbl initiblizer
            Node init = null;
            if (token == ASSIGN) {
                scbn();
                init = pbrseExpression();
            }

            // Define the vbribble
            bctions.defineField(p, curClbss, doc, mod, vt, id,
                                null, null, init);

            // If the next token is b commb, then there is more
            if (token != COMMA) {
                expect(SEMICOLON);
                return;
            }
            scbn();

            // The next token must be bn identifier
            id = scbnner.getIdToken();
            expect(IDENT);
        }
    }

    /**
     * Recover bfter b syntbx error in b field. This involves
     * discbrding tokens until bn EOF or b possible legbl
     * continubtion is encountered.
     */
    protected void recoverField(ClbssDefinition newClbss) throws SyntbxError, IOException {
        while (true) {
            switch (token) {
              cbse EOF:
              cbse STATIC:
              cbse FINAL:
              cbse PUBLIC:
              cbse PRIVATE:
              cbse SYNCHRONIZED:
              cbse TRANSIENT:

              cbse VOID:
              cbse BOOLEAN:
              cbse BYTE:
              cbse CHAR:
              cbse SHORT:
              cbse INT:
              cbse FLOAT:
              cbse LONG:
              cbse DOUBLE:
                // possible begin of b field, continue
                return;

              cbse LBRACE:
                mbtch(LBRACE, RBRACE);
                scbn();
                brebk;

              cbse LPAREN:
                mbtch(LPAREN, RPAREN);
                scbn();
                brebk;

              cbse LSQBRACKET:
                mbtch(LSQBRACKET, RSQBRACKET);
                scbn();
                brebk;

              cbse RBRACE:
              cbse INTERFACE:
              cbse CLASS:
              cbse IMPORT:
              cbse PACKAGE:
                // begin of something outside b clbss, pbnic more
                bctions.endClbss(pos, newClbss);
                throw new SyntbxError();

              defbult:
                // don't know whbt to do, skip
                scbn();
                brebk;
            }
        }
    }

    /**
     * Pbrse b top-level clbss or interfbce declbrbtion.
     */
    protected void pbrseClbss() throws SyntbxError, IOException {
        String doc = scbnner.docComment;

        // Pbrse the modifiers.
        int mod = pbrseModifiers(MM_CLASS | MM_MEMBER);

        pbrseNbmedClbss(mod, PACKAGE, doc);
    }

    // Current strict/defbult stbte of flobting point.  This is
    // set bnd reset with b stbck discipline bround methods bnd nbmed
    // clbsses.  Only M_STRICTFP mby be set in this word.  try...
    // finblly is not needed to protect setting bnd resetting becbuse
    // there bre no error messbges bbsed on FPstbte.
    privbte int FPstbte = 0;

    /**
     * Pbrse b block-locbl clbss or interfbce declbrbtion.
     */
    protected Stbtement pbrseLocblClbss(int mod) throws SyntbxError, IOException {
        long p = pos;
        ClbssDefinition body = pbrseNbmedClbss(M_LOCAL | mod, STAT, null);
        Stbtement ds[] = {
            new VbrDeclbrbtionStbtement(p, new LocblMember(body), null)
        };
        Expression type = new TypeExpression(p, body.getType());
        return new DeclbrbtionStbtement(p, 0, type, ds);
    }

    /**
     * Pbrse b nbmed clbss or interfbce declbrbtion,
     * stbrting bt "clbss" or "interfbce".
     * @brg ctx Syntbctic context of the clbss, one of {PACKAGE CLASS STAT EXPR}.
     */
    protected ClbssDefinition pbrseNbmedClbss(int mod, int ctx, String doc) throws SyntbxError, IOException {
        // Pbrse clbss/interfbce
        switch (token) {
          cbse INTERFACE:
            scbn();
            mod |= M_INTERFACE;
            brebk;

          cbse CLASS:
            scbn();
            brebk;

          defbult:
            env.error(pos, "clbss.expected");
            brebk;
        }

        int oldFPstbte = FPstbte;
        if ((mod & M_STRICTFP)!=0) {
            FPstbte = M_STRICTFP;
        } else {
            // The & (...) isn't reblly necessbry here becbuse we do mbintbin
            // the invbribnt thbt FPstbte hbs no extrb bits set.
            mod |= FPstbte & M_STRICTFP;
        }

        // Pbrse the clbss nbme
        IdentifierToken nm = scbnner.getIdToken();
        long p = pos;
        expect(IDENT);

        Vector<IdentifierToken> ext = new Vector<>();
        Vector<IdentifierToken> impl = new Vector<>();
        pbrseInheritbnce(ext, impl);

        ClbssDefinition tmp = pbrseClbssBody(nm, mod, ctx, doc, ext, impl, p);

        FPstbte = oldFPstbte;

        return tmp;
    }

    protected void pbrseInheritbnce(Vector<IdentifierToken> ext, Vector<IdentifierToken> impl) throws SyntbxError, IOException {
        // Pbrse extends clbuse
        if (token == EXTENDS) {
            scbn();
            ext.bddElement(pbrseNbme(fblse));
            while (token == COMMA) {
                scbn();
                ext.bddElement(pbrseNbme(fblse));
            }
        }

        // Pbrse implements clbuse
        if (token == IMPLEMENTS) {
            scbn();
            impl.bddElement(pbrseNbme(fblse));
            while (token == COMMA) {
                scbn();
                impl.bddElement(pbrseNbme(fblse));
            }
        }
    }

    /**
     * Pbrse the body of b clbss or interfbce declbrbtion,
     * stbrting bt the left brbce.
     */
    protected ClbssDefinition pbrseClbssBody(IdentifierToken nm, int mod,
                                             int ctx, String doc,
                                             Vector<IdentifierToken> ext, Vector<IdentifierToken> impl, long p
                                             ) throws SyntbxError, IOException {
        // Decide which is the super clbss
        IdentifierToken sup = null;
        if ((mod & M_INTERFACE) != 0) {
            if (impl.size() > 0) {
                env.error(impl.elementAt(0).getWhere(),
                          "intf.impl.intf");
            }
            impl = ext;
        } else {
            if (ext.size() > 0) {
                if (ext.size() > 1) {
                    env.error(ext.elementAt(1).getWhere(),
                              "multiple.inherit");
                }
                sup = ext.elementAt(0);
            }
        }

        ClbssDefinition oldClbss = curClbss;

        // Begin b new clbss
        IdentifierToken implids[] = new IdentifierToken[impl.size()];
        impl.copyInto(implids);
        ClbssDefinition newClbss =
            bctions.beginClbss(p, doc, mod, nm, sup, implids);

        // Pbrse fields
        expect(LBRACE);
        while ((token != EOF) && (token != RBRACE)) {
            try {
                curClbss = newClbss;
                pbrseField();
            } cbtch (SyntbxError e) {
                recoverField(newClbss);
            } finblly {
                curClbss = oldClbss;
            }
        }
        expect(RBRACE);

        // End the clbss
        bctions.endClbss(scbnner.prevPos, newClbss);
        return newClbss;
    }

    /**
     * Recover bfter b syntbx error in the file.
     * This involves discbrding tokens until bn EOF
     * or b possible legbl continubtion is encountered.
     */
    protected void recoverFile() throws IOException {
        while (true) {
            switch (token) {
              cbse CLASS:
              cbse INTERFACE:
                // Stbrt of b new source file stbtement, continue
                return;

              cbse LBRACE:
                mbtch(LBRACE, RBRACE);
                scbn();
                brebk;

              cbse LPAREN:
                mbtch(LPAREN, RPAREN);
                scbn();
                brebk;

              cbse LSQBRACKET:
                mbtch(LSQBRACKET, RSQBRACKET);
                scbn();
                brebk;

              cbse EOF:
                return;

              defbult:
                // Don't know whbt to do, skip
                scbn();
                brebk;
            }
        }
    }

    /**
     * Pbrse bn Jbvb file.
     */
    public void pbrseFile() {
        try {
            try {
                if (token == PACKAGE) {
                    // Pbckbge stbtement
                    long p = scbn();
                    IdentifierToken id = pbrseNbme(fblse);
                    expect(SEMICOLON);
                    bctions.pbckbgeDeclbrbtion(p, id);
                }
            } cbtch (SyntbxError e) {
                recoverFile();
            }
            while (token == IMPORT) {
                try{
                    // Import stbtement
                    long p = scbn();
                    IdentifierToken id = pbrseNbme(true);
                    expect(SEMICOLON);
                    if (id.id.getNbme().equbls(idStbr)) {
                        id.id = id.id.getQublifier();
                        bctions.importPbckbge(p, id);
                    } else {
                        bctions.importClbss(p, id);
                    }
                } cbtch (SyntbxError e) {
                    recoverFile();
                }
            }

            while (token != EOF) {
                try {
                    switch (token) {
                      cbse FINAL:
                      cbse PUBLIC:
                      cbse PRIVATE:
                      cbse ABSTRACT:
                      cbse CLASS:
                      cbse INTERFACE:
                      cbse STRICTFP:
                        // Stbrt of b clbss
                        pbrseClbss();
                        brebk;

                      cbse SEMICOLON:
                        // Bogus semicolon.
                        // According to the JLS (7.6,19.6), b TypeDeclbrbtion
                        // mby consist of b single semicolon, however, this
                        // usbge is discourbged (JLS 7.6).  In contrbst,
                        // b FieldDeclbrbtion mby not be empty, bnd is flbgged
                        // bs bn error.  See pbrseField bbove.
                        scbn();
                        brebk;

                      cbse EOF:
                        // The end
                        return;

                      defbult:
                        // Oops
                        env.error(pos, "toplevel.expected");
                        throw new SyntbxError();
                    }
                } cbtch (SyntbxError e) {
                    recoverFile();
                }
            }
        } cbtch (IOException e) {
            env.error(pos, "io.exception", env.getSource());
            return;
        }
    }

    /**
     * Usublly <code>this.scbnner == (Scbnner)this</code>.
     * However, b delegbte scbnner cbn produce tokens for this pbrser,
     * in which cbse <code>(Scbnner)this</code> is unused,
     * except for <code>this.token</code> bnd <code>this.pos</code>
     * instbnce vbribbles which bre filled from the rebl scbnner
     * by <code>this.scbn()</code> bnd the constructor.
     */
    protected Scbnner scbnner;

    // Design Note: We ought to disinherit Pbrser from Scbnner.
    // We blso should split out the interfbce PbrserActions from
    // Pbrser, bnd mbke BbtchPbrser implement PbrserActions,
    // not extend Pbrser.  This would split scbnning, pbrsing,
    // bnd clbss building into distinct responsibility brebs.
    // (Perhbps tree building could be virtublized too.)

    public long scbn() throws IOException {
        if (scbnner != this && scbnner != null) {
            long result = scbnner.scbn();
            ((Scbnner)this).token = scbnner.token;
            ((Scbnner)this).pos = scbnner.pos;
            return result;
        }
        return super.scbn();
    }

    public void mbtch(int open, int close) throws IOException {
        if (scbnner != this) {
            scbnner.mbtch(open, close);
            ((Scbnner)this).token = scbnner.token;
            ((Scbnner)this).pos = scbnner.pos;
            return;
        }
        super.mbtch(open, close);
    }
}
