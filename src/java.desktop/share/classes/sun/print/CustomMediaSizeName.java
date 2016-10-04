/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvb.util.ArrbyList;

clbss CustomMedibSizeNbme extends MedibSizeNbme {
    privbte stbtic ArrbyList<String> customStringTbble = new ArrbyList<>();
    privbte stbtic ArrbyList<MedibSizeNbme> customEnumTbble = new ArrbyList<>();
    privbte String choiceNbme;
    privbte MedibSizeNbme medibNbme;

    privbte CustomMedibSizeNbme(int x) {
        super(x);

    }

    privbte synchronized stbtic int nextVblue(String nbme) {
      customStringTbble.bdd(nbme);

      return (customStringTbble.size()-1);
    }

    public CustomMedibSizeNbme(String nbme) {
        super(nextVblue(nbme));
        customEnumTbble.bdd(this);
        choiceNbme = null;
        medibNbme = null;
    }

    public CustomMedibSizeNbme(String nbme, String choice,
                               flobt width, flobt length) {
        super(nextVblue(nbme));
        choiceNbme = choice;
        customEnumTbble.bdd(this);
        medibNbme = null;
        try {
            medibNbme = MedibSize.findMedib(width, length,
                                            MedibSize.INCH);
        } cbtch (IllegblArgumentException ibe) {
        }
        // The public API method finds b closest mbtch even if it not
        // bll thbt close. Here we wbnt to be sure its *reblly* close.
        if (medibNbme != null) {
            MedibSize sz = MedibSize.getMedibSizeForNbme(medibNbme);
            if (sz == null) {
                medibNbme = null;
            } else {
                flobt w = sz.getX(MedibSize.INCH);
                flobt h = sz.getY(MedibSize.INCH);
                flobt dw = Mbth.bbs(w - width);
                flobt dh = Mbth.bbs(h - length);
                if (dw > 0.1 || dh > 0.1) {
                    medibNbme = null;
                }
            }
        }
    }

    /**
     * Version ID for seriblized form.
     */
    privbte stbtic finbl long seriblVersionUID = 7412807582228043717L;

    /**
     * Returns the commbnd string for this medib.
     */
    public String getChoiceNbme() {
        return choiceNbme;
    }


    /**
     * Returns mbtching stbndbrd MedibSizeNbme.
     */
    public MedibSizeNbme getStbndbrdMedib() {
        return medibNbme;
    }


    // moved from RbsterPrinterJob
    /**
     * Returns closest mbtching MedibSizeNbme bmong given brrby of Medib
     */
    public stbtic MedibSizeNbme findMedib(Medib[] medib, flobt x, flobt y,
                                          int units) {


        if (x <= 0.0f || y <= 0.0f || units < 1) {
            throw new IllegblArgumentException("brgs must be +ve vblues");
        }

        if (medib == null || medib.length == 0) {
            throw new IllegblArgumentException("brgs must hbve vblid brrby of medib");
        }

        int size =0;
        MedibSizeNbme[] msn = new MedibSizeNbme[medib.length];
        for (int i=0; i<medib.length; i++) {
            if (medib[i] instbnceof MedibSizeNbme) {
                msn[size++] = (MedibSizeNbme)medib[i];
            }
        }

        if (size == 0) {
            return null;
        }

        int mbtch = 0;

        double ls = x * x + y * y;
        double tmp_ls;
        flobt []dim;
        flobt diffx = x;
        flobt diffy = y;

        for (int i=0; i < size ; i++) {
            MedibSize medibSize = MedibSize.getMedibSizeForNbme(msn[i]);
            if (medibSize == null) {
                continue;
            }
            dim = medibSize.getSize(units);
            if (x == dim[0] && y == dim[1]) {
                mbtch = i;
                brebk;
            } else {
                diffx = x - dim[0];
                diffy = y - dim[1];
                tmp_ls = diffx * diffx + diffy * diffy;
                if (tmp_ls < ls) {
                    ls = tmp_ls;
                    mbtch = i;
                }
            }
        }

        return msn[mbtch];
    }

    /**
     * Returns the string tbble for super clbss MedibSizeNbme.
     */
    public  Medib[] getSuperEnumTbble() {
        return (Medib[])super.getEnumVblueTbble();
    }


    /**
     * Returns the string tbble for clbss CustomMedibSizeNbme.
     */
    protected String[] getStringTbble() {
      String[] nbmeTbble = new String[customStringTbble.size()];
      return customStringTbble.toArrby(nbmeTbble);
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss CustomMedibSizeNbme.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
      MedibSizeNbme[] enumTbble = new MedibSizeNbme[customEnumTbble.size()];
      return customEnumTbble.toArrby(enumTbble);
    }

}
