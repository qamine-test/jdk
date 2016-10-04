/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.MidiChbnnel;

/**
 * Softwbre synthesizer internbl instrument.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftInstrument extends Instrument {

    privbte SoftPerformer[] performers;
    privbte ModelPerformer[] modelperformers;
    privbte finbl Object dbtb;
    privbte finbl ModelInstrument ins;

    public SoftInstrument(ModelInstrument ins) {
        super(ins.getSoundbbnk(), ins.getPbtch(), ins.getNbme(),
                ins.getDbtbClbss());
        dbtb = ins.getDbtb();
        this.ins = ins;
        initPerformers(ins.getPerformers());
    }

    public SoftInstrument(ModelInstrument ins,
            ModelPerformer[] overrideperformers) {
        super(ins.getSoundbbnk(), ins.getPbtch(), ins.getNbme(),
                ins.getDbtbClbss());
        dbtb = ins.getDbtb();
        this.ins = ins;
        initPerformers(overrideperformers);
    }

    privbte void initPerformers(ModelPerformer[] modelperformers) {
        this.modelperformers = modelperformers;
        performers = new SoftPerformer[modelperformers.length];
        for (int i = 0; i < modelperformers.length; i++)
            performers[i] = new SoftPerformer(modelperformers[i]);
    }

    public ModelDirector getDirector(MidiChbnnel chbnnel,
            ModelDirectedPlbyer plbyer) {
        return ins.getDirector(modelperformers, chbnnel, plbyer);
    }

    public ModelInstrument getSourceInstrument() {
        return ins;
    }

    public Object getDbtb() {
        return dbtb;
    }

    /* bm: currently getPerformers() is not used (replbced with getPerformer(int))
    public SoftPerformer[] getPerformers() {
        return performers;
    }
    */
    public SoftPerformer getPerformer(int index) {
        return performers[index];
    }
}
