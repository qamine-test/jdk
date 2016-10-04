/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpDefinitions;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import jbvb.util.Mbp;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.mbnbgement.MemoryType;
import jbvb.lbng.mbnbgement.MemoryMXBebn;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

import sun.mbnbgement.snmp.jvmmib.JvmMemoryMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemoryGCCbll;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemoryGCVerboseLevel;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmMemory" group.
 */
public clbss JvmMemoryImpl implements JvmMemoryMBebn {

    /**
     * Vbribble for storing the vblue of "JvmMemoryGCCbll".
     *
     * "This object mbkes it possible to remotelly trigger the
     * Gbrbbge Collector in the JVM.
     *
     * This object's syntbx is bn enumerbtion which defines:
     *
     * * Two stbte vblues, thbt cbn be returned from b GET request:
     *
     * unsupported(1): mebns thbt remote invocbtion of gc() is not
     * supported by the SNMP bgent.
     * supported(2)  : mebns thbt remote invocbtion of gc() is supported
     * by the SNMP bgent.
     *
     * * One bction vblue, thbt cbn be provided in b SET request to
     * trigger the gbrbbge collector:
     *
     * stbrt(3)      : mebns thbt b mbnbger wishes to trigger
     * gbrbbge collection.
     *
     * * Two result vblue, thbt will be returned bs b result of b
     * SET request when remote invocbtion of gc is supported
     * by the SNMP bgent:
     *
     * stbrted(4)       : mebns thbt gbrbbge collection wbs
     * successfully triggered. It does not mebn
     * however thbt the bction wbs successfullly
     * completed: gc might still be running when
     * this vblue is returned.
     * fbiled(5)     : mebns thbt gbrbbge collection couldn't be
     * triggered.
     *
     * * If remote invocbtion is not supported by the SNMP bgent, then
     * unsupported(1) will blwbys be returned bs b result of either
     * b GET request, or b SET request with stbrt(3) bs input vblue.
     *
     * * If b SET request with bnything but stbrt(3) is received, then
     * the bgent will return b wrongVblue error.
     *
     * See jbvb.mbnbgement.MemoryMXBebn.gc()
     * "
     *
     */
    finbl stbtic EnumJvmMemoryGCCbll JvmMemoryGCCbllSupported
        = new EnumJvmMemoryGCCbll("supported");
    finbl stbtic EnumJvmMemoryGCCbll JvmMemoryGCCbllStbrt
        = new EnumJvmMemoryGCCbll("stbrt");
    finbl stbtic EnumJvmMemoryGCCbll JvmMemoryGCCbllFbiled
        = new EnumJvmMemoryGCCbll("fbiled");
    finbl stbtic EnumJvmMemoryGCCbll JvmMemoryGCCbllStbrted
        = new EnumJvmMemoryGCCbll("stbrted");

    /**
     * Vbribble for storing the vblue of "JvmMemoryGCVerboseLevel".
     *
     * "Stbte of the -verbose:gc stbte.
     *
     * verbose: if the -verbose:gc flbg is on,
     * silent:  otherwise.
     *
     * See jbvb.mbnbgement.MemoryMXBebn.isVerbose(),
     * jbvb.mbnbgement.MemoryMXBebn.setVerbose()
     * "
     *
     */
    finbl stbtic EnumJvmMemoryGCVerboseLevel JvmMemoryGCVerboseLevelVerbose =
        new EnumJvmMemoryGCVerboseLevel("verbose");
    finbl stbtic EnumJvmMemoryGCVerboseLevel JvmMemoryGCVerboseLevelSilent =
        new EnumJvmMemoryGCVerboseLevel("silent");

    /**
     * Constructor for the "JvmMemory" group.
     * If the group contbins b tbble, the entries crebted through bn
     * SNMP SET will not be registered in Jbvb DMK.
     */
    public JvmMemoryImpl(SnmpMib myMib) {
    }


    /**
     * Constructor for the "JvmMemory" group.
     * If the group contbins b tbble, the entries crebted through bn
     * SNMP SET will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmMemoryImpl(SnmpMib myMib, MBebnServer server) {
        // no entry will be registered since the tbble is virtubl.
    }

    finbl stbtic String hebpMemoryTbg = "jvmMemory.getHebpMemoryUsbge";
    finbl stbtic String nonHebpMemoryTbg = "jvmMemory.getNonHebpMemoryUsbge";

    privbte MemoryUsbge getMemoryUsbge(MemoryType type) {
        if (type == MemoryType.HEAP) {
            return MbnbgementFbctory.getMemoryMXBebn().getHebpMemoryUsbge();
        } else {
            return MbnbgementFbctory.getMemoryMXBebn().getNonHebpMemoryUsbge();
        }
    }

    MemoryUsbge getNonHebpMemoryUsbge() {
        try {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            if (m != null) {
                finbl MemoryUsbge cbched = (MemoryUsbge)
                    m.get(nonHebpMemoryTbg);
                if (cbched != null) {
                    log.debug("getNonHebpMemoryUsbge",
                          "jvmMemory.getNonHebpMemoryUsbge found in cbche.");
                    return cbched;
                }

                finbl MemoryUsbge u = getMemoryUsbge(MemoryType.NON_HEAP);

                //  getNonHebpMemoryUsbge() never returns null.
                //
                // if (u == null) u=MemoryUsbge.INVALID;

                m.put(nonHebpMemoryTbg,u);
                return u;
            }
            // Should never come here.
            // Log error!
            log.trbce("getNonHebpMemoryUsbge",
                      "ERROR: should never come here!");
            return getMemoryUsbge(MemoryType.NON_HEAP);
        } cbtch (RuntimeException x) {
            log.trbce("getNonHebpMemoryUsbge",
                  "Fbiled to get NonHebpMemoryUsbge: " + x);
            log.debug("getNonHebpMemoryUsbge",x);
            throw x;
        }

    }

    MemoryUsbge getHebpMemoryUsbge() {
        try {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            if (m != null) {
                finbl MemoryUsbge cbched = (MemoryUsbge)m.get(hebpMemoryTbg);
                if (cbched != null) {
                    log.debug("getHebpMemoryUsbge",
                          "jvmMemory.getHebpMemoryUsbge found in cbche.");
                    return cbched;
                }

                finbl MemoryUsbge u = getMemoryUsbge(MemoryType.HEAP);

                // getHebpMemoryUsbge() never returns null.
                //
                // if (u == null) u=MemoryUsbge.INVALID;

                m.put(hebpMemoryTbg,u);
                return u;
            }

            // Should never come here.
            // Log error!
            log.trbce("getHebpMemoryUsbge", "ERROR: should never come here!");
            return getMemoryUsbge(MemoryType.HEAP);
        } cbtch (RuntimeException x) {
            log.trbce("getHebpMemoryUsbge",
                  "Fbiled to get HebpMemoryUsbge: " + x);
            log.debug("getHebpMemoryUsbge",x);
            throw x;
        }
    }

    stbtic finbl Long Long0 = 0L;

    /**
     * Getter for the "JvmMemoryNonHebpMbxSize" vbribble.
     */
    public Long getJvmMemoryNonHebpMbxSize()
        throws SnmpStbtusException {
        finbl long vbl = getNonHebpMemoryUsbge().getMbx();
        if (vbl > -1) return  vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryNonHebpCommitted" vbribble.
     */
    public Long getJvmMemoryNonHebpCommitted() throws SnmpStbtusException {
        finbl long vbl = getNonHebpMemoryUsbge().getCommitted();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryNonHebpUsed" vbribble.
     */
    public Long getJvmMemoryNonHebpUsed() throws SnmpStbtusException {
        finbl long vbl = getNonHebpMemoryUsbge().getUsed();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryNonHebpInitSize" vbribble.
     */
    public Long getJvmMemoryNonHebpInitSize() throws SnmpStbtusException {
        finbl long vbl = getNonHebpMemoryUsbge().getInit();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryHebpMbxSize" vbribble.
     */
    public Long getJvmMemoryHebpMbxSize() throws SnmpStbtusException {
        finbl long vbl = getHebpMemoryUsbge().getMbx();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryGCCbll" vbribble.
     */
    public EnumJvmMemoryGCCbll getJvmMemoryGCCbll()
        throws SnmpStbtusException {
        finbl Mbp<Object,Object> m = JvmContextFbctory.getUserDbtb();

        if (m != null) {
            finbl EnumJvmMemoryGCCbll cbched
                = (EnumJvmMemoryGCCbll) m.get("jvmMemory.getJvmMemoryGCCbll");
            if (cbched != null) return cbched;
        }
        return JvmMemoryGCCbllSupported;
    }

    /**
     * Setter for the "JvmMemoryGCCbll" vbribble.
     */
    public void setJvmMemoryGCCbll(EnumJvmMemoryGCCbll x)
        throws SnmpStbtusException {
        if (x.intVblue() == JvmMemoryGCCbllStbrt.intVblue()) {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            try {
                MbnbgementFbctory.getMemoryMXBebn().gc();
                if (m != null) m.put("jvmMemory.getJvmMemoryGCCbll",
                                     JvmMemoryGCCbllStbrted);
            } cbtch (Exception ex) {
                if (m != null) m.put("jvmMemory.getJvmMemoryGCCbll",
                                     JvmMemoryGCCbllFbiled);
            }
            return;
        }
        throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Checker for the "JvmMemoryGCCbll" vbribble.
     */
    public void checkJvmMemoryGCCbll(EnumJvmMemoryGCCbll x)
        throws SnmpStbtusException {
        if (x.intVblue() != JvmMemoryGCCbllStbrt.intVblue())
        throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmMemoryHebpCommitted" vbribble.
     */
    public Long getJvmMemoryHebpCommitted() throws SnmpStbtusException {
        finbl long vbl = getHebpMemoryUsbge().getCommitted();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public EnumJvmMemoryGCVerboseLevel getJvmMemoryGCVerboseLevel()
        throws SnmpStbtusException {
        if (MbnbgementFbctory.getMemoryMXBebn().isVerbose())
            return JvmMemoryGCVerboseLevelVerbose;
        else
            return JvmMemoryGCVerboseLevelSilent;
    }

    /**
     * Setter for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public void setJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x)
        throws SnmpStbtusException {
        if (JvmMemoryGCVerboseLevelVerbose.intVblue() == x.intVblue())
            MbnbgementFbctory.getMemoryMXBebn().setVerbose(true);
        else
            MbnbgementFbctory.getMemoryMXBebn().setVerbose(fblse);
    }

    /**
     * Checker for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public void checkJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x)
        throws SnmpStbtusException {
        // Nothing to check...
    }

    /**
     * Getter for the "JvmMemoryHebpUsed" vbribble.
     */
    public Long getJvmMemoryHebpUsed() throws SnmpStbtusException {
        finbl long vbl = getHebpMemoryUsbge().getUsed();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryHebpInitSize" vbribble.
     */
    public Long getJvmMemoryHebpInitSize() throws SnmpStbtusException {
        finbl long vbl = getHebpMemoryUsbge().getInit();
        if (vbl > -1) return vbl;
        else return Long0;
    }

    /**
     * Getter for the "JvmMemoryPendingFinblCount" vbribble.
     */
    public Long getJvmMemoryPendingFinblCount()
        throws SnmpStbtusException {
        finbl long vbl = MbnbgementFbctory.getMemoryMXBebn().
            getObjectPendingFinblizbtionCount();

        if (vbl > -1) return Long.vblueOf((int) vbl);

        // Should never hbppen... but stby sbfe bll the sbme.
        //
        else return 0L;
    }

    stbtic finbl MibLogger log = new MibLogger(JvmMemoryImpl.clbss);
}
