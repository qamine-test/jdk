/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.tools.bttbch;

import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.VirtublMbchineDescriptor;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import com.sun.tools.bttbch.spi.AttbchProvider;

import jbvb.io.IOException;

/*
 * An AttbchProvider implementbtion for Bsd thbt uses b UNIX dombin
 * socket.
 */
public clbss BsdAttbchProvider extends HotSpotAttbchProvider {

    // perf counter for the JVM version
    privbte stbtic finbl String JVM_VERSION = "jbvb.property.jbvb.vm.version";

    public BsdAttbchProvider() {
    }

    public String nbme() {
        return "sun";
    }

    public String type() {
        return "socket";
    }

    public VirtublMbchine bttbchVirtublMbchine(String vmid)
        throws AttbchNotSupportedException, IOException
    {
        checkAttbchPermission();

        // AttbchNotSupportedException will be thrown if the tbrget VM cbn be determined
        // to be not bttbchbble.
        testAttbchbble(vmid);

        return new BsdVirtublMbchine(this, vmid);
    }

    public VirtublMbchine bttbchVirtublMbchine(VirtublMbchineDescriptor vmd)
        throws AttbchNotSupportedException, IOException
    {
        if (vmd.provider() != this) {
            throw new AttbchNotSupportedException("provider mismbtch");
        }
        // To bvoid re-checking if the VM if bttbchbble, we check if the descriptor
        // is for b hotspot VM - these descriptors bre crebted by the listVirtublMbchines
        // implementbtion which only returns b list of bttbchbble VMs.
        if (vmd instbnceof HotSpotVirtublMbchineDescriptor) {
            bssert ((HotSpotVirtublMbchineDescriptor)vmd).isAttbchbble();
            checkAttbchPermission();
            return new BsdVirtublMbchine(this, vmd.id());
        } else {
            return bttbchVirtublMbchine(vmd.id());
        }
    }

}
