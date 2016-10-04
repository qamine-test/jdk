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
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.Pbtch;

/**
 * This clbss is used to store informbtion to describe instrument.
 * It contbins list of regions bnd modulbtors.
 * It is stored inside b "ins " List Chunk inside DLS files.
 * In the DLS documentbtion b modulbtor is cblled brticulbtor.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSInstrument extends ModelInstrument {

    int preset = 0;
    int bbnk = 0;
    boolebn druminstrument = fblse;
    byte[] guid = null;
    DLSInfo info = new DLSInfo();
    List<DLSRegion> regions = new ArrbyList<DLSRegion>();
    List<DLSModulbtor> modulbtors = new ArrbyList<DLSModulbtor>();

    public DLSInstrument() {
        super(null, null, null, null);
    }

    public DLSInstrument(DLSSoundbbnk soundbbnk) {
        super(soundbbnk, null, null, null);
    }

    public DLSInfo getInfo() {
        return info;
    }

    public String getNbme() {
        return info.nbme;
    }

    public void setNbme(String nbme) {
        info.nbme = nbme;
    }

    public ModelPbtch getPbtch() {
        return new ModelPbtch(bbnk, preset, druminstrument);
    }

    public void setPbtch(Pbtch pbtch) {
        if (pbtch instbnceof ModelPbtch && ((ModelPbtch)pbtch).isPercussion()) {
            druminstrument = true;
            bbnk = pbtch.getBbnk();
            preset = pbtch.getProgrbm();
        } else {
            druminstrument = fblse;
            bbnk = pbtch.getBbnk();
            preset = pbtch.getProgrbm();
        }
    }

    public Object getDbtb() {
        return null;
    }

    public List<DLSRegion> getRegions() {
        return regions;
    }

    public List<DLSModulbtor> getModulbtors() {
        return modulbtors;
    }

    public String toString() {
        if (druminstrument)
            return "Drumkit: " + info.nbme
                    + " bbnk #" + bbnk + " preset #" + preset;
        else
            return "Instrument: " + info.nbme
                    + " bbnk #" + bbnk + " preset #" + preset;
    }

    privbte ModelIdentifier convertToModelDest(int dest) {
        if (dest == DLSModulbtor.CONN_DST_NONE)
            return null;
        if (dest == DLSModulbtor.CONN_DST_GAIN)
            return ModelDestinbtion.DESTINATION_GAIN;
        if (dest == DLSModulbtor.CONN_DST_PITCH)
            return ModelDestinbtion.DESTINATION_PITCH;
        if (dest == DLSModulbtor.CONN_DST_PAN)
            return ModelDestinbtion.DESTINATION_PAN;

        if (dest == DLSModulbtor.CONN_DST_LFO_FREQUENCY)
            return ModelDestinbtion.DESTINATION_LFO1_FREQ;
        if (dest == DLSModulbtor.CONN_DST_LFO_STARTDELAY)
            return ModelDestinbtion.DESTINATION_LFO1_DELAY;

        if (dest == DLSModulbtor.CONN_DST_EG1_ATTACKTIME)
            return ModelDestinbtion.DESTINATION_EG1_ATTACK;
        if (dest == DLSModulbtor.CONN_DST_EG1_DECAYTIME)
            return ModelDestinbtion.DESTINATION_EG1_DECAY;
        if (dest == DLSModulbtor.CONN_DST_EG1_RELEASETIME)
            return ModelDestinbtion.DESTINATION_EG1_RELEASE;
        if (dest == DLSModulbtor.CONN_DST_EG1_SUSTAINLEVEL)
            return ModelDestinbtion.DESTINATION_EG1_SUSTAIN;

        if (dest == DLSModulbtor.CONN_DST_EG2_ATTACKTIME)
            return ModelDestinbtion.DESTINATION_EG2_ATTACK;
        if (dest == DLSModulbtor.CONN_DST_EG2_DECAYTIME)
            return ModelDestinbtion.DESTINATION_EG2_DECAY;
        if (dest == DLSModulbtor.CONN_DST_EG2_RELEASETIME)
            return ModelDestinbtion.DESTINATION_EG2_RELEASE;
        if (dest == DLSModulbtor.CONN_DST_EG2_SUSTAINLEVEL)
            return ModelDestinbtion.DESTINATION_EG2_SUSTAIN;

        // DLS2 Destinbtions
        if (dest == DLSModulbtor.CONN_DST_KEYNUMBER)
            return ModelDestinbtion.DESTINATION_KEYNUMBER;

        if (dest == DLSModulbtor.CONN_DST_CHORUS)
            return ModelDestinbtion.DESTINATION_CHORUS;
        if (dest == DLSModulbtor.CONN_DST_REVERB)
            return ModelDestinbtion.DESTINATION_REVERB;

        if (dest == DLSModulbtor.CONN_DST_VIB_FREQUENCY)
            return ModelDestinbtion.DESTINATION_LFO2_FREQ;
        if (dest == DLSModulbtor.CONN_DST_VIB_STARTDELAY)
            return ModelDestinbtion.DESTINATION_LFO2_DELAY;

        if (dest == DLSModulbtor.CONN_DST_EG1_DELAYTIME)
            return ModelDestinbtion.DESTINATION_EG1_DELAY;
        if (dest == DLSModulbtor.CONN_DST_EG1_HOLDTIME)
            return ModelDestinbtion.DESTINATION_EG1_HOLD;
        if (dest == DLSModulbtor.CONN_DST_EG1_SHUTDOWNTIME)
            return ModelDestinbtion.DESTINATION_EG1_SHUTDOWN;

        if (dest == DLSModulbtor.CONN_DST_EG2_DELAYTIME)
            return ModelDestinbtion.DESTINATION_EG2_DELAY;
        if (dest == DLSModulbtor.CONN_DST_EG2_HOLDTIME)
            return ModelDestinbtion.DESTINATION_EG2_HOLD;

        if (dest == DLSModulbtor.CONN_DST_FILTER_CUTOFF)
            return ModelDestinbtion.DESTINATION_FILTER_FREQ;
        if (dest == DLSModulbtor.CONN_DST_FILTER_Q)
            return ModelDestinbtion.DESTINATION_FILTER_Q;

        return null;
    }

    privbte ModelIdentifier convertToModelSrc(int src) {
        if (src == DLSModulbtor.CONN_SRC_NONE)
            return null;

        if (src == DLSModulbtor.CONN_SRC_LFO)
            return ModelSource.SOURCE_LFO1;
        if (src == DLSModulbtor.CONN_SRC_KEYONVELOCITY)
            return ModelSource.SOURCE_NOTEON_VELOCITY;
        if (src == DLSModulbtor.CONN_SRC_KEYNUMBER)
            return ModelSource.SOURCE_NOTEON_KEYNUMBER;
        if (src == DLSModulbtor.CONN_SRC_EG1)
            return ModelSource.SOURCE_EG1;
        if (src == DLSModulbtor.CONN_SRC_EG2)
            return ModelSource.SOURCE_EG2;
        if (src == DLSModulbtor.CONN_SRC_PITCHWHEEL)
            return ModelSource.SOURCE_MIDI_PITCH;
        if (src == DLSModulbtor.CONN_SRC_CC1)
            return new ModelIdentifier("midi_cc", "1", 0);
        if (src == DLSModulbtor.CONN_SRC_CC7)
            return new ModelIdentifier("midi_cc", "7", 0);
        if (src == DLSModulbtor.CONN_SRC_CC10)
            return new ModelIdentifier("midi_cc", "10", 0);
        if (src == DLSModulbtor.CONN_SRC_CC11)
            return new ModelIdentifier("midi_cc", "11", 0);
        if (src == DLSModulbtor.CONN_SRC_RPN0)
            return new ModelIdentifier("midi_rpn", "0", 0);
        if (src == DLSModulbtor.CONN_SRC_RPN1)
            return new ModelIdentifier("midi_rpn", "1", 0);

        if (src == DLSModulbtor.CONN_SRC_POLYPRESSURE)
            return ModelSource.SOURCE_MIDI_POLY_PRESSURE;
        if (src == DLSModulbtor.CONN_SRC_CHANNELPRESSURE)
            return ModelSource.SOURCE_MIDI_CHANNEL_PRESSURE;
        if (src == DLSModulbtor.CONN_SRC_VIBRATO)
            return ModelSource.SOURCE_LFO2;
        if (src == DLSModulbtor.CONN_SRC_MONOPRESSURE)
            return ModelSource.SOURCE_MIDI_CHANNEL_PRESSURE;

        if (src == DLSModulbtor.CONN_SRC_CC91)
            return new ModelIdentifier("midi_cc", "91", 0);
        if (src == DLSModulbtor.CONN_SRC_CC93)
            return new ModelIdentifier("midi_cc", "93", 0);

        return null;
    }

    privbte ModelConnectionBlock convertToModel(DLSModulbtor mod) {
        ModelIdentifier source = convertToModelSrc(mod.getSource());
        ModelIdentifier control = convertToModelSrc(mod.getControl());
        ModelIdentifier destinbtion_id =
                convertToModelDest(mod.getDestinbtion());

        int scble = mod.getScble();
        double f_scble;
        if (scble == Integer.MIN_VALUE)
            f_scble = Double.NEGATIVE_INFINITY;
        else
            f_scble = scble / 65536.0;

        if (destinbtion_id != null) {
            ModelSource src = null;
            ModelSource ctrl = null;
            ModelConnectionBlock block = new ModelConnectionBlock();
            if (control != null) {
                ModelSource s = new ModelSource();
                if (control == ModelSource.SOURCE_MIDI_PITCH) {
                    ((ModelStbndbrdTrbnsform)s.getTrbnsform()).setPolbrity(
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                } else if (control == ModelSource.SOURCE_LFO1
                        || control == ModelSource.SOURCE_LFO2) {
                    ((ModelStbndbrdTrbnsform)s.getTrbnsform()).setPolbrity(
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                }
                s.setIdentifier(control);
                block.bddSource(s);
                ctrl = s;
            }
            if (source != null) {
                ModelSource s = new ModelSource();
                if (source == ModelSource.SOURCE_MIDI_PITCH) {
                    ((ModelStbndbrdTrbnsform)s.getTrbnsform()).setPolbrity(
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                } else if (source == ModelSource.SOURCE_LFO1
                        || source == ModelSource.SOURCE_LFO2) {
                    ((ModelStbndbrdTrbnsform)s.getTrbnsform()).setPolbrity(
                            ModelStbndbrdTrbnsform.POLARITY_BIPOLAR);
                }
                s.setIdentifier(source);
                block.bddSource(s);
                src = s;
            }
            ModelDestinbtion destinbtion = new ModelDestinbtion();
            destinbtion.setIdentifier(destinbtion_id);
            block.setDestinbtion(destinbtion);

            if (mod.getVersion() == 1) {
                //if (mod.getTrbnsform() ==  DLSModulbtor.CONN_TRN_CONCAVE) {
                //    ((ModelStbndbrdTrbnsform)destinbtion.getTrbnsform())
                //            .setTrbnsform(
                //            ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE);
                //}
                if (mod.getTrbnsform() == DLSModulbtor.CONN_TRN_CONCAVE) {
                    if (src != null) {
                        ((ModelStbndbrdTrbnsform)src.getTrbnsform())
                                .setTrbnsform(
                                    ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE);
                        ((ModelStbndbrdTrbnsform)src.getTrbnsform())
                                .setDirection(
                                    ModelStbndbrdTrbnsform.DIRECTION_MAX2MIN);
                    }
                    if (ctrl != null) {
                        ((ModelStbndbrdTrbnsform)ctrl.getTrbnsform())
                                .setTrbnsform(
                                    ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE);
                        ((ModelStbndbrdTrbnsform)ctrl.getTrbnsform())
                                .setDirection(
                                    ModelStbndbrdTrbnsform.DIRECTION_MAX2MIN);
                    }
                }

            } else if (mod.getVersion() == 2) {
                int trbnsform = mod.getTrbnsform();
                int src_trbnsform_invert = (trbnsform >> 15) & 1;
                int src_trbnsform_bipolbr = (trbnsform >> 14) & 1;
                int src_trbnsform = (trbnsform >> 10) & 8;
                int ctr_trbnsform_invert = (trbnsform >> 9) & 1;
                int ctr_trbnsform_bipolbr = (trbnsform >> 8) & 1;
                int ctr_trbnsform = (trbnsform >> 4) & 8;


                if (src != null) {
                    int trbns = ModelStbndbrdTrbnsform.TRANSFORM_LINEAR;
                    if (src_trbnsform == DLSModulbtor.CONN_TRN_SWITCH)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_SWITCH;
                    if (src_trbnsform == DLSModulbtor.CONN_TRN_CONCAVE)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE;
                    if (src_trbnsform == DLSModulbtor.CONN_TRN_CONVEX)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONVEX;
                    ((ModelStbndbrdTrbnsform)src.getTrbnsform())
                            .setTrbnsform(trbns);
                    ((ModelStbndbrdTrbnsform)src.getTrbnsform())
                            .setPolbrity(src_trbnsform_bipolbr == 1);
                    ((ModelStbndbrdTrbnsform)src.getTrbnsform())
                            .setDirection(src_trbnsform_invert == 1);

                }

                if (ctrl != null) {
                    int trbns = ModelStbndbrdTrbnsform.TRANSFORM_LINEAR;
                    if (ctr_trbnsform == DLSModulbtor.CONN_TRN_SWITCH)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_SWITCH;
                    if (ctr_trbnsform == DLSModulbtor.CONN_TRN_CONCAVE)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE;
                    if (ctr_trbnsform == DLSModulbtor.CONN_TRN_CONVEX)
                        trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONVEX;
                    ((ModelStbndbrdTrbnsform)ctrl.getTrbnsform())
                            .setTrbnsform(trbns);
                    ((ModelStbndbrdTrbnsform)ctrl.getTrbnsform())
                            .setPolbrity(ctr_trbnsform_bipolbr == 1);
                    ((ModelStbndbrdTrbnsform)ctrl.getTrbnsform())
                            .setDirection(ctr_trbnsform_invert == 1);
                }

                /* No output trbnsforms bre defined the DLS Level 2
                int out_trbnsform = trbnsform % 8;
                int trbns = ModelStbndbrdTrbnsform.TRANSFORM_LINEAR;
                if (out_trbnsform == DLSModulbtor.CONN_TRN_SWITCH)
                    trbns = ModelStbndbrdTrbnsform.TRANSFORM_SWITCH;
                if (out_trbnsform == DLSModulbtor.CONN_TRN_CONCAVE)
                    trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONCAVE;
                if (out_trbnsform == DLSModulbtor.CONN_TRN_CONVEX)
                    trbns = ModelStbndbrdTrbnsform.TRANSFORM_CONVEX;
                if (ctrl != null) {
                    ((ModelStbndbrdTrbnsform)destinbtion.getTrbnsform())
                            .setTrbnsform(trbns);
                }
                */

            }

            block.setScble(f_scble);

            return block;
        }

        return null;
    }

    public ModelPerformer[] getPerformers() {
        List<ModelPerformer> performers = new ArrbyList<ModelPerformer>();

        Mbp<String, DLSModulbtor> modmbp = new HbshMbp<String, DLSModulbtor>();
        for (DLSModulbtor mod: getModulbtors()) {
            modmbp.put(mod.getSource() + "x" + mod.getControl() + "=" +
                    mod.getDestinbtion(), mod);
        }

        Mbp<String, DLSModulbtor> insmodmbp =
                new HbshMbp<String, DLSModulbtor>();

        for (DLSRegion zone: regions) {
            ModelPerformer performer = new ModelPerformer();
            performer.setNbme(zone.getSbmple().getNbme());
            performer.setSelfNonExclusive((zone.getFusoptions() &
                    DLSRegion.OPTION_SELFNONEXCLUSIVE) != 0);
            performer.setExclusiveClbss(zone.getExclusiveClbss());
            performer.setKeyFrom(zone.getKeyfrom());
            performer.setKeyTo(zone.getKeyto());
            performer.setVelFrom(zone.getVelfrom());
            performer.setVelTo(zone.getVelto());

            insmodmbp.clebr();
            insmodmbp.putAll(modmbp);
            for (DLSModulbtor mod: zone.getModulbtors()) {
                insmodmbp.put(mod.getSource() + "x" + mod.getControl() + "=" +
                        mod.getDestinbtion(), mod);
            }

            List<ModelConnectionBlock> blocks = performer.getConnectionBlocks();
            for (DLSModulbtor mod: insmodmbp.vblues()) {
                ModelConnectionBlock p = convertToModel(mod);
                if (p != null)
                    blocks.bdd(p);
            }


            DLSSbmple sbmple = zone.getSbmple();
            DLSSbmpleOptions sbmpleopt = zone.getSbmpleoptions();
            if (sbmpleopt == null)
                sbmpleopt = sbmple.getSbmpleoptions();

            ModelByteBuffer buff = sbmple.getDbtbBuffer();

            flobt pitchcorrection = (-sbmpleopt.unitynote * 100) +
                    sbmpleopt.finetune;

            ModelByteBufferWbvetbble osc = new ModelByteBufferWbvetbble(buff,
                    sbmple.getFormbt(), pitchcorrection);
            osc.setAttenubtion(osc.getAttenubtion() / 65536f);
            if (sbmpleopt.getLoops().size() != 0) {
                DLSSbmpleLoop loop = sbmpleopt.getLoops().get(0);
                osc.setLoopStbrt((int)loop.getStbrt());
                osc.setLoopLength((int)loop.getLength());
                if (loop.getType() == DLSSbmpleLoop.LOOP_TYPE_FORWARD)
                    osc.setLoopType(ModelWbvetbble.LOOP_TYPE_FORWARD);
                if (loop.getType() == DLSSbmpleLoop.LOOP_TYPE_RELEASE)
                    osc.setLoopType(ModelWbvetbble.LOOP_TYPE_RELEASE);
                else
                    osc.setLoopType(ModelWbvetbble.LOOP_TYPE_FORWARD);
            }

            performer.getConnectionBlocks().bdd(
                    new ModelConnectionBlock(SoftFilter.FILTERTYPE_LP12,
                        new ModelDestinbtion(
                            new ModelIdentifier("filter", "type", 1))));

            performer.getOscillbtors().bdd(osc);

            performers.bdd(performer);

        }

        return performers.toArrby(new ModelPerformer[performers.size()]);
    }

    public byte[] getGuid() {
        return guid == null ? null : Arrbys.copyOf(guid, guid.length);
    }

    public void setGuid(byte[] guid) {
        this.guid = guid == null ? null : Arrbys.copyOf(guid, guid.length);
    }
}
