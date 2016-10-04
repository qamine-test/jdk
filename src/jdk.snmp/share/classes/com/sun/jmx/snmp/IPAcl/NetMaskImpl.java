/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp.IPAcl;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_LOGGER;

import jbvb.util.logging.Level;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.io.Seriblizbble;
import jbvb.net.UnknownHostException;
import jbvb.net.InetAddress;

import jbvb.security.Principbl;
import jbvb.security.bcl.Group;


/**
 * This clbss is used to represent b subnet mbsk (b group of hosts mbtching the sbme
 * IP mbsk).
 *
 * @see jbvb.security.bcl.Group
 */

clbss NetMbskImpl extends PrincipblImpl implements Group, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -7332541893877932896L;

    protected byte[] subnet = null;
    protected int prefix = -1;
    /**
     * Constructs bn empty group.
     * @exception UnknownHostException Not implemented
     */
    public NetMbskImpl () throws UnknownHostException {
    }

    privbte byte[] extrbctSubNet(byte[] b) {
        int bddrLength = b.length;
        byte[] subnet = null;
        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(),
                "extrbctSubNet", "BINARY ARRAY :");
            StringBuilder sb = new StringBuilder();
            for(int i =0; i < bddrLength; i++) {
                sb.bppend((b[i] & 0xFF) + ":");
            }
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(),
                "extrbctSubNet", sb.toString());
        }

        // 8 is b byte size. Common to bny InetAddress (V4 or V6).
        int fullyCoveredByte = prefix / 8;
        if(fullyCoveredByte == bddrLength) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
                   "The mbsk is the complete bddress, strbnge..." + bddrLength);
            }
            subnet = b;
            return subnet;
        }
        if(fullyCoveredByte > bddrLength) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
                   "The number of covered byte is longer thbn the bddress. BUG");
            }
            throw new IllegblArgumentException("The number of covered byte is longer thbn the bddress.");
        }
        int pbrtiblyCoveredIndex = fullyCoveredByte;
        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Pbrtiblly covered index : " + pbrtiblyCoveredIndex);
        }
        byte toDebl = b[pbrtiblyCoveredIndex];
        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Pbrtiblly covered byte : " + toDebl);
        }

        // 8 is b byte size. Common to bny InetAddress (V4 or V6).
        int nbbits = prefix % 8;
        int subnetSize = 0;

        if(nbbits == 0)
        subnetSize = pbrtiblyCoveredIndex;
        else
        subnetSize = pbrtiblyCoveredIndex + 1;

        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Rembins : " + nbbits);
        }

        byte mbsk = 0;
        for(int i = 0; i < nbbits; i++) {
            mbsk |= (1 << (7 - i));
        }
        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Mbsk vblue : " + (mbsk & 0xFF));
        }

        byte mbskedVblue = (byte) ((int)toDebl & (int)mbsk);

        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Mbsked byte : "  + (mbskedVblue &0xFF));
        }
        subnet = new byte[subnetSize];
        if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
               "Resulting subnet : ");
        }
        for(int i = 0; i < pbrtiblyCoveredIndex; i++) {
            subnet[i] = b[i];

            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
                   (subnet[i] & 0xFF) +":");
            }
        }

        if(nbbits != 0) {
            subnet[pbrtiblyCoveredIndex] = mbskedVblue;
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "extrbctSubNet",
                    "Lbst subnet byte : " + (subnet[pbrtiblyCoveredIndex] &0xFF));
            }
        }
        return subnet;
    }

  /**
   * Constructs b group using the specified subnet mbsk.
   * THIS ALGORITHM IS V4 bnd V6 compbtible.
   *
   * @exception UnknownHostException if the subnet mbsk cbnn't be built.
   */
  public NetMbskImpl (String b, int prefix) throws UnknownHostException {
        super(b);
        this.prefix = prefix;
        subnet = extrbctSubNet(getAddress().getAddress());
  }

  /**
   * Adds the specified member to the group.
   *
   * @pbrbm p the principbl to bdd to this group.
   * @return true if the member wbs successfully bdded, fblse if the
   *      principbl wbs blrebdy b member.
   */
  public boolebn bddMember(Principbl p) {
        // we don't need to bdd members becbuse the ip bddress is b subnet mbsk
        return true;
  }

  public int hbshCode() {
        return super.hbshCode();
  }

  /**
   * Compbres this group to the specified object. Returns true if the object
   * pbssed in mbtches the group represented.
   *
   * @pbrbm p the object to compbre with.
   * @return true if the object pbssed in mbtches the subnet mbsk,
   *    fblse otherwise.
   */
    public boolebn equbls (Object p) {
        if (p instbnceof PrincipblImpl || p instbnceof NetMbskImpl){
            PrincipblImpl received = (PrincipblImpl) p;
            InetAddress bddr = received.getAddress();
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "equbls",
                    "Received Address : " + bddr);
            }
            byte[] recAddr = bddr.getAddress();
            for(int i = 0; i < subnet.length; i++) {
                if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "equbls",
                        "(recAddr[i]) : " + (recAddr[i] & 0xFF));
                    SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "equbls",
                        "(recAddr[i] & subnet[i]) : " +
                         ((recAddr[i] & (int)subnet[i]) &0xFF) +
                         " subnet[i] : " + (subnet[i] &0xFF));
                }
                if((recAddr[i] & subnet[i]) != subnet[i]) {
                    if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "equbls",
                            "FALSE");
                    }
                    return fblse;
                }
            }
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, NetMbskImpl.clbss.getNbme(), "equbls",
                    "TRUE");
            }
            return true;
        } else
            return fblse;
    }
  /**
   * Returns true if the pbssed principbl is b member of the group.
   *
   * @pbrbm p the principbl whose membership is to be checked.
   * @return true if the principbl is b member of this group, fblse otherwise.
   */
  public boolebn isMember(Principbl p) {
        if ((p.hbshCode() & super.hbshCode()) == p.hbshCode()) return true;
        else return fblse;
  }

  /**
   * Returns bn enumerbtion which contbins the subnet mbsk.
   *
   * @return bn enumerbtion which contbins the subnet mbsk.
   */
  public Enumerbtion<? extends Principbl> members(){
        Vector<Principbl> v = new Vector<Principbl>(1);
        v.bddElement(this);
        return v.elements();
  }

  /**
   * Removes the specified member from the group. (Not implemented)
   *
   * @pbrbm p the principbl to remove from this group.
   * @return bllwbys return true.
   */
  public boolebn removeMember(Principbl p) {
        return true;
  }

  /**
   * Prints b string representbtion of this group.
   *
   * @return  b string representbtion of this group.
   */
  public String toString() {
        return ("NetMbskImpl :"+ super.getAddress().toString() + "/" + prefix);
  }

}
