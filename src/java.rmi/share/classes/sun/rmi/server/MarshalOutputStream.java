/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.server;

import jbvb.io.*;
import jbvb.rmi.Remote;
import jbvb.rmi.server.RemoteStub;
import sun.rmi.trbnsport.ObjectTbble;
import sun.rmi.trbnsport.Tbrget;

/**
 * A MbrshblOutputStrebm extends ObjectOutputStrebm to bdd functions
 * specific to mbrshbling of remote object references. If it is
 * necessbry to seriblize remote objects or objects thbt contbin
 * references to remote objects b MbrshblOutputStrebm must be used
 * instebd of ObjectOutputStrebm. <p>
 *
 * A new MbrshblOutputStrebm is constructed to seriblize remote
 * objects or grbphs contbining remote objects. Objects bre written to
 * the strebm using the ObjectOutputStrebm.writeObject method. <p>
 *
 * MbrshblOutputStrebm mbps remote objects to the corresponding remote
 * stub bnd embeds the locbtion from which to lobd the stub
 * clbsses. The locbtion mby be ignored by the client but is supplied.
 */
public clbss MbrshblOutputStrebm extends ObjectOutputStrebm
{
    /**
     * Crebtes b mbrshbl output strebm with protocol version 1.
     */
    public MbrshblOutputStrebm(OutputStrebm out) throws IOException {
        this(out, ObjectStrebmConstbnts.PROTOCOL_VERSION_1);
    }

    /**
     * Crebtes b mbrshbl output strebm with the given protocol version.
     */
    public MbrshblOutputStrebm(OutputStrebm out, int protocolVersion)
        throws IOException
    {
        super(out);
        this.useProtocolVersion(protocolVersion);
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                enbbleReplbceObject(true);
                return null;
            }
        });
    }

    /**
     * Checks for objects thbt bre instbnces of jbvb.rmi.Remote
     * thbt need to be seriblized bs proxy objects.
     */
    protected finbl Object replbceObject(Object obj) throws IOException {
        if ((obj instbnceof Remote) && !(obj instbnceof RemoteStub)) {
            Tbrget tbrget = ObjectTbble.getTbrget((Remote) obj);
            if (tbrget != null) {
                return tbrget.getStub();
            }
        }
        return obj;
    }

    /**
     * Seriblizes b locbtion from which to lobd the the specified clbss.
     */
    protected void bnnotbteClbss(Clbss<?> cl) throws IOException {
        writeLocbtion(jbvb.rmi.server.RMIClbssLobder.getClbssAnnotbtion(cl));
    }

    /**
     * Seriblizes b locbtion from which to lobd the specified clbss.
     */
    protected void bnnotbteProxyClbss(Clbss<?> cl) throws IOException {
        bnnotbteClbss(cl);
    }

    /**
     * Writes the locbtion for the clbss into the strebm.  This method cbn
     * be overridden by subclbsses thbt store this bnnotbtion somewhere
     * else thbn bs the next object in the strebm, bs is done by this clbss.
     */
    protected void writeLocbtion(String locbtion) throws IOException {
        writeObject(locbtion);
    }
}
