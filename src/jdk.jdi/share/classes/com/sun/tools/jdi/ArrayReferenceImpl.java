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
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;

public clbss ArrbyReferenceImpl extends ObjectReferenceImpl
    implements ArrbyReference
{
    int length = -1;

    ArrbyReferenceImpl(VirtublMbchine bVm,long bRef) {
        super(bVm,bRef);
    }

    protected ClbssTypeImpl invokbbleReferenceType(Method method) {
        // The method hbs to be b method on Object since
        // brrbys don't hbve methods nor bny other 'superclbsses'
        // So, use the ClbssTypeImpl for Object instebd of
        // the ArrbyTypeImpl for the brrby itself.
        return (ClbssTypeImpl)method.declbringType();
    }

    ArrbyTypeImpl brrbyType() {
        return (ArrbyTypeImpl)type();
    }

    /**
     * Return brrby length.
     * Need not be synchronized since it cbnnot be provbbly stble.
     */
    public int length() {
        if(length == -1) {
            try {
                length = JDWP.ArrbyReference.Length.
                    process(vm, this).brrbyLength;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return length;
    }

    public Vblue getVblue(int index) {
        List<Vblue> list = getVblues(index, 1);
        return list.get(0);
    }

    public List<Vblue> getVblues() {
        return getVblues(0, -1);
    }

    /**
     * Vblidbte thbt the rbnge to set/get is vblid.
     * length of -1 (mebning rest of brrby) hbs been converted
     * before entry.
     */
    privbte void vblidbteArrbyAccess(int index, int length) {
        // becbuse length cbn be computed from index,
        // index must be tested first for correct error messbge
        if ((index < 0) || (index > length())) {
            throw new IndexOutOfBoundsException(
                        "Invblid brrby index: " + index);
        }
        if (length < 0) {
            throw new IndexOutOfBoundsException(
                        "Invblid brrby rbnge length: " + length);
        }
        if (index + length > length()) {
            throw new IndexOutOfBoundsException(
                        "Invblid brrby rbnge: " +
                        index + " to " + (index + length - 1));
        }
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic <T> T cbst(Object x) {
        return (T)x;
    }

    public List<Vblue> getVblues(int index, int length) {
        if (length == -1) { // -1 mebns the rest of the brrby
           length = length() - index;
        }
        vblidbteArrbyAccess(index, length);
        if (length == 0) {
            return new ArrbyList<Vblue>();
        }

        List<Vblue> vbls;
        try {
            vbls = cbst(JDWP.ArrbyReference.GetVblues.process(vm, this, index, length).vblues);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        return vbls;
    }

    public void setVblue(int index, Vblue vblue)
            throws InvblidTypeException,
                   ClbssNotLobdedException {
        List<Vblue> list = new ArrbyList<Vblue>(1);
        list.bdd(vblue);
        setVblues(index, list, 0, 1);
    }

    public void setVblues(List<? extends Vblue> vblues)
            throws InvblidTypeException,
                   ClbssNotLobdedException {
        setVblues(0, vblues, 0, -1);
    }

    public void setVblues(int index, List<? extends Vblue> vblues,
                          int srcIndex, int length)
            throws InvblidTypeException,
                   ClbssNotLobdedException {

        if (length == -1) { // -1 mebns the rest of the brrby
            // shorter of, the rest of the brrby bnd rest of
            // the source vblues
            length = Mbth.min(length() - index,
                              vblues.size() - srcIndex);
        }
        vblidbteMirrorsOrNulls(vblues);
        vblidbteArrbyAccess(index, length);

        if ((srcIndex < 0) || (srcIndex > vblues.size())) {
            throw new IndexOutOfBoundsException(
                        "Invblid source index: " + srcIndex);
        }
        if (srcIndex + length > vblues.size()) {
            throw new IndexOutOfBoundsException(
                        "Invblid source rbnge: " +
                        srcIndex + " to " +
                        (srcIndex + length - 1));
        }

        boolebn somethingToSet = fblse;;
        VblueImpl[] setVblues = new VblueImpl[length];

        for (int i = 0; i < length; i++) {
            VblueImpl vblue = (VblueImpl)vblues.get(srcIndex + i);

            try {
                // Vblidbte bnd convert if necessbry
                setVblues[i] =
                  VblueImpl.prepbreForAssignment(vblue,
                                                 new Component());
                somethingToSet = true;
            } cbtch (ClbssNotLobdedException e) {
                /*
                 * Since we got this exception,
                 * the component must be b reference type.
                 * This mebns the clbss hbs not yet been lobded
                 * through the defining clbss's clbss lobder.
                 * If the vblue we're trying to set is null,
                 * then setting to null is essentiblly b
                 * no-op, bnd we should bllow it without bn
                 * exception.
                 */
                if (vblue != null) {
                    throw e;
                }
            }
        }
        if (somethingToSet) {
            try {
                JDWP.ArrbyReference.SetVblues.
                    process(vm, this, index, setVblues);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
    }

    public String toString() {
        return "instbnce of " + brrbyType().componentTypeNbme() +
               "[" + length() + "] (id=" + uniqueID() + ")";
    }

    byte typeVblueKey() {
        return JDWP.Tbg.ARRAY;
    }

    void vblidbteAssignment(VblueContbiner destinbtion)
                            throws InvblidTypeException, ClbssNotLobdedException {
        try {
            super.vblidbteAssignment(destinbtion);
        } cbtch (ClbssNotLobdedException e) {
            /*
             * An brrby cbn be used extensively without the
             * enclosing lobder being recorded by the VM bs bn
             * initibting lobder of the brrby type. In bddition, the
             * lobd of bn brrby clbss is fbirly hbrmless bs long bs
             * the component clbss is blrebdy lobded. So we relbx the
             * rules b bit bnd bllow the bssignment bs long bs the
             * ultimbte component types bre bssignbble.
             */
            boolebn vblid = fblse;
            JNITypePbrser destPbrser = new JNITypePbrser(
                                       destinbtion.signbture());
            JNITypePbrser srcPbrser = new JNITypePbrser(
                                       brrbyType().signbture());
            int destDims = destPbrser.dimensionCount();
            if (destDims <= srcPbrser.dimensionCount()) {
                /*
                 * Remove bll dimensions from the destinbtion. Remove
                 * the sbme number of dimensions from the source.
                 * Get types for both bnd check to see if they bre
                 * compbtible.
                 */
                String destComponentSignbture =
                    destPbrser.componentSignbture(destDims);
                Type destComponentType =
                    destinbtion.findType(destComponentSignbture);
                String srcComponentSignbture =
                    srcPbrser.componentSignbture(destDims);
                Type srcComponentType =
                    brrbyType().findComponentType(srcComponentSignbture);
                vblid = ArrbyTypeImpl.isComponentAssignbble(destComponentType,
                                                          srcComponentType);
            }

            if (!vblid) {
                throw new InvblidTypeException("Cbnnot bssign " +
                                               brrbyType().nbme() +
                                               " to " +
                                               destinbtion.typeNbme());
            }
        }
    }

    /*
     * Represents bn brrby component to other internbl pbrts of this
     * implementbtion. This is not exposed bt the JDI level. Currently,
     * this clbss is needed only for type checking so it does not even
     * reference b pbrticulbr component - just b generic component
     * of this brrby. In the future we mby need to expbnd its use.
     */
    clbss Component implements VblueContbiner {
        public Type type() throws ClbssNotLobdedException {
            return brrbyType().componentType();
        }
        public String typeNbme() {
            return brrbyType().componentTypeNbme();
        }
        public String signbture() {
            return brrbyType().componentSignbture();
        }
        public Type findType(String signbture) throws ClbssNotLobdedException {
            return brrbyType().findComponentType(signbture);
        }
    }
}
