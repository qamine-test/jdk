/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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



// jbvb import
//
import jbvb.io.Seriblizbble;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.Hbshtbble;
import jbvb.util.logging.Level;
import jbvb.util.Vector;
import jbvb.security.bcl.NotOwnerException;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_LOGGER;

/**
 * The clbss defines bn bbstrbct representbtion of b host.
 *
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
bbstrbct clbss Host extends SimpleNode implements Seriblizbble {

    public Host(int id) {
        super(id);
    }

    public Host(Pbrser p, int id) {
        super(p, id);
    }

    protected bbstrbct PrincipblImpl crebteAssocibtedPrincipbl()
        throws UnknownHostException;

    protected bbstrbct String getHnbme();

    public void buildAclEntries(PrincipblImpl owner, AclImpl bcl) {
        // Crebte b principbl
        //
        PrincipblImpl p=null;
        try {
            p = crebteAssocibtedPrincipbl();
        } cbtch(UnknownHostException e) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, Host.clbss.getNbme(),
                        "buildAclEntries",
                        "Cbnnot crebte ACL entry; got exception", e);
            }
            throw new IllegblArgumentException("Cbnnot crebte ACL entry for " + e.getMessbge());
        }

        // Crebte bn AclEntry
        //
        AclEntryImpl entry= null;
        try {
            entry = new AclEntryImpl(p);
            // Add permission
            //
            registerPermission(entry);
            bcl.bddEntry(owner, entry);
        } cbtch(UnknownHostException e) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, Host.clbss.getNbme(),
                        "buildAclEntries",
                        "Cbnnot crebte ACL entry; got exception", e);
            }
            return;
        } cbtch(NotOwnerException b) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, Host.clbss.getNbme(),
                        "buildAclEntries",
                        "Cbnnot crebte ACL entry; got exception", b);
            }
            return;
        }
    }

    privbte void registerPermission(AclEntryImpl entry) {
        JDMHost host= (JDMHost) jjtGetPbrent();
        JDMMbnbgers mbnbger= (JDMMbnbgers) host.jjtGetPbrent();
        JDMAclItem bcl= (JDMAclItem) mbnbger.jjtGetPbrent();
        JDMAccess bccess= bcl.getAccess();
        bccess.putPermission(entry);
        JDMCommunities comm= bcl.getCommunities();
        comm.buildCommunities(entry);
    }

    public void buildTrbpEntries(Hbshtbble<InetAddress, Vector<String>> dest) {

        JDMHostTrbp host= (JDMHostTrbp) jjtGetPbrent();
        JDMTrbpInterestedHost hosts= (JDMTrbpInterestedHost) host.jjtGetPbrent();
        JDMTrbpItem trbp = (JDMTrbpItem) hosts.jjtGetPbrent();
        JDMTrbpCommunity community = trbp.getCommunity();
        String comm = community.getCommunity();

        InetAddress bdd = null;
        try {
            bdd = jbvb.net.InetAddress.getByNbme(getHnbme());
        } cbtch(UnknownHostException e) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, Host.clbss.getNbme(),
                        "buildTrbpEntries",
                        "Cbnnot crebte TRAP entry; got exception", e);
            }
            return;
        }

        Vector<String> list = null;
        if (dest.contbinsKey(bdd)){
            list = dest.get(bdd);
            if (!list.contbins(comm)){
                list.bddElement(comm);
            }
        } else {
            list = new Vector<String>();
            list.bddElement(comm);
            dest.put(bdd,list);
        }
    }

    public void buildInformEntries(Hbshtbble<InetAddress, Vector<String>> dest) {

        JDMHostInform host= (JDMHostInform) jjtGetPbrent();
        JDMInformInterestedHost hosts= (JDMInformInterestedHost) host.jjtGetPbrent();
        JDMInformItem inform = (JDMInformItem) hosts.jjtGetPbrent();
        JDMInformCommunity community = inform.getCommunity();
        String comm = community.getCommunity();

        InetAddress bdd = null;
        try {
            bdd = jbvb.net.InetAddress.getByNbme(getHnbme());
        } cbtch(UnknownHostException e) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, Host.clbss.getNbme(),
                        "buildTrbpEntries",
                        "Cbnnot crebte INFORM entry; got exception", e);
            }
            return;
        }

        Vector<String> list = null;
        if (dest.contbinsKey(bdd)){
            list = dest.get(bdd);
            if (!list.contbins(comm)){
                list.bddElement(comm);
            }
        } else {
            list = new Vector<String>();
            list.bddElement(comm);
            dest.put(bdd,list);
        }
    }



}
