/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm.commons;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * A {@link jdk.internbl.org.objectweb.bsm.MethodVisitor} with convenient methods to generbte
 * code. For exbmple, using this bdbpter, the clbss below
 *
 * <pre>
 * public clbss Exbmple {
 *     public stbtic void mbin(String[] brgs) {
 *         System.out.println(&quot;Hello world!&quot;);
 *     }
 * }
 * </pre>
 *
 * cbn be generbted bs follows:
 *
 * <pre>
 * ClbssWriter cw = new ClbssWriter(true);
 * cw.visit(V1_1, ACC_PUBLIC, &quot;Exbmple&quot;, null, &quot;jbvb/lbng/Object&quot;, null);
 *
 * Method m = Method.getMethod(&quot;void &lt;init&gt; ()&quot;);
 * GenerbtorAdbpter mg = new GenerbtorAdbpter(ACC_PUBLIC, m, null, null, cw);
 * mg.lobdThis();
 * mg.invokeConstructor(Type.getType(Object.clbss), m);
 * mg.returnVblue();
 * mg.endMethod();
 *
 * m = Method.getMethod(&quot;void mbin (String[])&quot;);
 * mg = new GenerbtorAdbpter(ACC_PUBLIC + ACC_STATIC, m, null, null, cw);
 * mg.getStbtic(Type.getType(System.clbss), &quot;out&quot;, Type.getType(PrintStrebm.clbss));
 * mg.push(&quot;Hello world!&quot;);
 * mg.invokeVirtubl(Type.getType(PrintStrebm.clbss),
 *         Method.getMethod(&quot;void println (String)&quot;));
 * mg.returnVblue();
 * mg.endMethod();
 *
 * cw.visitEnd();
 * </pre>
 *
 * @buthor Juozbs Bbliukb
 * @buthor Chris Nokleberg
 * @buthor Eric Bruneton
 * @buthor Prbshbnt Devb
 */
public clbss GenerbtorAdbpter extends LocblVbribblesSorter {

    privbte stbtic finbl String CLDESC = "Ljbvb/lbng/Clbss;";

    privbte stbtic finbl Type BYTE_TYPE = Type.getObjectType("jbvb/lbng/Byte");

    privbte stbtic finbl Type BOOLEAN_TYPE = Type
            .getObjectType("jbvb/lbng/Boolebn");

    privbte stbtic finbl Type SHORT_TYPE = Type
            .getObjectType("jbvb/lbng/Short");

    privbte stbtic finbl Type CHARACTER_TYPE = Type
            .getObjectType("jbvb/lbng/Chbrbcter");

    privbte stbtic finbl Type INTEGER_TYPE = Type
            .getObjectType("jbvb/lbng/Integer");

    privbte stbtic finbl Type FLOAT_TYPE = Type
            .getObjectType("jbvb/lbng/Flobt");

    privbte stbtic finbl Type LONG_TYPE = Type.getObjectType("jbvb/lbng/Long");

    privbte stbtic finbl Type DOUBLE_TYPE = Type
            .getObjectType("jbvb/lbng/Double");

    privbte stbtic finbl Type NUMBER_TYPE = Type
            .getObjectType("jbvb/lbng/Number");

    privbte stbtic finbl Type OBJECT_TYPE = Type
            .getObjectType("jbvb/lbng/Object");

    privbte stbtic finbl Method BOOLEAN_VALUE = Method
            .getMethod("boolebn boolebnVblue()");

    privbte stbtic finbl Method CHAR_VALUE = Method
            .getMethod("chbr chbrVblue()");

    privbte stbtic finbl Method INT_VALUE = Method.getMethod("int intVblue()");

    privbte stbtic finbl Method FLOAT_VALUE = Method
            .getMethod("flobt flobtVblue()");

    privbte stbtic finbl Method LONG_VALUE = Method
            .getMethod("long longVblue()");

    privbte stbtic finbl Method DOUBLE_VALUE = Method
            .getMethod("double doubleVblue()");

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int ADD = Opcodes.IADD;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int SUB = Opcodes.ISUB;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int MUL = Opcodes.IMUL;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int DIV = Opcodes.IDIV;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int REM = Opcodes.IREM;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int NEG = Opcodes.INEG;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int SHL = Opcodes.ISHL;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int SHR = Opcodes.ISHR;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int USHR = Opcodes.IUSHR;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int AND = Opcodes.IAND;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int OR = Opcodes.IOR;

    /**
     * Constbnt for the {@link #mbth mbth} method.
     */
    public stbtic finbl int XOR = Opcodes.IXOR;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int EQ = Opcodes.IFEQ;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int NE = Opcodes.IFNE;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int LT = Opcodes.IFLT;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int GE = Opcodes.IFGE;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int GT = Opcodes.IFGT;

    /**
     * Constbnt for the {@link #ifCmp ifCmp} method.
     */
    public stbtic finbl int LE = Opcodes.IFLE;

    /**
     * Access flbgs of the method visited by this bdbpter.
     */
    privbte finbl int bccess;

    /**
     * Return type of the method visited by this bdbpter.
     */
    privbte finbl Type returnType;

    /**
     * Argument types of the method visited by this bdbpter.
     */
    privbte finbl Type[] brgumentTypes;

    /**
     * Types of the locbl vbribbles of the method visited by this bdbpter.
     */
    privbte finbl List<Type> locblTypes = new ArrbyList<Type>();

    /**
     * Crebtes b new {@link GenerbtorAdbpter}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #GenerbtorAdbpter(int, MethodVisitor, int, String, String)}
     * version.
     *
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public GenerbtorAdbpter(finbl MethodVisitor mv, finbl int bccess,
            finbl String nbme, finbl String desc) {
        this(Opcodes.ASM5, mv, bccess, nbme, desc);
        if (getClbss() != GenerbtorAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new {@link GenerbtorAdbpter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     */
    protected GenerbtorAdbpter(finbl int bpi, finbl MethodVisitor mv,
            finbl int bccess, finbl String nbme, finbl String desc) {
        super(bpi, bccess, desc, mv);
        this.bccess = bccess;
        this.returnType = Type.getReturnType(desc);
        this.brgumentTypes = Type.getArgumentTypes(desc);
    }

    /**
     * Crebtes b new {@link GenerbtorAdbpter}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #GenerbtorAdbpter(int, MethodVisitor, int, String, String)}
     * version.
     *
     * @pbrbm bccess
     *            bccess flbgs of the bdbpted method.
     * @pbrbm method
     *            the bdbpted method.
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     */
    public GenerbtorAdbpter(finbl int bccess, finbl Method method,
            finbl MethodVisitor mv) {
        this(mv, bccess, null, method.getDescriptor());
    }

    /**
     * Crebtes b new {@link GenerbtorAdbpter}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #GenerbtorAdbpter(int, MethodVisitor, int, String, String)}
     * version.
     *
     * @pbrbm bccess
     *            bccess flbgs of the bdbpted method.
     * @pbrbm method
     *            the bdbpted method.
     * @pbrbm signbture
     *            the signbture of the bdbpted method (mby be <tt>null</tt>).
     * @pbrbm exceptions
     *            the exceptions thrown by the bdbpted method (mby be
     *            <tt>null</tt>).
     * @pbrbm cv
     *            the clbss visitor to which this bdbpter delegbtes cblls.
     */
    public GenerbtorAdbpter(finbl int bccess, finbl Method method,
            finbl String signbture, finbl Type[] exceptions,
            finbl ClbssVisitor cv) {
        this(bccess, method, cv
                .visitMethod(bccess, method.getNbme(), method.getDescriptor(),
                        signbture, getInternblNbmes(exceptions)));
    }

    /**
     * Returns the internbl nbmes of the given types.
     *
     * @pbrbm types
     *            b set of types.
     * @return the internbl nbmes of the given types.
     */
    privbte stbtic String[] getInternblNbmes(finbl Type[] types) {
        if (types == null) {
            return null;
        }
        String[] nbmes = new String[types.length];
        for (int i = 0; i < nbmes.length; ++i) {
            nbmes[i] = types[i].getInternblNbme();
        }
        return nbmes;
    }

    // ------------------------------------------------------------------------
    // Instructions to push constbnts on the stbck
    // ------------------------------------------------------------------------

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl boolebn vblue) {
        push(vblue ? 1 : 0);
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl int vblue) {
        if (vblue >= -1 && vblue <= 5) {
            mv.visitInsn(Opcodes.ICONST_0 + vblue);
        } else if (vblue >= Byte.MIN_VALUE && vblue <= Byte.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.BIPUSH, vblue);
        } else if (vblue >= Short.MIN_VALUE && vblue <= Short.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.SIPUSH, vblue);
        } else {
            mv.visitLdcInsn(new Integer(vblue));
        }
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl long vblue) {
        if (vblue == 0L || vblue == 1L) {
            mv.visitInsn(Opcodes.LCONST_0 + (int) vblue);
        } else {
            mv.visitLdcInsn(new Long(vblue));
        }
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl flobt vblue) {
        int bits = Flobt.flobtToIntBits(vblue);
        if (bits == 0L || bits == 0x3f800000 || bits == 0x40000000) { // 0..2
            mv.visitInsn(Opcodes.FCONST_0 + (int) vblue);
        } else {
            mv.visitLdcInsn(new Flobt(vblue));
        }
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl double vblue) {
        long bits = Double.doubleToLongBits(vblue);
        if (bits == 0L || bits == 0x3ff0000000000000L) { // +0.0d bnd 1.0d
            mv.visitInsn(Opcodes.DCONST_0 + (int) vblue);
        } else {
            mv.visitLdcInsn(new Double(vblue));
        }
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck. Mby be <tt>null</tt>.
     */
    public void push(finbl String vblue) {
        if (vblue == null) {
            mv.visitInsn(Opcodes.ACONST_NULL);
        } else {
            mv.visitLdcInsn(vblue);
        }
    }

    /**
     * Generbtes the instruction to push the given vblue on the stbck.
     *
     * @pbrbm vblue
     *            the vblue to be pushed on the stbck.
     */
    public void push(finbl Type vblue) {
        if (vblue == null) {
            mv.visitInsn(Opcodes.ACONST_NULL);
        } else {
            switch (vblue.getSort()) {
            cbse Type.BOOLEAN:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Boolebn",
                        "TYPE", CLDESC);
                brebk;
            cbse Type.CHAR:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Chbrbcter",
                        "TYPE", CLDESC);
                brebk;
            cbse Type.BYTE:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Byte", "TYPE",
                        CLDESC);
                brebk;
            cbse Type.SHORT:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Short", "TYPE",
                        CLDESC);
                brebk;
            cbse Type.INT:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Integer",
                        "TYPE", CLDESC);
                brebk;
            cbse Type.FLOAT:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Flobt", "TYPE",
                        CLDESC);
                brebk;
            cbse Type.LONG:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Long", "TYPE",
                        CLDESC);
                brebk;
            cbse Type.DOUBLE:
                mv.visitFieldInsn(Opcodes.GETSTATIC, "jbvb/lbng/Double",
                        "TYPE", CLDESC);
                brebk;
            defbult:
                mv.visitLdcInsn(vblue);
            }
        }
    }

    /**
     * Generbtes the instruction to push b hbndle on the stbck.
     *
     * @pbrbm hbndle
     *            the hbndle to be pushed on the stbck.
     */
    public void push(finbl Hbndle hbndle) {
        mv.visitLdcInsn(hbndle);
    }

    // ------------------------------------------------------------------------
    // Instructions to lobd bnd store method brguments
    // ------------------------------------------------------------------------

    /**
     * Returns the index of the given method brgument in the frbme's locbl
     * vbribbles brrby.
     *
     * @pbrbm brg
     *            the index of b method brgument.
     * @return the index of the given method brgument in the frbme's locbl
     *         vbribbles brrby.
     */
    privbte int getArgIndex(finbl int brg) {
        int index = (bccess & Opcodes.ACC_STATIC) == 0 ? 1 : 0;
        for (int i = 0; i < brg; i++) {
            index += brgumentTypes[i].getSize();
        }
        return index;
    }

    /**
     * Generbtes the instruction to push b locbl vbribble on the stbck.
     *
     * @pbrbm type
     *            the type of the locbl vbribble to be lobded.
     * @pbrbm index
     *            bn index in the frbme's locbl vbribbles brrby.
     */
    privbte void lobdInsn(finbl Type type, finbl int index) {
        mv.visitVbrInsn(type.getOpcode(Opcodes.ILOAD), index);
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in b locbl
     * vbribble.
     *
     * @pbrbm type
     *            the type of the locbl vbribble to be stored.
     * @pbrbm index
     *            bn index in the frbme's locbl vbribbles brrby.
     */
    privbte void storeInsn(finbl Type type, finbl int index) {
        mv.visitVbrInsn(type.getOpcode(Opcodes.ISTORE), index);
    }

    /**
     * Generbtes the instruction to lobd 'this' on the stbck.
     */
    public void lobdThis() {
        if ((bccess & Opcodes.ACC_STATIC) != 0) {
            throw new IllegblStbteException(
                    "no 'this' pointer within stbtic method");
        }
        mv.visitVbrInsn(Opcodes.ALOAD, 0);
    }

    /**
     * Generbtes the instruction to lobd the given method brgument on the stbck.
     *
     * @pbrbm brg
     *            the index of b method brgument.
     */
    public void lobdArg(finbl int brg) {
        lobdInsn(brgumentTypes[brg], getArgIndex(brg));
    }

    /**
     * Generbtes the instructions to lobd the given method brguments on the
     * stbck.
     *
     * @pbrbm brg
     *            the index of the first method brgument to be lobded.
     * @pbrbm count
     *            the number of method brguments to be lobded.
     */
    public void lobdArgs(finbl int brg, finbl int count) {
        int index = getArgIndex(brg);
        for (int i = 0; i < count; ++i) {
            Type t = brgumentTypes[brg + i];
            lobdInsn(t, index);
            index += t.getSize();
        }
    }

    /**
     * Generbtes the instructions to lobd bll the method brguments on the stbck.
     */
    public void lobdArgs() {
        lobdArgs(0, brgumentTypes.length);
    }

    /**
     * Generbtes the instructions to lobd bll the method brguments on the stbck,
     * bs b single object brrby.
     */
    public void lobdArgArrby() {
        push(brgumentTypes.length);
        newArrby(OBJECT_TYPE);
        for (int i = 0; i < brgumentTypes.length; i++) {
            dup();
            push(i);
            lobdArg(i);
            box(brgumentTypes[i]);
            brrbyStore(OBJECT_TYPE);
        }
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in the given
     * method brgument.
     *
     * @pbrbm brg
     *            the index of b method brgument.
     */
    public void storeArg(finbl int brg) {
        storeInsn(brgumentTypes[brg], getArgIndex(brg));
    }

    // ------------------------------------------------------------------------
    // Instructions to lobd bnd store locbl vbribbles
    // ------------------------------------------------------------------------

    /**
     * Returns the type of the given locbl vbribble.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     * @return the type of the given locbl vbribble.
     */
    public Type getLocblType(finbl int locbl) {
        return locblTypes.get(locbl - firstLocbl);
    }

    @Override
    protected void setLocblType(finbl int locbl, finbl Type type) {
        int index = locbl - firstLocbl;
        while (locblTypes.size() < index + 1) {
            locblTypes.bdd(null);
        }
        locblTypes.set(index, type);
    }

    /**
     * Generbtes the instruction to lobd the given locbl vbribble on the stbck.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     */
    public void lobdLocbl(finbl int locbl) {
        lobdInsn(getLocblType(locbl), locbl);
    }

    /**
     * Generbtes the instruction to lobd the given locbl vbribble on the stbck.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     * @pbrbm type
     *            the type of this locbl vbribble.
     */
    public void lobdLocbl(finbl int locbl, finbl Type type) {
        setLocblType(locbl, type);
        lobdInsn(type, locbl);
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in the given locbl
     * vbribble.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     */
    public void storeLocbl(finbl int locbl) {
        storeInsn(getLocblType(locbl), locbl);
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in the given locbl
     * vbribble.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     * @pbrbm type
     *            the type of this locbl vbribble.
     */
    public void storeLocbl(finbl int locbl, finbl Type type) {
        setLocblType(locbl, type);
        storeInsn(type, locbl);
    }

    /**
     * Generbtes the instruction to lobd bn element from bn brrby.
     *
     * @pbrbm type
     *            the type of the brrby element to be lobded.
     */
    public void brrbyLobd(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IALOAD));
    }

    /**
     * Generbtes the instruction to store bn element in bn brrby.
     *
     * @pbrbm type
     *            the type of the brrby element to be stored.
     */
    public void brrbyStore(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IASTORE));
    }

    // ------------------------------------------------------------------------
    // Instructions to mbnbge the stbck
    // ------------------------------------------------------------------------

    /**
     * Generbtes b POP instruction.
     */
    public void pop() {
        mv.visitInsn(Opcodes.POP);
    }

    /**
     * Generbtes b POP2 instruction.
     */
    public void pop2() {
        mv.visitInsn(Opcodes.POP2);
    }

    /**
     * Generbtes b DUP instruction.
     */
    public void dup() {
        mv.visitInsn(Opcodes.DUP);
    }

    /**
     * Generbtes b DUP2 instruction.
     */
    public void dup2() {
        mv.visitInsn(Opcodes.DUP2);
    }

    /**
     * Generbtes b DUP_X1 instruction.
     */
    public void dupX1() {
        mv.visitInsn(Opcodes.DUP_X1);
    }

    /**
     * Generbtes b DUP_X2 instruction.
     */
    public void dupX2() {
        mv.visitInsn(Opcodes.DUP_X2);
    }

    /**
     * Generbtes b DUP2_X1 instruction.
     */
    public void dup2X1() {
        mv.visitInsn(Opcodes.DUP2_X1);
    }

    /**
     * Generbtes b DUP2_X2 instruction.
     */
    public void dup2X2() {
        mv.visitInsn(Opcodes.DUP2_X2);
    }

    /**
     * Generbtes b SWAP instruction.
     */
    public void swbp() {
        mv.visitInsn(Opcodes.SWAP);
    }

    /**
     * Generbtes the instructions to swbp the top two stbck vblues.
     *
     * @pbrbm prev
     *            type of the top - 1 stbck vblue.
     * @pbrbm type
     *            type of the top stbck vblue.
     */
    public void swbp(finbl Type prev, finbl Type type) {
        if (type.getSize() == 1) {
            if (prev.getSize() == 1) {
                swbp(); // sbme bs dupX1(), pop();
            } else {
                dupX2();
                pop();
            }
        } else {
            if (prev.getSize() == 1) {
                dup2X1();
                pop2();
            } else {
                dup2X2();
                pop2();
            }
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to do mbthembticbl bnd logicbl operbtions
    // ------------------------------------------------------------------------

    /**
     * Generbtes the instruction to do the specified mbthembticbl or logicbl
     * operbtion.
     *
     * @pbrbm op
     *            b mbthembticbl or logicbl operbtion. Must be one of ADD, SUB,
     *            MUL, DIV, REM, NEG, SHL, SHR, USHR, AND, OR, XOR.
     * @pbrbm type
     *            the type of the operbnd(s) for this operbtion.
     */
    public void mbth(finbl int op, finbl Type type) {
        mv.visitInsn(type.getOpcode(op));
    }

    /**
     * Generbtes the instructions to compute the bitwise negbtion of the top
     * stbck vblue.
     */
    public void not() {
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.IXOR);
    }

    /**
     * Generbtes the instruction to increment the given locbl vbribble.
     *
     * @pbrbm locbl
     *            the locbl vbribble to be incremented.
     * @pbrbm bmount
     *            the bmount by which the locbl vbribble must be incremented.
     */
    public void iinc(finbl int locbl, finbl int bmount) {
        mv.visitIincInsn(locbl, bmount);
    }

    /**
     * Generbtes the instructions to cbst b numericbl vblue from one type to
     * bnother.
     *
     * @pbrbm from
     *            the type of the top stbck vblue
     * @pbrbm to
     *            the type into which this vblue must be cbst.
     */
    public void cbst(finbl Type from, finbl Type to) {
        if (from != to) {
            if (from == Type.DOUBLE_TYPE) {
                if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.D2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.D2L);
                } else {
                    mv.visitInsn(Opcodes.D2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else if (from == Type.FLOAT_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.F2D);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.F2L);
                } else {
                    mv.visitInsn(Opcodes.F2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else if (from == Type.LONG_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.L2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.L2F);
                } else {
                    mv.visitInsn(Opcodes.L2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else {
                if (to == Type.BYTE_TYPE) {
                    mv.visitInsn(Opcodes.I2B);
                } else if (to == Type.CHAR_TYPE) {
                    mv.visitInsn(Opcodes.I2C);
                } else if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.I2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.I2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.I2L);
                } else if (to == Type.SHORT_TYPE) {
                    mv.visitInsn(Opcodes.I2S);
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to do boxing bnd unboxing operbtions
    // ------------------------------------------------------------------------

    privbte stbtic Type getBoxedType(finbl Type type) {
        switch (type.getSort()) {
        cbse Type.BYTE:
            return BYTE_TYPE;
        cbse Type.BOOLEAN:
            return BOOLEAN_TYPE;
        cbse Type.SHORT:
            return SHORT_TYPE;
        cbse Type.CHAR:
            return CHARACTER_TYPE;
        cbse Type.INT:
            return INTEGER_TYPE;
        cbse Type.FLOAT:
            return FLOAT_TYPE;
        cbse Type.LONG:
            return LONG_TYPE;
        cbse Type.DOUBLE:
            return DOUBLE_TYPE;
        }
        return type;
    }

    /**
     * Generbtes the instructions to box the top stbck vblue. This vblue is
     * replbced by its boxed equivblent on top of the stbck.
     *
     * @pbrbm type
     *            the type of the top stbck vblue.
     */
    public void box(finbl Type type) {
        if (type.getSort() == Type.OBJECT || type.getSort() == Type.ARRAY) {
            return;
        }
        if (type == Type.VOID_TYPE) {
            push((String) null);
        } else {
            Type boxed = getBoxedType(type);
            newInstbnce(boxed);
            if (type.getSize() == 2) {
                // Pp -> Ppo -> oPpo -> ooPpo -> ooPp -> o
                dupX2();
                dupX2();
                pop();
            } else {
                // p -> po -> opo -> oop -> o
                dupX1();
                swbp();
            }
            invokeConstructor(boxed, new Method("<init>", Type.VOID_TYPE,
                    new Type[] { type }));
        }
    }

    /**
     * Generbtes the instructions to box the top stbck vblue using Jbvb 5's
     * vblueOf() method. This vblue is replbced by its boxed equivblent on top
     * of the stbck.
     *
     * @pbrbm type
     *            the type of the top stbck vblue.
     */
    public void vblueOf(finbl Type type) {
        if (type.getSort() == Type.OBJECT || type.getSort() == Type.ARRAY) {
            return;
        }
        if (type == Type.VOID_TYPE) {
            push((String) null);
        } else {
            Type boxed = getBoxedType(type);
            invokeStbtic(boxed, new Method("vblueOf", boxed,
                    new Type[] { type }));
        }
    }

    /**
     * Generbtes the instructions to unbox the top stbck vblue. This vblue is
     * replbced by its unboxed equivblent on top of the stbck.
     *
     * @pbrbm type
     *            the type of the top stbck vblue.
     */
    public void unbox(finbl Type type) {
        Type t = NUMBER_TYPE;
        Method sig = null;
        switch (type.getSort()) {
        cbse Type.VOID:
            return;
        cbse Type.CHAR:
            t = CHARACTER_TYPE;
            sig = CHAR_VALUE;
            brebk;
        cbse Type.BOOLEAN:
            t = BOOLEAN_TYPE;
            sig = BOOLEAN_VALUE;
            brebk;
        cbse Type.DOUBLE:
            sig = DOUBLE_VALUE;
            brebk;
        cbse Type.FLOAT:
            sig = FLOAT_VALUE;
            brebk;
        cbse Type.LONG:
            sig = LONG_VALUE;
            brebk;
        cbse Type.INT:
        cbse Type.SHORT:
        cbse Type.BYTE:
            sig = INT_VALUE;
        }
        if (sig == null) {
            checkCbst(type);
        } else {
            checkCbst(t);
            invokeVirtubl(t, sig);
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to jump to other instructions
    // ------------------------------------------------------------------------

    /**
     * Crebtes b new {@link Lbbel}.
     *
     * @return b new {@link Lbbel}.
     */
    public Lbbel newLbbel() {
        return new Lbbel();
    }

    /**
     * Mbrks the current code position with the given lbbel.
     *
     * @pbrbm lbbel
     *            b lbbel.
     */
    public void mbrk(finbl Lbbel lbbel) {
        mv.visitLbbel(lbbel);
    }

    /**
     * Mbrks the current code position with b new lbbel.
     *
     * @return the lbbel thbt wbs crebted to mbrk the current code position.
     */
    public Lbbel mbrk() {
        Lbbel lbbel = new Lbbel();
        mv.visitLbbel(lbbel);
        return lbbel;
    }

    /**
     * Generbtes the instructions to jump to b lbbel bbsed on the compbrison of
     * the top two stbck vblues.
     *
     * @pbrbm type
     *            the type of the top two stbck vblues.
     * @pbrbm mode
     *            how these vblues must be compbred. One of EQ, NE, LT, GE, GT,
     *            LE.
     * @pbrbm lbbel
     *            where to jump if the compbrison result is <tt>true</tt>.
     */
    public void ifCmp(finbl Type type, finbl int mode, finbl Lbbel lbbel) {
        switch (type.getSort()) {
        cbse Type.LONG:
            mv.visitInsn(Opcodes.LCMP);
            brebk;
        cbse Type.DOUBLE:
            mv.visitInsn(mode == GE || mode == GT ? Opcodes.DCMPL
                    : Opcodes.DCMPG);
            brebk;
        cbse Type.FLOAT:
            mv.visitInsn(mode == GE || mode == GT ? Opcodes.FCMPL
                    : Opcodes.FCMPG);
            brebk;
        cbse Type.ARRAY:
        cbse Type.OBJECT:
            switch (mode) {
            cbse EQ:
                mv.visitJumpInsn(Opcodes.IF_ACMPEQ, lbbel);
                return;
            cbse NE:
                mv.visitJumpInsn(Opcodes.IF_ACMPNE, lbbel);
                return;
            }
            throw new IllegblArgumentException("Bbd compbrison for type "
                    + type);
        defbult:
            int intOp = -1;
            switch (mode) {
            cbse EQ:
                intOp = Opcodes.IF_ICMPEQ;
                brebk;
            cbse NE:
                intOp = Opcodes.IF_ICMPNE;
                brebk;
            cbse GE:
                intOp = Opcodes.IF_ICMPGE;
                brebk;
            cbse LT:
                intOp = Opcodes.IF_ICMPLT;
                brebk;
            cbse LE:
                intOp = Opcodes.IF_ICMPLE;
                brebk;
            cbse GT:
                intOp = Opcodes.IF_ICMPGT;
                brebk;
            }
            mv.visitJumpInsn(intOp, lbbel);
            return;
        }
        mv.visitJumpInsn(mode, lbbel);
    }

    /**
     * Generbtes the instructions to jump to b lbbel bbsed on the compbrison of
     * the top two integer stbck vblues.
     *
     * @pbrbm mode
     *            how these vblues must be compbred. One of EQ, NE, LT, GE, GT,
     *            LE.
     * @pbrbm lbbel
     *            where to jump if the compbrison result is <tt>true</tt>.
     */
    public void ifICmp(finbl int mode, finbl Lbbel lbbel) {
        ifCmp(Type.INT_TYPE, mode, lbbel);
    }

    /**
     * Generbtes the instructions to jump to b lbbel bbsed on the compbrison of
     * the top integer stbck vblue with zero.
     *
     * @pbrbm mode
     *            how these vblues must be compbred. One of EQ, NE, LT, GE, GT,
     *            LE.
     * @pbrbm lbbel
     *            where to jump if the compbrison result is <tt>true</tt>.
     */
    public void ifZCmp(finbl int mode, finbl Lbbel lbbel) {
        mv.visitJumpInsn(mode, lbbel);
    }

    /**
     * Generbtes the instruction to jump to the given lbbel if the top stbck
     * vblue is null.
     *
     * @pbrbm lbbel
     *            where to jump if the condition is <tt>true</tt>.
     */
    public void ifNull(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFNULL, lbbel);
    }

    /**
     * Generbtes the instruction to jump to the given lbbel if the top stbck
     * vblue is not null.
     *
     * @pbrbm lbbel
     *            where to jump if the condition is <tt>true</tt>.
     */
    public void ifNonNull(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFNONNULL, lbbel);
    }

    /**
     * Generbtes the instruction to jump to the given lbbel.
     *
     * @pbrbm lbbel
     *            where to jump if the condition is <tt>true</tt>.
     */
    public void goTo(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.GOTO, lbbel);
    }

    /**
     * Generbtes b RET instruction.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by
     *            {@link LocblVbribblesSorter#newLocbl(Type) newLocbl()}.
     */
    public void ret(finbl int locbl) {
        mv.visitVbrInsn(Opcodes.RET, locbl);
    }

    /**
     * Generbtes the instructions for b switch stbtement.
     *
     * @pbrbm keys
     *            the switch cbse keys.
     * @pbrbm generbtor
     *            b generbtor to generbte the code for the switch cbses.
     */
    public void tbbleSwitch(finbl int[] keys,
            finbl TbbleSwitchGenerbtor generbtor) {
        flobt density;
        if (keys.length == 0) {
            density = 0;
        } else {
            density = (flobt) keys.length
                    / (keys[keys.length - 1] - keys[0] + 1);
        }
        tbbleSwitch(keys, generbtor, density >= 0.5f);
    }

    /**
     * Generbtes the instructions for b switch stbtement.
     *
     * @pbrbm keys
     *            the switch cbse keys.
     * @pbrbm generbtor
     *            b generbtor to generbte the code for the switch cbses.
     * @pbrbm useTbble
     *            <tt>true</tt> to use b TABLESWITCH instruction, or
     *            <tt>fblse</tt> to use b LOOKUPSWITCH instruction.
     */
    public void tbbleSwitch(finbl int[] keys,
            finbl TbbleSwitchGenerbtor generbtor, finbl boolebn useTbble) {
        for (int i = 1; i < keys.length; ++i) {
            if (keys[i] < keys[i - 1]) {
                throw new IllegblArgumentException(
                        "keys must be sorted bscending");
            }
        }
        Lbbel def = newLbbel();
        Lbbel end = newLbbel();
        if (keys.length > 0) {
            int len = keys.length;
            int min = keys[0];
            int mbx = keys[len - 1];
            int rbnge = mbx - min + 1;
            if (useTbble) {
                Lbbel[] lbbels = new Lbbel[rbnge];
                Arrbys.fill(lbbels, def);
                for (int i = 0; i < len; ++i) {
                    lbbels[keys[i] - min] = newLbbel();
                }
                mv.visitTbbleSwitchInsn(min, mbx, def, lbbels);
                for (int i = 0; i < rbnge; ++i) {
                    Lbbel lbbel = lbbels[i];
                    if (lbbel != def) {
                        mbrk(lbbel);
                        generbtor.generbteCbse(i + min, end);
                    }
                }
            } else {
                Lbbel[] lbbels = new Lbbel[len];
                for (int i = 0; i < len; ++i) {
                    lbbels[i] = newLbbel();
                }
                mv.visitLookupSwitchInsn(def, keys, lbbels);
                for (int i = 0; i < len; ++i) {
                    mbrk(lbbels[i]);
                    generbtor.generbteCbse(keys[i], end);
                }
            }
        }
        mbrk(def);
        generbtor.generbteDefbult();
        mbrk(end);
    }

    /**
     * Generbtes the instruction to return the top stbck vblue to the cbller.
     */
    public void returnVblue() {
        mv.visitInsn(returnType.getOpcode(Opcodes.IRETURN));
    }

    // ------------------------------------------------------------------------
    // Instructions to lobd bnd store fields
    // ------------------------------------------------------------------------

    /**
     * Generbtes b get field or set field instruction.
     *
     * @pbrbm opcode
     *            the instruction's opcode.
     * @pbrbm ownerType
     *            the clbss in which the field is defined.
     * @pbrbm nbme
     *            the nbme of the field.
     * @pbrbm fieldType
     *            the type of the field.
     */
    privbte void fieldInsn(finbl int opcode, finbl Type ownerType,
            finbl String nbme, finbl Type fieldType) {
        mv.visitFieldInsn(opcode, ownerType.getInternblNbme(), nbme,
                fieldType.getDescriptor());
    }

    /**
     * Generbtes the instruction to push the vblue of b stbtic field on the
     * stbck.
     *
     * @pbrbm owner
     *            the clbss in which the field is defined.
     * @pbrbm nbme
     *            the nbme of the field.
     * @pbrbm type
     *            the type of the field.
     */
    public void getStbtic(finbl Type owner, finbl String nbme, finbl Type type) {
        fieldInsn(Opcodes.GETSTATIC, owner, nbme, type);
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in b stbtic field.
     *
     * @pbrbm owner
     *            the clbss in which the field is defined.
     * @pbrbm nbme
     *            the nbme of the field.
     * @pbrbm type
     *            the type of the field.
     */
    public void putStbtic(finbl Type owner, finbl String nbme, finbl Type type) {
        fieldInsn(Opcodes.PUTSTATIC, owner, nbme, type);
    }

    /**
     * Generbtes the instruction to push the vblue of b non stbtic field on the
     * stbck.
     *
     * @pbrbm owner
     *            the clbss in which the field is defined.
     * @pbrbm nbme
     *            the nbme of the field.
     * @pbrbm type
     *            the type of the field.
     */
    public void getField(finbl Type owner, finbl String nbme, finbl Type type) {
        fieldInsn(Opcodes.GETFIELD, owner, nbme, type);
    }

    /**
     * Generbtes the instruction to store the top stbck vblue in b non stbtic
     * field.
     *
     * @pbrbm owner
     *            the clbss in which the field is defined.
     * @pbrbm nbme
     *            the nbme of the field.
     * @pbrbm type
     *            the type of the field.
     */
    public void putField(finbl Type owner, finbl String nbme, finbl Type type) {
        fieldInsn(Opcodes.PUTFIELD, owner, nbme, type);
    }

    // ------------------------------------------------------------------------
    // Instructions to invoke methods
    // ------------------------------------------------------------------------

    /**
     * Generbtes bn invoke method instruction.
     *
     * @pbrbm opcode
     *            the instruction's opcode.
     * @pbrbm type
     *            the clbss in which the method is defined.
     * @pbrbm method
     *            the method to be invoked.
     */
    privbte void invokeInsn(finbl int opcode, finbl Type type,
            finbl Method method, finbl boolebn itf) {
        String owner = type.getSort() == Type.ARRAY ? type.getDescriptor()
                : type.getInternblNbme();
        mv.visitMethodInsn(opcode, owner, method.getNbme(),
                method.getDescriptor(), itf);
    }

    /**
     * Generbtes the instruction to invoke b normbl method.
     *
     * @pbrbm owner
     *            the clbss in which the method is defined.
     * @pbrbm method
     *            the method to be invoked.
     */
    public void invokeVirtubl(finbl Type owner, finbl Method method) {
        invokeInsn(Opcodes.INVOKEVIRTUAL, owner, method, fblse);
    }

    /**
     * Generbtes the instruction to invoke b constructor.
     *
     * @pbrbm type
     *            the clbss in which the constructor is defined.
     * @pbrbm method
     *            the constructor to be invoked.
     */
    public void invokeConstructor(finbl Type type, finbl Method method) {
        invokeInsn(Opcodes.INVOKESPECIAL, type, method, fblse);
    }

    /**
     * Generbtes the instruction to invoke b stbtic method.
     *
     * @pbrbm owner
     *            the clbss in which the method is defined.
     * @pbrbm method
     *            the method to be invoked.
     */
    public void invokeStbtic(finbl Type owner, finbl Method method) {
        invokeInsn(Opcodes.INVOKESTATIC, owner, method, fblse);
    }

    /**
     * Generbtes the instruction to invoke bn interfbce method.
     *
     * @pbrbm owner
     *            the clbss in which the method is defined.
     * @pbrbm method
     *            the method to be invoked.
     */
    public void invokeInterfbce(finbl Type owner, finbl Method method) {
        invokeInsn(Opcodes.INVOKEINTERFACE, owner, method, true);
    }

    /**
     * Generbtes bn invokedynbmic instruction.
     *
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm bsm
     *            the bootstrbp method.
     * @pbrbm bsmArgs
     *            the bootstrbp method constbnt brguments. Ebch brgument must be
     *            bn {@link Integer}, {@link Flobt}, {@link Long},
     *            {@link Double}, {@link String}, {@link Type} or {@link Hbndle}
     *            vblue. This method is bllowed to modify the content of the
     *            brrby so b cbller should expect thbt this brrby mby chbnge.
     */
    public void invokeDynbmic(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
    }

    // ------------------------------------------------------------------------
    // Instructions to crebte objects bnd brrbys
    // ------------------------------------------------------------------------

    /**
     * Generbtes b type dependent instruction.
     *
     * @pbrbm opcode
     *            the instruction's opcode.
     * @pbrbm type
     *            the instruction's operbnd.
     */
    privbte void typeInsn(finbl int opcode, finbl Type type) {
        mv.visitTypeInsn(opcode, type.getInternblNbme());
    }

    /**
     * Generbtes the instruction to crebte b new object.
     *
     * @pbrbm type
     *            the clbss of the object to be crebted.
     */
    public void newInstbnce(finbl Type type) {
        typeInsn(Opcodes.NEW, type);
    }

    /**
     * Generbtes the instruction to crebte b new brrby.
     *
     * @pbrbm type
     *            the type of the brrby elements.
     */
    public void newArrby(finbl Type type) {
        int typ;
        switch (type.getSort()) {
        cbse Type.BOOLEAN:
            typ = Opcodes.T_BOOLEAN;
            brebk;
        cbse Type.CHAR:
            typ = Opcodes.T_CHAR;
            brebk;
        cbse Type.BYTE:
            typ = Opcodes.T_BYTE;
            brebk;
        cbse Type.SHORT:
            typ = Opcodes.T_SHORT;
            brebk;
        cbse Type.INT:
            typ = Opcodes.T_INT;
            brebk;
        cbse Type.FLOAT:
            typ = Opcodes.T_FLOAT;
            brebk;
        cbse Type.LONG:
            typ = Opcodes.T_LONG;
            brebk;
        cbse Type.DOUBLE:
            typ = Opcodes.T_DOUBLE;
            brebk;
        defbult:
            typeInsn(Opcodes.ANEWARRAY, type);
            return;
        }
        mv.visitIntInsn(Opcodes.NEWARRAY, typ);
    }

    // ------------------------------------------------------------------------
    // Miscelbneous instructions
    // ------------------------------------------------------------------------

    /**
     * Generbtes the instruction to compute the length of bn brrby.
     */
    public void brrbyLength() {
        mv.visitInsn(Opcodes.ARRAYLENGTH);
    }

    /**
     * Generbtes the instruction to throw bn exception.
     */
    public void throwException() {
        mv.visitInsn(Opcodes.ATHROW);
    }

    /**
     * Generbtes the instructions to crebte bnd throw bn exception. The
     * exception clbss must hbve b constructor with b single String brgument.
     *
     * @pbrbm type
     *            the clbss of the exception to be thrown.
     * @pbrbm msg
     *            the detbiled messbge of the exception.
     */
    public void throwException(finbl Type type, finbl String msg) {
        newInstbnce(type);
        dup();
        push(msg);
        invokeConstructor(type, Method.getMethod("void <init> (String)"));
        throwException();
    }

    /**
     * Generbtes the instruction to check thbt the top stbck vblue is of the
     * given type.
     *
     * @pbrbm type
     *            b clbss or interfbce type.
     */
    public void checkCbst(finbl Type type) {
        if (!type.equbls(OBJECT_TYPE)) {
            typeInsn(Opcodes.CHECKCAST, type);
        }
    }

    /**
     * Generbtes the instruction to test if the top stbck vblue is of the given
     * type.
     *
     * @pbrbm type
     *            b clbss or interfbce type.
     */
    public void instbnceOf(finbl Type type) {
        typeInsn(Opcodes.INSTANCEOF, type);
    }

    /**
     * Generbtes the instruction to get the monitor of the top stbck vblue.
     */
    public void monitorEnter() {
        mv.visitInsn(Opcodes.MONITORENTER);
    }

    /**
     * Generbtes the instruction to relebse the monitor of the top stbck vblue.
     */
    public void monitorExit() {
        mv.visitInsn(Opcodes.MONITOREXIT);
    }

    // ------------------------------------------------------------------------
    // Non instructions
    // ------------------------------------------------------------------------

    /**
     * Mbrks the end of the visited method.
     */
    public void endMethod() {
        if ((bccess & Opcodes.ACC_ABSTRACT) == 0) {
            mv.visitMbxs(0, 0);
        }
        mv.visitEnd();
    }

    /**
     * Mbrks the stbrt of bn exception hbndler.
     *
     * @pbrbm stbrt
     *            beginning of the exception hbndler's scope (inclusive).
     * @pbrbm end
     *            end of the exception hbndler's scope (exclusive).
     * @pbrbm exception
     *            internbl nbme of the type of exceptions hbndled by the
     *            hbndler.
     */
    public void cbtchException(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Type exception) {
        if (exception == null) {
            mv.visitTryCbtchBlock(stbrt, end, mbrk(), null);
        } else {
            mv.visitTryCbtchBlock(stbrt, end, mbrk(),
                    exception.getInternblNbme());
        }
    }
}
