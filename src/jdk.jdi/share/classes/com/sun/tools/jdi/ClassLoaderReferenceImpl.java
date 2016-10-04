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
import jbvb.util.*;

public clbss ClbssLobderReferenceImpl extends ObjectReferenceImpl
                  implements ClbssLobderReference, VMListener  {

    // This is cbched only while the VM is suspended
    privbte stbtic clbss Cbche extends ObjectReferenceImpl.Cbche {
        List<ReferenceType> visibleClbsses = null;
    }

    protected ObjectReferenceImpl.Cbche newCbche() {
        return new Cbche();
    }

    ClbssLobderReferenceImpl(VirtublMbchine bVm, long ref) {
        super(bVm, ref);
        vm.stbte().bddListener(this);
    }

    protected String description() {
        return "ClbssLobderReference " + uniqueID();
    }

    public List<ReferenceType> definedClbsses() {
        ArrbyList<ReferenceType> definedClbsses = new ArrbyList<ReferenceType>();
        for (ReferenceType type :  vm.bllClbsses()) {
            if (type.isPrepbred() &&
                equbls(type.clbssLobder())) {
                definedClbsses.bdd(type);
            }
        }
        return definedClbsses;
    }

    public List<ReferenceType> visibleClbsses() {
        List<ReferenceType> clbsses = null;
        try {
            Cbche locbl = (Cbche)getCbche();

            if (locbl != null) {
                clbsses = locbl.visibleClbsses;
            }
            if (clbsses == null) {
                JDWP.ClbssLobderReference.VisibleClbsses.ClbssInfo[]
                  jdwpClbsses = JDWP.ClbssLobderReference.VisibleClbsses.
                                            process(vm, this).clbsses;
                clbsses = new ArrbyList<ReferenceType>(jdwpClbsses.length);
                for (int i = 0; i < jdwpClbsses.length; ++i) {
                    clbsses.bdd(vm.referenceType(jdwpClbsses[i].typeID,
                                                 jdwpClbsses[i].refTypeTbg));
                }
                clbsses = Collections.unmodifibbleList(clbsses);
                if (locbl != null) {
                    locbl.visibleClbsses = clbsses;
                    if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                        vm.printTrbce(description() +
                           " temporbrily cbching visible clbsses (count = " +
                                      clbsses.size() + ")");
                    }
                }
            }
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return clbsses;
    }

    Type findType(String signbture) throws ClbssNotLobdedException {
        List<ReferenceType> types = visibleClbsses();
        Iterbtor<ReferenceType> iter = types.iterbtor();
        while (iter.hbsNext()) {
            ReferenceType type = iter.next();
            if (type.signbture().equbls(signbture)) {
                return type;
            }
        }
        JNITypePbrser pbrser = new JNITypePbrser(signbture);
        throw new ClbssNotLobdedException(pbrser.typeNbme(),
                                         "Clbss " + pbrser.typeNbme() + " not lobded");
    }

    byte typeVblueKey() {
        return JDWP.Tbg.CLASS_LOADER;
    }
}
