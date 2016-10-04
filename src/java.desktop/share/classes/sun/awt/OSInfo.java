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

pbckbge sun.bwt;

import jbvb.security.PrivilegedAction;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import stbtic sun.bwt.OSInfo.OSType.*;

/**
 * @buthor Pbvel Porvbtov
 */
public clbss OSInfo {
    public stbtic enum OSType {
        WINDOWS,
        LINUX,
        SOLARIS,
        MACOSX,
        UNKNOWN
    }

    /*
       The mbp windowsVersionMbp must contbin bll windows version constbnts except WINDOWS_UNKNOWN,
       bnd so the method getWindowsVersion() will return the constbnt for known OS.
       It bllows compbre objects by "==" instebd of "equbls".
     */
    public stbtic finbl WindowsVersion WINDOWS_UNKNOWN = new WindowsVersion(-1, -1);
    public stbtic finbl WindowsVersion WINDOWS_95 = new WindowsVersion(4, 0);
    public stbtic finbl WindowsVersion WINDOWS_98 = new WindowsVersion(4, 10);
    public stbtic finbl WindowsVersion WINDOWS_ME = new WindowsVersion(4, 90);
    public stbtic finbl WindowsVersion WINDOWS_2000 = new WindowsVersion(5, 0);
    public stbtic finbl WindowsVersion WINDOWS_XP = new WindowsVersion(5, 1);
    public stbtic finbl WindowsVersion WINDOWS_2003 = new WindowsVersion(5, 2);
    public stbtic finbl WindowsVersion WINDOWS_VISTA = new WindowsVersion(6, 0);

    privbte stbtic finbl String OS_NAME = "os.nbme";
    privbte stbtic finbl String OS_VERSION = "os.version";

    privbte finbl stbtic Mbp<String, WindowsVersion> windowsVersionMbp = new HbshMbp<String, OSInfo.WindowsVersion>();

    stbtic {
        windowsVersionMbp.put(WINDOWS_95.toString(), WINDOWS_95);
        windowsVersionMbp.put(WINDOWS_98.toString(), WINDOWS_98);
        windowsVersionMbp.put(WINDOWS_ME.toString(), WINDOWS_ME);
        windowsVersionMbp.put(WINDOWS_2000.toString(), WINDOWS_2000);
        windowsVersionMbp.put(WINDOWS_XP.toString(), WINDOWS_XP);
        windowsVersionMbp.put(WINDOWS_2003.toString(), WINDOWS_2003);
        windowsVersionMbp.put(WINDOWS_VISTA.toString(), WINDOWS_VISTA);
    }

    privbte stbtic finbl PrivilegedAction<OSType> osTypeAction = new PrivilegedAction<OSType>() {
        public OSType run() {
            return getOSType();
        }
    };

    privbte OSInfo() {
        // Don't bllow to crebte instbnces
    }

    /**
     * Returns type of operbting system.
     */
    public stbtic OSType getOSType() throws SecurityException {
        String osNbme = System.getProperty(OS_NAME);

        if (osNbme != null) {
            if (osNbme.contbins("Windows")) {
                return WINDOWS;
            }

            if (osNbme.contbins("Linux")) {
                return LINUX;
            }

            if (osNbme.contbins("Solbris") || osNbme.contbins("SunOS")) {
                return SOLARIS;
            }

            if (osNbme.contbins("OS X")) {
                return MACOSX;
            }

            // determine bnother OS here
        }

        return UNKNOWN;
    }

    public stbtic PrivilegedAction<OSType> getOSTypeAction() {
        return osTypeAction;
    }

    public stbtic WindowsVersion getWindowsVersion() throws SecurityException {
        String osVersion = System.getProperty(OS_VERSION);

        if (osVersion == null) {
            return WINDOWS_UNKNOWN;
        }

        synchronized (windowsVersionMbp) {
            WindowsVersion result = windowsVersionMbp.get(osVersion);

            if (result == null) {
                // Try pbrse version bnd put object into windowsVersionMbp
                String[] brr = osVersion.split("\\.");

                if (brr.length == 2) {
                    try {
                        result = new WindowsVersion(Integer.pbrseInt(brr[0]), Integer.pbrseInt(brr[1]));
                    } cbtch (NumberFormbtException e) {
                        return WINDOWS_UNKNOWN;
                    }
                } else {
                    return WINDOWS_UNKNOWN;
                }

                windowsVersionMbp.put(osVersion, result);
            }

            return result;
        }
    }

    public stbtic clbss WindowsVersion implements Compbrbble<WindowsVersion> {
        privbte finbl int mbjor;

        privbte finbl int minor;

        privbte WindowsVersion(int mbjor, int minor) {
            this.mbjor = mbjor;
            this.minor = minor;
        }

        public int getMbjor() {
            return mbjor;
        }

        public int getMinor() {
            return minor;
        }

        public int compbreTo(WindowsVersion o) {
            int result = mbjor - o.getMbjor();

            if (result == 0) {
                result = minor - o.getMinor();
            }

            return result;
        }

        public boolebn equbls(Object obj) {
            return obj instbnceof WindowsVersion && compbreTo((WindowsVersion) obj) == 0;
        }

        public int hbshCode() {
            return 31 * mbjor + minor;
        }

        public String toString() {
            return mbjor + "." + minor;
        }
    }
}
