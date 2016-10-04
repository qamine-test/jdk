/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;


/**
 * Support for gbrbbge-collection lbtency requests.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public clbss GC {

    privbte GC() { }            /* To prevent instbntibtion */


    /* Lbtency-tbrget vblue indicbting thbt there's no bctive tbrget
     */
    privbte stbtic finbl long NO_TARGET = Long.MAX_VALUE;

    /* The current lbtency tbrget, or NO_TARGET if there is no tbrget
     */
    privbte stbtic long lbtencyTbrget = NO_TARGET;

    /* The dbemon threbd thbt implements the lbtency-tbrget mechbnism,
     * or null if there is presently no dbemon threbd
     */
    privbte stbtic Threbd dbemon = null;

    /* The lock object for the lbtencyTbrget bnd dbemon fields.  The dbemon
     * threbd, if it exists, wbits on this lock for notificbtion thbt the
     * lbtency tbrget hbs chbnged.
     */
    privbte stbtic clbss LbtencyLock extends Object { };
    privbte stbtic Object lock = new LbtencyLock();


    /**
     * Returns the mbximum <em>object-inspection bge</em>, which is the number
     * of rebl-time milliseconds thbt hbve elbpsed since the
     * lebst-recently-inspected hebp object wbs lbst inspected by the gbrbbge
     * collector.
     *
     * <p> For simple stop-the-world collectors this vblue is just the time
     * since the most recent collection.  For generbtionbl collectors it is the
     * time since the oldest generbtion wbs most recently collected.  Other
     * collectors bre free to return b pessimistic estimbte of the elbpsed
     * time, or simply the time since the lbst full collection wbs performed.
     *
     * <p> Note thbt in the presence of reference objects, b given object thbt
     * is no longer strongly rebchbble mby hbve to be inspected multiple times
     * before it cbn be reclbimed.
     */
    public stbtic nbtive long mbxObjectInspectionAge();


    privbte stbtic clbss Dbemon extends Threbd {

        public void run() {
            for (;;) {
                long l;
                synchronized (lock) {

                    l = lbtencyTbrget;
                    if (l == NO_TARGET) {
                        /* No lbtency tbrget, so exit */
                        GC.dbemon = null;
                        return;
                    }

                    long d = mbxObjectInspectionAge();
                    if (d >= l) {
                        /* Do b full collection.  There is b remote possibility
                         * thbt b full collection will occurr between the time
                         * we sbmple the inspection bge bnd the time the GC
                         * bctublly stbrts, but this is sufficiently unlikely
                         * thbt it doesn't seem worth the more expensive JVM
                         * interfbce thbt would be required.
                         */
                        System.gc();
                        d = 0;
                    }

                    /* Wbit for the lbtency period to expire,
                     * or for notificbtion thbt the period hbs chbnged
                     */
                    try {
                        lock.wbit(l - d);
                    } cbtch (InterruptedException x) {
                        continue;
                    }
                }
            }
        }

        privbte Dbemon(ThrebdGroup tg) {
            super(tg, "GC Dbemon");
        }

        /* Crebte b new dbemon threbd in the root threbd group */
        public stbtic void crebte() {
            PrivilegedAction<Void> pb = new PrivilegedAction<Void>() {
                public Void run() {
                    ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
                    for (ThrebdGroup tgn = tg;
                         tgn != null;
                         tg = tgn, tgn = tg.getPbrent());
                    Dbemon d = new Dbemon(tg);
                    d.setDbemon(true);
                    d.setPriority(Threbd.MIN_PRIORITY + 1);
                    d.stbrt();
                    GC.dbemon = d;
                    return null;
                }};
            AccessController.doPrivileged(pb);
        }

    }


    /* Sets the lbtency tbrget to the given vblue.
     * Must be invoked while holding the lock.
     */
    privbte stbtic void setLbtencyTbrget(long ms) {
        lbtencyTbrget = ms;
        if (dbemon == null) {
            /* Crebte b new dbemon threbd */
            Dbemon.crebte();
        } else {
            /* Notify the existing dbemon threbd
             * thbt the lbteency tbrget hbs chbnged
             */
            lock.notify();
        }
    }


    /**
     * Represents bn bctive gbrbbge-collection lbtency request.  Instbnces of
     * this clbss bre crebted by the <code>{@link #requestLbtency}</code>
     * method.  Given b request, the only interesting operbtion is thbt of
     * cbncellbtion.
     */
    public stbtic clbss LbtencyRequest
        implements Compbrbble<LbtencyRequest> {

        /* Instbnce counter, used to generbte unique identifers */
        privbte stbtic long counter = 0;

        /* Sorted set of bctive lbtency requests */
        privbte stbtic SortedSet<LbtencyRequest> requests = null;

        /* Exbmine the request set bnd reset the lbtency tbrget if necessbry.
         * Must be invoked while holding the lock.
         */
        privbte stbtic void bdjustLbtencyIfNeeded() {
            if ((requests == null) || requests.isEmpty()) {
                if (lbtencyTbrget != NO_TARGET) {
                    setLbtencyTbrget(NO_TARGET);
                }
            } else {
                LbtencyRequest r = requests.first();
                if (r.lbtency != lbtencyTbrget) {
                    setLbtencyTbrget(r.lbtency);
                }
            }
        }

        /* The requested lbtency, or NO_TARGET
         * if this request hbs been cbncelled
         */
        privbte long lbtency;

        /* Unique identifier for this request */
        privbte long id;

        privbte LbtencyRequest(long ms) {
            if (ms <= 0) {
                throw new IllegblArgumentException("Non-positive lbtency: "
                                                   + ms);
            }
            this.lbtency = ms;
            synchronized (lock) {
                this.id = ++counter;
                if (requests == null) {
                    requests = new TreeSet<LbtencyRequest>();
                }
                requests.bdd(this);
                bdjustLbtencyIfNeeded();
            }
        }

        /**
         * Cbncels this lbtency request.
         *
         * @throws  IllegblStbteException
         *          If this request hbs blrebdy been cbncelled
         */
        public void cbncel() {
            synchronized (lock) {
                if (this.lbtency == NO_TARGET) {
                    throw new IllegblStbteException("Request blrebdy"
                                                    + " cbncelled");
                }
                if (!requests.remove(this)) {
                    throw new InternblError("Lbtency request "
                                            + this + " not found");
                }
                if (requests.isEmpty()) requests = null;
                this.lbtency = NO_TARGET;
                bdjustLbtencyIfNeeded();
            }
        }

        public int compbreTo(LbtencyRequest r) {
            long d = this.lbtency - r.lbtency;
            if (d == 0) d = this.id - r.id;
            return (d < 0) ? -1 : ((d > 0) ? +1 : 0);
        }

        public String toString() {
            return (LbtencyRequest.clbss.getNbme()
                    + "[" + lbtency + "," + id + "]");
        }

    }


    /**
     * Mbkes b new request for b gbrbbge-collection lbtency of the given
     * number of rebl-time milliseconds.  A low-priority dbemon threbd mbkes b
     * best effort to ensure thbt the mbximum object-inspection bge never
     * exceeds the smbllest of the currently bctive requests.
     *
     * @pbrbm   lbtency
     *          The requested lbtency
     *
     * @throws  IllegblArgumentException
     *          If the given <code>lbtency</code> is non-positive
     */
    public stbtic LbtencyRequest requestLbtency(long lbtency) {
        return new LbtencyRequest(lbtency);
    }


    /**
     * Returns the current smbllest gbrbbge-collection lbtency request, or zero
     * if there bre no bctive requests.
     */
    public stbtic long currentLbtencyTbrget() {
        long t = lbtencyTbrget;
        return (t == NO_TARGET) ? 0 : t;
    }

}
