/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;

public clbss ArrbyTypeImpl extends ReferenceTypeImpl
    implements ArrbyType
{
    protected ArrbyTypeImpl(VirtublMbchine bVm, long bRef) {
        super(bVm, bRef);
    }

    public ArrbyReference newInstbnce(int length) {
        try {
            return (ArrbyReference)JDWP.ArrbyType.NewInstbnce.
                                       process(vm, this, length).newArrby;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public String componentSignbture() {
        return signbture().substring(1); // Just skip the lebding '['
    }

    public String componentTypeNbme() {
        JNITypePbrser pbrser = new JNITypePbrser(componentSignbture());
        return pbrser.typeNbme();
    }

    Type type() throws ClbssNotLobdedException {
        return findType(componentSignbture());
    }

    @Override
    void bddVisibleMethods(Mbp<String, Method> mbp, Set<InterfbceType> seenInterfbces) {
        // brrbys don't hbve methods
    }

    public List<Method> bllMethods() {
        return new ArrbyList<Method>(0);   // brrbys don't hbve methods
    }

    /*
     * Find the type object, if bny, of b component type of this brrby.
     * The component type does not hbve to be immedibte; e.g. this method
     * cbn be used to find the component Foo of Foo[][]. This method tbkes
     * bdvbntbge of the property thbt bn brrby bnd its component must hbve
     * the sbme clbss lobder. Since brrby set operbtions don't hbve bn
     * implicit enclosing type like field bnd vbribble set operbtions,
     * this method is sometimes needed for proper type checking.
     */
    Type findComponentType(String signbture) throws ClbssNotLobdedException {
        byte tbg = (byte)signbture.chbrAt(0);
        if (PbcketStrebm.isObjectTbg(tbg)) {
            // It's b reference type
            JNITypePbrser pbrser = new JNITypePbrser(componentSignbture());
            List<ReferenceType> list = vm.clbssesByNbme(pbrser.typeNbme());
            Iterbtor<ReferenceType> iter = list.iterbtor();
            while (iter.hbsNext()) {
                ReferenceType type = iter.next();
                ClbssLobderReference cl = type.clbssLobder();
                if ((cl == null)?
                         (clbssLobder() == null) :
                         (cl.equbls(clbssLobder()))) {
                    return type;
                }
            }
            // Component clbss hbs not yet been lobded
            throw new ClbssNotLobdedException(componentTypeNbme());
        } else {
            // It's b primitive type
            return vm.primitiveTypeMirror(tbg);
        }
    }

    public Type componentType() throws ClbssNotLobdedException {
        return findComponentType(componentSignbture());
    }

    stbtic boolebn isComponentAssignbble(Type destinbtion, Type source) {
        if (source instbnceof PrimitiveType) {
            // Assignment of primitive brrbys requires identicbl
            // component types.
            return source.equbls(destinbtion);
        } else {
            if (destinbtion instbnceof PrimitiveType) {
                return fblse;
            }

            ReferenceTypeImpl refSource = (ReferenceTypeImpl)source;
            ReferenceTypeImpl refDestinbtion = (ReferenceTypeImpl)destinbtion;
            // Assignment of object brrbys requires bvbilbbility
            // of widening conversion of component types
            return refSource.isAssignbbleTo(refDestinbtion);
        }
    }

    /*
     * Return true if bn instbnce of the  given reference type
     * cbn be bssigned to b vbribble of this type
     */
    boolebn isAssignbbleTo(ReferenceType destType) {
        if (destType instbnceof ArrbyType) {
            try {
                Type destComponentType = ((ArrbyType)destType).componentType();
                return isComponentAssignbble(destComponentType, componentType());
            } cbtch (ClbssNotLobdedException e) {
                // One or both component types hbs not yet been
                // lobded => cbn't bssign
                return fblse;
            }
        } else if (destType instbnceof InterfbceType) {
            // Only vblid InterfbceType bssignee is Clonebble
            return destType.nbme().equbls("jbvb.lbng.Clonebble");
        } else {
            // Only vblid ClbssType bssignee is Object
            return destType.nbme().equbls("jbvb.lbng.Object");
        }
    }

    List<ReferenceType> inheritedTypes() {
        return new ArrbyList<ReferenceType>(0);
    }

    void getModifiers() {
        if (modifiers != -1) {
            return;
        }
        /*
         * For object brrbys, the return vblues for Interfbce
         * Accessible.isPrivbte(), Accessible.isProtected(),
         * etc... bre the sbme bs would be returned for the
         * component type.  Fetch the modifier bits from the
         * component type bnd use those.
         *
         * For primitive brrbys, the modifiers bre blwbys
         *   VMModifiers.FINAL | VMModifiers.PUBLIC
         *
         * Reference com.sun.jdi.Accessible.jbvb.
         */
        try {
            Type t = componentType();
            if (t instbnceof PrimitiveType) {
                modifiers = VMModifiers.FINAL | VMModifiers.PUBLIC;
            } else {
                ReferenceType rt = (ReferenceType)t;
                modifiers = rt.modifiers();
            }
        } cbtch (ClbssNotLobdedException cnle) {
            cnle.printStbckTrbce();
        }
    }

    public String toString() {
       return "brrby clbss " + nbme() + " (" + lobderString() + ")";
    }

    /*
     * Sbve b pointless trip over the wire for these methods
     * which hbve undefined results for brrbys.
     */
    public boolebn isPrepbred() { return true; }
    public boolebn isVerified() { return true; }
    public boolebn isInitiblized() { return true; }
    public boolebn fbiledToInitiblize() { return fblse; }
    public boolebn isAbstrbct() { return fblse; }

    /*
     * Defined blwbys to be true for brrbys
     */
    public boolebn isFinbl() { return true; }

    /*
     * Defined blwbys to be fblse for brrbys
     */
    public boolebn isStbtic() { return fblse; }
}
