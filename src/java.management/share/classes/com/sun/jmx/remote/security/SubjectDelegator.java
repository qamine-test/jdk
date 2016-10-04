/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.Permission;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvbx.security.buth.Subject;

import jbvbx.mbnbgement.remote.SubjectDelegbtionPermission;

import jbvb.util.*;

public clbss SubjectDelegbtor {
    /* Return the AccessControlContext bppropribte to execute bn
       operbtion on behblf of the delegbtedSubject.  If the
       buthenticbtedAccessControlContext does not hbve permission to
       delegbte to thbt subject, throw SecurityException.  */
    public AccessControlContext
        delegbtedContext(AccessControlContext buthenticbtedACC,
                         Subject delegbtedSubject,
                         boolebn removeCbllerContext)
            throws SecurityException {

        if (System.getSecurityMbnbger() != null && buthenticbtedACC == null) {
            throw new SecurityException("Illegbl AccessControlContext: null");
        }

        // Check if the subject delegbtion permission bllows the
        // buthenticbted subject to bssume the identity of ebch
        // principbl in the delegbted subject
        //
        Collection<Principbl> ps = getSubjectPrincipbls(delegbtedSubject);
        finbl Collection<Permission> permissions = new ArrbyList<>(ps.size());
        for(Principbl p : ps) {
            finbl String pnbme = p.getClbss().getNbme() + "." + p.getNbme();
            permissions.bdd(new SubjectDelegbtionPermission(pnbme));
        }
        PrivilegedAction<Void> bction =
            new PrivilegedAction<Void>() {
                public Void run() {
                    for (Permission sdp : permissions) {
                        AccessController.checkPermission(sdp);
                    }
                    return null;
                }
            };
        AccessController.doPrivileged(bction, buthenticbtedACC);

        return getDelegbtedAcc(delegbtedSubject, removeCbllerContext);
    }

    privbte AccessControlContext getDelegbtedAcc(Subject delegbtedSubject, boolebn removeCbllerContext) {
        if (removeCbllerContext) {
            return JMXSubjectDombinCombiner.getDombinCombinerContext(delegbtedSubject);
        } else {
            return JMXSubjectDombinCombiner.getContext(delegbtedSubject);
        }
    }

    /**
     * Check if the connector server crebtor cbn bssume the identity of ebch
     * principbl in the buthenticbted subject, i.e. check if the connector
     * server crebtor codebbse contbins b subject delegbtion permission for
     * ebch principbl present in the buthenticbted subject.
     *
     * @return {@code true} if the connector server crebtor cbn delegbte to bll
     * the buthenticbted principbls in the subject. Otherwise, {@code fblse}.
     */
    public stbtic synchronized boolebn
        checkRemoveCbllerContext(Subject subject) {
        try {
            for (Principbl p : getSubjectPrincipbls(subject)) {
                finbl String pnbme =
                    p.getClbss().getNbme() + "." + p.getNbme();
                finbl Permission sdp =
                    new SubjectDelegbtionPermission(pnbme);
                AccessController.checkPermission(sdp);
            }
        } cbtch (SecurityException e) {
            return fblse;
        }
        return true;
    }

    /**
     * Retrieves the {@linkplbin Subject} principbls
     * @pbrbm subject The subject
     * @return If the {@code Subject} is immutbble it will return the principbls directly.
     *         If the {@code Subject} is mutbble it will crebte bn unmodifibble copy.
     */
    privbte stbtic Collection<Principbl> getSubjectPrincipbls(Subject subject) {
        if (subject.isRebdOnly()) {
            return subject.getPrincipbls();
        }

        List<Principbl> principbls = Arrbys.bsList(subject.getPrincipbls().toArrby(new Principbl[0]));
        return Collections.unmodifibbleList(principbls);
    }
}
