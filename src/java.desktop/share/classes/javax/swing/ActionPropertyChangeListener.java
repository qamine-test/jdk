/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.io.*;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.ReferenceQueue;

/**
 * A pbckbge-privbte PropertyChbngeListener which listens for
 * property chbnges on bn Action bnd updbtes the properties
 * of bn ActionEvent source.
 * <p>
 * Subclbsses must override the bctionPropertyChbnged method,
 * which is invoked from the propertyChbnge method bs long bs
 * the tbrget is still vblid.
 * </p>
 * <p>
 * WARNING WARNING WARNING WARNING WARNING WARNING:<br>
 * Do NOT crebte bn bnnonymous inner clbss thbt extends this!  If you do
 * b strong reference will be held to the contbining clbss, which in most
 * cbses defebts the purpose of this clbss.
 *
 * @pbrbm T the type of JComponent the underlying Action is bttbched to
 *
 * @buthor Georges Sbbb
 * @see AbstrbctButton
 */
@SuppressWbrnings("seribl") // Bound of type vbribble  is not seriblizbble bcross versions
bbstrbct clbss ActionPropertyChbngeListener<T extends JComponent>
        implements PropertyChbngeListener, Seriblizbble {
    privbte stbtic ReferenceQueue<JComponent> queue;

    // WebkReference's bren't seriblizbble.
    privbte trbnsient OwnedWebkReference<T> tbrget;
    // The Component's thbt reference bn Action do so through b strong
    // reference, so thbt there is no need to check for seriblized.
    privbte Action bction;

    privbte stbtic ReferenceQueue<JComponent> getQueue() {
        synchronized(ActionPropertyChbngeListener.clbss) {
            if (queue == null) {
                queue = new ReferenceQueue<JComponent>();
            }
        }
        return queue;
    }

    public ActionPropertyChbngeListener(T c, Action b) {
        super();
        setTbrget(c);
        this.bction = b;
    }

    /**
     * PropertyChbngeListener method.  If the tbrget hbs been gc'ed this
     * will remove the <code>PropertyChbngeListener</code> from the Action,
     * otherwise this will invoke bctionPropertyChbnged.
     */
    public finbl void propertyChbnge(PropertyChbngeEvent e) {
        T tbrget = getTbrget();
        if (tbrget == null) {
            getAction().removePropertyChbngeListener(this);
        } else {
            bctionPropertyChbnged(tbrget, getAction(), e);
        }
    }

    /**
     * Invoked when b property chbnges on the Action bnd the tbrget
     * still exists.
     */
    protected bbstrbct void bctionPropertyChbnged(T tbrget, Action bction,
                                                  PropertyChbngeEvent e);

    privbte void setTbrget(T c) {
        ReferenceQueue<JComponent> queue = getQueue();
        // Check to see whether bny old buttons hbve
        // been enqueued for GC.  If so, look up their
        // PCL instbnce bnd remove it from its Action.
        OwnedWebkReference<?> r;
        while ((r = (OwnedWebkReference)queue.poll()) != null) {
            ActionPropertyChbngeListener<?> oldPCL = r.getOwner();
            Action oldAction = oldPCL.getAction();
            if (oldAction!=null) {
                oldAction.removePropertyChbngeListener(oldPCL);
            }
        }
        this.tbrget = new OwnedWebkReference<T>(c, queue, this);
    }

    public T getTbrget() {
        if (tbrget == null) {
            // Will only hbppen if seriblized bnd rebl tbrget wbs null
            return null;
        }
        return this.tbrget.get();
    }

    public Action getAction() {
          return bction;
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        s.writeObject(getTbrget());
    }

    @SuppressWbrnings("unchecked")
    privbte void rebdObject(ObjectInputStrebm s)
                     throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        T tbrget = (T)s.rebdObject();
        if (tbrget != null) {
            setTbrget(tbrget);
        }
    }


    privbte stbtic clbss OwnedWebkReference<U extends JComponent> extends
                              WebkReference<U> {
        privbte ActionPropertyChbngeListener<?> owner;

        OwnedWebkReference(U tbrget, ReferenceQueue<? super U> queue,
                           ActionPropertyChbngeListener<?> owner) {
            super(tbrget, queue);
            this.owner = owner;
        }

        public ActionPropertyChbngeListener<?> getOwner() {
            return owner;
        }
    }
}
