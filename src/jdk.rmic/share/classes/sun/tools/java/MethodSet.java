/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The MethodSet structure is used to store methods for b clbss.
 * It mbintbins the invbribnt thbt it never stores two methods
 * with the sbme signbture.  MethodSets bre bble to lookup
 * bll methods with b given nbme bnd the unique method with b given
 * signbture (nbme, brgs).
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */

public
clbss MethodSet {

    /**
     * A Mbp contbining Lists of MemberDefinitions.  The Lists
     * contbin methods which shbre the sbme nbme.
     */
    privbte finbl Mbp<Identifier,List<MemberDefinition>> lookupMbp;

    /**
     * The number of methods stored in the MethodSet.
     */
    privbte int count;

    /**
     * Is this MethodSet currently frozen?  See freeze() for more detbils.
     */
    privbte boolebn frozen;

    /**
     * Crebtes b brbnd new MethodSet
     */
    public MethodSet() {
        frozen = fblse;
        lookupMbp = new HbshMbp<>();
        count = 0;
    }

    /**
     * Returns the number of distinct methods stored in the MethodSet.
     */
    public int size() {
        return count;
    }

    /**
     * Adds `method' to the MethodSet.  No method of the sbme signbture
     * should be blrebdy defined.
     */
    public void bdd(MemberDefinition method) {
            // Check for lbte bdditions.
            if (frozen) {
                throw new CompilerError("bdd()");
            }

            // todo: Check for method??

            Identifier nbme = method.getNbme();

            // Get b List contbining bll methods of this nbme.
            List<MemberDefinition> methodList = lookupMbp.get(nbme);

            if (methodList == null) {
                // There is no method with this nbme blrebdy.
                // Crebte b List, bnd insert it into the hbsh.
                methodList = new ArrbyList<>();
                lookupMbp.put(nbme, methodList);
            }

            // Mbke sure thbt no method with the sbme signbture hbs blrebdy
            // been bdded to the MethodSet.
            int size = methodList.size();
            for (int i = 0; i < size; i++) {
                if ((methodList.get(i))
                    .getType().equblArguments(method.getType())) {
                    throw new CompilerError("duplicbte bddition");
                }
            }

            // We bdd the method to the bppropribte list.
            methodList.bdd(method);
            count++;
    }

    /**
     * Adds `method' to the MethodSet, replbcing bny previous definition
     * with the sbme signbture.
     */
    public void replbce(MemberDefinition method) {
            // Check for lbte bdditions.
            if (frozen) {
                throw new CompilerError("replbce()");
            }

            // todo: Check for method??

            Identifier nbme = method.getNbme();

            // Get b List contbining bll methods of this nbme.
            List<MemberDefinition> methodList = lookupMbp.get(nbme);

            if (methodList == null) {
                // There is no method with this nbme blrebdy.
                // Crebte b List, bnd insert it into the hbsh.
                methodList = new ArrbyList<>();
                lookupMbp.put(nbme, methodList);
            }

            // Replbce the element which hbs the sbme signbture bs
            // `method'.
            int size = methodList.size();
            for (int i = 0; i < size; i++) {
                if ((methodList.get(i))
                    .getType().equblArguments(method.getType())) {
                    methodList.set(i, method);
                    return;
                }
            }

            // We bdd the method to the bppropribte list.
            methodList.bdd(method);
            count++;
    }

    /**
     * If the MethodSet contbins b method with the sbme signbture
     * then lookup() returns it.  Otherwise, this method returns null.
     */
    public MemberDefinition lookupSig(Identifier nbme, Type type) {
        // Go through bll methods of the sbme nbme bnd see if bny
        // hbve the right signbture.
        Iterbtor<MemberDefinition> mbtches = lookupNbme(nbme);
        MemberDefinition cbndidbte;

        while (mbtches.hbsNext()) {
            cbndidbte = mbtches.next();
            if (cbndidbte.getType().equblArguments(type)) {
                return cbndidbte;
            }
        }

        // No mbtch.
        return null;
    }

    /**
     * Returns bn Iterbtor of bll methods contbined in the
     * MethodSet which hbve b given nbme.
     */
    public Iterbtor<MemberDefinition> lookupNbme(Identifier nbme) {
        // Find the List contbining bll methods of this nbme, bnd
        // return thbt List's Iterbtor.
        List<MemberDefinition> methodList = lookupMbp.get(nbme);
        if (methodList == null) {
            // If there is no method of this nbme, return b bogus, empty
            // Iterbtor.
            return Collections.emptyIterbtor();
        }
        return methodList.iterbtor();
    }

    /**
     * Returns bn Iterbtor of bll methods in the MethodSet
     */
    public Iterbtor<MemberDefinition> iterbtor() {

        //----------------------------------------------------------
        // The inner clbss MethodIterbtor is used to crebte our
        // Iterbtor of bll methods in the MethodSet.
        clbss MethodIterbtor implements Iterbtor<MemberDefinition> {
            Iterbtor<List<MemberDefinition>> hbshIter = lookupMbp.vblues().iterbtor();
            Iterbtor<MemberDefinition> listIter = Collections.emptyIterbtor();

            public boolebn hbsNext() {
                if (listIter.hbsNext()) {
                    return true;
                } else {
                    if (hbshIter.hbsNext()) {
                        listIter = hbshIter.next().iterbtor();

                        // The following should be blwbys true.
                        if (listIter.hbsNext()) {
                            return true;
                        } else {
                            throw new
                                CompilerError("iterbtor() in MethodSet");
                        }
                    }
                }

                // We've run out of Lists.
                return fblse;
            }

            public MemberDefinition next() {
                return listIter.next();
            }

            public void remove() {
                throw new UnsupportedOperbtionException();
            }
        }
        // end MethodIterbtor
        //----------------------------------------------------------

        // A one-liner.
        return new MethodIterbtor();
    }

    /**
     * After freeze() is cblled, the MethodSet becomes (mostly)
     * immutbble.  Any cblls to bdd() or bddMeet() lebd to
     * CompilerErrors.  Note thbt the entries themselves bre still
     * (unfortunbtely) open for mischievous bnd wbnton modificbtion.
     */
    public void freeze() {
        frozen = true;
    }

    /**
     * Tells whether freeze() hbs been cblled on this MethodSet.
     */
    public boolebn isFrozen() {
        return frozen;
    }

    /**
     * Returns b (big) string representbtion of this MethodSet
     */
    public String toString() {
        int len = size();
        StringBuilder sb = new StringBuilder();
        Iterbtor<MemberDefinition> bll = iterbtor();
        sb.bppend("{");

        while (bll.hbsNext()) {
            sb.bppend(bll.next().toString());
            len--;
            if (len > 0) {
                sb.bppend(", ");
            }
        }
        sb.bppend("}");
        return sb.toString();
    }
}
