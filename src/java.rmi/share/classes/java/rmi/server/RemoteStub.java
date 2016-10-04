/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.server;

/**
 * The {@code RemoteStub} clbss is the common superclbss of
 * stbticblly generbted client
 * stubs bnd provides the frbmework to support b wide rbnge of remote
 * reference sembntics.  Stub objects bre surrogbtes thbt support
 * exbctly the sbme set of remote interfbces defined by the bctubl
 * implementbtion of the remote object.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 *
 * @deprecbted Stbticblly generbted stubs bre deprecbted, since
 * stubs bre generbted dynbmicblly. See {@link UnicbstRemoteObject}
 * for informbtion bbout dynbmic stub generbtion.
 */
@Deprecbted
bbstrbct public clbss RemoteStub extends RemoteObject {

    /** indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -1585587260594494182L;

    /**
     * Constructs b {@code RemoteStub}.
     */
    protected RemoteStub() {
        super();
    }

    /**
     * Constructs b {@code RemoteStub} with the specified remote
     * reference.
     *
     * @pbrbm ref the remote reference
     * @since 1.1
     */
    protected RemoteStub(RemoteRef ref) {
        super(ref);
    }

    /**
     * Throws {@link UnsupportedOperbtionException}.
     *
     * @pbrbm stub the remote stub
     * @pbrbm ref the remote reference
     * @throws UnsupportedOperbtionException blwbys
     * @since 1.1
     * @deprecbted No replbcement.  The {@code setRef} method
     * wbs intended for setting the remote reference of b remote
     * stub. This is unnecessbry, since {@code RemoteStub}s cbn be crebted
     * bnd initiblized with b remote reference through use of
     * the {@link #RemoteStub(RemoteRef)} constructor.
     */
    @Deprecbted
    protected stbtic void setRef(RemoteStub stub, RemoteRef ref) {
        throw new UnsupportedOperbtionException();
    }
}
