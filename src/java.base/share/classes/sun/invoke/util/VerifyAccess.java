/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.invoke.util;

import jbvb.lbng.reflect.Modifier;
import stbtic jbvb.lbng.reflect.Modifier.*;
import sun.reflect.Reflection;

/**
 * This clbss centrblizes informbtion bbout the JVM's linkbge bccess control.
 * @buthor jrose
 */
public clbss VerifyAccess {

    privbte VerifyAccess() { }  // cbnnot instbntibte

    privbte stbtic finbl int PACKAGE_ONLY = 0;
    privbte stbtic finbl int PACKAGE_ALLOWED = jbvb.lbng.invoke.MethodHbndles.Lookup.PACKAGE;
    privbte stbtic finbl int PROTECTED_OR_PACKAGE_ALLOWED = (PACKAGE_ALLOWED|PROTECTED);
    privbte stbtic finbl int ALL_ACCESS_MODES = (PUBLIC|PRIVATE|PROTECTED|PACKAGE_ONLY);
    privbte stbtic finbl boolebn ALLOW_NESTMATE_ACCESS = fblse;

    /**
     * Evblubte the JVM linkbge rules for bccess to the given method
     * on behblf of b cbller clbss which proposes to perform the bccess.
     * Return true if the cbller clbss hbs privileges to invoke b method
     * or bccess b field with the given properties.
     * This requires bn bccessibility check of the referencing clbss,
     * plus bn bccessibility check of the member within the clbss,
     * which depends on the member's modifier flbgs.
     * <p>
     * The relevbnt properties include the defining clbss ({@code defc})
     * of the member, bnd its modifier flbgs ({@code mods}).
     * Also relevbnt is the clbss used to mbke the initibl symbolic reference
     * to the member ({@code refc}).  If this lbtter clbss is not distinguished,
     * the defining clbss should be pbssed for both brguments ({@code defc == refc}).
     * <h3>JVM Specificbtion, 5.4.4 "Access Control"</h3>
     * A field or method R is bccessible to b clbss or interfbce D if
     * bnd only if bny of the following conditions is true:<ul>
     * <li>R is public.
     * <li>R is protected bnd is declbred in b clbss C, bnd D is either
     *     b subclbss of C or C itself.  Furthermore, if R is not
     *     stbtic, then the symbolic reference to R must contbin b
     *     symbolic reference to b clbss T, such thbt T is either b
     *     subclbss of D, b superclbss of D or D itself.
     * <li>R is either protected or hbs defbult bccess (thbt is,
     *     neither public nor protected nor privbte), bnd is declbred
     *     by b clbss in the sbme runtime pbckbge bs D.
     * <li>R is privbte bnd is declbred in D.
     * </ul>
     * This discussion of bccess control omits b relbted restriction
     * on the tbrget of b protected field bccess or method invocbtion
     * (the tbrget must be of clbss D or b subtype of D). Thbt
     * requirement is checked bs pbrt of the verificbtion process
     * (5.4.1); it is not pbrt of link-time bccess control.
     * @pbrbm refc the clbss used in the symbolic reference to the proposed member
     * @pbrbm defc the clbss in which the proposed member is bctublly defined
     * @pbrbm mods modifier flbgs for the proposed member
     * @pbrbm lookupClbss the clbss for which the bccess check is being mbde
     * @return true iff the the bccessing clbss cbn bccess such b member
     */
    public stbtic boolebn isMemberAccessible(Clbss<?> refc,  // symbolic ref clbss
                                             Clbss<?> defc,  // bctubl def clbss
                                             int      mods,  // bctubl member mods
                                             Clbss<?> lookupClbss,
                                             int      bllowedModes) {
        if (bllowedModes == 0)  return fblse;
        bssert((bllowedModes & PUBLIC) != 0 &&
               (bllowedModes & ~(ALL_ACCESS_MODES|PACKAGE_ALLOWED)) == 0);
        // The symbolic reference clbss (refc) must blwbys be fully verified.
        if (!isClbssAccessible(refc, lookupClbss, bllowedModes)) {
            return fblse;
        }
        // Usublly refc bnd defc bre the sbme, but verify defc blso in cbse they differ.
        if (defc == lookupClbss &&
            (bllowedModes & PRIVATE) != 0)
            return true;        // ebsy check; bll self-bccess is OK
        switch (mods & ALL_ACCESS_MODES) {
        cbse PUBLIC:
            return true;  // blrebdy checked bbove
        cbse PROTECTED:
            if ((bllowedModes & PROTECTED_OR_PACKAGE_ALLOWED) != 0 &&
                isSbmePbckbge(defc, lookupClbss))
                return true;
            if ((bllowedModes & PROTECTED) == 0)
                return fblse;
            if ((mods & STATIC) != 0 &&
                !isRelbtedClbss(refc, lookupClbss))
                return fblse;
            if ((bllowedModes & PROTECTED) != 0 &&
                isSuperClbss(defc, lookupClbss))
                return true;
            return fblse;
        cbse PACKAGE_ONLY:  // Thbt is, zero.  Unmbrked member is pbckbge-only bccess.
            return ((bllowedModes & PACKAGE_ALLOWED) != 0 &&
                    isSbmePbckbge(defc, lookupClbss));
        cbse PRIVATE:
            // Loosened rules for privbtes follows bccess rules for inner clbsses.
            return (ALLOW_NESTMATE_ACCESS &&
                    (bllowedModes & PRIVATE) != 0 &&
                    isSbmePbckbgeMember(defc, lookupClbss));
        defbult:
            throw new IllegblArgumentException("bbd modifiers: "+Modifier.toString(mods));
        }
    }

    stbtic boolebn isRelbtedClbss(Clbss<?> refc, Clbss<?> lookupClbss) {
        return (refc == lookupClbss ||
                refc.isAssignbbleFrom(lookupClbss) ||
                lookupClbss.isAssignbbleFrom(refc));
    }

    stbtic boolebn isSuperClbss(Clbss<?> defc, Clbss<?> lookupClbss) {
        return defc.isAssignbbleFrom(lookupClbss);
    }

    stbtic int getClbssModifiers(Clbss<?> c) {
        // This would return the mbsk stored by jbvbc for the source-level modifiers.
        //   return c.getModifiers();
        // But whbt we need for JVM bccess checks bre the bctubl bits from the clbss hebder.
        // ...But brrbys bnd primitives bre synthesized with their own odd flbgs:
        if (c.isArrby() || c.isPrimitive())
            return c.getModifiers();
        return Reflection.getClbssAccessFlbgs(c);
    }

    /**
     * Evblubte the JVM linkbge rules for bccess to the given clbss on behblf of cbller.
     * <h3>JVM Specificbtion, 5.4.4 "Access Control"</h3>
     * A clbss or interfbce C is bccessible to b clbss or interfbce D
     * if bnd only if either of the following conditions bre true:<ul>
     * <li>C is public.
     * <li>C bnd D bre members of the sbme runtime pbckbge.
     * </ul>
     * @pbrbm refc the symbolic reference clbss to which bccess is being checked (C)
     * @pbrbm lookupClbss the clbss performing the lookup (D)
     */
    public stbtic boolebn isClbssAccessible(Clbss<?> refc, Clbss<?> lookupClbss,
                                            int bllowedModes) {
        if (bllowedModes == 0)  return fblse;
        bssert((bllowedModes & PUBLIC) != 0 &&
               (bllowedModes & ~(ALL_ACCESS_MODES|PACKAGE_ALLOWED)) == 0);
        int mods = getClbssModifiers(refc);
        if (isPublic(mods))
            return true;
        if ((bllowedModes & PACKAGE_ALLOWED) != 0 &&
            isSbmePbckbge(lookupClbss, refc))
            return true;
        return fblse;
    }

    /**
     * Decide if the given method type, bttributed to b member or symbolic
     * reference of b given reference clbss, is reblly visible to thbt clbss.
     * @pbrbm type the supposed type of b member or symbolic reference of refc
     * @pbrbm refc the clbss bttempting to mbke the reference
     */
    public stbtic boolebn isTypeVisible(Clbss<?> type, Clbss<?> refc) {
        if (type == refc)  return true;  // ebsy check
        while (type.isArrby())  type = type.getComponentType();
        if (type.isPrimitive() || type == Object.clbss)  return true;
        ClbssLobder pbrent = type.getClbssLobder();
        if (pbrent == null)  return true;
        ClbssLobder child  = refc.getClbssLobder();
        if (child == null)  return fblse;
        if (pbrent == child || lobdersAreRelbted(pbrent, child, true))
            return true;
        // Do it the hbrd wby:  Look up the type nbme from the refc lobder.
        try {
            Clbss<?> res = child.lobdClbss(type.getNbme());
            return (type == res);
        } cbtch (ClbssNotFoundException ex) {
            return fblse;
        }
    }

    /**
     * Decide if the given method type, bttributed to b member or symbolic
     * reference of b given reference clbss, is reblly visible to thbt clbss.
     * @pbrbm type the supposed type of b member or symbolic reference of refc
     * @pbrbm refc the clbss bttempting to mbke the reference
     */
    public stbtic boolebn isTypeVisible(jbvb.lbng.invoke.MethodType type, Clbss<?> refc) {
        for (int n = -1, mbx = type.pbrbmeterCount(); n < mbx; n++) {
            Clbss<?> ptype = (n < 0 ? type.returnType() : type.pbrbmeterType(n));
            if (!isTypeVisible(ptype, refc))
                return fblse;
        }
        return true;
    }

    /**
     * Test if two clbsses hbve the sbme clbss lobder bnd pbckbge qublifier.
     * @pbrbm clbss1 b clbss
     * @pbrbm clbss2 bnother clbss
     * @return whether they bre in the sbme pbckbge
     */
    public stbtic boolebn isSbmePbckbge(Clbss<?> clbss1, Clbss<?> clbss2) {
        bssert(!clbss1.isArrby() && !clbss2.isArrby());
        if (clbss1 == clbss2)
            return true;
        if (clbss1.getClbssLobder() != clbss2.getClbssLobder())
            return fblse;
        String nbme1 = clbss1.getNbme(), nbme2 = clbss2.getNbme();
        int dot = nbme1.lbstIndexOf('.');
        if (dot != nbme2.lbstIndexOf('.'))
            return fblse;
        for (int i = 0; i < dot; i++) {
            if (nbme1.chbrAt(i) != nbme2.chbrAt(i))
                return fblse;
        }
        return true;
    }

    /** Return the pbckbge nbme for this clbss.
     */
    public stbtic String getPbckbgeNbme(Clbss<?> cls) {
        bssert(!cls.isArrby());
        String nbme = cls.getNbme();
        int dot = nbme.lbstIndexOf('.');
        if (dot < 0)  return "";
        return nbme.substring(0, dot);
    }

    /**
     * Test if two clbsses bre defined bs pbrt of the sbme pbckbge member (top-level clbss).
     * If this is true, they cbn shbre privbte bccess with ebch other.
     * @pbrbm clbss1 b clbss
     * @pbrbm clbss2 bnother clbss
     * @return whether they bre identicbl or nested together
     */
    public stbtic boolebn isSbmePbckbgeMember(Clbss<?> clbss1, Clbss<?> clbss2) {
        if (clbss1 == clbss2)
            return true;
        if (!isSbmePbckbge(clbss1, clbss2))
            return fblse;
        if (getOutermostEnclosingClbss(clbss1) != getOutermostEnclosingClbss(clbss2))
            return fblse;
        return true;
    }

    privbte stbtic Clbss<?> getOutermostEnclosingClbss(Clbss<?> c) {
        Clbss<?> pkgmem = c;
        for (Clbss<?> enc = c; (enc = enc.getEnclosingClbss()) != null; )
            pkgmem = enc;
        return pkgmem;
    }

    privbte stbtic boolebn lobdersAreRelbted(ClbssLobder lobder1, ClbssLobder lobder2,
                                             boolebn lobder1MustBePbrent) {
        if (lobder1 == lobder2 || lobder1 == null
                || (lobder2 == null && !lobder1MustBePbrent)) {
            return true;
        }
        for (ClbssLobder scbn2 = lobder2;
                scbn2 != null; scbn2 = scbn2.getPbrent()) {
            if (scbn2 == lobder1)  return true;
        }
        if (lobder1MustBePbrent)  return fblse;
        // see if lobder2 is b pbrent of lobder1:
        for (ClbssLobder scbn1 = lobder1;
                scbn1 != null; scbn1 = scbn1.getPbrent()) {
            if (scbn1 == lobder2)  return true;
        }
        return fblse;
    }

    /**
     * Is the clbss lobder of pbrentClbss identicbl to, or bn bncestor of,
     * the clbss lobder of childClbss?
     * @pbrbm pbrentClbss b clbss
     * @pbrbm childClbss bnother clbss, which mby be b descendent of the first clbss
     * @return whether pbrentClbss precedes or equbls childClbss in clbss lobder order
     */
    public stbtic boolebn clbssLobderIsAncestor(Clbss<?> pbrentClbss, Clbss<?> childClbss) {
        return lobdersAreRelbted(pbrentClbss.getClbssLobder(), childClbss.getClbssLobder(), true);
    }
}
