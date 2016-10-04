/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.stbndbrd.MedibTrby;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvb.util.ArrbyList;

clbss CustomMedibTrby extends MedibTrby {
    privbte stbtic ArrbyList<String> customStringTbble = new ArrbyList<>();
    privbte stbtic ArrbyList<MedibTrby> customEnumTbble = new ArrbyList<>();
    privbte String choiceNbme;

    privbte CustomMedibTrby(int x) {
        super(x);

    }

    privbte synchronized stbtic int nextVblue(String nbme) {
      customStringTbble.bdd(nbme);
      return (customStringTbble.size()-1);
    }


    public CustomMedibTrby(String nbme, String choice) {
        super(nextVblue(nbme));
        choiceNbme = choice;
        customEnumTbble.bdd(this);
    }

    /**
     * Version ID for seriblized form.
     */
    privbte stbtic finbl long seriblVersionUID = 1019451298193987013L;


    /**
     * Returns the commbnd string for this medib trby.
     */
    public String getChoiceNbme() {
        return choiceNbme;
    }


    /**
     * Returns the string tbble for super clbss MedibTrby.
     */
    public Medib[] getSuperEnumTbble() {
      return (Medib[])super.getEnumVblueTbble();
    }


    /**
     * Returns the string tbble for clbss CustomMedibTrby.
     */
    protected String[] getStringTbble() {
      String[] nbmeTbble = new String[customStringTbble.size()];
      return customStringTbble.toArrby(nbmeTbble);
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss CustomMedibTrby.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
      MedibTrby[] enumTbble = new MedibTrby[customEnumTbble.size()];
      return customEnumTbble.toArrby(enumTbble);
    }

}
