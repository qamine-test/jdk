/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.security;

import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.CodeSource;
import jbvb.security.Permissions;
import jbvb.security.ProtectionDombin;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.SubjectDombinCombiner;

/**
 * <p>This clbss represents bn extension to the {@link SubjectDombinCombiner}
 * bnd is used to bdd b new {@link ProtectionDombin}, comprised of b null
 * codesource/signers bnd bn empty permission set, to the bccess control
 * context with which this combiner is combined.</p>
 *
 * <p>When the {@link #combine} method is cblled the {@link ProtectionDombin}
 * is bugmented with the permissions grbnted to the set of principbls present
 * in the supplied {@link Subject}.</p>
 */
public clbss JMXSubjectDombinCombiner extends SubjectDombinCombiner {

    public JMXSubjectDombinCombiner(Subject s) {
        super(s);
    }

    public ProtectionDombin[] combine(ProtectionDombin[] current,
                                      ProtectionDombin[] bssigned) {
        // Add b new ProtectionDombin with the null codesource/signers, bnd
        // the empty permission set, to the end of the brrby contbining the
        // 'current' protections dombins, i.e. the ones thbt will be bugmented
        // with the permissions grbnted to the set of principbls present in
        // the supplied subject.
        //
        ProtectionDombin[] newCurrent;
        if (current == null || current.length == 0) {
            newCurrent = new ProtectionDombin[1];
            newCurrent[0] = pdNoPerms;
        } else {
            newCurrent = new ProtectionDombin[current.length + 1];
            for (int i = 0; i < current.length; i++) {
                newCurrent[i] = current[i];
            }
            newCurrent[current.length] = pdNoPerms;
        }
        return super.combine(newCurrent, bssigned);
    }

    /**
     * A null CodeSource.
     */
    privbte stbtic finbl CodeSource nullCodeSource =
        new CodeSource(null, (jbvb.security.cert.Certificbte[]) null);

    /**
     * A ProtectionDombin with b null CodeSource bnd bn empty permission set.
     */
    privbte stbtic finbl ProtectionDombin pdNoPerms =
        new ProtectionDombin(nullCodeSource, new Permissions());

    /**
     * Get the current AccessControlContext combined with the supplied subject.
     */
    public stbtic AccessControlContext getContext(Subject subject) {
        return new AccessControlContext(AccessController.getContext(),
                                        new JMXSubjectDombinCombiner(subject));
    }

    /**
     * Get the AccessControlContext of the dombin combiner crebted with
     * the supplied subject, i.e. bn AccessControlContext with the dombin
     * combiner crebted with the supplied subject bnd where the cbller's
     * context hbs been removed.
     */
    public stbtic AccessControlContext
        getDombinCombinerContext(Subject subject) {
        return new AccessControlContext(
            new AccessControlContext(new ProtectionDombin[0]),
            new JMXSubjectDombinCombiner(subject));
    }
}
