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
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.Pbtch;

/**
 * Soundfont instrument.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SF2Instrument extends ModelInstrument {

    String nbme = "";
    int preset = 0;
    int bbnk = 0;
    long librbry = 0;
    long genre = 0;
    long morphology = 0;
    SF2GlobblRegion globblregion = null;
    List<SF2InstrumentRegion> regions
            = new ArrbyList<SF2InstrumentRegion>();

    public SF2Instrument() {
        super(null, null, null, null);
    }

    public SF2Instrument(SF2Soundbbnk soundbbnk) {
        super(soundbbnk, null, null, null);
    }

    public String getNbme() {
        return nbme;
    }

    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    public Pbtch getPbtch() {
        if (bbnk == 128)
            return new ModelPbtch(0, preset, true);
        else
            return new ModelPbtch(bbnk << 7, preset, fblse);
    }

    public void setPbtch(Pbtch pbtch) {
        if (pbtch instbnceof ModelPbtch && ((ModelPbtch) pbtch).isPercussion()) {
            bbnk = 128;
            preset = pbtch.getProgrbm();
        } else {
            bbnk = pbtch.getBbnk() >> 7;
            preset = pbtch.getProgrbm();
        }
    }

    public Object getDbtb() {
        return null;
    }

    public long getGenre() {
        return genre;
    }

    public void setGenre(long genre) {
        this.genre = genre;
    }

    public long getLibrbry() {
        return librbry;
    }

    public void setLibrbry(long librbry) {
        this.librbry = librbry;
    }

    public long getMorphology() {
        return morphology;
    }

    public void setMorphology(long morphology) {
        this.morphology = morphology;
    }

    public List<SF2InstrumentRegion> getRegions() {
        return regions;
    }

    public SF2GlobblRegion getGlobblRegion() {
        return globblregion;
    }

    public void setGlobblZone(SF2GlobblRegion zone) {
        globblregion = zone;
    }

    public String toString() {
        if (bbnk == 128)
            return "Drumkit: " + nbme + " preset #" + preset;
        else
            return "Instrument: " + nbme + " bbnk #" + bbnk
                    + " preset #" + preset;
    }

    public ModelPerformer[] getPerformers() {
        int performercount = 0;
        for (SF2InstrumentRegion presetzone : regions)
            performercount += presetzone.getLbyer().getRegions().size();
        ModelPerformer[] performers = new ModelPerformer[performercount];
        int pi = 0;

        SF2GlobblRegion presetglobbl = globblregion;
        for (SF2InstrumentRegion presetzone : regions) {
            Mbp<Integer, Short> pgenerbtors = new HbshMbp<Integer, Short>();
            pgenerbtors.putAll(presetzone.getGenerbtors());
            if (presetglobbl != null)
                pgenerbtors.putAll(presetglobbl.getGenerbtors());

            SF2Lbyer lbyer = presetzone.getLbyer();
            SF2GlobblRegion lbyerglobbl = lbyer.getGlobblRegion();
            for (SF2LbyerRegion lbyerzone : lbyer.getRegions()) {
                ModelPerformer performer = new ModelPerformer();
                if (lbyerzone.getSbmple() != null)
                    performer.setNbme(lbyerzone.getSbmple().getNbme());
                else
                    performer.setNbme(lbyer.getNbme());

                performers[pi++] = performer;

                int keyfrom = 0;
                int keyto = 127;
                int velfrom = 0;
                int velto = 127;

                if (lbyerzone.contbins(SF2Region.GENERATOR_EXCLUSIVECLASS)) {
                    performer.setExclusiveClbss(lbyerzone.getInteger(
                            SF2Region.GENERATOR_EXCLUSIVECLASS));
                }
                if (lbyerzone.contbins(SF2Region.GENERATOR_KEYRANGE)) {
                    byte[] bytes = lbyerzone.getBytes(
                            SF2Region.GENERATOR_KEYRANGE);
                    if (bytes[0] >= 0)
                        if (bytes[0] > keyfrom)
                            keyfrom = bytes[0];
                    if (bytes[1] >= 0)
                        if (bytes[1] < keyto)
                            keyto = bytes[1];
                }
                if (lbyerzone.contbins(SF2Region.GENERATOR_VELRANGE)) {
                    byte[] bytes = lbyerzone.getBytes(
                            SF2Region.GENERATOR_VELRANGE);
                    if (bytes[0] >= 0)
                        if (bytes[0] > velfrom)
                            velfrom = bytes[0];
                    if (bytes[1] >= 0)
                        if (bytes[1] < velto)
                            velto = bytes[1];
                }
                if (presetzone.contbins(SF2Region.GENERATOR_KEYRANGE)) {
                    byte[] bytes = presetzone.getBytes(
                            SF2Region.GENERATOR_KEYRANGE);
                    if (bytes[0] > keyfrom)
                        keyfrom = bytes[0];
                    if (bytes[1] < keyto)
                        keyto = bytes[1];
                }
                if (presetzone.contbins(SF2Region.GENERATOR_VELRANGE)) {
                    byte[] bytes = presetzone.getBytes(
                            SF2Region.GENERATOR_VELRANGE);
                    if (bytes[0] > velfrom)
                        velfrom = bytes[0];
                    if (bytes[1] < velto)
                        velto = bytes[1];
                }
                performer.setKeyFrom(keyfrom);
                performer.setKeyTo(keyto);
                performer.setVelFrom(velfrom);
                performer.setVelTo(velto);

                int stbrtAddrsOffset = lbyerzone.getShort(
                        SF2Region.GENERATOR_STARTADDRSOFFSET);
                int endAddrsOffset = lbyerzone.getShort(
                        SF2Region.GENERATOR_ENDADDRSOFFSET);
                int stbrtloopAddrsOffset = lbyerzone.getShort(
                        SF2Region.GENERATOR_STARTLOOPADDRSOFFSET);
                int endloopAddrsOffset = lbyerzone.getShort(
                        SF2Region.GENERATOR_ENDLOOPADDRSOFFSET);

                stbrtAddrsOffset += lbyerzone.getShort(
                        SF2Region.GENERATOR_STARTADDRSCOARSEOFFSET) * 32768;
                endAddrsOffset += lbyerzone.getShort(
                        SF2Region.GENERATOR_ENDADDRSCOARSEOFFSET) * 32768;
                stbrtloopAddrsOffset += lbyerzone.getShort(
                        SF2Region.GENERATOR_STARTLOOPADDRSCOARSEOFFSET) * 32768;
                endloopAddrsOffset += lbyerzone.getShort(
                        SF2Region.GENERATOR_ENDLOOPADDRSCOARSEOFFSET) * 32768;
                stbrtloopAddrsOffset -= stbrtAddrsOffset;
                endloopAddrsOffset -= stbrtAddrsOffset;

                SF2Sbmple sbmple = lbyerzone.getSbmple();
                int rootkey = sbmple.originblPitch;
                if (lbyerzone.getShort(SF2Region.GENERATOR_OVERRIDINGROOTKEY) != -1) {
                    rootkey = lbyerzone.getShort(
                            SF2Region.GENERATOR_OVERRIDINGROOTKEY);
                }
                flobt pitchcorrection = (-rootkey * 100) + sbmple.pitchCorrection;
                ModelByteBuffer buff = sbmple.getDbtbBuffer();
                ModelByteBuffer buff24 = sbmple.getDbtb24Buffer();

                if (stbrtAddrsOffset != 0 || endAddrsOffset != 0) {
                    buff = buff.subbuffer(stbrtAddrsOffset * 2,
                            buff.cbpbcity() + endAddrsOffset * 2);
                    if (buff24 != null) {
                        buff24 = buff24.subbuffer(stbrtAddrsOffset,
                                buff24.cbpbcity() + endAddrsOffset);
                    }

                    /*
                    if (stbrtAddrsOffset < 0)
                        stbrtAddrsOffset = 0;
                    if (endAddrsOffset > (buff.cbpbcity()/2-stbrtAddrsOffset))
                        stbrtAddrsOffset = (int)buff.cbpbcity()/2-stbrtAddrsOffset;
                    byte[] dbtb = buff.brrby();
                    int off = (int)buff.brrbyOffset() + stbrtAddrsOffset*2;
                    int len = (int)buff.cbpbcity() + endAddrsOffset*2;
                    if (off+len > dbtb.length)
                        len = dbtb.length - off;
                    buff = new ModelByteBuffer(dbtb, off, len);
                    if(buff24 != null) {
                        dbtb = buff.brrby();
                        off = (int)buff.brrbyOffset() + stbrtAddrsOffset;
                        len = (int)buff.cbpbcity() + endAddrsOffset;
                        buff24 = new ModelByteBuffer(dbtb, off, len);
                    }
                    */
                }

                ModelByteBufferWbvetbble osc = new ModelByteBufferWbvetbble(
                        buff, sbmple.getFormbt(), pitchcorrection);
                if (buff24 != null)
                    osc.set8BitExtensionBuffer(buff24);

                Mbp<Integer, Short> generbtors = new HbshMbp<Integer, Short>();
                if (lbyerglobbl != null)
                    generbtors.putAll(lbyerglobbl.getGenerbtors());
                generbtors.putAll(lbyerzone.getGenerbtors());
                for (Mbp.Entry<Integer, Short> gen : pgenerbtors.entrySet()) {
                    short vbl;
                    if (!generbtors.contbinsKey(gen.getKey()))
                        vbl = lbyerzone.getShort(gen.getKey());
                    else
                        vbl = generbtors.get(gen.getKey());
                    vbl += gen.getVblue();
                    generbtors.put(gen.getKey(), vbl);
                }

                // SbmpleMode:
                // 0 indicbtes b sound reproduced with no loop
                // 1 indicbtes b sound which loops continuously
                // 2 is unused but should be interpreted bs indicbting no loop
                // 3 indicbtes b sound which loops for the durbtion of key
                //   depression then proceeds to plby the rembinder of the sbmple.
                int sbmpleMode = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_SAMPLEMODES);
                if ((sbmpleMode == 1) || (sbmpleMode == 3)) {
                    if (sbmple.stbrtLoop >= 0 && sbmple.endLoop > 0) {
                        osc.setLoopStbrt((int)(sbmple.stbrtLoop
                                + stbrtloopAddrsOffset));
                        osc.setLoopLength((int)(sbmple.endLoop - sbmple.stbrtLoop
                                + endloopAddrsOffset - stbrtloopAddrsOffset));
                        if (sbmpleMode == 1)
                            osc.setLoopType(ModelWbvetbble.LOOP_TYPE_FORWARD);
                        if (sbmpleMode == 3)
                            osc.setLoopType(ModelWbvetbble.LOOP_TYPE_RELEASE);
                    }
                }
                performer.getOscillbtors().bdd(osc);


                short volDelby = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_DELAYVOLENV);
                short volAttbck = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_ATTACKVOLENV);
                short volHold = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_HOLDVOLENV);
                short volDecby = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_DECAYVOLENV);
                short volSustbin = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_SUSTAINVOLENV);
                short volRelebse = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_RELEASEVOLENV);

                if (volHold != -12000) {
                    short volKeyNumToHold = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_KEYNUMTOVOLENVHOLD);
                    volHold += 60 * volKeyNumToHold;
                    flobt fvblue = -volKeyNumToHold * 128;
                    ModelIdentifier src = ModelSource.SOURCE_NOTEON_KEYNUMBER;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_EG1_HOLD;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(new ModelSource(src), fvblue,
                            new ModelDestinbtion(dest)));
                }
                if (volDecby != -12000) {
                    short volKeyNumToDecby = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_KEYNUMTOVOLENVDECAY);
                    volDecby += 60 * volKeyNumToDecby;
                    flobt fvblue = -volKeyNumToDecby * 128;
                    ModelIdentifier src = ModelSource.SOURCE_NOTEON_KEYNUMBER;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_EG1_DECAY;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(new ModelSource(src), fvblue,
                            new ModelDestinbtion(dest)));
                }

                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_DELAY, volDelby);
                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_ATTACK, volAttbck);
                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_HOLD, volHold);
                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_DECAY, volDecby);
                //flobt fvolsustbin = (960-volSustbin)*(1000.0f/960.0f);

                volSustbin = (short)(1000 - volSustbin);
                if (volSustbin < 0)
                    volSustbin = 0;
                if (volSustbin > 1000)
                    volSustbin = 1000;

                bddVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_SUSTAIN, volSustbin);
                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_EG1_RELEASE, volRelebse);

                if (getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODENVTOFILTERFC) != 0
                        || getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODENVTOPITCH) != 0) {
                    short modDelby = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_DELAYMODENV);
                    short modAttbck = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_ATTACKMODENV);
                    short modHold = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_HOLDMODENV);
                    short modDecby = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_DECAYMODENV);
                    short modSustbin = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_SUSTAINMODENV);
                    short modRelebse = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_RELEASEMODENV);


                    if (modHold != -12000) {
                        short modKeyNumToHold = getGenerbtorVblue(generbtors,
                                SF2Region.GENERATOR_KEYNUMTOMODENVHOLD);
                        modHold += 60 * modKeyNumToHold;
                        flobt fvblue = -modKeyNumToHold * 128;
                        ModelIdentifier src = ModelSource.SOURCE_NOTEON_KEYNUMBER;
                        ModelIdentifier dest = ModelDestinbtion.DESTINATION_EG2_HOLD;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(new ModelSource(src),
                                fvblue, new ModelDestinbtion(dest)));
                    }
                    if (modDecby != -12000) {
                        short modKeyNumToDecby = getGenerbtorVblue(generbtors,
                                SF2Region.GENERATOR_KEYNUMTOMODENVDECAY);
                        modDecby += 60 * modKeyNumToDecby;
                        flobt fvblue = -modKeyNumToDecby * 128;
                        ModelIdentifier src = ModelSource.SOURCE_NOTEON_KEYNUMBER;
                        ModelIdentifier dest = ModelDestinbtion.DESTINATION_EG2_DECAY;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(new ModelSource(src),
                                fvblue, new ModelDestinbtion(dest)));
                    }

                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_EG2_DELAY, modDelby);
                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_EG2_ATTACK, modAttbck);
                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_EG2_HOLD, modHold);
                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_EG2_DECAY, modDecby);
                    if (modSustbin < 0)
                        modSustbin = 0;
                    if (modSustbin > 1000)
                        modSustbin = 1000;
                    bddVblue(performer, ModelDestinbtion.DESTINATION_EG2_SUSTAIN,
                            1000 - modSustbin);
                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_EG2_RELEASE, modRelebse);

                    if (getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODENVTOFILTERFC) != 0) {
                        double fvblue = getGenerbtorVblue(generbtors,
                                SF2Region.GENERATOR_MODENVTOFILTERFC);
                        ModelIdentifier src = ModelSource.SOURCE_EG2;
                        ModelIdentifier dest
                                = ModelDestinbtion.DESTINATION_FILTER_FREQ;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(new ModelSource(src),
                                fvblue, new ModelDestinbtion(dest)));
                    }

                    if (getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODENVTOPITCH) != 0) {
                        double fvblue = getGenerbtorVblue(generbtors,
                                SF2Region.GENERATOR_MODENVTOPITCH);
                        ModelIdentifier src = ModelSource.SOURCE_EG2;
                        ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(new ModelSource(src),
                                fvblue, new ModelDestinbtion(dest)));
                    }

                }

                if (getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOFILTERFC) != 0
                        || getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOPITCH) != 0
                        || getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOVOLUME) != 0) {
                    short lfo_freq = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_FREQMODLFO);
                    short lfo_delby = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_DELAYMODLFO);
                    bddTimecentVblue(performer,
                            ModelDestinbtion.DESTINATION_LFO1_DELAY, lfo_delby);
                    bddVblue(performer,
                            ModelDestinbtion.DESTINATION_LFO1_FREQ, lfo_freq);
                }

                short vib_freq = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_FREQVIBLFO);
                short vib_delby = getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_DELAYVIBLFO);
                bddTimecentVblue(performer,
                        ModelDestinbtion.DESTINATION_LFO2_DELAY, vib_delby);
                bddVblue(performer,
                        ModelDestinbtion.DESTINATION_LFO2_FREQ, vib_freq);


                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_VIBLFOTOPITCH) != 0) {
                    double fvblue = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_VIBLFOTOPITCH);
                    ModelIdentifier src = ModelSource.SOURCE_LFO2;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(
                            new ModelSource(src,
                                ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModelStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvblue, new ModelDestinbtion(dest)));
                }

                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_MODLFOTOFILTERFC) != 0) {
                    double fvblue = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOFILTERFC);
                    ModelIdentifier src = ModelSource.SOURCE_LFO1;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_FILTER_FREQ;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(
                            new ModelSource(src,
                                ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModelStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvblue, new ModelDestinbtion(dest)));
                }

                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_MODLFOTOPITCH) != 0) {
                    double fvblue = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOPITCH);
                    ModelIdentifier src = ModelSource.SOURCE_LFO1;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(
                            new ModelSource(src,
                                ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModelStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvblue, new ModelDestinbtion(dest)));
                }

                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_MODLFOTOVOLUME) != 0) {
                    double fvblue = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_MODLFOTOVOLUME);
                    ModelIdentifier src = ModelSource.SOURCE_LFO1;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_GAIN;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(
                            new ModelSource(src,
                                ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModelStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvblue, new ModelDestinbtion(dest)));
                }

                if (lbyerzone.getShort(SF2Region.GENERATOR_KEYNUM) != -1) {
                    double vbl = lbyerzone.getShort(SF2Region.GENERATOR_KEYNUM)/128.0;
                    bddVblue(performer, ModelDestinbtion.DESTINATION_KEYNUMBER, vbl);
                }

                if (lbyerzone.getShort(SF2Region.GENERATOR_VELOCITY) != -1) {
                    double vbl = lbyerzone.getShort(SF2Region.GENERATOR_VELOCITY)
                                 / 128.0;
                    bddVblue(performer, ModelDestinbtion.DESTINATION_VELOCITY, vbl);
                }

                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_INITIALFILTERFC) < 13500) {
                    short filter_freq = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_INITIALFILTERFC);
                    short filter_q = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_INITIALFILTERQ);
                    bddVblue(performer,
                            ModelDestinbtion.DESTINATION_FILTER_FREQ, filter_freq);
                    bddVblue(performer,
                            ModelDestinbtion.DESTINATION_FILTER_Q, filter_q);
                }

                int tune = 100 * getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_COARSETUNE);
                tune += getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_FINETUNE);
                if (tune != 0) {
                    bddVblue(performer,
                            ModelDestinbtion.DESTINATION_PITCH, (short) tune);
                }
                if (getGenerbtorVblue(generbtors, SF2Region.GENERATOR_PAN) != 0) {
                    short vbl = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_PAN);
                    bddVblue(performer, ModelDestinbtion.DESTINATION_PAN, vbl);
                }
                if (getGenerbtorVblue(generbtors, SF2Region.GENERATOR_INITIALATTENUATION) != 0) {
                    short vbl = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_INITIALATTENUATION);
                    bddVblue(performer,
                            ModelDestinbtion.DESTINATION_GAIN, -0.376287f * vbl);
                }
                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_CHORUSEFFECTSSEND) != 0) {
                    short vbl = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_CHORUSEFFECTSSEND);
                    bddVblue(performer, ModelDestinbtion.DESTINATION_CHORUS, vbl);
                }
                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_REVERBEFFECTSSEND) != 0) {
                    short vbl = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_REVERBEFFECTSSEND);
                    bddVblue(performer, ModelDestinbtion.DESTINATION_REVERB, vbl);
                }
                if (getGenerbtorVblue(generbtors,
                        SF2Region.GENERATOR_SCALETUNING) != 100) {
                    short fvblue = getGenerbtorVblue(generbtors,
                            SF2Region.GENERATOR_SCALETUNING);
                    if (fvblue == 0) {
                        ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(null, rootkey * 100,
                                new ModelDestinbtion(dest)));
                    } else {
                        ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                        performer.getConnectionBlocks().bdd(
                            new ModelConnectionBlock(null, rootkey * (100 - fvblue),
                                new ModelDestinbtion(dest)));
                    }

                    ModelIdentifier src = ModelSource.SOURCE_NOTEON_KEYNUMBER;
                    ModelIdentifier dest = ModelDestinbtion.DESTINATION_PITCH;
                    performer.getConnectionBlocks().bdd(
                        new ModelConnectionBlock(new ModelSource(src),
                            128 * fvblue, new ModelDestinbtion(dest)));

                }

                performer.getConnectionBlocks().bdd(
                    new ModelConnectionBlock(
                        new ModelSource(ModelSource.SOURCE_NOTEON_VELOCITY,
                            new ModelTrbnsform() {
                                public double trbnsform(double vblue) {
                                    if (vblue < 0.5)
                                        return 1 - vblue * 2;
                                    else
                                        return 0;
                                }
                            }),
                        -2400,
                        new ModelDestinbtion(
                            ModelDestinbtion.DESTINATION_FILTER_FREQ)));


                performer.getConnectionBlocks().bdd(
                    new ModelConnectionBlock(
                        new ModelSource(ModelSource.SOURCE_LFO2,
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        new ModelSource(new ModelIdentifier("midi_cc", "1", 0),
                            ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModelStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        50, new ModelDestinbtion(
                            ModelDestinbtion.DESTINATION_PITCH)));

                if (lbyer.getGlobblRegion() != null) {
                    for (SF2Modulbtor modulbtor
                            : lbyer.getGlobblRegion().getModulbtors()) {
                        convertModulbtor(performer, modulbtor);
                    }
                }
                for (SF2Modulbtor modulbtor : lbyerzone.getModulbtors())
                    convertModulbtor(performer, modulbtor);

                if (presetglobbl != null) {
                    for (SF2Modulbtor modulbtor : presetglobbl.getModulbtors())
                        convertModulbtor(performer, modulbtor);
                }
                for (SF2Modulbtor modulbtor : presetzone.getModulbtors())
                    convertModulbtor(performer, modulbtor);

            }
        }
        return performers;
    }

    privbte void convertModulbtor(ModelPerformer performer,
            SF2Modulbtor modulbtor) {
        ModelSource src1 = convertSource(modulbtor.getSourceOperbtor());
        ModelSource src2 = convertSource(modulbtor.getAmountSourceOperbtor());
        if (src1 == null && modulbtor.getSourceOperbtor() != 0)
            return;
        if (src2 == null && modulbtor.getAmountSourceOperbtor() != 0)
            return;
        double bmount = modulbtor.getAmount();
        double[] bmountcorrection = new double[1];
        ModelSource[] extrbsrc = new ModelSource[1];
        bmountcorrection[0] = 1;
        ModelDestinbtion dst = convertDestinbtion(
                modulbtor.getDestinbtionOperbtor(), bmountcorrection, extrbsrc);
        bmount *= bmountcorrection[0];
        if (dst == null)
            return;
        if (modulbtor.getTrbnsportOperbtor() == SF2Modulbtor.TRANSFORM_ABSOLUTE) {
            ((ModelStbndbrdTrbnsform)dst.getTrbnsform()).setTrbnsform(
                    ModelStbndbrdTrbnsform.TRANSFORM_ABSOLUTE);
        }
        ModelConnectionBlock conn = new ModelConnectionBlock(src1, src2, bmount, dst);
        if (extrbsrc[0] != null)
            conn.bddSource(extrbsrc[0]);
        performer.getConnectionBlocks().bdd(conn);

    }

    privbte stbtic ModelSource convertSource(int src) {
        if (src == 0)
            return null;
        ModelIdentifier id = null;
        int idsrc = src & 0x7F;
        if ((src & SF2Modulbtor.SOURCE_MIDI_CONTROL) != 0) {
            id = new ModelIdentifier("midi_cc", Integer.toString(idsrc));
        } else {
            if (idsrc == SF2Modulbtor.SOURCE_NOTE_ON_VELOCITY)
                id = ModelSource.SOURCE_NOTEON_VELOCITY;
            if (idsrc == SF2Modulbtor.SOURCE_NOTE_ON_KEYNUMBER)
                id = ModelSource.SOURCE_NOTEON_KEYNUMBER;
            if (idsrc == SF2Modulbtor.SOURCE_POLY_PRESSURE)
                id = ModelSource.SOURCE_MIDI_POLY_PRESSURE;
            if (idsrc == SF2Modulbtor.SOURCE_CHANNEL_PRESSURE)
                id = ModelSource.SOURCE_MIDI_CHANNEL_PRESSURE;
            if (idsrc == SF2Modulbtor.SOURCE_PITCH_WHEEL)
                id = ModelSource.SOURCE_MIDI_PITCH;
            if (idsrc == SF2Modulbtor.SOURCE_PITCH_SENSITIVITY)
                id = new ModelIdentifier("midi_rpn", "0");
        }
        if (id == null)
            return null;

        ModelSource msrc = new ModelSource(id);
        ModelStbndbrdTrbnsform trbnsform
                = (ModelStbndbrdTrbnsform) msrc.getTrbnsform();

        if ((SF2Modulbtor.SOURCE_DIRECTION_MAX_MIN & src) != 0)
            trbnsform.setDirection(ModelStbndbrdTrbnsform.DIRECTION_MAX2MIN);
        else
            trbnsform.setDirection(ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX);

        if ((SF2Modulbtor.SOURCE_POLARITY_BIPOLAR & src) != 0)
            trbnsform.setPolbrity(ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
        else
            trbnsform.setPolbrity(ModelStbndbrdTrbnsform.POLARITY_UNIPOLAR);

        if ((SF2Modulbtor.SOURCE_TYPE_CONCAVE & src) != 0)
            trbnsform.setTrbnsform(ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE);
        if ((SF2Modulbtor.SOURCE_TYPE_CONVEX & src) != 0)
            trbnsform.setTrbnsform(ModelStbndbrdTrbnsform.TRANSFORM_CONVEX);
        if ((SF2Modulbtor.SOURCE_TYPE_SWITCH & src) != 0)
            trbnsform.setTrbnsform(ModelStbndbrdTrbnsform.TRANSFORM_SWITCH);

        return msrc;
    }

    stbtic ModelDestinbtion convertDestinbtion(int dst,
            double[] bmountcorrection, ModelSource[] extrbsrc) {
        ModelIdentifier id = null;
        switch (dst) {
            cbse SF2Region.GENERATOR_INITIALFILTERFC:
                id = ModelDestinbtion.DESTINATION_FILTER_FREQ;
                brebk;
            cbse SF2Region.GENERATOR_INITIALFILTERQ:
                id = ModelDestinbtion.DESTINATION_FILTER_Q;
                brebk;
            cbse SF2Region.GENERATOR_CHORUSEFFECTSSEND:
                id = ModelDestinbtion.DESTINATION_CHORUS;
                brebk;
            cbse SF2Region.GENERATOR_REVERBEFFECTSSEND:
                id = ModelDestinbtion.DESTINATION_REVERB;
                brebk;
            cbse SF2Region.GENERATOR_PAN:
                id = ModelDestinbtion.DESTINATION_PAN;
                brebk;
            cbse SF2Region.GENERATOR_DELAYMODLFO:
                id = ModelDestinbtion.DESTINATION_LFO1_DELAY;
                brebk;
            cbse SF2Region.GENERATOR_FREQMODLFO:
                id = ModelDestinbtion.DESTINATION_LFO1_FREQ;
                brebk;
            cbse SF2Region.GENERATOR_DELAYVIBLFO:
                id = ModelDestinbtion.DESTINATION_LFO2_DELAY;
                brebk;
            cbse SF2Region.GENERATOR_FREQVIBLFO:
                id = ModelDestinbtion.DESTINATION_LFO2_FREQ;
                brebk;

            cbse SF2Region.GENERATOR_DELAYMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_DELAY;
                brebk;
            cbse SF2Region.GENERATOR_ATTACKMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_ATTACK;
                brebk;
            cbse SF2Region.GENERATOR_HOLDMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_HOLD;
                brebk;
            cbse SF2Region.GENERATOR_DECAYMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_DECAY;
                brebk;
            cbse SF2Region.GENERATOR_SUSTAINMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_SUSTAIN;
                bmountcorrection[0] = -1;
                brebk;
            cbse SF2Region.GENERATOR_RELEASEMODENV:
                id = ModelDestinbtion.DESTINATION_EG2_RELEASE;
                brebk;
            cbse SF2Region.GENERATOR_DELAYVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_DELAY;
                brebk;
            cbse SF2Region.GENERATOR_ATTACKVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_ATTACK;
                brebk;
            cbse SF2Region.GENERATOR_HOLDVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_HOLD;
                brebk;
            cbse SF2Region.GENERATOR_DECAYVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_DECAY;
                brebk;
            cbse SF2Region.GENERATOR_SUSTAINVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_SUSTAIN;
                bmountcorrection[0] = -1;
                brebk;
            cbse SF2Region.GENERATOR_RELEASEVOLENV:
                id = ModelDestinbtion.DESTINATION_EG1_RELEASE;
                brebk;
            cbse SF2Region.GENERATOR_KEYNUM:
                id = ModelDestinbtion.DESTINATION_KEYNUMBER;
                brebk;
            cbse SF2Region.GENERATOR_VELOCITY:
                id = ModelDestinbtion.DESTINATION_VELOCITY;
                brebk;

            cbse SF2Region.GENERATOR_COARSETUNE:
                bmountcorrection[0] = 100;
                id = ModelDestinbtion.DESTINATION_PITCH;
                brebk;

            cbse SF2Region.GENERATOR_FINETUNE:
                id = ModelDestinbtion.DESTINATION_PITCH;
                brebk;

            cbse SF2Region.GENERATOR_INITIALATTENUATION:
                id = ModelDestinbtion.DESTINATION_GAIN;
                bmountcorrection[0] = -0.376287f;
                brebk;

            cbse SF2Region.GENERATOR_VIBLFOTOPITCH:
                id = ModelDestinbtion.DESTINATION_PITCH;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_LFO2,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            cbse SF2Region.GENERATOR_MODLFOTOPITCH:
                id = ModelDestinbtion.DESTINATION_PITCH;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_LFO1,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            cbse SF2Region.GENERATOR_MODLFOTOFILTERFC:
                id = ModelDestinbtion.DESTINATION_FILTER_FREQ;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_LFO1,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            cbse SF2Region.GENERATOR_MODLFOTOVOLUME:
                id = ModelDestinbtion.DESTINATION_GAIN;
                bmountcorrection[0] = -0.376287f;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_LFO1,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            cbse SF2Region.GENERATOR_MODENVTOPITCH:
                id = ModelDestinbtion.DESTINATION_PITCH;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_EG2,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            cbse SF2Region.GENERATOR_MODENVTOFILTERFC:
                id = ModelDestinbtion.DESTINATION_FILTER_FREQ;
                extrbsrc[0] = new ModelSource(
                        ModelSource.SOURCE_EG2,
                        ModelStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brebk;

            defbult:
                brebk;
        }
        if (id != null)
            return new ModelDestinbtion(id);
        return null;
    }

    privbte void bddTimecentVblue(ModelPerformer performer,
            ModelIdentifier dest, short vblue) {
        double fvblue;
        if (vblue == -12000)
            fvblue = Double.NEGATIVE_INFINITY;
        else
            fvblue = vblue;
        performer.getConnectionBlocks().bdd(
                new ModelConnectionBlock(fvblue, new ModelDestinbtion(dest)));
    }

    privbte void bddVblue(ModelPerformer performer,
            ModelIdentifier dest, short vblue) {
        double fvblue = vblue;
        performer.getConnectionBlocks().bdd(
                new ModelConnectionBlock(fvblue, new ModelDestinbtion(dest)));
    }

    privbte void bddVblue(ModelPerformer performer,
            ModelIdentifier dest, double vblue) {
        double fvblue = vblue;
        performer.getConnectionBlocks().bdd(
                new ModelConnectionBlock(fvblue, new ModelDestinbtion(dest)));
    }

    privbte short getGenerbtorVblue(Mbp<Integer, Short> generbtors, int gen) {
        if (generbtors.contbinsKey(gen))
            return generbtors.get(gen);
        return SF2Region.getDefbultVblue(gen);
    }
}
