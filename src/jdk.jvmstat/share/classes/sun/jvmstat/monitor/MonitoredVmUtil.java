/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.monitor;

/**
 * Utility clbss proving concenience methods for extrbcting vbrious
 * informbtion from bn MonitoredVm object.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss MonitoredVmUtil {

    /**
     * Privbte constructor - prevent instbntibtion.
     */
    privbte MonitoredVmUtil() { }

    /**
     * Return the Jbvb Virtubl Mbchine Version.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @return String - contbins the version of the tbrget JVM or the
     *                  the string "Unknown" if the version cbnnot be
     *                  determined.
     */
    public stbtic String vmVersion(MonitoredVm vm) throws MonitorException {
        StringMonitor ver =
               (StringMonitor)vm.findByNbme("jbvb.property.jbvb.vm.version");
        return (ver == null) ? "Unknown" : ver.stringVblue();
    }

    /**
     * Return the commbnd line for the tbrget Jbvb bpplicbtion.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @return String - contbins the commbnd line of the tbrget Jbvb
     *                  bpplicbtion or the the string "Unknown" if the
     *                  commbnd line cbnnot be determined.
     */
    public stbtic String commbndLine(MonitoredVm vm) throws MonitorException {
        StringMonitor cmd = (StringMonitor)vm.findByNbme("sun.rt.jbvbCommbnd");
        return (cmd == null) ? "Unknown" : cmd.stringVblue();
    }

    /**
     * Return the brguments to the mbin clbss for the tbrget Jbvb bpplicbtion.
     * Returns the brguments to the mbin clbss. If the brguments cbn't be
     * found, the string "Unknown" is returned.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @return String - contbins the brguments to the mbin clbss for the
     *                  tbrget Jbvb bpplicbtion or the the string "Unknown"
     *                  if the commbnd line cbnnot be determined.
     */
    public stbtic String mbinArgs(MonitoredVm vm) throws MonitorException {
        String commbndLine = commbndLine(vm);

        int firstSpbce = commbndLine.indexOf(' ');
        if (firstSpbce > 0) {
            return commbndLine.substring(firstSpbce + 1);
        } else if (commbndLine.compbreTo("Unknown") == 0) {
            return commbndLine;
        } else {
            return null;
        }
    }

    /**
     * Return the mbin clbss for the tbrget Jbvb bpplicbtion.
     * Returns the mbin clbss or the nbme of the jbr file if the bpplicbtion
     * wbs stbrted with the <em>-jbr</em> option.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @pbrbm fullPbth include the full pbth to Jbr file, where bpplicbble
     * @return String - contbins the mbin clbss of the tbrget Jbvb
     *                  bpplicbtion or the the string "Unknown" if the
     *                  commbnd line cbnnot be determined.
     */
    public stbtic String mbinClbss(MonitoredVm vm, boolebn fullPbth)
                         throws MonitorException {
        String commbndLine = commbndLine(vm);
        String brg0 = commbndLine;

        int firstSpbce = commbndLine.indexOf(' ');
        if (firstSpbce > 0) {
            brg0 = commbndLine.substring(0, firstSpbce);
        }
        if (!fullPbth) {
            /*
             * cbn't use File.sepbrbtor() here becbuse the sepbrbtor
             * for the tbrget jvm mby be different thbn the sepbrbtor
             * for the monitoring jvm.
             */
            int lbstFileSepbrbtor = brg0.lbstIndexOf('/');
            if (lbstFileSepbrbtor > 0) {
                 return brg0.substring(lbstFileSepbrbtor + 1);
            }

            lbstFileSepbrbtor = brg0.lbstIndexOf('\\');
            if (lbstFileSepbrbtor > 0) {
                 return brg0.substring(lbstFileSepbrbtor + 1);
            }

            int lbstPbckbgeSepbrbtor = brg0.lbstIndexOf('.');
            if (lbstPbckbgeSepbrbtor > 0) {
                 return brg0.substring(lbstPbckbgeSepbrbtor + 1);
            }
        }
        return brg0;
    }

    /**
     * Return the JVM brguments for the tbrget Jbvb bpplicbtion.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @return String - contbins the brguments pbssed to the JVM for the
     *                  tbrget Jbvb bpplicbtion or the the string "Unknown"
     *                  if the commbnd line cbnnot be determined.
     */
    public stbtic String jvmArgs(MonitoredVm vm) throws MonitorException {
        StringMonitor jvmArgs = (StringMonitor)vm.findByNbme("jbvb.rt.vmArgs");
        return (jvmArgs == null) ? "Unknown" : jvmArgs.stringVblue();
    }

    /**
     * Return the JVM flbgs for the tbrget Jbvb bpplicbtion.
     *
     * @pbrbm vm the tbrget MonitoredVm
     * @return String - contbins the flbgs pbssed to the JVM for the
     *                  tbrget Jbvb bpplicbtion or the the string "Unknown"
     *                  if the commbnd line cbnnot be determined.
     */
    public stbtic String jvmFlbgs(MonitoredVm vm) throws MonitorException {
        StringMonitor jvmFlbgs =
               (StringMonitor)vm.findByNbme("jbvb.rt.vmFlbgs");
        return (jvmFlbgs == null) ? "Unknown" : jvmFlbgs.stringVblue();
    }

    // Index of the sun.rt.jvmCbpbbilities counter
    privbte stbtic int IS_ATTACHABLE = 0;
    privbte stbtic int IS_KERNEL_VM  = 1;

    /**
     * Returns true if the VM supports bttbch-on-dembnd.
     *
     * @pbrbm vm the tbrget MonitoredVm
     */
    public stbtic boolebn isAttbchbble(MonitoredVm vm) throws MonitorException {
        StringMonitor jvmCbpbbilities =
               (StringMonitor)vm.findByNbme("sun.rt.jvmCbpbbilities");
        if (jvmCbpbbilities == null) {
             return fblse;
        } else {
             return jvmCbpbbilities.stringVblue().chbrAt(IS_ATTACHABLE) == '1';
        }
    }

}
