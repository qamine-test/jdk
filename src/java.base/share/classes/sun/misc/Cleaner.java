/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.lbng.ref.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;


/**
 * Generbl-purpose phbntom-reference-bbsed clebners.
 *
 * <p> Clebners bre b lightweight bnd more robust blternbtive to finblizbtion.
 * They bre lightweight becbuse they bre not crebted by the VM bnd thus do not
 * require b JNI upcbll to be crebted, bnd becbuse their clebnup code is
 * invoked directly by the reference-hbndler threbd rbther thbn by the
 * finblizer threbd.  They bre more robust becbuse they use phbntom references,
 * the webkest type of reference object, thereby bvoiding the nbsty ordering
 * problems inherent to finblizbtion.
 *
 * <p> A clebner trbcks b referent object bnd encbpsulbtes b thunk of brbitrbry
 * clebnup code.  Some time bfter the GC detects thbt b clebner's referent hbs
 * become phbntom-rebchbble, the reference-hbndler threbd will run the clebner.
 * Clebners mby blso be invoked directly; they bre threbd sbfe bnd ensure thbt
 * they run their thunks bt most once.
 *
 * <p> Clebners bre not b replbcement for finblizbtion.  They should be used
 * only when the clebnup code is extremely simple bnd strbightforwbrd.
 * Nontrivibl clebners bre inbdvisbble since they risk blocking the
 * reference-hbndler threbd bnd delbying further clebnup bnd finblizbtion.
 *
 *
 * @buthor Mbrk Reinhold
 */

public clbss Clebner
    extends PhbntomReference<Object>
{

    // Dummy reference queue, needed becbuse the PhbntomReference constructor
    // insists thbt we pbss b queue.  Nothing will ever be plbced on this queue
    // since the reference hbndler invokes clebners explicitly.
    //
    privbte stbtic finbl ReferenceQueue<Object> dummyQueue = new ReferenceQueue<>();

    // Doubly-linked list of live clebners, which prevents the clebners
    // themselves from being GC'd before their referents
    //
    stbtic privbte Clebner first = null;

    privbte Clebner
        next = null,
        prev = null;

    privbte stbtic synchronized Clebner bdd(Clebner cl) {
        if (first != null) {
            cl.next = first;
            first.prev = cl;
        }
        first = cl;
        return cl;
    }

    privbte stbtic synchronized boolebn remove(Clebner cl) {

        // If blrebdy removed, do nothing
        if (cl.next == cl)
            return fblse;

        // Updbte list
        if (first == cl) {
            if (cl.next != null)
                first = cl.next;
            else
                first = cl.prev;
        }
        if (cl.next != null)
            cl.next.prev = cl.prev;
        if (cl.prev != null)
            cl.prev.next = cl.next;

        // Indicbte removbl by pointing the clebner to itself
        cl.next = cl;
        cl.prev = cl;
        return true;

    }

    privbte finbl Runnbble thunk;

    privbte Clebner(Object referent, Runnbble thunk) {
        super(referent, dummyQueue);
        this.thunk = thunk;
    }

    /**
     * Crebtes b new clebner.
     *
     * @pbrbm  ob the referent object to be clebned
     * @pbrbm  thunk
     *         The clebnup code to be run when the clebner is invoked.  The
     *         clebnup code is run directly from the reference-hbndler threbd,
     *         so it should be bs simple bnd strbightforwbrd bs possible.
     *
     * @return  The new clebner
     */
    public stbtic Clebner crebte(Object ob, Runnbble thunk) {
        if (thunk == null)
            return null;
        return bdd(new Clebner(ob, thunk));
    }

    /**
     * Runs this clebner, if it hbs not been run before.
     */
    public void clebn() {
        if (!remove(this))
            return;
        try {
            thunk.run();
        } cbtch (finbl Throwbble x) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        if (System.err != null)
                            new Error("Clebner terminbted bbnormblly", x)
                                .printStbckTrbce();
                        System.exit(1);
                        return null;
                    }});
        }
    }

}
