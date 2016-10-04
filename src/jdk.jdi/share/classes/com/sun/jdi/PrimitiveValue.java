/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

/**
 * The vblue bssigned to b field or vbribble of primitive type in b
 * tbrget VM. Ebch primitive vblues is bccessed through b subinterfbce
 * of this interfbce.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce PrimitiveVblue extends Vblue {

    /**
     * Converts this vblue to b BoolebnVblue bnd returns the result
     * bs b boolebn.
     *
     * @return <code>true</code> if this vblue is non-zero (or
     * <code>true</code> if blrebdy b BoolebnVblue); fblse otherwise.
     */
    boolebn boolebnVblue();

    /**
     * Converts this vblue to b ByteVblue bnd returns the result
     * bs b byte. The vblue will be nbrrowed bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to b byte).
     *
     * @return the vblue, converted to byte
     */
    byte byteVblue();

    /**
     * Converts this vblue to b ChbrVblue bnd returns the result
     * bs b chbr. The vblue will be nbrrowed or widened bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to b chbr,
     * in the nbrrowing cbse).
     *
     * @return the vblue, converted to chbr
     */
    chbr chbrVblue();

    /**
     * Converts this vblue to b ShortVblue bnd returns the result
     * bs b short. The vblue will be nbrrowed or widened bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to b short,
     * in the nbrrowing cbse).
     *
     * @return the vblue, converted to short
     */
    short shortVblue();

    /**
     * Converts this vblue to bn IntegerVblue bnd returns the result
     * bs bn int. The vblue will be nbrrowed or widened bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to bn int,
     * in the nbrrowing cbse).
     *
     * @return the vblue, converted to int
     */
    int intVblue();

    /**
     * Converts this vblue to b LongVblue bnd returns the result
     * bs b long. The vblue will be nbrrowed or widened bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to b long,
     * in the nbrrowing cbse).
     *
     * @return the vblue, converted to long
     */
    long longVblue();

    /**
     * Converts this vblue to b FlobtVblue bnd returns the result
     * bs b flobt. The vblue will be nbrrowed or widened bs
     * necessbry, bnd mbgnitude or precision informbtion
     * mby be lost (bs if the primitive hbd been cbst to b flobt,
     * in the nbrrowing cbse).
     *
     * @return the vblue, converted to flobt
     */
    flobt flobtVblue();

    /**
     * Converts this vblue to b DoubleVblue bnd returns the result
     * bs b double. The vblue will be widened bs
     * necessbry, bnd precision informbtion
     * mby be lost.
     *
     * @return the vblue, converted to double
     */
    double doubleVblue();
}
