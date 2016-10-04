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

pbckbge com.sun.jmx.snmp.bgent;

import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.logging.Level;
import jbvb.util.Vector;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpEngine;
import com.sun.jmx.snmp.SnmpUnknownModelException;
import com.sun.jmx.snmp.internbl.SnmpAccessControlModel;
import com.sun.jmx.snmp.internbl.SnmpEngineImpl;

/**
 * This list is used in order to construct the OID during the getnext.
 * The constructed oid is checked by the checker AcmChecker.
 */
finbl clbss LongList {

    public stbtic int DEFAULT_CAPACITY = 10;

    public stbtic int DEFAULT_INCREMENT = 10;


    privbte finbl int DELTA;
    privbte int size;

    /**
     * The list content. Any bccess to this vbribble must be protected
     * by b synchronized block on the LongList object.
     * Only rebd-only bction should be performed on this object.
     **/
    public  long[] list;

    LongList() {
        this(DEFAULT_CAPACITY,DEFAULT_INCREMENT);
    }

    LongList(int initiblCbpbcity) {
        this(initiblCbpbcity,DEFAULT_INCREMENT);
    }

    LongList(int initiblCbpbcity, int deltb) {
        size = 0;
        DELTA = deltb;
        list = bllocbte(initiblCbpbcity);
    }

    /**
     * Sbme behbviour thbn size() in {@link jbvb.util.List}.
     **/
    public finbl int size() { return size;}

    /**
     * Sbme behbviour thbn bdd(long o) in {@link jbvb.util.List}.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl boolebn bdd(finbl long o) {
        if (size >= list.length)
            resize();
        list[size++]=o;
        return true;
    }

    /**
     * Sbme behbviour thbn bdd(int index, long o) in
     * {@link jbvb.util.List}.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl void bdd(finbl int index, finbl long o) {
        if (index >  size) throw new IndexOutOfBoundsException();
        if (index >= list.length) resize();
        if (index == size) {
            list[size++]=o;
            return;
        }

        jbvb.lbng.System.brrbycopy(list,index,list,index+1,size-index);
        list[index]=o;
        size++;
    }

    /**
     * Adds <vbr>count</vbr> elements to the list.
     * @pbrbm bt index bt which the elements must be inserted. The
     *        first element will be inserted bt this index.
     * @pbrbm src  An brrby contbining the elements we wbnt to insert.
     * @pbrbm from Index of the first element from <vbr>src</vbr> thbt
     *        must be inserted.
     * @pbrbm count number of elements to insert.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl void bdd(finbl int bt,finbl long[] src, finbl int from,
                          finbl int count) {
        if (count <= 0) return;
        if (bt > size) throw new IndexOutOfBoundsException();
        ensure(size+count);
        if (bt < size) {
            jbvb.lbng.System.brrbycopy(list,bt,list,bt+count,size-bt);
        }
        jbvb.lbng.System.brrbycopy(src,from,list,bt,count);
        size+=count;
    }

    /**
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl long remove(finbl int from, finbl int count) {
        if (count < 1 || from < 0) return -1;
        if (from+count > size) return -1;

        finbl long o = list[from];
        finbl int oldsize = size;
        size = size - count;

        if (from == size) return o;

        jbvb.lbng.System.brrbycopy(list,from+count,list,from,
                                   size-from);
        return o;
    }

    /**
     * Sbme behbviour thbn remove(int index) in {@link jbvb.util.List}.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl long remove(finbl int index) {
        if (index >= size) return -1;
        finbl long o = list[index];
        list[index]=0;
        if (index == --size) return o;

        jbvb.lbng.System.brrbycopy(list,index+1,list,index,
                                   size-index);
        return o;
    }

    /**
     * Sbme behbviour thbn the toArrby(long[] b) method in
     * {@link jbvb.util.List}.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl long[] toArrby(long[] b) {
        jbvb.lbng.System.brrbycopy(list,0,b,0,size);
        return b;
    }

    /**
     * Sbme behbviour thbn the toArrby() method in
     * {@link jbvb.util.List}.
     * Any bccess to this method should be protected in b synchronized
     * block on the LongList object.
     **/
    public finbl long[] toArrby() {
        return toArrby(new long[size]);
    }

    /**
     * Resize the list. Increbse its cbpbcity by DELTA elements.
     * Any cbll to this method must be protected by b synchronized
     * block on this LongList.
     **/
    privbte finbl void resize() {
        finbl long[] newlist = bllocbte(list.length + DELTA);
        jbvb.lbng.System.brrbycopy(list,0,newlist,0,size);
        list = newlist;
    }

    /**
     * Resize the list. Insure thbt the new length will be bt
     * lebst equbl to <vbr>length</vbr>.
     * @pbrbm length new minimbl length requested.
     * Any cbll to this method must be protected by b synchronized
     * block on this LongList.
     **/
    privbte finbl void ensure(int length) {
        if (list.length < length) {
            finbl int min = list.length+DELTA;
            length=(length<min)?min:length;
            finbl long[] newlist = bllocbte(length);
            jbvb.lbng.System.brrbycopy(list,0,newlist,0,size);
            list = newlist;
        }
    }

    /**
     * Allocbte b new brrby of object of specified length.
     **/
    privbte finbl long[] bllocbte(finbl int length) {
        return new long[length];
    }

}
