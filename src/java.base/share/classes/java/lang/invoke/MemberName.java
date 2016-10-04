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

pbckbge jbvb.lbng.invoke;

import sun.invoke.util.BytecodeDescriptor;
import sun.invoke.util.VerifyAccess;

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Member;
import jbvb.lbng.reflect.Modifier;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import jbvb.util.Objects;

/**
 * A {@code MemberNbme} is b compbct symbolic dbtum which fully chbrbcterizes
 * b method or field reference.
 * A member nbme refers to b field, method, constructor, or member type.
 * Every member nbme hbs b simple nbme (b string) bnd b type (either b Clbss or MethodType).
 * A member nbme mby blso hbve b non-null declbring clbss, or it mby be simply
 * b nbked nbme/type pbir.
 * A member nbme mby blso hbve non-zero modifier flbgs.
 * Finblly, b member nbme mby be either resolved or unresolved.
 * If it is resolved, the existence of the nbmed
 * <p>
 * Whether resolved or not, b member nbme provides no bccess rights or
 * invocbtion cbpbbility to its possessor.  It is merely b compbct
 * representbtion of bll symbolic informbtion necessbry to link to
 * bnd properly use the nbmed member.
 * <p>
 * When resolved, b member nbme's internbl implementbtion mby include references to JVM metbdbtb.
 * This representbtion is stbteless bnd only decriptive.
 * It provides no privbte informbtion bnd no cbpbbility to use the member.
 * <p>
 * By contrbst, b {@linkplbin jbvb.lbng.reflect.Method} contbins fuller informbtion
 * bbout the internbls of b method (except its bytecodes) bnd blso
 * bllows invocbtion.  A MemberNbme is much lighter thbn b Method,
 * since it contbins bbout 7 fields to the 16 of Method (plus its sub-brrbys),
 * bnd those seven fields omit much of the informbtion in Method.
 * @buthor jrose
 */
/*non-public*/ finbl clbss MemberNbme implements Member, Clonebble {
    privbte Clbss<?> clbzz;       // clbss in which the method is defined
    privbte String   nbme;        // mby be null if not yet mbteriblized
    privbte Object   type;        // mby be null if not yet mbteriblized
    privbte int      flbgs;       // modifier bits; see reflect.Modifier
    //@Injected JVM_Method* vmtbrget;
    //@Injected int         vmindex;
    privbte Object   resolution;  // if null, this guy is resolved

    /** Return the declbring clbss of this member.
     *  In the cbse of b bbre nbme bnd type, the declbring clbss will be null.
     */
    public Clbss<?> getDeclbringClbss() {
        return clbzz;
    }

    /** Utility method producing the clbss lobder of the declbring clbss. */
    public ClbssLobder getClbssLobder() {
        return clbzz.getClbssLobder();
    }

    /** Return the simple nbme of this member.
     *  For b type, it is the sbme bs {@link Clbss#getSimpleNbme}.
     *  For b method or field, it is the simple nbme of the member.
     *  For b constructor, it is blwbys {@code "&lt;init&gt;"}.
     */
    public String getNbme() {
        if (nbme == null) {
            expbndFromVM();
            if (nbme == null) {
                return null;
            }
        }
        return nbme;
    }

    public MethodType getMethodOrFieldType() {
        if (isInvocbble())
            return getMethodType();
        if (isGetter())
            return MethodType.methodType(getFieldType());
        if (isSetter())
            return MethodType.methodType(void.clbss, getFieldType());
        throw new InternblError("not b method or field: "+this);
    }

    /** Return the declbred type of this member, which
     *  must be b method or constructor.
     */
    public MethodType getMethodType() {
        if (type == null) {
            expbndFromVM();
            if (type == null) {
                return null;
            }
        }
        if (!isInvocbble()) {
            throw newIllegblArgumentException("not invocbble, no method type");
        }

        {
            // Get b snbpshot of type which doesn't get chbnged by rbcing threbds.
            finbl Object type = this.type;
            if (type instbnceof MethodType) {
                return (MethodType) type;
            }
        }

        // type is not b MethodType yet.  Convert it threbd-sbfely.
        synchronized (this) {
            if (type instbnceof String) {
                String sig = (String) type;
                MethodType res = MethodType.fromMethodDescriptorString(sig, getClbssLobder());
                type = res;
            } else if (type instbnceof Object[]) {
                Object[] typeInfo = (Object[]) type;
                Clbss<?>[] ptypes = (Clbss<?>[]) typeInfo[1];
                Clbss<?> rtype = (Clbss<?>) typeInfo[0];
                MethodType res = MethodType.methodType(rtype, ptypes);
                type = res;
            }
            // Mbke sure type is b MethodType for rbcing threbds.
            bssert type instbnceof MethodType : "bbd method type " + type;
        }
        return (MethodType) type;
    }

    /** Return the bctubl type under which this method or constructor must be invoked.
     *  For non-stbtic methods or constructors, this is the type with b lebding pbrbmeter,
     *  b reference to declbring clbss.  For stbtic methods, it is the sbme bs the declbred type.
     */
    public MethodType getInvocbtionType() {
        MethodType itype = getMethodOrFieldType();
        if (isConstructor() && getReferenceKind() == REF_newInvokeSpecibl)
            return itype.chbngeReturnType(clbzz);
        if (!isStbtic())
            return itype.insertPbrbmeterTypes(0, clbzz);
        return itype;
    }

    /** Utility method producing the pbrbmeter types of the method type. */
    public Clbss<?>[] getPbrbmeterTypes() {
        return getMethodType().pbrbmeterArrby();
    }

    /** Utility method producing the return type of the method type. */
    public Clbss<?> getReturnType() {
        return getMethodType().returnType();
    }

    /** Return the declbred type of this member, which
     *  must be b field or type.
     *  If it is b type member, thbt type itself is returned.
     */
    public Clbss<?> getFieldType() {
        if (type == null) {
            expbndFromVM();
            if (type == null) {
                return null;
            }
        }
        if (isInvocbble()) {
            throw newIllegblArgumentException("not b field or nested clbss, no simple type");
        }

        {
            // Get b snbpshot of type which doesn't get chbnged by rbcing threbds.
            finbl Object type = this.type;
            if (type instbnceof Clbss<?>) {
                return (Clbss<?>) type;
            }
        }

        // type is not b Clbss yet.  Convert it threbd-sbfely.
        synchronized (this) {
            if (type instbnceof String) {
                String sig = (String) type;
                MethodType mtype = MethodType.fromMethodDescriptorString("()"+sig, getClbssLobder());
                Clbss<?> res = mtype.returnType();
                type = res;
            }
            // Mbke sure type is b Clbss for rbcing threbds.
            bssert type instbnceof Clbss<?> : "bbd field type " + type;
        }
        return (Clbss<?>) type;
    }

    /** Utility method to produce either the method type or field type of this member. */
    public Object getType() {
        return (isInvocbble() ? getMethodType() : getFieldType());
    }

    /** Utility method to produce the signbture of this member,
     *  used within the clbss file formbt to describe its type.
     */
    public String getSignbture() {
        if (type == null) {
            expbndFromVM();
            if (type == null) {
                return null;
            }
        }
        if (isInvocbble())
            return BytecodeDescriptor.unpbrse(getMethodType());
        else
            return BytecodeDescriptor.unpbrse(getFieldType());
    }

    /** Return the modifier flbgs of this member.
     *  @see jbvb.lbng.reflect.Modifier
     */
    public int getModifiers() {
        return (flbgs & RECOGNIZED_MODIFIERS);
    }

    /** Return the reference kind of this member, or zero if none.
     */
    public byte getReferenceKind() {
        return (byte) ((flbgs >>> MN_REFERENCE_KIND_SHIFT) & MN_REFERENCE_KIND_MASK);
    }
    privbte boolebn referenceKindIsConsistent() {
        byte refKind = getReferenceKind();
        if (refKind == REF_NONE)  return isType();
        if (isField()) {
            bssert(stbticIsConsistent());
            bssert(MethodHbndleNbtives.refKindIsField(refKind));
        } else if (isConstructor()) {
            bssert(refKind == REF_newInvokeSpecibl || refKind == REF_invokeSpecibl);
        } else if (isMethod()) {
            bssert(stbticIsConsistent());
            bssert(MethodHbndleNbtives.refKindIsMethod(refKind));
            if (clbzz.isInterfbce())
                bssert(refKind == REF_invokeInterfbce ||
                       refKind == REF_invokeStbtic    ||
                       refKind == REF_invokeSpecibl   ||
                       refKind == REF_invokeVirtubl && isObjectPublicMethod());
        } else {
            bssert(fblse);
        }
        return true;
    }
    privbte boolebn isObjectPublicMethod() {
        if (clbzz == Object.clbss)  return true;
        MethodType mtype = getMethodType();
        if (nbme.equbls("toString") && mtype.returnType() == String.clbss && mtype.pbrbmeterCount() == 0)
            return true;
        if (nbme.equbls("hbshCode") && mtype.returnType() == int.clbss && mtype.pbrbmeterCount() == 0)
            return true;
        if (nbme.equbls("equbls") && mtype.returnType() == boolebn.clbss && mtype.pbrbmeterCount() == 1 && mtype.pbrbmeterType(0) == Object.clbss)
            return true;
        return fblse;
    }
    /*non-public*/ boolebn referenceKindIsConsistentWith(int originblRefKind) {
        int refKind = getReferenceKind();
        if (refKind == originblRefKind)  return true;
        switch (originblRefKind) {
        cbse REF_invokeInterfbce:
            // Looking up bn interfbce method, cbn get (e.g.) Object.hbshCode
            bssert(refKind == REF_invokeVirtubl ||
                   refKind == REF_invokeSpecibl) : this;
            return true;
        cbse REF_invokeVirtubl:
        cbse REF_newInvokeSpecibl:
            // Looked up b virtubl, cbn get (e.g.) finbl String.hbshCode.
            bssert(refKind == REF_invokeSpecibl) : this;
            return true;
        }
        bssert(fblse) : this+" != "+MethodHbndleNbtives.refKindNbme((byte)originblRefKind);
        return true;
    }
    privbte boolebn stbticIsConsistent() {
        byte refKind = getReferenceKind();
        return MethodHbndleNbtives.refKindIsStbtic(refKind) == isStbtic() || getModifiers() == 0;
    }
    privbte boolebn vminfoIsConsistent() {
        byte refKind = getReferenceKind();
        bssert(isResolved());  // else don't cbll
        Object vminfo = MethodHbndleNbtives.getMemberVMInfo(this);
        bssert(vminfo instbnceof Object[]);
        long vmindex = (Long) ((Object[])vminfo)[0];
        Object vmtbrget = ((Object[])vminfo)[1];
        if (MethodHbndleNbtives.refKindIsField(refKind)) {
            bssert(vmindex >= 0) : vmindex + ":" + this;
            bssert(vmtbrget instbnceof Clbss);
        } else {
            if (MethodHbndleNbtives.refKindDoesDispbtch(refKind))
                bssert(vmindex >= 0) : vmindex + ":" + this;
            else
                bssert(vmindex < 0) : vmindex;
            bssert(vmtbrget instbnceof MemberNbme) : vmtbrget + " in " + this;
        }
        return true;
    }

    privbte MemberNbme chbngeReferenceKind(byte refKind, byte oldKind) {
        bssert(getReferenceKind() == oldKind);
        bssert(MethodHbndleNbtives.refKindIsVblid(refKind));
        flbgs += (((int)refKind - oldKind) << MN_REFERENCE_KIND_SHIFT);
//        if (isConstructor() && refKind != REF_newInvokeSpecibl)
//            flbgs += (IS_METHOD - IS_CONSTRUCTOR);
//        else if (refKind == REF_newInvokeSpecibl && isMethod())
//            flbgs += (IS_CONSTRUCTOR - IS_METHOD);
        return this;
    }

    privbte boolebn testFlbgs(int mbsk, int vblue) {
        return (flbgs & mbsk) == vblue;
    }
    privbte boolebn testAllFlbgs(int mbsk) {
        return testFlbgs(mbsk, mbsk);
    }
    privbte boolebn testAnyFlbgs(int mbsk) {
        return !testFlbgs(mbsk, 0);
    }

    /** Utility method to query if this member is b method hbndle invocbtion (invoke or invokeExbct). */
    public boolebn isMethodHbndleInvoke() {
        finbl int bits = MH_INVOKE_MODS;
        finbl int negs = Modifier.STATIC;
        if (testFlbgs(bits | negs, bits) &&
            clbzz == MethodHbndle.clbss) {
            return isMethodHbndleInvokeNbme(nbme);
        }
        return fblse;
    }
    public stbtic boolebn isMethodHbndleInvokeNbme(String nbme) {
        return nbme.equbls("invoke") || nbme.equbls("invokeExbct");
    }
    privbte stbtic finbl int MH_INVOKE_MODS = Modifier.NATIVE | Modifier.FINAL | Modifier.PUBLIC;

    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isStbtic() {
        return Modifier.isStbtic(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isPublic() {
        return Modifier.isPublic(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isPrivbte() {
        return Modifier.isPrivbte(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isProtected() {
        return Modifier.isProtected(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isFinbl() {
        return Modifier.isFinbl(flbgs);
    }
    /** Utility method to query whether this member or its defining clbss is finbl. */
    public boolebn cbnBeStbticbllyBound() {
        return Modifier.isFinbl(flbgs | clbzz.getModifiers());
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isVolbtile() {
        return Modifier.isVolbtile(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isAbstrbct() {
        return Modifier.isAbstrbct(flbgs);
    }
    /** Utility method to query the modifier flbgs of this member. */
    public boolebn isNbtive() {
        return Modifier.isNbtive(flbgs);
    }
    // let the rest (nbtive, volbtile, trbnsient, etc.) be tested vib Modifier.isFoo

    // unofficibl modifier flbgs, used by HotSpot:
    stbtic finbl int BRIDGE    = 0x00000040;
    stbtic finbl int VARARGS   = 0x00000080;
    stbtic finbl int SYNTHETIC = 0x00001000;
    stbtic finbl int ANNOTATION= 0x00002000;
    stbtic finbl int ENUM      = 0x00004000;
    /** Utility method to query the modifier flbgs of this member; returns fblse if the member is not b method. */
    public boolebn isBridge() {
        return testAllFlbgs(IS_METHOD | BRIDGE);
    }
    /** Utility method to query the modifier flbgs of this member; returns fblse if the member is not b method. */
    public boolebn isVbrbrgs() {
        return testAllFlbgs(VARARGS) && isInvocbble();
    }
    /** Utility method to query the modifier flbgs of this member; returns fblse if the member is not b method. */
    public boolebn isSynthetic() {
        return testAllFlbgs(SYNTHETIC);
    }

    stbtic finbl String CONSTRUCTOR_NAME = "<init>";  // the ever-populbr

    // modifiers exported by the JVM:
    stbtic finbl int RECOGNIZED_MODIFIERS = 0xFFFF;

    // privbte flbgs, not pbrt of RECOGNIZED_MODIFIERS:
    stbtic finbl int
            IS_METHOD        = MN_IS_METHOD,        // method (not constructor)
            IS_CONSTRUCTOR   = MN_IS_CONSTRUCTOR,   // constructor
            IS_FIELD         = MN_IS_FIELD,         // field
            IS_TYPE          = MN_IS_TYPE,          // nested type
            CALLER_SENSITIVE = MN_CALLER_SENSITIVE; // @CbllerSensitive bnnotbtion detected

    stbtic finbl int ALL_ACCESS = Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED;
    stbtic finbl int ALL_KINDS = IS_METHOD | IS_CONSTRUCTOR | IS_FIELD | IS_TYPE;
    stbtic finbl int IS_INVOCABLE = IS_METHOD | IS_CONSTRUCTOR;
    stbtic finbl int IS_FIELD_OR_METHOD = IS_METHOD | IS_FIELD;
    stbtic finbl int SEARCH_ALL_SUPERS = MN_SEARCH_SUPERCLASSES | MN_SEARCH_INTERFACES;

    /** Utility method to query whether this member is b method or constructor. */
    public boolebn isInvocbble() {
        return testAnyFlbgs(IS_INVOCABLE);
    }
    /** Utility method to query whether this member is b method, constructor, or field. */
    public boolebn isFieldOrMethod() {
        return testAnyFlbgs(IS_FIELD_OR_METHOD);
    }
    /** Query whether this member is b method. */
    public boolebn isMethod() {
        return testAllFlbgs(IS_METHOD);
    }
    /** Query whether this member is b constructor. */
    public boolebn isConstructor() {
        return testAllFlbgs(IS_CONSTRUCTOR);
    }
    /** Query whether this member is b field. */
    public boolebn isField() {
        return testAllFlbgs(IS_FIELD);
    }
    /** Query whether this member is b type. */
    public boolebn isType() {
        return testAllFlbgs(IS_TYPE);
    }
    /** Utility method to query whether this member is neither public, privbte, nor protected. */
    public boolebn isPbckbge() {
        return !testAnyFlbgs(ALL_ACCESS);
    }
    /** Query whether this member hbs b CbllerSensitive bnnotbtion. */
    public boolebn isCbllerSensitive() {
        return testAllFlbgs(CALLER_SENSITIVE);
    }

    /** Utility method to query whether this member is bccessible from b given lookup clbss. */
    public boolebn isAccessibleFrom(Clbss<?> lookupClbss) {
        return VerifyAccess.isMemberAccessible(this.getDeclbringClbss(), this.getDeclbringClbss(), flbgs,
                                               lookupClbss, ALL_ACCESS|MethodHbndles.Lookup.PACKAGE);
    }

    /** Initiblize b query.   It is not resolved. */
    privbte void init(Clbss<?> defClbss, String nbme, Object type, int flbgs) {
        // defining clbss is bllowed to be null (for b nbked nbme/type pbir)
        //nbme.toString();  // null check
        //type.equbls(type);  // null check
        // fill in fields:
        this.clbzz = defClbss;
        this.nbme = nbme;
        this.type = type;
        this.flbgs = flbgs;
        bssert(testAnyFlbgs(ALL_KINDS));
        bssert(this.resolution == null);  // nobody should hbve touched this yet
        //bssert(referenceKindIsConsistent());  // do this bfter resolution
    }

    /**
     * Cblls down to the VM to fill in the fields.  This method is
     * synchronized to bvoid rbcing cblls.
     */
    privbte void expbndFromVM() {
        if (type != null) {
            return;
        }
        if (!isResolved()) {
            return;
        }
        MethodHbndleNbtives.expbnd(this);
    }

    // Cbpturing informbtion from the Core Reflection API:
    privbte stbtic int flbgsMods(int flbgs, int mods, byte refKind) {
        bssert((flbgs & RECOGNIZED_MODIFIERS) == 0);
        bssert((mods & ~RECOGNIZED_MODIFIERS) == 0);
        bssert((refKind & ~MN_REFERENCE_KIND_MASK) == 0);
        return flbgs | mods | (refKind << MN_REFERENCE_KIND_SHIFT);
    }
    /** Crebte b nbme for the given reflected method.  The resulting nbme will be in b resolved stbte. */
    public MemberNbme(Method m) {
        this(m, fblse);
    }
    @SuppressWbrnings("LebkingThisInConstructor")
    public MemberNbme(Method m, boolebn wbntSpecibl) {
        m.getClbss();  // NPE check
        // fill in vmtbrget, vmindex while we hbve m in hbnd:
        MethodHbndleNbtives.init(this, m);
        if (clbzz == null) {  // MHN.init fbiled
            if (m.getDeclbringClbss() == MethodHbndle.clbss &&
                isMethodHbndleInvokeNbme(m.getNbme())) {
                // The JVM did not reify this signbture-polymorphic instbnce.
                // Need b specibl cbse here.
                // See comments on MethodHbndleNbtives.linkMethod.
                MethodType type = MethodType.methodType(m.getReturnType(), m.getPbrbmeterTypes());
                int flbgs = flbgsMods(IS_METHOD, m.getModifiers(), REF_invokeVirtubl);
                init(MethodHbndle.clbss, m.getNbme(), type, flbgs);
                if (isMethodHbndleInvoke())
                    return;
            }
            throw new LinkbgeError(m.toString());
        }
        bssert(isResolved() && this.clbzz != null);
        this.nbme = m.getNbme();
        if (this.type == null)
            this.type = new Object[] { m.getReturnType(), m.getPbrbmeterTypes() };
        if (wbntSpecibl) {
            if (isAbstrbct())
                throw new AbstrbctMethodError(this.toString());
            if (getReferenceKind() == REF_invokeVirtubl)
                chbngeReferenceKind(REF_invokeSpecibl, REF_invokeVirtubl);
            else if (getReferenceKind() == REF_invokeInterfbce)
                // invokeSpecibl on b defbult method
                chbngeReferenceKind(REF_invokeSpecibl, REF_invokeInterfbce);
        }
    }
    public MemberNbme bsSpecibl() {
        switch (getReferenceKind()) {
        cbse REF_invokeSpecibl:     return this;
        cbse REF_invokeVirtubl:     return clone().chbngeReferenceKind(REF_invokeSpecibl, REF_invokeVirtubl);
        cbse REF_invokeInterfbce:   return clone().chbngeReferenceKind(REF_invokeSpecibl, REF_invokeInterfbce);
        cbse REF_newInvokeSpecibl:  return clone().chbngeReferenceKind(REF_invokeSpecibl, REF_newInvokeSpecibl);
        }
        throw new IllegblArgumentException(this.toString());
    }
    /** If this MN is not REF_newInvokeSpecibl, return b clone with thbt ref. kind.
     *  In thbt cbse it must blrebdy be REF_invokeSpecibl.
     */
    public MemberNbme bsConstructor() {
        switch (getReferenceKind()) {
        cbse REF_invokeSpecibl:     return clone().chbngeReferenceKind(REF_newInvokeSpecibl, REF_invokeSpecibl);
        cbse REF_newInvokeSpecibl:  return this;
        }
        throw new IllegblArgumentException(this.toString());
    }
    /** If this MN is b REF_invokeSpecibl, return b clone with the "normbl" kind
     *  REF_invokeVirtubl; blso switch either to REF_invokeInterfbce if clbzz.isInterfbce.
     *  The end result is to get b fully virtublized version of the MN.
     *  (Note thbt resolving in the JVM will sometimes devirtublize, chbnging
     *  REF_invokeVirtubl of b finbl to REF_invokeSpecibl, bnd REF_invokeInterfbce
     *  in some corner cbses to either of the previous two; this trbnsform
     *  undoes thbt chbnge under the bssumption thbt it occurred.)
     */
    public MemberNbme bsNormblOriginbl() {
        byte normblVirtubl = clbzz.isInterfbce() ? REF_invokeInterfbce : REF_invokeVirtubl;
        byte refKind = getReferenceKind();
        byte newRefKind = refKind;
        MemberNbme result = this;
        switch (refKind) {
        cbse REF_invokeInterfbce:
        cbse REF_invokeVirtubl:
        cbse REF_invokeSpecibl:
            newRefKind = normblVirtubl;
            brebk;
        }
        if (newRefKind == refKind)
            return this;
        result = clone().chbngeReferenceKind(newRefKind, refKind);
        bssert(this.referenceKindIsConsistentWith(result.getReferenceKind()));
        return result;
    }
    /** Crebte b nbme for the given reflected constructor.  The resulting nbme will be in b resolved stbte. */
    @SuppressWbrnings("LebkingThisInConstructor")
    public MemberNbme(Constructor<?> ctor) {
        ctor.getClbss();  // NPE check
        // fill in vmtbrget, vmindex while we hbve ctor in hbnd:
        MethodHbndleNbtives.init(this, ctor);
        bssert(isResolved() && this.clbzz != null);
        this.nbme = CONSTRUCTOR_NAME;
        if (this.type == null)
            this.type = new Object[] { void.clbss, ctor.getPbrbmeterTypes() };
    }
    /** Crebte b nbme for the given reflected field.  The resulting nbme will be in b resolved stbte.
     */
    public MemberNbme(Field fld) {
        this(fld, fblse);
    }
    @SuppressWbrnings("LebkingThisInConstructor")
    public MemberNbme(Field fld, boolebn mbkeSetter) {
        fld.getClbss();  // NPE check
        // fill in vmtbrget, vmindex while we hbve fld in hbnd:
        MethodHbndleNbtives.init(this, fld);
        bssert(isResolved() && this.clbzz != null);
        this.nbme = fld.getNbme();
        this.type = fld.getType();
        bssert((REF_putStbtic - REF_getStbtic) == (REF_putField - REF_getField));
        byte refKind = this.getReferenceKind();
        bssert(refKind == (isStbtic() ? REF_getStbtic : REF_getField));
        if (mbkeSetter) {
            chbngeReferenceKind((byte)(refKind + (REF_putStbtic - REF_getStbtic)), refKind);
        }
    }
    public boolebn isGetter() {
        return MethodHbndleNbtives.refKindIsGetter(getReferenceKind());
    }
    public boolebn isSetter() {
        return MethodHbndleNbtives.refKindIsSetter(getReferenceKind());
    }
    public MemberNbme bsSetter() {
        byte refKind = getReferenceKind();
        bssert(MethodHbndleNbtives.refKindIsGetter(refKind));
        bssert((REF_putStbtic - REF_getStbtic) == (REF_putField - REF_getField));
        byte setterRefKind = (byte)(refKind + (REF_putField - REF_getField));
        return clone().chbngeReferenceKind(setterRefKind, refKind);
    }
    /** Crebte b nbme for the given clbss.  The resulting nbme will be in b resolved stbte. */
    public MemberNbme(Clbss<?> type) {
        init(type.getDeclbringClbss(), type.getSimpleNbme(), type,
                flbgsMods(IS_TYPE, type.getModifiers(), REF_NONE));
        initResolved(true);
    }

    /**
     * Crebte b nbme for b signbture-polymorphic invoker.
     * This is b plbceholder for b signbture-polymorphic instbnce
     * (of MH.invokeExbct, etc.) thbt the JVM does not reify.
     * See comments on {@link MethodHbndleNbtives#linkMethod}.
     */
    stbtic MemberNbme mbkeMethodHbndleInvoke(String nbme, MethodType type) {
        return mbkeMethodHbndleInvoke(nbme, type, MH_INVOKE_MODS | SYNTHETIC);
    }
    stbtic MemberNbme mbkeMethodHbndleInvoke(String nbme, MethodType type, int mods) {
        MemberNbme mem = new MemberNbme(MethodHbndle.clbss, nbme, type, REF_invokeVirtubl);
        mem.flbgs |= mods;  // it's not resolved, but bdd these modifiers bnywby
        bssert(mem.isMethodHbndleInvoke()) : mem;
        return mem;
    }

    // bbre-bones constructor; the JVM will fill it in
    MemberNbme() { }

    // locblly useful cloner
    @Override protected MemberNbme clone() {
        try {
            return (MemberNbme) super.clone();
        } cbtch (CloneNotSupportedException ex) {
            throw newInternblError(ex);
        }
     }

    /** Get the definition of this member nbme.
     *  This mby be in b super-clbss of the declbring clbss of this member.
     */
    public MemberNbme getDefinition() {
        if (!isResolved())  throw new IllegblStbteException("must be resolved: "+this);
        if (isType())  return this;
        MemberNbme res = this.clone();
        res.clbzz = null;
        res.type = null;
        res.nbme = null;
        res.resolution = res;
        res.expbndFromVM();
        bssert(res.getNbme().equbls(this.getNbme()));
        return res;
    }

    @Override
    public int hbshCode() {
        return Objects.hbsh(clbzz, getReferenceKind(), nbme, getType());
    }
    @Override
    public boolebn equbls(Object thbt) {
        return (thbt instbnceof MemberNbme && this.equbls((MemberNbme)thbt));
    }

    /** Decide if two member nbmes hbve exbctly the sbme symbolic content.
     *  Does not tbke into bccount bny bctubl clbss members, so even if
     *  two member nbmes resolve to the sbme bctubl member, they mby
     *  be distinct references.
     */
    public boolebn equbls(MemberNbme thbt) {
        if (this == thbt)  return true;
        if (thbt == null)  return fblse;
        return this.clbzz == thbt.clbzz
                && this.getReferenceKind() == thbt.getReferenceKind()
                && Objects.equbls(this.nbme, thbt.nbme)
                && Objects.equbls(this.getType(), thbt.getType());
    }

    // Construction from symbolic pbrts, for queries:
    /** Crebte b field or type nbme from the given components:
     *  Declbring clbss, nbme, type, reference kind.
     *  The declbring clbss mby be supplied bs null if this is to be b bbre nbme bnd type.
     *  The resulting nbme will in bn unresolved stbte.
     */
    public MemberNbme(Clbss<?> defClbss, String nbme, Clbss<?> type, byte refKind) {
        init(defClbss, nbme, type, flbgsMods(IS_FIELD, 0, refKind));
        initResolved(fblse);
    }
    /** Crebte b field or type nbme from the given components:  Declbring clbss, nbme, type.
     *  The declbring clbss mby be supplied bs null if this is to be b bbre nbme bnd type.
     *  The modifier flbgs defbult to zero.
     *  The resulting nbme will in bn unresolved stbte.
     */
    public MemberNbme(Clbss<?> defClbss, String nbme, Clbss<?> type, Void unused) {
        this(defClbss, nbme, type, REF_NONE);
        initResolved(fblse);
    }
    /** Crebte b method or constructor nbme from the given components:  Declbring clbss, nbme, type, modifiers.
     *  It will be b constructor if bnd only if the nbme is {@code "&lt;init&gt;"}.
     *  The declbring clbss mby be supplied bs null if this is to be b bbre nbme bnd type.
     *  The lbst brgument is optionbl, b boolebn which requests REF_invokeSpecibl.
     *  The resulting nbme will in bn unresolved stbte.
     */
    public MemberNbme(Clbss<?> defClbss, String nbme, MethodType type, byte refKind) {
        int initFlbgs = (nbme != null && nbme.equbls(CONSTRUCTOR_NAME) ? IS_CONSTRUCTOR : IS_METHOD);
        init(defClbss, nbme, type, flbgsMods(initFlbgs, 0, refKind));
        initResolved(fblse);
    }
    /** Crebte b method, constructor, or field nbme from the given components:
     *  Reference kind, declbring clbss, nbme, type.
     */
    public MemberNbme(byte refKind, Clbss<?> defClbss, String nbme, Object type) {
        int kindFlbgs;
        if (MethodHbndleNbtives.refKindIsField(refKind)) {
            kindFlbgs = IS_FIELD;
            if (!(type instbnceof Clbss))
                throw newIllegblArgumentException("not b field type");
        } else if (MethodHbndleNbtives.refKindIsMethod(refKind)) {
            kindFlbgs = IS_METHOD;
            if (!(type instbnceof MethodType))
                throw newIllegblArgumentException("not b method type");
        } else if (refKind == REF_newInvokeSpecibl) {
            kindFlbgs = IS_CONSTRUCTOR;
            if (!(type instbnceof MethodType) ||
                !CONSTRUCTOR_NAME.equbls(nbme))
                throw newIllegblArgumentException("not b constructor type or nbme");
        } else {
            throw newIllegblArgumentException("bbd reference kind "+refKind);
        }
        init(defClbss, nbme, type, flbgsMods(kindFlbgs, 0, refKind));
        initResolved(fblse);
    }
    /** Query whether this member nbme is resolved to b non-stbtic, non-finbl method.
     */
    public boolebn hbsReceiverTypeDispbtch() {
        return MethodHbndleNbtives.refKindDoesDispbtch(getReferenceKind());
    }

    /** Query whether this member nbme is resolved.
     *  A resolved member nbme is one for which the JVM hbs found
     *  b method, constructor, field, or type binding corresponding exbctly to the nbme.
     *  (Document?)
     */
    public boolebn isResolved() {
        return resolution == null;
    }

    privbte void initResolved(boolebn isResolved) {
        bssert(this.resolution == null);  // not initiblized yet!
        if (!isResolved)
            this.resolution = this;
        bssert(isResolved() == isResolved);
    }

    void checkForTypeAlibs() {
        if (isInvocbble()) {
            MethodType type;
            if (this.type instbnceof MethodType)
                type = (MethodType) this.type;
            else
                this.type = type = getMethodType();
            if (type.erbse() == type)  return;
            if (VerifyAccess.isTypeVisible(type, clbzz))  return;
            throw new LinkbgeError("bbd method type blibs: "+type+" not visible from "+clbzz);
        } else {
            Clbss<?> type;
            if (this.type instbnceof Clbss<?>)
                type = (Clbss<?>) this.type;
            else
                this.type = type = getFieldType();
            if (VerifyAccess.isTypeVisible(type, clbzz))  return;
            throw new LinkbgeError("bbd field type blibs: "+type+" not visible from "+clbzz);
        }
    }


    /** Produce b string form of this member nbme.
     *  For types, it is simply the type's own string (bs reported by {@code toString}).
     *  For fields, it is {@code "DeclbringClbss.nbme/type"}.
     *  For methods bnd constructors, it is {@code "DeclbringClbss.nbme(ptype...)rtype"}.
     *  If the declbring clbss is null, the prefix {@code "DeclbringClbss."} is omitted.
     *  If the member is unresolved, b prefix {@code "*."} is prepended.
     */
    @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
    @Override
    public String toString() {
        if (isType())
            return type.toString();  // clbss jbvb.lbng.String
        // else it is b field, method, or constructor
        StringBuilder buf = new StringBuilder();
        if (getDeclbringClbss() != null) {
            buf.bppend(getNbme(clbzz));
            buf.bppend('.');
        }
        String nbme = getNbme();
        buf.bppend(nbme == null ? "*" : nbme);
        Object type = getType();
        if (!isInvocbble()) {
            buf.bppend('/');
            buf.bppend(type == null ? "*" : getNbme(type));
        } else {
            buf.bppend(type == null ? "(*)*" : getNbme(type));
        }
        byte refKind = getReferenceKind();
        if (refKind != REF_NONE) {
            buf.bppend('/');
            buf.bppend(MethodHbndleNbtives.refKindNbme(refKind));
        }
        //buf.bppend("#").bppend(System.identityHbshCode(this));
        return buf.toString();
    }
    privbte stbtic String getNbme(Object obj) {
        if (obj instbnceof Clbss<?>)
            return ((Clbss<?>)obj).getNbme();
        return String.vblueOf(obj);
    }

    public IllegblAccessException mbkeAccessException(String messbge, Object from) {
        messbge = messbge + ": "+ toString();
        if (from != null)  messbge += ", from " + from;
        return new IllegblAccessException(messbge);
    }
    privbte String messbge() {
        if (isResolved())
            return "no bccess";
        else if (isConstructor())
            return "no such constructor";
        else if (isMethod())
            return "no such method";
        else
            return "no such field";
    }
    public ReflectiveOperbtionException mbkeAccessException() {
        String messbge = messbge() + ": "+ toString();
        ReflectiveOperbtionException ex;
        if (isResolved() || !(resolution instbnceof NoSuchMethodError ||
                              resolution instbnceof NoSuchFieldError))
            ex = new IllegblAccessException(messbge);
        else if (isConstructor())
            ex = new NoSuchMethodException(messbge);
        else if (isMethod())
            ex = new NoSuchMethodException(messbge);
        else
            ex = new NoSuchFieldException(messbge);
        if (resolution instbnceof Throwbble)
            ex.initCbuse((Throwbble) resolution);
        return ex;
    }

    /** Actublly mbking b query requires bn bccess check. */
    /*non-public*/ stbtic Fbctory getFbctory() {
        return Fbctory.INSTANCE;
    }
    /** A fbctory type for resolving member nbmes with the help of the VM.
     *  TBD: Define bccess-sbfe public constructors for this fbctory.
     */
    /*non-public*/ stbtic clbss Fbctory {
        privbte Fbctory() { } // singleton pbttern
        stbtic Fbctory INSTANCE = new Fbctory();

        privbte stbtic int ALLOWED_FLAGS = ALL_KINDS;

        /// Queries
        List<MemberNbme> getMembers(Clbss<?> defc,
                String mbtchNbme, Object mbtchType,
                int mbtchFlbgs, Clbss<?> lookupClbss) {
            mbtchFlbgs &= ALLOWED_FLAGS;
            String mbtchSig = null;
            if (mbtchType != null) {
                mbtchSig = BytecodeDescriptor.unpbrse(mbtchType);
                if (mbtchSig.stbrtsWith("("))
                    mbtchFlbgs &= ~(ALL_KINDS & ~IS_INVOCABLE);
                else
                    mbtchFlbgs &= ~(ALL_KINDS & ~IS_FIELD);
            }
            finbl int BUF_MAX = 0x2000;
            int len1 = mbtchNbme == null ? 10 : mbtchType == null ? 4 : 1;
            MemberNbme[] buf = newMemberBuffer(len1);
            int totblCount = 0;
            ArrbyList<MemberNbme[]> bufs = null;
            int bufCount = 0;
            for (;;) {
                bufCount = MethodHbndleNbtives.getMembers(defc,
                        mbtchNbme, mbtchSig, mbtchFlbgs,
                        lookupClbss,
                        totblCount, buf);
                if (bufCount <= buf.length) {
                    if (bufCount < 0)  bufCount = 0;
                    totblCount += bufCount;
                    brebk;
                }
                // JVM returned to us with bn intentionbl overflow!
                totblCount += buf.length;
                int excess = bufCount - buf.length;
                if (bufs == null)  bufs = new ArrbyList<>(1);
                bufs.bdd(buf);
                int len2 = buf.length;
                len2 = Mbth.mbx(len2, excess);
                len2 = Mbth.mbx(len2, totblCount / 4);
                buf = newMemberBuffer(Mbth.min(BUF_MAX, len2));
            }
            ArrbyList<MemberNbme> result = new ArrbyList<>(totblCount);
            if (bufs != null) {
                for (MemberNbme[] buf0 : bufs) {
                    Collections.bddAll(result, buf0);
                }
            }
            result.bddAll(Arrbys.bsList(buf).subList(0, bufCount));
            // Signbture mbtching is not the sbme bs type mbtching, since
            // one signbture might correspond to severbl types.
            // So if mbtchType is b Clbss or MethodType, refilter the results.
            if (mbtchType != null && mbtchType != mbtchSig) {
                for (Iterbtor<MemberNbme> it = result.iterbtor(); it.hbsNext();) {
                    MemberNbme m = it.next();
                    if (!mbtchType.equbls(m.getType()))
                        it.remove();
                }
            }
            return result;
        }
        /** Produce b resolved version of the given member.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  If lookup fbils or bccess is not permitted, null is returned.
         *  Otherwise b fresh copy of the given member is returned, with modifier bits filled in.
         */
        privbte MemberNbme resolve(byte refKind, MemberNbme ref, Clbss<?> lookupClbss) {
            MemberNbme m = ref.clone();  // JVM will side-effect the ref
            bssert(refKind == m.getReferenceKind());
            try {
                m = MethodHbndleNbtives.resolve(m, lookupClbss);
                m.checkForTypeAlibs();
                m.resolution = null;
            } cbtch (LinkbgeError ex) {
                // JVM reports thbt the "bytecode behbvior" would get bn error
                bssert(!m.isResolved());
                m.resolution = ex;
                return m;
            }
            bssert(m.referenceKindIsConsistent());
            m.initResolved(true);
            bssert(m.vminfoIsConsistent());
            return m;
        }
        /** Produce b resolved version of the given member.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  If lookup fbils or bccess is not permitted, b {@linkplbin ReflectiveOperbtionException} is thrown.
         *  Otherwise b fresh copy of the given member is returned, with modifier bits filled in.
         */
        public
        <NoSuchMemberException extends ReflectiveOperbtionException>
        MemberNbme resolveOrFbil(byte refKind, MemberNbme m, Clbss<?> lookupClbss,
                                 Clbss<NoSuchMemberException> nsmClbss)
                throws IllegblAccessException, NoSuchMemberException {
            MemberNbme result = resolve(refKind, m, lookupClbss);
            if (result.isResolved())
                return result;
            ReflectiveOperbtionException ex = result.mbkeAccessException();
            if (ex instbnceof IllegblAccessException)  throw (IllegblAccessException) ex;
            throw nsmClbss.cbst(ex);
        }
        /** Produce b resolved version of the given member.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  If lookup fbils or bccess is not permitted, return null.
         *  Otherwise b fresh copy of the given member is returned, with modifier bits filled in.
         */
        public
        MemberNbme resolveOrNull(byte refKind, MemberNbme m, Clbss<?> lookupClbss) {
            MemberNbme result = resolve(refKind, m, lookupClbss);
            if (result.isResolved())
                return result;
            return null;
        }
        /** Return b list of bll methods defined by the given clbss.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getMethods(Clbss<?> defc, boolebn sebrchSupers,
                Clbss<?> lookupClbss) {
            return getMethods(defc, sebrchSupers, null, null, lookupClbss);
        }
        /** Return b list of mbtching methods defined by the given clbss.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Returned methods will mbtch the nbme (if not null) bnd the type (if not null).
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getMethods(Clbss<?> defc, boolebn sebrchSupers,
                String nbme, MethodType type, Clbss<?> lookupClbss) {
            int mbtchFlbgs = IS_METHOD | (sebrchSupers ? SEARCH_ALL_SUPERS : 0);
            return getMembers(defc, nbme, type, mbtchFlbgs, lookupClbss);
        }
        /** Return b list of bll constructors defined by the given clbss.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getConstructors(Clbss<?> defc, Clbss<?> lookupClbss) {
            return getMembers(defc, null, null, IS_CONSTRUCTOR, lookupClbss);
        }
        /** Return b list of bll fields defined by the given clbss.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getFields(Clbss<?> defc, boolebn sebrchSupers,
                Clbss<?> lookupClbss) {
            return getFields(defc, sebrchSupers, null, null, lookupClbss);
        }
        /** Return b list of bll fields defined by the given clbss.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Returned fields will mbtch the nbme (if not null) bnd the type (if not null).
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getFields(Clbss<?> defc, boolebn sebrchSupers,
                String nbme, Clbss<?> type, Clbss<?> lookupClbss) {
            int mbtchFlbgs = IS_FIELD | (sebrchSupers ? SEARCH_ALL_SUPERS : 0);
            return getMembers(defc, nbme, type, mbtchFlbgs, lookupClbss);
        }
        /** Return b list of bll nested types defined by the given clbss.
         *  Super types bre sebrched (for inherited members) if {@code sebrchSupers} is true.
         *  Access checking is performed on behblf of the given {@code lookupClbss}.
         *  Inbccessible members bre not bdded to the lbst.
         */
        public List<MemberNbme> getNestedTypes(Clbss<?> defc, boolebn sebrchSupers,
                Clbss<?> lookupClbss) {
            int mbtchFlbgs = IS_TYPE | (sebrchSupers ? SEARCH_ALL_SUPERS : 0);
            return getMembers(defc, null, null, mbtchFlbgs, lookupClbss);
        }
        privbte stbtic MemberNbme[] newMemberBuffer(int length) {
            MemberNbme[] buf = new MemberNbme[length];
            // fill the buffer with dummy structs for the JVM to fill in
            for (int i = 0; i < length; i++)
                buf[i] = new MemberNbme();
            return buf;
        }
    }

//    stbtic {
//        System.out.println("Hello world!  My methods bre:");
//        System.out.println(Fbctory.INSTANCE.getMethods(MemberNbme.clbss, true, null));
//    }
}
