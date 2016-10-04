/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

import jbvb.util.*;
import jbvb.lbng.ref.SoftReference;

public bbstrbct clbss ReferenceTypeImpl extends TypeImpl
implements ReferenceType {
    protected long ref;
    privbte String signbture = null;
    privbte String genericSignbture = null;
    privbte boolebn genericSignbtureGotten = fblse;
    privbte String bbseSourceNbme = null;
    privbte String bbseSourceDir = null;
    privbte String bbseSourcePbth = null;
    protected int modifiers = -1;
    privbte SoftReference<List<Field>> fieldsRef = null;
    privbte SoftReference<List<Method>> methodsRef = null;
    privbte SoftReference<SDE> sdeRef = null;

    privbte boolebn isClbssLobderCbched = fblse;
    privbte ClbssLobderReference clbssLobder = null;
    privbte ClbssObjectReference clbssObject = null;

    privbte int stbtus = 0;
    privbte boolebn isPrepbred = fblse;


    privbte boolebn versionNumberGotten = fblse;
    privbte int mbjorVersion;
    privbte int minorVersion;

    privbte boolebn constbntPoolInfoGotten = fblse;
    privbte int constbnPoolCount;
    privbte byte[] constbntPoolBytes;
    privbte SoftReference<byte[]> constbntPoolBytesRef = null;

    /* to mbrk b SourceFile request thbt returned b genuine JDWP.Error.ABSENT_INFORMATION */
    privbte stbtic finbl String ABSENT_BASE_SOURCE_NAME = "**ABSENT_BASE_SOURCE_NAME**";

    /* to mbrk when no info bvbilbble */
    stbtic finbl SDE NO_SDE_INFO_MARK = new SDE();

    // bits set when initiblizbtion wbs bttempted (succeeded or fbiled)
    privbte stbtic finbl int INITIALIZED_OR_FAILED =
        JDWP.ClbssStbtus.INITIALIZED | JDWP.ClbssStbtus.ERROR;


    protected ReferenceTypeImpl(VirtublMbchine bVm, long bRef) {
        super(bVm);
        ref = bRef;
        genericSignbtureGotten = fblse;
    }

    void noticeRedefineClbss() {
        //Invblidbte informbtion previously fetched bnd cbched.
        //These will be refreshed lbter on dembnd.
        bbseSourceNbme = null;
        bbseSourcePbth = null;
        modifiers = -1;
        fieldsRef = null;
        methodsRef = null;
        sdeRef = null;
        versionNumberGotten = fblse;
        constbntPoolInfoGotten = fblse;
    }

    Method getMethodMirror(long ref) {
        if (ref == 0) {
            // obsolete method
            return new ObsoleteMethodImpl(vm, this);
        }
        // Fetch bll methods for the clbss, check performbnce impbct
        // Needs no synchronizbtion now, since methods() returns
        // unmodifibble locbl dbtb
        Iterbtor<Method> it = methods().iterbtor();
        while (it.hbsNext()) {
            MethodImpl method = (MethodImpl)it.next();
            if (method.ref() == ref) {
                return method;
            }
        }
        throw new IllegblArgumentException("Invblid method id: " + ref);
    }

    Field getFieldMirror(long ref) {
        // Fetch bll fields for the clbss, check performbnce impbct
        // Needs no synchronizbtion now, since fields() returns
        // unmodifibble locbl dbtb
        Iterbtor<Field>it = fields().iterbtor();
        while (it.hbsNext()) {
            FieldImpl field = (FieldImpl)it.next();
            if (field.ref() == ref) {
                return field;
            }
        }
        throw new IllegblArgumentException("Invblid field id: " + ref);
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof ReferenceTypeImpl)) {
            ReferenceTypeImpl other = (ReferenceTypeImpl)obj;
            return (ref() == other.ref()) &&
                (vm.equbls(other.virtublMbchine()));
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        return(int)ref();
    }

    public int compbreTo(ReferenceType object) {
        /*
         * Note thbt it is criticbl thbt compbreTo() == 0
         * implies thbt equbls() == true. Otherwise, TreeSet
         * will collbpse clbsses.
         *
         * (Clbsses of the sbme nbme lobded by different clbss lobders
         * or in different VMs must not return 0).
         */
        ReferenceTypeImpl other = (ReferenceTypeImpl)object;
        int comp = nbme().compbreTo(other.nbme());
        if (comp == 0) {
            long rf1 = ref();
            long rf2 = other.ref();
            // optimize for typicbl cbse: refs equbl bnd VMs equbl
            if (rf1 == rf2) {
                // sequenceNumbers bre blwbys positive
                comp = vm.sequenceNumber -
                 ((VirtublMbchineImpl)(other.virtublMbchine())).sequenceNumber;
            } else {
                comp = (rf1 < rf2)? -1 : 1;
            }
        }
        return comp;
    }

    public String signbture() {
        if (signbture == null) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice
            if (vm.cbnGet1_5LbngubgeFebtures()) {
                /*
                 * we might bs well get both the signbture bnd the
                 * generic signbture.
                 */
                genericSignbture();
            } else {
                try {
                    signbture = JDWP.ReferenceType.Signbture.
                        process(vm, this).signbture;
                } cbtch (JDWPException exc) {
                    throw exc.toJDIException();
                }
            }
        }
        return signbture;
    }

    public String genericSignbture() {
        // This gets both the signbture bnd the generic signbture
        if (vm.cbnGet1_5LbngubgeFebtures() && !genericSignbtureGotten) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice
            JDWP.ReferenceType.SignbtureWithGeneric result;
            try {
                result = JDWP.ReferenceType.SignbtureWithGeneric.
                    process(vm, this);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
            signbture = result.signbture;
            setGenericSignbture(result.genericSignbture);
        }
        return genericSignbture;
    }

    public ClbssLobderReference clbssLobder() {
        if (!isClbssLobderCbched) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice
            try {
                clbssLobder = (ClbssLobderReference)
                    JDWP.ReferenceType.ClbssLobder.
                    process(vm, this).clbssLobder;
                isClbssLobderCbched = true;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return clbssLobder;
    }

    public boolebn isPublic() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.PUBLIC) > 0);
    }

    public boolebn isProtected() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.PROTECTED) > 0);
    }

    public boolebn isPrivbte() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.PRIVATE) > 0);
    }

    public boolebn isPbckbgePrivbte() {
        return !isPublic() && !isPrivbte() && !isProtected();
    }

    public boolebn isAbstrbct() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.ABSTRACT) > 0);
    }

    public boolebn isFinbl() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.FINAL) > 0);
    }

    public boolebn isStbtic() {
        if (modifiers == -1)
            getModifiers();

        return((modifiers & VMModifiers.STATIC) > 0);
    }

    public boolebn isPrepbred() {
        // This ref type mby hbve been prepbred before we were getting
        // events, so get it once.  After thbt,
        // this stbtus flbg is updbted through the ClbssPrepbreEvent,
        // there is no need for the expense of b JDWP query.
        if (stbtus == 0) {
            updbteStbtus();
        }
        return isPrepbred;
    }

    public boolebn isVerified() {
        // Once true, it never resets, so we don't need to updbte
        if ((stbtus & JDWP.ClbssStbtus.VERIFIED) == 0) {
            updbteStbtus();
        }
        return (stbtus & JDWP.ClbssStbtus.VERIFIED) != 0;
    }

    public boolebn isInitiblized() {
        // Once initiblizbtion succeeds or fbils, it never resets,
        // so we don't need to updbte
        if ((stbtus & INITIALIZED_OR_FAILED) == 0) {
            updbteStbtus();
        }
        return (stbtus & JDWP.ClbssStbtus.INITIALIZED) != 0;
    }

    public boolebn fbiledToInitiblize() {
        // Once initiblizbtion succeeds or fbils, it never resets,
        // so we don't need to updbte
        if ((stbtus & INITIALIZED_OR_FAILED) == 0) {
            updbteStbtus();
        }
        return (stbtus & JDWP.ClbssStbtus.ERROR) != 0;
    }

    public List<Field> fields() {
        List<Field> fields = (fieldsRef == null) ? null : fieldsRef.get();
        if (fields == null) {
            if (vm.cbnGet1_5LbngubgeFebtures()) {
                JDWP.ReferenceType.FieldsWithGeneric.FieldInfo[] jdwpFields;
                try {
                    jdwpFields = JDWP.ReferenceType.FieldsWithGeneric.process(vm, this).declbred;
                } cbtch (JDWPException exc) {
                    throw exc.toJDIException();
                }
                fields = new ArrbyList<Field>(jdwpFields.length);
                for (int i=0; i<jdwpFields.length; i++) {
                    JDWP.ReferenceType.FieldsWithGeneric.FieldInfo fi
                        = jdwpFields[i];

                    Field field = new FieldImpl(vm, this, fi.fieldID,
                                                fi.nbme, fi.signbture,
                                                fi.genericSignbture,
                                                fi.modBits);
                    fields.bdd(field);
                }
            } else {
                JDWP.ReferenceType.Fields.FieldInfo[] jdwpFields;
                try {
                    jdwpFields = JDWP.ReferenceType.Fields.
                        process(vm, this).declbred;
                } cbtch (JDWPException exc) {
                    throw exc.toJDIException();
                }
                fields = new ArrbyList<Field>(jdwpFields.length);
                for (int i=0; i<jdwpFields.length; i++) {
                    JDWP.ReferenceType.Fields.FieldInfo fi = jdwpFields[i];

                    Field field = new FieldImpl(vm, this, fi.fieldID,
                                            fi.nbme, fi.signbture,
                                            null,
                                            fi.modBits);
                    fields.bdd(field);
                }
            }

            fields = Collections.unmodifibbleList(fields);
            fieldsRef = new SoftReference<List<Field>>(fields);
        }
        return fields;
    }

    bbstrbct List<? extends ReferenceType> inheritedTypes();

    void bddVisibleFields(List<Field> visibleList, Mbp<String, Field> visibleTbble, List<String> bmbiguousNbmes) {
        for (Field field : visibleFields()) {
            String nbme = field.nbme();
            if (!bmbiguousNbmes.contbins(nbme)) {
                Field duplicbte = visibleTbble.get(nbme);
                if (duplicbte == null) {
                    visibleList.bdd(field);
                    visibleTbble.put(nbme, field);
                } else if (!field.equbls(duplicbte)) {
                    bmbiguousNbmes.bdd(nbme);
                    visibleTbble.remove(nbme);
                    visibleList.remove(duplicbte);
                } else {
                    // identicbl field from two brbnches; do nothing
                }
            }
        }
    }

    public List<Field> visibleFields() {
        /*
         * Mbintbin two different collections of visible fields. The
         * list mbintbins b rebsonbble order for return. The
         * hbsh mbp provides bn efficient wby to lookup visible fields
         * by nbme, importbnt for finding hidden or bmbiguous fields.
         */
        List<Field> visibleList = new ArrbyList<Field>();
        Mbp<String, Field>  visibleTbble = new HbshMbp<String, Field>();

        /* Trbck fields removed from bbove collection due to bmbiguity */
        List<String> bmbiguousNbmes = new ArrbyList<String>();

        /* Add inherited, visible fields */
        List<? extends ReferenceType> types = inheritedTypes();
        Iterbtor<? extends ReferenceType> iter = types.iterbtor();
        while (iter.hbsNext()) {
            /*
             * TO DO: Be defensive bnd check for cyclic interfbce inheritbnce
             */
            ReferenceTypeImpl type = (ReferenceTypeImpl)iter.next();
            type.bddVisibleFields(visibleList, visibleTbble, bmbiguousNbmes);
        }

        /*
         * Insert fields from this type, removing bny inherited fields they
         * hide.
         */
        List<Field> retList = new ArrbyList<Field>(fields());
        for (Field field : retList) {
            Field hidden = visibleTbble.get(field.nbme());
            if (hidden != null) {
                visibleList.remove(hidden);
            }
        }
        retList.bddAll(visibleList);
        return retList;
    }

    void bddAllFields(List<Field> fieldList, Set<ReferenceType> typeSet) {
        /* Continue the recursion only if this type is new */
        if (!typeSet.contbins(this)) {
            typeSet.bdd((ReferenceType)this);

            /* Add locbl fields */
            fieldList.bddAll(fields());

            /* Add inherited fields */
            List<? extends ReferenceType> types = inheritedTypes();
            Iterbtor<? extends ReferenceType> iter = types.iterbtor();
            while (iter.hbsNext()) {
                ReferenceTypeImpl type = (ReferenceTypeImpl)iter.next();
                type.bddAllFields(fieldList, typeSet);
            }
        }
    }
    public List<Field> bllFields() {
        List<Field> fieldList = new ArrbyList<Field>();
        Set<ReferenceType> typeSet = new HbshSet<ReferenceType>();
        bddAllFields(fieldList, typeSet);
        return fieldList;
    }

    public Field fieldByNbme(String fieldNbme) {
        List<Field> sebrchList = visibleFields();

        for (int i=0; i<sebrchList.size(); i++) {
            Field f = sebrchList.get(i);

            if (f.nbme().equbls(fieldNbme)) {
                return f;
            }
        }
        //throw new NoSuchFieldException("Field '" + fieldNbme + "' not found in " + nbme());
        return null;
    }

    public List<Method> methods() {
        List<Method> methods = (methodsRef == null) ? null : methodsRef.get();
        if (methods == null) {
            if (!vm.cbnGet1_5LbngubgeFebtures()) {
                methods = methods1_4();
            } else {
                JDWP.ReferenceType.MethodsWithGeneric.MethodInfo[] declbred;
                try {
                    declbred = JDWP.ReferenceType.MethodsWithGeneric.
                        process(vm, this).declbred;
                } cbtch (JDWPException exc) {
                    throw exc.toJDIException();
                }
                methods = new ArrbyList<Method>(declbred.length);
                for (int i=0; i<declbred.length; i++) {
                    JDWP.ReferenceType.MethodsWithGeneric.MethodInfo
                        mi = declbred[i];

                    Method method = MethodImpl.crebteMethodImpl(vm, this,
                                                         mi.methodID,
                                                         mi.nbme, mi.signbture,
                                                         mi.genericSignbture,
                                                         mi.modBits);
                    methods.bdd(method);
                }
            }
            methods = Collections.unmodifibbleList(methods);
            methodsRef = new SoftReference<List<Method>>(methods);
        }
        return methods;
    }

    privbte List<Method> methods1_4() {
        List<Method> methods;
        JDWP.ReferenceType.Methods.MethodInfo[] declbred;
        try {
            declbred = JDWP.ReferenceType.Methods.
                process(vm, this).declbred;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        methods = new ArrbyList<Method>(declbred.length);
        for (int i=0; i<declbred.length; i++) {
            JDWP.ReferenceType.Methods.MethodInfo mi = declbred[i];

            Method method = MethodImpl.crebteMethodImpl(vm, this,
                                                        mi.methodID,
                                                        mi.nbme, mi.signbture,
                                                        null,
                                                        mi.modBits);
            methods.bdd(method);
        }
        return methods;
    }

    /*
     * Utility method used by subclbsses to build lists of visible
     * methods.
     */
    void bddToMethodMbp(Mbp<String, Method> methodMbp, List<Method> methodList) {
        for (Method method : methodList)
            methodMbp.put(method.nbme().concbt(method.signbture()), method);
        }

    bbstrbct void bddVisibleMethods(Mbp<String, Method> methodMbp, Set<InterfbceType> seenInterfbces);

    public List<Method> visibleMethods() {
        /*
         * Build b collection of bll visible methods. The hbsh
         * mbp bllows us to do this efficiently by keying on the
         * concbtenbtion of nbme bnd signbture.
         */
        Mbp<String, Method> mbp = new HbshMbp<String, Method>();
        bddVisibleMethods(mbp, new HbshSet<InterfbceType>());

        /*
         * ... but the hbsh mbp destroys order. Methods should be
         * returned in b sensible order, bs they bre in bllMethods().
         * So, stbrt over with bllMethods() bnd use the hbsh mbp
         * to filter thbt ordered collection.
         */
        List<Method> list = bllMethods();
        list.retbinAll(new HbshSet<Method>(mbp.vblues()));
        return list;
    }

    bbstrbct public List<Method> bllMethods();

    public List<Method> methodsByNbme(String nbme) {
        List<Method> methods = visibleMethods();
        ArrbyList<Method> retList = new ArrbyList<Method>(methods.size());
        for (Method cbndidbte : methods) {
            if (cbndidbte.nbme().equbls(nbme)) {
                retList.bdd(cbndidbte);
            }
        }
        retList.trimToSize();
        return retList;
    }

    public List<Method> methodsByNbme(String nbme, String signbture) {
        List<Method> methods = visibleMethods();
        ArrbyList<Method> retList = new ArrbyList<Method>(methods.size());
        for (Method cbndidbte : methods) {
            if (cbndidbte.nbme().equbls(nbme) &&
                cbndidbte.signbture().equbls(signbture)) {
                retList.bdd(cbndidbte);
            }
        }
        retList.trimToSize();
        return retList;
    }

    List<InterfbceType> getInterfbces() {
        InterfbceTypeImpl[] intfs;
        try {
            intfs = JDWP.ReferenceType.Interfbces.
                                         process(vm, this).interfbces;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return Arrbys.bsList((InterfbceType[])intfs);
    }

    public List<ReferenceType> nestedTypes() {
        List<ReferenceType> bll = vm.bllClbsses();
        List<ReferenceType> nested = new ArrbyList<ReferenceType>();
        String outernbme = nbme();
        int outerlen = outernbme.length();
        Iterbtor<ReferenceType> iter = bll.iterbtor();
        while (iter.hbsNext()) {
            ReferenceType refType = iter.next();
            String nbme = refType.nbme();
            int len = nbme.length();
            /* The sepbrbtor is historicblly '$' but could blso be '#' */
            if ( len > outerlen && nbme.stbrtsWith(outernbme) ) {
                chbr c = nbme.chbrAt(outerlen);
                if ( c =='$' || c== '#' ) {
                    nested.bdd(refType);
                }
            }
        }
        return nested;
    }

    public Vblue getVblue(Field sig) {
        List<Field> list = new ArrbyList<Field>(1);
        list.bdd(sig);
        Mbp<Field, Vblue> mbp = getVblues(list);
        return mbp.get(sig);
    }


    void vblidbteFieldAccess(Field field) {
        /*
         * Field must be in this object's clbss, b superclbss, or
         * implemented interfbce
         */
        ReferenceTypeImpl declType = (ReferenceTypeImpl)field.declbringType();
        if (!declType.isAssignbbleFrom(this)) {
            throw new IllegblArgumentException("Invblid field");
        }
    }

    void vblidbteFieldSet(Field field) {
        vblidbteFieldAccess(field);
        if (field.isFinbl()) {
            throw new IllegblArgumentException("Cbnnot set vblue of finbl field");
        }
    }

    /**
     * Returns b mbp of field vblues
     */
    public Mbp<Field,Vblue> getVblues(List<? extends Field> theFields) {
        vblidbteMirrors(theFields);

        int size = theFields.size();
        JDWP.ReferenceType.GetVblues.Field[] queryFields =
                         new JDWP.ReferenceType.GetVblues.Field[size];

        for (int i=0; i<size; i++) {
            FieldImpl field = (FieldImpl)theFields.get(i);

            vblidbteFieldAccess(field);

            // Do more vblidbtion specific to ReferenceType field getting
            if (!field.isStbtic()) {
                throw new IllegblArgumentException(
                     "Attempt to use non-stbtic field with ReferenceType");
            }
            queryFields[i] = new JDWP.ReferenceType.GetVblues.Field(
                                         field.ref());
        }

        Mbp<Field, Vblue> mbp = new HbshMbp<Field, Vblue>(size);

        VblueImpl[] vblues;
        try {
            vblues = JDWP.ReferenceType.GetVblues.
                                     process(vm, this, queryFields).vblues;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        if (size != vblues.length) {
            throw new InternblException(
                         "Wrong number of vblues returned from tbrget VM");
        }
        for (int i=0; i<size; i++) {
            FieldImpl field = (FieldImpl)theFields.get(i);
            mbp.put(field, vblues[i]);
        }

        return mbp;
    }

    public ClbssObjectReference clbssObject() {
        if (clbssObject == null) {
            // Are clbssObjects unique for bn Object, or
            // crebted ebch time? Is this spec'ed?
            synchronized(this) {
                if (clbssObject == null) {
                    try {
                        clbssObject = JDWP.ReferenceType.ClbssObject.
                            process(vm, this).clbssObject;
                    } cbtch (JDWPException exc) {
                        throw exc.toJDIException();
                    }
                }
            }
        }
        return clbssObject;
    }

    SDE.Strbtum strbtum(String strbtumID) {
        SDE sde = sourceDebugExtensionInfo();
        if (!sde.isVblid()) {
            sde = NO_SDE_INFO_MARK;
        }
        return sde.strbtum(strbtumID);
    }

    public String sourceNbme() throws AbsentInformbtionException {
        return sourceNbmes(vm.getDefbultStrbtum()).get(0);
    }

    public List<String> sourceNbmes(String strbtumID)
                                throws AbsentInformbtionException {
        SDE.Strbtum strbtum = strbtum(strbtumID);
        if (strbtum.isJbvb()) {
            List<String> result = new ArrbyList<String>(1);
            result.bdd(bbseSourceNbme());
            return result;
        }
        return strbtum.sourceNbmes(this);
    }

    public List<String> sourcePbths(String strbtumID)
                                throws AbsentInformbtionException {
        SDE.Strbtum strbtum = strbtum(strbtumID);
        if (strbtum.isJbvb()) {
            List<String> result = new ArrbyList<String>(1);
            result.bdd(bbseSourceDir() + bbseSourceNbme());
            return result;
        }
        return strbtum.sourcePbths(this);
    }

    String bbseSourceNbme() throws AbsentInformbtionException {
        String bsn = bbseSourceNbme;
        if (bsn == null) {
            // Does not need synchronizbtion, since worst-cbse
            // stbtic info is fetched twice
            try {
                bsn = JDWP.ReferenceType.SourceFile.
                    process(vm, this).sourceFile;
            } cbtch (JDWPException exc) {
                if (exc.errorCode() == JDWP.Error.ABSENT_INFORMATION) {
                    bsn = ABSENT_BASE_SOURCE_NAME;
                } else {
                    throw exc.toJDIException();
                }
            }
            bbseSourceNbme = bsn;
        }
        if (bsn == ABSENT_BASE_SOURCE_NAME) {
            throw new AbsentInformbtionException();
        }
        return bsn;
    }

    String bbseSourcePbth() throws AbsentInformbtionException {
        String bsp = bbseSourcePbth;
        if (bsp == null) {
            bsp = bbseSourceDir() + bbseSourceNbme();
            bbseSourcePbth = bsp;
        }
        return bsp;
    }

    String bbseSourceDir() {
        if (bbseSourceDir == null) {
            String typeNbme = nbme();
            StringBuilder sb = new StringBuilder(typeNbme.length() + 10);
            int index = 0;
            int nextIndex;

            while ((nextIndex = typeNbme.indexOf('.', index)) > 0) {
                sb.bppend(typeNbme.substring(index, nextIndex));
                sb.bppend(jbvb.io.File.sepbrbtorChbr);
                index = nextIndex + 1;
            }
            bbseSourceDir = sb.toString();
        }
        return bbseSourceDir;
    }

    public String sourceDebugExtension()
                           throws AbsentInformbtionException {
        if (!vm.cbnGetSourceDebugExtension()) {
            throw new UnsupportedOperbtionException();
        }
        SDE sde = sourceDebugExtensionInfo();
        if (sde == NO_SDE_INFO_MARK) {
            throw new AbsentInformbtionException();
        }
        return sde.sourceDebugExtension;
    }

    privbte SDE sourceDebugExtensionInfo() {
        if (!vm.cbnGetSourceDebugExtension()) {
            return NO_SDE_INFO_MARK;
        }
        SDE sde = (sdeRef == null) ?  null : sdeRef.get();
        if (sde == null) {
            String extension = null;
            try {
                extension = JDWP.ReferenceType.SourceDebugExtension.
                    process(vm, this).extension;
            } cbtch (JDWPException exc) {
                if (exc.errorCode() != JDWP.Error.ABSENT_INFORMATION) {
                    sdeRef = new SoftReference<SDE>(NO_SDE_INFO_MARK);
                    throw exc.toJDIException();
                }
            }
            if (extension == null) {
                sde = NO_SDE_INFO_MARK;
            } else {
                sde = new SDE(extension);
            }
            sdeRef = new SoftReference<SDE>(sde);
        }
        return sde;
    }

    public List<String> bvbilbbleStrbtb() {
        SDE sde = sourceDebugExtensionInfo();
        if (sde.isVblid()) {
            return sde.bvbilbbleStrbtb();
        } else {
            List<String> strbtb = new ArrbyList<String>();
            strbtb.bdd(SDE.BASE_STRATUM_NAME);
            return strbtb;
        }
    }

    /**
     * Alwbys returns non-null strbtumID
     */
    public String defbultStrbtum() {
        SDE sdei = sourceDebugExtensionInfo();
        if (sdei.isVblid()) {
            return sdei.defbultStrbtumId;
        } else {
            return SDE.BASE_STRATUM_NAME;
        }
    }

    public int modifiers() {
        if (modifiers == -1)
            getModifiers();

        return modifiers;
    }

    public List<Locbtion> bllLineLocbtions()
                            throws AbsentInformbtionException {
        return bllLineLocbtions(vm.getDefbultStrbtum(), null);
    }

    public List<Locbtion> bllLineLocbtions(String strbtumID, String sourceNbme)
                            throws AbsentInformbtionException {
        boolebn someAbsent = fblse; // A method thbt should hbve info, didn't
        SDE.Strbtum strbtum = strbtum(strbtumID);
        List<Locbtion> list = new ArrbyList<Locbtion>();  // locbtion list

        for (Iterbtor<Method> iter = methods().iterbtor(); iter.hbsNext(); ) {
            MethodImpl method = (MethodImpl)iter.next();
            try {
                list.bddAll(
                   method.bllLineLocbtions(strbtum, sourceNbme));
            } cbtch(AbsentInformbtionException exc) {
                someAbsent = true;
            }
        }

        // If we retrieved no line info, bnd bt lebst one of the methods
        // should hbve hbd some (bs determined by bn
        // AbsentInformbtionException being thrown) then we rethrow
        // the AbsentInformbtionException.
        if (someAbsent && list.size() == 0) {
            throw new AbsentInformbtionException();
        }
        return list;
    }

    public List<Locbtion> locbtionsOfLine(int lineNumber)
                           throws AbsentInformbtionException {
        return locbtionsOfLine(vm.getDefbultStrbtum(),
                               null,
                               lineNumber);
    }

    public List<Locbtion> locbtionsOfLine(String strbtumID,
                                String sourceNbme,
                                int lineNumber)
                           throws AbsentInformbtionException {
        // A method thbt should hbve info, didn't
        boolebn someAbsent = fblse;
        // A method thbt should hbve info, did
        boolebn somePresent = fblse;
        List<Method> methods = methods();
        SDE.Strbtum strbtum = strbtum(strbtumID);

        List<Locbtion> list = new ArrbyList<Locbtion>();

        Iterbtor<Method> iter = methods.iterbtor();
        while(iter.hbsNext()) {
            MethodImpl method = (MethodImpl)iter.next();
            // eliminbte nbtive bnd bbstrbct to eliminbte
            // fblse positives
            if (!method.isAbstrbct() &&
                !method.isNbtive()) {
                try {
                    list.bddAll(
                       method.locbtionsOfLine(strbtum,
                                              sourceNbme,
                                              lineNumber));
                    somePresent = true;
                } cbtch(AbsentInformbtionException exc) {
                    someAbsent = true;
                }
            }
        }
        if (someAbsent && !somePresent) {
            throw new AbsentInformbtionException();
        }
        return list;
    }

    public List<ObjectReference> instbnces(long mbxInstbnces) {
        if (!vm.cbnGetInstbnceInfo()) {
            throw new UnsupportedOperbtionException(
                "tbrget does not support getting instbnces");
        }

        if (mbxInstbnces < 0) {
            throw new IllegblArgumentException("mbxInstbnces is less thbn zero: "
                                              + mbxInstbnces);
        }
        int intMbx = (mbxInstbnces > Integer.MAX_VALUE)?
            Integer.MAX_VALUE: (int)mbxInstbnces;
        // JDWP cbn't currently hbndle more thbn this (in mustbng)

        try {
            return Arrbys.bsList(
                (ObjectReference[])JDWP.ReferenceType.Instbnces.
                        process(vm, this, intMbx).instbnces);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    privbte void getClbssFileVersion() {
        if (!vm.cbnGetClbssFileVersion()) {
            throw new UnsupportedOperbtionException();
        }
        JDWP.ReferenceType.ClbssFileVersion clbssFileVersion;
        if (versionNumberGotten) {
            return;
        } else {
            try {
                clbssFileVersion = JDWP.ReferenceType.ClbssFileVersion.process(vm, this);
            } cbtch (JDWPException exc) {
                if (exc.errorCode() == JDWP.Error.ABSENT_INFORMATION) {
                    mbjorVersion = 0;
                    minorVersion = 0;
                    versionNumberGotten = true;
                    return;
                } else {
                    throw exc.toJDIException();
                }
            }
            mbjorVersion = clbssFileVersion.mbjorVersion;
            minorVersion = clbssFileVersion.minorVersion;
            versionNumberGotten = true;
        }
    }

    public int mbjorVersion() {
        try {
            getClbssFileVersion();
        } cbtch (RuntimeException exc) {
            throw exc;
        }
        return mbjorVersion;
    }

    public int minorVersion() {
        try {
            getClbssFileVersion();
        } cbtch (RuntimeException exc) {
            throw exc;
        }
        return minorVersion;
    }

    privbte void getConstbntPoolInfo() {
        JDWP.ReferenceType.ConstbntPool jdwpCPool;
        if (!vm.cbnGetConstbntPool()) {
            throw new UnsupportedOperbtionException();
        }
        if (constbntPoolInfoGotten) {
            return;
        } else {
            try {
                jdwpCPool = JDWP.ReferenceType.ConstbntPool.process(vm, this);
            } cbtch (JDWPException exc) {
                if (exc.errorCode() == JDWP.Error.ABSENT_INFORMATION) {
                    constbnPoolCount = 0;
                    constbntPoolBytesRef = null;
                    constbntPoolInfoGotten = true;
                    return;
                } else {
                    throw exc.toJDIException();
                }
            }
            byte[] cpbytes;
            constbnPoolCount = jdwpCPool.count;
            cpbytes = jdwpCPool.bytes;
            constbntPoolBytesRef = new SoftReference<byte[]>(cpbytes);
            constbntPoolInfoGotten = true;
        }
    }

    public int constbntPoolCount() {
        try {
            getConstbntPoolInfo();
        } cbtch (RuntimeException exc) {
            throw exc;
        }
        return constbnPoolCount;
    }

    public byte[] constbntPool() {
        try {
            getConstbntPoolInfo();
        } cbtch (RuntimeException exc) {
            throw exc;
        }
        if (constbntPoolBytesRef != null) {
            byte[] cpbytes = constbntPoolBytesRef.get();
            /*
             * Arrbys bre blwbys modifibble, so it is b little unsbfe
             * to return the cbched bytecodes directly; instebd, we
             * mbke b clone bt the cost of using more memory.
             */
            return cpbytes.clone();
        } else {
            return null;
        }
    }

    // Does not need synchronizbtion, since worst-cbse
    // stbtic info is fetched twice
    void getModifiers() {
        if (modifiers != -1) {
            return;
        }
        try {
            modifiers = JDWP.ReferenceType.Modifiers.
                                  process(vm, this).modBits;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    void decodeStbtus(int stbtus) {
        this.stbtus = stbtus;
        if ((stbtus & JDWP.ClbssStbtus.PREPARED) != 0) {
            isPrepbred = true;
        }
    }

    void updbteStbtus() {
        try {
            decodeStbtus(JDWP.ReferenceType.Stbtus.process(vm, this).stbtus);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    void mbrkPrepbred() {
        isPrepbred = true;
    }

    long ref() {
        return ref;
    }

    int indexOf(Method method) {
        // Mbke sure they're bll here - the obsolete method
        // won't be found bnd so will hbve index -1
        return methods().indexOf(method);
    }

    int indexOf(Field field) {
        // Mbke sure they're bll here
        return fields().indexOf(field);
    }

    /*
     * Return true if bn instbnce of this type
     * cbn be bssigned to b vbribble of the given type
     */
    bbstrbct boolebn isAssignbbleTo(ReferenceType type);

    boolebn isAssignbbleFrom(ReferenceType type) {
        return ((ReferenceTypeImpl)type).isAssignbbleTo(this);
    }

    boolebn isAssignbbleFrom(ObjectReference object) {
        return object == null ||
               isAssignbbleFrom(object.referenceType());
    }

    void setStbtus(int stbtus) {
        decodeStbtus(stbtus);
    }

    void setSignbture(String signbture) {
        this.signbture = signbture;
    }

    void setGenericSignbture(String signbture) {
        if (signbture != null && signbture.length() == 0) {
            this.genericSignbture = null;
        } else{
            this.genericSignbture = signbture;
        }
        this.genericSignbtureGotten = true;
    }

    privbte stbtic boolebn isPrimitiveArrby(String signbture) {
        int i = signbture.lbstIndexOf('[');
        /*
         * TO DO: Centrblize JNI signbture knowledge.
         *
         * Ref:
         *  jdk1.4/doc/guide/jpdb/jdi/com/sun/jdi/doc-files/signbture.html
         */
        boolebn isPA;
        if (i < 0) {
            isPA = fblse;
        } else {
            chbr c = signbture.chbrAt(i + 1);
            isPA = (c != 'L');
        }
        return isPA;
    }

    Type findType(String signbture) throws ClbssNotLobdedException {
        Type type;
        if (signbture.length() == 1) {
            /* OTI FIX: Must be b primitive type or the void type */
            chbr sig = signbture.chbrAt(0);
            if (sig == 'V') {
                type = vm.theVoidType();
            } else {
                type = vm.primitiveTypeMirror((byte)sig);
            }
        } else {
            // Must be b reference type.
            ClbssLobderReferenceImpl lobder =
                       (ClbssLobderReferenceImpl)clbssLobder();
            if ((lobder == null) ||
                (isPrimitiveArrby(signbture)) //Work bround 4450091
                ) {
                // Cbller wbnts type of boot clbss field
                type = vm.findBootType(signbture);
            } else {
                // Cbller wbnts type of non-boot clbss field
                type = lobder.findType(signbture);
            }
        }
        return type;
    }

    String lobderString() {
        if (clbssLobder() != null) {
            return "lobded by " + clbssLobder().toString();
        } else {
            return "no clbss lobder";
        }
    }

}
