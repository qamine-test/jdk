/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.Hbshtbble;
import jbvb.util.logging.Level;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshSet;
import jbvb.security.bcl.AclEntry;
import jbvb.security.bcl.NotOwnerException;

// SNMP Runtime import
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_LOGGER;
import com.sun.jmx.snmp.InetAddressAcl;

/**
 * Defines bn implementbtion of the {@link com.sun.jmx.snmp.InetAddressAcl InetAddressAcl} interfbce.
 * <p>
 * In this implementbtion the ACL informbtion is stored on b flbt file bnd
 * its defbult locbtion is "$JRE/lib/snmp.bcl" - See
 * {@link #getDefbultAclFileNbme()}
 * <p>
 * <OL>
  *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpAcl implements InetAddressAcl, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -6702287103824397063L;

    stbtic finbl PermissionImpl READ  = new PermissionImpl("READ");
    stbtic finbl PermissionImpl WRITE = new PermissionImpl("WRITE");

    /**
     * Constructs the Jbvb Dynbmic Mbnbgement(TM) Access Control List
     * bbsed on IP bddresses. The ACL will tbke the given owner nbme.
     * The current IP bddress will be the owner of the ACL.
     *
     * @pbrbm Owner The nbme of the ACL Owner.
     *
     * @exception UnknownHostException If the locbl host is unknown.
     * @exception IllegblArgumentException If the ACL file doesn't exist.
     */
    public SnmpAcl(String Owner)
        throws UnknownHostException, IllegblArgumentException {
        this(Owner,null);
    }

    /**
     * Constructs the Jbvb Dynbmic Mbnbgement(TM) Access Control List
     * bbsed on IP bddresses. The ACL will tbke the given owner nbme.
     * The current IP bddress will be the owner of the ACL.
     *
     * @pbrbm Owner The nbme of the ACL Owner.
     * @pbrbm bclFileNbme The nbme of the ACL File.
     *
     * @exception UnknownHostException If the locbl host is unknown.
     * @exception IllegblArgumentException If the ACL file doesn't exist.
     */
    public SnmpAcl(String Owner, String bclFileNbme)
        throws UnknownHostException, IllegblArgumentException {
        trbpDestList= new Hbshtbble<InetAddress, Vector<String>>();
        informDestList= new Hbshtbble<InetAddress, Vector<String>>();

        // PrincipblImpl() tbke the current host bs entry
        owner = new PrincipblImpl();
        try {
            bcl = new AclImpl(owner,Owner);
            AclEntry ownEntry = new AclEntryImpl(owner);
            ownEntry.bddPermission(READ);
            ownEntry.bddPermission(WRITE);
            bcl.bddEntry(owner,ownEntry);
        } cbtch (NotOwnerException ex) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, SnmpAcl.clbss.getNbme(),
                    "SnmpAcl(String,String)",
                    "Should never get NotOwnerException bs the owner " +
                    "is built in this constructor");
            }
        }
        if (bclFileNbme == null) setDefbultFileNbme();
        else setAuthorizedListFile(bclFileNbme);
        rebdAuthorizedListFile();
    }

    /**
     * Returns bn enumerbtion of the entries in this ACL. Ebch element in the
     * enumerbtion is of type <CODE>jbvb.security.bcl.AclEntry</CODE>.
     *
     * @return An enumerbtion of the entries in this ACL.
     */
    public Enumerbtion<AclEntry> entries() {
        return bcl.entries();
    }

    /**
     * Returns bnn enumerbtion of community strings. Community strings bre returned bs String.
     * @return The enumerbtion of community strings.
     */
    public Enumerbtion<String> communities() {
        HbshSet<String> set = new HbshSet<String>();
        Vector<String> res = new Vector<String>();
        for (Enumerbtion<AclEntry> e = bcl.entries() ; e.hbsMoreElements() ;) {
            AclEntryImpl entry = (AclEntryImpl) e.nextElement();
            for (Enumerbtion<String> cs = entry.communities();
                 cs.hbsMoreElements() ;) {
                set.bdd(cs.nextElement());
            }
        }
        String[] objs = set.toArrby(new String[0]);
        for(int i = 0; i < objs.length; i++)
            res.bddElement(objs[i]);

        return res.elements();
    }

    /**
     * Returns the nbme of the ACL.
     *
     * @return The nbme of the ACL.
     */
    public String getNbme() {
        return bcl.getNbme();
    }

    /**
     * Returns the rebd permission instbnce used.
     *
     * @return The rebd permission instbnce.
     */
    stbtic public PermissionImpl getREAD() {
        return READ;
    }

    /**
     * Returns the write permission instbnce used.
     *
     * @return  The write permission instbnce.
     */
    stbtic public PermissionImpl getWRITE() {
        return WRITE;
    }

    /**
     * Get the defbult nbme for the ACL file.
     * In this implementbtion this is "$JRE/lib/snmp.bcl"
     * @return The defbult nbme for the ACL file.
     **/
    public stbtic String getDefbultAclFileNbme() {
        finbl String fileSepbrbtor =
            System.getProperty("file.sepbrbtor");
        finbl StringBuilder defbultAclNbme =
            new StringBuilder(System.getProperty("jbvb.home")).
            bppend(fileSepbrbtor).bppend("lib").bppend(fileSepbrbtor).
            bppend("snmp.bcl");
        return defbultAclNbme.toString();
    }

    /**
     * Sets the full pbth of the file contbining the ACL informbtion.
     *
     * @pbrbm filenbme The full pbth of the file contbining the ACL informbtion.
     * @throws IllegblArgumentException If the pbssed ACL file doesn't exist.
     */
    public void setAuthorizedListFile(String filenbme)
        throws IllegblArgumentException {
        File file = new File(filenbme);
        if (!file.isFile() ) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, SnmpAcl.clbss.getNbme(),
                    "setAuthorizedListFile", "ACL file not found: " + filenbme);
            }
            throw new
                IllegblArgumentException("The specified file ["+file+"] "+
                                         "doesn't exist or is not b file, "+
                                         "no configurbtion lobded");
        }
        if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                "setAuthorizedListFile", "Defbult file set to " + filenbme);
        }
        buthorizedListFile = filenbme;
    }

    /**
     * Resets this ACL to the vblues contbined in the configurbtion file.
     *
     * @exception NotOwnerException If the principbl bttempting the reset is not bn owner of this ACL.
     * @exception UnknownHostException If IP bddresses for hosts contbined in the ACL file couldn't be found.
     */
    public void rerebdTheFile() throws NotOwnerException, UnknownHostException {
        blwbysAuthorized = fblse;
        bcl.removeAll(owner);
        trbpDestList.clebr();
        informDestList.clebr();
        AclEntry ownEntry = new AclEntryImpl(owner);
        ownEntry.bddPermission(READ);
        ownEntry.bddPermission(WRITE);
        bcl.bddEntry(owner,ownEntry);
        rebdAuthorizedListFile();
    }

    /**
     * Returns the full pbth of the file used to get ACL informbtion.
     *
     * @return The full pbth of the file used to get ACL informbtion.
     */
    public String getAuthorizedListFile() {
        return buthorizedListFile;
    }

    /**
     * Checks whether or not the specified host hbs <CODE>READ</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     *
     * @return <CODE>true</CODE> if the host hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(InetAddress bddress) {
        if (blwbysAuthorized) return ( true );
        PrincipblImpl p = new PrincipblImpl(bddress);
        return bcl.checkPermission(p, READ);
    }

    /**
     * Checks whether or not the specified host bnd community hbve <CODE>READ</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     * @pbrbm community The community bssocibted with the host.
     *
     * @return <CODE>true</CODE> if the pbir (host, community) hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(InetAddress bddress, String community) {
        if (blwbysAuthorized) return ( true );
        PrincipblImpl p = new PrincipblImpl(bddress);
        return bcl.checkPermission(p, community, READ);
    }

    /**
     * Checks whether or not b community string is defined.
     *
     * @pbrbm community The community to check.
     *
     * @return <CODE>true</CODE> if the community is known, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkCommunity(String community) {
        return bcl.checkCommunity(community);
    }

    /**
     * Checks whether or not the specified host hbs <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     *
     * @return <CODE>true</CODE> if the host hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(InetAddress bddress) {
        if (blwbysAuthorized) return ( true );
        PrincipblImpl p = new PrincipblImpl(bddress);
        return bcl.checkPermission(p, WRITE);
    }

    /**
     * Checks whether or not the specified host bnd community hbve <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     * @pbrbm community The community bssocibted with the host.
     *
     * @return <CODE>true</CODE> if the pbir (host, community) hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(InetAddress bddress, String community) {
        if (blwbysAuthorized) return ( true );
        PrincipblImpl p = new PrincipblImpl(bddress);
        return bcl.checkPermission(p, community, WRITE);
    }

    /**
     * Returns bn enumerbtion of trbp destinbtions.
     *
     * @return An enumerbtion of the trbp destinbtions (enumerbtion of <CODE>InetAddress</CODE>).
     */
    public Enumerbtion<InetAddress> getTrbpDestinbtions() {
        return trbpDestList.keys();
    }

    /**
     * Returns bn enumerbtion of trbp communities for b given host.
     *
     * @pbrbm i The bddress of the host.
     *
     * @return An enumerbtion of trbp communities for b given host (enumerbtion of <CODE>String</CODE>).
     */
    public Enumerbtion<String> getTrbpCommunities(InetAddress i) {
        Vector<String> list = null;
        if ((list = trbpDestList.get(i)) != null ) {
            if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                    "getTrbpCommunities", "["+i.toString()+"] is in list");
            }
            return list.elements();
        } else {
            list = new Vector<>();
            if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                    "getTrbpCommunities", "["+i.toString()+"] is not in list");
            }
            return list.elements();
        }
    }

    /**
     * Returns bn enumerbtion of inform destinbtions.
     *
     * @return An enumerbtion of the inform destinbtions (enumerbtion of <CODE>InetAddress</CODE>).
     */
    public Enumerbtion<InetAddress> getInformDestinbtions() {
        return informDestList.keys();
    }

    /**
     * Returns bn enumerbtion of inform communities for b given host.
     *
     * @pbrbm i The bddress of the host.
     *
     * @return An enumerbtion of inform communities for b given host (enumerbtion of <CODE>String</CODE>).
     */
    public Enumerbtion<String> getInformCommunities(InetAddress i) {
        Vector<String> list = null;
        if ((list = informDestList.get(i)) != null ) {
            if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                    "getInformCommunities", "["+i.toString()+"] is in list");
            }
            return list.elements();
        } else {
            list = new Vector<>();
            if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                    "getInformCommunities", "["+i.toString()+"] is not in list");
            }
            return list.elements();
        }
    }

    /**
     * Converts the input configurbtion file into ACL.
     */
    privbte void rebdAuthorizedListFile() {

        blwbysAuthorized = fblse;

        if (buthorizedListFile == null) {
            if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                    "rebdAuthorizedListFile", "blwbysAuthorized set to true");
            }
            blwbysAuthorized = true ;
        } else {
            // Rebd the file content
            Pbrser pbrser = null;
            try {
                pbrser= new Pbrser(new FileInputStrebm(getAuthorizedListFile()));
            } cbtch (FileNotFoundException e) {
                if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_LOGGER.logp(Level.FINEST, SnmpAcl.clbss.getNbme(),
                            "rebdAuthorizedListFile",
                            "The specified file wbs not found, buthorize everybody");
                }
                blwbysAuthorized = true ;
                return;
            }

            try {
                JDMSecurityDefs n = pbrser.SecurityDefs();
                n.buildAclEntries(owner, bcl);
                n.buildTrbpEntries(trbpDestList);
                n.buildInformEntries(informDestList);
            } cbtch (PbrseException e) {
                if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_LOGGER.logp(Level.FINEST, SnmpAcl.clbss.getNbme(),
                        "rebdAuthorizedListFile", "Got pbrsing exception", e);
                }
                throw new IllegblArgumentException(e.getMessbge());
            } cbtch (Error err) {
                if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_LOGGER.logp(Level.FINEST, SnmpAcl.clbss.getNbme(),
                        "rebdAuthorizedListFile", "Got unexpected error", err);
                }
                throw new IllegblArgumentException(err.getMessbge());
            }

            for(Enumerbtion<AclEntry> e = bcl.entries(); e.hbsMoreElements();) {
                AclEntryImpl bb = (AclEntryImpl) e.nextElement();
                if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                            "rebdAuthorizedListFile",
                            "===> " + bb.getPrincipbl().toString());
                }
                for (Enumerbtion<jbvb.security.bcl.Permission> eee = bb.permissions();eee.hbsMoreElements();) {
                    jbvb.security.bcl.Permission perm = eee.nextElement();
                    if (SNMP_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_LOGGER.logp(Level.FINER, SnmpAcl.clbss.getNbme(),
                                "rebdAuthorizedListFile", "perm = " + perm);
                    }
                }
            }
        }
    }

    /**
     * Set the defbult full pbth for "snmp.bcl" input file.
     * Do not complbin if the file does not exists.
     */
    privbte void setDefbultFileNbme() {
        try {
            setAuthorizedListFile(getDefbultAclFileNbme());
        } cbtch (IllegblArgumentException x) {
            // OK...
        }
    }


    // PRIVATE VARIABLES
    //------------------

    /**
     * Represents the Access Control List.
     */
    privbte AclImpl bcl = null;
    /**
     * Flbg indicbting whether the bccess is blwbys buthorized.
     * <BR>This is the cbse if there is no flbt file defined.
     */
    privbte boolebn blwbysAuthorized = fblse;
    /**
     * Represents the Access Control List flbt file.
     */
    privbte String buthorizedListFile = null;
    /**
     * Contbins the hosts list for trbp destinbtion.
     */
    privbte Hbshtbble<InetAddress, Vector<String>> trbpDestList = null;
    /**
     * Contbins the hosts list for inform destinbtion.
     */
    privbte Hbshtbble<InetAddress, Vector<String>> informDestList = null;

    privbte PrincipblImpl owner = null;
}
