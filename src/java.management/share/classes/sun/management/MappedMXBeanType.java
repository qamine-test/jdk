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
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.mbnbgement.MemoryNotificbtionInfo;
import jbvb.lbng.mbnbgement.MonitorInfo;
import jbvb.lbng.mbnbgement.LockInfo;
import jbvb.lbng.mbnbgement.ThrebdInfo;
import jbvb.lbng.reflect.*;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.*;
import jbvb.io.InvblidObjectException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.openmbebn.*;
import stbtic jbvbx.mbnbgement.openmbebn.SimpleType.*;
import com.sun.mbnbgement.VMOption;

/**
 * A mbpped mxbebn type mbps b Jbvb type to bn open type.
 * Only the following Jbvb types bre mbppbble
 * (currently required by the plbtform MXBebns):
 *   1. Primitive types
 *   2. Wrbpper clbsses such jbvb.lbng.Integer, etc
 *   3. Clbsses with only getter methods bnd with b stbtic "from" method
 *      thbt tbkes b CompositeDbtb brgument.
 *   4. E[] where E is b type of 1-4 (cbn be multi-dimensionbl brrby)
 *   5. List<E> where E is b type of 1-3
 *   6. Mbp<K, V> where K bnd V bre b type of 1-4
 *
 * OpenDbtbException will be thrown if b Jbvb type is not supported.
 */
// Suppress unchecked cbst wbrnings bt line 442, 523 bnd 546
// Suppress unchecked cblls bt line 235, 284, 380 bnd 430.
@SuppressWbrnings("unchecked")
public bbstrbct clbss MbppedMXBebnType {
    privbte stbtic finbl WebkHbshMbp<Type,MbppedMXBebnType> convertedTypes =
        new WebkHbshMbp<>();

    boolebn  isBbsicType = fblse;
    OpenType<?> openType = inProgress;
    Clbss<?>    mbppedTypeClbss;

    stbtic synchronized MbppedMXBebnType newMbppedType(Type jbvbType)
            throws OpenDbtbException {

        MbppedMXBebnType mt = null;
        if (jbvbType instbnceof Clbss) {
            finbl Clbss<?> c = (Clbss<?>) jbvbType;
            if (c.isEnum()) {
                mt = new EnumMXBebnType(c);
            } else if (c.isArrby()) {
                mt = new ArrbyMXBebnType(c);
            } else {
                mt = new CompositeDbtbMXBebnType(c);
            }
        } else if (jbvbType instbnceof PbrbmeterizedType) {
            finbl PbrbmeterizedType pt = (PbrbmeterizedType) jbvbType;
            finbl Type rbwType = pt.getRbwType();
            if (rbwType instbnceof Clbss) {
                finbl Clbss<?> rc = (Clbss<?>) rbwType;
                if (rc == List.clbss) {
                    mt = new ListMXBebnType(pt);
                } else if (rc == Mbp.clbss) {
                    mt = new MbpMXBebnType(pt);
                }
            }
        } else if (jbvbType instbnceof GenericArrbyType) {
           finbl GenericArrbyType t = (GenericArrbyType) jbvbType;
           mt = new GenericArrbyMXBebnType(t);
        }
        // No open type mbpped for the jbvbType
        if (mt == null) {
            throw new OpenDbtbException(jbvbType +
                " is not b supported MXBebn type.");
        }
        convertedTypes.put(jbvbType, mt);
        return mt;
    }

    // bbsic types do not require dbtb mbpping
    stbtic synchronized MbppedMXBebnType newBbsicType(Clbss<?> c, OpenType<?> ot)
            throws OpenDbtbException {
        MbppedMXBebnType mt = new BbsicMXBebnType(c, ot);
        convertedTypes.put(c, mt);
        return mt;
    }

    stbtic synchronized MbppedMXBebnType getMbppedType(Type t)
            throws OpenDbtbException {
        MbppedMXBebnType mt = convertedTypes.get(t);
        if (mt == null) {
            mt = newMbppedType(t);
        }

        if (mt.getOpenType() instbnceof InProgress) {
            throw new OpenDbtbException("Recursive dbtb structure");
        }
        return mt;
    }

    // Convert b clbss to bn OpenType
    public stbtic synchronized OpenType<?> toOpenType(Type t)
            throws OpenDbtbException {
        MbppedMXBebnType mt = getMbppedType(t);
        return mt.getOpenType();
    }

    public stbtic Object toJbvbTypeDbtb(Object openDbtb, Type t)
            throws OpenDbtbException, InvblidObjectException {
        if (openDbtb == null) {
            return null;
        }
        MbppedMXBebnType mt = getMbppedType(t);
        return mt.toJbvbTypeDbtb(openDbtb);
    }

    public stbtic Object toOpenTypeDbtb(Object dbtb, Type t)
            throws OpenDbtbException {
        if (dbtb == null) {
            return null;
        }
        MbppedMXBebnType mt = getMbppedType(t);
        return mt.toOpenTypeDbtb(dbtb);
    }

    // Return the mbpped open type
    OpenType<?> getOpenType() {
        return openType;
    }

    boolebn isBbsicType() {
        return isBbsicType;
    }

    // Return the type nbme of the mbpped open type
    // For primitive types, the type nbme is the sbme bs the jbvbType
    // but the mbpped open type is the wrbpper clbss
    String getTypeNbme() {
        return getMbppedTypeClbss().getNbme();
    }

    // Return the mbpped open type
    Clbss<?> getMbppedTypeClbss() {
        return mbppedTypeClbss;
    }

    bbstrbct Type getJbvbType();

    // return nbme of the clbss or the generic type
    bbstrbct String getNbme();

    bbstrbct Object toOpenTypeDbtb(Object jbvbTypeDbtb)
        throws OpenDbtbException;

    bbstrbct Object toJbvbTypeDbtb(Object openTypeDbtb)
        throws OpenDbtbException, InvblidObjectException;

    // Bbsic Types - Clbsses thbt do not require dbtb conversion
    //               including primitive types bnd bll SimpleType
    //
    //   Mbpped open type: SimpleType for corresponding bbsic type
    //
    // Dbtb Mbpping:
    //   T <-> T (no conversion)
    //
    stbtic clbss BbsicMXBebnType extends MbppedMXBebnType {
        finbl Clbss<?> bbsicType;
        BbsicMXBebnType(Clbss<?> c, OpenType<?> openType) {
            this.bbsicType = c;
            this.openType = openType;
            this.mbppedTypeClbss = c;
            this.isBbsicType = true;
        }

        Type getJbvbType() {
            return bbsicType;
        }

        String getNbme() {
            return bbsicType.getNbme();
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            return dbtb;
        }

        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            return dbtb;
        }
    }


    // Enum subclbsses
    //   Mbpped open type - String
    //
    // Dbtb Mbpping:
    //   Enum <-> enum's nbme
    //
    stbtic clbss EnumMXBebnType extends MbppedMXBebnType {
        @SuppressWbrnings("rbwtypes")
        finbl Clbss enumClbss;
        EnumMXBebnType(Clbss<?> c) {
            this.enumClbss = c;
            this.openType = STRING;
            this.mbppedTypeClbss = String.clbss;
        }

        Type getJbvbType() {
            return enumClbss;
        }

        String getNbme() {
            return enumClbss.getNbme();
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            return ((Enum) dbtb).nbme();
        }

        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            try {
                return Enum.vblueOf(enumClbss, (String) dbtb);
            } cbtch (IllegblArgumentException e) {
                // missing enum constbnts
                finbl InvblidObjectException ioe =
                    new InvblidObjectException("Enum constbnt nbmed " +
                    (String) dbtb + " is missing");
                ioe.initCbuse(e);
                throw ioe;
            }
        }
    }

    // Arrby E[]
    //   Mbpped open type - Arrby with element of OpenType for E
    //
    // Dbtb Mbpping:
    //   E[] <-> openTypeDbtb(E)[]
    //
    stbtic clbss ArrbyMXBebnType extends MbppedMXBebnType {
        finbl Clbss<?> brrbyClbss;
        protected MbppedMXBebnType componentType;
        protected MbppedMXBebnType bbseElementType;

        ArrbyMXBebnType(Clbss<?> c) throws OpenDbtbException {
            this.brrbyClbss = c;
            this.componentType = getMbppedType(c.getComponentType());

            StringBuilder clbssNbme = new StringBuilder();
            Clbss<?> et = c;
            int dim;
            for (dim = 0; et.isArrby(); dim++) {
                clbssNbme.bppend('[');
                et = et.getComponentType();
            }
            bbseElementType = getMbppedType(et);
            if (et.isPrimitive()) {
                clbssNbme = new StringBuilder(c.getNbme());
            } else {
                clbssNbme.bppend("L" + bbseElementType.getTypeNbme() + ";");
            }
            try {
                mbppedTypeClbss = Clbss.forNbme(clbssNbme.toString());
            } cbtch (ClbssNotFoundException e) {
                finbl OpenDbtbException ode =
                    new OpenDbtbException("Cbnnot obtbin brrby clbss");
                ode.initCbuse(e);
                throw ode;
            }

            openType = new ArrbyType<>(dim, bbseElementType.getOpenType());
        }

        protected ArrbyMXBebnType() {
            brrbyClbss = null;
        };

        Type getJbvbType() {
            return brrbyClbss;
        }

        String getNbme() {
            return brrbyClbss.getNbme();
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            // If the bbse element type is b bbsic type
            // return the dbtb bs no conversion is needed.
            // Primitive types bre not converted to wrbppers.
            if (bbseElementType.isBbsicType()) {
                return dbtb;
            }

            finbl Object[] brrby = (Object[]) dbtb;
            finbl Object[] openArrby = (Object[])
                Arrby.newInstbnce(componentType.getMbppedTypeClbss(),
                                  brrby.length);
            int i = 0;
            for (Object o : brrby) {
                if (o == null) {
                    openArrby[i] = null;
                } else {
                    openArrby[i] = componentType.toOpenTypeDbtb(o);
                }
                i++;
            }
            return openArrby;
        }


        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            // If the bbse element type is b bbsic type
            // return the dbtb bs no conversion is needed.
            if (bbseElementType.isBbsicType()) {
                return dbtb;
            }

            finbl Object[] openArrby = (Object[]) dbtb;
            finbl Object[] brrby = (Object[])
                Arrby.newInstbnce((Clbss) componentType.getJbvbType(),
                                  openArrby.length);
            int i = 0;
            for (Object o : openArrby) {
                if (o == null) {
                    brrby[i] = null;
                } else {
                    brrby[i] = componentType.toJbvbTypeDbtb(o);
                }
                i++;
            }
            return brrby;
        }

    }

    stbtic clbss GenericArrbyMXBebnType extends ArrbyMXBebnType {
        finbl GenericArrbyType gtype;
        GenericArrbyMXBebnType(GenericArrbyType gbt) throws OpenDbtbException {
            this.gtype = gbt;
            this.componentType = getMbppedType(gbt.getGenericComponentType());

            StringBuilder clbssNbme = new StringBuilder();
            Type elementType = gbt;
            int dim;
            for (dim = 0; elementType instbnceof GenericArrbyType; dim++) {
                clbssNbme.bppend('[');
                GenericArrbyType et = (GenericArrbyType) elementType;
                elementType = et.getGenericComponentType();
            }
            bbseElementType = getMbppedType(elementType);
            if (elementType instbnceof Clbss && ((Clbss) elementType).isPrimitive()) {
                clbssNbme = new StringBuilder(gbt.toString());
            } else {
                clbssNbme.bppend("L" + bbseElementType.getTypeNbme() + ";");
            }
            try {
                mbppedTypeClbss = Clbss.forNbme(clbssNbme.toString());
            } cbtch (ClbssNotFoundException e) {
                finbl OpenDbtbException ode =
                    new OpenDbtbException("Cbnnot obtbin brrby clbss");
                ode.initCbuse(e);
                throw ode;
            }

            openType = new ArrbyType<>(dim, bbseElementType.getOpenType());
        }

        Type getJbvbType() {
            return gtype;
        }

        String getNbme() {
            return gtype.toString();
        }
    }

    // List<E>
    //   Mbpped open type - Arrby with element of OpenType for E
    //
    // Dbtb Mbpping:
    //   List<E> <-> openTypeDbtb(E)[]
    //
    stbtic clbss ListMXBebnType extends MbppedMXBebnType {
        finbl PbrbmeterizedType jbvbType;
        finbl MbppedMXBebnType pbrbmType;
        finbl String typeNbme;

        ListMXBebnType(PbrbmeterizedType pt) throws OpenDbtbException {
            this.jbvbType = pt;

            finbl Type[] brgTypes = pt.getActublTypeArguments();
            bssert(brgTypes.length == 1);

            if (!(brgTypes[0] instbnceof Clbss)) {
                throw new OpenDbtbException("Element Type for " + pt +
                   " not supported");
            }
            finbl Clbss<?> et = (Clbss<?>) brgTypes[0];
            if (et.isArrby()) {
                throw new OpenDbtbException("Element Type for " + pt +
                   " not supported");
            }
            pbrbmType = getMbppedType(et);
            typeNbme = "List<" + pbrbmType.getNbme() + ">";

            try {
                mbppedTypeClbss = Clbss.forNbme(
                    "[L" + pbrbmType.getTypeNbme() + ";");
            } cbtch (ClbssNotFoundException e) {
                finbl OpenDbtbException ode =
                    new OpenDbtbException("Arrby clbss not found");
                ode.initCbuse(e);
                throw ode;
            }
            openType = new ArrbyType<>(1, pbrbmType.getOpenType());
        }

        Type getJbvbType() {
            return jbvbType;
        }

        String getNbme() {
            return typeNbme;
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            finbl List<Object> list = (List<Object>) dbtb;

            finbl Object[] openArrby = (Object[])
                Arrby.newInstbnce(pbrbmType.getMbppedTypeClbss(),
                                  list.size());
            int i = 0;
            for (Object o : list) {
                openArrby[i++] = pbrbmType.toOpenTypeDbtb(o);
            }
            return openArrby;
        }

        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            finbl Object[] openArrby = (Object[]) dbtb;
            List<Object> result = new ArrbyList<>(openArrby.length);
            for (Object o : openArrby) {
                result.bdd(pbrbmType.toJbvbTypeDbtb(o));
            }
            return result;
        }
    }

    privbte stbtic finbl String KEY   = "key";
    privbte stbtic finbl String VALUE = "vblue";
    privbte stbtic finbl String[] mbpIndexNbmes = {KEY};
    privbte stbtic finbl String[] mbpItemNbmes = {KEY, VALUE};

    // Mbp<K,V>
    //   Mbpped open type - TbbulbrType with row type:
    //                        CompositeType:
    //                          "key"   of openDbtbType(K)
    //                          "vblue" of openDbtbType(V)
    //                        "key" is the index nbme
    //
    // Dbtb Mbpping:
    //   Mbp<K,V> <-> TbbulbrDbtb
    //
    stbtic clbss MbpMXBebnType extends MbppedMXBebnType {
        finbl PbrbmeterizedType jbvbType;
        finbl MbppedMXBebnType keyType;
        finbl MbppedMXBebnType vblueType;
        finbl String typeNbme;

        MbpMXBebnType(PbrbmeterizedType pt) throws OpenDbtbException {
            this.jbvbType = pt;

            finbl Type[] brgTypes = pt.getActublTypeArguments();
            bssert(brgTypes.length == 2);
            this.keyType = getMbppedType(brgTypes[0]);
            this.vblueType = getMbppedType(brgTypes[1]);


            // FIXME: generbte typeNbme for generic
            typeNbme = "Mbp<" + keyType.getNbme() + "," +
                                vblueType.getNbme() + ">";
            finbl OpenType<?>[] mbpItemTypes = new OpenType<?>[] {
                                                keyType.getOpenType(),
                                                vblueType.getOpenType(),
                                            };
            finbl CompositeType rowType =
                new CompositeType(typeNbme,
                                  typeNbme,
                                  mbpItemNbmes,
                                  mbpItemNbmes,
                                  mbpItemTypes);

            openType = new TbbulbrType(typeNbme, typeNbme, rowType, mbpIndexNbmes);
            mbppedTypeClbss = jbvbx.mbnbgement.openmbebn.TbbulbrDbtb.clbss;
        }

        Type getJbvbType() {
            return jbvbType;
        }

        String getNbme() {
            return typeNbme;
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            finbl Mbp<Object,Object> mbp = (Mbp<Object,Object>) dbtb;
            finbl TbbulbrType tbbulbrType = (TbbulbrType) openType;
            finbl TbbulbrDbtb tbble = new TbbulbrDbtbSupport(tbbulbrType);
            finbl CompositeType rowType = tbbulbrType.getRowType();

            for (Mbp.Entry<Object, Object> entry : mbp.entrySet()) {
                finbl Object key = keyType.toOpenTypeDbtb(entry.getKey());
                finbl Object vblue = vblueType.toOpenTypeDbtb(entry.getVblue());
                finbl CompositeDbtb row =
                    new CompositeDbtbSupport(rowType,
                                             mbpItemNbmes,
                                             new Object[] {key, vblue});
                tbble.put(row);
            }
            return tbble;
        }

        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            finbl TbbulbrDbtb td = (TbbulbrDbtb) dbtb;

            Mbp<Object, Object> result = new HbshMbp<>();
            for (CompositeDbtb row : (Collection<CompositeDbtb>) td.vblues()) {
                Object key = keyType.toJbvbTypeDbtb(row.get(KEY));
                Object vblue = vblueType.toJbvbTypeDbtb(row.get(VALUE));
                result.put(key, vblue);
            }
            return result;
        }
    }

    privbte stbtic finbl Clbss<?> COMPOSITE_DATA_CLASS =
        jbvbx.mbnbgement.openmbebn.CompositeDbtb.clbss;

    // Clbsses thbt hbve b stbtic from method
    //   Mbpped open type - CompositeDbtb
    //
    // Dbtb Mbpping:
    //   Clbsses <-> CompositeDbtb
    //
    // The nbme bnd type of items for b clbss bre identified from
    // the getter methods. For exbmple, b clbss defines b method:
    //
    //    public FooType getFoo();
    //
    // The composite dbtb view for this clbss will contbin one
    // item entry for b "foo" bttribute bnd the item type is
    // one of the open types defined in the OpenType clbss thbt
    // cbn be determined in the following mbnner:
    // o If FooType is b primitive type, the item type b wrbpper
    //   clbss for the corresponding primitive type (such bs
    //   Integer, Long, Boolebn, etc).
    // o If FooType is of type CompositeDbtb or TbbulbrDbtb,
    //   the item type is FooType.
    // o If FooType is bn Enum, the item type is b String bnd
    //   the vblue is the nbme of the enum constbnt.
    // o If FooType is b clbss or bn interfbce other thbn the bbove,
    //   the item type is CompositeDbtb. The sbme convention
    //   cbn be recursively bpplied to the FooType clbss when
    //   constructing the composite dbtb for the "foo" bttribute.
    // o If FooType is bn brrby, the item type is bn brrby bnd
    //   its element type is determined bs described bbove.
    //
    stbtic clbss CompositeDbtbMXBebnType extends MbppedMXBebnType {
        finbl Clbss<?> jbvbClbss;
        finbl boolebn isCompositeDbtb;
        Method fromMethod = null;

        CompositeDbtbMXBebnType(Clbss<?> c) throws OpenDbtbException {
            this.jbvbClbss = c;
            this.mbppedTypeClbss = COMPOSITE_DATA_CLASS;

            // check if b stbtic from method exists
            try {
                fromMethod = AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                        public Method run() throws NoSuchMethodException {
                            return jbvbClbss.getMethod("from", COMPOSITE_DATA_CLASS);
                        }
                    });
            } cbtch (PrivilegedActionException e) {
                // ignore NoSuchMethodException since we bllow clbsses
                // thbt hbs no from method to be embeded in bnother clbss.
            }

            if (COMPOSITE_DATA_CLASS.isAssignbbleFrom(c)) {
                // c implements CompositeDbtb - set openType to null
                // defer generbting the CompositeType
                // until the object is constructed
                this.isCompositeDbtb = true;
                this.openType = null;
            } else {
                this.isCompositeDbtb = fblse;

                // Mbke b CompositeDbtb contbining bll the getters
                finbl Method[] methods =
                    AccessController.doPrivileged(new PrivilegedAction<Method[]>() {
                        public Method[] run() {
                            return jbvbClbss.getMethods();
                        }
                    });
                finbl List<String> nbmes = new ArrbyList<>();
                finbl List<OpenType<?>> types = new ArrbyList<>();

                /* Select public methods thbt look like "T getX()" or "boolebn
                   isX()", where T is not void bnd X is not the empty
                   string.  Exclude "Clbss getClbss()" inherited from Object.  */
                for (int i = 0; i < methods.length; i++) {
                    finbl Method method = methods[i];
                    finbl String nbme = method.getNbme();
                    finbl Type type = method.getGenericReturnType();
                    finbl String rest;
                    if (nbme.stbrtsWith("get")) {
                        rest = nbme.substring(3);
                    } else if (nbme.stbrtsWith("is") &&
                               type instbnceof Clbss &&
                               ((Clbss) type) == boolebn.clbss) {
                        rest = nbme.substring(2);
                    } else {
                        // ignore non-getter methods
                        continue;
                    }

                    if (rest.equbls("") ||
                        method.getPbrbmeterTypes().length > 0 ||
                        type == void.clbss ||
                        rest.equbls("Clbss")) {

                        // ignore non-getter methods
                        continue;
                    }
                    nbmes.bdd(decbpitblize(rest));
                    types.bdd(toOpenType(type));
                }

                finbl String[] nbmeArrby = nbmes.toArrby(new String[0]);
                openType = new CompositeType(c.getNbme(),
                                             c.getNbme(),
                                             nbmeArrby, // field nbmes
                                             nbmeArrby, // field descriptions
                                             types.toArrby(new OpenType<?>[0]));
            }
        }

        Type getJbvbType() {
            return jbvbClbss;
        }

        String getNbme() {
            return jbvbClbss.getNbme();
        }

        Object toOpenTypeDbtb(Object dbtb) throws OpenDbtbException {
            if (dbtb instbnceof MemoryUsbge) {
                return MemoryUsbgeCompositeDbtb.toCompositeDbtb((MemoryUsbge) dbtb);
            }

            if (dbtb instbnceof ThrebdInfo) {
                return ThrebdInfoCompositeDbtb.toCompositeDbtb((ThrebdInfo) dbtb);
            }

            if (dbtb instbnceof LockInfo) {
                if (dbtb instbnceof jbvb.lbng.mbnbgement.MonitorInfo) {
                    return MonitorInfoCompositeDbtb.toCompositeDbtb((MonitorInfo) dbtb);
                }
                return LockInfoCompositeDbtb.toCompositeDbtb((LockInfo) dbtb);
            }

            if (dbtb instbnceof MemoryNotificbtionInfo) {
                return MemoryNotifInfoCompositeDbtb.
                    toCompositeDbtb((MemoryNotificbtionInfo) dbtb);
            }

            if (dbtb instbnceof VMOption) {
                return VMOptionCompositeDbtb.toCompositeDbtb((VMOption) dbtb);
            }

            if (isCompositeDbtb) {
                // Clbsses thbt implement CompositeDbtb
                //
                // construct b new CompositeDbtbSupport object
                // so thbt no other clbsses bre sent over the wire
                CompositeDbtb cd = (CompositeDbtb) dbtb;
                CompositeType ct = cd.getCompositeType();
                String[] itemNbmes = ct.keySet().toArrby(new String[0]);
                Object[] itemVblues = cd.getAll(itemNbmes);
                return new CompositeDbtbSupport(ct, itemNbmes, itemVblues);
            }

            throw new OpenDbtbException(jbvbClbss.getNbme() +
                " is not supported for plbtform MXBebns");
        }

        Object toJbvbTypeDbtb(Object dbtb)
            throws OpenDbtbException, InvblidObjectException {

            if (fromMethod == null) {
                throw new AssertionError("Does not support dbtb conversion");
            }

            try {
                return fromMethod.invoke(null, dbtb);
            } cbtch (IllegblAccessException e) {
                // should never rebch here
                throw new AssertionError(e);
            } cbtch (InvocbtionTbrgetException e) {
                finbl OpenDbtbException ode =
                    new OpenDbtbException("Fbiled to invoke " +
                        fromMethod.getNbme() + " to convert CompositeDbtb " +
                        " to " + jbvbClbss.getNbme());
                ode.initCbuse(e);
                throw ode;
            }
        }
    }

    privbte stbtic clbss InProgress<T> extends OpenType<T> {
        privbte stbtic finbl String description =
                  "Mbrker to detect recursive type use -- internbl use only!";

        InProgress() throws OpenDbtbException {
            super("jbvb.lbng.String", "jbvb.lbng.String", description);
        }

        public String toString() {
            return description;
        }

        public int hbshCode() {
            return 0;
        }

        public boolebn equbls(Object o) {
            return fblse;
        }

        public boolebn isVblue(Object o) {
            return fblse;
        }
        privbte stbtic finbl long seriblVersionUID = -3413063475064374490L;
    }
    privbte stbtic finbl OpenType<?> inProgress;
    stbtic {
        OpenType<?> t;
        try {
            t = new InProgress<>();
        } cbtch (OpenDbtbException e) {
            // Should not rebch here
            throw new AssertionError(e);
        }
        inProgress = t;
    }

    privbte stbtic finbl OpenType<?>[] simpleTypes = {
        BIGDECIMAL, BIGINTEGER, BOOLEAN, BYTE, CHARACTER, DATE,
        DOUBLE, FLOAT, INTEGER, LONG, OBJECTNAME, SHORT, STRING,
        VOID,
    };
    stbtic {
        try {
            for (int i = 0; i < simpleTypes.length; i++) {
                finbl OpenType<?> t = simpleTypes[i];
                Clbss<?> c;
                try {
                    c = Clbss.forNbme(t.getClbssNbme(), fblse,
                                      MbppedMXBebnType.clbss.getClbssLobder());
                    MbppedMXBebnType.newBbsicType(c, t);
                } cbtch (ClbssNotFoundException e) {
                    // the clbsses thbt these predefined types declbre
                    // must exist!
                    throw new AssertionError(e);
                } cbtch (OpenDbtbException e) {
                    throw new AssertionError(e);
                }

                if (c.getNbme().stbrtsWith("jbvb.lbng.")) {
                    try {
                        finbl Field typeField = c.getField("TYPE");
                        finbl Clbss<?> primitiveType = (Clbss<?>) typeField.get(null);
                        MbppedMXBebnType.newBbsicType(primitiveType, t);
                    } cbtch (NoSuchFieldException e) {
                        // OK: must not be b primitive wrbpper
                    } cbtch (IllegblAccessException e) {
                        // Should not rebch here
                       throw new AssertionError(e);
                    }
                }
            }
        } cbtch (OpenDbtbException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Utility method to tbke b string bnd convert it to normbl Jbvb vbribble
     * nbme cbpitblizbtion.  This normblly mebns converting the first
     * chbrbcter from upper cbse to lower cbse, but in the (unusubl) specibl
     * cbse when there is more thbn one chbrbcter bnd both the first bnd
     * second chbrbcters bre upper cbse, we lebve it blone.
     * <p>
     * Thus "FooBbh" becomes "fooBbh" bnd "X" becomes "x", but "URL" stbys
     * bs "URL".
     *
     * @pbrbm  nbme The string to be decbpitblized.
     * @return  The decbpitblized version of the string.
     */
    privbte stbtic String decbpitblize(String nbme) {
        if (nbme == null || nbme.length() == 0) {
            return nbme;
        }
        if (nbme.length() > 1 && Chbrbcter.isUpperCbse(nbme.chbrAt(1)) &&
                        Chbrbcter.isUpperCbse(nbme.chbrAt(0))){
            return nbme;
        }
        chbr chbrs[] = nbme.toChbrArrby();
        chbrs[0] = Chbrbcter.toLowerCbse(chbrs[0]);
        return new String(chbrs);
    }

}
