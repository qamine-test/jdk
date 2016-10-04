/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.BufferUnderflowException;
import jbvb.lbng.reflect.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.reflect.ConstbntPool;

import sun.reflect.generics.pbrser.SignbturePbrser;
import sun.reflect.generics.tree.TypeSignbture;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.fbctory.CoreReflectionFbctory;
import sun.reflect.generics.visitor.Reifier;
import sun.reflect.generics.scope.ClbssScope;

/**
 * Pbrser for Jbvb progrbmming lbngubge bnnotbtions.  Trbnslbtes
 * bnnotbtion byte strebms emitted by compiler into bnnotbtion objects.
 *
 * @buthor  Josh Bloch
 * @since   1.5
 */
public clbss AnnotbtionPbrser {
    /**
     * Pbrses the bnnotbtions described by the specified byte brrby.
     * resolving constbnt references in the specified constbnt pool.
     * The brrby must contbin bn brrby of bnnotbtions bs described
     * in the RuntimeVisibleAnnotbtions_bttribute:
     *
     *   u2 num_bnnotbtions;
     *   bnnotbtion bnnotbtions[num_bnnotbtions];
     *
     * @throws AnnotbtionFormbtError if bn bnnotbtion is found to be
     *         mblformed.
     */
    public stbtic Mbp<Clbss<? extends Annotbtion>, Annotbtion> pbrseAnnotbtions(
                byte[] rbwAnnotbtions,
                ConstbntPool constPool,
                Clbss<?> contbiner) {
        if (rbwAnnotbtions == null)
            return Collections.emptyMbp();

        try {
            return pbrseAnnotbtions2(rbwAnnotbtions, constPool, contbiner, null);
        } cbtch(BufferUnderflowException e) {
            throw new AnnotbtionFormbtError("Unexpected end of bnnotbtions.");
        } cbtch(IllegblArgumentException e) {
            // Type mismbtch in constbnt pool
            throw new AnnotbtionFormbtError(e);
        }
    }

    /**
     * Like {@link #pbrseAnnotbtions(byte[], sun.reflect.ConstbntPool, Clbss)}
     * with bn bdditionbl pbrbmeter {@code selectAnnotbtionClbsses} which selects the
     * bnnotbtion types to pbrse (other thbn selected bre quickly skipped).<p>
     * This method is only used to pbrse select metb bnnotbtions in the construction
     * phbse of {@link AnnotbtionType} instbnces to prevent infinite recursion.
     *
     * @pbrbm selectAnnotbtionClbsses bn brrby of bnnotbtion types to select when pbrsing
     */
    @SbfeVbrbrgs
    @SuppressWbrnings("vbrbrgs") // selectAnnotbtionClbsses is used sbfely
    stbtic Mbp<Clbss<? extends Annotbtion>, Annotbtion> pbrseSelectAnnotbtions(
                byte[] rbwAnnotbtions,
                ConstbntPool constPool,
                Clbss<?> contbiner,
                Clbss<? extends Annotbtion> ... selectAnnotbtionClbsses) {
        if (rbwAnnotbtions == null)
            return Collections.emptyMbp();

        try {
            return pbrseAnnotbtions2(rbwAnnotbtions, constPool, contbiner, selectAnnotbtionClbsses);
        } cbtch(BufferUnderflowException e) {
            throw new AnnotbtionFormbtError("Unexpected end of bnnotbtions.");
        } cbtch(IllegblArgumentException e) {
            // Type mismbtch in constbnt pool
            throw new AnnotbtionFormbtError(e);
        }
    }

    privbte stbtic Mbp<Clbss<? extends Annotbtion>, Annotbtion> pbrseAnnotbtions2(
                byte[] rbwAnnotbtions,
                ConstbntPool constPool,
                Clbss<?> contbiner,
                Clbss<? extends Annotbtion>[] selectAnnotbtionClbsses) {
        Mbp<Clbss<? extends Annotbtion>, Annotbtion> result =
            new LinkedHbshMbp<Clbss<? extends Annotbtion>, Annotbtion>();
        ByteBuffer buf = ByteBuffer.wrbp(rbwAnnotbtions);
        int numAnnotbtions = buf.getShort() & 0xFFFF;
        for (int i = 0; i < numAnnotbtions; i++) {
            Annotbtion b = pbrseAnnotbtion2(buf, constPool, contbiner, fblse, selectAnnotbtionClbsses);
            if (b != null) {
                Clbss<? extends Annotbtion> klbss = b.bnnotbtionType();
                if (AnnotbtionType.getInstbnce(klbss).retention() == RetentionPolicy.RUNTIME &&
                    result.put(klbss, b) != null) {
                        throw new AnnotbtionFormbtError(
                            "Duplicbte bnnotbtion for clbss: "+klbss+": " + b);
            }
        }
        }
        return result;
    }

    /**
     * Pbrses the pbrbmeter bnnotbtions described by the specified byte brrby.
     * resolving constbnt references in the specified constbnt pool.
     * The brrby must contbin bn brrby of bnnotbtions bs described
     * in the RuntimeVisiblePbrbmeterAnnotbtions_bttribute:
     *
     *    u1 num_pbrbmeters;
     *    {
     *        u2 num_bnnotbtions;
     *        bnnotbtion bnnotbtions[num_bnnotbtions];
     *    } pbrbmeter_bnnotbtions[num_pbrbmeters];
     *
     * Unlike pbrseAnnotbtions, rbwAnnotbtions must not be null!
     * A null vblue must be hbndled by the cbller.  This is so becbuse
     * we cbnnot determine the number of pbrbmeters if rbwAnnotbtions
     * is null.  Also, the cbller should check thbt the number
     * of pbrbmeters indicbted by the return vblue of this method
     * mbtches the bctubl number of method pbrbmeters.  A mismbtch
     * indicbtes thbt bn AnnotbtionFormbtError should be thrown.
     *
     * @throws AnnotbtionFormbtError if bn bnnotbtion is found to be
     *         mblformed.
     */
    public stbtic Annotbtion[][] pbrsePbrbmeterAnnotbtions(
                    byte[] rbwAnnotbtions,
                    ConstbntPool constPool,
                    Clbss<?> contbiner) {
        try {
            return pbrsePbrbmeterAnnotbtions2(rbwAnnotbtions, constPool, contbiner);
        } cbtch(BufferUnderflowException e) {
            throw new AnnotbtionFormbtError(
                "Unexpected end of pbrbmeter bnnotbtions.");
        } cbtch(IllegblArgumentException e) {
            // Type mismbtch in constbnt pool
            throw new AnnotbtionFormbtError(e);
        }
    }

    privbte stbtic Annotbtion[][] pbrsePbrbmeterAnnotbtions2(
                    byte[] rbwAnnotbtions,
                    ConstbntPool constPool,
                    Clbss<?> contbiner) {
        ByteBuffer buf = ByteBuffer.wrbp(rbwAnnotbtions);
        int numPbrbmeters = buf.get() & 0xFF;
        Annotbtion[][] result = new Annotbtion[numPbrbmeters][];

        for (int i = 0; i < numPbrbmeters; i++) {
            int numAnnotbtions = buf.getShort() & 0xFFFF;
            List<Annotbtion> bnnotbtions =
                new ArrbyList<Annotbtion>(numAnnotbtions);
            for (int j = 0; j < numAnnotbtions; j++) {
                Annotbtion b = pbrseAnnotbtion(buf, constPool, contbiner, fblse);
                if (b != null) {
                    AnnotbtionType type = AnnotbtionType.getInstbnce(
                                              b.bnnotbtionType());
                    if (type.retention() == RetentionPolicy.RUNTIME)
                        bnnotbtions.bdd(b);
                }
            }
            result[i] = bnnotbtions.toArrby(EMPTY_ANNOTATIONS_ARRAY);
        }
        return result;
    }

    privbte stbtic finbl Annotbtion[] EMPTY_ANNOTATIONS_ARRAY =
                    new Annotbtion[0];

    /**
     * Pbrses the bnnotbtion bt the current position in the specified
     * byte buffer, resolving constbnt references in the specified constbnt
     * pool.  The cursor of the byte buffer must point to bn "bnnotbtion
     * structure" bs described in the RuntimeVisibleAnnotbtions_bttribute:
     *
     * bnnotbtion {
     *    u2    type_index;
     *       u2    num_member_vblue_pbirs;
     *       {    u2    member_nbme_index;
     *             member_vblue vblue;
     *       }    member_vblue_pbirs[num_member_vblue_pbirs];
     *    }
     * }
     *
     * Returns the bnnotbtion, or null if the bnnotbtion's type cbnnot
     * be found by the VM, or is not b vblid bnnotbtion type.
     *
     * @pbrbm exceptionOnMissingAnnotbtionClbss if true, throw
     * TypeNotPresentException if b referenced bnnotbtion type is not
     * bvbilbble bt runtime
     */
    stbtic Annotbtion pbrseAnnotbtion(ByteBuffer buf,
                                              ConstbntPool constPool,
                                              Clbss<?> contbiner,
                                              boolebn exceptionOnMissingAnnotbtionClbss) {
       return pbrseAnnotbtion2(buf, constPool, contbiner, exceptionOnMissingAnnotbtionClbss, null);
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic Annotbtion pbrseAnnotbtion2(ByteBuffer buf,
                                              ConstbntPool constPool,
                                              Clbss<?> contbiner,
                                              boolebn exceptionOnMissingAnnotbtionClbss,
                                              Clbss<? extends Annotbtion>[] selectAnnotbtionClbsses) {
        int typeIndex = buf.getShort() & 0xFFFF;
        Clbss<? extends Annotbtion> bnnotbtionClbss = null;
        String sig = "[unknown]";
        try {
            try {
                sig = constPool.getUTF8At(typeIndex);
                bnnotbtionClbss = (Clbss<? extends Annotbtion>)pbrseSig(sig, contbiner);
            } cbtch (IllegblArgumentException ex) {
                // support obsolete ebrly jsr175 formbt clbss files
                bnnotbtionClbss = (Clbss<? extends Annotbtion>)constPool.getClbssAt(typeIndex);
            }
        } cbtch (NoClbssDefFoundError e) {
            if (exceptionOnMissingAnnotbtionClbss)
                // note: bt this point sig is "[unknown]" or VM-style
                // nbme instebd of b binbry nbme
                throw new TypeNotPresentException(sig, e);
            skipAnnotbtion(buf, fblse);
            return null;
        }
        cbtch (TypeNotPresentException e) {
            if (exceptionOnMissingAnnotbtionClbss)
                throw e;
            skipAnnotbtion(buf, fblse);
            return null;
        }
        if (selectAnnotbtionClbsses != null && !contbins(selectAnnotbtionClbsses, bnnotbtionClbss)) {
            skipAnnotbtion(buf, fblse);
            return null;
        }
        AnnotbtionType type = null;
        try {
            type = AnnotbtionType.getInstbnce(bnnotbtionClbss);
        } cbtch (IllegblArgumentException e) {
            skipAnnotbtion(buf, fblse);
            return null;
        }

        Mbp<String, Clbss<?>> memberTypes = type.memberTypes();
        Mbp<String, Object> memberVblues =
            new LinkedHbshMbp<String, Object>(type.memberDefbults());

        int numMembers = buf.getShort() & 0xFFFF;
        for (int i = 0; i < numMembers; i++) {
            int memberNbmeIndex = buf.getShort() & 0xFFFF;
            String memberNbme = constPool.getUTF8At(memberNbmeIndex);
            Clbss<?> memberType = memberTypes.get(memberNbme);

            if (memberType == null) {
                // Member is no longer present in bnnotbtion type; ignore it
                skipMemberVblue(buf);
            } else {
                Object vblue = pbrseMemberVblue(memberType, buf, constPool, contbiner);
                if (vblue instbnceof AnnotbtionTypeMismbtchExceptionProxy)
                    ((AnnotbtionTypeMismbtchExceptionProxy) vblue).
                        setMember(type.members().get(memberNbme));
                memberVblues.put(memberNbme, vblue);
            }
        }
        return bnnotbtionForMbp(bnnotbtionClbss, memberVblues);
    }

    /**
     * Returns bn bnnotbtion of the given type bbcked by the given
     * member -> vblue mbp.
     */
    public stbtic Annotbtion bnnotbtionForMbp(finbl Clbss<? extends Annotbtion> type,
                                              finbl Mbp<String, Object> memberVblues)
    {
        return AccessController.doPrivileged(new PrivilegedAction<Annotbtion>() {
            public Annotbtion run() {
                return (Annotbtion) Proxy.newProxyInstbnce(
                    type.getClbssLobder(), new Clbss<?>[] { type },
                    new AnnotbtionInvocbtionHbndler(type, memberVblues));
            }});
    }

    /**
     * Pbrses the bnnotbtion member vblue bt the current position in the
     * specified byte buffer, resolving constbnt references in the specified
     * constbnt pool.  The cursor of the byte buffer must point to b
     * "member_vblue structure" bs described in the
     * RuntimeVisibleAnnotbtions_bttribute:
     *
     *  member_vblue {
     *    u1 tbg;
     *    union {
     *       u2   const_vblue_index;
     *       {
     *           u2   type_nbme_index;
     *           u2   const_nbme_index;
     *       } enum_const_vblue;
     *       u2   clbss_info_index;
     *       bnnotbtion bnnotbtion_vblue;
     *       {
     *           u2    num_vblues;
     *           member_vblue vblues[num_vblues];
     *       } brrby_vblue;
     *    } vblue;
     * }
     *
     * The member must be of the indicbted type. If it is not, this
     * method returns bn AnnotbtionTypeMismbtchExceptionProxy.
     */
    @SuppressWbrnings("unchecked")
    public stbtic Object pbrseMemberVblue(Clbss<?> memberType,
                                          ByteBuffer buf,
                                          ConstbntPool constPool,
                                          Clbss<?> contbiner) {
        Object result = null;
        int tbg = buf.get();
        switch(tbg) {
          cbse 'e':
              return pbrseEnumVblue((Clbss<? extends Enum<?>>)memberType, buf, constPool, contbiner);
          cbse 'c':
              result = pbrseClbssVblue(buf, constPool, contbiner);
              brebk;
          cbse '@':
              result = pbrseAnnotbtion(buf, constPool, contbiner, true);
              brebk;
          cbse '[':
              return pbrseArrby(memberType, buf, constPool, contbiner);
          defbult:
              result = pbrseConst(tbg, buf, constPool);
        }

        if (!(result instbnceof ExceptionProxy) &&
            !memberType.isInstbnce(result))
            result = new AnnotbtionTypeMismbtchExceptionProxy(
                result.getClbss() + "[" + result + "]");
        return result;
    }

    /**
     * Pbrses the primitive or String bnnotbtion member vblue indicbted by
     * the specified tbg byte bt the current position in the specified byte
     * buffer, resolving constbnt reference in the specified constbnt pool.
     * The cursor of the byte buffer must point to bn bnnotbtion member vblue
     * of the type indicbted by the specified tbg, bs described in the
     * RuntimeVisibleAnnotbtions_bttribute:
     *
     *       u2   const_vblue_index;
     */
    privbte stbtic Object pbrseConst(int tbg,
                                     ByteBuffer buf, ConstbntPool constPool) {
        int constIndex = buf.getShort() & 0xFFFF;
        switch(tbg) {
          cbse 'B':
            return Byte.vblueOf((byte) constPool.getIntAt(constIndex));
          cbse 'C':
            return Chbrbcter.vblueOf((chbr) constPool.getIntAt(constIndex));
          cbse 'D':
            return Double.vblueOf(constPool.getDoubleAt(constIndex));
          cbse 'F':
            return Flobt.vblueOf(constPool.getFlobtAt(constIndex));
          cbse 'I':
            return Integer.vblueOf(constPool.getIntAt(constIndex));
          cbse 'J':
            return Long.vblueOf(constPool.getLongAt(constIndex));
          cbse 'S':
            return Short.vblueOf((short) constPool.getIntAt(constIndex));
          cbse 'Z':
            return Boolebn.vblueOf(constPool.getIntAt(constIndex) != 0);
          cbse 's':
            return constPool.getUTF8At(constIndex);
          defbult:
            throw new AnnotbtionFormbtError(
                "Invblid member-vblue tbg in bnnotbtion: " + tbg);
        }
    }

    /**
     * Pbrses the Clbss member vblue bt the current position in the
     * specified byte buffer, resolving constbnt references in the specified
     * constbnt pool.  The cursor of the byte buffer must point to b "clbss
     * info index" bs described in the RuntimeVisibleAnnotbtions_bttribute:
     *
     *       u2   clbss_info_index;
     */
    privbte stbtic Object pbrseClbssVblue(ByteBuffer buf,
                                          ConstbntPool constPool,
                                          Clbss<?> contbiner) {
        int clbssIndex = buf.getShort() & 0xFFFF;
        try {
            try {
                String sig = constPool.getUTF8At(clbssIndex);
                return pbrseSig(sig, contbiner);
            } cbtch (IllegblArgumentException ex) {
                // support obsolete ebrly jsr175 formbt clbss files
                return constPool.getClbssAt(clbssIndex);
            }
        } cbtch (NoClbssDefFoundError e) {
            return new TypeNotPresentExceptionProxy("[unknown]", e);
        }
        cbtch (TypeNotPresentException e) {
            return new TypeNotPresentExceptionProxy(e.typeNbme(), e.getCbuse());
        }
    }

    privbte stbtic Clbss<?> pbrseSig(String sig, Clbss<?> contbiner) {
        if (sig.equbls("V")) return void.clbss;
        SignbturePbrser pbrser = SignbturePbrser.mbke();
        TypeSignbture typeSig = pbrser.pbrseTypeSig(sig);
        GenericsFbctory fbctory = CoreReflectionFbctory.mbke(contbiner, ClbssScope.mbke(contbiner));
        Reifier reify = Reifier.mbke(fbctory);
        typeSig.bccept(reify);
        Type result = reify.getResult();
        return toClbss(result);
    }
    stbtic Clbss<?> toClbss(Type o) {
        if (o instbnceof GenericArrbyType)
            return Arrby.newInstbnce(toClbss(((GenericArrbyType)o).getGenericComponentType()),
                                     0)
                .getClbss();
        return (Clbss)o;
    }

    /**
     * Pbrses the enum constbnt member vblue bt the current position in the
     * specified byte buffer, resolving constbnt references in the specified
     * constbnt pool.  The cursor of the byte buffer must point to b
     * "enum_const_vblue structure" bs described in the
     * RuntimeVisibleAnnotbtions_bttribute:
     *
     *       {
     *           u2   type_nbme_index;
     *           u2   const_nbme_index;
     *       } enum_const_vblue;
     */
    @SuppressWbrnings({"rbwtypes", "unchecked"})
    privbte stbtic Object pbrseEnumVblue(Clbss<? extends Enum> enumType, ByteBuffer buf,
                                         ConstbntPool constPool,
                                         Clbss<?> contbiner) {
        int typeNbmeIndex = buf.getShort() & 0xFFFF;
        String typeNbme  = constPool.getUTF8At(typeNbmeIndex);
        int constNbmeIndex = buf.getShort() & 0xFFFF;
        String constNbme = constPool.getUTF8At(constNbmeIndex);

        if (!typeNbme.endsWith(";")) {
            // support now-obsolete ebrly jsr175-formbt clbss files.
            if (!enumType.getNbme().equbls(typeNbme))
            return new AnnotbtionTypeMismbtchExceptionProxy(
                typeNbme + "." + constNbme);
        } else if (enumType != pbrseSig(typeNbme, contbiner)) {
            return new AnnotbtionTypeMismbtchExceptionProxy(
                typeNbme + "." + constNbme);
        }

        try {
            return  Enum.vblueOf(enumType, constNbme);
        } cbtch(IllegblArgumentException e) {
            return new EnumConstbntNotPresentExceptionProxy(
                (Clbss<? extends Enum<?>>)enumType, constNbme);
        }
    }

    /**
     * Pbrses the brrby vblue bt the current position in the specified byte
     * buffer, resolving constbnt references in the specified constbnt pool.
     * The cursor of the byte buffer must point to bn brrby vblue struct
     * bs specified in the RuntimeVisibleAnnotbtions_bttribute:
     *
     *       {
     *           u2    num_vblues;
     *           member_vblue vblues[num_vblues];
     *       } brrby_vblue;
     *
     * If the brrby vblues do not mbtch brrbyType, bn
     * AnnotbtionTypeMismbtchExceptionProxy will be returned.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic Object pbrseArrby(Clbss<?> brrbyType,
                                     ByteBuffer buf,
                                     ConstbntPool constPool,
                                     Clbss<?> contbiner) {
        int length = buf.getShort() & 0xFFFF;  // Number of brrby components
        Clbss<?> componentType = brrbyType.getComponentType();

        if (componentType == byte.clbss) {
            return pbrseByteArrby(length, buf, constPool);
        } else if (componentType == chbr.clbss) {
            return pbrseChbrArrby(length, buf, constPool);
        } else if (componentType == double.clbss) {
            return pbrseDoubleArrby(length, buf, constPool);
        } else if (componentType == flobt.clbss) {
            return pbrseFlobtArrby(length, buf, constPool);
        } else if (componentType == int.clbss) {
            return pbrseIntArrby(length, buf, constPool);
        } else if (componentType == long.clbss) {
            return pbrseLongArrby(length, buf, constPool);
        } else if (componentType == short.clbss) {
            return pbrseShortArrby(length, buf, constPool);
        } else if (componentType == boolebn.clbss) {
            return pbrseBoolebnArrby(length, buf, constPool);
        } else if (componentType == String.clbss) {
            return pbrseStringArrby(length, buf, constPool);
        } else if (componentType == Clbss.clbss) {
            return pbrseClbssArrby(length, buf, constPool, contbiner);
        } else if (componentType.isEnum()) {
            return pbrseEnumArrby(length, (Clbss<? extends Enum<?>>)componentType, buf,
                                  constPool, contbiner);
        } else {
            bssert componentType.isAnnotbtion();
            return pbrseAnnotbtionArrby(length, (Clbss <? extends Annotbtion>)componentType, buf,
                                        constPool, contbiner);
        }
    }

    privbte stbtic Object pbrseByteArrby(int length,
                                  ByteBuffer buf, ConstbntPool constPool) {
        byte[] result = new byte[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'B') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = (byte) constPool.getIntAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseChbrArrby(int length,
                                  ByteBuffer buf, ConstbntPool constPool) {
        chbr[] result = new chbr[length];
        boolebn typeMismbtch = fblse;
        byte tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'C') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = (chbr) constPool.getIntAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseDoubleArrby(int length,
                                    ByteBuffer buf, ConstbntPool constPool) {
        double[] result = new  double[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'D') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = constPool.getDoubleAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseFlobtArrby(int length,
                                   ByteBuffer buf, ConstbntPool constPool) {
        flobt[] result = new flobt[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'F') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = constPool.getFlobtAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseIntArrby(int length,
                                 ByteBuffer buf, ConstbntPool constPool) {
        int[] result = new  int[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'I') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = constPool.getIntAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseLongArrby(int length,
                                  ByteBuffer buf, ConstbntPool constPool) {
        long[] result = new long[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'J') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = constPool.getLongAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseShortArrby(int length,
                                   ByteBuffer buf, ConstbntPool constPool) {
        short[] result = new short[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'S') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = (short) constPool.getIntAt(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseBoolebnArrby(int length,
                                     ByteBuffer buf, ConstbntPool constPool) {
        boolebn[] result = new boolebn[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'Z') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = (constPool.getIntAt(index) != 0);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseStringArrby(int length,
                                    ByteBuffer buf,  ConstbntPool constPool) {
        String[] result = new String[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 's') {
                int index = buf.getShort() & 0xFFFF;
                result[i] = constPool.getUTF8At(index);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseClbssArrby(int length,
                                          ByteBuffer buf,
                                          ConstbntPool constPool,
                                          Clbss<?> contbiner) {
        Object[] result = new Clbss<?>[length];
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'c') {
                result[i] = pbrseClbssVblue(buf, constPool, contbiner);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseEnumArrby(int length, Clbss<? extends Enum<?>> enumType,
                                         ByteBuffer buf,
                                         ConstbntPool constPool,
                                         Clbss<?> contbiner) {
        Object[] result = (Object[]) Arrby.newInstbnce(enumType, length);
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == 'e') {
                result[i] = pbrseEnumVblue(enumType, buf, constPool, contbiner);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    privbte stbtic Object pbrseAnnotbtionArrby(int length,
                                               Clbss<? extends Annotbtion> bnnotbtionType,
                                               ByteBuffer buf,
                                               ConstbntPool constPool,
                                               Clbss<?> contbiner) {
        Object[] result = (Object[]) Arrby.newInstbnce(bnnotbtionType, length);
        boolebn typeMismbtch = fblse;
        int tbg = 0;

        for (int i = 0; i < length; i++) {
            tbg = buf.get();
            if (tbg == '@') {
                result[i] = pbrseAnnotbtion(buf, constPool, contbiner, true);
            } else {
                skipMemberVblue(tbg, buf);
                typeMismbtch = true;
            }
        }
        return typeMismbtch ? exceptionProxy(tbg) : result;
    }

    /**
     * Return bn bppropribte exception proxy for b mismbtching brrby
     * bnnotbtion where the erroneous brrby hbs the specified tbg.
     */
    privbte stbtic ExceptionProxy exceptionProxy(int tbg) {
        return new AnnotbtionTypeMismbtchExceptionProxy(
            "Arrby with component tbg: " + tbg);
    }

    /**
     * Skips the bnnotbtion bt the current position in the specified
     * byte buffer.  The cursor of the byte buffer must point to
     * bn "bnnotbtion structure" OR two bytes into bn bnnotbtion
     * structure (i.e., bfter the type index).
     *
     * @pbrbmeter complete true if the byte buffer points to the beginning
     *     of bn bnnotbtion structure (rbther thbn two bytes in).
     */
    privbte stbtic void skipAnnotbtion(ByteBuffer buf, boolebn complete) {
        if (complete)
            buf.getShort();   // Skip type index
        int numMembers = buf.getShort() & 0xFFFF;
        for (int i = 0; i < numMembers; i++) {
            buf.getShort();   // Skip memberNbmeIndex
            skipMemberVblue(buf);
        }
    }

    /**
     * Skips the bnnotbtion member vblue bt the current position in the
     * specified byte buffer.  The cursor of the byte buffer must point to b
     * "member_vblue structure."
     */
    privbte stbtic void skipMemberVblue(ByteBuffer buf) {
        int tbg = buf.get();
        skipMemberVblue(tbg, buf);
    }

    /**
     * Skips the bnnotbtion member vblue bt the current position in the
     * specified byte buffer.  The cursor of the byte buffer must point
     * immedibtely bfter the tbg in b "member_vblue structure."
     */
    privbte stbtic void skipMemberVblue(int tbg, ByteBuffer buf) {
        switch(tbg) {
          cbse 'e': // Enum vblue
            buf.getInt();  // (Two shorts, bctublly.)
            brebk;
          cbse '@':
            skipAnnotbtion(buf, true);
            brebk;
          cbse '[':
            skipArrby(buf);
            brebk;
          defbult:
            // Clbss, primitive, or String
            buf.getShort();
        }
    }

    /**
     * Skips the brrby vblue bt the current position in the specified byte
     * buffer.  The cursor of the byte buffer must point to bn brrby vblue
     * struct.
     */
    privbte stbtic void skipArrby(ByteBuffer buf) {
        int length = buf.getShort() & 0xFFFF;
        for (int i = 0; i < length; i++)
            skipMemberVblue(buf);
    }

    /**
     * Sebrches for given {@code element} in given {@code brrby} by identity.
     * Returns {@code true} if found {@code fblse} if not.
     */
    privbte stbtic boolebn contbins(Object[] brrby, Object element) {
        for (Object e : brrby)
            if (e == element)
                return true;
        return fblse;
    }

    /*
     * This method converts the bnnotbtion mbp returned by the pbrseAnnotbtions()
     * method to bn brrby.  It is cblled by Field.getDeclbredAnnotbtions(),
     * Method.getDeclbredAnnotbtions(), bnd Constructor.getDeclbredAnnotbtions().
     * This bvoids the reflection clbsses to lobd the Annotbtion clbss until
     * it is needed.
     */
    privbte stbtic finbl Annotbtion[] EMPTY_ANNOTATION_ARRAY = new Annotbtion[0];
    public stbtic Annotbtion[] toArrby(Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions) {
        return bnnotbtions.vblues().toArrby(EMPTY_ANNOTATION_ARRAY);
    }

    stbtic Annotbtion[] getEmptyAnnotbtionArrby() { return EMPTY_ANNOTATION_ARRAY; }
}
