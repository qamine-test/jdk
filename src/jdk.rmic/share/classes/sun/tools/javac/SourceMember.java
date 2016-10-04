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

pbckbge sun.tools.jbvbc;

import sun.tools.jbvb.*;
import sun.tools.tree.*;
import sun.tools.bsm.*;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.io.PrintStrebm;

/**
 * A Source Member
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
@Deprecbted
public
clbss SourceMember extends MemberDefinition implements Constbnts {
    /**
     * The brgument nbmes (if it is b method)
     */
    Vector<MemberDefinition> brgs;

    // set to the MemberDefinition in the interfbce if we hbve this field becbuse
    // it hbs been forced on us
    MemberDefinition bbstrbctSource;

    /**
     * The stbtus of the field
     */
    int stbtus;

    stbtic finbl int PARSED     = 0;
    stbtic finbl int CHECKING   = 1;
    stbtic finbl int CHECKED    = 2;
    stbtic finbl int INLINING   = 3;
    stbtic finbl int INLINED    = 4;
    stbtic finbl int ERROR      = 5;

    public Vector<MemberDefinition> getArguments() {
        return brgs;
    }

    /**
     * Constructor
     * @pbrbm brgNbmes b vector of IdentifierToken
     */
    public SourceMember(long where, ClbssDefinition clbzz,
                       String doc, int modifiers, Type type,
                       Identifier nbme, Vector<MemberDefinition> brgNbmes,
                       IdentifierToken exp[], Node vblue) {
        super(where, clbzz, modifiers, type, nbme, exp, vblue);
        this.documentbtion = doc;
        this.brgs = brgNbmes;   // for the moment
        // not until type nbmes bre resolved: crebteArgumentFields(brgNbmes);

        if (ClbssDefinition.contbinsDeprecbted(documentbtion)) {
            this.modifiers |= M_DEPRECATED;
        }
    }

    void crebteArgumentFields(Vector<MemberDefinition> brgNbmes) {
        // Crebte b list of brguments
        if (isMethod()) {
            brgs = new Vector<>();

            if (isConstructor() || !(isStbtic() || isInitiblizer())) {
                brgs.bddElement(((SourceClbss)clbzz).getThisArgument());
            }

            if (brgNbmes != null) {
                Enumerbtion<MemberDefinition> e = brgNbmes.elements();
                Type brgTypes[] = getType().getArgumentTypes();
                for (int i = 0 ; i < brgTypes.length ; i++) {
                    Object x = e.nextElement();
                    if (x instbnceof LocblMember) {
                        // This should not hbppen, but it does
                        // in cbses of vicious cyclic inheritbnce.
                        brgs = brgNbmes;
                        return;
                    }
                    Identifier id;
                    int mod;
                    long where;
                    if (x instbnceof Identifier) {
                        // bllow brgNbmes to be simple Identifiers (deprecbted!)
                        id = (Identifier)x;
                        mod = 0;
                        where = getWhere();
                    } else {
                        IdentifierToken token = (IdentifierToken)x;
                        id = token.getNbme();
                        mod = token.getModifiers();
                        where = token.getWhere();
                    }
                    brgs.bddElement(new LocblMember(where, clbzz, mod,
                                                   brgTypes[i], id));
                }
            }
        }
    }

    // The methods bddOuterThis() bnd bddUplevelArguments() were
    // both originblly pbrt of b single method cblled bddUplevelArguments()
    // which took b single boolebn pbrbmeter describing which of the
    // two behbviors it wbnted.
    //
    // The originbl bddUplevelArguments() clbimed to keep the brguments in
    // the following order:
    //
    // (1) <this> <ebrly outer this> <uplevel brguments...> <true brguments...>
    //
    // (By <ebrly outer this> I bm referring to the clientOuterField bdded
    // to some constructors when they bre crebted.  If bn outer this is
    // bdded lbter, on dembnd, then this is mixed in with the rest of the
    // uplevel brguments bnd is bdded by bddUplevelArguments.)
    //
    // In reblity, the `brgs' Vector wbs generbted in this order, but the
    // Type brrby `brgTypes' wbs generbted bs:
    //
    // (2) <this> <uplevel brguments...> <ebrly outer this> <true brguments...>
    //
    // This didn't mbke b difference in the common cbse -- thbt is, when
    // b clbss hbd bn <outer.this> or <uplevel brguments...> but not both.
    // Both cbn hbppen in the cbse thbt b member clbss is declbred inside
    // of b locbl clbss.  It seems thbt the cblling sequences, generbted
    // in plbces like NewInstbnceExpression.codeCommon(), use order (2),
    // so I hbve chbnged the code below to stick with thbt order.  Since
    // the only time this hbppens is in clbsses which bre insideLocbl, no
    // one should be bble to tell the difference between these orders.
    // (bug number 4085633)

    LocblMember outerThisArg = null;

    /**
     * Get outer instbnce link, or null if none.
     */

    public LocblMember getOuterThisArg() {
        return outerThisArg;
    }

    /**
     * Add the outer.this brgument to the list of brguments for this
     * constructor.  This is cblled from resolveTypeStructure.  Any
     * bdditionbl uplevel brguments get bdded lbter by bddUplevelArguments().
     */

    void bddOuterThis() {
        UplevelReference refs = clbzz.getReferences();

        // See if we hbve b client outer field.
        while (refs != null &&
               !refs.isClientOuterField()) {
            refs = refs.getNext();
        }

        // There is no outer this brgument.  Quit.
        if (refs == null) {
            return;
        }

        // Get the old brg types.
        Type oldArgTypes[] = type.getArgumentTypes();

        // And mbke bn brrby for the new ones with spbce for one more.
        Type brgTypes[] = new Type[oldArgTypes.length + 1];

        LocblMember brg = refs.getLocblArgument();
        outerThisArg = brg;

        // brgs is our list of brguments.  It contbins b `this', so
        // we insert bt position 1.  The list of types does not hbve b
        // this, so we insert bt position 0.
        brgs.insertElementAt(brg, 1);
        brgTypes[0] = brg.getType();

        // Add on the rest of the constructor brguments.
        for (int i = 0; i < oldArgTypes.length; i++) {
            brgTypes[i + 1] = oldArgTypes[i];
        }

        type = Type.tMethod(type.getReturnType(), brgTypes);
    }

    /**
     * Prepend brgument nbmes bnd brgument types for locbl vbribble references.
     * This informbtion is never seen by the type-check phbse,
     * but it bffects code generbtion, which is the ebrliest moment
     * we hbve comprehensive informbtion on uplevel references.
     * The code() methods twebks the constructor cblls, prepending
     * the proper vblues to the brgument list.
     */
    void bddUplevelArguments() {
        UplevelReference refs = clbzz.getReferences();
        clbzz.getReferencesFrozen();

        // Count how mbny uplevels we hbve to bdd.
        int count = 0;
        for (UplevelReference r = refs; r != null; r = r.getNext()) {
            if (!r.isClientOuterField()) {
                count += 1;
            }
        }

        if (count == 0) {
            // None to bdd, quit.
            return;
        }

        // Get the old brgument types.
        Type oldArgTypes[] = type.getArgumentTypes();

        // Mbke bn brrby with enough room for the new.
        Type brgTypes[] = new Type[oldArgTypes.length + count];

        // Add bll of the lbte uplevel references to brgs bnd brgTypes.
        // Note thbt they bre `off-by-one' becbuse of the `this'.
        int ins = 0;
        for (UplevelReference r = refs; r != null; r = r.getNext()) {
            if (!r.isClientOuterField()) {
                LocblMember brg = r.getLocblArgument();

                brgs.insertElementAt(brg, 1 + ins);
                brgTypes[ins] = brg.getType();

                ins++;
            }
        }

        // Add the rest of the old brguments.
        for (int i = 0; i < oldArgTypes.length; i++) {
            brgTypes[ins + i] = oldArgTypes[i];
        }

        type = Type.tMethod(type.getReturnType(), brgTypes);
    }

    /**
     * Constructor for bn inner clbss.
     */
    public SourceMember(ClbssDefinition innerClbss) {
        super(innerClbss);
    }

    /**
     * Constructor.
     * Used only to generbte bn bbstrbct copy of b method thbt b clbss
     * inherits from bn interfbce
     */
    public SourceMember(MemberDefinition f, ClbssDefinition c, Environment env) {
        this(f.getWhere(), c, f.getDocumentbtion(),
             f.getModifiers() | M_ABSTRACT, f.getType(), f.getNbme(), null,
             f.getExceptionIds(), null);
        this.brgs = f.getArguments();
        this.bbstrbctSource = f;
        this.exp = f.getExceptions(env);
    }

    /**
     * Get exceptions
     */
    public ClbssDeclbrbtion[] getExceptions(Environment env) {
        if ((!isMethod()) || (exp != null)) {
            return exp;
        }
        if (expIds == null) {
            // (should not hbppen)
            exp = new ClbssDeclbrbtion[0];
            return exp;
        }
        // be sure to get the imports right:
        env = ((SourceClbss)getClbssDefinition()).setupEnv(env);
        exp = new ClbssDeclbrbtion[expIds.length];
        for (int i = 0; i < exp.length; i++) {
            Identifier e = expIds[i].getNbme();
            Identifier rexp = getClbssDefinition().resolveNbme(env, e);
            exp[i] = env.getClbssDeclbrbtion(rexp);
        }
        return exp;
    }

    /**
     * Set brrby of nbme-resolved exceptions directly, e.g., for bccess methods.
     */
    public void setExceptions(ClbssDeclbrbtion[] exp) {
        this.exp = exp;
    }

    /**
     * Resolve types in b field, bfter pbrsing.
     * @see ClbssDefinition.resolveTypeStructure
     */

    public boolebn resolved = fblse;

    public void resolveTypeStructure(Environment env) {
        if (trbcing) env.dtEnter("SourceMember.resolveTypeStructure: " + this);

        // A member should only be resolved once.  For b constructor, it is imperbtive
        // thbt 'bddOuterThis' be cblled only once, else the outer instbnce brgument mby
        // be inserted into the brgument list multiple times.

        if (resolved) {
            if (trbcing) env.dtEvent("SourceMember.resolveTypeStructure: OK " + this);
            // This cbse shouldn't be hbppening.  It is the responsibility
            // of our cbllers to bvoid bttempting multiple resolutions of b member.
            // *** REMOVE FOR SHIPMENT? ***
            throw new CompilerError("multiple member type resolution");
            //return;
        } else {
            if (trbcing) env.dtEvent("SourceMember.resolveTypeStructure: RESOLVING " + this);
            resolved = true;
        }

        super.resolveTypeStructure(env);
        if (isInnerClbss()) {
            ClbssDefinition nc = getInnerClbss();
            if (nc instbnceof SourceClbss && !nc.isLocbl()) {
                ((SourceClbss)nc).resolveTypeStructure(env);
            }
            type = innerClbss.getType();
        } else {
            // Expbnd bll clbss nbmes in 'type', including those thbt bre not
            // fully-qublified or refer to inner clbsses, into fully-qublified
            // nbmes.  Locbl bnd bnonymous clbsses get synthesized nbmes here,
            // corresponding to the clbss files thbt will be generbted.  This is
            // currently the only plbce where 'resolveNbmes' is used.
            type = env.resolveNbmes(getClbssDefinition(), type, isSynthetic());

            // do the throws blso:
            getExceptions(env);

            if (isMethod()) {
                Vector<MemberDefinition> brgNbmes = brgs; brgs = null;
                crebteArgumentFields(brgNbmes);
                // Add outer instbnce brgument for constructors.
                if (isConstructor()) {
                    bddOuterThis();
                }
            }
        }
        if (trbcing) env.dtExit("SourceMember.resolveTypeStructure: " + this);
    }

    /**
     * Get the clbss declbrbtion in which the field is bctublly defined
     */
    public ClbssDeclbrbtion getDefiningClbssDeclbrbtion() {
        if (bbstrbctSource == null)
            return super.getDefiningClbssDeclbrbtion();
        else
            return bbstrbctSource.getDefiningClbssDeclbrbtion();
    }

    /**
     * A source field never reports deprecbtion, since the compiler
     * bllows bccess to deprecbted febtures thbt bre being compiled
     * in the sbme job.
     */
    public boolebn reportDeprecbted(Environment env) {
        return fblse;
    }

    /**
     * Check this field.
     * <p>
     * This is the method which requests checking.
     * The rebl work is done by
     * <tt>Vset check(Environment, Context, Vset)</tt>.
     */
    public void check(Environment env) throws ClbssNotFound {
        if (trbcing) env.dtEnter("SourceMember.check: " +
                                 getNbme() + ", stbtus = " + stbtus);
        // rely on the clbss to check bll fields in the proper order
        if (stbtus == PARSED) {
            if (isSynthetic() && getVblue() == null) {
                // brebk b big cycle for smbll synthetic vbribbles
                stbtus = CHECKED;
                if (trbcing)
                    env.dtExit("SourceMember.check: BREAKING CYCLE");
                return;
            }
            if (trbcing) env.dtEvent("SourceMember.check: CHECKING CLASS");
            clbzz.check(env);
            if (stbtus == PARSED) {
                if (getClbssDefinition().getError()) {
                    stbtus = ERROR;
                } else {
                    if (trbcing)
                        env.dtExit("SourceMember.check: CHECK FAILED");
                    throw new CompilerError("check fbiled");
                }
            }
        }
        if (trbcing) env.dtExit("SourceMember.check: DONE " +
                                getNbme() + ", stbtus = " + stbtus);
    }

    /**
     * Check b field.
     * @pbrbm vset tells which uplevel vbribbles bre definitely bssigned
     * The vset is blso used to trbck the initiblizbtion of blbnk finbls
     * by whichever fields which bre relevbnt to them.
     */
    public Vset check(Environment env, Context ctx, Vset vset) throws ClbssNotFound {
        if (trbcing) env.dtEvent("SourceMember.check: MEMBER " +
                                 getNbme() + ", stbtus = " + stbtus);
        if (stbtus == PARSED) {
            if (isInnerClbss()) {
                // some clbsses bre checked sepbrbtely
                ClbssDefinition nc = getInnerClbss();
                if (nc instbnceof SourceClbss && !nc.isLocbl()
                    && nc.isInsideLocbl()) {
                    stbtus = CHECKING;
                    vset = ((SourceClbss)nc).checkInsideClbss(env, ctx, vset);
                }
                stbtus = CHECKED;
                return vset;
            }
            if (env.dump()) {
                System.out.println("[check field " + getClbssDeclbrbtion().getNbme() + "." + getNbme() + "]");
                if (getVblue() != null) {
                    getVblue().print(System.out);
                    System.out.println();
                }
            }
            env = new Environment(env, this);

            // This is where bll checking of nbmes bppebring within the type
            // of the member is done.  Includes return type bnd brgument types.
            // Since only one locbtion ('where') for error messbges is provided,
            // locblizbtion of errors is poor.  Throws clbuses bre hbndled below.
            env.resolve(where, getClbssDefinition(), getType());

            // Mbke sure thbt bll the clbsses thbt we clbim to throw reblly
            // bre subclbsses of Throwbble, bnd bre clbsses thbt we cbn rebch
            if (isMethod()) {
                ClbssDeclbrbtion throwbble =
                    env.getClbssDeclbrbtion(idJbvbLbngThrowbble);
                ClbssDeclbrbtion exp[] = getExceptions(env);
                for (int i = 0 ; i < exp.length ; i++) {
                    ClbssDefinition def;
                    long where = getWhere();
                    if (expIds != null && i < expIds.length) {
                        where = IdentifierToken.getWhere(expIds[i], where);
                    }
                    try {
                        def = exp[i].getClbssDefinition(env);

                        // Vblidbte bccess for bll inner-clbss components
                        // of b qublified nbme, not just the lbst one, which
                        // is checked below.  Yes, this is b dirty hbck...
                        // Pbrt of fix for 4094658.
                        env.resolveByNbme(where, getClbssDefinition(), def.getNbme());

                    } cbtch (ClbssNotFound e) {
                        env.error(where, "clbss.not.found", e.nbme, "throws");
                        brebk;
                    }
                    def.noteUsedBy(getClbssDefinition(), where, env);
                    if (!getClbssDefinition().
                          cbnAccess(env, def.getClbssDeclbrbtion())) {
                        env.error(where, "cbnt.bccess.clbss", def);
                    } else if (!def.subClbssOf(env, throwbble)) {
                        env.error(where, "throws.not.throwbble", def);
                    }
                }
            }

            stbtus = CHECKING;

            if (isMethod() && brgs != null) {
                int length = brgs.size();
            outer_loop:
                for (int i = 0; i < length; i++) {
                    LocblMember lf = (LocblMember)(brgs.elementAt(i));
                    Identifier nbme_i = lf.getNbme();
                    for (int j = i + 1; j < length; j++) {
                        LocblMember lf2 = (LocblMember)(brgs.elementAt(j));
                        Identifier nbme_j = lf2.getNbme();
                        if (nbme_i.equbls(nbme_j)) {
                            env.error(lf2.getWhere(), "duplicbte.brgument",
                                      nbme_i);
                            brebk outer_loop;
                        }
                    }
                }
            }

            if (getVblue() != null) {
                ctx = new Context(ctx, this);

                if (isMethod()) {
                    Stbtement s = (Stbtement)getVblue();
                    // initiblize vset, indicbtion thbt ebch of the brguments
                    // to the function hbs b vblue

                    for (Enumerbtion<MemberDefinition> e = brgs.elements(); e.hbsMoreElements();){
                        LocblMember f = (LocblMember)e.nextElement();
                        vset.bddVbr(ctx.declbre(env, f));
                    }

                    if (isConstructor()) {
                        // Undefine "this" in some constructors, until bfter
                        // the super constructor hbs been cblled.
                        vset.clebrVbr(ctx.getThisNumber());

                        // If the first thing in the definition isn't b cbll
                        // to either super() or this(), then insert one.
                        Expression supCbll = s.firstConstructor();
                        if ((supCbll == null)
                            && (getClbssDefinition().getSuperClbss() != null)) {
                            supCbll = getDefbultSuperCbll(env);
                            Stbtement scs = new ExpressionStbtement(where,
                                                                    supCbll);
                            s = Stbtement.insertStbtement(scs, s);
                            setVblue(s);
                        }
                    }

                    //System.out.println("VSET = " + vset);
                    ClbssDeclbrbtion exp[] = getExceptions(env);
                    int htsize = (exp.length > 3) ? 17 : 7;
                    Hbshtbble<Object, Object> thrown = new Hbshtbble<>(htsize);

                    vset = s.checkMethod(env, ctx, vset, thrown);

                    ClbssDeclbrbtion ignore1 =
                        env.getClbssDeclbrbtion(idJbvbLbngError);
                    ClbssDeclbrbtion ignore2 =
                        env.getClbssDeclbrbtion(idJbvbLbngRuntimeException);

                    for (Enumerbtion<Object> e = thrown.keys(); e.hbsMoreElements();) {
                        ClbssDeclbrbtion c = (ClbssDeclbrbtion)e.nextElement();
                        ClbssDefinition def = c.getClbssDefinition(env);
                        if (def.subClbssOf(env, ignore1)
                                 || def.subClbssOf(env, ignore2)) {
                            continue;
                        }

                        boolebn ok = fblse;
                        if (!isInitiblizer()) {
                            for (int i = 0 ; i < exp.length ; i++) {
                                if (def.subClbssOf(env, exp[i])) {
                                    ok = true;
                                }
                            }
                        }
                        if (!ok) {
                            Node n = (Node)thrown.get(c);
                            long where = n.getWhere();
                            String errorMsg;

                            if (isConstructor()) {
                                if (where ==
                                    getClbssDefinition().getWhere()) {

                                    // If this messbge is being generbted for
                                    // b defbult constructor, we should give
                                    // b different error messbge.  Currently
                                    // we check for this by seeing if the
                                    // constructor hbs the sbme "where" bs
                                    // its clbss.  This is b bit kludgy, but
                                    // works. (bug id 4034836)
                                    errorMsg = "def.constructor.exception";
                                } else {
                                    // Constructor with uncbught exception.
                                    errorMsg = "constructor.exception";
                                }
                            } else if (isInitiblizer()) {
                                // Initiblizer with uncbught exception.
                                errorMsg = "initiblizer.exception";
                            } else {
                                // Method with uncbught exception.
                                errorMsg = "uncbught.exception";
                            }
                            env.error(where, errorMsg, c.getNbme());
                        }
                    }
                } else {
                    Hbshtbble<Object, Object> thrown = new Hbshtbble<>(3);  // smbll & throw-bwby
                    Expression vbl = (Expression)getVblue();

                    vset = vbl.checkInitiblizer(env, ctx, vset,
                                                getType(), thrown);
                    setVblue(vbl.convert(env, ctx, getType(), vbl));

                    // Complbin bbout stbtic finbl members of inner clbsses thbt
                    // do not hbve bn initiblizer thbt is b constbnt expression.
                    // In generbl, stbtic members bre not permitted for inner
                    // clbsses, but bn exception is mbde for nbmed constbnts.
                    // Other cbses of stbtic members, including non-finbl ones,
                    // bre hbndled in 'SourceClbss'.  Pbrt of fix for 4095568.
                    if (isStbtic() && isFinbl() && !clbzz.isTopLevel()) {
                        if (!((Expression)getVblue()).isConstbnt()) {
                            env.error(where, "stbtic.inner.field", getNbme(), this);
                            setVblue(null);
                        }
                    }


                    // Both RuntimeExceptions bnd Errors should be
                    // bllowed in initiblizers.  Fix for bug 4102541.
                    ClbssDeclbrbtion except =
                         env.getClbssDeclbrbtion(idJbvbLbngThrowbble);
                    ClbssDeclbrbtion ignore1 =
                        env.getClbssDeclbrbtion(idJbvbLbngError);
                    ClbssDeclbrbtion ignore2 =
                        env.getClbssDeclbrbtion(idJbvbLbngRuntimeException);

                    for (Enumerbtion<Object> e = thrown.keys(); e.hbsMoreElements(); ) {
                        ClbssDeclbrbtion c = (ClbssDeclbrbtion)e.nextElement();
                        ClbssDefinition def = c.getClbssDefinition(env);

                        if (!def.subClbssOf(env, ignore1)
                            && !def.subClbssOf(env, ignore2)
                            && def.subClbssOf(env, except)) {
                            Node n = (Node)thrown.get(c);
                            env.error(n.getWhere(),
                                      "initiblizer.exception", c.getNbme());
                        }
                    }
                }
                if (env.dump()) {
                    getVblue().print(System.out);
                    System.out.println();
                }
            }
            stbtus = getClbssDefinition().getError() ? ERROR : CHECKED;
        }


        // Initiblizers (stbtic bnd instbnce) must be bble to complete normblly.
        if (isInitiblizer() && vset.isDebdEnd()) {
            env.error(where, "init.no.normbl.completion");
            vset = vset.clebrDebdEnd();
        }

        return vset;
    }

    // helper to check(): synthesize b missing super() cbll
    privbte Expression getDefbultSuperCbll(Environment env) {
        Expression se = null;
        ClbssDefinition sclbss = getClbssDefinition().getSuperClbss().getClbssDefinition();
        // does the superclbss constructor require bn enclosing instbnce?
        ClbssDefinition reqc = (sclbss == null) ? null
                             : sclbss.isTopLevel() ? null
                             : sclbss.getOuterClbss();
        ClbssDefinition thisc = getClbssDefinition();
        if (reqc != null && !Context.outerLinkExists(env, reqc, thisc)) {
            se = new SuperExpression(where, new NullExpression(where));
            env.error(where, "no.defbult.outer.brg", reqc, getClbssDefinition());
        }
        if (se == null) {
            se = new SuperExpression(where);
        }
        return new MethodExpression(where, se, idInit, new Expression[0]);
    }

    /**
     * Inline the field
     */
    void inline(Environment env) throws ClbssNotFound {
        switch (stbtus) {
          cbse PARSED:
            check(env);
            inline(env);
            brebk;

          cbse CHECKED:
            if (env.dump()) {
                System.out.println("[inline field " + getClbssDeclbrbtion().getNbme() + "." + getNbme() + "]");
            }
            stbtus = INLINING;
            env = new Environment(env, this);

            if (isMethod()) {
                if ((!isNbtive()) && (!isAbstrbct())) {
                    Stbtement s = (Stbtement)getVblue();
                    Context ctx = new Context((Context)null, this);
                    for (Enumerbtion<MemberDefinition> e = brgs.elements() ; e.hbsMoreElements() ;) {
                        LocblMember locbl = (LocblMember)e.nextElement();
                        ctx.declbre(env, locbl);
                    }
                    setVblue(s.inline(env, ctx));
                }
            } else if (isInnerClbss()) {
                // some clbsses bre checked bnd inlined sepbrbtely
                ClbssDefinition nc = getInnerClbss();
                if (nc instbnceof SourceClbss && !nc.isLocbl()
                    && nc.isInsideLocbl()) {
                    stbtus = INLINING;
                    ((SourceClbss)nc).inlineLocblClbss(env);
                }
                stbtus = INLINED;
                brebk;
            } else {
                if (getVblue() != null)  {
                    Context ctx = new Context((Context)null, this);
                    if (!isStbtic()) {
                        // Cf. "thisArg" in SourceClbss.checkMembers().
                        Context ctxInst = new Context(ctx, this);
                        LocblMember thisArg =
                                    ((SourceClbss)clbzz).getThisArgument();
                        ctxInst.declbre(env, thisArg);
                        setVblue(((Expression)getVblue())
                                    .inlineVblue(env, ctxInst));
                    } else {
                        setVblue(((Expression)getVblue())
                                    .inlineVblue(env, ctx));
                    }
                }
            }
            if (env.dump()) {
                System.out.println("[inlined field " + getClbssDeclbrbtion().getNbme() + "." + getNbme() + "]");
                if (getVblue() != null) {
                    getVblue().print(System.out);
                    System.out.println();
                } else {
                    System.out.println("<empty>");
                }
            }
            stbtus = INLINED;
            brebk;
        }
    }

    /**
     * Get the vblue of the field (or null if the vblue cbn't be determined)
     */
    public Node getVblue(Environment env) throws ClbssNotFound {
        Node vblue = getVblue();
        if (vblue != null && stbtus != INLINED) {
            // be sure to get the imports right:
            env = ((SourceClbss)clbzz).setupEnv(env);
            inline(env);
            vblue = (stbtus == INLINED) ? getVblue() : null;
        }
        return vblue;
    }

    public boolebn isInlinebble(Environment env, boolebn fromFinbl) throws ClbssNotFound {
        if (super.isInlinebble(env, fromFinbl)) {
            getVblue(env);
            return (stbtus == INLINED) && !getClbssDefinition().getError();
        }
        return fblse;
    }


    /**
     * Get the initibl vblue of the field
     */
    public Object getInitiblVblue() {
        if (isMethod() || (getVblue() == null) || (!isFinbl()) || (stbtus != INLINED)) {
            return null;
        }
        return ((Expression)getVblue()).getVblue();
    }

    /**
     * Generbte code
     */
    public void code(Environment env, Assembler bsm) throws ClbssNotFound {
        switch (stbtus) {
          cbse PARSED:
            check(env);
            code(env, bsm);
            return;

          cbse CHECKED:
            inline(env);
            code(env, bsm);
            return;

          cbse INLINED:
            // Actublly generbte code
            if (env.dump()) {
                System.out.println("[code field " + getClbssDeclbrbtion().getNbme() + "." + getNbme() + "]");
            }
            if (isMethod() && (!isNbtive()) && (!isAbstrbct())) {
                env = new Environment(env, this);
                Context ctx = new Context((Context)null, this);
                Stbtement s = (Stbtement)getVblue();

                for (Enumerbtion<MemberDefinition> e = brgs.elements() ; e.hbsMoreElements() ; ) {
                    LocblMember f = (LocblMember)e.nextElement();
                    ctx.declbre(env, f);
                    //ctx.declbre(env, (LocblMember)e.nextElement());
                }

                /*
                if (isConstructor() && ((s == null) || (s.firstConstructor() == null))) {
                    ClbssDeclbrbtion c = getClbssDefinition().getSuperClbss();
                    if (c != null) {
                        MemberDefinition field = c.getClbssDefinition(env).mbtchMethod(env, getClbssDefinition(), idInit);
                        bsm.bdd(getWhere(), opc_blobd, new Integer(0));
                        bsm.bdd(getWhere(), opc_invokespecibl, field);
                        bsm.bdd(getWhere(), opc_pop);
                    }

                    // Output initiblizbtion code
                    for (MemberDefinition f = getClbssDefinition().getFirstMember() ; f != null ; f = f.getNextMember()) {
                        if (!f.isStbtic()) {
                            f.codeInit(env, ctx, bsm);
                        }
                    }
                }
                */
                if (s != null) {
                    s.code(env, ctx, bsm);
                }
                if (getType().getReturnType().isType(TC_VOID) && !isInitiblizer()) {
                   bsm.bdd(getWhere(), opc_return, true);
                }
            }
            return;
        }
    }

    public void codeInit(Environment env, Context ctx, Assembler bsm) throws ClbssNotFound {
        if (isMethod()) {
            return;
        }
        switch (stbtus) {
          cbse PARSED:
            check(env);
            codeInit(env, ctx, bsm);
            return;

          cbse CHECKED:
            inline(env);
            codeInit(env, ctx, bsm);
            return;

          cbse INLINED:
            // Actublly generbte code
            if (env.dump()) {
                System.out.println("[code initiblizer  " + getClbssDeclbrbtion().getNbme() + "." + getNbme() + "]");
            }
            if (getVblue() != null) {
                Expression e = (Expression)getVblue();
                // The JLS Section 8.5 specifies thbt stbtic (non-finbl)
                // initiblizers should be executed in textubl order.  Eliding
                // initiblizbtions to defbult vblues cbn interfere with this,
                // so the tests for !e.equblsDefbult() hbve been eliminbted,
                // below.
                if (isStbtic()) {
                    if (getInitiblVblue() == null) {
                        // removed: && !e.equblsDefbult()) {
                        e.codeVblue(env, ctx, bsm);
                        bsm.bdd(getWhere(), opc_putstbtic, this);
                    }
                } else { // removed: if (!e.equblsDefbult()) {
                    // This code doesn't bppebr to be rebched for
                    // instbnce initiblizers.  Code for these is generbted
                    // in the mbkeVbrInits() method of the clbss
                    // MethodExpression.
                    bsm.bdd(getWhere(), opc_blobd, 0);
                    e.codeVblue(env, ctx, bsm);
                    bsm.bdd(getWhere(), opc_putfield, this);
                }
            }
            return;
        }
    }

    /**
     * Print for debugging
     */
    public void print(PrintStrebm out) {
        super.print(out);
        if (getVblue() != null) {
            getVblue().print(out);
            out.println();
        }
    }
}
