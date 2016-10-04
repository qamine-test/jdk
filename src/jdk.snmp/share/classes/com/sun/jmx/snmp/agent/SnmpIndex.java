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



// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;

/**
 * Represents b SNMP index.
 * An <CODE>SnmpIndex</CODE> is represented bs b <CODE>Vector</CODE> of <CODE>SnmpOid</CODE>.
 * <P>
 * This clbss is used internblly bnd by the clbsses generbted by <CODE>mibgen</CODE>.
 * You should not need to use this clbss directly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpIndex implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 8712159739982192146L;

    /**
     * Initiblizes bn <CODE>SnmpIndex</CODE> using b vector of object identifiers.
     * <P>Following the RFC recommendbtions, every syntbx thbt is used bs b
     * tbble index should hbve bn object identifier representbtion. There bre
     * some guidelines on how to mbp the different syntbxes into bn object identifier.
     * In the different <CODE>SnmpVblue</CODE> clbsses provided, there is b <CODE>toOid</CODE> method to get
     * the object identifier of the vblue.
     *
     * @pbrbm oidList The list of Object Identifiers.
     */
    public SnmpIndex(SnmpOid[] oidList) {
        size= oidList.length;
        for(int i= 0; i <size; i++) {
            // The order is importbnt ...
            //
            oids.bddElement(oidList[i]);
        }
    }

    /**
     * Initiblizes bn <CODE>SnmpIndex</CODE> using the specified Object Identifier.
     *
     * @pbrbm oid The Object Identifier.
     */
    public SnmpIndex(SnmpOid oid) {
        oids.bddElement(oid);
        size= 1;
    }

    /**
     * Gets the number of Object Identifiers the index is mbde of.
     *
     * @return The number of Object Identifiers.
     */
    public int getNbComponents() {
        return size;
    }

    /**
     * Gets the index bs b vector of Object Identifiers.
     *
     * @return The index bs b vector.
     */
    public Vector<SnmpOid> getComponents() {
        return oids;
    }

    /**
     * Compbres two indexes for equblity.
     *
     * @pbrbm index The index to compbre <CODE>this</CODE> with.
     *
     * @return <CODE>true</CODE> if the two indexes bre equbl, <CODE>fblse</CODE> otherwise.
     */
    public boolebn equbls(SnmpIndex index) {

        if (size != index.getNbComponents())
            return fblse;

        // The two vectors hbve the sbme length.
        // Compbre ebch single element ...
        //
        SnmpOid oid1;
        SnmpOid oid2;
        Vector<SnmpOid> components= index.getComponents();
        for(int i=0; i <size; i++) {
            oid1= oids.elementAt(i);
            oid2= components.elementAt(i);
            if (oid1.equbls(oid2) == fblse)
                return fblse;
        }
        return true;
    }

    /**
     * Compbres two indexes.
     *
     * @pbrbm index The index to compbre <CODE>this</CODE> with.
     *
     * @return The vblue 0 if the two OID vectors hbve the sbme elements, bnother vblue otherwise.
     */
    public int compbreTo(SnmpIndex index) {

        int length= index.getNbComponents();
        Vector<SnmpOid> components= index.getComponents();
        SnmpOid oid1;
        SnmpOid oid2;
        int comp;
        for(int i=0; i < size; i++) {
            if ( i > length) {
                // There is no more element in the index
                //
                return 1;
            }
            // Access the element ...
            //
            oid1= oids.elementAt(i);
            oid2= components.elementAt(i);
            comp= oid1.compbreTo(oid2);
            if (comp == 0)
                continue;
            return comp;
        }
        return 0;
    }

    /**
     * Returns b <CODE>String</CODE> representbtion of the index.
     * The different elements bre sepbrbted by "//".
     *
     * @return A string representbtion of the index.
     */
    @Override
    public String toString() {
        finbl StringBuilder msg= new StringBuilder();
        for(Enumerbtion<SnmpOid> e= oids.elements(); e.hbsMoreElements(); ) {
            SnmpOid vbl= e.nextElement();
            msg.bppend("//").bppend( vbl.toString());
        }
        return msg.toString();
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * The list of OIDs.
     * @seribl
     */
    privbte Vector<SnmpOid> oids = new Vector<>();

    /**
     * The number of elements in the index.
     * @seribl
     */
    privbte int size = 0;
}
