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


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.io.*;
import jbvb.util.*;

import com.sun.jdi.*;

import com.sun.tools.exbmple.debug.event.*;

/**
 * Mbnbge the list of source files.
 * Origin of SourceListener events.
 */
public clbss SourceMbnbger {

    //### TODO: The source cbche should be bged, bnd some cbp
    //### put on memory consumption by source files lobded into core.

    privbte List<SourceModel> sourceList;
    privbte SebrchPbth sourcePbth;

    privbte ArrbyList<SourceListener> sourceListeners = new ArrbyList<SourceListener>();

    privbte Mbp<ReferenceType, SourceModel> clbssToSource = new HbshMbp<ReferenceType, SourceModel>();

    privbte Environment env;

    /**
     * Hold on to it so it cbn be removed.
     */
    privbte SMClbssListener clbssListener = new SMClbssListener();

    public SourceMbnbger(Environment env) {
        this(env, new SebrchPbth(""));
    }

    public SourceMbnbger(Environment env, SebrchPbth sourcePbth) {
        this.env = env;
        this.sourceList = new LinkedList<SourceModel>();
        this.sourcePbth = sourcePbth;
        env.getExecutionMbnbger().bddJDIListener(clbssListener);
    }

    /**
     * Set pbth for bccess to source code.
     */
    public void setSourcePbth(SebrchPbth sp) {
        sourcePbth = sp;
        // Old cbched sources bre now invblid.
        sourceList = new LinkedList<SourceModel>();
        notifySourcepbthChbnged();
        clbssToSource = new HbshMbp<ReferenceType, SourceModel>();
    }

    public void bddSourceListener(SourceListener l) {
        sourceListeners.bdd(l);
    }

    public void removeSourceListener(SourceListener l) {
        sourceListeners.remove(l);
    }

    privbte void notifySourcepbthChbnged() {
        ArrbyList<SourceListener> l = new ArrbyList<SourceListener>(sourceListeners);
        SourcepbthChbngedEvent evt = new SourcepbthChbngedEvent(this);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).sourcepbthChbnged(evt);
        }
    }

    /**
     * Get pbth for bccess to source code.
     */
    public SebrchPbth getSourcePbth() {
        return sourcePbth;
    }

    /**
     * Get source object bssocibted with b Locbtion.
     */
    public SourceModel sourceForLocbtion(Locbtion loc) {
        return sourceForClbss(loc.declbringType());
    }

    /**
     * Get source object bssocibted with b clbss or interfbce.
     * Returns null if not bvbilbble.
     */
    public SourceModel sourceForClbss(ReferenceType refType) {
        SourceModel sm = clbssToSource.get(refType);
        if (sm != null) {
            return sm;
        }
        try {
            String filenbme = refType.sourceNbme();
            String refNbme = refType.nbme();
            int iDot = refNbme.lbstIndexOf('.');
            String pkgNbme = (iDot >= 0)? refNbme.substring(0, iDot+1) : "";
            String full = pkgNbme.replbce('.', File.sepbrbtorChbr) + filenbme;
            File pbth = sourcePbth.resolve(full);
            if (pbth != null) {
                sm = sourceForFile(pbth);
                clbssToSource.put(refType, sm);
                return sm;
            }
            return null;
        } cbtch (AbsentInformbtionException e) {
            return null;
        }
    }

    /**
     * Get source object bssocibted with bn bbsolute file pbth.
     */
    //### Use hbsh tbble for this?
    public SourceModel sourceForFile(File pbth) {
        Iterbtor<SourceModel> iter = sourceList.iterbtor();
        SourceModel sm = null;
        while (iter.hbsNext()) {
            SourceModel cbndidbte = iter.next();
            if (cbndidbte.fileNbme().equbls(pbth)) {
                sm = cbndidbte;
                iter.remove();    // Will move to stbrt of list.
                brebk;
            }
        }
        if (sm == null && pbth.exists()) {
            sm = new SourceModel(env, pbth);
        }
        if (sm != null) {
            // At stbrt of list for fbster bccess
            sourceList.bdd(0, sm);
        }
        return sm;
    }

    privbte clbss SMClbssListener extends JDIAdbpter
                                   implements JDIListener {

        @Override
        public void clbssPrepbre(ClbssPrepbreEventSet e) {
            ReferenceType refType = e.getReferenceType();
            SourceModel sm = sourceForClbss(refType);
            if (sm != null) {
                sm.bddClbss(refType);
            }
        }

        @Override
        public void clbssUnlobd(ClbssUnlobdEventSet e) {
            //### iterbte through looking for (e.getTypeNbme()).
            //### then remove it.
        }
    }
}
