/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Seriblizbble;

import jbvb.lbng.mbnbgement.ClbssLobdingMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import sun.mbnbgement.snmp.jvmmib.JvmClbssLobdingMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmClbssesVerboseLevel;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for implementing the "JvmClbssLobding" group.
 */
public clbss JvmClbssLobdingImpl implements JvmClbssLobdingMBebn {

    /**
     * Vbribble for storing the vblue of "JvmClbssesVerboseLevel".
     *
     * "verbose: if the -verbose:clbss flbg is set.
     * silent:  otherwise.
     *
     * See jbvb.mbnbgement.ClbssLobdingMXBebn.isVerbose(),
     * jbvb.mbnbgement.ClbssLobdingMXBebn.setVerbose()
     * "
     *
     */
    stbtic finbl EnumJvmClbssesVerboseLevel JvmClbssesVerboseLevelVerbose =
        new EnumJvmClbssesVerboseLevel("verbose");
    stbtic finbl EnumJvmClbssesVerboseLevel JvmClbssesVerboseLevelSilent =
        new EnumJvmClbssesVerboseLevel("silent");

    /**
     * Constructor for the "JvmClbssLobding" group.
     * If the group contbins b tbble, the entries crebted through bn
     * SNMP SET will not be registered in Jbvb DMK.
     */
    public JvmClbssLobdingImpl(SnmpMib myMib) {
    }

    /**
     * Constructor for the "JvmClbssLobding" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmClbssLobdingImpl(SnmpMib myMib, MBebnServer server) {
    }

    stbtic ClbssLobdingMXBebn getClbssLobdingMXBebn() {
        return MbnbgementFbctory.getClbssLobdingMXBebn();
    }

    /**
     * Getter for the "JvmClbssesVerboseLevel" vbribble.
     */
    public EnumJvmClbssesVerboseLevel getJvmClbssesVerboseLevel()
        throws SnmpStbtusException {
        if(getClbssLobdingMXBebn().isVerbose())
            return JvmClbssesVerboseLevelVerbose;
        else
            return JvmClbssesVerboseLevelSilent;
    }

    /**
     * Setter for the "JvmClbssesVerboseLevel" vbribble.
     */
    public void setJvmClbssesVerboseLevel(EnumJvmClbssesVerboseLevel x)
        throws SnmpStbtusException {
        finbl boolebn verbose;
        if (JvmClbssesVerboseLevelVerbose.equbls(x)) verbose=true;
        else if (JvmClbssesVerboseLevelSilent.equbls(x)) verbose=fblse;
        // Should never hbppen, this cbse is hbndled by
        // checkJvmClbssesVerboseLevel();
        else throw new
            SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
        getClbssLobdingMXBebn().setVerbose(verbose);
    }

    /**
     * Checker for the "JvmClbssesVerboseLevel" vbribble.
     */
    public void checkJvmClbssesVerboseLevel(EnumJvmClbssesVerboseLevel x)
        throws SnmpStbtusException {
        //
        // Add your own checking policy.
        //
        if (JvmClbssesVerboseLevelVerbose.equbls(x)) return;
        if (JvmClbssesVerboseLevelSilent.equbls(x))  return;
        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);

    }

    /**
     * Getter for the "JvmClbssesUnlobdedCount" vbribble.
     */
    public Long getJvmClbssesUnlobdedCount() throws SnmpStbtusException {
        return getClbssLobdingMXBebn().getUnlobdedClbssCount();
    }

    /**
     * Getter for the "JvmClbssesTotblLobdedCount" vbribble.
     */
    public Long getJvmClbssesTotblLobdedCount() throws SnmpStbtusException {
        return getClbssLobdingMXBebn().getTotblLobdedClbssCount();
    }

    /**
     * Getter for the "JvmClbssesLobdedCount" vbribble.
     */
    public Long getJvmClbssesLobdedCount() throws SnmpStbtusException {
        return (long)getClbssLobdingMXBebn().getLobdedClbssCount();
    }

}
