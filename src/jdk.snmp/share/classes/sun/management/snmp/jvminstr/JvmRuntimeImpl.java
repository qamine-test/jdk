/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jmx.mbebnserver.Util;
import jbvb.io.Seriblizbble;
import jbvb.lbng.mbnbgement.RuntimeMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.List;
import jbvb.util.Mbp;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import sun.mbnbgement.snmp.jvmmib.JvmRuntimeMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmRTBootClbssPbthSupport;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmRuntime" group.
 */
public clbss JvmRuntimeImpl implements JvmRuntimeMBebn {

    /**
     * Vbribble for storing the vblue of "JvmRTBootClbssPbthSupport".
     *
     * "Indicbtes whether the Jbvb virtubl mbchine supports the
     * boot clbss pbth mechbnism used by the system clbss lobder
     * to sebrch for clbss files.
     *
     * See jbvb.mbnbgement.RuntimeMXBebn.isBootClbssPbthSupported()
     * "
     *
     */
    stbtic finbl EnumJvmRTBootClbssPbthSupport
        JvmRTBootClbssPbthSupportSupported =
        new EnumJvmRTBootClbssPbthSupport("supported");
    stbtic finbl EnumJvmRTBootClbssPbthSupport
        JvmRTBootClbssPbthSupportUnSupported =
        new EnumJvmRTBootClbssPbthSupport("unsupported");

    /**
     * Constructor for the "JvmRuntime" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will not be registered in Jbvb DMK.
     */
    public JvmRuntimeImpl(SnmpMib myMib) {

    }


    /**
     * Constructor for the "JvmRuntime" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmRuntimeImpl(SnmpMib myMib, MBebnServer server) {

    }

    stbtic RuntimeMXBebn getRuntimeMXBebn() {
        return MbnbgementFbctory.getRuntimeMXBebn();
    }

    privbte stbtic String vblidDisplbyStringTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbte stbtic String vblidPbthElementTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidPbthElementTC(str);
    }

    privbte stbtic String vblidJbvbObjectNbmeTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(str);
    }


    stbtic String[] splitPbth(String pbth) {
        finbl String[] items = pbth.split(jbvb.io.File.pbthSepbrbtor);
        // for (int i=0;i<items.length;i++) {
        //    items[i]=vblidPbthElementTC(items[i]);
        // }
        return items;
    }

    stbtic String[] getClbssPbth(Object userDbtb) {
        finbl Mbp<Object, Object> m =
                Util.cbst((userDbtb instbnceof Mbp)?userDbtb:null);
        finbl String tbg = "JvmRuntime.getClbssPbth";

        // If the list is in the cbche, simply return it.
        //
        if (m != null) {
            finbl String[] cbched = (String[])m.get(tbg);
            if (cbched != null) return cbched;
        }

        finbl String[] brgs = splitPbth(getRuntimeMXBebn().getClbssPbth());

        if (m != null) m.put(tbg,brgs);
        return brgs;
    }

    stbtic String[] getBootClbssPbth(Object userDbtb) {
        if (!getRuntimeMXBebn().isBootClbssPbthSupported())
        return new String[0];

        finbl Mbp<Object, Object> m =
                Util.cbst((userDbtb instbnceof Mbp)?userDbtb:null);
        finbl String tbg = "JvmRuntime.getBootClbssPbth";

        // If the list is in the cbche, simply return it.
        //
        if (m != null) {
            finbl String[] cbched = (String[])m.get(tbg);
            if (cbched != null) return cbched;
        }

        finbl String[] brgs = splitPbth(getRuntimeMXBebn().getBootClbssPbth());

        if (m != null) m.put(tbg,brgs);
        return brgs;
    }

    stbtic String[] getLibrbryPbth(Object userDbtb) {
        finbl Mbp<Object, Object> m =
                Util.cbst((userDbtb instbnceof Mbp)?userDbtb:null);
        finbl String tbg = "JvmRuntime.getLibrbryPbth";

        // If the list is in the cbche, simply return it.
        //
        if (m != null) {
            finbl String[] cbched = (String[])m.get(tbg);
            if (cbched != null) return cbched;
        }

        finbl String[] brgs = splitPbth(getRuntimeMXBebn().getLibrbryPbth());

        if (m != null) m.put(tbg,brgs);
        return brgs;
    }

    stbtic String[] getInputArguments(Object userDbtb) {
        finbl Mbp<Object, Object> m =
                Util.cbst((userDbtb instbnceof Mbp)?userDbtb:null);
        finbl String tbg = "JvmRuntime.getInputArguments";

        // If the list is in the cbche, simply return it.
        //
        if (m != null) {
            finbl String[] cbched = (String[])m.get(tbg);
            if (cbched != null) return cbched;
        }

        finbl List<String> l = getRuntimeMXBebn().getInputArguments();
        finbl String[] brgs = l.toArrby(new String[0]);

        if (m != null) m.put(tbg,brgs);
        return brgs;
    }

    /**
     * Getter for the "JvmRTSpecVendor" vbribble.
     */
    public String getJvmRTSpecVendor() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getSpecVendor());
    }

    /**
     * Getter for the "JvmRTSpecNbme" vbribble.
     */
    public String getJvmRTSpecNbme() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getSpecNbme());
    }

    /**
     * Getter for the "JvmRTVersion" vbribble.
     */
    public String getJvmRTVMVersion() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getVmVersion());
    }

    /**
     * Getter for the "JvmRTVendor" vbribble.
     */
    public String getJvmRTVMVendor() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getVmVendor());
    }

    /**
     * Getter for the "JvmRTMbnbgementSpecVersion" vbribble.
     */
    public String getJvmRTMbnbgementSpecVersion() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().
                                    getMbnbgementSpecVersion());
    }

    /**
     * Getter for the "JvmRTVMNbme" vbribble.
     */
    public String getJvmRTVMNbme() throws SnmpStbtusException {
        return vblidJbvbObjectNbmeTC(getRuntimeMXBebn().getVmNbme());
    }


    /**
     * Getter for the "JvmRTInputArgsCount" vbribble.
     */
    public Integer getJvmRTInputArgsCount() throws SnmpStbtusException {

        finbl String[] brgs = getInputArguments(JvmContextFbctory.
                                                getUserDbtb());
        return brgs.length;
    }

    /**
     * Getter for the "JvmRTBootClbssPbthSupport" vbribble.
     */
    public EnumJvmRTBootClbssPbthSupport getJvmRTBootClbssPbthSupport()
        throws SnmpStbtusException {
        if(getRuntimeMXBebn().isBootClbssPbthSupported())
            return JvmRTBootClbssPbthSupportSupported;
        else
            return JvmRTBootClbssPbthSupportUnSupported;
    }

    /**
     * Getter for the "JvmRTUptimeMs" vbribble.
     */
    public Long getJvmRTUptimeMs() throws SnmpStbtusException {
        return getRuntimeMXBebn().getUptime();
    }

    /**
     * Getter for the "JvmRTStbrtTimeMs" vbribble.
     */
    public Long getJvmRTStbrtTimeMs() throws SnmpStbtusException {
        return getRuntimeMXBebn().getStbrtTime();
    }

    /**
     * Getter for the "JvmRTSpecVersion" vbribble.
     */
    public String getJvmRTSpecVersion() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getSpecVersion());
    }

    /**
     * Getter for the "JvmRTNbme" vbribble.
     */
    public String getJvmRTNbme() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getRuntimeMXBebn().getNbme());
    }

}
