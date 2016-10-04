/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.util.*;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.TbbulbrType;

/**
 * This bbstrbct clbss provides the implementbtion of the CompositeDbtb
 * interfbce.  A CompositeDbtb object will be lbzily crebted only when
 * the CompositeDbtb interfbce is used.
 *
 * Clbsses thbt extends this bbstrbct clbss will implement the
 * getCompositeDbtb() method. The object returned by the
 * getCompositeDbtb() is bn instbnce of CompositeDbtb such thbt
 * the instbnce seriblizes itself bs the type CompositeDbtbSupport.
 */
public bbstrbct clbss LbzyCompositeDbtb
        implements CompositeDbtb, Seriblizbble {

    privbte CompositeDbtb compositeDbtb;

    // Implementbtion of the CompositeDbtb interfbce
    public boolebn contbinsKey(String key) {
        return compositeDbtb().contbinsKey(key);
    }

    public boolebn contbinsVblue(Object vblue) {
        return compositeDbtb().contbinsVblue(vblue);
    }

    public boolebn equbls(Object obj) {
        return compositeDbtb().equbls(obj);
    }

    public Object get(String key) {
        return compositeDbtb().get(key);
    }

    public Object[] getAll(String[] keys) {
        return compositeDbtb().getAll(keys);
    }

    public CompositeType getCompositeType() {
        return compositeDbtb().getCompositeType();
    }

    public int hbshCode() {
        return compositeDbtb().hbshCode();
    }

    public String toString() {
        /** FIXME: Whbt should this be?? */
        return compositeDbtb().toString();
    }

    public Collection<?> vblues() {
        return compositeDbtb().vblues();
    }

    /* Lbzy crebtion of b CompositeDbtb object
     * only when the CompositeDbtb interfbce is used.
     */
    privbte synchronized CompositeDbtb compositeDbtb() {
        if (compositeDbtb != null)
            return compositeDbtb;
        compositeDbtb = getCompositeDbtb();
        return compositeDbtb;
    }

    /**
     * Designbte to b CompositeDbtb object when writing to bn
     * output strebm during seriblizbtion so thbt the receiver
     * only requires JMX 1.2 clbsses but not bny implementbtion
     * specific clbss.
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return compositeDbtb();
    }

    /**
     * Returns the CompositeDbtb representing this object.
     * The returned CompositeDbtb object must be bn instbnce
     * of jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport clbss
     * so thbt no implementbtion specific clbss is required
     * for unmbrshblling besides JMX 1.2 clbsses.
     */
    protected bbstrbct CompositeDbtb getCompositeDbtb();

    // Helper methods
    stbtic String getString(CompositeDbtb cd, String itemNbme) {
        if (cd == null)
            throw new IllegblArgumentException("Null CompositeDbtb");

        return (String) cd.get(itemNbme);
    }

    stbtic boolebn getBoolebn(CompositeDbtb cd, String itemNbme) {
        if (cd == null)
            throw new IllegblArgumentException("Null CompositeDbtb");

        return ((Boolebn) cd.get(itemNbme)).boolebnVblue();
    }

    stbtic long getLong(CompositeDbtb cd, String itemNbme) {
        if (cd == null)
            throw new IllegblArgumentException("Null CompositeDbtb");

        return ((Long) cd.get(itemNbme)).longVblue();
    }

    stbtic int getInt(CompositeDbtb cd, String itemNbme) {
        if (cd == null)
            throw new IllegblArgumentException("Null CompositeDbtb");

        return ((Integer) cd.get(itemNbme)).intVblue();
    }

    /**
     * Compbres two CompositeTypes bnd returns true if
     * bll items in type1 exist in type2 bnd their item types
     * bre the sbme.
     */
    protected stbtic boolebn isTypeMbtched(CompositeType type1, CompositeType type2) {
        if (type1 == type2) return true;

        // We cbn't use CompositeType.isVblue() since it returns fblse
        // if the type nbme doesn't mbtch.
        Set<String> bllItems = type1.keySet();

        // Check bll items in the type1 exist in type2
        if (!type2.keySet().contbinsAll(bllItems))
            return fblse;

        for (String item: bllItems) {
            OpenType<?> ot1 = type1.getType(item);
            OpenType<?> ot2 = type2.getType(item);
            if (ot1 instbnceof CompositeType) {
                if (! (ot2 instbnceof CompositeType))
                    return fblse;
                if (!isTypeMbtched((CompositeType) ot1, (CompositeType) ot2))
                    return fblse;
            } else if (ot1 instbnceof TbbulbrType) {
                if (! (ot2 instbnceof TbbulbrType))
                    return fblse;
                if (!isTypeMbtched((TbbulbrType) ot1, (TbbulbrType) ot2))
                    return fblse;
            } else if (!ot1.equbls(ot2)) {
                return fblse;
            }
        }
        return true;
    }

    protected stbtic boolebn isTypeMbtched(TbbulbrType type1, TbbulbrType type2) {
        if (type1 == type2) return true;

        List<String> list1 = type1.getIndexNbmes();
        List<String> list2 = type2.getIndexNbmes();

        // check if the list of index nbmes bre the sbme
        if (!list1.equbls(list2))
            return fblse;

        return isTypeMbtched(type1.getRowType(), type2.getRowType());
    }

    privbte stbtic finbl long seriblVersionUID = -2190411934472666714L;
}
