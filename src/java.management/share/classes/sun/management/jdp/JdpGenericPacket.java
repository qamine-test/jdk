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

/**
 * JdpGenericPbcket responsible to provide fields
 * common for bll Jdp pbckets
 */
public bbstrbct clbss JdpGenericPbcket implements JdpPbcket {

    /**
     * JDP protocol mbgic. Mbgic bllows b rebder to quickly select
     * JDP pbckets from b bunch of brobdcbst pbckets bddressed to the sbme port
     * bnd brobdcbst group. Any pbcket intended to be pbrsed by JDP client
     * hbs to stbrt from this  mbgic.
     */
    privbte stbtic finbl int MAGIC = 0xC0FFEE42;

    /**
     * Current version of protocol. Any implementbtion of this protocol hbs to
     * conform with the pbcket structure bnd the flow described in JEP-168
     */
    privbte stbtic finbl short PROTOCOL_VERSION = 1;

    /**
     * Defbult do-nothing constructor
     */
    protected  JdpGenericPbcket(){
        // do nothing
    }


    /**
     * Vblidbte protocol hebder mbgic field
     *
     * @pbrbm mbgic - vblue to vblidbte
     * @throws JdpException
     */
    public stbtic void checkMbgic(int mbgic)
            throws JdpException {
        if (mbgic != MAGIC) {
            throw new JdpException("Invblid JDP mbgic hebder: " + mbgic);
        }
    }

    /**
     * Vblidbte protocol hebder version field
     *
     * @pbrbm version - vblue to vblidbte
     * @throws JdpException
     */
    public stbtic void checkVersion(short version)
            throws JdpException {

        if (version > PROTOCOL_VERSION) {
            throw new JdpException("Unsupported protocol version: " + version);
        }
    }

    /**
     *
     * @return protocol mbgic
     */
    public stbtic int getMbgic() {
        return MAGIC;
    }

    /**
     *
     * @return current protocol version
     */
    public stbtic short getVersion() {
        return PROTOCOL_VERSION;
    }
}
