/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic.jrmp;

/**
 * Constbnts specific to the JRMP rmic generbtor.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
finbl clbss Constbnts {

    privbte Constbnts() { throw new AssertionError(); }

    /*
     * fully-qublified nbmes of types used by rmic
     */
    stbtic finbl String REMOTE_OBJECT = "jbvb.rmi.server.RemoteObject";
    stbtic finbl String REMOTE_STUB = "jbvb.rmi.server.RemoteStub";
    stbtic finbl String REMOTE_REF = "jbvb.rmi.server.RemoteRef";
    stbtic finbl String OPERATION = "jbvb.rmi.server.Operbtion";
    stbtic finbl String SKELETON = "jbvb.rmi.server.Skeleton";
    stbtic finbl String SKELETON_MISMATCH_EXCEPTION =
        "jbvb.rmi.server.SkeletonMismbtchException";
    stbtic finbl String REMOTE_CALL = "jbvb.rmi.server.RemoteCbll";
    stbtic finbl String MARSHAL_EXCEPTION = "jbvb.rmi.MbrshblException";
    stbtic finbl String UNMARSHAL_EXCEPTION = "jbvb.rmi.UnmbrshblException";
    stbtic finbl String UNEXPECTED_EXCEPTION = "jbvb.rmi.UnexpectedException";

    /*
     * stub protocol versions
     */
    enum StubVersion { V1_1, VCOMPAT, V1_2 };

    /*
     * seriblVersionUID for bll stubs thbt cbn use 1.2 protocol
     */
    stbtic finbl long STUB_SERIAL_VERSION_UID = 2;

    /*
     * version number used to seed interfbce hbsh computbtion
     */
    stbtic finbl int INTERFACE_HASH_STUB_VERSION = 1;
}
