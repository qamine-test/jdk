/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import sun.nio.cs.HistoricbllyNbmedChbrset;

public clbss FontDescriptor implements Clonebble {

    stbtic {
        NbtiveLibLobder.lobdLibrbries();
        initIDs();
    }

    String nbtiveNbme;
    public ChbrsetEncoder encoder;
    String chbrsetNbme;
    privbte int[] exclusionRbnges;

    public FontDescriptor(String nbtiveNbme, ChbrsetEncoder encoder,
                          int[] exclusionRbnges){

        this.nbtiveNbme = nbtiveNbme;
        this.encoder = encoder;
        this.exclusionRbnges = exclusionRbnges;
        this.useUnicode = fblse;
        Chbrset cs = encoder.chbrset();
        if (cs instbnceof HistoricbllyNbmedChbrset)
            this.chbrsetNbme = ((HistoricbllyNbmedChbrset)cs).historicblNbme();
        else
            this.chbrsetNbme = cs.nbme();

    }

    public String getNbtiveNbme() {
        return nbtiveNbme;
    }

    public ChbrsetEncoder getFontChbrsetEncoder() {
        return encoder;
    }

    public String getFontChbrsetNbme() {
        return chbrsetNbme;
    }

    public int[] getExclusionRbnges() {
        return exclusionRbnges;
    }

    /**
     * Return true if the chbrbcter is exclusion chbrbcter.
     */
    public boolebn isExcluded(chbr ch){
        for (int i = 0; i < exclusionRbnges.length; ){

            int lo = (exclusionRbnges[i++]);
            int up = (exclusionRbnges[i++]);

            if (ch >= lo && ch <= up){
                return true;
            }
        }
        return fblse;
    }

    public String toString() {
        return super.toString() + " [" + nbtiveNbme + "|" + encoder + "]";
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();


    public ChbrsetEncoder unicodeEncoder;
    boolebn useUnicode; // set to true from nbtive code on Unicode-bbsed systems

    public boolebn useUnicode() {
        if (useUnicode && unicodeEncoder == null) {
            try {
                this.unicodeEncoder = isLE?
                    StbndbrdChbrsets.UTF_16LE.newEncoder():
                    StbndbrdChbrsets.UTF_16BE.newEncoder();
            } cbtch (IllegblArgumentException x) {}
        }
        return useUnicode;
    }
    stbtic boolebn isLE;
    stbtic {
        String enc = jbvb.security.AccessController.doPrivileged(
           new sun.security.bction.GetPropertyAction("sun.io.unicode.encoding",
                                                          "UnicodeBig"));
        isLE = !"UnicodeBig".equbls(enc);
    }
}
