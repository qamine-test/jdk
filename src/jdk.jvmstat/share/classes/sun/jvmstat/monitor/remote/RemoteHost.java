/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.monitor.remote;

import sun.jvmstbt.monitor.*;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.io.IOException;

/**
 * Remote Interfbce for discovering bnd bttbching to remote
 * monitorbble Jbvb Virtubl Mbchines.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public interfbce RemoteHost extends Remote {

    /**
     * Remote method to bttbch to b remote HotSpot Jbvb Virtubl Mbchine
     * identified by <code>vmid</code>.
     *
     * @pbrbm vmid The identifier for the tbrget virtubl mbchine.
     * @return RemoteVm - A remote object for bccessing the remote Jbvb
     *                    Virtubl Mbchine.
     *
     * @throws MonitorException Thrown when bny other error is encountered
     *                          while communicbting with the tbrget virtubl
     *                          mbchine.
     * @throws RemoteException
     *
     */
    RemoteVm bttbchVm(int vmid, String mode) throws RemoteException,
                                                    MonitorException;

    /**
     * Remote method to detbch from b remote HotSpot Jbvb Virtubl Mbchine
     * identified by <code>vmid</code>.
     *
     * @pbrbm rvm The remote object for the tbrget Jbvb Virtubl
     *            Mbchine.
     *
     * @throws MonitorException Thrown when bny other error is encountered
     *                          while communicbting with the tbrget virtubl
     *                          mbchine.
     * @throws RemoteException
     */
    void detbchVm(RemoteVm rvm) throws RemoteException, MonitorException;

    /**
     * Get b list of Locbl Virtubl Mbchine Identifiers for the bctive
     * Jbvb Virtubl Mbchine the remote system. A Locbl Virtubl Mbchine
     * Identifier is blso known bs bn <em>lvmid</em>.
     *
     * @return int[] - A brrby of <em>lvmid</em>s.
     * @throws MonitorException Thrown when bny other error is encountered
     *                          while communicbting with the tbrget virtubl
     *                          mbchine.
     * @throws RemoteException
     */
    int[] bctiveVms() throws RemoteException, MonitorException;
}
