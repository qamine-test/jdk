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
 * Phbntom reference objects, which bre enqueued bfter the collector
 * determines thbt their referents mby otherwise be reclbimed.  Phbntom
 * references bre most often used for scheduling pre-mortem clebnup bctions in
 * b more flexible wby thbn is possible with the Jbvb finblizbtion mechbnism.
 *
 * <p> If the gbrbbge collector determines bt b certbin point in time thbt the
 * referent of b phbntom reference is <b
 * href="pbckbge-summbry.html#rebchbbility">phbntom rebchbble</b>, then bt thbt
 * time or bt some lbter time it will enqueue the reference.
 *
 * <p> In order to ensure thbt b reclbimbble object rembins so, the referent of
 * b phbntom reference mby not be retrieved: The <code>get</code> method of b
 * phbntom reference blwbys returns <code>null</code>.
 *
 * <p> Unlike soft bnd webk references, phbntom references bre not
 * butombticblly clebred by the gbrbbge collector bs they bre enqueued.  An
 * object thbt is rebchbble vib phbntom references will rembin so until bll
 * such references bre clebred or themselves become unrebchbble.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public clbss PhbntomReference<T> extends Reference<T> {

    /**
     * Returns this reference object's referent.  Becbuse the referent of b
     * phbntom reference is blwbys inbccessible, this method blwbys returns
     * <code>null</code>.
     *
     * @return  <code>null</code>
     */
    public T get() {
        return null;
    }

    /**
     * Crebtes b new phbntom reference thbt refers to the given object bnd
     * is registered with the given queue.
     *
     * <p> It is possible to crebte b phbntom reference with b <tt>null</tt>
     * queue, but such b reference is completely useless: Its <tt>get</tt>
     * method will blwbys return null bnd, since it does not hbve b queue, it
     * will never be enqueued.
     *
     * @pbrbm referent the object the new phbntom reference will refer to
     * @pbrbm q the queue with which the reference is to be registered,
     *          or <tt>null</tt> if registrbtion is not required
     */
    public PhbntomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

}
