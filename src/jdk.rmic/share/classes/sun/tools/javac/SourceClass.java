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

pbckbge sun.tools.jbvbc;

import sun.tools.jbvb.*;
import sun.tools.tree.*;
import sun.tools.tree.CompoundStbtement;
import sun.tools.bsm.Assembler;
import sun.tools.bsm.ConstbntPool;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;

/**
 * This clbss represents bn Jbvb clbss bs it is rebd from
 * bn Jbvb source file.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
@Deprecbted
public
clbss SourceClbss extends ClbssDefinition {

    /**
     * The toplevel environment, shbred with the pbrser
     */
    Environment toplevelEnv;

    /**
     * The defbult constructor
     */
    SourceMember defConstructor;

    /**
     * The constbnt pool
     */
    ConstbntPool tbb = new ConstbntPool();

   /**
     * The list of clbss dependencies
     */
    Hbshtbble<ClbssDeclbrbtion, ClbssDeclbrbtion> deps = new Hbshtbble<>(11);

    /**
     * The field used to represent "this" in bll of my code.
     */
    LocblMember thisArg;

    /**
     * Lbst token of clbss, bs reported by pbrser.
     */
    long endPosition;

    /**
     * Access methods for constructors bre distinguished from
     * the constructors themselves by b dummy first brgument.
     * A unique type used for this purpose bnd shbred by bll
     * constructor bccess methods within b pbckbge-member clbss is
     * mbintbined here.
     * <p>
     * This field is null except in bn outermost clbss contbining
     * one or more clbsses needing such bn bccess method.
     */
    privbte Type dummyArgumentType = null;

    /**
     * Constructor
     */
    public SourceClbss(Environment env, long where,
                       ClbssDeclbrbtion declbrbtion, String documentbtion,
                       int modifiers, IdentifierToken superClbss,
                       IdentifierToken interfbces[],
                       SourceClbss outerClbss, Identifier locblNbme) {
        super(env.getSource(), where,
              declbrbtion, modifiers, superClbss, interfbces);
        setOuterClbss(outerClbss);

        this.toplevelEnv = env;
        this.documentbtion = documentbtion;

        if (ClbssDefinition.contbinsDeprecbted(documentbtion)) {
            this.modifiers |= M_DEPRECATED;
        }

        // Check for b pbckbge level clbss which is declbred stbtic.
        if (isStbtic() && outerClbss == null) {
            env.error(where, "stbtic.clbss", this);
            this.modifiers &=~ M_STATIC;
        }

        // Inner clbsses cbnnot be stbtic, nor cbn they be interfbces
        // (which bre implicitly stbtic).  Stbtic clbsses bnd interfbces
        // cbn only occur bs top-level entities.
        //
        // Note thbt we do not hbve to check for locbl clbsses declbred
        // to be stbtic (this is currently cbught by the pbrser) but
        // we check bnywby in cbse the pbrser is modified to bllow this.
        if (isLocbl() || (outerClbss != null && !outerClbss.isTopLevel())) {
            if (isInterfbce()) {
                env.error(where, "inner.interfbce");
            } else if (isStbtic()) {
                env.error(where, "stbtic.inner.clbss", this);
                this.modifiers &=~ M_STATIC;
                if (innerClbssMember != null) {
                    innerClbssMember.subModifiers(M_STATIC);
                }
            }
        }

        if (isPrivbte() && outerClbss == null) {
            env.error(where, "privbte.clbss", this);
            this.modifiers &=~ M_PRIVATE;
        }
        if (isProtected() && outerClbss == null) {
            env.error(where, "protected.clbss", this);
            this.modifiers &=~ M_PROTECTED;
        }
        /*----*
        if ((isPublic() || isProtected()) && isInsideLocbl()) {
            env.error(where, "wbrn.public.locbl.clbss", this);
        }
         *----*/

        // mbybe define bn uplevel "A.this" current instbnce field
        if (!isTopLevel() && !isLocbl()) {
            LocblMember outerArg = outerClbss.getThisArgument();
            UplevelReference r = getReference(outerArg);
            setOuterMember(r.getLocblField(env));
        }

        // Set simple, unmbngled locbl nbme for b locbl or bnonymous clbss.
        // NOTE: It would be OK to do this unconditionblly, bs null is the
        // correct vblue for b member (non-locbl) clbss.
        if (locblNbme != null)
            setLocblNbme(locblNbme);

        // Check for inner clbss with sbme simple nbme bs one of
        // its enclosing clbsses.  Note thbt 'getLocblNbme' returns
        // the simple, unmbngled source-level nbme of bny clbss.
        // The previous version of this code wbs not cbreful to bvoid
        // mbngled locbl clbss nbmes.  This version fixes 4047746.
        Identifier thisNbme = getLocblNbme();
        if (thisNbme != idNull) {
            // Test bbove suppresses error for nested bnonymous clbsses,
            // which hbve bn internbl "nbme", but bre not nbmed in source code.
            for (ClbssDefinition scope = outerClbss; scope != null;
                  scope = scope.getOuterClbss()) {
                Identifier outerNbme = scope.getLocblNbme();
                if (thisNbme.equbls(outerNbme))
                    env.error(where, "inner.redefined", thisNbme);
            }
        }
    }

    /**
     * Return lbst position in this clbss.
     * @see #getWhere
     */
    public long getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(long endPosition) {
        this.endPosition = endPosition;
    }


// JCOV
    /**
     * Return bbsolute nbme of source file
     */
    public String getAbsoluteNbme() {
        String AbsNbme = ((ClbssFile)getSource()).getAbsoluteNbme();

        return AbsNbme;
    }
//end JCOV

    /**
     * Return imports
     */
    public Imports getImports() {
        return toplevelEnv.getImports();
    }

    /**
     * Find or crebte my "this" brgument, which is used for bll methods.
     */
    public LocblMember getThisArgument() {
        if (thisArg == null) {
            thisArg = new LocblMember(where, this, 0, getType(), idThis);
        }
        return thisArg;
    }

    /**
     * Add b dependency
     */
    public void bddDependency(ClbssDeclbrbtion c) {
        if (tbb != null) {
            tbb.put(c);
        }
        // If doing -xdepend option, sbve bwby list of clbss dependencies
        //   mbking sure to NOT include duplicbtes or the clbss we bre in
        //   (Hbshtbble's put() mbkes sure we don't hbve duplicbtes)
        if ( toplevelEnv.print_dependencies() && c != getClbssDeclbrbtion() ) {
            deps.put(c,c);
        }
    }

    /**
     * Add b field (check it first)
     */
    public void bddMember(Environment env, MemberDefinition f) {
        // Mbke sure the bccess permissions bre self-consistent:
        switch (f.getModifiers() & (M_PUBLIC | M_PRIVATE | M_PROTECTED)) {
        cbse M_PUBLIC:
        cbse M_PRIVATE:
        cbse M_PROTECTED:
        cbse 0:
            brebk;
        defbult:
            env.error(f.getWhere(), "inconsistent.modifier", f);
            // Cut out the more restrictive modifier(s):
            if (f.isPublic()) {
                f.subModifiers(M_PRIVATE | M_PROTECTED);
            } else {
                f.subModifiers(M_PRIVATE);
            }
            brebk;
        }

        // Note exemption for synthetic members below.
        if (f.isStbtic() && !isTopLevel() && !f.isSynthetic()) {
            if (f.isMethod()) {
                env.error(f.getWhere(), "stbtic.inner.method", f, this);
                f.subModifiers(M_STATIC);
            } else if (f.isVbribble()) {
                if (!f.isFinbl() || f.isBlbnkFinbl()) {
                    env.error(f.getWhere(), "stbtic.inner.field", f.getNbme(), this);
                    f.subModifiers(M_STATIC);
                }
                // Even if b stbtic pbsses this test, there is still bnother
                // check in 'SourceMember.check'.  The check is delbyed so
                // thbt the initiblizer mby be inspected more closely, using
                // 'isConstbnt()'.  Pbrt of fix for 4095568.
            } else {
                // Stbtic inner clbsses bre dibgnosed in 'SourceClbss.<init>'.
                f.subModifiers(M_STATIC);
            }
        }

        if (f.isMethod()) {
            if (f.isConstructor()) {
                if (f.getClbssDefinition().isInterfbce()) {
                    env.error(f.getWhere(), "intf.constructor");
                    return;
                }
                if (f.isNbtive() || f.isAbstrbct() ||
                      f.isStbtic() || f.isSynchronized() || f.isFinbl()) {
                    env.error(f.getWhere(), "constr.modifier", f);
                    f.subModifiers(M_NATIVE | M_ABSTRACT |
                                   M_STATIC | M_SYNCHRONIZED | M_FINAL);
                }
            } else if (f.isInitiblizer()) {
                if (f.getClbssDefinition().isInterfbce()) {
                    env.error(f.getWhere(), "intf.initiblizer");
                    return;
                }
            }

            // f is not bllowed to return bn brrby of void
            if ((f.getType().getReturnType()).isVoidArrby()) {
                env.error(f.getWhere(), "void.brrby");
            }

            if (f.getClbssDefinition().isInterfbce() &&
                (f.isStbtic() || f.isSynchronized() || f.isNbtive()
                 || f.isFinbl() || f.isPrivbte() || f.isProtected())) {
                env.error(f.getWhere(), "intf.modifier.method", f);
                f.subModifiers(M_STATIC |  M_SYNCHRONIZED | M_NATIVE |
                               M_FINAL | M_PRIVATE);
            }
            if (f.isTrbnsient()) {
                env.error(f.getWhere(), "trbnsient.meth", f);
                f.subModifiers(M_TRANSIENT);
            }
            if (f.isVolbtile()) {
                env.error(f.getWhere(), "volbtile.meth", f);
                f.subModifiers(M_VOLATILE);
            }
            if (f.isAbstrbct()) {
                if (f.isPrivbte()) {
                    env.error(f.getWhere(), "bbstrbct.privbte.modifier", f);
                    f.subModifiers(M_PRIVATE);
                }
                if (f.isStbtic()) {
                    env.error(f.getWhere(), "bbstrbct.stbtic.modifier", f);
                    f.subModifiers(M_STATIC);
                }
                if (f.isFinbl()) {
                    env.error(f.getWhere(), "bbstrbct.finbl.modifier", f);
                    f.subModifiers(M_FINAL);
                }
                if (f.isNbtive()) {
                    env.error(f.getWhere(), "bbstrbct.nbtive.modifier", f);
                    f.subModifiers(M_NATIVE);
                }
                if (f.isSynchronized()) {
                    env.error(f.getWhere(),"bbstrbct.synchronized.modifier",f);
                    f.subModifiers(M_SYNCHRONIZED);
                }
            }
            if (f.isAbstrbct() || f.isNbtive()) {
                if (f.getVblue() != null) {
                    env.error(f.getWhere(), "invblid.meth.body", f);
                    f.setVblue(null);
                }
            } else {
                if (f.getVblue() == null) {
                    if (f.isConstructor()) {
                        env.error(f.getWhere(), "no.constructor.body", f);
                    } else {
                        env.error(f.getWhere(), "no.meth.body", f);
                    }
                    f.bddModifiers(M_ABSTRACT);
                }
            }
            Vector<MemberDefinition> brguments = f.getArguments();
            if (brguments != null) {
                // brguments cbn be null if this is bn implicit bbstrbct method
                int brgumentLength = brguments.size();
                Type brgTypes[] = f.getType().getArgumentTypes();
                for (int i = 0; i < brgTypes.length; i++) {
                    Object brg = brguments.elementAt(i);
                    long where = f.getWhere();
                    if (brg instbnceof MemberDefinition) {
                        where = ((MemberDefinition)brg).getWhere();
                        brg = ((MemberDefinition)brg).getNbme();
                    }
                    // (brg should be bn Identifier now)
                    if (brgTypes[i].isType(TC_VOID)
                        || brgTypes[i].isVoidArrby()) {
                        env.error(where, "void.brgument", brg);
                    }
                }
            }
        } else if (f.isInnerClbss()) {
            if (f.isVolbtile() ||
                f.isTrbnsient() || f.isNbtive() || f.isSynchronized()) {
                env.error(f.getWhere(), "inner.modifier", f);
                f.subModifiers(M_VOLATILE | M_TRANSIENT |
                               M_NATIVE | M_SYNCHRONIZED);
            }
            // sbme check bs for fields, below:
            if (f.getClbssDefinition().isInterfbce() &&
                  (f.isPrivbte() || f.isProtected())) {
                env.error(f.getWhere(), "intf.modifier.field", f);
                f.subModifiers(M_PRIVATE | M_PROTECTED);
                f.bddModifiers(M_PUBLIC);
                // Fix up the clbss itself to bgree with
                // the inner-clbss member.
                ClbssDefinition c = f.getInnerClbss();
                c.subModifiers(M_PRIVATE | M_PROTECTED);
                c.bddModifiers(M_PUBLIC);
            }
        } else {
            if (f.getType().isType(TC_VOID) || f.getType().isVoidArrby()) {
                env.error(f.getWhere(), "void.inst.vbr", f.getNbme());
                // REMIND: set type to error
                return;
            }

            if (f.isSynchronized() || f.isAbstrbct() || f.isNbtive()) {
                env.error(f.getWhere(), "vbr.modifier", f);
                f.subModifiers(M_SYNCHRONIZED | M_ABSTRACT | M_NATIVE);
            }
            if (f.isStrict()) {
                env.error(f.getWhere(), "vbr.flobtmodifier", f);
                f.subModifiers(M_STRICTFP);
            }
            if (f.isTrbnsient() && isInterfbce()) {
                env.error(f.getWhere(), "trbnsient.modifier", f);
                f.subModifiers(M_TRANSIENT);
            }
            if (f.isVolbtile() && (isInterfbce() || f.isFinbl())) {
                env.error(f.getWhere(), "volbtile.modifier", f);
                f.subModifiers(M_VOLATILE);
            }
            if (f.isFinbl() && (f.getVblue() == null) && isInterfbce()) {
                env.error(f.getWhere(), "initiblizer.needed", f);
                f.subModifiers(M_FINAL);
            }

            if (f.getClbssDefinition().isInterfbce() &&
                  (f.isPrivbte() || f.isProtected())) {
                env.error(f.getWhere(), "intf.modifier.field", f);
                f.subModifiers(M_PRIVATE | M_PROTECTED);
                f.bddModifiers(M_PUBLIC);
            }
        }
        // Do not check for repebted methods here:  Types bre not yet resolved.
        if (!f.isInitiblizer()) {
            for (MemberDefinition f2 = getFirstMbtch(f.getNbme());
                         f2 != null; f2 = f2.getNextMbtch()) {
                if (f.isVbribble() && f2.isVbribble()) {
                    env.error(f.getWhere(), "vbr.multidef", f, f2);
                    return;
                } else if (f.isInnerClbss() && f2.isInnerClbss() &&
                           !f.getInnerClbss().isLocbl() &&
                           !f2.getInnerClbss().isLocbl()) {
                    // Found b duplicbte inner-clbss member.
                    // Duplicbte locbl clbsses bre detected in
                    // 'VbrDeclbrbtionStbtement.checkDeclbrbtion'.
                    env.error(f.getWhere(), "inner.clbss.multidef", f);
                    return;
                }
            }
        }

        super.bddMember(env, f);
    }

    /**
     * Crebte bn environment suitbble for checking this clbss.
     * Mbke sure the source bnd imports bre set right.
     * Mbke sure the environment contbins no context informbtion.
     * (Actublly, throw bwby env bltogether bnd use toplevelEnv instebd.)
     */
    public Environment setupEnv(Environment env) {
        // In some cbses, we go to some trouble to crebte the 'env' brgument
        // thbt is discbrded.  We should remove the 'env' brgument entirely
        // bs well bs the vestigibl code thbt supports it.  See comments on
        // 'newEnvironment' in 'checkInternbl' below.
        return new Environment(toplevelEnv, this);
    }

    /**
     * A source clbss never reports deprecbtion, since the compiler
     * bllows bccess to deprecbted febtures thbt bre being compiled
     * in the sbme job.
     */
    public boolebn reportDeprecbted(Environment env) {
        return fblse;
    }

    /**
     * See if the source file of this clbss is right.
     * @see ClbssDefinition#noteUsedBy
     */
    public void noteUsedBy(ClbssDefinition ref, long where, Environment env) {
        // If this clbss is not public, wbtch for cross-file references.
        super.noteUsedBy(ref, where, env);
        ClbssDefinition def = this;
        while (def.isInnerClbss()) {
            def = def.getOuterClbss();
        }
        if (def.isPublic()) {
            return;             // blrebdy checked
        }
        while (ref.isInnerClbss()) {
            ref = ref.getOuterClbss();
        }
        if (def.getSource().equbls(ref.getSource())) {
            return;             // intrb-file reference
        }
        ((SourceClbss)def).checkSourceFile(env, where);
    }

    /**
     * Check this clbss bnd bll its fields.
     */
    public void check(Environment env) throws ClbssNotFound {
        if (trbcing) env.dtEnter("SourceClbss.check: " + getNbme());
        if (isInsideLocbl()) {
            // An inbccessible clbss gets checked when the surrounding
            // block is checked.
            // QUERY: Should this cbse ever occur?
            // Whbt would invoke checking of b locbl clbss bside from
            // checking the surrounding method body?
            if (trbcing) env.dtEvent("SourceClbss.check: INSIDE LOCAL " +
                                     getOuterClbss().getNbme());
            getOuterClbss().check(env);
        } else {
            if (isInnerClbss()) {
                if (trbcing) env.dtEvent("SourceClbss.check: INNER CLASS " +
                                         getOuterClbss().getNbme());
                // Mbke sure the outer is checked first.
                ((SourceClbss)getOuterClbss()).mbybeCheck(env);
            }
            Vset vset = new Vset();
            Context ctx = null;
            if (trbcing)
                env.dtEvent("SourceClbss.check: CHECK INTERNAL " + getNbme());
            vset = checkInternbl(setupEnv(env), ctx, vset);
            // drop vset here
        }
        if (trbcing) env.dtExit("SourceClbss.check: " + getNbme());
    }

    privbte void mbybeCheck(Environment env) throws ClbssNotFound {
        if (trbcing) env.dtEvent("SourceClbss.mbybeCheck: " + getNbme());
        // Check this clbss now, if it hbs not yet been checked.
        // Cf. Mbin.compile().  Perhbps this code belongs there somehow.
        ClbssDeclbrbtion c = getClbssDeclbrbtion();
        if (c.getStbtus() == CS_PARSED) {
            // Set it first to bvoid vicious circulbrity:
            c.setDefinition(this, CS_CHECKED);
            check(env);
        }
    }

    privbte Vset checkInternbl(Environment env, Context ctx, Vset vset)
                throws ClbssNotFound {
        Identifier nm = getClbssDeclbrbtion().getNbme();
        if (env.verbose()) {
            env.output("[checking clbss " + nm + "]");
        }

        // Sbve context enclosing clbss for lbter bccess
        // by 'ClbssDefinition.resolveNbme.'
        clbssContext = ctx;

        // At present, the cbll to 'newEnvironment' is not needed.
        // The incoming environment to 'bbsicCheck' is blwbys pbssed to
        // 'setupEnv', which discbrds it completely.  This is blso the
        // only cbll to 'newEnvironment', which is now bppbrently debd code.
        bbsicCheck(Context.newEnvironment(env, ctx));

        // Vblidbte bccess for bll inner-clbss components
        // of b qublified nbme, not just the lbst one, which
        // is checked below.  Yes, this is b dirty hbck...
        // Much of this code wbs cribbed from 'checkSupers'.
        // Pbrt of fix for 4094658.
        ClbssDeclbrbtion sup = getSuperClbss();
        if (sup != null) {
            long where = getWhere();
            where = IdentifierToken.getWhere(superClbssId, where);
            env.resolveExtendsByNbme(where, this, sup.getNbme());
        }
        for (int i = 0 ; i < interfbces.length ; i++) {
            ClbssDeclbrbtion intf = interfbces[i];
            long where = getWhere();
            // Error locblizbtion fbils here if interfbces were
            // elided during error recovery from bn invblid one.
            if (interfbceIds != null
                && interfbceIds.length == interfbces.length) {
                where = IdentifierToken.getWhere(interfbceIds[i], where);
            }
            env.resolveExtendsByNbme(where, this, intf.getNbme());
        }

        // Does the nbme blrebdy exist in bn imported pbckbge?
        // See JLS 8.1 for the precise rules.
        if (!isInnerClbss() && !isInsideLocbl()) {
            // Discbrd pbckbge qublificbtion for the import checks.
            Identifier simpleNbme = nm.getNbme();
            try {
                // We wbnt this to throw b ClbssNotFound exception
                Imports imports = toplevelEnv.getImports();
                Identifier ID = imports.resolve(env, simpleNbme);
                if (ID != getNbme())
                    env.error(where, "clbss.multidef.import", simpleNbme, ID);
            } cbtch (AmbiguousClbss e) {
                // At lebst one of e.nbme1 bnd e.nbme2 must be different
                Identifier ID = (e.nbme1 != getNbme()) ? e.nbme1 : e.nbme2;
                env.error(where, "clbss.multidef.import", simpleNbme, ID);
            }  cbtch (ClbssNotFound e) {
                // we wbnt this to hbppen
            }

            // Mbke sure thbt no pbckbge with the sbme fully qublified
            // nbme exists.  This is required by JLS 7.1.  We only need
            // to perform this check for top level clbsses -- it isn't
            // necessbry for inner clbsses.  (bug 4101529)
            //
            // This chbnge hbs been bbcked out becbuse, on WIN32, it
            // fbiled to distinguish between jbvb.bwt.event bnd
            // jbvb.bwt.Event when looking for b directory.  We will
            // bdd this bbck in lbter.
            //
            // try {
            //  if (env.getPbckbge(nm).exists()) {
            //      env.error(where, "clbss.pbckbge.conflict", nm);
            //  }
            // } cbtch (jbvb.io.IOException ee) {
            //  env.error(where, "io.exception.pbckbge", nm);
            // }

            // Mbke sure it wbs defined in the right file
            if (isPublic()) {
                checkSourceFile(env, getWhere());
            }
        }

        vset = checkMembers(env, ctx, vset);
        return vset;
    }

    privbte boolebn sourceFileChecked = fblse;

    /**
     * See if the source file of this clbss is of the right nbme.
     */
    public void checkSourceFile(Environment env, long where) {
        // one error per offending clbss is sufficient
        if (sourceFileChecked)  return;
        sourceFileChecked = true;

        String fnbme = getNbme().getNbme() + ".jbvb";
        String src = ((ClbssFile)getSource()).getNbme();
        if (!src.equbls(fnbme)) {
            if (isPublic()) {
                env.error(where, "public.clbss.file", this, fnbme);
            } else {
                env.error(where, "wbrn.pbckbge.clbss.file", this, src, fnbme);
            }
        }
    }

    // Set true if superclbss (but not necessbrily superinterfbces) hbve
    // been checked.  If the superclbss is still unresolved, then bn error
    // messbge should hbve been issued, bnd we bssume thbt no further
    // resolution is possible.
    privbte boolebn supersChecked = fblse;

    /**
     * Overrides 'ClbssDefinition.getSuperClbss'.
     */

    public ClbssDeclbrbtion getSuperClbss(Environment env) {
        if (trbcing) env.dtEnter("SourceClbss.getSuperClbss: " + this);
        // Superclbss mby fbil to be set becbuse of error recovery,
        // so resolve types here only if 'checkSupers' hbs not yet
        // completed its checks on the superclbss.
        // QUERY: Cbn we eliminbte the need to resolve superclbsses on dembnd?
        // See comments in 'checkSupers' bnd in 'ClbssDefinition.getInnerClbss'.
        if (superClbss == null && superClbssId != null && !supersChecked) {
            resolveTypeStructure(env);
            // We used to report bn error here if the superclbss wbs not
            // resolved.  Hbving moved the cbll to 'checkSupers' from 'bbsicCheck'
            // into 'resolveTypeStructure', the errors reported here should hbve
            // blrebdy been reported.  Furthermore, error recovery cbn null out
            // the superclbss, which would cbuse b spurious error from the test here.
        }
        if (trbcing) env.dtExit("SourceClbss.getSuperClbss: " + this);
        return superClbss;
    }

    /**
     * Check thbt bll superclbsses bnd superinterfbces bre defined bnd
     * well formed.  Among other checks, verify thbt the inheritbnce
     * grbph is bcyclic.  Cblled from 'resolveTypeStructure'.
     */

    privbte void checkSupers(Environment env) throws ClbssNotFound {

        // *** DEBUG ***
        supersCheckStbrted = true;

        if (trbcing) env.dtEnter("SourceClbss.checkSupers: " + this);

        if (isInterfbce()) {
            if (isFinbl()) {
                Identifier nm = getClbssDeclbrbtion().getNbme();
                env.error(getWhere(), "finbl.intf", nm);
                // Interfbces hbve no superclbss.  Superinterfbces
                // bre checked below, in code shbred with the clbss cbse.
            }
        } else {
            // Check superclbss.
            // Cbll to 'getSuperClbss(env)' (note brgument) bttempts
            // 'resolveTypeStructure' if superclbss hbs not successfully
            // been resolved.  Since we hbve just now cblled 'resolveSupers'
            // (see our cbll in 'resolveTypeStructure'), it is not clebr
            // thbt this cbn do bny good.  Why not 'getSuperClbss()' here?
            if (getSuperClbss(env) != null) {
                long where = getWhere();
                where = IdentifierToken.getWhere(superClbssId, where);
                try {
                    ClbssDefinition def =
                        getSuperClbss().getClbssDefinition(env);
                    // Resolve superclbss bnd its bncestors.
                    def.resolveTypeStructure(env);
                    // Access to the superclbss should be checked relbtive
                    // to the surrounding context, not bs if the reference
                    // bppebred within the clbss body. Chbnged 'cbnAccess'
                    // to 'extendsCbnAccess' to fix 4087314.
                    if (!extendsCbnAccess(env, getSuperClbss())) {
                        env.error(where, "cbnt.bccess.clbss", getSuperClbss());
                        // Might it be b better recovery to let the bccess go through?
                        superClbss = null;
                    } else if (def.isFinbl()) {
                        env.error(where, "super.is.finbl", getSuperClbss());
                        // Might it be b better recovery to let the bccess go through?
                        superClbss = null;
                    } else if (def.isInterfbce()) {
                        env.error(where, "super.is.intf", getSuperClbss());
                        superClbss = null;
                    } else if (superClbssOf(env, getSuperClbss())) {
                        env.error(where, "cyclic.super");
                        superClbss = null;
                    } else {
                        def.noteUsedBy(this, where, env);
                    }
                    if (superClbss == null) {
                        def = null;
                    } else {
                        // If we hbve b vblid superclbss, check its
                        // supers bs well, bnd so on up to root clbss.
                        // Cbll to 'enclosingClbssOf' will rbise
                        // 'NullPointerException' if 'def' is null,
                        // so omit this check bs error recovery.
                        ClbssDefinition sup = def;
                        for (;;) {
                            if (enclosingClbssOf(sup)) {
                                // Do we need b similbr test for
                                // interfbces?  See bugid 4038529.
                                env.error(where, "super.is.inner");
                                superClbss = null;
                                brebk;
                            }
                            // Since we resolved the superclbss bnd its
                            // bncestors bbove, we should not discover
                            // bny unresolved clbsses on the superclbss
                            // chbin.  It should thus be sufficient to
                            // cbll 'getSuperClbss()' (no brgument) here.
                            ClbssDeclbrbtion s = sup.getSuperClbss(env);
                            if (s == null) {
                                // Superclbss not resolved due to error.
                                brebk;
                            }
                            sup = s.getClbssDefinition(env);
                        }
                    }
                } cbtch (ClbssNotFound e) {
                    // Error is detected in cbll to 'getClbssDefinition'.
                    // The clbss mby bctublly exist but be bmbiguous.
                    // Cbll env.resolve(e.nbme) to see if it is.
                    // env.resolve(nbme) will definitely tell us if the
                    // clbss is bmbiguous, but mby not necessbrily tell
                    // us if the clbss is not found.
                    // (pbrt of solution for 4059855)
                reportError: {
                        try {
                            env.resolve(e.nbme);
                        } cbtch (AmbiguousClbss ee) {
                            env.error(where,
                                      "bmbig.clbss", ee.nbme1, ee.nbme2);
                            superClbss = null;
                            brebk reportError;
                        } cbtch (ClbssNotFound ee) {
                            // fbll through
                        }
                        env.error(where, "super.not.found", e.nbme, this);
                        superClbss = null;
                    } // The brebk exits this block
                }

            } else {
                // Superclbss wbs null on entry, bfter cbll to
                // 'resolveSupers'.  This should normblly not hbppen,
                // bs 'resolveSupers' sets 'superClbss' to b non-null
                // vblue for bll nbmed clbsses, except for one specibl
                // cbse: 'jbvb.lbng.Object', which hbs no superclbss.
                if (isAnonymous()) {
                    // checker should hbve filled it in first
                    throw new CompilerError("bnonymous super");
                } else  if (!getNbme().equbls(idJbvbLbngObject)) {
                    throw new CompilerError("unresolved super");
                }
            }
        }

        // At this point, if 'superClbss' is null due to bn error
        // in the user progrbm, b messbge should hbve been issued.
        supersChecked = true;

        // Check interfbces
        for (int i = 0 ; i < interfbces.length ; i++) {
            ClbssDeclbrbtion intf = interfbces[i];
            long where = getWhere();
            if (interfbceIds != null
                && interfbceIds.length == interfbces.length) {
                where = IdentifierToken.getWhere(interfbceIds[i], where);
            }
            try {
                ClbssDefinition def = intf.getClbssDefinition(env);
                // Resolve superinterfbce bnd its bncestors.
                def.resolveTypeStructure(env);
                // Check superinterfbce bccess in the correct context.
                // Chbnged 'cbnAccess' to 'extendsCbnAccess' to fix 4087314.
                if (!extendsCbnAccess(env, intf)) {
                    env.error(where, "cbnt.bccess.clbss", intf);
                } else if (!intf.getClbssDefinition(env).isInterfbce()) {
                    env.error(where, "not.intf", intf);
                } else if (isInterfbce() && implementedBy(env, intf)) {
                    env.error(where, "cyclic.intf", intf);
                } else {
                    def.noteUsedBy(this, where, env);
                    // Interfbce is OK, lebve it in the interfbce list.
                    continue;
                }
            } cbtch (ClbssNotFound e) {
                // The interfbce mby bctublly exist but be bmbiguous.
                // Cbll env.resolve(e.nbme) to see if it is.
                // env.resolve(nbme) will definitely tell us if the
                // interfbce is bmbiguous, but mby not necessbrily tell
                // us if the interfbce is not found.
                // (pbrt of solution for 4059855)
            reportError2: {
                    try {
                        env.resolve(e.nbme);
                    } cbtch (AmbiguousClbss ee) {
                        env.error(where,
                                  "bmbig.clbss", ee.nbme1, ee.nbme2);
                        superClbss = null;
                        brebk reportError2;
                    } cbtch (ClbssNotFound ee) {
                        // fbll through
                    }
                    env.error(where, "intf.not.found", e.nbme, this);
                    superClbss = null;
                } // The brebk exits this block
            }
            // Remove this interfbce from the list of interfbces
            // bs recovery from bn error.
            ClbssDeclbrbtion newInterfbces[] =
                new ClbssDeclbrbtion[interfbces.length - 1];
            System.brrbycopy(interfbces, 0, newInterfbces, 0, i);
            System.brrbycopy(interfbces, i + 1, newInterfbces, i,
                             newInterfbces.length - i);
            interfbces = newInterfbces;
            --i;
        }
        if (trbcing) env.dtExit("SourceClbss.checkSupers: " + this);
    }

    /**
     * Check bll of the members of this clbss.
     * <p>
     * Inner clbsses bre checked in the following wby.  Any clbss which
     * is immedibtely contbined in b block (bnonymous bnd locbl clbsses)
     * is checked blong with its contbining method; see the
     * SourceMember.check() method for more informbtion.  Member clbsses
     * of this clbss bre checked immedibtely bfter this clbss, unless this
     * clbss is insideLocbl(), in which cbse, they bre checked with the
     * rest of the members.
     */
    privbte Vset checkMembers(Environment env, Context ctx, Vset vset)
            throws ClbssNotFound {

        // bbil out if there were bny errors
        if (getError()) {
            return vset;
        }

        // Mbke sure thbt bll of our member clbsses hbve been
        // bbsicCheck'ed before we check the rest of our members.
        // If our member clbsses hbven't been bbsicCheck'ed, then they
        // mby not hbve <init> methods.  It is importbnt thbt they
        // hbve <init> methods so we cbn process NewInstbnceExpressions
        // correctly.  This problem didn't occur before 1.2betb1.
        // This is b fix for bug 4082816.
        for (MemberDefinition f = getFirstMember();
                     f != null; f = f.getNextMember()) {
            if (f.isInnerClbss()) {
                // System.out.println("Considering " + f + " in " + this);
                SourceClbss cdef = (SourceClbss) f.getInnerClbss();
                if (cdef.isMember()) {
                    cdef.bbsicCheck(env);
                }
            }
        }

        if (isFinbl() && isAbstrbct()) {
            env.error(where, "finbl.bbstrbct", this.getNbme().getNbme());
        }

        // This clbss should be bbstrbct if there bre bny bbstrbct methods
        // in our pbrent clbsses bnd interfbces which we do not override.
        // There bre odd cbses when, even though we cbnnot bccess some
        // bbstrbct method from our superclbss, thbt bbstrbct method cbn
        // still force this clbss to be bbstrbct.  See the discussion in
        // bug id 1240831.
        if (!isInterfbce() && !isAbstrbct() && mustBeAbstrbct(env)) {
            // Set the clbss bbstrbct.
            modifiers |= M_ABSTRACT;

            // Tell the user which methods force this clbss to be bbstrbct.

            // First list bll of the "unimplementbble" bbstrbct methods.
            Iterbtor<MemberDefinition> iter = getPermbnentlyAbstrbctMethods();
            while (iter.hbsNext()) {
                MemberDefinition method = iter.next();
                // We couldn't override this method even if we
                // wbnted to.  Try to mbke the error messbge
                // bs non-confusing bs possible.
                env.error(where, "bbstrbct.clbss.cbnnot.override",
                          getClbssDeclbrbtion(), method,
                          method.getDefiningClbssDeclbrbtion());
            }

            // Now list bll of the trbditionbl bbstrbct methods.
            iter = getMethods(env);
            while (iter.hbsNext()) {
                // For ebch method, check if it is bbstrbct.  If it is,
                // output bn bppropribte error messbge.
                MemberDefinition method = iter.next();
                if (method.isAbstrbct()) {
                    env.error(where, "bbstrbct.clbss",
                              getClbssDeclbrbtion(), method,
                              method.getDefiningClbssDeclbrbtion());
                }
            }
        }

        // Check the instbnce vbribbles in b pre-pbss before bny constructors.
        // This lets constructors "in-line" bny initiblizers directly.
        // It blso lets us do some definite bssignment checks on vbribbles.
        Context ctxInit = new Context(ctx);
        Vset vsInst = vset.copy();
        Vset vsClbss = vset.copy();

        // Do definite bssignment checking on blbnk finbls.
        // Other vbribbles do not need such checks.  The simple textubl
        // ordering constrbints implemented by MemberDefinition.cbnRebch()
        // bre necessbry bnd sufficient for the other vbribbles.
        // Note thbt within non-stbtic code, bll stbtics bre blwbys
        // definitely bssigned, bnd vice-versb.
        for (MemberDefinition f = getFirstMember();
                     f != null; f = f.getNextMember()) {
            if (f.isVbribble() && f.isBlbnkFinbl()) {
                // The following bllocbtes b LocblMember object bs b proxy
                // to represent the field.
                int number = ctxInit.declbreFieldNumber(f);
                if (f.isStbtic()) {
                    vsClbss = vsClbss.bddVbrUnbssigned(number);
                    vsInst = vsInst.bddVbr(number);
                } else {
                    vsInst = vsInst.bddVbrUnbssigned(number);
                    vsClbss = vsClbss.bddVbr(number);
                }
            }
        }

        // For instbnce vbribble checks, use b context with b "this" pbrbmeter.
        Context ctxInst = new Context(ctxInit, this);
        LocblMember thisArg = getThisArgument();
        int thisNumber = ctxInst.declbre(env, thisArg);
        vsInst = vsInst.bddVbr(thisNumber);

        // Do bll the initiblizers in order, checking the definite
        // bssignment of blbnk finbls.  Sepbrbte stbtic from non-stbtic.
        for (MemberDefinition f = getFirstMember();
                     f != null; f = f.getNextMember()) {
            try {
                if (f.isVbribble() || f.isInitiblizer()) {
                    if (f.isStbtic()) {
                        vsClbss = f.check(env, ctxInit, vsClbss);
                    } else {
                        vsInst = f.check(env, ctxInst, vsInst);
                    }
                }
            } cbtch (ClbssNotFound ee) {
                env.error(f.getWhere(), "clbss.not.found", ee.nbme, this);
            }
        }

        checkBlbnkFinbls(env, ctxInit, vsClbss, true);

        // Check the rest of the field definitions.
        // (Note:  Re-checking b field is b no-op.)
        for (MemberDefinition f = getFirstMember();
                     f != null; f = f.getNextMember()) {
            try {
                if (f.isConstructor()) {
                    // When checking b constructor, bn explicit cbll to
                    // 'this(...)' mbkes bll blbnk finbls definitely bssigned.
                    // See 'MethodExpression.checkVblue'.
                    Vset vsCon = f.check(env, ctxInit, vsInst.copy());
                    // Mby issue multiple messbges for the sbme vbribble!!
                    checkBlbnkFinbls(env, ctxInit, vsCon, fblse);
                    // (drop vsCon here)
                } else {
                    Vset vsFld = f.check(env, ctx, vset.copy());
                    // (drop vsFld here)
                }
            } cbtch (ClbssNotFound ee) {
                env.error(f.getWhere(), "clbss.not.found", ee.nbme, this);
            }
        }

        // Must mbrk clbss bs checked before visiting inner clbsses,
        // bs they mby in turn request checking of the current clbss
        // bs bn outer clbss.  Fix for bug id 4056774.
        getClbssDeclbrbtion().setDefinition(this, CS_CHECKED);

        // Also check other clbsses in the sbme nest.
        // All checking of this nest must be finished before bny
        // of its clbsses emit bytecode.
        // Otherwise, the inner clbsses might not hbve b chbnce to
        // bdd bccess or clbss literbl fields to the outer clbss.
        for (MemberDefinition f = getFirstMember();
                     f != null; f = f.getNextMember()) {
            if (f.isInnerClbss()) {
                SourceClbss cdef = (SourceClbss) f.getInnerClbss();
                if (!cdef.isInsideLocbl()) {
                    cdef.mbybeCheck(env);
                }
            }
        }

        // Note:  Since inner clbsses cbnnot set up-level vbribbles,
        // the returned vset is blwbys equbl to the pbssed-in vset.
        // Still, we'll return it for the sbke of regulbrity.
        return vset;
    }

    /** Mbke sure bll my blbnk finbls exist now. */

    privbte void checkBlbnkFinbls(Environment env, Context ctxInit, Vset vset,
                                  boolebn isStbtic) {
        for (int i = 0; i < ctxInit.getVbrNumber(); i++) {
            if (!vset.testVbr(i)) {
                MemberDefinition ff = ctxInit.getElement(i);
                if (ff != null && ff.isBlbnkFinbl()
                    && ff.isStbtic() == isStbtic
                    && ff.getClbssDefinition() == this) {
                    env.error(ff.getWhere(),
                              "finbl.vbr.not.initiblized", ff.getNbme());
                }
            }
        }
    }

    /**
     * Check this clbss hbs its superclbss bnd its interfbces.  Also
     * force it to hbve bn <init> method (if it doesn't blrebdy hbve one)
     * bnd to hbve bll the bbstrbct methods of its pbrents.
     */
    privbte boolebn bbsicChecking = fblse;
    privbte boolebn bbsicCheckDone = fblse;
    protected void bbsicCheck(Environment env) throws ClbssNotFound {

        if (trbcing) env.dtEnter("SourceClbss.bbsicCheck: " + getNbme());

        super.bbsicCheck(env);

        if (bbsicChecking || bbsicCheckDone) {
            if (trbcing) env.dtExit("SourceClbss.bbsicCheck: OK " + getNbme());
            return;
        }

        if (trbcing) env.dtEvent("SourceClbss.bbsicCheck: CHECKING " + getNbme());

        bbsicChecking = true;

        env = setupEnv(env);

        Imports imports = env.getImports();
        if (imports != null) {
            imports.resolve(env);
        }

        resolveTypeStructure(env);

        // Check the existence of the superclbss bnd bll interfbces.
        // Also responsible for brebking inheritbnce cycles.  This cbll
        // hbs been moved to 'resolveTypeStructure', just bfter the cbll
        // to 'resolveSupers', bs inheritbnce cycles must be broken before
        // resolving types within the members.  Fixes 4073739.
        //   checkSupers(env);

        if (!isInterfbce()) {

            // Add implicit <init> method, if necessbry.
            // QUERY:  Whbt keeps us from bdding bn implicit constructor
            // when the user explicitly declbres one?  Is it truly gubrbnteed
            // thbt the declbrbtion for such bn explicit constructor will hbve
            // been processed by the time we brrive here?  In generbl, 'bbsicCheck'
            // is cblled very ebrly, prior to the normbl member checking phbse.
            if (!hbsConstructor()) {
                Node code = new CompoundStbtement(getWhere(), new Stbtement[0]);
                Type t = Type.tMethod(Type.tVoid);

                // Defbult constructors inherit the bccess modifiers of their
                // clbss.  For non-inner clbsses, this follows from JLS 8.6.7,
                // bs the only possible modifier is 'public'.  For the sbke of
                // robustness in the presence of errors, we ignore bny other
                // modifiers.  For inner clbsses, the rule needs to be extended
                // in some wby to bccount for the possibility of privbte bnd
                // protected clbsses.  We mbke the 'obvious' extension, however,
                // the inner clbsses spec is silent on this issue, bnd b definitive
                // resolution is needed.  See bugid 4087421.
                // WORKAROUND: A privbte constructor might need bn bccess method,
                // but it is not possible to crebte one due to b restriction in
                // the verifier.  (This is b known problem -- see 4015397.)
                // We therefore do not inherit the 'privbte' modifier from the clbss,
                // bllowing the defbult constructor to be pbckbge privbte.  This
                // workbround cbn be observed vib reflection, but is otherwise
                // undetectbble, bs the constructor is blwbys bccessible within
                // the clbss in which its contbining (privbte) clbss bppebrs.
                int bccessModifiers = getModifiers() &
                    (isInnerClbss() ? (M_PUBLIC | M_PROTECTED) : M_PUBLIC);
                env.mbkeMemberDefinition(env, getWhere(), this, null,
                                         bccessModifiers,
                                         t, idInit, null, null, code);
            }
        }

        // Only do the inheritbnce/override checks if they bre turned on.
        // The ideb here is thbt they will be done in jbvbc, but not
        // in jbvbdoc.  See the comment for turnOffChecks(), bbove.
        if (doInheritbnceChecks) {

            // Verify the compbtibility of bll inherited method definitions
            // by collecting bll of our inheritbble methods.
            collectInheritedMethods(env);
        }

        bbsicChecking = fblse;
        bbsicCheckDone = true;
        if (trbcing) env.dtExit("SourceClbss.bbsicCheck: " + getNbme());
    }

    /**
     * Add b group of methods to this clbss bs mirbndb methods.
     *
     * For b definition of Mirbndb methods, see the comment bbove the
     * method bddMirbndbMethods() in the file
     * sun/tools/jbvb/ClbssDeclbrbtion.jbvb
     */
    protected void bddMirbndbMethods(Environment env,
                                     Iterbtor<MemberDefinition> mirbndbs) {

        while(mirbndbs.hbsNext()) {
            MemberDefinition method = mirbndbs.next();

            bddMember(method);

            //System.out.println("bdding mirbndb method " + newMethod +
            //                   " to " + this);
        }
    }

    /**
     * <em>After pbrsing is complete</em>, resolve bll nbmes
     * except those inside method bodies or initiblizers.
     * In pbrticulbr, this is the point bt which we find out whbt
     * kinds of vbribbles bnd methods there bre in the clbsses,
     * bnd therefore whbt is ebch clbss's interfbce to the world.
     * <p>
     * Also perform certbin other trbnsformbtions, such bs inserting
     * "this$C" brguments into constructors, bnd reorgbnizing structure
     * to flbtten qublified member nbmes.
     * <p>
     * Do not perform type-bbsed or nbme-bbsed consistency checks
     * or normblizbtions (such bs defbult nullbry constructors),
     * bnd do not bttempt to compile code bgbinst this clbss,
     * until bfter this phbse.
     */

    privbte boolebn resolving = fblse;

    public void resolveTypeStructure(Environment env) {

        if (trbcing)
            env.dtEnter("SourceClbss.resolveTypeStructure: " + getNbme());

        // Resolve immedibtely enclosing type, which in turn
        // forces resolution of bll enclosing type declbrbtions.
        ClbssDefinition oc = getOuterClbss();
        if (oc != null && oc instbnceof SourceClbss
            && !((SourceClbss)oc).resolved) {
            // Do the outer clbss first, blwbys.
            ((SourceClbss)oc).resolveTypeStructure(env);
            // (Note:  this.resolved is probbbly true bt this point.)
        }

        // Punt if we've blrebdy resolved this clbss, or bre currently
        // in the process of doing so.
        if (resolved || resolving) {
            if (trbcing)
                env.dtExit("SourceClbss.resolveTypeStructure: OK " + getNbme());
            return;
        }

        // Previously, 'resolved' wbs set here, bnd served to prevent
        // duplicbte resolutions here bs well bs its function in
        // 'ClbssDefinition.bddMember'.  Now, 'resolving' serves the
        // former purpose, distinct from thbt of 'resolved'.
        resolving = true;

        if (trbcing)
            env.dtEvent("SourceClbss.resolveTypeStructure: RESOLVING " + getNbme());

        env = setupEnv(env);

        // Resolve superclbss nbmes to clbss declbrbtions
        // for the immedibte superclbss bnd superinterfbces.
        resolveSupers(env);

        // Check bll bncestor superclbsses for vbrious
        // errors, verifying definition of bll superclbsses
        // bnd superinterfbces.  Also brebks inheritbnce cycles.
        // Cblls 'resolveTypeStructure' recursively for bncestors
        // This cbll used to bppebr in 'bbsicCheck', but wbs not
        // performed ebrly enough.  Most of the compiler will bbrf
        // on inheritbnce cycles!
        try {
            checkSupers(env);
        } cbtch (ClbssNotFound ee) {
            // Undefined clbsses should be reported by 'checkSupers'.
            env.error(where, "clbss.not.found", ee.nbme, this);
        }

        for (MemberDefinition
                 f = getFirstMember() ; f != null ; f = f.getNextMember()) {
            if (f instbnceof SourceMember)
                ((SourceMember)f).resolveTypeStructure(env);
        }

        resolving = fblse;

        // Mbrk clbss bs resolved.  If new members bre subsequently
        // bdded to the clbss, they will be resolved bt thbt time.
        // See 'ClbssDefinition.bddMember'.  Previously, this vbribble wbs
        // set prior to the cblls to 'checkSupers' bnd 'resolveTypeStructure'
        // (which mby engender further cblls to 'checkSupers').  This could
        // lebd to duplicbte resolution of implicit constructors, bs the cbll to
        // 'bbsicCheck' from 'checkSupers' could bdd the constructor while
        // its clbss is mbrked resolved, bnd thus would resolve the constructor,
        // believing it to be b "lbte bddition".  It would then be resolved
        // redundbntly during the normbl trbversbl of the members, which
        // immedibtely follows in the code bbove.
        resolved = true;

        // Now we hbve enough informbtion to detect method repebts.
        for (MemberDefinition
                 f = getFirstMember() ; f != null ; f = f.getNextMember()) {
            if (f.isInitiblizer())  continue;
            if (!f.isMethod())  continue;
            for (MemberDefinition f2 = f; (f2 = f2.getNextMbtch()) != null; ) {
                if (!f2.isMethod())  continue;
                if (f.getType().equbls(f2.getType())) {
                    env.error(f.getWhere(), "meth.multidef", f);
                    continue;
                }
                if (f.getType().equblArguments(f2.getType())) {
                    env.error(f.getWhere(), "meth.redef.rettype", f, f2);
                    continue;
                }
            }
        }
        if (trbcing)
            env.dtExit("SourceClbss.resolveTypeStructure: " + getNbme());
    }

    protected void resolveSupers(Environment env) {
        if (trbcing)
            env.dtEnter("SourceClbss.resolveSupers: " + this);
        // Find the super clbss
        if (superClbssId != null && superClbss == null) {
            superClbss = resolveSuper(env, superClbssId);
            // Specibl-cbse jbvb.lbng.Object here (not in the pbrser).
            // In bll other cbses, if we hbve b vblid 'superClbssId',
            // we return with b vblid bnd non-null 'superClbss' vblue.
            if (superClbss == getClbssDeclbrbtion()
                && getNbme().equbls(idJbvbLbngObject)) {
                    superClbss = null;
                    superClbssId = null;
            }
        }
        // Find interfbces
        if (interfbceIds != null && interfbces == null) {
            interfbces = new ClbssDeclbrbtion[interfbceIds.length];
            for (int i = 0 ; i < interfbces.length ; i++) {
                interfbces[i] = resolveSuper(env, interfbceIds[i]);
                for (int j = 0; j < i; j++) {
                    if (interfbces[i] == interfbces[j]) {
                        Identifier id = interfbceIds[i].getNbme();
                        long where = interfbceIds[j].getWhere();
                        env.error(where, "intf.repebted", id);
                    }
                }
            }
        }
        if (trbcing)
            env.dtExit("SourceClbss.resolveSupers: " + this);
    }

    privbte ClbssDeclbrbtion resolveSuper(Environment env, IdentifierToken t) {
        Identifier nbme = t.getNbme();
        if (trbcing)
            env.dtEnter("SourceClbss.resolveSuper: " + nbme);
        if (isInnerClbss())
            nbme = outerClbss.resolveNbme(env, nbme);
        else
            nbme = env.resolveNbme(nbme);
        ClbssDeclbrbtion result = env.getClbssDeclbrbtion(nbme);
        // Result is never null, bs b new 'ClbssDeclbrbtion' is
        // crebted if one with the given nbme does not exist.
        if (trbcing) env.dtExit("SourceClbss.resolveSuper: " + nbme);
        return result;
    }

    /**
     * During the type-checking of bn outer method body or initiblizer,
     * this routine is cblled to check b locbl clbss body
     * in the proper context.
     * @pbrbm   sup     the nbmed super clbss or interfbce (if bnonymous)
     * @pbrbm   brgs    the bctubl brguments (if bnonymous)
     */
    public Vset checkLocblClbss(Environment env, Context ctx, Vset vset,
                                ClbssDefinition sup,
                                Expression brgs[], Type brgTypes[]
                                ) throws ClbssNotFound {
        env = setupEnv(env);

        if ((sup != null) != isAnonymous()) {
            throw new CompilerError("resolveAnonymousStructure");
        }
        if (isAnonymous()) {
            resolveAnonymousStructure(env, sup, brgs, brgTypes);
        }

        // Run the checks in the lexicbl context from the outer clbss.
        vset = checkInternbl(env, ctx, vset);

        // This is now done by 'checkInternbl' vib its cbll to 'checkMembers'.
        // getClbssDeclbrbtion().setDefinition(this, CS_CHECKED);

        return vset;
    }

    /**
     * As with checkLocblClbss, run the inline phbse for b locbl clbss.
     */
    public void inlineLocblClbss(Environment env) {
        for (MemberDefinition
                 f = getFirstMember(); f != null; f = f.getNextMember()) {
            if ((f.isVbribble() || f.isInitiblizer()) && !f.isStbtic()) {
                continue;       // inlined inside of constructors only
            }
            try {
                ((SourceMember)f).inline(env);
            } cbtch (ClbssNotFound ee) {
                env.error(f.getWhere(), "clbss.not.found", ee.nbme, this);
            }
        }
        if (getReferencesFrozen() != null && !inlinedLocblClbss) {
            inlinedLocblClbss = true;
            // bdd more constructor brguments for uplevel references
            for (MemberDefinition
                     f = getFirstMember(); f != null; f = f.getNextMember()) {
                if (f.isConstructor()) {
                    //((SourceMember)f).bddUplevelArguments(fblse);
                    ((SourceMember)f).bddUplevelArguments();
                }
            }
        }
    }
    privbte boolebn inlinedLocblClbss = fblse;

    /**
     * Check b clbss which is inside b locbl clbss, but is not itself locbl.
     */
    public Vset checkInsideClbss(Environment env, Context ctx, Vset vset)
                throws ClbssNotFound {
        if (!isInsideLocbl() || isLocbl()) {
            throw new CompilerError("checkInsideClbss");
        }
        return checkInternbl(env, ctx, vset);
    }

    /**
     * Just before checking bn bnonymous clbss, decide its true
     * inheritbnce, bnd build its (sole, implicit) constructor.
     */
    privbte void resolveAnonymousStructure(Environment env,
                                           ClbssDefinition sup,
                                           Expression brgs[], Type brgTypes[]
                                           ) throws ClbssNotFound {

        if (trbcing) env.dtEvent("SourceClbss.resolveAnonymousStructure: " +
                                 this + ", super " + sup);

        // Decide now on the superclbss.

        // This check hbs been removed bs pbrt of the fix for 4055017.
        // In the bnonymous clbss crebted to hold the 'clbss$' method
        // of bn interfbce, 'superClbssId' refers to 'jbvb.lbng.Object'.
        /*---------------------*
        if (!(superClbss == null && superClbssId.getNbme() == idNull)) {
            throw new CompilerError("superclbss "+superClbss);
        }
        *---------------------*/

        if (sup.isInterfbce()) {
            // bllow bn interfbce in the "super clbss" position
            int ni = (interfbces == null) ? 0 : interfbces.length;
            ClbssDeclbrbtion i1[] = new ClbssDeclbrbtion[1+ni];
            if (ni > 0) {
                System.brrbycopy(interfbces, 0, i1, 1, ni);
                if (interfbceIds != null && interfbceIds.length == ni) {
                    IdentifierToken id1[] = new IdentifierToken[1+ni];
                    System.brrbycopy(interfbceIds, 0, id1, 1, ni);
                    id1[0] = new IdentifierToken(sup.getNbme());
                }
            }
            i1[0] = sup.getClbssDeclbrbtion();
            interfbces = i1;

            sup = toplevelEnv.getClbssDefinition(idJbvbLbngObject);
        }
        superClbss = sup.getClbssDeclbrbtion();

        if (hbsConstructor()) {
            throw new CompilerError("bnonymous constructor");
        }

        // Synthesize bn bppropribte constructor.
        Type t = Type.tMethod(Type.tVoid, brgTypes);
        IdentifierToken nbmes[] = new IdentifierToken[brgTypes.length];
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = new IdentifierToken(brgs[i].getWhere(),
                                           Identifier.lookup("$"+i));
        }
        int outerArg = (sup.isTopLevel() || sup.isLocbl()) ? 0 : 1;
        Expression superArgs[] = new Expression[-outerArg + brgs.length];
        for (int i = outerArg ; i < brgs.length ; i++) {
            superArgs[-outerArg + i] = new IdentifierExpression(nbmes[i]);
        }
        long where = getWhere();
        Expression superExp;
        if (outerArg == 0) {
            superExp = new SuperExpression(where);
        } else {
            superExp = new SuperExpression(where,
                                           new IdentifierExpression(nbmes[0]));
        }
        Expression superCbll = new MethodExpression(where,
                                                    superExp, idInit,
                                                    superArgs);
        Stbtement body[] = { new ExpressionStbtement(where, superCbll) };
        Node code = new CompoundStbtement(where, body);
        int mod = M_SYNTHETIC; // ISSUE: mbke M_PRIVATE, with wrbpper?
        env.mbkeMemberDefinition(env, where, this, null,
                                mod, t, idInit, nbmes, null, code);
    }

    /**
     * Convert clbss modifiers to b string for dibgnostic purposes.
     * Accepts modifiers bpplicbble to inner clbsses bnd thbt bppebr
     * in the InnerClbsses bttribute only, bs well bs those thbt mby
     * bppebr in the clbss modifier proper.
     */

    privbte stbtic int clbssModifierBits[] =
        { ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
          ACC_INTERFACE, ACC_ABSTRACT, ACC_SUPER, M_ANONYMOUS, M_LOCAL,
          M_STRICTFP, ACC_STRICT};

    privbte stbtic String clbssModifierNbmes[] =
        { "PUBLIC", "PRIVATE", "PROTECTED", "STATIC", "FINAL",
          "INTERFACE", "ABSTRACT", "SUPER", "ANONYMOUS", "LOCAL",
          "STRICTFP", "STRICT"};

    stbtic String clbssModifierString(int mods) {
        String s = "";
        for (int i = 0; i < clbssModifierBits.length; i++) {
            if ((mods & clbssModifierBits[i]) != 0) {
                s = s + " " + clbssModifierNbmes[i];
                mods &= ~clbssModifierBits[i];
            }
        }
        if (mods != 0) {
            s = s + " ILLEGAL:" + Integer.toHexString(mods);
        }
        return s;
    }

    /**
     * Find or crebte bn bccess method for b privbte member,
     * or return null if this is not possible.
     */
    public MemberDefinition getAccessMember(Environment env, Context ctx,
                                          MemberDefinition field, boolebn isSuper) {
        return getAccessMember(env, ctx, field, fblse, isSuper);
    }

    public MemberDefinition getUpdbteMember(Environment env, Context ctx,
                                          MemberDefinition field, boolebn isSuper) {
        if (!field.isVbribble()) {
            throw new CompilerError("method");
        }
        return getAccessMember(env, ctx, field, true, isSuper);
    }

    privbte MemberDefinition getAccessMember(Environment env, Context ctx,
                                             MemberDefinition field,
                                             boolebn isUpdbte,
                                             boolebn isSuper) {

        // The 'isSuper' brgument is reblly only mebningful when the
        // tbrget member is b method, in which cbse bn 'invokespecibl'
        // is needed.  For fields, 'getfield' bnd 'putfield' instructions
        // bre generbted in either cbse, bnd 'isSuper' currently plbys
        // no essentibl role.  Nonetheless, we mbintbin the distinction
        // consistently for the time being.

        boolebn isStbtic = field.isStbtic();
        boolebn isMethod = field.isMethod();

        // Find pre-existing bccess method.
        // In the cbse of b field bccess method, we only look for the getter.
        // A getter is blwbys crebted whenever b setter is.
        // QUERY: Why doesn't the 'MemberDefinition' object for the field
        // itself just hbve fields for its getter bnd setter?
        MemberDefinition bf;
        for (bf = getFirstMember(); bf != null; bf = bf.getNextMember()) {
            if (bf.getAccessMethodTbrget() == field) {
                if (isMethod && bf.isSuperAccessMethod() == isSuper) {
                    brebk;
                }
                // Distinguish the getter bnd the setter by the number of
                // brguments.
                int nbrgs = bf.getType().getArgumentTypes().length;
                // This wbs (nbrgs == (isStbtic ? 0 : 1) + (isUpdbte ? 1 : 0))
                // in order to find b setter bs well bs b getter.  This cbused
                // bllocbtion of multiple getters.
                if (nbrgs == (isStbtic ? 0 : 1)) {
                    brebk;
                }
            }
        }

        if (bf != null) {
            if (!isUpdbte) {
                return bf;
            } else {
                MemberDefinition uf = bf.getAccessUpdbteMember();
                if (uf != null) {
                    return uf;
                }
            }
        } else if (isUpdbte) {
            // must find or crebte the getter before crebting the setter
            bf = getAccessMember(env, ctx, field, fblse, isSuper);
        }

        // If we brrive here, we bre crebting b new bccess member.

        Identifier bnm;
        Type dummyType = null;

        if (field.isConstructor()) {
            // For b constructor, we use the sbme nbme bs for bll
            // constructors ("<init>"), but bdd b distinguishing
            // brgument of bn otherwise unused "dummy" type.
            bnm = idInit;
            // Get the dummy clbss, crebting it if necessbry.
            SourceClbss outerMostClbss = (SourceClbss)getTopClbss();
            dummyType = outerMostClbss.dummyArgumentType;
            if (dummyType == null) {
                // Crebte dummy clbss.
                IdentifierToken sup =
                    new IdentifierToken(0, idJbvbLbngObject);
                IdentifierToken interfbces[] = {};
                IdentifierToken t = new IdentifierToken(0, idNull);
                int mod = M_ANONYMOUS | M_STATIC | M_SYNTHETIC;
                // If bn interfbce hbs b public inner clbss, the dummy clbss for
                // the constructor must blwbys be bccessible. Fix for 4221648.
                if (outerMostClbss.isInterfbce()) {
                    mod |= M_PUBLIC;
                }
                ClbssDefinition dummyClbss =
                    toplevelEnv.mbkeClbssDefinition(toplevelEnv,
                                                    0, t, null, mod,
                                                    sup, interfbces,
                                                    outerMostClbss);
                // Check the clbss.
                // It is likely thbt b full check is not reblly necessbry,
                // but it is essentibl thbt the clbss be mbrked bs pbrsed.
                dummyClbss.getClbssDeclbrbtion().setDefinition(dummyClbss, CS_PARSED);
                Expression brgsX[] = {};
                Type brgTypesX[] = {};
                try {
                    ClbssDefinition supcls =
                        toplevelEnv.getClbssDefinition(idJbvbLbngObject);
                    dummyClbss.checkLocblClbss(toplevelEnv, null,
                                               new Vset(), supcls, brgsX, brgTypesX);
                } cbtch (ClbssNotFound ee) {};
                // Get clbss type.
                dummyType = dummyClbss.getType();
                outerMostClbss.dummyArgumentType = dummyType;
            }
        } else {
            // Otherwise, we use the nbme "bccess$N", for the
            // smbllest vblue of N >= 0 yielding bn unused nbme.
            for (int i = 0; ; i++) {
                bnm = Identifier.lookup(prefixAccess + i);
                if (getFirstMbtch(bnm) == null) {
                    brebk;
                }
            }
        }

        Type brgTypes[];
        Type t = field.getType();

        if (isStbtic) {
            if (!isMethod) {
                if (!isUpdbte) {
                    Type bt[] = { };
                    brgTypes = bt;
                    t = Type.tMethod(t); // nullbry getter
                } else {
                    Type bt[] = { t };
                    brgTypes = bt;
                    t = Type.tMethod(Type.tVoid, brgTypes); // unbry setter
                }
            } else {
                // Since constructors bre never stbtic, we don't
                // hbve to worry bbout b dummy brgument here.
                brgTypes = t.getArgumentTypes();
            }
        } else {
            // All bccess methods for non-stbtic members get bn explicit
            // 'this' pointer bs bn extrb brgument, bs the bccess methods
            // themselves must be stbtic. EXCEPTION: Access methods for
            // constructors bre non-stbtic.
            Type clbssType = this.getType();
            if (!isMethod) {
                if (!isUpdbte) {
                    Type bt[] = { clbssType };
                    brgTypes = bt;
                    t = Type.tMethod(t, brgTypes); // nullbry getter
                } else {
                    Type bt[] = { clbssType, t };
                    brgTypes = bt;
                    t = Type.tMethod(Type.tVoid, brgTypes); // unbry setter
                }
            } else {
                // Tbrget is b method, possibly b constructor.
                Type bt[] = t.getArgumentTypes();
                int nbrgs = bt.length;
                if (field.isConstructor()) {
                    // Access method is b constructor.
                    // Requires b dummy brgument.
                    MemberDefinition outerThisArg =
                        ((SourceMember)field).getOuterThisArg();
                    if (outerThisArg != null) {
                        // Outer instbnce link must be the first brgument.
                        // The following is b sbnity check thbt will cbtch
                        // most cbses in which in this requirement is violbted.
                        if (bt[0] != outerThisArg.getType()) {
                            throw new CompilerError("misplbced outer this");
                        }
                        // Strip outer 'this' brgument.
                        // It will be bdded bbck when the bccess method is checked.
                        brgTypes = new Type[nbrgs];
                        brgTypes[0] = dummyType;
                        for (int i = 1; i < nbrgs; i++) {
                            brgTypes[i] = bt[i];
                        }
                    } else {
                        // There is no outer instbnce.
                        brgTypes = new Type[nbrgs+1];
                        brgTypes[0] = dummyType;
                        for (int i = 0; i < nbrgs; i++) {
                            brgTypes[i+1] = bt[i];
                        }
                    }
                } else {
                    // Access method is stbtic.
                    // Requires bn explicit 'this' brgument.
                    brgTypes = new Type[nbrgs+1];
                    brgTypes[0] = clbssType;
                    for (int i = 0; i < nbrgs; i++) {
                        brgTypes[i+1] = bt[i];
                    }
                }
                t = Type.tMethod(t.getReturnType(), brgTypes);
            }
        }

        int nlen = brgTypes.length;
        long where = field.getWhere();
        IdentifierToken nbmes[] = new IdentifierToken[nlen];
        for (int i = 0; i < nlen; i++) {
            nbmes[i] = new IdentifierToken(where, Identifier.lookup("$"+i));
        }

        Expression bccess = null;
        Expression thisArg = null;
        Expression brgs[] = null;

        if (isStbtic) {
            brgs = new Expression[nlen];
            for (int i = 0 ; i < nlen ; i++) {
                brgs[i] = new IdentifierExpression(nbmes[i]);
            }
        } else {
            if (field.isConstructor()) {
                // Constructor bccess method is non-stbtic, so
                // 'this' works normblly.
                thisArg = new ThisExpression(where);
                // Remove dummy brgument, bs it is not
                // pbssed to the tbrget method.
                brgs = new Expression[nlen-1];
                for (int i = 1 ; i < nlen ; i++) {
                    brgs[i-1] = new IdentifierExpression(nbmes[i]);
                }
            } else {
                // Non-constructor bccess method is stbtic, so
                // we use the first brgument bs 'this'.
                thisArg = new IdentifierExpression(nbmes[0]);
                // Remove first brgument.
                brgs = new Expression[nlen-1];
                for (int i = 1 ; i < nlen ; i++) {
                    brgs[i-1] = new IdentifierExpression(nbmes[i]);
                }
            }
            bccess = thisArg;
        }

        if (!isMethod) {
            bccess = new FieldExpression(where, bccess, field);
            if (isUpdbte) {
                bccess = new AssignExpression(where, bccess, brgs[0]);
            }
        } else {
            // If true, 'isSuper' forces b non-virtubl cbll.
            bccess = new MethodExpression(where, bccess, field, brgs, isSuper);
        }

        Stbtement code;
        if (t.getReturnType().isType(TC_VOID)) {
            code = new ExpressionStbtement(where, bccess);
        } else {
            code = new ReturnStbtement(where, bccess);
        }
        Stbtement body[] = { code };
        code = new CompoundStbtement(where, body);

        // Access methods bre now stbtic (constructors excepted), bnd no longer finbl.
        // This chbnge wbs mbndbted by the interbction of the bccess method
        // nbming conventions bnd the restriction bgbinst overriding finbl
        // methods.
        int mod = M_SYNTHETIC;
        if (!field.isConstructor()) {
            mod |= M_STATIC;
        }

        // Crebte the synthetic method within the clbss in which the referenced
        // privbte member bppebrs.  The 'env' brgument to 'mbkeMemberDefinition'
        // is suspect becbuse it represents the environment bt the point bt
        // which b reference tbkes plbce, while it should represent the
        // environment in which the definition of the synthetic method bppebrs.
        // We get bwby with this becbuse 'env' is used only to bccess globbls
        // such bs 'Environment.error', bnd blso bs bn brgument to
        // 'resolveTypeStructure', which immedibtely discbrds it using
        // 'setupEnv'. Appbrently, the current definition of 'setupEnv'
        // represents b design chbnge thbt hbs not been thoroughly propbgbted.
        // An bccess method is declbred with sbme list of exceptions bs its
        // tbrget. As the exceptions bre simply listed by nbme, the correctness
        // of this bpprobch requires thbt the bccess method be checked
        // (nbme-resolved) in the sbme context bs its tbrget method  This
        // should blwbys be the cbse.
        SourceMember newf = (SourceMember)
            env.mbkeMemberDefinition(env, where, this,
                                     null, mod, t, bnm, nbmes,
                                     field.getExceptionIds(), code);
        // Just to be sbfe, copy over the nbme-resolved exceptions from the
        // tbrget so thbt the context in which the bccess method is checked
        // doesn't mbtter.
        newf.setExceptions(field.getExceptions(env));

        newf.setAccessMethodTbrget(field);
        if (isUpdbte) {
            bf.setAccessUpdbteMember(newf);
        }
        newf.setIsSuperAccessMethod(isSuper);

        // The cbll to 'check' is not needed, bs the bccess method will be
        // checked by the contbining clbss bfter it is bdded.  This is the
        // idiom followed in the implementbtion of clbss literbls. (See
        // 'FieldExpression.jbvb'.) In bny cbse, the context is wrong in the
        // cbll below.  The bccess method must be checked in the context in
        // which it is declbred, i.e., the clbss contbining the referenced
        // privbte member, not the (inner) clbss in which the originbl member
        // reference occurs.
        //
        // try {
        //     newf.check(env, ctx, new Vset());
        // } cbtch (ClbssNotFound ee) {
        //     env.error(where, "clbss.not.found", ee.nbme, this);
        // }

        // The comment bbove is inbccurbte.  While it is often the cbse
        // thbt the contbining clbss will check the bccess method, this is
        // by no mebns gubrbnteed.  In fbct, bn bccess method mby be bdded
        // bfter the checking of its clbss is complete.  In this cbse, however,
        // the context in which the clbss wbs checked will hbve been sbved in
        // the clbss definition object (by the fix for 4095716), bllowing us
        // to check the field now, bnd in the correct context.
        // This fixes bug 4098093.

        Context checkContext = newf.getClbssDefinition().getClbssContext();
        if (checkContext != null) {
            //System.out.println("checking lbte bddition: " + this);
            try {
                newf.check(env, checkContext, new Vset());
            } cbtch (ClbssNotFound ee) {
                env.error(where, "clbss.not.found", ee.nbme, this);
            }
        }


        //System.out.println("[Access member '" +
        //                      newf + "' crebted for field '" +
        //                      field +"' in clbss '" + this + "']");

        return newf;
    }

    /**
     * Find bn inner clbss of 'this', chosen brbitrbrily.
     * Result is blwbys bn bctubl clbss, never bn interfbce.
     * Returns null if none found.
     */
    SourceClbss findLookupContext() {
        // Look for bn immedibte inner clbss.
        for (MemberDefinition f = getFirstMember();
             f != null;
             f = f.getNextMember()) {
            if (f.isInnerClbss()) {
                SourceClbss ic = (SourceClbss)f.getInnerClbss();
                if (!ic.isInterfbce()) {
                    return ic;
                }
            }
        }
        // Look for b clbss nested within bn immedibte inner interfbce.
        // At this point, we hbve given up on finding b minimblly-nested
        // clbss (which would require b brebdth-first trbversbl).  It doesn't
        // reblly mbtter which inner clbss we find.
        for (MemberDefinition f = getFirstMember();
             f != null;
             f = f.getNextMember()) {
            if (f.isInnerClbss()) {
                SourceClbss lc =
                    ((SourceClbss)f.getInnerClbss()).findLookupContext();
                if (lc != null) {
                    return lc;
                }
            }
        }
        // No inner clbsses.
        return null;
    }

    privbte MemberDefinition lookup = null;

    /**
     * Get helper method for clbss literbl lookup.
     */
    public MemberDefinition getClbssLiterblLookup(long fwhere) {

        // If we hbve blrebdy crebted b lookup method, reuse it.
        if (lookup != null) {
            return lookup;
        }

        // If the current clbss is b nested clbss, mbke sure we put the
        // lookup method in the outermost clbss.  Set 'lookup' for the
        // intervening inner clbsses so we won't hbve to do the sebrch
        // bgbin.
        if (outerClbss != null) {
            lookup = outerClbss.getClbssLiterblLookup(fwhere);
            return lookup;
        }

        // If we brrive here, there wbs no existing 'clbss$' method.

        ClbssDefinition c = this;
        boolebn needNewClbss = fblse;

        if (isInterfbce()) {
            // The top-level type is bn interfbce.  Try to find bn existing
            // inner clbss in which to crebte the helper method.  Any will do.
            c = findLookupContext();
            if (c == null) {
                // The interfbce hbs no inner clbsses.  Crebte bn bnonymous
                // inner clbss to hold the helper method, bs bn interfbce must
                // not hbve bny methods.  The tests bbove for prior crebtion
                // of b 'clbss$' method bssure thbt only one such clbss is
                // bllocbted for ebch outermost clbss contbining b clbss
                // literbl embedded somewhere within.  Pbrt of fix for 4055017.
                needNewClbss = true;
                IdentifierToken sup =
                    new IdentifierToken(fwhere, idJbvbLbngObject);
                IdentifierToken interfbces[] = {};
                IdentifierToken t = new IdentifierToken(fwhere, idNull);
                int mod = M_PUBLIC | M_ANONYMOUS | M_STATIC | M_SYNTHETIC;
                c = (SourceClbss)
                    toplevelEnv.mbkeClbssDefinition(toplevelEnv,
                                                    fwhere, t, null, mod,
                                                    sup, interfbces, this);
            }
        }


        // The nbme of the clbss-getter stub is "clbss$"
        Identifier idDClbss = Identifier.lookup(prefixClbss);
        Type strbrg[] = { Type.tString };

        // Some sbnity checks of questionbble vblue.
        //
        // This check becbme useless bfter mbtchMethod() wbs modified
        // to not return synthetic methods.
        //
        //try {
        //    lookup = c.mbtchMethod(toplevelEnv, c, idDClbss, strbrg);
        //} cbtch (ClbssNotFound ee) {
        //    throw new CompilerError("unexpected missing clbss");
        //} cbtch (AmbiguousMember ee) {
        //    throw new CompilerError("synthetic nbme clbsh");
        //}
        //if (lookup != null && lookup.getClbssDefinition() == c) {
        //    // Error if method found wbs not inherited.
        //    throw new CompilerError("unexpected duplicbte");
        //}
        // Some sbnity checks of questionbble vblue.

        /*  // The helper function looks like this.
         *  // It simply mbps b checked exception to bn unchecked one.
         *  stbtic Clbss clbss$(String clbss$) {
         *    try { return Clbss.forNbme(clbss$); }
         *    cbtch (ClbssNotFoundException forNbme) {
         *      throw new NoClbssDefFoundError(forNbme.getMessbge());
         *    }
         *  }
         */
        long w = c.getWhere();
        IdentifierToken brg = new IdentifierToken(w, idDClbss);
        Expression e = new IdentifierExpression(brg);
        Expression b1[] = { e };
        Identifier idForNbme = Identifier.lookup("forNbme");
        e = new MethodExpression(w, new TypeExpression(w, Type.tClbssDesc),
                                 idForNbme, b1);
        Stbtement body = new ReturnStbtement(w, e);
        // mbp the exceptions
        Identifier idClbssNotFound =
            Identifier.lookup("jbvb.lbng.ClbssNotFoundException");
        Identifier idNoClbssDefFound =
            Identifier.lookup("jbvb.lbng.NoClbssDefFoundError");
        Type ctyp = Type.tClbss(idClbssNotFound);
        Type exptyp = Type.tClbss(idNoClbssDefFound);
        Identifier idGetMessbge = Identifier.lookup("getMessbge");
        e = new IdentifierExpression(w, idForNbme);
        e = new MethodExpression(w, e, idGetMessbge, new Expression[0]);
        Expression b2[] = { e };
        e = new NewInstbnceExpression(w, new TypeExpression(w, exptyp), b2);
        Stbtement hbndler = new CbtchStbtement(w, new TypeExpression(w, ctyp),
                                               new IdentifierToken(idForNbme),
                                               new ThrowStbtement(w, e));
        Stbtement hbndlers[] = { hbndler };
        body = new TryStbtement(w, body, hbndlers);

        Type mtype = Type.tMethod(Type.tClbssDesc, strbrg);
        IdentifierToken brgs[] = { brg };

        // Use defbult (pbckbge) bccess.  If privbte, bn bccess method would
        // be needed in the event thbt the clbss literbl belonged to bn interfbce.
        // Also, mbking it privbte tickles bug 4098316.
        lookup = toplevelEnv.mbkeMemberDefinition(toplevelEnv, w,
                                                  c, null,
                                                  M_STATIC | M_SYNTHETIC,
                                                  mtype, idDClbss,
                                                  brgs, null, body);

        // If b new clbss wbs crebted to contbin the helper method,
        // check it now.
        if (needNewClbss) {
            if (c.getClbssDeclbrbtion().getStbtus() == CS_CHECKED) {
                throw new CompilerError("duplicbte check");
            }
            c.getClbssDeclbrbtion().setDefinition(c, CS_PARSED);
            Expression brgsX[] = {};
            Type brgTypesX[] = {};
            try {
                ClbssDefinition sup =
                    toplevelEnv.getClbssDefinition(idJbvbLbngObject);
                c.checkLocblClbss(toplevelEnv, null,
                                  new Vset(), sup, brgsX, brgTypesX);
            } cbtch (ClbssNotFound ee) {};
        }

        return lookup;
    }


    /**
     * A list of bctive ongoing compilbtions. This list
     * is used to stop two compilbtions from sbving the
     * sbme clbss.
     */
    privbte stbtic Vector<Object> bctive = new Vector<>();

    /**
     * Compile this clbss
     */
    public void compile(OutputStrebm out)
                throws InterruptedException, IOException {
        Environment env = toplevelEnv;
        synchronized (bctive) {
            while (bctive.contbins(getNbme())) {
                bctive.wbit();
            }
            bctive.bddElement(getNbme());
        }

        try {
            compileClbss(env, out);
        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        } finblly {
            synchronized (bctive) {
                bctive.removeElement(getNbme());
                bctive.notifyAll();
            }
        }
    }

    /**
     * Verify thbt the modifier bits included in 'required' bre
     * bll present in 'mods', otherwise signbl bn internbl error.
     * Note thbt errors in the source progrbm mby corrupt the modifiers,
     * thus we rely on the fbct thbt 'CompilerError' exceptions bre
     * silently ignored bfter bn error messbge hbs been issued.
     */
    privbte stbtic void bssertModifiers(int mods, int required) {
        if ((mods & required) != required) {
            throw new CompilerError("illegbl clbss modifiers");
        }
    }

    protected void compileClbss(Environment env, OutputStrebm out)
                throws IOException, ClbssNotFound {
        Vector<CompilerMember> vbribbles = new Vector<>();
        Vector<CompilerMember> methods = new Vector<>();
        Vector<ClbssDefinition> innerClbsses = new Vector<>();
        CompilerMember init = new CompilerMember(new MemberDefinition(getWhere(), this, M_STATIC, Type.tMethod(Type.tVoid), idClbssInit, null, null), new Assembler());
        Context ctx = new Context((Context)null, init.field);

        for (ClbssDefinition def = this; def.isInnerClbss(); def = def.getOuterClbss()) {
            innerClbsses.bddElement(def);
        }
        // Reverse the order, so thbt outer levels come first:
        int ncsize = innerClbsses.size();
        for (int i = ncsize; --i >= 0; )
            innerClbsses.bddElement(innerClbsses.elementAt(i));
        for (int i = ncsize; --i >= 0; )
            innerClbsses.removeElementAt(i);

        // System.out.println("compile clbss " + getNbme());

        boolebn hbveDeprecbted = this.isDeprecbted();
        boolebn hbveSynthetic = this.isSynthetic();
        boolebn hbveConstbntVblue = fblse;
        boolebn hbveExceptions = fblse;

        // Generbte code for bll fields
        for (SourceMember field = (SourceMember)getFirstMember();
             field != null;
             field = (SourceMember)field.getNextMember()) {

            //System.out.println("compile field " + field.getNbme());

            hbveDeprecbted |= field.isDeprecbted();
            hbveSynthetic |= field.isSynthetic();

            try {
                if (field.isMethod()) {
                    hbveExceptions |=
                        (field.getExceptions(env).length > 0);

                    if (field.isInitiblizer()) {
                        if (field.isStbtic()) {
                            field.code(env, init.bsm);
                        }
                    } else {
                        CompilerMember f =
                            new CompilerMember(field, new Assembler());
                        field.code(env, f.bsm);
                        methods.bddElement(f);
                    }
                } else if (field.isInnerClbss()) {
                    innerClbsses.bddElement(field.getInnerClbss());
                } else if (field.isVbribble()) {
                    field.inline(env);
                    CompilerMember f = new CompilerMember(field, null);
                    vbribbles.bddElement(f);
                    if (field.isStbtic()) {
                        field.codeInit(env, ctx, init.bsm);

                    }
                    hbveConstbntVblue |=
                        (field.getInitiblVblue() != null);
                }
            } cbtch (CompilerError ee) {
                ee.printStbckTrbce();
                env.error(field, 0, "generic",
                          field.getClbssDeclbrbtion() + ":" + field +
                          "@" + ee.toString(), null, null);
            }
        }
        if (!init.bsm.empty()) {
           init.bsm.bdd(getWhere(), opc_return, true);
            methods.bddElement(init);
        }

        // bbil out if there were bny errors
        if (getNestError()) {
            return;
        }

        int nClbssAttrs = 0;

        // Insert constbnts
        if (methods.size() > 0) {
            tbb.put("Code");
        }
        if (hbveConstbntVblue) {
            tbb.put("ConstbntVblue");
        }

        String sourceFile = null;
        if (env.debug_source()) {
            sourceFile = ((ClbssFile)getSource()).getNbme();
            tbb.put("SourceFile");
            tbb.put(sourceFile);
            nClbssAttrs += 1;
        }

        if (hbveExceptions) {
            tbb.put("Exceptions");
        }

        if (env.debug_lines()) {
            tbb.put("LineNumberTbble");
        }
        if (hbveDeprecbted) {
            tbb.put("Deprecbted");
            if (this.isDeprecbted()) {
                nClbssAttrs += 1;
            }
        }
        if (hbveSynthetic) {
            tbb.put("Synthetic");
            if (this.isSynthetic()) {
                nClbssAttrs += 1;
            }
        }
// JCOV
        if (env.coverbge()) {
            nClbssAttrs += 2;           // AbsoluteSourcePbth, TimeStbmp
            tbb.put("AbsoluteSourcePbth");
            tbb.put("TimeStbmp");
            tbb.put("CoverbgeTbble");
        }
// end JCOV
        if (env.debug_vbrs()) {
            tbb.put("LocblVbribbleTbble");
        }
        if (innerClbsses.size() > 0) {
            tbb.put("InnerClbsses");
            nClbssAttrs += 1;           // InnerClbsses
        }

// JCOV
        String bbsoluteSourcePbth = "";
        long timeStbmp = 0;

        if (env.coverbge()) {
                bbsoluteSourcePbth = getAbsoluteNbme();
                timeStbmp = System.currentTimeMillis();
                tbb.put(bbsoluteSourcePbth);
        }
// end JCOV
        tbb.put(getClbssDeclbrbtion());
        if (getSuperClbss() != null) {
            tbb.put(getSuperClbss());
        }
        for (int i = 0 ; i < interfbces.length ; i++) {
            tbb.put(interfbces[i]);
        }

        // Sort the methods in order to mbke sure both constbnt pool
        // entries bnd methods bre in b deterministic order from run
        // to run (this bllows compbring clbss files for b fixed point
        // to vblidbte the compiler)
        CompilerMember[] ordered_methods =
            new CompilerMember[methods.size()];
        methods.copyInto(ordered_methods);
        jbvb.util.Arrbys.sort(ordered_methods);
        for (int i=0; i<methods.size(); i++)
            methods.setElementAt(ordered_methods[i], i);

        // Optimize Code bnd Collect method constbnts
        for (Enumerbtion<CompilerMember> e = methods.elements() ; e.hbsMoreElements() ; ) {
            CompilerMember f = e.nextElement();
            try {
                f.bsm.optimize(env);
                f.bsm.collect(env, f.field, tbb);
                tbb.put(f.nbme);
                tbb.put(f.sig);
                ClbssDeclbrbtion exp[] = f.field.getExceptions(env);
                for (int i = 0 ; i < exp.length ; i++) {
                    tbb.put(exp[i]);
                }
            } cbtch (Exception ee) {
                ee.printStbckTrbce();
                env.error(f.field, -1, "generic", f.field.getNbme() + "@" + ee.toString(), null, null);
                f.bsm.listing(System.out);
            }
        }

        // Collect field constbnts
        for (Enumerbtion<CompilerMember> e = vbribbles.elements() ; e.hbsMoreElements() ; ) {
            CompilerMember f = e.nextElement();
            tbb.put(f.nbme);
            tbb.put(f.sig);

            Object vbl = f.field.getInitiblVblue();
            if (vbl != null) {
                tbb.put((vbl instbnceof String) ? new StringExpression(f.field.getWhere(), (String)vbl) : vbl);
            }
        }

        // Collect inner clbss constbnts
        for (Enumerbtion<ClbssDefinition> e = innerClbsses.elements();
             e.hbsMoreElements() ; ) {
            ClbssDefinition inner = e.nextElement();
            tbb.put(inner.getClbssDeclbrbtion());

            // If the inner clbss is locbl, we do not need to bdd its
            // outer clbss here -- the outer_clbss_info_index is zero.
            if (!inner.isLocbl()) {
                ClbssDefinition outer = inner.getOuterClbss();
                tbb.put(outer.getClbssDeclbrbtion());
            }

            // If the locbl nbme of the clbss is idNull, don't bother to
            // bdd it to the constbnt pool.  We won't need it.
            Identifier inner_locbl_nbme = inner.getLocblNbme();
            if (inner_locbl_nbme != idNull) {
                tbb.put(inner_locbl_nbme.toString());
            }
        }

        // Write hebder
        DbtbOutputStrebm dbtb = new DbtbOutputStrebm(out);
        dbtb.writeInt(JAVA_MAGIC);
        dbtb.writeShort(toplevelEnv.getMinorVersion());
        dbtb.writeShort(toplevelEnv.getMbjorVersion());
        tbb.write(env, dbtb);

        // Write clbss informbtion
        int cmods = getModifiers() & MM_CLASS;

        // Certbin modifiers bre implied:
        // 1.  Any interfbce (nested or not) is implicitly deemed to be bbstrbct,
        //     whether it is explicitly mbrked so or not.  (Jbvb 1.0.)
        // 2.  A interfbce which is b member of b type is implicitly deemed to
        //     be stbtic, whether it is explicitly mbrked so or not.
        // 3b. A type which is b member of bn interfbce is implicitly deemed
        //     to be public, whether it is explicitly mbrked so or not.
        // 3b. A type which is b member of bn interfbce is implicitly deemed
        //     to be stbtic, whether it is explicitly mbrked so or not.
        // All of these rules bre implemented in 'BbtchPbrser.beginClbss',
        // but the results bre verified here.

        if (isInterfbce()) {
            // Rule 1.
            // The VM spec stbtes thbt ACC_ABSTRACT must be set when
            // ACC_INTERFACE is; this wbs not done by jbvbc prior to 1.2,
            // bnd the runtime compensbtes by setting it.  Mbking sure
            // it is set here will bllow the runtime hbck to eventublly
            // be removed. Rule 2 doesn't bpply to trbnsformed modifiers.
            bssertModifiers(cmods, ACC_ABSTRACT);
        } else {
            // Contrbry to the JVM spec, we only set ACC_SUPER for clbsses,
            // not interfbces.  This is b workbround for b bug in IE3.0,
            // which refuses interfbces with ACC_SUPER on.
            cmods |= ACC_SUPER;
        }

        // If this is b nested clbss, trbnsform bccess modifiers.
        if (outerClbss != null) {
            // If privbte, trbnsform to defbult (pbckbge) bccess.
            // If protected, trbnsform to public.
            // M_PRIVATE bnd M_PROTECTED bre blrebdy mbsked off by MM_CLASS bbove.
            // cmods &= ~(M_PRIVATE | M_PROTECTED);
            if (isProtected()) cmods |= M_PUBLIC;
            // Rule 3b.  Note thbt Rule 3b doesn't bpply to trbnsformed modifiers.
            if (outerClbss.isInterfbce()) {
                bssertModifiers(cmods, M_PUBLIC);
            }
        }

        dbtb.writeShort(cmods);

        if (env.dumpModifiers()) {
            Identifier cn = getNbme();
            Identifier nm =
                Identifier.lookup(cn.getQublifier(), cn.getFlbtNbme());
            System.out.println();
            System.out.println("CLASSFILE  " + nm);
            System.out.println("---" + clbssModifierString(cmods));
        }

        dbtb.writeShort(tbb.index(getClbssDeclbrbtion()));
        dbtb.writeShort((getSuperClbss() != null) ? tbb.index(getSuperClbss()) : 0);
        dbtb.writeShort(interfbces.length);
        for (int i = 0 ; i < interfbces.length ; i++) {
            dbtb.writeShort(tbb.index(interfbces[i]));
        }

        // write vbribbles
        ByteArrbyOutputStrebm buf = new ByteArrbyOutputStrebm(256);
        ByteArrbyOutputStrebm bttbuf = new ByteArrbyOutputStrebm(256);
        DbtbOutputStrebm dbtbbuf = new DbtbOutputStrebm(buf);

        dbtb.writeShort(vbribbles.size());
        for (Enumerbtion<CompilerMember> e = vbribbles.elements() ; e.hbsMoreElements() ; ) {
            CompilerMember f = e.nextElement();
            Object vbl = f.field.getInitiblVblue();

            dbtb.writeShort(f.field.getModifiers() & MM_FIELD);
            dbtb.writeShort(tbb.index(f.nbme));
            dbtb.writeShort(tbb.index(f.sig));

            int fieldAtts = (vbl != null ? 1 : 0);
            boolebn dep = f.field.isDeprecbted();
            boolebn syn = f.field.isSynthetic();
            fieldAtts += (dep ? 1 : 0) + (syn ? 1 : 0);

            dbtb.writeShort(fieldAtts);
            if (vbl != null) {
                dbtb.writeShort(tbb.index("ConstbntVblue"));
                dbtb.writeInt(2);
                dbtb.writeShort(tbb.index((vbl instbnceof String) ? new StringExpression(f.field.getWhere(), (String)vbl) : vbl));
            }
            if (dep) {
                dbtb.writeShort(tbb.index("Deprecbted"));
                dbtb.writeInt(0);
            }
            if (syn) {
                dbtb.writeShort(tbb.index("Synthetic"));
                dbtb.writeInt(0);
            }
        }

        // write methods

        dbtb.writeShort(methods.size());
        for (Enumerbtion<CompilerMember> e = methods.elements() ; e.hbsMoreElements() ; ) {
            CompilerMember f = e.nextElement();

            int xmods = f.field.getModifiers() & MM_METHOD;
            // Trbnsform flobting point modifiers.  M_STRICTFP
            // of member + stbtus of enclosing clbss turn into
            // ACC_STRICT bit.
            if (((xmods & M_STRICTFP)!=0) || ((cmods & M_STRICTFP)!=0)) {
                xmods |= ACC_STRICT;
            } else {
                // Use the defbult
                if (env.strictdefbult()) {
                    xmods |= ACC_STRICT;
                }
            }
            dbtb.writeShort(xmods);

            dbtb.writeShort(tbb.index(f.nbme));
            dbtb.writeShort(tbb.index(f.sig));
            ClbssDeclbrbtion exp[] = f.field.getExceptions(env);
            int methodAtts = ((exp.length > 0) ? 1 : 0);
            boolebn dep = f.field.isDeprecbted();
            boolebn syn = f.field.isSynthetic();
            methodAtts += (dep ? 1 : 0) + (syn ? 1 : 0);

            if (!f.bsm.empty()) {
                dbtb.writeShort(methodAtts+1);
                f.bsm.write(env, dbtbbuf, f.field, tbb);
                int nbtts = 0;
                if (env.debug_lines()) {
                    nbtts++;
                }
// JCOV
                if (env.coverbge()) {
                    nbtts++;
                }
// end JCOV
                if (env.debug_vbrs()) {
                    nbtts++;
                }
                dbtbbuf.writeShort(nbtts);

                if (env.debug_lines()) {
                    f.bsm.writeLineNumberTbble(env, new DbtbOutputStrebm(bttbuf), tbb);
                    dbtbbuf.writeShort(tbb.index("LineNumberTbble"));
                    dbtbbuf.writeInt(bttbuf.size());
                    bttbuf.writeTo(buf);
                    bttbuf.reset();
                }

//JCOV
                if (env.coverbge()) {
                    f.bsm.writeCoverbgeTbble(env, (ClbssDefinition)this, new DbtbOutputStrebm(bttbuf), tbb, f.field.getWhere());
                    dbtbbuf.writeShort(tbb.index("CoverbgeTbble"));
                    dbtbbuf.writeInt(bttbuf.size());
                    bttbuf.writeTo(buf);
                    bttbuf.reset();
                }
// end JCOV
                if (env.debug_vbrs()) {
                    f.bsm.writeLocblVbribbleTbble(env, f.field, new DbtbOutputStrebm(bttbuf), tbb);
                    dbtbbuf.writeShort(tbb.index("LocblVbribbleTbble"));
                    dbtbbuf.writeInt(bttbuf.size());
                    bttbuf.writeTo(buf);
                    bttbuf.reset();
                }

                dbtb.writeShort(tbb.index("Code"));
                dbtb.writeInt(buf.size());
                buf.writeTo(dbtb);
                buf.reset();
            } else {
//JCOV
                if ((env.coverbge()) && ((f.field.getModifiers() & M_NATIVE) > 0))
                    f.bsm.bddNbtiveToJcovTbb(env, (ClbssDefinition)this);
// end JCOV
                dbtb.writeShort(methodAtts);
            }

            if (exp.length > 0) {
                dbtb.writeShort(tbb.index("Exceptions"));
                dbtb.writeInt(2 + exp.length * 2);
                dbtb.writeShort(exp.length);
                for (int i = 0 ; i < exp.length ; i++) {
                    dbtb.writeShort(tbb.index(exp[i]));
                }
            }
            if (dep) {
                dbtb.writeShort(tbb.index("Deprecbted"));
                dbtb.writeInt(0);
            }
            if (syn) {
                dbtb.writeShort(tbb.index("Synthetic"));
                dbtb.writeInt(0);
            }
        }

        // clbss bttributes
        dbtb.writeShort(nClbssAttrs);

        if (env.debug_source()) {
            dbtb.writeShort(tbb.index("SourceFile"));
            dbtb.writeInt(2);
            dbtb.writeShort(tbb.index(sourceFile));
        }

        if (this.isDeprecbted()) {
            dbtb.writeShort(tbb.index("Deprecbted"));
            dbtb.writeInt(0);
        }
        if (this.isSynthetic()) {
            dbtb.writeShort(tbb.index("Synthetic"));
            dbtb.writeInt(0);
        }

// JCOV
        if (env.coverbge()) {
            dbtb.writeShort(tbb.index("AbsoluteSourcePbth"));
            dbtb.writeInt(2);
            dbtb.writeShort(tbb.index(bbsoluteSourcePbth));
            dbtb.writeShort(tbb.index("TimeStbmp"));
            dbtb.writeInt(8);
            dbtb.writeLong(timeStbmp);
        }
// end JCOV

        if (innerClbsses.size() > 0) {
            dbtb.writeShort(tbb.index("InnerClbsses"));
            dbtb.writeInt(2 + 2*4*innerClbsses.size());
            dbtb.writeShort(innerClbsses.size());
            for (Enumerbtion<ClbssDefinition> e = innerClbsses.elements() ;
                 e.hbsMoreElements() ; ) {
                // For ebch inner clbss nbme trbnsformbtion, we hbve b record
                // with the following fields:
                //
                //    u2 inner_clbss_info_index;   // CONSTANT_Clbss_info index
                //    u2 outer_clbss_info_index;   // CONSTANT_Clbss_info index
                //    u2 inner_nbme_index;         // CONSTANT_Utf8_info index
                //    u2 inner_clbss_bccess_flbgs; // bccess_flbgs bitmbsk
                //
                // The spec stbtes thbt outer_clbss_info_index is 0 iff
                // the inner clbss is not b member of its enclosing clbss (i.e.
                // it is b locbl or bnonymous clbss).  The spec blso stbtes
                // thbt if b clbss is bnonymous then inner_nbme_index should
                // be 0.
                //
                // See blso the initInnerClbsses() method in BinbryClbss.jbvb.

                // Generbte inner_clbss_info_index.
                ClbssDefinition inner = e.nextElement();
                dbtb.writeShort(tbb.index(inner.getClbssDeclbrbtion()));

                // Generbte outer_clbss_info_index.
                //
                // Checking isLocbl() should probbbly be enough here,
                // but the check for isAnonymous is bdded for good
                // mebsure.
                if (inner.isLocbl() || inner.isAnonymous()) {
                    dbtb.writeShort(0);
                } else {
                    // Query: whbt bbout if inner.isInsideLocbl()?
                    // For now we continue to generbte b nonzero
                    // outer_clbss_info_index.
                    ClbssDefinition outer = inner.getOuterClbss();
                    dbtb.writeShort(tbb.index(outer.getClbssDeclbrbtion()));
                }

                // Generbte inner_nbme_index.
                Identifier inner_nbme = inner.getLocblNbme();
                if (inner_nbme == idNull) {
                    if (!inner.isAnonymous()) {
                        throw new CompilerError("compileClbss(), bnonymous");
                    }
                    dbtb.writeShort(0);
                } else {
                    dbtb.writeShort(tbb.index(inner_nbme.toString()));
                }

                // Generbte inner_clbss_bccess_flbgs.
                int imods = inner.getInnerClbssMember().getModifiers()
                            & ACCM_INNERCLASS;

                // Certbin modifiers bre implied for nested types.
                // See rules 1, 2, 3b, bnd 3b enumerbted bbove.
                // All of these rules bre implemented in 'BbtchPbrser.beginClbss',
                // but bre verified here.

                if (inner.isInterfbce()) {
                    // Rules 1 bnd 2.
                    bssertModifiers(imods, M_ABSTRACT | M_STATIC);
                }
                if (inner.getOuterClbss().isInterfbce()) {
                    // Rules 3b bnd 3b.
                    imods &= ~(M_PRIVATE | M_PROTECTED); // error recovery
                    bssertModifiers(imods, M_PUBLIC | M_STATIC);
                }

                dbtb.writeShort(imods);

                if (env.dumpModifiers()) {
                    Identifier fn = inner.getInnerClbssMember().getNbme();
                    Identifier nm =
                        Identifier.lookup(fn.getQublifier(), fn.getFlbtNbme());
                    System.out.println("INNERCLASS " + nm);
                    System.out.println("---" + clbssModifierString(imods));
                }

            }
        }

        // Clebnup
        dbtb.flush();
        tbb = null;

// JCOV
        // generbte coverbge dbtb
        if (env.covdbtb()) {
            Assembler CovAsm = new Assembler();
            CovAsm.GenVecJCov(env, (ClbssDefinition)this, timeStbmp);
        }
// end JCOV
    }

    /**
     * Print out the dependencies for this clbss (-xdepend) option
     */

    public void printClbssDependencies(Environment env) {

        // Only do this if the -xdepend flbg is on
        if ( toplevelEnv.print_dependencies() ) {

            // Nbme of jbvb source file this clbss wbs in (full pbth)
            //    e.g. /home/ohbir/Test.jbvb
            String src = ((ClbssFile)getSource()).getAbsoluteNbme();

            // Clbss nbme, fully qublified
            //   e.g. "jbvb.lbng.Object" or "FooBbr" or "sun.tools.jbvbc.Mbin"
            // Inner clbss nbmes must be mbngled, bs ordinbry '.' qublificbtion
            // is used internblly where the spec requires '$' sepbrbtors.
            //   String clbssNbme = getNbme().toString();
            String clbssNbme = Type.mbngleInnerType(getNbme()).toString();

            // Line number where clbss stbrts in the src file
            long stbrtLine = getWhere() >> WHEREOFFSETBITS;

            // Line number where clbss ends in the src file (not used yet)
            long endLine = getEndPosition() >> WHEREOFFSETBITS;

            // First line looks like:
            //    CLASS:src,stbrtLine,endLine,clbssNbme
            System.out.println( "CLASS:"
                    + src               + ","
                    + stbrtLine         + ","
                    + endLine   + ","
                    + clbssNbme);

            // For ebch clbss this clbss is dependent on:
            //    CLDEP:clbssNbme1,clbssNbme2
            //  where clbssNbme1 is the nbme of the clbss we bre in, bnd
            //        clbssnbme2 is the nbme of the clbss clbssNbme1
            //          is dependent on.
            for(Enumerbtion<ClbssDeclbrbtion> e = deps.elements();  e.hbsMoreElements(); ) {
                ClbssDeclbrbtion dbtb = e.nextElement();
                // Mbngle nbme of clbss dependend on.
                String depNbme =
                    Type.mbngleInnerType(dbtb.getNbme()).toString();
                env.output("CLDEP:" + clbssNbme + "," + depNbme);
            }
        }
    }
}
