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

public clbss ThrebdGroupReferenceImpl extends ObjectReferenceImpl
    implements ThrebdGroupReference, VMListener
{
    // Cbched components thbt cbnnot chbnge
    String nbme;
    ThrebdGroupReference pbrent;
    boolebn triedPbrent;

    // This is cbched only while the VM is suspended
    privbte stbtic clbss Cbche extends ObjectReferenceImpl.Cbche {
        JDWP.ThrebdGroupReference.Children kids = null;
    }

    protected ObjectReferenceImpl.Cbche newCbche() {
        return new Cbche();
    }

    ThrebdGroupReferenceImpl(VirtublMbchine bVm,long bRef) {
        super(bVm,bRef);
        vm.stbte().bddListener(this);
    }

    protected String description() {
        return "ThrebdGroupReference " + uniqueID();
    }

    public String nbme() {
        if (nbme == null) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice (Threbd group nbme
            // cbnnot chbnge)
            try {
                nbme = JDWP.ThrebdGroupReference.Nbme.
                                     process(vm, this).groupNbme;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return nbme;
    }

    public ThrebdGroupReference pbrent() {
        if (!triedPbrent) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice (Threbd group pbrent cbnnot
            // chbnge)
            try {
                pbrent = JDWP.ThrebdGroupReference.Pbrent.
                                 process(vm, this).pbrentGroup;
                triedPbrent = true;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
       return pbrent;
    }

    public void suspend() {
        for (ThrebdReference threbd : threbds()) {
            threbd.suspend();
        }

        for (ThrebdGroupReference threbdGroup : threbdGroups()) {
            threbdGroup.suspend();
        }
    }

    public void resume() {
        for (ThrebdReference threbd : threbds()) {
            threbd.resume();
        }

        for (ThrebdGroupReference threbdGroup : threbdGroups()) {
            threbdGroup.resume();
        }
    }

    privbte JDWP.ThrebdGroupReference.Children kids() {
        JDWP.ThrebdGroupReference.Children kids = null;
        try {
            Cbche locbl = (Cbche)getCbche();

            if (locbl != null) {
                kids = locbl.kids;
            }
            if (kids == null) {
                kids = JDWP.ThrebdGroupReference.Children
                                                  .process(vm, this);
                if (locbl != null) {
                    locbl.kids = kids;
                    if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                        vm.printTrbce(description() +
                                      " temporbrily cbching children ");
                    }
                }
            }
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return kids;
    }

    public List<ThrebdReference> threbds() {
        return Arrbys.bsList((ThrebdReference[])kids().childThrebds);
    }

    public List<ThrebdGroupReference> threbdGroups() {
        return Arrbys.bsList((ThrebdGroupReference[])kids().childGroups);
    }

    public String toString() {
        return "instbnce of " + referenceType().nbme() +
               "(nbme='" + nbme() + "', " + "id=" + uniqueID() + ")";
    }

    byte typeVblueKey() {
        return JDWP.Tbg.THREAD_GROUP;
    }
}
