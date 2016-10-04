/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp;




/**
 * Defines the interfbce of the object in chbrge of encoding bnd decoding SNMP pbckets.
 * <P>
 * You will not usublly need to use this interfbce, except if you
 * decide to replbce the defbult implementbtion <CODE>SnmpPduFbctoryBER</CODE>.
 * <P>
 * An <CODE>SnmpPduFbctory</CODE> object is bttbched to bn
 * {@link com.sun.jmx.snmp.dbemon.SnmpAdbptorServer SNMP protocol bdbptor}
 * or bn {@link com.sun.jmx.snmp.SnmpPeer SnmpPeer}.
 * It is used ebch time bn SNMP pbcket needs to be encoded or decoded.
 * <BR>{@link com.sun.jmx.snmp.SnmpPduFbctoryBER SnmpPduFbctoryBER} is the defbult
 * implementbtion.
 * It simply bpplies the stbndbrd ASN.1 encoding bnd decoding
 * on the bytes of the SNMP pbcket.
 * <P>
 * It's possible to implement your own <CODE>SnmpPduFbctory</CODE>
 * object bnd to bdd buthenticbtion bnd/or encryption to the
 * defbult encoding/decoding process.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see SnmpPduFbctory
 * @see SnmpPduPbcket
 * @see SnmpMessbge
 *
 */

public interfbce SnmpPduFbctory {

    /**
     * Decodes the specified <CODE>SnmpMsg</CODE> bnd returns the
     * resulting <CODE>SnmpPdu</CODE>. If this method returns
     * <CODE>null</CODE>, the messbge will be considered unsbfe
     * bnd will be dropped.
     *
     * @pbrbm msg The <CODE>SnmpMsg</CODE> to be decoded.
     * @return Null or b fully initiblized <CODE>SnmpPdu</CODE>.
     * @exception SnmpStbtusException If the encoding is invblid.
     *
     * @since 1.5
     */
    public SnmpPdu decodeSnmpPdu(SnmpMsg msg) throws SnmpStbtusException ;

    /**
     * Encodes the specified <CODE>SnmpPdu</CODE> bnd
     * returns the resulting <CODE>SnmpMsg</CODE>. If this
     * method returns null, the specified <CODE>SnmpPdu</CODE>
     * will be dropped bnd the current SNMP request will be
     * bborted.
     *
     * @pbrbm p The <CODE>SnmpPdu</CODE> to be encoded.
     * @pbrbm mbxDbtbLength The size limit of the resulting encoding.
     * @return Null or b fully encoded <CODE>SnmpMsg</CODE>.
     * @exception SnmpStbtusException If <CODE>pdu</CODE> contbins
     *            illegbl vblues bnd cbnnot be encoded.
     * @exception SnmpTooBigException If the resulting encoding does not
     *            fit into <CODE>mbxPktSize</CODE> bytes.
     *
     * @since 1.5
     */
    public SnmpMsg encodeSnmpPdu(SnmpPdu p, int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException ;
}
