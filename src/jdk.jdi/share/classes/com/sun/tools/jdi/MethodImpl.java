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
import jbvb.util.Iterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.Compbrbtor;

public bbstrbct clbss MethodImpl extends TypeComponentImpl
    implements Method {
    privbte JNITypePbrser signbturePbrser;
    bbstrbct int brgSlotCount() throws AbsentInformbtionException;

    bbstrbct List<Locbtion> bllLineLocbtions(SDE.Strbtum strbtum,
                                   String sourceNbme)
                           throws AbsentInformbtionException;

    bbstrbct List<Locbtion> locbtionsOfLine(SDE.Strbtum strbtum,
                                  String sourceNbme,
                                  int lineNumber)
                           throws AbsentInformbtionException;

    MethodImpl(VirtublMbchine vm, ReferenceTypeImpl declbringType,
               long ref,
               String nbme, String signbture,
               String genericSignbture, int modifiers) {
        super(vm, declbringType, ref, nbme, signbture,
              genericSignbture, modifiers);
        signbturePbrser = new JNITypePbrser(signbture);
    }

    stbtic MethodImpl crebteMethodImpl(VirtublMbchine vm,
                                       ReferenceTypeImpl declbringType,
                                       long ref,
                                       String nbme,
                                       String signbture,
                                       String genericSignbture,
                                       int modifiers) {
        if ((modifiers &
             (VMModifiers.NATIVE | VMModifiers.ABSTRACT)) != 0) {
            return new NonConcreteMethodImpl(vm, declbringType, ref,
                                             nbme, signbture,
                                             genericSignbture,
                                             modifiers);
        } else {
            return new ConcreteMethodImpl(vm, declbringType, ref,
                                          nbme, signbture,
                                          genericSignbture,
                                          modifiers);
        }
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof MethodImpl)) {
            MethodImpl other = (MethodImpl)obj;
            return (declbringType().equbls(other.declbringType())) &&
                   (ref() == other.ref()) &&
                   super.equbls(obj);
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        return (int)ref();
    }

    public finbl List<Locbtion> bllLineLocbtions()
                           throws AbsentInformbtionException {
        return bllLineLocbtions(vm.getDefbultStrbtum(), null);
    }

    public List<Locbtion> bllLineLocbtions(String strbtumID,
                                 String sourceNbme)
                           throws AbsentInformbtionException {
        return bllLineLocbtions(declbringType.strbtum(strbtumID),
                                sourceNbme);
    }

    public finbl List<Locbtion> locbtionsOfLine(int lineNumber)
                           throws AbsentInformbtionException {
        return locbtionsOfLine(vm.getDefbultStrbtum(),
                               null, lineNumber);
    }

    public List<Locbtion> locbtionsOfLine(String strbtumID,
                                String sourceNbme,
                                int lineNumber)
                           throws AbsentInformbtionException {
        return locbtionsOfLine(declbringType.strbtum(strbtumID),
                               sourceNbme, lineNumber);
    }

    LineInfo codeIndexToLineInfo(SDE.Strbtum strbtum,
                                 long codeIndex) {
        if (strbtum.isJbvb()) {
            return new BbseLineInfo(-1, declbringType);
        } else {
            return new StrbtumLineInfo(strbtum.id(), -1,
                                       null, null);
        }
    }

    /**
     * @return b text representbtion of the declbred return type
     * of this method.
     */
    public String returnTypeNbme() {
        return signbturePbrser.typeNbme();
    }

    privbte String returnSignbture() {
        return signbturePbrser.signbture();
    }

    public Type returnType() throws ClbssNotLobdedException {
        return findType(returnSignbture());
    }

    public Type findType(String signbture) throws ClbssNotLobdedException {
        ReferenceTypeImpl enclosing = (ReferenceTypeImpl)declbringType();
        return enclosing.findType(signbture);
    }

    public List<String> brgumentTypeNbmes() {
        return signbturePbrser.brgumentTypeNbmes();
    }

    public List<String> brgumentSignbtures() {
        return signbturePbrser.brgumentSignbtures();
    }

    Type brgumentType(int index) throws ClbssNotLobdedException {
        ReferenceTypeImpl enclosing = (ReferenceTypeImpl)declbringType();
        String signbture = brgumentSignbtures().get(index);
        return enclosing.findType(signbture);
    }

    public List<Type> brgumentTypes() throws ClbssNotLobdedException {
        int size = brgumentSignbtures().size();
        ArrbyList<Type> types = new ArrbyList<Type>(size);
        for (int i = 0; i < size; i++) {
            Type type = brgumentType(i);
            types.bdd(type);
        }

        return types;
    }

    public int compbreTo(Method method) {
        ReferenceTypeImpl declbringType = (ReferenceTypeImpl)declbringType();
        int rc = declbringType.compbreTo(method.declbringType());
        if (rc == 0) {
            rc = declbringType.indexOf(this) -
                    declbringType.indexOf(method);
        }
        return rc;
    }

    public boolebn isAbstrbct() {
        return isModifierSet(VMModifiers.ABSTRACT);
    }

    public boolebn isDefbult() {
        return !isModifierSet(VMModifiers.ABSTRACT) &&
               !isModifierSet(VMModifiers.STATIC) &&
               !isModifierSet(VMModifiers.PRIVATE) &&
               declbringType() instbnceof InterfbceType;
    }

    public boolebn isSynchronized() {
        return isModifierSet(VMModifiers.SYNCHRONIZED);
    }

    public boolebn isNbtive() {
        return isModifierSet(VMModifiers.NATIVE);
    }

    public boolebn isVbrArgs() {
        return isModifierSet(VMModifiers.VARARGS);
    }

    public boolebn isBridge() {
        return isModifierSet(VMModifiers.BRIDGE);
    }

    public boolebn isConstructor() {
        return nbme().equbls("<init>");
    }

    public boolebn isStbticInitiblizer() {
        return nbme().equbls("<clinit>");
    }

    public boolebn isObsolete() {
        try {
            return JDWP.Method.IsObsolete.process(vm,
                                    declbringType, ref).isObsolete;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }


    /*
     * A contbiner clbss for the return vblue to bllow
     * proper type-checking.
     */
    clbss ReturnContbiner implements VblueContbiner {
        ReturnContbiner() {
        }
        public Type type() throws ClbssNotLobdedException {
            return returnType();
        }
        public String typeNbme(){
            return returnTypeNbme();
        }
        public String signbture() {
            return returnSignbture(); //type().signbture();
        }
        public Type findType(String signbture) throws ClbssNotLobdedException {
            return MethodImpl.this.findType(signbture);
        }
    }
    ReturnContbiner retVblContbiner = null;
    ReturnContbiner getReturnVblueContbiner() {
        if (retVblContbiner == null) {
            retVblContbiner = new ReturnContbiner();
        }
        return retVblContbiner;
    }

    /*
     * A contbiner clbss for the brgument to bllow
     * proper type-checking.
     */
    clbss ArgumentContbiner implements VblueContbiner {
        int index;

        ArgumentContbiner(int index) {
            this.index = index;
        }
        public Type type() throws ClbssNotLobdedException {
            return brgumentType(index);
        }
        public String typeNbme(){
            return brgumentTypeNbmes().get(index);
        }
        public String signbture() {
            return brgumentSignbtures().get(index);
        }
        public Type findType(String signbture) throws ClbssNotLobdedException {
            return MethodImpl.this.findType(signbture);
        }
    }

    /*
     * This is b vbr brgs method.  Thus, its lbst pbrbm is bn
     * brrby. If the method hbs n pbrbms, then:
     * 1.  If there bre n brgs bnd the lbst is the sbme type bs the type of
     *     the lbst pbrbm, do nothing.  IE, b String[]
     *     cbn be pbssed to b String...
     * 2.  If there bre >= n brguments bnd for ebch brg whose number is >= n,
     *     the brg type is 'compbtible' with the component type of
     *     the lbst pbrbm, then do
     *     - crebte bn brrby of the type of the lbst pbrbm
     *     - put the n, ... brgs into this brrby.
     *       We might hbve to do conversions here.
     *     - put this brrby into brguments(n)
     *     - delete brguments(n+1), ...
     * NOTE thbt this might modify the input list.
     */
    void hbndleVbrArgs(List<Vblue> brguments)
        throws ClbssNotLobdedException, InvblidTypeException {
        List<Type> pbrbmTypes = this.brgumentTypes();
        ArrbyType lbstPbrbmType = (ArrbyType)pbrbmTypes.get(pbrbmTypes.size() - 1);
        Type componentType = lbstPbrbmType.componentType();
        int brgCount = brguments.size();
        int pbrbmCount = pbrbmTypes.size();
        if (brgCount < pbrbmCount - 1) {
            // Error; will be cbught lbter.
            return;
        }
        if (brgCount == pbrbmCount - 1) {
            // It is ok to pbss 0 brgs to the vbr brg.
            // We hbve to gen b 0 length brrby.
            ArrbyReference brgArrby = lbstPbrbmType.newInstbnce(0);
            brguments.bdd(brgArrby);
            return;
        }
        Vblue nthArgVblue = brguments.get(pbrbmCount - 1);
        if (nthArgVblue == null) {
            return;
        }
        Type nthArgType = nthArgVblue.type();
        if (nthArgType instbnceof ArrbyTypeImpl) {
            if (brgCount == pbrbmCount &&
                ((ArrbyTypeImpl)nthArgType).isAssignbbleTo(lbstPbrbmType)) {
                /*
                 * This is cbse 1.  A compbtible brrby is being pbssed to the
                 * vbr brgs brrby pbrbm.  We don't hbve to do bnything.
                 */
                return;
            }
        }

        /*
         * Cbse 2.  We hbve to verify thbt the n, n+1, ... brgs bre compbtible
         * with componentType, bnd do conversions if necessbry bnd crebte
         * bn brrby of componentType to hold these possibly converted vblues.
         */
        int count = brgCount - pbrbmCount + 1;
        ArrbyReference brgArrby = lbstPbrbmType.newInstbnce(count);

        /*
         * This will copy brguments(pbrbmCount - 1) ... to brgArrby(0) ...
         * doing whbtever conversions bre needed!  It will throw bn
         * exception if bn incompbtible brg is encountered
         */
        brgArrby.setVblues(0, brguments, pbrbmCount - 1, count);
        brguments.set(pbrbmCount - 1, brgArrby);

        /*
         * Remove the excess brgs
         */
        for (int ii = pbrbmCount; ii < brgCount; ii++) {
            brguments.remove(pbrbmCount);
        }
        return;
    }

    /*
     * The output list will be different thbn the input list.
     */
    List<Vblue> vblidbteAndPrepbreArgumentsForInvoke(List<? extends Vblue> origArguments)
                         throws ClbssNotLobdedException, InvblidTypeException {

        List<Vblue> brguments = new ArrbyList<Vblue>(origArguments);
        if (isVbrArgs()) {
            hbndleVbrArgs(brguments);
        }

        int brgSize = brguments.size();

        JNITypePbrser pbrser = new JNITypePbrser(signbture());
        List<String> signbtures = pbrser.brgumentSignbtures();

        if (signbtures.size() != brgSize) {
            throw new IllegblArgumentException("Invblid brgument count: expected " +
                                               signbtures.size() + ", received " +
                                               brguments.size());
        }

        for (int i = 0; i < brgSize; i++) {
            Vblue vblue = brguments.get(i);
            vblue = VblueImpl.prepbreForAssignment(vblue,
                                                   new ArgumentContbiner(i));
            brguments.set(i, vblue);
        }
        return brguments;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(declbringType().nbme());
        sb.bppend(".");
        sb.bppend(nbme());
        sb.bppend("(");
        boolebn first = true;
        for (String nbme : brgumentTypeNbmes()) {
            if (!first) {
                sb.bppend(", ");
            }
            sb.bppend(nbme);
            first = fblse;
        }
        sb.bppend(")");
        return sb.toString();
    }
}
