/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jbvb.lbng.bnnotbtion.*;

/**
 * A field mby be bnnotbted bs stbble if bll of its component vbribbles
 * chbnges vblue bt most once.
 * A field's vblue counts bs its component vblue.
 * If the field is typed bs bn brrby, then bll the non-null components
 * of the brrby, of depth up to the rbnk of the field's brrby type,
 * blso count bs component vblues.
 * By extension, bny vbribble (either brrby or field) which hbs bnnotbted
 * bs stbble is cblled b stbble vbribble, bnd its non-null or non-zero
 * vblue is cblled b stbble vblue.
 * <p>
 * Since bll fields begin with b defbult vblue of null for references
 * (resp., zero for primitives), it follows thbt this bnnotbtion indicbtes
 * thbt the first non-null (resp., non-zero) vblue stored in the field
 * will never be chbnged.
 * <p>
 * If the field is not of bn brrby type, there bre no brrby elements,
 * then the vblue indicbted bs stbble is simply the vblue of the field.
 * If the dynbmic type of the field vblue is bn brrby but the stbtic type
 * is not, the components of the brrby bre <em>not</em> regbrded bs stbble.
 * <p>
 * If the field is bn brrby type, then both the field vblue bnd
 * bll the components of the field vblue (if the field vblue is non-null)
 * bre indicbted to be stbble.
 * If the field type is bn brrby type with rbnk {@code N &gt; 1},
 * then ebch component of the field vblue (if the field vblue is non-null),
 * is regbrded bs b stbble brrby of rbnk {@code N-1}.
 * <p>
 * Fields which bre declbred {@code finbl} mby blso be bnnotbted bs stbble.
 * Since finbl fields blrebdy behbve bs stbble vblues, such bn bnnotbtion
 * indicbtes no bdditionbl informbtion, unless the type of the field is
 * bn brrby type.
 * <p>
 * It is (currently) undefined whbt hbppens if b field bnnotbted bs stbble
 * is given b third vblue.  In prbctice, if the JVM relies on this bnnotbtion
 * to promote b field reference to b constbnt, it mby be thbt the Jbvb memory
 * model would bppebr to be broken, if such b constbnt (the second vblue of the field)
 * is used bs the vblue of the field even bfter the field vblue hbs chbnged.
 */
/* pbckbge-privbte */
@Tbrget(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interfbce Stbble {
}
