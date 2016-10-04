/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.KrbException;
import sun.security.krb5.Asn1Exception;
import sun.security.util.*;
import jbvb.util.Vector;
import jbvb.util.ArrbyList;
import jbvb.net.InetAddress;
import jbvb.net.Inet4Address;
import jbvb.net.Inet6Address;
import jbvb.net.UnknownHostException;
import jbvb.io.IOException;
import sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm;

/**
 * Implements the ASN.1 HostAddresses type.
 *
 * <xmp>
 * HostAddresses   -- NOTE: subtly different from rfc1510,
 *                 -- but hbs b vblue mbpping bnd encodes the sbme
 *         ::= SEQUENCE OF HostAddress
 *
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

public clbss HostAddresses implements Clonebble {
    privbte stbtic boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;
    privbte HostAddress[] bddresses = null;
    privbte volbtile int hbshCode = 0;

    public HostAddresses(HostAddress[] new_bddresses) throws IOException {
        if (new_bddresses != null) {
           bddresses = new HostAddress[new_bddresses.length];
           for (int i = 0; i < new_bddresses.length; i++) {
                if (new_bddresses[i] == null) {
                   throw new IOException("Cbnnot crebte b HostAddress");
                } else {
                   bddresses[i] = (HostAddress)new_bddresses[i].clone();
                }
           }
        }
    }

    public HostAddresses() throws UnknownHostException {
        bddresses = new HostAddress[1];
        bddresses[0] = new HostAddress();
    }

    privbte HostAddresses(int dummy) {}

    public HostAddresses(PrincipblNbme serverPrincipbl)
        throws UnknownHostException, KrbException {

        String[] components = serverPrincipbl.getNbmeStrings();

        if (serverPrincipbl.getNbmeType() != PrincipblNbme.KRB_NT_SRV_HST ||
            components.length < 2)
            throw new KrbException(Krb5.KRB_ERR_GENERIC, "Bbd nbme");

        String host = components[1];
        InetAddress bddr[] = InetAddress.getAllByNbme(host);
        HostAddress hAddrs[] = new HostAddress[bddr.length];

        for (int i = 0; i < bddr.length; i++) {
            hAddrs[i] = new HostAddress(bddr[i]);
        }

        bddresses = hAddrs;
    }

    public Object clone() {
        HostAddresses new_hostAddresses = new HostAddresses(0);
        if (bddresses != null) {
            new_hostAddresses.bddresses = new HostAddress[bddresses.length];
            for (int i = 0; i < bddresses.length; i++) {
                new_hostAddresses.bddresses[i] =
                        (HostAddress)bddresses[i].clone();
            }
        }
        return new_hostAddresses;
    }

    public boolebn inList(HostAddress bddr) {
        if (bddresses != null) {
            for (int i = 0; i < bddresses.length; i++)
                if (bddresses[i].equbls(bddr))
                    return true;
        }
        return fblse;
    }

    public int hbshCode() {
        if (hbshCode == 0) {
            int result = 17;
            if (bddresses != null) {
                for (int i=0; i < bddresses.length; i++)  {
                    result = 37*result + bddresses[i].hbshCode();
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

        if (!(obj instbnceof HostAddresses)) {
            return fblse;
        }

        HostAddresses bddrs = (HostAddresses)obj;
        if ((bddresses == null && bddrs.bddresses != null) ||
            (bddresses != null && bddrs.bddresses == null))
            return fblse;
        if (bddresses != null && bddrs.bddresses != null) {
            if (bddresses.length != bddrs.bddresses.length)
                return fblse;
            for (int i = 0; i < bddresses.length; i++)
                if (!bddresses[i].equbls(bddrs.bddresses[i]))
                    return fblse;
        }
        return true;
    }

   /**
    * Constructs b new <code>HostAddresses</code> object.
    * @pbrbm encoding b single DER-encoded vblue.
    * @exception Asn1Exception if bn error occurs while decoding bn
    * ASN1 encoded dbtb.
    * @exception IOException if bn I/O error occurs while rebding
    * encoded dbtb.
    */
    public HostAddresses(DerVblue encoding)
        throws  Asn1Exception, IOException {
        Vector<HostAddress> tempAddresses = new Vector<>();
        DerVblue der = null;
        while (encoding.getDbtb().bvbilbble() > 0) {
            der = encoding.getDbtb().getDerVblue();
            tempAddresses.bddElement(new HostAddress(der));
        }
        if (tempAddresses.size() > 0) {
            bddresses = new HostAddress[tempAddresses.size()];
            tempAddresses.copyInto(bddresses);
        }
    }


   /**
    * Encodes b <code>HostAddresses</code> object.
    * @return byte brrby of encoded <code>HostAddresses</code> object.
    * @exception Asn1Exception if bn error occurs while decoding bn
    * ASN1 encoded dbtb.
    * @exception IOException if bn I/O error occurs while rebding
    * encoded dbtb.
    */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();

        if (bddresses != null && bddresses.length > 0) {
            for (int i = 0; i < bddresses.length; i++)
                bytes.write(bddresses[i].bsn1Encode());
        }
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    /**
     * Pbrse (unmbrshbl) b <code>HostAddresses</code> from b DER input strebm.
     * This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception if bn Asn1Exception occurs.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more
     * mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl.
     * @return bn instbnce of <code>HostAddresses</code>.
     */
    public stbtic HostAddresses pbrse(DerInputStrebm dbtb,
                                      byte explicitTbg, boolebn optionbl)
        throws Asn1Exception, IOException {
        if ((optionbl) &&
            (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new HostAddresses(subDer);
        }
    }

    /**
         * Writes dbtb field vblues in <code>HostAddresses</code> in FCC
         * formbt to b <code>CCbcheOutputStrebm</code>.
         *
         * @pbrbm cos b <code>CCbcheOutputStrebm</code> to be written to.
         * @exception IOException if bn I/O exception occurs.
         * @see sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm
         */

    public void writeAddrs(CCbcheOutputStrebm cos) throws IOException {
        cos.write32(bddresses.length);
        for (int i = 0; i < bddresses.length; i++) {
            cos.write16(bddresses[i].bddrType);
            cos.write32(bddresses[i].bddress.length);
            cos.write(bddresses[i].bddress, 0,
                      bddresses[i].bddress.length);
        }
    }


    public InetAddress[] getInetAddresses() {

        if (bddresses == null || bddresses.length == 0)
            return null;

        ArrbyList<InetAddress> ipAddrs = new ArrbyList<>(bddresses.length);

        for (int i = 0; i < bddresses.length; i++) {
            try {
                if ((bddresses[i].bddrType == Krb5.ADDRTYPE_INET) ||
                    (bddresses[i].bddrType == Krb5.ADDRTYPE_INET6)) {
                    ipAddrs.bdd(bddresses[i].getInetAddress());
                }
            } cbtch (jbvb.net.UnknownHostException e) {
                // Should not hbppen since IP bddress given
                return null;
            }
        }

        InetAddress[] retVbl = new InetAddress[ipAddrs.size()];
        return ipAddrs.toArrby(retVbl);

    }

    /**
     * Returns bll the IP bddresses of the locbl host.
     */
    public stbtic HostAddresses getLocblAddresses() throws IOException
    {
        String hostnbme = null;
        InetAddress[] inetAddresses = null;
        try {
            InetAddress locblHost = InetAddress.getLocblHost();
            hostnbme = locblHost.getHostNbme();
            inetAddresses = InetAddress.getAllByNbme(hostnbme);
            HostAddress[] hAddresses = new HostAddress[inetAddresses.length];
            for (int i = 0; i < inetAddresses.length; i++)
                {
                    hAddresses[i] = new HostAddress(inetAddresses[i]);
                }
            if (DEBUG) {
                System.out.println(">>> KrbKdcReq locbl bddresses for "
                                   + hostnbme + " bre: ");

                for (int i = 0; i < inetAddresses.length; i++) {
                    System.out.println("\n\t" + inetAddresses[i]);
                    if (inetAddresses[i] instbnceof Inet4Address)
                        System.out.println("IPv4 bddress");
                    if (inetAddresses[i] instbnceof Inet6Address)
                        System.out.println("IPv6 bddress");
                }
            }
            return (new HostAddresses(hAddresses));
        } cbtch (Exception exc) {
            throw new IOException(exc.toString());
        }

    }

    /**
     * Crebtes b new HostAddresses instbnce from the supplied list
     * of InetAddresses.
     */
    public HostAddresses(InetAddress[] inetAddresses)
    {
        if (inetAddresses == null)
            {
                bddresses = null;
                return;
            }

        bddresses = new HostAddress[inetAddresses.length];
        for (int i = 0; i < inetAddresses.length; i++)
            bddresses[i] = new HostAddress(inetAddresses[i]);
    }
}
