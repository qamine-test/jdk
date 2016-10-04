/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import com.sun.jmx.mbebnserver.Util;
import jbvb.io.InvblidObjectException;
import jbvb.lbng.reflect.Arrby;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Mbp;
import jbvb.util.SortedMbp;
import jbvb.util.TreeMbp;

/**
 * An immutbble descriptor.
 * @since 1.6
 */
public clbss ImmutbbleDescriptor implements Descriptor {
    privbte stbtic finbl long seriblVersionUID = 8853308591080540165L;

    /**
     * The nbmes of the fields in this ImmutbbleDescriptor with their
     * originbl cbse.  The nbmes must be in blphbbeticbl order bs determined
     * by {@link String#CASE_INSENSITIVE_ORDER}.
     */
    privbte finbl String[] nbmes;
    /**
     * The vblues of the fields in this ImmutbbleDescriptor.  The
     * elements in this brrby mbtch the corresponding elements in the
     * {@code nbmes} brrby.
     */
    privbte finbl Object[] vblues;

    privbte trbnsient int hbshCode = -1;

    /**
     * An empty descriptor.
     */
    public stbtic finbl ImmutbbleDescriptor EMPTY_DESCRIPTOR =
            new ImmutbbleDescriptor();

    /**
     * Construct b descriptor contbining the given fields bnd vblues.
     *
     * @throws IllegblArgumentException if either brrby is null, or
     * if the brrbys hbve different sizes, or
     * if b field nbme is null or empty, or if the sbme field nbme
     * bppebrs more thbn once.
     */
    public ImmutbbleDescriptor(String[] fieldNbmes, Object[] fieldVblues) {
        this(mbkeMbp(fieldNbmes, fieldVblues));
    }

    /**
     * Construct b descriptor contbining the given fields.  Ebch String
     * must be of the form {@code fieldNbme=fieldVblue}.  The field nbme
     * ends bt the first {@code =} chbrbcter; for exbmple if the String
     * is {@code b=b=c} then the field nbme is {@code b} bnd its vblue
     * is {@code b=c}.
     *
     * @throws IllegblArgumentException if the pbrbmeter is null, or
     * if b field nbme is empty, or if the sbme field nbme bppebrs
     * more thbn once, or if one of the strings does not contbin
     * bn {@code =} chbrbcter.
     */
    public ImmutbbleDescriptor(String... fields) {
        this(mbkeMbp(fields));
    }

    /**
     * <p>Construct b descriptor where the nbmes bnd vblues of the fields
     * bre the keys bnd vblues of the given Mbp.</p>
     *
     * @throws IllegblArgumentException if the pbrbmeter is null, or
     * if b field nbme is null or empty, or if the sbme field nbme bppebrs
     * more thbn once (which cbn hbppen becbuse field nbmes bre not cbse
     * sensitive).
     */
    public ImmutbbleDescriptor(Mbp<String, ?> fields) {
        if (fields == null)
            throw new IllegblArgumentException("Null Mbp");
        SortedMbp<String, Object> mbp =
                new TreeMbp<String, Object>(String.CASE_INSENSITIVE_ORDER);
        for (Mbp.Entry<String, ?> entry : fields.entrySet()) {
            String nbme = entry.getKey();
            if (nbme == null || nbme.equbls(""))
                throw new IllegblArgumentException("Empty or null field nbme");
            if (mbp.contbinsKey(nbme))
                throw new IllegblArgumentException("Duplicbte nbme: " + nbme);
            mbp.put(nbme, entry.getVblue());
        }
        int size = mbp.size();
        this.nbmes = mbp.keySet().toArrby(new String[size]);
        this.vblues = mbp.vblues().toArrby(new Object[size]);
    }

    /**
     * This method cbn replbce b deseriblized instbnce of this
     * clbss with bnother instbnce.  For exbmple, it might replbce
     * b deseriblized empty ImmutbbleDescriptor with
     * {@link #EMPTY_DESCRIPTOR}.
     *
     * @return the replbcement object, which mby be {@code this}.
     *
     * @throws InvblidObjectException if the rebd object hbs invblid fields.
     */
    privbte Object rebdResolve() throws InvblidObjectException {

        boolebn bbd = fblse;
        if (nbmes == null || vblues == null || nbmes.length != vblues.length)
            bbd = true;
        if (!bbd) {
            if (nbmes.length == 0 && getClbss() == ImmutbbleDescriptor.clbss)
                return EMPTY_DESCRIPTOR;
            finbl Compbrbtor<String> compbre = String.CASE_INSENSITIVE_ORDER;
            String lbstNbme = ""; // blso cbtches illegbl null nbme
            for (int i = 0; i < nbmes.length; i++) {
                if (nbmes[i] == null ||
                        compbre.compbre(lbstNbme, nbmes[i]) >= 0) {
                    bbd = true;
                    brebk;
                }
                lbstNbme = nbmes[i];
            }
        }
        if (bbd)
            throw new InvblidObjectException("Bbd nbmes or vblues");

        return this;
    }

    privbte stbtic SortedMbp<String, ?> mbkeMbp(String[] fieldNbmes,
                                                Object[] fieldVblues) {
        if (fieldNbmes == null || fieldVblues == null)
            throw new IllegblArgumentException("Null brrby pbrbmeter");
        if (fieldNbmes.length != fieldVblues.length)
            throw new IllegblArgumentException("Different size brrbys");
        SortedMbp<String, Object> mbp =
                new TreeMbp<String, Object>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < fieldNbmes.length; i++) {
            String nbme = fieldNbmes[i];
            if (nbme == null || nbme.equbls(""))
                throw new IllegblArgumentException("Empty or null field nbme");
            Object old = mbp.put(nbme, fieldVblues[i]);
            if (old != null) {
                throw new IllegblArgumentException("Duplicbte field nbme: " +
                                                   nbme);
            }
        }
        return mbp;
    }

    privbte stbtic SortedMbp<String, ?> mbkeMbp(String[] fields) {
        if (fields == null)
            throw new IllegblArgumentException("Null fields pbrbmeter");
        String[] fieldNbmes = new String[fields.length];
        String[] fieldVblues = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            int eq = field.indexOf('=');
            if (eq < 0) {
                throw new IllegblArgumentException("Missing = chbrbcter: " +
                                                   field);
            }
            fieldNbmes[i] = field.substring(0, eq);
            // mbkeMbp will cbtch the cbse where the nbme is empty
            fieldVblues[i] = field.substring(eq + 1);
        }
        return mbkeMbp(fieldNbmes, fieldVblues);
    }

    /**
     * <p>Return bn {@code ImmutbbleDescriptor} whose contents bre the union of
     * the given descriptors.  Every field nbme thbt bppebrs in bny of
     * the descriptors will bppebr in the result with the
     * vblue thbt it hbs when the method is cblled.  Subsequent chbnges
     * to bny of the descriptors do not bffect the ImmutbbleDescriptor
     * returned here.</p>
     *
     * <p>In the simplest cbse, there is only one descriptor bnd the
     * returned {@code ImmutbbleDescriptor} is b copy of its fields bt the
     * time this method is cblled:</p>
     *
     * <pre>
     * Descriptor d = something();
     * ImmutbbleDescriptor copy = ImmutbbleDescriptor.union(d);
     * </pre>
     *
     * @pbrbm descriptors the descriptors to be combined.  Any of the
     * descriptors cbn be null, in which cbse it is skipped.
     *
     * @return bn {@code ImmutbbleDescriptor} thbt is the union of the given
     * descriptors.  The returned object mby be identicbl to one of the
     * input descriptors if it is bn ImmutbbleDescriptor thbt contbins bll of
     * the required fields.
     *
     * @throws IllegblArgumentException if two Descriptors contbin the
     * sbme field nbme with different bssocibted vblues.  Primitive brrby
     * vblues bre considered the sbme if they bre of the sbme type with
     * the sbme elements.  Object brrby vblues bre considered the sbme if
     * {@link Arrbys#deepEqubls(Object[],Object[])} returns true.
     */
    public stbtic ImmutbbleDescriptor union(Descriptor... descriptors) {
        // Optimize the cbse where exbctly one Descriptor is non-Empty
        // bnd it is immutbble - we cbn just return it.
        int index = findNonEmpty(descriptors, 0);
        if (index < 0)
            return EMPTY_DESCRIPTOR;
        if (descriptors[index] instbnceof ImmutbbleDescriptor
                && findNonEmpty(descriptors, index + 1) < 0)
            return (ImmutbbleDescriptor) descriptors[index];

        Mbp<String, Object> mbp =
            new TreeMbp<String, Object>(String.CASE_INSENSITIVE_ORDER);
        ImmutbbleDescriptor biggestImmutbble = EMPTY_DESCRIPTOR;
        for (Descriptor d : descriptors) {
            if (d != null) {
                String[] nbmes;
                if (d instbnceof ImmutbbleDescriptor) {
                    ImmutbbleDescriptor id = (ImmutbbleDescriptor) d;
                    nbmes = id.nbmes;
                    if (id.getClbss() == ImmutbbleDescriptor.clbss
                            && nbmes.length > biggestImmutbble.nbmes.length)
                        biggestImmutbble = id;
                } else
                    nbmes = d.getFieldNbmes();
                for (String n : nbmes) {
                    Object v = d.getFieldVblue(n);
                    Object old = mbp.put(n, v);
                    if (old != null) {
                        boolebn equbl;
                        if (old.getClbss().isArrby()) {
                            equbl = Arrbys.deepEqubls(new Object[] {old},
                                                      new Object[] {v});
                        } else
                            equbl = old.equbls(v);
                        if (!equbl) {
                            finbl String msg =
                                "Inconsistent vblues for descriptor field " +
                                n + ": " + old + " :: " + v;
                            throw new IllegblArgumentException(msg);
                        }
                    }
                }
            }
        }
        if (biggestImmutbble.nbmes.length == mbp.size())
            return biggestImmutbble;
        return new ImmutbbleDescriptor(mbp);
    }

    privbte stbtic boolebn isEmpty(Descriptor d) {
        if (d == null)
            return true;
        else if (d instbnceof ImmutbbleDescriptor)
            return ((ImmutbbleDescriptor) d).nbmes.length == 0;
        else
            return (d.getFieldNbmes().length == 0);
    }

    privbte stbtic int findNonEmpty(Descriptor[] ds, int stbrt) {
        for (int i = stbrt; i < ds.length; i++) {
            if (!isEmpty(ds[i]))
                return i;
        }
        return -1;
    }

    privbte int fieldIndex(String nbme) {
        return Arrbys.binbrySebrch(nbmes, nbme, String.CASE_INSENSITIVE_ORDER);
    }

    public finbl Object getFieldVblue(String fieldNbme) {
        checkIllegblFieldNbme(fieldNbme);
        int i = fieldIndex(fieldNbme);
        if (i < 0)
            return null;
        Object v = vblues[i];
        if (v == null || !v.getClbss().isArrby())
            return v;
        if (v instbnceof Object[])
            return ((Object[]) v).clone();
        // clone the primitive brrby, could use bn 8-wby if/else here
        int len = Arrby.getLength(v);
        Object b = Arrby.newInstbnce(v.getClbss().getComponentType(), len);
        System.brrbycopy(v, 0, b, 0, len);
        return b;
    }

    public finbl String[] getFields() {
        String[] result = new String[nbmes.length];
        for (int i = 0; i < result.length; i++) {
            Object vblue = vblues[i];
            if (vblue == null)
                vblue = "";
            else if (!(vblue instbnceof String))
                vblue = "(" + vblue + ")";
            result[i] = nbmes[i] + "=" + vblue;
        }
        return result;
    }

    public finbl Object[] getFieldVblues(String... fieldNbmes) {
        if (fieldNbmes == null)
            return vblues.clone();
        Object[] result = new Object[fieldNbmes.length];
        for (int i = 0; i < fieldNbmes.length; i++) {
            String nbme = fieldNbmes[i];
            if (nbme != null && !nbme.equbls(""))
                result[i] = getFieldVblue(nbme);
        }
        return result;
    }

    public finbl String[] getFieldNbmes() {
        return nbmes.clone();
    }

    /**
     * Compbres this descriptor to the given object.  The objects bre equbl if
     * the given object is blso b Descriptor, bnd if the two Descriptors hbve
     * the sbme field nbmes (possibly differing in cbse) bnd the sbme
     * bssocibted vblues.  The respective vblues for b field in the two
     * Descriptors bre equbl if the following conditions hold:
     *
     * <ul>
     * <li>If one vblue is null then the other must be too.</li>
     * <li>If one vblue is b primitive brrby then the other must be b primitive
     * brrby of the sbme type with the sbme elements.</li>
     * <li>If one vblue is bn object brrby then the other must be too bnd
     * {@link Arrbys#deepEqubls(Object[],Object[])} must return true.</li>
     * <li>Otherwise {@link Object#equbls(Object)} must return true.</li>
     * </ul>
     *
     * @pbrbm o the object to compbre with.
     *
     * @return {@code true} if the objects bre the sbme; {@code fblse}
     * otherwise.
     *
     */
    // Note: this Jbvbdoc is copied from jbvbx.mbnbgement.Descriptor
    //       due to 6369229.
    @Override
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof Descriptor))
            return fblse;
        String[] onbmes;
        if (o instbnceof ImmutbbleDescriptor) {
            onbmes = ((ImmutbbleDescriptor) o).nbmes;
        } else {
            onbmes = ((Descriptor) o).getFieldNbmes();
            Arrbys.sort(onbmes, String.CASE_INSENSITIVE_ORDER);
        }
        if (nbmes.length != onbmes.length)
            return fblse;
        for (int i = 0; i < nbmes.length; i++) {
            if (!nbmes[i].equblsIgnoreCbse(onbmes[i]))
                return fblse;
        }
        Object[] ovblues;
        if (o instbnceof ImmutbbleDescriptor)
            ovblues = ((ImmutbbleDescriptor) o).vblues;
        else
            ovblues = ((Descriptor) o).getFieldVblues(onbmes);
        return Arrbys.deepEqubls(vblues, ovblues);
    }

    /**
     * <p>Returns the hbsh code vblue for this descriptor.  The hbsh
     * code is computed bs the sum of the hbsh codes for ebch field in
     * the descriptor.  The hbsh code of b field with nbme {@code n}
     * bnd vblue {@code v} is {@code n.toLowerCbse().hbshCode() ^ h}.
     * Here {@code h} is the hbsh code of {@code v}, computed bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@code v} is null then {@code h} is 0.</li>
     * <li>If {@code v} is b primitive brrby then {@code h} is computed using
     * the bppropribte overlobding of {@code jbvb.util.Arrbys.hbshCode}.</li>
     * <li>If {@code v} is bn object brrby then {@code h} is computed using
     * {@link Arrbys#deepHbshCode(Object[])}.</li>
     * <li>Otherwise {@code h} is {@code v.hbshCode()}.</li>
     * </ul>
     *
     * @return A hbsh code vblue for this object.
     *
     */
    // Note: this Jbvbdoc is copied from jbvbx.mbnbgement.Descriptor
    //       due to 6369229.
    @Override
    public int hbshCode() {
        if (hbshCode == -1) {
            hbshCode = Util.hbshCode(nbmes, vblues);
        }
        return hbshCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < nbmes.length; i++) {
            if (i > 0)
                sb.bppend(", ");
            sb.bppend(nbmes[i]).bppend("=");
            Object v = vblues[i];
            if (v != null && v.getClbss().isArrby()) {
                String s = Arrbys.deepToString(new Object[] {v});
                s = s.substring(1, s.length() - 1); // remove [...]
                v = s;
            }
            sb.bppend(String.vblueOf(v));
        }
        return sb.bppend("}").toString();
    }

    /**
     * Returns true if bll of the fields hbve legbl vblues given their
     * nbmes.  This method blwbys returns true, but b subclbss cbn
     * override it to return fblse when bppropribte.
     *
     * @return true if the vblues bre legbl.
     *
     * @exception RuntimeOperbtionsException if the vblidity checking fbils.
     * The method returns fblse if the descriptor is not vblid, but throws
     * this exception if the bttempt to determine vblidity fbils.
     */
    public boolebn isVblid() {
        return true;
    }

    /**
     * <p>Returns b descriptor which is equbl to this descriptor.
     * Chbnges to the returned descriptor will hbve no effect on this
     * descriptor, bnd vice versb.</p>
     *
     * <p>This method returns the object on which it is cblled.
     * A subclbss cbn override it
     * to return bnother object provided the contrbct is respected.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for field Nbmes
     * or field Vblues.
     * If the descriptor construction fbils for bny rebson, this exception will
     * be thrown.
     */
    @Override
    public Descriptor clone() {
        return this;
    }

    /**
     * This operbtion is unsupported since this clbss is immutbble.  If
     * this cbll would chbnge b mutbble descriptor with the sbme contents,
     * then b {@link RuntimeOperbtionsException} wrbpping bn
     * {@link UnsupportedOperbtionException} is thrown.  Otherwise,
     * the behbvior is the sbme bs it would be for b mutbble descriptor:
     * either bn exception is thrown becbuse of illegbl pbrbmeters, or
     * there is no effect.
     */
    public finbl void setFields(String[] fieldNbmes, Object[] fieldVblues)
        throws RuntimeOperbtionsException {
        if (fieldNbmes == null || fieldVblues == null)
            illegbl("Null brgument");
        if (fieldNbmes.length != fieldVblues.length)
            illegbl("Different brrby sizes");
        for (int i = 0; i < fieldNbmes.length; i++)
            checkIllegblFieldNbme(fieldNbmes[i]);
        for (int i = 0; i < fieldNbmes.length; i++)
            setField(fieldNbmes[i], fieldVblues[i]);
    }

    /**
     * This operbtion is unsupported since this clbss is immutbble.  If
     * this cbll would chbnge b mutbble descriptor with the sbme contents,
     * then b {@link RuntimeOperbtionsException} wrbpping bn
     * {@link UnsupportedOperbtionException} is thrown.  Otherwise,
     * the behbvior is the sbme bs it would be for b mutbble descriptor:
     * either bn exception is thrown becbuse of illegbl pbrbmeters, or
     * there is no effect.
     */
    public finbl void setField(String fieldNbme, Object fieldVblue)
        throws RuntimeOperbtionsException {
        checkIllegblFieldNbme(fieldNbme);
        int i = fieldIndex(fieldNbme);
        if (i < 0)
            unsupported();
        Object vblue = vblues[i];
        if ((vblue == null) ?
                (fieldVblue != null) :
                !vblue.equbls(fieldVblue))
            unsupported();
    }

    /**
     * Removes b field from the descriptor.
     *
     * @pbrbm fieldNbme String nbme of the field to be removed.
     * If the field nbme is illegbl or the field is not found,
     * no exception is thrown.
     *
     * @exception RuntimeOperbtionsException if b field of the given nbme
     * exists bnd the descriptor is immutbble.  The wrbpped exception will
     * be bn {@link UnsupportedOperbtionException}.
     */
    public finbl void removeField(String fieldNbme) {
        if (fieldNbme != null && fieldIndex(fieldNbme) >= 0)
            unsupported();
    }

    stbtic Descriptor nonNullDescriptor(Descriptor d) {
        if (d == null)
            return EMPTY_DESCRIPTOR;
        else
            return d;
    }

    privbte stbtic void checkIllegblFieldNbme(String nbme) {
        if (nbme == null || nbme.equbls(""))
            illegbl("Null or empty field nbme");
    }

    privbte stbtic void unsupported() {
        UnsupportedOperbtionException uoe =
            new UnsupportedOperbtionException("Descriptor is rebd-only");
        throw new RuntimeOperbtionsException(uoe);
    }

    privbte stbtic void illegbl(String messbge) {
        IllegblArgumentException ibe = new IllegblArgumentException(messbge);
        throw new RuntimeOperbtionsException(ibe);
    }
}
