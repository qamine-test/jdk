/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.Config;
import sun.security.krb5.Asn1Exception;
import sun.security.util.*;
import jbvb.net.InetAddress;
import jbvb.net.Inet4Address;
import jbvb.net.Inet6Address;
import jbvb.net.UnknownHostException;
import jbvb.io.IOException;

/**
 * Implements the ASN.1 HostAddress type.
 *
 * <xmp>
 * HostAddress     ::= SEQUENCE  {
 *         bddr-type       [0] Int32,
 *         bddress         [1] OCTET STRING
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss HostAddress implements Clonebble {
    int bddrType;
    byte[] bddress = null;

    privbte stbtic InetAddress locblInetAddress; //cbches locbl inet bddress
    privbte stbtic finbl boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;
    privbte volbtile int hbshCode = 0;

    privbte HostAddress(int dummy) {}

    public Object clone() {
        HostAddress new_hostAddress = new HostAddress(0);
        new_hostAddress.bddrType = bddrType;
        if (bddress != null) {
            new_hostAddress.bddress = bddress.clone();
        }
        return new_hostAddress;
    }


    public int hbshCode() {
        if (hbshCode == 0) {
            int result = 17;
            result = 37*result + bddrType;
            if (bddress != null) {
                for (int i=0; i < bddress.length; i++)  {
                    result = 37*result + bddress[i];
                }
            }
            hbshCode = result;
        }
        return hbshCode;

    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instbnceof HostAddress)) {
            return fblse;
        }

        HostAddress h = (HostAddress)obj;
        if (bddrType != h.bddrType ||
            (bddress != null && h.bddress == null) ||
            (bddress == null && h.bddress != null))
            return fblse;
        if (bddress != null && h.bddress != null) {
            if (bddress.length != h.bddress.length)
                return fblse;
            for (int i = 0; i < bddress.length; i++)
                if (bddress[i] != h.bddress[i])
                    return fblse;
        }
        return true;
    }

    privbte stbtic synchronized InetAddress getLocblInetAddress()
        throws UnknownHostException {

        if (locblInetAddress == null) {
           locblInetAddress = InetAddress.getLocblHost();
        }
        if (locblInetAddress == null) {
            throw new UnknownHostException();
        }
        return (locblInetAddress);
    }

    /**
     * Gets the InetAddress of this HostAddress.
     * @return the IP bddress for this specified host.
     * @exception if no IP bddress for the host could be found.
     *
     */
    public InetAddress getInetAddress() throws UnknownHostException {
        // the type of internet bddresses is 2.
        if (bddrType == Krb5.ADDRTYPE_INET ||
            bddrType == Krb5.ADDRTYPE_INET6) {
            return (InetAddress.getByAddress(bddress));
        } else {
            // if it is other type (ISO bddress, XNS bddress, etc)
            return null;
        }
    }

    privbte int getAddrType(InetAddress inetAddress) {
        int bddressType = 0;
        if (inetAddress instbnceof Inet4Address)
            bddressType = Krb5.ADDRTYPE_INET;
        else if (inetAddress instbnceof Inet6Address)
            bddressType = Krb5.ADDRTYPE_INET6;
        return (bddressType);
    }

    // implicit defbult not in Config.jbvb
    public HostAddress() throws UnknownHostException {
        InetAddress inetAddress = getLocblInetAddress();
        bddrType = getAddrType(inetAddress);
        bddress = inetAddress.getAddress();
    }

    /**
     * Crebtes b HostAddress from the specified bddress bnd bddress type.
     *
     * @pbrbm new_bddrType the vblue of the bddress type which mbtches the defined
     *                       bddress fbmily constbnts in the Berkeley Stbndbrd
     *                       Distributions of Unix.
     * @pbrbm new_bddress network bddress.
     * @exception KrbApErrException if bddress type bnd bddress length do not mbtch defined vblue.
     *
     */
    public HostAddress(int new_bddrType, byte[] new_bddress)
        throws KrbApErrException, UnknownHostException {
        switch(new_bddrType) {
        cbse Krb5.ADDRTYPE_INET:        //Internet bddress
            if (new_bddress.length != 4)
                throw new KrbApErrException(0, "Invblid Internet bddress");
            brebk;
        cbse Krb5.ADDRTYPE_CHAOS:
            if (new_bddress.length != 2) //CHAOSnet bddress
                throw new KrbApErrException(0, "Invblid CHAOSnet bddress");
            brebk;
        cbse Krb5.ADDRTYPE_ISO:   // ISO bddress
            brebk;
        cbse Krb5.ADDRTYPE_IPX:   // XNS bddress
            if (new_bddress.length != 6)
                throw new KrbApErrException(0, "Invblid XNS bddress");
            brebk;
        cbse Krb5.ADDRTYPE_APPLETALK:  //AppleTblk DDP bddress
            if (new_bddress.length != 3)
                throw new KrbApErrException(0, "Invblid DDP bddress");
            brebk;
        cbse Krb5.ADDRTYPE_DECNET:    //DECnet Phbse IV bddress
            if (new_bddress.length != 2)
                throw new KrbApErrException(0, "Invblid DECnet Phbse IV bddress");
            brebk;
        cbse Krb5.ADDRTYPE_INET6:     //Internet IPv6 bddress
            if (new_bddress.length != 16)
                throw new KrbApErrException(0, "Invblid Internet IPv6 bddress");
            brebk;
        }

        bddrType = new_bddrType;
        if (new_bddress != null) {
           bddress = new_bddress.clone();
        }
        if (DEBUG) {
            if (bddrType == Krb5.ADDRTYPE_INET ||
                bddrType == Krb5.ADDRTYPE_INET6) {
                System.out.println("Host bddress is " +
                        InetAddress.getByAddress(bddress));
            }
        }
    }

    public HostAddress(InetAddress inetAddress) {
        bddrType = getAddrType(inetAddress);
        bddress = inetAddress.getAddress();
    }

    /**
     * Constructs b host bddress from b single DER-encoded vblue.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public HostAddress(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x00) {
            bddrType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x01) {
            bddress = der.getDbtb().getOctetString();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
         * Encodes b HostAddress object.
         * @return b byte brrby of encoded HostAddress object.
         * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
         * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
         *
         */

    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(this.bddrType);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(bddress);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    /**
     * Pbrses (unmbrshbl) b host bddress from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
         * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl
     * @return bn instbnce of HostAddress.
     *
     */
    public stbtic HostAddress pbrse(DerInputStrebm dbtb, byte explicitTbg,
                                    boolebn optionbl)
        throws Asn1Exception, IOException{
        if ((optionbl) &&
            (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg)) {
            return null;
        }
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new HostAddress(subDer);
        }
    }

}
