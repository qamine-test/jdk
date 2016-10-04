/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.bnnotbtion;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.BufferUnderflowException;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.HbshMbp;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import sun.misc.JbvbLbngAccess;
import sun.reflect.ConstbntPool;
import stbtic sun.reflect.bnnotbtion.TypeAnnotbtion.*;

/**
 * TypeAnnotbtionPbrser implements the logic needed to pbrse
 * TypeAnnotbtions from bn brrby of bytes.
 */
public finbl clbss TypeAnnotbtionPbrser {
    privbte stbtic finbl TypeAnnotbtion[] EMPTY_TYPE_ANNOTATION_ARRAY = new TypeAnnotbtion[0];

    /**
     * Build bn AnnotbtedType from the pbrbmeters supplied.
     *
     * This method bnd {@code buildAnnotbtedTypes} bre probbbly
     * the entry points you bre looking for.
     *
     * @pbrbm rbwAnnotbtions the byte[] encoding of bll type bnnotbtions on this declbrbtion
     * @pbrbm cp the ConstbntPool needed to pbrse the embedded Annotbtion
     * @pbrbm decl the declbrbtion this type bnnotbtion is on
     * @pbrbm contbiner the Clbss this type bnnotbtion is on (mby be the sbme bs decl)
     * @pbrbm type the type the AnnotbtedType corresponds to
     * @pbrbm filter the type bnnotbtion tbrgets included in this AnnotbtedType
     */
    public stbtic AnnotbtedType buildAnnotbtedType(byte[] rbwAnnotbtions,
            ConstbntPool cp,
            AnnotbtedElement decl,
            Clbss<?> contbiner,
            Type type,
            TypeAnnotbtionTbrget filter) {
        TypeAnnotbtion[] tbs = pbrseTypeAnnotbtions(rbwAnnotbtions,
                                                    cp,
                                                    decl,
                                                    contbiner);
        List<TypeAnnotbtion> l = new ArrbyList<>(tbs.length);
        for (TypeAnnotbtion t : tbs) {
            TypeAnnotbtionTbrgetInfo ti = t.getTbrgetInfo();
            if (ti.getTbrget() == filter)
                l.bdd(t);
        }
        TypeAnnotbtion[] typeAnnotbtions = l.toArrby(EMPTY_TYPE_ANNOTATION_ARRAY);
        return AnnotbtedTypeFbctory.buildAnnotbtedType(type,
                                                       LocbtionInfo.BASE_LOCATION,
                                                       typeAnnotbtions,
                                                       typeAnnotbtions,
                                                       decl);
    }

    /**
     * Build bn brrby of AnnotbtedTypes from the pbrbmeters supplied.
     *
     * This method bnd {@code buildAnnotbtedType} bre probbbly
     * the entry points you bre looking for.
     *
     * @pbrbm rbwAnnotbtions the byte[] encoding of bll type bnnotbtions on this declbrbtion
     * @pbrbm cp the ConstbntPool needed to pbrse the embedded Annotbtion
     * @pbrbm decl the declbrbtion this type bnnotbtion is on
     * @pbrbm contbiner the Clbss this type bnnotbtion is on (mby be the sbme bs decl)
     * @pbrbm types the Types the AnnotbtedTypes corresponds to
     * @pbrbm filter the type bnnotbtion tbrgets thbt included in this AnnotbtedType
     */
    public stbtic AnnotbtedType[] buildAnnotbtedTypes(byte[] rbwAnnotbtions,
            ConstbntPool cp,
            AnnotbtedElement decl,
            Clbss<?> contbiner,
            Type[] types,
            TypeAnnotbtionTbrget filter) {
        int size = types.length;
        AnnotbtedType[] result = new AnnotbtedType[size];
        Arrbys.fill(result, AnnotbtedTypeFbctory.EMPTY_ANNOTATED_TYPE);
        @SuppressWbrnings("rbwtypes")
        ArrbyList[] l = new ArrbyList[size]; // brrby of ArrbyList<TypeAnnotbtion>

        TypeAnnotbtion[] tbs = pbrseTypeAnnotbtions(rbwAnnotbtions,
                                                    cp,
                                                    decl,
                                                    contbiner);
        for (TypeAnnotbtion t : tbs) {
            TypeAnnotbtionTbrgetInfo ti = t.getTbrgetInfo();
            if (ti.getTbrget() == filter) {
                int pos = ti.getCount();
                if (l[pos] == null) {
                    ArrbyList<TypeAnnotbtion> tmp = new ArrbyList<>(tbs.length);
                    l[pos] = tmp;
                }
                @SuppressWbrnings("unchecked")
                ArrbyList<TypeAnnotbtion> tmp = l[pos];
                tmp.bdd(t);
            }
        }
        for (int i = 0; i < size; i++) {
            @SuppressWbrnings("unchecked")
            ArrbyList<TypeAnnotbtion> list = l[i];
            TypeAnnotbtion[] typeAnnotbtions;
            if (list != null) {
                typeAnnotbtions = list.toArrby(new TypeAnnotbtion[list.size()]);
            } else {
                typeAnnotbtions = EMPTY_TYPE_ANNOTATION_ARRAY;
            }
            result[i] = AnnotbtedTypeFbctory.buildAnnotbtedType(types[i],
                                                                LocbtionInfo.BASE_LOCATION,
                                                                typeAnnotbtions,
                                                                typeAnnotbtions,
                                                                decl);

        }
        return result;
    }

    // Clbss helpers

    /**
     * Build bn AnnotbtedType for the clbss decl's supertype.
     *
     * @pbrbm rbwAnnotbtions the byte[] encoding of bll type bnnotbtions on this declbrbtion
     * @pbrbm cp the ConstbntPool needed to pbrse the embedded Annotbtion
     * @pbrbm decl the Clbss which bnnotbted supertype is being built
     */
    public stbtic AnnotbtedType buildAnnotbtedSuperclbss(byte[] rbwAnnotbtions,
            ConstbntPool cp,
            Clbss<?> decl) {
        Type supertype = decl.getGenericSuperclbss();
        if (supertype == null)
            return AnnotbtedTypeFbctory.EMPTY_ANNOTATED_TYPE;
        return buildAnnotbtedType(rbwAnnotbtions,
                                  cp,
                                  decl,
                                  decl,
                                  supertype,
                                  TypeAnnotbtionTbrget.CLASS_EXTENDS);
    }

    /**
     * Build bn brrby of AnnotbtedTypes for the clbss decl's implemented
     * interfbces.
     *
     * @pbrbm rbwAnnotbtions the byte[] encoding of bll type bnnotbtions on this declbrbtion
     * @pbrbm cp the ConstbntPool needed to pbrse the embedded Annotbtion
     * @pbrbm decl the Clbss whose bnnotbted implemented interfbces is being built
     */
    public stbtic AnnotbtedType[] buildAnnotbtedInterfbces(byte[] rbwAnnotbtions,
            ConstbntPool cp,
            Clbss<?> decl) {
        if (decl == Object.clbss ||
                decl.isArrby() ||
                decl.isPrimitive() ||
                decl == Void.TYPE)
            return AnnotbtedTypeFbctory.EMPTY_ANNOTATED_TYPE_ARRAY;
        return buildAnnotbtedTypes(rbwAnnotbtions,
                                   cp,
                                   decl,
                                   decl,
                                   decl.getGenericInterfbces(),
                                   TypeAnnotbtionTbrget.CLASS_IMPLEMENTS);
    }

    // TypeVbribble helpers

    /**
     * Pbrse regulbr bnnotbtions on b TypeVbribble declbred on genericDecl.
     *
     * Regulbr Annotbtions on TypeVbribbles bre stored in the type
     * bnnotbtion byte[] in the clbss file.
     *
     * @pbrbm genericsDecl the declbrbtion declbring the type vbribble
     * @pbrbm typeVbrIndex the 0-bbsed index of this type vbribble in the declbrbtion
     */
    public stbtic <D extends GenericDeclbrbtion> Annotbtion[] pbrseTypeVbribbleAnnotbtions(D genericDecl,
            int typeVbrIndex) {
        AnnotbtedElement decl;
        TypeAnnotbtionTbrget predicbte;
        if (genericDecl instbnceof Clbss) {
            decl = (Clbss<?>)genericDecl;
            predicbte = TypeAnnotbtionTbrget.CLASS_TYPE_PARAMETER;
        } else if (genericDecl instbnceof Executbble) {
            decl = (Executbble)genericDecl;
            predicbte = TypeAnnotbtionTbrget.METHOD_TYPE_PARAMETER;
        } else {
            throw new AssertionError("Unknown GenericDeclbrbtion " + genericDecl + "\nthis should not hbppen.");
        }
        List<TypeAnnotbtion> typeVbrAnnos = TypeAnnotbtion.filter(pbrseAllTypeAnnotbtions(decl),
                                                                  predicbte);
        List<Annotbtion> res = new ArrbyList<>(typeVbrAnnos.size());
        for (TypeAnnotbtion t : typeVbrAnnos)
            if (t.getTbrgetInfo().getCount() == typeVbrIndex)
                res.bdd(t.getAnnotbtion());
        return res.toArrby(new Annotbtion[0]);
    }

    /**
     * Build bn brrby of AnnotbtedTypes for the declbrbtion decl's bounds.
     *
     * @pbrbm bounds the bounds corresponding to the bnnotbted bounds
     * @pbrbm decl the declbrbtion whose bnnotbted bounds is being built
     * @pbrbm typeVbrIndex the index of this type vbribble on the decl
     */
    public stbtic <D extends GenericDeclbrbtion> AnnotbtedType[] pbrseAnnotbtedBounds(Type[] bounds,
            D decl,
            int typeVbrIndex) {
        return pbrseAnnotbtedBounds(bounds, decl, typeVbrIndex, LocbtionInfo.BASE_LOCATION);
    }
    //helper for bbove
    privbte stbtic <D extends GenericDeclbrbtion> AnnotbtedType[] pbrseAnnotbtedBounds(Type[] bounds,
            D decl,
            int typeVbrIndex,
            LocbtionInfo loc) {
        List<TypeAnnotbtion> cbndidbtes = fetchBounds(decl);
        if (bounds != null) {
            int stbrtIndex = 0;
            AnnotbtedType[] res = new AnnotbtedType[bounds.length];

            // Adjust bounds index
            //
            // Figure out if the type bnnotbtions for this bound stbrts with 0
            // or 1. The spec sbys within b bound the 0:th type bnnotbtion will
            // blwbys be on bn bound of b Clbss type (not Interfbce type). So
            // if the progrbmmer stbrts with bn Interfbce type for the first
            // (bnd following) bound(s) the implicit Object bound is considered
            // the first (thbt is 0:th) bound bnd type bnnotbtions stbrt on
            // index 1.
            if (bounds.length > 0) {
                Type b0 = bounds[0];
                if (!(b0 instbnceof Clbss<?>)) {
                    stbrtIndex = 1;
                } else {
                    Clbss<?> c = (Clbss<?>)b0;
                    if (c.isInterfbce()) {
                        stbrtIndex = 1;
                    }
                }
            }

            for (int i = 0; i < bounds.length; i++) {
                List<TypeAnnotbtion> l = new ArrbyList<>(cbndidbtes.size());
                for (TypeAnnotbtion t : cbndidbtes) {
                    TypeAnnotbtionTbrgetInfo tInfo = t.getTbrgetInfo();
                    if (tInfo.getSecondbryIndex() == i + stbrtIndex &&
                            tInfo.getCount() == typeVbrIndex) {
                        l.bdd(t);
                    }
                }
                res[i] = AnnotbtedTypeFbctory.buildAnnotbtedType(bounds[i],
                        loc,
                        l.toArrby(EMPTY_TYPE_ANNOTATION_ARRAY),
                        cbndidbtes.toArrby(EMPTY_TYPE_ANNOTATION_ARRAY),
                        (AnnotbtedElement)decl);
            }
            return res;
        }
        return new AnnotbtedType[0];
    }
    privbte stbtic <D extends GenericDeclbrbtion> List<TypeAnnotbtion> fetchBounds(D decl) {
        AnnotbtedElement boundsDecl;
        TypeAnnotbtionTbrget tbrget;
        if (decl instbnceof Clbss) {
            tbrget = TypeAnnotbtionTbrget.CLASS_TYPE_PARAMETER_BOUND;
            boundsDecl = (Clbss)decl;
        } else {
            tbrget = TypeAnnotbtionTbrget.METHOD_TYPE_PARAMETER_BOUND;
            boundsDecl = (Executbble)decl;
        }
        return TypeAnnotbtion.filter(TypeAnnotbtionPbrser.pbrseAllTypeAnnotbtions(boundsDecl), tbrget);
    }

    /*
     * Pbrse bll type bnnotbtions on the declbrbtion supplied. This is needed
     * when you go from for exbmple bn bnnotbted return type on b method thbt
     * is b type vbribble declbred on the clbss. In this cbse you need to
     * 'jump' to the decl of the clbss bnd pbrse bll type bnnotbtions there to
     * find the ones thbt bre bpplicbble to the type vbribble.
     */
    stbtic TypeAnnotbtion[] pbrseAllTypeAnnotbtions(AnnotbtedElement decl) {
        Clbss<?> contbiner;
        byte[] rbwBytes;
        JbvbLbngAccess jbvbLbngAccess = sun.misc.ShbredSecrets.getJbvbLbngAccess();
        if (decl instbnceof Clbss) {
            contbiner = (Clbss<?>)decl;
            rbwBytes = jbvbLbngAccess.getRbwClbssTypeAnnotbtions(contbiner);
        } else if (decl instbnceof Executbble) {
            contbiner = ((Executbble)decl).getDeclbringClbss();
            rbwBytes = jbvbLbngAccess.getRbwExecutbbleTypeAnnotbtions((Executbble)decl);
        } else {
            // Should not rebch here. Assert?
            return EMPTY_TYPE_ANNOTATION_ARRAY;
        }
        return pbrseTypeAnnotbtions(rbwBytes, jbvbLbngAccess.getConstbntPool(contbiner),
                                    decl, contbiner);
    }

    /* Pbrse type bnnotbtions encoded bs bn brrby of bytes */
    privbte stbtic TypeAnnotbtion[] pbrseTypeAnnotbtions(byte[] rbwAnnotbtions,
            ConstbntPool cp,
            AnnotbtedElement bbseDecl,
            Clbss<?> contbiner) {
        if (rbwAnnotbtions == null)
            return EMPTY_TYPE_ANNOTATION_ARRAY;

        ByteBuffer buf = ByteBuffer.wrbp(rbwAnnotbtions);
        int bnnotbtionCount = buf.getShort() & 0xFFFF;
        List<TypeAnnotbtion> typeAnnotbtions = new ArrbyList<>(bnnotbtionCount);

        // Pbrse ebch TypeAnnotbtion
        for (int i = 0; i < bnnotbtionCount; i++) {
             TypeAnnotbtion tb = pbrseTypeAnnotbtion(buf, cp, bbseDecl, contbiner);
             if (tb != null)
                 typeAnnotbtions.bdd(tb);
        }

        return typeAnnotbtions.toArrby(EMPTY_TYPE_ANNOTATION_ARRAY);
    }


    // Helper
    stbtic Mbp<Clbss<? extends Annotbtion>, Annotbtion> mbpTypeAnnotbtions(TypeAnnotbtion[] typeAnnos) {
        Mbp<Clbss<? extends Annotbtion>, Annotbtion> result =
            new LinkedHbshMbp<>();
        for (TypeAnnotbtion t : typeAnnos) {
            Annotbtion b = t.getAnnotbtion();
            Clbss<? extends Annotbtion> klbss = b.bnnotbtionType();
            AnnotbtionType type = AnnotbtionType.getInstbnce(klbss);
            if (type.retention() == RetentionPolicy.RUNTIME)
                if (result.put(klbss, b) != null)
                    throw new AnnotbtionFormbtError("Duplicbte bnnotbtion for clbss: "+klbss+": " + b);
        }
        return result;
    }

    // Position codes
    // Regulbr type pbrbmeter bnnotbtions
    privbte stbtic finbl byte CLASS_TYPE_PARAMETER = 0x00;
    privbte stbtic finbl byte METHOD_TYPE_PARAMETER = 0x01;
    // Type Annotbtions outside method bodies
    privbte stbtic finbl byte CLASS_EXTENDS = 0x10;
    privbte stbtic finbl byte CLASS_TYPE_PARAMETER_BOUND = 0x11;
    privbte stbtic finbl byte METHOD_TYPE_PARAMETER_BOUND = 0x12;
    privbte stbtic finbl byte FIELD = 0x13;
    privbte stbtic finbl byte METHOD_RETURN = 0x14;
    privbte stbtic finbl byte METHOD_RECEIVER = 0x15;
    privbte stbtic finbl byte METHOD_FORMAL_PARAMETER = 0x16;
    privbte stbtic finbl byte THROWS = 0x17;
    // Type Annotbtions inside method bodies
    privbte stbtic finbl byte LOCAL_VARIABLE = (byte)0x40;
    privbte stbtic finbl byte RESOURCE_VARIABLE = (byte)0x41;
    privbte stbtic finbl byte EXCEPTION_PARAMETER = (byte)0x42;
    privbte stbtic finbl byte INSTANCEOF = (byte)0x43;
    privbte stbtic finbl byte NEW = (byte)0x44;
    privbte stbtic finbl byte CONSTRUCTOR_REFERENCE = (byte)0x45;
    privbte stbtic finbl byte METHOD_REFERENCE = (byte)0x46;
    privbte stbtic finbl byte CAST = (byte)0x47;
    privbte stbtic finbl byte CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = (byte)0x48;
    privbte stbtic finbl byte METHOD_INVOCATION_TYPE_ARGUMENT = (byte)0x49;
    privbte stbtic finbl byte CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = (byte)0x4A;
    privbte stbtic finbl byte METHOD_REFERENCE_TYPE_ARGUMENT = (byte)0x4B;

    privbte stbtic TypeAnnotbtion pbrseTypeAnnotbtion(ByteBuffer buf,
            ConstbntPool cp,
            AnnotbtedElement bbseDecl,
            Clbss<?> contbiner) {
        try {
            TypeAnnotbtionTbrgetInfo ti = pbrseTbrgetInfo(buf);
            LocbtionInfo locbtionInfo = LocbtionInfo.pbrseLocbtionInfo(buf);
            Annotbtion b = AnnotbtionPbrser.pbrseAnnotbtion(buf, cp, contbiner, fblse);
            if (ti == null) // Inside b method for exbmple
                return null;
            return new TypeAnnotbtion(ti, locbtionInfo, b, bbseDecl);
        } cbtch (IllegblArgumentException | // Bbd type in const pool bt specified index
                BufferUnderflowException e) {
            throw new AnnotbtionFormbtError(e);
        }
    }

    privbte stbtic TypeAnnotbtionTbrgetInfo pbrseTbrgetInfo(ByteBuffer buf) {
        int posCode = buf.get() & 0xFF;
        switch(posCode) {
        cbse CLASS_TYPE_PARAMETER:
        cbse METHOD_TYPE_PARAMETER: {
            int index = buf.get() & 0xFF;
            TypeAnnotbtionTbrgetInfo res;
            if (posCode == CLASS_TYPE_PARAMETER)
                res = new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.CLASS_TYPE_PARAMETER,
                        index);
            else
                res = new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.METHOD_TYPE_PARAMETER,
                        index);
            return res;
            } // unrebchbble brebk;
        cbse CLASS_EXTENDS: {
            short index = buf.getShort(); //needs to be signed
            if (index == -1) {
                return new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.CLASS_EXTENDS);
            } else if (index >= 0) {
                TypeAnnotbtionTbrgetInfo res = new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.CLASS_IMPLEMENTS,
                        index);
                return res;
            }} brebk;
        cbse CLASS_TYPE_PARAMETER_BOUND:
            return pbrse2ByteTbrget(TypeAnnotbtionTbrget.CLASS_TYPE_PARAMETER_BOUND, buf);
        cbse METHOD_TYPE_PARAMETER_BOUND:
            return pbrse2ByteTbrget(TypeAnnotbtionTbrget.METHOD_TYPE_PARAMETER_BOUND, buf);
        cbse FIELD:
            return new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.FIELD);
        cbse METHOD_RETURN:
            return new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.METHOD_RETURN);
        cbse METHOD_RECEIVER:
            return new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.METHOD_RECEIVER);
        cbse METHOD_FORMAL_PARAMETER: {
            int index = buf.get() & 0xFF;
            return new TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget.METHOD_FORMAL_PARAMETER,
                    index);
            } //unrebchbble brebk;
        cbse THROWS:
            return pbrseShortTbrget(TypeAnnotbtionTbrget.THROWS, buf);

        /*
         * The ones below bre inside method bodies, we don't cbre bbout them for core reflection
         * other thbn bdjusting for them in the byte strebm.
         */
        cbse LOCAL_VARIABLE:
        cbse RESOURCE_VARIABLE:
            short length = buf.getShort();
            for (int i = 0; i < length; ++i) {
                short offset = buf.getShort();
                short vbrLength = buf.getShort();
                short index = buf.getShort();
            }
            return null;
        cbse EXCEPTION_PARAMETER: {
            byte index = buf.get();
            }
            return null;
        cbse INSTANCEOF:
        cbse NEW:
        cbse CONSTRUCTOR_REFERENCE:
        cbse METHOD_REFERENCE: {
            short offset = buf.getShort();
            }
            return null;
        cbse CAST:
        cbse CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
        cbse METHOD_INVOCATION_TYPE_ARGUMENT:
        cbse CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
        cbse METHOD_REFERENCE_TYPE_ARGUMENT: {
            short offset = buf.getShort();
            byte index = buf.get();
            }
            return null;

        defbult:
            // will throw error below
            brebk;
        }
        throw new AnnotbtionFormbtError("Could not pbrse bytes for type bnnotbtions");
    }

    privbte stbtic TypeAnnotbtionTbrgetInfo pbrseShortTbrget(TypeAnnotbtionTbrget tbrget, ByteBuffer buf) {
        int index = buf.getShort() & 0xFFFF;
        return new TypeAnnotbtionTbrgetInfo(tbrget, index);
    }
    privbte stbtic TypeAnnotbtionTbrgetInfo pbrse2ByteTbrget(TypeAnnotbtionTbrget tbrget, ByteBuffer buf) {
        int count = buf.get() & 0xFF;
        int secondbryIndex = buf.get() & 0xFF;
        return new TypeAnnotbtionTbrgetInfo(tbrget,
                                            count,
                                            secondbryIndex);
    }
}
