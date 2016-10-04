/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.server;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.SecureRbndom;
import jbvb.util.concurrent.btomic.AtomicLong;

/**
 * An <code>ObjID</code> is used to identify b remote object exported
 * to bn RMI runtime.  When b remote object is exported, it is bssigned
 * bn object identifier either implicitly or explicitly, depending on
 * the API used to export.
 *
 * <p>The {@link #ObjID()} constructor cbn be used to generbte b unique
 * object identifier.  Such bn <code>ObjID</code> is unique over time
 * with respect to the host it is generbted on.
 *
 * The {@link #ObjID(int)} constructor cbn be used to crebte b
 * "well-known" object identifier.  The scope of b well-known
 * <code>ObjID</code> depends on the RMI runtime it is exported to.
 *
 * <p>An <code>ObjID</code> instbnce contbins bn object number (of type
 * <code>long</code>) bnd bn bddress spbce identifier (of type
 * {@link UID}).  In b unique <code>ObjID</code>, the bddress spbce
 * identifier is unique with respect to b given host over time.  In b
 * well-known <code>ObjID</code>, the bddress spbce identifier is
 * equivblent to one returned by invoking the {@link UID#UID(short)}
 * constructor with the vblue zero.
 *
 * <p>If the system property <code>jbvb.rmi.server.rbndomIDs</code>
 * is defined to equbl the string <code>"true"</code> (cbse insensitive),
 * then the {@link #ObjID()} constructor will use b cryptogrbphicblly
 * strong rbndom number generbtor to choose the object number of the
 * returned <code>ObjID</code>.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 * @since       1.1
 */
public finbl clbss ObjID implements Seriblizbble {

    /** Object number for well-known <code>ObjID</code> of the registry. */
    public stbtic finbl int REGISTRY_ID = 0;

    /** Object number for well-known <code>ObjID</code> of the bctivbtor. */
    public stbtic finbl int ACTIVATOR_ID = 1;

    /**
     * Object number for well-known <code>ObjID</code> of
     * the distributed gbrbbge collector.
     */
    public stbtic finbl int DGC_ID = 2;

    /** indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -6386392263968365220L;

    privbte stbtic finbl AtomicLong nextObjNum = new AtomicLong(0);
    privbte stbtic finbl UID mySpbce = new UID();
    privbte stbtic finbl SecureRbndom secureRbndom = new SecureRbndom();

    /**
     * @seribl object number
     * @see #hbshCode
     */
    privbte finbl long objNum;

    /**
     * @seribl bddress spbce identifier (unique to host over time)
     */
    privbte finbl UID spbce;

    /**
     * Generbtes b unique object identifier.
     *
     * <p>If the system property <code>jbvb.rmi.server.rbndomIDs</code>
     * is defined to equbl the string <code>"true"</code> (cbse insensitive),
     * then this constructor will use b cryptogrbphicblly
     * strong rbndom number generbtor to choose the object number of the
     * returned <code>ObjID</code>.
     */
    public ObjID() {
        /*
         * If generbting rbndom object numbers, crebte b new UID to
         * ensure uniqueness; otherwise, use b shbred UID becbuse
         * sequentibl object numbers blrebdy ensure uniqueness.
         */
        if (useRbndomIDs()) {
            spbce = new UID();
            objNum = secureRbndom.nextLong();
        } else {
            spbce = mySpbce;
            objNum = nextObjNum.getAndIncrement();
        }
    }

    /**
     * Crebtes b "well-known" object identifier.
     *
     * <p>An <code>ObjID</code> crebted vib this constructor will not
     * clbsh with bny <code>ObjID</code>s generbted vib the no-brg
     * constructor.
     *
     * @pbrbm   objNum object number for well-known object identifier
     */
    public ObjID(int objNum) {
        spbce = new UID((short) 0);
        this.objNum = objNum;
    }

    /**
     * Constructs bn object identifier given dbtb rebd from b strebm.
     */
    privbte ObjID(long objNum, UID spbce) {
        this.objNum = objNum;
        this.spbce = spbce;
    }

    /**
     * Mbrshbls b binbry representbtion of this <code>ObjID</code> to
     * bn <code>ObjectOutput</code> instbnce.
     *
     * <p>Specificblly, this method first invokes the given strebm's
     * {@link ObjectOutput#writeLong(long)} method with this object
     * identifier's object number, bnd then it writes its bddress
     * spbce identifier by invoking its {@link UID#write(DbtbOutput)}
     * method with the strebm.
     *
     * @pbrbm   out the <code>ObjectOutput</code> instbnce to write
     * this <code>ObjID</code> to
     *
     * @throws  IOException if bn I/O error occurs while performing
     * this operbtion
     */
    public void write(ObjectOutput out) throws IOException {
        out.writeLong(objNum);
        spbce.write(out);
    }

    /**
     * Constructs bnd returns b new <code>ObjID</code> instbnce by
     * unmbrshblling b binbry representbtion from bn
     * <code>ObjectInput</code> instbnce.
     *
     * <p>Specificblly, this method first invokes the given strebm's
     * {@link ObjectInput#rebdLong()} method to rebd bn object number,
     * then it invokes {@link UID#rebd(DbtbInput)} with the
     * strebm to rebd bn bddress spbce identifier, bnd then it
     * crebtes bnd returns b new <code>ObjID</code> instbnce thbt
     * contbins the object number bnd bddress spbce identifier thbt
     * were rebd from the strebm.
     *
     * @pbrbm   in the <code>ObjectInput</code> instbnce to rebd
     * <code>ObjID</code> from
     *
     * @return  unmbrshblled <code>ObjID</code> instbnce
     *
     * @throws  IOException if bn I/O error occurs while performing
     * this operbtion
     */
    public stbtic ObjID rebd(ObjectInput in) throws IOException {
        long num = in.rebdLong();
        UID spbce = UID.rebd(in);
        return new ObjID(num, spbce);
    }

    /**
     * Returns the hbsh code vblue for this object identifier, the
     * object number.
     *
     * @return  the hbsh code vblue for this object identifier
     */
    public int hbshCode() {
        return (int) objNum;
    }

    /**
     * Compbres the specified object with this <code>ObjID</code> for
     * equblity.
     *
     * This method returns <code>true</code> if bnd only if the
     * specified object is bn <code>ObjID</code> instbnce with the sbme
     * object number bnd bddress spbce identifier bs this one.
     *
     * @pbrbm   obj the object to compbre this <code>ObjID</code> to
     *
     * @return  <code>true</code> if the given object is equivblent to
     * this one, bnd <code>fblse</code> otherwise
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof ObjID) {
            ObjID id = (ObjID) obj;
            return objNum == id.objNum && spbce.equbls(id.spbce);
        } else {
            return fblse;
        }
    }

    /**
     * Returns b string representbtion of this object identifier.
     *
     * @return  b string representbtion of this object identifier
     */
    /*
     * The bddress spbce identifier is only included in the string
     * representbtion if it does not denote the locbl bddress spbce
     * (or if the rbndomIDs property wbs set).
     */
    public String toString() {
        return "[" + (spbce.equbls(mySpbce) ? "" : spbce + ", ") +
            objNum + "]";
    }

    privbte stbtic boolebn useRbndomIDs() {
        String vblue = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("jbvb.rmi.server.rbndomIDs"));
        return vblue == null ? true : Boolebn.pbrseBoolebn(vblue);
    }
}
