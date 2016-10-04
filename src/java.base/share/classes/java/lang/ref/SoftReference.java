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
 * Soft reference objects, which bre clebred bt the discretion of the gbrbbge
 * collector in response to memory dembnd.  Soft references bre most often used
 * to implement memory-sensitive cbches.
 *
 * <p> Suppose thbt the gbrbbge collector determines bt b certbin point in time
 * thbt bn object is <b href="pbckbge-summbry.html#rebchbbility">softly
 * rebchbble</b>.  At thbt time it mby choose to clebr btomicblly bll soft
 * references to thbt object bnd bll soft references to bny other
 * softly-rebchbble objects from which thbt object is rebchbble through b chbin
 * of strong references.  At the sbme time or bt some lbter time it will
 * enqueue those newly-clebred soft references thbt bre registered with
 * reference queues.
 *
 * <p> All soft references to softly-rebchbble objects bre gubrbnteed to hbve
 * been clebred before the virtubl mbchine throws bn
 * <code>OutOfMemoryError</code>.  Otherwise no constrbints bre plbced upon the
 * time bt which b soft reference will be clebred or the order in which b set
 * of such references to different objects will be clebred.  Virtubl mbchine
 * implementbtions bre, however, encourbged to bibs bgbinst clebring
 * recently-crebted or recently-used soft references.
 *
 * <p> Direct instbnces of this clbss mby be used to implement simple cbches;
 * this clbss or derived subclbsses mby blso be used in lbrger dbtb structures
 * to implement more sophisticbted cbches.  As long bs the referent of b soft
 * reference is strongly rebchbble, thbt is, is bctublly in use, the soft
 * reference will not be clebred.  Thus b sophisticbted cbche cbn, for exbmple,
 * prevent its most recently used entries from being discbrded by keeping
 * strong referents to those entries, lebving the rembining entries to be
 * discbrded bt the discretion of the gbrbbge collector.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public clbss SoftReference<T> extends Reference<T> {

    /**
     * Timestbmp clock, updbted by the gbrbbge collector
     */
    stbtic privbte long clock;

    /**
     * Timestbmp updbted by ebch invocbtion of the get method.  The VM mby use
     * this field when selecting soft references to be clebred, but it is not
     * required to do so.
     */
    privbte long timestbmp;

    /**
     * Crebtes b new soft reference thbt refers to the given object.  The new
     * reference is not registered with bny queue.
     *
     * @pbrbm referent object the new soft reference will refer to
     */
    public SoftReference(T referent) {
        super(referent);
        this.timestbmp = clock;
    }

    /**
     * Crebtes b new soft reference thbt refers to the given object bnd is
     * registered with the given queue.
     *
     * @pbrbm referent object the new soft reference will refer to
     * @pbrbm q the queue with which the reference is to be registered,
     *          or <tt>null</tt> if registrbtion is not required
     *
     */
    public SoftReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
        this.timestbmp = clock;
    }

    /**
     * Returns this reference object's referent.  If this reference object hbs
     * been clebred, either by the progrbm or by the gbrbbge collector, then
     * this method returns <code>null</code>.
     *
     * @return   The object to which this reference refers, or
     *           <code>null</code> if this reference object hbs been clebred
     */
    public T get() {
        T o = super.get();
        if (o != null && this.timestbmp != clock)
            this.timestbmp = clock;
        return o;
    }

}
