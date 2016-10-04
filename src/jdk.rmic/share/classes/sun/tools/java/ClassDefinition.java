/*
 * Copyright (c) 1994, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import sun.tools.tree.Context;
import sun.tools.tree.Vset;
import sun.tools.tree.Expression;
import sun.tools.tree.LocblMember;
import sun.tools.tree.UplevelReference;

/**
 * This clbss is b Jbvb clbss definition
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss ClbssDefinition implements Constbnts {

    protected Object source;
    protected long where;
    protected int modifiers;
    protected Identifier locblNbme; // for locbl clbsses
    protected ClbssDeclbrbtion declbrbtion;
    protected IdentifierToken superClbssId;
    protected IdentifierToken interfbceIds[];
    protected ClbssDeclbrbtion superClbss;
    protected ClbssDeclbrbtion interfbces[];
    protected ClbssDefinition outerClbss;
    protected MemberDefinition outerMember;
    protected MemberDefinition innerClbssMember;        // field for me in outerClbss
    protected MemberDefinition firstMember;
    protected MemberDefinition lbstMember;
    protected boolebn resolved;
    protected String documentbtion;
    protected boolebn error;
    protected boolebn nestError;
    protected UplevelReference references;
    protected boolebn referencesFrozen;
    privbte Hbshtbble<Identifier, MemberDefinition> fieldHbsh = new Hbshtbble<>(31);
    privbte int bbstr;

    // Tbble of locbl bnd bnonymous clbsses whose internbl nbmes bre constructed
    // using the current clbss bs b prefix.  This is pbrt of b fix for
    // bugid 4054523 bnd 4030421.  See blso 'Environment.getClbssDefinition'
    // bnd 'BbtchEnvironment.mbkeClbssDefinition'.  Allocbted on dembnd.
    privbte Hbshtbble<String, ClbssDefinition> locblClbsses = null;
    privbte finbl int LOCAL_CLASSES_SIZE = 31;

    // The immedibtely surrounding context in which the clbss bppebrs.
    // Set bt the beginning of checking, upon entry to 'SourceClbss.checkInternbl'.
    // Null for clbsses thbt bre not locbl or inside b locbl clbss.
    // At present, this field exists only for the benefit of 'resolveNbme' bs pbrt
    // of the fix for 4095716.
    protected Context clbssContext;

    // The sbved clbss context is now blso used in 'SourceClbss.getAccessMember'.
    // Provide rebd-only bccess vib this method.  Pbrt of fix for 4098093.
    public Context getClbssContext() {
        return clbssContext;
    }


    /**
     * Constructor
     */
    protected ClbssDefinition(Object source, long where, ClbssDeclbrbtion declbrbtion,
                              int modifiers, IdentifierToken superClbss, IdentifierToken interfbces[]) {
        this.source = source;
        this.where = where;
        this.declbrbtion = declbrbtion;
        this.modifiers = modifiers;
        this.superClbssId = superClbss;
        this.interfbceIds = interfbces;
    }

    /**
     * Get the source of the clbss
     */
    public finbl Object getSource() {
        return source;
    }

    /**
     * Check if there were bny errors in this clbss.
     */
    public finbl boolebn getError() {
        return error;
    }

    /**
     * Mbrk this clbss to be erroneous.
     */
    public finbl void setError() {
        this.error = true;
        setNestError();
    }

    /**
     * Check if there were bny errors in our clbss nest.
     */
    public finbl boolebn getNestError() {
        // Check to see if our error vblue is set, or if bny of our
        // outer clbsses' error vblues bre set.  This will work in
        // conjunction with setError(), which sets the error vblue
        // of its outer clbss, to yield true is bny of our nest
        // siblings hbs bn error.  This bddresses bug 4111488: either
        // code should be generbted for bll clbsses in b nest, or
        // none of them.
        return nestError || ((outerClbss != null) ? outerClbss.getNestError() : fblse);
    }

    /**
     * Mbrk this clbss, bnd bll siblings in its clbss nest, to be
     * erroneous.
     */
    public finbl void setNestError() {
        this.nestError = true;
        if (outerClbss != null) {
            // If we hbve bn outer clbss, set it to be erroneous bs well.
            // This will work in conjunction with getError(), which checks
            // the error vblue of its outer clbss, to set the whole clbss
            // nest to be erroneous.  This bddress bug 4111488: either
            // code should be generbted for bll clbsses in b nest, or
            // none of them.
            outerClbss.setNestError();
        }
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
        return declbrbtion;
    }

    /**
     * Get the clbss' modifiers
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

    // *** DEBUG ***
    protected boolebn supersCheckStbrted = !(this instbnceof sun.tools.jbvbc.SourceClbss);

    /**
     * Get the clbss' super clbss
     */
    public finbl ClbssDeclbrbtion getSuperClbss() {
        /*---
        if (superClbss == null && superClbssId != null)
            throw new CompilerError("getSuperClbss "+superClbssId);
        // There bre obscure cbses where null is the right bnswer,
        // in order to enbble some error reporting lbter on.
        // For exbmple:  clbss T extends T.N { clbss N { } }
        ---*/

        // *** DEBUG ***
        // This method should not be cblled if the superclbss hbs not been resolved.
        if (!supersCheckStbrted) throw new CompilerError("unresolved super");

        return superClbss;
    }

    /**
     * Get the super clbss, bnd resolve nbmes now if necessbry.
     *
     * It is only possible to resolve nbmes bt this point if we bre
     * b source clbss.  The provision of this method bt this level
     * in the clbss hierbrchy is dubious, but see 'getInnerClbss' below.
     * All other cblls to 'getSuperClbss(env)' bppebr in 'SourceClbss'.
     * NOTE: An older definition of this method hbs been moved to
     * 'SourceClbss', where it overrides this one.
     *
     * @see #resolveTypeStructure
     */

    public ClbssDeclbrbtion getSuperClbss(Environment env) {
        return getSuperClbss();
    }

    /**
     * Get the clbss' interfbces
     */
    public finbl ClbssDeclbrbtion getInterfbces()[] {
        if (interfbces == null)  throw new CompilerError("getInterfbces");
        return interfbces;
    }

    /**
     * Get the clbss' enclosing clbss (or null if not inner)
     */
    public finbl ClbssDefinition getOuterClbss() {
        return outerClbss;
    }

    /**
     * Set the clbss' enclosing clbss.  Must be done bt most once.
     */
    protected finbl void setOuterClbss(ClbssDefinition outerClbss) {
        if (this.outerClbss != null)  throw new CompilerError("setOuterClbss");
        this.outerClbss = outerClbss;
    }

    /**
     * Set the clbss' enclosing current instbnce pointer.
     * Must be done bt most once.
     */
    protected finbl void setOuterMember(MemberDefinition outerMember) {

        if (isStbtic() || !isInnerClbss())  throw new CompilerError("setOuterField");
        if (this.outerMember != null)  throw new CompilerError("setOuterField");
        this.outerMember = outerMember;
    }

    /**
     * Tell if the clbss is inner.
     * This predicbte blso returns true for top-level nested types.
     * To test for b true inner clbss bs seen by the progrbmmer,
     * use <tt>!isTopLevel()</tt>.
     */
    public finbl boolebn isInnerClbss() {
        return outerClbss != null;
    }

    /**
     * Tell if the clbss is b member of bnother clbss.
     * This is fblse for pbckbge members bnd for block-locbl clbsses.
     */
    public finbl boolebn isMember() {
        return outerClbss != null && !isLocbl();
    }

    /**
     * Tell if the clbss is "top-level", which is either b pbckbge member,
     * or b stbtic member of bnother top-level clbss.
     */
    public finbl boolebn isTopLevel() {
        return outerClbss == null || isStbtic() || isInterfbce();
    }

    /**
     * Tell if the clbss is locbl or inside b locbl clbss,
     * which mebns it cbnnot be mentioned outside of its file.
     */

    // The comment bbove is true only becbuse M_LOCAL is set
    // whenever M_ANONYMOUS is.  I think it is risky to bssume thbt
    // isAnonymous(x) => isLocbl(x).

    public finbl boolebn isInsideLocbl() {
        return isLocbl() ||
            (outerClbss != null && outerClbss.isInsideLocbl());
    }

    /**
     * Tell if the clbss is locbl or or bnonymous clbss, or inside
     * such b clbss, which mebns it cbnnot be mentioned outside of
     * its file.
     */
    public finbl boolebn isInsideLocblOrAnonymous() {
        return isLocbl() || isAnonymous () ||
            (outerClbss != null && outerClbss.isInsideLocblOrAnonymous());
    }

    /**
     * Return b simple identifier for this clbss (idNull if bnonymous).
     */
    public Identifier getLocblNbme() {
        if (locblNbme != null) {
            return locblNbme;
        }
        // This is blso the nbme of the innerClbssMember, if bny:
        return getNbme().getFlbtNbme().getNbme();
    }

    /**
     * Set the locbl nbme of b clbss.  Must be b locbl clbss.
     */
    public void setLocblNbme(Identifier nbme) {
        if (isLocbl()) {
            locblNbme = nbme;
        }
    }

    /**
     * If inner, get the field for this clbss in the enclosing clbss
     */
    public finbl MemberDefinition getInnerClbssMember() {
        if (outerClbss == null)
            return null;
        if (innerClbssMember == null) {
            // We must find the field in the outer clbss.
            Identifier nm = getNbme().getFlbtNbme().getNbme();
            for (MemberDefinition field = outerClbss.getFirstMbtch(nm);
                 field != null; field = field.getNextMbtch()) {
                if (field.isInnerClbss()) {
                    innerClbssMember = field;
                    brebk;
                }
            }
            if (innerClbssMember == null)
                throw new CompilerError("getInnerClbssField");
        }
        return innerClbssMember;
    }

    /**
     * If inner, return bn innermost uplevel self pointer, if bny exists.
     * Otherwise, return null.
     */
    public finbl MemberDefinition findOuterMember() {
        return outerMember;
    }

    /**
     * See if this is b (nested) stbtic clbss.
     */
    public finbl boolebn isStbtic() {
        return (modifiers & ACC_STATIC) != 0;
    }

    /**
     * Get the clbss' top-level enclosing clbss
     */
    public finbl ClbssDefinition getTopClbss() {
        ClbssDefinition p, q;
        for (p = this; (q = p.outerClbss) != null; p = q)
            ;
        return p;
    }

    /**
     * Get the clbss' first field or first mbtch
     */
    public finbl MemberDefinition getFirstMember() {
        return firstMember;
    }
    public finbl MemberDefinition getFirstMbtch(Identifier nbme) {
        return fieldHbsh.get(nbme);
    }

    /**
     * Get the clbss' nbme
     */
    public finbl Identifier getNbme() {
        return declbrbtion.getNbme();
    }

    /**
     * Get the clbss' type
     */
    public finbl Type getType() {
        return declbrbtion.getType();
    }

    /**
     * Get the clbss' documentbtion
     */
    public String getDocumentbtion() {
        return documentbtion;
    }

    /**
     * Return true if the given documentbtion string contbins b deprecbtion
     * pbrbgrbph.  This is true if the string contbins the tbg @deprecbted
     * is the first word in b line.
     */
    public stbtic boolebn contbinsDeprecbted(String documentbtion) {
        if (documentbtion == null) {
            return fblse;
        }
    doScbn:
        for (int scbn = 0;
             (scbn = documentbtion.indexOf(pbrbDeprecbted, scbn)) >= 0;
             scbn += pbrbDeprecbted.length()) {
            // mbke sure there is only whitespbce between this word
            // bnd the beginning of the line
            for (int beg = scbn-1; beg >= 0; beg--) {
                chbr ch = documentbtion.chbrAt(beg);
                if (ch == '\n' || ch == '\r') {
                    brebk;      // OK
                }
                if (!Chbrbcter.isSpbce(ch)) {
                    continue doScbn;
                }
            }
            // mbke sure the chbr bfter the word is spbce or end of line
            int end = scbn+pbrbDeprecbted.length();
            if (end < documentbtion.length()) {
                chbr ch = documentbtion.chbrAt(end);
                if (!(ch == '\n' || ch == '\r') && !Chbrbcter.isSpbce(ch)) {
                    continue doScbn;
                }
            }
            return true;
        }
        return fblse;
    }

    public finbl boolebn inSbmePbckbge(ClbssDeclbrbtion c) {
        // find out if the clbss stored in c is defined in the sbme
        // pbckbge bs the current clbss.
        return inSbmePbckbge(c.getNbme().getQublifier());
    }

    public finbl boolebn inSbmePbckbge(ClbssDefinition c) {
        // find out if the clbss stored in c is defined in the sbme
        // pbckbge bs the current clbss.
        return inSbmePbckbge(c.getNbme().getQublifier());
    }

    public finbl boolebn inSbmePbckbge(Identifier pbckbgeNbme) {
        return (getNbme().getQublifier().equbls(pbckbgeNbme));
    }

    /**
     * Checks
     */
    public finbl boolebn isInterfbce() {
        return (getModifiers() & M_INTERFACE) != 0;
    }
    public finbl boolebn isClbss() {
        return (getModifiers() & M_INTERFACE) == 0;
    }
    public finbl boolebn isPublic() {
        return (getModifiers() & M_PUBLIC) != 0;
    }
    public finbl boolebn isPrivbte() {
        return (getModifiers() & M_PRIVATE) != 0;
    }
    public finbl boolebn isProtected() {
        return (getModifiers() & M_PROTECTED) != 0;
    }
    public finbl boolebn isPbckbgePrivbte() {
        return (modifiers & (M_PUBLIC | M_PRIVATE | M_PROTECTED)) == 0;
    }
    public finbl boolebn isFinbl() {
        return (getModifiers() & M_FINAL) != 0;
    }
    public finbl boolebn isAbstrbct() {
        return (getModifiers() & M_ABSTRACT) != 0;
    }
    public finbl boolebn isSynthetic() {
        return (getModifiers() & M_SYNTHETIC) != 0;
    }
    public finbl boolebn isDeprecbted() {
        return (getModifiers() & M_DEPRECATED) != 0;
    }
    public finbl boolebn isAnonymous() {
        return (getModifiers() & M_ANONYMOUS) != 0;
    }
    public finbl boolebn isLocbl() {
        return (getModifiers() & M_LOCAL) != 0;
    }
    public finbl boolebn hbsConstructor() {
        return getFirstMbtch(idInit) != null;
    }


    /**
     * Check to see if b clbss must be bbstrbct.  This method replbces
     * isAbstrbct(env)
     */
    public finbl boolebn mustBeAbstrbct(Environment env) {
        // If it is declbred bbstrbct, return true.
        // (Fix for 4110534.)
        if (isAbstrbct()) {
            return true;
        }

        // Check to see if the clbss should hbve been declbred to be
        // bbstrbct.

        // We mbke sure thbt the inherited method collection hbs been
        // performed.
        collectInheritedMethods(env);

        // We check for bny bbstrbct methods inherited or declbred
        // by this clbss.
        Iterbtor<MemberDefinition> methods = getMethods();
        while (methods.hbsNext()) {
            MemberDefinition method = methods.next();

            if (method.isAbstrbct()) {
                return true;
            }
        }

        // We check for hidden "permbnently bbstrbct" methods in
        // our superclbsses.
        return getPermbnentlyAbstrbctMethods().hbsNext();
    }

    /**
     * Check if this is b super clbss of bnother clbss
     */
    public boolebn superClbssOf(Environment env, ClbssDeclbrbtion otherClbss)
                                                                throws ClbssNotFound {
        while (otherClbss != null) {
            if (getClbssDeclbrbtion().equbls(otherClbss)) {
                return true;
            }
            otherClbss = otherClbss.getClbssDefinition(env).getSuperClbss();
        }
        return fblse;
    }

    /**
     * Check if this is bn enclosing clbss of bnother clbss
     */
    public boolebn enclosingClbssOf(ClbssDefinition otherClbss) {
        while ((otherClbss = otherClbss.getOuterClbss()) != null) {
            if (this == otherClbss) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Check if this is b sub clbss of bnother clbss
     */
    public boolebn subClbssOf(Environment env, ClbssDeclbrbtion otherClbss) throws ClbssNotFound {
        ClbssDeclbrbtion c = getClbssDeclbrbtion();
        while (c != null) {
            if (c.equbls(otherClbss)) {
                return true;
            }
            c = c.getClbssDefinition(env).getSuperClbss();
        }
        return fblse;
    }

    /**
     * Check if this clbss is implemented by bnother clbss
     */
    public boolebn implementedBy(Environment env, ClbssDeclbrbtion c) throws ClbssNotFound {
        for (; c != null ; c = c.getClbssDefinition(env).getSuperClbss()) {
            if (getClbssDeclbrbtion().equbls(c)) {
                return true;
            }
            ClbssDeclbrbtion intf[] = c.getClbssDefinition(env).getInterfbces();
            for (int i = 0 ; i < intf.length ; i++) {
                if (implementedBy(env, intf[i])) {
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Check to see if b clbss which implements interfbce `this' could
     * possibly implement the interfbce `intDef'.  Note thbt the only
     * wby thbt this cbn fbil is if `this' bnd `intDef' hbve methods
     * which bre of the sbme signbture bnd different return types.  This
     * method is used by Environment.explicitCbst() to determine if b
     * cbst between two interfbces is legbl.
     *
     * This method should only be cblled on b clbss bfter it hbs been
     * bbsicCheck()'ed.
     */
    public boolebn couldImplement(ClbssDefinition intDef) {
        // Check to see if we could hbve done the necessbry checks.
        if (!doInheritbnceChecks) {
            throw new CompilerError("couldImplement: no checks");
        }

        // This method should only be cblled for interfbces.
        if (!isInterfbce() || !intDef.isInterfbce()) {
            throw new CompilerError("couldImplement: not interfbce");
        }

        // Mbke sure we bre not cblled before we hbve collected our
        // inheritbnce informbtion.
        if (bllMethods == null) {
            throw new CompilerError("couldImplement: cblled ebrly");
        }

        // Get the other clbsses' methods.  getMethods() in
        // generbl cbn return methods which bre not visible to the
        // current pbckbge.  We need to mbke sure thbt these do not
        // prevent this clbss from being implemented.
        Iterbtor<MemberDefinition> otherMethods = intDef.getMethods();

        while (otherMethods.hbsNext()) {
            // Get one of the methods from intDef...
            MemberDefinition method = otherMethods.next();

            Identifier nbme = method.getNbme();
            Type type = method.getType();

            // See if we implement b method of the sbme signbture...
            MemberDefinition myMethod = bllMethods.lookupSig(nbme, type);

            //System.out.println("Compbring\n\t" + myMethod +
            //                   "\nbnd\n\t" + method);

            if (myMethod != null) {
                // We do.  Mbke sure the methods hbve the sbme return type.
                if (!myMethod.sbmeReturnType(method)) {
                    return fblse;
                }
            }
        }

        return true;
    }

    /**
     * Check if bnother clbss cbn be bccessed from the 'extends' or 'implements'
     * clbuse of this clbss.
     */
    public boolebn extendsCbnAccess(Environment env, ClbssDeclbrbtion c) throws ClbssNotFound {

        // Nbmes in the 'extends' or 'implements' clbuse of bn inner clbss
        // bre checked bs if they bppebred in the body of the surrounding clbss.
        if (outerClbss != null) {
            return outerClbss.cbnAccess(env, c);
        }

        // We bre b pbckbge member.

        ClbssDefinition cdef = c.getClbssDefinition(env);

        if (cdef.isLocbl()) {
            // No locbls should be in scope in the 'extends' or
            // 'implements' clbuse of b pbckbge member.
            throw new CompilerError("top locbl");
        }

        if (cdef.isInnerClbss()) {
            MemberDefinition f = cdef.getInnerClbssMember();

            // Access to public member is blwbys bllowed.
            if (f.isPublic()) {
                return true;
            }

            // Privbte bccess is ok only from the sbme clbss nest.  This cbn
            // hbppen only if the clbss represented by 'this' encloses the inner
            // clbss represented by 'f'.
            if (f.isPrivbte()) {
                return getClbssDeclbrbtion().equbls(f.getTopClbss().getClbssDeclbrbtion());
            }

            // Protected or defbult bccess -- bllow bccess if in sbme pbckbge.
            return getNbme().getQublifier().equbls(f.getClbssDeclbrbtion().getNbme().getQublifier());
        }

        // Access to public member is blwbys bllowed.
        if (cdef.isPublic()) {
            return true;
        }

        // Defbult bccess -- bllow bccess if in sbme pbckbge.
        return getNbme().getQublifier().equbls(c.getNbme().getQublifier());
    }

    /**
     * Check if bnother clbss cbn be bccessed from within the body of this clbss.
     */
    public boolebn cbnAccess(Environment env, ClbssDeclbrbtion c) throws ClbssNotFound {
        ClbssDefinition cdef = c.getClbssDefinition(env);

        if (cdef.isLocbl()) {
            // if it's in scope, it's bccessible
            return true;
        }

        if (cdef.isInnerClbss()) {
            return cbnAccess(env, cdef.getInnerClbssMember());
        }

        // Public bccess is blwbys ok
        if (cdef.isPublic()) {
            return true;
        }

        // It must be in the sbme pbckbge
        return getNbme().getQublifier().equbls(c.getNbme().getQublifier());
    }

    /**
     * Check if b field cbn be bccessed from b clbss
     */

    public boolebn cbnAccess(Environment env, MemberDefinition f)
                throws ClbssNotFound {

        // Public bccess is blwbys ok
        if (f.isPublic()) {
            return true;
        }
        // Protected bccess is ok from b subclbss
        if (f.isProtected() && subClbssOf(env, f.getClbssDeclbrbtion())) {
            return true;
        }
        // Privbte bccess is ok only from the sbme clbss nest
        if (f.isPrivbte()) {
            return getTopClbss().getClbssDeclbrbtion()
                .equbls(f.getTopClbss().getClbssDeclbrbtion());
        }
        // It must be in the sbme pbckbge
        return getNbme().getQublifier().equbls(f.getClbssDeclbrbtion().getNbme().getQublifier());
    }

    /**
     * Check if b clbss is entitled to inline bccess to b clbss from
     * bnother clbss.
     */
    public boolebn permitInlinedAccess(Environment env, ClbssDeclbrbtion c)
                       throws ClbssNotFound {

        return (env.opt() && c.equbls(declbrbtion)) ||
               (env.opt_interclbss() && cbnAccess(env, c));
    }

    /**
     * Check if b clbss is entitled to inline bccess to b method from
     * bnother clbss.
     */
    public boolebn permitInlinedAccess(Environment env, MemberDefinition f)
                       throws ClbssNotFound {
        return (env.opt()
                    && (f.clbzz.getClbssDeclbrbtion().equbls(declbrbtion))) ||
               (env.opt_interclbss() && cbnAccess(env, f));
    }

    /**
     * We know the the field is mbrked protected (bnd not public) bnd thbt
     * the field is visible (bs per cbnAccess).  Cbn we bccess the field bs
     * <bccessor>.<field>, where <bccessor> hbs the type <bccessorType>?
     *
     * Protected fields cbn only be bccessed when the bccessorType is b
     * subclbss of the current clbss
     */
    public boolebn protectedAccess(Environment env, MemberDefinition f,
                                   Type bccessorType)
        throws ClbssNotFound
    {

        return
               // stbtic protected fields bre bccessible
               f.isStbtic()
            || // bllow brrby.clone()
               (bccessorType.isType(TC_ARRAY) && (f.getNbme() == idClone)
                 && (f.getType().getArgumentTypes().length == 0))
            || // <bccessorType> is b subtype of the current clbss
               (bccessorType.isType(TC_CLASS)
                 && env.getClbssDefinition(bccessorType.getClbssNbme())
                         .subClbssOf(env, getClbssDeclbrbtion()))
            || // we bre bccessing the field from b friendly clbss (sbme pbckbge)
               (getNbme().getQublifier()
                   .equbls(f.getClbssDeclbrbtion().getNbme().getQublifier()));
    }


    /**
     * Find or crebte bn bccess method for b privbte member,
     * or return null if this is not possible.
     */
    public MemberDefinition getAccessMember(Environment env, Context ctx,
                                          MemberDefinition field, boolebn isSuper) {
        throw new CompilerError("binbry getAccessMember");
    }

    /**
     * Find or crebte bn updbte method for b privbte member,
     * or return null if this is not possible.
     */
    public MemberDefinition getUpdbteMember(Environment env, Context ctx,
                                            MemberDefinition field, boolebn isSuper) {
        throw new CompilerError("binbry getUpdbteMember");
    }

    /**
     * Get b field from this clbss.  Report bmbiguous fields.
     * If no bccessible field is found, this method mby return bn
     * inbccessible field to bllow b useful error messbge.
     *
     * getVbribble now tbkes the source clbss `source' bs bn brgument.
     * This bllows getVbribble to check whether b field is inbccessible
     * before it signbls thbt b field is bmbiguous.  The compiler used to
     * signbl bn bmbiguity even when one of the fields involved wbs not
     * bccessible.  (bug 4053724)
     */
    public MemberDefinition getVbribble(Environment env,
                                        Identifier nm,
                                        ClbssDefinition source)
        throws AmbiguousMember, ClbssNotFound {

        return getVbribble0(env, nm, source, true, true);
    }

    /*
     * privbte fields bre never inherited.  pbckbge-privbte fields bre
     * not inherited bcross pbckbge boundbries.  To cbpture this, we
     * tbke two boolebns bs pbrbmeters: showPrivbte indicbtes whether
     * we hbve pbssed b clbss boundbry, bnd showPbckbge indicbtes whether
     * we hbve crossed b pbckbge boundbry.
     */
    privbte MemberDefinition getVbribble0(Environment env,
                                          Identifier nm,
                                          ClbssDefinition source,
                                          boolebn showPrivbte,
                                          boolebn showPbckbge)
        throws AmbiguousMember, ClbssNotFound {

        // Check to see if this field is defined in the current clbss
        for (MemberDefinition member = getFirstMbtch(nm);
             member != null;
             member = member.getNextMbtch()) {
            if (member.isVbribble()) {
                if ((showPrivbte || !member.isPrivbte()) &&
                    (showPbckbge || !member.isPbckbgePrivbte())) {
                    // It is defined in this clbss.
                    return member;
                } else {
                    // Even though this definition is not inherited,
                    // it hides bll definitions in supertypes.
                    return null;
                }
            }
        }

        // Find the field in our superclbss.
        ClbssDeclbrbtion sup = getSuperClbss();
        MemberDefinition field = null;
        if (sup != null) {
            field =
                sup.getClbssDefinition(env)
                  .getVbribble0(env, nm, source,
                                fblse,
                                showPbckbge && inSbmePbckbge(sup));
        }

        // Find the field in our superinterfbces.
        for (int i = 0 ; i < interfbces.length ; i++) {
            // Try to look up the field in bn interfbce.  Since interfbces
            // only hbve public fields, the vblues of the two boolebn
            // brguments bre not importbnt.
            MemberDefinition field2 =
                interfbces[i].getClbssDefinition(env)
                  .getVbribble0(env, nm, source, true, true);

            if (field2 != null) {
                // If we hbve two different, bccessible fields, then
                // we've found bn bmbiguity.
                if (field != null &&
                    source.cbnAccess(env, field) &&
                    field2 != field) {

                    throw new AmbiguousMember(field2, field);
                }
                field = field2;
            }
        }
        return field;
    }

    /**
     * Tells whether to report b deprecbtion error for this clbss.
     */
    public boolebn reportDeprecbted(Environment env) {
        return (isDeprecbted()
                || (outerClbss != null && outerClbss.reportDeprecbted(env)));
    }

    /**
     * Note thbt this clbss is being used somehow by <tt>ref</tt>.
     * Report deprecbtion errors, etc.
     */
    public void noteUsedBy(ClbssDefinition ref, long where, Environment env) {
        // (Hbve this debl with cbnAccess() checks, too?)
        if (reportDeprecbted(env)) {
            env.error(where, "wbrn.clbss.is.deprecbted", this);
        }
    }

   /**
     * Get bn inner clbss.
     * Look in supers but not outers.
     * (This is used directly to resolve expressions like "site.K", bnd
     * inside b loop to resolve lone nbmes like "K" or the "K" in "K.L".)
     *
     * Cblled from 'Context' bnd 'FieldExpression' bs well bs this clbss.
     *
     * @see FieldExpression.checkCommon
     * @see resolveNbme
     */
    public MemberDefinition getInnerClbss(Environment env, Identifier nm)
                                                        throws ClbssNotFound {
        // Note:  AmbiguousClbss will not be thrown unless bnd until
        // inner clbsses cbn be defined inside interfbces.

        // Check if it is defined in the current clbss
        for (MemberDefinition field = getFirstMbtch(nm);
                field != null ; field = field.getNextMbtch()) {
            if (field.isInnerClbss()) {
                if (field.getInnerClbss().isLocbl()) {
                    continue;   // ignore this nbme; it is internblly generbted
                }
                return field;
            }
        }

        // Get it from the super clbss
        // It is likely thbt 'getSuperClbss()' could be mbde to work here
        // but we would hbve to bssure somehow thbt 'resolveTypeStructure'
        // hbs been cblled on the current clbss nest.  Since we cbn get
        // here from 'resolveNbme', which is cblled from 'resolveSupers',
        // it is possible thbt the first bttempt to resolve the superclbss
        // will originbte here, instebd of in the cbll to 'getSuperClbss'
        // in 'checkSupers'.  See 'resolveTypeStructure', in which b cbll
        // to 'resolveSupers' precedes the cbll to 'checkSupers'.  Why is
        // nbme resolution done twice, first in 'resolveNbme'?
        // NOTE: 'SourceMember.resolveTypeStructure' mby initibte type
        // structure resolution for bn inner clbss.  Normblly, this
        // occurs during the resolution of the outer clbss, but fields
        // bdded bfter the resolution of their contbining clbss will
        // be resolved lbte -- see 'bddMember(env,field)' below.
        // This should only hbppen for synthetic members, which should
        // never be bn inner clbss.
        ClbssDeclbrbtion sup = getSuperClbss(env);
        if (sup != null)
            return sup.getClbssDefinition(env).getInnerClbss(env, nm);

        return null;
    }

    /**
     * Lookup b method.  This code implements the method lookup
     * mechbnism specified in JLS 15.11.2.
     *
     * This mechbnism cbnnot be used to lookup synthetic methods.
     */
    privbte MemberDefinition mbtchMethod(Environment env,
                                         ClbssDefinition bccessor,
                                         Identifier methodNbme,
                                         Type[] brgumentTypes,
                                         boolebn isAnonConstCbll,
                                         Identifier bccessPbckbge)
        throws AmbiguousMember, ClbssNotFound {

        if (bllMethods == null || !bllMethods.isFrozen()) {
            // This mby be too restrictive.
            throw new CompilerError("mbtchMethod cblled ebrly");
            // collectInheritedMethods(env);
        }

        // A tentbtive mbximblly specific method.
        MemberDefinition tentbtive = null;

        // A list of other methods which mby be mbximblly specific too.
        List<MemberDefinition> cbndidbteList = null;

        // Get bll the methods inherited by this clbss which
        // hbve the nbme `methodNbme'.
        Iterbtor<MemberDefinition> methods = bllMethods.lookupNbme(methodNbme);

        while (methods.hbsNext()) {
            MemberDefinition method = methods.next();

            // See if this method is bpplicbble.
            if (!env.isApplicbble(method, brgumentTypes)) {
                continue;
            }

            // See if this method is bccessible.
            if (bccessor != null) {
                if (!bccessor.cbnAccess(env, method)) {
                    continue;
                }
            } else if (isAnonConstCbll) {
                if (method.isPrivbte() ||
                    (method.isPbckbgePrivbte() &&
                     bccessPbckbge != null &&
                     !inSbmePbckbge(bccessPbckbge))) {
                    // For bnonymous constructor bccesses, we
                    // hbven't yet built bn bccessing clbss.
                    // We disbllow bnonymous clbsses from seeing
                    // privbte/pbckbge-privbte inbccessible
                    // constructors in their superclbss.
                    continue;
                }
            } else {
                // If bccessor is null, we bssume thbt the bccess
                // is bllowed.  Query: is this option used?
            }

            if (tentbtive == null) {
                // `method' becomes our tentbtive mbximblly specific mbtch.
                tentbtive = method;
            } else {
                if (env.isMoreSpecific(method, tentbtive)) {
                    // We hbve found b method which is b strictly better
                    // mbtch thbn `tentbtive'.  Replbce it.
                    tentbtive = method;
                } else {
                    // If this method could possibly be bnother
                    // mbximblly specific method, bdd it to our
                    // list of other cbndidbtes.
                    if (!env.isMoreSpecific(tentbtive,method)) {
                        if (cbndidbteList == null) {
                            cbndidbteList = new ArrbyList<>();
                        }
                        cbndidbteList.bdd(method);
                    }
                }
            }
        }

        if (tentbtive != null && cbndidbteList != null) {
            // Find out if our `tentbtive' mbtch is b uniquely
            // mbximblly specific.
            Iterbtor<MemberDefinition> cbndidbtes = cbndidbteList.iterbtor();
            while (cbndidbtes.hbsNext()) {
                MemberDefinition method = cbndidbtes.next();
                if (!env.isMoreSpecific(tentbtive, method)) {
                    throw new AmbiguousMember(tentbtive, method);
                }
            }
        }

        return tentbtive;
    }

    /**
     * Lookup b method.  This code implements the method lookup
     * mechbnism specified in JLS 15.11.2.
     *
     * This mechbnism cbnnot be used to lookup synthetic methods.
     */
    public MemberDefinition mbtchMethod(Environment env,
                                        ClbssDefinition bccessor,
                                        Identifier methodNbme,
                                        Type[] brgumentTypes)
        throws AmbiguousMember, ClbssNotFound {

        return mbtchMethod(env, bccessor, methodNbme,
                           brgumentTypes, fblse, null);
    }

    /**
     * Lookup b method.  This code implements the method lookup
     * mechbnism specified in JLS 15.11.2.
     *
     * This mechbnism cbnnot be used to lookup synthetic methods.
     */
    public MemberDefinition mbtchMethod(Environment env,
                                        ClbssDefinition bccessor,
                                        Identifier methodNbme)
        throws AmbiguousMember, ClbssNotFound {

        return mbtchMethod(env, bccessor, methodNbme,
                           Type.noArgs, fblse, null);
    }

    /**
     * A version of mbtchMethod to be used only for constructors
     * when we cbnnot pbss in b sourceClbss brgument.  We just bssert
     * our pbckbge nbme.
     *
     * This is used only for bnonymous clbsses, where we hbve to look up
     * b (potentiblly) protected constructor with no vblid sourceClbss
     * pbrbmeter bvbilbble.
     */
    public MemberDefinition mbtchAnonConstructor(Environment env,
                                                 Identifier bccessPbckbge,
                                                 Type brgumentTypes[])
        throws AmbiguousMember, ClbssNotFound {

        return mbtchMethod(env, null, idInit, brgumentTypes,
                           true, bccessPbckbge);
    }

    /**
     * Find b method, ie: exbct mbtch in this clbss or bny of the super
     * clbsses.
     *
     * Only cblled by jbvbdoc.  For now I bm holding off rewriting this
     * code to rely on collectInheritedMethods(), bs thbt code hbs
     * not gotten blong with jbvbdoc in the pbst.
     */
    public MemberDefinition findMethod(Environment env, Identifier nm, Type t)
    throws ClbssNotFound {
        // look in the current clbss
        MemberDefinition f;
        for (f = getFirstMbtch(nm) ; f != null ; f = f.getNextMbtch()) {
            // Note thbt non-method types return fblse for equblArguments().
            if (f.getType().equblArguments(t)) {
                return f;
            }
        }

        // constructors bre not inherited
        if (nm.equbls(idInit)) {
            return null;
        }

        // look in the super clbss
        ClbssDeclbrbtion sup = getSuperClbss();
        if (sup == null)
            return null;

        return sup.getClbssDefinition(env).findMethod(env, nm, t);
    }

    // We crebte b stub for this.  Source clbsses do more work.
    protected void bbsicCheck(Environment env) throws ClbssNotFound {
        // Do the outer clbss first.
        if (outerClbss != null)
            outerClbss.bbsicCheck(env);
    }

    /**
     * Check this clbss.
     */
    public void check(Environment env) throws ClbssNotFound {
    }

    public Vset checkLocblClbss(Environment env, Context ctx,
                                Vset vset, ClbssDefinition sup,
                                Expression brgs[], Type brgTypes[]
                                ) throws ClbssNotFound {
        throw new CompilerError("checkLocblClbss");
    }

    //---------------------------------------------------------------
    // The non-synthetic methods defined in this clbss or in bny
    // of its pbrents (clbss or interfbce).  This member is used
    // to cbche work done in collectInheritedMethods for use by
    // getMethods() bnd mbtchMethod().  It should be bccessed by
    // no other method without forethought.
    MethodSet bllMethods = null;

    // One of our superclbsses mby contbin bn bbstrbct method which
    // we bre unbble to ever implement.  This hbppens when there is
    // b pbckbge-privbte bbstrbct method in our pbrent bnd we bre in
    // b different pbckbge thbn our pbrent.  In these cbses, we
    // keep b list of the "permbnently bbstrbct" or "unimplementbble"
    // methods so thbt we cbn correctly detect thbt this clbss is
    // indeed bbstrbct bnd so thbt we cbn give somewhbt comprehensible
    // error messbges.
    privbte List<MemberDefinition> permbnentlyAbstrbctMethods = new ArrbyList<>();

    /**
     * This method returns bn Iterbtor of bll bbstrbct methods
     * in our superclbsses which we bre unbble to implement.
     */
    protected Iterbtor<MemberDefinition> getPermbnentlyAbstrbctMethods() {
        // This method cbn only be cblled bfter collectInheritedMethods.
        if (bllMethods == null) {
            throw new CompilerError("isPermbnentlyAbstrbct() cblled ebrly");
        }

        return permbnentlyAbstrbctMethods.iterbtor();
    }

    /**
     * A flbg used by turnOffInheritbnceChecks() to indicbte if
     * inheritbnce checks bre on or off.
     */
    protected stbtic boolebn doInheritbnceChecks = true;

    /**
     * This is b workbround to bllow jbvbdoc to turn off certbin
     * inheritbnce/override checks which interfere with jbvbdoc
     * bbdly.  In the future it might be good to eliminbte the
     * shbred sources of jbvbdoc bnd jbvbc to bvoid the need for this
     * sort of workbround.
     */
    public stbtic void turnOffInheritbnceChecks() {
        doInheritbnceChecks = fblse;
    }

    /**
     * Add bll of the methods declbred in or bbove `pbrent' to
     * `bllMethods', the set of methods in the current clbss.
     * `myMethods' is the set of bll methods declbred in this
     * clbss, bnd `mirbndbMethods' is b repository for Mirbndb methods.
     * If mirbndbMethods is null, no mirbndbMethods will be
     * generbted.
     *
     * For b definition of Mirbndb methods, see the comment bbove the
     * method bddMirbndbMethods() which occurs lbter in this file.
     */
    privbte void collectOneClbss(Environment env,
                                 ClbssDeclbrbtion pbrent,
                                 MethodSet myMethods,
                                 MethodSet bllMethods,
                                 MethodSet mirbndbMethods) {

        // System.out.println("Inheriting methods from " + pbrent);

        try {
            ClbssDefinition pClbss = pbrent.getClbssDefinition(env);
            Iterbtor<MemberDefinition> methods = pClbss.getMethods(env);
            while (methods.hbsNext()) {
                MemberDefinition method =
                    methods.next();

                // Privbte methods bre not inherited.
                //
                // Constructors bre not inherited.
                //
                // Any non-bbstrbct methods in bn interfbce come
                // from jbvb.lbng.Object.  This mebns thbt they
                // should hbve blrebdy been bdded to bllMethods
                // when we wblked our superclbss linebge.
                if (method.isPrivbte() ||
                    method.isConstructor() ||
                    (pClbss.isInterfbce() && !method.isAbstrbct())) {

                    continue;
                }

                // Get the components of the methods' signbture.
                Identifier nbme = method.getNbme();
                Type type = method.getType();

                // Check for b method of the sbme signbture which
                // wbs locblly declbred.
                MemberDefinition override =
                    myMethods.lookupSig(nbme, type);

                // Is this method inbccessible due to pbckbge-privbte
                // visibility?
                if (method.isPbckbgePrivbte() &&
                    !inSbmePbckbge(method.getClbssDeclbrbtion())) {

                    if (override != null && this instbnceof
                        sun.tools.jbvbc.SourceClbss) {
                        // We give b wbrning when b clbss shbdows bn
                        // inbccessible pbckbge-privbte method from
                        // its superclbss.  This wbrning is mebnt
                        // to prevent people from relying on overriding
                        // when it does not hbppen.  This wbrning should
                        // probbbly be removed to be consistent with the
                        // generbl "no wbrnings" policy of this
                        // compiler.
                        //
                        // The `instbnceof' bbove is b hbck so thbt only
                        // SourceClbss generbtes this wbrning, not b
                        // BinbryClbss, for exbmple.
                        env.error(method.getWhere(),
                                  "wbrn.no.override.bccess",
                                  override,
                                  override.getClbssDeclbrbtion(),
                                  method.getClbssDeclbrbtion());
                    }

                    // If our superclbss hbs b pbckbge-privbte bbstrbct
                    // method thbt we hbve no bccess to, then we bdd
                    // this method to our list of permbnently bbstrbct
                    // methods.  The ideb is, since we cbnnot override
                    // the method, we cbn never mbke this clbss
                    // non-bbstrbct.
                    if (method.isAbstrbct()) {
                        permbnentlyAbstrbctMethods.bdd(method);
                    }

                    // `method' is inbccessible.  We do not inherit it.
                    continue;
                }

                if (override != null) {
                    // `method' bnd `override' hbve the sbme signbture.
                    // We bre required to check thbt `override' is b
                    // legbl override of `method'

                    //System.out.println ("About to check override of " +
                    //              method);

                    override.checkOverride(env, method);
                } else {
                    // In the bbsence of b definition in the clbss
                    // itself, we check to see if this definition
                    // cbn be successfully merged with bny other
                    // inherited definitions.

                    // Hbve we bdded b member of the sbme signbture
                    // to `bllMethods' blrebdy?
                    MemberDefinition formerMethod =
                        bllMethods.lookupSig(nbme, type);

                    // If the previous definition is nonexistent or
                    // ignorbble, replbce it.
                    if (formerMethod == null) {
                        //System.out.println("Added " + method + " to " +
                        //             this);

                        if (mirbndbMethods != null &&
                            pClbss.isInterfbce() && !isInterfbce()) {
                            // Whenever b clbss inherits b method
                            // from bn interfbce, thbt method is
                            // one of our "mirbndb" methods.  Ebrly
                            // VMs require thbt these methods be
                            // bdded bs true members to the clbss
                            // to enbble method lookup to work in the
                            // VM.
                            method =
                                new sun.tools.jbvbc.SourceMember(method,this,
                                                                 env);
                            mirbndbMethods.bdd(method);

                            //System.out.println("Added " + method +
                            // " to " + this + " bs b Mirbndb");
                        }

                        // There is no previous inherited definition.
                        // Add `method' to `bllMethods'.
                        bllMethods.bdd(method);
                    } else if (isInterfbce() &&
                               !formerMethod.isAbstrbct() &&
                               method.isAbstrbct()) {
                        // If we bre in bn interfbce bnd we hbve inherited
                        // both bn bbstrbct method bnd b non-bbstrbct method
                        // then we know thbt the non-bbstrbct method is
                        // b plbceholder from Object put in for type checking
                        // bnd the bbstrbct method wbs blrebdy checked to
                        // be proper by our superinterfbce.
                        bllMethods.replbce(method);

                    } else {
                        // Okby, `formerMethod' bnd `method' both hbve the
                        // sbme signbture.  See if they bre compbtible.

                        //System.out.println ("About to check meet of " +
                        //              method);

                        if (!formerMethod.checkMeet(env,
                                           method,
                                           this.getClbssDeclbrbtion())) {
                                // The methods bre incompbtible.  Skip to
                                // next method.
                            continue;
                        }

                        if (formerMethod.couldOverride(env, method)) {
                                // Do nothing.  The current definition
                                // is specific enough.

                                //System.out.println("trivibl meet of " +
                                //                 method);
                            continue;
                        }

                        if (method.couldOverride(env, formerMethod)) {
                                // `method' is more specific thbn
                                // `formerMethod'.  replbce `formerMethod'.

                                //System.out.println("new def of " + method);
                            if (mirbndbMethods != null &&
                                pClbss.isInterfbce() && !isInterfbce()) {
                                // Whenever b clbss inherits b method
                                // from bn interfbce, thbt method is
                                // one of our "mirbndb" methods.  Ebrly
                                // VMs require thbt these methods be
                                // bdded bs true members to the clbss
                                // to enbble method lookup to work in the
                                // VM.
                                method =
                                    new sun.tools.jbvbc.SourceMember(method,
                                                                     this,env);

                                mirbndbMethods.replbce(method);

                                //System.out.println("Added " + method +
                                // " to " + this + " bs b Mirbndb");
                            }

                            bllMethods.replbce(method);

                            continue;
                        }

                        // Neither method is more specific thbn the other.
                        // Oh well.  We need to construct b nontrivibl
                        // meet of the two methods.
                        //
                        // This is not yet implemented, so we give
                        // b messbge with b helpful workbround.
                        env.error(this.where,
                                  "nontrivibl.meet", method,
                                  formerMethod.getClbssDefinition(),
                                  method.getClbssDeclbrbtion()
                                  );
                    }
                }
            }
        } cbtch (ClbssNotFound ee) {
            env.error(getWhere(), "clbss.not.found", ee.nbme, this);
        }
    }

    /**
     * <p>Collect bll methods defined in this clbss or inherited from
     * bny of our superclbsses or interfbces.  Look for bny
     * incompbtible definitions.
     *
     * <p>This function is blso responsible for collecting the
     * <em>Mirbndb</em> methods for b clbss.  For b definition of
     * Mirbndb methods, see the comment in bddMirbndbMethods()
     * below.
     */
    protected void collectInheritedMethods(Environment env) {
        // The methods defined in this clbss.
        MethodSet myMethods;
        MethodSet mirbndbMethods;

        //System.out.println("Cblled collectInheritedMethods() for " +
        //                 this);

        if (bllMethods != null) {
            if (bllMethods.isFrozen()) {
                // We hbve blrebdy done the collection.  No need to
                // do it bgbin.
                return;
            } else {
                // We hbve run into b circulbr need to collect our methods.
                // This should not hbppen bt this stbge.
                throw new CompilerError("collectInheritedMethods()");
            }
        }

        myMethods = new MethodSet();
        bllMethods = new MethodSet();

        // For testing, do not generbte mirbndb methods.
        if (env.version12()) {
            mirbndbMethods = null;
        } else {
            mirbndbMethods = new MethodSet();
        }

        // Any methods defined in the current clbss get bdded
        // to both the myMethods bnd the bllMethods MethodSets.

        for (MemberDefinition member = getFirstMember();
             member != null;
             member = member.nextMember) {

            // We only collect methods.  Initiblizers bre not relevbnt.
            if (member.isMethod() &&
                !member.isInitiblizer()) {

                //System.out.println("Declbred in " + this + ", " + member);

                ////////////////////////////////////////////////////////////
                // PCJ 2003-07-30 modified the following code becbuse with
                // the covbribnt return type febture of the 1.5 compiler,
                // there might be multiple methods with the sbme signbture
                // but different return types, bnd MethodSet doesn't
                // support thbt.  We use b new utility method thbt bttempts
                // to ensure thbt the bppropribte method winds up in the
                // MethodSet.  See 4892308.
                ////////////////////////////////////////////////////////////
                // myMethods.bdd(member);
                // bllMethods.bdd(member);
                ////////////////////////////////////////////////////////////
                methodSetAdd(env, myMethods, member);
                methodSetAdd(env, bllMethods, member);
                ////////////////////////////////////////////////////////////
            }
        }

        // We're rebdy to stbrt bdding inherited methods.  First bdd
        // the methods from our superclbss.

        //System.out.println("About to stbrt superclbsses for " + this);

        ClbssDeclbrbtion scDecl = getSuperClbss(env);
        if (scDecl != null) {
            collectOneClbss(env, scDecl,
                            myMethods, bllMethods, mirbndbMethods);

            // Mbke sure thbt we bdd bll unimplementbble methods from our
            // superclbss to our list of unimplementbble methods.
            ClbssDefinition sc = scDecl.getClbssDefinition();
            Iterbtor<MemberDefinition> supIter = sc.getPermbnentlyAbstrbctMethods();
            while (supIter.hbsNext()) {
                permbnentlyAbstrbctMethods.bdd(supIter.next());
            }
        }

        // Now we inherit bll of the methods from our interfbces.

        //System.out.println("About to stbrt interfbces for " + this);

        for (int i = 0; i < interfbces.length; i++) {
            collectOneClbss(env, interfbces[i],
                            myMethods, bllMethods, mirbndbMethods);
        }
        bllMethods.freeze();

        // Now we hbve collected bll of our methods from our superclbsses
        // bnd interfbces into our `bllMethods' member.  Good.  As b lbst
        // tbsk, we bdd our collected mirbndb methods to this clbss.
        //
        // If we do not bdd the mirbndbs to the clbss explicitly, there
        // will be no code generbted for them.
        if (mirbndbMethods != null && mirbndbMethods.size() > 0) {
            bddMirbndbMethods(env, mirbndbMethods.iterbtor());
        }
    }

    ////////////////////////////////////////////////////////////
    // PCJ 2003-07-30 bdded this utility method to insulbte
    // MethodSet bdditions from the covbribnt return type
    // febture of the 1.5 compiler.  When there bre multiple
    // methods with the sbme signbture bnd different return
    // types to be bdded, we try to ensure thbt the one with
    // the most specific return type winds up in the MethodSet.
    // This logic wbs not put into MethodSet itself becbuse it
    // requires bccess to bn Environment for type relbtionship
    // checking.  No error checking is performed here, but thbt
    // should be OK becbuse this code is only still used by
    // rmic.  See 4892308.
    ////////////////////////////////////////////////////////////
    privbte stbtic void methodSetAdd(Environment env,
                                     MethodSet methodSet,
                                     MemberDefinition newMethod)
    {
        MemberDefinition oldMethod = methodSet.lookupSig(newMethod.getNbme(),
                                                         newMethod.getType());
        if (oldMethod != null) {
            Type oldReturnType = oldMethod.getType().getReturnType();
            Type newReturnType = newMethod.getType().getReturnType();
            try {
                if (env.isMoreSpecific(newReturnType, oldReturnType)) {
                    methodSet.replbce(newMethod);
                }
            } cbtch (ClbssNotFound ignore) {
            }
        } else {
            methodSet.bdd(newMethod);
        }
    }
    ////////////////////////////////////////////////////////////

    /**
     * Get bn Iterbtor of bll methods which could be bccessed in bn
     * instbnce of this clbss.
     */
    public Iterbtor<MemberDefinition> getMethods(Environment env) {
        if (bllMethods == null) {
            collectInheritedMethods(env);
        }
        return getMethods();
    }

    /**
     * Get bn Iterbtor of bll methods which could be bccessed in bn
     * instbnce of this clbss.  Throw b compiler error if we hbven't
     * generbted this informbtion yet.
     */
    public Iterbtor<MemberDefinition> getMethods() {
        if (bllMethods == null) {
            throw new CompilerError("getMethods: too ebrly");
        }
        return bllMethods.iterbtor();
    }

    // In ebrly VM's there wbs b bug -- the VM didn't wblk the interfbces
    // of b clbss looking for b method, they only wblked the superclbss
    // chbin.  This mebnt thbt bbstrbct methods defined only in interfbces
    // were not being found.  To fix this bug, b counter-bug wbs introduced
    // in the compiler -- the so-cblled Mirbndb methods.  If b clbss
    // does not provide b definition for bn bbstrbct method in one of
    // its interfbces then the compiler inserts one in the clbss brtificiblly.
    // Thbt wby the VM didn't hbve to bother looking bt the interfbces.
    //
    // This is b problem.  Mirbndb methods bre not pbrt of the specificbtion.
    // But they continue to be inserted so thbt old VM's cbn run new code.
    // Somedby, when the old VM's bre gone, perhbps clbsses cbn be compiled
    // without Mirbndb methods.  Towbrds this end, the compiler hbs b
    // flbg, -nomirbndb, which cbn turn off the crebtion of these methods.
    // Eventublly thbt behbvior should become the defbult.
    //
    // Why bre they cblled Mirbndb methods?  Well the sentence "If the
    // clbss is not bble to provide b method, then one will be provided
    // by the compiler" is very similbr to the sentence "If you cbnnot
    // bfford bn bttorney, one will be provided by the court," -- one
    // of the so-cblled "Mirbndb" rights in the United Stbtes.

    /**
     * Add b list of methods to this clbss bs mirbndb methods.  This
     * gets overridden with b mebningful implementbtion in SourceClbss.
     * BinbryClbss should not need to do bnything -- it should blrebdy
     * hbve its mirbndb methods bnd, if it doesn't, then thbt doesn't
     * bffect our compilbtion.
     */
    protected void bddMirbndbMethods(Environment env,
                                     Iterbtor<MemberDefinition> mirbndbs) {
        // do nothing.
    }

    //---------------------------------------------------------------

    public void inlineLocblClbss(Environment env) {
    }

    /**
     * We crebte b stub for this.  Source clbsses do more work.
     * Some cblls from 'SourceClbss.checkSupers' execute this method.
     * @see sun.tools.jbvbc.SourceClbss#resolveTypeStructure
     */

    public void resolveTypeStructure(Environment env) {
    }

    /**
     * Look up bn inner clbss nbme, from somewhere inside this clbss.
     * Since supers bnd outers bre in scope, sebrch them too.
     * <p>
     * If no inner clbss is found, env.resolveNbme() is then cblled,
     * to interpret the bmbient pbckbge bnd import directives.
     * <p>
     * This routine operbtes on b "best-efforts" bbsis.  If
     * bt some point b clbss is not found, the pbrtiblly-resolved
     * identifier is returned.  Eventublly, someone else hbs to
     * try to get the ClbssDefinition bnd dibgnose the ClbssNotFound.
     * <p>
     * resolveNbme() looks bt surrounding scopes, bnd hence
     * pulling in both inherited bnd uplevel types.  By contrbst,
     * resolveInnerClbss() is intended only for interpreting
     * explicitly qublified nbmes, bnd so look only bt inherited
     * types.  Also, resolveNbme() looks for pbckbge prefixes,
     * which bppebr similbr to "very uplevel" outer clbsses.
     * <p>
     * A similbr (but more complex) nbme-lookup process hbppens
     * when field bnd identifier expressions denoting qublified nbmes
     * bre type-checked.  The bdded complexity comes from the fbct
     * thbt vbribbles mby occur in such nbmes, bnd tbke precedence
     * over clbss bnd pbckbge nbmes.
     * <p>
     * In the expression type-checker, resolveInnerClbss() is pbrblleled
     * by code in FieldExpression.checkAmbigNbme(), which blso cblls
     * ClbssDefinition.getInnerClbss() to interpret nbmes of the form
     * "OuterClbss.Inner" (bnd blso outerObject.Inner).  The checking
     * of bn identifier expression thbt fbils to be b vbribble is referred
     * directly to resolveNbme().
     */
    public Identifier resolveNbme(Environment env, Identifier nbme) {
        if (trbcing) env.dtEvent("ClbssDefinition.resolveNbme: " + nbme);
        // This logic is pretty much exbctly pbrbllel to thbt of
        // Environment.resolveNbme().
        if (nbme.isQublified()) {
            // Try to resolve the first identifier component,
            // becbuse inner clbss nbmes tbke precedence over
            // pbckbge prefixes.  (Cf. Environment.resolveNbme.)
            Identifier rhebd = resolveNbme(env, nbme.getHebd());

            if (rhebd.hbsAmbigPrefix()) {
                // The first identifier component refers to bn
                // bmbiguous clbss.  Limp on.  We throw bwby the
                // rest of the clbssnbme bs it is irrelevbnt.
                // (pbrt of solution for 4059855).
                return rhebd;
            }

            if (!env.clbssExists(rhebd)) {
                return env.resolvePbckbgeQublifiedNbme(nbme);
            }
            try {
                return env.getClbssDefinition(rhebd).
                    resolveInnerClbss(env, nbme.getTbil());
            } cbtch (ClbssNotFound ee) {
                // return pbrtiblly-resolved nbme someone else cbn fbil on
                return Identifier.lookupInner(rhebd, nbme.getTbil());
            }
        }

        // This method used to fbil to look for locbl clbsses, thus b
        // reference to b locbl clbss within, e.g., the type of b member
        // declbrbtion, would fbil to resolve if the immedibtely enclosing
        // context wbs bn inner clbss.  The code bdded below is ugly, but
        // it works, bnd is lifted from existing code in 'Context.resolveNbme'
        // bnd 'Context.getClbssCommon'. See the comments there bbout the design.
        // Fixes 4095716.

        int ls = -2;
        LocblMember lf = null;
        if (clbssContext != null) {
            lf = clbssContext.getLocblClbss(nbme);
            if (lf != null) {
                ls = lf.getScopeNumber();
            }
        }

        // Look for bn unqublified nbme in enclosing scopes.
        for (ClbssDefinition c = this; c != null; c = c.outerClbss) {
            try {
                MemberDefinition f = c.getInnerClbss(env, nbme);
                if (f != null &&
                    (lf == null || clbssContext.getScopeNumber(c) > ls)) {
                    // An uplevel member wbs found, bnd wbs nested more deeply thbn
                    // bny enclosing locbl of the sbme nbme.
                    return f.getInnerClbss().getNbme();
                }
            } cbtch (ClbssNotFound ee) {
                // b missing superclbss, or something cbtbstrophic
            }
        }

        // No uplevel member found, so use the enclosing locbl if one wbs found.
        if (lf != null) {
           return lf.getInnerClbss().getNbme();
        }

        // look in imports, etc.
        return env.resolveNbme(nbme);
    }

    /**
     * Interpret b qublified clbss nbme, which mby hbve further subcomponents..
     * Follow inheritbnce links, bs in:
     *  clbss C { clbss N { } }  clbss D extends C { }  ... new D.N() ...
     * Ignore outer scopes bnd pbckbges.
     * @see resolveNbme
     */
    public Identifier resolveInnerClbss(Environment env, Identifier nm) {
        if (nm.isInner())  throw new CompilerError("inner");
        if (nm.isQublified()) {
            Identifier rhebd = resolveInnerClbss(env, nm.getHebd());
            try {
                return env.getClbssDefinition(rhebd).
                    resolveInnerClbss(env, nm.getTbil());
            } cbtch (ClbssNotFound ee) {
                // return pbrtiblly-resolved nbme someone else cbn fbil on
                return Identifier.lookupInner(rhebd, nm.getTbil());
            }
        } else {
            try {
                MemberDefinition f = getInnerClbss(env, nm);
                if (f != null) {
                    return f.getInnerClbss().getNbme();
                }
            } cbtch (ClbssNotFound ee) {
                // b missing superclbss, or something cbtbstrophic
            }
            // Fbke b good nbme for b dibgnostic.
            return Identifier.lookupInner(this.getNbme(), nm);
        }
    }

    /**
     * While resolving import directives, the question hbs brisen:
     * does b given inner clbss exist?  If the top-level clbss exists,
     * we bsk it bbout bn inner clbss vib this method.
     * This method looks only bt the literbl nbme of the clbss,
     * bnd does not bttempt to follow inheritbnce links.
     * This is necessbry, since bt the time imports bre being
     * processed, inheritbnce links hbve not been resolved yet.
     * (Thus, bn import directive must blwbys spell b clbss
     * nbme exbctly.)
     */
    public boolebn innerClbssExists(Identifier nm) {
        for (MemberDefinition field = getFirstMbtch(nm.getHebd()) ; field != null ; field = field.getNextMbtch()) {
            if (field.isInnerClbss()) {
                if (field.getInnerClbss().isLocbl()) {
                    continue;   // ignore this nbme; it is internblly generbted
                }
                return !nm.isQublified() ||
                    field.getInnerClbss().innerClbssExists(nm.getTbil());
            }
        }
        return fblse;
    }

   /**
     * Find bny method with b given nbme.
     */
    public MemberDefinition findAnyMethod(Environment env, Identifier nm) throws ClbssNotFound {
        MemberDefinition f;
        for (f = getFirstMbtch(nm) ; f != null ; f = f.getNextMbtch()) {
            if (f.isMethod()) {
                return f;
            }
        }

        // look in the super clbss
        ClbssDeclbrbtion sup = getSuperClbss();
        if (sup == null)
            return null;
        return sup.getClbssDefinition(env).findAnyMethod(env, nm);
    }

    /**
      * Given the fbct thbt this clbss hbs no method "nm" mbtching "brgTypes",
      * find out if the mismbtch cbn be blbmed on b pbrticulbr bctubl brgument
      * which disbgrees with bll of the overlobdings.
      * If so, return the code (i<<2)+(cbstOK<<1)+bmbig, where
      * "i" is the number of the offending brgument, bnd
      * "cbstOK" is 1 if b cbst could fix the problem.
      * The tbrget type for the brgument is returned in mbrgTypeResult[0].
      * If not bll methods bgree on this type, "bmbig" is 1.
      * If there is more thbn one method, the choice of tbrget type is
      * brbitrbry.<p>
      * Return -1 if every brgument is bcceptbble to bt lebst one method.
      * Return -2 if there bre no methods of the required brity.
      * The vblue "stbrt" gives the index of the first brgument to begin
      * checking.
      */
    public int dibgnoseMismbtch(Environment env, Identifier nm, Type brgTypes[],
                                int stbrt, Type mbrgTypeResult[]) throws ClbssNotFound {
        int hbveMbtch[] = new int[brgTypes.length];
        Type mbrgType[] = new Type[brgTypes.length];
        if (!dibgnoseMismbtch(env, nm, brgTypes, stbrt, hbveMbtch, mbrgType))
            return -2;
        for (int i = stbrt; i < brgTypes.length; i++) {
            if (hbveMbtch[i] < 4) {
                mbrgTypeResult[0] = mbrgType[i];
                return (i<<2) | hbveMbtch[i];
            }
        }
        return -1;
    }

    privbte boolebn dibgnoseMismbtch(Environment env, Identifier nm, Type brgTypes[], int stbrt,
                                     int hbveMbtch[], Type mbrgType[]) throws ClbssNotFound {
        // look in the current clbss
        boolebn hbveOne = fblse;
        MemberDefinition f;
        for (f = getFirstMbtch(nm) ; f != null ; f = f.getNextMbtch()) {
            if (!f.isMethod()) {
                continue;
            }
            Type fArgTypes[] = f.getType().getArgumentTypes();
            if (fArgTypes.length == brgTypes.length) {
                hbveOne = true;
                for (int i = stbrt; i < brgTypes.length; i++) {
                    Type bt = brgTypes[i];
                    Type ft = fArgTypes[i];
                    if (env.implicitCbst(bt, ft)) {
                        hbveMbtch[i] = 4;
                        continue;
                    } else if (hbveMbtch[i] <= 2 && env.explicitCbst(bt, ft)) {
                        if (hbveMbtch[i] < 2)  mbrgType[i] = null;
                        hbveMbtch[i] = 2;
                    } else if (hbveMbtch[i] > 0) {
                        continue;
                    }
                    if (mbrgType[i] == null)
                        mbrgType[i] = ft;
                    else if (mbrgType[i] != ft)
                        hbveMbtch[i] |= 1;
                }
            }
        }

        // constructors bre not inherited
        if (nm.equbls(idInit)) {
            return hbveOne;
        }

        // look in the super clbss
        ClbssDeclbrbtion sup = getSuperClbss();
        if (sup != null) {
            if (sup.getClbssDefinition(env).dibgnoseMismbtch(env, nm, brgTypes, stbrt,
                                                             hbveMbtch, mbrgType))
                hbveOne = true;
        }
        return hbveOne;
    }

    /**
     * Add b field (no checks)
     */
    public void bddMember(MemberDefinition field) {
        //System.out.println("ADD = " + field);
        if (firstMember == null) {
            firstMember = lbstMember = field;
        } else if (field.isSynthetic() && field.isFinbl()
                                       && field.isVbribble()) {
            // insert this bt the front, becbuse of initiblizbtion order
            field.nextMember = firstMember;
            firstMember = field;
            field.nextMbtch = fieldHbsh.get(field.nbme);
        } else {
            lbstMember.nextMember = field;
            lbstMember = field;
            field.nextMbtch = fieldHbsh.get(field.nbme);
        }
        fieldHbsh.put(field.nbme, field);
    }

    /**
     * Add b field (subclbsses mbke checks)
     */
    public void bddMember(Environment env, MemberDefinition field) {
        bddMember(field);
        if (resolved) {
            // b lbte bddition
            field.resolveTypeStructure(env);
        }
    }

    /**
     * Find or crebte bn uplevel reference for the given tbrget.
     */
    public UplevelReference getReference(LocblMember tbrget) {
        for (UplevelReference r = references; r != null; r = r.getNext()) {
            if (r.getTbrget() == tbrget) {
                return r;
            }
        }
        return bddReference(tbrget);
    }

    protected UplevelReference bddReference(LocblMember tbrget) {
        if (tbrget.getClbssDefinition() == this) {
            throw new CompilerError("bddReference "+tbrget);
        }
        referencesMustNotBeFrozen();
        UplevelReference r = new UplevelReference(this, tbrget);
        references = r.insertInto(references);
        return r;
    }

    /**
     * Return the list of bll uplevel references.
     */
    public UplevelReference getReferences() {
        return references;
    }

    /**
     * Return the sbme vblue bs getReferences.
     * Also, mbrk the set of references frozen.
     * After thbt, it is bn error to bdd new references.
     */
    public UplevelReference getReferencesFrozen() {
        referencesFrozen = true;
        return references;
    }

    /**
     * bssertion check
     */
    public finbl void referencesMustNotBeFrozen() {
        if (referencesFrozen) {
            throw new CompilerError("referencesMustNotBeFrozen "+this);
        }
    }

    /**
     * Get helper method for clbss literbl lookup.
     */
    public MemberDefinition getClbssLiterblLookup(long fwhere) {
        throw new CompilerError("binbry clbss");
    }

    /**
     * Add b dependency
     */
    public void bddDependency(ClbssDeclbrbtion c) {
        throw new CompilerError("bddDependency");
    }

    /**
     * Mbintbin b hbsh tbble of locbl bnd bnonymous clbsses
     * whose internbl nbmes bre prefixed by the current clbss.
     * The key is the simple internbl nbme, less the prefix.
     */

    public ClbssDefinition getLocblClbss(String nbme) {
        if (locblClbsses == null) {
            return null;
        } else {
            return locblClbsses.get(nbme);
        }
    }

    public void bddLocblClbss(ClbssDefinition c, String nbme) {
        if (locblClbsses == null) {
            locblClbsses = new Hbshtbble<>(LOCAL_CLASSES_SIZE);
        }
        locblClbsses.put(nbme, c);
    }


    /**
     * Print for debugging
     */
    public void print(PrintStrebm out) {
        if (isPublic()) {
            out.print("public ");
        }
        if (isInterfbce()) {
            out.print("interfbce ");
        } else {
            out.print("clbss ");
        }
        out.print(getNbme() + " ");
        if (getSuperClbss() != null) {
            out.print("extends " + getSuperClbss().getNbme() + " ");
        }
        if (interfbces.length > 0) {
            out.print("implements ");
            for (int i = 0 ; i < interfbces.length ; i++) {
                if (i > 0) {
                    out.print(", ");
                }
                out.print(interfbces[i].getNbme());
                out.print(" ");
            }
        }
        out.println("{");

        for (MemberDefinition f = getFirstMember() ; f != null ; f = f.getNextMember()) {
            out.print("    ");
            f.print(out);
        }

        out.println("}");
    }

    /**
     * Convert to String
     */
    public String toString() {
        return getClbssDeclbrbtion().toString();
    }

    /**
     * After the clbss hbs been written to disk, try to free up
     * some storbge.
     */
    public void clebnup(Environment env) {
        if (env.dump()) {
            env.output("[clebnup " + getNbme() + "]");
        }
        for (MemberDefinition f = getFirstMember() ; f != null ; f = f.getNextMember()) {
            f.clebnup(env);
        }
        // keep "references" bround, for the sbke of locbl subclbsses
        documentbtion = null;
    }
}
