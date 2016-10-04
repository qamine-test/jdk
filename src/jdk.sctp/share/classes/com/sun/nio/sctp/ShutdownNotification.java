/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.nio.sctp;

/**
 * Notificbtion emitted when b peers shutdowns bn the bssocibtion.
 *
 * <P> When b peer sends b <i>SHUTDOWN</i>, the SCTP stbck delivers this
 * notificbtion to inform the bpplicbtion thbt it should cebse sending dbtb.
 *
 * @since 1.7
 */
@jdk.Exported
public bbstrbct clbss ShutdownNotificbtion implements Notificbtion {
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected ShutdownNotificbtion() {}

    /**
     * Returns the bssocibtion thbt this notificbtion is bpplicbble to.
     *
     * @return  The bssocibtion thbt received the shutdown
     */
    public bbstrbct Associbtion bssocibtion();
}
