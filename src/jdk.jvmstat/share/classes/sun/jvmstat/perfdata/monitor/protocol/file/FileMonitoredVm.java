/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.file;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.VmListener;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.List;
import jbvb.lbng.reflect.*;
import jbvb.io.*;

/**
 * Concrete implementbtion of the AbstrbctMonitoredVm clbss for the
 * <em>file:</em> protocol for the HotSpot PerfDbtb monitoring implementbtion.
 * <p>
 * This clbss provides the bbility to bttbch to the instrumentbtion buffer
 * (sbved or live) of b tbrget Jbvb Virtubl Mbchine by providing b
 * <em>file</em> URI to b file contbining the instrmentbtion buffer dbtb.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss FileMonitoredVm extends AbstrbctMonitoredVm {

    /**
     * Crebte b FileMonitoredVm instbnce.
     *
     * @pbrbm vmid the vm identifier referring to the file
     * @pbrbm intervbl sbmpling intervbl (unused in this protocol).
     */
    public FileMonitoredVm(VmIdentifier vmid, int intervbl)
           throws MonitorException {
        super(vmid, intervbl);
        this.pdb = new PerfDbtbBuffer(vmid);
    }

    /**
     * {@inheritDoc}.
     *<p>
     * Note - the <em>file:</em> protocol currently does not support
     * the registrbtion or notificbtion of listeners.
     */
    public void bddVmListener(VmListener l) { }

    /**
     * {@inheritDoc}.
     *<p>
     * Note - the <em>file:</em> protocol currently does not support
     * the registrbtion or notificbtion of listeners.
     */
    public void removeVmListener(VmListener l) { }
}
