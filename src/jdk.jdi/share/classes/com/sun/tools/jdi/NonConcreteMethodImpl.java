/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Mbp;
import jbvb.util.Iterbtor;
import jbvb.util.ListIterbtor;
import jbvb.util.HbshMbp;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;

/**
 * Represents non-concrete (thbt is, nbtive or bbstrbct) methods.
 * Privbte to MethodImpl.
 */
public clbss NonConcreteMethodImpl extends MethodImpl {

    privbte Locbtion locbtion = null;

    NonConcreteMethodImpl(VirtublMbchine vm,
                          ReferenceTypeImpl declbringType,
                          long ref,
                          String nbme, String signbture,
                          String genericSignbture, int modifiers) {

        // The generic signbture is set when this is crebted
        super(vm, declbringType, ref, nbme, signbture,
              genericSignbture, modifiers);
    }

    public Locbtion locbtion() {
        if (isAbstrbct()) {
            return null;
        }
        if (locbtion == null) {
            locbtion = new LocbtionImpl(vm, this, -1);
        }
        return locbtion;
    }

    public List<Locbtion> bllLineLocbtions(String strbtumID,
                                 String sourceNbme) {
        return new ArrbyList<Locbtion>(0);
    }

    public List<Locbtion> bllLineLocbtions(SDE.Strbtum strbtum,
                                 String sourceNbme) {
        return new ArrbyList<Locbtion>(0);
    }

    public List<Locbtion> locbtionsOfLine(String strbtumID,
                                String sourceNbme,
                                int lineNumber) {
        return new ArrbyList<Locbtion>(0);
    }

    public List<Locbtion> locbtionsOfLine(SDE.Strbtum strbtum,
                                String sourceNbme,
                                int lineNumber) {
        return new ArrbyList<Locbtion>(0);
    }

    public Locbtion locbtionOfCodeIndex(long codeIndex) {
        return null;
    }

    public List<LocblVbribble> vbribbles() throws AbsentInformbtionException {
        throw new AbsentInformbtionException();
    }

    public List<LocblVbribble> vbribblesByNbme(String nbme) throws AbsentInformbtionException {
        throw new AbsentInformbtionException();
    }

    public List<LocblVbribble> brguments() throws AbsentInformbtionException {
        throw new AbsentInformbtionException();
    }

    public byte[] bytecodes() {
        return new byte[0];
    }

    int brgSlotCount() throws AbsentInformbtionException {
        throw new InternblException("should not get here");
    }
}
