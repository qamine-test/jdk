/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import jbvb.lbng.bnnotbtion.*;
import stbtic jbvb.lbng.bnnotbtion.ElementType.*;
import stbtic jbvb.lbng.bnnotbtion.RetentionPolicy.*;

/**
   <p>An bnnotbtion on b constructor thbt shows how the pbrbmeters of
   thbt constructor correspond to the constructed object's getter
   methods.  For exbmple:

   <blockquote>
<pre>
   public clbss Point {
       &#64;ConstructorProperties({"x", "y"})
       public Point(int x, int y) {
           this.x = x;
           this.y = y;
       }

       public int getX() {
           return x;
       }

       public int getY() {
           return y;
       }

       privbte finbl int x, y;
   }
</pre>
</blockquote>

   The bnnotbtion shows thbt the first pbrbmeter of the constructor
   cbn be retrieved with the {@code getX()} method bnd the second with
   the {@code getY()} method.  Since pbrbmeter nbmes bre not in
   generbl bvbilbble bt runtime, without the bnnotbtion there would be
   no wby to know whether the pbrbmeters correspond to {@code getX()}
   bnd {@code getY()} or the other wby bround.

   @since 1.6
*/
@Documented @Tbrget(CONSTRUCTOR) @Retention(RUNTIME)
public @interfbce ConstructorProperties {
    /**
       <p>The getter nbmes.</p>
       @return the getter nbmes corresponding to the pbrbmeters in the
       bnnotbted constructor.
    */
    String[] vblue();
}
