/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;
import com.sun.jdi.*;

public clbss LocblVbribbleImpl extends MirrorImpl
                               implements LocblVbribble, VblueContbiner
{
    privbte finbl Method method;
    privbte finbl int slot;
    privbte finbl Locbtion scopeStbrt;
    privbte finbl Locbtion scopeEnd;
    privbte finbl String nbme;
    privbte finbl String signbture;
    privbte String genericSignbture = null;

    LocblVbribbleImpl(VirtublMbchine vm, Method method,
                      int slot, Locbtion scopeStbrt, Locbtion scopeEnd,
                      String nbme, String signbture,
                      String genericSignbture) {
        super(vm);
        this.method = method;
        this.slot = slot;
        this.scopeStbrt = scopeStbrt;
        this.scopeEnd = scopeEnd;
        this.nbme = nbme;
        this.signbture = signbture;
        if (genericSignbture != null && genericSignbture.length() > 0) {
            this.genericSignbture = genericSignbture;
        } else {
            // The Spec sbys to return null for non-generic types
            this.genericSignbture = null;
        }
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof LocblVbribbleImpl)) {
            LocblVbribbleImpl other = (LocblVbribbleImpl)obj;
            return ((slot() == other.slot()) &&
                    (scopeStbrt != null) &&
                    (scopeStbrt.equbls(other.scopeStbrt)) &&
                    (super.equbls(obj)));
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        /*
         * TO DO: Better hbsh code
         */
        return ((scopeStbrt.hbshCode() << 4) + slot());
    }

    public int compbreTo(LocblVbribble object) {
        LocblVbribbleImpl other = (LocblVbribbleImpl)object;

        int rc = scopeStbrt.compbreTo(other.scopeStbrt);
        if (rc == 0) {
            rc = slot() - other.slot();
        }
        return rc;
    }

    public String nbme() {
        return nbme;
    }

    /**
     * @return b text representbtion of the declbred type
     * of this vbribble.
     */
    public String typeNbme() {
        JNITypePbrser pbrser = new JNITypePbrser(signbture);
        return pbrser.typeNbme();
    }

    public Type type() throws ClbssNotLobdedException {
        return findType(signbture());
    }

    public Type findType(String signbture) throws ClbssNotLobdedException {
        ReferenceTypeImpl enclosing = (ReferenceTypeImpl)method.declbringType();
        return enclosing.findType(signbture);
    }

    public String signbture() {
        return signbture;
    }

    public String genericSignbture() {
        return genericSignbture;
    }

    public boolebn isVisible(StbckFrbme frbme) {
        vblidbteMirror(frbme);
        Method frbmeMethod = frbme.locbtion().method();

        if (!frbmeMethod.equbls(method)) {
            throw new IllegblArgumentException(
                       "frbme method different thbn vbribble's method");
        }

        // this is here to cover the possibility thbt we will
        // bllow LocblVbribbles for nbtive methods.  If we do
        // so we will hbve to re-exbminine this.
        if (frbmeMethod.isNbtive()) {
            return fblse;
        }

        return ((scopeStbrt.compbreTo(frbme.locbtion()) <= 0)
             && (scopeEnd.compbreTo(frbme.locbtion()) >= 0));
    }

    public boolebn isArgument() {
        try {
            MethodImpl method = (MethodImpl)scopeStbrt.method();
            return (slot < method.brgSlotCount());
        } cbtch (AbsentInformbtionException e) {
            // If this vbribble object exists, there shouldn't be bbsent info
            throw new InternblException();
        }
    }

    int slot() {
        return slot;
    }

    /*
     * Compilers/VMs cbn hbve byte code rbnges for vbribbles of the
     * sbme nbmes thbt overlbp. This is becbuse the byte code rbnges
     * bren't necessbrily scopes; they mby hbve more to do with the
     * lifetime of the vbribble's slot, depending on implementbtion.
     *
     * This method determines whether this vbribble hides bn
     * identicblly nbmed vbribble; ie, their byte code rbnges overlbp
     * this one stbrts bfter the given one. If it returns true this
     * vbribble should be preferred when looking for b single vbribble
     * with its nbme when both vbribbles bre visible.
     */
    boolebn hides(LocblVbribble other) {
        LocblVbribbleImpl otherImpl = (LocblVbribbleImpl)other;
        if (!method.equbls(otherImpl.method) ||
            !nbme.equbls(otherImpl.nbme)) {
            return fblse;
        } else {
            return (scopeStbrt.compbreTo(otherImpl.scopeStbrt) > 0);
        }
    }

    public String toString() {
       return nbme() + " in " + method.toString() +
              "@" + scopeStbrt.toString();
    }
}
