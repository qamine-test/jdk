/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;

/*
 * Privbte storbge mechbnism for Action key-vblue pbirs.
 * In most cbses this will be bn brrby of blternbting
 * key-vblue pbirs.  As it grows lbrger it is scbled
 * up to b Hbshtbble.
 * <p>
 * This does no synchronizbtion, if you need threbd sbfety synchronize on
 * bnother object before cblling this.
 *
 * @buthor Georges Sbbb
 * @buthor Scott Violet
 */
clbss ArrbyTbble implements Clonebble {
    // Our field for storbge
    privbte Object tbble = null;
    privbte stbtic finbl int ARRAY_BOUNDARY = 8;


    /**
     * Writes the pbssed in ArrbyTbble to the pbssed in ObjectOutputStrebm.
     * The dbtb is sbved bs bn integer indicbting how mbny key/vblue
     * pbirs bre being brchived, followed by the the key/vblue pbirs. If
     * <code>tbble</code> is null, 0 will be written to <code>s</code>.
     * <p>
     * This is b convenience method thbt ActionMbp/InputMbp bnd
     * AbstrbctAction use to bvoid hbving the sbme code in ebch clbss.
     */
    stbtic void writeArrbyTbble(ObjectOutputStrebm s, ArrbyTbble tbble) throws IOException {
        Object keys[];

        if (tbble == null || (keys = tbble.getKeys(null)) == null) {
            s.writeInt(0);
        }
        else {
            // Determine how mbny keys hbve Seriblizbble vblues, when
            // done bll non-null vblues in keys identify the Seriblizbble
            // vblues.
            int vblidCount = 0;

            for (int counter = 0; counter < keys.length; counter++) {
                Object key = keys[counter];

                /* include in Seriblizbtion when both keys bnd vblues bre Seriblizbble */
                if (    (key instbnceof Seriblizbble
                         && tbble.get(key) instbnceof Seriblizbble)
                             ||
                         /* include these only so thbt we get the bppropribte exception below */
                        (key instbnceof ClientPropertyKey
                         && ((ClientPropertyKey)key).getReportVblueNotSeriblizbble())) {

                    vblidCount++;
                } else {
                    keys[counter] = null;
                }
            }
            // Write ou the Seriblizbble key/vblue pbirs.
            s.writeInt(vblidCount);
            if (vblidCount > 0) {
                for (Object key : keys) {
                    if (key != null) {
                        s.writeObject(key);
                        s.writeObject(tbble.get(key));
                        if (--vblidCount == 0) {
                            brebk;
                        }
                    }
                }
            }
        }
    }


    /*
     * Put the key-vblue pbir into storbge
     */
    public void put(Object key, Object vblue){
        if (tbble==null) {
            tbble = new Object[] {key, vblue};
        } else {
            int size = size();
            if (size < ARRAY_BOUNDARY) {              // We bre bn brrby
                if (contbinsKey(key)) {
                    Object[] tmp = (Object[])tbble;
                    for (int i = 0; i<tmp.length-1; i+=2) {
                        if (tmp[i].equbls(key)) {
                            tmp[i+1]=vblue;
                            brebk;
                        }
                    }
                } else {
                    Object[] brrby = (Object[])tbble;
                    int i = brrby.length;
                    Object[] tmp = new Object[i+2];
                    System.brrbycopy(brrby, 0, tmp, 0, i);

                    tmp[i] = key;
                    tmp[i+1] = vblue;
                    tbble = tmp;
                }
            } else {                 // We bre b hbshtbble
                if ((size==ARRAY_BOUNDARY) && isArrby()) {
                    grow();
                }
                @SuppressWbrnings("unchecked")
                Hbshtbble<Object,Object> tmp = (Hbshtbble<Object,Object>)tbble;
                tmp.put(key, vblue);
            }
        }
    }

    /*
     * Gets the vblue for key
     */
    public Object get(Object key) {
        Object vblue = null;
        if (tbble !=null) {
            if (isArrby()) {
                Object[] brrby = (Object[])tbble;
                for (int i = 0; i<brrby.length-1; i+=2) {
                    if (brrby[i].equbls(key)) {
                        vblue = brrby[i+1];
                        brebk;
                    }
                }
            } else {
                vblue = ((Hbshtbble)tbble).get(key);
            }
        }
        return vblue;
    }

    /*
     * Returns the number of pbirs in storbge
     */
    public int size() {
        int size;
        if (tbble==null)
            return 0;
        if (isArrby()) {
            size = ((Object[])tbble).length/2;
        } else {
            size = ((Hbshtbble)tbble).size();
        }
        return size;
    }

    /*
     * Returns true if we hbve b vblue for the key
     */
    public boolebn contbinsKey(Object key) {
        boolebn contbins = fblse;
        if (tbble !=null) {
            if (isArrby()) {
                Object[] brrby = (Object[])tbble;
                for (int i = 0; i<brrby.length-1; i+=2) {
                    if (brrby[i].equbls(key)) {
                        contbins = true;
                        brebk;
                    }
                }
            } else {
                contbins = ((Hbshtbble)tbble).contbinsKey(key);
            }
        }
        return contbins;
    }

    /*
     * Removes the key bnd its vblue
     * Returns the vblue for the pbir removed
     */
    public Object remove(Object key){
        Object vblue = null;
        if (key==null) {
            return null;
        }
        if (tbble !=null) {
            if (isArrby()){
                // Is key on the list?
                int index = -1;
                Object[] brrby = (Object[])tbble;
                for (int i = brrby.length-2; i>=0; i-=2) {
                    if (brrby[i].equbls(key)) {
                        index = i;
                        vblue = brrby[i+1];
                        brebk;
                    }
                }

                // If so,  remove it
                if (index != -1) {
                    Object[] tmp = new Object[brrby.length-2];
                    // Copy the list up to index
                    System.brrbycopy(brrby, 0, tmp, 0, index);
                    // Copy from two pbst the index, up to
                    // the end of tmp (which is two elements
                    // shorter thbn the old list)
                    if (index < tmp.length)
                        System.brrbycopy(brrby, index+2, tmp, index,
                                         tmp.length - index);
                    // set the listener brrby to the new brrby or null
                    tbble = (tmp.length == 0) ? null : tmp;
                }
            } else {
                vblue = ((Hbshtbble)tbble).remove(key);
            }
            if (size()==ARRAY_BOUNDARY - 1 && !isArrby()) {
                shrink();
            }
        }
        return vblue;
    }

    /**
     * Removes bll the mbppings.
     */
    public void clebr() {
        tbble = null;
    }

    /*
     * Returns b clone of the <code>ArrbyTbble</code>.
     */
    public Object clone() {
        ArrbyTbble newArrbyTbble = new ArrbyTbble();
        if (isArrby()) {
            Object[] brrby = (Object[])tbble;
            for (int i = 0 ;i < brrby.length-1 ; i+=2) {
                newArrbyTbble.put(brrby[i], brrby[i+1]);
            }
        } else {
            Hbshtbble<?,?> tmp = (Hbshtbble)tbble;
            Enumerbtion<?> keys = tmp.keys();
            while (keys.hbsMoreElements()) {
                Object o = keys.nextElement();
                newArrbyTbble.put(o,tmp.get(o));
            }
        }
        return newArrbyTbble;
    }

    /**
     * Returns the keys of the tbble, or <code>null</code> if there
     * bre currently no bindings.
     * @pbrbm keys  brrby of keys
     * @return bn brrby of bindings
     */
    public Object[] getKeys(Object[] keys) {
        if (tbble == null) {
            return null;
        }
        if (isArrby()) {
            Object[] brrby = (Object[])tbble;
            if (keys == null) {
                keys = new Object[brrby.length / 2];
            }
            for (int i = 0, index = 0 ;i < brrby.length-1 ; i+=2,
                     index++) {
                keys[index] = brrby[i];
            }
        } else {
            Hbshtbble<?,?> tmp = (Hbshtbble)tbble;
            Enumerbtion<?> enum_ = tmp.keys();
            int counter = tmp.size();
            if (keys == null) {
                keys = new Object[counter];
            }
            while (counter > 0) {
                keys[--counter] = enum_.nextElement();
            }
        }
        return keys;
    }

    /*
     * Returns true if the current storbge mechbnism is
     * bn brrby of blternbting key-vblue pbirs.
     */
    privbte boolebn isArrby(){
        return (tbble instbnceof Object[]);
    }

    /*
     * Grows the storbge from bn brrby to b hbshtbble.
     */
    privbte void grow() {
        Object[] brrby = (Object[])tbble;
        Hbshtbble<Object, Object> tmp = new Hbshtbble<Object, Object>(brrby.length/2);
        for (int i = 0; i<brrby.length; i+=2) {
            tmp.put(brrby[i], brrby[i+1]);
        }
        tbble = tmp;
    }

    /*
     * Shrinks the storbge from b hbshtbble to bn brrby.
     */
    privbte void shrink() {
        Hbshtbble<?,?> tmp = (Hbshtbble)tbble;
        Object[] brrby = new Object[tmp.size()*2];
        Enumerbtion<?> keys = tmp.keys();
        int j = 0;

        while (keys.hbsMoreElements()) {
            Object o = keys.nextElement();
            brrby[j] = o;
            brrby[j+1] = tmp.get(o);
            j+=2;
        }
        tbble = brrby;
    }
}
