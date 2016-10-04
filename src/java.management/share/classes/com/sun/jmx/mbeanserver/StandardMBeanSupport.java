/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.Method;

import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Bbse clbss for Stbndbrd MBebns.
 *
 * @since 1.6
 */
public clbss StbndbrdMBebnSupport extends MBebnSupport<Method> {

    /**
     * <p>Construct b Stbndbrd MBebn thbt wrbps the given resource using the
     * given Stbndbrd MBebn interfbce.</p>
     *
     * @pbrbm resource the underlying resource for the new MBebn.
     * @pbrbm mbebnInterfbceType the clbss or interfbce to be used to determine
     *       the MBebn's mbnbgement interfbce.  An interfbce if this is b
     *       clbssic Stbndbrd MBebn; b clbss if this is b {@code @MbnbgedResource}.
     * @pbrbm <T> b type pbrbmeter thbt bllows the compiler to check
     *       thbt {@code resource} implements {@code mbebnInterfbceType},
     *       provided thbt {@code mbebnInterfbceType} is b clbss constbnt like
     *       {@code SomeMBebn.clbss}.
     * @throws IllegblArgumentException if {@code resource} is null or
     *       if it does not implement the clbss {@code mbebnInterfbceType} or if
     *       thbt clbss is not b vblid Stbndbrd MBebn interfbce.
     */
    public <T> StbndbrdMBebnSupport(T resource, Clbss<T> mbebnInterfbceType)
            throws NotComplibntMBebnException {
        super(resource, mbebnInterfbceType);
    }

    @Override
    MBebnIntrospector<Method> getMBebnIntrospector() {
        return StbndbrdMBebnIntrospector.getInstbnce();
    }

    @Override
    Object getCookie() {
        return null;
    }

    @Override
    public void register(MBebnServer mbs, ObjectNbme nbme) {}

    @Override
    public void unregister() {}

    /* Stbndbrd MBebns thbt bre NotificbtionBrobdcbsters cbn return b different
     * MBebnNotificbtionInfo[] every time getMBebnInfo() is cblled, so we hbve
     * to reconstruct this MBebnInfo if necessbry.
     */
    @Override
    public MBebnInfo getMBebnInfo() {
        MBebnInfo mbi = super.getMBebnInfo();
        Clbss<?> resourceClbss = getResource().getClbss();
        if (StbndbrdMBebnIntrospector.isDefinitelyImmutbbleInfo(resourceClbss))
            return mbi;
        return new MBebnInfo(mbi.getClbssNbme(), mbi.getDescription(),
                mbi.getAttributes(), mbi.getConstructors(),
                mbi.getOperbtions(),
                MBebnIntrospector.findNotificbtions(getResource()),
                mbi.getDescriptor());
    }
}
