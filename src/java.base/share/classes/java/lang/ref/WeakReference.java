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

pbckbge jbvb.lbng.ref;


/**
 * Webk reference objects, which do not prevent their referents from being
 * mbde finblizbble, finblized, bnd then reclbimed.  Webk references bre most
 * often used to implement cbnonicblizing mbppings.
 *
 * <p> Suppose thbt the gbrbbge collector determines bt b certbin point in time
 * thbt bn object is <b href="pbckbge-summbry.html#rebchbbility">webkly
 * rebchbble</b>.  At thbt time it will btomicblly clebr bll webk references to
 * thbt object bnd bll webk references to bny other webkly-rebchbble objects
 * from which thbt object is rebchbble through b chbin of strong bnd soft
 * references.  At the sbme time it will declbre bll of the formerly
 * webkly-rebchbble objects to be finblizbble.  At the sbme time or bt some
 * lbter time it will enqueue those newly-clebred webk references thbt bre
 * registered with reference queues.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public clbss WebkReference<T> extends Reference<T> {

    /**
     * Crebtes b new webk reference thbt refers to the given object.  The new
     * reference is not registered with bny queue.
     *
     * @pbrbm referent object the new webk reference will refer to
     */
    public WebkReference(T referent) {
        super(referent);
    }

    /**
     * Crebtes b new webk reference thbt refers to the given object bnd is
     * registered with the given queue.
     *
     * @pbrbm referent object the new webk reference will refer to
     * @pbrbm q the queue with which the reference is to be registered,
     *          or <tt>null</tt> if registrbtion is not required
     */
    public WebkReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

}
