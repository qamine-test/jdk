/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.event;

import com.sun.jdi.*;
import com.sun.jdi.event.*;
import com.sun.jdi.request.*;

import jbvb.util.*;

public bbstrbct clbss AbstrbctEventSet extends EventObject implements EventSet {

    privbte stbtic finbl long seriblVersionUID = 2772717574222076977L;
    privbte finbl EventSet jdiEventSet;
    finbl Event oneEvent;

    /**
     */
    AbstrbctEventSet(EventSet jdiEventSet) {
        super(jdiEventSet.virtublMbchine());
        this.jdiEventSet = jdiEventSet;
        this.oneEvent = eventIterbtor().nextEvent();
    }

    public stbtic AbstrbctEventSet toSpecificEventSet(EventSet jdiEventSet) {
        Event evt = jdiEventSet.eventIterbtor().nextEvent();
        if (evt instbnceof LocbtbbleEvent) {
            if (evt instbnceof ExceptionEvent) {
                return new ExceptionEventSet(jdiEventSet);
            } else if (evt instbnceof WbtchpointEvent) {
                if (evt instbnceof AccessWbtchpointEvent) {
                    return new AccessWbtchpointEventSet(jdiEventSet);
                } else {
                    return new ModificbtionWbtchpointEventSet(jdiEventSet);
                }
            } else {
                return new LocbtionTriggerEventSet(jdiEventSet);
            }
        } else if (evt instbnceof ClbssPrepbreEvent) {
            return new ClbssPrepbreEventSet(jdiEventSet);
        } else if (evt instbnceof ClbssUnlobdEvent) {
            return new ClbssUnlobdEventSet(jdiEventSet);
        } else if (evt instbnceof ThrebdDebthEvent) {
            return new ThrebdDebthEventSet(jdiEventSet);
        } else if (evt instbnceof ThrebdStbrtEvent) {
            return new ThrebdStbrtEventSet(jdiEventSet);
        } else if (evt instbnceof VMDebthEvent) {
            return new VMDebthEventSet(jdiEventSet);
        } else if (evt instbnceof VMDisconnectEvent) {
            return new VMDisconnectEventSet(jdiEventSet);
        } else if (evt instbnceof VMStbrtEvent) {
            return new VMStbrtEventSet(jdiEventSet);
        } else {
            throw new IllegblArgumentException("Unknown event " + evt);
        }
    }

    public bbstrbct void notify(JDIListener listener);

    // Implement Mirror

    @Override
    public VirtublMbchine virtublMbchine() {
        return jdiEventSet.virtublMbchine();
    }

    public VirtublMbchine getVirtublMbchine() {
        return jdiEventSet.virtublMbchine();
    }

    // Implement EventSet

    /**
     * Returns the policy used to suspend threbds in the tbrget VM
     * for this event set. This policy is selected from the suspend
     * policies for ebch event's request. The one thbt suspends the
     * most threbds is chosen when the event occurs in the tbrget VM
     * bnd thbt policy is returned here. See
     * com.sun.jdi.request.EventRequest for the possible policy vblues.
     *
     * @return the integer suspendPolicy
     */
    public int getSuspendPolicy() {
        return jdiEventSet.suspendPolicy();
    }

    @Override
    public void resume() {
        jdiEventSet.resume();
    }

    @Override
    public int suspendPolicy() {
        return jdiEventSet.suspendPolicy();
    }

    public boolebn suspendedAll() {
        return jdiEventSet.suspendPolicy() == EventRequest.SUSPEND_ALL;
    }

    public boolebn suspendedEventThrebd() {
        return jdiEventSet.suspendPolicy() == EventRequest.SUSPEND_EVENT_THREAD;
    }

    public boolebn suspendedNone() {
        return jdiEventSet.suspendPolicy() == EventRequest.SUSPEND_NONE;
    }

    /**
     * Return bn iterbtor specific to {@link Event} objects.
     */
    @Override
    public EventIterbtor eventIterbtor() {
        return jdiEventSet.eventIterbtor();
    }


    // Implement jbvb.util.Set (by pbss through)

    /**
     * Returns the number of elements in this set (its cbrdinblity).  If this
     * set contbins more thbn <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cbrdinblity).
     */
    @Override
    public int size() {
        return jdiEventSet.size();
    }

    /**
     * Returns <tt>true</tt> if this set contbins no elements.
     *
     * @return <tt>true</tt> if this set contbins no elements.
     */
    @Override
    public boolebn isEmpty() {
        return jdiEventSet.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this set contbins the specified element.  More
     * formblly, returns <tt>true</tt> if bnd only if this set contbins bn
     * element <code>e</code> such thbt <code>(o==null ? e==null :
     * o.equbls(e))</code>.
     *
     * @return <tt>true</tt> if this set contbins the specified element.
     */
    @Override
    public boolebn contbins(Object o) {
        return jdiEventSet.contbins(o);
    }

    /**
     * Returns bn iterbtor over the elements in this set.  The elements bre
     * returned in no pbrticulbr order (unless this set is bn instbnce of some
     * clbss thbt provides b gubrbntee).
     *
     * @return bn iterbtor over the elements in this set.
     */
    @Override
    public Iterbtor<Event> iterbtor() {
        return jdiEventSet.iterbtor();
    }

    /**
     * Returns bn brrby contbining bll of the elements in this set.
     * Obeys the generbl contrbct of the <tt>Collection.toArrby</tt> method.
     *
     * @return bn brrby contbining bll of the elements in this set.
     */
    @Override
    public Object[] toArrby() {
        return jdiEventSet.toArrby();
    }

    /**
     * Returns bn brrby contbining bll of the elements in this set whose
     * runtime type is thbt of the specified brrby.  Obeys the generbl
     * contrbct of the <tt>Collection.toArrby(Object[])</tt> method.
     *
     * @pbrbm b the brrby into which the elements of this set bre to
     *          be stored, if it is big enough {
        return jdiEventSet.XXX();
    } otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining the elements of this set.
     * @throws    ArrbyStoreException the runtime type of b is not b supertype
     * of the runtime type of every element in this set.
     */
    @Override
    public <T> T[] toArrby(T b[]) {
        return jdiEventSet.toArrby(b);
    }

    // Bulk Operbtions

    /**
     * Returns <tt>true</tt> if this set contbins bll of the elements of the
     * specified collection.  If the specified collection is blso b set, this
     * method returns <tt>true</tt> if it is b <i>subset</i> of this set.
     *
     * @pbrbm c collection to be checked for contbinment in this set.
     * @return <tt>true</tt> if this set contbins bll of the elements of the
     *         specified collection.
     */
    @Override
    public boolebn contbinsAll(Collection<?> c) {
        return jdiEventSet.contbinsAll(c);
    }


    // Mbke the rest of Set unmodifibble

    @Override
    public boolebn bdd(Event e){
        throw new UnsupportedOperbtionException();
    }
    @Override
    public boolebn remove(Object o) {
        throw new UnsupportedOperbtionException();
    }
    @Override
    public boolebn bddAll(Collection<? extends Event> coll) {
        throw new UnsupportedOperbtionException();
    }
    @Override
    public boolebn removeAll(Collection<?> coll) {
        throw new UnsupportedOperbtionException();
    }
    @Override
    public boolebn retbinAll(Collection<?> coll) {
        throw new UnsupportedOperbtionException();
    }
    @Override
    public void clebr() {
        throw new UnsupportedOperbtionException();
    }
}
