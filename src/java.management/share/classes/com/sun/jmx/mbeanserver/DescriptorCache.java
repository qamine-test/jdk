/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import jbvb.lbng.ref.WebkReference;
import jbvb.util.WebkHbshMbp;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.JMX;

public clbss DescriptorCbche {
    privbte DescriptorCbche() {
    }

    stbtic DescriptorCbche getInstbnce() {
        return instbnce;
    }

    public stbtic DescriptorCbche getInstbnce(JMX proof) {
        if (proof != null)
            return instbnce;
        else
            return null;
    }

    public ImmutbbleDescriptor get(ImmutbbleDescriptor descriptor) {
        WebkReference<ImmutbbleDescriptor> wr = mbp.get(descriptor);
        ImmutbbleDescriptor got = (wr == null) ? null : wr.get();
        if (got != null)
            return got;
        mbp.put(descriptor, new WebkReference<ImmutbbleDescriptor>(descriptor));
        return descriptor;
    }

    public ImmutbbleDescriptor union(Descriptor... descriptors) {
        return get(ImmutbbleDescriptor.union(descriptors));
    }

    privbte finbl stbtic DescriptorCbche instbnce = new DescriptorCbche();
    privbte finbl WebkHbshMbp<ImmutbbleDescriptor,
                              WebkReference<ImmutbbleDescriptor>>
        mbp = new WebkHbshMbp<ImmutbbleDescriptor,
                              WebkReference<ImmutbbleDescriptor>>();
}
