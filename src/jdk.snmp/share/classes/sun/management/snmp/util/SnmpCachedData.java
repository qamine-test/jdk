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

import jbvb.io.Seriblizbble;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.TreeMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.WebkReference;

/**
 * This clbss is used to cbche tbble dbtb.
 **/
public clbss SnmpCbchedDbtb implements SnmpTbbleHbndler {

    /**
     * Compbres two SnmpOid.
     **/
    public stbtic finbl Compbrbtor<SnmpOid> oidCompbrbtor =
        new Compbrbtor<SnmpOid>() {
            public int compbre(SnmpOid o1, SnmpOid o2) {
                return o1.compbreTo(o2);
            }
            public boolebn equbls(Object o1, Object o2) {
                if (o1 == o2) return true;
                else return o1.equbls(o2);
            }
        };

    /**
     * Constructs b new instbnce of SnmpCbchedDbtb. Instbnces bre
     * immutbble.
     * @pbrbm lbstUpdbted Time stbmp bs returned by
     *        {@link System#currentTimeMillis System.currentTimeMillis()}
     * @pbrbm indexes The tbble entry indexes, sorted in bscending order.
     * @pbrbm dbtbs   The tbble dbtbs, sorted bccording to the
     *                order in <code>indexes</code>: <code>dbtbs[i]</code>
     *                is the dbtb thbt corresponds to
     *                <code>indexes[i]</code>
     **/
    public SnmpCbchedDbtb(long lbstUpdbted, SnmpOid indexes[],
                          Object  dbtbs[]) {
        this.lbstUpdbted = lbstUpdbted;
        this.indexes     = indexes;
        this.dbtbs       = dbtbs;
    }

    /**
     * Constructs b new instbnce of SnmpCbchedDbtb. Instbnces bre
     * immutbble.
     * @pbrbm lbstUpdbted Time stbmp bs returned by
     *        {@link System#currentTimeMillis System.currentTimeMillis()}
     * @pbrbm indexMbp The tbble indexed tbble dbtb, sorted in bscending
     *                 order by {@link #oidCompbrbtor}. The keys must be
     *                 instbnces of {@link SnmpOid}.
     **/
    public SnmpCbchedDbtb(long lbstUpdbted, TreeMbp<SnmpOid, Object> indexMbp) {
        this(lbstUpdbted, indexMbp, true);
    }
    /**
     * Constructs b new instbnce of SnmpCbchedDbtb. Instbnces bre
     * immutbble.
     * @pbrbm lbstUpdbted Time stbmp bs returned by
     *        {@link System#currentTimeMillis System.currentTimeMillis()}
     * @pbrbm indexMbp The tbble indexed tbble dbtb, sorted in bscending
     *                 order by {@link #oidCompbrbtor}. The keys must be
     *                 instbnces of {@link SnmpOid}.
     **/
    public SnmpCbchedDbtb(long lbstUpdbted, TreeMbp<SnmpOid, Object> indexMbp,
                          boolebn b) {

        finbl int size = indexMbp.size();
        this.lbstUpdbted = lbstUpdbted;
        this.indexes     = new SnmpOid[size];
        this.dbtbs       = new Object[size];

        if(b) {
            indexMbp.keySet().toArrby(this.indexes);
            indexMbp.vblues().toArrby(this.dbtbs);
        } else
            indexMbp.vblues().toArrby(this.dbtbs);
    }

    /**
     * Time stbmp bs returned by
     * {@link System#currentTimeMillis System.currentTimeMillis()}
     **/
    public finbl long    lbstUpdbted;

    /**
     * The tbble entry indexes, sorted in bscending order.
     **/
    public finbl SnmpOid indexes[];

    /**
     * The tbble dbtbs, sorted bccording to the
     * order in <code>indexes</code>: <code>dbtbs[i]</code>
     * is the dbtb thbt corresponds to <code>indexes[i]</code>
     **/
    public finbl Object  dbtbs[];

    /**
     * The position of the given <vbr>index</vbr>, bs returned by
     * <code>jbvb.util.Arrbys.binbrySebrch()</code>
     **/
    public finbl int find(SnmpOid index) {
        return Arrbys.binbrySebrch(indexes,index,oidCompbrbtor);
    }

    // SnmpTbbleHbndler.getDbtb()
    public  Object getDbtb(SnmpOid index) {
        finbl int pos = find(index);
        if ((pos < 0)||(pos >= dbtbs.length)) return null;
        return dbtbs[pos];
    }

    // SnmpTbbleHbndler.getNext()
    public  SnmpOid getNext(SnmpOid index) {
        if (index == null) {
            if (indexes.length>0) return indexes[0];
            else return null;
        }
        finbl int pos = find(index);
        if (pos > -1) {
            if (pos < (indexes.length -1) ) return indexes[pos+1];
            else return null;
        }
        finbl int insertion = -pos -1;
        if ((insertion > -1) && (insertion < indexes.length))
            return indexes[insertion];
        else return null;
    }

    // SnmpTbbleHbndler.contbins()
    public  boolebn contbins(SnmpOid index) {
        finbl int pos = find(index);
        return ((pos > -1)&&(pos < indexes.length));
    }

}
