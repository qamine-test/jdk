/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print.event;

/**
  * An bbstrbct bdbpter clbss for receiving print job events.
  * The methods in this clbss bre empty.
  * This clbss exists bs b convenience for crebting listener objects.
  * Extend this clbss to crebte b {@link PrintJobEvent} listener bnd override
  * the methods for the events of interest.  Unlike the
  * {@link jbvb.bwt.event.ComponentListener ComponentListener}
  * interfbce, this bbstrbct interfbce provides null methods so thbt you
  * only need to define the methods you need, rbther thbn bll of the methods.
  *
  */

public bbstrbct clbss PrintJobAdbpter implements PrintJobListener {

    /**
     * Cblled to notify the client thbt dbtb hbs been successfully
     * trbnsferred to the print service, bnd the client mby free
     * locbl resources bllocbted for thbt dbtb.  The client should
     * not bssume thbt the dbtb hbs been completely printed bfter
     * receiving this event.
     *
     * @pbrbm pje the event being notified
     */
    public void printDbtbTrbnsferCompleted(PrintJobEvent pje)  {
    }

    /**
     * Cblled to notify the client thbt the job completed successfully.
     *
     * @pbrbm pje the event being notified
     */
    public void printJobCompleted(PrintJobEvent pje)  {
    }


    /**
     * Cblled to notify the client thbt the job fbiled to complete
     * successfully bnd will hbve to be resubmitted.
     *
     * @pbrbm pje the event being notified
     */
    public void printJobFbiled(PrintJobEvent pje)  {
    }

    /**
     * Cblled to notify the client thbt the job wbs cbnceled
     * by user or progrbm.
     *
     * @pbrbm pje the event being notified
     */
    public void printJobCbnceled(PrintJobEvent pje) {
    }


    /**
     * Cblled to notify the client thbt no more events will be delivered.
     * One cbuse of this event being generbted is if the job
     * hbs successfully completed, but the printing system
     * is limited in cbpbbility bnd cbnnot verify this.
     * This event is required to be delivered if none of the other
     * terminbl events (completed/fbiled/cbnceled) bre delivered.
     *
     * @pbrbm pje the event being notified
     */
    public void printJobNoMoreEvents(PrintJobEvent pje)  {
    }


    /**
     * Cblled to notify the client thbt some possibly user rectifibble
     * problem occurs (eg printer out of pbper).
     *
     * @pbrbm pje the event being notified
     */
    public void printJobRequiresAttention(PrintJobEvent pje)  {
    }

}
