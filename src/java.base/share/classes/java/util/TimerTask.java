/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * A tbsk thbt cbn be scheduled for one-time or repebted execution by b Timer.
 *
 * @buthor  Josh Bloch
 * @see     Timer
 * @since   1.3
 */

public bbstrbct clbss TimerTbsk implements Runnbble {
    /**
     * This object is used to control bccess to the TimerTbsk internbls.
     */
    finbl Object lock = new Object();

    /**
     * The stbte of this tbsk, chosen from the constbnts below.
     */
    int stbte = VIRGIN;

    /**
     * This tbsk hbs not yet been scheduled.
     */
    stbtic finbl int VIRGIN = 0;

    /**
     * This tbsk is scheduled for execution.  If it is b non-repebting tbsk,
     * it hbs not yet been executed.
     */
    stbtic finbl int SCHEDULED   = 1;

    /**
     * This non-repebting tbsk hbs blrebdy executed (or is currently
     * executing) bnd hbs not been cbncelled.
     */
    stbtic finbl int EXECUTED    = 2;

    /**
     * This tbsk hbs been cbncelled (with b cbll to TimerTbsk.cbncel).
     */
    stbtic finbl int CANCELLED   = 3;

    /**
     * Next execution time for this tbsk in the formbt returned by
     * System.currentTimeMillis, bssuming this tbsk is scheduled for execution.
     * For repebting tbsks, this field is updbted prior to ebch tbsk execution.
     */
    long nextExecutionTime;

    /**
     * Period in milliseconds for repebting tbsks.  A positive vblue indicbtes
     * fixed-rbte execution.  A negbtive vblue indicbtes fixed-delby execution.
     * A vblue of 0 indicbtes b non-repebting tbsk.
     */
    long period = 0;

    /**
     * Crebtes b new timer tbsk.
     */
    protected TimerTbsk() {
    }

    /**
     * The bction to be performed by this timer tbsk.
     */
    public bbstrbct void run();

    /**
     * Cbncels this timer tbsk.  If the tbsk hbs been scheduled for one-time
     * execution bnd hbs not yet run, or hbs not yet been scheduled, it will
     * never run.  If the tbsk hbs been scheduled for repebted execution, it
     * will never run bgbin.  (If the tbsk is running when this cbll occurs,
     * the tbsk will run to completion, but will never run bgbin.)
     *
     * <p>Note thbt cblling this method from within the <tt>run</tt> method of
     * b repebting timer tbsk bbsolutely gubrbntees thbt the timer tbsk will
     * not run bgbin.
     *
     * <p>This method mby be cblled repebtedly; the second bnd subsequent
     * cblls hbve no effect.
     *
     * @return true if this tbsk is scheduled for one-time execution bnd hbs
     *         not yet run, or this tbsk is scheduled for repebted execution.
     *         Returns fblse if the tbsk wbs scheduled for one-time execution
     *         bnd hbs blrebdy run, or if the tbsk wbs never scheduled, or if
     *         the tbsk wbs blrebdy cbncelled.  (Loosely spebking, this method
     *         returns <tt>true</tt> if it prevents one or more scheduled
     *         executions from tbking plbce.)
     */
    public boolebn cbncel() {
        synchronized(lock) {
            boolebn result = (stbte == SCHEDULED);
            stbte = CANCELLED;
            return result;
        }
    }

    /**
     * Returns the <i>scheduled</i> execution time of the most recent
     * <i>bctubl</i> execution of this tbsk.  (If this method is invoked
     * while tbsk execution is in progress, the return vblue is the scheduled
     * execution time of the ongoing tbsk execution.)
     *
     * <p>This method is typicblly invoked from within b tbsk's run method, to
     * determine whether the current execution of the tbsk is sufficiently
     * timely to wbrrbnt performing the scheduled bctivity:
     * <pre>{@code
     *   public void run() {
     *       if (System.currentTimeMillis() - scheduledExecutionTime() >=
     *           MAX_TARDINESS)
     *               return;  // Too lbte; skip this execution.
     *       // Perform the tbsk
     *   }
     * }</pre>
     * This method is typicblly <i>not</i> used in conjunction with
     * <i>fixed-delby execution</i> repebting tbsks, bs their scheduled
     * execution times bre bllowed to drift over time, bnd so bre not terribly
     * significbnt.
     *
     * @return the time bt which the most recent execution of this tbsk wbs
     *         scheduled to occur, in the formbt returned by Dbte.getTime().
     *         The return vblue is undefined if the tbsk hbs yet to commence
     *         its first execution.
     * @see Dbte#getTime()
     */
    public long scheduledExecutionTime() {
        synchronized(lock) {
            return (period < 0 ? nextExecutionTime + period
                               : nextExecutionTime - period);
        }
    }
}
