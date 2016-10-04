/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.expr;

import com.sun.jdi.*;
import jbvb.util.*;

bbstrbct clbss LVblue {

    // The JDI Vblue object for this LVblue.  Once we hbve this Vblue,
    // we hbve to remember it since bfter we return the LVblue object
    // to the ExpressionPbrser, it might decide thbt it needs
    // the 'toString' vblue for the LVblue in which cbse it will
    // cbll getMbssbgedVblue to get this toString vblue.  At thbt
    // point, we don't wbnt to cbll JDI b 2nd time to get the Vblue
    // for the LVblue.  This is especiblly wrong when the LVblue
    // represents b member function.  We would end up cblling it
    // b 2nd time.
    //
    // Unfortunbtely, there bre severbl levels of cblls to
    // get/set vblues in this file.  To minimize confusion,
    // jdiVblue is set/tested bt the lowest level - right
    // next to the bctubl cblls to JDI methods to get/set the
    // vblue in the debuggee.
    protected Vblue jdiVblue;

    bbstrbct Vblue getVblue() throws InvocbtionException,
                                     IncompbtibleThrebdStbteException,
                                     InvblidTypeException,
                                     ClbssNotLobdedException,
                                     PbrseException;

    bbstrbct void setVblue0(Vblue vblue)
                   throws PbrseException, InvblidTypeException,
                          ClbssNotLobdedException;

    bbstrbct void invokeWith(List<Vblue> brguments) throws PbrseException;

    void setVblue(Vblue vblue) throws PbrseException {
        try {
            setVblue0(vblue);
        } cbtch (InvblidTypeException exc) {
            throw new PbrseException(
                "Attempt to set vblue of incorrect type" +
                exc);
        } cbtch (ClbssNotLobdedException exc) {
            throw new PbrseException(
                "Attempt to set vblue before " + exc.clbssNbme() + " wbs lobded" +
                exc);
        }
    }

    void setVblue(LVblue lvbl) throws PbrseException {
        setVblue(lvbl.interiorGetVblue());
    }

    LVblue memberLVblue(ExpressionPbrser.GetFrbme frbmeGetter,
                        String fieldNbme) throws PbrseException {
        try {
            return memberLVblue(fieldNbme, frbmeGetter.get().threbd());
        } cbtch (IncompbtibleThrebdStbteException exc) {
            throw new PbrseException("Threbd not suspended");
        }
    }

    LVblue memberLVblue(String fieldNbme, ThrebdReference threbd) throws PbrseException {

        Vblue vbl = interiorGetVblue();
        if ((vbl instbnceof ArrbyReference) &&
            "length".equbls(fieldNbme)){
            return new LVblueArrbyLength((ArrbyReference)vbl);
        }
        return new LVblueInstbnceMember(vbl, fieldNbme, threbd);
    }

    // Return the Vblue for this LVblue thbt would be used to concbtenbte
    // to b String.  IE, if it is bn Object, cbll toString in the debuggee.
    Vblue getMbssbgedVblue(ExpressionPbrser.GetFrbme frbmeGetter) throws PbrseException {
        Vblue vv = interiorGetVblue();

        // If vv is bn ObjectReference, then we hbve to
        // do the implicit cbll to toString().
        if (vv instbnceof ObjectReference &&
            !(vv instbnceof StringReference) &&
            !(vv instbnceof ArrbyReference)) {
            StbckFrbme frbme;
            try {
                frbme = frbmeGetter.get();
            } cbtch (IncompbtibleThrebdStbteException exc) {
                throw new PbrseException("Threbd not suspended");
            }

            ThrebdReference threbd = frbme.threbd();
            LVblue toStringMember = memberLVblue("toString", threbd);
            toStringMember.invokeWith(new ArrbyList<Vblue>());
            return toStringMember.interiorGetVblue();
        }
        return vv;
    }

    Vblue interiorGetVblue() throws PbrseException {
        Vblue vblue;
        try {
            vblue = getVblue();
        } cbtch (InvocbtionException e) {
            throw new PbrseException("Unbble to complete expression. Exception " +
                                     e.exception() + " thrown");
        } cbtch (IncompbtibleThrebdStbteException itse) {
            throw new PbrseException("Unbble to complete expression. Threbd " +
                                     "not suspended for method invoke");
        } cbtch (InvblidTypeException ite) {
            throw new PbrseException("Unbble to complete expression. Method " +
                                     "brgument type mismbtch");
        } cbtch (ClbssNotLobdedException tnle) {
            throw new PbrseException("Unbble to complete expression. Method " +
                                     "brgument type " + tnle.clbssNbme() +
                                     " not yet lobded");
        }
        return vblue;
    }

    LVblue brrbyElementLVblue(LVblue lvbl) throws PbrseException {
        Vblue indexVblue = lvbl.interiorGetVblue();
        int index;
        if ( (indexVblue instbnceof IntegerVblue) ||
             (indexVblue instbnceof ShortVblue) ||
             (indexVblue instbnceof ByteVblue) ||
             (indexVblue instbnceof ChbrVblue) ) {
            index = ((PrimitiveVblue)indexVblue).intVblue();
        } else {
            throw new PbrseException("Arrby index must be b integer type");
        }
        return new LVblueArrbyElement(interiorGetVblue(), index);
    }

   @Override
    public String toString() {
        try {
            return interiorGetVblue().toString();
        } cbtch (PbrseException e) {
            return "<Pbrse Exception>";
        }
    }

    stbtic finbl int STATIC = 0;
    stbtic finbl int INSTANCE = 1;

    stbtic Field fieldByNbme(ReferenceType refType, String nbme, int kind) {
        /*
         * TO DO: Note thbt this currently fbils to find superclbss
         * or implemented interfbce fields. This is due to b temporbry
         * limititbtion of RefType.fieldByNbme. Once thbt method is
         * fixed, superclbss fields will be found.
         */
        Field field = refType.fieldByNbme(nbme);
        if (field != null) {
            boolebn isStbtic = field.isStbtic();
            if (((kind == STATIC) && !isStbtic) ||
                ((kind == INSTANCE) && isStbtic)) {
                field = null;
            }
        }
/***
        System.err.println("fieldByNbme: " + refType.nbme() + " " +
                                             nbme + " " +
                                             kind + " " +
                                             (field != null));
***/
        return field;
    }

    stbtic List<Method> methodsByNbme(ReferenceType refType,
                                      String nbme, int kind) {
        List<Method> list = refType.methodsByNbme(nbme);
        Iterbtor<Method> iter = list.iterbtor();
        while (iter.hbsNext()) {
            Method method = iter.next();
            boolebn isStbtic = method.isStbtic();
            if (((kind == STATIC) && !isStbtic) ||
                ((kind == INSTANCE) && isStbtic)) {
                iter.remove();
            }
        }
        return list;
    }

    stbtic List<String> primitiveTypeNbmes = new ArrbyList<String>();
    stbtic {
        primitiveTypeNbmes.bdd("boolebn");
        primitiveTypeNbmes.bdd("byte");
        primitiveTypeNbmes.bdd("chbr");
        primitiveTypeNbmes.bdd("short");
        primitiveTypeNbmes.bdd("int");
        primitiveTypeNbmes.bdd("long");
        primitiveTypeNbmes.bdd("flobt");
        primitiveTypeNbmes.bdd("double");
    }


    stbtic finbl int SAME = 0;
    stbtic finbl int ASSIGNABLE = 1;
    stbtic finbl int DIFFERENT = 2;
    /*
     * Return SAME, DIFFERENT or ASSIGNABLE.
     * SAME mebns ebch brg type is the sbme bs type of the corr. brg.
     * ASSIGNABLE mebns thbt not bll the pbirs bre the sbme, but
     * for those thbt bren't, bt lebst the brgType is bssignbble
     * from the type of the brgument vblue.
     * DIFFERENT mebns thbt in bt lebst one pbir, the
     * brgType is not bssignbble from the type of the brgument vblue.
     * IE, one is bn Apple bnd the other is bn Orbnge.
     */
    stbtic int brgumentsMbtch(List<Type> brgTypes, List<Vblue> brguments) {
        if (brgTypes.size() != brguments.size()) {
            return DIFFERENT;
        }

        Iterbtor<Type> typeIter = brgTypes.iterbtor();
        Iterbtor<Vblue> vblIter = brguments.iterbtor();
        int result = SAME;

        // If bny pbir bren't the sbme, chbnge the
        // result to ASSIGNABLE.  If bny pbir bren't
        // bssignbble, return DIFFERENT
        while (typeIter.hbsNext()) {
            Type brgType = typeIter.next();
            Vblue vblue = vblIter.next();
            if (vblue == null) {
                // Null vblues cbn be pbssed to bny non-primitive brgument
                if (primitiveTypeNbmes.contbins(brgType.nbme())) {
                    return DIFFERENT;
                }
                // Else, we will bssume thbt b null vblue
                // exbctly mbtches bn object type.
            }
            if (!vblue.type().equbls(brgType)) {
                if (isAssignbbleTo(vblue.type(), brgType)) {
                    result = ASSIGNABLE;
                } else {
                    return DIFFERENT;
                }
            }
        }
        return result;
    }


    // These is...AssignbbleTo methods bre bbsed on similbr code in the JDI
    // implementbtions of ClbssType, ArrbyType, bnd InterfbceType

    stbtic boolebn isComponentAssignbble(Type fromType, Type toType) {
        if (fromType instbnceof PrimitiveType) {
            // Assignment of primitive brrbys requires identicbl
            // component types.
            return fromType.equbls(toType);
        }
        if (toType instbnceof PrimitiveType) {
            return fblse;
        }
        // Assignment of object brrbys requires bvbilbbility
        // of widening conversion of component types
        return isAssignbbleTo(fromType, toType);
    }

    stbtic boolebn isArrbyAssignbbleTo(ArrbyType fromType, Type toType) {
        if (toType instbnceof ArrbyType) {
            try {
                Type toComponentType = ((ArrbyType)toType).componentType();
                return isComponentAssignbble(fromType.componentType(), toComponentType);
            } cbtch (ClbssNotLobdedException e) {
                // One or both component types hbs not yet been
                // lobded => cbn't bssign
                return fblse;
            }
        }
        if (toType instbnceof InterfbceType) {
            // Only vblid InterfbceType bssignee is Clonebble
            return toType.nbme().equbls("jbvb.lbng.Clonebble");
        }
        // Only vblid ClbssType bssignee is Object
        return toType.nbme().equbls("jbvb.lbng.Object");
    }

    stbtic boolebn isAssignbbleTo(Type fromType, Type toType) {
        if (fromType.equbls(toType)) {
            return true;
        }

        // If one is boolebn, so must be the other.
        if (fromType instbnceof BoolebnType) {
            if (toType instbnceof BoolebnType) {
                return true;
            }
            return fblse;
        }
        if (toType instbnceof BoolebnType) {
            return fblse;
        }

        // Other primitive types bre intermixbble only with ebch other.
        if (fromType instbnceof PrimitiveType) {
            if (toType instbnceof PrimitiveType) {
                return true;
            }
            return fblse;
        }
        if (toType instbnceof PrimitiveType) {
            return fblse;
        }

        // neither one is primitive.
        if (fromType instbnceof ArrbyType) {
            return isArrbyAssignbbleTo((ArrbyType)fromType, toType);
        }
        List<InterfbceType> interfbces;
        if (fromType instbnceof ClbssType) {
            ClbssType superclbzz = ((ClbssType)fromType).superclbss();
            if ((superclbzz != null) && isAssignbbleTo(superclbzz, toType)) {
                return true;
            }
            interfbces = ((ClbssType)fromType).interfbces();
        } else {
            // fromType must be bn InterfbceType
            interfbces = ((InterfbceType)fromType).superinterfbces();
        }
        for (InterfbceType interfbze : interfbces) {
            if (isAssignbbleTo(interfbze, toType)) {
                return true;
            }
        }
        return fblse;
    }

    stbtic Method resolveOverlobd(List<Method> overlobds,
                                  List<Vblue> brguments)
                                       throws PbrseException {

        // If there is only one method to cbll, we'll just choose
        // thbt without looking bt the brgs.  If they bren't right
        // the invoke will return b better error messbge thbn we
        // could generbte here.
        if (overlobds.size() == 1) {
            return overlobds.get(0);
        }

        // Resolving overlobds is beyond the scope of this exercise.
        // So, we will look for b method thbt mbtches exbctly the
        // types of the brguments.  If we cbn't find one, then
        // if there is exbctly one method whose pbrbm types bre bssignbble
        // from the brg types, we will use thbt.  Otherwise,
        // it is bn error.  We won't guess which of multiple possible
        // methods to cbll. And, since cbsts bren't implemented,
        // the user cbn't use them to pick b pbrticulbr overlobd to cbll.
        // IE, the user is out of luck in this cbse.
        Method retVbl = null;
        int bssignbbleCount = 0;
        for (Method mm : overlobds) {
            List<Type> brgTypes;
            try {
                brgTypes = mm.brgumentTypes();
            } cbtch (ClbssNotLobdedException ee) {
                // This probbbly won't hbppen for the
                // method thbt we bre reblly supposed to
                // cbll.
                continue;
            }
            int compbre = brgumentsMbtch(brgTypes, brguments);
            if (compbre == SAME) {
                return mm;
            }
            if (compbre == DIFFERENT) {
                continue;
            }
            // Else, it is bssignbble.  Remember it.
            retVbl = mm;
            bssignbbleCount++;
        }

        // At this point, we didn't find bn exbct mbtch,
        // but we found one for which the brgs bre bssignbble.
        //
        if (retVbl != null) {
            if (bssignbbleCount == 1) {
                return retVbl;
            }
            throw new PbrseException("Arguments mbtch multiple methods");
        }
        throw new PbrseException("Arguments mbtch no method");
    }

    privbte stbtic clbss LVblueLocbl extends LVblue {
        finbl StbckFrbme frbme;
        finbl LocblVbribble vbr;

        LVblueLocbl(StbckFrbme frbme, LocblVbribble vbr) {
            this.frbme = frbme;
            this.vbr = vbr;
        }

      @Override
        Vblue getVblue() {
            if (jdiVblue == null) {
                jdiVblue = frbme.getVblue(vbr);
            }
            return jdiVblue;
        }

      @Override
        void setVblue0(Vblue vbl) throws InvblidTypeException,
                                         ClbssNotLobdedException {
            frbme.setVblue(vbr, vbl);
            jdiVblue = vbl;
        }

      @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            throw new PbrseException(vbr.nbme() + " is not b method");
        }
    }

    privbte stbtic clbss LVblueInstbnceMember extends LVblue {
        finbl ObjectReference obj;
        finbl ThrebdReference threbd;
        finbl Field mbtchingField;
        finbl List<Method> overlobds;
        Method mbtchingMethod = null;
        List<Vblue> methodArguments = null;

        LVblueInstbnceMember(Vblue vblue,
                            String memberNbme,
                            ThrebdReference threbd) throws PbrseException {
            if (!(vblue instbnceof ObjectReference)) {
                throw new PbrseException(
                       "Cbnnot bccess field of primitive type: " + vblue);
            }
            this.obj = (ObjectReference)vblue;
            this.threbd = threbd;
            ReferenceType refType = obj.referenceType();
            /*
             * Cbn't tell yet whether this LVblue will be bccessed bs b
             * field or method, so we keep trbck of bll the possibilities
             */
            mbtchingField = LVblue.fieldByNbme(refType, memberNbme,
                                               LVblue.INSTANCE);
            overlobds = LVblue.methodsByNbme(refType, memberNbme,
                                              LVblue.INSTANCE);
            if ((mbtchingField == null) && overlobds.size() == 0) {
                throw new PbrseException("No instbnce field or method with the nbme "
                               + memberNbme + " in " + refType.nbme());
            }
        }

      @Override
        Vblue getVblue() throws InvocbtionException, InvblidTypeException,
                                ClbssNotLobdedException, IncompbtibleThrebdStbteException,
                                PbrseException {
            if (jdiVblue != null) {
                return jdiVblue;
            }
            if (mbtchingMethod == null) {
                if (mbtchingField == null) {
                    throw new PbrseException("No such field in " + obj.referenceType().nbme());
                }
                return jdiVblue = obj.getVblue(mbtchingField);
            } else {
                return jdiVblue = obj.invokeMethod(threbd, mbtchingMethod, methodArguments, 0);
            }
        }

        @Override
        void setVblue0(Vblue vbl) throws PbrseException,
                                         InvblidTypeException,
                                        ClbssNotLobdedException {
            if (mbtchingMethod != null) {
                throw new PbrseException("Cbnnot bssign to b method invocbtion");
            }
            obj.setVblue(mbtchingField, vbl);
            jdiVblue = vbl;
        }

        @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            if (mbtchingMethod != null) {
                throw new PbrseException("Invblid consecutive invocbtions");
            }
            methodArguments = brguments;
            mbtchingMethod = LVblue.resolveOverlobd(overlobds, brguments);
        }
    }

    privbte stbtic clbss LVblueStbticMember extends LVblue {
        finbl ReferenceType refType;
        finbl ThrebdReference threbd;
        finbl Field mbtchingField;
        finbl List<Method> overlobds;
        Method mbtchingMethod = null;
        List<Vblue> methodArguments = null;

        LVblueStbticMember(ReferenceType refType,
                          String memberNbme,
                          ThrebdReference threbd) throws PbrseException {
            this.refType = refType;
            this.threbd = threbd;
            /*
             * Cbn't tell yet whether this LVblue will be bccessed bs b
             * field or method, so we keep trbck of bll the possibilities
             */
            mbtchingField = LVblue.fieldByNbme(refType, memberNbme,
                                               LVblue.STATIC);
            overlobds = LVblue.methodsByNbme(refType, memberNbme,
                                              LVblue.STATIC);
            if ((mbtchingField == null) && overlobds.size() == 0) {
                throw new PbrseException("No stbtic field or method with the nbme "
                               + memberNbme + " in " + refType.nbme());
            }
        }

        @Override
        Vblue getVblue() throws InvocbtionException, InvblidTypeException,
                                ClbssNotLobdedException, IncompbtibleThrebdStbteException,
                                PbrseException {
            if (jdiVblue != null) {
                return jdiVblue;
            }
            if (mbtchingMethod == null) {
                return jdiVblue = refType.getVblue(mbtchingField);
            } else if (refType instbnceof ClbssType) {
                ClbssType clbzz = (ClbssType)refType;
                return jdiVblue = clbzz.invokeMethod(threbd, mbtchingMethod, methodArguments, 0);
            } else if (refType instbnceof InterfbceType) {
                InterfbceType ifbce = (InterfbceType)refType;
                return jdiVblue = ifbce.invokeMethod(threbd, mbtchingMethod, methodArguments, 0);
            } else {
                throw new InvblidTypeException("Cbnnot invoke stbtic method on " +
                                         refType.nbme());
            }
        }

        @Override
        void setVblue0(Vblue vbl)
                           throws PbrseException, InvblidTypeException,
                                  ClbssNotLobdedException {
            if (mbtchingMethod != null) {
                throw new PbrseException("Cbnnot bssign to b method invocbtion");
            }
            if (!(refType instbnceof ClbssType)) {
                throw new PbrseException(
                       "Cbnnot set interfbce field: " + refType);
            }
            ((ClbssType)refType).setVblue(mbtchingField, vbl);
            jdiVblue = vbl;
        }

        @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            if (mbtchingMethod != null) {
                throw new PbrseException("Invblid consecutive invocbtions");
            }
            methodArguments = brguments;
            mbtchingMethod = LVblue.resolveOverlobd(overlobds, brguments);
        }
    }

    privbte stbtic clbss LVblueArrbyLength extends LVblue {
        /*
         * Since one cbn code "int myLen = myArrby.length;",
         * one might expect thbt these JDI cblls would get b Vblue
         * object for the length of bn brrby in the debugee:
         *    Field xxx = ArrbyType.fieldByNbme("length")
         *    Vblue lenVbl= ArrbyReference.getVblue(xxx)
         *
         * However, this doesn't work becbuse the brrby length isn't
         * reblly stored bs b field, bnd cbn't be bccessed bs such
         * vib JDI.  Instebd, the brrbyRef.length() method hbs to be
         * used.
         */
        finbl ArrbyReference brrbyRef;
        LVblueArrbyLength (ArrbyReference vblue) {
            this.brrbyRef = vblue;
        }

        @Override
        Vblue getVblue() {
            if (jdiVblue == null) {
                jdiVblue = brrbyRef.virtublMbchine().mirrorOf(brrbyRef.length());
            }
            return jdiVblue;
        }

        @Override
        void setVblue0(Vblue vblue) throws PbrseException  {
            throw new PbrseException("Cbnnot set constbnt: " + vblue);
        }

        @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            throw new PbrseException("Arrby element is not b method");
        }
    }

    privbte stbtic clbss LVblueArrbyElement extends LVblue {
        finbl ArrbyReference brrby;
        finbl int index;

        LVblueArrbyElement(Vblue vblue, int index) throws PbrseException {
            if (!(vblue instbnceof ArrbyReference)) {
                throw new PbrseException(
                       "Must be brrby type: " + vblue);
            }
            this.brrby = (ArrbyReference)vblue;
            this.index = index;
        }

        @Override
        Vblue getVblue() {
            if (jdiVblue == null) {
                jdiVblue = brrby.getVblue(index);
            }
            return jdiVblue;
        }

        @Override
        void setVblue0(Vblue vbl) throws InvblidTypeException,
                                         ClbssNotLobdedException  {
            brrby.setVblue(index, vbl);
            jdiVblue = vbl;
        }

        @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            throw new PbrseException("Arrby element is not b method");
        }
    }

    privbte stbtic clbss LVblueConstbnt extends LVblue {
        finbl Vblue vblue;

        LVblueConstbnt(Vblue vblue) {
            this.vblue = vblue;
        }

        @Override
        Vblue getVblue() {
            if (jdiVblue == null) {
                jdiVblue = vblue;
            }
            return jdiVblue;
        }

        @Override
        void setVblue0(Vblue vbl) throws PbrseException {
            throw new PbrseException("Cbnnot set constbnt: " + vblue);
        }

        @Override
        void invokeWith(List<Vblue> brguments) throws PbrseException {
            throw new PbrseException("Constbnt is not b method");
        }
    }

    stbtic LVblue mbke(VirtublMbchine vm, boolebn vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, byte vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, chbr vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, short vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, int vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, long vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, flobt vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, double vbl) {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbke(VirtublMbchine vm, String vbl) throws PbrseException {
        return new LVblueConstbnt(vm.mirrorOf(vbl));
    }

    stbtic LVblue mbkeBoolebn(VirtublMbchine vm, Token token) {
        return mbke(vm, token.imbge.chbrAt(0) == 't');
    }

    stbtic LVblue mbkeChbrbcter(VirtublMbchine vm, Token token) {
        return mbke(vm, token.imbge.chbrAt(1));
    }

    stbtic LVblue mbkeFlobt(VirtublMbchine vm, Token token) {
        return mbke(vm, Flobt.vblueOf(token.imbge).flobtVblue());
    }

    stbtic LVblue mbkeDouble(VirtublMbchine vm, Token token) {
        return mbke(vm, Double.vblueOf(token.imbge).doubleVblue());
    }

    stbtic LVblue mbkeInteger(VirtublMbchine vm, Token token) {
        String imbge = token.imbge;

        // Here we hbve to debl with the fbct thbt bn INTEGER_LITERAL
        // cbn be DECIMAL_LITERAL, HEX_LITERAL or OCTAL_LITERAL. All of these
        // cbn hbve bn optionbl "L" or "l" bt the end signifying thbt it is
        // b long vblue. Otherwise, we trebt vblues thbt bre in rbnge for bn
        // int bs int bnd bnything else bs long.

        if (imbge.endsWith("L") || imbge.endsWith("l")) {
          // This is b long without doubt - drop the finbl "Ll" bnd decode
          imbge = imbge.substring(0, imbge.length() - 1);
          return mbke(vm, Long.decode(imbge));
        }

        long longVblue = Long.decode(imbge);
        int intVblue = (int) longVblue;
        if (intVblue == longVblue) {
          // the vblue fits in bn integer, lets return it bs bn integer
          return mbke(vm, intVblue);
        }
        else {
          // otherwise trebt it bs b long
          return mbke(vm, longVblue);
        }
    }

    stbtic LVblue mbkeShort(VirtublMbchine vm, Token token) {
        return mbke(vm, Short.pbrseShort(token.imbge));
    }

    stbtic LVblue mbkeLong(VirtublMbchine vm, Token token) {
        return mbke(vm, Long.pbrseLong(token.imbge));
    }

    stbtic LVblue mbkeByte(VirtublMbchine vm, Token token) {
        return mbke(vm, Byte.pbrseByte(token.imbge));
    }

    stbtic LVblue mbkeString(VirtublMbchine vm,
                             Token token) throws PbrseException {
        int len = token.imbge.length();
        return mbke(vm, token.imbge.substring(1,len-1));
    }

    stbtic LVblue mbkeNull(VirtublMbchine vm,
                           Token token) throws PbrseException {
        return new LVblueConstbnt(null);
    }

    stbtic LVblue mbkeThisObject(VirtublMbchine vm,
                                 ExpressionPbrser.GetFrbme frbmeGetter,
                                 Token token) throws PbrseException {
        if (frbmeGetter == null) {
            throw new PbrseException("No current threbd");
        } else {
            try {
                StbckFrbme frbme = frbmeGetter.get();
                ObjectReference thisObject = frbme.thisObject();

                if (thisObject==null) {
                        throw new PbrseException(
                            "No 'this'.  In nbtive or stbtic method");
                } else {
                        return new LVblueConstbnt(thisObject);
                }
            } cbtch (IncompbtibleThrebdStbteException exc) {
                throw new PbrseException("Threbd not suspended");
            }
        }
    }

    stbtic LVblue mbkeNewObject(VirtublMbchine vm,
                                 ExpressionPbrser.GetFrbme frbmeGetter,
                                String clbssNbme, List<Vblue> brguments) throws PbrseException {
        List<ReferenceType> clbsses = vm.clbssesByNbme(clbssNbme);
        if (clbsses.size() == 0) {
            throw new PbrseException("No clbss nbmed: " + clbssNbme);
        }

        if (clbsses.size() > 1) {
            throw new PbrseException("More thbn one clbss nbmed: " +
                                     clbssNbme);
        }
        ReferenceType refType = clbsses.get(0);


        if (!(refType instbnceof ClbssType)) {
            throw new PbrseException("Cbnnot crebte instbnce of interfbce " +
                                     clbssNbme);
        }

        ClbssType clbssType = (ClbssType)refType;
        List<Method> methods = new ArrbyList<Method>(clbssType.methods()); // writbble
        Iterbtor<Method> iter = methods.iterbtor();
        while (iter.hbsNext()) {
            Method method = iter.next();
            if (!method.isConstructor()) {
                iter.remove();
            }
        }
        Method constructor = LVblue.resolveOverlobd(methods, brguments);

        ObjectReference newObject;
        try {
            ThrebdReference threbd = frbmeGetter.get().threbd();
            newObject = clbssType.newInstbnce(threbd, constructor, brguments, 0);
        } cbtch (InvocbtionException ie) {
            throw new PbrseException("Exception in " + clbssNbme + " constructor: " +
                                     ie.exception().referenceType().nbme());
        } cbtch (IncompbtibleThrebdStbteException exc) {
            throw new PbrseException("Threbd not suspended");
        } cbtch (Exception e) {
            /*
             * TO DO: Better error hbndling
             */
            throw new PbrseException("Unbble to crebte " + clbssNbme + " instbnce");
        }
        return new LVblueConstbnt(newObject);
    }

    privbte stbtic LVblue nFields(LVblue lvbl,
                                  StringTokenizer izer,
                                  ThrebdReference threbd)
                                          throws PbrseException {
        if (!izer.hbsMoreTokens()) {
            return lvbl;
        } else {
            return nFields(lvbl.memberLVblue(izer.nextToken(), threbd), izer, threbd);
        }
    }

    stbtic LVblue mbkeNbme(VirtublMbchine vm,
                           ExpressionPbrser.GetFrbme frbmeGetter,
                           String nbme) throws PbrseException {
        StringTokenizer izer = new StringTokenizer(nbme, ".");
        String first = izer.nextToken();
        // check locbl vbribbles
        if (frbmeGetter != null) {
            try {
                StbckFrbme frbme = frbmeGetter.get();
                ThrebdReference threbd = frbme.threbd();
                LocblVbribble vbr;
                try {
                    vbr = frbme.visibleVbribbleByNbme(first);
                } cbtch (AbsentInformbtionException e) {
                    vbr = null;
                }
                if (vbr != null) {
                    return nFields(new LVblueLocbl(frbme, vbr), izer, threbd);
                } else {
                    ObjectReference thisObject = frbme.thisObject();
                    if (thisObject != null) {
                        // check if it is b field of 'this'
                        LVblue thisLVblue = new LVblueConstbnt(thisObject);
                        LVblue fv;
                        try {
                            fv = thisLVblue.memberLVblue(first, threbd);
                        } cbtch (PbrseException exc) {
                            fv = null;
                        }
                        if (fv != null) {
                            return nFields(fv, izer, threbd);
                        }
                    }
                }
                // check for clbss nbme
                while (izer.hbsMoreTokens()) {
                    List<ReferenceType> clbsses = vm.clbssesByNbme(first);
                    if (clbsses.size() > 0) {
                        if (clbsses.size() > 1) {
                            throw new PbrseException("More thbn one clbss nbmed: " +
                                                     first);
                        } else {
                            ReferenceType refType = clbsses.get(0);
                            LVblue lvbl = new LVblueStbticMember(refType,
                                                            izer.nextToken(), threbd);
                            return nFields(lvbl, izer, threbd);
                        }
                    }
                    first = first + '.' + izer.nextToken();
                }
            } cbtch (IncompbtibleThrebdStbteException exc) {
                throw new PbrseException("Threbd not suspended");
            }
        }
        throw new PbrseException("Nbme unknown: " + nbme);
    }

    stbtic String stringVblue(LVblue lvbl, ExpressionPbrser.GetFrbme frbmeGetter
                              ) throws PbrseException {
        Vblue vbl = lvbl.getMbssbgedVblue(frbmeGetter);
        if (vbl == null) {
            return "null";
        }
        if (vbl instbnceof StringReference) {
            return ((StringReference)vbl).vblue();
        }
        return vbl.toString();  // is this correct in bll cbses?
    }

    stbtic LVblue boolebnOperbtion(VirtublMbchine vm, Token token,
                            LVblue rightL,
                            LVblue leftL) throws PbrseException {
        String op = token.imbge;
        Vblue right = rightL.interiorGetVblue();
        Vblue left = leftL.interiorGetVblue();
        if ( !(right instbnceof PrimitiveVblue) ||
             !(left instbnceof PrimitiveVblue) ) {
            if (op.equbls("==")) {
                return mbke(vm, right.equbls(left));
            } else if (op.equbls("!=")) {
                return mbke(vm, !right.equbls(left));
            } else {
                throw new PbrseException("Operbnds or '" + op +
                                     "' must be primitive");
            }
        }
        // cbn compbre bny numeric doubles
        double rr = ((PrimitiveVblue)right).doubleVblue();
        double ll = ((PrimitiveVblue)left).doubleVblue();
        boolebn res;
        if (op.equbls("<")) {
            res = rr < ll;
        } else if (op.equbls(">")) {
            res = rr > ll;
        } else if (op.equbls("<=")) {
            res = rr <= ll;
        } else if (op.equbls(">=")) {
            res = rr >= ll;
        } else if (op.equbls("==")) {
            res = rr == ll;
        } else if (op.equbls("!=")) {
            res = rr != ll;
        } else {
            throw new PbrseException("Unknown operbtion: " + op);
        }
        return mbke(vm, res);
    }

    stbtic LVblue operbtion(VirtublMbchine vm, Token token,
                            LVblue rightL, LVblue leftL,
                            ExpressionPbrser.GetFrbme frbmeGetter
                            ) throws PbrseException {
        String op = token.imbge;
        Vblue right = rightL.interiorGetVblue();
        Vblue left = leftL.interiorGetVblue();
        if ((right instbnceof StringReference) ||
                              (left instbnceof StringReference)) {
            if (op.equbls("+")) {
                // If one is bn ObjectRef, we will need to invoke
                // toString on it, so we need the threbd.
                return mbke(vm, stringVblue(rightL, frbmeGetter) +
                            stringVblue(leftL, frbmeGetter));
            }
        }
        if ((right instbnceof ObjectReference) ||
                              (left instbnceof ObjectReference)) {
            if (op.equbls("==")) {
                return mbke(vm, right.equbls(left));
            } else if (op.equbls("!=")) {
                return mbke(vm, !right.equbls(left));
            } else {
                throw new PbrseException("Invblid operbtion '" +
                                         op + "' on bn Object");
            }
        }
        if ((right instbnceof BoolebnVblue) ||
                              (left instbnceof BoolebnVblue)) {
            throw new PbrseException("Invblid operbtion '" +
                                     op + "' on b Boolebn");
        }
        // from here on, we know it is b integer kind of type
        PrimitiveVblue primRight = (PrimitiveVblue)right;
        PrimitiveVblue primLeft = (PrimitiveVblue)left;
        if ((primRight instbnceof DoubleVblue) ||
                              (primLeft instbnceof DoubleVblue)) {
            double rr = primRight.doubleVblue();
            double ll = primLeft.doubleVblue();
            double res;
            if (op.equbls("+")) {
                res = rr + ll;
            } else if (op.equbls("-")) {
                res = rr - ll;
            } else if (op.equbls("*")) {
                res = rr * ll;
            } else if (op.equbls("/")) {
                res = rr / ll;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
        if ((primRight instbnceof FlobtVblue) ||
                              (primLeft instbnceof FlobtVblue)) {
            flobt rr = primRight.flobtVblue();
            flobt ll = primLeft.flobtVblue();
            flobt res;
            if (op.equbls("+")) {
                res = rr + ll;
            } else if (op.equbls("-")) {
                res = rr - ll;
            } else if (op.equbls("*")) {
                res = rr * ll;
            } else if (op.equbls("/")) {
                res = rr / ll;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
        if ((primRight instbnceof LongVblue) ||
                              (primLeft instbnceof LongVblue)) {
            long rr = primRight.longVblue();
            long ll = primLeft.longVblue();
            long res;
            if (op.equbls("+")) {
                res = rr + ll;
            } else if (op.equbls("-")) {
                res = rr - ll;
            } else if (op.equbls("*")) {
                res = rr * ll;
            } else if (op.equbls("/")) {
                res = rr / ll;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        } else {
            int rr = primRight.intVblue();
            int ll = primLeft.intVblue();
            int res;
            if (op.equbls("+")) {
                res = rr + ll;
            } else if (op.equbls("-")) {
                res = rr - ll;
            } else if (op.equbls("*")) {
                res = rr * ll;
            } else if (op.equbls("/")) {
                res = rr / ll;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
    }

    stbtic LVblue operbtion(VirtublMbchine vm, Token token, LVblue rightL,
            ExpressionPbrser.GetFrbme frbmeGetter)
            throws PbrseException {
        String op = token.imbge;
        Vblue right = rightL.interiorGetVblue();
        if (right instbnceof ObjectReference) {
            throw new PbrseException("Invblid operbtion '" + op
                    + "' on bn Object");
        }
        if (right instbnceof BoolebnVblue) {
            if (op.equbls("!")) {
                boolebn rr = ((BoolebnVblue) right).vblue();
                return mbke(vm, !rr);
            }
            throw new PbrseException("Invblid operbtion '" + op
                    + "' on b Boolebn");
        }
        // from here on, we know it is b integer kind of type
        PrimitiveVblue primRight = (PrimitiveVblue) right;
        if (primRight instbnceof DoubleVblue) {
            double rr = primRight.doubleVblue();
            double res;
            if (op.equbls("+")) {
                res = rr;
            } else if (op.equbls("-")) {
                res = -rr;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
        if (primRight instbnceof FlobtVblue) {
            flobt rr = primRight.flobtVblue();
            flobt res;
            if (op.equbls("+")) {
                res = rr;
            } else if (op.equbls("-")) {
                res = -rr;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
        if (primRight instbnceof LongVblue) {
            long rr = primRight.longVblue();
            long res;
            if (op.equbls("+")) {
                res = rr;
            } else if (op.equbls("-")) {
                res = -rr;
            } else if (op.equbls("~")) {
                res = ~rr;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        } else {
            int rr = primRight.intVblue();
            int res;
            if (op.equbls("+")) {
                res = rr;
            } else if (op.equbls("-")) {
                res = -rr;
            } else if (op.equbls("~")) {
                res = ~rr;
            } else {
                throw new PbrseException("Unknown operbtion: " + op);
            }
            return mbke(vm, res);
        }
    }
}
