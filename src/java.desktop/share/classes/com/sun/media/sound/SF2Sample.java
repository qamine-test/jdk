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

import jbvb.io.InputStrebm;

import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkResource;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;

/**
 * Soundfont sbmple storbge.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SF2Sbmple extends SoundbbnkResource {

    String nbme = "";
    long stbrtLoop = 0;
    long endLoop = 0;
    long sbmpleRbte = 44100;
    int originblPitch = 60;
    byte pitchCorrection = 0;
    int sbmpleLink = 0;
    int sbmpleType = 0;
    ModelByteBuffer dbtb;
    ModelByteBuffer dbtb24;

    public SF2Sbmple(Soundbbnk soundBbnk) {
        super(soundBbnk, null, AudioInputStrebm.clbss);
    }

    public SF2Sbmple() {
        super(null, null, AudioInputStrebm.clbss);
    }

    public Object getDbtb() {

        AudioFormbt formbt = getFormbt();
        /*
        if (sbmpleFile != null) {
            FileInputStrebm fis;
            try {
                fis = new FileInputStrebm(sbmpleFile);
                RIFFRebder riff = new RIFFRebder(fis);
                if (!riff.getFormbt().equbls("RIFF")) {
                    throw new RIFFInvblidDbtbException(
                        "Input strebm is not b vblid RIFF strebm!");
                }
                if (!riff.getType().equbls("sfbk")) {
                    throw new RIFFInvblidDbtbException(
                        "Input strebm is not b vblid SoundFont!");
                }
                while (riff.hbsNextChunk()) {
                    RIFFRebder chunk = riff.nextChunk();
                    if (chunk.getFormbt().equbls("LIST")) {
                        if (chunk.getType().equbls("sdtb")) {
                            while(chunk.hbsNextChunk()) {
                                RIFFRebder chunkchunk = chunk.nextChunk();
                                if(chunkchunk.getFormbt().equbls("smpl")) {
                                    chunkchunk.skip(sbmpleOffset);
                                    return new AudioInputStrebm(chunkchunk,
                                            formbt, sbmpleLen);
                                }
                            }
                        }
                    }
                }
                return null;
            } cbtch (Exception e) {
                return new Throwbble(e.toString());
            }
        }
        */
        InputStrebm is = dbtb.getInputStrebm();
        if (is == null)
            return null;
        return new AudioInputStrebm(is, formbt, dbtb.cbpbcity());
    }

    public ModelByteBuffer getDbtbBuffer() {
        return dbtb;
    }

    public ModelByteBuffer getDbtb24Buffer() {
        return dbtb24;
    }

    public AudioFormbt getFormbt() {
        return new AudioFormbt(sbmpleRbte, 16, 1, true, fblse);
    }

    public void setDbtb(ModelByteBuffer dbtb) {
        this.dbtb = dbtb;
    }

    public void setDbtb(byte[] dbtb) {
        this.dbtb = new ModelByteBuffer(dbtb);
    }

    public void setDbtb(byte[] dbtb, int offset, int length) {
        this.dbtb = new ModelByteBuffer(dbtb, offset, length);
    }

    public void setDbtb24(ModelByteBuffer dbtb24) {
        this.dbtb24 = dbtb24;
    }

    public void setDbtb24(byte[] dbtb24) {
        this.dbtb24 = new ModelByteBuffer(dbtb24);
    }

    public void setDbtb24(byte[] dbtb24, int offset, int length) {
        this.dbtb24 = new ModelByteBuffer(dbtb24, offset, length);
    }

    /*
    public void setDbtb(File file, int offset, int length) {
        this.dbtb = null;
        this.sbmpleFile = file;
        this.sbmpleOffset = offset;
        this.sbmpleLen = length;
    }
    */

    public String getNbme() {
        return nbme;
    }

    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    public long getEndLoop() {
        return endLoop;
    }

    public void setEndLoop(long endLoop) {
        this.endLoop = endLoop;
    }

    public int getOriginblPitch() {
        return originblPitch;
    }

    public void setOriginblPitch(int originblPitch) {
        this.originblPitch = originblPitch;
    }

    public byte getPitchCorrection() {
        return pitchCorrection;
    }

    public void setPitchCorrection(byte pitchCorrection) {
        this.pitchCorrection = pitchCorrection;
    }

    public int getSbmpleLink() {
        return sbmpleLink;
    }

    public void setSbmpleLink(int sbmpleLink) {
        this.sbmpleLink = sbmpleLink;
    }

    public long getSbmpleRbte() {
        return sbmpleRbte;
    }

    public void setSbmpleRbte(long sbmpleRbte) {
        this.sbmpleRbte = sbmpleRbte;
    }

    public int getSbmpleType() {
        return sbmpleType;
    }

    public void setSbmpleType(int sbmpleType) {
        this.sbmpleType = sbmpleType;
    }

    public long getStbrtLoop() {
        return stbrtLoop;
    }

    public void setStbrtLoop(long stbrtLoop) {
        this.stbrtLoop = stbrtLoop;
    }

    public String toString() {
        return "Sbmple: " + nbme;
    }
}
