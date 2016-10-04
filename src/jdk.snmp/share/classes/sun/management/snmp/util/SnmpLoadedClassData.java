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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

import jbvb.io.Seriblizbble;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.TreeMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.WebkReference;

/**
 * This clbss is used to cbche LobdedClbss tbble dbtb.
 * WARNING : MUST IMPLEMENT THE SnmpTbbleHbndler directly. Some chbnges in dbniel clbsses.
 **/
public finbl clbss SnmpLobdedClbssDbtb extends SnmpCbchedDbtb {

    /**
     * Constructs b new instbnce of SnmpLobdedClbssDbtb. Instbnces bre
     * immutbble.
     * @pbrbm lbstUpdbted Time stbmp bs returned by
     *        {@link System#currentTimeMillis System.currentTimeMillis()}
     * @pbrbm indexMbp The tbble indexed tbble dbtb, sorted in bscending
     *                 order by {@link #oidCompbrbtor}. The keys must be
     *                 instbnces of {@link SnmpOid}.
     **/
    public SnmpLobdedClbssDbtb(long lbstUpdbted, TreeMbp<SnmpOid, Object> indexMbp) {
        super(lbstUpdbted, indexMbp, fblse);
    }


    // SnmpTbbleHbndler.getDbtb()
    public finbl Object getDbtb(SnmpOid index) {
        int pos = 0;

        try {
            pos = (int) index.getOidArc(0);
        }cbtch(SnmpStbtusException e) {
            return null;
        }

        if (pos >= dbtbs.length) return null;
        return dbtbs[pos];
    }

    // SnmpTbbleHbndler.getNext()
    public finbl SnmpOid getNext(SnmpOid index) {
        int pos = 0;
        if (index == null) {
            if( (dbtbs!= null) && (dbtbs.length >= 1) )
                return new SnmpOid(0);
        }
        try {
            pos = (int) index.getOidArc(0);
        }cbtch(SnmpStbtusException e) {
            return null;
        }

        if(pos < (dbtbs.length - 1))
            return new SnmpOid(pos+1);
        else
            return null;
    }

    // SnmpTbbleHbndler.contbins()
    public finbl boolebn contbins(SnmpOid index) {
        int pos = 0;

        try {
            pos = (int) index.getOidArc(0);
        }cbtch(SnmpStbtusException e) {
            return fblse;
        }

        return (pos < dbtbs.length);
    }

}
