/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * This clbss stores options how to plbybbck sbmpled dbtb like pitch/tuning,
 * bttenubtion bnd loops.
 * It is stored bs b "wsmp" chunk inside DLS files.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSSbmpleOptions {

    int unitynote;
    short finetune;
    int bttenubtion;
    long options;
    List<DLSSbmpleLoop> loops = new ArrbyList<DLSSbmpleLoop>();

    public int getAttenubtion() {
        return bttenubtion;
    }

    public void setAttenubtion(int bttenubtion) {
        this.bttenubtion = bttenubtion;
    }

    public short getFinetune() {
        return finetune;
    }

    public void setFinetune(short finetune) {
        this.finetune = finetune;
    }

    public List<DLSSbmpleLoop> getLoops() {
        return loops;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public int getUnitynote() {
        return unitynote;
    }

    public void setUnitynote(int unitynote) {
        this.unitynote = unitynote;
    }
}
