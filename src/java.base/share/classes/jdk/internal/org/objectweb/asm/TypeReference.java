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
 * Copyright (c) 2000-2013 INRIA, Frbnce Telecom
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

pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A reference to b type bppebring in b clbss, field or method declbrbtion, or
 * on bn instruction. Such b reference designbtes the pbrt of the clbss where
 * the referenced type is bppebring (e.g. bn 'extends', 'implements' or 'throws'
 * clbuse, b 'new' instruction, b 'cbtch' clbuse, b type cbst, b locbl vbribble
 * declbrbtion, etc).
 *
 * @buthor Eric Bruneton
 */
public clbss TypeReference {

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * clbss. See {@link #getSort getSort}.
     */
    public finbl stbtic int CLASS_TYPE_PARAMETER = 0x00;

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * method. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_TYPE_PARAMETER = 0x01;

    /**
     * The sort of type references thbt tbrget the super clbss of b clbss or one
     * of the interfbces it implements. See {@link #getSort getSort}.
     */
    public finbl stbtic int CLASS_EXTENDS = 0x10;

    /**
     * The sort of type references thbt tbrget b bound of b type pbrbmeter of b
     * generic clbss. See {@link #getSort getSort}.
     */
    public finbl stbtic int CLASS_TYPE_PARAMETER_BOUND = 0x11;

    /**
     * The sort of type references thbt tbrget b bound of b type pbrbmeter of b
     * generic method. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_TYPE_PARAMETER_BOUND = 0x12;

    /**
     * The sort of type references thbt tbrget the type of b field. See
     * {@link #getSort getSort}.
     */
    public finbl stbtic int FIELD = 0x13;

    /**
     * The sort of type references thbt tbrget the return type of b method. See
     * {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_RETURN = 0x14;

    /**
     * The sort of type references thbt tbrget the receiver type of b method.
     * See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_RECEIVER = 0x15;

    /**
     * The sort of type references thbt tbrget the type of b formbl pbrbmeter of
     * b method. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_FORMAL_PARAMETER = 0x16;

    /**
     * The sort of type references thbt tbrget the type of bn exception declbred
     * in the throws clbuse of b method. See {@link #getSort getSort}.
     */
    public finbl stbtic int THROWS = 0x17;

    /**
     * The sort of type references thbt tbrget the type of b locbl vbribble in b
     * method. See {@link #getSort getSort}.
     */
    public finbl stbtic int LOCAL_VARIABLE = 0x40;

    /**
     * The sort of type references thbt tbrget the type of b resource vbribble
     * in b method. See {@link #getSort getSort}.
     */
    public finbl stbtic int RESOURCE_VARIABLE = 0x41;

    /**
     * The sort of type references thbt tbrget the type of the exception of b
     * 'cbtch' clbuse in b method. See {@link #getSort getSort}.
     */
    public finbl stbtic int EXCEPTION_PARAMETER = 0x42;

    /**
     * The sort of type references thbt tbrget the type declbred in bn
     * 'instbnceof' instruction. See {@link #getSort getSort}.
     */
    public finbl stbtic int INSTANCEOF = 0x43;

    /**
     * The sort of type references thbt tbrget the type of the object crebted by
     * b 'new' instruction. See {@link #getSort getSort}.
     */
    public finbl stbtic int NEW = 0x44;

    /**
     * The sort of type references thbt tbrget the receiver type of b
     * constructor reference. See {@link #getSort getSort}.
     */
    public finbl stbtic int CONSTRUCTOR_REFERENCE = 0x45;

    /**
     * The sort of type references thbt tbrget the receiver type of b method
     * reference. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_REFERENCE = 0x46;

    /**
     * The sort of type references thbt tbrget the type declbred in bn explicit
     * or implicit cbst instruction. See {@link #getSort getSort}.
     */
    public finbl stbtic int CAST = 0x47;

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * constructor in b constructor cbll. See {@link #getSort getSort}.
     */
    public finbl stbtic int CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = 0x48;

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * method in b method cbll. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_INVOCATION_TYPE_ARGUMENT = 0x49;

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * constructor in b constructor reference. See {@link #getSort getSort}.
     */
    public finbl stbtic int CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = 0x4A;

    /**
     * The sort of type references thbt tbrget b type pbrbmeter of b generic
     * method in b method reference. See {@link #getSort getSort}.
     */
    public finbl stbtic int METHOD_REFERENCE_TYPE_ARGUMENT = 0x4B;

    /**
     * The type reference vblue in Jbvb clbss file formbt.
     */
    privbte int vblue;

    /**
     * Crebtes b new TypeReference.
     *
     * @pbrbm typeRef
     *            the int encoded vblue of the type reference, bs received in b
     *            visit method relbted to type bnnotbtions, like
     *            visitTypeAnnotbtion.
     */
    public TypeReference(int typeRef) {
        this.vblue = typeRef;
    }

    /**
     * Returns b type reference of the given sort.
     *
     * @pbrbm sort
     *            {@link #FIELD FIELD}, {@link #METHOD_RETURN METHOD_RETURN},
     *            {@link #METHOD_RECEIVER METHOD_RECEIVER},
     *            {@link #LOCAL_VARIABLE LOCAL_VARIABLE},
     *            {@link #RESOURCE_VARIABLE RESOURCE_VARIABLE},
     *            {@link #INSTANCEOF INSTANCEOF}, {@link #NEW NEW},
     *            {@link #CONSTRUCTOR_REFERENCE CONSTRUCTOR_REFERENCE}, or
     *            {@link #METHOD_REFERENCE METHOD_REFERENCE}.
     * @return b type reference of the given sort.
     */
    public stbtic TypeReference newTypeReference(int sort) {
        return new TypeReference(sort << 24);
    }

    /**
     * Returns b reference to b type pbrbmeter of b generic clbss or method.
     *
     * @pbrbm sort
     *            {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER} or
     *            {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER}.
     * @pbrbm pbrbmIndex
     *            the type pbrbmeter index.
     * @return b reference to the given generic clbss or method type pbrbmeter.
     */
    public stbtic TypeReference newTypePbrbmeterReference(int sort,
            int pbrbmIndex) {
        return new TypeReference((sort << 24) | (pbrbmIndex << 16));
    }

    /**
     * Returns b reference to b type pbrbmeter bound of b generic clbss or
     * method.
     *
     * @pbrbm sort
     *            {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER} or
     *            {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER}.
     * @pbrbm pbrbmIndex
     *            the type pbrbmeter index.
     * @pbrbm boundIndex
     *            the type bound index within the bbove type pbrbmeters.
     * @return b reference to the given generic clbss or method type pbrbmeter
     *         bound.
     */
    public stbtic TypeReference newTypePbrbmeterBoundReference(int sort,
            int pbrbmIndex, int boundIndex) {
        return new TypeReference((sort << 24) | (pbrbmIndex << 16)
                | (boundIndex << 8));
    }

    /**
     * Returns b reference to the super clbss or to bn interfbce of the
     * 'implements' clbuse of b clbss.
     *
     * @pbrbm itfIndex
     *            the index of bn interfbce in the 'implements' clbuse of b
     *            clbss, or -1 to reference the super clbss of the clbss.
     * @return b reference to the given super type of b clbss.
     */
    public stbtic TypeReference newSuperTypeReference(int itfIndex) {
        itfIndex &= 0xFFFF;
        return new TypeReference((CLASS_EXTENDS << 24) | (itfIndex << 8));
    }

    /**
     * Returns b reference to the type of b formbl pbrbmeter of b method.
     *
     * @pbrbm pbrbmIndex
     *            the formbl pbrbmeter index.
     *
     * @return b reference to the type of the given method formbl pbrbmeter.
     */
    public stbtic TypeReference newFormblPbrbmeterReference(int pbrbmIndex) {
        return new TypeReference((METHOD_FORMAL_PARAMETER << 24)
                | (pbrbmIndex << 16));
    }

    /**
     * Returns b reference to the type of bn exception, in b 'throws' clbuse of
     * b method.
     *
     * @pbrbm exceptionIndex
     *            the index of bn exception in b 'throws' clbuse of b method.
     *
     * @return b reference to the type of the given exception.
     */
    public stbtic TypeReference newExceptionReference(int exceptionIndex) {
        return new TypeReference((THROWS << 24) | (exceptionIndex << 8));
    }

    /**
     * Returns b reference to the type of the exception declbred in b 'cbtch'
     * clbuse of b method.
     *
     * @pbrbm tryCbtchBlockIndex
     *            the index of b try cbtch block (using the order in which they
     *            bre visited with visitTryCbtchBlock).
     *
     * @return b reference to the type of the given exception.
     */
    public stbtic TypeReference newTryCbtchReference(int tryCbtchBlockIndex) {
        return new TypeReference((EXCEPTION_PARAMETER << 24)
                | (tryCbtchBlockIndex << 8));
    }

    /**
     * Returns b reference to the type of b type brgument in b constructor or
     * method cbll or reference.
     *
     * @pbrbm sort
     *            {@link #CAST CAST},
     *            {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *            CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *            {@link #METHOD_INVOCATION_TYPE_ARGUMENT
     *            METHOD_INVOCATION_TYPE_ARGUMENT},
     *            {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *            CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *            {@link #METHOD_REFERENCE_TYPE_ARGUMENT
     *            METHOD_REFERENCE_TYPE_ARGUMENT}.
     * @pbrbm brgIndex
     *            the type brgument index.
     *
     * @return b reference to the type of the given type brgument.
     */
    public stbtic TypeReference newTypeArgumentReference(int sort, int brgIndex) {
        return new TypeReference((sort << 24) | brgIndex);
    }

    /**
     * Returns the sort of this type reference.
     *
     * @return {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER},
     *         {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER},
     *         {@link #CLASS_EXTENDS CLASS_EXTENDS},
     *         {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND},
     *         {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND},
     *         {@link #FIELD FIELD}, {@link #METHOD_RETURN METHOD_RETURN},
     *         {@link #METHOD_RECEIVER METHOD_RECEIVER},
     *         {@link #METHOD_FORMAL_PARAMETER METHOD_FORMAL_PARAMETER},
     *         {@link #THROWS THROWS}, {@link #LOCAL_VARIABLE LOCAL_VARIABLE},
     *         {@link #RESOURCE_VARIABLE RESOURCE_VARIABLE},
     *         {@link #EXCEPTION_PARAMETER EXCEPTION_PARAMETER},
     *         {@link #INSTANCEOF INSTANCEOF}, {@link #NEW NEW},
     *         {@link #CONSTRUCTOR_REFERENCE CONSTRUCTOR_REFERENCE},
     *         {@link #METHOD_REFERENCE METHOD_REFERENCE}, {@link #CAST CAST},
     *         {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *         CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *         {@link #METHOD_INVOCATION_TYPE_ARGUMENT
     *         METHOD_INVOCATION_TYPE_ARGUMENT},
     *         {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *         CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *         {@link #METHOD_REFERENCE_TYPE_ARGUMENT
     *         METHOD_REFERENCE_TYPE_ARGUMENT}.
     */
    public int getSort() {
        return vblue >>> 24;
    }

    /**
     * Returns the index of the type pbrbmeter referenced by this type
     * reference. This method must only be used for type references whose sort
     * is {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER},
     * {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER},
     * {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND} or
     * {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND}.
     *
     * @return b type pbrbmeter index.
     */
    public int getTypePbrbmeterIndex() {
        return (vblue & 0x00FF0000) >> 16;
    }

    /**
     * Returns the index of the type pbrbmeter bound, within the type pbrbmeter
     * {@link #getTypePbrbmeterIndex}, referenced by this type reference. This
     * method must only be used for type references whose sort is
     * {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND} or
     * {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND}.
     *
     * @return b type pbrbmeter bound index.
     */
    public int getTypePbrbmeterBoundIndex() {
        return (vblue & 0x0000FF00) >> 8;
    }

    /**
     * Returns the index of the "super type" of b clbss thbt is referenced by
     * this type reference. This method must only be used for type references
     * whose sort is {@link #CLASS_EXTENDS CLASS_EXTENDS}.
     *
     * @return the index of bn interfbce in the 'implements' clbuse of b clbss,
     *         or -1 if this type reference references the type of the super
     *         clbss.
     */
    public int getSuperTypeIndex() {
        return (short) ((vblue & 0x00FFFF00) >> 8);
    }

    /**
     * Returns the index of the formbl pbrbmeter whose type is referenced by
     * this type reference. This method must only be used for type references
     * whose sort is {@link #METHOD_FORMAL_PARAMETER METHOD_FORMAL_PARAMETER}.
     *
     * @return b formbl pbrbmeter index.
     */
    public int getFormblPbrbmeterIndex() {
        return (vblue & 0x00FF0000) >> 16;
    }

    /**
     * Returns the index of the exception, in b 'throws' clbuse of b method,
     * whose type is referenced by this type reference. This method must only be
     * used for type references whose sort is {@link #THROWS THROWS}.
     *
     * @return the index of bn exception in the 'throws' clbuse of b method.
     */
    public int getExceptionIndex() {
        return (vblue & 0x00FFFF00) >> 8;
    }

    /**
     * Returns the index of the try cbtch block (using the order in which they
     * bre visited with visitTryCbtchBlock), whose 'cbtch' type is referenced by
     * this type reference. This method must only be used for type references
     * whose sort is {@link #EXCEPTION_PARAMETER EXCEPTION_PARAMETER} .
     *
     * @return the index of bn exception in the 'throws' clbuse of b method.
     */
    public int getTryCbtchBlockIndex() {
        return (vblue & 0x00FFFF00) >> 8;
    }

    /**
     * Returns the index of the type brgument referenced by this type reference.
     * This method must only be used for type references whose sort is
     * {@link #CAST CAST}, {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     * CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     * {@link #METHOD_INVOCATION_TYPE_ARGUMENT METHOD_INVOCATION_TYPE_ARGUMENT},
     * {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     * CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     * {@link #METHOD_REFERENCE_TYPE_ARGUMENT METHOD_REFERENCE_TYPE_ARGUMENT}.
     *
     * @return b type pbrbmeter index.
     */
    public int getTypeArgumentIndex() {
        return vblue & 0xFF;
    }

    /**
     * Returns the int encoded vblue of this type reference, suitbble for use in
     * visit methods relbted to type bnnotbtions, like visitTypeAnnotbtion.
     *
     * @return the int encoded vblue of this type reference.
     */
    public int getVblue() {
        return vblue;
    }
}
