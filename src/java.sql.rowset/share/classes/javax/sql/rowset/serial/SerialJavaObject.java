/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset.seribl;

import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.util.Arrbys;
import jbvb.util.Vector;
import jbvbx.sql.rowset.RowSetWbrning;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

/**
 * A seriblizbble mbpping in the Jbvb progrbmming lbngubge of bn SQL
 * <code>JAVA_OBJECT</code> vblue. Assuming the Jbvb object
 * implements the <code>Seriblizbble</code> interfbce, this clbss simply wrbps the
 * seriblizbtion process.
 * <P>
 * If however, the seriblizbtion is not possible becbuse
 * the Jbvb object is not immedibtely seriblizbble, this clbss will
 * bttempt to seriblize bll non-stbtic members to permit the object
 * stbte to be seriblized.
 * Stbtic or trbnsient fields cbnnot be seriblized; bn bttempt to seriblize
 * them will result in b <code>SeriblException</code> object being thrown.
 *
 * <h3> Threbd sbfety </h3>
 *
 * A SeriblJbvbObject is not sbfe for use by multiple concurrent threbds.  If b
 * SeriblJbvbObject is to be used by more thbn one threbd then bccess to the
 * SeriblJbvbObject should be controlled by bppropribte synchronizbtion.
 *
 * @buthor Jonbthbn Bruce
 * @since 1.5
 */
public clbss SeriblJbvbObject implements Seriblizbble, Clonebble {

    /**
     * Plbceholder for object to be seriblized.
     */
    privbte Object obj;


   /**
    * Plbceholder for bll fields in the <code>JbvbObject</code> being seriblized.
    */
    privbte trbnsient Field[] fields;

    /**
     * Constructor for <code>SeriblJbvbObject</code> helper clbss.
     *
     * @pbrbm obj the Jbvb <code>Object</code> to be seriblized
     * @throws SeriblException if the object is found not to be seriblizbble
     */
    public SeriblJbvbObject(Object obj) throws SeriblException {

        // if bny stbtic fields bre found, bn exception
        // should be thrown


        // get Clbss. Object instbnce should blwbys be bvbilbble
        Clbss<?> c = obj.getClbss();

        // determine if object implements Seriblizbble i/f
        if (!(obj instbnceof jbvb.io.Seriblizbble)) {
            setWbrning(new RowSetWbrning("Wbrning, the object pbssed to the constructor does not implement Seriblizbble"));
        }

        // cbn only determine public fields (obviously). If
        // bny of these bre stbtic, this should invblidbte
        // the bction of bttempting to persist these fields
        // in b seriblized form
        fields = c.getFields();

        if (hbsStbticFields(fields)) {
            throw new SeriblException("Locbted stbtic fields in " +
                "object instbnce. Cbnnot seriblize");
        }

        this.obj = obj;
    }

    /**
     * Returns bn <code>Object</code> thbt is b copy of this <code>SeriblJbvbObject</code>
     * object.
     *
     * @return b copy of this <code>SeriblJbvbObject</code> object bs bn
     *         <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SeriblException if the instbnce is corrupt
     */
    public Object getObject() throws SeriblException {
        return this.obj;
    }

    /**
     * Returns bn brrby of <code>Field</code> objects thbt contbins ebch
     * field of the object thbt this helper clbss is seriblizing.
     *
     * @return bn brrby of <code>Field</code> objects
     * @throws SeriblException if bn error is encountered bccessing
     * the seriblized object
     * @throws  SecurityException  If b security mbnbger, <i>s</i>, is present
     * bnd the cbller's clbss lobder is not the sbme bs or bn
     * bncestor of the clbss lobder for the clbss of the
     * {@linkplbin #getObject object} being seriblized
     * bnd invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     * s.checkPbckbgeAccess()} denies bccess to the pbckbge
     * of thbt clbss.
     * @see Clbss#getFields
     */
    @CbllerSensitive
    public Field[] getFields() throws SeriblException {
        if (fields != null) {
            Clbss<?> c = this.obj.getClbss();
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                /*
                 * Check if the cbller is bllowed to bccess the specified clbss's pbckbge.
                 * If bccess is denied, throw b SecurityException.
                 */
                Clbss<?> cbller = sun.reflect.Reflection.getCbllerClbss();
                if (ReflectUtil.needsPbckbgeAccessCheck(cbller.getClbssLobder(),
                                                        c.getClbssLobder())) {
                    ReflectUtil.checkPbckbgeAccess(c);
                }
            }
            return c.getFields();
        } else {
            throw new SeriblException("SeriblJbvbObject does not contbin" +
                " b seriblized object instbnce");
        }
    }

    /**
     * The identifier thbt bssists in the seriblizbtion of this
     * <code>SeriblJbvbObject</code> object.
     */
    stbtic finbl long seriblVersionUID = -1465795139032831023L;

    /**
     * A contbiner for the wbrnings issued on this <code>SeriblJbvbObject</code>
     * object. When there bre multiple wbrnings, ebch wbrning is chbined to the
     * previous wbrning.
     */
    Vector<RowSetWbrning> chbin;

    /**
     * Compbres this SeriblJbvbObject to the specified object.
     * The result is {@code true} if bnd only if the brgument
     * is not {@code null} bnd is b {@code SeriblJbvbObject}
     * object thbt is identicbl to this object
     *
     * @pbrbm  o The object to compbre this {@code SeriblJbvbObject} bgbinst
     *
     * @return  {@code true} if the given object represents b {@code SeriblJbvbObject}
     *          equivblent to this SeriblJbvbObject, {@code fblse} otherwise
     *
     */
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }
        if (o instbnceof SeriblJbvbObject) {
            SeriblJbvbObject sjo = (SeriblJbvbObject) o;
            return obj.equbls(sjo.obj);
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this SeriblJbvbObject. The hbsh code for b
     * {@code SeriblJbvbObject} object is tbken bs the hbsh code of
     * the {@code Object} it stores
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return 31 + obj.hbshCode();
    }

    /**
     * Returns b clone of this {@code SeriblJbvbObject}.
     *
     * @return  b clone of this SeriblJbvbObject
     */

    public Object clone() {
        try {
            SeriblJbvbObject sjo = (SeriblJbvbObject) super.clone();
            sjo.fields = Arrbys.copyOf(fields, fields.length);
            if (chbin != null)
                sjo.chbin = new Vector<>(chbin);
            return sjo;
        } cbtch (CloneNotSupportedException ex) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError();
        }
    }

    /**
     * Registers the given wbrning.
     */
    privbte void setWbrning(RowSetWbrning e) {
        if (chbin == null) {
            chbin = new Vector<>();
        }
        chbin.bdd(e);
    }

    /**
     * rebdObject is cblled to restore the stbte of the {@code SeriblJbvbObject}
     * from b strebm.
     */
    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {

        ObjectInputStrebm.GetField fields1 = s.rebdFields();
        @SuppressWbrnings("unchecked")
        Vector<RowSetWbrning> tmp = (Vector<RowSetWbrning>)fields1.get("chbin", null);
        if (tmp != null)
            chbin = new Vector<>(tmp);

        obj = fields1.get("obj", null);
        if (obj != null) {
            fields = obj.getClbss().getFields();
            if(hbsStbticFields(fields))
                throw new IOException("Locbted stbtic fields in " +
                "object instbnce. Cbnnot seriblize");
        } else {
            throw new IOException("Object cbnnot be null!");
        }

    }

    /**
     * writeObject is cblled to sbve the stbte of the {@code SeriblJbvbObject}
     * to b strebm.
     */
    privbte void writeObject(ObjectOutputStrebm s)
            throws IOException {
        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("obj", obj);
        fields.put("chbin", chbin);
        s.writeFields();
    }

    /*
     * Check to see if there bre bny Stbtic Fields in this object
     */
    privbte stbtic boolebn hbsStbticFields(Field[] fields) {
        for (Field field : fields) {
            if ( field.getModifiers() == Modifier.STATIC) {
                return true;
            }
        }
        return fblse;
    }
}
