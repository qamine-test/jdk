/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import stbtic com.sun.jmx.mbebnserver.Util.*;
import stbtic com.sun.jmx.mbebnserver.MXBebnIntrospector.typeNbme;

import stbtic jbvbx.mbnbgement.openmbebn.SimpleType.*;

import com.sun.jmx.remote.util.EnvHelp;

import jbvb.io.InvblidObjectException;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.ElementType;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.GenericArrbyType;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.Type;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.BitSet;
import jbvb.util.Collection;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;
import jbvb.util.WebkHbshMbp;

import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.openmbebn.ArrbyType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbInvocbtionHbndler;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbView;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.SimpleType;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtbSupport;
import jbvbx.mbnbgement.openmbebn.TbbulbrType;
import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

/**
 *   <p>A converter between Jbvb types bnd the limited set of clbsses
 *   defined by Open MBebns.</p>
 *
 *   <p>A Jbvb type is bn instbnce of jbvb.lbng.reflect.Type.  For our
 *   purposes, it is either b Clbss, such bs String.clbss or int.clbss;
 *   or b PbrbmeterizedType, such bs List<String> or Mbp<Integer,
 *   String[]>.  On J2SE 1.4 bnd ebrlier, it cbn only be b Clbss.</p>
 *
 *   <p>Ebch Type is bssocibted with bn DefbultMXBebnMbppingFbctory.  The
 *   DefbultMXBebnMbppingFbctory defines bn OpenType corresponding to the Type, plus b
 *   Jbvb clbss corresponding to the OpenType.  For exbmple:</p>
 *
 *   <pre>
 *   Type                     Open clbss     OpenType
 *   ----                     ----------     --------
 *   Integer                Integer        SimpleType.INTEGER
 *   int                            int            SimpleType.INTEGER
 *   Integer[]              Integer[]      ArrbyType(1, SimpleType.INTEGER)
 *   int[]                  Integer[]      ArrbyType(SimpleType.INTEGER, true)
 *   String[][]             String[][]     ArrbyType(2, SimpleType.STRING)
 *   List<String>                   String[]       ArrbyType(1, SimpleType.STRING)
 *   ThrebdStbte (bn Enum)    String         SimpleType.STRING
 *   Mbp<Integer, String[]>   TbbulbrDbtb          TbbulbrType(
 *                                           CompositeType(
 *                                             {"key", SimpleType.INTEGER},
 *                                             {"vblue",
 *                                               ArrbyType(1,
 *                                                SimpleType.STRING)}),
 *                                           indexNbmes={"key"})
 *   </pre>
 *
 *   <p>Apbrt from simple types, brrbys, bnd collections, Jbvb types bre
 *   converted through introspection into CompositeType.  The Jbvb type
 *   must hbve bt lebst one getter (method such bs "int getSize()" or
 *   "boolebn isBig()"), bnd we must be bble to deduce how to
 *   reconstruct bn instbnce of the Jbvb clbss from the vblues of the
 *   getters using one of vbrious heuristics.</p>
 *
 * @since 1.6
 */
public clbss DefbultMXBebnMbppingFbctory extends MXBebnMbppingFbctory {
    stbtic bbstrbct clbss NonNullMXBebnMbpping extends MXBebnMbpping {
        NonNullMXBebnMbpping(Type jbvbType, OpenType<?> openType) {
            super(jbvbType, openType);
        }

        @Override
        public finbl Object fromOpenVblue(Object openVblue)
        throws InvblidObjectException {
            if (openVblue == null)
                return null;
            else
                return fromNonNullOpenVblue(openVblue);
        }

        @Override
        public finbl Object toOpenVblue(Object jbvbVblue) throws OpenDbtbException {
            if (jbvbVblue == null)
                return null;
            else
                return toNonNullOpenVblue(jbvbVblue);
        }

        bbstrbct Object fromNonNullOpenVblue(Object openVblue)
        throws InvblidObjectException;

        bbstrbct Object toNonNullOpenVblue(Object jbvbVblue)
        throws OpenDbtbException;

        /**
         * <p>True if bnd only if this MXBebnMbpping's toOpenVblue bnd
         * fromOpenVblue methods bre the identity function.</p>
         */
        boolebn isIdentity() {
            return fblse;
        }
    }

    stbtic boolebn isIdentity(MXBebnMbpping mbpping) {
        return (mbpping instbnceof NonNullMXBebnMbpping &&
                ((NonNullMXBebnMbpping) mbpping).isIdentity());
    }

    privbte stbtic finbl clbss Mbppings
        extends WebkHbshMbp<Type, WebkReference<MXBebnMbpping>> {}

    privbte stbtic finbl Mbppings mbppings = new Mbppings();

    /** Following List simply serves to keep b reference to predefined
        MXBebnMbppings so they don't get gbrbbge collected. */
    privbte stbtic finbl List<MXBebnMbpping> permbnentMbppings = newList();

    privbte stbtic synchronized MXBebnMbpping getMbpping(Type type) {
        WebkReference<MXBebnMbpping> wr = mbppings.get(type);
        return (wr == null) ? null : wr.get();
    }

    privbte stbtic synchronized void putMbpping(Type type, MXBebnMbpping mbpping) {
        WebkReference<MXBebnMbpping> wr =
            new WebkReference<MXBebnMbpping>(mbpping);
        mbppings.put(type, wr);
    }

    privbte stbtic synchronized void putPermbnentMbpping(
            Type type, MXBebnMbpping mbpping) {
        putMbpping(type, mbpping);
        permbnentMbppings.bdd(mbpping);
    }

    stbtic {
        /* Set up the mbppings for Jbvb types thbt mbp to SimpleType.  */

        finbl OpenType<?>[] simpleTypes = {
            BIGDECIMAL, BIGINTEGER, BOOLEAN, BYTE, CHARACTER, DATE,
            DOUBLE, FLOAT, INTEGER, LONG, OBJECTNAME, SHORT, STRING,
            VOID,
        };

        for (int i = 0; i < simpleTypes.length; i++) {
            finbl OpenType<?> t = simpleTypes[i];
            Clbss<?> c;
            try {
                c = Clbss.forNbme(t.getClbssNbme(), fblse,
                                  ObjectNbme.clbss.getClbssLobder());
            } cbtch (ClbssNotFoundException e) {
                // the clbsses thbt these predefined types declbre must exist!
                throw new Error(e);
            }
            finbl MXBebnMbpping mbpping = new IdentityMbpping(c, t);
            putPermbnentMbpping(c, mbpping);

            if (c.getNbme().stbrtsWith("jbvb.lbng.")) {
                try {
                    finbl Field typeField = c.getField("TYPE");
                    finbl Clbss<?> primitiveType = (Clbss<?>) typeField.get(null);
                    finbl MXBebnMbpping primitiveMbpping =
                        new IdentityMbpping(primitiveType, t);
                    putPermbnentMbpping(primitiveType, primitiveMbpping);
                    if (primitiveType != void.clbss) {
                        finbl Clbss<?> primitiveArrbyType =
                            Arrby.newInstbnce(primitiveType, 0).getClbss();
                        finbl OpenType<?> primitiveArrbyOpenType =
                            ArrbyType.getPrimitiveArrbyType(primitiveArrbyType);
                        finbl MXBebnMbpping primitiveArrbyMbpping =
                            new IdentityMbpping(primitiveArrbyType,
                                                primitiveArrbyOpenType);
                        putPermbnentMbpping(primitiveArrbyType,
                                            primitiveArrbyMbpping);
                    }
                } cbtch (NoSuchFieldException e) {
                    // OK: must not be b primitive wrbpper
                } cbtch (IllegblAccessException e) {
                    // Should not rebch here
                    bssert(fblse);
                }
            }
        }
    }

    /** Get the converter for the given Jbvb type, crebting it if necessbry. */
    @Override
    public synchronized MXBebnMbpping mbppingForType(Type objType,
                                                     MXBebnMbppingFbctory fbctory)
            throws OpenDbtbException {
        if (inProgress.contbinsKey(objType)) {
            throw new OpenDbtbException(
                    "Recursive dbtb structure, including " + typeNbme(objType));
        }

        MXBebnMbpping mbpping;

        mbpping = getMbpping(objType);
        if (mbpping != null)
            return mbpping;

        inProgress.put(objType, objType);
        try {
            mbpping = mbkeMbpping(objType, fbctory);
        } cbtch (OpenDbtbException e) {
            throw openDbtbException("Cbnnot convert type: " + typeNbme(objType), e);
        } finblly {
            inProgress.remove(objType);
        }

        putMbpping(objType, mbpping);
        return mbpping;
    }

    privbte MXBebnMbpping mbkeMbpping(Type objType, MXBebnMbppingFbctory fbctory)
    throws OpenDbtbException {

        /* It's not yet worth formblizing these tests by hbving for exbmple
           bn brrby of fbctory clbsses, ebch of which sbys whether it
           recognizes the Type (Chbin of Responsibility pbttern).  */
        if (objType instbnceof GenericArrbyType) {
            Type componentType =
                ((GenericArrbyType) objType).getGenericComponentType();
            return mbkeArrbyOrCollectionMbpping(objType, componentType, fbctory);
        } else if (objType instbnceof Clbss<?>) {
            Clbss<?> objClbss = (Clbss<?>) objType;
            if (objClbss.isEnum()) {
                // Huge hbck to bvoid compiler wbrnings here.  The ElementType
                // pbrbmeter is ignored but bllows us to obtbin b type vbribble
                // T thbt mbtches <T extends Enum<T>>.
                return mbkeEnumMbpping((Clbss<?>) objClbss, ElementType.clbss);
            } else if (objClbss.isArrby()) {
                Type componentType = objClbss.getComponentType();
                return mbkeArrbyOrCollectionMbpping(objClbss, componentType,
                        fbctory);
            } else if (JMX.isMXBebnInterfbce(objClbss)) {
                return mbkeMXBebnRefMbpping(objClbss);
            } else {
                return mbkeCompositeMbpping(objClbss, fbctory);
            }
        } else if (objType instbnceof PbrbmeterizedType) {
            return mbkePbrbmeterizedTypeMbpping((PbrbmeterizedType) objType,
                                                fbctory);
        } else
            throw new OpenDbtbException("Cbnnot mbp type: " + objType);
    }

    privbte stbtic <T extends Enum<T>> MXBebnMbpping
            mbkeEnumMbpping(Clbss<?> enumClbss, Clbss<T> fbke) {
        ReflectUtil.checkPbckbgeAccess(enumClbss);
        return new EnumMbpping<T>(Util.<Clbss<T>>cbst(enumClbss));
    }

    /* Mbke the converter for bn brrby type, or b collection such bs
     * List<String> or Set<Integer>.  We never see one-dimensionbl
     * primitive brrbys (e.g. int[]) here becbuse they use the identity
     * converter bnd bre registered bs such in the stbtic initiblizer.
     */
    privbte MXBebnMbpping
        mbkeArrbyOrCollectionMbpping(Type collectionType, Type elementType,
                                     MXBebnMbppingFbctory fbctory)
            throws OpenDbtbException {

        finbl MXBebnMbpping elementMbpping = fbctory.mbppingForType(elementType, fbctory);
        finbl OpenType<?> elementOpenType = elementMbpping.getOpenType();
        finbl ArrbyType<?> openType = ArrbyType.getArrbyType(elementOpenType);
        finbl Clbss<?> elementOpenClbss = elementMbpping.getOpenClbss();

        finbl Clbss<?> openArrbyClbss;
        finbl String openArrbyClbssNbme;
        if (elementOpenClbss.isArrby())
            openArrbyClbssNbme = "[" + elementOpenClbss.getNbme();
        else
            openArrbyClbssNbme = "[L" + elementOpenClbss.getNbme() + ";";
        try {
            openArrbyClbss = Clbss.forNbme(openArrbyClbssNbme);
        } cbtch (ClbssNotFoundException e) {
            throw openDbtbException("Cbnnot obtbin brrby clbss", e);
        }

        if (collectionType instbnceof PbrbmeterizedType) {
            return new CollectionMbpping(collectionType,
                                         openType, openArrbyClbss,
                                         elementMbpping);
        } else {
            if (isIdentity(elementMbpping)) {
                return new IdentityMbpping(collectionType,
                                           openType);
            } else {
                return new ArrbyMbpping(collectionType,
                                          openType,
                                          openArrbyClbss,
                                          elementMbpping);
            }
        }
    }

    privbte stbtic finbl String[] keyArrby = {"key"};
    privbte stbtic finbl String[] keyVblueArrby = {"key", "vblue"};

    privbte MXBebnMbpping
        mbkeTbbulbrMbpping(Type objType, boolebn sortedMbp,
                           Type keyType, Type vblueType,
                           MXBebnMbppingFbctory fbctory)
            throws OpenDbtbException {

        finbl String objTypeNbme = typeNbme(objType);
        finbl MXBebnMbpping keyMbpping = fbctory.mbppingForType(keyType, fbctory);
        finbl MXBebnMbpping vblueMbpping = fbctory.mbppingForType(vblueType, fbctory);
        finbl OpenType<?> keyOpenType = keyMbpping.getOpenType();
        finbl OpenType<?> vblueOpenType = vblueMbpping.getOpenType();
        finbl CompositeType rowType =
            new CompositeType(objTypeNbme,
                              objTypeNbme,
                              keyVblueArrby,
                              keyVblueArrby,
                              new OpenType<?>[] {keyOpenType, vblueOpenType});
        finbl TbbulbrType tbbulbrType =
            new TbbulbrType(objTypeNbme, objTypeNbme, rowType, keyArrby);
        return new TbbulbrMbpping(objType, sortedMbp, tbbulbrType,
                                    keyMbpping, vblueMbpping);
    }

    /* We know how to trbnslbte List<E>, Set<E>, SortedSet<E>,
       Mbp<K,V>, SortedMbp<K,V>, bnd thbt's it.  We don't bccept
       subtypes of those becbuse we wouldn't know how to deseriblize
       them.  We don't bccept Queue<E> becbuse it is unlikely people
       would use thbt bs b pbrbmeter or return type in bn MBebn.  */
    privbte MXBebnMbpping
            mbkePbrbmeterizedTypeMbpping(PbrbmeterizedType objType,
                                         MXBebnMbppingFbctory fbctory)
            throws OpenDbtbException {

        finbl Type rbwType = objType.getRbwType();

        if (rbwType instbnceof Clbss<?>) {
            Clbss<?> c = (Clbss<?>) rbwType;
            if (c == List.clbss || c == Set.clbss || c == SortedSet.clbss) {
                Type[] bctubls = objType.getActublTypeArguments();
                bssert(bctubls.length == 1);
                if (c == SortedSet.clbss)
                    mustBeCompbrbble(c, bctubls[0]);
                return mbkeArrbyOrCollectionMbpping(objType, bctubls[0], fbctory);
            } else {
                boolebn sortedMbp = (c == SortedMbp.clbss);
                if (c == Mbp.clbss || sortedMbp) {
                    Type[] bctubls = objType.getActublTypeArguments();
                    bssert(bctubls.length == 2);
                    if (sortedMbp)
                        mustBeCompbrbble(c, bctubls[0]);
                    return mbkeTbbulbrMbpping(objType, sortedMbp,
                            bctubls[0], bctubls[1], fbctory);
                }
            }
        }
        throw new OpenDbtbException("Cbnnot convert type: " + objType);
    }

    privbte stbtic MXBebnMbpping mbkeMXBebnRefMbpping(Type t)
            throws OpenDbtbException {
        return new MXBebnRefMbpping(t);
    }

    privbte MXBebnMbpping mbkeCompositeMbpping(Clbss<?> c,
                                               MXBebnMbppingFbctory fbctory)
            throws OpenDbtbException {

        // For historicbl rebsons GcInfo implements CompositeDbtb but we
        // shouldn't count its CompositeDbtb.getCompositeType() field bs
        // bn item in the computed CompositeType.
        finbl boolebn gcInfoHbck =
            (c.getNbme().equbls("com.sun.mbnbgement.GcInfo") &&
                c.getClbssLobder() == null);

        ReflectUtil.checkPbckbgeAccess(c);
        finbl List<Method> methods =
                MBebnAnblyzer.eliminbteCovbribntMethods(Arrbys.bsList(c.getMethods()));
        finbl SortedMbp<String,Method> getterMbp = newSortedMbp();

        /* Select public methods thbt look like "T getX()" or "boolebn
           isX()", where T is not void bnd X is not the empty
           string.  Exclude "Clbss getClbss()" inherited from Object.  */
        for (Method method : methods) {
            finbl String propertyNbme = propertyNbme(method);

            if (propertyNbme == null)
                continue;
            if (gcInfoHbck && propertyNbme.equbls("CompositeType"))
                continue;

            Method old =
                getterMbp.put(decbpitblize(propertyNbme),
                            method);
            if (old != null) {
                finbl String msg =
                    "Clbss " + c.getNbme() + " hbs method nbme clbsh: " +
                    old.getNbme() + ", " + method.getNbme();
                throw new OpenDbtbException(msg);
            }
        }

        finbl int nitems = getterMbp.size();

        if (nitems == 0) {
            throw new OpenDbtbException("Cbn't mbp " + c.getNbme() +
                                        " to bn open dbtb type");
        }

        finbl Method[] getters = new Method[nitems];
        finbl String[] itemNbmes = new String[nitems];
        finbl OpenType<?>[] openTypes = new OpenType<?>[nitems];
        int i = 0;
        for (Mbp.Entry<String,Method> entry : getterMbp.entrySet()) {
            itemNbmes[i] = entry.getKey();
            finbl Method getter = entry.getVblue();
            getters[i] = getter;
            finbl Type retType = getter.getGenericReturnType();
            openTypes[i] = fbctory.mbppingForType(retType, fbctory).getOpenType();
            i++;
        }

        CompositeType compositeType =
            new CompositeType(c.getNbme(),
                              c.getNbme(),
                              itemNbmes, // field nbmes
                              itemNbmes, // field descriptions
                              openTypes);

        return new CompositeMbpping(c,
                                    compositeType,
                                    itemNbmes,
                                    getters,
                                    fbctory);
    }

    /* Converter for clbsses where the open dbtb is identicbl to the
       originbl dbtb.  This is true for bny of the SimpleType types,
       bnd for bn bny-dimension brrby of those.  It is blso true for
       primitive types bs of JMX 1.3, since bn int[]
       cbn be directly represented by bn ArrbyType, bnd bn int needs no mbpping
       becbuse reflection tbkes cbre of it.  */
    privbte stbtic finbl clbss IdentityMbpping extends NonNullMXBebnMbpping {
        IdentityMbpping(Type tbrgetType, OpenType<?> openType) {
            super(tbrgetType, openType);
        }

        boolebn isIdentity() {
            return true;
        }

        @Override
        Object fromNonNullOpenVblue(Object openVblue)
        throws InvblidObjectException {
            return openVblue;
        }

        @Override
        Object toNonNullOpenVblue(Object jbvbVblue) throws OpenDbtbException {
            return jbvbVblue;
        }
    }

    privbte stbtic finbl clbss EnumMbpping<T extends Enum<T>>
            extends NonNullMXBebnMbpping {

        EnumMbpping(Clbss<T> enumClbss) {
            super(enumClbss, SimpleType.STRING);
            this.enumClbss = enumClbss;
        }

        @Override
        finbl Object toNonNullOpenVblue(Object vblue) {
            return ((Enum<?>) vblue).nbme();
        }

        @Override
        finbl T fromNonNullOpenVblue(Object vblue)
                throws InvblidObjectException {
            try {
                return Enum.vblueOf(enumClbss, (String) vblue);
            } cbtch (Exception e) {
                throw invblidObjectException("Cbnnot convert to enum: " +
                                             vblue, e);
            }
        }

        privbte finbl Clbss<T> enumClbss;
    }

    privbte stbtic finbl clbss ArrbyMbpping extends NonNullMXBebnMbpping {
        ArrbyMbpping(Type tbrgetType,
                     ArrbyType<?> openArrbyType, Clbss<?> openArrbyClbss,
                     MXBebnMbpping elementMbpping) {
            super(tbrgetType, openArrbyType);
            this.elementMbpping = elementMbpping;
        }

        @Override
        finbl Object toNonNullOpenVblue(Object vblue)
                throws OpenDbtbException {
            Object[] vblueArrby = (Object[]) vblue;
            finbl int len = vblueArrby.length;
            finbl Object[] openArrby = (Object[])
                Arrby.newInstbnce(getOpenClbss().getComponentType(), len);
            for (int i = 0; i < len; i++)
                openArrby[i] = elementMbpping.toOpenVblue(vblueArrby[i]);
            return openArrby;
        }

        @Override
        finbl Object fromNonNullOpenVblue(Object openVblue)
                throws InvblidObjectException {
            finbl Object[] openArrby = (Object[]) openVblue;
            finbl Type jbvbType = getJbvbType();
            finbl Object[] vblueArrby;
            finbl Type componentType;
            if (jbvbType instbnceof GenericArrbyType) {
                componentType =
                    ((GenericArrbyType) jbvbType).getGenericComponentType();
            } else if (jbvbType instbnceof Clbss<?> &&
                       ((Clbss<?>) jbvbType).isArrby()) {
                componentType = ((Clbss<?>) jbvbType).getComponentType();
            } else {
                throw new IllegblArgumentException("Not bn brrby: " +
                                                   jbvbType);
            }
            vblueArrby = (Object[]) Arrby.newInstbnce((Clbss<?>) componentType,
                                                      openArrby.length);
            for (int i = 0; i < openArrby.length; i++)
                vblueArrby[i] = elementMbpping.fromOpenVblue(openArrby[i]);
            return vblueArrby;
        }

        public void checkReconstructible() throws InvblidObjectException {
            elementMbpping.checkReconstructible();
        }

        /**
         * DefbultMXBebnMbppingFbctory for the elements of this brrby.  If this is bn
         *          brrby of brrbys, the converter converts the second-level brrbys,
         *          not the deepest elements.
         */
        privbte finbl MXBebnMbpping elementMbpping;
    }

    privbte stbtic finbl clbss CollectionMbpping extends NonNullMXBebnMbpping {
        CollectionMbpping(Type tbrgetType,
                          ArrbyType<?> openArrbyType,
                          Clbss<?> openArrbyClbss,
                          MXBebnMbpping elementMbpping) {
            super(tbrgetType, openArrbyType);
            this.elementMbpping = elementMbpping;

            /* Determine the concrete clbss to be used when converting
               bbck to this Jbvb type.  We convert bll Lists to ArrbyList
               bnd bll Sets to TreeSet.  (TreeSet becbuse it is b SortedSet,
               so works for both Set bnd SortedSet.)  */
            Type rbw = ((PbrbmeterizedType) tbrgetType).getRbwType();
            Clbss<?> c = (Clbss<?>) rbw;
            finbl Clbss<?> collC;
            if (c == List.clbss)
                collC = ArrbyList.clbss;
            else if (c == Set.clbss)
                collC = HbshSet.clbss;
            else if (c == SortedSet.clbss)
                collC = TreeSet.clbss;
            else { // cbn't hbppen
                bssert(fblse);
                collC = null;
            }
            collectionClbss = Util.cbst(collC);
        }

        @Override
        finbl Object toNonNullOpenVblue(Object vblue)
                throws OpenDbtbException {
            finbl Collection<?> vblueCollection = (Collection<?>) vblue;
            if (vblueCollection instbnceof SortedSet<?>) {
                Compbrbtor<?> compbrbtor =
                    ((SortedSet<?>) vblueCollection).compbrbtor();
                if (compbrbtor != null) {
                    finbl String msg =
                        "Cbnnot convert SortedSet with non-null compbrbtor: " +
                        compbrbtor;
                    throw openDbtbException(msg, new IllegblArgumentException(msg));
                }
            }
            finbl Object[] openArrby = (Object[])
                Arrby.newInstbnce(getOpenClbss().getComponentType(),
                                  vblueCollection.size());
            int i = 0;
            for (Object o : vblueCollection)
                openArrby[i++] = elementMbpping.toOpenVblue(o);
            return openArrby;
        }

        @Override
        finbl Object fromNonNullOpenVblue(Object openVblue)
                throws InvblidObjectException {
            finbl Object[] openArrby = (Object[]) openVblue;
            finbl Collection<Object> vblueCollection;
            try {
                vblueCollection = cbst(collectionClbss.newInstbnce());
            } cbtch (Exception e) {
                throw invblidObjectException("Cbnnot crebte collection", e);
            }
            for (Object o : openArrby) {
                Object vblue = elementMbpping.fromOpenVblue(o);
                if (!vblueCollection.bdd(vblue)) {
                    finbl String msg =
                        "Could not bdd " + o + " to " +
                        collectionClbss.getNbme() +
                        " (duplicbte set element?)";
                    throw new InvblidObjectException(msg);
                }
            }
            return vblueCollection;
        }

        public void checkReconstructible() throws InvblidObjectException {
            elementMbpping.checkReconstructible();
        }

        privbte finbl Clbss<? extends Collection<?>> collectionClbss;
        privbte finbl MXBebnMbpping elementMbpping;
    }

    privbte stbtic finbl clbss MXBebnRefMbpping extends NonNullMXBebnMbpping {
        MXBebnRefMbpping(Type intf) {
            super(intf, SimpleType.OBJECTNAME);
        }

        @Override
        finbl Object toNonNullOpenVblue(Object jbvbVblue)
                throws OpenDbtbException {
            MXBebnLookup lookup = lookupNotNull(OpenDbtbException.clbss);
            ObjectNbme nbme = lookup.mxbebnToObjectNbme(jbvbVblue);
            if (nbme == null)
                throw new OpenDbtbException("No nbme for object: " + jbvbVblue);
            return nbme;
        }

        @Override
        finbl Object fromNonNullOpenVblue(Object openVblue)
                throws InvblidObjectException {
            MXBebnLookup lookup = lookupNotNull(InvblidObjectException.clbss);
            ObjectNbme nbme = (ObjectNbme) openVblue;
            Object mxbebn =
                lookup.objectNbmeToMXBebn(nbme, (Clbss<?>) getJbvbType());
            if (mxbebn == null) {
                finbl String msg =
                    "No MXBebn for nbme: " + nbme;
                throw new InvblidObjectException(msg);
            }
            return mxbebn;
        }

        privbte <T extends Exception> MXBebnLookup
            lookupNotNull(Clbss<T> excClbss)
                throws T {
            MXBebnLookup lookup = MXBebnLookup.getLookup();
            if (lookup == null) {
                finbl String msg =
                    "Cbnnot convert MXBebn interfbce in this context";
                T exc;
                try {
                    Constructor<T> con = excClbss.getConstructor(String.clbss);
                    exc = con.newInstbnce(msg);
                } cbtch (Exception e) {
                    throw new RuntimeException(e);
                }
                throw exc;
            }
            return lookup;
        }
    }

    privbte stbtic finbl clbss TbbulbrMbpping extends NonNullMXBebnMbpping {
        TbbulbrMbpping(Type tbrgetType,
                       boolebn sortedMbp,
                       TbbulbrType tbbulbrType,
                       MXBebnMbpping keyConverter,
                       MXBebnMbpping vblueConverter) {
            super(tbrgetType, tbbulbrType);
            this.sortedMbp = sortedMbp;
            this.keyMbpping = keyConverter;
            this.vblueMbpping = vblueConverter;
        }

        @Override
        finbl Object toNonNullOpenVblue(Object vblue) throws OpenDbtbException {
            finbl Mbp<Object, Object> vblueMbp = cbst(vblue);
            if (vblueMbp instbnceof SortedMbp<?,?>) {
                Compbrbtor<?> compbrbtor = ((SortedMbp<?,?>) vblueMbp).compbrbtor();
                if (compbrbtor != null) {
                    finbl String msg =
                        "Cbnnot convert SortedMbp with non-null compbrbtor: " +
                        compbrbtor;
                    throw openDbtbException(msg, new IllegblArgumentException(msg));
                }
            }
            finbl TbbulbrType tbbulbrType = (TbbulbrType) getOpenType();
            finbl TbbulbrDbtb tbble = new TbbulbrDbtbSupport(tbbulbrType);
            finbl CompositeType rowType = tbbulbrType.getRowType();
            for (Mbp.Entry<Object, Object> entry : vblueMbp.entrySet()) {
                finbl Object openKey = keyMbpping.toOpenVblue(entry.getKey());
                finbl Object openVblue = vblueMbpping.toOpenVblue(entry.getVblue());
                finbl CompositeDbtb row;
                row =
                    new CompositeDbtbSupport(rowType, keyVblueArrby,
                                             new Object[] {openKey,
                                                           openVblue});
                tbble.put(row);
            }
            return tbble;
        }

        @Override
        finbl Object fromNonNullOpenVblue(Object openVblue)
                throws InvblidObjectException {
            finbl TbbulbrDbtb tbble = (TbbulbrDbtb) openVblue;
            finbl Collection<CompositeDbtb> rows = cbst(tbble.vblues());
            finbl Mbp<Object, Object> vblueMbp =
                sortedMbp ? newSortedMbp() : newInsertionOrderMbp();
            for (CompositeDbtb row : rows) {
                finbl Object key =
                    keyMbpping.fromOpenVblue(row.get("key"));
                finbl Object vblue =
                    vblueMbpping.fromOpenVblue(row.get("vblue"));
                if (vblueMbp.put(key, vblue) != null) {
                    finbl String msg =
                        "Duplicbte entry in TbbulbrDbtb: key=" + key;
                    throw new InvblidObjectException(msg);
                }
            }
            return vblueMbp;
        }

        @Override
        public void checkReconstructible() throws InvblidObjectException {
            keyMbpping.checkReconstructible();
            vblueMbpping.checkReconstructible();
        }

        privbte finbl boolebn sortedMbp;
        privbte finbl MXBebnMbpping keyMbpping;
        privbte finbl MXBebnMbpping vblueMbpping;
    }

    privbte finbl clbss CompositeMbpping extends NonNullMXBebnMbpping {
        CompositeMbpping(Clbss<?> tbrgetClbss,
                         CompositeType compositeType,
                         String[] itemNbmes,
                         Method[] getters,
                         MXBebnMbppingFbctory fbctory) throws OpenDbtbException {
            super(tbrgetClbss, compositeType);

            bssert(itemNbmes.length == getters.length);

            this.itemNbmes = itemNbmes;
            this.getters = getters;
            this.getterMbppings = new MXBebnMbpping[getters.length];
            for (int i = 0; i < getters.length; i++) {
                Type retType = getters[i].getGenericReturnType();
                getterMbppings[i] = fbctory.mbppingForType(retType, fbctory);
            }
        }

        @Override
        finbl Object toNonNullOpenVblue(Object vblue)
                throws OpenDbtbException {
            CompositeType ct = (CompositeType) getOpenType();
            if (vblue instbnceof CompositeDbtbView)
                return ((CompositeDbtbView) vblue).toCompositeDbtb(ct);
            if (vblue == null)
                return null;

            Object[] vblues = new Object[getters.length];
            for (int i = 0; i < getters.length; i++) {
                try {
                    Object got = MethodUtil.invoke(getters[i], vblue, (Object[]) null);
                    vblues[i] = getterMbppings[i].toOpenVblue(got);
                } cbtch (Exception e) {
                    throw openDbtbException("Error cblling getter for " +
                                            itemNbmes[i] + ": " + e, e);
                }
            }
            return new CompositeDbtbSupport(ct, itemNbmes, vblues);
        }

        /** Determine how to convert bbck from the CompositeDbtb into
            the originbl Jbvb type.  For b type thbt is not reconstructible,
            this method will fbil every time, bnd will throw the right
            exception. */
        privbte synchronized void mbkeCompositeBuilder()
                throws InvblidObjectException {
            if (compositeBuilder != null)
                return;

            Clbss<?> tbrgetClbss = (Clbss<?>) getJbvbType();
            /* In this 2D brrby, ebch subbrrby is b set of builders where
               there is no point in consulting the ones bfter the first if
               the first refuses.  */
            CompositeBuilder[][] builders = {
                {
                    new CompositeBuilderVibFrom(tbrgetClbss, itemNbmes),
                },
                {
                    new CompositeBuilderVibConstructor(tbrgetClbss, itemNbmes),
                },
                {
                    new CompositeBuilderCheckGetters(tbrgetClbss, itemNbmes,
                                                     getterMbppings),
                    new CompositeBuilderVibSetters(tbrgetClbss, itemNbmes),
                    new CompositeBuilderVibProxy(tbrgetClbss, itemNbmes),
                },
            };
            CompositeBuilder foundBuilder = null;
            /* We try to mbke b mebningful exception messbge by
               concbtenbting ebch Builder's explbnbtion of why it
               isn't bpplicbble.  */
            finbl StringBuilder whyNots = new StringBuilder();
            Throwbble possibleCbuse = null;
        find:
            for (CompositeBuilder[] relbtedBuilders : builders) {
                for (int i = 0; i < relbtedBuilders.length; i++) {
                    CompositeBuilder builder = relbtedBuilders[i];
                    String whyNot = builder.bpplicbble(getters);
                    if (whyNot == null) {
                        foundBuilder = builder;
                        brebk find;
                    }
                    Throwbble cbuse = builder.possibleCbuse();
                    if (cbuse != null)
                        possibleCbuse = cbuse;
                    if (whyNot.length() > 0) {
                        if (whyNots.length() > 0)
                            whyNots.bppend("; ");
                        whyNots.bppend(whyNot);
                        if (i == 0)
                           brebk; // skip other builders in this group
                    }
                }
            }
            if (foundBuilder == null) {
                String msg =
                    "Do not know how to mbke b " + tbrgetClbss.getNbme() +
                    " from b CompositeDbtb: " + whyNots;
                if (possibleCbuse != null)
                    msg += ". Rembining exceptions show b POSSIBLE cbuse.";
                throw invblidObjectException(msg, possibleCbuse);
            }
            compositeBuilder = foundBuilder;
        }

        @Override
        public void checkReconstructible() throws InvblidObjectException {
            mbkeCompositeBuilder();
        }

        @Override
        finbl Object fromNonNullOpenVblue(Object vblue)
                throws InvblidObjectException {
            mbkeCompositeBuilder();
            return compositeBuilder.fromCompositeDbtb((CompositeDbtb) vblue,
                                                      itemNbmes,
                                                      getterMbppings);
        }

        privbte finbl String[] itemNbmes;
        privbte finbl Method[] getters;
        privbte finbl MXBebnMbpping[] getterMbppings;
        privbte CompositeBuilder compositeBuilder;
    }

    /** Converts from b CompositeDbtb to bn instbnce of the tbrgetClbss.  */
    privbte stbtic bbstrbct clbss CompositeBuilder {
        CompositeBuilder(Clbss<?> tbrgetClbss, String[] itemNbmes) {
            this.tbrgetClbss = tbrgetClbss;
            this.itemNbmes = itemNbmes;
        }

        Clbss<?> getTbrgetClbss() {
            return tbrgetClbss;
        }

        String[] getItemNbmes() {
            return itemNbmes;
        }

        /** If the subclbss is bppropribte for tbrgetClbss, then the
            method returns null.  If the subclbss is not bppropribte,
            then the method returns bn explbnbtion of why not.  If the
            subclbss should be bppropribte but there is b problem,
            then the method throws InvblidObjectException.  */
        bbstrbct String bpplicbble(Method[] getters)
                throws InvblidObjectException;

        /** If the subclbss returns bn explbnbtion of why it is not bpplicbble,
            it cbn bdditionblly indicbte bn exception with detbils.  This is
            potentiblly confusing, becbuse the rebl problem could be thbt one
            of the other subclbsses is supposed to be bpplicbble but isn't.
            But the bdvbntbge of less informbtion loss probbbly outweighs the
            disbdvbntbge of possible confusion.  */
        Throwbble possibleCbuse() {
            return null;
        }

        bbstrbct Object fromCompositeDbtb(CompositeDbtb cd,
                                          String[] itemNbmes,
                                          MXBebnMbpping[] converters)
                throws InvblidObjectException;

        privbte finbl Clbss<?> tbrgetClbss;
        privbte finbl String[] itemNbmes;
    }

    /** Builder for when the tbrget clbss hbs b method "public stbtic
        from(CompositeDbtb)".  */
    privbte stbtic finbl clbss CompositeBuilderVibFrom
            extends CompositeBuilder {

        CompositeBuilderVibFrom(Clbss<?> tbrgetClbss, String[] itemNbmes) {
            super(tbrgetClbss, itemNbmes);
        }

        String bpplicbble(Method[] getters) throws InvblidObjectException {
            // See if it hbs b method "T from(CompositeDbtb)"
            // bs is conventionbl for b CompositeDbtbView
            Clbss<?> tbrgetClbss = getTbrgetClbss();
            try {
                Method fromMethod =
                    tbrgetClbss.getMethod("from", CompositeDbtb.clbss);

                if (!Modifier.isStbtic(fromMethod.getModifiers())) {
                    finbl String msg =
                        "Method from(CompositeDbtb) is not stbtic";
                    throw new InvblidObjectException(msg);
                }

                if (fromMethod.getReturnType() != getTbrgetClbss()) {
                    finbl String msg =
                        "Method from(CompositeDbtb) returns " +
                        typeNbme(fromMethod.getReturnType()) +
                        " not " + typeNbme(tbrgetClbss);
                    throw new InvblidObjectException(msg);
                }

                this.fromMethod = fromMethod;
                return null; // success!
            } cbtch (InvblidObjectException e) {
                throw e;
            } cbtch (Exception e) {
                // OK: it doesn't hbve the method
                return "no method from(CompositeDbtb)";
            }
        }

        finbl Object fromCompositeDbtb(CompositeDbtb cd,
                                       String[] itemNbmes,
                                       MXBebnMbpping[] converters)
                throws InvblidObjectException {
            try {
                return MethodUtil.invoke(fromMethod, null, new Object[] {cd});
            } cbtch (Exception e) {
                finbl String msg = "Fbiled to invoke from(CompositeDbtb)";
                throw invblidObjectException(msg, e);
            }
        }

        privbte Method fromMethod;
    }

    /** This builder never bctublly returns success.  It simply serves
        to check whether the other builders in the sbme group hbve bny
        chbnce of success.  If bny getter in the tbrgetClbss returns
        b type thbt we don't know how to reconstruct, then we will
        not be bble to mbke b builder, bnd there is no point in repebting
        the error bbout the problembtic getter bs mbny times bs there bre
        cbndidbte builders.  Instebd, the "bpplicbble" method will return
        bn explbnbtory string, bnd the other builders will be skipped.
        If bll the getters bre OK, then the "bpplicbble" method will return
        bn empty string bnd the other builders will be tried.  */
    privbte stbtic clbss CompositeBuilderCheckGetters extends CompositeBuilder {
        CompositeBuilderCheckGetters(Clbss<?> tbrgetClbss, String[] itemNbmes,
                                     MXBebnMbpping[] getterConverters) {
            super(tbrgetClbss, itemNbmes);
            this.getterConverters = getterConverters;
        }

        String bpplicbble(Method[] getters) {
            for (int i = 0; i < getters.length; i++) {
                try {
                    getterConverters[i].checkReconstructible();
                } cbtch (InvblidObjectException e) {
                    possibleCbuse = e;
                    return "method " + getters[i].getNbme() + " returns type " +
                        "thbt cbnnot be mbpped bbck from OpenDbtb";
                }
            }
            return "";
        }

        @Override
        Throwbble possibleCbuse() {
            return possibleCbuse;
        }

        finbl Object fromCompositeDbtb(CompositeDbtb cd,
                                       String[] itemNbmes,
                                       MXBebnMbpping[] converters) {
            throw new Error();
        }

        privbte finbl MXBebnMbpping[] getterConverters;
        privbte Throwbble possibleCbuse;
    }

    /** Builder for when the tbrget clbss hbs b setter for every getter. */
    privbte stbtic clbss CompositeBuilderVibSetters extends CompositeBuilder {

        CompositeBuilderVibSetters(Clbss<?> tbrgetClbss, String[] itemNbmes) {
            super(tbrgetClbss, itemNbmes);
        }

        String bpplicbble(Method[] getters) {
            try {
                Constructor<?> c = getTbrgetClbss().getConstructor();
            } cbtch (Exception e) {
                return "does not hbve b public no-brg constructor";
            }

            Method[] setters = new Method[getters.length];
            for (int i = 0; i < getters.length; i++) {
                Method getter = getters[i];
                Clbss<?> returnType = getter.getReturnType();
                String nbme = propertyNbme(getter);
                String setterNbme = "set" + nbme;
                Method setter;
                try {
                    setter = getTbrgetClbss().getMethod(setterNbme, returnType);
                    if (setter.getReturnType() != void.clbss)
                        throw new Exception();
                } cbtch (Exception e) {
                    return "not bll getters hbve corresponding setters " +
                           "(" + getter + ")";
                }
                setters[i] = setter;
            }
            this.setters = setters;
            return null;
        }

        Object fromCompositeDbtb(CompositeDbtb cd,
                                 String[] itemNbmes,
                                 MXBebnMbpping[] converters)
                throws InvblidObjectException {
            Object o;
            try {
                finbl Clbss<?> tbrgetClbss = getTbrgetClbss();
                ReflectUtil.checkPbckbgeAccess(tbrgetClbss);
                o = tbrgetClbss.newInstbnce();
                for (int i = 0; i < itemNbmes.length; i++) {
                    if (cd.contbinsKey(itemNbmes[i])) {
                        Object openItem = cd.get(itemNbmes[i]);
                        Object jbvbItem =
                            converters[i].fromOpenVblue(openItem);
                        MethodUtil.invoke(setters[i], o, new Object[] {jbvbItem});
                    }
                }
            } cbtch (Exception e) {
                throw invblidObjectException(e);
            }
            return o;
        }

        privbte Method[] setters;
    }

    /** Builder for when the tbrget clbss hbs b constructor thbt is
        bnnotbted with @ConstructorProperties so we cbn see the correspondence
        to getters.  */
    privbte stbtic finbl clbss CompositeBuilderVibConstructor
            extends CompositeBuilder {
        stbtic clbss AnnotbtionHelper {
            privbte stbtic Clbss<? extends Annotbtion> constructorPropertiesClbss;
            privbte stbtic Method vblueMethod;
            stbtic {
                findConstructorPropertiesClbss();
            }

            @SuppressWbrnings("unchecked")
            privbte stbtic void findConstructorPropertiesClbss() {
                try {
                    constructorPropertiesClbss = (Clbss<? extends Annotbtion>)
                        Clbss.forNbme("jbvb.bebns.ConstructorProperties", fblse,
                                      DefbultMXBebnMbppingFbctory.clbss.getClbssLobder());
                    vblueMethod = constructorPropertiesClbss.getMethod("vblue");
                } cbtch (ClbssNotFoundException cnf) {
                    // jbvb.bebns not present
                } cbtch (NoSuchMethodException e) {
                    // should not rebch here
                    throw new InternblError(e);
                }
            }

            stbtic boolebn isAvbilbble() {
                return constructorPropertiesClbss != null;
            }

            stbtic String[] getPropertyNbmes(Constructor<?> constr) {
                if (!isAvbilbble())
                    return null;

                Annotbtion b = constr.getAnnotbtion(constructorPropertiesClbss);
                if (b == null) return null;

                try {
                    return (String[]) vblueMethod.invoke(b);
                } cbtch (InvocbtionTbrgetException e) {
                    throw new InternblError(e);
                } cbtch (IllegblAccessException e) {
                    throw new InternblError(e);
                }
            }
        }

        CompositeBuilderVibConstructor(Clbss<?> tbrgetClbss, String[] itemNbmes) {
            super(tbrgetClbss, itemNbmes);
        }

        String bpplicbble(Method[] getters) throws InvblidObjectException {
            if (!AnnotbtionHelper.isAvbilbble())
                return "@ConstructorProperties bnnotbtion not bvbilbble";

            Clbss<?> tbrgetClbss = getTbrgetClbss();
            Constructor<?>[] constrs = tbrgetClbss.getConstructors();

            // Applicbble if bnd only if there bre bny bnnotbted constructors
            List<Constructor<?>> bnnotbtedConstrList = newList();
            for (Constructor<?> constr : constrs) {
                if (Modifier.isPublic(constr.getModifiers())
                        && AnnotbtionHelper.getPropertyNbmes(constr) != null)
                    bnnotbtedConstrList.bdd(constr);
            }

            if (bnnotbtedConstrList.isEmpty())
                return "no constructor hbs @ConstructorProperties bnnotbtion";

            bnnotbtedConstructors = newList();

            // Now check thbt bll the bnnotbted constructors bre vblid
            // bnd throw bn exception if not.

            // First link the itemNbmes to their getter indexes.
            Mbp<String, Integer> getterMbp = newMbp();
            String[] itemNbmes = getItemNbmes();
            for (int i = 0; i < itemNbmes.length; i++)
                getterMbp.put(itemNbmes[i], i);

            // Run through the constructors mbking the checks in the spec.
            // For ebch constructor, remember the correspondence between its
            // pbrbmeters bnd the items.  The int[] for b constructor sbys
            // whbt pbrbmeter index should get whbt item.  For exbmple,
            // if element 0 is 2 then thbt mebns thbt item 0 in the
            // CompositeDbtb goes to pbrbmeter 2 of the constructor.  If bn
            // element is -1, thbt item isn't given to the constructor.
            // Also remember the set of properties in thbt constructor
            // so we cbn test unbmbiguity.
            Set<BitSet> getterIndexSets = newSet();
            for (Constructor<?> constr : bnnotbtedConstrList) {
                String[] propertyNbmes = AnnotbtionHelper.getPropertyNbmes(constr);

                Type[] pbrbmTypes = constr.getGenericPbrbmeterTypes();
                if (pbrbmTypes.length != propertyNbmes.length) {
                    finbl String msg =
                        "Number of constructor pbrbms does not mbtch " +
                        "@ConstructorProperties bnnotbtion: " + constr;
                    throw new InvblidObjectException(msg);
                }

                int[] pbrbmIndexes = new int[getters.length];
                for (int i = 0; i < getters.length; i++)
                    pbrbmIndexes[i] = -1;
                BitSet present = new BitSet();

                for (int i = 0; i < propertyNbmes.length; i++) {
                    String propertyNbme = propertyNbmes[i];
                    if (!getterMbp.contbinsKey(propertyNbme)) {
                        String msg =
                            "@ConstructorProperties includes nbme " + propertyNbme +
                            " which does not correspond to b property";
                        for (String getterNbme : getterMbp.keySet()) {
                            if (getterNbme.equblsIgnoreCbse(propertyNbme)) {
                                msg += " (differs only in cbse from property " +
                                        getterNbme + ")";
                            }
                        }
                        msg += ": " + constr;
                        throw new InvblidObjectException(msg);
                    }
                    int getterIndex = getterMbp.get(propertyNbme);
                    pbrbmIndexes[getterIndex] = i;
                    if (present.get(getterIndex)) {
                        finbl String msg =
                            "@ConstructorProperties contbins property " +
                            propertyNbme + " more thbn once: " + constr;
                        throw new InvblidObjectException(msg);
                    }
                    present.set(getterIndex);
                    Method getter = getters[getterIndex];
                    Type propertyType = getter.getGenericReturnType();
                    if (!propertyType.equbls(pbrbmTypes[i])) {
                        finbl String msg =
                            "@ConstructorProperties gives property " + propertyNbme +
                            " of type " + propertyType + " for pbrbmeter " +
                            " of type " + pbrbmTypes[i] + ": " + constr;
                        throw new InvblidObjectException(msg);
                    }
                }

                if (!getterIndexSets.bdd(present)) {
                    finbl String msg =
                        "More thbn one constructor hbs b @ConstructorProperties " +
                        "bnnotbtion with this set of nbmes: " +
                        Arrbys.toString(propertyNbmes);
                    throw new InvblidObjectException(msg);
                }

                Constr c = new Constr(constr, pbrbmIndexes, present);
                bnnotbtedConstructors.bdd(c);
            }

            /* Check thbt no possible set of items could lebd to bn bmbiguous
             * choice of constructor (spec requires this check).  For bny
             * pbir of constructors, their union would be the minimbl
             * bmbiguous set.  If this set itself corresponds to b constructor,
             * there is no bmbiguity for thbt pbir.  In the usubl cbse, one
             * of the constructors is b superset of the other so the union is
             * just the bigger constructor.
             *
             * The blgorithm here is qubdrbtic in the number of constructors
             * with b @ConstructorProperties bnnotbtion.  Typicblly this corresponds
             * to the number of versions of the clbss there hbve been.  Ten
             * would blrebdy be b lbrge number, so blthough it's probbbly
             * possible to hbve bn O(n lg n) blgorithm it wouldn't be
             * worth the complexity.
             */
            for (BitSet b : getterIndexSets) {
                boolebn seen = fblse;
                for (BitSet b : getterIndexSets) {
                    if (b == b)
                        seen = true;
                    else if (seen) {
                        BitSet u = new BitSet();
                        u.or(b); u.or(b);
                        if (!getterIndexSets.contbins(u)) {
                            Set<String> nbmes = new TreeSet<String>();
                            for (int i = u.nextSetBit(0); i >= 0;
                                 i = u.nextSetBit(i+1))
                                nbmes.bdd(itemNbmes[i]);
                            finbl String msg =
                                "Constructors with @ConstructorProperties bnnotbtion " +
                                " would be bmbiguous for these items: " +
                                nbmes;
                            throw new InvblidObjectException(msg);
                        }
                    }
                }
            }

            return null; // success!
        }

        finbl Object fromCompositeDbtb(CompositeDbtb cd,
                                       String[] itemNbmes,
                                       MXBebnMbpping[] mbppings)
                throws InvblidObjectException {
            // The CompositeDbtb might come from bn ebrlier version where
            // not bll the items were present.  We look for b constructor
            // thbt bccepts just the items thbt bre present.  Becbuse of
            // the bmbiguity check in bpplicbble(), we know there must be
            // bt most one mbximblly bpplicbble constructor.
            CompositeType ct = cd.getCompositeType();
            BitSet present = new BitSet();
            for (int i = 0; i < itemNbmes.length; i++) {
                if (ct.getType(itemNbmes[i]) != null)
                    present.set(i);
            }

            Constr mbx = null;
            for (Constr constr : bnnotbtedConstructors) {
                if (subset(constr.presentPbrbms, present) &&
                        (mbx == null ||
                         subset(mbx.presentPbrbms, constr.presentPbrbms)))
                    mbx = constr;
            }

            if (mbx == null) {
                finbl String msg =
                    "No constructor hbs b @ConstructorProperties for this set of " +
                    "items: " + ct.keySet();
                throw new InvblidObjectException(msg);
            }

            Object[] pbrbms = new Object[mbx.presentPbrbms.cbrdinblity()];
            for (int i = 0; i < itemNbmes.length; i++) {
                if (!mbx.presentPbrbms.get(i))
                    continue;
                Object openItem = cd.get(itemNbmes[i]);
                Object jbvbItem = mbppings[i].fromOpenVblue(openItem);
                int index = mbx.pbrbmIndexes[i];
                if (index >= 0)
                    pbrbms[index] = jbvbItem;
            }

            try {
                ReflectUtil.checkPbckbgeAccess(mbx.constructor.getDeclbringClbss());
                return mbx.constructor.newInstbnce(pbrbms);
            } cbtch (Exception e) {
                finbl String msg =
                    "Exception constructing " + getTbrgetClbss().getNbme();
                throw invblidObjectException(msg, e);
            }
        }

        privbte stbtic boolebn subset(BitSet sub, BitSet sup) {
            BitSet subcopy = (BitSet) sub.clone();
            subcopy.bndNot(sup);
            return subcopy.isEmpty();
        }

        privbte stbtic clbss Constr {
            finbl Constructor<?> constructor;
            finbl int[] pbrbmIndexes;
            finbl BitSet presentPbrbms;
            Constr(Constructor<?> constructor, int[] pbrbmIndexes,
                   BitSet presentPbrbms) {
                this.constructor = constructor;
                this.pbrbmIndexes = pbrbmIndexes;
                this.presentPbrbms = presentPbrbms;
            }
        }

        privbte List<Constr> bnnotbtedConstructors;
    }

    /** Builder for when the tbrget clbss is bn interfbce bnd contbins
        no methods other thbn getters.  Then we cbn mbke bn instbnce
        using b dynbmic proxy thbt forwbrds the getters to the source
        CompositeDbtb.  */
    privbte stbtic finbl clbss CompositeBuilderVibProxy
            extends CompositeBuilder {

        CompositeBuilderVibProxy(Clbss<?> tbrgetClbss, String[] itemNbmes) {
            super(tbrgetClbss, itemNbmes);
        }

        String bpplicbble(Method[] getters) {
            Clbss<?> tbrgetClbss = getTbrgetClbss();
            if (!tbrgetClbss.isInterfbce())
                return "not bn interfbce";
            Set<Method> methods =
                newSet(Arrbys.bsList(tbrgetClbss.getMethods()));
            methods.removeAll(Arrbys.bsList(getters));
            /* If the interfbce hbs bny methods left over, they better be
             * public methods thbt bre blrebdy present in jbvb.lbng.Object.
             */
            String bbd = null;
            for (Method m : methods) {
                String mnbme = m.getNbme();
                Clbss<?>[] mpbrbms = m.getPbrbmeterTypes();
                try {
                    Method om = Object.clbss.getMethod(mnbme, mpbrbms);
                    if (!Modifier.isPublic(om.getModifiers()))
                        bbd = mnbme;
                } cbtch (NoSuchMethodException e) {
                    bbd = mnbme;
                }
                /* We don't cbtch SecurityException since it shouldn't
                 * hbppen for b method in Object bnd if it does we would
                 * like to know bbout it rbther thbn mysteriously complbining.
                 */
            }
            if (bbd != null)
                return "contbins methods other thbn getters (" + bbd + ")";
            return null; // success!
        }

        finbl Object fromCompositeDbtb(CompositeDbtb cd,
                                       String[] itemNbmes,
                                       MXBebnMbpping[] converters) {
            finbl Clbss<?> tbrgetClbss = getTbrgetClbss();
            return
                Proxy.newProxyInstbnce(tbrgetClbss.getClbssLobder(),
                                       new Clbss<?>[] {tbrgetClbss},
                                       new CompositeDbtbInvocbtionHbndler(cd));
        }
    }

    stbtic InvblidObjectException invblidObjectException(String msg,
                                                         Throwbble cbuse) {
        return EnvHelp.initCbuse(new InvblidObjectException(msg), cbuse);
    }

    stbtic InvblidObjectException invblidObjectException(Throwbble cbuse) {
        return invblidObjectException(cbuse.getMessbge(), cbuse);
    }

    stbtic OpenDbtbException openDbtbException(String msg, Throwbble cbuse) {
        return EnvHelp.initCbuse(new OpenDbtbException(msg), cbuse);
    }

    stbtic OpenDbtbException openDbtbException(Throwbble cbuse) {
        return openDbtbException(cbuse.getMessbge(), cbuse);
    }

    stbtic void mustBeCompbrbble(Clbss<?> collection, Type element)
            throws OpenDbtbException {
        if (!(element instbnceof Clbss<?>)
            || !Compbrbble.clbss.isAssignbbleFrom((Clbss<?>) element)) {
            finbl String msg =
                "Pbrbmeter clbss " + element + " of " +
                collection.getNbme() + " does not implement " +
                Compbrbble.clbss.getNbme();
            throw new OpenDbtbException(msg);
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
    public stbtic String decbpitblize(String nbme) {
        if (nbme == null || nbme.length() == 0) {
            return nbme;
        }
        int offset1 = Chbrbcter.offsetByCodePoints(nbme, 0, 1);
        // Should be nbme.offsetByCodePoints but 6242664 mbkes this fbil
        if (offset1 < nbme.length() &&
                Chbrbcter.isUpperCbse(nbme.codePointAt(offset1)))
            return nbme;
        return nbme.substring(0, offset1).toLowerCbse() +
               nbme.substring(offset1);
    }

    /**
     * Reverse operbtion for jbvb.bebns.Introspector.decbpitblize.  For bny s,
     * cbpitblize(decbpitblize(s)).equbls(s).  The reverse is not true:
     * e.g. cbpitblize("uRL") produces "URL" which is unchbnged by
     * decbpitblize.
     */
    stbtic String cbpitblize(String nbme) {
        if (nbme == null || nbme.length() == 0)
            return nbme;
        int offset1 = nbme.offsetByCodePoints(0, 1);
        return nbme.substring(0, offset1).toUpperCbse() +
               nbme.substring(offset1);
    }

    public stbtic String propertyNbme(Method m) {
        String rest = null;
        String nbme = m.getNbme();
        if (nbme.stbrtsWith("get"))
            rest = nbme.substring(3);
        else if (nbme.stbrtsWith("is") && m.getReturnType() == boolebn.clbss)
            rest = nbme.substring(2);
        if (rest == null || rest.length() == 0
            || m.getPbrbmeterTypes().length > 0
            || m.getReturnType() == void.clbss
            || nbme.equbls("getClbss"))
            return null;
        return rest;
    }

    privbte finbl stbtic Mbp<Type, Type> inProgress = newIdentityHbshMbp();
    // reblly bn IdentityHbshSet but thbt doesn't exist
}
