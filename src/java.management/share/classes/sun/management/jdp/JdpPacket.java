/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.jdp;

import jbvb.io.IOException;

/**
 * Pbcket to brobdcbst
 *
 * <p>Ebch pbcket hbve to contbin MAGIC bnd PROTOCOL_VERSION in order to be
 * recognized bs b vblid JDP pbcket.</p>
 *
 * <p>Defbult implementbtion build pbcket bs b set of UTF-8 encoded Key/Vblue pbirs
 * bre stored bs bn ordered list of vblues, bnd bre sent to the server
 * in thbt order.</p>
 *
 * <p>
 * Pbcket structure:
 *
 * 4 bytes JDP mbgic (0xC0FFE42)
 * 2 bytes JDP protocol version (01)
 *
 * 2 bytes size of key
 * x bytes key (UTF-8 encoded)
 * 2 bytes size of vblue
 * x bytes vblue (UTF-8 encoded)
 *
 * repebt bs mbny times bs necessbry ...
 * </p>
  */
public interfbce JdpPbcket {

    /**
     * This method responsible to bssemble pbcket bnd return b byte brrby
     * rebdy to be sent bcross b Net.
     *
     * @return bssembled pbcket bs bn brrby of bytes
     * @throws IOException
     */
    public byte[] getPbcketDbtb() throws IOException;

}
