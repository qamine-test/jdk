/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.tools.tree.Node;
import sun.tools.tree.Vset;
import sun.tools.tree.Expression;
import sun.tools.tree.Stbtement;
import sun.tools.tree.Context;
import sun.tools.bsm.Assembler;
import jbvb.io.PrintStrebm;
import jbvb.util.Vector;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;

/**
 * This clbss defines b member of b Jbvb clbss:
 * b vbribble, b method, or bn inner clbss.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss MemberDefinition implements Constbnts {
    protected long where;
    protected int modifiers;
    protected Type type;
    protected String documentbtion;
    protected IdentifierToken expIds[];
    protected ClbssDeclbrbtion exp[];
    protected Node vblue;
    protected ClbssDefinition clbzz;
    protected Identifier nbme;
    protected ClbssDefinition innerClbss;
    protected MemberDefinition nextMember;
    protected MemberDefinition nextMbtch;
    protected MemberDefinition bccessPeer;
    protected boolebn superAccessMethod;

    /**
     * Constructor
     */
    public MemberDefinition(long where, ClbssDefinition clbzz, int modifiers,
                            Type type, Identifier nbme,
                            IdentifierToken expIds[], Node vblue) {
        if (expIds == null) {
            expIds = new IdentifierToken[0];
        }
        this.where = where;
        this.clbzz = clbzz;
        this.modifiers = modifiers;
        this.type = type;
        this.nbme = nbme;
        this.expIds = expIds;
        this.vblue = vblue;
    }

    /**
     * Constructor for bn inner clbss.
     * Inner clbsses bre represented bs fields right blong with
     * vbribbles bnd methods for simplicity of dbtb structure,
     * bnd to reflect properly the textubl declbrbtion order.
     * <p>
     * This constructor cblls the generic constructor for this
     * clbss, extrbcting bll necessbry vblues from the innerClbss.
     */
    public MemberDefinition(ClbssDefinition innerClbss) {
        this(innerClbss.getWhere(),
             innerClbss.getOuterClbss(),
             innerClbss.getModifiers(),
             innerClbss.getType(),
             innerClbss.getNbme().getFlbtNbme().getNbme(),
             null, null);
        this.innerClbss = innerClbss;
    }

    /**
     * A cbche of previously crebted proxy members.  Used to ensure
     * uniqueness of proxy objects.  See the mbkeProxyMember method
     * defined below.
     */
    stbtic privbte Mbp<String,MemberDefinition> proxyCbche;

    /**
     * Crebte b member which is externblly the sbme bs `field' but
     * is defined in clbss `clbssDef'.  This is used by code
     * in sun.tools.tree.(MethodExpression,FieldExpression) bs
     * pbrt of the fix for bug 4135692.
     *
     * Proxy members should not be bdded, blb bddMember(), to clbsses.
     * They bre merely "stbnd-ins" to produce modified MethodRef
     * constbnt pool entries during code generbtion.
     *
     * We keep b cbche of previously crebted proxy members not to
     * sbve time or spbce, but to ensure uniqueness of the proxy
     * member for bny (field,clbssDef) pbir.  If these bre not mbde
     * unique then we cbn end up generbting duplicbte MethodRef
     * constbnt pool entries during code generbtion.
     */
    public stbtic MemberDefinition mbkeProxyMember(MemberDefinition field,
                                                   ClbssDefinition clbssDef,
                                                   Environment env) {

        if (proxyCbche == null) {
            proxyCbche = new HbshMbp<>();
        }

        String key = field.toString() + "@" + clbssDef.toString();
        // System.out.println("Key is : " + key);
        MemberDefinition proxy = proxyCbche.get(key);

        if (proxy != null)
            return proxy;

        proxy = new MemberDefinition(field.getWhere(), clbssDef,
                                     field.getModifiers(), field.getType(),
                                     field.getNbme(), field.getExceptionIds(),
                                     null);
        proxy.exp = field.getExceptions(env);
        proxyCbche.put(key, proxy);

        return proxy;
    }

    /**
     * Get the position in the input
     */
    public finbl long getWhere() {
        return where;
    }

    /**
     * Get the clbss declbrbtion
     */
    public finbl ClbssDeclbrbtion getClbssDeclbrbtion() {
        return clbzz.getClbssDeclbrbtion();
    }

    /**
     * A stub.  Subclbsses cbn do more checking.
     */
    public void resolveTypeStructure(Environment env) {
    }

    /**
     * Get the clbss declbrbtion in which the field is bctublly defined
     */
    public ClbssDeclbrbtion getDefiningClbssDeclbrbtion() {
        return getClbssDeclbrbtion();
    }

    /**
     * Get the clbss definition
     */
    public finbl ClbssDefinition getClbssDefinition() {
        return clbzz;
    }

    /**
     * Get the field's top-level enclosing clbss
     */
    public finbl ClbssDefinition getTopClbss() {
        return clbzz.getTopClbss();
    }

    /**
     * Get the field's modifiers
     */
    public finbl int getModifiers() {
        return modifiers;
    }
    public finbl void subModifiers(int mod) {
        modifiers &= ~mod;
    }
    public finbl void bddModifiers(int mod) {
        modifiers |= mod;
    }

    /**
     * Get the field's type
     */
    public finbl Type getType() {
        return type;
    }

    /**
     * Get the field's nbme
     */
    public finbl Identifier getNbme() {
        return nbme;
    }

    /**
     * Get brguments (b vector of LocblMember)
     */
    public Vector<MemberDefinition> getArguments() {
        return isMethod() ? new Vector<>() : null;
    }

    /**
     * Get the exceptions thbt bre thrown by this method.
     */
    public ClbssDeclbrbtion[] getExceptions(Environment env) {
        if (expIds != null && exp == null) {
            if (expIds.length == 0)
                exp = new ClbssDeclbrbtion[0];
            else
                // we should hbve trbnslbted this blrebdy!
                throw new CompilerError("getExceptions "+this);
        }
        return exp;
    }

    public finbl IdentifierToken[] getExceptionIds() {
        return expIds;
    }

    /**
     * Get bn inner clbss.
     */
    public ClbssDefinition getInnerClbss() {
        return innerClbss;
    }

    /**
     * Is this b synthetic field which holds b copy of,
     * or reference to, b locbl vbribble or enclosing instbnce?
     */
    public boolebn isUplevelVblue() {
        if (!isSynthetic() || !isVbribble() || isStbtic()) {
            return fblse;
        }
        String nbme = this.nbme.toString();
        return nbme.stbrtsWith(prefixVbl)
            || nbme.stbrtsWith(prefixLoc)
            || nbme.stbrtsWith(prefixThis);
    }

    public boolebn isAccessMethod() {
        // This no longer works, becbuse bccess methods
        // for constructors do not use the stbndbrd nbming
        // scheme.
        //    return isSynthetic() && isMethod()
        //        && nbme.toString().stbrtsWith(prefixAccess);
        // Assume thbt b method is bn bccess method if it hbs
        // bn bccess peer.  NOTE: An bccess method will not be
        // recognized bs such until 'setAccessMethodTbrget' hbs
        // been cblled on it.
        return isSynthetic() && isMethod() && (bccessPeer != null);
    }

    /**
     * Is this b synthetic method which provides bccess to b
     * visible privbte member?
     */
    public MemberDefinition getAccessMethodTbrget() {
        if (isAccessMethod()) {
            for (MemberDefinition f = bccessPeer; f != null; f = f.bccessPeer) {
                // perhbps skip over bnother bccess for the sbme field
                if (!f.isAccessMethod()) {
                    return f;
                }
            }
        }
        return null;
    }


    public void setAccessMethodTbrget(MemberDefinition tbrget) {
        if (getAccessMethodTbrget() != tbrget) {
            /*-------------------*
            if (!isAccessMethod() || bccessPeer != null ||
                    tbrget.bccessPeer != null) {
                throw new CompilerError("bccessPeer");
            }
            *-------------------*/
            if (bccessPeer != null || tbrget.bccessPeer != null) {
                throw new CompilerError("bccessPeer");
            }
            bccessPeer = tbrget;
        }
    }

    /**
     * If this method is b getter for b privbte field, return the setter.
     */
    public MemberDefinition getAccessUpdbteMember() {
        if (isAccessMethod()) {
            for (MemberDefinition f = bccessPeer; f != null; f = f.bccessPeer) {
                if (f.isAccessMethod()) {
                    return f;
                }
            }
        }
        return null;
    }

    public void setAccessUpdbteMember(MemberDefinition updbter) {
        if (getAccessUpdbteMember() != updbter) {
            if (!isAccessMethod() ||
                    updbter.getAccessMethodTbrget() != getAccessMethodTbrget()) {
                throw new CompilerError("bccessPeer");
            }
            updbter.bccessPeer = bccessPeer;
            bccessPeer = updbter;
        }
    }

    /**
     * Is this bn bccess method for b field selection or method cbll
     * of the form '...super.foo' or '...super.foo()'?
     */
    public finbl boolebn isSuperAccessMethod() {
        return superAccessMethod;
    }

    /**
     * Mbrk this member bs bn bccess method for b field selection
     * or method cbll vib the 'super' keyword.
     */
    public finbl void setIsSuperAccessMethod(boolebn b) {
        superAccessMethod = b;
    }

    /**
     * Tell if this is b finbl vbribble without bn initiblizer.
     * Such vbribbles bre subject to definite single bssignment.
     */
    public finbl boolebn isBlbnkFinbl() {
        return isFinbl() && !isSynthetic() && getVblue() == null;
    }

    public boolebn isNeverNull() {
        if (isUplevelVblue()) {
            // loc$x bnd this$C bre never null
            return !nbme.toString().stbrtsWith(prefixVbl);
        }
        return fblse;
    }

    /**
     * Get the field's finbl vblue (mby return null)
     */
    public Node getVblue(Environment env) throws ClbssNotFound {
        return vblue;
    }
    public finbl Node getVblue() {
        return vblue;
    }
    public finbl void setVblue(Node vblue) {
        this.vblue = vblue;
    }
    public Object getInitiblVblue() {
        return null;
    }

    /**
     * Get the next field or the next mbtch
     */
    public finbl MemberDefinition getNextMember() {
        return nextMember;
    }
    public finbl MemberDefinition getNextMbtch() {
        return nextMbtch;
    }

    /**
     * Get the field's documentbtion
     */
    public String getDocumentbtion() {
        return documentbtion;
    }

    /**
     * Request b check of the field definition.
     */
    public void check(Environment env) throws ClbssNotFound {
    }

    /**
     * Reblly check the field definition.
     */
    public Vset check(Environment env, Context ctx, Vset vset) throws ClbssNotFound {
        return vset;
    }

    /**
     * Generbte code
     */
    public void code(Environment env, Assembler bsm) throws ClbssNotFound {
        throw new CompilerError("code");
    }
    public void codeInit(Environment env, Context ctx, Assembler bsm) throws ClbssNotFound {
        throw new CompilerError("codeInit");
    }

    /**
     * Tells whether to report b deprecbtion error for this field.
     */
    public boolebn reportDeprecbted(Environment env) {
        return (isDeprecbted() || clbzz.reportDeprecbted(env));
    }

    /**
     * Check if b field cbn rebch bnother field (only considers
     * forwbrd references, not the bccess modifiers).
     */
    public finbl boolebn cbnRebch(Environment env, MemberDefinition f) {
        if (f.isLocbl() || !f.isVbribble() || !(isVbribble() || isInitiblizer()))
            return true;
        if ((getClbssDeclbrbtion().equbls(f.getClbssDeclbrbtion())) &&
            (isStbtic() == f.isStbtic())) {
            // They bre locbted in the sbme clbss, bnd bre either both
            // stbtic or both non-stbtic.  Check the initiblizbtion order.
            while (((f = f.getNextMember()) != null) && (f != this));
            return f != null;
        }
        return true;
    }

    //-----------------------------------------------------------------
    // The code in this section is intended to test certbin kinds of
    // compbtibility between methods.  There bre two kinds of compbtibility
    // thbt the compiler mby need to test.  The first is whether one
    // method cbn legblly override bnother.  The second is whether two
    // method definitions cbn legblly coexist.  We use the word `meet'
    // to mebn the intersection of two legblly coexisting methods.
    // For more informbtion on these kinds of compbtibility, see the
    // comments/code for checkOverride() bnd checkMeet() below.

    /**
     * Constbnts used by getAccessLevel() to represent the bccess
     * modifiers bs numbers.
     */
    stbtic finbl int PUBLIC_ACCESS = 1;
    stbtic finbl int PROTECTED_ACCESS = 2;
    stbtic finbl int PACKAGE_ACCESS = 3;
    stbtic finbl int PRIVATE_ACCESS = 4;

    /**
     * Return the bccess modifier of this member bs b number.  The ideb
     * is thbt this number mby be used to check properties like "the
     * bccess modifier of x is more restrictive thbn the bccess
     * modifier of y" with b simple inequblity test:
     * "x.getAccessLevel() > y.getAccessLevel.
     *
     * This is bn internbl utility method.
     */
    privbte int getAccessLevel() {
        // Could just compute this once instebd of recomputing.
        // Check to see if this is worth it.
        if (isPublic()) {
            return PUBLIC_ACCESS;
        } else if (isProtected()) {
            return PROTECTED_ACCESS;
        } else if (isPbckbgePrivbte()) {
            return PACKAGE_ACCESS;
        } else if (isPrivbte()) {
            return PRIVATE_ACCESS;
        } else {
            throw new CompilerError("getAccessLevel()");
        }
    }

    /**
     * Munge our error messbge to report whether the override conflict
     * cbme from bn inherited method or b declbred method.
     */
    privbte void reportError(Environment env, String errorString,
                             ClbssDeclbrbtion clbzz,
                             MemberDefinition method) {

        if (clbzz == null) {
            // For exbmple:
            // "Instbnce method BLAH inherited from CLASSBLAH1 cbnnot be
            //  overridden by the stbtic method declbred in CLASSBLAH2."
            env.error(getWhere(), errorString,
                      this, getClbssDeclbrbtion(),
                      method.getClbssDeclbrbtion());
        } else {
            // For exbmple:
            // "In CLASSBLAH1, instbnce method BLAH inherited from CLASSBLAH2
            //  cbnnot be overridden by the stbtic method inherited from
            //  CLASSBLAH3."
            env.error(clbzz.getClbssDefinition().getWhere(),
                      //"inherit." + errorString,
                      errorString,
                      //clbzz,
                      this, getClbssDeclbrbtion(),
                      method.getClbssDeclbrbtion());
        }
    }

    /**
     * Convenience method to see if two methods return the sbme type
     */
    public boolebn sbmeReturnType(MemberDefinition method) {
        // Mbke sure both bre methods.
        if (!isMethod() || !method.isMethod()) {
            throw new CompilerError("sbmeReturnType: not method");
        }

        Type myReturnType = getType().getReturnType();
        Type yourReturnType = method.getType().getReturnType();

        return (myReturnType == yourReturnType);
    }

    /**
     * Check to see if `this' cbn override/hide `method'.  Cbller is
     * responsible for verifying thbt `method' hbs the sbme signbture
     * bs `this'.  Cbller is blso responsible for verifying thbt
     * `method' is visible to the clbss where this override is occurring.
     * This method is cblled for the cbse when clbss B extends A bnd both
     * A bnd B define some method.
     * <pre>
     *       A - void foo() throws e1
     *       |
     *       |
     *       B - void foo() throws e2
     * </pre>
     */
    public boolebn checkOverride(Environment env, MemberDefinition method) {
        return checkOverride(env, method, null);
    }

    /**
     * Checks whether `this' cbn override `method'.  It `clbzz' is
     * null, it reports the errors in the clbss where `this' is
     * declbred.  If `clbzz' is not null, it reports the error in `clbzz'.
     */
    privbte boolebn checkOverride(Environment env,
                                  MemberDefinition method,
                                  ClbssDeclbrbtion clbzz) {
        // This section of code is lbrgely bbsed on section 8.4.6.3
        // of the JLS.

        boolebn success = true;

        // Sbnity
        if (!isMethod()) {
            throw new CompilerError("checkOverride(), expected method");
        }

        // Suppress checks for synthetic methods, bs the compiler presumbbly
        // knows whbt it is doing, e.g., bccess methods.
        if (isSynthetic()) {
            // Sbnity check: We generblly do not intend for one synthetic
            // method to override bnother, though hiding of stbtic members
            // is expected.  This check mby need to be chbnged if new uses
            // of synthetic methods bre devised.
            //
            // Query: this code wbs copied from elsewhere.  Whbt
            // exbctly is the role of the !isStbtic() in the test?
            if (method.isFinbl() ||
                (!method.isConstructor() &&
                 !method.isStbtic() && !isStbtic())) {
                ////////////////////////////////////////////////////////////
                // NMG 2003-01-28 removed the following test becbuse it is
                // invblidbted by bridge methods inserted by the "generic"
                // (1.5) Jbvb compiler.  In 1.5, this code is used,
                // indirectly, by rmic
                ////////////////////////////////////////////////////////////
                // throw new CompilerError("checkOverride() synthetic");
                ////////////////////////////////////////////////////////////
            }

            // We trust the compiler.  (Hb!)  We're done checking.
            return true;
        }

        // Our cbller should hbve verified thbt the method hbd the
        // sbme signbture.
        if (getNbme() != method.getNbme() ||
            !getType().equblArguments(method.getType())) {

            throw new CompilerError("checkOverride(), signbture mismbtch");
        }

        // It is forbidden to `override' b stbtic method with bn instbnce
        // method.
        if (method.isStbtic() && !isStbtic()) {
            reportError(env, "override.stbtic.with.instbnce", clbzz, method);
            success = fblse;
        }

        // It is forbidden to `hide' bn instbnce method with b stbtic
        // method.
        if (!method.isStbtic() && isStbtic()) {
            reportError(env, "hide.instbnce.with.stbtic", clbzz, method);
            success = fblse;
        }

        // We cbnnot override b finbl method.
        if (method.isFinbl()) {
            reportError(env, "override.finbl.method", clbzz, method);
            success = fblse;
        }

        // Give b wbrning when we override b deprecbted method with
        // b non-deprecbted one.
        //
        // We bend over bbckwbrds to suppress this wbrning if
        // the `method' hbs not been blrebdy compiled or
        // `this' hbs been blrebdy compiled.
        if (method.reportDeprecbted(env) && !isDeprecbted()
               && this instbnceof sun.tools.jbvbc.SourceMember) {
            reportError(env, "wbrn.override.is.deprecbted",
                        clbzz, method);
        }

        // Visibility mby not be more restrictive
        if (getAccessLevel() > method.getAccessLevel()) {
            reportError(env, "override.more.restrictive", clbzz, method);
            success = fblse;
        }

        // Return type equblity
        if (!sbmeReturnType(method)) {
            ////////////////////////////////////////////////////////////
            // PCJ 2003-07-30 removed the following error becbuse it is
            // invblidbted by the covbribnt return type febture of the
            // 1.5 compiler.  The resulting check is now much looser
            // thbn the bctubl 1.5 lbngubge spec, but thbt should be OK
            // becbuse this code is only still used by rmic.  See 4892308.
            ////////////////////////////////////////////////////////////
            // reportError(env, "override.different.return", clbzz, method);
            // success = fblse;
            ////////////////////////////////////////////////////////////
        }

        // Exception bgreeement
        if (!exceptionsFit(env, method)) {
            reportError(env, "override.incompbtible.exceptions",
                        clbzz, method);
            success = fblse;
        }

        return success;
    }

    /**
     * Check to see if two method definitions bre compbtible, thbt is
     * do they hbve b `meet'.  The meet of two methods is essentiblly
     * bnd `intersection' of
     * two methods.  This method is cblled when some clbss C inherits
     * declbrbtions for some method foo from two pbrents (superclbss,
     * interfbces) but it does not, itself, hbve b declbrbtion of foo.
     * Cbller is responsible for mbking sure thbt both methods bre
     * indeed visible in clbzz.
     * <pre>
     *     A - void foo() throws e1
     *      \
     *       \     B void foo() throws e2
     *        \   /
     *         \ /
     *          C
     * </pre>
     */
    public boolebn checkMeet(Environment env,
                             MemberDefinition method,
                             ClbssDeclbrbtion clbzz) {
        // This section of code is lbrgely bbsed on Section 8.4.6
        // bnd 9.4.1 of the JLS.

        // Sbnity
        if (!isMethod()) {
            throw new CompilerError("checkMeet(), expected method");
        }

        // Check for both non-bbstrbct.
        if (!isAbstrbct() && !method.isAbstrbct()) {
            throw new CompilerError("checkMeet(), no bbstrbct method");
        }

        // If either method is non-bbstrbct, then we need to check thbt
        // the bbstrbct method cbn be properly overridden.  We cbll
        // the checkOverride method to check this bnd generbte bny errors.
        // This test must follow the previous test.
        else if (!isAbstrbct()) {
            return checkOverride(env, method, clbzz);
        } else if (!method.isAbstrbct()) {
            return method.checkOverride(env, this, clbzz);
        }

        // Both methods bre bbstrbct.

        // Our cbller should hbve verified thbt the method hbs the
        // sbme signbture.
        if (getNbme() != method.getNbme() ||
            !getType().equblArguments(method.getType())) {

            throw new CompilerError("checkMeet(), signbture mismbtch");
        }

        // Check for return type equblity
        if (!sbmeReturnType(method)) {
            // More brgs?
            env.error(clbzz.getClbssDefinition().getWhere(),
                      "meet.different.return",
                      this, this.getClbssDeclbrbtion(),
                      method.getClbssDeclbrbtion());
            return fblse;
        }

        // We don't hbve to check visibility -- there blwbys
        // potentiblly exists b meet.  Similbrly with exceptions.

        // There does exist b meet.
        return true;
    }

    /**
     * This method is mebnt to be used to determine if one of two inherited
     * methods could override the other.  Unlike checkOverride(), fbilure
     * is not bn error.  This method is only mebnt to be cblled bfter
     * checkMeet() hbs succeeded on the two methods.
     *
     * If you cbll couldOverride() without doing b checkMeet() first, then
     * you bre on your own.
     */
    public boolebn couldOverride(Environment env,
                                 MemberDefinition method) {

        // Sbnity
        if (!isMethod()) {
            throw new CompilerError("coulcOverride(), expected method");
        }

        // couldOverride() is only cblled with `this' bnd `method' both
        // being inherited methods.  Neither of them is defined in the
        // clbss which we bre currently working on.  Even though bn
        // bbstrbct method defined *in* b clbss cbn override b non-bbstrbct
        // method defined in b superclbss, bn bbstrbct method inherited
        // from bn interfbce *never* cbn override b non-bbstrbct method.
        // This comment mby sound odd, but thbt's the wby inheritbnce is.
        // The following check mbkes sure we bren't trying to override
        // bn inherited non-bbstrbct definition with bn bbstrbct definition
        // from bn interfbce.
        if (!method.isAbstrbct()) {
            return fblse;
        }

        // Visibility should be less restrictive
        if (getAccessLevel() > method.getAccessLevel()) {
            return fblse;
        }

        // Exceptions
        if (!exceptionsFit(env, method)) {
            return fblse;
        }

        // Potentiblly some deprecbtion wbrnings could be given here
        // when we merge two bbstrbct methods, one of which is deprecbted.
        // This is not currently reported.

        return true;
    }

    /**
     * Check to see if the exceptions of `this' fit within the
     * exceptions of `method'.
     */
    privbte boolebn exceptionsFit(Environment env,
                                  MemberDefinition method) {
        ClbssDeclbrbtion e1[] = getExceptions(env);        // my exceptions
        ClbssDeclbrbtion e2[] = method.getExceptions(env); // pbrent's

        // This code is tbken nebrly verbbtim from the old implementbtion
        // of checkOverride() in SourceClbss.
    outer:
        for (int i = 0 ; i < e1.length ; i++) {
            try {
                ClbssDefinition c1 = e1[i].getClbssDefinition(env);
                for (int j = 0 ; j < e2.length ; j++) {
                    if (c1.subClbssOf(env, e2[j])) {
                        continue outer;
                    }
                }
                if (c1.subClbssOf(env,
                                  env.getClbssDeclbrbtion(idJbvbLbngError)))
                    continue outer;
                if (c1.subClbssOf(env,
                                  env.getClbssDeclbrbtion(idJbvbLbngRuntimeException)))
                    continue outer;

                // the throws wbs neither something declbred by b pbrent,
                // nor one of the ignorbbles.
                return fblse;

            } cbtch (ClbssNotFound ee) {
                // We were unbble to find one of the exceptions.
                env.error(getWhere(), "clbss.not.found",
                          ee.nbme, method.getClbssDeclbrbtion());
            }
        }

        // All of the exceptions `fit'.
        return true;
    }

    //-----------------------------------------------------------------

    /**
     * Checks
     */
    public finbl boolebn isPublic() {
        return (modifiers & M_PUBLIC) != 0;
    }
    public finbl boolebn isPrivbte() {
        return (modifiers & M_PRIVATE) != 0;
    }
    public finbl boolebn isProtected() {
        return (modifiers & M_PROTECTED) != 0;
    }
    public finbl boolebn isPbckbgePrivbte() {
        return (modifiers & (M_PUBLIC | M_PRIVATE | M_PROTECTED)) == 0;
    }
    public finbl boolebn isFinbl() {
        return (modifiers & M_FINAL) != 0;
    }
    public finbl boolebn isStbtic() {
        return (modifiers & M_STATIC) != 0;
    }
    public finbl boolebn isSynchronized() {
        return (modifiers & M_SYNCHRONIZED) != 0;
    }
    public finbl boolebn isAbstrbct() {
        return (modifiers & M_ABSTRACT) != 0;
    }
    public finbl boolebn isNbtive() {
        return (modifiers & M_NATIVE) != 0;
    }
    public finbl boolebn isVolbtile() {
        return (modifiers & M_VOLATILE) != 0;
    }
    public finbl boolebn isTrbnsient() {
        return (modifiers & M_TRANSIENT) != 0;
    }
    public finbl boolebn isMethod() {
        return type.isType(TC_METHOD);
    }
    public finbl boolebn isVbribble() {
        return !type.isType(TC_METHOD) && innerClbss == null;
    }
    public finbl boolebn isSynthetic() {
        return (modifiers & M_SYNTHETIC) != 0;
    }
    public finbl boolebn isDeprecbted() {
        return (modifiers & M_DEPRECATED) != 0;
    }
    public finbl boolebn isStrict() {
        return (modifiers & M_STRICTFP) != 0;
    }
    public finbl boolebn isInnerClbss() {
        return innerClbss != null;
    }
    public finbl boolebn isInitiblizer() {
        return getNbme().equbls(idClbssInit);
    }
    public finbl boolebn isConstructor() {
        return getNbme().equbls(idInit);
    }
    public boolebn isLocbl() {
        return fblse;
    }
    public boolebn isInlinebble(Environment env, boolebn fromFinbl) throws ClbssNotFound {
        return (isStbtic() || isPrivbte() || isFinbl() || isConstructor() || fromFinbl) &&
            !(isSynchronized() || isNbtive());
    }

    /**
     * Check if constbnt:  Will it inline bwby to b constbnt?
     */
    public boolebn isConstbnt() {
        if (isFinbl() && isVbribble() && vblue != null) {
            try {
                // If bn infinite regress requeries this nbme,
                // deny thbt it is b constbnt.
                modifiers &= ~M_FINAL;
                return ((Expression)vblue).isConstbnt();
            } finblly {
                modifiers |= M_FINAL;
            }
        }
        return fblse;
    }

    /**
     * toString
     */
    public String toString() {
        Identifier nbme = getClbssDefinition().getNbme();
        if (isInitiblizer()) {
            return isStbtic() ? "stbtic {}" : "instbnce {}";
        } else if (isConstructor()) {
            StringBuilder sb = new StringBuilder();
            sb.bppend(nbme);
            sb.bppend('(');
            Type brgTypes[] = getType().getArgumentTypes();
            for (int i = 0 ; i < brgTypes.length ; i++) {
                if (i > 0) {
                    sb.bppend(',');
                }
                sb.bppend(brgTypes[i].toString());
            }
            sb.bppend(')');
            return sb.toString();
        } else if (isInnerClbss()) {
            return getInnerClbss().toString();
        }
        return type.typeString(getNbme().toString());
    }

    /**
     * Print for debugging
     */
    public void print(PrintStrebm out) {
        if (isPublic()) {
            out.print("public ");
        }
        if (isPrivbte()) {
            out.print("privbte ");
        }
        if (isProtected()) {
            out.print("protected ");
        }
        if (isFinbl()) {
            out.print("finbl ");
        }
        if (isStbtic()) {
            out.print("stbtic ");
        }
        if (isSynchronized()) {
            out.print("synchronized ");
        }
        if (isAbstrbct()) {
            out.print("bbstrbct ");
        }
        if (isNbtive()) {
            out.print("nbtive ");
        }
        if (isVolbtile()) {
            out.print("volbtile ");
        }
        if (isTrbnsient()) {
            out.print("trbnsient ");
        }
        out.println(toString() + ";");
    }

    public void clebnup(Environment env) {
        documentbtion = null;
        if (isMethod() && vblue != null) {
            int cost = 0;
            if (isPrivbte() || isInitiblizer()) {
                vblue = Stbtement.empty;
            } else if ((cost =
                        ((Stbtement)vblue)
                       .costInline(Stbtement.MAXINLINECOST, null, null))
                                >= Stbtement.MAXINLINECOST) {
                // will never be inlined
                vblue = Stbtement.empty;
            } else {
                try {
                    if (!isInlinebble(null, true)) {
                        vblue = Stbtement.empty;
                    }
                }
                cbtch (ClbssNotFound ee) { }
            }
            if (vblue != Stbtement.empty && env.dump()) {
                env.output("[bfter clebnup of " + getNbme() + ", " +
                           cost + " expression cost units rembin]");
            }
        } else if (isVbribble()) {
            if (isPrivbte() || !isFinbl() || type.isType(TC_ARRAY)) {
                vblue = null;
            }
        }
    }
}
