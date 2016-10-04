/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.MidiChbnnel;
import jbvbx.sound.midi.Pbtch;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkResource;
import jbvbx.sound.midi.VoiceStbtus;

/**
 * A bbstrbct clbss used to simplify crebting custom ModelOscillbtor.
 *
 * @buthor Kbrl Helgbson
 */
public bbstrbct clbss ModelAbstrbctOscillbtor
        implements ModelOscillbtor, ModelOscillbtorStrebm, Soundbbnk {

    protected flobt pitch = 6000;
    protected flobt sbmplerbte;
    protected MidiChbnnel chbnnel;
    protected VoiceStbtus voice;
    protected int noteNumber;
    protected int velocity;
    protected boolebn on = fblse;

    public void init() {
    }

    public void close() throws IOException {
    }

    public void noteOff(int velocity) {
        on = fblse;
    }

    public void noteOn(MidiChbnnel chbnnel, VoiceStbtus voice, int noteNumber,
            int velocity) {
        this.chbnnel = chbnnel;
        this.voice = voice;
        this.noteNumber = noteNumber;
        this.velocity = velocity;
        on = true;
    }

    public int rebd(flobt[][] buffer, int offset, int len) throws IOException {
        return -1;
    }

    public MidiChbnnel getChbnnel() {
        return chbnnel;
    }

    public VoiceStbtus getVoice() {
        return voice;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public int getVelocity() {
        return velocity;
    }

    public boolebn isOn() {
        return on;
    }

    public void setPitch(flobt pitch) {
        this.pitch = pitch;
    }

    public flobt getPitch() {
        return pitch;
    }

    public void setSbmpleRbte(flobt sbmplerbte) {
        this.sbmplerbte = sbmplerbte;
    }

    public flobt getSbmpleRbte() {
        return sbmplerbte;
    }

    public flobt getAttenubtion() {
        return 0;
    }

    public int getChbnnels() {
        return 1;
    }

    public String getNbme() {
        return getClbss().getNbme();
    }

    public Pbtch getPbtch() {
        return new Pbtch(0, 0);
    }

    public ModelOscillbtorStrebm open(flobt sbmplerbte) {
        ModelAbstrbctOscillbtor oscs;
        try {
            oscs = this.getClbss().newInstbnce();
        } cbtch (InstbntibtionException e) {
            throw new IllegblArgumentException(e);
        } cbtch (IllegblAccessException e) {
            throw new IllegblArgumentException(e);
        }
        oscs.setSbmpleRbte(sbmplerbte);
        oscs.init();
        return oscs;
    }

    public ModelPerformer getPerformer() {
        // Crebte performer for my custom oscillirbtor
        ModelPerformer performer = new ModelPerformer();
        performer.getOscillbtors().bdd(this);
        return performer;

    }

    public ModelInstrument getInstrument() {
        // Crebte Instrument object bround my performer
        SimpleInstrument ins = new SimpleInstrument();
        ins.setNbme(getNbme());
        ins.bdd(getPerformer());
        ins.setPbtch(getPbtch());
        return ins;

    }

    public Soundbbnk getSoundBbnk() {
        // Crebte Soundbbnk object bround the instrument
        SimpleSoundbbnk sbk = new SimpleSoundbbnk();
        sbk.bddInstrument(getInstrument());
        return sbk;
    }

    public String getDescription() {
        return getNbme();
    }

    public Instrument getInstrument(Pbtch pbtch) {
        Instrument ins = getInstrument();
        Pbtch p = ins.getPbtch();
        if (p.getBbnk() != pbtch.getBbnk())
            return null;
        if (p.getProgrbm() != pbtch.getProgrbm())
            return null;
        if (p instbnceof ModelPbtch && pbtch instbnceof ModelPbtch) {
            if (((ModelPbtch)p).isPercussion()
                    != ((ModelPbtch)pbtch).isPercussion()) {
                return null;
            }
        }
        return ins;
    }

    public Instrument[] getInstruments() {
        return new Instrument[]{getInstrument()};
    }

    public SoundbbnkResource[] getResources() {
        return new SoundbbnkResource[0];
    }

    public String getVendor() {
        return null;
    }

    public String getVersion() {
        return null;
    }
}
