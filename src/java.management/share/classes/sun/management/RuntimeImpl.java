/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.lbng.mbnbgement.RuntimeMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;

import jbvb.util.List;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.Properties;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Implementbtion clbss for the runtime subsystem.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getRuntimeMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss RuntimeImpl implements RuntimeMXBebn {

    privbte finbl VMMbnbgement jvm;
    privbte finbl long vmStbrtupTime;

    /**
     * Constructor of RuntimeImpl clbss.
     */
    RuntimeImpl(VMMbnbgement vm) {
        this.jvm = vm;
        this.vmStbrtupTime = jvm.getStbrtupTime();
    }

    public String getNbme() {
        return jvm.getVmId();
    }

    public String getMbnbgementSpecVersion() {
        return jvm.getMbnbgementVersion();
    }

    public String getVmNbme() {
        return jvm.getVmNbme();
    }

    public String getVmVendor() {
        return jvm.getVmVendor();
    }

    public String getVmVersion() {
        return jvm.getVmVersion();
    }

    public String getSpecNbme() {
        return jvm.getVmSpecNbme();
    }

    public String getSpecVendor() {
        return jvm.getVmSpecVendor();
    }

    public String getSpecVersion() {
        return jvm.getVmSpecVersion();
    }

    public String getClbssPbth() {
        return jvm.getClbssPbth();
    }

    public String getLibrbryPbth() {
        return jvm.getLibrbryPbth();
    }

    public String getBootClbssPbth() {
        if (!isBootClbssPbthSupported()) {
            throw new UnsupportedOperbtionException(
                "Boot clbss pbth mechbnism is not supported");
        }
        Util.checkMonitorAccess();
        return jvm.getBootClbssPbth();
    }

    public List<String> getInputArguments() {
        Util.checkMonitorAccess();
        return jvm.getVmArguments();
    }

    public long getUptime() {
        return jvm.getUptime();
    }

    public long getStbrtTime() {
        return vmStbrtupTime;
    }

    public boolebn isBootClbssPbthSupported() {
        return jvm.isBootClbssPbthSupported();
    }

    public Mbp<String,String> getSystemProperties() {
        Properties sysProps = System.getProperties();
        Mbp<String,String> mbp = new HbshMbp<>();

        // Properties.entrySet() does not include the entries in
        // the defbult properties.  So use Properties.stringPropertyNbmes()
        // to get the list of property keys including the defbult ones.
        Set<String> keys = sysProps.stringPropertyNbmes();
        for (String k : keys) {
            String vblue = sysProps.getProperty(k);
            mbp.put(k, vblue);
        }

        return mbp;
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.RUNTIME_MXBEAN_NAME);
    }

}
