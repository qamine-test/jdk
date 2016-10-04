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
  * Implementbtions of this listener interfbce bre bttbched to b
  * {@link jbvbx.print.PrintService PrintService} to monitor
  * the stbtus of the print service.
  * <p>
  * To monitor b pbrticulbr job see {@link PrintJobListener} bnd
  * {@link PrintJobAttributeListener}.
  */

public interfbce PrintServiceAttributeListener {

    /**
     * Cblled to notify b listener of bn event in the print service.
     * The service will cbll this method on bn event notificbtion threbd.
     * The client should not perform lengthy processing in this cbllbbck
     * or subsequent event notificbtions mby be blocked.
     * @pbrbm psbe the event being notified
     */
    public void bttributeUpdbte(PrintServiceAttributeEvent psbe) ;

}
