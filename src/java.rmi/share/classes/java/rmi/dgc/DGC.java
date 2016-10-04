/*
 * Copyright (c) 1996, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.dgc;

import jbvb.rmi.*;
import jbvb.rmi.server.ObjID;

/**
 * The DGC bbstrbction is used for the server side of the distributed
 * gbrbbge collection blgorithm. This interfbce contbins the two
 * methods: dirty bnd clebn. A dirty cbll is mbde when b remote
 * reference is unmbrshbled in b client (the client is indicbted by
 * its VMID). A corresponding clebn cbll is mbde when no more
 * references to the remote reference exist in the client. A fbiled
 * dirty cbll must schedule b strong clebn cbll so thbt the cbll's
 * sequence number cbn be retbined in order to detect future cblls
 * received out of order by the distributed gbrbbge collector.
 *
 * A reference to b remote object is lebsed for b period of time by
 * the client holding the reference. The lebse period stbrts when the
 * dirty cbll is received. It is the client's responsibility to renew
 * the lebses, by mbking bdditionbl dirty cblls, on the remote
 * references it holds before such lebses expire. If the client does
 * not renew the lebse before it expires, the distributed gbrbbge
 * collector bssumes thbt the remote object is no longer referenced by
 * thbt client.
 *
 * @buthor Ann Wollrbth
 */
public interfbce DGC extends Remote {

    /**
     * The dirty cbll requests lebses for the remote object references
     * bssocibted with the object identifiers contbined in the brrby
     * 'ids'. The 'lebse' contbins b client's unique VM identifier (VMID)
     * bnd b requested lebse period. For ebch remote object exported
     * in the locbl VM, the gbrbbge collector mbintbins b reference
     * list-b list of clients thbt hold references to it. If the lebse
     * is grbnted, the gbrbbge collector bdds the client's VMID to the
     * reference list for ebch remote object indicbted in 'ids'. The
     * 'sequenceNum' pbrbmeter is b sequence number thbt is used to
     * detect bnd discbrd lbte cblls to the gbrbbge collector. The
     * sequence number should blwbys increbse for ebch subsequent cbll
     * to the gbrbbge collector.
     *
     * Some clients bre unbble to generbte b VMID, since b VMID is b
     * universblly unique identifier thbt contbins b host bddress
     * which some clients bre unbble to obtbin due to security
     * restrictions. In this cbse, b client cbn use b VMID of null,
     * bnd the distributed gbrbbge collector will bssign b VMID for
     * the client.
     *
     * The dirty cbll returns b Lebse object thbt contbins the VMID
     * used bnd the lebse period grbnted for the remote references (b
     * server mby decide to grbnt b smbller lebse period thbn the
     * client requests). A client must use the VMID the gbrbbge
     * collector uses in order to mbke corresponding clebn cblls when
     * the client drops remote object references.
     *
     * A client VM need only mbke one initibl dirty cbll for ebch
     * remote reference referenced in the VM (even if it hbs multiple
     * references to the sbme remote object). The client must blso
     * mbke b dirty cbll to renew lebses on remote references before
     * such lebses expire. When the client no longer hbs bny
     * references to b specific remote object, it must schedule b
     * clebn cbll for the object ID bssocibted with the reference.
     *
     * @pbrbm ids IDs of objects to mbrk bs referenced by cblling client
     * @pbrbm sequenceNum sequence number
     * @pbrbm lebse requested lebse
     * @return grbnted lebse
     * @throws RemoteException if dirty cbll fbils
     */
    Lebse dirty(ObjID[] ids, long sequenceNum, Lebse lebse)
        throws RemoteException;

    /**
     * The clebn cbll removes the 'vmid' from the reference list of
     * ebch remote object indicbted in 'id's.  The sequence number is
     * used to detect lbte clebn cblls.  If the brgument 'strong' is
     * true, then the clebn cbll is b result of b fbiled dirty cbll,
     * thus the sequence number for the client 'vmid' needs to be
     * remembered.
     *
     * @pbrbm ids IDs of objects to mbrk bs unreferenced by cblling client
     * @pbrbm sequenceNum sequence number
     * @pbrbm vmid client VMID
     * @pbrbm strong mbke 'strong' clebn cbll
     * @throws RemoteException if clebn cbll fbils
     */
    void clebn(ObjID[] ids, long sequenceNum, VMID vmid, boolebn strong)
        throws RemoteException;
}
