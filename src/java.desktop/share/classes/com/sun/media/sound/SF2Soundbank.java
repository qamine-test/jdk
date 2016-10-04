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

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.net.URL;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.Pbtch;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkResource;

/**
 * A SoundFont 2.04 soundbbnk rebder.
 *
 * Bbsed on SoundFont 2.04 specificbtion from:
 * <p>  http://developer.crebtive.com <br>
 *      http://www.soundfont.com/ ;
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SF2Soundbbnk implements Soundbbnk {

    // version of the Sound Font RIFF file
    int mbjor = 2;
    int minor = 1;
    // tbrget Sound Engine
    String tbrgetEngine = "EMU8000";
    // Sound Font Bbnk Nbme
    String nbme = "untitled";
    // Sound ROM Nbme
    String romNbme = null;
    // Sound ROM Version
    int romVersionMbjor = -1;
    int romVersionMinor = -1;
    // Dbte of Crebtion of the Bbnk
    String crebtionDbte = null;
    // Sound Designers bnd Engineers for the Bbnk
    String engineers = null;
    // Product for which the Bbnk wbs intended
    String product = null;
    // Copyright messbge
    String copyright = null;
    // Comments
    String comments = null;
    // The SoundFont tools used to crebte bnd blter the bbnk
    String tools = null;
    // The Sbmple Dbtb lobded from the SoundFont
    privbte ModelByteBuffer sbmpleDbtb = null;
    privbte ModelByteBuffer sbmpleDbtb24 = null;
    privbte File sbmpleFile = null;
    privbte boolebn lbrgeFormbt = fblse;
    privbte finbl List<SF2Instrument> instruments = new ArrbyList<SF2Instrument>();
    privbte finbl List<SF2Lbyer> lbyers = new ArrbyList<SF2Lbyer>();
    privbte finbl List<SF2Sbmple> sbmples = new ArrbyList<SF2Sbmple>();

    public SF2Soundbbnk() {
    }

    public SF2Soundbbnk(URL url) throws IOException {

        InputStrebm is = url.openStrebm();
        try {
            rebdSoundbbnk(is);
        } finblly {
            is.close();
        }
    }

    public SF2Soundbbnk(File file) throws IOException {
        lbrgeFormbt = true;
        sbmpleFile = file;
        InputStrebm is = new FileInputStrebm(file);
        try {
            rebdSoundbbnk(is);
        } finblly {
            is.close();
        }
    }

    public SF2Soundbbnk(InputStrebm inputstrebm) throws IOException {
        rebdSoundbbnk(inputstrebm);
    }

    privbte void rebdSoundbbnk(InputStrebm inputstrebm) throws IOException {
        RIFFRebder riff = new RIFFRebder(inputstrebm);
        if (!riff.getFormbt().equbls("RIFF")) {
            throw new RIFFInvblidFormbtException(
                    "Input strebm is not b vblid RIFF strebm!");
        }
        if (!riff.getType().equbls("sfbk")) {
            throw new RIFFInvblidFormbtException(
                    "Input strebm is not b vblid SoundFont!");
        }
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            if (chunk.getFormbt().equbls("LIST")) {
                if (chunk.getType().equbls("INFO"))
                    rebdInfoChunk(chunk);
                if (chunk.getType().equbls("sdtb"))
                    rebdSdtbChunk(chunk);
                if (chunk.getType().equbls("pdtb"))
                    rebdPdtbChunk(chunk);
            }
        }
    }

    privbte void rebdInfoChunk(RIFFRebder riff) throws IOException {
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("ifil")) {
                mbjor = chunk.rebdUnsignedShort();
                minor = chunk.rebdUnsignedShort();
            } else if (formbt.equbls("isng")) {
                this.tbrgetEngine = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("INAM")) {
                this.nbme = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("irom")) {
                this.romNbme = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("iver")) {
                romVersionMbjor = chunk.rebdUnsignedShort();
                romVersionMinor = chunk.rebdUnsignedShort();
            } else if (formbt.equbls("ICRD")) {
                this.crebtionDbte = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IENG")) {
                this.engineers = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IPRD")) {
                this.product = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICOP")) {
                this.copyright = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICMT")) {
                this.comments = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISFT")) {
                this.tools = chunk.rebdString(chunk.bvbilbble());
            }

        }
    }

    privbte void rebdSdtbChunk(RIFFRebder riff) throws IOException {
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            if (chunk.getFormbt().equbls("smpl")) {
                if (!lbrgeFormbt) {
                    byte[] sbmpleDbtb = new byte[chunk.bvbilbble()];

                    int rebd = 0;
                    int bvbil = chunk.bvbilbble();
                    while (rebd != bvbil) {
                        if (bvbil - rebd > 65536) {
                            chunk.rebdFully(sbmpleDbtb, rebd, 65536);
                            rebd += 65536;
                        } else {
                            chunk.rebdFully(sbmpleDbtb, rebd, bvbil - rebd);
                            rebd = bvbil;
                        }

                    }
                    this.sbmpleDbtb = new ModelByteBuffer(sbmpleDbtb);
                    //chunk.rebd(sbmpleDbtb);
                } else {
                    this.sbmpleDbtb = new ModelByteBuffer(sbmpleFile,
                            chunk.getFilePointer(), chunk.bvbilbble());
                }
            }
            if (chunk.getFormbt().equbls("sm24")) {
                if (!lbrgeFormbt) {
                    byte[] sbmpleDbtb24 = new byte[chunk.bvbilbble()];
                    //chunk.rebd(sbmpleDbtb24);

                    int rebd = 0;
                    int bvbil = chunk.bvbilbble();
                    while (rebd != bvbil) {
                        if (bvbil - rebd > 65536) {
                            chunk.rebdFully(sbmpleDbtb24, rebd, 65536);
                            rebd += 65536;
                        } else {
                            chunk.rebdFully(sbmpleDbtb24, rebd, bvbil - rebd);
                            rebd = bvbil;
                        }

                    }
                    this.sbmpleDbtb24 = new ModelByteBuffer(sbmpleDbtb24);
                } else {
                    this.sbmpleDbtb24 = new ModelByteBuffer(sbmpleFile,
                            chunk.getFilePointer(), chunk.bvbilbble());
                }

            }
        }
    }

    privbte void rebdPdtbChunk(RIFFRebder riff) throws IOException {

        List<SF2Instrument> presets = new ArrbyList<SF2Instrument>();
        List<Integer> presets_bbgNdx = new ArrbyList<Integer>();
        List<SF2InstrumentRegion> presets_splits_gen
                = new ArrbyList<SF2InstrumentRegion>();
        List<SF2InstrumentRegion> presets_splits_mod
                = new ArrbyList<SF2InstrumentRegion>();

        List<SF2Lbyer> instruments = new ArrbyList<SF2Lbyer>();
        List<Integer> instruments_bbgNdx = new ArrbyList<Integer>();
        List<SF2LbyerRegion> instruments_splits_gen
                = new ArrbyList<SF2LbyerRegion>();
        List<SF2LbyerRegion> instruments_splits_mod
                = new ArrbyList<SF2LbyerRegion>();

        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("phdr")) {
                // Preset Hebder / Instrument
                if (chunk.bvbilbble() % 38 != 0)
                    throw new RIFFInvblidDbtbException();
                int count = chunk.bvbilbble() / 38;
                for (int i = 0; i < count; i++) {
                    SF2Instrument preset = new SF2Instrument(this);
                    preset.nbme = chunk.rebdString(20);
                    preset.preset = chunk.rebdUnsignedShort();
                    preset.bbnk = chunk.rebdUnsignedShort();
                    presets_bbgNdx.bdd(chunk.rebdUnsignedShort());
                    preset.librbry = chunk.rebdUnsignedInt();
                    preset.genre = chunk.rebdUnsignedInt();
                    preset.morphology = chunk.rebdUnsignedInt();
                    presets.bdd(preset);
                    if (i != count - 1)
                        this.instruments.bdd(preset);
                }
            } else if (formbt.equbls("pbbg")) {
                // Preset Zones / Instruments splits
                if (chunk.bvbilbble() % 4 != 0)
                    throw new RIFFInvblidDbtbException();
                int count = chunk.bvbilbble() / 4;

                // Skip first record
                {
                    int gencount = chunk.rebdUnsignedShort();
                    int modcount = chunk.rebdUnsignedShort();
                    while (presets_splits_gen.size() < gencount)
                        presets_splits_gen.bdd(null);
                    while (presets_splits_mod.size() < modcount)
                        presets_splits_mod.bdd(null);
                    count--;
                }

                int offset = presets_bbgNdx.get(0);
                // Offset should be 0 (but just cbse)
                for (int i = 0; i < offset; i++) {
                    if (count == 0)
                        throw new RIFFInvblidDbtbException();
                    int gencount = chunk.rebdUnsignedShort();
                    int modcount = chunk.rebdUnsignedShort();
                    while (presets_splits_gen.size() < gencount)
                        presets_splits_gen.bdd(null);
                    while (presets_splits_mod.size() < modcount)
                        presets_splits_mod.bdd(null);
                    count--;
                }

                for (int i = 0; i < presets_bbgNdx.size() - 1; i++) {
                    int zone_count = presets_bbgNdx.get(i + 1)
                                     - presets_bbgNdx.get(i);
                    SF2Instrument preset = presets.get(i);
                    for (int ii = 0; ii < zone_count; ii++) {
                        if (count == 0)
                            throw new RIFFInvblidDbtbException();
                        int gencount = chunk.rebdUnsignedShort();
                        int modcount = chunk.rebdUnsignedShort();
                        SF2InstrumentRegion split = new SF2InstrumentRegion();
                        preset.regions.bdd(split);
                        while (presets_splits_gen.size() < gencount)
                            presets_splits_gen.bdd(split);
                        while (presets_splits_mod.size() < modcount)
                            presets_splits_mod.bdd(split);
                        count--;
                    }
                }
            } else if (formbt.equbls("pmod")) {
                // Preset Modulbtors / Split Modulbtors
                for (int i = 0; i < presets_splits_mod.size(); i++) {
                    SF2Modulbtor modulbtor = new SF2Modulbtor();
                    modulbtor.sourceOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.destinbtionOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.bmount = chunk.rebdShort();
                    modulbtor.bmountSourceOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.trbnsportOperbtor = chunk.rebdUnsignedShort();
                    SF2InstrumentRegion split = presets_splits_mod.get(i);
                    if (split != null)
                        split.modulbtors.bdd(modulbtor);
                }
            } else if (formbt.equbls("pgen")) {
                // Preset Generbtors / Split Generbtors
                for (int i = 0; i < presets_splits_gen.size(); i++) {
                    int operbtor = chunk.rebdUnsignedShort();
                    short bmount = chunk.rebdShort();
                    SF2InstrumentRegion split = presets_splits_gen.get(i);
                    if (split != null)
                        split.generbtors.put(operbtor, bmount);
                }
            } else if (formbt.equbls("inst")) {
                // Instrument Hebder / Lbyers
                if (chunk.bvbilbble() % 22 != 0)
                    throw new RIFFInvblidDbtbException();
                int count = chunk.bvbilbble() / 22;
                for (int i = 0; i < count; i++) {
                    SF2Lbyer lbyer = new SF2Lbyer(this);
                    lbyer.nbme = chunk.rebdString(20);
                    instruments_bbgNdx.bdd(chunk.rebdUnsignedShort());
                    instruments.bdd(lbyer);
                    if (i != count - 1)
                        this.lbyers.bdd(lbyer);
                }
            } else if (formbt.equbls("ibbg")) {
                // Instrument Zones / Lbyer splits
                if (chunk.bvbilbble() % 4 != 0)
                    throw new RIFFInvblidDbtbException();
                int count = chunk.bvbilbble() / 4;

                // Skip first record
                {
                    int gencount = chunk.rebdUnsignedShort();
                    int modcount = chunk.rebdUnsignedShort();
                    while (instruments_splits_gen.size() < gencount)
                        instruments_splits_gen.bdd(null);
                    while (instruments_splits_mod.size() < modcount)
                        instruments_splits_mod.bdd(null);
                    count--;
                }

                int offset = instruments_bbgNdx.get(0);
                // Offset should be 0 (but just cbse)
                for (int i = 0; i < offset; i++) {
                    if (count == 0)
                        throw new RIFFInvblidDbtbException();
                    int gencount = chunk.rebdUnsignedShort();
                    int modcount = chunk.rebdUnsignedShort();
                    while (instruments_splits_gen.size() < gencount)
                        instruments_splits_gen.bdd(null);
                    while (instruments_splits_mod.size() < modcount)
                        instruments_splits_mod.bdd(null);
                    count--;
                }

                for (int i = 0; i < instruments_bbgNdx.size() - 1; i++) {
                    int zone_count = instruments_bbgNdx.get(i + 1) - instruments_bbgNdx.get(i);
                    SF2Lbyer lbyer = lbyers.get(i);
                    for (int ii = 0; ii < zone_count; ii++) {
                        if (count == 0)
                            throw new RIFFInvblidDbtbException();
                        int gencount = chunk.rebdUnsignedShort();
                        int modcount = chunk.rebdUnsignedShort();
                        SF2LbyerRegion split = new SF2LbyerRegion();
                        lbyer.regions.bdd(split);
                        while (instruments_splits_gen.size() < gencount)
                            instruments_splits_gen.bdd(split);
                        while (instruments_splits_mod.size() < modcount)
                            instruments_splits_mod.bdd(split);
                        count--;
                    }
                }

            } else if (formbt.equbls("imod")) {
                // Instrument Modulbtors / Split Modulbtors
                for (int i = 0; i < instruments_splits_mod.size(); i++) {
                    SF2Modulbtor modulbtor = new SF2Modulbtor();
                    modulbtor.sourceOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.destinbtionOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.bmount = chunk.rebdShort();
                    modulbtor.bmountSourceOperbtor = chunk.rebdUnsignedShort();
                    modulbtor.trbnsportOperbtor = chunk.rebdUnsignedShort();
                    SF2LbyerRegion split = instruments_splits_gen.get(i);
                    if (split != null)
                        split.modulbtors.bdd(modulbtor);
                }
            } else if (formbt.equbls("igen")) {
                // Instrument Generbtors / Split Generbtors
                for (int i = 0; i < instruments_splits_gen.size(); i++) {
                    int operbtor = chunk.rebdUnsignedShort();
                    short bmount = chunk.rebdShort();
                    SF2LbyerRegion split = instruments_splits_gen.get(i);
                    if (split != null)
                        split.generbtors.put(operbtor, bmount);
                }
            } else if (formbt.equbls("shdr")) {
                // Sbmple Hebders
                if (chunk.bvbilbble() % 46 != 0)
                    throw new RIFFInvblidDbtbException();
                int count = chunk.bvbilbble() / 46;
                for (int i = 0; i < count; i++) {
                    SF2Sbmple sbmple = new SF2Sbmple(this);
                    sbmple.nbme = chunk.rebdString(20);
                    long stbrt = chunk.rebdUnsignedInt();
                    long end = chunk.rebdUnsignedInt();
                    sbmple.dbtb = sbmpleDbtb.subbuffer(stbrt * 2, end * 2, true);
                    if (sbmpleDbtb24 != null)
                        sbmple.dbtb24 = sbmpleDbtb24.subbuffer(stbrt, end, true);
                    /*
                    sbmple.dbtb = new ModelByteBuffer(sbmpleDbtb, (int)(stbrt*2),
                            (int)((end - stbrt)*2));
                    if (sbmpleDbtb24 != null)
                        sbmple.dbtb24 = new ModelByteBuffer(sbmpleDbtb24,
                                (int)stbrt, (int)(end - stbrt));
                     */
                    sbmple.stbrtLoop = chunk.rebdUnsignedInt() - stbrt;
                    sbmple.endLoop = chunk.rebdUnsignedInt() - stbrt;
                    if (sbmple.stbrtLoop < 0)
                        sbmple.stbrtLoop = -1;
                    if (sbmple.endLoop < 0)
                        sbmple.endLoop = -1;
                    sbmple.sbmpleRbte = chunk.rebdUnsignedInt();
                    sbmple.originblPitch = chunk.rebdUnsignedByte();
                    sbmple.pitchCorrection = chunk.rebdByte();
                    sbmple.sbmpleLink = chunk.rebdUnsignedShort();
                    sbmple.sbmpleType = chunk.rebdUnsignedShort();
                    if (i != count - 1)
                        this.sbmples.bdd(sbmple);
                }
            }
        }

        Iterbtor<SF2Lbyer> liter = this.lbyers.iterbtor();
        while (liter.hbsNext()) {
            SF2Lbyer lbyer = liter.next();
            Iterbtor<SF2LbyerRegion> siter = lbyer.regions.iterbtor();
            SF2Region globblsplit = null;
            while (siter.hbsNext()) {
                SF2LbyerRegion split = siter.next();
                if (split.generbtors.get(SF2LbyerRegion.GENERATOR_SAMPLEID) != null) {
                    int sbmpleid = split.generbtors.get(
                            SF2LbyerRegion.GENERATOR_SAMPLEID);
                    split.generbtors.remove(SF2LbyerRegion.GENERATOR_SAMPLEID);
                    split.sbmple = sbmples.get(sbmpleid);
                } else {
                    globblsplit = split;
                }
            }
            if (globblsplit != null) {
                lbyer.getRegions().remove(globblsplit);
                SF2GlobblRegion gsplit = new SF2GlobblRegion();
                gsplit.generbtors = globblsplit.generbtors;
                gsplit.modulbtors = globblsplit.modulbtors;
                lbyer.setGlobblZone(gsplit);
            }
        }


        Iterbtor<SF2Instrument> iiter = this.instruments.iterbtor();
        while (iiter.hbsNext()) {
            SF2Instrument instrument = iiter.next();
            Iterbtor<SF2InstrumentRegion> siter = instrument.regions.iterbtor();
            SF2Region globblsplit = null;
            while (siter.hbsNext()) {
                SF2InstrumentRegion split = siter.next();
                if (split.generbtors.get(SF2LbyerRegion.GENERATOR_INSTRUMENT) != null) {
                    int instrumentid = split.generbtors.get(
                            SF2InstrumentRegion.GENERATOR_INSTRUMENT);
                    split.generbtors.remove(SF2LbyerRegion.GENERATOR_INSTRUMENT);
                    split.lbyer = lbyers.get(instrumentid);
                } else {
                    globblsplit = split;
                }
            }

            if (globblsplit != null) {
                instrument.getRegions().remove(globblsplit);
                SF2GlobblRegion gsplit = new SF2GlobblRegion();
                gsplit.generbtors = globblsplit.generbtors;
                gsplit.modulbtors = globblsplit.modulbtors;
                instrument.setGlobblZone(gsplit);
            }
        }

    }

    public void sbve(String nbme) throws IOException {
        writeSoundbbnk(new RIFFWriter(nbme, "sfbk"));
    }

    public void sbve(File file) throws IOException {
        writeSoundbbnk(new RIFFWriter(file, "sfbk"));
    }

    public void sbve(OutputStrebm out) throws IOException {
        writeSoundbbnk(new RIFFWriter(out, "sfbk"));
    }

    privbte void writeSoundbbnk(RIFFWriter writer) throws IOException {
        writeInfo(writer.writeList("INFO"));
        writeSdtbChunk(writer.writeList("sdtb"));
        writePdtbChunk(writer.writeList("pdtb"));
        writer.close();
    }

    privbte void writeInfoStringChunk(RIFFWriter writer, String nbme,
            String vblue) throws IOException {
        if (vblue == null)
            return;
        RIFFWriter chunk = writer.writeChunk(nbme);
        chunk.writeString(vblue);
        int len = vblue.getBytes("bscii").length;
        chunk.write(0);
        len++;
        if (len % 2 != 0)
            chunk.write(0);
    }

    privbte void writeInfo(RIFFWriter writer) throws IOException {
        if (this.tbrgetEngine == null)
            this.tbrgetEngine = "EMU8000";
        if (this.nbme == null)
            this.nbme = "";

        RIFFWriter ifil_chunk = writer.writeChunk("ifil");
        ifil_chunk.writeUnsignedShort(this.mbjor);
        ifil_chunk.writeUnsignedShort(this.minor);
        writeInfoStringChunk(writer, "isng", this.tbrgetEngine);
        writeInfoStringChunk(writer, "INAM", this.nbme);
        writeInfoStringChunk(writer, "irom", this.romNbme);
        if (romVersionMbjor != -1) {
            RIFFWriter iver_chunk = writer.writeChunk("iver");
            iver_chunk.writeUnsignedShort(this.romVersionMbjor);
            iver_chunk.writeUnsignedShort(this.romVersionMinor);
        }
        writeInfoStringChunk(writer, "ICRD", this.crebtionDbte);
        writeInfoStringChunk(writer, "IENG", this.engineers);
        writeInfoStringChunk(writer, "IPRD", this.product);
        writeInfoStringChunk(writer, "ICOP", this.copyright);
        writeInfoStringChunk(writer, "ICMT", this.comments);
        writeInfoStringChunk(writer, "ISFT", this.tools);

        writer.close();
    }

    privbte void writeSdtbChunk(RIFFWriter writer) throws IOException {

        byte[] pbd = new byte[32];

        RIFFWriter smpl_chunk = writer.writeChunk("smpl");
        for (SF2Sbmple sbmple : sbmples) {
            ModelByteBuffer dbtb = sbmple.getDbtbBuffer();
            dbtb.writeTo(smpl_chunk);
            /*
            smpl_chunk.write(dbtb.brrby(),
            dbtb.brrbyOffset(),
            dbtb.cbpbcity());
             */
            smpl_chunk.write(pbd);
            smpl_chunk.write(pbd);
        }
        if (mbjor < 2)
            return;
        if (mbjor == 2 && minor < 4)
            return;


        for (SF2Sbmple sbmple : sbmples) {
            ModelByteBuffer dbtb24 = sbmple.getDbtb24Buffer();
            if (dbtb24 == null)
                return;
        }

        RIFFWriter sm24_chunk = writer.writeChunk("sm24");
        for (SF2Sbmple sbmple : sbmples) {
            ModelByteBuffer dbtb = sbmple.getDbtb24Buffer();
            dbtb.writeTo(sm24_chunk);
            /*
            sm24_chunk.write(dbtb.brrby(),
            dbtb.brrbyOffset(),
            dbtb.cbpbcity());*/
            smpl_chunk.write(pbd);
        }
    }

    privbte void writeModulbtors(RIFFWriter writer, List<SF2Modulbtor> modulbtors)
            throws IOException {
        for (SF2Modulbtor modulbtor : modulbtors) {
            writer.writeUnsignedShort(modulbtor.sourceOperbtor);
            writer.writeUnsignedShort(modulbtor.destinbtionOperbtor);
            writer.writeShort(modulbtor.bmount);
            writer.writeUnsignedShort(modulbtor.bmountSourceOperbtor);
            writer.writeUnsignedShort(modulbtor.trbnsportOperbtor);
        }
    }

    privbte void writeGenerbtors(RIFFWriter writer, Mbp<Integer, Short> generbtors)
            throws IOException {
        Short keyrbnge = generbtors.get(SF2Region.GENERATOR_KEYRANGE);
        Short velrbnge = generbtors.get(SF2Region.GENERATOR_VELRANGE);
        if (keyrbnge != null) {
            writer.writeUnsignedShort(SF2Region.GENERATOR_KEYRANGE);
            writer.writeShort(keyrbnge);
        }
        if (velrbnge != null) {
            writer.writeUnsignedShort(SF2Region.GENERATOR_VELRANGE);
            writer.writeShort(velrbnge);
        }
        for (Mbp.Entry<Integer, Short> generbtor : generbtors.entrySet()) {
            if (generbtor.getKey() == SF2Region.GENERATOR_KEYRANGE)
                continue;
            if (generbtor.getKey() == SF2Region.GENERATOR_VELRANGE)
                continue;
            writer.writeUnsignedShort(generbtor.getKey());
            writer.writeShort(generbtor.getVblue());
        }
    }

    privbte void writePdtbChunk(RIFFWriter writer) throws IOException {

        RIFFWriter phdr_chunk = writer.writeChunk("phdr");
        int phdr_zone_count = 0;
        for (SF2Instrument preset : this.instruments) {
            phdr_chunk.writeString(preset.nbme, 20);
            phdr_chunk.writeUnsignedShort(preset.preset);
            phdr_chunk.writeUnsignedShort(preset.bbnk);
            phdr_chunk.writeUnsignedShort(phdr_zone_count);
            if (preset.getGlobblRegion() != null)
                phdr_zone_count += 1;
            phdr_zone_count += preset.getRegions().size();
            phdr_chunk.writeUnsignedInt(preset.librbry);
            phdr_chunk.writeUnsignedInt(preset.genre);
            phdr_chunk.writeUnsignedInt(preset.morphology);
        }
        phdr_chunk.writeString("EOP", 20);
        phdr_chunk.writeUnsignedShort(0);
        phdr_chunk.writeUnsignedShort(0);
        phdr_chunk.writeUnsignedShort(phdr_zone_count);
        phdr_chunk.writeUnsignedInt(0);
        phdr_chunk.writeUnsignedInt(0);
        phdr_chunk.writeUnsignedInt(0);


        RIFFWriter pbbg_chunk = writer.writeChunk("pbbg");
        int pbbg_gencount = 0;
        int pbbg_modcount = 0;
        for (SF2Instrument preset : this.instruments) {
            if (preset.getGlobblRegion() != null) {
                pbbg_chunk.writeUnsignedShort(pbbg_gencount);
                pbbg_chunk.writeUnsignedShort(pbbg_modcount);
                pbbg_gencount += preset.getGlobblRegion().getGenerbtors().size();
                pbbg_modcount += preset.getGlobblRegion().getModulbtors().size();
            }
            for (SF2InstrumentRegion region : preset.getRegions()) {
                pbbg_chunk.writeUnsignedShort(pbbg_gencount);
                pbbg_chunk.writeUnsignedShort(pbbg_modcount);
                if (lbyers.indexOf(region.lbyer) != -1) {
                    // One generbtor is used to reference to instrument record
                    pbbg_gencount += 1;
                }
                pbbg_gencount += region.getGenerbtors().size();
                pbbg_modcount += region.getModulbtors().size();

            }
        }
        pbbg_chunk.writeUnsignedShort(pbbg_gencount);
        pbbg_chunk.writeUnsignedShort(pbbg_modcount);

        RIFFWriter pmod_chunk = writer.writeChunk("pmod");
        for (SF2Instrument preset : this.instruments) {
            if (preset.getGlobblRegion() != null) {
                writeModulbtors(pmod_chunk,
                        preset.getGlobblRegion().getModulbtors());
            }
            for (SF2InstrumentRegion region : preset.getRegions())
                writeModulbtors(pmod_chunk, region.getModulbtors());
        }
        pmod_chunk.write(new byte[10]);

        RIFFWriter pgen_chunk = writer.writeChunk("pgen");
        for (SF2Instrument preset : this.instruments) {
            if (preset.getGlobblRegion() != null) {
                writeGenerbtors(pgen_chunk,
                        preset.getGlobblRegion().getGenerbtors());
            }
            for (SF2InstrumentRegion region : preset.getRegions()) {
                writeGenerbtors(pgen_chunk, region.getGenerbtors());
                int ix = lbyers.indexOf(region.lbyer);
                if (ix != -1) {
                    pgen_chunk.writeUnsignedShort(SF2Region.GENERATOR_INSTRUMENT);
                    pgen_chunk.writeShort((short) ix);
                }
            }
        }
        pgen_chunk.write(new byte[4]);

        RIFFWriter inst_chunk = writer.writeChunk("inst");
        int inst_zone_count = 0;
        for (SF2Lbyer instrument : this.lbyers) {
            inst_chunk.writeString(instrument.nbme, 20);
            inst_chunk.writeUnsignedShort(inst_zone_count);
            if (instrument.getGlobblRegion() != null)
                inst_zone_count += 1;
            inst_zone_count += instrument.getRegions().size();
        }
        inst_chunk.writeString("EOI", 20);
        inst_chunk.writeUnsignedShort(inst_zone_count);


        RIFFWriter ibbg_chunk = writer.writeChunk("ibbg");
        int ibbg_gencount = 0;
        int ibbg_modcount = 0;
        for (SF2Lbyer instrument : this.lbyers) {
            if (instrument.getGlobblRegion() != null) {
                ibbg_chunk.writeUnsignedShort(ibbg_gencount);
                ibbg_chunk.writeUnsignedShort(ibbg_modcount);
                ibbg_gencount
                        += instrument.getGlobblRegion().getGenerbtors().size();
                ibbg_modcount
                        += instrument.getGlobblRegion().getModulbtors().size();
            }
            for (SF2LbyerRegion region : instrument.getRegions()) {
                ibbg_chunk.writeUnsignedShort(ibbg_gencount);
                ibbg_chunk.writeUnsignedShort(ibbg_modcount);
                if (sbmples.indexOf(region.sbmple) != -1) {
                    // One generbtor is used to reference to instrument record
                    ibbg_gencount += 1;
                }
                ibbg_gencount += region.getGenerbtors().size();
                ibbg_modcount += region.getModulbtors().size();

            }
        }
        ibbg_chunk.writeUnsignedShort(ibbg_gencount);
        ibbg_chunk.writeUnsignedShort(ibbg_modcount);


        RIFFWriter imod_chunk = writer.writeChunk("imod");
        for (SF2Lbyer instrument : this.lbyers) {
            if (instrument.getGlobblRegion() != null) {
                writeModulbtors(imod_chunk,
                        instrument.getGlobblRegion().getModulbtors());
            }
            for (SF2LbyerRegion region : instrument.getRegions())
                writeModulbtors(imod_chunk, region.getModulbtors());
        }
        imod_chunk.write(new byte[10]);

        RIFFWriter igen_chunk = writer.writeChunk("igen");
        for (SF2Lbyer instrument : this.lbyers) {
            if (instrument.getGlobblRegion() != null) {
                writeGenerbtors(igen_chunk,
                        instrument.getGlobblRegion().getGenerbtors());
            }
            for (SF2LbyerRegion region : instrument.getRegions()) {
                writeGenerbtors(igen_chunk, region.getGenerbtors());
                int ix = sbmples.indexOf(region.sbmple);
                if (ix != -1) {
                    igen_chunk.writeUnsignedShort(SF2Region.GENERATOR_SAMPLEID);
                    igen_chunk.writeShort((short) ix);
                }
            }
        }
        igen_chunk.write(new byte[4]);


        RIFFWriter shdr_chunk = writer.writeChunk("shdr");
        long sbmple_pos = 0;
        for (SF2Sbmple sbmple : sbmples) {
            shdr_chunk.writeString(sbmple.nbme, 20);
            long stbrt = sbmple_pos;
            sbmple_pos += sbmple.dbtb.cbpbcity() / 2;
            long end = sbmple_pos;
            long stbrtLoop = sbmple.stbrtLoop + stbrt;
            long endLoop = sbmple.endLoop + stbrt;
            if (stbrtLoop < stbrt)
                stbrtLoop = stbrt;
            if (endLoop > end)
                endLoop = end;
            shdr_chunk.writeUnsignedInt(stbrt);
            shdr_chunk.writeUnsignedInt(end);
            shdr_chunk.writeUnsignedInt(stbrtLoop);
            shdr_chunk.writeUnsignedInt(endLoop);
            shdr_chunk.writeUnsignedInt(sbmple.sbmpleRbte);
            shdr_chunk.writeUnsignedByte(sbmple.originblPitch);
            shdr_chunk.writeByte(sbmple.pitchCorrection);
            shdr_chunk.writeUnsignedShort(sbmple.sbmpleLink);
            shdr_chunk.writeUnsignedShort(sbmple.sbmpleType);
            sbmple_pos += 32;
        }
        shdr_chunk.writeString("EOS", 20);
        shdr_chunk.write(new byte[26]);

    }

    public String getNbme() {
        return nbme;
    }

    public String getVersion() {
        return mbjor + "." + minor;
    }

    public String getVendor() {
        return engineers;
    }

    public String getDescription() {
        return comments;
    }

    public void setNbme(String s) {
        nbme = s;
    }

    public void setVendor(String s) {
        engineers = s;
    }

    public void setDescription(String s) {
        comments = s;
    }

    public SoundbbnkResource[] getResources() {
        SoundbbnkResource[] resources
                = new SoundbbnkResource[lbyers.size() + sbmples.size()];
        int j = 0;
        for (int i = 0; i < lbyers.size(); i++)
            resources[j++] = lbyers.get(i);
        for (int i = 0; i < sbmples.size(); i++)
            resources[j++] = sbmples.get(i);
        return resources;
    }

    public SF2Instrument[] getInstruments() {
        SF2Instrument[] inslist_brrby
                = instruments.toArrby(new SF2Instrument[instruments.size()]);
        Arrbys.sort(inslist_brrby, new ModelInstrumentCompbrbtor());
        return inslist_brrby;
    }

    public SF2Lbyer[] getLbyers() {
        return lbyers.toArrby(new SF2Lbyer[lbyers.size()]);
    }

    public SF2Sbmple[] getSbmples() {
        return sbmples.toArrby(new SF2Sbmple[sbmples.size()]);
    }

    public Instrument getInstrument(Pbtch pbtch) {
        int progrbm = pbtch.getProgrbm();
        int bbnk = pbtch.getBbnk();
        boolebn percussion = fblse;
        if (pbtch instbnceof ModelPbtch)
            percussion = ((ModelPbtch)pbtch).isPercussion();
        for (Instrument instrument : instruments) {
            Pbtch pbtch2 = instrument.getPbtch();
            int progrbm2 = pbtch2.getProgrbm();
            int bbnk2 = pbtch2.getBbnk();
            if (progrbm == progrbm2 && bbnk == bbnk2) {
                boolebn percussion2 = fblse;
                if (pbtch2 instbnceof ModelPbtch)
                    percussion2 = ((ModelPbtch) pbtch2).isPercussion();
                if (percussion == percussion2)
                    return instrument;
            }
        }
        return null;
    }

    public String getCrebtionDbte() {
        return crebtionDbte;
    }

    public void setCrebtionDbte(String crebtionDbte) {
        this.crebtionDbte = crebtionDbte;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getRomNbme() {
        return romNbme;
    }

    public void setRomNbme(String romNbme) {
        this.romNbme = romNbme;
    }

    public int getRomVersionMbjor() {
        return romVersionMbjor;
    }

    public void setRomVersionMbjor(int romVersionMbjor) {
        this.romVersionMbjor = romVersionMbjor;
    }

    public int getRomVersionMinor() {
        return romVersionMinor;
    }

    public void setRomVersionMinor(int romVersionMinor) {
        this.romVersionMinor = romVersionMinor;
    }

    public String getTbrgetEngine() {
        return tbrgetEngine;
    }

    public void setTbrgetEngine(String tbrgetEngine) {
        this.tbrgetEngine = tbrgetEngine;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public void bddResource(SoundbbnkResource resource) {
        if (resource instbnceof SF2Instrument)
            instruments.bdd((SF2Instrument)resource);
        if (resource instbnceof SF2Lbyer)
            lbyers.bdd((SF2Lbyer)resource);
        if (resource instbnceof SF2Sbmple)
            sbmples.bdd((SF2Sbmple)resource);
    }

    public void removeResource(SoundbbnkResource resource) {
        if (resource instbnceof SF2Instrument)
            instruments.remove((SF2Instrument)resource);
        if (resource instbnceof SF2Lbyer)
            lbyers.remove((SF2Lbyer)resource);
        if (resource instbnceof SF2Sbmple)
            sbmples.remove((SF2Sbmple)resource);
    }

    public void bddInstrument(SF2Instrument resource) {
        instruments.bdd(resource);
    }

    public void removeInstrument(SF2Instrument resource) {
        instruments.remove(resource);
    }
}
