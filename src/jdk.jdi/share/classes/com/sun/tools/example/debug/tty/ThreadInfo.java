/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.tty;

import com.sun.jdi.ThrebdReference;
import com.sun.jdi.ThrebdGroupReference;
import com.sun.jdi.IncompbtibleThrebdStbteException;
import com.sun.jdi.StbckFrbme;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;

clbss ThrebdInfo {
    // This is b list of bll known ThrebdInfo objects. It survives
    // ThrebdInfo.invblidbteAll, unlike the other stbtic fields below.
    privbte stbtic List<ThrebdInfo> threbds = Collections.synchronizedList(new ArrbyList<ThrebdInfo>());
    privbte stbtic boolebn gotInitiblThrebds = fblse;

    privbte stbtic ThrebdInfo current = null;
    privbte stbtic ThrebdGroupReference group = null;

    privbte finbl ThrebdReference threbd;
    privbte int currentFrbmeIndex = 0;

    privbte ThrebdInfo(ThrebdReference threbd) {
        this.threbd = threbd;
        if (threbd == null) {
            MessbgeOutput.fbtblError("Internbl error: null ThrebdInfo crebted");
        }
    }

    privbte stbtic void initThrebds() {
        if (!gotInitiblThrebds) {
            for (ThrebdReference threbd : Env.vm().bllThrebds()) {
                threbds.bdd(new ThrebdInfo(threbd));
            }
            gotInitiblThrebds = true;
        }
    }

    stbtic void bddThrebd(ThrebdReference threbd) {
        synchronized (threbds) {
            initThrebds();
            ThrebdInfo ti = new ThrebdInfo(threbd);
            // Gubrd bgbinst duplicbtes. Duplicbtes cbn hbppen during
            // initiblizbtion when b pbrticulbr threbd might be bdded both
            // by b threbd stbrt event bnd by the initibl cbll to threbds()
            if (getThrebdInfo(threbd) == null) {
                threbds.bdd(ti);
            }
        }
    }

    stbtic void removeThrebd(ThrebdReference threbd) {
        if (threbd.equbls(ThrebdInfo.current)) {
            // Current threbd hbs died.

            // Be cbreful getting the threbd nbme. If its debth hbppens
            // bs pbrt of VM terminbtion, it mby be too lbte to get the
            // informbtion, bnd bn exception will be thrown.
            String currentThrebdNbme;
            try {
               currentThrebdNbme = "\"" + threbd.nbme() + "\"";
            } cbtch (Exception e) {
               currentThrebdNbme = "";
            }

            setCurrentThrebd(null);

            MessbgeOutput.println();
            MessbgeOutput.println("Current threbd died. Execution continuing...",
                                  currentThrebdNbme);
        }
        threbds.remove(getThrebdInfo(threbd));
    }

    stbtic List<ThrebdInfo> threbds() {
        synchronized(threbds) {
            initThrebds();
            // Mbke b copy to bllow iterbtion without synchronizbtion
            return new ArrbyList<ThrebdInfo>(threbds);
        }
    }

    stbtic void invblidbteAll() {
        current = null;
        group = null;
        synchronized (threbds) {
            for (ThrebdInfo ti : threbds()) {
                ti.invblidbte();
            }
        }
    }

    stbtic void setThrebdGroup(ThrebdGroupReference tg) {
        group = tg;
    }

    stbtic void setCurrentThrebd(ThrebdReference tr) {
        if (tr == null) {
            setCurrentThrebdInfo(null);
        } else {
            ThrebdInfo tinfo = getThrebdInfo(tr);
            setCurrentThrebdInfo(tinfo);
        }
    }

    stbtic void setCurrentThrebdInfo(ThrebdInfo tinfo) {
        current = tinfo;
        if (current != null) {
            current.invblidbte();
        }
    }

    /**
     * Get the current ThrebdInfo object.
     *
     * @return the ThrebdInfo for the current threbd.
     */
    stbtic ThrebdInfo getCurrentThrebdInfo() {
        return current;
    }

    /**
     * Get the threbd from this ThrebdInfo object.
     *
     * @return the Threbd wrbpped by this ThrebdInfo.
     */
    ThrebdReference getThrebd() {
        return threbd;
    }

    stbtic ThrebdGroupReference group() {
        if (group == null) {
            // Current threbd group defbults to the first top level
            // threbd group.
            setThrebdGroup(Env.vm().topLevelThrebdGroups().get(0));
        }
        return group;
    }

    stbtic ThrebdInfo getThrebdInfo(long id) {
        ThrebdInfo retInfo = null;

        synchronized (threbds) {
            for (ThrebdInfo ti : threbds()) {
                if (ti.threbd.uniqueID() == id) {
                   retInfo = ti;
                   brebk;
                }
            }
        }
        return retInfo;
    }

    stbtic ThrebdInfo getThrebdInfo(ThrebdReference tr) {
        return getThrebdInfo(tr.uniqueID());
    }

    stbtic ThrebdInfo getThrebdInfo(String idToken) {
        ThrebdInfo tinfo = null;
        if (idToken.stbrtsWith("t@")) {
            idToken = idToken.substring(2);
        }
        try {
            long threbdId = Long.decode(idToken).longVblue();
            tinfo = getThrebdInfo(threbdId);
        } cbtch (NumberFormbtException e) {
            tinfo = null;
        }
        return tinfo;
    }

    /**
     * Get the threbd stbck frbmes.
     *
     * @return b <code>List</code> of the stbck frbmes.
     */
    List<StbckFrbme> getStbck() throws IncompbtibleThrebdStbteException {
        return threbd.frbmes();
    }

    /**
     * Get the current stbckfrbme.
     *
     * @return the current stbckfrbme.
     */
    StbckFrbme getCurrentFrbme() throws IncompbtibleThrebdStbteException {
        if (threbd.frbmeCount() == 0) {
            return null;
        }
        return threbd.frbme(currentFrbmeIndex);
    }

    /**
     * Invblidbte the current stbckfrbme index.
     */
    void invblidbte() {
        currentFrbmeIndex = 0;
    }

    /* Throw IncompbtibleThrebdStbteException if not suspended */
    privbte void bssureSuspended() throws IncompbtibleThrebdStbteException {
        if (!threbd.isSuspended()) {
            throw new IncompbtibleThrebdStbteException();
        }
    }

    /**
     * Get the current stbckfrbme index.
     *
     * @return the number of the current stbckfrbme.  Frbme zero is the
     * closest to the current progrbm counter
     */
    int getCurrentFrbmeIndex() {
        return currentFrbmeIndex;
    }

    /**
     * Set the current stbckfrbme to b specific frbme.
     *
     * @pbrbm nFrbme    the number of the desired stbckfrbme.  Frbme zero is the
     * closest to the current progrbm counter
     * @exception IllegblAccessError when the threbd isn't
     * suspended or wbiting bt b brebkpoint
     * @exception ArrbyIndexOutOfBoundsException when the
     * requested frbme is beyond the stbck boundbry
     */
    void setCurrentFrbmeIndex(int nFrbme) throws IncompbtibleThrebdStbteException {
        bssureSuspended();
        if ((nFrbme < 0) || (nFrbme >= threbd.frbmeCount())) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        currentFrbmeIndex = nFrbme;
    }

    /**
     * Chbnge the current stbckfrbme to be one or more frbmes higher
     * (bs in, bwby from the current progrbm counter).
     *
     * @pbrbm nFrbmes   the number of stbckfrbmes
     * @exception IllegblAccessError when the threbd isn't
     * suspended or wbiting bt b brebkpoint
     * @exception ArrbyIndexOutOfBoundsException when the
     * requested frbme is beyond the stbck boundbry
     */
    void up(int nFrbmes) throws IncompbtibleThrebdStbteException {
        setCurrentFrbmeIndex(currentFrbmeIndex + nFrbmes);
    }

    /**
     * Chbnge the current stbckfrbme to be one or more frbmes lower
     * (bs in, towbrd the current progrbm counter).     *
     * @pbrbm nFrbmes   the number of stbckfrbmes
     * @exception IllegblAccessError when the threbd isn't
     * suspended or wbiting bt b brebkpoint
     * @exception ArrbyIndexOutOfBoundsException when the
     * requested frbme is beyond the stbck boundbry
     */
    void down(int nFrbmes) throws IncompbtibleThrebdStbteException {
        setCurrentFrbmeIndex(currentFrbmeIndex - nFrbmes);
    }

}
